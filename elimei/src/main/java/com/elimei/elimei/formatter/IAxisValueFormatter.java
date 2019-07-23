package com.elimei.elimei.formatter;

import com.elimei.elimei.components.AxisBase;

public interface IAxisValueFormatter {
  String getFormattedValue(float paramFloat, AxisBase paramAxisBase);
  
  int getFormattedColor(float paramFloat, AxisBase paramAxisBase);
  
  int GetColor(float paramFloat);
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\formatter\IAxisValueFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */