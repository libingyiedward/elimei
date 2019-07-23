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
/*     */ public class YAxisRenderer extends AxisRenderer {
/*     */   protected YAxis mYAxis;
/*     */   protected Paint mZeroLinePaint;
/*     */   protected Path mRenderGridLinesPath;
/*     */   protected RectF mGridClippingRect;
/*     */   protected float[] mGetTransformedPositionsBuffer;
/*     */   protected Path mDrawZeroLinePath;
/*     */   protected RectF mZeroLineClippingRect;
/*     */   protected Path mRenderLimitLines;
/*     */   protected float[] mRenderLimitLinesBuffer;
/*     */   protected RectF mLimitLineClippingRect;
/*     */   
/*  27 */   public YAxisRenderer(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer trans) { super(viewPortHandler, trans, yAxis);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     this.mRenderGridLinesPath = new Path();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     this.mGridClippingRect = new RectF();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     this.mGetTransformedPositionsBuffer = new float[2];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     this.mDrawZeroLinePath = new Path();
/* 215 */     this.mZeroLineClippingRect = new RectF();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     this.mRenderLimitLines = new Path();
/* 246 */     this.mRenderLimitLinesBuffer = new float[2];
/* 247 */     this.mLimitLineClippingRect = new RectF();
/*     */     this.mYAxis = yAxis;
/*     */     if (this.mViewPortHandler != null) {
/*     */       this.mAxisLabelPaint.setColor(-16777216);
/*     */       this.mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10.0F));
/*     */       this.mZeroLinePaint = new Paint();
/*     */       this.mZeroLinePaint.setColor(-7829368);
/*     */       this.mZeroLinePaint.setStrokeWidth(1.0F);
/*     */       this.mZeroLinePaint.setStyle(Paint.Style.STROKE);
/* 256 */     }  } public void renderLimitLines(Canvas c) { List<LimitLine> limitLines = this.mYAxis.getLimitLines();
/*     */     
/* 258 */     if (limitLines == null || limitLines.size() <= 0) {
/*     */       return;
/*     */     }
/* 261 */     float[] pts = this.mRenderLimitLinesBuffer;
/* 262 */     pts[0] = 0.0F;
/* 263 */     pts[1] = 0.0F;
/* 264 */     Path limitLinePath = this.mRenderLimitLines;
/* 265 */     limitLinePath.reset();
/*     */     
/* 267 */     for (int i = 0; i < limitLines.size(); i++) {
/*     */       
/* 269 */       LimitLine l = (LimitLine)limitLines.get(i);
/*     */       
/* 271 */       if (l.isEnabled()) {
/*     */ 
/*     */         
/* 274 */         int clipRestoreCount = c.save();
/* 275 */         this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
/* 276 */         this.mLimitLineClippingRect.inset(0.0F, -l.getLineWidth());
/* 277 */         c.clipRect(this.mLimitLineClippingRect);
/*     */         
/* 279 */         this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
/* 280 */         this.mLimitLinePaint.setColor(l.getLineColor());
/* 281 */         this.mLimitLinePaint.setStrokeWidth(l.getLineWidth());
/* 282 */         this.mLimitLinePaint.setPathEffect(l.getDashPathEffect());
/*     */         
/* 284 */         pts[1] = l.getLimit();
/*     */         
/* 286 */         this.mTrans.pointValuesToPixel(pts);
/*     */         
/* 288 */         limitLinePath.moveTo(this.mViewPortHandler.contentLeft(), pts[1]);
/* 289 */         limitLinePath.lineTo(this.mViewPortHandler.contentRight(), pts[1]);
/*     */         
/* 291 */         c.drawPath(limitLinePath, this.mLimitLinePaint);
/* 292 */         limitLinePath.reset();
/* 293 */         c.drawLines(pts, this.mLimitLinePaint);
/*     */         
/* 295 */         String label = l.getLabel();
/*     */ 
/*     */         
/* 298 */         if (label != null && !label.equals("")) {
/*     */           
/* 300 */           this.mLimitLinePaint.setStyle(l.getTextStyle());
/* 301 */           this.mLimitLinePaint.setPathEffect(null);
/* 302 */           this.mLimitLinePaint.setColor(l.getTextColor());
/* 303 */           this.mLimitLinePaint.setTypeface(l.getTypeface());
/* 304 */           this.mLimitLinePaint.setStrokeWidth(0.5F);
/* 305 */           this.mLimitLinePaint.setTextSize(l.getTextSize());
/*     */           
/* 307 */           float labelLineHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
/* 308 */           float xOffset = Utils.convertDpToPixel(4.0F) + l.getXOffset();
/* 309 */           float yOffset = l.getLineWidth() + labelLineHeight + l.getYOffset();
/*     */           
/* 311 */           LimitLine.LimitLabelPosition position = l.getLabelPosition();
/*     */           
/* 313 */           if (position == LimitLine.LimitLabelPosition.RIGHT_TOP) {
/*     */             
/* 315 */             this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
/* 316 */             c.drawText(label, this.mViewPortHandler
/* 317 */                 .contentRight() - xOffset, pts[1] - yOffset + labelLineHeight, this.mLimitLinePaint);
/*     */           
/*     */           }
/* 320 */           else if (position == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
/*     */             
/* 322 */             this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
/* 323 */             c.drawText(label, this.mViewPortHandler
/* 324 */                 .contentRight() - xOffset, pts[1] + yOffset, this.mLimitLinePaint);
/*     */           
/*     */           }
/* 327 */           else if (position == LimitLine.LimitLabelPosition.LEFT_TOP) {
/*     */             
/* 329 */             this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
/* 330 */             c.drawText(label, this.mViewPortHandler
/* 331 */                 .contentLeft() + xOffset, pts[1] - yOffset + labelLineHeight, this.mLimitLinePaint);
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 336 */             this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
/* 337 */             c.drawText(label, this.mViewPortHandler
/* 338 */                 .offsetLeft() + xOffset, pts[1] + yOffset, this.mLimitLinePaint);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 343 */         c.restoreToCount(clipRestoreCount);
/*     */       } 
/*     */     }  }
/*     */ 
/*     */   
/*     */   public void renderAxisLabels(Canvas c) {
/*     */     if (!this.mYAxis.isEnabled() || !this.mYAxis.isDrawLabelsEnabled())
/*     */       return; 
/*     */     float[] positions = getTransformedPositions();
/*     */     this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
/*     */     this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
/*     */     this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
/*     */     float xoffset = this.mYAxis.getXOffset();
/*     */     float yoffset = Utils.calcTextHeight(this.mAxisLabelPaint, "A") / 2.5F + this.mYAxis.getYOffset();
/*     */     YAxis.AxisDependency dependency = this.mYAxis.getAxisDependency();
/*     */     YAxis.YAxisLabelPosition labelPosition = this.mYAxis.getLabelPosition();
/*     */     float xPos = 0.0F;
/*     */     if (dependency == YAxis.AxisDependency.LEFT) {
/*     */       if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
/*     */         this.mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
/*     */         xPos = this.mViewPortHandler.offsetLeft() - xoffset;
/*     */       } else {
/*     */         this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
/*     */         xPos = this.mViewPortHandler.offsetLeft() + xoffset;
/*     */       } 
/*     */     } else if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
/*     */       this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
/*     */       xPos = this.mViewPortHandler.contentRight() + xoffset;
/*     */     } else {
/*     */       this.mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
/*     */       xPos = this.mViewPortHandler.contentRight() - xoffset;
/*     */     } 
/*     */     drawYLabels(c, xPos, positions, yoffset);
/*     */   }
/*     */   
/*     */   public void renderAxisLine(Canvas c) {
/*     */     if (!this.mYAxis.isEnabled() || !this.mYAxis.isDrawAxisLineEnabled())
/*     */       return; 
/*     */     this.mAxisLinePaint.setColor(this.mYAxis.getAxisLineColor());
/*     */     this.mAxisLinePaint.setStrokeWidth(this.mYAxis.getAxisLineWidth());
/*     */     if (this.mYAxis.getAxisDependency() == YAxis.AxisDependency.LEFT) {
/*     */       c.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
/*     */     } else {
/*     */       c.drawLine(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawYLabels(Canvas c, float fixedPosition, float[] positions, float offset) {
/*     */     int from = this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 0 : 1;
/*     */     int to = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : (this.mYAxis.mEntryCount - 1);
/*     */     for (int i = from; i < to; i++) {
/*     */       String text = this.mYAxis.getFormattedLabel(i);
/*     */       c.drawText(text, fixedPosition, positions[i * 2 + 1] + offset, this.mAxisLabelPaint);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderGridLines(Canvas c) {
/*     */     if (!this.mYAxis.isEnabled())
/*     */       return; 
/*     */     if (this.mYAxis.isDrawGridLinesEnabled()) {
/*     */       int clipRestoreCount = c.save();
/*     */       c.clipRect(getGridClippingRect());
/*     */       float[] positions = getTransformedPositions();
/*     */       this.mGridPaint.setColor(this.mYAxis.getGridColor());
/*     */       this.mGridPaint.setStrokeWidth(this.mYAxis.getGridLineWidth());
/*     */       this.mGridPaint.setPathEffect(this.mYAxis.getGridDashPathEffect());
/*     */       Path gridLinePath = this.mRenderGridLinesPath;
/*     */       gridLinePath.reset();
/*     */       for (int i = 0; i < positions.length; i += 2) {
/*     */         c.drawPath(linePath(gridLinePath, i, positions), this.mGridPaint);
/*     */         gridLinePath.reset();
/*     */       } 
/*     */       c.restoreToCount(clipRestoreCount);
/*     */     } 
/*     */     if (this.mYAxis.isDrawZeroLineEnabled())
/*     */       drawZeroLine(c); 
/*     */   }
/*     */   
/*     */   public RectF getGridClippingRect() {
/*     */     this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
/*     */     this.mGridClippingRect.inset(0.0F, -this.mAxis.getGridLineWidth());
/*     */     return this.mGridClippingRect;
/*     */   }
/*     */   
/*     */   protected Path linePath(Path p, int i, float[] positions) {
/*     */     p.moveTo(this.mViewPortHandler.offsetLeft(), positions[i + 1]);
/*     */     p.lineTo(this.mViewPortHandler.contentRight(), positions[i + 1]);
/*     */     return p;
/*     */   }
/*     */   
/*     */   protected float[] getTransformedPositions() {
/*     */     if (this.mGetTransformedPositionsBuffer.length != this.mYAxis.mEntryCount * 2)
/*     */       this.mGetTransformedPositionsBuffer = new float[this.mYAxis.mEntryCount * 2]; 
/*     */     float[] positions = this.mGetTransformedPositionsBuffer;
/*     */     for (int i = 0; i < positions.length; i += 2)
/*     */       positions[i + 1] = this.mYAxis.mEntries[i / 2]; 
/*     */     this.mTrans.pointValuesToPixel(positions);
/*     */     return positions;
/*     */   }
/*     */   
/*     */   protected void drawZeroLine(Canvas c) {
/*     */     int clipRestoreCount = c.save();
/*     */     this.mZeroLineClippingRect.set(this.mViewPortHandler.getContentRect());
/*     */     this.mZeroLineClippingRect.inset(0.0F, -this.mYAxis.getZeroLineWidth());
/*     */     c.clipRect(this.mZeroLineClippingRect);
/*     */     MPPointD pos = this.mTrans.getPixelForValues(0.0F, 0.0F);
/*     */     this.mZeroLinePaint.setColor(this.mYAxis.getZeroLineColor());
/*     */     this.mZeroLinePaint.setStrokeWidth(this.mYAxis.getZeroLineWidth());
/*     */     Path zeroLinePath = this.mDrawZeroLinePath;
/*     */     zeroLinePath.reset();
/*     */     zeroLinePath.moveTo(this.mViewPortHandler.contentLeft(), (float)pos.y);
/*     */     zeroLinePath.lineTo(this.mViewPortHandler.contentRight(), (float)pos.y);
/*     */     c.drawPath(zeroLinePath, this.mZeroLinePaint);
/*     */     c.restoreToCount(clipRestoreCount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\YAxisRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */