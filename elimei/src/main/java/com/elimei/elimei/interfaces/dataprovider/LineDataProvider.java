package com.elimei.elimei.interfaces.dataprovider;

import com.elimei.elimei.components.YAxis;
import com.elimei.elimei.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {
  LineData getLineData();
  
  YAxis getAxis(YAxis.AxisDependency paramAxisDependency);
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\dataprovider\LineDataProvider.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */