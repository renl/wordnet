package com.renlore.wordnet;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Ng on 6/3/2015.
 */
public class Captured {
    private Bitmap bitmap;
    private Rect rect;
    private int lifespan;
    private Letter letter;
    private int x,y;

    public boolean wpReached() {
        return letter.wpReached();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void update() {
        x = (int)letter.getX();
        y = (int)letter.getY();
        rect.offsetTo(x - rect.width() / 2, y - rect.height() / 2);
//        if (lifespan > 0) {
//            lifespan--;
//        }
//        return lifespan;
    }

    public Captured(Bitmap bitmap, Rect rect, int lifespan, Letter letter) {
        this.letter = letter;
        this.bitmap = bitmap;
        this.rect = rect;
        this.lifespan = lifespan;
    }

    public Bitmap getBitmap() {

        return bitmap;
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

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }
}
