/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import android.util.Log;
/*     */ import com.elimei.elimei.highlight.Highlight;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
/*     */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CombinedData
/*     */   extends BarLineScatterCandleBubbleData<IBarLineScatterCandleBubbleDataSet<? extends Entry>>
/*     */ {
/*     */   private LineData mLineData;
/*     */   private BarData mBarData;
/*     */   private ScatterData mScatterData;
/*     */   private CandleData mCandleData;
/*     */   private BubbleData mBubbleData;
/*     */   
/*     */   public void setData(LineData data) {
/*  31 */     this.mLineData = data;
/*  32 */     notifyDataChanged();
/*     */   }
/*     */   
/*     */   public void setData(BarData data) {
/*  36 */     this.mBarData = data;
/*  37 */     notifyDataChanged();
/*     */   }
/*     */   
/*     */   public void setData(ScatterData data) {
/*  41 */     this.mScatterData = data;
/*  42 */     notifyDataChanged();
/*     */   }
/*     */   
/*     */   public void setData(CandleData data) {
/*  46 */     this.mCandleData = data;
/*  47 */     notifyDataChanged();
/*     */   }
/*     */   
/*     */   public void setData(BubbleData data) {
/*  51 */     this.mBubbleData = data;
/*  52 */     notifyDataChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void calcMinMax() {
/*  58 */     if (this.mDataSets == null) {
/*  59 */       this.mDataSets = new ArrayList();
/*     */     }
/*  61 */     this.mDataSets.clear();
/*     */     
/*  63 */     this.mYMax = -3.4028235E38F;
/*  64 */     this.mYMin = Float.MAX_VALUE;
/*  65 */     this.mXMax = -3.4028235E38F;
/*  66 */     this.mXMin = Float.MAX_VALUE;
/*     */     
/*  68 */     this.mLeftAxisMax = -3.4028235E38F;
/*  69 */     this.mLeftAxisMin = Float.MAX_VALUE;
/*  70 */     this.mRightAxisMax = -3.4028235E38F;
/*  71 */     this.mRightAxisMin = Float.MAX_VALUE;
/*     */     
/*  73 */     List<BarLineScatterCandleBubbleData> allData = getAllData();
/*     */     
/*  75 */     for (ChartData data : allData) {
/*     */       
/*  77 */       data.calcMinMax();
/*     */       
/*  79 */       List<IBarLineScatterCandleBubbleDataSet<? extends Entry>> sets = data.getDataSets();
/*  80 */       this.mDataSets.addAll(sets);
/*     */       
/*  82 */       if (data.getYMax() > this.mYMax) {
/*  83 */         this.mYMax = data.getYMax();
/*     */       }
/*  85 */       if (data.getYMin() < this.mYMin) {
/*  86 */         this.mYMin = data.getYMin();
/*     */       }
/*  88 */       if (data.getXMax() > this.mXMax) {
/*  89 */         this.mXMax = data.getXMax();
/*     */       }
/*  91 */       if (data.getXMin() < this.mXMin) {
/*  92 */         this.mXMin = data.getXMin();
/*     */       }
/*  94 */       if (data.mLeftAxisMax > this.mLeftAxisMax) {
/*  95 */         this.mLeftAxisMax = data.mLeftAxisMax;
/*     */       }
/*  97 */       if (data.mLeftAxisMin < this.mLeftAxisMin) {
/*  98 */         this.mLeftAxisMin = data.mLeftAxisMin;
/*     */       }
/* 100 */       if (data.mRightAxisMax > this.mRightAxisMax) {
/* 101 */         this.mRightAxisMax = data.mRightAxisMax;
/*     */       }
/* 103 */       if (data.mRightAxisMin < this.mRightAxisMin) {
/* 104 */         this.mRightAxisMin = data.mRightAxisMin;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 110 */   public BubbleData getBubbleData() { return this.mBubbleData; }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public LineData getLineData() { return this.mLineData; }
/*     */ 
/*     */ 
/*     */   
/* 118 */   public BarData getBarData() { return this.mBarData; }
/*     */ 
/*     */ 
/*     */   
/* 122 */   public ScatterData getScatterData() { return this.mScatterData; }
/*     */ 
/*     */ 
/*     */   
/* 126 */   public CandleData getCandleData() { return this.mCandleData; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BarLineScatterCandleBubbleData> getAllData() {
/* 136 */     List<BarLineScatterCandleBubbleData> data = new ArrayList<BarLineScatterCandleBubbleData>();
/* 137 */     if (this.mLineData != null)
/* 138 */       data.add(this.mLineData); 
/* 139 */     if (this.mBarData != null)
/* 140 */       data.add(this.mBarData); 
/* 141 */     if (this.mScatterData != null)
/* 142 */       data.add(this.mScatterData); 
/* 143 */     if (this.mCandleData != null)
/* 144 */       data.add(this.mCandleData); 
/* 145 */     if (this.mBubbleData != null) {
/* 146 */       data.add(this.mBubbleData);
/*     */     }
/* 148 */     return data;
/*     */   }
/*     */ 
/*     */   
/* 152 */   public BarLineScatterCandleBubbleData getDataByIndex(int index) { return (BarLineScatterCandleBubbleData)getAllData().get(index); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyDataChanged() {
/* 157 */     if (this.mLineData != null)
/* 158 */       this.mLineData.notifyDataChanged(); 
/* 159 */     if (this.mBarData != null)
/* 160 */       this.mBarData.notifyDataChanged(); 
/* 161 */     if (this.mCandleData != null)
/* 162 */       this.mCandleData.notifyDataChanged(); 
/* 163 */     if (this.mScatterData != null)
/* 164 */       this.mScatterData.notifyDataChanged(); 
/* 165 */     if (this.mBubbleData != null) {
/* 166 */       this.mBubbleData.notifyDataChanged();
/*     */     }
/* 168 */     calcMinMax();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry getEntryForHighlight(Highlight highlight) {
/* 180 */     if (highlight.getDataIndex() >= getAllData().size()) {
/* 181 */       return null;
/*     */     }
/* 183 */     ChartData data = getDataByIndex(highlight.getDataIndex());
/*     */     
/* 185 */     if (highlight.getDataSetIndex() >= data.getDataSetCount()) {
/* 186 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     List<Entry> entries = data.getDataSetByIndex(highlight.getDataSetIndex()).getEntriesForXValue(highlight.getX());
/* 193 */     for (Entry entry : entries) {
/* 194 */       if (entry.getY() == highlight.getY() || 
/* 195 */         Float.isNaN(highlight.getY()))
/* 196 */         return entry; 
/*     */     } 
/* 198 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBarLineScatterCandleBubbleDataSet<? extends Entry> getDataSetByHighlight(Highlight highlight) {
/* 208 */     if (highlight.getDataIndex() >= getAllData().size()) {
/* 209 */       return null;
/*     */     }
/* 211 */     BarLineScatterCandleBubbleData data = getDataByIndex(highlight.getDataIndex());
/*     */     
/* 213 */     if (highlight.getDataSetIndex() >= data.getDataSetCount()) {
/* 214 */       return null;
/*     */     }
/* 216 */     return (IBarLineScatterCandleBubbleDataSet)data
/* 217 */       .getDataSets().get(highlight.getDataSetIndex());
/*     */   }
/*     */ 
/*     */   
/* 221 */   public int getDataIndex(ChartData data) { return getAllData().indexOf(data); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeDataSet(IBarLineScatterCandleBubbleDataSet<? extends Entry> d) {
/* 227 */     List<BarLineScatterCandleBubbleData> datas = getAllData();
/*     */     
/* 229 */     boolean success = false;
/*     */     
/* 231 */     for (ChartData data : datas) {
/*     */       
/* 233 */       success = data.removeDataSet(d);
/*     */       
/* 235 */       if (success) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 240 */     return success;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean removeDataSet(int index) {
/* 246 */     Log.e("MPAndroidChart", "removeDataSet(int index) not supported for CombinedData");
/* 247 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean removeEntry(Entry e, int dataSetIndex) {
/* 253 */     Log.e("MPAndroidChart", "removeEntry(...) not supported for CombinedData");
/* 254 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean removeEntry(float xValue, int dataSetIndex) {
/* 260 */     Log.e("MPAndroidChart", "removeEntry(...) not supported for CombinedData");
/* 261 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\CombinedData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */