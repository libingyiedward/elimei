/*     */ package com.elimei.elimei.charts;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.RectF;
/*     */ import android.util.AttributeSet;
/*     */ import com.elimei.elimei.components.YAxis;
/*     */ import com.elimei.elimei.data.RadarData;
/*     */ import com.elimei.elimei.highlight.RadarHighlighter;
/*     */ import com.elimei.elimei.interfaces.datasets.IRadarDataSet;
/*     */ import com.elimei.elimei.renderer.RadarChartRenderer;
/*     */ import com.elimei.elimei.renderer.XAxisRendererRadarChart;
/*     */ import com.elimei.elimei.renderer.YAxisRendererRadarChart;
/*     */ import com.elimei.elimei.utils.Utils;
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
/*     */ public class RadarChart
/*     */   extends PieRadarChartBase<RadarData>
/*     */ {
/*  30 */   private float mWebLineWidth = 2.5F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private float mInnerWebLineWidth = 1.5F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   private int mWebColor = Color.rgb(122, 122, 122);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   private int mWebColorInner = Color.rgb(122, 122, 122);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   private int mWebAlpha = 150;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean mDrawWeb = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private int mSkipWebLineCount = 0;
/*     */ 
/*     */   
/*     */   private YAxis mYAxis;
/*     */ 
/*     */   
/*     */   protected YAxisRendererRadarChart mYAxisRenderer;
/*     */   
/*     */   protected XAxisRendererRadarChart mXAxisRenderer;
/*     */ 
/*     */   
/*  71 */   public RadarChart(Context context) { super(context); }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public RadarChart(Context context, AttributeSet attrs) { super(context, attrs); }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public RadarChart(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() {
/*  84 */     super.init();
/*     */     
/*  86 */     this.mYAxis = new YAxis(YAxis.AxisDependency.LEFT);
/*     */     
/*  88 */     this.mWebLineWidth = Utils.convertDpToPixel(1.5F);
/*  89 */     this.mInnerWebLineWidth = Utils.convertDpToPixel(0.75F);
/*     */     
/*  91 */     this.mRenderer = new RadarChartRenderer(this, this.mAnimator, this.mViewPortHandler);
/*  92 */     this.mYAxisRenderer = new YAxisRendererRadarChart(this.mViewPortHandler, this.mYAxis, this);
/*  93 */     this.mXAxisRenderer = new XAxisRendererRadarChart(this.mViewPortHandler, this.mXAxis, this);
/*     */     
/*  95 */     this.mHighlighter = new RadarHighlighter(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void calcMinMax() {
/* 100 */     super.calcMinMax();
/*     */     
/* 102 */     this.mYAxis.calculate(((RadarData)this.mData).getYMin(YAxis.AxisDependency.LEFT), ((RadarData)this.mData).getYMax(YAxis.AxisDependency.LEFT));
/* 103 */     this.mXAxis.calculate(0.0F, ((IRadarDataSet)((RadarData)this.mData).getMaxEntryCountSet()).getEntryCount());
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifyDataSetChanged() {
/* 108 */     if (this.mData == null) {
/*     */       return;
/*     */     }
/* 111 */     calcMinMax();
/*     */     
/* 113 */     this.mYAxisRenderer.computeAxis(this.mYAxis.mAxisMinimum, this.mYAxis.mAxisMaximum, this.mYAxis.isInverted());
/* 114 */     this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
/*     */     
/* 116 */     if (this.mLegend != null && !this.mLegend.isLegendCustom()) {
/* 117 */       this.mLegendRenderer.computeLegend(this.mData);
/*     */     }
/* 119 */     calculateOffsets();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 124 */     super.onDraw(canvas);
/*     */     
/* 126 */     if (this.mData == null) {
/*     */       return;
/*     */     }
/* 129 */     if (this.mYAxis.isEnabled()) {
/* 130 */       this.mYAxisRenderer.computeAxis(this.mYAxis.mAxisMinimum, this.mYAxis.mAxisMaximum, this.mYAxis.isInverted());
/*     */     }
/* 132 */     if (this.mXAxis.isEnabled()) {
/* 133 */       this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
/*     */     }
/* 135 */     this.mXAxisRenderer.renderAxisLabels(canvas);
/*     */     
/* 137 */     if (this.mDrawWeb) {
/* 138 */       this.mRenderer.drawExtras(canvas);
/*     */     }
/* 140 */     if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLimitLinesBehindDataEnabled()) {
/* 141 */       this.mYAxisRenderer.renderLimitLines(canvas);
/*     */     }
/* 143 */     this.mRenderer.drawData(canvas);
/*     */     
/* 145 */     if (valuesToHighlight()) {
/* 146 */       this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
/*     */     }
/* 148 */     if (this.mYAxis.isEnabled() && !this.mYAxis.isDrawLimitLinesBehindDataEnabled()) {
/* 149 */       this.mYAxisRenderer.renderLimitLines(canvas);
/*     */     }
/* 151 */     this.mYAxisRenderer.renderAxisLabels(canvas);
/*     */     
/* 153 */     this.mRenderer.drawValues(canvas);
/*     */     
/* 155 */     this.mLegendRenderer.renderLegend(canvas);
/*     */     
/* 157 */     drawDescription(canvas);
/*     */     
/* 159 */     drawMarkers(canvas);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFactor() {
/* 168 */     RectF content = this.mViewPortHandler.getContentRect();
/* 169 */     return Math.min(content.width() / 2.0F, content.height() / 2.0F) / this.mYAxis.mAxisRange;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public float getSliceAngle() { return 360.0F / ((IRadarDataSet)((RadarData)this.mData).getMaxEntryCountSet()).getEntryCount(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndexForAngle(float angle) {
/* 185 */     float a = Utils.getNormalizedAngle(angle - getRotationAngle());
/*     */     
/* 187 */     float sliceangle = getSliceAngle();
/*     */     
/* 189 */     int max = ((IRadarDataSet)((RadarData)this.mData).getMaxEntryCountSet()).getEntryCount();
/*     */     
/* 191 */     int index = 0;
/*     */     
/* 193 */     for (int i = 0; i < max; i++) {
/*     */       
/* 195 */       float referenceAngle = sliceangle * (i + 1) - sliceangle / 2.0F;
/*     */       
/* 197 */       if (referenceAngle > a) {
/* 198 */         index = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 203 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public YAxis getYAxis() { return this.mYAxis; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   public void setWebLineWidth(float width) { this.mWebLineWidth = Utils.convertDpToPixel(width); }
/*     */ 
/*     */ 
/*     */   
/* 225 */   public float getWebLineWidth() { return this.mWebLineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public void setWebLineWidthInner(float width) { this.mInnerWebLineWidth = Utils.convertDpToPixel(width); }
/*     */ 
/*     */ 
/*     */   
/* 239 */   public float getWebLineWidthInner() { return this.mInnerWebLineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public void setWebAlpha(int alpha) { this.mWebAlpha = alpha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public int getWebAlpha() { return this.mWebAlpha; }
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
/* 269 */   public void setWebColor(int color) { this.mWebColor = color; }
/*     */ 
/*     */ 
/*     */   
/* 273 */   public int getWebColor() { return this.mWebColor; }
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
/* 284 */   public void setWebColorInner(int color) { this.mWebColorInner = color; }
/*     */ 
/*     */ 
/*     */   
/* 288 */   public int getWebColorInner() { return this.mWebColorInner; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public void setDrawWeb(boolean enabled) { this.mDrawWeb = enabled; }
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
/* 309 */   public void setSkipWebLineCount(int count) { this.mSkipWebLineCount = Math.max(0, count); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   public int getSkipWebLineCount() { return this.mSkipWebLineCount; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 323 */   protected float getRequiredLegendOffset() { return this.mLegendRenderer.getLabelPaint().getTextSize() * 4.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 328 */   protected float getRequiredBaseOffset() { return (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) ? this.mXAxis.mLabelRotatedWidth : 
/*     */       
/* 330 */       Utils.convertDpToPixel(10.0F); }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getRadius() {
/* 335 */     RectF content = this.mViewPortHandler.getContentRect();
/* 336 */     return Math.min(content.width() / 2.0F, content.height() / 2.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public float getYChartMax() { return this.mYAxis.mAxisMaximum; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 350 */   public float getYChartMin() { return this.mYAxis.mAxisMinimum; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public float getYRange() { return this.mYAxis.mAxisRange; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\RadarChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */