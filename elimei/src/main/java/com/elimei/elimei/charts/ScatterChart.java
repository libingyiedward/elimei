/*    */ package com.elimei.elimei.charts;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import com.elimei.elimei.data.ScatterData;
/*    */ import com.elimei.elimei.interfaces.dataprovider.ScatterDataProvider;
/*    */ import com.elimei.elimei.renderer.ScatterChartRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScatterChart
/*    */   extends BarLineChartBase<ScatterData>
/*    */   implements ScatterDataProvider
/*    */ {
/* 21 */   public ScatterChart(Context context) { super(context); }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public ScatterChart(Context context, AttributeSet attrs) { super(context, attrs); }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public ScatterChart(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void init() {
/* 35 */     super.init();
/*    */     
/* 37 */     this.mRenderer = new ScatterChartRenderer(this, this.mAnimator, this.mViewPortHandler);
/*    */     
/* 39 */     getXAxis().setSpaceMin(0.5F);
/* 40 */     getXAxis().setSpaceMax(0.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public ScatterData getScatterData() { return (ScatterData)this.mData; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum ScatterShape
/*    */   {
/* 54 */     SQUARE("SQUARE"),
/* 55 */     CIRCLE("CIRCLE"),
/* 56 */     TRIANGLE("TRIANGLE"),
/* 57 */     CROSS("CROSS"),
/* 58 */     X("X"),
/* 59 */     CHEVRON_UP("CHEVRON_UP"),
/* 60 */     CHEVRON_DOWN("CHEVRON_DOWN");
/*    */     
/*    */     private final String shapeIdentifier;
/*    */ 
/*    */     
/* 65 */     ScatterShape(String shapeIdentifier) { this.shapeIdentifier = shapeIdentifier; }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 70 */     public String toString() { return this.shapeIdentifier; }
/*    */ 
/*    */ 
/*    */     
/* 74 */     public static ScatterShape[] getAllDefaultShapes() { return new ScatterShape[] { SQUARE, CIRCLE, TRIANGLE, CROSS, X, CHEVRON_UP, CHEVRON_DOWN }; }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\ScatterChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */