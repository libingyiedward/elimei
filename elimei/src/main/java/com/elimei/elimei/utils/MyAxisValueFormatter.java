/*    */ package com.elimei.elimei.utils;
/*    */ 
/*    */ import com.elimei.elimei.components.AxisBase;
/*    */ import com.elimei.elimei.formatter.IAxisValueFormatter;
/*    */ import java.text.DecimalFormat;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MyAxisValueFormatter
/*    */   implements IAxisValueFormatter
/*    */ {
/* 15 */   private DecimalFormat mFormat = new DecimalFormat("###,###,###,##0.0");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public String getFormattedValue(float value, AxisBase axis) { return this.mFormat.format(value) + " $"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public int GetColor(float value) { return 0; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\MyAxisValueFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */