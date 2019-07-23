/*    */ package com.elimei.elimei.renderer;
/*    */ 
/*    */ import android.graphics.Canvas;
/*    */ import com.elimei.elimei.charts.RadarChart;
/*    */ import com.elimei.elimei.components.XAxis;
/*    */ import com.elimei.elimei.data.RadarData;
/*    */ import com.elimei.elimei.interfaces.datasets.IRadarDataSet;
/*    */ import com.elimei.elimei.utils.MPPointF;
/*    */ import com.elimei.elimei.utils.Utils;
/*    */ import com.elimei.elimei.utils.ViewPortHandler;
/*    */ 
/*    */ public class XAxisRendererRadarChart
/*    */   extends XAxisRenderer {
/*    */   private RadarChart mChart;
/*    */   
/*    */   public XAxisRendererRadarChart(ViewPortHandler viewPortHandler, XAxis xAxis, RadarChart chart) {
/* 17 */     super(viewPortHandler, xAxis, null);
/*    */     
/* 19 */     this.mChart = chart;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderAxisLabels(Canvas c) {
/* 25 */     if (!this.mXAxis.isEnabled() || !this.mXAxis.isDrawLabelsEnabled()) {
/*    */       return;
/*    */     }
/* 28 */     float labelRotationAngleDegrees = this.mXAxis.getLabelRotationAngle();
/* 29 */     MPPointF drawLabelAnchor = MPPointF.getInstance(0.5F, 0.25F);
/*    */     
/* 31 */     this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
/* 32 */     this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
/* 33 */     this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
/*    */     
/* 35 */     float sliceangle = this.mChart.getSliceAngle();
/*    */ 
/*    */ 
/*    */     
/* 39 */     float factor = this.mChart.getFactor();
/*    */     
/* 41 */     MPPointF center = this.mChart.getCenterOffsets();
/* 42 */     MPPointF pOut = MPPointF.getInstance(0.0F, 0.0F);
/* 43 */     for (int i = 0; i < ((IRadarDataSet)((RadarData)this.mChart.getData()).getMaxEntryCountSet()).getEntryCount(); i++) {
/*    */       
/* 45 */       String label = this.mXAxis.getValueFormatter().getFormattedValue(i, this.mXAxis);
/* 46 */       int color = this.mXAxis.getValueFormatter().getFormattedColor(i, this.mXAxis);
/* 47 */       float angle = (sliceangle * i + this.mChart.getRotationAngle()) % 360.0F;
/*    */       
/* 49 */       Utils.getPosition(center, this.mChart.getYRange() * factor + this.mXAxis.mLabelRotatedWidth / 2.0F, angle, pOut);
/*    */ 
/*    */       
/* 52 */       drawLabel(c, label, pOut.x, pOut.y - this.mXAxis.mLabelRotatedHeight / 2.0F, drawLabelAnchor, labelRotationAngleDegrees, color);
/*    */     } 
/*    */ 
/*    */     
/* 56 */     MPPointF.recycleInstance(center);
/* 57 */     MPPointF.recycleInstance(pOut);
/* 58 */     MPPointF.recycleInstance(drawLabelAnchor);
/*    */   }
/*    */   
/*    */   public void renderLimitLines(Canvas c) {}
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\XAxisRendererRadarChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */