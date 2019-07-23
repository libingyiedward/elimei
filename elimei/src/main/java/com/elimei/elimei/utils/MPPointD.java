/*    */ package com.elimei.elimei.utils;
/*    */ 
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
/*    */ public class MPPointD
/*    */   extends ObjectPool.Poolable
/*    */ {
/* 16 */   private static ObjectPool<MPPointD> pool = ObjectPool.create(64, new MPPointD(0.0D, 0.0D)); public double x; static  {
/* 17 */     pool.setReplenishPercentage(0.5F);
/*    */   }
/*    */   public double y;
/*    */   public static MPPointD getInstance(double x, double y) {
/* 21 */     MPPointD result = (MPPointD)pool.get();
/* 22 */     result.x = x;
/* 23 */     result.y = y;
/* 24 */     return result;
/*    */   }
/*    */ 
/*    */   
/* 28 */   public static void recycleInstance(MPPointD instance) { pool.recycle(instance); }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public static void recycleInstances(List<MPPointD> instances) { pool.recycle(instances); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   protected ObjectPool.Poolable instantiate() { return new MPPointD(0.0D, 0.0D); }
/*    */ 
/*    */   
/*    */   private MPPointD(double x, double y) {
/* 43 */     this.x = x;
/* 44 */     this.y = y;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public String toString() { return "MPPointD, x: " + this.x + ", y: " + this.y; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\MPPointD.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */