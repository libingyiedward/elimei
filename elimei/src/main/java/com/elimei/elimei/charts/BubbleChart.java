/*    */ package com.elimei.elimei.charts;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import com.elimei.elimei.data.BubbleData;
/*    */ import com.elimei.elimei.interfaces.dataprovider.BubbleDataProvider;
/*    */ import com.elimei.elimei.renderer.BubbleChartRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BubbleChart
/*    */   extends BarLineChartBase<BubbleData>
/*    */   implements BubbleDataProvider
/*    */ {
/* 22 */   public BubbleChart(Context context) { super(context); }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public BubbleChart(Context context, AttributeSet attrs) { super(context, attrs); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public BubbleChart(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void init() {
/* 35 */     super.init();
/*    */     
/* 37 */     this.mRenderer = new BubbleChartRenderer(this, this.mAnimator, this.mViewPortHandler);
/*    */   }
/*    */ 
/*    */   
/* 41 */   public BubbleData getBubbleData() { return (BubbleData)this.mData; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\BubbleChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */