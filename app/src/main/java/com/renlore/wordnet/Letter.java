package com.renlore.wordnet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

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
    private boolean wobble;
    private Random randGen = new Random();
    private Waypoint wp;
    private Point currentLocation;

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
        this.wobble = randGen.nextBoolean();
        this.currentLocation = new Point(this.x, this.y);
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
                dir += 0.1;
                if (dir > Math.toRadians(45)) {
                    wobble = !wobble;
                }

            } else {
                dir -= 0.1;
                if (dir < Math.toRadians(-45)) {
                    wobble = !wobble;
                }
            }
        }
        int dx = (int) (speed * Math.cos(dir));
        int dy = (int) (speed * Math.sin(dir));
        rect.offset(dx, dy);
        setX(rect.centerX());
        setY(rect.centerY());
        currentLocation.set(x, y);
    }

    public boolean wpReached() {
        return !wp.waypointAvail();
    }

    public void addWP(Point point) {
        this.wp.addWP(point);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

}
