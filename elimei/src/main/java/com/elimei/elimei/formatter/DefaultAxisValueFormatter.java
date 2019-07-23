/*    */ package com.elimei.elimei.formatter;
/*    */ 
/*    */ import com.elimei.elimei.components.AxisBase;
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
/*    */ public class DefaultAxisValueFormatter
/*    */   implements IAxisValueFormatter
/*    */ {
/*    */   protected DecimalFormat mFormat;
/*    */   protected int digits;
/*    */   
/*    */   public DefaultAxisValueFormatter(int digits) {
/* 21 */     this.digits = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     this.digits = digits;
/*    */     
/* 32 */     StringBuffer b = new StringBuffer();
/* 33 */     for (int i = 0; i < digits; i++) {
/* 34 */       if (i == 0)
/* 35 */         b.append("."); 
/* 36 */       b.append("0");
/*    */     } 
/*    */     
/* 39 */     this.mFormat = new DecimalFormat("###,###,###,##0" + b.toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String getFormattedValue(float value, AxisBase axis) { return this.mFormat.format(value); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public int GetColor(float value) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public int getDecimalDigits() { return this.digits; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\formatter\DefaultAxisValueFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */