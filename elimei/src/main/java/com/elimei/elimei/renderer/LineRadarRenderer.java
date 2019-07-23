/*    */ package com.elimei.elimei.renderer;
/*    */ 
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.Paint;
/*    */ import android.graphics.Path;
/*    */ import android.graphics.drawable.Drawable;
/*    */ import com.elimei.elimei.animation.ChartAnimator;
/*    */ import com.elimei.elimei.utils.Utils;
/*    */ import com.elimei.elimei.utils.ViewPortHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LineRadarRenderer
/*    */   extends LineScatterCandleRadarRenderer
/*    */ {
/* 18 */   public LineRadarRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) { super(animator, viewPortHandler); }
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
/*    */   protected void drawFilledPath(Canvas c, Path filledPath, Drawable drawable) {
/* 30 */     if (clipPathSupported()) {
/*    */       
/* 32 */       int save = c.save();
/* 33 */       c.clipPath(filledPath);
/*    */       
/* 35 */       drawable.setBounds((int)this.mViewPortHandler.contentLeft(), 
/* 36 */           (int)this.mViewPortHandler.contentTop(), 
/* 37 */           (int)this.mViewPortHandler.contentRight(), 
/* 38 */           (int)this.mViewPortHandler.contentBottom());
/* 39 */       drawable.draw(c);
/*    */       
/* 41 */       c.restoreToCount(save);
/*    */     } else {
/*    */       
/* 44 */       throw new RuntimeException("Fill-drawables not (yet) supported below API level 18, this code was run on API level " + 
/* 45 */           Utils.getSDKInt() + ".");
/*    */     } 
/*    */   }
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
/*    */   protected void drawFilledPath(Canvas c, Path filledPath, int fillColor, int fillAlpha) {
/* 60 */     int color = fillAlpha << 24 | fillColor & 0xFFFFFF;
/*    */     
/* 62 */     if (clipPathSupported()) {
/*    */       
/* 64 */       int save = c.save();
/*    */       
/* 66 */       c.clipPath(filledPath);
/*    */       
/* 68 */       c.drawColor(color);
/* 69 */       c.restoreToCount(save);
/*    */     }
/*    */     else {
/*    */       
/* 73 */       Paint.Style previous = this.mRenderPaint.getStyle();
/* 74 */       int previousColor = this.mRenderPaint.getColor();
/*    */ 
/*    */       
/* 77 */       this.mRenderPaint.setStyle(Paint.Style.FILL);
/* 78 */       this.mRenderPaint.setColor(color);
/*    */       
/* 80 */       c.drawPath(filledPath, this.mRenderPaint);
/*    */ 
/*    */       
/* 83 */       this.mRenderPaint.setColor(previousColor);
/* 84 */       this.mRenderPaint.setStyle(previous);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 94 */   private boolean clipPathSupported() { return (Utils.getSDKInt() >= 18); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\LineRadarRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */