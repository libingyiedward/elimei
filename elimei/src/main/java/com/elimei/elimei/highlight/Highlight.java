package com.elimei.elimei.highlight;

import com.elimei.elimei.components.YAxis;


public class Highlight {
    private float mX;
    private float mY;
    private float mXPx;
    private float mYPx;
    private int mDataIndex;

    public Highlight(float x, float y, int dataSetIndex) {
        this.mDataIndex = -1;

        /*  46 */
        this.mStackIndex = -1;
        /*  64 */
        this.mX = x;
        /*  65 */
        this.mY = y;
        /*  66 */
        this.mDataSetIndex = dataSetIndex;
    }

    private int mDataSetIndex;
    private int mStackIndex;
    private YAxis.AxisDependency axis;
    private float mDrawX;
    private float mDrawY;

    public Highlight(float x, int dataSetIndex, int stackIndex) {
        this.mStackIndex = stackIndex;
    }


    public Highlight(float x, float y, float xPx, float yPx, int dataSetIndex, YAxis.AxisDependency axis) {

        this.mDataIndex = -1;
        this.mStackIndex = -1;
        /*  82 */
        this.mX = x;
        /*  83 */
        this.mY = y;
        /*  84 */
        this.mXPx = xPx;
        /*  85 */
        this.mYPx = yPx;
        /*  86 */
        this.mDataSetIndex = dataSetIndex;
        /*  87 */
        this.axis = axis;
    }


    public Highlight(float x, float y, float xPx, float yPx, int dataSetIndex, int stackIndex, YAxis.AxisDependency axis) {
        /* 100 */
        this(x, y, xPx, yPx, dataSetIndex, axis);
        /* 101 */
        this.mStackIndex = stackIndex;
    }


    /* 110 */
    public float getX() {
        return this.mX;
    }


    /* 119 */
    public float getY() {
        return this.mY;
    }


    /* 126 */
    public float getXPx() {
        return this.mXPx;
    }


    /* 133 */
    public float getYPx() {
        return this.mYPx;
    }


    /* 142 */
    public int getDataIndex() {
        return this.mDataIndex;
    }


    /* 146 */
    public void setDataIndex(int mDataIndex) {
        this.mDataIndex = mDataIndex;
    }


    /* 155 */
    public int getDataSetIndex() {
        return this.mDataSetIndex;
    }


    /* 165 */
    public int getStackIndex() {
        return this.mStackIndex;
    }


    /* 169 */
    public boolean isStacked() {
        return (this.mStackIndex >= 0);
    }


    /* 178 */
    public YAxis.AxisDependency getAxis() {
        return this.axis;
    }


    public void setDraw(float x, float y) {
        /* 188 */
        this.mDrawX = x;
        /* 189 */
        this.mDrawY = y;
    }


    /* 198 */
    public float getDrawX() {
        return this.mDrawX;
    }


    /* 207 */
    public float getDrawY() {
        return this.mDrawY;
    }


    public boolean equalTo(Highlight h) {
        /* 219 */
        if (h == null) {
            /* 220 */
            return false;
        }
        /* 222 */
        if (this.mDataSetIndex == h.mDataSetIndex && this.mX == h.mX && this.mStackIndex == h.mStackIndex && this.mDataIndex == h.mDataIndex) {
            /* 224 */
            return true;
        }
        /* 226 */
        return false;
    }


    /* 232 */
    public String toString() {
        return "Highlight, x: " + this.mX + ", y: " + this.mY + ", dataSetIndex: " + this.mDataSetIndex + ", stackIndex (only stacked barentry): " + this.mStackIndex;
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\highlight\Highlight.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */