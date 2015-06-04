package com.renlore.wordnet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
    private Bitmap bA = BitmapFactory.decodeResource(getResources(), R.drawable.a);
    private Bitmap bB = BitmapFactory.decodeResource(getResources(), R.drawable.b);
    private Bitmap bC = BitmapFactory.decodeResource(getResources(), R.drawable.c);
    private Bitmap bD = BitmapFactory.decodeResource(getResources(), R.drawable.d);
    private Bitmap bE = BitmapFactory.decodeResource(getResources(), R.drawable.e);
    private Bitmap bF = BitmapFactory.decodeResource(getResources(), R.drawable.f);
    private Bitmap bG = BitmapFactory.decodeResource(getResources(), R.drawable.g);
    private Bitmap bH = BitmapFactory.decodeResource(getResources(), R.drawable.h);
    private Bitmap bI = BitmapFactory.decodeResource(getResources(), R.drawable.i);
    private Bitmap bJ = BitmapFactory.decodeResource(getResources(), R.drawable.j);
    private Bitmap bK = BitmapFactory.decodeResource(getResources(), R.drawable.k);
    private Bitmap bL = BitmapFactory.decodeResource(getResources(), R.drawable.l);
    private Bitmap bM = BitmapFactory.decodeResource(getResources(), R.drawable.m);
    private Bitmap bN = BitmapFactory.decodeResource(getResources(), R.drawable.n);
    private Bitmap bO = BitmapFactory.decodeResource(getResources(), R.drawable.o);
    private Bitmap bP = BitmapFactory.decodeResource(getResources(), R.drawable.p);
    private Bitmap bQ = BitmapFactory.decodeResource(getResources(), R.drawable.q);
    private Bitmap bR = BitmapFactory.decodeResource(getResources(), R.drawable.r);
    private Bitmap bS = BitmapFactory.decodeResource(getResources(), R.drawable.s);
    private Bitmap bT = BitmapFactory.decodeResource(getResources(), R.drawable.t);
    private Bitmap bU = BitmapFactory.decodeResource(getResources(), R.drawable.u);
    private Bitmap bV = BitmapFactory.decodeResource(getResources(), R.drawable.v);
    private Bitmap bW = BitmapFactory.decodeResource(getResources(), R.drawable.w);
    private Bitmap bX = BitmapFactory.decodeResource(getResources(), R.drawable.x);
    private Bitmap bY = BitmapFactory.decodeResource(getResources(), R.drawable.y);
    private Bitmap bZ = BitmapFactory.decodeResource(getResources(), R.drawable.z);
    private Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
    private Bitmap character = BitmapFactory.decodeResource(getResources(), R.drawable.character);
    private Bitmap thrownnet = BitmapFactory.decodeResource(getResources(), R.drawable.thrownnet);
    private Bitmap capturenet = BitmapFactory.decodeResource(getResources(), R.drawable.capturenet);
    private Random randGen = new Random();
    private float scale;
    private GameAreaManager gam;
    private int[] slotTracker = new int[NUM_SLOTS];

    private static final int TITLE_SCREEN = 0;
    private static final int LEVEL_SCREEN = 1;
    private static final int REPLAY_SCREEN = 2;
    private static final int END_SCREEN = 3;
    private int gameState = TITLE_SCREEN;
    private static final int LETTER_SIZE = 60;
    private static final int CAPTURED_SIZE = 60;
    private static final int BULLET_SIZE = 40;
    private String[] wordBonus;
    private String capturedLetters = "";
    private List<String> selectedWords = new ArrayList<String>();

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
        background = Bitmap.createScaledBitmap(background, gam.getWidth(), gam.getHeight(), true);
        character = Bitmap.createScaledBitmap(character, gam.toScale(50), gam.toScale(50), true);
        thrownnet = Bitmap.createScaledBitmap(thrownnet, gam.toScale(BULLET_SIZE), gam.toScale(BULLET_SIZE), true);
        capturenet = Bitmap.createScaledBitmap(capturenet, gam.toScale(60), gam.toScale(60), true);
        bA = Bitmap.createScaledBitmap(bA, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bB = Bitmap.createScaledBitmap(bB, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bC = Bitmap.createScaledBitmap(bC, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bD = Bitmap.createScaledBitmap(bD, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bE = Bitmap.createScaledBitmap(bE, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bF = Bitmap.createScaledBitmap(bF, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bG = Bitmap.createScaledBitmap(bG, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bH = Bitmap.createScaledBitmap(bH, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bI = Bitmap.createScaledBitmap(bI, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bJ = Bitmap.createScaledBitmap(bJ, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bK = Bitmap.createScaledBitmap(bK, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bL = Bitmap.createScaledBitmap(bL, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bM = Bitmap.createScaledBitmap(bM, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bN = Bitmap.createScaledBitmap(bN, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bO = Bitmap.createScaledBitmap(bO, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bP = Bitmap.createScaledBitmap(bP, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bQ = Bitmap.createScaledBitmap(bQ, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bR = Bitmap.createScaledBitmap(bR, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bS = Bitmap.createScaledBitmap(bS, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bT = Bitmap.createScaledBitmap(bT, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bU = Bitmap.createScaledBitmap(bU, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bV = Bitmap.createScaledBitmap(bV, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bW = Bitmap.createScaledBitmap(bW, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bX = Bitmap.createScaledBitmap(bX, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bY = Bitmap.createScaledBitmap(bY, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        bZ = Bitmap.createScaledBitmap(bZ, gam.toScale(LETTER_SIZE), gam.toScale(LETTER_SIZE), true);
        Arrays.fill(slotTracker, 0);

        for (int i = 0; i < 5; i++) {
            String word = wordBonus[randGen.nextInt(wordBonus.length)];
            while (selectedWords.contains(word)) {
                word = wordBonus[randGen.nextInt(wordBonus.length)];
            }
            selectedWords.add(word);
        }
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

                double angle = Math.atan2(gam.dY2bY((int) event.getY()) - 750, gam.dX2bX((int) event.getX()) - 240);
                bulletsToAdd.add(new Bullet(thrownnet,
                        angle,
                        new Rect(240 - BULLET_SIZE / 2,
                                750 - BULLET_SIZE / 2,
                                240 + BULLET_SIZE / 2,
                                750 + BULLET_SIZE / 2)
                ));
            }
        }
    }

    private void onTouchEventReplayScreen(MotionEvent event) {

    }

    private void onTouchEventEndScreen(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
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
        canvas.drawBitmap(background, gam.dix(0), gam.diy(0), null);
        canvas.drawBitmap(character,
                gam.getCenterX() - (character.getWidth() / 2),
                gam.getBottom() - character.getHeight(),
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
        paint.setColor(Color.WHITE);
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
//                Log.d(TAG, "Creating letter");
                Rect rect = new Rect(letterSlot(slot, SLOT_SPACING) - LETTER_SIZE / 2,
                        500 - LETTER_SIZE / 2,
                        letterSlot(slot, SLOT_SPACING) + LETTER_SIZE / 2,
                        500 + LETTER_SIZE / 2);
                switch (randGen.nextInt(26)) {
                    case 0:
                        letters.add(new Letter(bA, 'A', -Math.PI / 2, rect));
                        break;
                    case 1:
                        letters.add(new Letter(bB, 'B', -Math.PI / 2, rect));
                        break;
                    case 2:
                        letters.add(new Letter(bC, 'C', -Math.PI / 2, rect));
                        break;
                    case 3:
                        letters.add(new Letter(bD, 'D', -Math.PI / 2, rect));
                        break;
                    case 4:
                        letters.add(new Letter(bE, 'E', -Math.PI / 2, rect));
                        break;
                    case 5:
                        letters.add(new Letter(bF, 'F', -Math.PI / 2, rect));
                        break;
                    case 6:
                        letters.add(new Letter(bG, 'G', -Math.PI / 2, rect));
                        break;
                    case 7:
                        letters.add(new Letter(bH, 'H', -Math.PI / 2, rect));
                        break;
                    case 8:
                        letters.add(new Letter(bI, 'I', -Math.PI / 2, rect));
                        break;
                    case 9:
                        letters.add(new Letter(bJ, 'J', -Math.PI / 2, rect));
                        break;
                    case 10:
                        letters.add(new Letter(bK, 'K', -Math.PI / 2, rect));
                        break;
                    case 11:
                        letters.add(new Letter(bL, 'L', -Math.PI / 2, rect));
                        break;
                    case 12:
                        letters.add(new Letter(bM, 'M', -Math.PI / 2, rect));
                        break;
                    case 13:
                        letters.add(new Letter(bN, 'N', -Math.PI / 2, rect));
                        break;
                    case 14:
                        letters.add(new Letter(bO, 'O', -Math.PI / 2, rect));
                        break;
                    case 15:
                        letters.add(new Letter(bP, 'P', -Math.PI / 2, rect));
                        break;
                    case 16:
                        letters.add(new Letter(bQ, 'Q', -Math.PI / 2, rect));
                        break;
                    case 17:
                        letters.add(new Letter(bR, 'R', -Math.PI / 2, rect));
                        break;
                    case 18:
                        letters.add(new Letter(bS, 'S', -Math.PI / 2, rect));
                        break;
                    case 19:
                        letters.add(new Letter(bT, 'T', -Math.PI / 2, rect));
                        break;
                    case 20:
                        letters.add(new Letter(bU, 'U', -Math.PI / 2, rect));
                        break;
                    case 21:
                        letters.add(new Letter(bV, 'V', -Math.PI / 2, rect));
                        break;
                    case 22:
                        letters.add(new Letter(bW, 'W', -Math.PI / 2, rect));
                        break;
                    case 23:
                        letters.add(new Letter(bX, 'X', -Math.PI / 2, rect));
                        break;
                    case 24:
                        letters.add(new Letter(bY, 'Y', -Math.PI / 2, rect));
                        break;
                    case 25:
                        letters.add(new Letter(bZ, 'Z', -Math.PI / 2, rect));
                        break;
                }
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
                        foundBullets.add(thisBullet);
                        foundLetters.add(thisLetter);
                        capturedLetters += thisLetter.getLetter();
                        if (capturedLetters.length() > 10) {
                            capturedLetters = capturedLetters.substring(1);
                        }
                        captures.add(new Captured(capturenet,
                                new Rect(thisLetter.getRect().centerX() - CAPTURED_SIZE / 2,
                                        thisLetter.getRect().centerY() - CAPTURED_SIZE / 2,
                                        thisLetter.getRect().centerX() + CAPTURED_SIZE / 2,
                                        thisLetter.getRect().centerY() + CAPTURED_SIZE / 2),
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
                foundString.add(thisString);
            }
        }
        selectedWords.removeAll(foundString);
        while (selectedWords.size() < 5) {
            String word = wordBonus[randGen.nextInt(wordBonus.length)];
            while (selectedWords.contains(word)) {
                word = wordBonus[randGen.nextInt(wordBonus.length)];
            }
            selectedWords.add(word);
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