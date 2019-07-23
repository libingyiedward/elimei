/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.RectF;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import com.elimei.elimei.animation.ChartAnimator;
/*     */ import com.elimei.elimei.buffer.BarBuffer;
/*     */ import com.elimei.elimei.buffer.HorizontalBarBuffer;
/*     */ import com.elimei.elimei.data.BarData;
/*     */ import com.elimei.elimei.data.BarEntry;
/*     */ import com.elimei.elimei.formatter.IValueFormatter;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.dataprovider.BarDataProvider;
/*     */ import com.elimei.elimei.interfaces.dataprovider.ChartInterface;
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
/*     */ public class HorizontalBarChartRenderer
/*     */   extends BarChartRenderer
/*     */ {
/*     */   private RectF mBarShadowRectBuffer;
/*     */   
/*     */   public HorizontalBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
/*  35 */     super(chart, animator, viewPortHandler);
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
/*  53 */     this.mBarShadowRectBuffer = new RectF(); this.mValuePaint.setTextAlign(Paint.Align.LEFT);
/*     */   } public void initBuffers() { BarData barData = this.mChart.getBarData(); this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
/*     */     for (int i = 0; i < this.mBarBuffers.length; i++) {
/*     */       IBarDataSet set = (IBarDataSet)barData.getDataSetByIndex(i);
/*     */       this.mBarBuffers[i] = new HorizontalBarBuffer(set.getEntryCount() * 4 * (set.isStacked() ? set.getStackSize() : 1), barData.getDataSetCount(), set.isStacked());
/*  58 */     }  } protected void drawDataSet(Canvas c, IBarDataSet dataSet, int index) { Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
/*     */     
/*  60 */     this.mBarBorderPaint.setColor(dataSet.getBarBorderColor());
/*  61 */     this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
/*     */     
/*  63 */     boolean drawBorder = (dataSet.getBarBorderWidth() > 0.0F);
/*     */     
/*  65 */     float phaseX = this.mAnimator.getPhaseX();
/*  66 */     float phaseY = this.mAnimator.getPhaseY();
/*     */ 
/*     */     
/*  69 */     if (this.mChart.isDrawBarShadowEnabled()) {
/*  70 */       this.mShadowPaint.setColor(dataSet.getBarShadowColor());
/*     */       
/*  72 */       BarData barData = this.mChart.getBarData();
/*     */       
/*  74 */       float barWidth = barData.getBarWidth();
/*  75 */       float barWidthHalf = barWidth / 2.0F;
/*     */ 
/*     */       
/*  78 */       int i = 0, count = Math.min((int)Math.ceil((dataSet.getEntryCount() * phaseX)), dataSet.getEntryCount());
/*  79 */       for (; i < count; 
/*  80 */         i++) {
/*     */         
/*  82 */         BarEntry e = (BarEntry)dataSet.getEntryForIndex(i);
/*     */         
/*  84 */         float x = e.getX();
/*     */         
/*  86 */         this.mBarShadowRectBuffer.top = x - barWidthHalf;
/*  87 */         this.mBarShadowRectBuffer.bottom = x + barWidthHalf;
/*     */         
/*  89 */         trans.rectValueToPixel(this.mBarShadowRectBuffer);
/*     */         
/*  91 */         if (this.mViewPortHandler.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
/*     */ 
/*     */           
/*  94 */           if (!this.mViewPortHandler.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
/*     */             break;
/*     */           }
/*  97 */           this.mBarShadowRectBuffer.left = this.mViewPortHandler.contentLeft();
/*  98 */           this.mBarShadowRectBuffer.right = this.mViewPortHandler.contentRight();
/*     */           
/* 100 */           c.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     BarBuffer buffer = this.mBarBuffers[index];
/* 106 */     buffer.setPhases(phaseX, phaseY);
/* 107 */     buffer.setDataSet(index);
/* 108 */     buffer.setInverted(this.mChart.isInverted(dataSet.getAxisDependency()));
/* 109 */     buffer.setBarWidth(this.mChart.getBarData().getBarWidth());
/*     */     
/* 111 */     buffer.feed(dataSet);
/*     */     
/* 113 */     trans.pointValuesToPixel(buffer.buffer);
/*     */     
/* 115 */     boolean isSingleColor = (dataSet.getColors().size() == 1);
/*     */     
/* 117 */     if (isSingleColor) {
/* 118 */       this.mRenderPaint.setColor(dataSet.getColor());
/*     */     }
/*     */     
/* 121 */     for (int j = 0; j < buffer.size(); j += 4) {
/*     */       
/* 123 */       if (!this.mViewPortHandler.isInBoundsTop(buffer.buffer[j + 3])) {
/*     */         break;
/*     */       }
/* 126 */       if (this.mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 1])) {
/*     */ 
/*     */         
/* 129 */         if (!isSingleColor)
/*     */         {
/*     */           
/* 132 */           this.mRenderPaint.setColor(dataSet.getColor(j / 4));
/*     */         }
/*     */         
/* 135 */         c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mRenderPaint);
/*     */ 
/*     */         
/* 138 */         if (drawBorder) {
/* 139 */           c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mBarBorderPaint);
/*     */         }
/*     */       } 
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawValues(Canvas c) {
/* 148 */     if (isDrawingValuesAllowed(this.mChart)) {
/*     */       
/* 150 */       List<IBarDataSet> dataSets = this.mChart.getBarData().getDataSets();
/*     */       
/* 152 */       float valueOffsetPlus = Utils.convertDpToPixel(5.0F);
/* 153 */       float posOffset = 0.0F;
/* 154 */       float negOffset = 0.0F;
/* 155 */       boolean drawValueAboveBar = this.mChart.isDrawValueAboveBarEnabled();
/*     */       
/* 157 */       for (int i = 0; i < this.mChart.getBarData().getDataSetCount(); i++) {
/*     */         
/* 159 */         IBarDataSet dataSet = (IBarDataSet)dataSets.get(i);
/*     */         
/* 161 */         if (shouldDrawValues(dataSet)) {
/*     */ 
/*     */           
/* 164 */           boolean isInverted = this.mChart.isInverted(dataSet.getAxisDependency());
/*     */ 
/*     */           
/* 167 */           applyValueTextStyle(dataSet);
/* 168 */           float halfTextHeight = Utils.calcTextHeight(this.mValuePaint, "10") / 2.0F;
/*     */           
/* 170 */           IValueFormatter formatter = dataSet.getValueFormatter();
/*     */ 
/*     */           
/* 173 */           BarBuffer buffer = this.mBarBuffers[i];
/*     */           
/* 175 */           float phaseY = this.mAnimator.getPhaseY();
/*     */           
/* 177 */           MPPointF iconsOffset = MPPointF.getInstance(dataSet.getIconsOffset());
/* 178 */           iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
/* 179 */           iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);
/*     */ 
/*     */           
/* 182 */           if (!dataSet.isStacked()) {
/*     */             
/* 184 */             for (int j = 0; j < buffer.buffer.length * this.mAnimator.getPhaseX(); j += 4) {
/*     */               
/* 186 */               float y = (buffer.buffer[j + 1] + buffer.buffer[j + 3]) / 2.0F;
/*     */               
/* 188 */               if (!this.mViewPortHandler.isInBoundsTop(buffer.buffer[j + 1])) {
/*     */                 break;
/*     */               }
/* 191 */               if (this.mViewPortHandler.isInBoundsX(buffer.buffer[j]))
/*     */               {
/*     */                 
/* 194 */                 if (this.mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 1])) {
/*     */ 
/*     */                   
/* 197 */                   BarEntry entry = (BarEntry)dataSet.getEntryForIndex(j / 4);
/* 198 */                   float val = entry.getY();
/* 199 */                   String formattedValue = formatter.getFormattedValue(val, entry, i, this.mViewPortHandler);
/*     */ 
/*     */                   
/* 202 */                   float valueTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue);
/* 203 */                   posOffset = drawValueAboveBar ? valueOffsetPlus : -(valueTextWidth + valueOffsetPlus);
/* 204 */                   negOffset = drawValueAboveBar ? -(valueTextWidth + valueOffsetPlus) : valueOffsetPlus;
/*     */                   
/* 206 */                   if (isInverted) {
/* 207 */                     posOffset = -posOffset - valueTextWidth;
/* 208 */                     negOffset = -negOffset - valueTextWidth;
/*     */                   } 
/*     */                   
/* 211 */                   if (dataSet.isDrawValuesEnabled()) {
/* 212 */                     drawValue(c, formattedValue, buffer.buffer[j + 2] + ((val >= 0.0F) ? posOffset : negOffset), y + halfTextHeight, dataSet
/*     */ 
/*     */ 
/*     */                         
/* 216 */                         .getValueTextColor(j / 2));
/*     */                   }
/*     */                   
/* 219 */                   if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
/*     */                     
/* 221 */                     Drawable icon = entry.getIcon();
/*     */                     
/* 223 */                     float px = buffer.buffer[j + 2] + ((val >= 0.0F) ? posOffset : negOffset);
/* 224 */                     float py = y;
/*     */                     
/* 226 */                     px += iconsOffset.x;
/* 227 */                     py += iconsOffset.y;
/*     */                     
/* 229 */                     Utils.drawImage(c, icon, (int)px, (int)py, icon
/*     */ 
/*     */ 
/*     */ 
/*     */                         
/* 234 */                         .getIntrinsicWidth(), icon
/* 235 */                         .getIntrinsicHeight());
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } else {
/*     */             
/* 242 */             Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
/*     */             
/* 244 */             int bufferIndex = 0;
/* 245 */             int index = 0;
/*     */             
/* 247 */             while (index < dataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
/*     */               
/* 249 */               BarEntry entry = (BarEntry)dataSet.getEntryForIndex(index);
/*     */               
/* 251 */               int color = dataSet.getValueTextColor(index);
/* 252 */               float[] vals = entry.getYVals();
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 257 */               if (vals == null) {
/*     */                 
/* 259 */                 if (!this.mViewPortHandler.isInBoundsTop(buffer.buffer[bufferIndex + 1])) {
/*     */                   break;
/*     */                 }
/* 262 */                 if (!this.mViewPortHandler.isInBoundsX(buffer.buffer[bufferIndex])) {
/*     */                   continue;
/*     */                 }
/* 265 */                 if (!this.mViewPortHandler.isInBoundsBottom(buffer.buffer[bufferIndex + 1])) {
/*     */                   continue;
/*     */                 }
/* 268 */                 float val = entry.getY();
/* 269 */                 String formattedValue = formatter.getFormattedValue(val, entry, i, this.mViewPortHandler);
/*     */ 
/*     */ 
/*     */                 
/* 273 */                 float valueTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue);
/* 274 */                 posOffset = drawValueAboveBar ? valueOffsetPlus : -(valueTextWidth + valueOffsetPlus);
/* 275 */                 negOffset = drawValueAboveBar ? -(valueTextWidth + valueOffsetPlus) : valueOffsetPlus;
/*     */                 
/* 277 */                 if (isInverted) {
/* 278 */                   posOffset = -posOffset - valueTextWidth;
/* 279 */                   negOffset = -negOffset - valueTextWidth;
/*     */                 } 
/*     */                 
/* 282 */                 if (dataSet.isDrawValuesEnabled()) {
/* 283 */                   drawValue(c, formattedValue, buffer.buffer[bufferIndex + 2] + (
/*     */                       
/* 285 */                       (entry.getY() >= 0.0F) ? posOffset : negOffset), buffer.buffer[bufferIndex + 1] + halfTextHeight, color);
/*     */                 }
/*     */ 
/*     */                 
/* 289 */                 if (entry.getIcon() != null && dataSet.isDrawIconsEnabled())
/*     */                 {
/* 291 */                   Drawable icon = entry.getIcon();
/*     */ 
/*     */                   
/* 294 */                   float px = buffer.buffer[bufferIndex + 2] + ((entry.getY() >= 0.0F) ? posOffset : negOffset);
/* 295 */                   float py = buffer.buffer[bufferIndex + 1];
/*     */                   
/* 297 */                   px += iconsOffset.x;
/* 298 */                   py += iconsOffset.y;
/*     */                   
/* 300 */                   Utils.drawImage(c, icon, (int)px, (int)py, icon
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 305 */                       .getIntrinsicWidth(), icon
/* 306 */                       .getIntrinsicHeight());
/*     */                 }
/*     */               
/*     */               } else {
/*     */                 
/* 311 */                 float[] transformed = new float[vals.length * 2];
/*     */                 
/* 313 */                 float posY = 0.0F;
/* 314 */                 float negY = -entry.getNegativeSum();
/*     */                 
/* 316 */                 for (int k = 0, idx = 0; k < transformed.length; k += 2, idx++) {
/*     */                   
/* 318 */                   float y, value = vals[idx];
/*     */ 
/*     */                   
/* 321 */                   if (value == 0.0F && (posY == 0.0F || negY == 0.0F)) {
/*     */                     
/* 323 */                     y = value;
/* 324 */                   } else if (value >= 0.0F) {
/* 325 */                     posY += value;
/* 326 */                     y = posY;
/*     */                   } else {
/* 328 */                     y = negY;
/* 329 */                     negY -= value;
/*     */                   } 
/*     */                   
/* 332 */                   transformed[k] = y * phaseY;
/*     */                 } 
/*     */                 
/* 335 */                 trans.pointValuesToPixel(transformed);
/*     */                 
/* 337 */                 for (int k = 0; k < transformed.length; k += 2) {
/*     */                   
/* 339 */                   float val = vals[k / 2];
/* 340 */                   String formattedValue = formatter.getFormattedValue(val, entry, i, this.mViewPortHandler);
/*     */ 
/*     */ 
/*     */                   
/* 344 */                   float valueTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue);
/* 345 */                   posOffset = drawValueAboveBar ? valueOffsetPlus : -(valueTextWidth + valueOffsetPlus);
/* 346 */                   negOffset = drawValueAboveBar ? -(valueTextWidth + valueOffsetPlus) : valueOffsetPlus;
/*     */                   
/* 348 */                   if (isInverted) {
/* 349 */                     posOffset = -posOffset - valueTextWidth;
/* 350 */                     negOffset = -negOffset - valueTextWidth;
/*     */                   } 
/*     */                   
/* 353 */                   boolean drawBelow = ((val == 0.0F && negY == 0.0F && posY > 0.0F) || val < 0.0F);
/*     */ 
/*     */ 
/*     */                   
/* 357 */                   float x = transformed[k] + (drawBelow ? negOffset : posOffset);
/*     */                   
/* 359 */                   float y = (buffer.buffer[bufferIndex + 1] + buffer.buffer[bufferIndex + 3]) / 2.0F;
/*     */                   
/* 361 */                   if (!this.mViewPortHandler.isInBoundsTop(y)) {
/*     */                     break;
/*     */                   }
/* 364 */                   if (this.mViewPortHandler.isInBoundsX(x))
/*     */                   {
/*     */                     
/* 367 */                     if (this.mViewPortHandler.isInBoundsBottom(y)) {
/*     */ 
/*     */                       
/* 370 */                       if (dataSet.isDrawValuesEnabled()) {
/* 371 */                         drawValue(c, formattedValue, x, y + halfTextHeight, color);
/*     */                       }
/*     */                       
/* 374 */                       if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
/*     */                         
/* 376 */                         Drawable icon = entry.getIcon();
/*     */                         
/* 378 */                         Utils.drawImage(c, icon, (int)(x + iconsOffset.x), (int)(y + iconsOffset.y), icon
/*     */ 
/*     */ 
/*     */ 
/*     */                             
/* 383 */                             .getIntrinsicWidth(), icon
/* 384 */                             .getIntrinsicHeight());
/*     */                       } 
/*     */                     }  } 
/*     */                 } 
/*     */               } 
/* 389 */               bufferIndex = (vals == null) ? (bufferIndex + 4) : (bufferIndex + 4 * vals.length);
/* 390 */               index++;
/*     */             } 
/*     */           } 
/*     */           
/* 394 */           MPPointF.recycleInstance(iconsOffset);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   protected void drawValue(Canvas c, String valueText, float x, float y, int color) {
/* 400 */     this.mValuePaint.setColor(color);
/* 401 */     c.drawText(valueText, x, y, this.mValuePaint);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
/* 407 */     float top = x - barWidthHalf;
/* 408 */     float bottom = x + barWidthHalf;
/* 409 */     float left = y1;
/* 410 */     float right = y2;
/*     */     
/* 412 */     this.mBarRect.set(left, top, right, bottom);
/*     */     
/* 414 */     trans.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 419 */   protected void setHighlightDrawPos(Highlight high, RectF bar) { high.setDraw(bar.centerY(), bar.right); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDrawingValuesAllowed(ChartInterface chart) {
/* 424 */     return 
/* 425 */       (chart.getData().getEntryCount() < chart.getMaxVisibleCount() * this.mViewPortHandler.getScaleY());
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\HorizontalBarChartRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */