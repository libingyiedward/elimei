/*     */
package com.elimei.elimei.charts;
/*     */
/*     */

import android.animation.ObjectAnimator;
/*     */ import android.animation.ValueAnimator;
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.graphics.RectF;
/*     */ import android.os.Build;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.Log;
/*     */ import android.view.MotionEvent;
/*     */ import com.elimei.elimei.animation.Easing;
/*     */ import com.elimei.elimei.components.Legend;
/*     */ import com.elimei.elimei.components.XAxis;
/*     */ import com.elimei.elimei.data.ChartData;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*     */ import com.elimei.elimei.listener.PieRadarChartTouchListener;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import com.elimei.elimei.utils.Utils;

import static com.elimei.elimei.components.Legend.LegendOrientation.HORIZONTAL;
import static com.elimei.elimei.components.Legend.LegendOrientation.VERTICAL;

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
/*     */ public abstract class PieRadarChartBase<T extends ChartData<? extends IDataSet<? extends Entry>>>
        /*     */ extends Chart<T>
        /*     */ {
    /*  35 */   private float mRotationAngle = 270.0F;
    /*     */
    /*     */
    /*     */
    /*     */
    /*  40 */   private float mRawRotationAngle = 270.0F;
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   protected boolean mRotateEnabled = true;
    /*     */
    /*     */
    /*     */
    /*     */
    /*  50 */   protected float mMinOffset = 0.0F;

    /*     */
    /*     */
    /*  53 */
    public PieRadarChartBase(Context context) {
        super(context);
    }

    /*     */
    /*     */
    /*     */
    /*  57 */
    public PieRadarChartBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*     */
    /*     */
    /*     */
    /*  61 */
    public PieRadarChartBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /*     */
    /*     */
    /*     */
    /*     */
    protected void init() {
        /*  66 */
        super.init();
        /*     */
        /*  68 */
        this.mChartTouchListener = new PieRadarChartTouchListener(this);
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    protected void calcMinMax() {
    }

    /*     */
    /*     */
    /*     */
    /*  78 */
    public int getMaxVisibleCount() {
        return this.mData.getEntryCount();
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public boolean onTouchEvent(MotionEvent event) {
        /*  84 */
        if (this.mTouchEnabled && this.mChartTouchListener != null) {
            /*  85 */
            return this.mChartTouchListener.onTouch(this, event);
            /*     */
        }
        /*  87 */
        return super.onTouchEvent(event);
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    public void computeScroll() {
        /*  93 */
        if (this.mChartTouchListener instanceof PieRadarChartTouchListener) {
            /*  94 */
            ((PieRadarChartTouchListener) this.mChartTouchListener).computeScroll();
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    public void notifyDataSetChanged() {
        /*  99 */
        if (this.mData == null) {
            /*     */
            return;
            /*     */
        }
        /* 102 */
        calcMinMax();
        /*     */
        /* 104 */
        if (this.mLegend != null) {
            /* 105 */
            this.mLegendRenderer.computeLegend(this.mData);
            /*     */
        }
        /* 107 */
        calculateOffsets();
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    public void calculateOffsets() {
        /* 113 */
        float legendLeft = 0.0F, legendRight = 0.0F, legendBottom = 0.0F, legendTop = 0.0F;
        /*     */
        /* 115 */
        if (this.mLegend != null && this.mLegend.isEnabled() && !this.mLegend.isDrawInsideEnabled()) {
            /*     */
            /* 117 */
            float yLegendOffset, fullLegendWidth = Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler
/* 118 */.getChartWidth() * this.mLegend.getMaxSizePercent());
            /*     */
            /* 120 */
            switch (this.mLegend.getOrientation()) {
                /*     */
                case VERTICAL:
                    /* 122 */
                    float xLegendOffset = 0.0F;
                    /*     */
                    /* 124 */
                    if (this.mLegend.getHorizontalAlignment() == Legend.LegendHorizontalAlignment.LEFT || this.mLegend
/* 125 */.getHorizontalAlignment() == Legend.LegendHorizontalAlignment.RIGHT) {
                        /* 126 */
                        if (this.mLegend.getVerticalAlignment() == Legend.LegendVerticalAlignment.CENTER) {
                            /*     */
                            /* 128 */
                            float spacing = Utils.convertDpToPixel(13.0F);
                            /*     */
                            /* 130 */
                            xLegendOffset = fullLegendWidth + spacing;
                            /*     */
                        }
                        /*     */
                        else {
                            /*     */
                            /* 134 */
                            float spacing = Utils.convertDpToPixel(8.0F);
                            /*     */
                            /* 136 */
                            float legendWidth = fullLegendWidth + spacing;
                            /* 137 */
                            float legendHeight = this.mLegend.mNeededHeight + this.mLegend.mTextHeightMax;
                            /*     */
                            /* 139 */
                            MPPointF center = getCenter();
                            /*     */
                            /*     */
                            /*     */
                            /* 143 */
                            float bottomX = (this.mLegend.getHorizontalAlignment() == Legend.LegendHorizontalAlignment.RIGHT) ? (getWidth() - legendWidth + 15.0F) : (legendWidth - 15.0F);
                            /*     */
                            /* 145 */
                            float bottomY = legendHeight + 15.0F;
                            /* 146 */
                            float distLegend = distanceToCenter(bottomX, bottomY);
                            /*     */
                            /* 148 */
                            MPPointF reference = getPosition(center, getRadius(),
                                    /* 149 */                   getAngleForPoint(bottomX, bottomY));
                            /*     */
                            /* 151 */
                            float distReference = distanceToCenter(reference.x, reference.y);
                            /* 152 */
                            float minOffset = Utils.convertDpToPixel(5.0F);
                            /*     */
                            /* 154 */
                            if (bottomY >= center.y && getHeight() - legendWidth > getWidth()) {
                                /* 155 */
                                xLegendOffset = legendWidth;
                                /* 156 */
                            } else if (distLegend < distReference) {
                                /*     */
                                /* 158 */
                                float diff = distReference - distLegend;
                                /* 159 */
                                xLegendOffset = minOffset + diff;
                                /*     */
                            }
                            /*     */
                            /* 162 */
                            MPPointF.recycleInstance(center);
                            /* 163 */
                            MPPointF.recycleInstance(reference);
                            /*     */
                        }
                        /*     */
                    }
                    /*     */
                    /* 167 */
                    switch (this.mLegend.getHorizontalAlignment()) {
                        /*     */
//                        case VERTICAL:
//                            /* 169 */
//                            legendLeft = xLegendOffset;
//                            /*     */
//                            break;
//                        /*     */
//                        /*     */
//                        case HORIZONTAL:
//                            /* 173 */
//                            legendRight = xLegendOffset;
//                            break;
                    }
                    switch (this.mLegend.getVerticalAlignment())
                        /*     */ {
//                        case VERTICAL:
//                            /* 179 */
//                            legendTop = Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler
///* 180 */.getChartHeight() * this.mLegend.getMaxSizePercent());
//                            break;
//                        /*     */
//                        case HORIZONTAL:
//                            /*     */
//                        case null:
//                            /* 183 */
//                            break;
                    }
                    legendBottom = Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler
/* 184 */.getChartHeight() * this.mLegend.getMaxSizePercent());
                    /*     */
                    break;
                /*     */
                /*     */
                /*     */
                /*     */
                /*     */
                /*     */
                /*     */
                case HORIZONTAL:
                    /* 193 */
                    yLegendOffset = 0.0F;
                    /*     */
                    /* 195 */
                    if (this.mLegend.getVerticalAlignment() == Legend.LegendVerticalAlignment.TOP || this.mLegend
/* 196 */.getVerticalAlignment() == Legend.LegendVerticalAlignment.BOTTOM) {
                        /*     */
                        /*     */
                        /*     */
                        /*     */
                        /* 201 */
                        float yOffset = getRequiredLegendOffset();
                        /*     */
                        /* 203 */
                        yLegendOffset = Math.min(this.mLegend.mNeededHeight + yOffset, this.mViewPortHandler
/* 204 */.getChartHeight() * this.mLegend.getMaxSizePercent());
                        /*     */
                        /* 206 */
                        switch (this.mLegend.getVerticalAlignment())
                            /*     */ {
//                            case VERTICAL:
//                                /* 208 */
//                                legendTop = yLegendOffset;
//                                break;
//                            /*     */
//                            case HORIZONTAL:
//                                /*     */
//                            case null:
//                                /* 211 */
//                                break;
                        }
                        legendBottom = yLegendOffset;
                        /*     */
                    }
                    /*     */
                    break;
                /*     */
            }
            /*     */
            /*     */
            /*     */
            /* 218 */
            legendLeft += getRequiredBaseOffset();
            /* 219 */
            legendRight += getRequiredBaseOffset();
            /* 220 */
            legendTop += getRequiredBaseOffset();
            /* 221 */
            legendBottom += getRequiredBaseOffset();
            /*     */
        }
        /*     */
        /* 224 */
        float minOffset = Utils.convertDpToPixel(this.mMinOffset);
        /*     */
        /* 226 */
        if (this instanceof RadarChart) {
            /* 227 */
            XAxis x = getXAxis();
            /*     */
            /* 229 */
            if (x.isEnabled() && x.isDrawLabelsEnabled()) {
                /* 230 */
                minOffset = Math.max(minOffset, x.mLabelRotatedWidth);
                /*     */
            }
            /*     */
        }
        /*     */
        /* 234 */
        legendTop += getExtraTopOffset();
        /* 235 */
        legendRight += getExtraRightOffset();
        /* 236 */
        legendBottom += getExtraBottomOffset();
        /* 237 */
        legendLeft += getExtraLeftOffset();
        /*     */
        /* 239 */
        float offsetLeft = Math.max(minOffset, legendLeft);
        /* 240 */
        float offsetTop = Math.max(minOffset, legendTop);
        /* 241 */
        float offsetRight = Math.max(minOffset, legendRight);
        /* 242 */
        float offsetBottom = Math.max(minOffset, Math.max(getRequiredBaseOffset(), legendBottom));
        /*     */
        /* 244 */
        this.mViewPortHandler.restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
        /*     */
        /* 246 */
        if (this.mLogEnabled) {
            /* 247 */
            Log.i("MPAndroidChart", "offsetLeft: " + offsetLeft + ", offsetTop: " + offsetTop + ", offsetRight: " + offsetRight + ", offsetBottom: " + offsetBottom);
            /*     */
        }
        /*     */
    }

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
    public float getAngleForPoint(float x, float y) {
        /* 262 */
        MPPointF c = getCenterOffsets();
        /*     */
        /* 264 */
        double tx = (x - c.x), ty = (y - c.y);
        /* 265 */
        double length = Math.sqrt(tx * tx + ty * ty);
        /* 266 */
        double r = Math.acos(ty / length);
        /*     */
        /* 268 */
        float angle = (float) Math.toDegrees(r);
        /*     */
        /* 270 */
        if (x > c.x) {
            /* 271 */
            angle = 360.0F - angle;
            /*     */
        }
        /*     */
        /* 274 */
        angle += 90.0F;
        /*     */
        /*     */
        /* 277 */
        if (angle > 360.0F) {
            /* 278 */
            angle -= 360.0F;
            /*     */
        }
        /* 280 */
        MPPointF.recycleInstance(c);
        /*     */
        /* 282 */
        return angle;
        /*     */
    }

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
    public MPPointF getPosition(MPPointF center, float dist, float angle) {
        /* 297 */
        MPPointF p = MPPointF.getInstance(0.0F, 0.0F);
        /* 298 */
        getPosition(center, dist, angle, p);
        /* 299 */
        return p;
        /*     */
    }

    /*     */
    /*     */
    public void getPosition(MPPointF center, float dist, float angle, MPPointF outputPoint) {
        /* 303 */
        outputPoint.x = (float) (center.x + dist * Math.cos(Math.toRadians(angle)));
        /* 304 */
        outputPoint.y = (float) (center.y + dist * Math.sin(Math.toRadians(angle)));
        /*     */
    }

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
    public float distanceToCenter(float x, float y) {
        /* 317 */
        MPPointF c = getCenterOffsets();
        /*     */
        /* 319 */
        float dist = 0.0F;
        /*     */
        /* 321 */
        float xDist = 0.0F;
        /* 322 */
        float yDist = 0.0F;
        /*     */
        /* 324 */
        if (x > c.x) {
            /* 325 */
            xDist = x - c.x;
            /*     */
        } else {
            /* 327 */
            xDist = c.x - x;
            /*     */
        }
        /*     */
        /* 330 */
        if (y > c.y) {
            /* 331 */
            yDist = y - c.y;
            /*     */
        } else {
            /* 333 */
            yDist = c.y - y;
            /*     */
        }
        /*     */
        /*     */
        /* 337 */
        dist = (float) Math.sqrt(Math.pow(xDist, 2.0D) + Math.pow(yDist, 2.0D));
        /*     */
        /* 339 */
        MPPointF.recycleInstance(c);
        /*     */
        /* 341 */
        return dist;
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public abstract int getIndexForAngle(float paramFloat);

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public void setRotationAngle(float angle) {
        /* 360 */
        this.mRawRotationAngle = angle;
        /* 361 */
        this.mRotationAngle = Utils.getNormalizedAngle(this.mRawRotationAngle);
        /*     */
    }

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
    /* 373 */
    public float getRawRotationAngle() {
        return this.mRawRotationAngle;
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /* 383 */
    public float getRotationAngle() {
        return this.mRotationAngle;
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /* 393 */
    public void setRotationEnabled(boolean enabled) {
        this.mRotateEnabled = enabled;
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /* 402 */
    public boolean isRotationEnabled() {
        return this.mRotateEnabled;
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /* 409 */
    public float getMinOffset() {
        return this.mMinOffset;
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /* 416 */
    public void setMinOffset(float minOffset) {
        this.mMinOffset = minOffset;
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public float getDiameter() {
        /* 425 */
        RectF content = this.mViewPortHandler.getContentRect();
        /* 426 */
        content.left += getExtraLeftOffset();
        /* 427 */
        content.top += getExtraTopOffset();
        /* 428 */
        content.right -= getExtraRightOffset();
        /* 429 */
        content.bottom -= getExtraBottomOffset();
        /* 430 */
        return Math.min(content.width(), content.height());
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public abstract float getRadius();

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    protected abstract float getRequiredLegendOffset();

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    protected abstract float getRequiredBaseOffset();

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /* 458 */
    public float getYChartMax() {
        return 0.0F;
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /* 464 */
    public float getYChartMin() {
        return 0.0F;
    }

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
    @SuppressLint({"NewApi"})
    /*     */ public void spin(int durationmillis, float fromangle, float toangle, Easing.EasingOption easing) {
        /* 482 */
        if (Build.VERSION.SDK_INT < 11) {
            /*     */
            return;
            /*     */
        }
        /* 485 */
        setRotationAngle(fromangle);
        /*     */
        /* 487 */
        ObjectAnimator spinAnimator = ObjectAnimator.ofFloat(this, "rotationAngle", new float[]{fromangle, toangle});
        /*     */
        /* 489 */
        spinAnimator.setDuration(durationmillis);
        /* 490 */
        spinAnimator.setInterpolator(Easing.getEasingFunctionFromOption(easing));
        /*     */
        /* 492 */
        spinAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                /*     */ {
            /*     */
            public void onAnimationUpdate(ValueAnimator animation)
            /*     */ {
                /* 496 */
                PieRadarChartBase.this.postInvalidate();
                /*     */
            }
            /*     */
        });
        /* 499 */
        spinAnimator.start();
        /*     */
    }
    /*     */
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\PieRadarChartBase.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */