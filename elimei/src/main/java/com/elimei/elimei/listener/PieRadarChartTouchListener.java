/*     */ package com.elimei.elimei.listener;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.View;
/*     */ import android.view.animation.AnimationUtils;
/*     */ import com.elimei.elimei.charts.PieRadarChartBase;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PieRadarChartTouchListener
/*     */   extends ChartTouchListener<PieRadarChartBase<?>>
/*     */ {
/*  23 */   private MPPointF mTouchStartPoint = MPPointF.getInstance(0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   private float mStartAngle = 0.0F;
/*     */   
/*  30 */   private ArrayList<AngularVelocitySample> _velocitySamples = new ArrayList();
/*     */   
/*  32 */   private long mDecelerationLastTime = 0L;
/*  33 */   private float mDecelerationAngularVelocity = 0.0F;
/*     */ 
/*     */   
/*  36 */   public PieRadarChartTouchListener(PieRadarChartBase<?> chart) { super(chart); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"ClickableViewAccessibility"})
/*     */   public boolean onTouch(View v, MotionEvent event) {
/*  43 */     if (this.mGestureDetector.onTouchEvent(event)) {
/*  44 */       return true;
/*     */     }
/*     */     
/*  47 */     if (((PieRadarChartBase)this.mChart).isRotationEnabled()) {
/*     */       
/*  49 */       float x = event.getX();
/*  50 */       float y = event.getY();
/*     */       
/*  52 */       switch (event.getAction()) {
/*     */ 
/*     */         
/*     */         case 0:
/*  56 */           startAction(event);
/*     */           
/*  58 */           stopDeceleration();
/*     */           
/*  60 */           resetVelocity();
/*     */           
/*  62 */           if (((PieRadarChartBase)this.mChart).isDragDecelerationEnabled()) {
/*  63 */             sampleVelocity(x, y);
/*     */           }
/*  65 */           setGestureStartAngle(x, y);
/*  66 */           this.mTouchStartPoint.x = x;
/*  67 */           this.mTouchStartPoint.y = y;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/*  72 */           if (((PieRadarChartBase)this.mChart).isDragDecelerationEnabled()) {
/*  73 */             sampleVelocity(x, y);
/*     */           }
/*  75 */           if (this.mTouchMode == 0 && 
/*  76 */             distance(x, this.mTouchStartPoint.x, y, this.mTouchStartPoint.y) > 
/*  77 */             Utils.convertDpToPixel(8.0F)) {
/*  78 */             this.mLastGesture = ChartTouchListener.ChartGesture.ROTATE;
/*  79 */             this.mTouchMode = 6;
/*  80 */             ((PieRadarChartBase)this.mChart).disableScroll();
/*  81 */           } else if (this.mTouchMode == 6) {
/*  82 */             updateGestureRotation(x, y);
/*  83 */             ((PieRadarChartBase)this.mChart).invalidate();
/*     */           } 
/*     */           
/*  86 */           endAction(event);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/*  91 */           if (((PieRadarChartBase)this.mChart).isDragDecelerationEnabled()) {
/*     */             
/*  93 */             stopDeceleration();
/*     */             
/*  95 */             sampleVelocity(x, y);
/*     */             
/*  97 */             this.mDecelerationAngularVelocity = calculateVelocity();
/*     */             
/*  99 */             if (this.mDecelerationAngularVelocity != 0.0F) {
/* 100 */               this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
/*     */               
/* 102 */               Utils.postInvalidateOnAnimation(this.mChart);
/*     */             } 
/*     */           } 
/*     */           
/* 106 */           ((PieRadarChartBase)this.mChart).enableScroll();
/* 107 */           this.mTouchMode = 0;
/*     */           
/* 109 */           endAction(event);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLongPress(MotionEvent me) {
/* 121 */     this.mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
/*     */     
/* 123 */     OnChartGestureListener l = ((PieRadarChartBase)this.mChart).getOnChartGestureListener();
/*     */     
/* 125 */     if (l != null) {
/* 126 */       l.onChartLongPressed(me);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 132 */   public boolean onSingleTapConfirmed(MotionEvent e) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onSingleTapUp(MotionEvent e) {
/* 138 */     this.mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
/*     */     
/* 140 */     OnChartGestureListener l = ((PieRadarChartBase)this.mChart).getOnChartGestureListener();
/*     */     
/* 142 */     if (l != null) {
/* 143 */       l.onChartSingleTapped(e);
/*     */     }
/*     */     
/* 146 */     if (!((PieRadarChartBase)this.mChart).isHighlightPerTapEnabled()) {
/* 147 */       return false;
/*     */     }
/*     */     
/* 150 */     Highlight high = ((PieRadarChartBase)this.mChart).getHighlightByTouchPoint(e.getX(), e.getY());
/* 151 */     performHighlight(high, e);
/*     */     
/* 153 */     return true;
/*     */   }
/*     */ 
/*     */   
/* 157 */   private void resetVelocity() { this._velocitySamples.clear(); }
/*     */ 
/*     */ 
/*     */   
/*     */   private void sampleVelocity(float touchLocationX, float touchLocationY) {
/* 162 */     long currentTime = AnimationUtils.currentAnimationTimeMillis();
/*     */     
/* 164 */     this._velocitySamples.add(new AngularVelocitySample(currentTime, ((PieRadarChartBase)this.mChart).getAngleForPoint(touchLocationX, touchLocationY)));
/*     */ 
/*     */     
/* 167 */     for (int i = 0, count = this._velocitySamples.size(); i < count - 2 && 
/* 168 */       currentTime - ((AngularVelocitySample)this._velocitySamples.get(i)).time > 1000L; i++) {
/* 169 */       this._velocitySamples.remove(0);
/* 170 */       i--;
/* 171 */       count--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float calculateVelocity() {
/* 180 */     if (this._velocitySamples.isEmpty()) {
/* 181 */       return 0.0F;
/*     */     }
/* 183 */     AngularVelocitySample firstSample = (AngularVelocitySample)this._velocitySamples.get(0);
/* 184 */     AngularVelocitySample lastSample = (AngularVelocitySample)this._velocitySamples.get(this._velocitySamples.size() - 1);
/*     */ 
/*     */     
/* 187 */     AngularVelocitySample beforeLastSample = firstSample;
/* 188 */     for (int i = this._velocitySamples.size() - 1; i >= 0; i--) {
/* 189 */       beforeLastSample = (AngularVelocitySample)this._velocitySamples.get(i);
/* 190 */       if (beforeLastSample.angle != lastSample.angle) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 196 */     float timeDelta = (float)(lastSample.time - firstSample.time) / 1000.0F;
/* 197 */     if (timeDelta == 0.0F) {
/* 198 */       timeDelta = 0.1F;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 203 */     boolean clockwise = (lastSample.angle >= beforeLastSample.angle);
/* 204 */     if (Math.abs(lastSample.angle - beforeLastSample.angle) > 270.0D) {
/* 205 */       clockwise = !clockwise;
/*     */     }
/*     */ 
/*     */     
/* 209 */     if ((lastSample.angle - firstSample.angle) > 180.0D) {
/* 210 */       firstSample.angle = (float)(firstSample.angle + 360.0D);
/* 211 */     } else if ((firstSample.angle - lastSample.angle) > 180.0D) {
/* 212 */       lastSample.angle = (float)(lastSample.angle + 360.0D);
/*     */     } 
/*     */ 
/*     */     
/* 216 */     float velocity = Math.abs((lastSample.angle - firstSample.angle) / timeDelta);
/*     */ 
/*     */     
/* 219 */     if (!clockwise) {
/* 220 */       velocity = -velocity;
/*     */     }
/*     */     
/* 223 */     return velocity;
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
/* 234 */   public void setGestureStartAngle(float x, float y) { this.mStartAngle = ((PieRadarChartBase)this.mChart).getAngleForPoint(x, y) - ((PieRadarChartBase)this.mChart).getRawRotationAngle(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public void updateGestureRotation(float x, float y) { ((PieRadarChartBase)this.mChart).setRotationAngle(((PieRadarChartBase)this.mChart).getAngleForPoint(x, y) - this.mStartAngle); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public void stopDeceleration() { this.mDecelerationAngularVelocity = 0.0F; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void computeScroll() {
/* 257 */     if (this.mDecelerationAngularVelocity == 0.0F) {
/*     */       return;
/*     */     }
/* 260 */     long currentTime = AnimationUtils.currentAnimationTimeMillis();
/*     */     
/* 262 */     this.mDecelerationAngularVelocity *= ((PieRadarChartBase)this.mChart).getDragDecelerationFrictionCoef();
/*     */     
/* 264 */     float timeInterval = (float)(currentTime - this.mDecelerationLastTime) / 1000.0F;
/*     */     
/* 266 */     ((PieRadarChartBase)this.mChart).setRotationAngle(((PieRadarChartBase)this.mChart).getRotationAngle() + this.mDecelerationAngularVelocity * timeInterval);
/*     */     
/* 268 */     this.mDecelerationLastTime = currentTime;
/*     */     
/* 270 */     if (Math.abs(this.mDecelerationAngularVelocity) >= 0.001D) {
/* 271 */       Utils.postInvalidateOnAnimation(this.mChart);
/*     */     } else {
/* 273 */       stopDeceleration();
/*     */     } 
/*     */   }
/*     */   
/*     */   private class AngularVelocitySample {
/*     */     public long time;
/*     */     public float angle;
/*     */     
/*     */     public AngularVelocitySample(long time, float angle) {
/* 282 */       this.time = time;
/* 283 */       this.angle = angle;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\listener\PieRadarChartTouchListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */