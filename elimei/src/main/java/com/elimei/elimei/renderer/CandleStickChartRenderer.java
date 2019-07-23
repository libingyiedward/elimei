/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import com.elimei.elimei.animation.ChartAnimator;
/*     */ import com.elimei.elimei.data.CandleData;
/*     */ import com.elimei.elimei.data.CandleEntry;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.dataprovider.CandleDataProvider;
/*     */ import com.elimei.elimei.interfaces.datasets.ICandleDataSet;
/*     */ import com.elimei.elimei.utils.MPPointD;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import com.elimei.elimei.utils.Transformer;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CandleStickChartRenderer
/*     */   extends LineScatterCandleRadarRenderer
/*     */ {
/*     */   protected CandleDataProvider mChart;
/*  27 */   private float[] mShadowBuffers = new float[8];
/*  28 */   private float[] mBodyBuffers = new float[4];
/*  29 */   private float[] mRangeBuffers = new float[4];
/*  30 */   private float[] mOpenBuffers = new float[4];
/*  31 */   private float[] mCloseBuffers = new float[4];
/*     */ 
/*     */   
/*     */   public CandleStickChartRenderer(CandleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
/*  35 */     super(animator, viewPortHandler);
/*  36 */     this.mChart = chart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initBuffers() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawData(Canvas c) {
/*  47 */     CandleData candleData = this.mChart.getCandleData();
/*     */     
/*  49 */     for (ICandleDataSet set : candleData.getDataSets()) {
/*     */       
/*  51 */       if (set.isVisible()) {
/*  52 */         drawDataSet(c, set);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawDataSet(Canvas c, ICandleDataSet dataSet) {
/*  59 */     Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
/*     */     
/*  61 */     float phaseY = this.mAnimator.getPhaseY();
/*  62 */     float barSpace = dataSet.getBarSpace();
/*  63 */     boolean showCandleBar = dataSet.getShowCandleBar();
/*     */     
/*  65 */     this.mXBounds.set(this.mChart, dataSet);
/*     */     
/*  67 */     this.mRenderPaint.setStrokeWidth(dataSet.getShadowWidth());
/*     */ 
/*     */     
/*  70 */     for (int j = this.mXBounds.min; j <= this.mXBounds.range + this.mXBounds.min; j++) {
/*     */ 
/*     */       
/*  73 */       CandleEntry e = (CandleEntry)dataSet.getEntryForIndex(j);
/*     */       
/*  75 */       if (e != null) {
/*     */ 
/*     */         
/*  78 */         float xPos = e.getX();
/*     */         
/*  80 */         float open = e.getOpen();
/*  81 */         float close = e.getClose();
/*  82 */         float high = e.getHigh();
/*  83 */         float low = e.getLow();
/*     */         
/*  85 */         if (showCandleBar) {
/*     */ 
/*     */           
/*  88 */           this.mShadowBuffers[0] = xPos;
/*  89 */           this.mShadowBuffers[2] = xPos;
/*  90 */           this.mShadowBuffers[4] = xPos;
/*  91 */           this.mShadowBuffers[6] = xPos;
/*     */           
/*  93 */           if (open > close) {
/*  94 */             this.mShadowBuffers[1] = high * phaseY;
/*  95 */             this.mShadowBuffers[3] = open * phaseY;
/*  96 */             this.mShadowBuffers[5] = low * phaseY;
/*  97 */             this.mShadowBuffers[7] = close * phaseY;
/*  98 */           } else if (open < close) {
/*  99 */             this.mShadowBuffers[1] = high * phaseY;
/* 100 */             this.mShadowBuffers[3] = close * phaseY;
/* 101 */             this.mShadowBuffers[5] = low * phaseY;
/* 102 */             this.mShadowBuffers[7] = open * phaseY;
/*     */           } else {
/* 104 */             this.mShadowBuffers[1] = high * phaseY;
/* 105 */             this.mShadowBuffers[3] = open * phaseY;
/* 106 */             this.mShadowBuffers[5] = low * phaseY;
/* 107 */             this.mShadowBuffers[7] = this.mShadowBuffers[3];
/*     */           } 
/*     */           
/* 110 */           trans.pointValuesToPixel(this.mShadowBuffers);
/*     */ 
/*     */ 
/*     */           
/* 114 */           if (dataSet.getShadowColorSameAsCandle()) {
/*     */             
/* 116 */             if (open > close) {
/* 117 */               this.mRenderPaint.setColor(
/* 118 */                   (dataSet.getDecreasingColor() == 1122867) ? dataSet
/* 119 */                   .getColor(j) : dataSet
/* 120 */                   .getDecreasingColor());
/*     */             
/*     */             }
/* 123 */             else if (open < close) {
/* 124 */               this.mRenderPaint.setColor(
/* 125 */                   (dataSet.getIncreasingColor() == 1122867) ? dataSet
/* 126 */                   .getColor(j) : dataSet
/* 127 */                   .getIncreasingColor());
/*     */             }
/*     */             else {
/*     */               
/* 131 */               this.mRenderPaint.setColor(
/* 132 */                   (dataSet.getNeutralColor() == 1122867) ? dataSet
/* 133 */                   .getColor(j) : dataSet
/* 134 */                   .getNeutralColor());
/*     */             } 
/*     */           } else {
/*     */             
/* 138 */             this.mRenderPaint.setColor(
/* 139 */                 (dataSet.getShadowColor() == 1122867) ? dataSet
/* 140 */                 .getColor(j) : dataSet
/* 141 */                 .getShadowColor());
/*     */           } 
/*     */ 
/*     */           
/* 145 */           this.mRenderPaint.setStyle(Paint.Style.STROKE);
/*     */           
/* 147 */           c.drawLines(this.mShadowBuffers, this.mRenderPaint);
/*     */ 
/*     */ 
/*     */           
/* 151 */           this.mBodyBuffers[0] = xPos - 0.5F + barSpace;
/* 152 */           this.mBodyBuffers[1] = close * phaseY;
/* 153 */           this.mBodyBuffers[2] = xPos + 0.5F - barSpace;
/* 154 */           this.mBodyBuffers[3] = open * phaseY;
/*     */           
/* 156 */           trans.pointValuesToPixel(this.mBodyBuffers);
/*     */ 
/*     */           
/* 159 */           if (open > close) {
/*     */             
/* 161 */             if (dataSet.getDecreasingColor() == 1122867) {
/* 162 */               this.mRenderPaint.setColor(dataSet.getColor(j));
/*     */             } else {
/* 164 */               this.mRenderPaint.setColor(dataSet.getDecreasingColor());
/*     */             } 
/*     */             
/* 167 */             this.mRenderPaint.setStyle(dataSet.getDecreasingPaintStyle());
/*     */             
/* 169 */             c.drawRect(this.mBodyBuffers[0], this.mBodyBuffers[3], this.mBodyBuffers[2], this.mBodyBuffers[1], this.mRenderPaint);
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 174 */           else if (open < close) {
/*     */             
/* 176 */             if (dataSet.getIncreasingColor() == 1122867) {
/* 177 */               this.mRenderPaint.setColor(dataSet.getColor(j));
/*     */             } else {
/* 179 */               this.mRenderPaint.setColor(dataSet.getIncreasingColor());
/*     */             } 
/*     */             
/* 182 */             this.mRenderPaint.setStyle(dataSet.getIncreasingPaintStyle());
/*     */             
/* 184 */             c.drawRect(this.mBodyBuffers[0], this.mBodyBuffers[1], this.mBodyBuffers[2], this.mBodyBuffers[3], this.mRenderPaint);
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 190 */             if (dataSet.getNeutralColor() == 1122867) {
/* 191 */               this.mRenderPaint.setColor(dataSet.getColor(j));
/*     */             } else {
/* 193 */               this.mRenderPaint.setColor(dataSet.getNeutralColor());
/*     */             } 
/*     */             
/* 196 */             c.drawLine(this.mBodyBuffers[0], this.mBodyBuffers[1], this.mBodyBuffers[2], this.mBodyBuffers[3], this.mRenderPaint);
/*     */           } 
/*     */         } else {
/*     */           int barColor;
/*     */ 
/*     */ 
/*     */           
/* 203 */           this.mRangeBuffers[0] = xPos;
/* 204 */           this.mRangeBuffers[1] = high * phaseY;
/* 205 */           this.mRangeBuffers[2] = xPos;
/* 206 */           this.mRangeBuffers[3] = low * phaseY;
/*     */           
/* 208 */           this.mOpenBuffers[0] = xPos - 0.5F + barSpace;
/* 209 */           this.mOpenBuffers[1] = open * phaseY;
/* 210 */           this.mOpenBuffers[2] = xPos;
/* 211 */           this.mOpenBuffers[3] = open * phaseY;
/*     */           
/* 213 */           this.mCloseBuffers[0] = xPos + 0.5F - barSpace;
/* 214 */           this.mCloseBuffers[1] = close * phaseY;
/* 215 */           this.mCloseBuffers[2] = xPos;
/* 216 */           this.mCloseBuffers[3] = close * phaseY;
/*     */           
/* 218 */           trans.pointValuesToPixel(this.mRangeBuffers);
/* 219 */           trans.pointValuesToPixel(this.mOpenBuffers);
/* 220 */           trans.pointValuesToPixel(this.mCloseBuffers);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 225 */           if (open > close) {
/*     */ 
/*     */             
/* 228 */             barColor = (dataSet.getDecreasingColor() == 1122867) ? dataSet.getColor(j) : dataSet.getDecreasingColor();
/* 229 */           } else if (open < close) {
/*     */ 
/*     */             
/* 232 */             barColor = (dataSet.getIncreasingColor() == 1122867) ? dataSet.getColor(j) : dataSet.getIncreasingColor();
/*     */           }
/*     */           else {
/*     */             
/* 236 */             barColor = (dataSet.getNeutralColor() == 1122867) ? dataSet.getColor(j) : dataSet.getNeutralColor();
/*     */           } 
/* 238 */           this.mRenderPaint.setColor(barColor);
/* 239 */           c.drawLine(this.mRangeBuffers[0], this.mRangeBuffers[1], this.mRangeBuffers[2], this.mRangeBuffers[3], this.mRenderPaint);
/*     */ 
/*     */ 
/*     */           
/* 243 */           c.drawLine(this.mOpenBuffers[0], this.mOpenBuffers[1], this.mOpenBuffers[2], this.mOpenBuffers[3], this.mRenderPaint);
/*     */ 
/*     */ 
/*     */           
/* 247 */           c.drawLine(this.mCloseBuffers[0], this.mCloseBuffers[1], this.mCloseBuffers[2], this.mCloseBuffers[3], this.mRenderPaint);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawValues(Canvas c) {
/* 259 */     if (isDrawingValuesAllowed(this.mChart)) {
/*     */       
/* 261 */       List<ICandleDataSet> dataSets = this.mChart.getCandleData().getDataSets();
/*     */       
/* 263 */       for (int i = 0; i < dataSets.size(); i++) {
/*     */         
/* 265 */         ICandleDataSet dataSet = (ICandleDataSet)dataSets.get(i);
/*     */         
/* 267 */         if (shouldDrawValues(dataSet)) {
/*     */ 
/*     */ 
/*     */           
/* 271 */           applyValueTextStyle(dataSet);
/*     */           
/* 273 */           Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
/*     */           
/* 275 */           this.mXBounds.set(this.mChart, dataSet);
/*     */           
/* 277 */           float[] positions = trans.generateTransformedValuesCandle(dataSet, this.mAnimator
/* 278 */               .getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
/*     */           
/* 280 */           float yOffset = Utils.convertDpToPixel(5.0F);
/*     */           
/* 282 */           MPPointF iconsOffset = MPPointF.getInstance(dataSet.getIconsOffset());
/* 283 */           iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
/* 284 */           iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);
/*     */           
/* 286 */           for (int j = 0; j < positions.length; j += 2) {
/*     */             
/* 288 */             float x = positions[j];
/* 289 */             float y = positions[j + 1];
/*     */             
/* 291 */             if (!this.mViewPortHandler.isInBoundsRight(x)) {
/*     */               break;
/*     */             }
/* 294 */             if (this.mViewPortHandler.isInBoundsLeft(x) && this.mViewPortHandler.isInBoundsY(y)) {
/*     */ 
/*     */               
/* 297 */               CandleEntry entry = (CandleEntry)dataSet.getEntryForIndex(j / 2 + this.mXBounds.min);
/*     */               
/* 299 */               if (dataSet.isDrawValuesEnabled()) {
/* 300 */                 drawValue(c, dataSet
/* 301 */                     .getValueFormatter(), entry
/* 302 */                     .getHigh(), entry, i, x, y - yOffset, dataSet
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 308 */                     .getValueTextColor(j / 2));
/*     */               }
/*     */               
/* 311 */               if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
/*     */                 
/* 313 */                 Drawable icon = entry.getIcon();
/*     */                 
/* 315 */                 Utils.drawImage(c, icon, (int)(x + iconsOffset.x), (int)(y + iconsOffset.y), icon
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 320 */                     .getIntrinsicWidth(), icon
/* 321 */                     .getIntrinsicHeight());
/*     */               } 
/*     */             } 
/*     */           } 
/* 325 */           MPPointF.recycleInstance(iconsOffset);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawExtras(Canvas c) {}
/*     */ 
/*     */   
/*     */   public void drawHighlighted(Canvas c, Highlight[] indices) {
/* 337 */     CandleData candleData = this.mChart.getCandleData();
/*     */     
/* 339 */     for (Highlight high : indices) {
/*     */       
/* 341 */       ICandleDataSet set = (ICandleDataSet)candleData.getDataSetByIndex(high.getDataSetIndex());
/*     */       
/* 343 */       if (set != null && set.isHighlightEnabled()) {
/*     */ 
/*     */         
/* 346 */         CandleEntry e = (CandleEntry)set.getEntryForXValue(high.getX(), high.getY());
/*     */         
/* 348 */         if (isInBoundsX(e, set)) {
/*     */ 
/*     */           
/* 351 */           float lowValue = e.getLow() * this.mAnimator.getPhaseY();
/* 352 */           float highValue = e.getHigh() * this.mAnimator.getPhaseY();
/* 353 */           float y = (lowValue + highValue) / 2.0F;
/*     */           
/* 355 */           MPPointD pix = this.mChart.getTransformer(set.getAxisDependency()).getPixelForValues(e.getX(), y);
/*     */           
/* 357 */           high.setDraw((float)pix.x, (float)pix.y);
/*     */ 
/*     */           
/* 360 */           drawHighlightLines(c, (float)pix.x, (float)pix.y, set);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\CandleStickChartRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */