/*    */ package com.elimei.elimei.components;
/*    */ 
/*    */ import android.graphics.Paint;
/*    */ import com.elimei.elimei.utils.MPPointF;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Description
/*    */   extends ComponentBase
/*    */ {
/* 16 */   private String text = "Description Label";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private MPPointF mPosition;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   private Paint.Align mTextAlign = Paint.Align.RIGHT;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public void setText(String text) { this.text = text; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public String getText() { return this.text; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPosition(float x, float y) {
/* 61 */     if (this.mPosition == null) {
/* 62 */       this.mPosition = MPPointF.getInstance(x, y);
/*    */     } else {
/* 64 */       this.mPosition.x = x;
/* 65 */       this.mPosition.y = y;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   public MPPointF getPosition() { return this.mPosition; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   public void setTextAlign(Paint.Align align) { this.mTextAlign = align; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 93 */   public Paint.Align getTextAlign() { return this.mTextAlign; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\Description.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */