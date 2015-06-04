package com.renlore.wordnet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Ng on 6/1/2015.
 */
public class Letter {
    private static final String TAG = Letter.class.getSimpleName();
    private double dir;
    private int x;
    private int y;
    private int speed = 2;
    private char letter;
    private Bitmap bitmap;
    private Rect rect;

    public void setDir(double dir) {
        this.dir = dir;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getDir() {

        return dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public Letter(Bitmap bitmap, char letter, double dir, int x, int y) {
        this.bitmap = bitmap;
        this.letter = letter;
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.rect = new Rect(x, y, x + 60, y + 60);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Letter(Bitmap bitmap, char letter, double dir, Rect rect) {
        this.bitmap = bitmap;
        this.letter = letter;
        this.dir = dir;
        this.rect = rect;
        setX(rect.centerX());
        setY(rect.centerY());
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void update() {
        int dx = (int) (speed * Math.cos(dir));
        int dy = (int) (speed * Math.sin(dir));
        rect.offset(dx, dy);
        setX(rect.centerX());
        setY(rect.centerY());
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

}
