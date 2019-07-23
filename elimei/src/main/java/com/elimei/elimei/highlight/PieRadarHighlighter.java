/*    */ package com.elimei.elimei.highlight;
/*    */ 
/*    */ import com.elimei.elimei.charts.PieRadarChartBase;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PieRadarHighlighter<T extends PieRadarChartBase>
/*    */   extends Object
/*    */   implements IHighlighter
/*    */ {
/*    */   protected T mChart;
/*    */   protected List<Highlight> mHighlightBuffer;
/*    */   
/*    */   public PieRadarHighlighter(T chart) {
/* 20 */     this.mHighlightBuffer = new ArrayList();
/*    */ 
/*    */     
/* 23 */     this.mChart = chart;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Highlight getHighlight(float x, float y) {
/* 29 */     float touchDistanceToCenter = this.mChart.distanceToCenter(x, y);
/*    */ 
/*    */     
/* 32 */     if (touchDistanceToCenter > this.mChart.getRadius())
/*    */     {
/*    */       
/* 35 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 39 */     float angle = this.mChart.getAngleForPoint(x, y);
/*    */     
/* 41 */     if (this.mChart instanceof com.elimei.elimei.charts.PieChart) {
/* 42 */       angle /= this.mChart.getAnimator().getPhaseY();
/*    */     }
/*    */     
/* 45 */     int index = this.mChart.getIndexForAngle(angle);
/*    */ 
/*    */     
/* 48 */     if (index < 0 || index >= this.mChart.getData().getMaxEntryCountSet().getEntryCount()) {
/* 49 */       return null;
/*    */     }
/*    */     
/* 52 */     return getClosestHighlight(index, x, y);
/*    */   }
/*    */   
/*    */   protected abstract Highlight getClosestHighlight(int paramInt, float paramFloat1, float paramFloat2);
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\highlight\PieRadarHighlighter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */