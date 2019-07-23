/*    */ package com.elimei.elimei.buffer;
/*    */ 
/*    */ import com.elimei.elimei.data.BarEntry;
/*    */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HorizontalBarBuffer
/*    */   extends BarBuffer
/*    */ {
/* 11 */   public HorizontalBarBuffer(int size, int dataSetCount, boolean containsStacks) { super(size, dataSetCount, containsStacks); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void feed(IBarDataSet data) {
/* 17 */     float size = data.getEntryCount() * this.phaseX;
/* 18 */     float barWidthHalf = this.mBarWidth / 2.0F;
/*    */     
/* 20 */     for (int i = 0; i < size; i++) {
/*    */       
/* 22 */       BarEntry e = (BarEntry)data.getEntryForIndex(i);
/*    */       
/* 24 */       if (e != null) {
/*    */ 
/*    */         
/* 27 */         float x = e.getX();
/* 28 */         float y = e.getY();
/* 29 */         float[] vals = e.getYVals();
/*    */         
/* 31 */         if (!this.mContainsStacks || vals == null) {
/*    */           
/* 33 */           float right, left, bottom = x - barWidthHalf;
/* 34 */           float top = x + barWidthHalf;
/*    */           
/* 36 */           if (this.mInverted) {
/* 37 */             left = (y >= 0.0F) ? y : 0.0F;
/* 38 */             right = (y <= 0.0F) ? y : 0.0F;
/*    */           } else {
/* 40 */             right = (y >= 0.0F) ? y : 0.0F;
/* 41 */             left = (y <= 0.0F) ? y : 0.0F;
/*    */           } 
/*    */ 
/*    */           
/* 45 */           if (right > 0.0F) {
/* 46 */             right *= this.phaseY;
/*    */           } else {
/* 48 */             left *= this.phaseY;
/*    */           } 
/* 50 */           addBar(left, top, right, bottom);
/*    */         }
/*    */         else {
/*    */           
/* 54 */           float posY = 0.0F;
/* 55 */           float negY = -e.getNegativeSum();
/* 56 */           float yStart = 0.0F;
/*    */ 
/*    */           
/* 59 */           for (int k = 0; k < vals.length; k++) {
/*    */             
/* 61 */             float right, left, value = vals[k];
/*    */             
/* 63 */             if (value >= 0.0F) {
/* 64 */               y = posY;
/* 65 */               yStart = posY + value;
/* 66 */               posY = yStart;
/*    */             } else {
/* 68 */               y = negY;
/* 69 */               yStart = negY + Math.abs(value);
/* 70 */               negY += Math.abs(value);
/*    */             } 
/*    */             
/* 73 */             float bottom = x - barWidthHalf;
/* 74 */             float top = x + barWidthHalf;
/*    */             
/* 76 */             if (this.mInverted) {
/* 77 */               left = (y >= yStart) ? y : yStart;
/* 78 */               right = (y <= yStart) ? y : yStart;
/*    */             } else {
/* 80 */               right = (y >= yStart) ? y : yStart;
/* 81 */               left = (y <= yStart) ? y : yStart;
/*    */             } 
/*    */ 
/*    */             
/* 85 */             right *= this.phaseY;
/* 86 */             left *= this.phaseY;
/*    */             
/* 88 */             addBar(left, top, right, bottom);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 93 */     reset();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\buffer\HorizontalBarBuffer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */