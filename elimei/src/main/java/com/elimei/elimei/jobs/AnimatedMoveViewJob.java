/*    */ package com.elimei.elimei.jobs;
/*    */ 
/*    */ import android.animation.ValueAnimator;
/*    */ import android.annotation.SuppressLint;
/*    */ import android.view.View;
/*    */ import com.elimei.elimei.utils.ObjectPool;
/*    */ import com.elimei.elimei.utils.Transformer;
/*    */ import com.elimei.elimei.utils.ViewPortHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SuppressLint({"NewApi"})
/*    */ public class AnimatedMoveViewJob
/*    */   extends AnimatedViewPortJob
/*    */ {
/* 20 */   private static ObjectPool<AnimatedMoveViewJob> pool = ObjectPool.create(4, new AnimatedMoveViewJob(null, 0.0F, 0.0F, null, null, 0.0F, 0.0F, 0L)); static  {
/* 21 */     pool.setReplenishPercentage(0.5F);
/*    */   }
/*    */   
/*    */   public static AnimatedMoveViewJob getInstance(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v, float xOrigin, float yOrigin, long duration) {
/* 25 */     AnimatedMoveViewJob result = (AnimatedMoveViewJob)pool.get();
/* 26 */     result.mViewPortHandler = viewPortHandler;
/* 27 */     result.xValue = xValue;
/* 28 */     result.yValue = yValue;
/* 29 */     result.mTrans = trans;
/* 30 */     result.view = v;
/* 31 */     result.xOrigin = xOrigin;
/* 32 */     result.yOrigin = yOrigin;
/*    */     
/* 34 */     result.animator.setDuration(duration);
/* 35 */     return result;
/*    */   }
/*    */ 
/*    */   
/* 39 */   public static void recycleInstance(AnimatedMoveViewJob instance) { pool.recycle(instance); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public AnimatedMoveViewJob(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v, float xOrigin, float yOrigin, long duration) { super(viewPortHandler, xValue, yValue, trans, v, xOrigin, yOrigin, duration); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onAnimationUpdate(ValueAnimator animation) {
/* 50 */     this.pts[0] = this.xOrigin + (this.xValue - this.xOrigin) * this.phase;
/* 51 */     this.pts[1] = this.yOrigin + (this.yValue - this.yOrigin) * this.phase;
/*    */     
/* 53 */     this.mTrans.pointValuesToPixel(this.pts);
/* 54 */     this.mViewPortHandler.centerViewPort(this.pts, this.view);
/*    */   }
/*    */ 
/*    */   
/* 58 */   public void recycleSelf() { recycleInstance(this); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   protected ObjectPool.Poolable instantiate() { return new AnimatedMoveViewJob(null, 0.0F, 0.0F, null, null, 0.0F, 0.0F, 0L); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\jobs\AnimatedMoveViewJob.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */