package com.renlore.wordnet;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Ng on 6/15/2015.
 */
public class ObjectsInfo {
    public static final Point balloonStartPoint = new Point(GameAreaManager.getLeft(), 0);
    public static final double balloonDir = Math.toRadians(0);
    public static final Rect bulletActiveZone = new Rect(0, 0, GameAreaManager.getBaseWidth(), GameAreaManager.getBaseHeight() - 60);
    public static final Point balloonReceivePoint = new Point(GameAreaManager.getBaseCenterX(), GameAreaManager.getBaseHeight() - 120);
}
