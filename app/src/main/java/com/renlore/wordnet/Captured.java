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

    public int update() {
        if (lifespan > 0) {
            lifespan--;
        }
        return lifespan;
    }
    public Captured(Bitmap bitmap, Rect rect, int lifespan) {
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
