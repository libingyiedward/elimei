/*     */ package com.elimei.elimei.highlight;
/*     */ 
/*     */ import com.elimei.elimei.components.YAxis;
/*     */ import com.elimei.elimei.data.BarLineScatterCandleBubbleData;
/*     */ import com.elimei.elimei.data.DataSet;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
/*     */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*     */ import com.elimei.elimei.utils.MPPointD;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChartHighlighter<T extends BarLineScatterCandleBubbleDataProvider>
/*     */   extends Object
/*     */   implements IHighlighter
/*     */ {
/*     */   protected T mChart;
/*     */   protected List<Highlight> mHighlightBuffer;
/*     */   
/*     */   public ChartHighlighter(T chart) {
/*  28 */     this.mHighlightBuffer = new ArrayList();
/*     */ 
/*     */     
/*  31 */     this.mChart = chart;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Highlight getHighlight(float x, float y) {
/*  37 */     MPPointD pos = getValsForTouch(x, y);
/*  38 */     float xVal = (float)pos.x;
/*  39 */     MPPointD.recycleInstance(pos);
/*     */     
/*  41 */     return getHighlightForX(xVal, x, y);
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
/*  56 */   protected MPPointD getValsForTouch(float x, float y) { return this.mChart.getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(x, y); }
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
/*     */   protected Highlight getHighlightForX(float xVal, float x, float y) {
/*  70 */     List<Highlight> closestValues = getHighlightsAtXValue(xVal, x, y);
/*     */     
/*  72 */     if (closestValues.isEmpty()) {
/*  73 */       return null;
/*     */     }
/*     */     
/*  76 */     float leftAxisMinDist = getMinimumDistance(closestValues, y, YAxis.AxisDependency.LEFT);
/*  77 */     float rightAxisMinDist = getMinimumDistance(closestValues, y, YAxis.AxisDependency.RIGHT);
/*     */     
/*  79 */     YAxis.AxisDependency axis = (leftAxisMinDist < rightAxisMinDist) ? YAxis.AxisDependency.LEFT : YAxis.AxisDependency.RIGHT;
/*     */     
/*  81 */     return getClosestHighlightByPixel(closestValues, x, y, axis, this.mChart.getMaxHighlightDistance());
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
/*     */   protected float getMinimumDistance(List<Highlight> closestValues, float pos, YAxis.AxisDependency axis) {
/*  97 */     float distance = Float.MAX_VALUE;
/*     */     
/*  99 */     for (int i = 0; i < closestValues.size(); i++) {
/*     */       
/* 101 */       Highlight high = (Highlight)closestValues.get(i);
/*     */       
/* 103 */       if (high.getAxis() == axis) {
/*     */         
/* 105 */         float tempDistance = Math.abs(getHighlightPos(high) - pos);
/* 106 */         if (tempDistance < distance) {
/* 107 */           distance = tempDistance;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     return distance;
/*     */   }
/*     */ 
/*     */   
/* 116 */   protected float getHighlightPos(Highlight h) { return h.getYPx(); }
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
/*     */   protected List<Highlight> getHighlightsAtXValue(float xVal, float x, float y) {
/* 130 */     this.mHighlightBuffer.clear();
/*     */     
/* 132 */     BarLineScatterCandleBubbleData data = getData();
/*     */     
/* 134 */     if (data == null) {
/* 135 */       return this.mHighlightBuffer;
/*     */     }
/* 137 */     for (int i = 0, dataSetCount = data.getDataSetCount(); i < dataSetCount; i++) {
/*     */       
/* 139 */       IDataSet dataSet = data.getDataSetByIndex(i);
/*     */ 
/*     */       
/* 142 */       if (dataSet.isHighlightEnabled())
/*     */       {
/*     */         
/* 145 */         this.mHighlightBuffer.addAll(buildHighlights(dataSet, i, xVal, DataSet.Rounding.CLOSEST));
/*     */       }
/*     */     } 
/* 148 */     return this.mHighlightBuffer;
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
/*     */   protected List<Highlight> buildHighlights(IDataSet set, int dataSetIndex, float xVal, DataSet.Rounding rounding) {
/* 162 */     ArrayList<Highlight> highlights = new ArrayList<Highlight>();
/*     */ 
/*     */     
/* 165 */     List<Entry> entries = set.getEntriesForXValue(xVal);
/* 166 */     if (entries.size() == 0) {
/*     */       
/* 168 */       Entry closest = set.getEntryForXValue(xVal, 0f, rounding);
/* 169 */       if (closest != null)
/*     */       {
/*     */         
/* 172 */         entries = set.getEntriesForXValue(closest.getX());
/*     */       }
/*     */     } 
/*     */     
/* 176 */     if (entries.size() == 0) {
/* 177 */       return highlights;
/*     */     }
/* 179 */     for (Entry e : entries) {
/*     */       
/* 181 */       MPPointD pixels = this.mChart.getTransformer(set.getAxisDependency()).getPixelForValues(e.getX(), e.getY());
/*     */       
/* 183 */       highlights.add(new Highlight(e
/* 184 */             .getX(), e.getY(), (float)pixels.x, (float)pixels.y, dataSetIndex, set
/*     */             
/* 186 */             .getAxisDependency()));
/*     */     } 
/*     */     
/* 189 */     return highlights;
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
/*     */   public Highlight getClosestHighlightByPixel(List<Highlight> closestValues, float x, float y, YAxis.AxisDependency axis, float minSelectionDistance) {
/* 207 */     Highlight closest = null;
/* 208 */     float distance = minSelectionDistance;
/*     */     
/* 210 */     for (int i = 0; i < closestValues.size(); i++) {
/*     */       
/* 212 */       Highlight high = (Highlight)closestValues.get(i);
/*     */       
/* 214 */       if (axis == null || high.getAxis() == axis) {
/*     */         
/* 216 */         float cDistance = getDistance(x, y, high.getXPx(), high.getYPx());
/*     */         
/* 218 */         if (cDistance < distance) {
/* 219 */           closest = high;
/* 220 */           distance = cDistance;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 225 */     return closest;
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
/* 240 */   protected float getDistance(float x1, float y1, float x2, float y2) { return (float)Math.hypot((x1 - x2), (y1 - y2)); }
/*     */ 
/*     */ 
/*     */   
/* 244 */   protected BarLineScatterCandleBubbleData getData() { return this.mChart.getData(); }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\highlight\ChartHighlighter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */