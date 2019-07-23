/*    */ package com.elimei.elimei.jobs;
/*    */ 
/*    */ import android.view.View;
/*    */ import com.elimei.elimei.utils.ObjectPool;
/*    */ import com.elimei.elimei.utils.Transformer;
/*    */ import com.elimei.elimei.utils.ViewPortHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ViewPortJob
/*    */   extends ObjectPool.Poolable
/*    */   implements Runnable
/*    */ {
/*    */   protected float[] pts;
/*    */   protected ViewPortHandler mViewPortHandler;
/*    */   protected float xValue;
/*    */   protected float yValue;
/*    */   protected Transformer mTrans;
/*    */   protected View view;
/*    */   
/*    */   public ViewPortJob(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v) {
/* 22 */     this.pts = new float[2];
/*    */ 
/*    */     
/* 25 */     this.xValue = 0.0F;
/* 26 */     this.yValue = 0.0F;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     this.mViewPortHandler = viewPortHandler;
/* 33 */     this.xValue = xValue;
/* 34 */     this.yValue = yValue;
/* 35 */     this.mTrans = trans;
/* 36 */     this.view = v;
/*    */   }
/*    */ 
/*    */   
/* 40 */   public float getXValue() { return this.xValue; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public float getYValue() { return this.yValue; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\jobs\ViewPortJob.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */