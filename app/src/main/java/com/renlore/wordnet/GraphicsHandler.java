package com.renlore.wordnet;

import android.content.Context;
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

    private Bitmap bA;
    private Bitmap bB;
    private Bitmap bC;
    private Bitmap bD;
    private Bitmap bE;
    private Bitmap bF;
    private Bitmap bG;
    private Bitmap bH;
    private Bitmap bI;
    private Bitmap bJ;
    private Bitmap bK;
    private Bitmap bL;
    private Bitmap bM;
    private Bitmap bN;
    private Bitmap bO;
    private Bitmap bP;
    private Bitmap bQ;
    private Bitmap bR;
    private Bitmap bS;
    private Bitmap bT;
    private Bitmap bU;
    private Bitmap bV;
    private Bitmap bW;
    private Bitmap bX;
    private Bitmap bY;
    private Bitmap bZ;
    private Bitmap background;
    private Bitmap character;
    private Bitmap thrownnet;
    private Bitmap capturenet;
    private Context context;
    private GameAreaManager gam;

    public GraphicsHandler(Context context, GameAreaManager gam) {
        this.context = context;
        this.gam = gam;
        bA = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.a), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bB = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.b), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bC = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.c), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bD = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bE = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.e), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bF = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.f), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bG = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.g), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bH = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.h), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bI = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.i), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bJ = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.j), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bK = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.k), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bL = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.l), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bM = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.m), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bN = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.n), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bO = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.o), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bP = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.p), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bQ = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.q), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bR = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.r), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bS = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.s), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bT = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.t), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bU = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.u), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bV = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.v), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bW = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.w), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bX = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.x), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bY = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.y), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        bZ = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.z), gam.toScale(LETTER_SIZE.x), gam.toScale(LETTER_SIZE.y), true);
        background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.background), gam.getWidth(), gam.getHeight(), true);
        character = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.character), gam.toScale(CHARACTER_SIZE), gam.toScale(CHARACTER_SIZE), true);
        thrownnet = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.thrownnet), gam.toScale(BULLET_SIZE), gam.toScale(BULLET_SIZE), true);
        capturenet = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.capturenet), gam.toScale(CAPTURED_SIZE), gam.toScale(CAPTURED_SIZE), true);

    }

    public Rect genBulletRect(int x, int y) {
        return new Rect(x - BULLET_SIZE / 2,
                y - BULLET_SIZE / 2,
                x + BULLET_SIZE / 2,
                y + BULLET_SIZE / 2);
    }

    public Rect genLetterRect(int x, int y) {
        return new Rect(x - LETTER_SIZE.x / 2,
                y - LETTER_SIZE.y / 2,
                x + LETTER_SIZE.x / 2,
                y + LETTER_SIZE.y / 2);
    }

    public Rect genCapturedRect(int x, int y) {
        return new Rect(x - CAPTURED_SIZE / 2,
                y - CAPTURED_SIZE / 2,
                x + CAPTURED_SIZE / 2,
                y + CAPTURED_SIZE / 2);
    }
    public Bitmap getbA() {
        return bA;
    }

    public Bitmap getbB() {
        return bB;
    }

    public Bitmap getbC() {
        return bC;
    }

    public Bitmap getbD() {
        return bD;
    }

    public Bitmap getbE() {
        return bE;
    }

    public Bitmap getbF() {
        return bF;
    }

    public Bitmap getbG() {
        return bG;
    }

    public Bitmap getbH() {
        return bH;
    }

    public Bitmap getbI() {
        return bI;
    }

    public Bitmap getbJ() {
        return bJ;
    }

    public Bitmap getbK() {
        return bK;
    }

    public Bitmap getbL() {
        return bL;
    }

    public Bitmap getbM() {
        return bM;
    }

    public Bitmap getbN() {
        return bN;
    }

    public Bitmap getbO() {
        return bO;
    }

    public Bitmap getbP() {
        return bP;
    }

    public Bitmap getbQ() {
        return bQ;
    }

    public Bitmap getbR() {
        return bR;
    }

    public Bitmap getbS() {
        return bS;
    }

    public Bitmap getbT() {
        return bT;
    }

    public Bitmap getbU() {
        return bU;
    }

    public Bitmap getbV() {
        return bV;
    }

    public Bitmap getbW() {
        return bW;
    }

    public Bitmap getbX() {
        return bX;
    }

    public Bitmap getbY() {
        return bY;
    }

    public Bitmap getbZ() {
        return bZ;
    }

    public Bitmap getBackground() {
        return background;
    }

    public Bitmap getCharacter() {
        return character;
    }

    public Bitmap getThrownnet() {
        return thrownnet;
    }

    public Bitmap getCapturenet() {
        return capturenet;
    }
}
