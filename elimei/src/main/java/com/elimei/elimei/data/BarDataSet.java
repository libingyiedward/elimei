/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import android.graphics.Color;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarDataSet
/*     */   extends BarLineScatterCandleBubbleDataSet<BarEntry>
/*     */   implements IBarDataSet
/*     */ {
/*  17 */   private int mStackSize = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   private int mBarShadowColor = Color.rgb(215, 215, 215);
/*     */   
/*  24 */   private float mBarBorderWidth = 0.0F;
/*     */   
/*  26 */   private int mBarBorderColor = -16777216;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   private int mHighLightAlpha = 120;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   private int mEntryCountStacks = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   private String[] mStackLabels = { "Stack" };
/*     */ 
/*     */ 
/*     */   
/*     */   public BarDataSet(List<BarEntry> yVals, String label) {
/*  46 */     super(yVals, label);
/*     */     
/*  48 */     this.mHighLightColor = Color.rgb(0, 0, 0);
/*     */     
/*  50 */     calcStackSize(yVals);
/*  51 */     calcEntryCountIncludingStacks(yVals);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSet<BarEntry> copy() {
/*  57 */     List<BarEntry> yVals = new ArrayList<BarEntry>();
/*  58 */     yVals.clear();
/*     */     
/*  60 */     for (int i = 0; i < this.mValues.size(); i++) {
/*  61 */       yVals.add(((BarEntry)this.mValues.get(i)).copy());
/*     */     }
/*     */     
/*  64 */     BarDataSet copied = new BarDataSet(yVals, getLabel());
/*  65 */     copied.mColors = this.mColors;
/*  66 */     copied.mStackSize = this.mStackSize;
/*  67 */     copied.mBarShadowColor = this.mBarShadowColor;
/*  68 */     copied.mStackLabels = this.mStackLabels;
/*  69 */     copied.mHighLightColor = this.mHighLightColor;
/*  70 */     copied.mHighLightAlpha = this.mHighLightAlpha;
/*     */     
/*  72 */     return copied;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calcEntryCountIncludingStacks(List<BarEntry> yVals) {
/*  81 */     this.mEntryCountStacks = 0;
/*     */     
/*  83 */     for (int i = 0; i < yVals.size(); i++) {
/*     */       
/*  85 */       float[] vals = ((BarEntry)yVals.get(i)).getYVals();
/*     */       
/*  87 */       if (vals == null) {
/*  88 */         this.mEntryCountStacks++;
/*     */       } else {
/*  90 */         this.mEntryCountStacks += vals.length;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calcStackSize(List<BarEntry> yVals) {
/* 100 */     for (int i = 0; i < yVals.size(); i++) {
/*     */       
/* 102 */       float[] vals = ((BarEntry)yVals.get(i)).getYVals();
/*     */       
/* 104 */       if (vals != null && vals.length > this.mStackSize) {
/* 105 */         this.mStackSize = vals.length;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void calcMinMax(BarEntry e) {
/* 112 */     if (e != null && !Float.isNaN(e.getY())) {
/*     */       
/* 114 */       if (e.getYVals() == null) {
/*     */         
/* 116 */         if (e.getY() < this.mYMin) {
/* 117 */           this.mYMin = e.getY();
/*     */         }
/* 119 */         if (e.getY() > this.mYMax) {
/* 120 */           this.mYMax = e.getY();
/*     */         }
/*     */       } else {
/* 123 */         if (-e.getNegativeSum() < this.mYMin) {
/* 124 */           this.mYMin = -e.getNegativeSum();
/*     */         }
/* 126 */         if (e.getPositiveSum() > this.mYMax) {
/* 127 */           this.mYMax = e.getPositiveSum();
/*     */         }
/*     */       } 
/* 130 */       calcMinMaxX(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 136 */   public int getStackSize() { return this.mStackSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public boolean isStacked() { return (this.mStackSize > 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public int getEntryCountStacks() { return this.mEntryCountStacks; }
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
/* 162 */   public void setBarShadowColor(int color) { this.mBarShadowColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public int getBarShadowColor() { return this.mBarShadowColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public void setBarBorderWidth(float width) { this.mBarBorderWidth = width; }
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
/* 188 */   public float getBarBorderWidth() { return this.mBarBorderWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   public void setBarBorderColor(int color) { this.mBarBorderColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public int getBarBorderColor() { return this.mBarBorderColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public void setHighLightAlpha(int alpha) { this.mHighLightAlpha = alpha; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   public int getHighLightAlpha() { return this.mHighLightAlpha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public void setStackLabels(String[] labels) { this.mStackLabels = labels; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public String[] getStackLabels() { return this.mStackLabels; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BarDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */