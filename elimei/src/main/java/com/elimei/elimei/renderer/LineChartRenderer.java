/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import com.elimei.elimei.animation.ChartAnimator;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.data.LineData;
/*     */ import com.elimei.elimei.data.LineDataSet;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.dataprovider.LineDataProvider;
/*     */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*     */ import com.elimei.elimei.interfaces.datasets.ILineDataSet;
/*     */ import com.elimei.elimei.utils.MPPointD;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import com.elimei.elimei.utils.Transformer;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LineChartRenderer
/*     */   extends LineRadarRenderer
/*     */ {
/*     */   protected LineDataProvider mChart;
/*     */   protected Paint mCirclePaintInner;
/*     */   protected WeakReference<Bitmap> mDrawBitmap;
/*     */   protected Canvas mBitmapCanvas;
/*  54 */   protected Bitmap.Config mBitmapConfig = Bitmap.Config.ARGB_8888;
/*     */   
/*  56 */   protected Path cubicPath = new Path();
/*  57 */   protected Path cubicFillPath = new Path(); private float[] mLineBuffer; protected Path mGenerateFilledPathBuffer;
/*     */   private HashMap<IDataSet, DataSetImageCache> mImageCaches;
/*     */   private float[] mCirclesBuffer;
/*     */   
/*  61 */   public LineChartRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) { super(animator, viewPortHandler);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     this.mLineBuffer = new float[4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 424 */     this.mGenerateFilledPathBuffer = new Path();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 599 */     this.mImageCaches = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 604 */     this.mCirclesBuffer = new float[2]; this.mChart = chart; this.mCirclePaintInner = new Paint(1); this.mCirclePaintInner.setStyle(Paint.Style.FILL); this.mCirclePaintInner.setColor(-1); } public void initBuffers() {} public void drawData(Canvas c) { int width = (int)this.mViewPortHandler.getChartWidth(); int height = (int)this.mViewPortHandler.getChartHeight(); if (this.mDrawBitmap == null || ((Bitmap)this.mDrawBitmap.get()).getWidth() != width || ((Bitmap)this.mDrawBitmap.get()).getHeight() != height) if (width > 0 && height > 0) { this.mDrawBitmap = new WeakReference(Bitmap.createBitmap(width, height, this.mBitmapConfig)); this.mBitmapCanvas = new Canvas((Bitmap)this.mDrawBitmap.get()); } else { return; }   ((Bitmap)this.mDrawBitmap.get()).eraseColor(0); LineData lineData = this.mChart.getLineData(); for (ILineDataSet set : lineData.getDataSets()) { if (set.isVisible()) drawDataSet(c, set);  }  c.drawBitmap((Bitmap)this.mDrawBitmap.get(), 0.0F, 0.0F, this.mRenderPaint); }
/*     */   protected void drawDataSet(Canvas c, ILineDataSet dataSet) { if (dataSet.getEntryCount() < 1) return;  this.mRenderPaint.setStrokeWidth(dataSet.getLineWidth()); this.mRenderPaint.setPathEffect(dataSet.getDashPathEffect()); switch (dataSet.getMode()) { default: drawLinear(c, dataSet); break;case CUBIC_BEZIER: drawCubicBezier(dataSet); break;case HORIZONTAL_BEZIER: drawHorizontalBezier(dataSet); break; }  this.mRenderPaint.setPathEffect(null); }
/*     */   protected void drawHorizontalBezier(ILineDataSet dataSet) { float phaseY = this.mAnimator.getPhaseY(); Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency()); this.mXBounds.set(this.mChart, dataSet); this.cubicPath.reset(); if (this.mXBounds.range >= 1) { Entry prev = dataSet.getEntryForIndex(this.mXBounds.min); Entry cur = prev; this.cubicPath.moveTo(cur.getX(), cur.getY() * phaseY); for (int j = this.mXBounds.min + 1; j <= this.mXBounds.range + this.mXBounds.min; j++) { prev = cur; cur = dataSet.getEntryForIndex(j); float cpx = prev.getX() + (cur.getX() - prev.getX()) / 2.0F; this.cubicPath.cubicTo(cpx, prev.getY() * phaseY, cpx, cur.getY() * phaseY, cur.getX(), cur.getY() * phaseY); }  }  if (dataSet.isDrawFilledEnabled()) { this.cubicFillPath.reset(); this.cubicFillPath.addPath(this.cubicPath); drawCubicFill(this.mBitmapCanvas, dataSet, this.cubicFillPath, trans, this.mXBounds); }  this.mRenderPaint.setColor(dataSet.getColor()); this.mRenderPaint.setStyle(Paint.Style.STROKE); trans.pathValueToPixel(this.cubicPath); this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint); this.mRenderPaint.setPathEffect(null); }
/*     */   protected void drawCubicBezier(ILineDataSet dataSet) { float phaseX = Math.max(0.0F, Math.min(1.0F, this.mAnimator.getPhaseX())); float phaseY = this.mAnimator.getPhaseY(); Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency()); this.mXBounds.set(this.mChart, dataSet); float intensity = dataSet.getCubicIntensity(); this.cubicPath.reset(); if (this.mXBounds.range >= 1) { float prevDx = 0.0F; float prevDy = 0.0F; float curDx = 0.0F; float curDy = 0.0F; int firstIndex = this.mXBounds.min + 1; int lastIndex = this.mXBounds.min + this.mXBounds.range; Entry prev = dataSet.getEntryForIndex(Math.max(firstIndex - 2, 0)); Entry cur = dataSet.getEntryForIndex(Math.max(firstIndex - 1, 0)); Entry next = cur; int nextIndex = -1; if (cur == null) return;  this.cubicPath.moveTo(cur.getX(), cur.getY() * phaseY); for (int j = this.mXBounds.min + 1; j <= this.mXBounds.range + this.mXBounds.min; j++) { Entry prevPrev = prev; prev = cur; cur = (nextIndex == j) ? next : dataSet.getEntryForIndex(j); nextIndex = (j + 1 < dataSet.getEntryCount()) ? (j + 1) : j; next = dataSet.getEntryForIndex(nextIndex); prevDx = (cur.getX() - prevPrev.getX()) * intensity; prevDy = (cur.getY() - prevPrev.getY()) * intensity; curDx = (next.getX() - prev.getX()) * intensity; curDy = (next.getY() - prev.getY()) * intensity; this.cubicPath.cubicTo(prev.getX() + prevDx, (prev.getY() + prevDy) * phaseY, cur.getX() - curDx, (cur.getY() - curDy) * phaseY, cur.getX(), cur.getY() * phaseY); }  }  if (dataSet.isDrawFilledEnabled()) { this.cubicFillPath.reset(); this.cubicFillPath.addPath(this.cubicPath); drawCubicFill(this.mBitmapCanvas, dataSet, this.cubicFillPath, trans, this.mXBounds); }  this.mRenderPaint.setColor(dataSet.getColor()); this.mRenderPaint.setStyle(Paint.Style.STROKE); trans.pathValueToPixel(this.cubicPath); this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint); this.mRenderPaint.setPathEffect(null); }
/* 608 */   protected void drawCircles(Canvas c) { this.mRenderPaint.setStyle(Paint.Style.FILL);
/*     */     
/* 610 */     float phaseY = this.mAnimator.getPhaseY();
/*     */     
/* 612 */     this.mCirclesBuffer[0] = 0.0F;
/* 613 */     this.mCirclesBuffer[1] = 0.0F;
/*     */     
/* 615 */     List<ILineDataSet> dataSets = this.mChart.getLineData().getDataSets();
/*     */     
/* 617 */     for (int i = 0; i < dataSets.size(); i++) {
/*     */       
/* 619 */       ILineDataSet dataSet = (ILineDataSet)dataSets.get(i);
/*     */       
/* 621 */       if (dataSet.isVisible() && dataSet.isDrawCirclesEnabled() && dataSet
/* 622 */         .getEntryCount() != 0) {
/*     */         DataSetImageCache imageCache;
/*     */         
/* 625 */         this.mCirclePaintInner.setColor(dataSet.getCircleHoleColor());
/*     */         
/* 627 */         Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
/*     */         
/* 629 */         this.mXBounds.set(this.mChart, dataSet);
/*     */         
/* 631 */         float circleRadius = dataSet.getCircleRadius();
/* 632 */         float circleHoleRadius = dataSet.getCircleHoleRadius();
/* 633 */         boolean drawCircleHole = (dataSet.isDrawCircleHoleEnabled() && circleHoleRadius < circleRadius && circleHoleRadius > 0.0F);
/*     */ 
/*     */ 
/*     */         
/* 637 */         boolean drawTransparentCircleHole = (drawCircleHole && dataSet.getCircleHoleColor() == 1122867);
/*     */ 
/*     */ 
/*     */         
/* 641 */         if (this.mImageCaches.containsKey(dataSet)) {
/* 642 */           imageCache = (DataSetImageCache)this.mImageCaches.get(dataSet);
/*     */         } else {
/* 644 */           imageCache = new DataSetImageCache();
/* 645 */           this.mImageCaches.put(dataSet, imageCache);
/*     */         } 
/*     */         
/* 648 */         boolean changeRequired = imageCache.init(dataSet);
/*     */ 
/*     */         
/* 651 */         if (changeRequired) {
/* 652 */           imageCache.fill(dataSet, drawCircleHole, drawTransparentCircleHole);
/*     */         }
/*     */         
/* 655 */         int boundsRangeCount = this.mXBounds.range + this.mXBounds.min;
/*     */         
/* 657 */         for (int j = this.mXBounds.min; j <= boundsRangeCount; j++) {
/*     */           
/* 659 */           Entry e = dataSet.getEntryForIndex(j);
/*     */           
/* 661 */           if (e == null)
/*     */             break; 
/* 663 */           this.mCirclesBuffer[0] = e.getX();
/* 664 */           this.mCirclesBuffer[1] = e.getY() * phaseY;
/*     */           
/* 666 */           trans.pointValuesToPixel(this.mCirclesBuffer);
/*     */           
/* 668 */           if (!this.mViewPortHandler.isInBoundsRight(this.mCirclesBuffer[0])) {
/*     */             break;
/*     */           }
/* 671 */           if (this.mViewPortHandler.isInBoundsLeft(this.mCirclesBuffer[0]) && this.mViewPortHandler
/* 672 */             .isInBoundsY(this.mCirclesBuffer[1])) {
/*     */ 
/*     */             
/* 675 */             Bitmap circleBitmap = imageCache.getBitmap(j);
/*     */             
/* 677 */             if (circleBitmap != null)
/* 678 */               c.drawBitmap(circleBitmap, this.mCirclesBuffer[0] - circleRadius, this.mCirclesBuffer[1] - circleRadius, null); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }  }
/*     */   protected void drawCubicFill(Canvas c, ILineDataSet dataSet, Path spline, Transformer trans, BarLineScatterCandleBubbleRenderer.XBounds bounds) { float fillMin = dataSet.getFillFormatter().getFillLinePosition(dataSet, this.mChart); spline.lineTo(dataSet.getEntryForIndex(bounds.min + bounds.range).getX(), fillMin); spline.lineTo(dataSet.getEntryForIndex(bounds.min).getX(), fillMin); spline.close(); trans.pathValueToPixel(spline); Drawable drawable = dataSet.getFillDrawable(); if (drawable != null) {
/*     */       drawFilledPath(c, spline, drawable);
/*     */     } else {
/*     */       drawFilledPath(c, spline, dataSet.getFillColor(), dataSet.getFillAlpha());
/* 687 */     }  } public void drawHighlighted(Canvas c, Highlight[] indices) { LineData lineData = this.mChart.getLineData();
/*     */     
/* 689 */     for (Highlight high : indices)
/*     */     
/* 691 */     { ILineDataSet set = (ILineDataSet)lineData.getDataSetByIndex(high.getDataSetIndex());
/*     */       
/* 693 */       if (set != null && set.isHighlightEnabled())
/*     */       
/*     */       { 
/* 696 */         Entry e = set.getEntryForXValue(high.getX(), high.getY());
/*     */         
/* 698 */         if (isInBoundsX(e, set))
/*     */         
/*     */         { 
/* 701 */           MPPointD pix = this.mChart.getTransformer(set.getAxisDependency()).getPixelForValues(e.getX(), e.getY() * this.mAnimator
/* 702 */               .getPhaseY());
/*     */           
/* 704 */           high.setDraw((float)pix.x, (float)pix.y);
/*     */ 
/*     */           
/* 707 */           drawHighlightLines(c, (float)pix.x, (float)pix.y, set); }  }  }  }
/*     */   protected void drawLinear(Canvas c, ILineDataSet dataSet) { int entryCount = dataSet.getEntryCount(); boolean isDrawSteppedEnabled = dataSet.isDrawSteppedEnabled(); int pointsPerEntryPair = isDrawSteppedEnabled ? 4 : 2; Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency()); float phaseY = this.mAnimator.getPhaseY(); this.mRenderPaint.setStyle(Paint.Style.STROKE); Canvas canvas = null; if (dataSet.isDashedLineEnabled()) { canvas = this.mBitmapCanvas; } else { canvas = c; }  this.mXBounds.set(this.mChart, dataSet); if (dataSet.isDrawFilledEnabled() && entryCount > 0) drawLinearFill(c, dataSet, trans, this.mXBounds);  if (dataSet.getColors().size() > 1) { if (this.mLineBuffer.length <= pointsPerEntryPair * 2) this.mLineBuffer = new float[pointsPerEntryPair * 4];  for (int j = this.mXBounds.min; j <= this.mXBounds.range + this.mXBounds.min; j++) { Entry e = dataSet.getEntryForIndex(j); if (e != null) { this.mLineBuffer[0] = e.getX(); this.mLineBuffer[1] = e.getY() * phaseY; if (j < this.mXBounds.max) { e = dataSet.getEntryForIndex(j + 1); if (e == null)
/*     */               break;  if (isDrawSteppedEnabled) { this.mLineBuffer[2] = e.getX(); this.mLineBuffer[3] = this.mLineBuffer[1]; this.mLineBuffer[4] = this.mLineBuffer[2]; this.mLineBuffer[5] = this.mLineBuffer[3]; this.mLineBuffer[6] = e.getX(); this.mLineBuffer[7] = e.getY() * phaseY; } else { this.mLineBuffer[2] = e.getX(); this.mLineBuffer[3] = e.getY() * phaseY; }  } else { this.mLineBuffer[2] = this.mLineBuffer[0]; this.mLineBuffer[3] = this.mLineBuffer[1]; }  trans.pointValuesToPixel(this.mLineBuffer); if (!this.mViewPortHandler.isInBoundsRight(this.mLineBuffer[0]))
/*     */             break;  if (this.mViewPortHandler.isInBoundsLeft(this.mLineBuffer[2]) && (this.mViewPortHandler.isInBoundsTop(this.mLineBuffer[1]) || this.mViewPortHandler.isInBoundsBottom(this.mLineBuffer[3]))) { this.mRenderPaint.setColor(dataSet.getColor(j)); canvas.drawLines(this.mLineBuffer, 0, pointsPerEntryPair * 2, this.mRenderPaint); }  }  }  } else { if (this.mLineBuffer.length < Math.max(entryCount * pointsPerEntryPair, pointsPerEntryPair) * 2)
/*     */         this.mLineBuffer = new float[Math.max(entryCount * pointsPerEntryPair, pointsPerEntryPair) * 4];  Entry e1 = dataSet.getEntryForIndex(this.mXBounds.min); if (e1 != null) { int j = 0; for (int x = this.mXBounds.min; x <= this.mXBounds.range + this.mXBounds.min; x++) { e1 = dataSet.getEntryForIndex((x == 0) ? 0 : (x - 1)); Entry e2 = dataSet.getEntryForIndex(x); if (e1 != null && e2 != null) { this.mLineBuffer[j++] = e1.getX(); this.mLineBuffer[j++] = e1.getY() * phaseY; if (isDrawSteppedEnabled) { this.mLineBuffer[j++] = e2.getX(); this.mLineBuffer[j++] = e1.getY() * phaseY; this.mLineBuffer[j++] = e2.getX(); this.mLineBuffer[j++] = e1.getY() * phaseY; }  this.mLineBuffer[j++] = e2.getX(); this.mLineBuffer[j++] = e2.getY() * phaseY; }  }  if (j > 0) { trans.pointValuesToPixel(this.mLineBuffer); int size = Math.max((this.mXBounds.range + 1) * pointsPerEntryPair, pointsPerEntryPair) * 2; this.mRenderPaint.setColor(dataSet.getColor()); canvas.drawLines(this.mLineBuffer, 0, size, this.mRenderPaint); }  }  }  this.mRenderPaint.setPathEffect(null); }
/*     */   protected void drawLinearFill(Canvas c, ILineDataSet dataSet, Transformer trans, BarLineScatterCandleBubbleRenderer.XBounds bounds) { Path filled = this.mGenerateFilledPathBuffer; int startingIndex = bounds.min; int endingIndex = bounds.range + bounds.min; int indexInterval = 128; int currentStartIndex = 0; int currentEndIndex = 128; int iterations = 0; do { currentStartIndex = startingIndex + iterations * 128; currentEndIndex = currentStartIndex + 128; currentEndIndex = (currentEndIndex > endingIndex) ? endingIndex : currentEndIndex; if (currentStartIndex <= currentEndIndex) { generateFilledPath(dataSet, currentStartIndex, currentEndIndex, filled); trans.pathValueToPixel(filled); Drawable drawable = dataSet.getFillDrawable(); if (drawable != null) { drawFilledPath(c, filled, drawable); } else { drawFilledPath(c, filled, dataSet.getFillColor(), dataSet.getFillAlpha()); }  }  iterations++; } while (currentStartIndex <= currentEndIndex); }
/*     */   private void generateFilledPath(ILineDataSet dataSet, int startIndex, int endIndex, Path outputPath) { float fillMin = dataSet.getFillFormatter().getFillLinePosition(dataSet, this.mChart); float phaseY = this.mAnimator.getPhaseY(); boolean isDrawSteppedEnabled = (dataSet.getMode() == LineDataSet.Mode.STEPPED); Path filled = outputPath; filled.reset(); Entry entry = dataSet.getEntryForIndex(startIndex); filled.moveTo(entry.getX(), fillMin); filled.lineTo(entry.getX(), entry.getY() * phaseY); Entry currentEntry = null; Entry previousEntry = null; for (int x = startIndex + 1; x <= endIndex; x++) { currentEntry = dataSet.getEntryForIndex(x); if (isDrawSteppedEnabled && previousEntry != null)
/*     */         filled.lineTo(currentEntry.getX(), previousEntry.getY() * phaseY);  filled.lineTo(currentEntry.getX(), currentEntry.getY() * phaseY); previousEntry = currentEntry; }  if (currentEntry != null)
/*     */       filled.lineTo(currentEntry.getX(), fillMin);  filled.close(); }
/*     */   public void drawValues(Canvas c) { if (isDrawingValuesAllowed(this.mChart)) { List<ILineDataSet> dataSets = this.mChart.getLineData().getDataSets(); for (int i = 0; i < dataSets.size(); i++) { ILineDataSet dataSet = (ILineDataSet)dataSets.get(i); if (shouldDrawValues(dataSet)) { applyValueTextStyle(dataSet); Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency()); int valOffset = (int)(dataSet.getCircleRadius() * 1.75F); if (!dataSet.isDrawCirclesEnabled())
/*     */             valOffset /= 2;  this.mXBounds.set(this.mChart, dataSet); float[] positions = trans.generateTransformedValuesLine(dataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max); MPPointF iconsOffset = MPPointF.getInstance(dataSet.getIconsOffset()); iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x); iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y); for (int j = 0; j < positions.length; j += 2) { float x = positions[j]; float y = positions[j + 1]; if (!this.mViewPortHandler.isInBoundsRight(x))
/*     */               break;  if (this.mViewPortHandler.isInBoundsLeft(x) && this.mViewPortHandler.isInBoundsY(y)) { Entry entry = dataSet.getEntryForIndex(j / 2 + this.mXBounds.min); if (dataSet.isDrawValuesEnabled())
/* 719 */                 drawValue(c, dataSet.getValueFormatter(), entry.getY(), entry, i, x, y - valOffset, dataSet.getValueTextColor(j / 2));  if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) { Drawable icon = entry.getIcon(); Utils.drawImage(c, icon, (int)(x + iconsOffset.x), (int)(y + iconsOffset.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight()); }  }  }  MPPointF.recycleInstance(iconsOffset); }  }  }  } public void drawExtras(Canvas c) { drawCircles(c); } public void setBitmapConfig(Bitmap.Config config) { this.mBitmapConfig = config;
/* 720 */     releaseBitmap(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 729 */   public Bitmap.Config getBitmapConfig() { return this.mBitmapConfig; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void releaseBitmap() {
/* 736 */     if (this.mBitmapCanvas != null) {
/* 737 */       this.mBitmapCanvas.setBitmap(null);
/* 738 */       this.mBitmapCanvas = null;
/*     */     } 
/* 740 */     if (this.mDrawBitmap != null) {
/* 741 */       ((Bitmap)this.mDrawBitmap.get()).recycle();
/* 742 */       this.mDrawBitmap.clear();
/* 743 */       this.mDrawBitmap = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private class DataSetImageCache
/*     */   {
/* 749 */     private Path mCirclePathBuffer = new Path();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Bitmap[] circleBitmaps;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean init(ILineDataSet set) {
/* 761 */       int size = set.getCircleColorCount();
/* 762 */       boolean changeRequired = false;
/*     */       
/* 764 */       if (this.circleBitmaps == null) {
/* 765 */         this.circleBitmaps = new Bitmap[size];
/* 766 */         changeRequired = true;
/* 767 */       } else if (this.circleBitmaps.length != size) {
/* 768 */         this.circleBitmaps = new Bitmap[size];
/* 769 */         changeRequired = true;
/*     */       } 
/*     */       
/* 772 */       return changeRequired;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void fill(ILineDataSet set, boolean drawCircleHole, boolean drawTransparentCircleHole) {
/* 784 */       int colorCount = set.getCircleColorCount();
/* 785 */       float circleRadius = set.getCircleRadius();
/* 786 */       float circleHoleRadius = set.getCircleHoleRadius();
/*     */       
/* 788 */       for (int i = 0; i < colorCount; i++) {
/*     */         
/* 790 */         Bitmap.Config conf = Bitmap.Config.ARGB_4444;
/* 791 */         Bitmap circleBitmap = Bitmap.createBitmap((int)(circleRadius * 2.1D), (int)(circleRadius * 2.1D), conf);
/*     */         
/* 793 */         Canvas canvas = new Canvas(circleBitmap);
/* 794 */         this.circleBitmaps[i] = circleBitmap;
/* 795 */         LineChartRenderer.this.mRenderPaint.setColor(set.getCircleColor(i));
/*     */         
/* 797 */         if (drawTransparentCircleHole) {
/*     */           
/* 799 */           this.mCirclePathBuffer.reset();
/*     */           
/* 801 */           this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleRadius, Path.Direction.CW);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 808 */           this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleHoleRadius, Path.Direction.CCW);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 815 */           canvas.drawPath(this.mCirclePathBuffer, LineChartRenderer.this.mRenderPaint);
/*     */         } else {
/*     */           
/* 818 */           canvas.drawCircle(circleRadius, circleRadius, circleRadius, LineChartRenderer.this.mRenderPaint);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 824 */           if (drawCircleHole) {
/* 825 */             canvas.drawCircle(circleRadius, circleRadius, circleHoleRadius, LineChartRenderer.this.mCirclePaintInner);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 842 */     protected Bitmap getBitmap(int index) { return this.circleBitmaps[index % this.circleBitmaps.length]; }
/*     */     
/*     */     private DataSetImageCache() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\LineChartRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */