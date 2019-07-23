/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import android.graphics.Paint;
/*     */ import com.elimei.elimei.interfaces.datasets.ICandleDataSet;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ public class CandleDataSet
/*     */   extends LineScatterCandleRadarDataSet<CandleEntry>
/*     */   implements ICandleDataSet
/*     */ {
/*  23 */   private float mShadowWidth = 3.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean mShowCandleBar = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   private float mBarSpace = 0.1F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean mShadowColorSameAsCandle = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   protected Paint.Style mIncreasingPaintStyle = Paint.Style.STROKE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   protected Paint.Style mDecreasingPaintStyle = Paint.Style.FILL;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected int mNeutralColor = 1122868;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected int mIncreasingColor = 1122868;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   protected int mDecreasingColor = 1122868;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   protected int mShadowColor = 1122868;
/*     */ 
/*     */   
/*  77 */   public CandleDataSet(List<CandleEntry> yVals, String label) { super(yVals, label); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSet<CandleEntry> copy() {
/*  83 */     List<CandleEntry> yVals = new ArrayList<CandleEntry>();
/*  84 */     yVals.clear();
/*     */     
/*  86 */     for (int i = 0; i < this.mValues.size(); i++) {
/*  87 */       yVals.add(((CandleEntry)this.mValues.get(i)).copy());
/*     */     }
/*     */     
/*  90 */     CandleDataSet copied = new CandleDataSet(yVals, getLabel());
/*  91 */     copied.mColors = this.mColors;
/*  92 */     copied.mShadowWidth = this.mShadowWidth;
/*  93 */     copied.mShowCandleBar = this.mShowCandleBar;
/*  94 */     copied.mBarSpace = this.mBarSpace;
/*  95 */     copied.mHighLightColor = this.mHighLightColor;
/*  96 */     copied.mIncreasingPaintStyle = this.mIncreasingPaintStyle;
/*  97 */     copied.mDecreasingPaintStyle = this.mDecreasingPaintStyle;
/*  98 */     copied.mShadowColor = this.mShadowColor;
/*     */     
/* 100 */     return copied;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void calcMinMax(CandleEntry e) {
/* 106 */     if (e.getLow() < this.mYMin) {
/* 107 */       this.mYMin = e.getLow();
/*     */     }
/* 109 */     if (e.getHigh() > this.mYMax) {
/* 110 */       this.mYMax = e.getHigh();
/*     */     }
/* 112 */     calcMinMaxX(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void calcMinMaxY(CandleEntry e) {
/* 118 */     if (e.getHigh() < this.mYMin) {
/* 119 */       this.mYMin = e.getHigh();
/*     */     }
/* 121 */     if (e.getHigh() > this.mYMax) {
/* 122 */       this.mYMax = e.getHigh();
/*     */     }
/* 124 */     if (e.getLow() < this.mYMin) {
/* 125 */       this.mYMin = e.getLow();
/*     */     }
/* 127 */     if (e.getLow() > this.mYMax) {
/* 128 */       this.mYMax = e.getLow();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBarSpace(float space) {
/* 139 */     if (space < 0.0F)
/* 140 */       space = 0.0F; 
/* 141 */     if (space > 0.45F) {
/* 142 */       space = 0.45F;
/*     */     }
/* 144 */     this.mBarSpace = space;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 149 */   public float getBarSpace() { return this.mBarSpace; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public void setShadowWidth(float width) { this.mShadowWidth = Utils.convertDpToPixel(width); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public float getShadowWidth() { return this.mShadowWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setShowCandleBar(boolean showCandleBar) { this.mShowCandleBar = showCandleBar; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public boolean getShowCandleBar() { return this.mShowCandleBar; }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public void setNeutralColor(int color) { this.mNeutralColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public int getNeutralColor() { return this.mNeutralColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public void setIncreasingColor(int color) { this.mIncreasingColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public int getIncreasingColor() { return this.mIncreasingColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public void setDecreasingColor(int color) { this.mDecreasingColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public int getDecreasingColor() { return this.mDecreasingColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   public Paint.Style getIncreasingPaintStyle() { return this.mIncreasingPaintStyle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   public void setIncreasingPaintStyle(Paint.Style paintStyle) { this.mIncreasingPaintStyle = paintStyle; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public Paint.Style getDecreasingPaintStyle() { return this.mDecreasingPaintStyle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public void setDecreasingPaintStyle(Paint.Style decreasingPaintStyle) { this.mDecreasingPaintStyle = decreasingPaintStyle; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public int getShadowColor() { return this.mShadowColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public void setShadowColor(int shadowColor) { this.mShadowColor = shadowColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   public boolean getShadowColorSameAsCandle() { return this.mShadowColorSameAsCandle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 290 */   public void setShadowColorSameAsCandle(boolean shadowColorSameAsCandle) { this.mShadowColorSameAsCandle = shadowColorSameAsCandle; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\CandleDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */