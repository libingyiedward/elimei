/*     */ package com.elimei.elimei.utils;
/*     */ 
/*     */ import android.graphics.Matrix;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.RectF;
/*     */ import com.elimei.elimei.data.CandleEntry;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.interfaces.datasets.IBubbleDataSet;
/*     */ import com.elimei.elimei.interfaces.datasets.ICandleDataSet;
/*     */ import com.elimei.elimei.interfaces.datasets.ILineDataSet;
/*     */ import com.elimei.elimei.interfaces.datasets.IScatterDataSet;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Transformer {
/*     */   protected Matrix mMatrixValueToPx;
/*     */   protected Matrix mMatrixOffset;
/*     */   protected ViewPortHandler mViewPortHandler;
/*     */   protected float[] valuePointsForGenerateTransformedValuesScatter;
/*     */   protected float[] valuePointsForGenerateTransformedValuesBubble;
/*     */   protected float[] valuePointsForGenerateTransformedValuesLine;
/*     */   protected float[] valuePointsForGenerateTransformedValuesCandle;
/*     */   protected Matrix mPixelToValueMatrixBuffer;
/*     */   float[] ptsBuffer;
/*     */   private Matrix mMBuffer1;
/*     */   private Matrix mMBuffer2;
/*     */   
/*     */   public Transformer(ViewPortHandler viewPortHandler) {
/*  28 */     this.mMatrixValueToPx = new Matrix();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     this.mMatrixOffset = new Matrix();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     this.valuePointsForGenerateTransformedValuesScatter = new float[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     this.valuePointsForGenerateTransformedValuesBubble = new float[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     this.valuePointsForGenerateTransformedValuesLine = new float[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     this.valuePointsForGenerateTransformedValuesCandle = new float[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 357 */     this.mPixelToValueMatrixBuffer = new Matrix();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 384 */     this.ptsBuffer = new float[2];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 444 */     this.mMBuffer1 = new Matrix();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 453 */     this.mMBuffer2 = new Matrix();
/*     */     this.mViewPortHandler = viewPortHandler;
/*     */   } public Matrix getPixelToValueMatrix() {
/* 456 */     getValueToPixelMatrix().invert(this.mMBuffer2);
/* 457 */     return this.mMBuffer2;
/*     */   }
/*     */   
/*     */   public void prepareMatrixValuePx(float xChartMin, float deltaX, float deltaY, float yChartMin) {
/*     */     float scaleX = this.mViewPortHandler.contentWidth() / deltaX;
/*     */     float scaleY = this.mViewPortHandler.contentHeight() / deltaY;
/*     */     if (Float.isInfinite(scaleX))
/*     */       scaleX = 0.0F; 
/*     */     if (Float.isInfinite(scaleY))
/*     */       scaleY = 0.0F; 
/*     */     this.mMatrixValueToPx.reset();
/*     */     this.mMatrixValueToPx.postTranslate(-xChartMin, -yChartMin);
/*     */     this.mMatrixValueToPx.postScale(scaleX, -scaleY);
/*     */   }
/*     */   
/*     */   public void prepareMatrixOffset(boolean inverted) {
/*     */     this.mMatrixOffset.reset();
/*     */     if (!inverted) {
/*     */       this.mMatrixOffset.postTranslate(this.mViewPortHandler.offsetLeft(), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
/*     */     } else {
/*     */       this.mMatrixOffset.setTranslate(this.mViewPortHandler.offsetLeft(), -this.mViewPortHandler.offsetTop());
/*     */       this.mMatrixOffset.postScale(1.0F, -1.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public float[] generateTransformedValuesScatter(IScatterDataSet data, float phaseX, float phaseY, int from, int to) {
/*     */     int count = (int)((to - from) * phaseX + 1.0F) * 2;
/*     */     if (this.valuePointsForGenerateTransformedValuesScatter.length != count)
/*     */       this.valuePointsForGenerateTransformedValuesScatter = new float[count]; 
/*     */     float[] valuePoints = this.valuePointsForGenerateTransformedValuesScatter;
/*     */     for (int j = 0; j < count; j += 2) {
/*     */       Entry e = data.getEntryForIndex(j / 2 + from);
/*     */       if (e != null) {
/*     */         valuePoints[j] = e.getX();
/*     */         valuePoints[j + 1] = e.getY() * phaseY;
/*     */       } else {
/*     */         valuePoints[j] = 0.0F;
/*     */         valuePoints[j + 1] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     getValueToPixelMatrix().mapPoints(valuePoints);
/*     */     return valuePoints;
/*     */   }
/*     */   
/*     */   public float[] generateTransformedValuesBubble(IBubbleDataSet data, float phaseY, int from, int to) {
/*     */     int count = (to - from + 1) * 2;
/*     */     if (this.valuePointsForGenerateTransformedValuesBubble.length != count)
/*     */       this.valuePointsForGenerateTransformedValuesBubble = new float[count]; 
/*     */     float[] valuePoints = this.valuePointsForGenerateTransformedValuesBubble;
/*     */     for (int j = 0; j < count; j += 2) {
/*     */       Entry e = data.getEntryForIndex(j / 2 + from);
/*     */       if (e != null) {
/*     */         valuePoints[j] = e.getX();
/*     */         valuePoints[j + 1] = e.getY() * phaseY;
/*     */       } else {
/*     */         valuePoints[j] = 0.0F;
/*     */         valuePoints[j + 1] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     getValueToPixelMatrix().mapPoints(valuePoints);
/*     */     return valuePoints;
/*     */   }
/*     */   
/*     */   public float[] generateTransformedValuesLine(ILineDataSet data, float phaseX, float phaseY, int min, int max) {
/*     */     int count = ((int)((max - min) * phaseX) + 1) * 2;
/*     */     if (this.valuePointsForGenerateTransformedValuesLine.length != count)
/*     */       this.valuePointsForGenerateTransformedValuesLine = new float[count]; 
/*     */     float[] valuePoints = this.valuePointsForGenerateTransformedValuesLine;
/*     */     for (int j = 0; j < count; j += 2) {
/*     */       Entry e = data.getEntryForIndex(j / 2 + min);
/*     */       if (e != null) {
/*     */         valuePoints[j] = e.getX();
/*     */         valuePoints[j + 1] = e.getY() * phaseY;
/*     */       } else {
/*     */         valuePoints[j] = 0.0F;
/*     */         valuePoints[j + 1] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     getValueToPixelMatrix().mapPoints(valuePoints);
/*     */     return valuePoints;
/*     */   }
/*     */   
/*     */   public float[] generateTransformedValuesCandle(ICandleDataSet data, float phaseX, float phaseY, int from, int to) {
/*     */     int count = (int)((to - from) * phaseX + 1.0F) * 2;
/*     */     if (this.valuePointsForGenerateTransformedValuesCandle.length != count)
/*     */       this.valuePointsForGenerateTransformedValuesCandle = new float[count]; 
/*     */     float[] valuePoints = this.valuePointsForGenerateTransformedValuesCandle;
/*     */     for (int j = 0; j < count; j += 2) {
/*     */       CandleEntry e = (CandleEntry)data.getEntryForIndex(j / 2 + from);
/*     */       if (e != null) {
/*     */         valuePoints[j] = e.getX();
/*     */         valuePoints[j + 1] = e.getHigh() * phaseY;
/*     */       } else {
/*     */         valuePoints[j] = 0.0F;
/*     */         valuePoints[j + 1] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     getValueToPixelMatrix().mapPoints(valuePoints);
/*     */     return valuePoints;
/*     */   }
/*     */   
/*     */   public void pathValueToPixel(Path path) {
/*     */     path.transform(this.mMatrixValueToPx);
/*     */     path.transform(this.mViewPortHandler.getMatrixTouch());
/*     */     path.transform(this.mMatrixOffset);
/*     */   }
/*     */   
/*     */   public void pathValuesToPixel(List<Path> paths) {
/*     */     for (int i = 0; i < paths.size(); i++)
/*     */       pathValueToPixel((Path)paths.get(i)); 
/*     */   }
/*     */   
/*     */   public void pointValuesToPixel(float[] pts) {
/*     */     this.mMatrixValueToPx.mapPoints(pts);
/*     */     this.mViewPortHandler.getMatrixTouch().mapPoints(pts);
/*     */     this.mMatrixOffset.mapPoints(pts);
/*     */   }
/*     */   
/*     */   public void rectValueToPixel(RectF r) {
/*     */     this.mMatrixValueToPx.mapRect(r);
/*     */     this.mViewPortHandler.getMatrixTouch().mapRect(r);
/*     */     this.mMatrixOffset.mapRect(r);
/*     */   }
/*     */   
/*     */   public void rectToPixelPhase(RectF r, float phaseY) {
/*     */     r.top *= phaseY;
/*     */     r.bottom *= phaseY;
/*     */     this.mMatrixValueToPx.mapRect(r);
/*     */     this.mViewPortHandler.getMatrixTouch().mapRect(r);
/*     */     this.mMatrixOffset.mapRect(r);
/*     */   }
/*     */   
/*     */   public void rectToPixelPhaseHorizontal(RectF r, float phaseY) {
/*     */     r.left *= phaseY;
/*     */     r.right *= phaseY;
/*     */     this.mMatrixValueToPx.mapRect(r);
/*     */     this.mViewPortHandler.getMatrixTouch().mapRect(r);
/*     */     this.mMatrixOffset.mapRect(r);
/*     */   }
/*     */   
/*     */   public void rectValueToPixelHorizontal(RectF r) {
/*     */     this.mMatrixValueToPx.mapRect(r);
/*     */     this.mViewPortHandler.getMatrixTouch().mapRect(r);
/*     */     this.mMatrixOffset.mapRect(r);
/*     */   }
/*     */   
/*     */   public void rectValueToPixelHorizontal(RectF r, float phaseY) {
/*     */     r.left *= phaseY;
/*     */     r.right *= phaseY;
/*     */     this.mMatrixValueToPx.mapRect(r);
/*     */     this.mViewPortHandler.getMatrixTouch().mapRect(r);
/*     */     this.mMatrixOffset.mapRect(r);
/*     */   }
/*     */   
/*     */   public void rectValuesToPixel(List<RectF> rects) {
/*     */     Matrix m = getValueToPixelMatrix();
/*     */     for (int i = 0; i < rects.size(); i++)
/*     */       m.mapRect((RectF)rects.get(i)); 
/*     */   }
/*     */   
/*     */   public void pixelsToValue(float[] pixels) {
/*     */     Matrix tmp = this.mPixelToValueMatrixBuffer;
/*     */     tmp.reset();
/*     */     this.mMatrixOffset.invert(tmp);
/*     */     tmp.mapPoints(pixels);
/*     */     this.mViewPortHandler.getMatrixTouch().invert(tmp);
/*     */     tmp.mapPoints(pixels);
/*     */     this.mMatrixValueToPx.invert(tmp);
/*     */     tmp.mapPoints(pixels);
/*     */   }
/*     */   
/*     */   public MPPointD getValuesByTouchPoint(float x, float y) {
/*     */     MPPointD result = MPPointD.getInstance(0.0D, 0.0D);
/*     */     getValuesByTouchPoint(x, y, result);
/*     */     return result;
/*     */   }
/*     */   
/*     */   public void getValuesByTouchPoint(float x, float y, MPPointD outputPoint) {
/*     */     this.ptsBuffer[0] = x;
/*     */     this.ptsBuffer[1] = y;
/*     */     pixelsToValue(this.ptsBuffer);
/*     */     outputPoint.x = this.ptsBuffer[0];
/*     */     outputPoint.y = this.ptsBuffer[1];
/*     */   }
/*     */   
/*     */   public MPPointD getPixelForValues(float x, float y) {
/*     */     this.ptsBuffer[0] = x;
/*     */     this.ptsBuffer[1] = y;
/*     */     pointValuesToPixel(this.ptsBuffer);
/*     */     double xPx = this.ptsBuffer[0];
/*     */     double yPx = this.ptsBuffer[1];
/*     */     return MPPointD.getInstance(xPx, yPx);
/*     */   }
/*     */   
/*     */   public Matrix getValueMatrix() { return this.mMatrixValueToPx; }
/*     */   
/*     */   public Matrix getOffsetMatrix() { return this.mMatrixOffset; }
/*     */   
/*     */   public Matrix getValueToPixelMatrix() {
/*     */     this.mMBuffer1.set(this.mMatrixValueToPx);
/*     */     this.mMBuffer1.postConcat(this.mViewPortHandler.mMatrixTouch);
/*     */     this.mMBuffer1.postConcat(this.mMatrixOffset);
/*     */     return this.mMBuffer1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\Transformer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */