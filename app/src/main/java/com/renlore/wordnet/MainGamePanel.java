package com.renlore.wordnet;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Ng on 5/31/2015.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public MainThread thread;
    private static final String TAG = MainGamePanel.class.getSimpleName();
    private State currentState;

    public void setCurrentState(State newState) {
        System.gc();
        newState.init();
        currentState = newState;
    }

    public MainGamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGame(context);
    }

    public MainGamePanel(Context context) {
        super(context);
        initGame(context);
    }

    private void initGame(Context context) {
        getHolder().addCallback(this);
        setFocusable(true);
        GameAreaManager.init();
        GraphicsHandler.load();
        AudioHandler.load();
        setCurrentState(new LoadState());
        thread = new MainThread(getHolder(), this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "Width: " + getMeasuredWidth() + ", Height: " + getMeasuredHeight() + ", Proportion: " + (float) getMeasuredHeight() / (float) getMeasuredWidth());
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        currentState.onTouch(event);
        return true;
    }

    protected void render(Canvas canvas) {
        currentState.render(canvas);
    }

    public void update(long tickcount) {
        currentState.update();
    }
}