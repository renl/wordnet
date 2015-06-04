package com.renlore.wordnet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Ng on 5/31/2015.
 */
public class Bullet {
    private static final String TAG = Bullet.class.getSimpleName();
    private double dir;
    private int x;
    private int y;
    private int speed = 20;
    private Paint paint = new Paint();
    private Rect drawRect;
    private Rect hitboxRect;
    private Bitmap bitmap;

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Rect getDrawRect() {
        return drawRect;
    }

    public void setDrawRect(Rect drawRect) {
        this.drawRect = drawRect;
    }

    public Rect getHitboxRect() {
        return hitboxRect;
    }

    public void setHitboxRect(Rect hitboxRect) {
        this.hitboxRect = hitboxRect;
    }

    public Bullet(Bitmap bitmap, double dir, Rect drawRect) {
        this.dir = dir;
        this.drawRect = drawRect;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        x = drawRect.centerX();
        y = drawRect.centerY();
        setBitmap(bitmap);
        setHitboxRect(new Rect(drawRect.centerX() - drawRect.width() / 10,
                drawRect.top,
                drawRect.centerX() + drawRect.width() / 10,
                drawRect.top - drawRect.height() / 5
        ));
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDir() {
        return dir;
    }

    public void setDir(double dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void update() {
        int dx = (int) (speed * Math.cos(dir));
        int dy = (int) (speed * Math.sin(dir));
        drawRect.offset(dx, dy);
        hitboxRect.offset(dx, dy);
        x = drawRect.centerX();
        y = drawRect.centerY();
    }
}
