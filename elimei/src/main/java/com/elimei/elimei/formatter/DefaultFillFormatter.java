/*    */ package com.elimei.elimei.formatter;
/*    */ 
/*    */ import com.elimei.elimei.data.LineData;
/*    */ import com.elimei.elimei.interfaces.dataprovider.LineDataProvider;
/*    */ import com.elimei.elimei.interfaces.datasets.ILineDataSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultFillFormatter
/*    */   implements IFillFormatter
/*    */ {
/*    */   public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
/* 19 */     float fillMin = 0.0F;
/* 20 */     float chartMaxY = dataProvider.getYChartMax();
/* 21 */     float chartMinY = dataProvider.getYChartMin();
/*    */     
/* 23 */     LineData data = dataProvider.getLineData();
/*    */     
/* 25 */     if (dataSet.getYMax() > 0.0F && dataSet.getYMin() < 0.0F) {
/* 26 */       fillMin = 0.0F;
/*    */     } else {
/*    */       float min, max;
/*    */ 
/*    */       
/* 31 */       if (data.getYMax() > 0.0F) {
/* 32 */         max = 0.0F;
/*    */       } else {
/* 34 */         max = chartMaxY;
/* 35 */       }  if (data.getYMin() < 0.0F) {
/* 36 */         min = 0.0F;
/*    */       } else {
/* 38 */         min = chartMinY;
/*    */       } 
/* 40 */       fillMin = (dataSet.getYMin() >= 0.0F) ? min : max;
/*    */     } 
/*    */     
/* 43 */     return fillMin;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\formatter\DefaultFillFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */