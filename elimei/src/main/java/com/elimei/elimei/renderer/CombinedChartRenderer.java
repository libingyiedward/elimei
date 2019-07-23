/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import com.elimei.elimei.animation.ChartAnimator;
/*     */ import com.elimei.elimei.charts.Chart;
/*     */ import com.elimei.elimei.charts.CombinedChart;
/*     */ import com.elimei.elimei.data.BarData;
/*     */ import com.elimei.elimei.data.BubbleData;
/*     */ import com.elimei.elimei.data.CandleData;
/*     */ import com.elimei.elimei.data.CombinedData;
/*     */ import com.elimei.elimei.data.LineData;
/*     */ import com.elimei.elimei.data.ScatterData;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CombinedChartRenderer
/*     */   extends DataRenderer
/*     */ {
/*  26 */   protected List<DataRenderer> mRenderers = new ArrayList(5);
/*     */   protected WeakReference<Chart> mChart; protected List<Highlight> mHighlightBuffer; public void createRenderers() { this.mRenderers.clear(); CombinedChart chart = (CombinedChart)this.mChart.get(); if (chart == null) return;  CombinedChart.DrawOrder[] arrayOfDrawOrder = chart.getDrawOrder(); for (CombinedChart.DrawOrder order : arrayOfDrawOrder) { switch (order) { case BAR: if (chart.getBarData() != null) this.mRenderers.add(new BarChartRenderer(chart, this.mAnimator, this.mViewPortHandler));  break;case BUBBLE: if (chart.getBubbleData() != null)
/*     */             this.mRenderers.add(new BubbleChartRenderer(chart, this.mAnimator, this.mViewPortHandler));  break;case LINE: if (chart.getLineData() != null)
/*     */             this.mRenderers.add(new LineChartRenderer(chart, this.mAnimator, this.mViewPortHandler));  break;case CANDLE: if (chart.getCandleData() != null)
/*     */             this.mRenderers.add(new CandleStickChartRenderer(chart, this.mAnimator, this.mViewPortHandler));  break;case SCATTER: if (chart.getScatterData() != null)
/*  31 */             this.mRenderers.add(new ScatterChartRenderer(chart, this.mAnimator, this.mViewPortHandler));  break; }  }  } public CombinedChartRenderer(CombinedChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) { super(animator, viewPortHandler);
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
/* 105 */     this.mHighlightBuffer = new ArrayList();
/*     */     this.mChart = new WeakReference(chart);
/*     */     createRenderers(); }
/*     */   public void initBuffers() { for (DataRenderer renderer : this.mRenderers)
/*     */       renderer.initBuffers();  } public void drawHighlighted(Canvas c, Highlight[] indices) {
/* 110 */     Chart chart = (Chart)this.mChart.get();
/* 111 */     if (chart == null)
/*     */       return; 
/* 113 */     for (DataRenderer renderer : this.mRenderers) {
/* 114 */       BubbleData bubbleData = null;
/*     */       
/* 116 */       if (renderer instanceof BarChartRenderer) {
/* 117 */         BarData barData = ((BarChartRenderer)renderer).mChart.getBarData();
/* 118 */       } else if (renderer instanceof LineChartRenderer) {
/* 119 */         LineData lineData = ((LineChartRenderer)renderer).mChart.getLineData();
/* 120 */       } else if (renderer instanceof CandleStickChartRenderer) {
/* 121 */         CandleData candleData = ((CandleStickChartRenderer)renderer).mChart.getCandleData();
/* 122 */       } else if (renderer instanceof ScatterChartRenderer) {
/* 123 */         ScatterData scatterData = ((ScatterChartRenderer)renderer).mChart.getScatterData();
/* 124 */       } else if (renderer instanceof BubbleChartRenderer) {
/* 125 */         bubbleData = ((BubbleChartRenderer)renderer).mChart.getBubbleData();
/*     */       } 
/*     */       
/* 128 */       int dataIndex = (bubbleData == null) ? -1 : ((CombinedData)chart.getData()).getAllData().indexOf(bubbleData);
/*     */       
/* 130 */       this.mHighlightBuffer.clear();
/*     */       
/* 132 */       for (Highlight h : indices) {
/* 133 */         if (h.getDataIndex() == dataIndex || h.getDataIndex() == -1) {
/* 134 */           this.mHighlightBuffer.add(h);
/*     */         }
/*     */       } 
/* 137 */       renderer.drawHighlighted(c, (Highlight[])this.mHighlightBuffer.toArray(new Highlight[this.mHighlightBuffer.size()]));
/*     */     }  } public void drawData(Canvas c) {
/*     */     for (DataRenderer renderer : this.mRenderers)
/*     */       renderer.drawData(c); 
/*     */   } public void drawValues(Canvas c) {
/*     */     for (DataRenderer renderer : this.mRenderers)
/*     */       renderer.drawValues(c); 
/*     */   } public void drawExtras(Canvas c) {
/*     */     for (DataRenderer renderer : this.mRenderers)
/*     */       renderer.drawExtras(c); 
/*     */   } public DataRenderer getSubRenderer(int index) {
/* 148 */     if (index >= this.mRenderers.size() || index < 0) {
/* 149 */       return null;
/*     */     }
/* 151 */     return (DataRenderer)this.mRenderers.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public List<DataRenderer> getSubRenderers() { return this.mRenderers; }
/*     */ 
/*     */ 
/*     */   
/* 164 */   public void setSubRenderers(List<DataRenderer> renderers) { this.mRenderers = renderers; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\CombinedChartRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */