package com.elimei.elimei.interfaces.datasets;

import com.elimei.elimei.data.RadarEntry;

public interface IRadarDataSet extends ILineRadarDataSet<RadarEntry> {
  boolean isDrawHighlightCircleEnabled();
  
  void setDrawHighlightCircleEnabled(boolean paramBoolean);
  
  int getHighlightCircleFillColor();
  
  int getHighlightCircleStrokeColor();
  
  int getHighlightCircleStrokeAlpha();
  
  float getHighlightCircleInnerRadius();
  
  float getHighlightCircleOuterRadius();
  
  float getHighlightCircleStrokeWidth();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\IRadarDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */