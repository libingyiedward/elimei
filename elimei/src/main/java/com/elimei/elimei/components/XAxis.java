/*     */ package com.elimei.elimei.components;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAxis
/*     */   extends AxisBase
/*     */ {
/*  19 */   public int mLabelWidth = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   public int mLabelHeight = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   public int mLabelRotatedWidth = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   public int mLabelRotatedHeight = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   protected float mLabelRotationAngle = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean mAvoidFirstLastClipping = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private XAxisPosition mPosition = XAxisPosition.TOP;
/*     */ 
/*     */ 
/*     */   
/*     */   public enum XAxisPosition
/*     */   {
/*  59 */     TOP, BOTTOM, BOTH_SIDED, TOP_INSIDE, BOTTOM_INSIDE;
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
/*  72 */   public XAxisPosition getPosition() { return this.mPosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setPosition(XAxisPosition pos) { this.mPosition = pos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public float getLabelRotationAngle() { return this.mLabelRotationAngle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void setLabelRotationAngle(float angle) { this.mLabelRotationAngle = angle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setAvoidFirstLastClipping(boolean enabled) { this.mAvoidFirstLastClipping = enabled; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public boolean isAvoidFirstLastClippingEnabled() { return this.mAvoidFirstLastClipping; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\XAxis.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */