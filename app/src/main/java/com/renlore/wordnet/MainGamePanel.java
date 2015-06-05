package com.renlore.wordnet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Ng on 5/31/2015.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private static final String TAG = MainGamePanel.class.getSimpleName();
    private static final int NUM_SLOTS = 11;
    private static final int SLOT_SPACING = 40;
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Bullet> bulletsToAdd = new ArrayList<Bullet>();
    private List<Letter> letters = new ArrayList<Letter>();
    private List<Captured> captures = new ArrayList<Captured>();
    private GraphicsHandler gh;
    private Random randGen = new Random();
    private float scale;
    private GameAreaManager gam;
    private int[] slotTracker = new int[NUM_SLOTS];

    private static final int TITLE_SCREEN = 0;
    private static final int LEVEL_SCREEN = 1;
    private static final int REPLAY_SCREEN = 2;
    private static final int END_SCREEN = 3;
    private int gameState = TITLE_SCREEN;

    private String[] wordBonus;
    private String capturedLetters = "";
    private List<String> selectedWords = new ArrayList<String>();

    private MediaPlayer music, swosh, netted, achieve;
    private String letterPool;
    private int letterPoolInd = 0;

    public MainGamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGame(context);
    }

    public MainGamePanel(Context context) {
        super(context);
        initGame(context);
    }

    private void initGame(Context context) {
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        wordBonus = context.getResources().getStringArray(R.array.wordBonus);
        gam = new GameAreaManager(context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels);
        scale = gam.getScale();
        gh = new GraphicsHandler(context, gam);
        Arrays.fill(slotTracker, 0);

        letterPool = new String();
        for (int i = 0; i < 5; i++) {
            String word = wordBonus[randGen.nextInt(wordBonus.length)];
            while (selectedWords.contains(word)) {
                word = wordBonus[randGen.nextInt(wordBonus.length)];
            }
            for (char letter : word.toCharArray()) {
                if (!letterPool.contains(Character.toString(letter))) {
                    letterPool += letter;
                }
            }
            selectedWords.add(word);
        }

        netted = MediaPlayer.create(context.getApplicationContext(), R.raw.netted);
        swosh = MediaPlayer.create(context.getApplicationContext(), R.raw.swosh);
        achieve = MediaPlayer.create(context.getApplicationContext(), R.raw.achievement);
        music = MediaPlayer.create(context.getApplicationContext(), R.raw.whimsicalpopsicle);
        music.setVolume(0.3f, 0.3f);
        music.start();
        music.setLooping(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "Width: " + getMeasuredWidth() + ", Height: " + getMeasuredHeight() + ", Proportion: " + (float) getMeasuredHeight() / (float) getMeasuredWidth());
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    private void onTouchEventTitleScreen(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            gameState = LEVEL_SCREEN;
        }
    }

    private void onTouchEventLevelScreen(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if ((event.getY() > gam.getBottom() - gam.toScale(50)) && (event.getX() > gam.getRight() - gam.toScale(100))) {
                gameState = END_SCREEN;
            } else {
                Log.d(TAG, "X: " + event.getX() + ", Y: " + event.getY());
                if (swosh.isPlaying()) {
                    swosh.seekTo(0);
                } else {
                    swosh.start();
                }
                double angle = Math.atan2(gam.dY2bY((int) event.getY()) - 750, gam.dX2bX((int) event.getX()) - 240);
                bulletsToAdd.add(new Bullet(gh.getThrownnet(),
                        angle,
                        gh.genBulletRect(240, 750)
                ));
            }
        }
    }

    private void onTouchEventReplayScreen(MotionEvent event) {

    }

    private void onTouchEventEndScreen(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            music.release();
            swosh.release();
            achieve.release();
            netted.release();
            thread.setRunning(false);
            ((Activity) getContext()).finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (gameState) {
            case TITLE_SCREEN:
                onTouchEventTitleScreen(event);
                break;
            case LEVEL_SCREEN:
                onTouchEventLevelScreen(event);
                break;
            case REPLAY_SCREEN:
                onTouchEventReplayScreen(event);
                break;
            case END_SCREEN:
                onTouchEventEndScreen(event);
                break;
        }
        return true;
    }

    private void renderTitleScreen(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawColor(Color.BLUE);
        paint.setTextSize((int) (48 * scale));
        canvas.drawText("WORDNET", gam.dix(0), gam.getCenterY() - gam.toScale(30), paint);
        paint.setTextSize((int) (16 * scale));
        canvas.drawText("PRESS ANYWHERE TO START", gam.dix(0), gam.getCenterY() + gam.toScale(30), paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(gam.dix(0),
                gam.diy(0),
                gam.dix(50),
                gam.diy(50),
                paint);
        canvas.drawRect(gam.getRight() - gam.toScale(50),
                gam.diy(0),
                gam.getRight(),
                gam.diy(50),
                paint);
        canvas.drawRect(gam.dix(0),
                gam.getBottom() - gam.toScale(50),
                gam.dix(50),
                gam.getBottom(),
                paint);
        canvas.drawRect(gam.getRight() - gam.toScale(50),
                gam.getBottom() - gam.toScale(50),
                gam.getRight(),
                gam.getBottom(),
                paint);
    }

    private void renderLevelScreen(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawBitmap(gh.getBackground(), gam.dix(0), gam.diy(0), null);
        canvas.drawBitmap(gh.getCharacter(),
                gam.getCenterX() - (gh.getCharacter().getWidth() / 2),
                gam.getBottom() - gh.getCharacter().getHeight(),
                null);
        paint.setColor(Color.GRAY);
        canvas.drawRect(gam.getRight() - gam.toScale(100), gam.getBottom() - gam.toScale(50),
                gam.getRight(), gam.getBottom(), paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText("QUIT", gam.getRight() - gam.toScale(90),
                gam.getBottom() - gam.toScale(10), paint);
        for (Bullet thisBullet : bullets) {
            canvas.drawBitmap(thisBullet.getBitmap(), null, gam.toScaleRect(thisBullet.getDrawRect()), null);
        }
        for (Letter thisLetter : letters) {
            canvas.drawBitmap(thisLetter.getBitmap(), null, gam.toScaleRect(thisLetter.getRect()), null);
        }
        for (Captured thisCaptured : captures) {
            canvas.drawBitmap(thisCaptured.getBitmap(), null, gam.toScaleRect(thisCaptured.getRect()), null);
        }
        paint.setColor(Color.RED);
        paint.setTextSize((int) (48 * scale));
        canvas.drawText(capturedLetters, gam.dix(0), gam.diy(600), paint);
        paint.setTextSize((int) (40 * scale));
        canvas.drawText(selectedWords.get(0), gam.dix(0), gam.diy(680), paint);
        canvas.drawText(selectedWords.get(1), gam.dix(0), gam.diy(730), paint);
        canvas.drawText(selectedWords.get(2), gam.dix(150), gam.diy(680), paint);
        canvas.drawText(selectedWords.get(3), gam.dix(300), gam.diy(680), paint);
        canvas.drawText(selectedWords.get(4), gam.dix(300), gam.diy(730), paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, gam.getDeviceWidth(), gam.getTop(), paint);
        canvas.drawRect(0, 0, gam.getLeft(), gam.getDeviceHeight(), paint);
        canvas.drawRect(0, gam.getBottom(), gam.getDeviceWidth(), gam.getDeviceHeight(), paint);
        canvas.drawRect(gam.getRight(), 0, gam.getDeviceWidth(), gam.getDeviceHeight(), paint);
    }

    private void renderReplayScreen(Canvas canvas) {

    }

    private void renderEndScreen(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
    }

    protected void render(Canvas canvas) {
        switch (gameState) {
            case TITLE_SCREEN:
                renderTitleScreen(canvas);
                break;
            case LEVEL_SCREEN:
                renderLevelScreen(canvas);
                break;
            case REPLAY_SCREEN:
                renderReplayScreen(canvas);
                break;
            case END_SCREEN:
                renderEndScreen(canvas);
                break;
        }
    }

    private void updateTitleScreen(long tickcount) {

    }

    private int letterSlot(int slot, int slotSpacing) {
        return slotSpacing + slotSpacing * slot;
    }


    private int getSlot() {
        int slot = randGen.nextInt(NUM_SLOTS);
        if (slotTracker[slot] > 50) {
            slotTracker[slot] = 0;
            if (slot > 0) {
                slotTracker[slot - 1] = 0;
            }
            if (slot < NUM_SLOTS - 1) {
                slotTracker[slot + 1] = 0;
            }
            return slot;
        } else {
            return -1;
        }
    }

    private void updateLevelScreen(long tickcount) {
        for (int i = 0; i < NUM_SLOTS; i++) {
            slotTracker[i]++;
        }
        bullets.addAll(bulletsToAdd);
        bulletsToAdd.clear();
        if (randGen.nextInt(100) <= 5) {
            int slot = getSlot();
            if (slot != -1) {
                Rect rect = gh.genLetterRect(letterSlot(slot, SLOT_SPACING), 500);
                if (letterPoolInd >= letterPool.length()) letterPoolInd = 0;
                char c = letterPool.charAt(letterPoolInd++);
                letters.add(new Letter(gh.getLetterBMfromChar(c), c, Math.toRadians(270), rect));
            }
        }

        List<Letter> foundLetters = new ArrayList<Letter>();
        for (Letter thisLetter : letters) {
            thisLetter.update();
            if (thisLetter.getRect().bottom <= 0) {
                foundLetters.add(thisLetter);
            }
        }
        letters.removeAll(foundLetters);
        foundLetters.clear();

        List<Bullet> foundBullets = new ArrayList<Bullet>();
        for (Bullet thisBullet : bullets) {
            thisBullet.update();
            if (thisBullet.getY() <= 0 || thisBullet.getX() <= 0 || thisBullet.getX() >= 480) {
                foundBullets.add(thisBullet);
            } else {
                for (Letter thisLetter : letters) {
                    if (thisLetter.getRect().contains(thisBullet.getHitboxRect())) {
                        Log.d(TAG, letterPool);
                        if (netted.isPlaying()) {
                            netted.seekTo(0);
                        } else {
                            netted.start();
                        }
                        foundBullets.add(thisBullet);
                        foundLetters.add(thisLetter);
                        capturedLetters += thisLetter.getLetter();
                        if (capturedLetters.length() > 10) {
                            capturedLetters = capturedLetters.substring(1);
                        }
                        captures.add(new Captured(gh.getCapturenet(),
                                gh.genCapturedRect(thisLetter.getRect().centerX(), thisLetter.getRect().centerY()),
                                50));
                    }
                }
            }
        }
        bullets.removeAll(foundBullets);
        letters.removeAll(foundLetters);

        List<String> foundString = new ArrayList<String>();
        for (String thisString : selectedWords) {
            if (capturedLetters.contains(thisString)) {
                if (!achieve.isPlaying()) {
                    achieve.start();
                }
                foundString.add(thisString);
            }
        }
        if (!foundString.isEmpty()) {
            letterPool = new String();
            selectedWords.removeAll(foundString);
            for (String word : selectedWords) {
                for (char letter : word.toCharArray()) {
                    if (!letterPool.contains(Character.toString(letter))) {
                        letterPool += letter;
                    }
                }
            }
            while (selectedWords.size() < 5) {
                String word = wordBonus[randGen.nextInt(wordBonus.length)];
                while (selectedWords.contains(word)) {
                    word = wordBonus[randGen.nextInt(wordBonus.length)];
                }
                selectedWords.add(word);
                for (char letter : word.toCharArray()) {
                    if (!letterPool.contains(Character.toString(letter))) {
                        letterPool += letter;
                    }
                }
            }
        }
        List<Captured> foundCaptured = new ArrayList<Captured>();
        for (Captured thisCaptured : captures) {
            if (thisCaptured.update() <= 0) {
                foundCaptured.add(thisCaptured);
            }
        }
        captures.removeAll(foundCaptured);
    }

    private void updateReplayScreen(long tickcount) {

    }

    private void updateEndScreen(long tickcount) {

    }

    public void update(long tickcount) {
        switch (gameState) {
            case TITLE_SCREEN:
                updateTitleScreen(tickcount);
                break;
            case LEVEL_SCREEN:
                updateLevelScreen(tickcount);
                break;
            case REPLAY_SCREEN:
                updateReplayScreen(tickcount);
                break;
            case END_SCREEN:
                updateEndScreen(tickcount);
                break;
        }
    }
}