/*    */ package com.elimei.elimei.formatter;
/*    */ 
/*    */ import com.elimei.elimei.data.BarEntry;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StackedValueFormatter
/*    */   implements IValueFormatter
/*    */ {
/*    */   private boolean mDrawWholeStack;
/*    */   private String mAppendix;
/*    */   private DecimalFormat mFormat;
/*    */   
/*    */   public StackedValueFormatter(boolean drawWholeStack, String appendix, int decimals) {
/* 38 */     this.mDrawWholeStack = drawWholeStack;
/* 39 */     this.mAppendix = appendix;
/*    */     
/* 41 */     StringBuffer b = new StringBuffer();
/* 42 */     for (int i = 0; i < decimals; i++) {
/* 43 */       if (i == 0)
/* 44 */         b.append("."); 
/* 45 */       b.append("0");
/*    */     } 
/*    */     
/* 48 */     this.mFormat = new DecimalFormat("###,###,###,##0" + b.toString());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
/* 54 */     if (!this.mDrawWholeStack && entry instanceof BarEntry) {
/*    */       
/* 56 */       BarEntry barEntry = (BarEntry)entry;
/* 57 */       float[] vals = barEntry.getYVals();
/*    */       
/* 59 */       if (vals != null) {
/*    */ 
/*    */         
/* 62 */         if (vals[vals.length - 1] == value)
/*    */         {
/*    */           
/* 65 */           return this.mFormat.format(barEntry.getY()) + this.mAppendix;
/*    */         }
/* 67 */         return "";
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 73 */     return this.mFormat.format(value) + this.mAppendix;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\formatter\StackedValueFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */