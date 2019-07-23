/*    */ package com.elimei.elimei.utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TransformerHorizontalBarChart
/*    */   extends Transformer
/*    */ {
/* 12 */   public TransformerHorizontalBarChart(ViewPortHandler viewPortHandler) { super(viewPortHandler); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void prepareMatrixOffset(boolean inverted) {
/* 22 */     this.mMatrixOffset.reset();
/*    */ 
/*    */ 
/*    */     
/* 26 */     if (!inverted) {
/* 27 */       this.mMatrixOffset.postTranslate(this.mViewPortHandler.offsetLeft(), this.mViewPortHandler
/* 28 */           .getChartHeight() - this.mViewPortHandler.offsetBottom());
/*    */     } else {
/* 30 */       this.mMatrixOffset
/* 31 */         .setTranslate(
/* 32 */           -(this.mViewPortHandler.getChartWidth() - this.mViewPortHandler.offsetRight()), this.mViewPortHandler
/* 33 */           .getChartHeight() - this.mViewPortHandler.offsetBottom());
/* 34 */       this.mMatrixOffset.postScale(-1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\TransformerHorizontalBarChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */