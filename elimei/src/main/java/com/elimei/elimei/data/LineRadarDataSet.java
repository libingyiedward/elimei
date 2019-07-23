/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import android.annotation.TargetApi;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import com.elimei.elimei.interfaces.datasets.ILineRadarDataSet;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import java.util.List;
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
/*     */ public abstract class LineRadarDataSet<T extends Entry>
/*     */   extends LineScatterCandleRadarDataSet<T>
/*     */   implements ILineRadarDataSet<T>
/*     */ {
/*  23 */   private int mFillColor = Color.rgb(140, 234, 255);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Drawable mFillDrawable;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   private int mFillAlpha = 85;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   private float mLineWidth = 2.5F;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean mDrawFilled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public LineRadarDataSet(List<T> yVals, String label) { super(yVals, label); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public int getFillColor() { return this.mFillColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFillColor(int color) {
/*  62 */     this.mFillColor = color;
/*  63 */     this.mFillDrawable = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public Drawable getFillDrawable() { return this.mFillDrawable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @TargetApi(18)
/*  78 */   public void setFillDrawable(Drawable drawable) { this.mFillDrawable = drawable; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public int getFillAlpha() { return this.mFillAlpha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void setFillAlpha(int alpha) { this.mFillAlpha = alpha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineWidth(float width) {
/* 104 */     if (width < 0.0F)
/* 105 */       width = 0.0F; 
/* 106 */     if (width > 10.0F)
/* 107 */       width = 10.0F; 
/* 108 */     this.mLineWidth = Utils.convertDpToPixel(width);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public float getLineWidth() { return this.mLineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public void setDrawFilled(boolean filled) { this.mDrawFilled = filled; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public boolean isDrawFilledEnabled() { return this.mDrawFilled; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\LineRadarDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */