/*    */ package com.elimei.elimei.utils;
/*    */ 
/*    */ import com.elimei.elimei.charts.BarLineChartBase;
/*    */ import com.elimei.elimei.components.AxisBase;
/*    */ import com.elimei.elimei.formatter.IAxisValueFormatter;
/*    */ 
/*    */ public class DayAxisValueFormatter
/*    */   implements IAxisValueFormatter {
/*    */   protected String[] mMonths;
/*    */   private BarLineChartBase<?> chart;
/*    */   
/*    */   public DayAxisValueFormatter(BarLineChartBase<?> chart) {
/* 13 */     this.mMonths = new String[] { "水分", "油脂", "纹理", "皱纹", "肤色", "色素", "敏感", "毛孔" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.chart = chart;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFormattedValue(float value, AxisBase axis) {
/* 26 */     int days = (int)value;
/* 27 */     return this.mMonths[days];
/*    */   }
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
/*    */ 
/*    */   
/* 71 */   public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   public int GetColor(float value) { return 0; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\DayAxisValueFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */