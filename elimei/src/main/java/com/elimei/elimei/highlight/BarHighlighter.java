/*     */ package com.elimei.elimei.highlight;
/*     */ 
/*     */ import com.elimei.elimei.data.BarData;
/*     */ import com.elimei.elimei.data.BarEntry;
/*     */ import com.elimei.elimei.data.BarLineScatterCandleBubbleData;
/*     */ import com.elimei.elimei.interfaces.dataprovider.BarDataProvider;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
/*     */ import com.elimei.elimei.utils.MPPointD;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarHighlighter
/*     */   extends ChartHighlighter<BarDataProvider>
/*     */ {
/*  17 */   public BarHighlighter(BarDataProvider chart) { super(chart); }
/*     */ 
/*     */ 
/*     */   
/*     */   public Highlight getHighlight(float x, float y) {
/*  22 */     Highlight high = super.getHighlight(x, y);
/*     */     
/*  24 */     if (high == null) {
/*  25 */       return null;
/*     */     }
/*     */     
/*  28 */     MPPointD pos = getValsForTouch(x, y);
/*     */     
/*  30 */     BarData barData = ((BarDataProvider)this.mChart).getBarData();
/*     */     
/*  32 */     IBarDataSet set = (IBarDataSet)barData.getDataSetByIndex(high.getDataSetIndex());
/*  33 */     if (set.isStacked())
/*     */     {
/*  35 */       return getStackedHighlight(high, set, (float)pos.x, (float)pos.y);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  41 */     MPPointD.recycleInstance(pos);
/*     */     
/*  43 */     return high;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Highlight getStackedHighlight(Highlight high, IBarDataSet set, float xVal, float yVal) {
/*  58 */     BarEntry entry = (BarEntry)set.getEntryForXValue(xVal, yVal);
/*     */     
/*  60 */     if (entry == null) {
/*  61 */       return null;
/*     */     }
/*     */     
/*  64 */     if (entry.getYVals() == null) {
/*  65 */       return high;
/*     */     }
/*  67 */     Range[] ranges = entry.getRanges();
/*     */     
/*  69 */     if (ranges.length > 0) {
/*  70 */       int stackIndex = getClosestStackIndex(ranges, yVal);
/*     */       
/*  72 */       MPPointD pixels = ((BarDataProvider)this.mChart).getTransformer(set.getAxisDependency()).getPixelForValues(high.getX(), (ranges[stackIndex]).to);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  81 */       Highlight stackedHigh = new Highlight(entry.getX(), entry.getY(), (float)pixels.x, (float)pixels.y, high.getDataSetIndex(), stackIndex, high.getAxis());
/*     */ 
/*     */       
/*  84 */       MPPointD.recycleInstance(pixels);
/*     */       
/*  86 */       return stackedHigh;
/*     */     } 
/*     */ 
/*     */     
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getClosestStackIndex(Range[] ranges, float value) {
/* 104 */     if (ranges == null || ranges.length == 0) {
/* 105 */       return 0;
/*     */     }
/* 107 */     int stackIndex = 0;
/*     */     
/* 109 */     for (Range range : ranges) {
/* 110 */       if (range.contains(value)) {
/* 111 */         return stackIndex;
/*     */       }
/* 113 */       stackIndex++;
/*     */     } 
/*     */     
/* 116 */     int length = Math.max(ranges.length - 1, 0);
/*     */     
/* 118 */     return (value > (ranges[length]).to) ? length : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   protected float getDistance(float x1, float y1, float x2, float y2) { return Math.abs(x1 - x2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   protected BarLineScatterCandleBubbleData getData() { return ((BarDataProvider)this.mChart).getBarData(); }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\highlight\BarHighlighter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */