/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import com.elimei.elimei.interfaces.datasets.ILineDataSet;
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
/*    */ public class LineData
/*    */   extends BarLineScatterCandleBubbleData<ILineDataSet>
/*    */ {
/*    */   public LineData() {}
/*    */   
/* 21 */   public LineData(ILineDataSet... dataSets) { super(dataSets); }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public LineData(List<ILineDataSet> dataSets) { super(dataSets); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\LineData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */