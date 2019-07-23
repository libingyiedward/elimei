/*    */ package com.elimei.elimei.highlight;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Range
/*    */ {
/*    */   public float from;
/*    */   public float to;
/*    */   
/*    */   public Range(float from, float to) {
/* 13 */     this.from = from;
/* 14 */     this.to = to;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean contains(float value) {
/* 25 */     if (value > this.from && value <= this.to) {
/* 26 */       return true;
/*    */     }
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   
/* 32 */   public boolean isLarger(float value) { return (value > this.to); }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public boolean isSmaller(float value) { return (value < this.from); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\highlight\Range.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */