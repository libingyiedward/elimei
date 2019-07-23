/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import com.elimei.elimei.interfaces.datasets.IBubbleDataSet;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BubbleData
/*    */   extends BarLineScatterCandleBubbleData<IBubbleDataSet>
/*    */ {
/*    */   public BubbleData() {}
/*    */   
/* 16 */   public BubbleData(IBubbleDataSet... dataSets) { super(dataSets); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public BubbleData(List<IBubbleDataSet> dataSets) { super(dataSets); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setHighlightCircleWidth(float width) {
/* 31 */     for (IBubbleDataSet set : this.mDataSets)
/* 32 */       set.setHighlightCircleWidth(width); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BubbleData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */