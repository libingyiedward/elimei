/*     */ package com.elimei.elimei.components;
/*     */ 
/*     */ import android.graphics.DashPathEffect;
/*     */ import android.graphics.Paint;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LimitLine
/*     */   extends ComponentBase
/*     */ {
/*     */   private float mLimit;
/*     */   private float mLineWidth;
/*     */   private int mLineColor;
/*     */   private Paint.Style mTextStyle;
/*     */   private String mLabel;
/*     */   private DashPathEffect mDashPathEffect;
/*     */   private LimitLabelPosition mLabelPosition;
/*     */   
/*     */   public enum LimitLabelPosition
/*     */   {
/*  43 */     LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM; }
/*     */   
/*     */   public LimitLine(float limit) {
/*     */     this.mLimit = 0.0F;
/*     */     this.mLineWidth = 2.0F;
/*     */     this.mLineColor = -16776961;
/*     */     this.mTextStyle = Paint.Style.FILL_AND_STROKE;
/*     */     this.mLabel = "";
/*     */     this.mDashPathEffect = null;
/*     */     this.mLabelPosition = LimitLabelPosition.RIGHT_TOP;
/*  53 */     this.mLimit = limit;
/*     */   }
/*     */   
/*     */   public LimitLine(float limit, String label) {
/*     */     this.mLimit = 0.0F;
/*     */     this.mLineWidth = 2.0F;
/*     */     this.mLineColor = -16776961;
/*     */     this.mTextStyle = Paint.Style.FILL_AND_STROKE;
/*     */     this.mLabel = "";
/*     */     this.mDashPathEffect = null;
/*     */     this.mLabelPosition = LimitLabelPosition.RIGHT_TOP;
/*  64 */     this.mLimit = limit;
/*  65 */     this.mLabel = label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public float getLimit() { return this.mLimit; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineWidth(float width) {
/*  85 */     if (width < 0.2F)
/*  86 */       width = 0.2F; 
/*  87 */     if (width > 12.0F)
/*  88 */       width = 12.0F; 
/*  89 */     this.mLineWidth = Utils.convertDpToPixel(width);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public float getLineWidth() { return this.mLineWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public void setLineColor(int color) { this.mLineColor = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public int getLineColor() { return this.mLineColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public void enableDashedLine(float lineLength, float spaceLength, float phase) { this.mDashPathEffect = new DashPathEffect(new float[] { lineLength, spaceLength }, phase); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void disableDashedLine() { this.mDashPathEffect = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public boolean isDashedLineEnabled() { return !(this.mDashPathEffect == null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public DashPathEffect getDashPathEffect() { return this.mDashPathEffect; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public void setTextStyle(Paint.Style style) { this.mTextStyle = style; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public Paint.Style getTextStyle() { return this.mTextStyle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public void setLabelPosition(LimitLabelPosition pos) { this.mLabelPosition = pos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public LimitLabelPosition getLabelPosition() { return this.mLabelPosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public void setLabel(String label) { this.mLabel = label; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public String getLabel() { return this.mLabel; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\LimitLine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */