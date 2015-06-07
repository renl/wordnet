package com.renlore.wordnet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Ng on 6/7/2015.
 */
public class PlayState extends State {
    private static final String TAG = PlayState.class.getSimpleName();
    private static final int NUM_SLOTS = 11;
    private static final int SLOT_SPACING = 40;
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Bullet> bulletsToAdd = new ArrayList<Bullet>();
    private List<Letter> letters = new ArrayList<Letter>();
    private List<Letter> caughtLetters = new ArrayList<Letter>();
    private List<Captured> captures = new ArrayList<Captured>();
    private int[] slotTracker = new int[NUM_SLOTS];
    private Random randGen = new Random();
    private String letterPool;
    private int letterPoolInd = 0;
    private String[] wordBonus;
    private String capturedLetters = "";
    private List<String> selectedWords = new ArrayList<String>();

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

    @Override
    public void init() {
        wordBonus = WordNet.context.getResources().getStringArray(R.array.wordBonus);
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
    }

    @Override
    public void update() {
        for (int i = 0; i < NUM_SLOTS; i++) {
            slotTracker[i]++;
        }
        bullets.addAll(bulletsToAdd);
        bulletsToAdd.clear();
        if (randGen.nextInt(100) <= 5) {
            int slot = getSlot();
            if (slot != -1) {
                Rect rect = GraphicsHandler.genLetterRect(letterSlot(slot, SLOT_SPACING), 500);
                if (letterPoolInd >= letterPool.length()) letterPoolInd = 0;
                char c = letterPool.charAt(letterPoolInd++);
                letters.add(new Letter(GraphicsHandler.getLetterBMfromChar(c), c, Math.toRadians(270), rect));
            }
        }
        List<Letter> foundLetters = new ArrayList<Letter>();
        for (Letter thisLetter : caughtLetters) {
            thisLetter.update();
            if (thisLetter.wpReached()) {
                foundLetters.add(thisLetter);
            }
        }
        caughtLetters.removeAll(foundLetters);
        foundLetters.clear();

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
                        AudioHandler.playNetted();
                        foundBullets.add(thisBullet);
                        thisLetter.addWP(new Point(240, 750));
                        thisLetter.setSpeed(10);
                        foundLetters.add(thisLetter);
                        caughtLetters.add(thisLetter);
                        capturedLetters += thisLetter.getLetter();
                        if (capturedLetters.length() > 10) {
                            capturedLetters = capturedLetters.substring(1);
                        }
                        captures.add(new Captured(GraphicsHandler.getCapturenet(),
                                GraphicsHandler.genCapturedRect(thisLetter.getRect().centerX(), thisLetter.getRect().centerY()),
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
                AudioHandler.playAchieve();
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

    @Override
    public void render(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawBitmap(GraphicsHandler.getBackground(), GameAreaManager.dix(0), GameAreaManager.diy(0), null);
        canvas.drawBitmap(GraphicsHandler.getCharacter(),
                GameAreaManager.getCenterX() - (GraphicsHandler.getCharacter().getWidth() / 2),
                GameAreaManager.getBottom() - GraphicsHandler.getCharacter().getHeight(),
                null);
        paint.setColor(Color.GRAY);
        canvas.drawRect(GameAreaManager.getRight() - GameAreaManager.toScale(100), GameAreaManager.getBottom() - GameAreaManager.toScale(50),
                GameAreaManager.getRight(), GameAreaManager.getBottom(), paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText("QUIT", GameAreaManager.getRight() - GameAreaManager.toScale(90),
                GameAreaManager.getBottom() - GameAreaManager.toScale(10), paint);
        for (Bullet thisBullet : bullets) {
            canvas.drawBitmap(thisBullet.getBitmap(), null, GameAreaManager.toScaleRect(thisBullet.getDrawRect()), null);
        }
        for (Letter thisLetter : letters) {
            canvas.drawBitmap(thisLetter.getBitmap(), null, GameAreaManager.toScaleRect(thisLetter.getRect()), null);
        }
        for (Captured thisCaptured : captures) {
            canvas.drawBitmap(thisCaptured.getBitmap(), null, GameAreaManager.toScaleRect(thisCaptured.getRect()), null);
        }
        for (Letter thisLetter : caughtLetters) {
            canvas.drawBitmap(thisLetter.getBitmap(), null, GameAreaManager.toScaleRect(thisLetter.getRect()), null);
        }
        paint.setColor(Color.RED);
        paint.setTextSize((int) (48 * GameAreaManager.getScale()));
        canvas.drawText(capturedLetters, GameAreaManager.dix(0), GameAreaManager.diy(600), paint);
        paint.setTextSize((int) (40 * GameAreaManager.getScale()));
        canvas.drawText(selectedWords.get(0), GameAreaManager.dix(0), GameAreaManager.diy(680), paint);
        canvas.drawText(selectedWords.get(1), GameAreaManager.dix(0), GameAreaManager.diy(730), paint);
        canvas.drawText(selectedWords.get(2), GameAreaManager.dix(150), GameAreaManager.diy(680), paint);
        canvas.drawText(selectedWords.get(3), GameAreaManager.dix(300), GameAreaManager.diy(680), paint);
        canvas.drawText(selectedWords.get(4), GameAreaManager.dix(300), GameAreaManager.diy(730), paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, GameAreaManager.getDeviceWidth(), GameAreaManager.getTop(), paint);
        canvas.drawRect(0, 0, GameAreaManager.getLeft(), GameAreaManager.getDeviceHeight(), paint);
        canvas.drawRect(0, GameAreaManager.getBottom(), GameAreaManager.getDeviceWidth(), GameAreaManager.getDeviceHeight(), paint);
        canvas.drawRect(GameAreaManager.getRight(), 0, GameAreaManager.getDeviceWidth(), GameAreaManager.getDeviceHeight(), paint);
    }

    @Override
    public boolean onTouch(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            if ((e.getY() > GameAreaManager.getBottom() - GameAreaManager.toScale(50)) && (e.getX() > GameAreaManager.getRight() - GameAreaManager.toScale(100))) {
                setCurrentState(new EndState());
            } else {
                Log.d(TAG, "X: " + e.getX() + ", Y: " + e.getY());
                AudioHandler.playSwosh();
                double angle = Math.atan2(GameAreaManager.dY2bY((int) e.getY()) - 750, GameAreaManager.dX2bX((int) e.getX()) - 240);
                bulletsToAdd.add(new Bullet(GraphicsHandler.getThrownnet(),
                        angle,
                        GraphicsHandler.genBulletRect(240, 750)
                ));
            }
        }

        return true;
    }
}
