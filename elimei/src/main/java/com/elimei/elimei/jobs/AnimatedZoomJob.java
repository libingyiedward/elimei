/*     */ package com.elimei.elimei.jobs;
/*     */ 
/*     */ import android.animation.Animator;
/*     */ import android.animation.ValueAnimator;
/*     */ import android.annotation.SuppressLint;
/*     */ import android.graphics.Matrix;
/*     */ import android.view.View;
/*     */ import com.elimei.elimei.charts.BarLineChartBase;
/*     */ import com.elimei.elimei.components.YAxis;
/*     */ import com.elimei.elimei.utils.ObjectPool;
/*     */ import com.elimei.elimei.utils.Transformer;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SuppressLint({"NewApi"})
/*     */ public class AnimatedZoomJob
/*     */   extends AnimatedViewPortJob
/*     */   implements Animator.AnimatorListener
/*     */ {
/*  24 */   private static ObjectPool<AnimatedZoomJob> pool = ObjectPool.create(8, new AnimatedZoomJob(null, null, null, null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0L)); protected float zoomOriginX; protected float zoomOriginY; protected float zoomCenterX; protected float zoomCenterY; protected YAxis yAxis; protected float xAxisRange;
/*     */   protected Matrix mOnAnimationUpdateMatrixBuffer;
/*     */   
/*     */   public static AnimatedZoomJob getInstance(ViewPortHandler viewPortHandler, View v, Transformer trans, YAxis axis, float xAxisRange, float scaleX, float scaleY, float xOrigin, float yOrigin, float zoomCenterX, float zoomCenterY, float zoomOriginX, float zoomOriginY, long duration) {
/*  28 */     AnimatedZoomJob result = (AnimatedZoomJob)pool.get();
/*  29 */     result.mViewPortHandler = viewPortHandler;
/*  30 */     result.xValue = scaleX;
/*  31 */     result.yValue = scaleY;
/*  32 */     result.mTrans = trans;
/*  33 */     result.view = v;
/*  34 */     result.xOrigin = xOrigin;
/*  35 */     result.yOrigin = yOrigin;
/*  36 */     result.resetAnimator();
/*  37 */     result.animator.setDuration(duration);
/*  38 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   public AnimatedZoomJob(ViewPortHandler viewPortHandler, View v, Transformer trans, YAxis axis, float xAxisRange, float scaleX, float scaleY, float xOrigin, float yOrigin, float zoomCenterX, float zoomCenterY, float zoomOriginX, float zoomOriginY, long duration) {
/*  53 */     super(viewPortHandler, scaleX, scaleY, trans, v, xOrigin, yOrigin, duration);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.mOnAnimationUpdateMatrixBuffer = new Matrix(); this.zoomCenterX = zoomCenterX; this.zoomCenterY = zoomCenterY; this.zoomOriginX = zoomOriginX;
/*     */     this.zoomOriginY = zoomOriginY;
/*     */     this.animator.addListener(this);
/*     */     this.yAxis = axis;
/*  68 */     this.xAxisRange = xAxisRange; } public void onAnimationUpdate(ValueAnimator animation) { float scaleX = this.xOrigin + (this.xValue - this.xOrigin) * this.phase;
/*  69 */     float scaleY = this.yOrigin + (this.yValue - this.yOrigin) * this.phase;
/*     */     
/*  71 */     Matrix save = this.mOnAnimationUpdateMatrixBuffer;
/*  72 */     this.mViewPortHandler.setZoom(scaleX, scaleY, save);
/*  73 */     this.mViewPortHandler.refresh(save, this.view, false);
/*     */     
/*  75 */     float valsInView = this.yAxis.mAxisRange / this.mViewPortHandler.getScaleY();
/*  76 */     float xsInView = this.xAxisRange / this.mViewPortHandler.getScaleX();
/*     */     
/*  78 */     this.pts[0] = this.zoomOriginX + (this.zoomCenterX - xsInView / 2.0F - this.zoomOriginX) * this.phase;
/*  79 */     this.pts[1] = this.zoomOriginY + (this.zoomCenterY + valsInView / 2.0F - this.zoomOriginY) * this.phase;
/*     */     
/*  81 */     this.mTrans.pointValuesToPixel(this.pts);
/*     */     
/*  83 */     this.mViewPortHandler.translate(this.pts, save);
/*  84 */     this.mViewPortHandler.refresh(save, this.view, true); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onAnimationEnd(Animator animation) {
/*  89 */     ((BarLineChartBase)this.view).calculateOffsets();
/*  90 */     this.view.postInvalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onAnimationCancel(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onAnimationRepeat(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recycleSelf() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onAnimationStart(Animator animation) {}
/*     */ 
/*     */ 
/*     */   
/* 115 */   protected ObjectPool.Poolable instantiate() { return new AnimatedZoomJob(null, null, null, null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0L); }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\jobs\AnimatedZoomJob.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */