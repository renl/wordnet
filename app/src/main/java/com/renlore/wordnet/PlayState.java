package com.renlore.wordnet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
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
    private static final int NUM_SLOTS = 2;
    private static final int SLOT_SPACING = 120;
    private static final int NUM_SELECTED_WORDS = 4;
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
        if (slotTracker[slot] > 100) {
            slotTracker[slot] = 0;
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

    private void addLetters() {
        for (int i = 0; i < NUM_SLOTS; i++) {
            slotTracker[i]++;
        }
        int chance = randGen.nextInt(100);
        if (chance <= 1) {
            int slot = getSlot();
            if (slot != -1) {
                Rect rect = GraphicsHandler.genLetterRect(ObjectsInfo.balloonStartPointLeft.x, letterSlot(slot, SLOT_SPACING) - 60);
                if (letterPoolInd >= letterPool.length()) letterPoolInd = 0;
                char c = letterPool.charAt(letterPoolInd++);
                letters.add(new Letter(GraphicsHandler.getLetterBMfromChar(c), c, ObjectsInfo.balloonDirMovingRight, rect));
            }
        } else if (chance <= 3) {
            int slot = getSlot();
            if (slot != -1) {
                Rect rect = GraphicsHandler.genLetterRect(ObjectsInfo.balloonStartPointRight.x, letterSlot(slot, SLOT_SPACING));
                if (letterPoolInd >= letterPool.length()) letterPoolInd = 0;
                char c = letterPool.charAt(letterPoolInd++);
                letters.add(new Letter(GraphicsHandler.getLetterBMfromChar(c), c, ObjectsInfo.balloonDirMovingLeft, rect));
            }

        }
    }

    private void addBullets() {
        bullets.addAll(bulletsToAdd);
        bulletsToAdd.clear();
    }

    private void updateCaughtLetters(List<Letter> foundLetters) {
        for (Letter thisLetter : caughtLetters) {
            thisLetter.update();
            if (thisLetter.wpReached()) {
                foundLetters.add(thisLetter);
            }
        }
        caughtLetters.removeAll(foundLetters);
        foundLetters.clear();
    }

    private void updateLetter(List<Letter> foundLetters) {
        for (Letter thisLetter : letters) {
            thisLetter.update();
            if (!Rect.intersects(ObjectsInfo.activeZone, thisLetter.getRect())) {
//                if (thisLetter.getRect().bottom <= 0) {
                foundLetters.add(thisLetter);
            }
        }
        letters.removeAll(foundLetters);
        foundLetters.clear();
    }

    private void updateBullets(List<Bullet> foundBullets, List<Letter> foundLetters) {
        for (Bullet thisBullet : bullets) {
            thisBullet.update();
            if (!ObjectsInfo.activeZone.contains(thisBullet.getDrawRect())) {
//            if (thisBullet.getY() <= 0 || thisBullet.getX() <= 0 || thisBullet.getX() >= 480) {
                foundBullets.add(thisBullet);
            } else {
                for (Letter thisLetter : letters) {
                    if (thisLetter.getRect().contains(thisBullet.getHitboxRect())) {
                        Log.d(TAG, letterPool);
                        AudioHandler.playNetted();
                        foundBullets.add(thisBullet);
                        thisLetter.addWP(ObjectsInfo.balloonReceivePoint);
                        thisLetter.setSpeed(10);
                        foundLetters.add(thisLetter);
                        caughtLetters.add(thisLetter);
                        capturedLetters += thisLetter.getLetter();
                        if (capturedLetters.length() > 10) {
                            capturedLetters = capturedLetters.substring(1);
                        }
                        captures.add(new Captured(GraphicsHandler.getCapturenet(),
                                GraphicsHandler.genCapturedRect(thisLetter.getRect().centerX(), thisLetter.getRect().centerY()),
                                50, thisLetter));
                    }
                }
            }
        }
        bullets.removeAll(foundBullets);
        letters.removeAll(foundLetters);
    }

    private void updateWordCapture(List<String> foundString) {
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
            while (selectedWords.size() < NUM_SELECTED_WORDS) {
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
    }

    private void updateCaptured(List<Captured> foundCaptured) {
        for (Captured thisCaptured : captures) {
            if (thisCaptured.update() <= 0) {
                foundCaptured.add(thisCaptured);
            }
        }
        captures.removeAll(foundCaptured);
    }

    @Override
    public void update() {
        List<Letter> foundLetters = new ArrayList<Letter>();
        List<Bullet> foundBullets = new ArrayList<Bullet>();
        List<String> foundString = new ArrayList<String>();
        List<Captured> foundCaptured = new ArrayList<Captured>();

        addBullets();
        addLetters();
        updateCaughtLetters(foundLetters);
        updateLetter(foundLetters);
        updateBullets(foundBullets, foundLetters);
        updateWordCapture(foundString);
        updateCaptured(foundCaptured);
    }

    @Override
    public void render(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawBitmap(GraphicsHandler.getBackground(), GameAreaManager.dix(0), GameAreaManager.diy(0), null);
        canvas.drawBitmap(GraphicsHandler.getCharacter(),
                GameAreaManager.getCenterX() - (GraphicsHandler.getCharacter().getWidth() / 2),
                GameAreaManager.diy(420) - GraphicsHandler.getCharacter().getHeight(),
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
        paint.setColor(0xFF966026);
        paint.setTypeface(Typeface.create("Jokerman", Typeface.BOLD));
        Rect bounds = new Rect();
        paint.setTextSize((int) (26 * GameAreaManager.getScale()));
        paint.getTextBounds("A", 0, 1, bounds);
        if (capturedLetters.length() > 0) {
//            paint.getTextBounds(capturedLetters, 0, capturedLetters.length() - 1, bounds);
            canvas.drawText(capturedLetters,
                    GameAreaManager.dix(ObjectsInfo.board5Center.x) - paint.measureText(capturedLetters) / 2,
                    GameAreaManager.diy(ObjectsInfo.board5Center.y) + bounds.height() / 2,
                    paint);
        }
//        paint.getTextBounds(selectedWords.get(0), 0, selectedWords.get(0).length() - 1, bounds);
        canvas.drawText(selectedWords.get(0),
                GameAreaManager.dix(ObjectsInfo.board1Center.x) - paint.measureText(selectedWords.get(0)) / 2,
                GameAreaManager.diy(ObjectsInfo.board1Center.y) + bounds.height() / 2,
                paint);
//        paint.getTextBounds(selectedWords.get(1), 0, selectedWords.get(1).length() - 1, bounds);
        canvas.drawText(selectedWords.get(1),
                GameAreaManager.dix(ObjectsInfo.board2Center.x) - paint.measureText(selectedWords.get(1)) / 2,
                GameAreaManager.diy(ObjectsInfo.board2Center.y) + bounds.height() / 2,
                paint);
//        paint.getTextBounds(selectedWords.get(2), 0, selectedWords.get(2).length() - 1, bounds);
        canvas.drawText(selectedWords.get(2),
                GameAreaManager.dix(ObjectsInfo.board3Center.x) - paint.measureText(selectedWords.get(2)) / 2,
                GameAreaManager.diy(ObjectsInfo.board3Center.y) + bounds.height() / 2,
                paint);
//        paint.getTextBounds(selectedWords.get(3), 0, selectedWords.get(3).length() - 1, bounds);
        canvas.drawText(selectedWords.get(3),
                GameAreaManager.dix(ObjectsInfo.board4Center.x) - paint.measureText(selectedWords.get(3)) / 2,
                GameAreaManager.diy(ObjectsInfo.board4Center.y) + bounds.height() / 2,
                paint);
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
                double angle = Math.atan2(GameAreaManager.dY2bY((int) e.getY()) - 370, GameAreaManager.dX2bX((int) e.getX()) - GameAreaManager.getBaseCenterX());
                bulletsToAdd.add(new Bullet(GraphicsHandler.getThrownnet(),
                        angle,
                        GraphicsHandler.genBulletRect(GameAreaManager.getBaseCenterX(), 370)
                ));
            }
        }

        return true;
    }
}
