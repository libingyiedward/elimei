/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import android.graphics.DashPathEffect;
/*     */ import com.elimei.elimei.interfaces.datasets.ILineScatterCandleRadarDataSet;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class LineScatterCandleRadarDataSet<T extends Entry>
/*     */   extends BarLineScatterCandleBubbleDataSet<T>
/*     */   implements ILineScatterCandleRadarDataSet<T>
/*     */ {
/*     */   protected boolean mDrawVerticalHighlightIndicator = true;
/*     */   protected boolean mDrawHorizontalHighlightIndicator = true;
/*  19 */   protected float mHighlightLineWidth = 0.5F;
/*     */ 
/*     */   
/*  22 */   protected DashPathEffect mHighlightDashPathEffect = null;
/*     */ 
/*     */   
/*     */   public LineScatterCandleRadarDataSet(List<T> yVals, String label) {
/*  26 */     super(yVals, label);
/*  27 */     this.mHighlightLineWidth = Utils.convertDpToPixel(0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public void setDrawHorizontalHighlightIndicator(boolean enabled) { this.mDrawHorizontalHighlightIndicator = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public void setDrawVerticalHighlightIndicator(boolean enabled) { this.mDrawVerticalHighlightIndicator = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDrawHighlightIndicators(boolean enabled) {
/*  51 */     setDrawVerticalHighlightIndicator(enabled);
/*  52 */     setDrawHorizontalHighlightIndicator(enabled);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public boolean isVerticalHighlightIndicatorEnabled() { return this.mDrawVerticalHighlightIndicator; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public boolean isHorizontalHighlightIndicatorEnabled() { return this.mDrawHorizontalHighlightIndicator; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public void setHighlightLineWidth(float width) { this.mHighlightLineWidth = Utils.convertDpToPixel(width); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public float getHighlightLineWidth() { return this.mHighlightLineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public void enableDashedHighlightLine(float lineLength, float spaceLength, float phase) { this.mHighlightDashPathEffect = new DashPathEffect(new float[] { lineLength, spaceLength }, phase); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void disableDashedHighlightLine() { this.mHighlightDashPathEffect = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public boolean isDashedHighlightLineEnabled() { return !(this.mHighlightDashPathEffect == null); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public DashPathEffect getDashPathEffectHighlight() { return this.mHighlightDashPathEffect; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\LineScatterCandleRadarDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */