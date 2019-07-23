/*     */ package com.elimei.elimei.charts;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.RectF;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.Log;
/*     */ import com.elimei.elimei.components.XAxis;
/*     */ import com.elimei.elimei.components.YAxis;
/*     */ import com.elimei.elimei.data.BarData;
/*     */ import com.elimei.elimei.data.BarEntry;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.highlight.HorizontalBarHighlighter;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
/*     */ import com.elimei.elimei.renderer.HorizontalBarChartRenderer;
/*     */ import com.elimei.elimei.renderer.XAxisRendererHorizontalBarChart;
/*     */ import com.elimei.elimei.renderer.YAxisRendererHorizontalBarChart;
/*     */ import com.elimei.elimei.utils.HorizontalViewPortHandler;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import com.elimei.elimei.utils.TransformerHorizontalBarChart;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HorizontalBarChart
/*     */   extends BarChart
/*     */ {
/*     */   private RectF mOffsetsBuffer;
/*     */   protected float[] mGetPositionBuffer;
/*     */   
/*     */   public HorizontalBarChart(Context context) {
/*  32 */     super(context);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     this.mOffsetsBuffer = new RectF();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     this.mGetPositionBuffer = new float[2]; } public HorizontalBarChart(Context context, AttributeSet attrs) { super(context, attrs); this.mOffsetsBuffer = new RectF(); this.mGetPositionBuffer = new float[2]; } protected void init() { this.mViewPortHandler = new HorizontalViewPortHandler(); super.init(); this.mLeftAxisTransformer = new TransformerHorizontalBarChart(this.mViewPortHandler); this.mRightAxisTransformer = new TransformerHorizontalBarChart(this.mViewPortHandler); this.mRenderer = new HorizontalBarChartRenderer(this, this.mAnimator, this.mViewPortHandler); setHighlighter(new HorizontalBarHighlighter(this)); this.mAxisRendererLeft = new YAxisRendererHorizontalBarChart(this.mViewPortHandler, this.mAxisLeft, this.mLeftAxisTransformer); this.mAxisRendererRight = new YAxisRendererHorizontalBarChart(this.mViewPortHandler, this.mAxisRight, this.mRightAxisTransformer); this.mXAxisRenderer = new XAxisRendererHorizontalBarChart(this.mViewPortHandler, this.mXAxis, this.mLeftAxisTransformer, this); } public HorizontalBarChart(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); this.mOffsetsBuffer = new RectF(); this.mGetPositionBuffer = new float[2]; }
/*     */   public void calculateOffsets() { float offsetLeft = 0.0F, offsetRight = 0.0F, offsetTop = 0.0F, offsetBottom = 0.0F; calculateLegendOffsets(this.mOffsetsBuffer); offsetLeft += this.mOffsetsBuffer.left; offsetTop += this.mOffsetsBuffer.top; offsetRight += this.mOffsetsBuffer.right; offsetBottom += this.mOffsetsBuffer.bottom; if (this.mAxisLeft.needsOffset())
/*     */       offsetTop += this.mAxisLeft.getRequiredHeightSpace(this.mAxisRendererLeft.getPaintAxisLabels());  if (this.mAxisRight.needsOffset())
/*     */       offsetBottom += this.mAxisRight.getRequiredHeightSpace(this.mAxisRendererRight.getPaintAxisLabels());  float xlabelwidth = this.mXAxis.mLabelRotatedWidth; if (this.mXAxis.isEnabled())
/*     */       if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
/*     */         offsetLeft += xlabelwidth;
/*     */       } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP) {
/*     */         offsetRight += xlabelwidth;
/*     */       } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
/*     */         offsetLeft += xlabelwidth; offsetRight += xlabelwidth;
/*     */       }   offsetTop += getExtraTopOffset(); offsetRight += getExtraRightOffset(); offsetBottom += getExtraBottomOffset(); offsetLeft += getExtraLeftOffset(); float minOffset = Utils.convertDpToPixel(this.mMinOffset); this.mViewPortHandler.restrainViewPort(Math.max(minOffset, offsetLeft), Math.max(minOffset, offsetTop), Math.max(minOffset, offsetRight), Math.max(minOffset, offsetBottom)); if (this.mLogEnabled) {
/*     */       Log.i("MPAndroidChart", "offsetLeft: " + offsetLeft + ", offsetTop: " + offsetTop + ", offsetRight: " + offsetRight + ", offsetBottom: " + offsetBottom); Log.i("MPAndroidChart", "Content: " + this.mViewPortHandler.getContentRect().toString());
/* 180 */     }  prepareOffsetMatrix(); prepareValuePxMatrix(); } public MPPointF getPosition(Entry e, YAxis.AxisDependency axis) { if (e == null) {
/* 181 */       return null;
/*     */     }
/* 183 */     float[] vals = this.mGetPositionBuffer;
/* 184 */     vals[0] = e.getY();
/* 185 */     vals[1] = e.getX();
/*     */     
/* 187 */     getTransformer(axis).pointValuesToPixel(vals);
/*     */     
/* 189 */     return MPPointF.getInstance(vals[0], vals[1]); } protected void prepareValuePxMatrix() { this.mRightAxisTransformer.prepareMatrixValuePx(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisRange, this.mXAxis.mAxisRange, this.mXAxis.mAxisMinimum); this.mLeftAxisTransformer.prepareMatrixValuePx(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisRange, this.mXAxis.mAxisRange, this.mXAxis.mAxisMinimum); } protected float[] getMarkerPosition(Highlight high) { return new float[] { high.getDrawY(), high.getDrawX() }; }
/*     */   public void getBarBounds(BarEntry e, RectF outputRect) { RectF bounds = outputRect; IBarDataSet set = (IBarDataSet)((BarData)this.mData).getDataSetForEntry(e); if (set == null) {
/*     */       outputRect.set(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
/*     */       return;
/*     */     } 
/*     */     float y = e.getY();
/*     */     float x = e.getX();
/*     */     float barWidth = ((BarData)this.mData).getBarWidth();
/*     */     float top = x - barWidth / 2.0F;
/*     */     float bottom = x + barWidth / 2.0F;
/*     */     float left = (y >= 0.0F) ? y : 0.0F;
/*     */     float right = (y <= 0.0F) ? y : 0.0F;
/*     */     bounds.set(left, top, right, bottom);
/*     */     getTransformer(set.getAxisDependency()).rectValueToPixel(bounds); }
/* 203 */   public Highlight getHighlightByTouchPoint(float x, float y) { if (this.mData == null) {
/* 204 */       if (this.mLogEnabled)
/* 205 */         Log.e("MPAndroidChart", "Can't select by touch. No data set."); 
/* 206 */       return null;
/*     */     } 
/* 208 */     return getHighlighter().getHighlight(y, x); }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLowestVisibleX() {
/* 213 */     getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler
/* 214 */         .contentBottom(), this.posForGetLowestVisibleX);
/* 215 */     return (float)Math.max(this.mXAxis.mAxisMinimum, this.posForGetLowestVisibleX.y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHighestVisibleX() {
/* 221 */     getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler
/* 222 */         .contentTop(), this.posForGetHighestVisibleX);
/* 223 */     return (float)Math.min(this.mXAxis.mAxisMaximum, this.posForGetHighestVisibleX.y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisibleXRangeMaximum(float maxXRange) {
/* 233 */     float xScale = this.mXAxis.mAxisRange / maxXRange;
/* 234 */     this.mViewPortHandler.setMinimumScaleY(xScale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisibleXRangeMinimum(float minXRange) {
/* 239 */     float xScale = this.mXAxis.mAxisRange / minXRange;
/* 240 */     this.mViewPortHandler.setMaximumScaleY(xScale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisibleXRange(float minXRange, float maxXRange) {
/* 245 */     float minScale = this.mXAxis.mAxisRange / minXRange;
/* 246 */     float maxScale = this.mXAxis.mAxisRange / maxXRange;
/* 247 */     this.mViewPortHandler.setMinMaxScaleY(minScale, maxScale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisibleYRangeMaximum(float maxYRange, YAxis.AxisDependency axis) {
/* 252 */     float yScale = getAxisRange(axis) / maxYRange;
/* 253 */     this.mViewPortHandler.setMinimumScaleX(yScale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisibleYRangeMinimum(float minYRange, YAxis.AxisDependency axis) {
/* 258 */     float yScale = getAxisRange(axis) / minYRange;
/* 259 */     this.mViewPortHandler.setMaximumScaleX(yScale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisibleYRange(float minYRange, float maxYRange, YAxis.AxisDependency axis) {
/* 264 */     float minScale = getAxisRange(axis) / minYRange;
/* 265 */     float maxScale = getAxisRange(axis) / maxYRange;
/* 266 */     this.mViewPortHandler.setMinMaxScaleX(minScale, maxScale);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\HorizontalBarChart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */