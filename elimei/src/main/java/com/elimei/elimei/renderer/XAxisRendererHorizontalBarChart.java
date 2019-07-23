/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.RectF;
/*     */ import com.elimei.elimei.charts.BarChart;
/*     */ import com.elimei.elimei.components.LimitLine;
/*     */ import com.elimei.elimei.components.XAxis;
/*     */ import com.elimei.elimei.utils.FSize;
/*     */ import com.elimei.elimei.utils.MPPointD;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import com.elimei.elimei.utils.Transformer;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAxisRendererHorizontalBarChart
/*     */   extends XAxisRenderer
/*     */ {
/*     */   protected BarChart mChart;
/*     */   protected Path mRenderLimitLinesPathBuffer;
/*     */   
/*     */   public XAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans, BarChart chart) {
/*  29 */     super(viewPortHandler, xAxis, trans);
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
/* 210 */     this.mRenderLimitLinesPathBuffer = new Path();
/*     */     this.mChart = chart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderLimitLines(Canvas c) {
/* 220 */     List<LimitLine> limitLines = this.mXAxis.getLimitLines();
/*     */     
/* 222 */     if (limitLines == null || limitLines.size() <= 0) {
/*     */       return;
/*     */     }
/* 225 */     float[] pts = this.mRenderLimitLinesBuffer;
/* 226 */     pts[0] = 0.0F;
/* 227 */     pts[1] = 0.0F;
/*     */     
/* 229 */     Path limitLinePath = this.mRenderLimitLinesPathBuffer;
/* 230 */     limitLinePath.reset();
/*     */     
/* 232 */     for (int i = 0; i < limitLines.size(); i++) {
/*     */       
/* 234 */       LimitLine l = (LimitLine)limitLines.get(i);
/*     */       
/* 236 */       if (l.isEnabled()) {
/*     */ 
/*     */         
/* 239 */         int clipRestoreCount = c.save();
/* 240 */         this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
/* 241 */         this.mLimitLineClippingRect.inset(0.0F, -l.getLineWidth());
/* 242 */         c.clipRect(this.mLimitLineClippingRect);
/*     */         
/* 244 */         this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
/* 245 */         this.mLimitLinePaint.setColor(l.getLineColor());
/* 246 */         this.mLimitLinePaint.setStrokeWidth(l.getLineWidth());
/* 247 */         this.mLimitLinePaint.setPathEffect(l.getDashPathEffect());
/*     */         
/* 249 */         pts[1] = l.getLimit();
/*     */         
/* 251 */         this.mTrans.pointValuesToPixel(pts);
/*     */         
/* 253 */         limitLinePath.moveTo(this.mViewPortHandler.contentLeft(), pts[1]);
/* 254 */         limitLinePath.lineTo(this.mViewPortHandler.contentRight(), pts[1]);
/*     */         
/* 256 */         c.drawPath(limitLinePath, this.mLimitLinePaint);
/* 257 */         limitLinePath.reset();
/*     */ 
/*     */         
/* 260 */         String label = l.getLabel();
/*     */ 
/*     */         
/* 263 */         if (label != null && !label.equals("")) {
/*     */           
/* 265 */           this.mLimitLinePaint.setStyle(l.getTextStyle());
/* 266 */           this.mLimitLinePaint.setPathEffect(null);
/* 267 */           this.mLimitLinePaint.setColor(l.getTextColor());
/* 268 */           this.mLimitLinePaint.setStrokeWidth(0.5F);
/* 269 */           this.mLimitLinePaint.setTextSize(l.getTextSize());
/*     */           
/* 271 */           float labelLineHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
/* 272 */           float xOffset = Utils.convertDpToPixel(4.0F) + l.getXOffset();
/* 273 */           float yOffset = l.getLineWidth() + labelLineHeight + l.getYOffset();
/*     */           
/* 275 */           LimitLine.LimitLabelPosition position = l.getLabelPosition();
/*     */           
/* 277 */           if (position == LimitLine.LimitLabelPosition.RIGHT_TOP) {
/*     */             
/* 279 */             this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
/* 280 */             c.drawText(label, this.mViewPortHandler
/* 281 */                 .contentRight() - xOffset, pts[1] - yOffset + labelLineHeight, this.mLimitLinePaint);
/*     */           
/*     */           }
/* 284 */           else if (position == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
/*     */             
/* 286 */             this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
/* 287 */             c.drawText(label, this.mViewPortHandler
/* 288 */                 .contentRight() - xOffset, pts[1] + yOffset, this.mLimitLinePaint);
/*     */           
/*     */           }
/* 291 */           else if (position == LimitLine.LimitLabelPosition.LEFT_TOP) {
/*     */             
/* 293 */             this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
/* 294 */             c.drawText(label, this.mViewPortHandler
/* 295 */                 .contentLeft() + xOffset, pts[1] - yOffset + labelLineHeight, this.mLimitLinePaint);
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 300 */             this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
/* 301 */             c.drawText(label, this.mViewPortHandler
/* 302 */                 .offsetLeft() + xOffset, pts[1] + yOffset, this.mLimitLinePaint);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 307 */         c.restoreToCount(clipRestoreCount);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void computeAxis(float min, float max, boolean inverted) {
/*     */     if (this.mViewPortHandler.contentWidth() > 10.0F && !this.mViewPortHandler.isFullyZoomedOutY()) {
/*     */       MPPointD p1 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom());
/*     */       MPPointD p2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
/*     */       if (inverted) {
/*     */         min = (float)p2.y;
/*     */         max = (float)p1.y;
/*     */       } else {
/*     */         min = (float)p1.y;
/*     */         max = (float)p2.y;
/*     */       } 
/*     */       MPPointD.recycleInstance(p1);
/*     */       MPPointD.recycleInstance(p2);
/*     */     } 
/*     */     computeAxisValues(min, max);
/*     */   }
/*     */   
/*     */   protected void computeSize() {
/*     */     this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
/*     */     this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
/*     */     String longest = this.mXAxis.getLongestLabel();
/*     */     FSize labelSize = Utils.calcTextSize(this.mAxisLabelPaint, longest);
/*     */     float labelWidth = (int)(labelSize.width + this.mXAxis.getXOffset() * 3.5F);
/*     */     float labelHeight = labelSize.height;
/*     */     FSize labelRotatedSize = Utils.getSizeOfRotatedRectangleByDegrees(labelSize.width, labelHeight, this.mXAxis.getLabelRotationAngle());
/*     */     this.mXAxis.mLabelWidth = Math.round(labelWidth);
/*     */     this.mXAxis.mLabelHeight = Math.round(labelHeight);
/*     */     this.mXAxis.mLabelRotatedWidth = (int)(labelRotatedSize.width + this.mXAxis.getXOffset() * 3.5F);
/*     */     this.mXAxis.mLabelRotatedHeight = Math.round(labelRotatedSize.height);
/*     */     FSize.recycleInstance(labelRotatedSize);
/*     */   }
/*     */   
/*     */   public void renderAxisLabels(Canvas c) {
/*     */     if (!this.mXAxis.isEnabled() || !this.mXAxis.isDrawLabelsEnabled())
/*     */       return; 
/*     */     float xoffset = this.mXAxis.getXOffset();
/*     */     this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
/*     */     this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
/*     */     this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
/*     */     MPPointF pointF = MPPointF.getInstance(0.0F, 0.0F);
/*     */     if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP) {
/*     */       pointF.x = 0.0F;
/*     */       pointF.y = 0.5F;
/*     */       drawLabels(c, this.mViewPortHandler.contentRight() + xoffset, pointF);
/*     */     } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE) {
/*     */       pointF.x = 1.0F;
/*     */       pointF.y = 0.5F;
/*     */       drawLabels(c, this.mViewPortHandler.contentRight() - xoffset, pointF);
/*     */     } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
/*     */       pointF.x = 1.0F;
/*     */       pointF.y = 0.5F;
/*     */       drawLabels(c, this.mViewPortHandler.contentLeft() - xoffset, pointF);
/*     */     } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE) {
/*     */       pointF.x = 1.0F;
/*     */       pointF.y = 0.5F;
/*     */       drawLabels(c, this.mViewPortHandler.contentLeft() + xoffset, pointF);
/*     */     } else {
/*     */       pointF.x = 0.0F;
/*     */       pointF.y = 0.5F;
/*     */       drawLabels(c, this.mViewPortHandler.contentRight() + xoffset, pointF);
/*     */       pointF.x = 1.0F;
/*     */       pointF.y = 0.5F;
/*     */       drawLabels(c, this.mViewPortHandler.contentLeft() - xoffset, pointF);
/*     */     } 
/*     */     MPPointF.recycleInstance(pointF);
/*     */   }
/*     */   
/*     */   protected void drawLabels(Canvas c, float pos, MPPointF anchor) {
/*     */     float labelRotationAngleDegrees = this.mXAxis.getLabelRotationAngle();
/*     */     boolean centeringEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
/*     */     float[] positions = new float[this.mXAxis.mEntryCount * 2];
/*     */     for (int i = 0; i < positions.length; i += 2) {
/*     */       if (centeringEnabled) {
/*     */         positions[i + 1] = this.mXAxis.mCenteredEntries[i / 2];
/*     */       } else {
/*     */         positions[i + 1] = this.mXAxis.mEntries[i / 2];
/*     */       } 
/*     */     } 
/*     */     this.mTrans.pointValuesToPixel(positions);
/*     */     for (int i = 0; i < positions.length; i += 2) {
/*     */       float y = positions[i + 1];
/*     */       if (this.mViewPortHandler.isInBoundsY(y)) {
/*     */         String label = this.mXAxis.getValueFormatter().getFormattedValue(this.mXAxis.mEntries[i / 2], this.mXAxis);
/*     */         drawLabel(c, label, pos, y, anchor, labelRotationAngleDegrees);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RectF getGridClippingRect() {
/*     */     this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
/*     */     this.mGridClippingRect.inset(0.0F, -this.mAxis.getGridLineWidth());
/*     */     return this.mGridClippingRect;
/*     */   }
/*     */   
/*     */   protected void drawGridLine(Canvas c, float x, float y, Path gridLinePath) {
/*     */     gridLinePath.moveTo(this.mViewPortHandler.contentRight(), y);
/*     */     gridLinePath.lineTo(this.mViewPortHandler.contentLeft(), y);
/*     */     c.drawPath(gridLinePath, this.mGridPaint);
/*     */     gridLinePath.reset();
/*     */   }
/*     */   
/*     */   public void renderAxisLine(Canvas c) {
/*     */     if (!this.mXAxis.isDrawAxisLineEnabled() || !this.mXAxis.isEnabled())
/*     */       return; 
/*     */     this.mAxisLinePaint.setColor(this.mXAxis.getAxisLineColor());
/*     */     this.mAxisLinePaint.setStrokeWidth(this.mXAxis.getAxisLineWidth());
/*     */     if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP || this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE || this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)
/*     */       c.drawLine(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint); 
/*     */     if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM || this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE || this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)
/*     */       c.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\XAxisRendererHorizontalBarChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */