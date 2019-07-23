/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import com.elimei.elimei.highlight.Highlight;
/*    */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*    */ import com.elimei.elimei.interfaces.datasets.IPieDataSet;
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
/*    */ public class PieData
/*    */   extends ChartData<IPieDataSet>
/*    */ {
/*    */   public PieData() {}
/*    */   
/* 23 */   public PieData(IPieDataSet dataSet) { super(new IPieDataSet[] { dataSet }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDataSet(IPieDataSet dataSet) {
/* 32 */     this.mDataSets.clear();
/* 33 */     this.mDataSets.add(dataSet);
/* 34 */     notifyDataChanged();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public IPieDataSet getDataSet() { return (IPieDataSet)this.mDataSets.get(0); }
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
/* 55 */   public IPieDataSet getDataSetByIndex(int index) { return (index == 0) ? getDataSet() : null; }
/*    */ 
/*    */ 
/*    */   
/*    */   public IPieDataSet getDataSetByLabel(String label, boolean ignorecase) {
/* 60 */     return ignorecase ? (label.equalsIgnoreCase(((IPieDataSet)this.mDataSets.get(0)).getLabel()) ? (IPieDataSet)this.mDataSets.get(0) : null) : (
/* 61 */       label.equals(((IPieDataSet)this.mDataSets.get(0)).getLabel()) ? (IPieDataSet)this.mDataSets.get(0) : null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 66 */   public Entry getEntryForHighlight(Highlight highlight) { return getDataSet().getEntryForIndex((int)highlight.getX()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getYValueSum() {
/* 76 */     float sum = 0.0F;
/*    */     
/* 78 */     for (int i = 0; i < getDataSet().getEntryCount(); i++) {
/* 79 */       sum += ((PieEntry)getDataSet().getEntryForIndex(i)).getY();
/*    */     }
/*    */     
/* 82 */     return sum;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\PieData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */