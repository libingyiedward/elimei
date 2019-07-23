package com.elimei.elimei.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.elimei.elimei.components.XAxis;
import com.elimei.elimei.data.PieData;
import com.elimei.elimei.data.PieEntry;
import com.elimei.elimei.highlight.Highlight;
import com.elimei.elimei.highlight.PieHighlighter;
import com.elimei.elimei.interfaces.datasets.IPieDataSet;
import com.elimei.elimei.renderer.PieChartRenderer;
import com.elimei.elimei.utils.MPPointF;
import com.elimei.elimei.utils.Utils;

import java.util.List;


public class PieChart
        extends PieRadarChartBase<PieData> {
    /*  33 */   private RectF mCircleBox = new RectF();


    private boolean mDrawEntryLabels = true;


    /*  43 */   private float[] mDrawAngles = new float[1];


    /*  48 */   private float[] mAbsoluteAngles = new float[1];


    private boolean mDrawHole = true;


    private boolean mDrawSlicesUnderHole = false;


    private boolean mUsePercentValues = false;


    private boolean mDrawRoundedSlices = false;


    /*  73 */   private CharSequence mCenterText = "";

    /*  75 */   private MPPointF mCenterTextOffset = MPPointF.getInstance(0.0F, 0.0F);


    /*  81 */   private float mHoleRadiusPercent = 50.0F;


    /*  86 */   protected float mTransparentCircleRadiusPercent = 55.0F;


    private boolean mDrawCenterText = true;


    /*  93 */   private float mCenterTextRadiusPercent = 100.0F;

    /*  95 */   protected float mMaxAngle = 360.0F;


    /*  98 */
    public PieChart(Context context) {
        super(context);
    }


    /* 102 */
    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /* 106 */
    public PieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    protected void init() {
        /* 111 */
        super.init();

        /* 113 */
        this.mRenderer = new PieChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        /* 114 */
        this.mXAxis = null;

        /* 116 */
        this.mHighlighter = new PieHighlighter(this);
    }


    protected void onDraw(Canvas canvas) {
        /* 121 */
        super.onDraw(canvas);

        /* 123 */
        if (this.mData == null) {
            return;
        }
        /* 126 */
        this.mRenderer.drawData(canvas);

        /* 128 */
        if (valuesToHighlight()) {
            /* 129 */
            this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
        }
        /* 131 */
        this.mRenderer.drawExtras(canvas);

        /* 133 */
        this.mRenderer.drawValues(canvas);

        /* 135 */
        this.mLegendRenderer.renderLegend(canvas);

        /* 137 */
        drawDescription(canvas);

        /* 139 */
        drawMarkers(canvas);
    }


    public void calculateOffsets() {
        /* 144 */
        super.calculateOffsets();


        /* 147 */
        if (this.mData == null) {
            return;
        }
        /* 150 */
        float diameter = getDiameter();
        /* 151 */
        float radius = diameter / 2.0F;

        /* 153 */
        MPPointF c = getCenterOffsets();

        /* 155 */
        float shift = ((PieData) this.mData).getDataSet().getSelectionShift();



        /* 159 */
        this.mCircleBox.set(c.x - radius + shift, c.y - radius + shift, c.x + radius - shift, c.y + radius - shift);




        /* 164 */
        MPPointF.recycleInstance(c);
    }


    /* 169 */
    protected void calcMinMax() {
        calcAngles();
    }


    protected float[] getMarkerPosition(Highlight highlight) {
        /* 175 */
        MPPointF center = getCenterCircleBox();
        /* 176 */
        float r = getRadius();

        /* 178 */
        float off = r / 10.0F * 3.6F;

        /* 180 */
        if (isDrawHoleEnabled()) {
            /* 181 */
            off = (r - r / 100.0F * getHoleRadius()) / 2.0F;
        }

        /* 184 */
        r -= off;

        /* 186 */
        float rotationAngle = getRotationAngle();

        /* 188 */
        int entryIndex = (int) highlight.getX();


        /* 191 */
        float offset = this.mDrawAngles[entryIndex] / 2.0F;



        /* 195 */
        float x = (float) (r * Math.cos(Math.toRadians(((rotationAngle + this.mAbsoluteAngles[entryIndex] - offset) * this.mAnimator
/* 196 */.getPhaseY()))) + center.x);

        /* 198 */
        float y = (float) (r * Math.sin(Math.toRadians(((rotationAngle + this.mAbsoluteAngles[entryIndex] - offset) * this.mAnimator
/* 199 */.getPhaseY()))) + center.y);

        /* 201 */
        MPPointF.recycleInstance(center);
        /* 202 */
        return new float[]{x, y};
    }


    private void calcAngles() {
        /* 210 */
        int entryCount = ((PieData) this.mData).getEntryCount();

        /* 212 */
        if (this.mDrawAngles.length != entryCount) {
            /* 213 */
            this.mDrawAngles = new float[entryCount];
        } else {
            /* 215 */
            for (int i = 0; i < entryCount; i++) {
                /* 216 */
                this.mDrawAngles[i] = 0.0F;
            }
        }
        /* 219 */
        if (this.mAbsoluteAngles.length != entryCount) {
            /* 220 */
            this.mAbsoluteAngles = new float[entryCount];
        } else {
            /* 222 */
            for (int i = 0; i < entryCount; i++) {
                /* 223 */
                this.mAbsoluteAngles[i] = 0.0F;
            }
        }

        /* 227 */
        float yValueSum = ((PieData) this.mData).getYValueSum();

        /* 229 */
        List<IPieDataSet> dataSets = ((PieData) this.mData).getDataSets();

        /* 231 */
        int cnt = 0;

        /* 233 */
        for (int i = 0; i < ((PieData) this.mData).getDataSetCount(); i++) {

            /* 235 */
            IPieDataSet set = (IPieDataSet) dataSets.get(i);

            /* 237 */
            for (int j = 0; j < set.getEntryCount(); j++) {

                /* 239 */
                this.mDrawAngles[cnt] = calcAngle(Math.abs(((PieEntry) set.getEntryForIndex(j)).getY()), yValueSum);

                /* 241 */
                if (cnt == 0) {
                    /* 242 */
                    this.mAbsoluteAngles[cnt] = this.mDrawAngles[cnt];
                } else {
                    /* 244 */
                    this.mAbsoluteAngles[cnt] = this.mAbsoluteAngles[cnt - 1] + this.mDrawAngles[cnt];
                }

                /* 247 */
                cnt++;
            }
        }
    }


    public boolean needsHighlight(int index) {
        /* 262 */
        if (!valuesToHighlight()) {
            /* 263 */
            return false;
        }
        /* 265 */
        for (int i = 0; i < this.mIndicesToHighlight.length; i++) {


            /* 268 */
            if ((int) this.mIndicesToHighlight[i].getX() == index)
                /* 269 */ return true;
        }
        /* 271 */
        return false;
    }


    /* 281 */
    private float calcAngle(float value) {
        return calcAngle(value, ((PieData) this.mData).getYValueSum());
    }


    /* 292 */
    private float calcAngle(float value, float yValueSum) {
        return value / yValueSum * this.mMaxAngle;
    }


    @Deprecated
    /* 303 */ public XAxis getXAxis() {
        throw new RuntimeException("PieChart has no XAxis");
    }


    public int getIndexForAngle(float angle) {
        /* 310 */
        float a = Utils.getNormalizedAngle(angle - getRotationAngle());

        /* 312 */
        for (int i = 0; i < this.mAbsoluteAngles.length; i++) {
            /* 313 */
            if (this.mAbsoluteAngles[i] > a) {
                /* 314 */
                return i;
            }
        }
        /* 317 */
        return -1;
    }


    public int getDataSetIndexForIndex(int xIndex) {
        /* 328 */
        List<IPieDataSet> dataSets = ((PieData) this.mData).getDataSets();

        /* 330 */
        for (int i = 0; i < dataSets.size(); i++) {
            /* 331 */
            if (((IPieDataSet) dataSets.get(i)).getEntryForXValue(xIndex, 0f) != null) {
                /* 332 */
                return i;
            }
        }
        /* 335 */
        return -1;
    }


    /* 346 */
    public float[] getDrawAngles() {
        return this.mDrawAngles;
    }


    /* 356 */
    public float[] getAbsoluteAngles() {
        return this.mAbsoluteAngles;
    }


    /* 366 */
    public void setHoleColor(int color) {
        ((PieChartRenderer) this.mRenderer).getPaintHole().setColor(color);
    }


    /* 373 */
    public void setDrawSlicesUnderHole(boolean enable) {
        this.mDrawSlicesUnderHole = enable;
    }


    /* 383 */
    public boolean isDrawSlicesUnderHoleEnabled() {
        return this.mDrawSlicesUnderHole;
    }


    /* 392 */
    public void setDrawHoleEnabled(boolean enabled) {
        this.mDrawHole = enabled;
    }


    /* 402 */
    public boolean isDrawHoleEnabled() {
        return this.mDrawHole;
    }


    public void setCenterText(CharSequence text) {
        /* 411 */
        if (text == null) {
            /* 412 */
            this.mCenterText = "";
        } else {
            /* 414 */
            this.mCenterText = text;
        }
    }


    /* 423 */
    public CharSequence getCenterText() {
        return this.mCenterText;
    }


    /* 433 */
    public void setDrawCenterText(boolean enabled) {
        this.mDrawCenterText = enabled;
    }


    /* 442 */
    public boolean isDrawCenterTextEnabled() {
        return this.mDrawCenterText;
    }


    /* 447 */
    protected float getRequiredLegendOffset() {
        return this.mLegendRenderer.getLabelPaint().getTextSize() * 2.0F;
    }


    /* 452 */
    protected float getRequiredBaseOffset() {
        return 0.0F;
    }


    public float getRadius() {
        /* 457 */
        if (this.mCircleBox == null) {
            /* 458 */
            return 0.0F;
        }
        /* 460 */
        return Math.min(this.mCircleBox.width() / 2.0F, this.mCircleBox.height() / 2.0F);
    }


    /* 469 */
    public RectF getCircleBox() {
        return this.mCircleBox;
    }


    /* 478 */
    public MPPointF getCenterCircleBox() {
        return MPPointF.getInstance(this.mCircleBox.centerX(), this.mCircleBox.centerY());
    }


    /* 487 */
    public void setCenterTextTypeface(Typeface t) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTypeface(t);
    }


    /* 496 */
    public void setCenterTextSize(float sizeDp) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(
                /* 497 */         Utils.convertDpToPixel(sizeDp));
    }


    /* 506 */
    public void setCenterTextSizePixels(float sizePixels) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(sizePixels);
    }


    public void setCenterTextOffset(float x, float y) {
        /* 516 */
        this.mCenterTextOffset.x = Utils.convertDpToPixel(x);
        /* 517 */
        this.mCenterTextOffset.y = Utils.convertDpToPixel(y);
    }


    /* 526 */
    public MPPointF getCenterTextOffset() {
        return MPPointF.getInstance(this.mCenterTextOffset.x, this.mCenterTextOffset.y);
    }


    /* 535 */
    public void setCenterTextColor(int color) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setColor(color);
    }


    /* 545 */
    public void setHoleRadius(float percent) {
        this.mHoleRadiusPercent = percent;
    }


    /* 554 */
    public float getHoleRadius() {
        return this.mHoleRadiusPercent;
    }


    public void setTransparentCircleColor(int color) {
        /* 564 */
        Paint p = ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle();
        /* 565 */
        int alpha = p.getAlpha();
        /* 566 */
        p.setColor(color);
        /* 567 */
        p.setAlpha(alpha);
    }


    /* 579 */
    public void setTransparentCircleRadius(float percent) {
        this.mTransparentCircleRadiusPercent = percent;
    }


    /* 583 */
    public float getTransparentCircleRadius() {
        return this.mTransparentCircleRadiusPercent;
    }


    /* 594 */
    public void setTransparentCircleAlpha(int alpha) {
        ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle().setAlpha(alpha);
    }


    @Deprecated
    /* 605 */ public void setDrawSliceText(boolean enabled) {
        this.mDrawEntryLabels = enabled;
    }


    /* 614 */
    public void setDrawEntryLabels(boolean enabled) {
        this.mDrawEntryLabels = enabled;
    }


    /* 623 */
    public boolean isDrawEntryLabelsEnabled() {
        return this.mDrawEntryLabels;
    }


    /* 632 */
    public void setEntryLabelColor(int color) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setColor(color);
    }


    /* 641 */
    public void setEntryLabelTypeface(Typeface tf) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setTypeface(tf);
    }


    /* 650 */
    public void setEntryLabelTextSize(float size) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setTextSize(Utils.convertDpToPixel(size));
    }


    /* 660 */
    public boolean isDrawRoundedSlicesEnabled() {
        return this.mDrawRoundedSlices;
    }


    /* 671 */
    public void setUsePercentValues(boolean enabled) {
        this.mUsePercentValues = enabled;
    }


    /* 680 */
    public boolean isUsePercentValuesEnabled() {
        return this.mUsePercentValues;
    }


    /* 689 */
    public void setCenterTextRadiusPercent(float percent) {
        this.mCenterTextRadiusPercent = percent;
    }


    /* 698 */
    public float getCenterTextRadiusPercent() {
        return this.mCenterTextRadiusPercent;
    }


    /* 702 */
    public float getMaxAngle() {
        return this.mMaxAngle;
    }


    public void setMaxAngle(float maxangle) {
        /* 713 */
        if (maxangle > 360.0F) {
            /* 714 */
            maxangle = 360.0F;
        }
        /* 716 */
        if (maxangle < 90.0F) {
            /* 717 */
            maxangle = 90.0F;
        }
        /* 719 */
        this.mMaxAngle = maxangle;
    }


    protected void onDetachedFromWindow() {
        /* 725 */
        if (this.mRenderer != null && this.mRenderer instanceof PieChartRenderer) {
            /* 726 */
            ((PieChartRenderer) this.mRenderer).releaseBitmap();
        }
        /* 728 */
        super.onDetachedFromWindow();
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\PieChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */