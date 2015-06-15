package com.renlore.wordnet;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Ng on 5/31/2015.
 */
public class MainThread extends Thread {
    private static final int FPS = 25;
    private static final int FRAME_PERIOD = 1000 / FPS;
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long tickcount = 0L;
        long nextFrameTime = System.currentTimeMillis();
        long timeDiff;
        while (running) {

            Canvas canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

                    while (System.currentTimeMillis() > nextFrameTime) {
                        tickcount++;
                        this.gamePanel.update(tickcount);
                        nextFrameTime += FRAME_PERIOD;
                    }
                    this.gamePanel.render(canvas);
                    if (System.currentTimeMillis() < nextFrameTime) {
                        try {
                            timeDiff = nextFrameTime - System.currentTimeMillis();
                            if (timeDiff > 0) {
                                Thread.sleep((int) (timeDiff));
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
