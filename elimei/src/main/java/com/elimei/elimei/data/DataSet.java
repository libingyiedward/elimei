package com.elimei.elimei.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;











public abstract class DataSet<T extends Entry>
  extends BaseDataSet<T>
{
/*  20 */   protected List<T> mValues = null;



  
/*  25 */   protected float mYMax = -3.4028235E38F;



  
/*  30 */   protected float mYMin = Float.MAX_VALUE;



  
/*  35 */   protected float mXMax = -3.4028235E38F;



  
/*  40 */   protected float mXMin = Float.MAX_VALUE;









  
  public DataSet(List<T> values, String label) {
/*  52 */     super(label);
/*  53 */     this.mValues = values;
    
/*  55 */     if (this.mValues == null) {
/*  56 */       this.mValues = new ArrayList();
    }
/*  58 */     calcMinMax();
  }


  
  public void calcMinMax() {
/*  64 */     if (this.mValues == null || this.mValues.isEmpty()) {
      return;
    }
/*  67 */     this.mYMax = -3.4028235E38F;
/*  68 */     this.mYMin = Float.MAX_VALUE;
/*  69 */     this.mXMax = -3.4028235E38F;
/*  70 */     this.mXMin = Float.MAX_VALUE;
    
/*  72 */     for (Iterator iterator = this.mValues.iterator(); iterator.hasNext(); ) { T e = (T)(Entry)iterator.next();
/*  73 */       calcMinMax(e); }
  
  }


  
  public void calcMinMaxY(float fromX, float toX) {
/*  80 */     if (this.mValues == null || this.mValues.isEmpty()) {
      return;
    }
/*  83 */     this.mYMax = -3.4028235E38F;
/*  84 */     this.mYMin = Float.MAX_VALUE;
    
/*  86 */     int indexFrom = getEntryIndex(fromX, 0f, Rounding.DOWN);
/*  87 */     int indexTo = getEntryIndex(toX, 0f, Rounding.UP);
    
/*  89 */     for (int i = indexFrom; i <= indexTo; i++)
    {
        calcMinMaxY(this.mValues.get(i));
    }
  }






  
  protected void calcMinMax(T e) {
/* 103 */     if (e == null) {
      return;
    }
/* 106 */     calcMinMaxX(e);
    
/* 108 */     calcMinMaxY(e);
  }

  
  protected void calcMinMaxX(T e) {
/* 113 */     if (e.getX() < this.mXMin) {
/* 114 */       this.mXMin = e.getX();
    }
/* 116 */     if (e.getX() > this.mXMax) {
/* 117 */       this.mXMax = e.getX();
    }
  }
  
  protected void calcMinMaxY(T e) {
/* 122 */     if (e.getY() < this.mYMin) {
/* 123 */       this.mYMin = e.getY();
    }
/* 125 */     if (e.getY() > this.mYMax) {
/* 126 */       this.mYMax = e.getY();
    }
  }

  
/* 131 */   public int getEntryCount() { return this.mValues.size(); }







  
/* 140 */   public List<T> getValues() { return this.mValues; }






  
  public void setValues(List<T> values) {
/* 149 */     this.mValues = values;
/* 150 */     notifyDataSetChanged();
  }



  
  public abstract DataSet<T> copy();



  
  public String toString() {
/* 162 */     StringBuffer buffer = new StringBuffer();
/* 163 */     buffer.append(toSimpleString());
/* 164 */     for (int i = 0; i < this.mValues.size(); i++) {
/* 165 */       buffer.append(((Entry)this.mValues.get(i)).toString() + " ");
    }
/* 167 */     return buffer.toString();
  }






  
  public String toSimpleString() {
/* 177 */     StringBuffer buffer = new StringBuffer();
/* 178 */     buffer.append("DataSet, label: " + ((getLabel() == null) ? "" : getLabel()) + ", entries: " + this.mValues.size() + "\n");
    
/* 180 */     return buffer.toString();
  }


  
/* 185 */   public float getYMin() { return this.mYMin; }



  
/* 190 */   public float getYMax() { return this.mYMax; }



  
/* 195 */   public float getXMin() { return this.mXMin; }



  
/* 200 */   public float getXMax() { return this.mXMax; }



  
  public void addEntryOrdered(T e) {
/* 206 */     if (e == null) {
      return;
    }
/* 209 */     if (this.mValues == null) {
/* 210 */       this.mValues = new ArrayList();
    }
    
/* 213 */     calcMinMax(e);
    
/* 215 */     if (this.mValues.size() > 0 && ((Entry)this.mValues.get(this.mValues.size() - 1)).getX() > e.getX()) {
/* 216 */       int closestIndex = getEntryIndex(e.getX(), e.getY(), Rounding.UP);
/* 217 */       this.mValues.add(closestIndex, e);
    } else {
/* 219 */       this.mValues.add(e);
    } 
  }

  
  public void clear() {
/* 225 */     this.mValues.clear();
/* 226 */     notifyDataSetChanged();
  }


  
  public boolean addEntry(T e) {
/* 232 */     if (e == null) {
/* 233 */       return false;
    }
/* 235 */     List<T> values = getValues();
/* 236 */     if (values == null) {
/* 237 */       values = new ArrayList<T>();
    }
    
/* 240 */     calcMinMax(e);

    
/* 243 */     return values.add(e);
  }


  
  public boolean removeEntry(T e) {
/* 249 */     if (e == null) {
/* 250 */       return false;
    }
/* 252 */     if (this.mValues == null) {
/* 253 */       return false;
    }
    
/* 256 */     boolean removed = this.mValues.remove(e);
    
/* 258 */     if (removed) {
/* 259 */       calcMinMax();
    }
    
/* 262 */     return removed;
  }


  
/* 267 */   public int getEntryIndex(Entry e) { return this.mValues.indexOf(e); }



  
  public T getEntryForXValue(float xValue, float closestToY, Rounding rounding) {
/* 273 */     int index = getEntryIndex(xValue, closestToY, rounding);
/* 274 */     if (index > -1)
/* 275 */       return (T)(Entry)this.mValues.get(index); 
/* 276 */     return null;
  }


  
/* 281 */   public T getEntryForXValue(float xValue, float closestToY) { return (T)getEntryForXValue(xValue, closestToY, Rounding.CLOSEST); }



  
/* 286 */   public T getEntryForIndex(int index) { return (T)(Entry)this.mValues.get(index); }



  
  public int getEntryIndex(float xValue, float closestToY, Rounding rounding) {
/* 292 */     if (this.mValues == null || this.mValues.isEmpty()) {
/* 293 */       return -1;
    }
/* 295 */     int low = 0;
/* 296 */     int high = this.mValues.size() - 1;
/* 297 */     int closest = high;
    
/* 299 */     while (low < high) {
/* 300 */       int m = (low + high) / 2;
      
/* 302 */       float d1 = ((Entry)this.mValues.get(m)).getX() - xValue;
/* 303 */       float d2 = ((Entry)this.mValues.get(m + 1)).getX() - xValue;
/* 304 */       float ad1 = Math.abs(d1), ad2 = Math.abs(d2);
      
/* 306 */       if (ad2 < ad1) {

        
/* 309 */         low = m + 1;
/* 310 */       } else if (ad1 < ad2) {

        
/* 313 */         high = m;

      
      }
/* 317 */       else if (d1 >= 0.0D) {
        
/* 319 */         high = m;
/* 320 */       } else if (d1 < 0.0D) {
        
/* 322 */         low = m + 1;
      } 

      
/* 326 */       closest = high;
    } 
    
/* 329 */     if (closest != -1) {
/* 330 */       float closestXValue = ((Entry)this.mValues.get(closest)).getX();
/* 331 */       if (rounding == Rounding.UP) {
        
/* 333 */         if (closestXValue < xValue && closest < this.mValues.size() - 1) {
/* 334 */           closest++;
        }
/* 336 */       } else if (rounding == Rounding.DOWN) {
        
/* 338 */         if (closestXValue > xValue && closest > 0) {
/* 339 */           closest--;
        }
      } 

      
/* 344 */       if (!Float.isNaN(closestToY)) {
/* 345 */         while (closest > 0 && ((Entry)this.mValues.get(closest - 1)).getX() == closestXValue) {
/* 346 */           closest--;
        }
/* 348 */         float closestYValue = ((Entry)this.mValues.get(closest)).getY();
/* 349 */         int closestYIndex = closest;

        
/* 352 */         closest++;
/* 353 */         while (closest < this.mValues.size()) {

          
/* 356 */           Entry value = (Entry)this.mValues.get(closest);
          
/* 358 */           if (value.getX() != closestXValue) {
            break;
          }
/* 361 */           if (Math.abs(value.getY() - closestToY) < Math.abs(closestYValue - closestToY)) {
/* 362 */             closestYValue = closestToY;
/* 363 */             closestYIndex = closest;
          } 
        } 
        
/* 367 */         closest = closestYIndex;
      } 
    } 
    
/* 371 */     return closest;
  }


  
  public List<T> getEntriesForXValue(float xValue) {
/* 377 */     List<T> entries = new ArrayList<T>();
    
/* 379 */     int low = 0;
/* 380 */     int high = this.mValues.size() - 1;
    
/* 382 */     while (low <= high) {
/* 383 */       int m = (high + low) / 2;
/* 384 */       T entry = (T)(Entry)this.mValues.get(m);

      
/* 387 */       if (xValue == entry.getX()) {
/* 388 */         while (m > 0 && ((Entry)this.mValues.get(m - 1)).getX() == xValue) {
/* 389 */           m--;
        }
/* 391 */         high = this.mValues.size();

        
/* 394 */         while (m < high) {
/* 395 */           entry = (T)(Entry)this.mValues.get(m);
/* 396 */           if (entry.getX() == xValue) {
/* 397 */             entries.add(entry);
            
            m++;
          } 
        } 
        
        break;
      } 
/* 405 */       if (xValue > entry.getX()) {
/* 406 */         low = m + 1; continue;
      } 
/* 408 */       high = m - 1;
    } 

    
/* 412 */     return entries;
  }




  
  public enum Rounding
  {
/* 421 */     UP,
/* 422 */     DOWN,
/* 423 */     CLOSEST;
  }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\DataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */