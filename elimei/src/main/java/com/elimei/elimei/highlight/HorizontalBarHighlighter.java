/*    */ package com.elimei.elimei.highlight;
/*    */ 
/*    */ import com.elimei.elimei.data.BarData;
/*    */ import com.elimei.elimei.data.DataSet;
/*    */ import com.elimei.elimei.data.Entry;
/*    */ import com.elimei.elimei.interfaces.dataprovider.BarDataProvider;
/*    */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
/*    */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*    */ import com.elimei.elimei.utils.MPPointD;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HorizontalBarHighlighter
/*    */   extends BarHighlighter
/*    */ {
/* 20 */   public HorizontalBarHighlighter(BarDataProvider chart) { super(chart); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Highlight getHighlight(float x, float y) {
/* 26 */     BarData barData = ((BarDataProvider)this.mChart).getBarData();
/*    */     
/* 28 */     MPPointD pos = getValsForTouch(y, x);
/*    */     
/* 30 */     Highlight high = getHighlightForX((float)pos.y, y, x);
/* 31 */     if (high == null) {
/* 32 */       return null;
/*    */     }
/* 34 */     IBarDataSet set = (IBarDataSet)barData.getDataSetByIndex(high.getDataSetIndex());
/* 35 */     if (set.isStacked())
/*    */     {
/* 37 */       return getStackedHighlight(high, set, (float)pos.y, (float)pos.x);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     MPPointD.recycleInstance(pos);
/*    */     
/* 45 */     return high;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected List<Highlight> buildHighlights(IDataSet set, int dataSetIndex, float xVal, DataSet.Rounding rounding) {
/* 51 */     ArrayList<Highlight> highlights = new ArrayList<Highlight>();
/*    */ 
/*    */     
/* 54 */     List<Entry> entries = set.getEntriesForXValue(xVal);
/* 55 */     if (entries.size() == 0) {
/*    */       
/* 57 */       Entry closest = set.getEntryForXValue(xVal, 0f, rounding);
/* 58 */       if (closest != null)
/*    */       {
/*    */         
/* 61 */         entries = set.getEntriesForXValue(closest.getX());
/*    */       }
/*    */     } 
/*    */     
/* 65 */     if (entries.size() == 0) {
/* 66 */       return highlights;
/*    */     }
/* 68 */     for (Entry e : entries) {
/*    */       
/* 70 */       MPPointD pixels = ((BarDataProvider)this.mChart).getTransformer(set.getAxisDependency()).getPixelForValues(e.getY(), e.getX());
/*    */       
/* 72 */       highlights.add(new Highlight(e
/* 73 */             .getX(), e.getY(), (float)pixels.x, (float)pixels.y, dataSetIndex, set
/*    */             
/* 75 */             .getAxisDependency()));
/*    */     } 
/*    */     
/* 78 */     return highlights;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 83 */   protected float getDistance(float x1, float y1, float x2, float y2) { return Math.abs(y1 - y2); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\highlight\HorizontalBarHighlighter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */