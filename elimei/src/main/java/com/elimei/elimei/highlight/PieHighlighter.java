/*    */ package com.elimei.elimei.highlight;
/*    */ 
/*    */ import com.elimei.elimei.charts.PieChart;
/*    */ import com.elimei.elimei.data.Entry;
/*    */ import com.elimei.elimei.data.PieData;
/*    */ import com.elimei.elimei.interfaces.datasets.IPieDataSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PieHighlighter
/*    */   extends PieRadarHighlighter<PieChart>
/*    */ {
/* 13 */   public PieHighlighter(PieChart chart) { super(chart); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Highlight getClosestHighlight(int index, float x, float y) {
/* 19 */     IPieDataSet set = ((PieData)((PieChart)this.mChart).getData()).getDataSet();
/*    */     
/* 21 */     Entry entry = set.getEntryForIndex(index);
/*    */     
/* 23 */     return new Highlight(index, entry.getY(), x, y, 0, set.getAxisDependency());
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\highlight\PieHighlighter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */