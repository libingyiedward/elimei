/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import com.elimei.elimei.interfaces.datasets.IBubbleDataSet;
/*    */ import com.elimei.elimei.utils.Utils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BubbleDataSet
/*    */   extends BarLineScatterCandleBubbleDataSet<BubbleEntry>
/*    */   implements IBubbleDataSet
/*    */ {
/*    */   protected float mMaxSize;
/*    */   protected boolean mNormalizeSize = true;
/* 16 */   private float mHighlightCircleWidth = 2.5F;
/*    */ 
/*    */   
/* 19 */   public BubbleDataSet(List<BubbleEntry> yVals, String label) { super(yVals, label); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public void setHighlightCircleWidth(float width) { this.mHighlightCircleWidth = Utils.convertDpToPixel(width); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public float getHighlightCircleWidth() { return this.mHighlightCircleWidth; }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void calcMinMax(BubbleEntry e) {
/* 34 */     super.calcMinMax(e);
/*    */     
/* 36 */     float size = e.getSize();
/*    */     
/* 38 */     if (size > this.mMaxSize) {
/* 39 */       this.mMaxSize = size;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DataSet<BubbleEntry> copy() {
/* 46 */     List<BubbleEntry> yVals = new ArrayList<BubbleEntry>();
/*    */     
/* 48 */     for (int i = 0; i < this.mValues.size(); i++) {
/* 49 */       yVals.add(((BubbleEntry)this.mValues.get(i)).copy());
/*    */     }
/*    */     
/* 52 */     BubbleDataSet copied = new BubbleDataSet(yVals, getLabel());
/* 53 */     copied.mColors = this.mColors;
/* 54 */     copied.mHighLightColor = this.mHighLightColor;
/*    */     
/* 56 */     return copied;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public float getMaxSize() { return this.mMaxSize; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public boolean isNormalizeSizeEnabled() { return this.mNormalizeSize; }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public void setNormalizeSizeEnabled(boolean normalizeSize) { this.mNormalizeSize = normalizeSize; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BubbleDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */