/*     */ package com.elimei.elimei.charts;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.RectF;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.Log;
/*     */ import com.elimei.elimei.components.YAxis;
/*     */ import com.elimei.elimei.data.BarData;
/*     */ import com.elimei.elimei.data.BarEntry;
/*     */ import com.elimei.elimei.highlight.BarHighlighter;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.dataprovider.BarDataProvider;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
/*     */ import com.elimei.elimei.renderer.BarChartRenderer;
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
/*     */ public class BarChart
/*     */   extends BarLineChartBase<BarData>
/*     */   implements BarDataProvider
/*     */ {
/*     */   protected boolean mHighlightFullBarEnabled = false;
/*     */   private boolean mDrawValueAboveBar = true;
/*     */   private boolean mDrawBarShadow = false;
/*     */   private boolean mFitBars = false;
/*     */   
/*  43 */   public BarChart(Context context) { super(context); }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public BarChart(Context context, AttributeSet attrs) { super(context, attrs); }
/*     */ 
/*     */ 
/*     */   
/*  51 */   public BarChart(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() {
/*  56 */     super.init();
/*     */     
/*  58 */     this.mRenderer = new BarChartRenderer(this, this.mAnimator, this.mViewPortHandler);
/*     */     
/*  60 */     setHighlighter(new BarHighlighter(this));
/*     */     
/*  62 */     getXAxis().setSpaceMin(0.5F);
/*  63 */     getXAxis().setSpaceMax(0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void calcMinMax() {
/*  69 */     if (this.mFitBars) {
/*  70 */       this.mXAxis.calculate(((BarData)this.mData).getXMin() - ((BarData)this.mData).getBarWidth() / 2.0F, ((BarData)this.mData).getXMax() + ((BarData)this.mData).getBarWidth() / 2.0F);
/*     */     } else {
/*  72 */       this.mXAxis.calculate(((BarData)this.mData).getXMin(), ((BarData)this.mData).getXMax());
/*     */     } 
/*     */ 
/*     */     
/*  76 */     this.mAxisLeft.calculate(((BarData)this.mData).getYMin(YAxis.AxisDependency.LEFT), ((BarData)this.mData).getYMax(YAxis.AxisDependency.LEFT));
/*  77 */     this.mAxisRight.calculate(((BarData)this.mData).getYMin(YAxis.AxisDependency.RIGHT), ((BarData)this.mData).getYMax(YAxis.AxisDependency.RIGHT));
/*     */   }
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
/*     */   public Highlight getHighlightByTouchPoint(float x, float y) {
/*  93 */     if (this.mData == null) {
/*  94 */       Log.e("MPAndroidChart", "Can't select by touch. No data set.");
/*  95 */       return null;
/*     */     } 
/*  97 */     Highlight h = getHighlighter().getHighlight(x, y);
/*  98 */     if (h == null || !isHighlightFullBarEnabled()) return h;
/*     */ 
/*     */     
/* 101 */     return new Highlight(h.getX(), h.getY(), h
/* 102 */         .getXPx(), h.getYPx(), h
/* 103 */         .getDataSetIndex(), -1, h.getAxis());
/*     */   }
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
/*     */   public RectF getBarBounds(BarEntry e) {
/* 116 */     RectF bounds = new RectF();
/* 117 */     getBarBounds(e, bounds);
/*     */     
/* 119 */     return bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getBarBounds(BarEntry e, RectF outputRect) {
/* 131 */     RectF bounds = outputRect;
/*     */     
/* 133 */     IBarDataSet set = (IBarDataSet)((BarData)this.mData).getDataSetForEntry(e);
/*     */     
/* 135 */     if (set == null) {
/* 136 */       bounds.set(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
/*     */       
/*     */       return;
/*     */     } 
/* 140 */     float y = e.getY();
/* 141 */     float x = e.getX();
/*     */     
/* 143 */     float barWidth = ((BarData)this.mData).getBarWidth();
/*     */     
/* 145 */     float left = x - barWidth / 2.0F;
/* 146 */     float right = x + barWidth / 2.0F;
/* 147 */     float top = (y >= 0.0F) ? y : 0.0F;
/* 148 */     float bottom = (y <= 0.0F) ? y : 0.0F;
/*     */     
/* 150 */     bounds.set(left, top, right, bottom);
/*     */     
/* 152 */     getTransformer(set.getAxisDependency()).rectValueToPixel(outputRect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public void setDrawValueAboveBar(boolean enabled) { this.mDrawValueAboveBar = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public boolean isDrawValueAboveBarEnabled() { return this.mDrawValueAboveBar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public void setDrawBarShadow(boolean enabled) { this.mDrawBarShadow = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public boolean isDrawBarShadowEnabled() { return this.mDrawBarShadow; }
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
/* 201 */   public void setHighlightFullBarEnabled(boolean enabled) { this.mHighlightFullBarEnabled = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public boolean isHighlightFullBarEnabled() { return this.mHighlightFullBarEnabled; }
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
/* 221 */   public void highlightValue(float x, int dataSetIndex, int stackIndex) { highlightValue(new Highlight(x, dataSetIndex, stackIndex), false); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public BarData getBarData() { return (BarData)this.mData; }
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
/* 237 */   public void setFitBars(boolean enabled) { this.mFitBars = enabled; }
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
/*     */   public void groupBars(float fromX, float groupSpace, float barSpace) {
/* 252 */     if (getBarData() == null) {
/* 253 */       throw new RuntimeException("You need to set data for the chart before grouping bars.");
/*     */     }
/* 255 */     getBarData().groupBars(fromX, groupSpace, barSpace);
/* 256 */     notifyDataSetChanged();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\BarChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */