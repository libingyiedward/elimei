package com.elimei.elimei.interfaces.datasets;

import android.graphics.DashPathEffect;
import com.elimei.elimei.data.Entry;

public interface ILineScatterCandleRadarDataSet<T extends Entry> extends IBarLineScatterCandleBubbleDataSet<T> {
  boolean isVerticalHighlightIndicatorEnabled();
  
  boolean isHorizontalHighlightIndicatorEnabled();
  
  float getHighlightLineWidth();
  
  DashPathEffect getDashPathEffectHighlight();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\ILineScatterCandleRadarDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */