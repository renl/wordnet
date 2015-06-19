package com.renlore.wordnet;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ng on 6/19/2015.
 */
public class Animation {
    private List<Frame> frames = new ArrayList<Frame>();
    private float duration;
    private int frameIndex = 0;
    private float currentTime = 0;

    public Animation(Frame... frames) {
        for (Frame frame : frames) {
            this.frames.add(frame);
            duration = frame.getEndTime();
        }
    }

    public void update(float delta) {
        currentTime += delta;
        currentTime %= duration;
        boolean frameFound = false;
        while (!frameFound) {
            // last worked on here.
        }
    }
}
