package com.elimei.elimei.interfaces.datasets;

import com.elimei.elimei.data.BarEntry;

public interface IBarDataSet extends IBarLineScatterCandleBubbleDataSet<BarEntry> {
  boolean isStacked();
  
  int getStackSize();
  
  int getBarShadowColor();
  
  float getBarBorderWidth();
  
  int getBarBorderColor();
  
  int getHighLightAlpha();
  
  String[] getStackLabels();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\IBarDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */