package com.elimei.elimei.interfaces.dataprovider;

import com.elimei.elimei.data.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {
  BarData getBarData();
  
  boolean isDrawBarShadowEnabled();
  
  boolean isDrawValueAboveBarEnabled();
  
  boolean isHighlightFullBarEnabled();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\dataprovider\BarDataProvider.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */