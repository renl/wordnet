package com.renlore.wordnet;

import android.graphics.Bitmap;

/**
 * Created by Ng on 6/19/2015.
 */
public class Frame {
    private Bitmap bitmap;
    private float endTime;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }

    public Frame(Bitmap bitmap, float endTime) {

        this.bitmap = bitmap;
        this.endTime = endTime;
    }
}
