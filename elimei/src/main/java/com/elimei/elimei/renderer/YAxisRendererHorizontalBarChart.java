/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.RectF;
/*     */ import com.elimei.elimei.components.LimitLine;
/*     */ import com.elimei.elimei.components.YAxis;
/*     */ import com.elimei.elimei.utils.MPPointD;
/*     */ import com.elimei.elimei.utils.Transformer;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class YAxisRendererHorizontalBarChart
/*     */   extends YAxisRenderer
/*     */ {
/*     */   protected Path mDrawZeroLinePathBuffer;
/*     */   protected Path mRenderLimitLinesPathBuffer;
/*     */   protected float[] mRenderLimitLinesBuffer;
/*     */   
/*     */   public YAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer trans) {
/*  25 */     super(viewPortHandler, yAxis, trans);
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
/* 186 */     this.mDrawZeroLinePathBuffer = new Path();
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
/* 214 */     this.mRenderLimitLinesPathBuffer = new Path();
/* 215 */     this.mRenderLimitLinesBuffer = new float[4];
/*     */     this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderLimitLines(Canvas c) {
/* 225 */     List<LimitLine> limitLines = this.mYAxis.getLimitLines();
/*     */     
/* 227 */     if (limitLines == null || limitLines.size() <= 0) {
/*     */       return;
/*     */     }
/* 230 */     float[] pts = this.mRenderLimitLinesBuffer;
/* 231 */     pts[0] = 0.0F;
/* 232 */     pts[1] = 0.0F;
/* 233 */     pts[2] = 0.0F;
/* 234 */     pts[3] = 0.0F;
/* 235 */     Path limitLinePath = this.mRenderLimitLinesPathBuffer;
/* 236 */     limitLinePath.reset();
/*     */     
/* 238 */     for (int i = 0; i < limitLines.size(); i++) {
/*     */       
/* 240 */       LimitLine l = (LimitLine)limitLines.get(i);
/*     */       
/* 242 */       if (l.isEnabled()) {
/*     */ 
/*     */         
/* 245 */         int clipRestoreCount = c.save();
/* 246 */         this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
/* 247 */         this.mLimitLineClippingRect.inset(-l.getLineWidth(), 0.0F);
/* 248 */         c.clipRect(this.mLimitLineClippingRect);
/*     */         
/* 250 */         pts[0] = l.getLimit();
/* 251 */         pts[2] = l.getLimit();
/*     */         
/* 253 */         this.mTrans.pointValuesToPixel(pts);
/*     */         
/* 255 */         pts[1] = this.mViewPortHandler.contentTop();
/* 256 */         pts[3] = this.mViewPortHandler.contentBottom();
/*     */         
/* 258 */         limitLinePath.moveTo(pts[0], pts[1]);
/* 259 */         limitLinePath.lineTo(pts[2], pts[3]);
/*     */         
/* 261 */         this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
/* 262 */         this.mLimitLinePaint.setColor(l.getLineColor());
/* 263 */         this.mLimitLinePaint.setPathEffect(l.getDashPathEffect());
/* 264 */         this.mLimitLinePaint.setStrokeWidth(l.getLineWidth());
/*     */         
/* 266 */         c.drawPath(limitLinePath, this.mLimitLinePaint);
/* 267 */         limitLinePath.reset();
/*     */         
/* 269 */         String label = l.getLabel();
/*     */ 
/*     */         
/* 272 */         if (label != null && !label.equals("")) {
/*     */           
/* 274 */           this.mLimitLinePaint.setStyle(l.getTextStyle());
/* 275 */           this.mLimitLinePaint.setPathEffect(null);
/* 276 */           this.mLimitLinePaint.setColor(l.getTextColor());
/* 277 */           this.mLimitLinePaint.setTypeface(l.getTypeface());
/* 278 */           this.mLimitLinePaint.setStrokeWidth(0.5F);
/* 279 */           this.mLimitLinePaint.setTextSize(l.getTextSize());
/*     */           
/* 281 */           float xOffset = l.getLineWidth() + l.getXOffset();
/* 282 */           float yOffset = Utils.convertDpToPixel(2.0F) + l.getYOffset();
/*     */           
/* 284 */           LimitLine.LimitLabelPosition position = l.getLabelPosition();
/*     */           
/* 286 */           if (position == LimitLine.LimitLabelPosition.RIGHT_TOP) {
/*     */             
/* 288 */             float labelLineHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
/* 289 */             this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
/* 290 */             c.drawText(label, pts[0] + xOffset, this.mViewPortHandler.contentTop() + yOffset + labelLineHeight, this.mLimitLinePaint);
/* 291 */           } else if (position == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
/*     */             
/* 293 */             this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
/* 294 */             c.drawText(label, pts[0] + xOffset, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
/* 295 */           } else if (position == LimitLine.LimitLabelPosition.LEFT_TOP) {
/*     */             
/* 297 */             this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
/* 298 */             float labelLineHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
/* 299 */             c.drawText(label, pts[0] - xOffset, this.mViewPortHandler.contentTop() + yOffset + labelLineHeight, this.mLimitLinePaint);
/*     */           } else {
/*     */             
/* 302 */             this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
/* 303 */             c.drawText(label, pts[0] - xOffset, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
/*     */           } 
/*     */         } 
/*     */         
/* 307 */         c.restoreToCount(clipRestoreCount);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void computeAxis(float yMin, float yMax, boolean inverted) {
/*     */     if (this.mViewPortHandler.contentHeight() > 10.0F && !this.mViewPortHandler.isFullyZoomedOutX()) {
/*     */       MPPointD p1 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
/*     */       MPPointD p2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop());
/*     */       if (!inverted) {
/*     */         yMin = (float)p1.x;
/*     */         yMax = (float)p2.x;
/*     */       } else {
/*     */         yMin = (float)p2.x;
/*     */         yMax = (float)p1.x;
/*     */       } 
/*     */       MPPointD.recycleInstance(p1);
/*     */       MPPointD.recycleInstance(p2);
/*     */     } 
/*     */     computeAxisValues(yMin, yMax);
/*     */   }
/*     */   
/*     */   public void renderAxisLabels(Canvas c) {
/*     */     if (!this.mYAxis.isEnabled() || !this.mYAxis.isDrawLabelsEnabled())
/*     */       return; 
/*     */     float[] positions = getTransformedPositions();
/*     */     this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
/*     */     this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
/*     */     this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
/*     */     this.mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
/*     */     float baseYOffset = Utils.convertDpToPixel(2.5F);
/*     */     float textHeight = Utils.calcTextHeight(this.mAxisLabelPaint, "Q");
/*     */     YAxis.AxisDependency dependency = this.mYAxis.getAxisDependency();
/*     */     YAxis.YAxisLabelPosition labelPosition = this.mYAxis.getLabelPosition();
/*     */     float yPos = 0.0F;
/*     */     if (dependency == YAxis.AxisDependency.LEFT) {
/*     */       if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
/*     */         yPos = this.mViewPortHandler.contentTop() - baseYOffset;
/*     */       } else {
/*     */         yPos = this.mViewPortHandler.contentTop() - baseYOffset;
/*     */       } 
/*     */     } else if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
/*     */       yPos = this.mViewPortHandler.contentBottom() + textHeight + baseYOffset;
/*     */     } else {
/*     */       yPos = this.mViewPortHandler.contentBottom() + textHeight + baseYOffset;
/*     */     } 
/*     */     drawYLabels(c, yPos, positions, this.mYAxis.getYOffset());
/*     */   }
/*     */   
/*     */   public void renderAxisLine(Canvas c) {
/*     */     if (!this.mYAxis.isEnabled() || !this.mYAxis.isDrawAxisLineEnabled())
/*     */       return; 
/*     */     this.mAxisLinePaint.setColor(this.mYAxis.getAxisLineColor());
/*     */     this.mAxisLinePaint.setStrokeWidth(this.mYAxis.getAxisLineWidth());
/*     */     if (this.mYAxis.getAxisDependency() == YAxis.AxisDependency.LEFT) {
/*     */       c.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mAxisLinePaint);
/*     */     } else {
/*     */       c.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawYLabels(Canvas c, float fixedPosition, float[] positions, float offset) {
/*     */     this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
/*     */     this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
/*     */     this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
/*     */     int from = this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 0 : 1;
/*     */     int to = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : (this.mYAxis.mEntryCount - 1);
/*     */     for (int i = from; i < to; i++) {
/*     */       String text = this.mYAxis.getFormattedLabel(i);
/*     */       c.drawText(text, positions[i * 2], fixedPosition - offset, this.mAxisLabelPaint);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected float[] getTransformedPositions() {
/*     */     if (this.mGetTransformedPositionsBuffer.length != this.mYAxis.mEntryCount * 2)
/*     */       this.mGetTransformedPositionsBuffer = new float[this.mYAxis.mEntryCount * 2]; 
/*     */     float[] positions = this.mGetTransformedPositionsBuffer;
/*     */     for (int i = 0; i < positions.length; i += 2)
/*     */       positions[i] = this.mYAxis.mEntries[i / 2]; 
/*     */     this.mTrans.pointValuesToPixel(positions);
/*     */     return positions;
/*     */   }
/*     */   
/*     */   public RectF getGridClippingRect() {
/*     */     this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
/*     */     this.mGridClippingRect.inset(-this.mAxis.getGridLineWidth(), 0.0F);
/*     */     return this.mGridClippingRect;
/*     */   }
/*     */   
/*     */   protected Path linePath(Path p, int i, float[] positions) {
/*     */     p.moveTo(positions[i], this.mViewPortHandler.contentTop());
/*     */     p.lineTo(positions[i], this.mViewPortHandler.contentBottom());
/*     */     return p;
/*     */   }
/*     */   
/*     */   protected void drawZeroLine(Canvas c) {
/*     */     int clipRestoreCount = c.save();
/*     */     this.mZeroLineClippingRect.set(this.mViewPortHandler.getContentRect());
/*     */     this.mZeroLineClippingRect.inset(-this.mYAxis.getZeroLineWidth(), 0.0F);
/*     */     c.clipRect(this.mLimitLineClippingRect);
/*     */     MPPointD pos = this.mTrans.getPixelForValues(0.0F, 0.0F);
/*     */     this.mZeroLinePaint.setColor(this.mYAxis.getZeroLineColor());
/*     */     this.mZeroLinePaint.setStrokeWidth(this.mYAxis.getZeroLineWidth());
/*     */     Path zeroLinePath = this.mDrawZeroLinePathBuffer;
/*     */     zeroLinePath.reset();
/*     */     zeroLinePath.moveTo((float)pos.x - 1.0F, this.mViewPortHandler.contentTop());
/*     */     zeroLinePath.lineTo((float)pos.x - 1.0F, this.mViewPortHandler.contentBottom());
/*     */     c.drawPath(zeroLinePath, this.mZeroLinePaint);
/*     */     c.restoreToCount(clipRestoreCount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\YAxisRendererHorizontalBarChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */