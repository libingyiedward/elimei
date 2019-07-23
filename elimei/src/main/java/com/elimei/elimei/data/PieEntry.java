/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.graphics.drawable.Drawable;
/*    */ import android.util.Log;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SuppressLint({"ParcelCreator"})
/*    */ public class PieEntry
/*    */   extends Entry
/*    */ {
/*    */   private String label;
/*    */   
/* 16 */   public PieEntry(float value) { super(0.0F, value); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public PieEntry(float value, Object data) { super(0.0F, value, data); }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public PieEntry(float value, Drawable icon) { super(0.0F, value, icon); }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public PieEntry(float value, Drawable icon, Object data) { super(0.0F, value, icon, data); }
/*    */ 
/*    */   
/*    */   public PieEntry(float value, String label) {
/* 32 */     super(0.0F, value);
/* 33 */     this.label = label;
/*    */   }
/*    */   
/*    */   public PieEntry(float value, String label, Object data) {
/* 37 */     super(0.0F, value, data);
/* 38 */     this.label = label;
/*    */   }
/*    */   
/*    */   public PieEntry(float value, String label, Drawable icon) {
/* 42 */     super(0.0F, value, icon);
/* 43 */     this.label = label;
/*    */   }
/*    */   
/*    */   public PieEntry(float value, String label, Drawable icon, Object data) {
/* 47 */     super(0.0F, value, icon, data);
/* 48 */     this.label = label;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public float getValue() { return getY(); }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public String getLabel() { return this.label; }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void setLabel(String label) { this.label = label; }
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void setX(float x) {
/* 71 */     super.setX(x);
/* 72 */     Log.i("DEPRECATED", "Pie entries do not have x values");
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public float getX() {
/* 78 */     Log.i("DEPRECATED", "Pie entries do not have x values");
/* 79 */     return super.getX();
/*    */   }
/*    */ 
/*    */   
/* 83 */   public PieEntry copy() { return new PieEntry(getY(), this.label, getData()); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\PieEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */