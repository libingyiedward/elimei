/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.DashPathEffect;
/*     */ import android.util.Log;
/*     */ import com.elimei.elimei.formatter.DefaultFillFormatter;
/*     */ import com.elimei.elimei.formatter.IFillFormatter;
/*     */ import com.elimei.elimei.interfaces.datasets.ILineDataSet;
/*     */ import com.elimei.elimei.utils.ColorTemplate;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LineDataSet
/*     */   extends LineRadarDataSet<Entry>
/*     */   implements ILineDataSet
/*     */ {
/*  23 */   private Mode mMode = Mode.LINEAR;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   private List<Integer> mCircleColors = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   private int mCircleColorHole = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   private float mCircleRadius = 8.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   private float mCircleHoleRadius = 4.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private float mCubicIntensity = 0.2F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private DashPathEffect mDashPathEffect = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   private IFillFormatter mFillFormatter = new DefaultFillFormatter();
/*     */ 
/*     */   
/*     */   private boolean mDrawCircles = true;
/*     */ 
/*     */   
/*     */   private boolean mDrawCircleHole = true;
/*     */ 
/*     */ 
/*     */   
/*     */   public LineDataSet(List<Entry> yVals, String label) {
/*  69 */     super(yVals, label);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     if (this.mCircleColors == null) {
/*  75 */       this.mCircleColors = new ArrayList();
/*     */     }
/*  77 */     this.mCircleColors.clear();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     this.mCircleColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSet<Entry> copy() {
/*  88 */     List<Entry> yVals = new ArrayList<Entry>();
/*     */     
/*  90 */     for (int i = 0; i < this.mValues.size(); i++) {
/*  91 */       yVals.add(((Entry)this.mValues.get(i)).copy());
/*     */     }
/*     */     
/*  94 */     LineDataSet copied = new LineDataSet(yVals, getLabel());
/*  95 */     copied.mMode = this.mMode;
/*  96 */     copied.mColors = this.mColors;
/*  97 */     copied.mCircleRadius = this.mCircleRadius;
/*  98 */     copied.mCircleHoleRadius = this.mCircleHoleRadius;
/*  99 */     copied.mCircleColors = this.mCircleColors;
/* 100 */     copied.mDashPathEffect = this.mDashPathEffect;
/* 101 */     copied.mDrawCircles = this.mDrawCircles;
/* 102 */     copied.mDrawCircleHole = this.mDrawCircleHole;
/* 103 */     copied.mHighLightColor = this.mHighLightColor;
/*     */     
/* 105 */     return copied;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public Mode getMode() { return this.mMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setMode(Mode mode) { this.mMode = mode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCubicIntensity(float intensity) {
/* 135 */     if (intensity > 1.0F)
/* 136 */       intensity = 1.0F; 
/* 137 */     if (intensity < 0.05F) {
/* 138 */       intensity = 0.05F;
/*     */     }
/* 140 */     this.mCubicIntensity = intensity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 145 */   public float getCubicIntensity() { return this.mCubicIntensity; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCircleRadius(float radius) {
/* 157 */     if (radius >= 1.0F) {
/* 158 */       this.mCircleRadius = Utils.convertDpToPixel(radius);
/*     */     } else {
/* 160 */       Log.e("LineDataSet", "Circle radius cannot be < 1");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 166 */   public float getCircleRadius() { return this.mCircleRadius; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCircleHoleRadius(float holeRadius) {
/* 177 */     if (holeRadius >= 0.5F) {
/* 178 */       this.mCircleHoleRadius = Utils.convertDpToPixel(holeRadius);
/*     */     } else {
/* 180 */       Log.e("LineDataSet", "Circle radius cannot be < 0.5");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 186 */   public float getCircleHoleRadius() { return this.mCircleHoleRadius; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 199 */   public void setCircleSize(float size) { setCircleRadius(size); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 207 */   public float getCircleSize() { return getCircleRadius(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void enableDashedLine(float lineLength, float spaceLength, float phase) { this.mDashPathEffect = new DashPathEffect(new float[] { lineLength, spaceLength }, phase); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public void disableDashedLine() { this.mDashPathEffect = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public boolean isDashedLineEnabled() { return !(this.mDashPathEffect == null); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   public DashPathEffect getDashPathEffect() { return this.mDashPathEffect; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public void setDrawCircles(boolean enabled) { this.mDrawCircles = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   public boolean isDrawCirclesEnabled() { return this.mDrawCircles; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 260 */   public boolean isDrawCubicEnabled() { return (this.mMode == Mode.CUBIC_BEZIER); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 266 */   public boolean isDrawSteppedEnabled() { return (this.mMode == Mode.STEPPED); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 277 */   public List<Integer> getCircleColors() { return this.mCircleColors; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   public int getCircleColor(int index) { return ((Integer)this.mCircleColors.get(index)).intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public int getCircleColorCount() { return this.mCircleColors.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public void setCircleColors(List<Integer> colors) { this.mCircleColors = colors; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public void setCircleColors(int... colors) { this.mCircleColors = ColorTemplate.createColors(colors); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCircleColors(int[] colors, Context c) {
/* 328 */     List<Integer> clrs = this.mCircleColors;
/* 329 */     if (clrs == null) {
/* 330 */       clrs = new ArrayList<Integer>();
/*     */     }
/* 332 */     clrs.clear();
/*     */     
/* 334 */     for (int color : colors) {
/* 335 */       clrs.add(Integer.valueOf(c.getResources().getColor(color)));
/*     */     }
/*     */     
/* 338 */     this.mCircleColors = clrs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCircleColor(int color) {
/* 348 */     resetCircleColors();
/* 349 */     this.mCircleColors.add(Integer.valueOf(color));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetCircleColors() {
/* 356 */     if (this.mCircleColors == null) {
/* 357 */       this.mCircleColors = new ArrayList();
/*     */     }
/* 359 */     this.mCircleColors.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 368 */   public void setCircleColorHole(int color) { this.mCircleColorHole = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 373 */   public int getCircleHoleColor() { return this.mCircleColorHole; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 382 */   public void setDrawCircleHole(boolean enabled) { this.mDrawCircleHole = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 387 */   public boolean isDrawCircleHoleEnabled() { return this.mDrawCircleHole; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFillFormatter(IFillFormatter formatter) {
/* 398 */     if (formatter == null) {
/* 399 */       this.mFillFormatter = new DefaultFillFormatter();
/*     */     } else {
/* 401 */       this.mFillFormatter = formatter;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 406 */   public IFillFormatter getFillFormatter() { return this.mFillFormatter; }
/*     */   
/*     */   public enum Mode
/*     */   {
/* 410 */     LINEAR,
/* 411 */     STEPPED,
/* 412 */     CUBIC_BEZIER,
/* 413 */     HORIZONTAL_BEZIER;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\LineDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */