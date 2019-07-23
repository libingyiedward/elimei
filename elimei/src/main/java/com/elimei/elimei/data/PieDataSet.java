/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import com.elimei.elimei.interfaces.datasets.IPieDataSet;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PieDataSet
/*     */   extends DataSet<PieEntry>
/*     */   implements IPieDataSet
/*     */ {
/*  14 */   private float mSliceSpace = 0.0F;
/*     */   
/*     */   private boolean mAutomaticallyDisableSliceSpacing;
/*     */   
/*  18 */   private float mShift = 18.0F;
/*     */   
/*  20 */   private ValuePosition mXValuePosition = ValuePosition.INSIDE_SLICE;
/*  21 */   private ValuePosition mYValuePosition = ValuePosition.INSIDE_SLICE;
/*  22 */   private int mValueLineColor = -16777216;
/*  23 */   private float mValueLineWidth = 1.0F;
/*  24 */   private float mValueLinePart1OffsetPercentage = 75.0F;
/*  25 */   private float mValueLinePart1Length = 0.3F;
/*  26 */   private float mValueLinePart2Length = 0.4F;
/*     */   
/*     */   private boolean mValueLineVariableLength = true;
/*     */   
/*  30 */   public PieDataSet(List<PieEntry> yVals, String label) { super(yVals, label); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSet<PieEntry> copy() {
/*  37 */     List<PieEntry> yVals = new ArrayList<PieEntry>();
/*     */     
/*  39 */     for (int i = 0; i < this.mValues.size(); i++) {
/*  40 */       yVals.add(((PieEntry)this.mValues.get(i)).copy());
/*     */     }
/*     */     
/*  43 */     PieDataSet copied = new PieDataSet(yVals, getLabel());
/*  44 */     copied.mColors = this.mColors;
/*  45 */     copied.mSliceSpace = this.mSliceSpace;
/*  46 */     copied.mShift = this.mShift;
/*  47 */     return copied;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void calcMinMax(PieEntry e) {
/*  53 */     if (e == null) {
/*     */       return;
/*     */     }
/*  56 */     calcMinMaxY(e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSliceSpace(float spaceDp) {
/*  67 */     if (spaceDp > 20.0F)
/*  68 */       spaceDp = 20.0F; 
/*  69 */     if (spaceDp < 0.0F) {
/*  70 */       spaceDp = 0.0F;
/*     */     }
/*  72 */     this.mSliceSpace = Utils.convertDpToPixel(spaceDp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public float getSliceSpace() { return this.mSliceSpace; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setAutomaticallyDisableSliceSpacing(boolean autoDisable) { this.mAutomaticallyDisableSliceSpacing = autoDisable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public boolean isAutomaticallyDisableSliceSpacingEnabled() { return this.mAutomaticallyDisableSliceSpacing; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public void setSelectionShift(float shift) { this.mShift = Utils.convertDpToPixel(shift); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public float getSelectionShift() { return this.mShift; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public ValuePosition getXValuePosition() { return this.mXValuePosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setXValuePosition(ValuePosition xValuePosition) { this.mXValuePosition = xValuePosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public ValuePosition getYValuePosition() { return this.mYValuePosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public void setYValuePosition(ValuePosition yValuePosition) { this.mYValuePosition = yValuePosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public int getValueLineColor() { return this.mValueLineColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void setValueLineColor(int valueLineColor) { this.mValueLineColor = valueLineColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public float getValueLineWidth() { return this.mValueLineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public void setValueLineWidth(float valueLineWidth) { this.mValueLineWidth = valueLineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public float getValueLinePart1OffsetPercentage() { return this.mValueLinePart1OffsetPercentage; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public void setValueLinePart1OffsetPercentage(float valueLinePart1OffsetPercentage) { this.mValueLinePart1OffsetPercentage = valueLinePart1OffsetPercentage; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public float getValueLinePart1Length() { return this.mValueLinePart1Length; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public void setValueLinePart1Length(float valueLinePart1Length) { this.mValueLinePart1Length = valueLinePart1Length; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public float getValueLinePart2Length() { return this.mValueLinePart2Length; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public void setValueLinePart2Length(float valueLinePart2Length) { this.mValueLinePart2Length = valueLinePart2Length; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public boolean isValueLineVariableLength() { return this.mValueLineVariableLength; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public void setValueLineVariableLength(boolean valueLineVariableLength) { this.mValueLineVariableLength = valueLineVariableLength; }
/*     */   
/*     */   public enum ValuePosition
/*     */   {
/* 211 */     INSIDE_SLICE,
/* 212 */     OUTSIDE_SLICE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\PieDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */