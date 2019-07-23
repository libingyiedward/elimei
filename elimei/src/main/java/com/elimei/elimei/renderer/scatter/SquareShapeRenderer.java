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
/*    */ 
/*    */ public class SquareShapeRenderer
/*    */   implements IShapeRenderer
/*    */ {
/*    */   public void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler, float posX, float posY, Paint renderPaint) {
/* 23 */     float shapeSize = dataSet.getScatterShapeSize();
/* 24 */     float shapeHalf = shapeSize / 2.0F;
/* 25 */     float shapeHoleSizeHalf = Utils.convertDpToPixel(dataSet.getScatterShapeHoleRadius());
/* 26 */     float shapeHoleSize = shapeHoleSizeHalf * 2.0F;
/* 27 */     float shapeStrokeSize = (shapeSize - shapeHoleSize) / 2.0F;
/* 28 */     float shapeStrokeSizeHalf = shapeStrokeSize / 2.0F;
/*    */     
/* 30 */     int shapeHoleColor = dataSet.getScatterShapeHoleColor();
/*    */     
/* 32 */     if (shapeSize > 0.0D) {
/* 33 */       renderPaint.setStyle(Paint.Style.STROKE);
/* 34 */       renderPaint.setStrokeWidth(shapeStrokeSize);
/*    */       
/* 36 */       c.drawRect(posX - shapeHoleSizeHalf - shapeStrokeSizeHalf, posY - shapeHoleSizeHalf - shapeStrokeSizeHalf, posX + shapeHoleSizeHalf + shapeStrokeSizeHalf, posY + shapeHoleSizeHalf + shapeStrokeSizeHalf, renderPaint);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 42 */       if (shapeHoleColor != 1122867) {
/* 43 */         renderPaint.setStyle(Paint.Style.FILL);
/*    */         
/* 45 */         renderPaint.setColor(shapeHoleColor);
/* 46 */         c.drawRect(posX - shapeHoleSizeHalf, posY - shapeHoleSizeHalf, posX + shapeHoleSizeHalf, posY + shapeHoleSizeHalf, renderPaint);
/*    */       
/*    */       }
/*    */ 
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 54 */       renderPaint.setStyle(Paint.Style.FILL);
/*    */       
/* 56 */       c.drawRect(posX - shapeHalf, posY - shapeHalf, posX + shapeHalf, posY + shapeHalf, renderPaint);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\scatter\SquareShapeRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */