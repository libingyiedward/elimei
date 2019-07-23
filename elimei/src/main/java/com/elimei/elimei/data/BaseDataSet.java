package com.elimei.elimei.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import com.elimei.elimei.components.Legend;
import com.elimei.elimei.components.YAxis;
import com.elimei.elimei.formatter.IValueFormatter;
import com.elimei.elimei.interfaces.datasets.IDataSet;
import com.elimei.elimei.utils.ColorTemplate;
import com.elimei.elimei.utils.MPPointF;
import com.elimei.elimei.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataSet<T extends Entry>
  extends Object implements IDataSet<T> {
  protected List<Integer> mColors;
  protected List<Integer> mValueColors;
  private String mLabel;
  protected YAxis.AxisDependency mAxisDependency;
  protected boolean mHighlightEnabled;
  protected IValueFormatter mValueFormatter;
  protected Typeface mValueTypeface;
  private Legend.LegendForm mForm;
  
  public BaseDataSet() {
/*  29 */     this.mColors = null;



    
/*  34 */     this.mValueColors = null;



    
/*  39 */     this.mLabel = "DataSet";



    
 this.mAxisDependency = YAxis.AxisDependency.LEFT;



    
 this.mHighlightEnabled = true;

this.mForm = Legend.LegendForm.DEFAULT;
     this.mFormSize = 0f ;
    this.mFormLineWidth = 0f;
    this.mFormLineDashEffect = null;



    
    this.mDrawValues = true;



    
   this.mDrawIcons = true;

    this.mIconsOffset = new MPPointF();



    
/*  84 */     this.mValueTextSize = 17.0F;



    
/*  89 */     this.mVisible = true;




    
/*  95 */     this.mColors = new ArrayList();
/*  96 */     this.mValueColors = new ArrayList();

    
/*  99 */     this.mColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
/* 100 */     this.mValueColors.add(Integer.valueOf(-16777216));
  }
  
  private float mFormSize;
  private float mFormLineWidth;
  private DashPathEffect mFormLineDashEffect;
  protected boolean mDrawValues;
  
  public BaseDataSet(String label) {
/* 109 */     this();
/* 110 */     this.mLabel = label;
  }

  
  protected boolean mDrawIcons;
  protected MPPointF mIconsOffset;
  
/* 117 */   public void notifyDataSetChanged() { calcMinMax(); }


  
  protected float mValueTextSize;

  
  protected boolean mVisible;

  
/* 127 */   public List<Integer> getColors() { return this.mColors; }


  
/* 131 */   public List<Integer> getValueColors() { return this.mValueColors; }



  
/* 136 */   public int getColor() { return ((Integer)this.mColors.get(0)).intValue(); }



  
/* 141 */   public int getColor(int index) { return ((Integer)this.mColors.get(index % this.mColors.size())).intValue(); }















  
/* 158 */   public void setColors(List<Integer> colors) { this.mColors = colors; }











  
/* 171 */   public void setColors(int... colors) { this.mColors = ColorTemplate.createColors(colors); }












  
  public void setColors(int[] colors, Context c) {
/* 186 */     if (this.mColors == null) {
/* 187 */       this.mColors = new ArrayList();
    }
    
/* 190 */     this.mColors.clear();
    
/* 192 */     for (int color : colors) {
/* 193 */       this.mColors.add(Integer.valueOf(c.getResources().getColor(color)));
    }
  }





  
  public void addColor(int color) {
/* 203 */     if (this.mColors == null)
/* 204 */       this.mColors = new ArrayList(); 
/* 205 */     this.mColors.add(Integer.valueOf(color));
  }






  
  public void setColor(int color) {
/* 215 */     resetColors();
/* 216 */     this.mColors.add(Integer.valueOf(color));
  }







  
/* 226 */   public void setColor(int color, int alpha) { setColor(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color))); }







  
  public void setColors(int[] colors, int alpha) {
/* 236 */     resetColors();
/* 237 */     for (int color : colors) {
/* 238 */       addColor(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)));
    }
  }



  
  public void resetColors() {
/* 246 */     if (this.mColors == null) {
/* 247 */       this.mColors = new ArrayList();
    }
/* 249 */     this.mColors.clear();
  }






  
/* 258 */   public void setLabel(String label) { this.mLabel = label; }



  
/* 263 */   public String getLabel() { return this.mLabel; }



  
/* 268 */   public void setHighlightEnabled(boolean enabled) { this.mHighlightEnabled = enabled; }



  
/* 273 */   public boolean isHighlightEnabled() { return this.mHighlightEnabled; }



  
  public void setValueFormatter(IValueFormatter f) {
/* 279 */     if (f == null) {
      return;
    }
/* 282 */     this.mValueFormatter = f;
  }

  
  public IValueFormatter getValueFormatter() {
/* 287 */     if (needsFormatter())
/* 288 */       return Utils.getDefaultValueFormatter(); 
/* 289 */     return this.mValueFormatter;
  }


  
/* 294 */   public boolean needsFormatter() { return (this.mValueFormatter == null); }


  
  public void setValueTextColor(int color) {
/* 299 */     this.mValueColors.clear();
/* 300 */     this.mValueColors.add(Integer.valueOf(color));
  }


  
/* 305 */   public void setValueTextColors(List<Integer> colors) { this.mValueColors = colors; }



  
/* 310 */   public void setValueTypeface(Typeface tf) { this.mValueTypeface = tf; }



  
/* 315 */   public void setValueTextSize(float size) { this.mValueTextSize = Utils.convertDpToPixel(size); }



  
/* 320 */   public int getValueTextColor() { return ((Integer)this.mValueColors.get(0)).intValue(); }



  
/* 325 */   public int getValueTextColor(int index) { return ((Integer)this.mValueColors.get(index % this.mValueColors.size())).intValue(); }



  
/* 330 */   public Typeface getValueTypeface() { return this.mValueTypeface; }



  
/* 335 */   public float getValueTextSize() { return this.mValueTextSize; }


  
/* 339 */   public void setForm(Legend.LegendForm form) { this.mForm = form; }



  
/* 344 */   public Legend.LegendForm getForm() { return this.mForm; }


  
/* 348 */   public void setFormSize(float formSize) { this.mFormSize = formSize; }



  
/* 353 */   public float getFormSize() { return this.mFormSize; }


  
/* 357 */   public void setFormLineWidth(float formLineWidth) { this.mFormLineWidth = formLineWidth; }



  
/* 362 */   public float getFormLineWidth() { return this.mFormLineWidth; }


  
/* 366 */   public void setFormLineDashEffect(DashPathEffect dashPathEffect) { this.mFormLineDashEffect = dashPathEffect; }



  
/* 371 */   public DashPathEffect getFormLineDashEffect() { return this.mFormLineDashEffect; }



  
/* 376 */   public void setDrawValues(boolean enabled) { this.mDrawValues = enabled; }



  
/* 381 */   public boolean isDrawValuesEnabled() { return this.mDrawValues; }



  
/* 386 */   public void setDrawIcons(boolean enabled) { this.mDrawIcons = enabled; }



  
/* 391 */   public boolean isDrawIconsEnabled() { return this.mDrawIcons; }



  
  public void setIconsOffset(MPPointF offsetDp) {
/* 397 */     this.mIconsOffset.x = offsetDp.x;
/* 398 */     this.mIconsOffset.y = offsetDp.y;
  }


  
/* 403 */   public MPPointF getIconsOffset() { return this.mIconsOffset; }



  
/* 408 */   public void setVisible(boolean visible) { this.mVisible = visible; }



  
/* 413 */   public boolean isVisible() { return this.mVisible; }



  
/* 418 */   public YAxis.AxisDependency getAxisDependency() { return this.mAxisDependency; }



  
/* 423 */   public void setAxisDependency(YAxis.AxisDependency dependency) { this.mAxisDependency = dependency; }








  
  public int getIndexInEntries(int xIndex) {
/* 434 */     for (int i = 0; i < getEntryCount(); i++) {
/* 435 */       if (xIndex == getEntryForIndex(i).getX()) {
/* 436 */         return i;
      }
    } 
/* 439 */     return -1;
  }


  
  public boolean removeFirst() {
/* 445 */     if (getEntryCount() > 0) {
      
/* 447 */       T entry = (T)getEntryForIndex(0);
/* 448 */       return removeEntry(entry);
    } 
/* 450 */     return false;
  }


  
  public boolean removeLast() {
/* 456 */     if (getEntryCount() > 0) {
      
/* 458 */       T e = (T)getEntryForIndex(getEntryCount() - 1);
/* 459 */       return removeEntry(e);
    } 
/* 461 */     return false;
  }


  
  public boolean removeEntryByXValue(float xValue) {
/* 467 */     T e = (T)getEntryForXValue(xValue, 0f);
/* 468 */     return removeEntry(e);
  }


  
  public boolean removeEntry(int index) {
/* 474 */     T e = (T)getEntryForIndex(index);
/* 475 */     return removeEntry(e);
  }


  
  public boolean contains(T e) {
/* 481 */     for (int i = 0; i < getEntryCount(); i++) {
/* 482 */       if (getEntryForIndex(i).equals(e)) {
/* 483 */         return true;
      }
    } 
/* 486 */     return false;
  }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BaseDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */