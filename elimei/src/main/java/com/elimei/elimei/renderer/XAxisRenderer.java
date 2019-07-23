package com.elimei.elimei.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import com.elimei.elimei.components.LimitLine;
import com.elimei.elimei.components.XAxis;
import com.elimei.elimei.utils.FSize;
import com.elimei.elimei.utils.MPPointD;
import com.elimei.elimei.utils.MPPointF;
import com.elimei.elimei.utils.Transformer;
import com.elimei.elimei.utils.Utils;
import com.elimei.elimei.utils.ViewPortHandler;

import java.util.List;


public class XAxisRenderer extends AxisRenderer {
    protected XAxis mXAxis;
    protected Path mRenderGridLinesPath;
    protected float[] mRenderGridLinesBuffer;
    protected RectF mGridClippingRect;
    protected float[] mRenderLimitLinesBuffer;
    protected RectF mLimitLineClippingRect;
    float[] mLimitLineSegmentsBuffer;
    private Path mLimitLinePath;

    protected void setupGridPaint() {
        this.mGridPaint.setColor(this.mXAxis.getGridColor());
        this.mGridPaint.setStrokeWidth(this.mXAxis.getGridLineWidth());
        this.mGridPaint.setPathEffect(this.mXAxis.getGridDashPathEffect());
    }

    public void computeAxis(float min, float max, boolean inverted) {
        if (this.mViewPortHandler.contentWidth() > 10.0F && !this.mViewPortHandler.isFullyZoomedOutX()) {
            MPPointD p1 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD p2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop());
            if (inverted) {
                min = (float) p2.x;
                max = (float) p1.x;
            } else {
                min = (float) p1.x;
                max = (float) p2.x;
            }
            MPPointD.recycleInstance(p1);
            MPPointD.recycleInstance(p2);
        }
        computeAxisValues(min, max);
    }

    protected void computeAxisValues(float min, float max) {
        super.computeAxisValues(min, max);
        computeSize();
    }

    public XAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
        super(viewPortHandler, trans, xAxis);


        this.mRenderGridLinesPath = new Path();
        this.mRenderGridLinesBuffer = new float[2];

        this.mGridClippingRect = new RectF();

        this.mRenderLimitLinesBuffer = new float[2];

        this.mLimitLineClippingRect = new RectF();

        this.mLimitLineSegmentsBuffer = new float[4];
        /* 347 */
        this.mLimitLinePath = new Path();
        this.mXAxis = xAxis;
        this.mAxisLabelPaint.setColor(-16777216);
        this.mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
        this.mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10.0F));
    }

    protected void computeSize() {
        String longest = this.mXAxis.getLongestLabel();
        this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
        FSize labelSize = Utils.calcTextSize(this.mAxisLabelPaint, longest);
        float labelWidth = labelSize.width;
        float labelHeight = Utils.calcTextHeight(this.mAxisLabelPaint, "Q");
        FSize labelRotatedSize = Utils.getSizeOfRotatedRectangleByDegrees(labelWidth, labelHeight, this.mXAxis.getLabelRotationAngle());
        this.mXAxis.mLabelWidth = Math.round(labelWidth);
        this.mXAxis.mLabelHeight = Math.round(labelHeight);
        this.mXAxis.mLabelRotatedWidth = Math.round(labelRotatedSize.width);
        this.mXAxis.mLabelRotatedHeight = Math.round(labelRotatedSize.height);
        FSize.recycleInstance(labelRotatedSize);
        FSize.recycleInstance(labelSize);
    }

    public void renderAxisLabels(Canvas c) {
        if (!this.mXAxis.isEnabled() || !this.mXAxis.isDrawLabelsEnabled()) return;
        float yoffset = this.mXAxis.getYOffset();
        this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
        MPPointF pointF = MPPointF.getInstance(0.0F, 0.0F);
        if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP) {
            pointF.x = 0.5F;
            pointF.y = 1.0F;
            drawLabels(c, this.mViewPortHandler.contentTop() - yoffset, pointF);
        } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE) {
            pointF.x = 0.5F;
            pointF.y = 1.0F;
            drawLabels(c, this.mViewPortHandler.contentTop() + yoffset + this.mXAxis.mLabelRotatedHeight, pointF);
        } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
            pointF.x = 0.5F;
            pointF.y = 0.0F;
            drawLabels(c, this.mViewPortHandler.contentBottom() + yoffset, pointF);
        } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE) {
            pointF.x = 0.5F;
            pointF.y = 0.0F;
            drawLabels(c, this.mViewPortHandler.contentBottom() - yoffset - this.mXAxis.mLabelRotatedHeight, pointF);
        } else {
            pointF.x = 0.5F;
            pointF.y = 1.0F;
            drawLabels(c, this.mViewPortHandler.contentTop() - yoffset, pointF);
            pointF.x = 0.5F;
            pointF.y = 0.0F;
            drawLabels(c, this.mViewPortHandler.contentBottom() + yoffset, pointF);
        }
        MPPointF.recycleInstance(pointF);
    }

    /* 350 */
    public void renderAxisLine(Canvas c) {
        if (!this.mXAxis.isDrawAxisLineEnabled() || !this.mXAxis.isEnabled()) return;
        this.mAxisLinePaint.setColor(this.mXAxis.getAxisLineColor());
        this.mAxisLinePaint.setStrokeWidth(this.mXAxis.getAxisLineWidth());
        this.mAxisLinePaint.setPathEffect(this.mXAxis.getAxisLineDashPathEffect());
        if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP || this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE || this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)
            ;
        if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM || this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE || this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)
            ;
    }

    public void renderLimitLineLine(Canvas c, LimitLine limitLine, float[] position) {
        this.mLimitLineSegmentsBuffer[0] = position[0];
        /* 351 */
        this.mLimitLineSegmentsBuffer[1] = this.mViewPortHandler.contentTop();
        /* 352 */
        this.mLimitLineSegmentsBuffer[2] = position[0];
        /* 353 */
        this.mLimitLineSegmentsBuffer[3] = this.mViewPortHandler.contentBottom();

        /* 355 */
        this.mLimitLinePath.reset();
        /* 356 */
        this.mLimitLinePath.moveTo(this.mLimitLineSegmentsBuffer[0], this.mLimitLineSegmentsBuffer[1]);
        /* 357 */
        this.mLimitLinePath.lineTo(this.mLimitLineSegmentsBuffer[2], this.mLimitLineSegmentsBuffer[3]);

        /* 359 */
        this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
        /* 360 */
        this.mLimitLinePaint.setColor(limitLine.getLineColor());
        /* 361 */
        this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
        /* 362 */
        this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
    }

    protected void drawLabels(Canvas c, float pos, MPPointF anchor) {
        float labelRotationAngleDegrees = this.mXAxis.getLabelRotationAngle();
        boolean centeringEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
        float[] positions = new float[this.mXAxis.mEntryCount * 2];
        for (int i = 0; i < positions.length; i += 2) {
            if (centeringEnabled) {
                positions[i] = this.mXAxis.mCenteredEntries[i / 2];
            } else {
                positions[i] = this.mXAxis.mEntries[i / 2];
            }
        }
        this.mTrans.pointValuesToPixel(positions);
        for (int i = 0; i < positions.length; i += 2) {
            float x = positions[i];
            if (this.mViewPortHandler.isInBoundsX(x)) {
                String label = this.mXAxis.getValueFormatter().getFormattedValue(this.mXAxis.mEntries[i / 2], this.mXAxis);
                if (this.mXAxis.isAvoidFirstLastClippingEnabled())
                    if (i == this.mXAxis.mEntryCount - 1 && this.mXAxis.mEntryCount > 1) {
                        float width = Utils.calcTextWidth(this.mAxisLabelPaint, label);
                        if (width > this.mViewPortHandler.offsetRight() * 2.0F && x + width > this.mViewPortHandler.getChartWidth())
                            x -= width / 2.0F;
                    } else if (i == 0) {
                        float width = Utils.calcTextWidth(this.mAxisLabelPaint, label);
                        x += width / 2.0F;
                    }
                drawLabel(c, label, x, pos, anchor, labelRotationAngleDegrees);
            }
        }
    }

    protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
        Utils.drawXAxisValue(c, formattedLabel, x, y, this.mAxisLabelPaint, anchor, angleDegrees);
    }

    protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees, int color) {
        Utils.drawXAxisValue(c, formattedLabel, x, y, this.mAxisLabelPaint, anchor, angleDegrees, color);
    }

    public void renderGridLines(Canvas c) {
        if (!this.mXAxis.isDrawGridLinesEnabled() || !this.mXAxis.isEnabled()) return;
        int clipRestoreCount = c.save();
        c.clipRect(getGridClippingRect());
        if (this.mRenderGridLinesBuffer.length != this.mAxis.mEntryCount * 2)
            this.mRenderGridLinesBuffer = new float[this.mXAxis.mEntryCount * 2];
        float[] positions = this.mRenderGridLinesBuffer;
        for (int i = 0; i < positions.length; i += 2) {
            positions[i] = this.mXAxis.mEntries[i / 2];
            positions[i + 1] = this.mXAxis.mEntries[i / 2];
        }
        this.mTrans.pointValuesToPixel(positions);
        setupGridPaint();
        Path gridLinePath = this.mRenderGridLinesPath;
        gridLinePath.reset();
        for (int i = 0; i < positions.length; i += 2)
            drawGridLine(c, positions[i], positions[i + 1], gridLinePath);
        c.restoreToCount(clipRestoreCount);
    }

    public RectF getGridClippingRect() {
        this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mGridClippingRect.inset(-this.mAxis.getGridLineWidth(), 0.0F);
        return this.mGridClippingRect;
    }

    protected void drawGridLine(Canvas c, float x, float y, Path gridLinePath) {
        gridLinePath.moveTo(x, this.mViewPortHandler.contentBottom());
        gridLinePath.lineTo(x, this.mViewPortHandler.contentTop());
        c.drawPath(gridLinePath, this.mGridPaint);
        gridLinePath.reset();
    }

    public void renderLimitLines(Canvas c) {
        List<LimitLine> limitLines = this.mXAxis.getLimitLines();
        if (limitLines == null || limitLines.size() <= 0)
            return;
        float[] position = this.mRenderLimitLinesBuffer;
        position[0] = 0.0F;
        position[1] = 0.0F;
        for (int i = 0; i < limitLines.size(); i++) {
            LimitLine l = (LimitLine) limitLines.get(i);
            if (l.isEnabled()) {
                int clipRestoreCount = c.save();
                this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
                this.mLimitLineClippingRect.inset(-l.getLineWidth(), 0.0F);
                c.clipRect(this.mLimitLineClippingRect);
                position[0] = l.getLimit();
                position[1] = 0.0F;
                this.mTrans.pointValuesToPixel(position);
                renderLimitLineLine(c, l, position);
                renderLimitLineLabel(c, l, position, 2.0F + l.getYOffset());
                c.restoreToCount(clipRestoreCount);
            }
        }
    }

    /* 368 */
    public void renderLimitLineLabel(Canvas c, LimitLine limitLine, float[] position, float yOffset) {
        String label = limitLine.getLabel();


        /* 371 */
        if (label != null && !label.equals("")) {

            /* 373 */
            this.mLimitLinePaint.setStyle(limitLine.getTextStyle());
            /* 374 */
            this.mLimitLinePaint.setPathEffect(null);
            /* 375 */
            this.mLimitLinePaint.setColor(limitLine.getTextColor());
            /* 376 */
            this.mLimitLinePaint.setStrokeWidth(0.5F);
            /* 377 */
            this.mLimitLinePaint.setTextSize(limitLine.getTextSize());


            /* 380 */
            float xOffset = limitLine.getLineWidth() + limitLine.getXOffset();

            /* 382 */
            LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition();

            /* 384 */
            if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_TOP) {

                /* 386 */
                float labelLineHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
                /* 387 */
                this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
                /* 388 */
                c.drawText(label, position[0] + xOffset, this.mViewPortHandler.contentTop() + yOffset + labelLineHeight, this.mLimitLinePaint);
            }
            /* 390 */
            else if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {

                /* 392 */
                this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
                /* 393 */
                c.drawText(label, position[0] + xOffset, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
                /* 394 */
            } else if (labelPosition == LimitLine.LimitLabelPosition.LEFT_TOP) {

                /* 396 */
                this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
                /* 397 */
                float labelLineHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
                /* 398 */
                c.drawText(label, position[0] - xOffset, this.mViewPortHandler.contentTop() + yOffset + labelLineHeight, this.mLimitLinePaint);
            } else {

                /* 402 */
                this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
                /* 403 */
                c.drawText(label, position[0] - xOffset, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
            }
        }
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\XAxisRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */