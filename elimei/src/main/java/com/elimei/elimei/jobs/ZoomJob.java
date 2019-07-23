/*    */ package com.elimei.elimei.jobs;
/*    */ 
/*    */ import android.graphics.Matrix;
/*    */ import android.view.View;
/*    */ import com.elimei.elimei.charts.BarLineChartBase;
/*    */ import com.elimei.elimei.components.YAxis;
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
/*    */ public class ZoomJob
/*    */   extends ViewPortJob
/*    */ {
/* 21 */   private static ObjectPool<ZoomJob> pool = ObjectPool.create(1, new ZoomJob(null, 0.0F, 0.0F, 0.0F, 0.0F, null, null, null)); protected float scaleX; protected float scaleY; static  {
/* 22 */     pool.setReplenishPercentage(0.5F);
/*    */   }
/*    */   protected YAxis.AxisDependency axisDependency; protected Matrix mRunMatrixBuffer;
/*    */   
/*    */   public static ZoomJob getInstance(ViewPortHandler viewPortHandler, float scaleX, float scaleY, float xValue, float yValue, Transformer trans, YAxis.AxisDependency axis, View v) {
/* 27 */     ZoomJob result = (ZoomJob)pool.get();
/* 28 */     result.xValue = xValue;
/* 29 */     result.yValue = yValue;
/* 30 */     result.scaleX = scaleX;
/* 31 */     result.scaleY = scaleY;
/* 32 */     result.mViewPortHandler = viewPortHandler;
/* 33 */     result.mTrans = trans;
/* 34 */     result.axisDependency = axis;
/* 35 */     result.view = v;
/* 36 */     return result;
/*    */   }
/*    */ 
/*    */   
/* 40 */   public static void recycleInstance(ZoomJob instance) { pool.recycle(instance); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoomJob(ViewPortHandler viewPortHandler, float scaleX, float scaleY, float xValue, float yValue, Transformer trans, YAxis.AxisDependency axis, View v) {
/* 50 */     super(viewPortHandler, xValue, yValue, trans, v);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 57 */     this.mRunMatrixBuffer = new Matrix();
/*    */     this.scaleX = scaleX;
/*    */     this.scaleY = scaleY;
/*    */     this.axisDependency = axis;
/*    */   } public void run() {
/* 62 */     Matrix save = this.mRunMatrixBuffer;
/* 63 */     this.mViewPortHandler.zoom(this.scaleX, this.scaleY, save);
/* 64 */     this.mViewPortHandler.refresh(save, this.view, false);
/*    */     
/* 66 */     float yValsInView = (((BarLineChartBase)this.view).getAxis(this.axisDependency)).mAxisRange / this.mViewPortHandler.getScaleY();
/* 67 */     float xValsInView = (((BarLineChartBase)this.view).getXAxis()).mAxisRange / this.mViewPortHandler.getScaleX();
/*    */     
/* 69 */     this.pts[0] = this.xValue - xValsInView / 2.0F;
/* 70 */     this.pts[1] = this.yValue + yValsInView / 2.0F;
/*    */     
/* 72 */     this.mTrans.pointValuesToPixel(this.pts);
/*    */     
/* 74 */     this.mViewPortHandler.translate(this.pts, save);
/* 75 */     this.mViewPortHandler.refresh(save, this.view, false);
/*    */     
/* 77 */     ((BarLineChartBase)this.view).calculateOffsets();
/* 78 */     this.view.postInvalidate();
/*    */     
/* 80 */     recycleInstance(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 85 */   protected ObjectPool.Poolable instantiate() { return new ZoomJob(null, 0.0F, 0.0F, 0.0F, 0.0F, null, null, null); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\jobs\ZoomJob.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */