package com.renlore.wordnet;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Ng on 6/7/2015.
 */
public class LoadState extends State {
    @Override
    public void init() {
        GameAreaManager.init();
        GraphicsHandler.load();
        AudioHandler.load();
    }

    @Override
    public void update() {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Canvas canvas) {

    }

    @Override
    public boolean onTouch(MotionEvent e) {
        return false;
    }
}
