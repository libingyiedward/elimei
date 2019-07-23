/*    */ package com.elimei.elimei.jobs;
/*    */ 
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
/*    */ 
/*    */ public class MoveViewJob
/*    */   extends ViewPortJob
/*    */ {
/* 18 */   private static ObjectPool<MoveViewJob> pool = ObjectPool.create(2, new MoveViewJob(null, 0.0F, 0.0F, null, null)); static  {
/* 19 */     pool.setReplenishPercentage(0.5F);
/*    */   }
/*    */   
/*    */   public static MoveViewJob getInstance(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v) {
/* 23 */     MoveViewJob result = (MoveViewJob)pool.get();
/* 24 */     result.mViewPortHandler = viewPortHandler;
/* 25 */     result.xValue = xValue;
/* 26 */     result.yValue = yValue;
/* 27 */     result.mTrans = trans;
/* 28 */     result.view = v;
/* 29 */     return result;
/*    */   }
/*    */ 
/*    */   
/* 33 */   public static void recycleInstance(MoveViewJob instance) { pool.recycle(instance); }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public MoveViewJob(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v) { super(viewPortHandler, xValue, yValue, trans, v); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 43 */     this.pts[0] = this.xValue;
/* 44 */     this.pts[1] = this.yValue;
/*    */     
/* 46 */     this.mTrans.pointValuesToPixel(this.pts);
/* 47 */     this.mViewPortHandler.centerViewPort(this.pts, this.view);
/*    */     
/* 49 */     recycleInstance(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 54 */   protected ObjectPool.Poolable instantiate() { return new MoveViewJob(this.mViewPortHandler, this.xValue, this.yValue, this.mTrans, this.view); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\jobs\MoveViewJob.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */