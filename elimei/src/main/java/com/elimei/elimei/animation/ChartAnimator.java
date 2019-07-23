/*     */ package com.elimei.elimei.animation;
/*     */ 
/*     */ import android.animation.ObjectAnimator;
/*     */ import android.animation.ValueAnimator;
/*     */ import android.os.Build;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChartAnimator
/*     */ {
/*     */   private ValueAnimator.AnimatorUpdateListener mListener;
/*     */   
/*     */   public ChartAnimator() {}
/*     */   
/*  23 */   public ChartAnimator(ValueAnimator.AnimatorUpdateListener listener) { this.mListener = listener; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   protected float mPhaseY = 1.0F;
/*     */ 
/*     */   
/*  35 */   protected float mPhaseX = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void animateXY(int durationMillisX, int durationMillisY, EasingFunction easingX, EasingFunction easingY) {
/*  55 */     if (Build.VERSION.SDK_INT < 11) {
/*     */       return;
/*     */     }
/*  58 */     ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
/*  59 */     animatorY.setInterpolator(easingY);
/*  60 */     animatorY.setDuration(durationMillisY);
/*     */     
/*  62 */     ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
/*  63 */     animatorX.setInterpolator(easingX);
/*  64 */     animatorX.setDuration(durationMillisX);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     if (durationMillisX > durationMillisY) {
/*  70 */       animatorX.addUpdateListener(this.mListener);
/*     */     } else {
/*  72 */       animatorY.addUpdateListener(this.mListener);
/*     */     } 
/*     */     
/*  75 */     animatorX.start();
/*  76 */     animatorY.start();
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
/*     */   public void animateX(int durationMillis, EasingFunction easing) {
/*  89 */     if (Build.VERSION.SDK_INT < 11) {
/*     */       return;
/*     */     }
/*  92 */     ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
/*  93 */     animatorX.setInterpolator(easing);
/*  94 */     animatorX.setDuration(durationMillis);
/*  95 */     animatorX.addUpdateListener(this.mListener);
/*  96 */     animatorX.start();
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
/*     */   public void animateY(int durationMillis, EasingFunction easing) {
/* 109 */     if (Build.VERSION.SDK_INT < 11) {
/*     */       return;
/*     */     }
/* 112 */     ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
/* 113 */     animatorY.setInterpolator(easing);
/* 114 */     animatorY.setDuration(durationMillis);
/* 115 */     animatorY.addUpdateListener(this.mListener);
/* 116 */     animatorY.start();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void animateXY(int durationMillisX, int durationMillisY, Easing.EasingOption easingX, Easing.EasingOption easingY) {
/* 137 */     if (Build.VERSION.SDK_INT < 11) {
/*     */       return;
/*     */     }
/* 140 */     ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
/* 141 */     animatorY.setInterpolator(Easing.getEasingFunctionFromOption(easingY));
/* 142 */     animatorY.setDuration(durationMillisY);
/*     */     
/* 144 */     ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
/* 145 */     animatorX.setInterpolator(Easing.getEasingFunctionFromOption(easingX));
/* 146 */     animatorX.setDuration(durationMillisX);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     if (durationMillisX > durationMillisY) {
/* 152 */       animatorX.addUpdateListener(this.mListener);
/*     */     } else {
/* 154 */       animatorY.addUpdateListener(this.mListener);
/*     */     } 
/*     */     
/* 157 */     animatorX.start();
/* 158 */     animatorY.start();
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
/*     */   public void animateX(int durationMillis, Easing.EasingOption easing) {
/* 171 */     if (Build.VERSION.SDK_INT < 11) {
/*     */       return;
/*     */     }
/* 174 */     ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
/* 175 */     animatorX.setInterpolator(Easing.getEasingFunctionFromOption(easing));
/* 176 */     animatorX.setDuration(durationMillis);
/* 177 */     animatorX.addUpdateListener(this.mListener);
/* 178 */     animatorX.start();
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
/*     */   public void animateY(int durationMillis, Easing.EasingOption easing) {
/* 191 */     if (Build.VERSION.SDK_INT < 11) {
/*     */       return;
/*     */     }
/* 194 */     ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
/* 195 */     animatorY.setInterpolator(Easing.getEasingFunctionFromOption(easing));
/* 196 */     animatorY.setDuration(durationMillis);
/* 197 */     animatorY.addUpdateListener(this.mListener);
/* 198 */     animatorY.start();
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
/*     */ 
/*     */   
/*     */   public void animateXY(int durationMillisX, int durationMillisY) {
/* 216 */     if (Build.VERSION.SDK_INT < 11) {
/*     */       return;
/*     */     }
/* 219 */     ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
/* 220 */     animatorY.setDuration(durationMillisY);
/*     */     
/* 222 */     ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
/* 223 */     animatorX.setDuration(durationMillisX);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 228 */     if (durationMillisX > durationMillisY) {
/* 229 */       animatorX.addUpdateListener(this.mListener);
/*     */     } else {
/* 231 */       animatorY.addUpdateListener(this.mListener);
/*     */     } 
/*     */     
/* 234 */     animatorX.start();
/* 235 */     animatorY.start();
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
/*     */   public void animateX(int durationMillis) {
/* 247 */     if (Build.VERSION.SDK_INT < 11) {
/*     */       return;
/*     */     }
/* 250 */     ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
/* 251 */     animatorX.setDuration(durationMillis);
/* 252 */     animatorX.addUpdateListener(this.mListener);
/* 253 */     animatorX.start();
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
/*     */   public void animateY(int durationMillis) {
/* 265 */     if (Build.VERSION.SDK_INT < 11) {
/*     */       return;
/*     */     }
/* 268 */     ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
/* 269 */     animatorY.setDuration(durationMillis);
/* 270 */     animatorY.addUpdateListener(this.mListener);
/* 271 */     animatorY.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public float getPhaseY() { return this.mPhaseY; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public void setPhaseY(float phase) { this.mPhaseY = phase; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public float getPhaseX() { return this.mPhaseX; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   public void setPhaseX(float phase) { this.mPhaseX = phase; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\animation\ChartAnimator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */