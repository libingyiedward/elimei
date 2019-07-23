/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.graphics.drawable.Drawable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SuppressLint({"ParcelCreator"})
/*     */ public class CandleEntry
/*     */   extends Entry
/*     */ {
/*  16 */   private float mShadowHigh = 0.0F;
/*     */ 
/*     */   
/*  19 */   private float mShadowLow = 0.0F;
/*     */ 
/*     */   
/*  22 */   private float mClose = 0.0F;
/*     */ 
/*     */   
/*  25 */   private float mOpen = 0.0F;
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
/*     */   public CandleEntry(float x, float shadowH, float shadowL, float open, float close) {
/*  37 */     super(x, (shadowH + shadowL) / 2.0F);
/*     */     
/*  39 */     this.mShadowHigh = shadowH;
/*  40 */     this.mShadowLow = shadowL;
/*  41 */     this.mOpen = open;
/*  42 */     this.mClose = close;
/*     */   }
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
/*     */   public CandleEntry(float x, float shadowH, float shadowL, float open, float close, Object data) {
/*  57 */     super(x, (shadowH + shadowL) / 2.0F, data);
/*     */     
/*  59 */     this.mShadowHigh = shadowH;
/*  60 */     this.mShadowLow = shadowL;
/*  61 */     this.mOpen = open;
/*  62 */     this.mClose = close;
/*     */   }
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
/*     */   public CandleEntry(float x, float shadowH, float shadowL, float open, float close, Drawable icon) {
/*  77 */     super(x, (shadowH + shadowL) / 2.0F, icon);
/*     */     
/*  79 */     this.mShadowHigh = shadowH;
/*  80 */     this.mShadowLow = shadowL;
/*  81 */     this.mOpen = open;
/*  82 */     this.mClose = close;
/*     */   }
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
/*     */   public CandleEntry(float x, float shadowH, float shadowL, float open, float close, Drawable icon, Object data) {
/*  98 */     super(x, (shadowH + shadowL) / 2.0F, icon, data);
/*     */     
/* 100 */     this.mShadowHigh = shadowH;
/* 101 */     this.mShadowLow = shadowL;
/* 102 */     this.mOpen = open;
/* 103 */     this.mClose = close;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public float getShadowRange() { return Math.abs(this.mShadowHigh - this.mShadowLow); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public float getBodyRange() { return Math.abs(this.mOpen - this.mClose); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public float getY() { return super.getY(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public CandleEntry copy() {
/* 136 */     return new CandleEntry(getX(), this.mShadowHigh, this.mShadowLow, this.mOpen, this.mClose, 
/* 137 */         getData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public float getHigh() { return this.mShadowHigh; }
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void setHigh(float mShadowHigh) { this.mShadowHigh = mShadowHigh; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public float getLow() { return this.mShadowLow; }
/*     */ 
/*     */ 
/*     */   
/* 165 */   public void setLow(float mShadowLow) { this.mShadowLow = mShadowLow; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public float getClose() { return this.mClose; }
/*     */ 
/*     */ 
/*     */   
/* 178 */   public void setClose(float mClose) { this.mClose = mClose; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   public float getOpen() { return this.mOpen; }
/*     */ 
/*     */ 
/*     */   
/* 191 */   public void setOpen(float mOpen) { this.mOpen = mOpen; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\CandleEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */