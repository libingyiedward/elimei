/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.graphics.drawable.Drawable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SuppressLint({"ParcelCreator"})
/*    */ public class BubbleEntry
/*    */   extends Entry
/*    */ {
/* 18 */   private float mSize = 0.0F;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BubbleEntry(float x, float y, float size) {
/* 28 */     super(x, y);
/* 29 */     this.mSize = size;
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
/*    */   public BubbleEntry(float x, float y, float size, Object data) {
/* 41 */     super(x, y, data);
/* 42 */     this.mSize = size;
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
/*    */   public BubbleEntry(float x, float y, float size, Drawable icon) {
/* 54 */     super(x, y, icon);
/* 55 */     this.mSize = size;
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
/*    */   public BubbleEntry(float x, float y, float size, Drawable icon, Object data) {
/* 68 */     super(x, y, icon, data);
/* 69 */     this.mSize = size;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 74 */   public BubbleEntry copy() { return new BubbleEntry(getX(), getY(), this.mSize, getData()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   public float getSize() { return this.mSize; }
/*    */ 
/*    */ 
/*    */   
/* 88 */   public void setSize(float size) { this.mSize = size; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BubbleEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */