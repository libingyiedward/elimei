/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import com.elimei.elimei.animation.ChartAnimator;
/*     */ import com.elimei.elimei.data.BubbleData;
/*     */ import com.elimei.elimei.data.BubbleEntry;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.dataprovider.BubbleDataProvider;
/*     */ import com.elimei.elimei.interfaces.datasets.IBubbleDataSet;
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
/*     */ public class BubbleChartRenderer
/*     */   extends BarLineScatterCandleBubbleRenderer
/*     */ {
/*     */   protected BubbleDataProvider mChart;
/*     */   private float[] sizeBuffer;
/*     */   private float[] pointBuffer;
/*     */   private float[] _hsvBuffer;
/*     */   
/*     */   public BubbleChartRenderer(BubbleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
/*  32 */     super(animator, viewPortHandler);
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
/*  58 */     this.sizeBuffer = new float[4];
/*  59 */     this.pointBuffer = new float[2];
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
/* 199 */     this._hsvBuffer = new float[3];
/*     */     this.mChart = chart;
/*     */     this.mRenderPaint.setStyle(Paint.Style.FILL);
/*     */     this.mHighlightPaint.setStyle(Paint.Style.STROKE);
/*     */     this.mHighlightPaint.setStrokeWidth(Utils.convertDpToPixel(1.5F)); } public void drawHighlighted(Canvas c, Highlight[] indices) {
/* 204 */     BubbleData bubbleData = this.mChart.getBubbleData();
/*     */     
/* 206 */     float phaseY = this.mAnimator.getPhaseY();
/*     */     
/* 208 */     for (Highlight high : indices) {
/*     */       
/* 210 */       IBubbleDataSet set = (IBubbleDataSet)bubbleData.getDataSetByIndex(high.getDataSetIndex());
/*     */       
/* 212 */       if (set != null && set.isHighlightEnabled()) {
/*     */ 
/*     */         
/* 215 */         BubbleEntry entry = (BubbleEntry)set.getEntryForXValue(high.getX(), high.getY());
/*     */         
/* 217 */         if (entry.getY() == high.getY())
/*     */         {
/*     */           
/* 220 */           if (isInBoundsX(entry, set)) {
/*     */ 
/*     */             
/* 223 */             Transformer trans = this.mChart.getTransformer(set.getAxisDependency());
/*     */             
/* 225 */             this.sizeBuffer[0] = 0.0F;
/* 226 */             this.sizeBuffer[2] = 1.0F;
/*     */             
/* 228 */             trans.pointValuesToPixel(this.sizeBuffer);
/*     */             
/* 230 */             boolean normalizeSize = set.isNormalizeSizeEnabled();
/*     */ 
/*     */             
/* 233 */             float maxBubbleWidth = Math.abs(this.sizeBuffer[2] - this.sizeBuffer[0]);
/* 234 */             float maxBubbleHeight = Math.abs(this.mViewPortHandler
/* 235 */                 .contentBottom() - this.mViewPortHandler.contentTop());
/* 236 */             float referenceSize = Math.min(maxBubbleHeight, maxBubbleWidth);
/*     */             
/* 238 */             this.pointBuffer[0] = entry.getX();
/* 239 */             this.pointBuffer[1] = entry.getY() * phaseY;
/* 240 */             trans.pointValuesToPixel(this.pointBuffer);
/*     */             
/* 242 */             high.setDraw(this.pointBuffer[0], this.pointBuffer[1]);
/*     */             
/* 244 */             float shapeHalf = getShapeSize(entry.getSize(), set
/* 245 */                 .getMaxSize(), referenceSize, normalizeSize) / 2.0F;
/*     */ 
/*     */ 
/*     */             
/* 249 */             if (this.mViewPortHandler.isInBoundsTop(this.pointBuffer[1] + shapeHalf) && this.mViewPortHandler
/* 250 */               .isInBoundsBottom(this.pointBuffer[1] - shapeHalf))
/*     */             {
/*     */               
/* 253 */               if (this.mViewPortHandler.isInBoundsLeft(this.pointBuffer[0] + shapeHalf)) {
/*     */ 
/*     */                 
/* 256 */                 if (!this.mViewPortHandler.isInBoundsRight(this.pointBuffer[0] - shapeHalf)) {
/*     */                   break;
/*     */                 }
/* 259 */                 int originalColor = set.getColor((int)entry.getX());
/*     */                 
/* 261 */                 Color.RGBToHSV(Color.red(originalColor), Color.green(originalColor), 
/* 262 */                     Color.blue(originalColor), this._hsvBuffer);
/* 263 */                 this._hsvBuffer[2] = this._hsvBuffer[2] * 0.5F;
/* 264 */                 int color = Color.HSVToColor(Color.alpha(originalColor), this._hsvBuffer);
/*     */                 
/* 266 */                 this.mHighlightPaint.setColor(color);
/* 267 */                 this.mHighlightPaint.setStrokeWidth(set.getHighlightCircleWidth());
/* 268 */                 c.drawCircle(this.pointBuffer[0], this.pointBuffer[1], shapeHalf, this.mHighlightPaint);
/*     */               }  } 
/*     */           }  } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initBuffers() {}
/*     */   
/*     */   public void drawData(Canvas c) {
/*     */     BubbleData bubbleData = this.mChart.getBubbleData();
/*     */     for (IBubbleDataSet set : bubbleData.getDataSets()) {
/*     */       if (set.isVisible())
/*     */         drawDataSet(c, set); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected float getShapeSize(float entrySize, float maxSize, float reference, boolean normalizeSize) {
/*     */     float factor = normalizeSize ? ((maxSize == 0.0F) ? 1.0F : (float)Math.sqrt((entrySize / maxSize))) : entrySize;
/*     */     return reference * factor;
/*     */   }
/*     */   
/*     */   protected void drawDataSet(Canvas c, IBubbleDataSet dataSet) {
/*     */     Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
/*     */     float phaseY = this.mAnimator.getPhaseY();
/*     */     this.mXBounds.set(this.mChart, dataSet);
/*     */     this.sizeBuffer[0] = 0.0F;
/*     */     this.sizeBuffer[2] = 1.0F;
/*     */     trans.pointValuesToPixel(this.sizeBuffer);
/*     */     boolean normalizeSize = dataSet.isNormalizeSizeEnabled();
/*     */     float maxBubbleWidth = Math.abs(this.sizeBuffer[2] - this.sizeBuffer[0]);
/*     */     float maxBubbleHeight = Math.abs(this.mViewPortHandler.contentBottom() - this.mViewPortHandler.contentTop());
/*     */     float referenceSize = Math.min(maxBubbleHeight, maxBubbleWidth);
/*     */     for (int j = this.mXBounds.min; j <= this.mXBounds.range + this.mXBounds.min; j++) {
/*     */       BubbleEntry entry = (BubbleEntry)dataSet.getEntryForIndex(j);
/*     */       this.pointBuffer[0] = entry.getX();
/*     */       this.pointBuffer[1] = entry.getY() * phaseY;
/*     */       trans.pointValuesToPixel(this.pointBuffer);
/*     */       float shapeHalf = getShapeSize(entry.getSize(), dataSet.getMaxSize(), referenceSize, normalizeSize) / 2.0F;
/*     */       if (this.mViewPortHandler.isInBoundsTop(this.pointBuffer[1] + shapeHalf) && this.mViewPortHandler.isInBoundsBottom(this.pointBuffer[1] - shapeHalf))
/*     */         if (this.mViewPortHandler.isInBoundsLeft(this.pointBuffer[0] + shapeHalf)) {
/*     */           if (!this.mViewPortHandler.isInBoundsRight(this.pointBuffer[0] - shapeHalf))
/*     */             break; 
/*     */           int color = dataSet.getColor((int)entry.getX());
/*     */           this.mRenderPaint.setColor(color);
/*     */           c.drawCircle(this.pointBuffer[0], this.pointBuffer[1], shapeHalf, this.mRenderPaint);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawValues(Canvas c) {
/*     */     BubbleData bubbleData = this.mChart.getBubbleData();
/*     */     if (bubbleData == null)
/*     */       return; 
/*     */     if (isDrawingValuesAllowed(this.mChart)) {
/*     */       List<IBubbleDataSet> dataSets = bubbleData.getDataSets();
/*     */       float lineHeight = Utils.calcTextHeight(this.mValuePaint, "1");
/*     */       for (int i = 0; i < dataSets.size(); i++) {
/*     */         IBubbleDataSet dataSet = (IBubbleDataSet)dataSets.get(i);
/*     */         if (shouldDrawValues(dataSet)) {
/*     */           applyValueTextStyle(dataSet);
/*     */           float phaseX = Math.max(0.0F, Math.min(1.0F, this.mAnimator.getPhaseX()));
/*     */           float phaseY = this.mAnimator.getPhaseY();
/*     */           this.mXBounds.set(this.mChart, dataSet);
/*     */           float[] positions = this.mChart.getTransformer(dataSet.getAxisDependency()).generateTransformedValuesBubble(dataSet, phaseY, this.mXBounds.min, this.mXBounds.max);
/*     */           float alpha = (phaseX == 1.0F) ? phaseY : phaseX;
/*     */           MPPointF iconsOffset = MPPointF.getInstance(dataSet.getIconsOffset());
/*     */           iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
/*     */           iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);
/*     */           for (int j = 0; j < positions.length; j += 2) {
/*     */             int valueTextColor = dataSet.getValueTextColor(j / 2 + this.mXBounds.min);
/*     */             valueTextColor = Color.argb(Math.round(255.0F * alpha), Color.red(valueTextColor), Color.green(valueTextColor), Color.blue(valueTextColor));
/*     */             float x = positions[j];
/*     */             float y = positions[j + 1];
/*     */             if (!this.mViewPortHandler.isInBoundsRight(x))
/*     */               break; 
/*     */             if (this.mViewPortHandler.isInBoundsLeft(x) && this.mViewPortHandler.isInBoundsY(y)) {
/*     */               BubbleEntry entry = (BubbleEntry)dataSet.getEntryForIndex(j / 2 + this.mXBounds.min);
/*     */               if (dataSet.isDrawValuesEnabled())
/*     */                 drawValue(c, dataSet.getValueFormatter(), entry.getSize(), entry, i, x, y + 0.5F * lineHeight, valueTextColor); 
/*     */               if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
/*     */                 Drawable icon = entry.getIcon();
/*     */                 Utils.drawImage(c, icon, (int)(x + iconsOffset.x), (int)(y + iconsOffset.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           MPPointF.recycleInstance(iconsOffset);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawExtras(Canvas c) {}
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\BubbleChartRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */