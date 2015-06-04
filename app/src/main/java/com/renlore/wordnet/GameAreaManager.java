package com.renlore.wordnet;

import android.graphics.Rect;

/**
 * Created by Ng on 6/2/2015.
 */
public class GameAreaManager {
    private int deviceWidth;
    private int deviceHeight;
    private static final int baseWidth = 480;
    private static final int baseHeight = 800;
    private float scaleWidth, scaleHeight;
    private float scale;
    private static final int WIDTH_FIT = 0;
    private static final int HEIGHT_FIT = 1;
    private int fitDim;
    private int top;
    private int bottom;
    private int left;
    private int right;
    private int width;
    private int height;
    private int centerX, centerY;

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDeviceWidth() {
        return deviceWidth;
    }

    public void setDeviceWidth(int deviceWidth) {
        this.deviceWidth = deviceWidth;
    }

    public int getDeviceHeight() {
        return deviceHeight;
    }

    public void setDeviceHeight(int deviceHeight) {
        this.deviceHeight = deviceHeight;
    }

    public static int getBaseWidth() {
        return baseWidth;
    }

    public static int getBaseHeight() {
        return baseHeight;
    }

    public float getScaleWidth() {
        return scaleWidth;
    }

    public float getScaleHeight() {
        return scaleHeight;
    }

    public float getScale() {
        return scale;
    }

    public int getFitDim() {
        return fitDim;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int dX2bX(int x) {
        if (x < left) {
            return 0;
        } else if (x > right) {
            return baseWidth;
        } else {
            return (int) ((x - left) / scale);
        }
    }

    public int dY2bY(int y) {
        if (y < top) {
            return 0;
        } else if (y > bottom) {
            return baseHeight;
        } else {
            return (int) ((y - top) / scale);
        }
    }

    public void calcScales() {
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

    private void calcCriticalPointsHeightFit() {
        width = (int) (baseWidth * scale + 0.5f);
        height = (int) (baseHeight * scale + 0.5f);
        top = 0;
        bottom = height;
        left = (deviceWidth - width) / 2;
        right = left + width;
    }

    private void calcCriticalPointsWidthFit() {
        width = (int) (baseWidth * scale + 0.5f);
        height = (int) (baseHeight * scale + 0.5f);
        top = (deviceHeight - height) / 2;
        bottom = top + height;
        left = 0;
        right = width;
    }

    public int dix(int x) {
        if (fitDim == WIDTH_FIT) {
            return (int) (x * scale);
        } else {
            return (int) (x * scale + left);
        }
    }

    public Rect toScaleRect(Rect rect) {
        Rect scaledRect = new Rect(rect);
        scaledRect.left *= scale;
        scaledRect.top *= scale;
        scaledRect.right *= scale;
        scaledRect.bottom *= scale;
        scaledRect.offset(left, top);
        return scaledRect;
    }

    public int toScale(int num) {
        return (int) (num * scale);
    }

    public int diy(int y) {
        if (fitDim == WIDTH_FIT) {
            return (int) (y * scale + top);
        } else {
            return (int) (y * scale);
        }
    }

    public GameAreaManager(int deviceWidth, int deviceHeight) {

        this.deviceWidth = deviceWidth;
        this.deviceHeight = deviceHeight;
        calcScales();
    }
}
