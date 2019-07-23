/*    */ package com.elimei.elimei.renderer.scatter;
/*    */ 
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.Paint;
/*    */ import android.graphics.Path;
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
/*    */ public class TriangleShapeRenderer
/*    */   implements IShapeRenderer
/*    */ {
/* 19 */   protected Path mTrianglePathBuffer = new Path();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler, float posX, float posY, Paint renderPaint) {
/* 25 */     float shapeSize = dataSet.getScatterShapeSize();
/* 26 */     float shapeHalf = shapeSize / 2.0F;
/* 27 */     float shapeHoleSizeHalf = Utils.convertDpToPixel(dataSet.getScatterShapeHoleRadius());
/* 28 */     float shapeHoleSize = shapeHoleSizeHalf * 2.0F;
/* 29 */     float shapeStrokeSize = (shapeSize - shapeHoleSize) / 2.0F;
/*    */     
/* 31 */     int shapeHoleColor = dataSet.getScatterShapeHoleColor();
/*    */     
/* 33 */     renderPaint.setStyle(Paint.Style.FILL);
/*    */ 
/*    */     
/* 36 */     Path tri = this.mTrianglePathBuffer;
/* 37 */     tri.reset();
/*    */     
/* 39 */     tri.moveTo(posX, posY - shapeHalf);
/* 40 */     tri.lineTo(posX + shapeHalf, posY + shapeHalf);
/* 41 */     tri.lineTo(posX - shapeHalf, posY + shapeHalf);
/*    */     
/* 43 */     if (shapeSize > 0.0D) {
/* 44 */       tri.lineTo(posX, posY - shapeHalf);
/*    */       
/* 46 */       tri.moveTo(posX - shapeHalf + shapeStrokeSize, posY + shapeHalf - shapeStrokeSize);
/*    */       
/* 48 */       tri.lineTo(posX + shapeHalf - shapeStrokeSize, posY + shapeHalf - shapeStrokeSize);
/*    */       
/* 50 */       tri.lineTo(posX, posY - shapeHalf + shapeStrokeSize);
/*    */       
/* 52 */       tri.lineTo(posX - shapeHalf + shapeStrokeSize, posY + shapeHalf - shapeStrokeSize);
/*    */     } 
/*    */ 
/*    */     
/* 56 */     tri.close();
/*    */     
/* 58 */     c.drawPath(tri, renderPaint);
/* 59 */     tri.reset();
/*    */     
/* 61 */     if (shapeSize > 0.0D && shapeHoleColor != 1122867) {
/*    */ 
/*    */       
/* 64 */       renderPaint.setColor(shapeHoleColor);
/*    */       
/* 66 */       tri.moveTo(posX, posY - shapeHalf + shapeStrokeSize);
/*    */       
/* 68 */       tri.lineTo(posX + shapeHalf - shapeStrokeSize, posY + shapeHalf - shapeStrokeSize);
/*    */       
/* 70 */       tri.lineTo(posX - shapeHalf + shapeStrokeSize, posY + shapeHalf - shapeStrokeSize);
/*    */       
/* 72 */       tri.close();
/*    */       
/* 74 */       c.drawPath(tri, renderPaint);
/* 75 */       tri.reset();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\scatter\TriangleShapeRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */