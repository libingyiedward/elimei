/*     */ package com.elimei.elimei.components;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.widget.RelativeLayout;
/*     */ import com.elimei.elimei.charts.Chart;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import java.lang.ref.WeakReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarkerView
/*     */   extends RelativeLayout
/*     */   implements IMarker
/*     */ {
/*  24 */   private MPPointF mOffset = new MPPointF();
/*  25 */   private MPPointF mOffset2 = new MPPointF();
/*     */ 
/*     */ 
/*     */   
/*     */   private WeakReference<Chart> mWeakChart;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MarkerView(Context context, int layoutResource) {
/*  35 */     super(context);
/*  36 */     setupLayoutResource(layoutResource);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setupLayoutResource(int layoutResource) {
/*  46 */     View inflated = LayoutInflater.from(getContext()).inflate(layoutResource, this);
/*     */     
/*  48 */     inflated.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
/*  49 */     inflated.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
/*     */ 
/*     */     
/*  52 */     inflated.layout(0, 0, inflated.getMeasuredWidth(), inflated.getMeasuredHeight());
/*     */   }
/*     */   
/*     */   public void setOffset(MPPointF offset) {
/*  56 */     this.mOffset = offset;
/*     */     
/*  58 */     if (this.mOffset == null) {
/*  59 */       this.mOffset = new MPPointF();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setOffset(float offsetX, float offsetY) {
/*  64 */     this.mOffset.x = offsetX;
/*  65 */     this.mOffset.y = offsetY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public MPPointF getOffset() { return this.mOffset; }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public void setChartView(Chart chart) { this.mWeakChart = new WeakReference(chart); }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public Chart getChartView() { return (this.mWeakChart == null) ? null : (Chart)this.mWeakChart.get(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
/*  84 */     MPPointF offset = getOffset();
/*  85 */     this.mOffset2.x = offset.x;
/*  86 */     this.mOffset2.y = offset.y;
/*     */     
/*  88 */     Chart chart = getChartView();
/*     */     
/*  90 */     float width = getWidth();
/*  91 */     float height = getHeight();
/*     */     
/*  93 */     if (posX + this.mOffset2.x < 0.0F) {
/*  94 */       this.mOffset2.x = -posX;
/*  95 */     } else if (chart != null && posX + width + this.mOffset2.x > chart.getWidth()) {
/*  96 */       this.mOffset2.x = chart.getWidth() - posX - width;
/*     */     } 
/*     */     
/*  99 */     if (posY + this.mOffset2.y < 0.0F) {
/* 100 */       this.mOffset2.y = -posY;
/* 101 */     } else if (chart != null && posY + height + this.mOffset2.y > chart.getHeight()) {
/* 102 */       this.mOffset2.y = chart.getHeight() - posY - height;
/*     */     } 
/*     */     
/* 105 */     return this.mOffset2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshContent(Entry e, Highlight highlight) {
/* 111 */     measure(View.MeasureSpec.makeMeasureSpec(0, 0), 
/* 112 */         View.MeasureSpec.makeMeasureSpec(0, 0));
/* 113 */     layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Canvas canvas, float posX, float posY) {
/* 120 */     MPPointF offset = getOffsetForDrawingAtPoint(posX, posY);
/*     */     
/* 122 */     int saveId = canvas.save();
/*     */     
/* 124 */     canvas.translate(posX + offset.x, posY + offset.y);
/* 125 */     draw(canvas);
/* 126 */     canvas.restoreToCount(saveId);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\MarkerView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */