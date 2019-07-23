package com.elimei.elimei.listener;

import com.elimei.elimei.data.DataSet;
import com.elimei.elimei.data.Entry;

public interface OnDrawListener {
  void onEntryAdded(Entry paramEntry);
  
  void onEntryMoved(Entry paramEntry);
  
  void onDrawFinished(DataSet<?> paramDataSet);
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\listener\OnDrawListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */