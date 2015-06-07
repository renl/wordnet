package com.renlore.wordnet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by Ng on 6/7/2015.
 */
public class MenuState extends State {
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawColor(Color.BLUE);
        paint.setTextSize((int) (48 * GameAreaManager.getScale()));
        canvas.drawText("WORDNET", GameAreaManager.dix(0), GameAreaManager.getCenterY() - GameAreaManager.toScale(30), paint);
        paint.setTextSize((int) (16 * GameAreaManager.getScale()));
        canvas.drawText("PRESS ANYWHERE TO START", GameAreaManager.dix(0), GameAreaManager.getCenterY() + GameAreaManager.toScale(30), paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(GameAreaManager.dix(0),
                GameAreaManager.diy(0),
                GameAreaManager.dix(50),
                GameAreaManager.diy(50),
                paint);
        canvas.drawRect(GameAreaManager.getRight() - GameAreaManager.toScale(50),
                GameAreaManager.diy(0),
                GameAreaManager.getRight(),
                GameAreaManager.diy(50),
                paint);
        canvas.drawRect(GameAreaManager.dix(0),
                GameAreaManager.getBottom() - GameAreaManager.toScale(50),
                GameAreaManager.dix(50),
                GameAreaManager.getBottom(),
                paint);
        canvas.drawRect(GameAreaManager.getRight() - GameAreaManager.toScale(50),
                GameAreaManager.getBottom() - GameAreaManager.toScale(50),
                GameAreaManager.getRight(),
                GameAreaManager.getBottom(),
                paint);
    }

    @Override
    public boolean onTouch(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            setCurrentState(new PlayState());
        }
        return true;
    }
}
