/*    */ package com.elimei.elimei.data;
/*    */ 
/*    */ import android.graphics.Color;
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
/*    */ public abstract class BarLineScatterCandleBubbleDataSet<T extends Entry>
/*    */   extends DataSet<T>
/*    */   implements IBarLineScatterCandleBubbleDataSet<T>
/*    */ {
/* 18 */   protected int mHighLightColor = Color.rgb(255, 187, 115);
/*    */ 
/*    */   
/* 21 */   public BarLineScatterCandleBubbleDataSet(List<T> yVals, String label) { super(yVals, label); }
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
/* 32 */   public void setHighLightColor(int color) { this.mHighLightColor = color; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public int getHighLightColor() { return this.mHighLightColor; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\BarLineScatterCandleBubbleDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */