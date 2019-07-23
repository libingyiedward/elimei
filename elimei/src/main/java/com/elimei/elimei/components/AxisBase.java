/*     */ package com.elimei.elimei.components;
/*     */ 
/*     */ import android.graphics.DashPathEffect;
/*     */ import android.util.Log;
/*     */ import com.elimei.elimei.formatter.DefaultAxisValueFormatter;
/*     */ import com.elimei.elimei.formatter.IAxisValueFormatter;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AxisBase
/*     */   extends ComponentBase
/*     */ {
/*  27 */   private int mGridColor = -7829368;
/*     */   
/*  29 */   private float mGridLineWidth = 1.0F;
/*     */   
/*  31 */   private int mAxisLineColor = -7829368;
/*     */   
/*  33 */   private float mAxisLineWidth = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public float[] mEntries = new float[0];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public float[] mCenteredEntries = new float[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   private int mLabelCount = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected float mGranularity = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mGranularityEnabled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mForceLabels = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mDrawGridLines = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mDrawAxisLine = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mDrawLabels = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mCenterAxisLabels = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   private DashPathEffect mAxisLineDashPathEffect = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   private DashPathEffect mGridDashPathEffect = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mDrawLimitLineBehindData = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   protected float mSpaceMin = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   protected float mSpaceMax = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mCustomAxisMin = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mCustomAxisMax = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public float mAxisMaximum = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public float mAxisMinimum = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public float mAxisRange = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   protected List<LimitLine> mLimitLines = new ArrayList();
/*     */   
/*     */   protected IAxisValueFormatter mAxisValueFormatter;
/*     */   
/*     */   public int mEntryCount;
/*     */   
/*     */   public int mDecimals;
/*     */ 
/*     */   
/* 166 */   public void setDrawGridLines(boolean enabled) { this.mDrawGridLines = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public boolean isDrawGridLinesEnabled() { return this.mDrawGridLines; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public void setDrawAxisLine(boolean enabled) { this.mDrawAxisLine = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public boolean isDrawAxisLineEnabled() { return this.mDrawAxisLine; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public void setCenterAxisLabels(boolean enabled) { this.mCenterAxisLabels = enabled; }
/*     */ 
/*     */ 
/*     */   
/* 207 */   public boolean isCenterAxisLabelsEnabled() { return (this.mCenterAxisLabels && this.mEntryCount > 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public void setGridColor(int color) { this.mGridColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 227 */   public int getGridColor() { return this.mGridColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void setAxisLineWidth(float width) { this.mAxisLineWidth = Utils.convertDpToPixel(width); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public float getAxisLineWidth() { return this.mAxisLineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public void setGridLineWidth(float width) { this.mGridLineWidth = Utils.convertDpToPixel(width); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 265 */   public float getGridLineWidth() { return this.mGridLineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   public void setAxisLineColor(int color) { this.mAxisLineColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 283 */   public int getAxisLineColor() { return this.mAxisLineColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public void setDrawLabels(boolean enabled) { this.mDrawLabels = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public boolean isDrawLabelsEnabled() { return this.mDrawLabels; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabelCount(int count) {
/* 313 */     if (count > 25)
/* 314 */       count = 25; 
/* 315 */     if (count < 2) {
/* 316 */       count = 2;
/*     */     }
/* 318 */     this.mLabelCount = count;
/* 319 */     this.mForceLabels = false;
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
/*     */   public void setLabelCount(int count, boolean force) {
/* 335 */     setLabelCount(count);
/* 336 */     this.mForceLabels = force;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 345 */   public boolean isForceLabelsEnabled() { return this.mForceLabels; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public int getLabelCount() { return this.mLabelCount; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public boolean isGranularityEnabled() { return this.mGranularityEnabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 371 */   public void setGranularityEnabled(boolean enabled) { this.mGranularityEnabled = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   public float getGranularity() { return this.mGranularity; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGranularity(float granularity) {
/* 388 */     this.mGranularity = granularity;
/*     */     
/* 390 */     this.mGranularityEnabled = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLimitLine(LimitLine l) {
/* 399 */     this.mLimitLines.add(l);
/*     */     
/* 401 */     if (this.mLimitLines.size() > 6) {
/* 402 */       Log.e("MPAndroiChart", "Warning! You have more than 6 LimitLines on your axis, do you really want that?");
/*     */     }
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
/* 414 */   public void removeLimitLine(LimitLine l) { this.mLimitLines.remove(l); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 421 */   public void removeAllLimitLines() { this.mLimitLines.clear(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public List<LimitLine> getLimitLines() { return this.mLimitLines; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 440 */   public void setDrawLimitLinesBehindData(boolean enabled) { this.mDrawLimitLineBehindData = enabled; }
/*     */ 
/*     */ 
/*     */   
/* 444 */   public boolean isDrawLimitLinesBehindDataEnabled() { return this.mDrawLimitLineBehindData; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLongestLabel() {
/* 455 */     String longest = "";
/*     */     
/* 457 */     for (int i = 0; i < this.mEntries.length; i++) {
/* 458 */       String text = getFormattedLabel(i);
/*     */       
/* 460 */       if (text != null && longest.length() < text.length()) {
/* 461 */         longest = text;
/*     */       }
/*     */     } 
/* 464 */     return longest;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFormattedLabel(int index) {
/* 469 */     if (index < 0 || index >= this.mEntries.length) {
/* 470 */       return "";
/*     */     }
/* 472 */     return getValueFormatter().getFormattedValue(this.mEntries[index], this);
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
/*     */   public void setValueFormatter(IAxisValueFormatter f) {
/* 486 */     if (f == null) {
/* 487 */       this.mAxisValueFormatter = new DefaultAxisValueFormatter(this.mDecimals);
/*     */     } else {
/* 489 */       this.mAxisValueFormatter = f;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IAxisValueFormatter getValueFormatter() {
/* 499 */     if (this.mAxisValueFormatter == null || (this.mAxisValueFormatter instanceof DefaultAxisValueFormatter && ((DefaultAxisValueFormatter)this.mAxisValueFormatter)
/*     */       
/* 501 */       .getDecimalDigits() != this.mDecimals)) {
/* 502 */       this.mAxisValueFormatter = new DefaultAxisValueFormatter(this.mDecimals);
/*     */     }
/* 504 */     return this.mAxisValueFormatter;
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
/* 517 */   public void enableGridDashedLine(float lineLength, float spaceLength, float phase) { this.mGridDashPathEffect = new DashPathEffect(new float[] { lineLength, spaceLength }, phase); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 530 */   public void setGridDashedLine(DashPathEffect effect) { this.mGridDashPathEffect = effect; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 537 */   public void disableGridDashedLine() { this.mGridDashPathEffect = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 546 */   public boolean isGridDashedLineEnabled() { return !(this.mGridDashPathEffect == null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 555 */   public DashPathEffect getGridDashPathEffect() { return this.mGridDashPathEffect; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 569 */   public void enableAxisLineDashedLine(float lineLength, float spaceLength, float phase) { this.mAxisLineDashPathEffect = new DashPathEffect(new float[] { lineLength, spaceLength }, phase); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 582 */   public void setAxisLineDashedLine(DashPathEffect effect) { this.mAxisLineDashPathEffect = effect; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 589 */   public void disableAxisLineDashedLine() { this.mAxisLineDashPathEffect = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 598 */   public boolean isAxisLineDashedLineEnabled() { return !(this.mAxisLineDashPathEffect == null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 607 */   public DashPathEffect getAxisLineDashPathEffect() { return this.mAxisLineDashPathEffect; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 615 */   public float getAxisMaximum() { return this.mAxisMaximum; }
/*     */ 
/*     */ 
/*     */   
/* 619 */   public float getAxisMinimum() { return this.mAxisMinimum; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 628 */   public void resetAxisMaximum() { this.mCustomAxisMax = false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 637 */   public boolean isAxisMaxCustom() { return this.mCustomAxisMax; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 646 */   public void resetAxisMinimum() { this.mCustomAxisMin = false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 655 */   public boolean isAxisMinCustom() { return this.mCustomAxisMin; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAxisMinimum(float min) {
/* 668 */     this.mCustomAxisMin = true;
/* 669 */     this.mAxisMinimum = min;
/* 670 */     this.mAxisRange = Math.abs(this.mAxisMaximum - min);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 680 */   public void setAxisMinValue(float min) { setAxisMinimum(min); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAxisMaximum(float max) {
/* 691 */     this.mCustomAxisMax = true;
/* 692 */     this.mAxisMaximum = max;
/* 693 */     this.mAxisRange = Math.abs(max - this.mAxisMinimum);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 703 */   public void setAxisMaxValue(float max) { setAxisMaximum(max); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void calculate(float dataMin, float dataMax) {
/* 716 */     float min = this.mCustomAxisMin ? this.mAxisMinimum : (dataMin - this.mSpaceMin);
/* 717 */     float max = this.mCustomAxisMax ? this.mAxisMaximum : (dataMax + this.mSpaceMax);
/*     */ 
/*     */     
/* 720 */     float range = Math.abs(max - min);
/*     */ 
/*     */     
/* 723 */     if (range == 0.0F) {
/* 724 */       max++;
/* 725 */       min--;
/*     */     } 
/*     */     
/* 728 */     this.mAxisMinimum = min;
/* 729 */     this.mAxisMaximum = max;
/*     */ 
/*     */     
/* 732 */     this.mAxisRange = Math.abs(max - min);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 740 */   public float getSpaceMin() { return this.mSpaceMin; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 748 */   public void setSpaceMin(float mSpaceMin) { this.mSpaceMin = mSpaceMin; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 756 */   public float getSpaceMax() { return this.mSpaceMax; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 764 */   public void setSpaceMax(float mSpaceMax) { this.mSpaceMax = mSpaceMax; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\AxisBase.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */