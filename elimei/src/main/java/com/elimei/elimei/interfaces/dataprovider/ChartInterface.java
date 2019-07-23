package com.elimei.elimei.interfaces.dataprovider;

import android.graphics.RectF;
import com.elimei.elimei.data.ChartData;
import com.elimei.elimei.formatter.IValueFormatter;
import com.elimei.elimei.utils.MPPointF;

public interface ChartInterface {
  float getXChartMin();
  
  float getXChartMax();
  
  float getXRange();
  
  float getYChartMin();
  
  float getYChartMax();
  
  float getMaxHighlightDistance();
  
  int getWidth();
  
  int getHeight();
  
  MPPointF getCenterOfView();
  
  MPPointF getCenterOffsets();
  
  RectF getContentRect();
  
  IValueFormatter getDefaultValueFormatter();
  
  ChartData getData();
  
  int getMaxVisibleCount();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\dataprovider\ChartInterface.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */