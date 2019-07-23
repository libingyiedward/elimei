/*    */ package com.elimei.elimei.formatter;
/*    */ 
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
/*    */ public class DefaultValueFormatter
/*    */   implements IValueFormatter
/*    */ {
/*    */   protected DecimalFormat mFormat;
/*    */   protected int mDecimalDigits;
/*    */   
/* 32 */   public DefaultValueFormatter(int digits) { setup(digits); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setup(int digits) {
/* 42 */     this.mDecimalDigits = digits;
/*    */     
/* 44 */     StringBuffer b = new StringBuffer();
/* 45 */     for (int i = 0; i < digits; i++) {
/* 46 */       if (i == 0)
/* 47 */         b.append("."); 
/* 48 */       b.append("0");
/*    */     } 
/*    */     
/* 51 */     this.mFormat = new DecimalFormat("###,###,###,##0" + b.toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) { return this.mFormat.format(value); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public int getDecimalDigits() { return this.mDecimalDigits; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\formatter\DefaultValueFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */