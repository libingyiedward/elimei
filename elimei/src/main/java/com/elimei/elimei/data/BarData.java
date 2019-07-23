/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
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
/*     */ public class BarData
/*     */   extends BarLineScatterCandleBubbleData<IBarDataSet>
/*     */ {
/*  18 */   private float mBarWidth = 0.85F;
/*     */ 
/*     */ 
/*     */   
/*     */   public BarData() {}
/*     */ 
/*     */   
/*  25 */   public BarData(IBarDataSet... dataSets) { super(dataSets); }
/*     */ 
/*     */ 
/*     */   
/*  29 */   public BarData(List<IBarDataSet> dataSets) { super(dataSets); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public void setBarWidth(float mBarWidth) { this.mBarWidth = mBarWidth; }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public float getBarWidth() { return this.mBarWidth; }
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
/*     */   
/*     */   public void groupBars(float fromX, float groupSpace, float barSpace) {
/*  58 */     int setCount = this.mDataSets.size();
/*  59 */     if (setCount <= 1) {
/*  60 */       throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
/*     */     }
/*     */     
/*  63 */     IBarDataSet max = (IBarDataSet)getMaxEntryCountSet();
/*  64 */     int maxEntryCount = max.getEntryCount();
/*     */     
/*  66 */     float groupSpaceWidthHalf = groupSpace / 2.0F;
/*  67 */     float barSpaceHalf = barSpace / 2.0F;
/*  68 */     float barWidthHalf = this.mBarWidth / 2.0F;
/*     */     
/*  70 */     float interval = getGroupWidth(groupSpace, barSpace);
/*     */     
/*  72 */     for (int i = 0; i < maxEntryCount; i++) {
/*     */       
/*  74 */       float start = fromX;
/*  75 */       fromX += groupSpaceWidthHalf;
/*     */       
/*  77 */       for (IBarDataSet set : this.mDataSets) {
/*     */         
/*  79 */         fromX += barSpaceHalf;
/*  80 */         fromX += barWidthHalf;
/*     */         
/*  82 */         if (i < set.getEntryCount()) {
/*     */           
/*  84 */           BarEntry entry = (BarEntry)set.getEntryForIndex(i);
/*     */           
/*  86 */           if (entry != null) {
/*  87 */             entry.setX(fromX);
/*     */           }
/*     */         } 
/*     */         
/*  91 */         fromX += barWidthHalf;
/*  92 */         fromX += barSpaceHalf;
/*     */       } 
/*     */       
/*  95 */       fromX += groupSpaceWidthHalf;
/*  96 */       float end = fromX;
/*  97 */       float innerInterval = end - start;
/*  98 */       float diff = interval - innerInterval;
/*     */ 
/*     */       
/* 101 */       if (diff > 0.0F || diff < 0.0F) {
/* 102 */         fromX += diff;
/*     */       }
/*     */     } 
/*     */     
/* 106 */     notifyDataChanged();
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
/* 117 */   public float getGroupWidth(float groupSpace, float barSpace) { return this.mDataSets.size() * (this.mBarWidth + barSpace) + groupSpace; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BarData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */