/*    */ package com.elimei.elimei.formatter;
/*    */ 
/*    */ import com.elimei.elimei.components.AxisBase;
/*    */ import com.elimei.elimei.data.Entry;
/*    */ import com.elimei.elimei.utils.ViewPortHandler;
/*    */ import java.text.DecimalFormat;
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
/*    */ public class PercentFormatter
/*    */   implements IValueFormatter, IAxisValueFormatter
/*    */ {
/*    */   protected DecimalFormat mFormat;
/*    */   
/* 22 */   public PercentFormatter() { this.mFormat = new DecimalFormat("###,###,##0.0"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public PercentFormatter(DecimalFormat format) { this.mFormat = format; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) { return this.mFormat.format(value) + " %"; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public String getFormattedValue(float value, AxisBase axis) { return this.mFormat.format(value) + " %"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   public int GetColor(float value) { return 0; }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public int getDecimalDigits() { return 1; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\formatter\PercentFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */