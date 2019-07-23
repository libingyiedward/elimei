/*    */ package com.elimei.elimei.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DrawingDataSetNotCreatedException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/* 11 */   public DrawingDataSetNotCreatedException() { super("Have to create a new drawing set first. Call ChartData's createNewDrawingDataSet() method"); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\exception\DrawingDataSetNotCreatedException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */