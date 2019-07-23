/*    */ package com.elimei.elimei.buffer;
/*    */ 
/*    */ public abstract class AbstractBuffer<T>
/*    */   extends Object
/*    */ {
/*    */   protected int index;
/*    */   public final float[] buffer;
/*    */   protected float phaseX;
/*    */   protected float phaseY;
/*    */   protected int mFrom;
/*    */   protected int mTo;
/*    */   
/*    */   public AbstractBuffer(int size) {
/* 14 */     this.index = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.phaseX = 1.0F;
/*    */ 
/*    */     
/* 23 */     this.phaseY = 1.0F;
/*    */ 
/*    */     
/* 26 */     this.mFrom = 0;
/*    */ 
/*    */     
/* 29 */     this.mTo = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     this.index = 0;
/* 38 */     this.buffer = new float[size];
/*    */   }
/*    */ 
/*    */   
/*    */   public void limitFrom(int from) {
/* 43 */     if (from < 0)
/* 44 */       from = 0; 
/* 45 */     this.mFrom = from;
/*    */   }
/*    */ 
/*    */   
/*    */   public void limitTo(int to) {
/* 50 */     if (to < 0)
/* 51 */       to = 0; 
/* 52 */     this.mTo = to;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public void reset() { this.index = 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 68 */   public int size() { return this.buffer.length; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPhases(float phaseX, float phaseY) {
/* 78 */     this.phaseX = phaseX;
/* 79 */     this.phaseY = phaseY;
/*    */   }
/*    */   
/*    */   public abstract void feed(T paramT);
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\buffer\AbstractBuffer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */