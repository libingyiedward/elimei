package com.elimei.elimei.interfaces.datasets;

import android.graphics.DashPathEffect;
import com.elimei.elimei.data.Entry;
import com.elimei.elimei.data.LineDataSet;
import com.elimei.elimei.formatter.IFillFormatter;

public interface ILineDataSet extends ILineRadarDataSet<Entry> {
  LineDataSet.Mode getMode();
  
  float getCubicIntensity();
  
  @Deprecated
  boolean isDrawCubicEnabled();
  
  @Deprecated
  boolean isDrawSteppedEnabled();
  
  float getCircleRadius();
  
  float getCircleHoleRadius();
  
  int getCircleColor(int paramInt);
  
  int getCircleColorCount();
  
  boolean isDrawCirclesEnabled();
  
  int getCircleHoleColor();
  
  boolean isDrawCircleHoleEnabled();
  
  DashPathEffect getDashPathEffect();
  
  boolean isDashedLineEnabled();
  
  IFillFormatter getFillFormatter();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\ILineDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */