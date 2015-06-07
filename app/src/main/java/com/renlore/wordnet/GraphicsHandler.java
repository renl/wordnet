package com.renlore.wordnet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Ng on 6/5/2015.
 */
public class GraphicsHandler {
    private static final Point LETTER_SIZE = new Point(40, 60);
    private static final int CAPTURED_SIZE = 60;
    private static final int BULLET_SIZE = 40;
    private static final int CHARACTER_SIZE = 50;

    private static Bitmap bA;
    private static Bitmap bB;
    private static Bitmap bC;
    private static Bitmap bD;
    private static Bitmap bE;
    private static Bitmap bF;
    private static Bitmap bG;
    private static Bitmap bH;
    private static Bitmap bI;
    private static Bitmap bJ;
    private static Bitmap bK;
    private static Bitmap bL;
    private static Bitmap bM;
    private static Bitmap bN;
    private static Bitmap bO;
    private static Bitmap bP;
    private static Bitmap bQ;
    private static Bitmap bR;
    private static Bitmap bS;
    private static Bitmap bT;
    private static Bitmap bU;
    private static Bitmap bV;
    private static Bitmap bW;
    private static Bitmap bX;
    private static Bitmap bY;
    private static Bitmap bZ;
    private static Bitmap background;
    private static Bitmap character;
    private static Bitmap thrownnet;
    private static Bitmap capturenet;

    public static void load() {
        bA = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.a), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bB = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.b), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bC = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.c), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bD = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.d), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bE = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.e), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bF = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.f), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bG = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.g), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bH = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.h), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bI = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.i), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bJ = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.j), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bK = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.k), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bL = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.l), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bM = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.m), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bN = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.n), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bO = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.o), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bP = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.p), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bQ = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.q), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bR = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.r), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bS = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.s), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bT = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.t), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bU = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.u), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bV = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.v), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bW = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.w), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bX = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.x), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bY = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.y), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        bZ = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.z), GameAreaManager.toScale(LETTER_SIZE.x), GameAreaManager.toScale(LETTER_SIZE.y), true);
        background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.background), GameAreaManager.getWidth(), GameAreaManager.getHeight(), true);
        character = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.character), GameAreaManager.toScale(CHARACTER_SIZE), GameAreaManager.toScale(CHARACTER_SIZE), true);
        thrownnet = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.thrownnet), GameAreaManager.toScale(BULLET_SIZE), GameAreaManager.toScale(BULLET_SIZE), true);
        capturenet = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WordNet.context.getResources(), R.drawable.capturenet), GameAreaManager.toScale(CAPTURED_SIZE), GameAreaManager.toScale(CAPTURED_SIZE), true);

    }

    public static Rect genBulletRect(int x, int y) {
        return new Rect(x - BULLET_SIZE / 2,
                y - BULLET_SIZE / 2,
                x + BULLET_SIZE / 2,
                y + BULLET_SIZE / 2);
    }

    public static Rect genLetterRect(int x, int y) {
        return new Rect(x - LETTER_SIZE.x / 2,
                y - LETTER_SIZE.y / 2,
                x + LETTER_SIZE.x / 2,
                y + LETTER_SIZE.y / 2);
    }

    public static Rect genCapturedRect(int x, int y) {
        return new Rect(x - CAPTURED_SIZE / 2,
                y - CAPTURED_SIZE / 2,
                x + CAPTURED_SIZE / 2,
                y + CAPTURED_SIZE / 2);
    }

    public static Bitmap getLetterBMfromChar(char c) {
        switch (c) {
            case 'A':
                return getbA();
            case 'B':
                return getbB();
            case 'C':
                return getbC();
            case 'D':
                return getbD();
            case 'E':
                return getbE();
            case 'F':
                return getbF();
            case 'G':
                return getbG();
            case 'H':
                return getbH();
            case 'I':
                return getbI();
            case 'J':
                return getbJ();
            case 'K':
                return getbK();
            case 'L':
                return getbL();
            case 'M':
                return getbM();
            case 'N':
                return getbN();
            case 'O':
                return getbO();
            case 'P':
                return getbP();
            case 'Q':
                return getbQ();
            case 'R':
                return getbR();
            case 'S':
                return getbS();
            case 'T':
                return getbT();
            case 'U':
                return getbU();
            case 'V':
                return getbV();
            case 'W':
                return getbW();
            case 'X':
                return getbX();
            case 'Y':
                return getbY();
            case 'Z':
                return getbZ();
        }
        return getbA();
    }

    public static Bitmap getbA() {
        return bA;
    }

    public static Bitmap getbB() {
        return bB;
    }

    public static Bitmap getbC() {
        return bC;
    }

    public static Bitmap getbD() {
        return bD;
    }

    public static Bitmap getbE() {
        return bE;
    }

    public static Bitmap getbF() {
        return bF;
    }

    public static Bitmap getbG() {
        return bG;
    }

    public static Bitmap getbH() {
        return bH;
    }

    public static Bitmap getbI() {
        return bI;
    }

    public static Bitmap getbJ() {
        return bJ;
    }

    public static Bitmap getbK() {
        return bK;
    }

    public static Bitmap getbL() {
        return bL;
    }

    public static Bitmap getbM() {
        return bM;
    }

    public static Bitmap getbN() {
        return bN;
    }

    public static Bitmap getbO() {
        return bO;
    }

    public static Bitmap getbP() {
        return bP;
    }

    public static Bitmap getbQ() {
        return bQ;
    }

    public static Bitmap getbR() {
        return bR;
    }

    public static Bitmap getbS() {
        return bS;
    }

    public static Bitmap getbT() {
        return bT;
    }

    public static Bitmap getbU() {
        return bU;
    }

    public static Bitmap getbV() {
        return bV;
    }

    public static Bitmap getbW() {
        return bW;
    }

    public static Bitmap getbX() {
        return bX;
    }

    public static Bitmap getbY() {
        return bY;
    }

    public static Bitmap getbZ() {
        return bZ;
    }

    public static Bitmap getBackground() {
        return background;
    }

    public static Bitmap getCharacter() {
        return character;
    }

    public static Bitmap getThrownnet() {
        return thrownnet;
    }

    public static Bitmap getCapturenet() {
        return capturenet;
    }
}
