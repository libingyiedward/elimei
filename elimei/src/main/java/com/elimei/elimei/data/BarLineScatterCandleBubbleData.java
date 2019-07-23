/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import com.elimei.elimei.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BarLineScatterCandleBubbleData<T extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>
/*    */   extends ChartData<T>
/*    */ {
/*    */   public BarLineScatterCandleBubbleData() {}
/*    */   
/* 22 */   public BarLineScatterCandleBubbleData(T... sets) { super(sets); }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public BarLineScatterCandleBubbleData(List<T> sets) { super(sets); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BarLineScatterCandleBubbleData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */