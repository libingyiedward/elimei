/*     */ package com.elimei.elimei.components;
/*     */ 
/*     */ import android.graphics.Typeface;
/*     */ import com.elimei.elimei.utils.Utils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ComponentBase
/*     */ {
/*     */   protected boolean mEnabled = true;
/*  24 */   protected float mXOffset = 5.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  29 */   protected float mYOffset = 5.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   protected Typeface mTypeface = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   protected float mTextSize = Utils.convertDpToPixel(10.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   protected int mTextColor = -16777216;
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
/*  58 */   public float getXOffset() { return this.mXOffset; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public void setXOffset(float xOffset) { this.mXOffset = Utils.convertDpToPixel(xOffset); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public float getYOffset() { return this.mYOffset; }
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
/*  88 */   public void setYOffset(float yOffset) { this.mYOffset = Utils.convertDpToPixel(yOffset); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public Typeface getTypeface() { return this.mTypeface; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setTypeface(Typeface tf) { this.mTypeface = tf; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextSize(float size) {
/* 117 */     if (size > 24.0F)
/* 118 */       size = 24.0F; 
/* 119 */     if (size < 6.0F) {
/* 120 */       size = 6.0F;
/*     */     }
/* 122 */     this.mTextSize = Utils.convertDpToPixel(size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public float getTextSize() { return this.mTextSize; }
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
/* 142 */   public void setTextColor(int color) { this.mTextColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public int getTextColor() { return this.mTextColor; }
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
/* 162 */   public void setEnabled(boolean enabled) { this.mEnabled = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public boolean isEnabled() { return this.mEnabled; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\ComponentBase.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */