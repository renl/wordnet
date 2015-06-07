package com.renlore.wordnet;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

/**
 * Created by Ng on 6/7/2015.
 */
public class EndState extends State {
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
    }

    @Override
    public boolean onTouch(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            AudioHandler.releaseAll();
            WordNet.gamePanel.thread.setRunning(false);
            ((Activity)WordNet.context).finish();
        }
        return true;
    }
}

