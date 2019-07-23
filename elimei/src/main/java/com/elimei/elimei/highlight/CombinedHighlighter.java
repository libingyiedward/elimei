/*    */ package com.elimei.elimei.highlight;
/*    */ 
/*    */ import com.elimei.elimei.data.BarLineScatterCandleBubbleData;
/*    */ import com.elimei.elimei.data.ChartData;
/*    */ import com.elimei.elimei.data.DataSet;
/*    */ import com.elimei.elimei.interfaces.dataprovider.BarDataProvider;
/*    */ import com.elimei.elimei.interfaces.dataprovider.CombinedDataProvider;
/*    */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CombinedHighlighter
/*    */   extends ChartHighlighter<CombinedDataProvider>
/*    */   implements IHighlighter
/*    */ {
/*    */   protected BarHighlighter barHighlighter;
/*    */   
/*    */   public CombinedHighlighter(CombinedDataProvider chart, BarDataProvider barChart) {
/* 25 */     super(chart);
/*    */ 
/*    */     
/* 28 */     this.barHighlighter = (barChart.getBarData() == null) ? null : new BarHighlighter(barChart);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected List<Highlight> getHighlightsAtXValue(float xVal, float x, float y) {
/* 34 */     this.mHighlightBuffer.clear();
/*    */     
/* 36 */     List<BarLineScatterCandleBubbleData> dataObjects = ((CombinedDataProvider)this.mChart).getCombinedData().getAllData();
/*    */     
/* 38 */     for (int i = 0; i < dataObjects.size(); i++) {
/*    */       
/* 40 */       ChartData dataObject = (ChartData)dataObjects.get(i);
/*    */ 
/*    */       
/* 43 */       if (this.barHighlighter != null && dataObject instanceof com.elimei.elimei.data.BarData) {
/* 44 */         Highlight high = this.barHighlighter.getHighlight(x, y);
/*    */         
/* 46 */         if (high != null) {
/* 47 */           high.setDataIndex(i);
/* 48 */           this.mHighlightBuffer.add(high);
/*    */         } 
/*    */       } else {
/*    */         
/* 52 */         for (int j = 0, dataSetCount = dataObject.getDataSetCount(); j < dataSetCount; j++) {
/*    */           
/* 54 */           IDataSet dataSet = ((BarLineScatterCandleBubbleData)dataObjects.get(i)).getDataSetByIndex(j);
/*    */ 
/*    */           
/* 57 */           if (dataSet.isHighlightEnabled()) {
/*    */ 
/*    */             
/* 60 */             List<Highlight> highs = buildHighlights(dataSet, j, xVal, DataSet.Rounding.CLOSEST);
/* 61 */             for (Highlight high : highs) {
/*    */               
/* 63 */               high.setDataIndex(i);
/* 64 */               this.mHighlightBuffer.add(high);
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 70 */     return this.mHighlightBuffer;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\highlight\CombinedHighlighter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */