/*    */ package com.elimei.elimei.charts;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import com.elimei.elimei.data.CandleData;
/*    */ import com.elimei.elimei.interfaces.dataprovider.CandleDataProvider;
/*    */ import com.elimei.elimei.renderer.CandleStickChartRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CandleStickChart
/*    */   extends BarLineChartBase<CandleData>
/*    */   implements CandleDataProvider
/*    */ {
/* 19 */   public CandleStickChart(Context context) { super(context); }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public CandleStickChart(Context context, AttributeSet attrs) { super(context, attrs); }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public CandleStickChart(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void init() {
/* 32 */     super.init();
/*    */     
/* 34 */     this.mRenderer = new CandleStickChartRenderer(this, this.mAnimator, this.mViewPortHandler);
/*    */     
/* 36 */     getXAxis().setSpaceMin(0.5F);
/* 37 */     getXAxis().setSpaceMax(0.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public CandleData getCandleData() { return (CandleData)this.mData; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\CandleStickChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */