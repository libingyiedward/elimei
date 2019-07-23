package com.elimei.elimei.interfaces.datasets;

import android.graphics.Paint;
import com.elimei.elimei.data.CandleEntry;

public interface ICandleDataSet extends ILineScatterCandleRadarDataSet<CandleEntry> {
  float getBarSpace();
  
  boolean getShowCandleBar();
  
  float getShadowWidth();
  
  int getShadowColor();
  
  int getNeutralColor();
  
  int getIncreasingColor();
  
  int getDecreasingColor();
  
  Paint.Style getIncreasingPaintStyle();
  
  Paint.Style getDecreasingPaintStyle();
  
  boolean getShadowColorSameAsCandle();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\ICandleDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */