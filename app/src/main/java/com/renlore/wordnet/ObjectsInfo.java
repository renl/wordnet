package com.renlore.wordnet;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Ng on 6/15/2015.
 */
public class ObjectsInfo {
    public static final Point balloonStartPointLeft = new Point(0, 0);
    public static final Point balloonStartPointRight = new Point(800, 0);
    public static final double balloonDirMovingRight = 0;
    public static final double balloonDirMovingLeft = 180;
    public static final Rect activeZone = new Rect(0, 0, GameAreaManager.getBaseWidth(), GameAreaManager.getBaseHeight() - 60);
    public static final Point balloonReceivePoint = new Point(GameAreaManager.getBaseCenterX(), GameAreaManager.getBaseHeight() - 120);
    public static final Point board1Center = new Point(63, 328);
    public static final Point board2Center = new Point(173, 328);
    public static final Point board3Center = new Point(63, 387);
    public static final Point board4Center = new Point(173, 387);
    public static final Point board5Center = new Point(671, 387);
}
