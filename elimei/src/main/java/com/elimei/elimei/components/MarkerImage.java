/*     */ package com.elimei.elimei.components;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Build;
/*     */ import com.elimei.elimei.charts.Chart;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.utils.FSize;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import java.lang.ref.WeakReference;
/*     */ 
/*     */ 
/*     */ public class MarkerImage
/*     */   implements IMarker
/*     */ {
/*     */   private Context mContext;
/*     */   private Drawable mDrawable;
/*     */   private MPPointF mOffset;
/*     */   private MPPointF mOffset2;
/*     */   private WeakReference<Chart> mWeakChart;
/*     */   private FSize mSize;
/*     */   private Rect mDrawableBoundsCache;
/*     */   
/*     */   public MarkerImage(Context context, int drawableResourceId) {
/*  28 */     this.mOffset = new MPPointF();
/*  29 */     this.mOffset2 = new MPPointF();
/*     */ 
/*     */     
/*  32 */     this.mSize = new FSize();
/*  33 */     this.mDrawableBoundsCache = new Rect();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.mContext = context;
/*     */     
/*  44 */     if (Build.VERSION.SDK_INT >= 21) {
/*     */       
/*  46 */       this.mDrawable = this.mContext.getResources().getDrawable(drawableResourceId, null);
/*     */     }
/*     */     else {
/*     */       
/*  50 */       this.mDrawable = this.mContext.getResources().getDrawable(drawableResourceId);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setOffset(MPPointF offset) {
/*  55 */     this.mOffset = offset;
/*     */     
/*  57 */     if (this.mOffset == null) {
/*  58 */       this.mOffset = new MPPointF();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setOffset(float offsetX, float offsetY) {
/*  63 */     this.mOffset.x = offsetX;
/*  64 */     this.mOffset.y = offsetY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  69 */   public MPPointF getOffset() { return this.mOffset; }
/*     */ 
/*     */   
/*     */   public void setSize(FSize size) {
/*  73 */     this.mSize = size;
/*     */     
/*  75 */     if (this.mSize == null) {
/*  76 */       this.mSize = new FSize();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  81 */   public FSize getSize() { return this.mSize; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public void setChartView(Chart chart) { this.mWeakChart = new WeakReference(chart); }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public Chart getChartView() { return (this.mWeakChart == null) ? null : (Chart)this.mWeakChart.get(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
/*  95 */     MPPointF offset = getOffset();
/*  96 */     this.mOffset2.x = offset.x;
/*  97 */     this.mOffset2.y = offset.y;
/*     */     
/*  99 */     Chart chart = getChartView();
/*     */     
/* 101 */     float width = this.mSize.width;
/* 102 */     float height = this.mSize.height;
/*     */     
/* 104 */     if (width == 0.0F && this.mDrawable != null) {
/* 105 */       width = this.mDrawable.getIntrinsicWidth();
/*     */     }
/* 107 */     if (height == 0.0F && this.mDrawable != null) {
/* 108 */       height = this.mDrawable.getIntrinsicHeight();
/*     */     }
/*     */     
/* 111 */     if (posX + this.mOffset2.x < 0.0F) {
/* 112 */       this.mOffset2.x = -posX;
/* 113 */     } else if (chart != null && posX + width + this.mOffset2.x > chart.getWidth()) {
/* 114 */       this.mOffset2.x = chart.getWidth() - posX - width;
/*     */     } 
/*     */     
/* 117 */     if (posY + this.mOffset2.y < 0.0F) {
/* 118 */       this.mOffset2.y = -posY;
/* 119 */     } else if (chart != null && posY + height + this.mOffset2.y > chart.getHeight()) {
/* 120 */       this.mOffset2.y = chart.getHeight() - posY - height;
/*     */     } 
/*     */     
/* 123 */     return this.mOffset2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshContent(Entry e, Highlight highlight) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Canvas canvas, float posX, float posY) {
/* 134 */     if (this.mDrawable == null)
/*     */       return; 
/* 136 */     MPPointF offset = getOffsetForDrawingAtPoint(posX, posY);
/*     */     
/* 138 */     float width = this.mSize.width;
/* 139 */     float height = this.mSize.height;
/*     */     
/* 141 */     if (width == 0.0F && this.mDrawable != null) {
/* 142 */       width = this.mDrawable.getIntrinsicWidth();
/*     */     }
/* 144 */     if (height == 0.0F && this.mDrawable != null) {
/* 145 */       height = this.mDrawable.getIntrinsicHeight();
/*     */     }
/*     */     
/* 148 */     this.mDrawable.copyBounds(this.mDrawableBoundsCache);
/* 149 */     this.mDrawable.setBounds(this.mDrawableBoundsCache.left, this.mDrawableBoundsCache.top, this.mDrawableBoundsCache.left + (int)width, this.mDrawableBoundsCache.top + (int)height);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     int saveId = canvas.save();
/*     */     
/* 157 */     canvas.translate(posX + offset.x, posY + offset.y);
/* 158 */     this.mDrawable.draw(canvas);
/* 159 */     canvas.restoreToCount(saveId);
/*     */     
/* 161 */     this.mDrawable.setBounds(this.mDrawableBoundsCache);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\MarkerImage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */