/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import com.elimei.elimei.interfaces.datasets.IRadarDataSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RadarDataSet
/*     */   extends LineRadarDataSet<RadarEntry>
/*     */   implements IRadarDataSet
/*     */ {
/*     */   protected boolean mDrawHighlightCircleEnabled = false;
/*  17 */   protected int mHighlightCircleFillColor = -1;
/*     */ 
/*     */ 
/*     */   
/*  21 */   protected int mHighlightCircleStrokeColor = 1122867;
/*     */   
/*  23 */   protected int mHighlightCircleStrokeAlpha = 76;
/*  24 */   protected float mHighlightCircleInnerRadius = 3.0F;
/*  25 */   protected float mHighlightCircleOuterRadius = 4.0F;
/*  26 */   protected float mHighlightCircleStrokeWidth = 2.0F;
/*     */ 
/*     */   
/*  29 */   public RadarDataSet(List<RadarEntry> yVals, String label) { super(yVals, label); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public boolean isDrawHighlightCircleEnabled() { return this.mDrawHighlightCircleEnabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public void setDrawHighlightCircleEnabled(boolean enabled) { this.mDrawHighlightCircleEnabled = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public int getHighlightCircleFillColor() { return this.mHighlightCircleFillColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public void setHighlightCircleFillColor(int color) { this.mHighlightCircleFillColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public int getHighlightCircleStrokeColor() { return this.mHighlightCircleStrokeColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public void setHighlightCircleStrokeColor(int color) { this.mHighlightCircleStrokeColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public int getHighlightCircleStrokeAlpha() { return this.mHighlightCircleStrokeAlpha; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public void setHighlightCircleStrokeAlpha(int alpha) { this.mHighlightCircleStrokeAlpha = alpha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public float getHighlightCircleInnerRadius() { return this.mHighlightCircleInnerRadius; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setHighlightCircleInnerRadius(float radius) { this.mHighlightCircleInnerRadius = radius; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public float getHighlightCircleOuterRadius() { return this.mHighlightCircleOuterRadius; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public void setHighlightCircleOuterRadius(float radius) { this.mHighlightCircleOuterRadius = radius; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public float getHighlightCircleStrokeWidth() { return this.mHighlightCircleStrokeWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setHighlightCircleStrokeWidth(float strokeWidth) { this.mHighlightCircleStrokeWidth = strokeWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSet<RadarEntry> copy() {
/* 119 */     List<RadarEntry> yVals = new ArrayList<RadarEntry>();
/*     */     
/* 121 */     for (int i = 0; i < this.mValues.size(); i++) {
/* 122 */       yVals.add(((RadarEntry)this.mValues.get(i)).copy());
/*     */     }
/*     */     
/* 125 */     RadarDataSet copied = new RadarDataSet(yVals, getLabel());
/* 126 */     copied.mColors = this.mColors;
/* 127 */     copied.mHighLightColor = this.mHighLightColor;
/*     */     
/* 129 */     return copied;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\RadarDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */