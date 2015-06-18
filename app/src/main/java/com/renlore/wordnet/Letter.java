package com.renlore.wordnet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.Random;

/**
 * Created by Ng on 6/1/2015.
 */
public class Letter {
    private static final String TAG = Letter.class.getSimpleName();
    private double dir;
    private double wobbleDir;
    private float x;
    private float y;
    private int speed = 2;
    private char letter;
    private Bitmap bitmap;
    private RectF rect;
    private boolean wobble;
    private Random randGen = new Random();
    private Waypoint wp;
    private PointF currentLocation;

    public void setDir(double dir) {
        this.dir = dir;
        this.wobbleDir = dir;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getDir() {

        return dir;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Rect getRect() {
        Rect retRect = new Rect();
        rect.roundOut(retRect);
        return retRect;
    }

    public void setRect(RectF rect) {
        this.rect = rect;
    }

    public Letter(Bitmap bitmap, char letter, double dir, Rect rect) {
        this.bitmap = bitmap;
        this.letter = letter;
        this.dir = dir;
        this.wobbleDir = dir;
        this.rect = new RectF(rect);
        setX(rect.centerX());
        setY(rect.centerY());
        this.wobble = randGen.nextBoolean();
        this.currentLocation = new PointF(this.x, this.y);
        this.wp = new Waypoint(this.currentLocation, 20);
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean isWobble() {
        return wobble;
    }

    public void setWobble(boolean wobble) {
        this.wobble = wobble;
    }

    public void update() {
        if (wp.waypointAvail()) {
            setDir(wp.getHeading());
            wp.update(currentLocation);
        } else {
            if (wobble) {
                wobbleDir += 5;
                if (wobbleDir > dir + 45) {
                    wobble = !wobble;
                }

            } else {
                wobbleDir -= 5;
                if (wobbleDir < dir - 45) {
                    wobble = !wobble;
                }
            }
        }
        float dx = (float) (speed * Math.cos(Math.toRadians(wobbleDir)));
        float dy = (float) (speed * Math.sin(Math.toRadians(wobbleDir)));
        rect.offset(dx, dy);
        setX(rect.centerX());
        setY(rect.centerY());
        currentLocation.set(x, y);
    }

    public boolean wpReached() {
        return !wp.waypointAvail();
    }

    public void addWP(Point point) {
        this.wp.addWP(new PointF(point));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

}
