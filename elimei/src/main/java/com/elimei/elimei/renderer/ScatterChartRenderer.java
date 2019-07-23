/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.util.Log;
/*     */ import com.elimei.elimei.animation.ChartAnimator;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.data.ScatterData;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.dataprovider.ScatterDataProvider;
/*     */ import com.elimei.elimei.interfaces.datasets.IScatterDataSet;
/*     */ import com.elimei.elimei.renderer.scatter.IShapeRenderer;
/*     */ import com.elimei.elimei.utils.MPPointD;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import com.elimei.elimei.utils.Transformer;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ScatterChartRenderer
/*     */   extends LineScatterCandleRadarRenderer {
/*     */   protected ScatterDataProvider mChart;
/*     */   float[] mPixelBuffer;
/*     */   
/*     */   public void initBuffers() {}
/*     */   
/*     */   public ScatterChartRenderer(ScatterDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
/*  28 */     super(animator, viewPortHandler);
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
/*  48 */     this.mPixelBuffer = new float[2];
/*     */     this.mChart = chart;
/*     */   }
/*     */   protected void drawDataSet(Canvas c, IScatterDataSet dataSet) {
/*  52 */     ViewPortHandler viewPortHandler = this.mViewPortHandler;
/*     */     
/*  54 */     Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
/*     */     
/*  56 */     float phaseY = this.mAnimator.getPhaseY();
/*     */     
/*  58 */     IShapeRenderer renderer = dataSet.getShapeRenderer();
/*  59 */     if (renderer == null) {
/*  60 */       Log.i("MISSING", "There's no IShapeRenderer specified for ScatterDataSet");
/*     */       
/*     */       return;
/*     */     } 
/*  64 */     int max = (int)Math.min(
/*  65 */         Math.ceil((dataSet.getEntryCount() * this.mAnimator.getPhaseX())), dataSet
/*  66 */         .getEntryCount());
/*     */     
/*  68 */     for (int i = 0; i < max; i++) {
/*     */       
/*  70 */       Entry e = dataSet.getEntryForIndex(i);
/*     */       
/*  72 */       this.mPixelBuffer[0] = e.getX();
/*  73 */       this.mPixelBuffer[1] = e.getY() * phaseY;
/*     */       
/*  75 */       trans.pointValuesToPixel(this.mPixelBuffer);
/*     */       
/*  77 */       if (!viewPortHandler.isInBoundsRight(this.mPixelBuffer[0])) {
/*     */         break;
/*     */       }
/*  80 */       if (viewPortHandler.isInBoundsLeft(this.mPixelBuffer[0]) && viewPortHandler
/*  81 */         .isInBoundsY(this.mPixelBuffer[1])) {
/*     */ 
/*     */         
/*  84 */         this.mRenderPaint.setColor(dataSet.getColor(i / 2));
/*  85 */         renderer.renderShape(c, dataSet, this.mViewPortHandler, this.mPixelBuffer[0], this.mPixelBuffer[1], this.mRenderPaint);
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
/*  96 */     if (isDrawingValuesAllowed(this.mChart)) {
/*     */       
/*  98 */       List<IScatterDataSet> dataSets = this.mChart.getScatterData().getDataSets();
/*     */       
/* 100 */       for (int i = 0; i < this.mChart.getScatterData().getDataSetCount(); i++) {
/*     */         
/* 102 */         IScatterDataSet dataSet = (IScatterDataSet)dataSets.get(i);
/*     */         
/* 104 */         if (shouldDrawValues(dataSet)) {
/*     */ 
/*     */ 
/*     */           
/* 108 */           applyValueTextStyle(dataSet);
/*     */           
/* 110 */           this.mXBounds.set(this.mChart, dataSet);
/*     */ 
/*     */           
/* 113 */           float[] positions = this.mChart.getTransformer(dataSet.getAxisDependency()).generateTransformedValuesScatter(dataSet, this.mAnimator
/* 114 */               .getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
/*     */           
/* 116 */           float shapeSize = Utils.convertDpToPixel(dataSet.getScatterShapeSize());
/*     */           
/* 118 */           MPPointF iconsOffset = MPPointF.getInstance(dataSet.getIconsOffset());
/* 119 */           iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
/* 120 */           iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);
/*     */           
/* 122 */           for (int j = 0; j < positions.length; j += 2) {
/*     */             
/* 124 */             if (!this.mViewPortHandler.isInBoundsRight(positions[j])) {
/*     */               break;
/*     */             }
/*     */             
/* 128 */             if (this.mViewPortHandler.isInBoundsLeft(positions[j]) && this.mViewPortHandler
/* 129 */               .isInBoundsY(positions[j + 1])) {
/*     */ 
/*     */               
/* 132 */               Entry entry = dataSet.getEntryForIndex(j / 2 + this.mXBounds.min);
/*     */               
/* 134 */               if (dataSet.isDrawValuesEnabled()) {
/* 135 */                 drawValue(c, dataSet
/* 136 */                     .getValueFormatter(), entry
/* 137 */                     .getY(), entry, i, positions[j], positions[j + 1] - shapeSize, dataSet
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 142 */                     .getValueTextColor(j / 2 + this.mXBounds.min));
/*     */               }
/*     */               
/* 145 */               if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
/*     */                 
/* 147 */                 Drawable icon = entry.getIcon();
/*     */                 
/* 149 */                 Utils.drawImage(c, icon, (int)(positions[j] + iconsOffset.x), (int)(positions[j + 1] + iconsOffset.y), icon
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 154 */                     .getIntrinsicWidth(), icon
/* 155 */                     .getIntrinsicHeight());
/*     */               } 
/*     */             } 
/*     */           } 
/* 159 */           MPPointF.recycleInstance(iconsOffset);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   } public void drawData(Canvas c) {
/*     */     ScatterData scatterData = this.mChart.getScatterData();
/*     */     for (IScatterDataSet set : scatterData.getDataSets()) {
/*     */       if (set.isVisible())
/*     */         drawDataSet(c, set); 
/*     */     } 
/*     */   } public void drawExtras(Canvas c) {}
/*     */   public void drawHighlighted(Canvas c, Highlight[] indices) {
/* 171 */     ScatterData scatterData = this.mChart.getScatterData();
/*     */     
/* 173 */     for (Highlight high : indices) {
/*     */       
/* 175 */       IScatterDataSet set = (IScatterDataSet)scatterData.getDataSetByIndex(high.getDataSetIndex());
/*     */       
/* 177 */       if (set != null && set.isHighlightEnabled()) {
/*     */ 
/*     */         
/* 180 */         Entry e = set.getEntryForXValue(high.getX(), high.getY());
/*     */         
/* 182 */         if (isInBoundsX(e, set)) {
/*     */ 
/*     */           
/* 185 */           MPPointD pix = this.mChart.getTransformer(set.getAxisDependency()).getPixelForValues(e.getX(), e.getY() * this.mAnimator
/* 186 */               .getPhaseY());
/*     */           
/* 188 */           high.setDraw((float)pix.x, (float)pix.y);
/*     */ 
/*     */           
/* 191 */           drawHighlightLines(c, (float)pix.x, (float)pix.y, set);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\ScatterChartRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */