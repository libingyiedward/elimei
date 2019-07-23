/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import com.elimei.elimei.highlight.Range;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SuppressLint({"ParcelCreator"})
/*     */ public class BarEntry
/*     */   extends Entry
/*     */ {
/*     */   private float[] mYVals;
/*     */   private Range[] mRanges;
/*     */   private float mNegativeSum;
/*     */   private float mPositiveSum;
/*     */   
/*  44 */   public BarEntry(float x, float y) { super(x, y); }
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
/*  55 */   public BarEntry(float x, float y, Object data) { super(x, y, data); }
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
/*  66 */   public BarEntry(float x, float y, Drawable icon) { super(x, y, icon); }
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
/*  78 */   public BarEntry(float x, float y, Drawable icon, Object data) { super(x, y, icon, data); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BarEntry(float x, float[] vals) {
/*  88 */     super(x, calcSum(vals));
/*     */     
/*  90 */     this.mYVals = vals;
/*  91 */     calcPosNegSum();
/*  92 */     calcRanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BarEntry(float x, float[] vals, Object data) {
/* 103 */     super(x, calcSum(vals), data);
/*     */     
/* 105 */     this.mYVals = vals;
/* 106 */     calcPosNegSum();
/* 107 */     calcRanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BarEntry(float x, float[] vals, Drawable icon) {
/* 118 */     super(x, calcSum(vals), icon);
/*     */     
/* 120 */     this.mYVals = vals;
/* 121 */     calcPosNegSum();
/* 122 */     calcRanges();
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
/*     */   public BarEntry(float x, float[] vals, Drawable icon, Object data) {
/* 134 */     super(x, calcSum(vals), icon, data);
/*     */     
/* 136 */     this.mYVals = vals;
/* 137 */     calcPosNegSum();
/* 138 */     calcRanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BarEntry copy() {
/* 146 */     BarEntry copied = new BarEntry(getX(), getY(), getData());
/* 147 */     copied.setVals(this.mYVals);
/* 148 */     return copied;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public float[] getYVals() { return this.mYVals; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVals(float[] vals) {
/* 167 */     setY(calcSum(vals));
/* 168 */     this.mYVals = vals;
/* 169 */     calcPosNegSum();
/* 170 */     calcRanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public float getY() { return super.getY(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public Range[] getRanges() { return this.mRanges; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public boolean isStacked() { return (this.mYVals != null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 206 */   public float getBelowSum(int stackIndex) { return getSumBelow(stackIndex); }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSumBelow(int stackIndex) {
/* 211 */     if (this.mYVals == null) {
/* 212 */       return 0.0F;
/*     */     }
/* 214 */     float remainder = 0.0F;
/* 215 */     int index = this.mYVals.length - 1;
/*     */     
/* 217 */     while (index > stackIndex && index >= 0) {
/* 218 */       remainder += this.mYVals[index];
/* 219 */       index--;
/*     */     } 
/*     */     
/* 222 */     return remainder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public float getPositiveSum() { return this.mPositiveSum; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public float getNegativeSum() { return this.mNegativeSum; }
/*     */ 
/*     */ 
/*     */   
/*     */   private void calcPosNegSum() {
/* 245 */     if (this.mYVals == null) {
/* 246 */       this.mNegativeSum = 0.0F;
/* 247 */       this.mPositiveSum = 0.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 251 */     float sumNeg = 0.0F;
/* 252 */     float sumPos = 0.0F;
/*     */     
/* 254 */     for (float f : this.mYVals) {
/* 255 */       if (f <= 0.0F) {
/* 256 */         sumNeg += Math.abs(f);
/*     */       } else {
/* 258 */         sumPos += f;
/*     */       } 
/*     */     } 
/* 261 */     this.mNegativeSum = sumNeg;
/* 262 */     this.mPositiveSum = sumPos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static float calcSum(float[] vals) {
/* 273 */     if (vals == null) {
/* 274 */       return 0.0F;
/*     */     }
/* 276 */     float sum = 0.0F;
/*     */     
/* 278 */     for (float f : vals) {
/* 279 */       sum += f;
/*     */     }
/* 281 */     return sum;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void calcRanges() {
/* 286 */     float[] values = getYVals();
/*     */     
/* 288 */     if (values == null || values.length == 0) {
/*     */       return;
/*     */     }
/* 291 */     this.mRanges = new Range[values.length];
/*     */     
/* 293 */     float negRemain = -getNegativeSum();
/* 294 */     float posRemain = 0.0F;
/*     */     
/* 296 */     for (int i = 0; i < this.mRanges.length; i++) {
/*     */       
/* 298 */       float value = values[i];
/*     */       
/* 300 */       if (value < 0.0F) {
/* 301 */         this.mRanges[i] = new Range(negRemain, negRemain - value);
/* 302 */         negRemain -= value;
/*     */       } else {
/* 304 */         this.mRanges[i] = new Range(posRemain, posRemain + value);
/* 305 */         posRemain += value;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BarEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */