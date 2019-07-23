/*     */ package com.elimei.elimei.listener;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.graphics.Matrix;
/*     */ import android.util.Log;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.VelocityTracker;
/*     */ import android.view.View;
/*     */ import android.view.animation.AnimationUtils;
/*     */ import com.elimei.elimei.charts.BarLineChartBase;
/*     */ import com.elimei.elimei.data.BarLineScatterCandleBubbleData;
/*     */ import com.elimei.elimei.data.Entry;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
/*     */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*     */ import com.elimei.elimei.utils.MPPointF;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarLineChartTouchListener
/*     */   extends ChartTouchListener<BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>>
/*     */ {
/*  34 */   private Matrix mMatrix = new Matrix();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private Matrix mSavedMatrix = new Matrix();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private MPPointF mTouchStartPoint = MPPointF.getInstance(0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private MPPointF mTouchPointCenter = MPPointF.getInstance(0.0F, 0.0F);
/*     */   
/*  51 */   private float mSavedXDist = 1.0F;
/*  52 */   private float mSavedYDist = 1.0F;
/*  53 */   private float mSavedDist = 1.0F;
/*     */ 
/*     */   
/*     */   private IDataSet mClosestDataSetToTouch;
/*     */ 
/*     */   
/*     */   private VelocityTracker mVelocityTracker;
/*     */ 
/*     */   
/*  62 */   private long mDecelerationLastTime = 0L;
/*  63 */   private MPPointF mDecelerationCurrentPoint = MPPointF.getInstance(0.0F, 0.0F);
/*  64 */   private MPPointF mDecelerationVelocity = MPPointF.getInstance(0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float mDragTriggerDist;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float mMinScalePointerDistance;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BarLineChartTouchListener(BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> chart, Matrix touchMatrix, float dragTriggerDistance) {
/*  86 */     super(chart);
/*  87 */     this.mMatrix = touchMatrix;
/*     */     
/*  89 */     this.mDragTriggerDist = Utils.convertDpToPixel(dragTriggerDistance);
/*     */     
/*  91 */     this.mMinScalePointerDistance = Utils.convertDpToPixel(3.5F);
/*     */   }
/*     */   @SuppressLint({"ClickableViewAccessibility"})
/*     */   public boolean onTouch(View v, MotionEvent event) {
/*     */     float velocityX, velocityY;
/*     */     int pointerId;
/*     */     VelocityTracker velocityTracker;
/*  98 */     if (this.mVelocityTracker == null) {
/*  99 */       this.mVelocityTracker = VelocityTracker.obtain();
/*     */     }
/* 101 */     this.mVelocityTracker.addMovement(event);
/*     */     
/* 103 */     if (event.getActionMasked() == 3 && 
/* 104 */       this.mVelocityTracker != null) {
/* 105 */       this.mVelocityTracker.recycle();
/* 106 */       this.mVelocityTracker = null;
/*     */     } 
/*     */ 
/*     */     
/* 110 */     if (this.mTouchMode == 0) {
/* 111 */       this.mGestureDetector.onTouchEvent(event);
/*     */     }
/*     */     
/* 114 */     if (!((BarLineChartBase)this.mChart).isDragEnabled() && !((BarLineChartBase)this.mChart).isScaleXEnabled() && !((BarLineChartBase)this.mChart).isScaleYEnabled()) {
/* 115 */       return true;
/*     */     }
/*     */     
/* 118 */     switch (event.getAction() & 0xFF) {
/*     */ 
/*     */       
/*     */       case 0:
/* 122 */         startAction(event);
/*     */         
/* 124 */         stopDeceleration();
/*     */         
/* 126 */         saveTouchStart(event);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 132 */         if (event.getPointerCount() >= 2) {
/*     */           
/* 134 */           ((BarLineChartBase)this.mChart).disableScroll();
/*     */           
/* 136 */           saveTouchStart(event);
/*     */ 
/*     */           
/* 139 */           this.mSavedXDist = getXDist(event);
/*     */ 
/*     */           
/* 142 */           this.mSavedYDist = getYDist(event);
/*     */ 
/*     */           
/* 145 */           this.mSavedDist = spacing(event);
/*     */           
/* 147 */           if (this.mSavedDist > 10.0F)
/*     */           {
/* 149 */             if (((BarLineChartBase)this.mChart).isPinchZoomEnabled()) {
/* 150 */               this.mTouchMode = 4;
/*     */             }
/* 152 */             else if (((BarLineChartBase)this.mChart).isScaleXEnabled() != ((BarLineChartBase)this.mChart).isScaleYEnabled()) {
/* 153 */               this.mTouchMode = ((BarLineChartBase)this.mChart).isScaleXEnabled() ? 2 : 3;
/*     */             } else {
/* 155 */               this.mTouchMode = (this.mSavedXDist > this.mSavedYDist) ? 2 : 3;
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 161 */           midPoint(this.mTouchPointCenter, event);
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 167 */         if (this.mTouchMode == 1) {
/*     */           
/* 169 */           ((BarLineChartBase)this.mChart).disableScroll();
/*     */           
/* 171 */           float x = ((BarLineChartBase)this.mChart).isDragXEnabled() ? (event.getX() - this.mTouchStartPoint.x) : 0.0F;
/* 172 */           float y = ((BarLineChartBase)this.mChart).isDragYEnabled() ? (event.getY() - this.mTouchStartPoint.y) : 0.0F;
/*     */           
/* 174 */           performDrag(event, x, y); break;
/*     */         } 
/* 176 */         if (this.mTouchMode == 2 || this.mTouchMode == 3 || this.mTouchMode == 4) {
/*     */           
/* 178 */           ((BarLineChartBase)this.mChart).disableScroll();
/*     */           
/* 180 */           if (((BarLineChartBase)this.mChart).isScaleXEnabled() || ((BarLineChartBase)this.mChart).isScaleYEnabled())
/* 181 */             performZoom(event);  break;
/*     */         } 
/* 183 */         if (this.mTouchMode == 0 && 
/* 184 */           Math.abs(distance(event.getX(), this.mTouchStartPoint.x, event.getY(), this.mTouchStartPoint.y)) > this.mDragTriggerDist)
/*     */         {
/*     */           
/* 187 */           if (((BarLineChartBase)this.mChart).isDragEnabled()) {
/*     */ 
/*     */             
/* 190 */             boolean shouldPan = (!((BarLineChartBase)this.mChart).isFullyZoomedOut() || !((BarLineChartBase)this.mChart).hasNoDragOffset());
/*     */             
/* 192 */             if (shouldPan) {
/*     */               
/* 194 */               float distanceX = Math.abs(event.getX() - this.mTouchStartPoint.x);
/* 195 */               float distanceY = Math.abs(event.getY() - this.mTouchStartPoint.y);
/*     */ 
/*     */               
/* 198 */               if ((((BarLineChartBase)this.mChart).isDragXEnabled() || distanceY >= distanceX) && (((BarLineChartBase)this.mChart)
/* 199 */                 .isDragYEnabled() || distanceY <= distanceX)) {
/*     */                 
/* 201 */                 this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
/* 202 */                 this.mTouchMode = 1;
/*     */               } 
/*     */               
/*     */               break;
/*     */             } 
/* 207 */             if (((BarLineChartBase)this.mChart).isHighlightPerDragEnabled()) {
/* 208 */               this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
/*     */               
/* 210 */               if (((BarLineChartBase)this.mChart).isHighlightPerDragEnabled()) {
/* 211 */                 performHighlightDrag(event);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 222 */         velocityTracker = this.mVelocityTracker;
/* 223 */         pointerId = event.getPointerId(0);
/* 224 */         velocityTracker.computeCurrentVelocity(1000, Utils.getMaximumFlingVelocity());
/* 225 */         velocityY = velocityTracker.getYVelocity(pointerId);
/* 226 */         velocityX = velocityTracker.getXVelocity(pointerId);
/*     */         
/* 228 */         if (Math.abs(velocityX) > Utils.getMinimumFlingVelocity() || 
/* 229 */           Math.abs(velocityY) > Utils.getMinimumFlingVelocity())
/*     */         {
/* 231 */           if (this.mTouchMode == 1 && ((BarLineChartBase)this.mChart).isDragDecelerationEnabled()) {
/*     */             
/* 233 */             stopDeceleration();
/*     */             
/* 235 */             this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
/*     */             
/* 237 */             this.mDecelerationCurrentPoint.x = event.getX();
/* 238 */             this.mDecelerationCurrentPoint.y = event.getY();
/*     */             
/* 240 */             this.mDecelerationVelocity.x = velocityX;
/* 241 */             this.mDecelerationVelocity.y = velocityY;
/*     */             
/* 243 */             Utils.postInvalidateOnAnimation(this.mChart);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 248 */         if (this.mTouchMode == 2 || this.mTouchMode == 3 || this.mTouchMode == 4 || this.mTouchMode == 5) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 256 */           ((BarLineChartBase)this.mChart).calculateOffsets();
/* 257 */           ((BarLineChartBase)this.mChart).postInvalidate();
/*     */         } 
/*     */         
/* 260 */         this.mTouchMode = 0;
/* 261 */         ((BarLineChartBase)this.mChart).enableScroll();
/*     */         
/* 263 */         if (this.mVelocityTracker != null) {
/* 264 */           this.mVelocityTracker.recycle();
/* 265 */           this.mVelocityTracker = null;
/*     */         } 
/*     */         
/* 268 */         endAction(event);
/*     */         break;
/*     */       
/*     */       case 6:
/* 272 */         Utils.velocityTrackerPointerUpCleanUpIfNecessary(event, this.mVelocityTracker);
/*     */         
/* 274 */         this.mTouchMode = 5;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 279 */         this.mTouchMode = 0;
/* 280 */         endAction(event);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 285 */     this.mMatrix = ((BarLineChartBase)this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, true);
/*     */     
/* 287 */     return true;
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
/*     */   private void saveTouchStart(MotionEvent event) {
/* 302 */     this.mSavedMatrix.set(this.mMatrix);
/* 303 */     this.mTouchStartPoint.x = event.getX();
/* 304 */     this.mTouchStartPoint.y = event.getY();
/*     */     
/* 306 */     this.mClosestDataSetToTouch = ((BarLineChartBase)this.mChart).getDataSetByTouchPoint(event.getX(), event.getY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void performDrag(MotionEvent event, float distanceX, float distanceY) {
/* 316 */     this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
/*     */     
/* 318 */     this.mMatrix.set(this.mSavedMatrix);
/*     */     
/* 320 */     OnChartGestureListener l = ((BarLineChartBase)this.mChart).getOnChartGestureListener();
/*     */ 
/*     */     
/* 323 */     if (inverted())
/*     */     {
/*     */       
/* 326 */       if (this.mChart instanceof com.elimei.elimei.charts.HorizontalBarChart) {
/* 327 */         distanceX = -distanceX;
/*     */       } else {
/* 329 */         distanceY = -distanceY;
/*     */       } 
/*     */     }
/*     */     
/* 333 */     this.mMatrix.postTranslate(distanceX, distanceY);
/*     */     
/* 335 */     if (l != null) {
/* 336 */       l.onChartTranslate(event, distanceX, distanceY);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void performZoom(MotionEvent event) {
/* 346 */     if (event.getPointerCount() >= 2) {
/*     */       
/* 348 */       OnChartGestureListener l = ((BarLineChartBase)this.mChart).getOnChartGestureListener();
/*     */ 
/*     */       
/* 351 */       float totalDist = spacing(event);
/*     */       
/* 353 */       if (totalDist > this.mMinScalePointerDistance) {
/*     */ 
/*     */         
/* 356 */         MPPointF t = getTrans(this.mTouchPointCenter.x, this.mTouchPointCenter.y);
/* 357 */         ViewPortHandler h = ((BarLineChartBase)this.mChart).getViewPortHandler();
/*     */ 
/*     */         
/* 360 */         if (this.mTouchMode == 4) {
/*     */           
/* 362 */           this.mLastGesture = ChartTouchListener.ChartGesture.PINCH_ZOOM;
/*     */           
/* 364 */           float scale = totalDist / this.mSavedDist;
/*     */           
/* 366 */           boolean isZoomingOut = (scale < 1.0F);
/*     */ 
/*     */ 
/*     */           
/* 370 */           boolean canZoomMoreX = isZoomingOut ? h.canZoomOutMoreX() : h.canZoomInMoreX();
/*     */ 
/*     */ 
/*     */           
/* 374 */           boolean canZoomMoreY = isZoomingOut ? h.canZoomOutMoreY() : h.canZoomInMoreY();
/*     */           
/* 376 */           float scaleX = ((BarLineChartBase)this.mChart).isScaleXEnabled() ? scale : 1.0F;
/* 377 */           float scaleY = ((BarLineChartBase)this.mChart).isScaleYEnabled() ? scale : 1.0F;
/*     */           
/* 379 */           if (canZoomMoreY || canZoomMoreX) {
/*     */             
/* 381 */             this.mMatrix.set(this.mSavedMatrix);
/* 382 */             this.mMatrix.postScale(scaleX, scaleY, t.x, t.y);
/*     */             
/* 384 */             if (l != null) {
/* 385 */               l.onChartScale(event, scaleX, scaleY);
/*     */             }
/*     */           } 
/* 388 */         } else if (this.mTouchMode == 2 && ((BarLineChartBase)this.mChart).isScaleXEnabled()) {
/*     */           
/* 390 */           this.mLastGesture = ChartTouchListener.ChartGesture.X_ZOOM;
/*     */           
/* 392 */           float xDist = getXDist(event);
/* 393 */           float scaleX = xDist / this.mSavedXDist;
/*     */           
/* 395 */           boolean isZoomingOut = (scaleX < 1.0F);
/*     */ 
/*     */           
/* 398 */           boolean canZoomMoreX = isZoomingOut ? h.canZoomOutMoreX() : h.canZoomInMoreX();
/*     */           
/* 400 */           if (canZoomMoreX) {
/*     */             
/* 402 */             this.mMatrix.set(this.mSavedMatrix);
/* 403 */             this.mMatrix.postScale(scaleX, 1.0F, t.x, t.y);
/*     */             
/* 405 */             if (l != null) {
/* 406 */               l.onChartScale(event, scaleX, 1.0F);
/*     */             }
/*     */           } 
/* 409 */         } else if (this.mTouchMode == 3 && ((BarLineChartBase)this.mChart).isScaleYEnabled()) {
/*     */           
/* 411 */           this.mLastGesture = ChartTouchListener.ChartGesture.Y_ZOOM;
/*     */           
/* 413 */           float yDist = getYDist(event);
/* 414 */           float scaleY = yDist / this.mSavedYDist;
/*     */           
/* 416 */           boolean isZoomingOut = (scaleY < 1.0F);
/*     */ 
/*     */           
/* 419 */           boolean canZoomMoreY = isZoomingOut ? h.canZoomOutMoreY() : h.canZoomInMoreY();
/*     */           
/* 421 */           if (canZoomMoreY) {
/*     */             
/* 423 */             this.mMatrix.set(this.mSavedMatrix);
/* 424 */             this.mMatrix.postScale(1.0F, scaleY, t.x, t.y);
/*     */             
/* 426 */             if (l != null) {
/* 427 */               l.onChartScale(event, 1.0F, scaleY);
/*     */             }
/*     */           } 
/*     */         } 
/* 431 */         MPPointF.recycleInstance(t);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void performHighlightDrag(MotionEvent e) {
/* 443 */     Highlight h = ((BarLineChartBase)this.mChart).getHighlightByTouchPoint(e.getX(), e.getY());
/*     */     
/* 445 */     if (h != null && !h.equalTo(this.mLastHighlighted)) {
/* 446 */       this.mLastHighlighted = h;
/* 447 */       ((BarLineChartBase)this.mChart).highlightValue(h, true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void midPoint(MPPointF point, MotionEvent event) {
/* 464 */     float x = event.getX(0) + event.getX(1);
/* 465 */     float y = event.getY(0) + event.getY(1);
/* 466 */     point.x = x / 2.0F;
/* 467 */     point.y = y / 2.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static float spacing(MotionEvent event) {
/* 477 */     float x = event.getX(0) - event.getX(1);
/* 478 */     float y = event.getY(0) - event.getY(1);
/* 479 */     return (float)Math.sqrt((x * x + y * y));
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
/* 490 */   private static float getXDist(MotionEvent e) { return Math.abs(e.getX(0) - e.getX(1)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 502 */   private static float getYDist(MotionEvent e) { return Math.abs(e.getY(0) - e.getY(1)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MPPointF getTrans(float x, float y) {
/* 517 */     ViewPortHandler vph = ((BarLineChartBase)this.mChart).getViewPortHandler();
/*     */     
/* 519 */     float xTrans = x - vph.offsetLeft();
/* 520 */     float yTrans = 0.0F;
/*     */ 
/*     */     
/* 523 */     if (inverted()) {
/* 524 */       yTrans = -(y - vph.offsetTop());
/*     */     } else {
/* 526 */       yTrans = -(((BarLineChartBase)this.mChart).getMeasuredHeight() - y - vph.offsetBottom());
/*     */     } 
/*     */     
/* 529 */     return MPPointF.getInstance(xTrans, yTrans);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean inverted() {
/* 538 */     return ((this.mClosestDataSetToTouch == null && ((BarLineChartBase)this.mChart).isAnyAxisInverted()) || (this.mClosestDataSetToTouch != null && ((BarLineChartBase)this.mChart)
/* 539 */       .isInverted(this.mClosestDataSetToTouch.getAxisDependency())));
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
/* 553 */   public Matrix getMatrix() { return this.mMatrix; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 563 */   public void setDragTriggerDist(float dragTriggerDistance) { this.mDragTriggerDist = Utils.convertDpToPixel(dragTriggerDistance); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onDoubleTap(MotionEvent e) {
/* 569 */     this.mLastGesture = ChartTouchListener.ChartGesture.DOUBLE_TAP;
/*     */     
/* 571 */     OnChartGestureListener l = ((BarLineChartBase)this.mChart).getOnChartGestureListener();
/*     */     
/* 573 */     if (l != null) {
/* 574 */       l.onChartDoubleTapped(e);
/*     */     }
/*     */ 
/*     */     
/* 578 */     if (((BarLineChartBase)this.mChart).isDoubleTapToZoomEnabled() && ((BarLineScatterCandleBubbleData)((BarLineChartBase)this.mChart).getData()).getEntryCount() > 0) {
/*     */       
/* 580 */       MPPointF trans = getTrans(e.getX(), e.getY());
/*     */       
/* 582 */       ((BarLineChartBase)this.mChart).zoom(((BarLineChartBase)this.mChart).isScaleXEnabled() ? 1.4F : 1.0F, ((BarLineChartBase)this.mChart).isScaleYEnabled() ? 1.4F : 1.0F, trans.x, trans.y);
/*     */       
/* 584 */       if (((BarLineChartBase)this.mChart).isLogEnabled()) {
/* 585 */         Log.i("BarlineChartTouch", "Double-Tap, Zooming In, x: " + trans.x + ", y: " + trans.y);
/*     */       }
/*     */       
/* 588 */       MPPointF.recycleInstance(trans);
/*     */     } 
/*     */     
/* 591 */     return super.onDoubleTap(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLongPress(MotionEvent e) {
/* 597 */     this.mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
/*     */     
/* 599 */     OnChartGestureListener l = ((BarLineChartBase)this.mChart).getOnChartGestureListener();
/*     */     
/* 601 */     if (l != null)
/*     */     {
/* 603 */       l.onChartLongPressed(e);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onSingleTapUp(MotionEvent e) {
/* 610 */     this.mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
/*     */     
/* 612 */     OnChartGestureListener l = ((BarLineChartBase)this.mChart).getOnChartGestureListener();
/*     */     
/* 614 */     if (l != null) {
/* 615 */       l.onChartSingleTapped(e);
/*     */     }
/*     */     
/* 618 */     if (!((BarLineChartBase)this.mChart).isHighlightPerTapEnabled()) {
/* 619 */       return false;
/*     */     }
/*     */     
/* 622 */     Highlight h = ((BarLineChartBase)this.mChart).getHighlightByTouchPoint(e.getX(), e.getY());
/* 623 */     performHighlight(h, e);
/*     */     
/* 625 */     return super.onSingleTapUp(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
/* 631 */     this.mLastGesture = ChartTouchListener.ChartGesture.FLING;
/*     */     
/* 633 */     OnChartGestureListener l = ((BarLineChartBase)this.mChart).getOnChartGestureListener();
/*     */     
/* 635 */     if (l != null) {
/* 636 */       l.onChartFling(e1, e2, velocityX, velocityY);
/*     */     }
/*     */     
/* 639 */     return super.onFling(e1, e2, velocityX, velocityY);
/*     */   }
/*     */   
/*     */   public void stopDeceleration() {
/* 643 */     this.mDecelerationVelocity.x = 0.0F;
/* 644 */     this.mDecelerationVelocity.y = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void computeScroll() {
/* 649 */     if (this.mDecelerationVelocity.x == 0.0F && this.mDecelerationVelocity.y == 0.0F) {
/*     */       return;
/*     */     }
/* 652 */     long currentTime = AnimationUtils.currentAnimationTimeMillis();
/*     */     
/* 654 */     this.mDecelerationVelocity.x *= ((BarLineChartBase)this.mChart).getDragDecelerationFrictionCoef();
/* 655 */     this.mDecelerationVelocity.y *= ((BarLineChartBase)this.mChart).getDragDecelerationFrictionCoef();
/*     */     
/* 657 */     float timeInterval = (float)(currentTime - this.mDecelerationLastTime) / 1000.0F;
/*     */     
/* 659 */     float distanceX = this.mDecelerationVelocity.x * timeInterval;
/* 660 */     float distanceY = this.mDecelerationVelocity.y * timeInterval;
/*     */     
/* 662 */     this.mDecelerationCurrentPoint.x += distanceX;
/* 663 */     this.mDecelerationCurrentPoint.y += distanceY;
/*     */     
/* 665 */     MotionEvent event = MotionEvent.obtain(currentTime, currentTime, 2, this.mDecelerationCurrentPoint.x, this.mDecelerationCurrentPoint.y, 0);
/*     */ 
/*     */     
/* 668 */     float dragDistanceX = ((BarLineChartBase)this.mChart).isDragXEnabled() ? (this.mDecelerationCurrentPoint.x - this.mTouchStartPoint.x) : 0.0F;
/* 669 */     float dragDistanceY = ((BarLineChartBase)this.mChart).isDragYEnabled() ? (this.mDecelerationCurrentPoint.y - this.mTouchStartPoint.y) : 0.0F;
/*     */     
/* 671 */     performDrag(event, dragDistanceX, dragDistanceY);
/*     */     
/* 673 */     event.recycle();
/* 674 */     this.mMatrix = ((BarLineChartBase)this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, false);
/*     */     
/* 676 */     this.mDecelerationLastTime = currentTime;
/*     */     
/* 678 */     if (Math.abs(this.mDecelerationVelocity.x) >= 0.01D || Math.abs(this.mDecelerationVelocity.y) >= 0.01D) {
/* 679 */       Utils.postInvalidateOnAnimation(this.mChart);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 684 */       ((BarLineChartBase)this.mChart).calculateOffsets();
/* 685 */       ((BarLineChartBase)this.mChart).postInvalidate();
/*     */       
/* 687 */       stopDeceleration();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\listener\BarLineChartTouchListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */