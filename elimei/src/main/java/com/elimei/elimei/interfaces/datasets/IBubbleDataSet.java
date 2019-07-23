package com.elimei.elimei.interfaces.datasets;

import com.elimei.elimei.data.BubbleEntry;

public interface IBubbleDataSet extends IBarLineScatterCandleBubbleDataSet<BubbleEntry> {
  void setHighlightCircleWidth(float paramFloat);
  
  float getMaxSize();
  
  boolean isNormalizeSizeEnabled();
  
  float getHighlightCircleWidth();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\IBubbleDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */