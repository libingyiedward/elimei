/*     */ package com.elimei.elimei.charts;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.Log;
/*     */ import com.elimei.elimei.data.BarData;
/*     */ import com.elimei.elimei.data.BubbleData;
/*     */ import com.elimei.elimei.data.CandleData;
/*     */ import com.elimei.elimei.data.ChartData;
/*     */ import com.elimei.elimei.data.CombinedData;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.data.LineData;
/*     */ import com.elimei.elimei.data.ScatterData;
/*     */ import com.elimei.elimei.highlight.CombinedHighlighter;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.dataprovider.CombinedDataProvider;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
/*     */ import com.elimei.elimei.renderer.CombinedChartRenderer;
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
/*     */ public class CombinedChart
/*     */   extends BarLineChartBase<CombinedData>
/*     */   implements CombinedDataProvider
/*     */ {
/*     */   private boolean mDrawValueAboveBar = true;
/*     */   protected boolean mHighlightFullBarEnabled = false;
/*     */   private boolean mDrawBarShadow = false;
/*     */   protected DrawOrder[] mDrawOrder;
/*     */   
/*     */   public enum DrawOrder
/*     */   {
/*  55 */     BAR, BUBBLE, LINE, CANDLE, SCATTER;
/*     */   }
/*     */ 
/*     */   
/*  59 */   public CombinedChart(Context context) { super(context); }
/*     */ 
/*     */ 
/*     */   
/*  63 */   public CombinedChart(Context context, AttributeSet attrs) { super(context, attrs); }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public CombinedChart(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() {
/*  72 */     super.init();
/*     */ 
/*     */     
/*  75 */     this.mDrawOrder = new DrawOrder[] { DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER };
/*     */ 
/*     */ 
/*     */     
/*  79 */     setHighlighter(new CombinedHighlighter(this, this));
/*     */ 
/*     */     
/*  82 */     setHighlightFullBarEnabled(true);
/*     */     
/*  84 */     this.mRenderer = new CombinedChartRenderer(this, this.mAnimator, this.mViewPortHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public CombinedData getCombinedData() { return (CombinedData)this.mData; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(CombinedData data) {
/*  94 */     super.setData(data);
/*  95 */     setHighlighter(new CombinedHighlighter(this, this));
/*  96 */     ((CombinedChartRenderer)this.mRenderer).createRenderers();
/*  97 */     this.mRenderer.initBuffers();
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
/*     */   public Highlight getHighlightByTouchPoint(float x, float y) {
/* 112 */     if (this.mData == null) {
/* 113 */       Log.e("MPAndroidChart", "Can't select by touch. No data set.");
/* 114 */       return null;
/*     */     } 
/* 116 */     Highlight h = getHighlighter().getHighlight(x, y);
/* 117 */     if (h == null || !isHighlightFullBarEnabled()) return h;
/*     */ 
/*     */     
/* 120 */     return new Highlight(h.getX(), h.getY(), h
/* 121 */         .getXPx(), h.getYPx(), h
/* 122 */         .getDataSetIndex(), -1, h.getAxis());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LineData getLineData() {
/* 128 */     if (this.mData == null)
/* 129 */       return null; 
/* 130 */     return ((CombinedData)this.mData).getLineData();
/*     */   }
/*     */ 
/*     */   
/*     */   public BarData getBarData() {
/* 135 */     if (this.mData == null)
/* 136 */       return null; 
/* 137 */     return ((CombinedData)this.mData).getBarData();
/*     */   }
/*     */ 
/*     */   
/*     */   public ScatterData getScatterData() {
/* 142 */     if (this.mData == null)
/* 143 */       return null; 
/* 144 */     return ((CombinedData)this.mData).getScatterData();
/*     */   }
/*     */ 
/*     */   
/*     */   public CandleData getCandleData() {
/* 149 */     if (this.mData == null)
/* 150 */       return null; 
/* 151 */     return ((CombinedData)this.mData).getCandleData();
/*     */   }
/*     */ 
/*     */   
/*     */   public BubbleData getBubbleData() {
/* 156 */     if (this.mData == null)
/* 157 */       return null; 
/* 158 */     return ((CombinedData)this.mData).getBubbleData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 163 */   public boolean isDrawBarShadowEnabled() { return this.mDrawBarShadow; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   public boolean isDrawValueAboveBarEnabled() { return this.mDrawValueAboveBar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public void setDrawValueAboveBar(boolean enabled) { this.mDrawValueAboveBar = enabled; }
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
/* 189 */   public void setDrawBarShadow(boolean enabled) { this.mDrawBarShadow = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public void setHighlightFullBarEnabled(boolean enabled) { this.mHighlightFullBarEnabled = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public boolean isHighlightFullBarEnabled() { return this.mHighlightFullBarEnabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public DrawOrder[] getDrawOrder() { return this.mDrawOrder; }
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
/*     */   public void setDrawOrder(DrawOrder[] order) {
/* 228 */     if (order == null || order.length <= 0)
/*     */       return; 
/* 230 */     this.mDrawOrder = order;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawMarkers(Canvas canvas) {
/* 239 */     if (this.mMarker == null || !isDrawMarkersEnabled() || !valuesToHighlight()) {
/*     */       return;
/*     */     }
/* 242 */     for (int i = 0; i < this.mIndicesToHighlight.length; i++) {
/*     */       
/* 244 */       Highlight highlight = this.mIndicesToHighlight[i];
/*     */       
/* 246 */       IBarLineScatterCandleBubbleDataSet iBarLineScatterCandleBubbleDataSet = ((CombinedData)this.mData).getDataSetByHighlight(highlight);
/*     */       
/* 248 */       Entry e = ((CombinedData)this.mData).getEntryForHighlight(highlight);
/* 249 */       if (e != null) {
/*     */ 
/*     */         
/* 252 */         int entryIndex = iBarLineScatterCandleBubbleDataSet.getEntryIndex(e);
/*     */ 
/*     */         
/* 255 */         if (entryIndex <= iBarLineScatterCandleBubbleDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
/*     */ 
/*     */           
/* 258 */           float[] pos = getMarkerPosition(highlight);
/*     */ 
/*     */           
/* 261 */           if (this.mViewPortHandler.isInBounds(pos[0], pos[1])) {
/*     */ 
/*     */ 
/*     */             
/* 265 */             this.mMarker.refreshContent(e, highlight);
/*     */ 
/*     */             
/* 268 */             this.mMarker.draw(canvas, pos[0], pos[1]);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\CombinedChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */