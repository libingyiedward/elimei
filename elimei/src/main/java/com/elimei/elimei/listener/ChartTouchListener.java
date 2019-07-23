/*     */ package com.elimei.elimei.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.elimei.elimei.charts.Chart;
import com.elimei.elimei.highlight.Highlight;

public abstract class ChartTouchListener<T extends Chart<?>> extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
/*     */   protected ChartGesture mLastGesture;
/*     */   protected static final int NONE = 0;
/*     */   protected static final int DRAG = 1;
/*     */   protected static final int X_ZOOM = 2;
/*     */   protected static final int Y_ZOOM = 3;
/*     */   protected static final int PINCH_ZOOM = 4;
/*     */   protected static final int POST_ZOOM = 5;
/*     */   protected static final int ROTATE = 6;
/*     */   protected int mTouchMode;
/*     */   protected Highlight mLastHighlighted;
/*     */   protected GestureDetector mGestureDetector;
/*     */   protected T mChart;
/*     */   
/*     */   public enum ChartGesture {
/*  16 */     NONE, DRAG, X_ZOOM, Y_ZOOM, PINCH_ZOOM, ROTATE, SINGLE_TAP, DOUBLE_TAP, LONG_PRESS, FLING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ChartTouchListener(T chart) {
/*  22 */     this.mLastGesture = ChartGesture.NONE;
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
/*  36 */     this.mTouchMode = 0;
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
/*  54 */     this.mChart = chart;
/*     */     
/*  56 */     this.mGestureDetector = new GestureDetector(chart.getContext(), this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startAction(MotionEvent me) {
/*  66 */     OnChartGestureListener l = this.mChart.getOnChartGestureListener();
/*     */     
/*  68 */     if (l != null) {
/*  69 */       l.onChartGestureStart(me, this.mLastGesture);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endAction(MotionEvent me) {
/*  79 */     OnChartGestureListener l = this.mChart.getOnChartGestureListener();
/*     */     
/*  81 */     if (l != null) {
/*  82 */       l.onChartGestureEnd(me, this.mLastGesture);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setLastHighlighted(Highlight high) { this.mLastHighlighted = high; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public int getTouchMode() { return this.mTouchMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public ChartGesture getLastGesture() { return this.mLastGesture; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void performHighlight(Highlight h, MotionEvent e) {
/* 120 */     if (h == null || h.equalTo(this.mLastHighlighted)) {
/* 121 */       this.mChart.highlightValue(null, true);
/* 122 */       this.mLastHighlighted = null;
/*     */     } else {
/* 124 */       this.mChart.highlightValue(h, true);
/* 125 */       this.mLastHighlighted = h;
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
/*     */   protected static float distance(float eventX, float startX, float eventY, float startY) {
/* 139 */     float dx = eventX - startX;
/* 140 */     float dy = eventY - startY;
/* 141 */     return (float)Math.sqrt((dx * dx + dy * dy));
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\listener\ChartTouchListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */