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
/*    */ public class ChevronDownShapeRenderer
/*    */   implements IShapeRenderer
/*    */ {
/*    */   public void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler, float posX, float posY, Paint renderPaint) {
/* 22 */     float shapeHalf = dataSet.getScatterShapeSize() / 2.0F;
/*    */     
/* 24 */     renderPaint.setStyle(Paint.Style.STROKE);
/* 25 */     renderPaint.setStrokeWidth(Utils.convertDpToPixel(1.0F));
/*    */     
/* 27 */     c.drawLine(posX, posY + 2.0F * shapeHalf, posX + 2.0F * shapeHalf, posY, renderPaint);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     c.drawLine(posX, posY + 2.0F * shapeHalf, posX - 2.0F * shapeHalf, posY, renderPaint);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\scatter\ChevronDownShapeRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */