/*      */
package com.elimei.elimei.renderer;
/*      */
/*      */

import android.graphics.Bitmap;
/*      */ import android.graphics.Canvas;
/*      */ import android.graphics.Color;
/*      */ import android.graphics.Paint;
/*      */ import android.graphics.Path;
/*      */ import android.graphics.RectF;
/*      */ import android.graphics.drawable.Drawable;
/*      */ import android.os.Build;
/*      */ import android.text.Layout;
/*      */ import android.text.StaticLayout;
/*      */ import android.text.TextPaint;
/*      */ import com.elimei.elimei.animation.ChartAnimator;
/*      */ import com.elimei.elimei.charts.PieChart;
/*      */ import com.elimei.elimei.data.Entry;
/*      */ import com.elimei.elimei.data.PieData;
/*      */ import com.elimei.elimei.data.PieDataSet;
/*      */ import com.elimei.elimei.data.PieEntry;
/*      */ import com.elimei.elimei.formatter.IValueFormatter;
/*      */ import com.elimei.elimei.highlight.Highlight;
/*      */ import com.elimei.elimei.interfaces.datasets.IPieDataSet;
/*      */ import com.elimei.elimei.utils.MPPointF;
/*      */ import com.elimei.elimei.utils.Utils;
/*      */ import com.elimei.elimei.utils.ViewPortHandler;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.List;

/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */ public class PieChartRenderer
        /*      */ extends DataRenderer
        /*      */ {
    /*      */   protected PieChart mChart;
    /*      */   protected Paint mHolePaint;
    /*      */   protected Paint mTransparentCirclePaint;
    /*      */   protected Paint mValueLinePaint;
    /*      */   private TextPaint mCenterTextPaint;
    /*      */   private Paint mEntryLabelsPaint;
    /*      */   private StaticLayout mCenterTextLayout;
    /*      */   private CharSequence mCenterTextLastValue;
    /*   61 */   private RectF mCenterTextLastBounds = new RectF();
    /*   62 */   private RectF[] mRectBuffer = {new RectF(), new RectF(), new RectF()};
    /*      */   protected WeakReference<Bitmap> mDrawBitmap;
    /*      */   protected Canvas mBitmapCanvas;
    /*      */   private Path mPathBuffer;
    /*      */   private RectF mInnerRectBuffer;
    /*      */   private Path mHoleCirclePath;
    /*      */   protected Path mDrawCenterTextPathBuffer;
    /*      */   protected RectF mDrawHighlightedRectF;

    /*      */
    /*      */
    public PieChartRenderer(PieChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler)
    /*      */ {
        /*   73 */
        super(animator, viewPortHandler);
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*  152 */
        this.mPathBuffer = new Path();
        /*  153 */
        this.mInnerRectBuffer = new RectF();
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*  642 */
        this.mHoleCirclePath = new Path();
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*  685 */
        this.mDrawCenterTextPathBuffer = new Path();
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*  758 */
        this.mDrawHighlightedRectF = new RectF();
        this.mChart = chart;
        this.mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mHolePaint.setColor(-1);
        this.mHolePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mTransparentCirclePaint.setColor(-1);
        this.mTransparentCirclePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint.setAlpha(105);
        this.mCenterTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        this.mCenterTextPaint.setColor(-16777216);
        this.mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0F));
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(13.0F));
        this.mValuePaint.setColor(-1);
        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mEntryLabelsPaint.setColor(-1);
        this.mEntryLabelsPaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint.setTextSize(Utils.convertDpToPixel(13.0F));
        this.mValueLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mValueLinePaint.setStyle(Paint.Style.STROKE);
    }

    /*      */
    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    /*      */
    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    /*      */
    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    /*  762 */
    public Paint getPaintEntryLabels() {
        return this.mEntryLabelsPaint;
    }

    public void initBuffers() {
    }

    public void drawData(Canvas c) {
        int width = (int) this.mViewPortHandler.getChartWidth();
        int height = (int) this.mViewPortHandler.getChartHeight();
        if (this.mDrawBitmap == null || ((Bitmap) this.mDrawBitmap.get()).getWidth() != width || ((Bitmap) this.mDrawBitmap.get()).getHeight() != height)
            if (width > 0 && height > 0) {
                this.mDrawBitmap = new WeakReference(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444));
                this.mBitmapCanvas = new Canvas((Bitmap) this.mDrawBitmap.get());
            } else {
                return;
            }
        ((Bitmap) this.mDrawBitmap.get()).eraseColor(0);
        PieData pieData = (PieData) this.mChart.getData();
        for (IPieDataSet set : pieData.getDataSets()) {
            if (set.isVisible() && set.getEntryCount() > 0) drawDataSet(c, set);
        }
    }

    protected float calculateMinimumRadiusForSpacedSlice(MPPointF center, float radius, float angle, float arcStartPointX, float arcStartPointY, float startAngle, float sweepAngle) {
        float angleMiddle = startAngle + sweepAngle / 2.0F;
        float arcEndPointX = center.x + radius * (float) Math.cos(((startAngle + sweepAngle) * 0.017453292F));
        float arcEndPointY = center.y + radius * (float) Math.sin(((startAngle + sweepAngle) * 0.017453292F));
        float arcMidPointX = center.x + radius * (float) Math.cos((angleMiddle * 0.017453292F));
        float arcMidPointY = center.y + radius * (float) Math.sin((angleMiddle * 0.017453292F));
        double basePointsDistance = Math.sqrt(Math.pow((arcEndPointX - arcStartPointX), 2.0D) + Math.pow((arcEndPointY - arcStartPointY), 2.0D));
        float containedTriangleHeight = (float) (basePointsDistance / 2.0D * Math.tan((180.0D - angle) / 2.0D * 0.017453292519943295D));
        float spacedRadius = radius - containedTriangleHeight;
        return (float) (spacedRadius - Math.sqrt(Math.pow((arcMidPointX - (arcEndPointX + arcStartPointX) / 2.0F), 2.0D) + Math.pow((arcMidPointY - (arcEndPointY + arcStartPointY) / 2.0F), 2.0D)));
    }

    protected float getSliceSpace(IPieDataSet dataSet) {
        if (!dataSet.isAutomaticallyDisableSliceSpacingEnabled()) return dataSet.getSliceSpace();
        float spaceSizeRatio = dataSet.getSliceSpace() / this.mViewPortHandler.getSmallestContentExtension();
        float minValueRatio = dataSet.getYMin() / ((PieData) this.mChart.getData()).getYValueSum() * 2.0F;
        return (spaceSizeRatio > minValueRatio) ? 0.0F : dataSet.getSliceSpace();
    }

    protected void drawDataSet(Canvas c, IPieDataSet dataSet) {
        float angle = 0.0F;
        float rotationAngle = this.mChart.getRotationAngle();
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        RectF circleBox = this.mChart.getCircleBox();
        int entryCount = dataSet.getEntryCount();
        float[] drawAngles = this.mChart.getDrawAngles();
        MPPointF center = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        boolean drawInnerArc = (this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled());
        float userInnerRadius = drawInnerArc ? (radius * this.mChart.getHoleRadius() / 100.0F) : 0.0F;
        int visibleAngleCount = 0;
        for (int j = 0; j < entryCount; j++) {
            if (Math.abs(((PieEntry) dataSet.getEntryForIndex(j)).getY()) > Utils.FLOAT_EPSILON)
                visibleAngleCount++;
        }
        float sliceSpace = (visibleAngleCount <= 1) ? 0.0F : getSliceSpace(dataSet);
        for (int j = 0; j < entryCount; j++) {
            float sliceAngle = drawAngles[j];
            float innerRadius = userInnerRadius;
            Entry e = dataSet.getEntryForIndex(j);
            if (Math.abs(e.getY()) > Utils.FLOAT_EPSILON) if (!this.mChart.needsHighlight(j)) {
                boolean accountForSliceSpacing = (sliceSpace > 0.0F && sliceAngle <= 180.0F);
                this.mRenderPaint.setColor(dataSet.getColor(j));
                float sliceSpaceAngleOuter = (visibleAngleCount == 1) ? 0.0F : (sliceSpace / 0.017453292F * radius);
                float startAngleOuter = rotationAngle + (angle + sliceSpaceAngleOuter / 2.0F) * phaseY;
                float sweepAngleOuter = (sliceAngle - sliceSpaceAngleOuter) * phaseY;
                if (sweepAngleOuter < 0.0F) sweepAngleOuter = 0.0F;
                this.mPathBuffer.reset();
                float arcStartPointX = center.x + radius * (float) Math.cos((startAngleOuter * 0.017453292F));
                float arcStartPointY = center.y + radius * (float) Math.sin((startAngleOuter * 0.017453292F));
                if (sweepAngleOuter >= 360.0F && sweepAngleOuter % 360.0F <= Utils.FLOAT_EPSILON) {
                    this.mPathBuffer.addCircle(center.x, center.y, radius, Path.Direction.CW);
                } else {
                    this.mPathBuffer.moveTo(arcStartPointX, arcStartPointY);
                    this.mPathBuffer.arcTo(circleBox, startAngleOuter, sweepAngleOuter);
                }
                this.mInnerRectBuffer.set(center.x - innerRadius, center.y - innerRadius, center.x + innerRadius, center.y + innerRadius);
                if (drawInnerArc && (innerRadius > 0.0F || accountForSliceSpacing)) {
                    if (accountForSliceSpacing) {
                        float minSpacedRadius = calculateMinimumRadiusForSpacedSlice(center, radius, sliceAngle * phaseY, arcStartPointX, arcStartPointY, startAngleOuter, sweepAngleOuter);
                        if (minSpacedRadius < 0.0F) minSpacedRadius = -minSpacedRadius;
                        innerRadius = Math.max(innerRadius, minSpacedRadius);
                    }
                    float sliceSpaceAngleInner = (visibleAngleCount == 1 || innerRadius == 0.0F) ? 0.0F : (sliceSpace / 0.017453292F * innerRadius);
                    float startAngleInner = rotationAngle + (angle + sliceSpaceAngleInner / 2.0F) * phaseY;
                    float sweepAngleInner = (sliceAngle - sliceSpaceAngleInner) * phaseY;
                    if (sweepAngleInner < 0.0F) sweepAngleInner = 0.0F;
                    float endAngleInner = startAngleInner + sweepAngleInner;
                    if (sweepAngleOuter >= 360.0F && sweepAngleOuter % 360.0F <= Utils.FLOAT_EPSILON) {
                        this.mPathBuffer.addCircle(center.x, center.y, innerRadius, Path.Direction.CCW);
                    } else {
                        this.mPathBuffer.lineTo(center.x + innerRadius * (float) Math.cos((endAngleInner * 0.017453292F)), center.y + innerRadius * (float) Math.sin((endAngleInner * 0.017453292F)));
                        this.mPathBuffer.arcTo(this.mInnerRectBuffer, endAngleInner, -sweepAngleInner);
                    }
                } else if (sweepAngleOuter % 360.0F > Utils.FLOAT_EPSILON) {
                    if (accountForSliceSpacing) {
                        float angleMiddle = startAngleOuter + sweepAngleOuter / 2.0F;
                        float sliceSpaceOffset = calculateMinimumRadiusForSpacedSlice(center, radius, sliceAngle * phaseY, arcStartPointX, arcStartPointY, startAngleOuter, sweepAngleOuter);
                        float arcEndPointX = center.x + sliceSpaceOffset * (float) Math.cos((angleMiddle * 0.017453292F));
                        float arcEndPointY = center.y + sliceSpaceOffset * (float) Math.sin((angleMiddle * 0.017453292F));
                        this.mPathBuffer.lineTo(arcEndPointX, arcEndPointY);
                    } else {
                        this.mPathBuffer.lineTo(center.x, center.y);
                    }
                }
                this.mPathBuffer.close();
                this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
            }
            angle += sliceAngle * phaseX;
        }
        MPPointF.recycleInstance(center);
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        float phaseX = this.mAnimator.getPhaseX();
        /*  763 */
        float phaseY = this.mAnimator.getPhaseY();
        /*      */
        /*      */
        /*  766 */
        float rotationAngle = this.mChart.getRotationAngle();
        /*      */
        /*  768 */
        float[] drawAngles = this.mChart.getDrawAngles();
        /*  769 */
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        /*  770 */
        MPPointF center = this.mChart.getCenterCircleBox();
        /*  771 */
        float radius = this.mChart.getRadius();
        /*  772 */
        boolean drawInnerArc = (this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled());
        /*      */
        /*  774 */
        float userInnerRadius = drawInnerArc ? (radius * this.mChart.getHoleRadius() / 100.0F) : 0.0F;
        /*      */
        /*      */
        /*  777 */
        RectF highlightedCircleBox = this.mDrawHighlightedRectF;
        /*  778 */
        highlightedCircleBox.set(0.0F, 0.0F, 0.0F, 0.0F);
        /*      */
        /*  780 */
        for (int i = 0; i < indices.length; i++) {
            /*      */
            /*      */
            /*  783 */
            int index = (int) indices[i].getX();
            /*      */
            /*  785 */
            if (index < drawAngles.length) {
                /*      */
                /*      */
                /*      */
                /*  789 */
                IPieDataSet set = ((PieData) this.mChart.getData()).getDataSetByIndex(indices[i]
/*  790 */.getDataSetIndex());
                /*      */
                /*  792 */
                if (set != null && set.isHighlightEnabled()) {
                    /*      */
                    float angle;
                    /*      */
                    /*  795 */
                    int entryCount = set.getEntryCount();
                    /*  796 */
                    int visibleAngleCount = 0;
                    /*  797 */
                    for (int j = 0; j < entryCount; j++) {
                        /*      */
                        /*  799 */
                        if (Math.abs(((PieEntry) set.getEntryForIndex(j)).getY()) > Utils.FLOAT_EPSILON) {
                            /*  800 */
                            visibleAngleCount++;
                            /*      */
                        }
                        /*      */
                    }
                    /*      */
                    /*  804 */
                    if (index == 0) {
                        /*  805 */
                        angle = 0.0F;
                        /*      */
                    } else {
                        /*  807 */
                        angle = absoluteAngles[index - 1] * phaseX;
                        /*      */
                    }
                    /*  809 */
                    float sliceSpace = (visibleAngleCount <= 1) ? 0.0F : set.getSliceSpace();
                    /*      */
                    /*  811 */
                    float sliceAngle = drawAngles[index];
                    /*  812 */
                    float innerRadius = userInnerRadius;
                    /*      */
                    /*  814 */
                    float shift = set.getSelectionShift();
                    /*  815 */
                    float highlightedRadius = radius + shift;
                    /*  816 */
                    highlightedCircleBox.set(this.mChart.getCircleBox());
                    /*  817 */
                    highlightedCircleBox.inset(-shift, -shift);
                    /*      */
                    /*  819 */
                    boolean accountForSliceSpacing = (sliceSpace > 0.0F && sliceAngle <= 180.0F);
                    /*      */
                    /*  821 */
                    this.mRenderPaint.setColor(set.getColor(index));
                    /*      */
                    /*  823 */
                    float sliceSpaceAngleOuter = (visibleAngleCount == 1) ? 0.0F : (sliceSpace / 0.017453292F * radius);
                    /*      */
                    /*      */
                    /*      */
                    /*  827 */
                    float sliceSpaceAngleShifted = (visibleAngleCount == 1) ? 0.0F : (sliceSpace / 0.017453292F * highlightedRadius);
                    /*      */
                    /*      */
                    /*      */
                    /*  831 */
                    float startAngleOuter = rotationAngle + (angle + sliceSpaceAngleOuter / 2.0F) * phaseY;
                    /*  832 */
                    float sweepAngleOuter = (sliceAngle - sliceSpaceAngleOuter) * phaseY;
                    /*  833 */
                    if (sweepAngleOuter < 0.0F) {
                        /*  834 */
                        sweepAngleOuter = 0.0F;
                        /*      */
                    }
                    /*      */
                    /*  837 */
                    float startAngleShifted = rotationAngle + (angle + sliceSpaceAngleShifted / 2.0F) * phaseY;
                    /*  838 */
                    float sweepAngleShifted = (sliceAngle - sliceSpaceAngleShifted) * phaseY;
                    /*  839 */
                    if (sweepAngleShifted < 0.0F) {
                        /*  840 */
                        sweepAngleShifted = 0.0F;
                        /*      */
                    }
                    /*      */
                    /*  843 */
                    this.mPathBuffer.reset();
                    /*      */
                    /*  845 */
                    if (sweepAngleOuter >= 360.0F && sweepAngleOuter % 360.0F <= Utils.FLOAT_EPSILON) {
                        /*      */
                        /*  847 */
                        this.mPathBuffer.addCircle(center.x, center.y, highlightedRadius, Path.Direction.CW);
                        /*      */
                    } else {
                        /*      */
                        /*  850 */
                        this.mPathBuffer.moveTo(center.x + highlightedRadius *
                                /*  851 */                 (float) Math.cos((startAngleShifted * 0.017453292F)), center.y + highlightedRadius *
                                /*  852 */                 (float) Math.sin((startAngleShifted * 0.017453292F)));
                        /*      */
                        /*  854 */
                        this.mPathBuffer.arcTo(highlightedCircleBox, startAngleShifted, sweepAngleShifted);
                        /*      */
                    }
                    /*      */
                    /*      */
                    /*      */
                    /*      */
                    /*      */
                    /*  861 */
                    float sliceSpaceRadius = 0.0F;
                    /*  862 */
                    if (accountForSliceSpacing)
                        /*      */ {
                        /*  864 */
                        sliceSpaceRadius = calculateMinimumRadiusForSpacedSlice(center, radius, sliceAngle * phaseY, center.x + radius *
                                /*      */
                                /*      */
                                /*  867 */                 (float) Math.cos((startAngleOuter * 0.017453292F)), center.y + radius *
                                /*  868 */                 (float) Math.sin((startAngleOuter * 0.017453292F)), startAngleOuter, sweepAngleOuter);
                        /*      */
                    }
                    /*      */
                    /*      */
                    /*      */
                    /*      */
                    /*  874 */
                    this.mInnerRectBuffer.set(center.x - innerRadius, center.y - innerRadius, center.x + innerRadius, center.y + innerRadius);
                    /*      */
                    /*      */
                    /*      */
                    /*      */
                    /*      */
                    /*  880 */
                    if (drawInnerArc && (innerRadius > 0.0F || accountForSliceSpacing)) {
                        /*      */
                        /*      */
                        /*  883 */
                        if (accountForSliceSpacing) {
                            /*  884 */
                            float minSpacedRadius = sliceSpaceRadius;
                            /*      */
                            /*  886 */
                            if (minSpacedRadius < 0.0F) {
                                /*  887 */
                                minSpacedRadius = -minSpacedRadius;
                                /*      */
                            }
                            /*  889 */
                            innerRadius = Math.max(innerRadius, minSpacedRadius);
                            /*      */
                        }
                        /*      */
                        /*  892 */
                        float sliceSpaceAngleInner = (visibleAngleCount == 1 || innerRadius == 0.0F) ? 0.0F : (sliceSpace / 0.017453292F * innerRadius);
                        /*      */
                        /*      */
                        /*  895 */
                        float startAngleInner = rotationAngle + (angle + sliceSpaceAngleInner / 2.0F) * phaseY;
                        /*  896 */
                        float sweepAngleInner = (sliceAngle - sliceSpaceAngleInner) * phaseY;
                        /*  897 */
                        if (sweepAngleInner < 0.0F) {
                            /*  898 */
                            sweepAngleInner = 0.0F;
                            /*      */
                        }
                        /*  900 */
                        float endAngleInner = startAngleInner + sweepAngleInner;
                        /*      */
                        /*  902 */
                        if (sweepAngleOuter >= 360.0F && sweepAngleOuter % 360.0F <= Utils.FLOAT_EPSILON) {
                            /*      */
                            /*  904 */
                            this.mPathBuffer.addCircle(center.x, center.y, innerRadius, Path.Direction.CCW);
                            /*      */
                        } else {
                            /*      */
                            /*  907 */
                            this.mPathBuffer.lineTo(center.x + innerRadius *
                                    /*  908 */                   (float) Math.cos((endAngleInner * 0.017453292F)), center.y + innerRadius *
                                    /*  909 */                   (float) Math.sin((endAngleInner * 0.017453292F)));
                            /*      */
                            /*  911 */
                            this.mPathBuffer.arcTo(this.mInnerRectBuffer, endAngleInner, -sweepAngleInner);
                            /*      */
                            /*      */
                            /*      */
                        }
                        /*      */
                        /*      */
                        /*      */
                        /*      */
                    }
                    /*  919 */
                    else if (sweepAngleOuter % 360.0F > Utils.FLOAT_EPSILON) {
                        /*      */
                        /*  921 */
                        if (accountForSliceSpacing) {
                            /*  922 */
                            float angleMiddle = startAngleOuter + sweepAngleOuter / 2.0F;
                            /*      */
                            /*      */
                            /*  925 */
                            float arcEndPointX = center.x + sliceSpaceRadius * (float) Math.cos((angleMiddle * 0.017453292F));
                            /*      */
                            /*  927 */
                            float arcEndPointY = center.y + sliceSpaceRadius * (float) Math.sin((angleMiddle * 0.017453292F));
                            /*      */
                            /*  929 */
                            this.mPathBuffer.lineTo(arcEndPointX, arcEndPointY);
                            /*      */
                            /*      */
                        }
                        /*      */
                        else {
                            /*      */
                            /*      */
                            /*  935 */
                            this.mPathBuffer.lineTo(center.x, center.y);
                            /*      */
                        }
                        /*      */
                    }
                    /*      */
                    /*      */
                    /*      */
                    /*      */
                    /*      */
                    /*      */
                    /*  944 */
                    this.mPathBuffer.close();
                    /*      */
                    /*  946 */
                    this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                    /*      */
                }
                /*      */
            }
            /*  949 */
        }
        MPPointF.recycleInstance(center);
    }

    /*      */
    public void drawValues(Canvas c) {
        MPPointF center = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles = this.mChart.getDrawAngles();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float holeRadiusPercent = this.mChart.getHoleRadius() / 100.0F;
        float labelRadiusOffset = radius / 10.0F * 3.6F;
        if (this.mChart.isDrawHoleEnabled())
            /*      */ labelRadiusOffset = (radius - radius * holeRadiusPercent) / 2.0F;
        float labelRadius = radius - labelRadiusOffset;
        PieData data = (PieData) this.mChart.getData();
        List<IPieDataSet> dataSets = data.getDataSets();
        float yValueSum = data.getYValueSum();
        boolean drawEntryLabels = this.mChart.isDrawEntryLabelsEnabled();
        int xIndex = 0;
        c.save();
        float offset = Utils.convertDpToPixel(5.0F);
        for (int i = 0; i < dataSets.size(); i++) {
            IPieDataSet dataSet = (IPieDataSet) dataSets.get(i);
            boolean drawValues = dataSet.isDrawValuesEnabled();
            if (drawValues || drawEntryLabels) {
                PieDataSet.ValuePosition xValuePosition = dataSet.getXValuePosition();
                PieDataSet.ValuePosition yValuePosition = dataSet.getYValuePosition();
                applyValueTextStyle(dataSet);
                float lineHeight = Utils.calcTextHeight(this.mValuePaint, "Q") + Utils.convertDpToPixel(4.0F);
                IValueFormatter formatter = dataSet.getValueFormatter();
                int entryCount = dataSet.getEntryCount();
                this.mValueLinePaint.setColor(dataSet.getValueLineColor());
                this.mValueLinePaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getValueLineWidth()));
                float sliceSpace = getSliceSpace(dataSet);
                MPPointF iconsOffset = MPPointF.getInstance(dataSet.getIconsOffset());
                iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
                iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);
                for (int j = 0; j < entryCount; j++) {
                    float angle;
                    PieEntry entry = (PieEntry) dataSet.getEntryForIndex(j);
                    if (xIndex == 0) {
                        angle = 0.0F;
                    } else {
                        angle = absoluteAngles[xIndex - 1] * phaseX;
                    }
                    float sliceAngle = drawAngles[xIndex];
                    float sliceSpaceMiddleAngle = sliceSpace / 0.017453292F * labelRadius;
                    float angleOffset = (sliceAngle - sliceSpaceMiddleAngle / 2.0F) / 2.0F;
                    angle += angleOffset;
                    float transformedAngle = rotationAngle + angle * phaseY;
                    float value = this.mChart.isUsePercentValuesEnabled() ? (entry.getY() / yValueSum * 100.0F) : entry.getY();
                    float sliceXBase = (float) Math.cos((transformedAngle * 0.017453292F));
                    float sliceYBase = (float) Math.sin((transformedAngle * 0.017453292F));
                    boolean drawXOutside = (drawEntryLabels && xValuePosition == PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    boolean drawYOutside = (drawValues && yValuePosition == PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    boolean drawXInside = (drawEntryLabels && xValuePosition == PieDataSet.ValuePosition.INSIDE_SLICE);
                    boolean drawYInside = (drawValues && yValuePosition == PieDataSet.ValuePosition.INSIDE_SLICE);
                    if (drawXOutside || drawYOutside) {
                        float line1Radius, labelPty, labelPtx, pt2y, pt2x, valueLineLength1 = dataSet.getValueLinePart1Length();
                        float valueLineLength2 = dataSet.getValueLinePart2Length();
                        float valueLinePart1OffsetPercentage = dataSet.getValueLinePart1OffsetPercentage() / 100.0F;
                        if (this.mChart.isDrawHoleEnabled()) {
                            line1Radius = (radius - radius * holeRadiusPercent) * valueLinePart1OffsetPercentage + radius * holeRadiusPercent;
                        } else {
                            line1Radius = radius * valueLinePart1OffsetPercentage;
                        }
                        float polyline2Width = dataSet.isValueLineVariableLength() ? (labelRadius * valueLineLength2 * (float) Math.abs(Math.sin((transformedAngle * 0.017453292F)))) : (labelRadius * valueLineLength2);
                        float pt0x = line1Radius * sliceXBase + center.x;
                        float pt0y = line1Radius * sliceYBase + center.y;
                        float pt1x = labelRadius * (1.0F + valueLineLength1) * sliceXBase + center.x;
                        float pt1y = labelRadius * (1.0F + valueLineLength1) * sliceYBase + center.y;
                        if (transformedAngle % 360.0D >= 90.0D && transformedAngle % 360.0D <= 270.0D) {
                            pt2x = pt1x - polyline2Width;
                            pt2y = pt1y;
                            this.mValuePaint.setTextAlign(Paint.Align.RIGHT);
                            if (drawXOutside)
                                /*      */ this.mEntryLabelsPaint.setTextAlign(Paint.Align.RIGHT);
                            labelPtx = pt2x - offset;
                            labelPty = pt2y;
                        } else {
                            pt2x = pt1x + polyline2Width;
                            pt2y = pt1y;
                            this.mValuePaint.setTextAlign(Paint.Align.LEFT);
                            if (drawXOutside)
                                /*      */ this.mEntryLabelsPaint.setTextAlign(Paint.Align.LEFT);
                            labelPtx = pt2x + offset;
                            labelPty = pt2y;
                        }
                        if (dataSet.getValueLineColor() != 1122867) {
                            c.drawLine(pt0x, pt0y, pt1x, pt1y, this.mValueLinePaint);
                            c.drawLine(pt1x, pt1y, pt2x, pt2y, this.mValueLinePaint);
                        }
                        if (drawXOutside && drawYOutside) {
                            drawValue(c, formatter, value, entry, 0, labelPtx, labelPty, dataSet.getValueTextColor(j));
                            if (j < data.getEntryCount() && entry.getLabel() != null)
                                /*      */
                                drawEntryLabel(c, entry.getLabel(), labelPtx, labelPty + lineHeight);
                        } else if (drawXOutside) {
                            if (j < data.getEntryCount() && entry.getLabel() != null)
                                /*      */
                                drawEntryLabel(c, entry.getLabel(), labelPtx, labelPty + lineHeight / 2.0F);
                        } else if (drawYOutside) {
                            drawValue(c, formatter, value, entry, 0, labelPtx, labelPty + lineHeight / 2.0F, dataSet.getValueTextColor(j));
                        }
                    }
                    if (drawXInside || drawYInside) {
                        float x = labelRadius * sliceXBase + center.x;
                        float y = labelRadius * sliceYBase + center.y;
                        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
                        if (drawXInside && drawYInside) {
                            drawValue(c, formatter, value, entry, 0, x, y, dataSet.getValueTextColor(j));
                            if (j < data.getEntryCount() && entry.getLabel() != null)
                                /*      */ drawEntryLabel(c, entry.getLabel(), x, y + lineHeight);
                        } else if (drawXInside) {
                            if (j < data.getEntryCount() && entry.getLabel() != null)
                                /*      */
                                drawEntryLabel(c, entry.getLabel(), x, y + lineHeight / 2.0F);
                        } else if (drawYInside) {
                            drawValue(c, formatter, value, entry, 0, x, y + lineHeight / 2.0F, dataSet.getValueTextColor(j));
                        }
                    }
                    if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                        Drawable icon = entry.getIcon();
                        float x = (labelRadius + iconsOffset.y) * sliceXBase + center.x;
                        float y = (labelRadius + iconsOffset.y) * sliceYBase + center.y;
                        y += iconsOffset.x;
                        Utils.drawImage(c, icon, (int) x, (int) y, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    xIndex++;
                }
                MPPointF.recycleInstance(iconsOffset);
            }
            /*      */
        }
        /*  959 */
        MPPointF.recycleInstance(center);
        c.restore();
    }

    protected void drawRoundedSlices(Canvas c) {
        if (!this.mChart.isDrawRoundedSlicesEnabled()) {
            /*      */
            return;
            /*      */
        }
        /*  962 */
        IPieDataSet dataSet = ((PieData) this.mChart.getData()).getDataSet();
        /*      */
        /*  964 */
        if (!dataSet.isVisible()) {
            /*      */
            return;
            /*      */
        }
        /*  967 */
        float phaseX = this.mAnimator.getPhaseX();
        /*  968 */
        float phaseY = this.mAnimator.getPhaseY();
        /*      */
        /*  970 */
        MPPointF center = this.mChart.getCenterCircleBox();
        /*  971 */
        float r = this.mChart.getRadius();
        /*      */
        /*      */
        /*  974 */
        float circleRadius = (r - r * this.mChart.getHoleRadius() / 100.0F) / 2.0F;
        /*      */
        /*  976 */
        float[] drawAngles = this.mChart.getDrawAngles();
        /*  977 */
        float angle = this.mChart.getRotationAngle();
        /*      */
        /*  979 */
        for (int j = 0; j < dataSet.getEntryCount(); j++) {
            /*      */
            /*  981 */
            float sliceAngle = drawAngles[j];
            /*      */
            /*  983 */
            Entry e = dataSet.getEntryForIndex(j);
            /*      */
            /*      */
            /*  986 */
            if (Math.abs(e.getY()) > Utils.FLOAT_EPSILON) {
                /*      */
                /*      */
                /*  989 */
                float x = (float) ((r - circleRadius) * Math.cos(Math.toRadians(((angle + sliceAngle) * phaseY))) + center.x);
                /*      */
                /*      */
                /*  992 */
                float y = (float) ((r - circleRadius) * Math.sin(Math.toRadians(((angle + sliceAngle) * phaseY))) + center.y);
                /*      */
                /*      */
                /*  995 */
                this.mRenderPaint.setColor(dataSet.getColor(j));
                /*  996 */
                this.mBitmapCanvas.drawCircle(x, y, circleRadius, this.mRenderPaint);
                /*      */
            }
            /*      */
            /*  999 */
            angle += sliceAngle * phaseX;
            /*      */
        }
        /* 1001 */
        MPPointF.recycleInstance(center);
    }

    protected void drawEntryLabel(Canvas c, String label, float x, float y) {
        c.drawText(label, x, y, this.mEntryLabelsPaint);
    }

    public void drawExtras(Canvas c) {
        drawHole(c);
        c.drawBitmap((Bitmap) this.mDrawBitmap.get(), 0.0F, 0.0F, null);
        drawCenterText(c);
    }

    /*      */
    protected void drawHole(Canvas c) {
        if (this.mChart.isDrawHoleEnabled() && this.mBitmapCanvas != null) {
            float radius = this.mChart.getRadius();
            float holeRadius = radius * this.mChart.getHoleRadius() / 100.0F;
            MPPointF center = this.mChart.getCenterCircleBox();
            if (Color.alpha(this.mHolePaint.getColor()) > 0)
                /*      */
                this.mBitmapCanvas.drawCircle(center.x, center.y, holeRadius, this.mHolePaint);
            if (Color.alpha(this.mTransparentCirclePaint.getColor()) > 0 && this.mChart.getTransparentCircleRadius() > this.mChart.getHoleRadius()) {
                int alpha = this.mTransparentCirclePaint.getAlpha();
                float secondHoleRadius = radius * this.mChart.getTransparentCircleRadius() / 100.0F;
                this.mTransparentCirclePaint.setAlpha((int) (alpha * this.mAnimator.getPhaseX() * this.mAnimator.getPhaseY()));
                this.mHoleCirclePath.reset();
                this.mHoleCirclePath.addCircle(center.x, center.y, secondHoleRadius, Path.Direction.CW);
                this.mHoleCirclePath.addCircle(center.x, center.y, holeRadius, Path.Direction.CCW);
                this.mBitmapCanvas.drawPath(this.mHoleCirclePath, this.mTransparentCirclePaint);
                this.mTransparentCirclePaint.setAlpha(alpha);
            }
            MPPointF.recycleInstance(center);
        }
    }

    /*      */
    protected void drawCenterText(Canvas c) {
        CharSequence centerText = this.mChart.getCenterText();
        if (this.mChart.isDrawCenterTextEnabled() && centerText != null) {
            MPPointF center = this.mChart.getCenterCircleBox();
            MPPointF offset = this.mChart.getCenterTextOffset();
            float x = center.x + offset.x;
            float y = center.y + offset.y;
            float innerRadius = (this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled()) ? (this.mChart.getRadius() * this.mChart.getHoleRadius() / 100.0F) : this.mChart.getRadius();
            RectF holeRect = this.mRectBuffer[0];
            holeRect.left = x - innerRadius;
            holeRect.top = y - innerRadius;
            holeRect.right = x + innerRadius;
            holeRect.bottom = y + innerRadius;
            RectF boundingRect = this.mRectBuffer[1];
            boundingRect.set(holeRect);
            float radiusPercent = this.mChart.getCenterTextRadiusPercent() / 100.0F;
            if (radiusPercent > 0.0D)
                /*      */
                boundingRect.inset((boundingRect.width() - boundingRect.width() * radiusPercent) / 2.0F, (boundingRect.height() - boundingRect.height() * radiusPercent) / 2.0F);
            if (!centerText.equals(this.mCenterTextLastValue) || !boundingRect.equals(this.mCenterTextLastBounds)) {
                this.mCenterTextLastBounds.set(boundingRect);
                this.mCenterTextLastValue = centerText;
                float width = this.mCenterTextLastBounds.width();
                this.mCenterTextLayout = new StaticLayout(centerText, 0, centerText.length(), this.mCenterTextPaint, (int) Math.max(Math.ceil(width), 1.0D), Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, false);
            }
            float layoutHeight = this.mCenterTextLayout.getHeight();
            c.save();
            if (Build.VERSION.SDK_INT >= 18) {
                Path path = this.mDrawCenterTextPathBuffer;
                path.reset();
                path.addOval(holeRect, Path.Direction.CW);
                c.clipPath(path);
            }
            /*      */
            c.translate(boundingRect.left, boundingRect.top + (boundingRect.height() - layoutHeight) / 2.0F);
            this.mCenterTextLayout.draw(c);
            c.restore();
            MPPointF.recycleInstance(center);
            MPPointF.recycleInstance(offset);
        }
        /*      */
    }

    /* 1008 */
    public void releaseBitmap() {
        if (this.mBitmapCanvas != null) {
            /* 1009 */
            this.mBitmapCanvas.setBitmap(null);
            /* 1010 */
            this.mBitmapCanvas = null;
            /*      */
        }
        /* 1012 */
        if (this.mDrawBitmap != null) {
            /* 1013 */
            ((Bitmap) this.mDrawBitmap.get()).recycle();
            /* 1014 */
            this.mDrawBitmap.clear();
            /* 1015 */
            this.mDrawBitmap = null;
            /*      */
        }
    }
    /*      */
    /*      */
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\PieChartRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */