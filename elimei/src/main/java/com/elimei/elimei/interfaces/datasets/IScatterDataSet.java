package com.elimei.elimei.interfaces.datasets;

import com.elimei.elimei.data.Entry;
import com.elimei.elimei.renderer.scatter.IShapeRenderer;

public interface IScatterDataSet extends ILineScatterCandleRadarDataSet<Entry> {
  float getScatterShapeSize();
  
  float getScatterShapeHoleRadius();
  
  int getScatterShapeHoleColor();
  
  IShapeRenderer getShapeRenderer();
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\interfaces\datasets\IScatterDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */