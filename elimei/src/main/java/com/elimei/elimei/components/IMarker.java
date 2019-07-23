package com.elimei.elimei.components;

import android.graphics.Canvas;
import com.elimei.elimei.data.Entry;
import com.elimei.elimei.highlight.Highlight;
import com.elimei.elimei.utils.MPPointF;

public interface IMarker {
  MPPointF getOffset();
  
  MPPointF getOffsetForDrawingAtPoint(float paramFloat1, float paramFloat2);
  
  void refreshContent(Entry paramEntry, Highlight paramHighlight);
  
  void draw(Canvas paramCanvas, float paramFloat1, float paramFloat2);
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\IMarker.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */