package com.elimei.elimei.animation;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;

@SuppressLint({"NewApi"})
public interface EasingFunction extends TimeInterpolator {
  float getInterpolation(float paramFloat);
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\animation\EasingFunction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */