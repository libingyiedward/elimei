/*    */ package com.elimei.elimei.renderer;
/*    */ 
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.Path;
/*    */ import com.elimei.elimei.animation.ChartAnimator;
/*    */ import com.elimei.elimei.interfaces.datasets.ILineScatterCandleRadarDataSet;
/*    */ import com.elimei.elimei.utils.ViewPortHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LineScatterCandleRadarRenderer
/*    */   extends BarLineScatterCandleBubbleRenderer
/*    */ {
/* 18 */   private Path mHighlightLinePath = new Path();
/*    */ 
/*    */   
/* 21 */   public LineScatterCandleRadarRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) { super(animator, viewPortHandler); }
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
/*    */   protected void drawHighlightLines(Canvas c, float x, float y, ILineScatterCandleRadarDataSet set) {
/* 35 */     this.mHighlightPaint.setColor(set.getHighLightColor());
/* 36 */     this.mHighlightPaint.setStrokeWidth(set.getHighlightLineWidth());
/*    */ 
/*    */     
/* 39 */     this.mHighlightPaint.setPathEffect(set.getDashPathEffectHighlight());
/*    */ 
/*    */     
/* 42 */     if (set.isVerticalHighlightIndicatorEnabled()) {
/*    */ 
/*    */       
/* 45 */       this.mHighlightLinePath.reset();
/* 46 */       this.mHighlightLinePath.moveTo(x, this.mViewPortHandler.contentTop());
/* 47 */       this.mHighlightLinePath.lineTo(x, this.mViewPortHandler.contentBottom());
/*    */       
/* 49 */       c.drawPath(this.mHighlightLinePath, this.mHighlightPaint);
/*    */     } 
/*    */ 
/*    */     
/* 53 */     if (set.isHorizontalHighlightIndicatorEnabled()) {
/*    */ 
/*    */       
/* 56 */       this.mHighlightLinePath.reset();
/* 57 */       this.mHighlightLinePath.moveTo(this.mViewPortHandler.contentLeft(), y);
/* 58 */       this.mHighlightLinePath.lineTo(this.mViewPortHandler.contentRight(), y);
/*    */       
/* 60 */       c.drawPath(this.mHighlightLinePath, this.mHighlightPaint);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\LineScatterCandleRadarRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */