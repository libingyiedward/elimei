/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import android.graphics.drawable.Drawable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BaseEntry
/*    */ {
/* 11 */   private float y = 0.0F;
/*    */ 
/*    */   
/* 14 */   private Object mData = null;
/*    */ 
/*    */   
/* 17 */   private Drawable mIcon = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public BaseEntry() {}
/*    */ 
/*    */   
/* 24 */   public BaseEntry(float y) { this.y = y; }
/*    */ 
/*    */   
/*    */   public BaseEntry(float y, Object data) {
/* 28 */     this(y);
/* 29 */     this.mData = data;
/*    */   }
/*    */   
/*    */   public BaseEntry(float y, Drawable icon) {
/* 33 */     this(y);
/* 34 */     this.mIcon = icon;
/*    */   }
/*    */   
/*    */   public BaseEntry(float y, Drawable icon, Object data) {
/* 38 */     this(y);
/* 39 */     this.mIcon = icon;
/* 40 */     this.mData = data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public float getY() { return this.y; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void setIcon(Drawable icon) { this.mIcon = icon; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public Drawable getIcon() { return this.mIcon; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   public void setY(float y) { this.y = y; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 86 */   public Object getData() { return this.mData; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 95 */   public void setData(Object data) { this.mData = data; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BaseEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */