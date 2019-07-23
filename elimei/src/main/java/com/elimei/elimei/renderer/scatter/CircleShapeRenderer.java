/*    */ package com.elimei.elimei.renderer.scatter;
/*    */ 
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.Paint;
/*    */ import com.elimei.elimei.interfaces.datasets.IScatterDataSet;
/*    */ import com.elimei.elimei.utils.Utils;
/*    */ import com.elimei.elimei.utils.ViewPortHandler;
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
/*    */ public class CircleShapeRenderer
/*    */   implements IShapeRenderer
/*    */ {
/*    */   public void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler, float posX, float posY, Paint renderPaint) {
/* 22 */     float shapeSize = dataSet.getScatterShapeSize();
/* 23 */     float shapeHalf = shapeSize / 2.0F;
/* 24 */     float shapeHoleSizeHalf = Utils.convertDpToPixel(dataSet.getScatterShapeHoleRadius());
/* 25 */     float shapeHoleSize = shapeHoleSizeHalf * 2.0F;
/* 26 */     float shapeStrokeSize = (shapeSize - shapeHoleSize) / 2.0F;
/* 27 */     float shapeStrokeSizeHalf = shapeStrokeSize / 2.0F;
/*    */     
/* 29 */     int shapeHoleColor = dataSet.getScatterShapeHoleColor();
/*    */     
/* 31 */     if (shapeSize > 0.0D) {
/* 32 */       renderPaint.setStyle(Paint.Style.STROKE);
/* 33 */       renderPaint.setStrokeWidth(shapeStrokeSize);
/*    */       
/* 35 */       c.drawCircle(posX, posY, shapeHoleSizeHalf + shapeStrokeSizeHalf, renderPaint);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 41 */       if (shapeHoleColor != 1122867) {
/* 42 */         renderPaint.setStyle(Paint.Style.FILL);
/*    */         
/* 44 */         renderPaint.setColor(shapeHoleColor);
/* 45 */         c.drawCircle(posX, posY, shapeHoleSizeHalf, renderPaint);
/*    */       
/*    */       }
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 52 */       renderPaint.setStyle(Paint.Style.FILL);
/*    */       
/* 54 */       c.drawCircle(posX, posY, shapeHalf, renderPaint);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\scatter\CircleShapeRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */