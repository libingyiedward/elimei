package com.elimei.elimei.interfaces.dataprovider;

import com.elimei.elimei.data.CombinedData;

public interface CombinedDataProvider extends LineDataProvider, BarDataProvider, BubbleDataProvider, CandleDataProvider, ScatterDataProvider {
  CombinedData getCombinedData();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\dataprovider\CombinedDataProvider.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */