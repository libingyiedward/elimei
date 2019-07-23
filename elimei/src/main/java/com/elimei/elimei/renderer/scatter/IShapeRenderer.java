package com.elimei.elimei.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.elimei.elimei.interfaces.datasets.IScatterDataSet;
import com.elimei.elimei.utils.ViewPortHandler;

public interface IShapeRenderer {
  void renderShape(Canvas paramCanvas, IScatterDataSet paramIScatterDataSet, ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Paint paramPaint);
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\scatter\IShapeRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */