package com.renlore.wordnet;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Ng on 6/7/2015.
 */
public abstract class State {
    public void setCurrentState(State newState) {
        WordNet.gamePanel.setCurrentState(newState);
    }

    public abstract void init();

    public abstract void update();

    public abstract void render(Canvas canvas);

    public abstract boolean onTouch(MotionEvent e);
}
