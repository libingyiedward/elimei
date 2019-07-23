package com.elimei.elimei.listener;

import android.view.MotionEvent;

public interface OnChartGestureListener {
  void onChartGestureStart(MotionEvent paramMotionEvent, ChartTouchListener.ChartGesture paramChartGesture);
  
  void onChartGestureEnd(MotionEvent paramMotionEvent, ChartTouchListener.ChartGesture paramChartGesture);
  
  void onChartLongPressed(MotionEvent paramMotionEvent);
  
  void onChartDoubleTapped(MotionEvent paramMotionEvent);
  
  void onChartSingleTapped(MotionEvent paramMotionEvent);
  
  void onChartFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2);
  
  void onChartScale(MotionEvent paramMotionEvent, float paramFloat1, float paramFloat2);
  
  void onChartTranslate(MotionEvent paramMotionEvent, float paramFloat1, float paramFloat2);
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\listener\OnChartGestureListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */