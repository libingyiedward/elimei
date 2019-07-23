/*    */ package com.elimei.elimei.charts;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import com.elimei.elimei.data.LineData;
/*    */ import com.elimei.elimei.interfaces.dataprovider.LineDataProvider;
/*    */ import com.elimei.elimei.renderer.LineChartRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LineChart
/*    */   extends BarLineChartBase<LineData>
/*    */   implements LineDataProvider
/*    */ {
/* 19 */   public LineChart(Context context) { super(context); }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public LineChart(Context context, AttributeSet attrs) { super(context, attrs); }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public LineChart(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void init() {
/* 32 */     super.init();
/*    */     
/* 34 */     this.mRenderer = new LineChartRenderer(this, this.mAnimator, this.mViewPortHandler);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public LineData getLineData() { return (LineData)this.mData; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onDetachedFromWindow() {
/* 45 */     if (this.mRenderer != null && this.mRenderer instanceof LineChartRenderer) {
/* 46 */       ((LineChartRenderer)this.mRenderer).releaseBitmap();
/*    */     }
/* 48 */     super.onDetachedFromWindow();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\LineChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */