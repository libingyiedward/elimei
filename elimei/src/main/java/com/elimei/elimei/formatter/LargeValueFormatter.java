/*     */ package com.elimei.elimei.formatter;
/*     */ 
/*     */ import com.elimei.elimei.components.AxisBase;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ import java.text.DecimalFormat;
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
/*     */ public class LargeValueFormatter
/*     */   implements IValueFormatter, IAxisValueFormatter
/*     */ {
/*  23 */   private static String[] SUFFIX = { "", "k", "m", "b", "t" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   private String mText = "";
/*     */ 
/*     */   
/*  31 */   private DecimalFormat mFormat = new DecimalFormat("###E00");
/*     */   
/*     */   private static final int MAX_LENGTH = 5;
/*     */ 
/*     */   
/*     */   public LargeValueFormatter() {}
/*     */ 
/*     */   
/*     */   public LargeValueFormatter(String appendix) {
/*  40 */     this();
/*  41 */     this.mText = appendix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) { return makePretty(value) + this.mText; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public String getFormattedValue(float value, AxisBase axis) { return makePretty(value) + this.mText; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public int GetColor(float value) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void setAppendix(String appendix) { this.mText = appendix; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public void setSuffix(String[] suff) { SUFFIX = suff; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String makePretty(double number) {
/*  91 */     String r = this.mFormat.format(number);
/*     */     
/*  93 */     int numericValue1 = Character.getNumericValue(r.charAt(r.length() - 1));
/*  94 */     int numericValue2 = Character.getNumericValue(r.charAt(r.length() - 2));
/*  95 */     int combined = Integer.valueOf(numericValue2 + "" + numericValue1).intValue();
/*     */     
/*  97 */     r = r.replaceAll("E[0-9][0-9]", SUFFIX[combined / 3]);
/*     */     
/*  99 */     while (r.length() > 5 || r.matches("[0-9]+\\.[a-z]")) {
/* 100 */       r = r.substring(0, r.length() - 2) + r.substring(r.length() - 1);
/*     */     }
/*     */     
/* 103 */     return r;
/*     */   }
/*     */ 
/*     */   
/* 107 */   public int getDecimalDigits() { return 0; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\formatter\LargeValueFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */