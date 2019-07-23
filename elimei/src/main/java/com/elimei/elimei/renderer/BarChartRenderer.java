/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.RectF;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import com.elimei.elimei.animation.ChartAnimator;
/*     */ import com.elimei.elimei.buffer.BarBuffer;
/*     */ import com.elimei.elimei.data.BarData;
/*     */ import com.elimei.elimei.data.BarEntry;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.highlight.Range;
/*     */ import com.elimei.elimei.interfaces.dataprovider.BarDataProvider;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
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
/*     */ 
/*     */ 
/*     */ public class BarChartRenderer
/*     */   extends BarLineScatterCandleBubbleRenderer
/*     */ {
/*     */   protected BarDataProvider mChart;
/*  32 */   protected RectF mBarRect = new RectF();
/*     */   protected BarBuffer[] mBarBuffers; protected Paint mShadowPaint; protected Paint mBarBorderPaint; private RectF mBarShadowRectBuffer; public void initBuffers() {
/*     */     BarData barData = this.mChart.getBarData();
/*     */     this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
/*     */     for (int i = 0; i < this.mBarBuffers.length; i++) {
/*     */       IBarDataSet set = (IBarDataSet)barData.getDataSetByIndex(i);
/*     */       this.mBarBuffers[i] = new BarBuffer(set.getEntryCount() * 4 * (set.isStacked() ? set.getStackSize() : 1), barData.getDataSetCount(), set.isStacked());
/*     */     } 
/*     */   }
/*  41 */   public BarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) { super(animator, viewPortHandler);
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
/*  85 */     this.mBarShadowRectBuffer = new RectF(); this.mChart = chart; this.mHighlightPaint = new Paint(Paint.ANTI_ALIAS_FLAG); this.mHighlightPaint.setStyle(Paint.Style.FILL); this.mHighlightPaint.setColor(Color.rgb(0, 0, 0)); this.mHighlightPaint.setAlpha(120);
/*     */     this.mShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
/*     */     this.mShadowPaint.setStyle(Paint.Style.FILL);
/*     */     this.mBarBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
/*  89 */     this.mBarBorderPaint.setStyle(Paint.Style.STROKE); } protected void drawDataSet(Canvas c, IBarDataSet dataSet, int index) { Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
/*     */     
/*  91 */     this.mBarBorderPaint.setColor(dataSet.getBarBorderColor());
/*  92 */     this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
/*     */     
/*  94 */     boolean drawBorder = (dataSet.getBarBorderWidth() > 0.0F);
/*     */     
/*  96 */     float phaseX = this.mAnimator.getPhaseX();
/*  97 */     float phaseY = this.mAnimator.getPhaseY();
/*     */ 
/*     */     
/* 100 */     if (this.mChart.isDrawBarShadowEnabled()) {
/* 101 */       this.mShadowPaint.setColor(dataSet.getBarShadowColor());
/*     */       
/* 103 */       BarData barData = this.mChart.getBarData();
/*     */       
/* 105 */       float barWidth = barData.getBarWidth();
/* 106 */       float barWidthHalf = barWidth / 2.0F;
/*     */ 
/*     */       
/* 109 */       int i = 0, count = Math.min((int)Math.ceil((dataSet.getEntryCount() * phaseX)), dataSet.getEntryCount());
/* 110 */       for (; i < count; 
/* 111 */         i++) {
/*     */         
/* 113 */         BarEntry e = (BarEntry)dataSet.getEntryForIndex(i);
/*     */         
/* 115 */         float x = e.getX();
/*     */         
/* 117 */         this.mBarShadowRectBuffer.left = x - barWidthHalf;
/* 118 */         this.mBarShadowRectBuffer.right = x + barWidthHalf;
/*     */         
/* 120 */         trans.rectValueToPixel(this.mBarShadowRectBuffer);
/*     */         
/* 122 */         if (this.mViewPortHandler.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
/*     */ 
/*     */           
/* 125 */           if (!this.mViewPortHandler.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
/*     */             break;
/*     */           }
/* 128 */           this.mBarShadowRectBuffer.top = this.mViewPortHandler.contentTop();
/* 129 */           this.mBarShadowRectBuffer.bottom = this.mViewPortHandler.contentBottom();
/*     */           
/* 131 */           c.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     BarBuffer buffer = this.mBarBuffers[index];
/* 137 */     buffer.setPhases(phaseX, phaseY);
/* 138 */     buffer.setDataSet(index);
/* 139 */     buffer.setInverted(this.mChart.isInverted(dataSet.getAxisDependency()));
/* 140 */     buffer.setBarWidth(this.mChart.getBarData().getBarWidth());
/*     */     
/* 142 */     buffer.feed(dataSet);
/*     */     
/* 144 */     trans.pointValuesToPixel(buffer.buffer);
/*     */     
/* 146 */     boolean isSingleColor = (dataSet.getColors().size() == 1);
/*     */     
/* 148 */     if (isSingleColor) {
/* 149 */       this.mRenderPaint.setColor(dataSet.getColor());
/*     */     }
/*     */     
/* 152 */     for (int j = 0; j < buffer.size(); j += 4) {
/*     */       
/* 154 */       if (this.mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
/*     */ 
/*     */         
/* 157 */         if (!this.mViewPortHandler.isInBoundsRight(buffer.buffer[j])) {
/*     */           break;
/*     */         }
/* 160 */         if (!isSingleColor)
/*     */         {
/*     */           
/* 163 */           this.mRenderPaint.setColor(dataSet.getColor(j / 4));
/*     */         }
/*     */         
/* 166 */         c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mRenderPaint);
/*     */ 
/*     */         
/* 169 */         if (drawBorder)
/* 170 */           c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mBarBorderPaint); 
/*     */       } 
/*     */     }  } public void drawData(Canvas c) { BarData barData = this.mChart.getBarData();
/*     */     for (int i = 0; i < barData.getDataSetCount(); i++) {
/*     */       IBarDataSet set = (IBarDataSet)barData.getDataSetByIndex(i);
/*     */       if (set.isVisible())
/*     */         drawDataSet(c, set, i); 
/*     */     }  }
/* 178 */   protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) { float left = x - barWidthHalf;
/* 179 */     float right = x + barWidthHalf;
/* 180 */     float top = y1;
/* 181 */     float bottom = y2;
/*     */     
/* 183 */     this.mBarRect.set(left, top, right, bottom);
/*     */     
/* 185 */     trans.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawValues(Canvas c) {
/* 192 */     if (isDrawingValuesAllowed(this.mChart)) {
/*     */       
/* 194 */       List<IBarDataSet> dataSets = this.mChart.getBarData().getDataSets();
/*     */       
/* 196 */       float valueOffsetPlus = Utils.convertDpToPixel(4.5F);
/* 197 */       float posOffset = 0.0F;
/* 198 */       float negOffset = 0.0F;
/* 199 */       boolean drawValueAboveBar = this.mChart.isDrawValueAboveBarEnabled();
/*     */       
/* 201 */       for (int i = 0; i < this.mChart.getBarData().getDataSetCount(); i++) {
/*     */         
/* 203 */         IBarDataSet dataSet = (IBarDataSet)dataSets.get(i);
/*     */         
/* 205 */         if (shouldDrawValues(dataSet)) {
/*     */ 
/*     */ 
/*     */           
/* 209 */           applyValueTextStyle(dataSet);
/*     */           
/* 211 */           boolean isInverted = this.mChart.isInverted(dataSet.getAxisDependency());
/*     */ 
/*     */ 
/*     */           
/* 215 */           float valueTextHeight = Utils.calcTextHeight(this.mValuePaint, "8");
/* 216 */           posOffset = drawValueAboveBar ? -valueOffsetPlus : (valueTextHeight + valueOffsetPlus);
/* 217 */           negOffset = drawValueAboveBar ? (valueTextHeight + valueOffsetPlus) : -valueOffsetPlus;
/*     */           
/* 219 */           if (isInverted) {
/* 220 */             posOffset = -posOffset - valueTextHeight;
/* 221 */             negOffset = -negOffset - valueTextHeight;
/*     */           } 
/*     */ 
/*     */           
/* 225 */           BarBuffer buffer = this.mBarBuffers[i];
/*     */           
/* 227 */           float phaseY = this.mAnimator.getPhaseY();
/*     */           
/* 229 */           MPPointF iconsOffset = MPPointF.getInstance(dataSet.getIconsOffset());
/* 230 */           iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
/* 231 */           iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);
/*     */ 
/*     */           
/* 234 */           if (!dataSet.isStacked()) {
/*     */             
/* 236 */             for (int j = 0; j < buffer.buffer.length * this.mAnimator.getPhaseX(); j += 4) {
/*     */               
/* 238 */               float x = (buffer.buffer[j] + buffer.buffer[j + 2]) / 2.0F;
/*     */               
/* 240 */               if (!this.mViewPortHandler.isInBoundsRight(x)) {
/*     */                 break;
/*     */               }
/* 243 */               if (this.mViewPortHandler.isInBoundsY(buffer.buffer[j + 1]) && this.mViewPortHandler
/* 244 */                 .isInBoundsLeft(x)) {
/*     */ 
/*     */                 
/* 247 */                 BarEntry entry = (BarEntry)dataSet.getEntryForIndex(j / 4);
/* 248 */                 float val = entry.getY();
/*     */                 
/* 250 */                 if (dataSet.isDrawValuesEnabled()) {
/* 251 */                   drawValue(c, dataSet.getValueFormatter(), val, entry, i, x, (val >= 0.0F) ? (buffer.buffer[j + 1] + posOffset) : (buffer.buffer[j + 3] + negOffset), dataSet
/*     */ 
/*     */ 
/*     */                       
/* 255 */                       .getValueTextColor(j / 4));
/*     */                 }
/*     */                 
/* 258 */                 if (entry.getIcon() != null && dataSet.isDrawIconsEnabled())
/*     */                 {
/* 260 */                   Drawable icon = entry.getIcon();
/*     */                   
/* 262 */                   float px = x;
/* 263 */                   float py = (val >= 0.0F) ? (buffer.buffer[j + 1] + posOffset) : (buffer.buffer[j + 3] + negOffset);
/*     */ 
/*     */ 
/*     */                   
/* 267 */                   px += iconsOffset.x;
/* 268 */                   py += iconsOffset.y;
/*     */                   
/* 270 */                   Utils.drawImage(c, icon, (int)px, (int)py, icon
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 275 */                       .getIntrinsicWidth(), icon
/* 276 */                       .getIntrinsicHeight());
/*     */                 }
/*     */               
/*     */               } 
/*     */             } 
/*     */           } else {
/*     */             
/* 283 */             Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
/*     */             
/* 285 */             int bufferIndex = 0;
/* 286 */             int index = 0;
/*     */             
/* 288 */             while (index < dataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
/*     */               
/* 290 */               BarEntry entry = (BarEntry)dataSet.getEntryForIndex(index);
/*     */               
/* 292 */               float[] vals = entry.getYVals();
/* 293 */               float x = (buffer.buffer[bufferIndex] + buffer.buffer[bufferIndex + 2]) / 2.0F;
/*     */               
/* 295 */               int color = dataSet.getValueTextColor(index);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 300 */               if (vals == null) {
/*     */                 
/* 302 */                 if (!this.mViewPortHandler.isInBoundsRight(x)) {
/*     */                   break;
/*     */                 }
/* 305 */                 if (!this.mViewPortHandler.isInBoundsY(buffer.buffer[bufferIndex + 1]) || 
/* 306 */                   !this.mViewPortHandler.isInBoundsLeft(x)) {
/*     */                   continue;
/*     */                 }
/* 309 */                 if (dataSet.isDrawValuesEnabled()) {
/* 310 */                   drawValue(c, dataSet.getValueFormatter(), entry.getY(), entry, i, x, buffer.buffer[bufferIndex + 1] + (
/*     */                       
/* 312 */                       (entry.getY() >= 0.0F) ? posOffset : negOffset), color);
/*     */                 }
/*     */ 
/*     */                 
/* 316 */                 if (entry.getIcon() != null && dataSet.isDrawIconsEnabled())
/*     */                 {
/* 318 */                   Drawable icon = entry.getIcon();
/*     */                   
/* 320 */                   float px = x;
/*     */                   
/* 322 */                   float py = buffer.buffer[bufferIndex + 1] + ((entry.getY() >= 0.0F) ? posOffset : negOffset);
/*     */                   
/* 324 */                   px += iconsOffset.x;
/* 325 */                   py += iconsOffset.y;
/*     */                   
/* 327 */                   Utils.drawImage(c, icon, (int)px, (int)py, icon
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 332 */                       .getIntrinsicWidth(), icon
/* 333 */                       .getIntrinsicHeight());
/*     */                 }
/*     */               
/*     */               }
/*     */               else {
/*     */                 
/* 339 */                 float[] transformed = new float[vals.length * 2];
/*     */                 
/* 341 */                 float posY = 0.0F;
/* 342 */                 float negY = -entry.getNegativeSum();
/*     */                 
/* 344 */                 for (int k = 0, idx = 0; k < transformed.length; k += 2, idx++) {
/*     */                   
/* 346 */                   float y, value = vals[idx];
/*     */ 
/*     */                   
/* 349 */                   if (value == 0.0F && (posY == 0.0F || negY == 0.0F)) {
/*     */                     
/* 351 */                     y = value;
/* 352 */                   } else if (value >= 0.0F) {
/* 353 */                     posY += value;
/* 354 */                     y = posY;
/*     */                   } else {
/* 356 */                     y = negY;
/* 357 */                     negY -= value;
/*     */                   } 
/*     */                   
/* 360 */                   transformed[k + 1] = y * phaseY;
/*     */                 } 
/*     */                 
/* 363 */                 trans.pointValuesToPixel(transformed);
/*     */                 
/* 365 */                 for (int k = 0; k < transformed.length; k += 2) {
/*     */                   
/* 367 */                   float val = vals[k / 2];
/* 368 */                   boolean drawBelow = ((val == 0.0F && negY == 0.0F && posY > 0.0F) || val < 0.0F);
/*     */ 
/*     */                   
/* 371 */                   float y = transformed[k + 1] + (drawBelow ? negOffset : posOffset);
/*     */ 
/*     */                   
/* 374 */                   if (!this.mViewPortHandler.isInBoundsRight(x)) {
/*     */                     break;
/*     */                   }
/* 377 */                   if (this.mViewPortHandler.isInBoundsY(y) && this.mViewPortHandler
/* 378 */                     .isInBoundsLeft(x)) {
/*     */ 
/*     */                     
/* 381 */                     if (dataSet.isDrawValuesEnabled()) {
/* 382 */                       drawValue(c, dataSet
/* 383 */                           .getValueFormatter(), vals[k / 2], entry, i, x, y, color);
/*     */                     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 392 */                     if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
/*     */                       
/* 394 */                       Drawable icon = entry.getIcon();
/*     */                       
/* 396 */                       Utils.drawImage(c, icon, (int)(x + iconsOffset.x), (int)(y + iconsOffset.y), icon
/*     */ 
/*     */ 
/*     */ 
/*     */                           
/* 401 */                           .getIntrinsicWidth(), icon
/* 402 */                           .getIntrinsicHeight());
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/* 407 */               bufferIndex = (vals == null) ? (bufferIndex + 4) : (bufferIndex + 4 * vals.length);
/* 408 */               index++;
/*     */             } 
/*     */           } 
/*     */           
/* 412 */           MPPointF.recycleInstance(iconsOffset);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawHighlighted(Canvas c, Highlight[] indices) {
/* 420 */     BarData barData = this.mChart.getBarData();
/*     */     
/* 422 */     for (Highlight high : indices) {
/*     */       
/* 424 */       IBarDataSet set = (IBarDataSet)barData.getDataSetByIndex(high.getDataSetIndex());
/*     */       
/* 426 */       if (set != null && set.isHighlightEnabled()) {
/*     */ 
/*     */         
/* 429 */         BarEntry e = (BarEntry)set.getEntryForXValue(high.getX(), high.getY());
/*     */         
/* 431 */         if (isInBoundsX(e, set)) {
/*     */           float y2, y1;
/*     */           
/* 434 */           Transformer trans = this.mChart.getTransformer(set.getAxisDependency());
/*     */           
/* 436 */           this.mHighlightPaint.setColor(set.getHighLightColor());
/* 437 */           this.mHighlightPaint.setAlpha(set.getHighLightAlpha());
/*     */           
/* 439 */           boolean isStack = (high.getStackIndex() >= 0 && e.isStacked());
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 444 */           if (isStack) {
/*     */             
/* 446 */             if (this.mChart.isHighlightFullBarEnabled()) {
/*     */               
/* 448 */               y1 = e.getPositiveSum();
/* 449 */               y2 = -e.getNegativeSum();
/*     */             }
/*     */             else {
/*     */               
/* 453 */               Range range = e.getRanges()[high.getStackIndex()];
/*     */               
/* 455 */               y1 = range.from;
/* 456 */               y2 = range.to;
/*     */             } 
/*     */           } else {
/*     */             
/* 460 */             y1 = e.getY();
/* 461 */             y2 = 0.0F;
/*     */           } 
/*     */           
/* 464 */           prepareBarHighlight(e.getX(), y1, y2, barData.getBarWidth() / 2.0F, trans);
/*     */           
/* 466 */           setHighlightDrawPos(high, this.mBarRect);
/*     */           
/* 468 */           c.drawRect(this.mBarRect, this.mHighlightPaint);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 477 */   protected void setHighlightDrawPos(Highlight high, RectF bar) { high.setDraw(bar.centerX(), bar.top); }
/*     */   
/*     */   public void drawExtras(Canvas c) {}
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\BarChartRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */