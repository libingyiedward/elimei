/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SuppressLint({"ParcelCreator"})
/*    */ public class RadarEntry
/*    */   extends Entry
/*    */ {
/* 12 */   public RadarEntry(float value) { super(0.0F, value); }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public RadarEntry(float value, Object data) { super(0.0F, value, data); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public float getValue() { return getY(); }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public RadarEntry copy() { return new RadarEntry(getY(), getData()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/* 36 */   public void setX(float x) { super.setX(x); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/* 42 */   public float getX() { return super.getX(); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\RadarEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */