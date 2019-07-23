package com.elimei.elimei.interfaces.datasets;

import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import com.elimei.elimei.components.Legend;
import com.elimei.elimei.components.YAxis;
import com.elimei.elimei.data.DataSet;
import com.elimei.elimei.data.Entry;
import com.elimei.elimei.formatter.IValueFormatter;
import com.elimei.elimei.utils.MPPointF;
import java.util.List;

public interface IDataSet<T extends Entry> {
  float getYMin();
  
  float getYMax();
  
  float getXMin();
  
  float getXMax();
  
  int getEntryCount();
  
  void calcMinMax();
  
  void calcMinMaxY(float paramFloat1, float paramFloat2);
  
  T getEntryForXValue(float paramFloat1, float paramFloat2, DataSet.Rounding paramRounding);
  
  T getEntryForXValue(float paramFloat1, float paramFloat2);
  
  List<T> getEntriesForXValue(float paramFloat);
  
  T getEntryForIndex(int paramInt);
  
  int getEntryIndex(float paramFloat1, float paramFloat2, DataSet.Rounding paramRounding);
  
  int getEntryIndex(T paramT);
  
  int getIndexInEntries(int paramInt);
  
  boolean addEntry(T paramT);
  
  void addEntryOrdered(T paramT);
  
  boolean removeFirst();
  
  boolean removeLast();
  
  boolean removeEntry(T paramT);
  
  boolean removeEntryByXValue(float paramFloat);
  
  boolean removeEntry(int paramInt);
  
  boolean contains(T paramT);
  
  void clear();
  
  String getLabel();
  
  void setLabel(String paramString);
  
  YAxis.AxisDependency getAxisDependency();
  
  void setAxisDependency(YAxis.AxisDependency paramAxisDependency);
  
  List<Integer> getColors();
  
  int getColor();
  
  int getColor(int paramInt);
  
  boolean isHighlightEnabled();
  
  void setHighlightEnabled(boolean paramBoolean);
  
  void setValueFormatter(IValueFormatter paramIValueFormatter);
  
  IValueFormatter getValueFormatter();
  
  boolean needsFormatter();
  
  void setValueTextColor(int paramInt);
  
  void setValueTextColors(List<Integer> paramList);
  
  void setValueTypeface(Typeface paramTypeface);
  
  void setValueTextSize(float paramFloat);
  
  int getValueTextColor();
  
  int getValueTextColor(int paramInt);
  
  Typeface getValueTypeface();
  
  float getValueTextSize();
  
  Legend.LegendForm getForm();
  
  float getFormSize();
  
  float getFormLineWidth();
  
  DashPathEffect getFormLineDashEffect();
  
  void setDrawValues(boolean paramBoolean);
  
  boolean isDrawValuesEnabled();
  
  void setDrawIcons(boolean paramBoolean);
  
  boolean isDrawIconsEnabled();
  
  void setIconsOffset(MPPointF paramMPPointF);
  
  MPPointF getIconsOffset();
  
  void setVisible(boolean paramBoolean);
  
  boolean isVisible();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\IDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */