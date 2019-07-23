/*    */ package com.elimei.elimei.renderer;
/*    */ 
/*    */ import com.elimei.elimei.animation.ChartAnimator;
/*    */ import com.elimei.elimei.data.DataSet;
/*    */ import com.elimei.elimei.data.Entry;
/*    */ import com.elimei.elimei.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
/*    */ import com.elimei.elimei.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
/*    */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*    */ import com.elimei.elimei.utils.ViewPortHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BarLineScatterCandleBubbleRenderer
/*    */   extends DataRenderer
/*    */ {
/* 19 */   protected XBounds mXBounds = new XBounds();
/*    */ 
/*    */   
/* 22 */   public BarLineScatterCandleBubbleRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) { super(animator, viewPortHandler); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   protected boolean shouldDrawValues(IDataSet set) { return (set.isVisible() && (set.isDrawValuesEnabled() || set.isDrawIconsEnabled())); }
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
/*    */   protected boolean isInBoundsX(Entry e, IBarLineScatterCandleBubbleDataSet set) {
/* 44 */     if (e == null) {
/* 45 */       return false;
/*    */     }
/* 47 */     float entryIndex = set.getEntryIndex(e);
/*    */     
/* 49 */     if (e == null || entryIndex >= set.getEntryCount() * this.mAnimator.getPhaseX()) {
/* 50 */       return false;
/*    */     }
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected class XBounds
/*    */   {
/*    */     public int min;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public int max;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public int range;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public void set(BarLineScatterCandleBubbleDataProvider chart, IBarLineScatterCandleBubbleDataSet dataSet) {
/* 83 */       float phaseX = Math.max(0.0F, Math.min(1.0F, BarLineScatterCandleBubbleRenderer.this.mAnimator.getPhaseX()));
/*    */       
/* 85 */       float low = chart.getLowestVisibleX();
/* 86 */       float high = chart.getHighestVisibleX();
/*    */       
/* 88 */       Entry entryFrom = dataSet.getEntryForXValue(low, 0f, DataSet.Rounding.DOWN);
/* 89 */       Entry entryTo = dataSet.getEntryForXValue(high, 0f, DataSet.Rounding.UP);
/*    */       
/* 91 */       this.min = (entryFrom == null) ? 0 : dataSet.getEntryIndex(entryFrom);
/* 92 */       this.max = (entryTo == null) ? 0 : dataSet.getEntryIndex(entryTo);
/* 93 */       this.range = (int)((this.max - this.min) * phaseX);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\BarLineScatterCandleBubbleRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */