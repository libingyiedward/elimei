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
/*    */ 
/*    */ 
/*    */ public final class FSize
/*    */   extends ObjectPool.Poolable
/*    */ {
/*    */   public float width;
/*    */   public float height;
/* 20 */   private static ObjectPool<FSize> pool = ObjectPool.create(256, new FSize(0.0F, 0.0F)); static  {
/* 21 */     pool.setReplenishPercentage(0.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 26 */   protected ObjectPool.Poolable instantiate() { return new FSize(0.0F, 0.0F); }
/*    */ 
/*    */   
/*    */   public static FSize getInstance(float width, float height) {
/* 30 */     FSize result = (FSize)pool.get();
/* 31 */     result.width = width;
/* 32 */     result.height = height;
/* 33 */     return result;
/*    */   }
/*    */ 
/*    */   
/* 37 */   public static void recycleInstance(FSize instance) { pool.recycle(instance); }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public static void recycleInstances(List<FSize> instances) { pool.recycle(instances); }
/*    */ 
/*    */   
/*    */   public FSize() {}
/*    */ 
/*    */   
/*    */   public FSize(float width, float height) {
/* 48 */     this.width = width;
/* 49 */     this.height = height;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 54 */     if (obj == null) {
/* 55 */       return false;
/*    */     }
/* 57 */     if (this == obj) {
/* 58 */       return true;
/*    */     }
/* 60 */     if (obj instanceof FSize) {
/* 61 */       FSize other = (FSize)obj;
/* 62 */       return (this.width == other.width && this.height == other.height);
/*    */     } 
/* 64 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public String toString() { return this.width + "x" + this.height; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 77 */   public int hashCode() { return Float.floatToIntBits(this.width) ^ Float.floatToIntBits(this.height); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\FSize.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */