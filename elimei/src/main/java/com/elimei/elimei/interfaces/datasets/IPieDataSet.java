package com.elimei.elimei.interfaces.datasets;

import com.elimei.elimei.data.PieDataSet;
import com.elimei.elimei.data.PieEntry;

public interface IPieDataSet extends IDataSet<PieEntry> {
  float getSliceSpace();
  
  boolean isAutomaticallyDisableSliceSpacingEnabled();
  
  float getSelectionShift();
  
  PieDataSet.ValuePosition getXValuePosition();
  
  PieDataSet.ValuePosition getYValuePosition();
  
  int getValueLineColor();
  
  float getValueLineWidth();
  
  float getValueLinePart1OffsetPercentage();
  
  float getValueLinePart1Length();
  
  float getValueLinePart2Length();
  
  boolean isValueLineVariableLength();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\IPieDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */