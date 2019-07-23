/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import com.elimei.elimei.interfaces.datasets.IScatterDataSet;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScatterData
/*    */   extends BarLineScatterCandleBubbleData<IScatterDataSet>
/*    */ {
/*    */   public ScatterData() {}
/*    */   
/* 16 */   public ScatterData(List<IScatterDataSet> dataSets) { super(dataSets); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public ScatterData(IScatterDataSet... dataSets) { super(dataSets); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getGreatestShapeSize() {
/* 30 */     float max = 0.0F;
/*    */     
/* 32 */     for (IScatterDataSet set : this.mDataSets) {
/* 33 */       float size = set.getScatterShapeSize();
/*    */       
/* 35 */       if (size > max) {
/* 36 */         max = size;
/*    */       }
/*    */     } 
/* 39 */     return max;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\ScatterData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */