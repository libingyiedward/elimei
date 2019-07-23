package com.elimei.elimei.data;

import android.graphics.Typeface;
import android.util.Log;

import com.elimei.elimei.components.YAxis;
import com.elimei.elimei.formatter.IValueFormatter;
import com.elimei.elimei.highlight.Highlight;
import com.elimei.elimei.interfaces.datasets.IDataSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class ChartData<T extends IDataSet<? extends Entry>>
        extends Object {
    protected float mYMax = -3.4028235E38F;


    protected float mYMin = Float.MAX_VALUE;


    protected float mXMax = -3.4028235E38F;


    protected float mXMin = Float.MAX_VALUE;


    protected float mLeftAxisMax = -3.4028235E38F;

    protected float mLeftAxisMin = Float.MAX_VALUE;

    protected float mRightAxisMax = -3.4028235E38F;

    protected float mRightAxisMin = Float.MAX_VALUE;


    protected List<T> mDataSets;


    /*  61 */
    public ChartData() {
        this.mDataSets = new ArrayList();
    }


    public ChartData(T... dataSets) {
        /*  70 */
        this.mDataSets = arrayToList(dataSets);
        /*  71 */
        notifyDataChanged();
    }


    private List<T> arrayToList(T[] array) {
        /*  82 */
        List<T> list = new ArrayList<T>();

        /*  84 */
        for (T set : array) {
            /*  85 */
            list.add(set);
        }

        /*  88 */
        return list;
    }


    public ChartData(List<T> sets) {
        /*  97 */
        this.mDataSets = sets;
        /*  98 */
        notifyDataChanged();
    }


    /* 107 */
    public void notifyDataChanged() {
        calcMinMax();
    }


    public void calcMinMaxY(float fromX, float toX) {
        /* 119 */
        for (Iterator iterator = this.mDataSets.iterator(); iterator.hasNext(); ) {
            T set = (T) (IDataSet) iterator.next();
            /* 120 */
            set.calcMinMaxY(fromX, toX);
        }

        calcMinMax();
    }


    protected void calcMinMax() {
        /* 132 */
        if (this.mDataSets == null) {
            return;
        }
        /* 135 */
        this.mYMax = -3.4028235E38F;
        /* 136 */
        this.mYMin = Float.MAX_VALUE;
        /* 137 */
        this.mXMax = -3.4028235E38F;
        /* 138 */
        this.mXMin = Float.MAX_VALUE;

        /* 140 */
        for (this.mDataSets.iterator(); mDataSets.iterator().hasNext(); ) {
            T set = (T) (IDataSet) mDataSets.iterator().next();
            /* 141 */
            calcMinMax(set);
        }



        /* 144 */
        this.mLeftAxisMax = -3.4028235E38F;
        /* 145 */
        this.mLeftAxisMin = Float.MAX_VALUE;
        /* 146 */
        this.mRightAxisMax = -3.4028235E38F;
        /* 147 */
        this.mRightAxisMin = Float.MAX_VALUE;


        /* 150 */
        T firstLeft = (T) getFirstLeft(this.mDataSets);

        /* 152 */
        if (firstLeft != null) {

            /* 154 */
            this.mLeftAxisMax = firstLeft.getYMax();
            /* 155 */
            this.mLeftAxisMin = firstLeft.getYMin();

            /* 157 */
            for (Iterator iterator = this.mDataSets.iterator(); iterator.hasNext(); ) {
                T dataSet = (T) (IDataSet) iterator.next();
                /* 158 */
                if (dataSet.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                    /* 159 */
                    if (dataSet.getYMin() < this.mLeftAxisMin) {
                        /* 160 */
                        this.mLeftAxisMin = dataSet.getYMin();
                    }
                    /* 162 */
                    if (dataSet.getYMax() > this.mLeftAxisMax) {
                        /* 163 */
                        this.mLeftAxisMax = dataSet.getYMax();
                    }
                }
            }

        }

        /* 169 */
        T firstRight = (T) getFirstRight(this.mDataSets);

        /* 171 */
        if (firstRight != null) {

            /* 173 */
            this.mRightAxisMax = firstRight.getYMax();
            /* 174 */
            this.mRightAxisMin = firstRight.getYMin();

            /* 176 */
            for (Iterator iterator = this.mDataSets.iterator(); iterator.hasNext(); ) {
                T dataSet = (T) (IDataSet) iterator.next();
                /* 177 */
                if (dataSet.getAxisDependency() == YAxis.AxisDependency.RIGHT) {
                    /* 178 */
                    if (dataSet.getYMin() < this.mRightAxisMin) {
                        /* 179 */
                        this.mRightAxisMin = dataSet.getYMin();
                    }
                    /* 181 */
                    if (dataSet.getYMax() > this.mRightAxisMax) {
                        /* 182 */
                        this.mRightAxisMax = dataSet.getYMax();
                    }
                }
            }

        }
    }


    public int getDataSetCount() {
        /* 196 */
        if (this.mDataSets == null)
            /* 197 */ return 0;
        /* 198 */
        return this.mDataSets.size();
    }


    /* 207 */
    public float getYMin() {
        return this.mYMin;
    }


    public float getYMin(YAxis.AxisDependency axis) {
        /* 217 */
        if (axis == YAxis.AxisDependency.LEFT) {

            /* 219 */
            if (this.mLeftAxisMin == Float.MAX_VALUE) {
                /* 220 */
                return this.mRightAxisMin;
            }
            /* 222 */
            return this.mLeftAxisMin;
        }
        /* 224 */
        if (this.mRightAxisMin == Float.MAX_VALUE) {
            /* 225 */
            return this.mLeftAxisMin;
        }
        /* 227 */
        return this.mRightAxisMin;
    }


    /* 237 */
    public float getYMax() {
        return this.mYMax;
    }


    public float getYMax(YAxis.AxisDependency axis) {
        /* 247 */
        if (axis == YAxis.AxisDependency.LEFT) {

            /* 249 */
            if (this.mLeftAxisMax == -3.4028235E38F) {
                /* 250 */
                return this.mRightAxisMax;
            }
            /* 252 */
            return this.mLeftAxisMax;
        }
        /* 254 */
        if (this.mRightAxisMax == -3.4028235E38F) {
            /* 255 */
            return this.mLeftAxisMax;
        }
        /* 257 */
        return this.mRightAxisMax;
    }


    /* 267 */
    public float getXMin() {
        return this.mXMin;
    }


    /* 276 */
    public float getXMax() {
        return this.mXMax;
    }


    /* 285 */
    public List<T> getDataSets() {
        return this.mDataSets;
    }


    protected int getDataSetIndexByLabel(List<T> dataSets, String label, boolean ignorecase) {
        /* 302 */
        if (ignorecase)
            /* 303 */ {
            for (int i = 0; i < dataSets.size(); i++) {
                /* 304 */
                if (label.equalsIgnoreCase(((IDataSet) dataSets.get(i)).getLabel()))
                    /* 305 */ return i;
            }
        }
        /* 307 */
        else {
            for (int i = 0; i < dataSets.size(); i++) {
                /* 308 */
                if (label.equals(((IDataSet) dataSets.get(i)).getLabel()))
                    /* 309 */ return i;
            }
        }

        /* 312 */
        return -1;
    }


    public String[] getDataSetLabels() {
        /* 322 */
        String[] types = new String[this.mDataSets.size()];

        /* 324 */
        for (int i = 0; i < this.mDataSets.size(); i++) {
            /* 325 */
            types[i] = ((IDataSet) this.mDataSets.get(i)).getLabel();
        }

        /* 328 */
        return types;
    }


    public Entry getEntryForHighlight(Highlight highlight) {
        /* 338 */
        if (highlight.getDataSetIndex() >= this.mDataSets.size()) {
            /* 339 */
            return null;
        }
        /* 341 */
        return ((IDataSet) this.mDataSets.get(highlight.getDataSetIndex())).getEntryForXValue(highlight.getX(), highlight.getY());
    }


    public T getDataSetByLabel(String label, boolean ignorecase) {
        /* 356 */
        int index = getDataSetIndexByLabel(this.mDataSets, label, ignorecase);

        /* 358 */
        if (index < 0 || index >= this.mDataSets.size()) {
            /* 359 */
            return null;
        }
        /* 361 */
        return (T) (IDataSet) this.mDataSets.get(index);
    }

    public T getDataSetByIndex(int index) {
        /* 366 */
        if (this.mDataSets == null || index < 0 || index >= this.mDataSets.size()) {
            /* 367 */
            return null;
        }
        /* 369 */
        return (T) (IDataSet) this.mDataSets.get(index);
    }

    public void addDataSet(T d) {
        /* 379 */
        if (d == null) {
            return;
        }
        /* 382 */
        calcMinMax(d);

        /* 384 */
        this.mDataSets.add(d);
    }


    public boolean removeDataSet(T d) {
        /* 396 */
        if (d == null) {
            /* 397 */
            return false;
        }
        /* 399 */
        boolean removed = this.mDataSets.remove(d);


        /* 402 */
        if (removed) {
            /* 403 */
            calcMinMax();
        }

        /* 406 */
        return removed;
    }


    public boolean removeDataSet(int index) {
        /* 418 */
        if (index >= this.mDataSets.size() || index < 0) {
            /* 419 */
            return false;
        }
        /* 421 */
        T set = (T) (IDataSet) this.mDataSets.get(index);
        /* 422 */
        return removeDataSet(set);
    }


    public void addEntry(Entry e, int dataSetIndex) {
        /* 434 */
        if (this.mDataSets.size() > dataSetIndex && dataSetIndex >= 0) {

            /* 436 */
            IDataSet set = (IDataSet) this.mDataSets.get(dataSetIndex);

            /* 438 */
            if (!set.addEntry(e)) {
                return;
            }
            /* 441 */
            calcMinMax(e, set.getAxisDependency());
        } else {

            /* 444 */
            Log.e("addEntry", "Cannot add Entry because dataSetIndex too high or too low.");
        }
    }


    protected void calcMinMax(Entry e, YAxis.AxisDependency axis) {
        /* 456 */
        if (this.mYMax < e.getY())
            /* 457 */ this.mYMax = e.getY();
        /* 458 */
        if (this.mYMin > e.getY()) {
            /* 459 */
            this.mYMin = e.getY();
        }
        /* 461 */
        if (this.mXMax < e.getX())
            /* 462 */ this.mXMax = e.getX();
        /* 463 */
        if (this.mXMin > e.getX()) {
            /* 464 */
            this.mXMin = e.getX();
        }
        /* 466 */
        if (axis == YAxis.AxisDependency.LEFT) {

            /* 468 */
            if (this.mLeftAxisMax < e.getY())
                /* 469 */ this.mLeftAxisMax = e.getY();
            /* 470 */
            if (this.mLeftAxisMin > e.getY())
                /* 471 */ this.mLeftAxisMin = e.getY();
        } else {
            /* 473 */
            if (this.mRightAxisMax < e.getY())
                /* 474 */ this.mRightAxisMax = e.getY();
            /* 475 */
            if (this.mRightAxisMin > e.getY()) {
                /* 476 */
                this.mRightAxisMin = e.getY();
            }
        }
    }


    protected void calcMinMax(T d) {
        /* 487 */
        if (this.mYMax < d.getYMax())
            /* 488 */ this.mYMax = d.getYMax();
        /* 489 */
        if (this.mYMin > d.getYMin()) {
            /* 490 */
            this.mYMin = d.getYMin();
        }
        /* 492 */
        if (this.mXMax < d.getXMax())
            /* 493 */ this.mXMax = d.getXMax();
        /* 494 */
        if (this.mXMin > d.getXMin()) {
            /* 495 */
            this.mXMin = d.getXMin();
        }
        /* 497 */
        if (d.getAxisDependency() == YAxis.AxisDependency.LEFT) {

            /* 499 */
            if (this.mLeftAxisMax < d.getYMax())
                /* 500 */ this.mLeftAxisMax = d.getYMax();
            /* 501 */
            if (this.mLeftAxisMin > d.getYMin())
                /* 502 */ this.mLeftAxisMin = d.getYMin();
        } else {
            /* 504 */
            if (this.mRightAxisMax < d.getYMax())
                /* 505 */ this.mRightAxisMax = d.getYMax();
            /* 506 */
            if (this.mRightAxisMin > d.getYMin()) {
                /* 507 */
                this.mRightAxisMin = d.getYMin();
            }
        }
    }


    public boolean removeEntry(Entry e, int dataSetIndex) {
        /* 520 */
        if (e == null || dataSetIndex >= this.mDataSets.size()) {
            /* 521 */
            return false;
        }
        /* 523 */
        IDataSet set = (IDataSet) this.mDataSets.get(dataSetIndex);

        /* 525 */
        if (set != null) {

            /* 527 */
            boolean removed = set.removeEntry(e);

            /* 529 */
            if (removed) {
                /* 530 */
                calcMinMax();
            }

            /* 533 */
            return removed;
        }
        /* 535 */
        return false;
    }


    public boolean removeEntry(float xValue, int dataSetIndex) {
        if (dataSetIndex >= this.mDataSets.size()) {
            return false;
        }
        IDataSet dataSet = (IDataSet) this.mDataSets.get(dataSetIndex);
        Entry e = dataSet.getEntryForXValue(xValue, 0f);

        if (e == null) {
            return false;
        }
        return removeEntry(e, dataSetIndex);
    }


    public T getDataSetForEntry(Entry e) {
        /* 570 */
        if (e == null) {
            /* 571 */
            return null;
        }
        /* 573 */
        for (int i = 0; i < this.mDataSets.size(); i++) {

            /* 575 */
            T set = (T) (IDataSet) this.mDataSets.get(i);

            /* 577 */
            for (int j = 0; j < set.getEntryCount(); j++) {
                /* 578 */
                if (e.equalTo(set.getEntryForXValue(e.getX(), e.getY()))) {
                    /* 579 */
                    return set;
                }
            }
        }
        /* 583 */
        return null;
    }


    public int[] getColors() {
        /* 594 */
        if (this.mDataSets == null) {
            /* 595 */
            return null;
        }
        /* 597 */
        int clrcnt = 0;

        /* 599 */
        for (int i = 0; i < this.mDataSets.size(); i++) {
            /* 600 */
            clrcnt += ((IDataSet) this.mDataSets.get(i)).getColors().size();
        }

        /* 603 */
        int[] colors = new int[clrcnt];
        /* 604 */
        int cnt = 0;

        /* 606 */
        for (int i = 0; i < this.mDataSets.size(); i++) {

            /* 608 */
            List<Integer> clrs = ((IDataSet) this.mDataSets.get(i)).getColors();

            /* 610 */
            for (Integer clr : clrs) {
                /* 611 */
                colors[cnt] = clr.intValue();
                /* 612 */
                cnt++;
            }
        }

        /* 616 */
        return colors;
    }


    /* 626 */
    public int getIndexOfDataSet(T dataSet) {
        return this.mDataSets.indexOf(dataSet);
    }


    protected T getFirstLeft(List<T> sets) {
        /* 636 */
        for (Iterator iterator = sets.iterator(); iterator.hasNext(); ) {
            T dataSet = (T) (IDataSet) iterator.next();
            /* 637 */
            if (dataSet.getAxisDependency() == YAxis.AxisDependency.LEFT)
                /* 638 */ return dataSet;
        }

        /* 640 */
        return null;
    }


    public T getFirstRight(List<T> sets) {
        /* 650 */
        for (Iterator iterator = sets.iterator(); iterator.hasNext(); ) {
            T dataSet = (T) (IDataSet) iterator.next();
            /* 651 */
            if (dataSet.getAxisDependency() == YAxis.AxisDependency.RIGHT)
                /* 652 */ return dataSet;
        }

        /* 654 */
        return null;
    }


    public void setValueFormatter(IValueFormatter f) {
        /* 663 */
        if (f == null) {
            return;
        }
        /* 666 */
        for (IDataSet set : this.mDataSets) {
            /* 667 */
            set.setValueFormatter(f);
        }
    }


    public void setValueTextColor(int color) {
        /* 679 */
        for (IDataSet set : this.mDataSets) {
            /* 680 */
            set.setValueTextColor(color);
        }
    }


    public void setValueTextColors(List<Integer> colors) {
        /* 691 */
        for (IDataSet set : this.mDataSets) {
            /* 692 */
            set.setValueTextColors(colors);
        }
    }


    public void setValueTypeface(Typeface tf) {
        /* 703 */
        for (IDataSet set : this.mDataSets) {
            /* 704 */
            set.setValueTypeface(tf);
        }
    }


    public void setValueTextSize(float size) {
        /* 715 */
        for (IDataSet set : this.mDataSets) {
            /* 716 */
            set.setValueTextSize(size);
        }
    }


    public void setDrawValues(boolean enabled) {
        /* 727 */
        for (IDataSet set : this.mDataSets) {
            /* 728 */
            set.setDrawValues(enabled);
        }
    }


    public void setHighlightEnabled(boolean enabled) {
        /* 738 */
        for (IDataSet set : this.mDataSets) {
            /* 739 */
            set.setHighlightEnabled(enabled);
        }
    }


    public boolean isHighlightEnabled() {
        /* 750 */
        for (IDataSet set : this.mDataSets) {
            /* 751 */
            if (!set.isHighlightEnabled())
                /* 752 */ return false;
        }
        /* 754 */
        return true;
    }


    public void clearValues() {
        /* 762 */
        if (this.mDataSets != null) {
            /* 763 */
            this.mDataSets.clear();
        }
        /* 765 */
        notifyDataChanged();
    }


    public boolean contains(T dataSet) {
        /* 777 */
        for (Iterator iterator = this.mDataSets.iterator(); iterator.hasNext(); ) {
            T set = (T) (IDataSet) iterator.next();
            /* 778 */
            if (set.equals(dataSet)) {
                /* 779 */
                return true;
            }
        }

        /* 782 */
        return false;
    }


    public int getEntryCount() {
        /* 792 */
        int count = 0;

        /* 794 */
        for (Iterator iterator = this.mDataSets.iterator(); iterator.hasNext(); ) {
            T set = (T) (IDataSet) iterator.next();
            /* 795 */
            count += set.getEntryCount();
        }


        /* 798 */
        return count;
    }


    public T getMaxEntryCountSet() {
        /* 808 */
        if (this.mDataSets == null || this.mDataSets.isEmpty()) {
            /* 809 */
            return null;
        }
        /* 811 */
        T max = (T) (IDataSet) this.mDataSets.get(0);

        /* 813 */
        for (Iterator iterator = this.mDataSets.iterator(); iterator.hasNext(); ) {
            T set = (T) (IDataSet) iterator.next();

            /* 815 */
            if (set.getEntryCount() > max.getEntryCount()) {
                /* 816 */
                max = set;
            }
        }

        /* 819 */
        return max;
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\ChartData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */