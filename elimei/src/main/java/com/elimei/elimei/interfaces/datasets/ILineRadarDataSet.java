package com.elimei.elimei.interfaces.datasets;

import android.graphics.drawable.Drawable;
import com.elimei.elimei.data.Entry;

public interface ILineRadarDataSet<T extends Entry> extends ILineScatterCandleRadarDataSet<T> {
  int getFillColor();
  
  Drawable getFillDrawable();
  
  int getFillAlpha();
  
  float getLineWidth();
  
  boolean isDrawFilledEnabled();
  
  void setDrawFilled(boolean paramBoolean);
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\ILineRadarDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */