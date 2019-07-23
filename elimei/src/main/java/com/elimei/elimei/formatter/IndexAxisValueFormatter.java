/*    */ package com.elimei.elimei.formatter;
/*    */ 
/*    */ import com.elimei.elimei.components.AxisBase;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IndexAxisValueFormatter
/*    */   implements IAxisValueFormatter
/*    */ {
/* 13 */   private String[] mValues = new String[0];
/* 14 */   private int mValueCount = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IndexAxisValueFormatter() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IndexAxisValueFormatter(String[] values) {
/* 29 */     if (values != null) {
/* 30 */       setValues(values);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IndexAxisValueFormatter(Collection<String> values) {
/* 39 */     if (values != null)
/* 40 */       setValues((String[])values.toArray(new String[values.size()])); 
/*    */   }
/*    */   
/*    */   public String getFormattedValue(float value, AxisBase axis) {
/* 44 */     int index = Math.round(value);
/*    */     
/* 46 */     if (index < 0 || index >= this.mValueCount || index != (int)value) {
/* 47 */       return "";
/*    */     }
/* 49 */     return this.mValues[index];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public int GetColor(float value) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public String[] getValues() { return this.mValues; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValues(String[] values) {
/* 69 */     if (values == null) {
/* 70 */       values = new String[0];
/*    */     }
/* 72 */     this.mValues = values;
/* 73 */     this.mValueCount = values.length;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\formatter\IndexAxisValueFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */