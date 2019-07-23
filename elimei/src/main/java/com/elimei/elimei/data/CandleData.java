/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import com.elimei.elimei.interfaces.datasets.ICandleDataSet;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CandleData
/*    */   extends BarLineScatterCandleBubbleData<ICandleDataSet>
/*    */ {
/*    */   public CandleData() {}
/*    */   
/* 15 */   public CandleData(List<ICandleDataSet> dataSets) { super(dataSets); }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public CandleData(ICandleDataSet... dataSets) { super(dataSets); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\CandleData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */