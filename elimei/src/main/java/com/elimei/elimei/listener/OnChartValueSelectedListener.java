package com.elimei.elimei.listener;

import com.elimei.elimei.data.Entry;
import com.elimei.elimei.highlight.Highlight;

public interface OnChartValueSelectedListener {
  void onValueSelected(Entry paramEntry, Highlight paramHighlight);
  
  void onNothingSelected();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\listener\OnChartValueSelectedListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */