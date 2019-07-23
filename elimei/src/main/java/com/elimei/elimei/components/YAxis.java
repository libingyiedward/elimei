/*     */
package com.elimei.elimei.components;
/*     */
/*     */

import android.graphics.Paint;
/*     */ import com.elimei.elimei.utils.Utils;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class YAxis
        extends AxisBase
        {
      private boolean mDrawBottomYLabelEntry = true;
      private boolean mDrawTopYLabelEntry = true;
      protected boolean mInverted = false;
      protected boolean mDrawZeroLine = false;
    /*  44 */   protected int mZeroLineColor = -7829368;
   
   
   
   
    /*  49 */   protected float mZeroLineWidth = 1.0F;
   
   
   
   
    /*  54 */   protected float mSpacePercentTop = 10.0F;
   
   
   
   
    /*  59 */   protected float mSpacePercentBottom = 10.0F;
   
   
   
   
    /*  64 */   private YAxisLabelPosition mPosition = YAxisLabelPosition.OUTSIDE_CHART;
   
      private AxisDependency mAxisDependency;

   
      public enum YAxisLabelPosition
            {
        /*  70 */     OUTSIDE_CHART, INSIDE_CHART;
       
    }

   
   
   
   
   
   
   
   
   
   
   
    /*  83 */   protected float mMinWidth = 0.0F;
   
   
   
   
   
   
    /*  90 */   protected float mMaxWidth = Float.POSITIVE_INFINITY;

   
   
   
   
   
      public enum AxisDependency
            {
        /*  98 */     LEFT, RIGHT;
       
    }

   
   
   
   
   
    public YAxis() {
        /* 105 */
        this.mAxisDependency = AxisDependency.LEFT;
        /* 106 */
        this.mYOffset = 0.0F;
       
    }

   
   
   
    public YAxis(AxisDependency position) {
        /* 111 */
        this.mAxisDependency = position;
        /* 112 */
        this.mYOffset = 0.0F;
       
    }

   
   
    /* 116 */
    public AxisDependency getAxisDependency() {
        return this.mAxisDependency;
    }

   
   
   
   
   
   
    /* 123 */
    public float getMinWidth() {
        return this.mMinWidth;
    }

   
   
   
   
   
   
   
   
    /* 132 */
    public void setMinWidth(float minWidth) {
        this.mMinWidth = minWidth;
    }

   
   
   
   
   
   
    /* 139 */
    public float getMaxWidth() {
        return this.mMaxWidth;
    }

   
   
   
   
   
   
   
   
    /* 148 */
    public void setMaxWidth(float maxWidth) {
        this.mMaxWidth = maxWidth;
    }

   
   
   
   
   
   
    /* 155 */
    public YAxisLabelPosition getLabelPosition() {
        return this.mPosition;
    }

   
   
   
   
   
   
   
   
    /* 164 */
    public void setPosition(YAxisLabelPosition pos) {
        this.mPosition = pos;
    }

   
   
   
   
   
   
   
   
    /* 173 */
    public boolean isDrawTopYLabelEntryEnabled() {
        return this.mDrawTopYLabelEntry;
    }

   
   
   
   
   
   
   
   
    /* 182 */
    public boolean isDrawBottomYLabelEntryEnabled() {
        return this.mDrawBottomYLabelEntry;
    }

   
   
   
   
   
   
   
   
   
   
    /* 193 */
    public void setDrawTopYLabelEntry(boolean enabled) {
        this.mDrawTopYLabelEntry = enabled;
    }

   
   
   
   
   
   
   
   
   
   
    /* 204 */
    public void setInverted(boolean enabled) {
        this.mInverted = enabled;
    }

   
   
   
   
   
   
   
   
    /* 213 */
    public boolean isInverted() {
        return this.mInverted;
    }

   
   
   
   
   
   
   
   
   
    @Deprecated
    public void setStartAtZero(boolean startAtZero) {
        /* 224 */
        if (startAtZero) {
            /* 225 */
            setAxisMinimum(0.0F);
           
        } else {
            /* 227 */
            resetAxisMinimum();
           
        }
       
    }

   
   
   
   
   
   
    /* 236 */
    public void setSpaceTop(float percent) {
        this.mSpacePercentTop = percent;
    }

   
   
   
   
   
   
   
   
    /* 245 */
    public float getSpaceTop() {
        return this.mSpacePercentTop;
    }

   
   
   
   
   
   
   
   
    /* 254 */
    public void setSpaceBottom(float percent) {
        this.mSpacePercentBottom = percent;
    }

   
   
   
   
   
   
   
   
    /* 263 */
    public float getSpaceBottom() {
        return this.mSpacePercentBottom;
    }

   
   
   
    /* 267 */
    public boolean isDrawZeroLineEnabled() {
        return this.mDrawZeroLine;
    }

   
   
   
   
   
   
   
   
   
    /* 277 */
    public void setDrawZeroLine(boolean mDrawZeroLine) {
        this.mDrawZeroLine = mDrawZeroLine;
    }

   
   
   
    /* 281 */
    public int getZeroLineColor() {
        return this.mZeroLineColor;
    }

   
   
   
   
   
   
   
   
    /* 290 */
    public void setZeroLineColor(int color) {
        this.mZeroLineColor = color;
    }

   
   
   
    /* 294 */
    public float getZeroLineWidth() {
        return this.mZeroLineWidth;
    }

   
   
   
   
   
   
   
   
    /* 303 */
    public void setZeroLineWidth(float width) {
        this.mZeroLineWidth = Utils.convertDpToPixel(width);
    }

   
   
   
   
   
   
   
   
   
   
    public float getRequiredWidthSpace(Paint p) {
        /* 314 */
        p.setTextSize(this.mTextSize);
       
        /* 316 */
        String label = getLongestLabel();
        /* 317 */
        float width = Utils.calcTextWidth(p, label) + getXOffset() * 2.0F;

        float minWidth = getMinWidth();

        float maxWidth = getMaxWidth();

        if (minWidth > 0.0F) {

            minWidth = Utils.convertDpToPixel(minWidth);
           
        }

        if (maxWidth > 0.0F && maxWidth != Float.POSITIVE_INFINITY) {
            maxWidth = Utils.convertDpToPixel(maxWidth);
           
        }

        return Math.max(minWidth, Math.min(width, (maxWidth > 0.0D) ? maxWidth : width));
       
    }

   
   
   
   
   
   
   
   
   
   
   
    public float getRequiredHeightSpace(Paint p) {
        /* 341 */
        p.setTextSize(this.mTextSize);
       
        /* 343 */
        String label = getLongestLabel();
        /* 344 */
        return Utils.calcTextHeight(p, label) + getYOffset() * 2.0F;
       
    }

   
   
   
   
   
   
   
    public boolean needsOffset() {
        /* 353 */
        if (isEnabled() && isDrawLabelsEnabled() && getLabelPosition() == YAxisLabelPosition.OUTSIDE_CHART)
            {
            /* 355 */
            return true;
           
        }
        /* 357 */
        return false;
       
    }

   
   
   
   
   
    public void calculate(float dataMin, float dataMax) {
        /* 364 */
        float min = this.mCustomAxisMin ? this.mAxisMinimum : dataMin;
        /* 365 */
        float max = this.mCustomAxisMax ? this.mAxisMaximum : dataMax;
       
       
        /* 368 */
        float range = Math.abs(max - min);
       
       
        /* 371 */
        if (range == 0.0F) {
            /* 372 */
            max++;
            /* 373 */
            min--;
           
        }
       
       
        /* 377 */
        if (!this.mCustomAxisMin) {
           
            /* 379 */
            float bottomSpace = range / 100.0F * getSpaceBottom();
            /* 380 */
            this.mAxisMinimum = min - bottomSpace;
           
        }
       
       
        /* 384 */
        if (!this.mCustomAxisMax) {
           
            /* 386 */
            float topSpace = range / 100.0F * getSpaceTop();
            /* 387 */
            this.mAxisMaximum = max + topSpace;
           
        }
       
       
        /* 391 */
        this.mAxisRange = Math.abs(this.mAxisMaximum - this.mAxisMinimum);
       
    }
   
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\YAxis.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */