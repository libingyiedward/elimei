/*    */ package com.elimei.elimei.jobs;
/*    */ 
/*    */ import android.animation.Animator;
/*    */ import android.animation.ObjectAnimator;
/*    */ import android.animation.ValueAnimator;
/*    */ import android.annotation.SuppressLint;
/*    */ import android.view.View;
/*    */ import com.elimei.elimei.utils.Transformer;
/*    */ import com.elimei.elimei.utils.ViewPortHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SuppressLint({"NewApi"})
/*    */ public abstract class AnimatedViewPortJob
/*    */   extends ViewPortJob
/*    */   implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener
/*    */ {
/*    */   protected ObjectAnimator animator;
/*    */   protected float phase;
/*    */   protected float xOrigin;
/*    */   protected float yOrigin;
/*    */   
/*    */   public AnimatedViewPortJob(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v, float xOrigin, float yOrigin, long duration) {
/* 26 */     super(viewPortHandler, xValue, yValue, trans, v);
/* 27 */     this.xOrigin = xOrigin;
/* 28 */     this.yOrigin = yOrigin;
/* 29 */     this.animator = ObjectAnimator.ofFloat(this, "phase", new float[] { 0.0F, 1.0F });
/* 30 */     this.animator.setDuration(duration);
/* 31 */     this.animator.addUpdateListener(this);
/* 32 */     this.animator.addListener(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SuppressLint({"NewApi"})
/* 38 */   public void run() { this.animator.start(); }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public float getPhase() { return this.phase; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public void setPhase(float phase) { this.phase = phase; }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public float getXOrigin() { return this.xOrigin; }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public float getYOrigin() { return this.yOrigin; }
/*    */ 
/*    */   
/*    */   public abstract void recycleSelf();
/*    */   
/*    */   protected void resetAnimator() {
/* 60 */     this.animator.removeAllListeners();
/* 61 */     this.animator.removeAllUpdateListeners();
/* 62 */     this.animator.reverse();
/* 63 */     this.animator.addUpdateListener(this);
/* 64 */     this.animator.addListener(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onAnimationStart(Animator animation) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void onAnimationEnd(Animator animation) {
/*    */     try {
/* 75 */       recycleSelf();
/* 76 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onAnimationCancel(Animator animation) {
/*    */     try {
/* 84 */       recycleSelf();
/* 85 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*    */   }
/*    */   
/*    */   public void onAnimationRepeat(Animator animation) {}
/*    */   
/*    */   public void onAnimationUpdate(ValueAnimator animation) {}
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\jobs\AnimatedViewPortJob.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */