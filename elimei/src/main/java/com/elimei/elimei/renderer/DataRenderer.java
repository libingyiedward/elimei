/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.Paint;
/*     */ import com.elimei.elimei.animation.ChartAnimator;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.formatter.IValueFormatter;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.dataprovider.ChartInterface;
/*     */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DataRenderer
/*     */   extends Renderer
/*     */ {
/*     */   protected ChartAnimator mAnimator;
/*     */   protected Paint mRenderPaint;
/*     */   protected Paint mHighlightPaint;
/*     */   protected Paint mDrawPaint;
/*     */   protected Paint mValuePaint;
/*     */   
/*     */   public DataRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) {
/*  50 */     super(viewPortHandler);
/*  51 */     this.mAnimator = animator;
/*     */     
/*  53 */     this.mRenderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
/*  54 */     this.mRenderPaint.setStyle(Paint.Style.FILL);
/*     */     
/*  56 */     this.mDrawPaint = new Paint(4);
/*     */     
/*  58 */     this.mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
/*  59 */     this.mValuePaint.setColor(Color.rgb(63, 63, 63));
/*  60 */     this.mValuePaint.setTextAlign(Paint.Align.CENTER);
/*  61 */     this.mValuePaint.setTextSize(Utils.convertDpToPixel(9.0F));
/*     */     
/*  63 */     this.mHighlightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
/*  64 */     this.mHighlightPaint.setStyle(Paint.Style.STROKE);
/*  65 */     this.mHighlightPaint.setStrokeWidth(2.0F);
/*  66 */     this.mHighlightPaint.setColor(Color.rgb(255, 187, 115));
/*     */   }
/*     */   
/*     */   protected boolean isDrawingValuesAllowed(ChartInterface chart) {
/*  70 */     return 
/*  71 */       (chart.getData().getEntryCount() < chart.getMaxVisibleCount() * this.mViewPortHandler.getScaleX());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public Paint getPaintValues() { return this.mValuePaint; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public Paint getPaintHighlight() { return this.mHighlightPaint; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public Paint getPaintRender() { return this.mRenderPaint; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyValueTextStyle(IDataSet set) {
/* 111 */     this.mValuePaint.setTypeface(set.getValueTypeface());
/* 112 */     this.mValuePaint.setTextSize(set.getValueTextSize());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void initBuffers();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void drawData(Canvas paramCanvas);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void drawValues(Canvas paramCanvas);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawValue(Canvas c, IValueFormatter formatter, float value, Entry entry, int dataSetIndex, float x, float y, int color) {
/* 149 */     this.mValuePaint.setColor(color);
/* 150 */     c.drawText(formatter.getFormattedValue(value, entry, dataSetIndex, this.mViewPortHandler), x, y, this.mValuePaint);
/*     */   }
/*     */   
/*     */   public abstract void drawExtras(Canvas paramCanvas);
/*     */   
/*     */   public abstract void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight);
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\DataRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */