/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Path;
/*     */ import com.elimei.elimei.charts.RadarChart;
/*     */ import com.elimei.elimei.components.LimitLine;
/*     */ import com.elimei.elimei.components.YAxis;
/*     */ import com.elimei.elimei.data.RadarData;
/*     */ import com.elimei.elimei.interfaces.datasets.IRadarDataSet;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ import java.util.List;
/*     */ 
/*     */ public class YAxisRendererRadarChart extends YAxisRenderer {
/*     */   private RadarChart mChart;
/*     */   private Path mRenderLimitLinesPathBuffer;
/*     */   
/*     */   public YAxisRendererRadarChart(ViewPortHandler viewPortHandler, YAxis yAxis, RadarChart chart) {
/*  20 */     super(viewPortHandler, yAxis, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     this.mRenderLimitLinesPathBuffer = new Path();
/*     */     this.mChart = chart;
/*     */   }
/*     */   
/*     */   public void renderLimitLines(Canvas c) {
/* 182 */     List<LimitLine> limitLines = this.mYAxis.getLimitLines();
/*     */     
/* 184 */     if (limitLines == null) {
/*     */       return;
/*     */     }
/* 187 */     float sliceangle = this.mChart.getSliceAngle();
/*     */ 
/*     */ 
/*     */     
/* 191 */     float factor = this.mChart.getFactor();
/*     */     
/* 193 */     MPPointF center = this.mChart.getCenterOffsets();
/* 194 */     MPPointF pOut = MPPointF.getInstance(0.0F, 0.0F);
/* 195 */     for (int i = 0; i < limitLines.size(); i++) {
/* 196 */       LimitLine l = (LimitLine)limitLines.get(i);
/* 197 */       if (l.isEnabled()) {
/*     */         
/* 199 */         this.mLimitLinePaint.setColor(l.getLineColor());
/* 200 */         this.mLimitLinePaint.setPathEffect(l.getDashPathEffect());
/* 201 */         this.mLimitLinePaint.setStrokeWidth(l.getLineWidth());
/* 202 */         float r = (l.getLimit() - this.mChart.getYChartMin()) * factor;
/*     */         
/* 204 */         Path limitPath = this.mRenderLimitLinesPathBuffer;
/* 205 */         limitPath.reset();
/*     */ 
/*     */         
/* 208 */         for (int j = 0; j < ((IRadarDataSet)((RadarData)this.mChart.getData()).getMaxEntryCountSet()).getEntryCount(); j++) {
/*     */           
/* 210 */           Utils.getPosition(center, r, sliceangle * j + this.mChart.getRotationAngle(), pOut);
/*     */           
/* 212 */           if (j == 0) {
/* 213 */             limitPath.moveTo(pOut.x, pOut.y);
/*     */           } else {
/* 215 */             limitPath.lineTo(pOut.x, pOut.y);
/*     */           } 
/* 217 */         }  limitPath.close();
/*     */         
/* 219 */         c.drawPath(limitPath, this.mLimitLinePaint);
/*     */       } 
/*     */     } 
/* 222 */     MPPointF.recycleInstance(center);
/* 223 */     MPPointF.recycleInstance(pOut);
/*     */   }
/*     */   
/*     */   protected void computeAxisValues(float min, float max) {
/*     */     float yMin = min;
/*     */     float yMax = max;
/*     */     int labelCount = this.mAxis.getLabelCount();
/*     */     double range = Math.abs(yMax - yMin);
/*     */     if (labelCount == 0 || range <= 0.0D || Double.isInfinite(range)) {
/*     */       this.mAxis.mEntries = new float[0];
/*     */       this.mAxis.mCenteredEntries = new float[0];
/*     */       this.mAxis.mEntryCount = 0;
/*     */       return;
/*     */     } 
/*     */     double rawInterval = range / labelCount;
/*     */     double interval = Utils.roundToNextSignificant(rawInterval);
/*     */     if (this.mAxis.isGranularityEnabled())
/*     */       interval = (interval < this.mAxis.getGranularity()) ? this.mAxis.getGranularity() : interval; 
/*     */     double intervalMagnitude = Utils.roundToNextSignificant(Math.pow(10.0D, (int)Math.log10(interval)));
/*     */     int intervalSigDigit = (int)(interval / intervalMagnitude);
/*     */     if (intervalSigDigit > 5)
/*     */       interval = Math.floor(10.0D * intervalMagnitude); 
/*     */     boolean centeringEnabled = this.mAxis.isCenterAxisLabelsEnabled();
/*     */     int n = centeringEnabled ? 1 : 0;
/*     */     if (this.mAxis.isForceLabelsEnabled()) {
/*     */       float step = (float)range / (labelCount - 1);
/*     */       this.mAxis.mEntryCount = labelCount;
/*     */       if (this.mAxis.mEntries.length < labelCount)
/*     */         this.mAxis.mEntries = new float[labelCount]; 
/*     */       float v = min;
/*     */       for (int i = 0; i < labelCount; i++) {
/*     */         this.mAxis.mEntries[i] = v;
/*     */         v += step;
/*     */       } 
/*     */       n = labelCount;
/*     */     } else {
/*     */       double first = (interval == 0.0D) ? 0.0D : (Math.ceil(yMin / interval) * interval);
/*     */       if (centeringEnabled)
/*     */         first -= interval; 
/*     */       double last = (interval == 0.0D) ? 0.0D : Utils.nextUp(Math.floor(yMax / interval) * interval);
/*     */       if (interval != 0.0D) {
/*     */         double f;
/*     */         for (f = first; f <= last; f += interval)
/*     */           n++; 
/*     */       } 
/*     */       this.mAxis.mEntryCount = ++n;
/*     */       if (this.mAxis.mEntries.length < n)
/*     */         this.mAxis.mEntries = new float[n]; 
/*     */       double f;
/*     */       int i;
/*     */       for (f = first, i = 0; i < n; f += interval, i++) {
/*     */         if (f == 0.0D)
/*     */           f = 0.0D; 
/*     */         this.mAxis.mEntries[i] = (float)f;
/*     */       } 
/*     */     } 
/*     */     if (interval < 1.0D) {
/*     */       this.mAxis.mDecimals = (int)Math.ceil(-Math.log10(interval));
/*     */     } else {
/*     */       this.mAxis.mDecimals = 0;
/*     */     } 
/*     */     if (centeringEnabled) {
/*     */       if (this.mAxis.mCenteredEntries.length < n)
/*     */         this.mAxis.mCenteredEntries = new float[n]; 
/*     */       float offset = (this.mAxis.mEntries[1] - this.mAxis.mEntries[0]) / 2.0F;
/*     */       for (int i = 0; i < n; i++)
/*     */         this.mAxis.mCenteredEntries[i] = this.mAxis.mEntries[i] + offset; 
/*     */     } 
/*     */     this.mAxis.mAxisMinimum = this.mAxis.mEntries[0];
/*     */     this.mAxis.mAxisMaximum = this.mAxis.mEntries[n - 1];
/*     */     this.mAxis.mAxisRange = Math.abs(this.mAxis.mAxisMaximum - this.mAxis.mAxisMinimum);
/*     */   }
/*     */   
/*     */   public void renderAxisLabels(Canvas c) {
/*     */     if (!this.mYAxis.isEnabled() || !this.mYAxis.isDrawLabelsEnabled())
/*     */       return; 
/*     */     this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
/*     */     this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
/*     */     this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
/*     */     MPPointF center = this.mChart.getCenterOffsets();
/*     */     MPPointF pOut = MPPointF.getInstance(0.0F, 0.0F);
/*     */     float factor = this.mChart.getFactor();
/*     */     int from = this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 0 : 1;
/*     */     int to = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : (this.mYAxis.mEntryCount - 1);
/*     */     for (int j = from; j < to; j++) {
/*     */       float r = (this.mYAxis.mEntries[j] - this.mYAxis.mAxisMinimum) * factor;
/*     */       Utils.getPosition(center, r, this.mChart.getRotationAngle(), pOut);
/*     */       String label = this.mYAxis.getFormattedLabel(j);
/*     */       c.drawText(label, pOut.x + 10.0F, pOut.y, this.mAxisLabelPaint);
/*     */     } 
/*     */     MPPointF.recycleInstance(center);
/*     */     MPPointF.recycleInstance(pOut);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\YAxisRendererRadarChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */