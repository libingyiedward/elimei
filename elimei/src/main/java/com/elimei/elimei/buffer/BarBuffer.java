/*     */ package com.elimei.elimei.buffer;
/*     */ 
/*     */ import com.elimei.elimei.data.BarEntry;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
/*     */ 
/*     */ 
/*     */ public class BarBuffer
/*     */   extends AbstractBuffer<IBarDataSet>
/*     */ {
/*  10 */   protected int mDataSetIndex = 0;
/*  11 */   protected int mDataSetCount = 1;
/*     */   
/*     */   protected boolean mContainsStacks = false;
/*     */   
/*     */   protected boolean mInverted = false;
/*  16 */   protected float mBarWidth = 1.0F;
/*     */   
/*     */   public BarBuffer(int size, int dataSetCount, boolean containsStacks) {
/*  19 */     super(size);
/*  20 */     this.mDataSetCount = dataSetCount;
/*  21 */     this.mContainsStacks = containsStacks;
/*     */   }
/*     */ 
/*     */   
/*  25 */   public void setBarWidth(float barWidth) { this.mBarWidth = barWidth; }
/*     */ 
/*     */ 
/*     */   
/*  29 */   public void setDataSet(int index) { this.mDataSetIndex = index; }
/*     */ 
/*     */ 
/*     */   
/*  33 */   public void setInverted(boolean inverted) { this.mInverted = inverted; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addBar(float left, float top, float right, float bottom) {
/*  38 */     this.buffer[this.index++] = left;
/*  39 */     this.buffer[this.index++] = top;
/*  40 */     this.buffer[this.index++] = right;
/*  41 */     this.buffer[this.index++] = bottom;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void feed(IBarDataSet data) {
/*  47 */     float size = data.getEntryCount() * this.phaseX;
/*  48 */     float barWidthHalf = this.mBarWidth / 2.0F;
/*     */     
/*  50 */     for (int i = 0; i < size; i++) {
/*     */       
/*  52 */       BarEntry e = (BarEntry)data.getEntryForIndex(i);
/*     */       
/*  54 */       if (e != null) {
/*     */ 
/*     */         
/*  57 */         float x = e.getX();
/*  58 */         float y = e.getY();
/*  59 */         float[] vals = e.getYVals();
/*     */         
/*  61 */         if (!this.mContainsStacks || vals == null) {
/*     */           
/*  63 */           float top, bottom, left = x - barWidthHalf;
/*  64 */           float right = x + barWidthHalf;
/*     */ 
/*     */           
/*  67 */           if (this.mInverted) {
/*  68 */             bottom = (y >= 0.0F) ? y : 0.0F;
/*  69 */             top = (y <= 0.0F) ? y : 0.0F;
/*     */           } else {
/*  71 */             top = (y >= 0.0F) ? y : 0.0F;
/*  72 */             bottom = (y <= 0.0F) ? y : 0.0F;
/*     */           } 
/*     */ 
/*     */           
/*  76 */           if (top > 0.0F) {
/*  77 */             top *= this.phaseY;
/*     */           } else {
/*  79 */             bottom *= this.phaseY;
/*     */           } 
/*  81 */           addBar(left, top, right, bottom);
/*     */         }
/*     */         else {
/*     */           
/*  85 */           float posY = 0.0F;
/*  86 */           float negY = -e.getNegativeSum();
/*  87 */           float yStart = 0.0F;
/*     */ 
/*     */           
/*  90 */           for (int k = 0; k < vals.length; k++) {
/*     */             
/*  92 */             float top, bottom, value = vals[k];
/*     */             
/*  94 */             if (value == 0.0F && (posY == 0.0F || negY == 0.0F)) {
/*     */               
/*  96 */               y = value;
/*  97 */               yStart = y;
/*  98 */             } else if (value >= 0.0F) {
/*  99 */               y = posY;
/* 100 */               yStart = posY + value;
/* 101 */               posY = yStart;
/*     */             } else {
/* 103 */               y = negY;
/* 104 */               yStart = negY + Math.abs(value);
/* 105 */               negY += Math.abs(value);
/*     */             } 
/*     */             
/* 108 */             float left = x - barWidthHalf;
/* 109 */             float right = x + barWidthHalf;
/*     */ 
/*     */             
/* 112 */             if (this.mInverted) {
/* 113 */               bottom = (y >= yStart) ? y : yStart;
/* 114 */               top = (y <= yStart) ? y : yStart;
/*     */             } else {
/* 116 */               top = (y >= yStart) ? y : yStart;
/* 117 */               bottom = (y <= yStart) ? y : yStart;
/*     */             } 
/*     */ 
/*     */             
/* 121 */             top *= this.phaseY;
/* 122 */             bottom *= this.phaseY;
/*     */             
/* 124 */             addBar(left, top, right, bottom);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 129 */     reset();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\buffer\BarBuffer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */