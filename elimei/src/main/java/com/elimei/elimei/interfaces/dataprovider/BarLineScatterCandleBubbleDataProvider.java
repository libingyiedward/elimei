package com.elimei.elimei.interfaces.dataprovider;

import com.elimei.elimei.components.YAxis;
import com.elimei.elimei.data.BarLineScatterCandleBubbleData;
import com.elimei.elimei.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {
  Transformer getTransformer(YAxis.AxisDependency paramAxisDependency);
  
  boolean isInverted(YAxis.AxisDependency paramAxisDependency);
  
  float getLowestVisibleX();
  
  float getHighestVisibleX();
  
  BarLineScatterCandleBubbleData getData();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\dataprovider\BarLineScatterCandleBubbleDataProvider.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */