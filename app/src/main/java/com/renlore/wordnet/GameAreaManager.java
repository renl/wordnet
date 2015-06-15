package com.renlore.wordnet;

import android.graphics.Rect;

/**
 * Created by Ng on 6/2/2015.
 */
public class GameAreaManager {
    private static int deviceWidth;
    private static int deviceHeight;
    private static final int baseWidth = 800;
    private static final int baseHeight = 480;
    private static float scaleWidth, scaleHeight;
    private static float scale;
    private static final int WIDTH_FIT = 0;
    private static final int HEIGHT_FIT = 1;
    private static int fitDim;
    private static int top;
    private static int bottom;
    private static int left;
    private static int right;
    private static int width;
    private static int height;
    private static int centerX, centerY;
    private static final int baseCenterX = baseWidth / 2;

    public static int getBaseCenterX() {
        return baseCenterX;
    }

    public static int getBaseCenterY() {
        return baseCenterY;
    }

    private static final int baseCenterY = baseHeight / 2;

    public static int getTop() {
        return top;
    }

    public static int getBottom() {
        return bottom;
    }

    public static int getLeft() {
        return left;
    }

    public static int getRight() {
        return right;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getDeviceWidth() {
        return deviceWidth;
    }

    public static void setDeviceWidth(int deviceWidth) {
        deviceWidth = deviceWidth;
    }

    public static int getDeviceHeight() {
        return deviceHeight;
    }

    public static void setDeviceHeight(int deviceHeight) {
        deviceHeight = deviceHeight;
    }

    public static int getBaseWidth() {
        return baseWidth;
    }

    public static int getBaseHeight() {
        return baseHeight;
    }

    public static float getScaleWidth() {
        return scaleWidth;
    }

    public static float getScaleHeight() {
        return scaleHeight;
    }

    public static float getScale() {
        return scale;
    }

    public static int getFitDim() {
        return fitDim;
    }

    public static int getCenterX() {
        return centerX;
    }

    public static int getCenterY() {
        return centerY;
    }

    public static int dX2bX(int x) {
        if (x < left) {
            return 0;
        } else if (x > right) {
            return baseWidth;
        } else {
            return (int) ((x - left) / scale);
        }
    }

    public static int dY2bY(int y) {
        if (y < top) {
            return 0;
        } else if (y > bottom) {
            return baseHeight;
        } else {
            return (int) ((y - top) / scale);
        }
    }

    public static void calcScales() {
        scaleWidth = (float) deviceWidth / (float) baseWidth;
        scaleHeight = (float) deviceHeight / (float) baseHeight;
        centerX = deviceWidth / 2;
        centerY = deviceHeight / 2;


        if (scaleWidth > scaleHeight) {
            scale = scaleHeight;
            fitDim = HEIGHT_FIT;
            calcCriticalPointsHeightFit();
        } else {
            scale = scaleWidth;
            fitDim = WIDTH_FIT;
            calcCriticalPointsWidthFit();
        }
    }

    private static void calcCriticalPointsHeightFit() {
        width = (int) (baseWidth * scale + 0.5f);
        height = (int) (baseHeight * scale + 0.5f);
        top = 0;
        bottom = height;
        left = (deviceWidth - width) / 2;
        right = left + width;
    }

    private static void calcCriticalPointsWidthFit() {
        width = (int) (baseWidth * scale + 0.5f);
        height = (int) (baseHeight * scale + 0.5f);
        top = (deviceHeight - height) / 2;
        bottom = top + height;
        left = 0;
        right = width;
    }

    public static int dix(int x) {
        if (fitDim == WIDTH_FIT) {
            return (int) (x * scale);
        } else {
            return (int) (x * scale + left);
        }
    }

    public static Rect toScaleRect(Rect rect) {
        Rect scaledRect = new Rect(rect);
        scaledRect.left *= scale;
        scaledRect.top *= scale;
        scaledRect.right *= scale;
        scaledRect.bottom *= scale;
        scaledRect.offset(left, top);
        return scaledRect;
    }

    public static int toScale(int num) {
        return (int) (num * scale);
    }

    public static int diy(int y) {
        if (fitDim == WIDTH_FIT) {
            return (int) (y * scale + top);
        } else {
            return (int) (y * scale);
        }
    }

    public  static void init() {
        deviceHeight = WordNet.context.getResources().getDisplayMetrics().heightPixels;
        deviceWidth = WordNet.context.getResources().getDisplayMetrics().widthPixels;
        calcScales();
    }
    public GameAreaManager(int deviceWidth, int deviceHeight) {

        this.deviceWidth = deviceWidth;
        this.deviceHeight = deviceHeight;
        calcScales();
    }
}
