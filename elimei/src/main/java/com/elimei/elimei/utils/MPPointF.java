/*    */ package com.elimei.elimei.utils;
/*    */ 
/*    */ import android.os.Parcel;
/*    */ import android.os.Parcelable;
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
/*    */ public class MPPointF
/*    */   extends ObjectPool.Poolable
/*    */ {
/* 19 */   private static ObjectPool<MPPointF> pool = ObjectPool.create(32, new MPPointF(0.0F, 0.0F)); public float x; public float y; public static final Parcelable.Creator<MPPointF> CREATOR;
/* 20 */   static  { pool.setReplenishPercentage(0.5F);
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
/* 57 */     CREATOR = new Parcelable.Creator<MPPointF>()
/*    */       {
/*    */         
/*    */         public MPPointF createFromParcel(Parcel in)
/*    */         {
/* 62 */           MPPointF r = new MPPointF(0.0F, 0.0F);
/* 63 */           r.my_readFromParcel(in);
/* 64 */           return r;
/*    */         }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 71 */         public MPPointF[] newArray(int size) { return new MPPointF[size]; }
/*    */       }; }
/*    */    public MPPointF() {} public MPPointF(float x, float y) {
/*    */     this.x = x;
/*    */     this.y = y;
/*    */   }
/*    */   public static MPPointF getInstance(float x, float y) {
/*    */     MPPointF result = (MPPointF)pool.get();
/*    */     result.x = x;
/*    */     result.y = y;
/*    */     return result;
/*    */   }
/* 83 */   public void my_readFromParcel(Parcel in) { this.x = in.readFloat();
/* 84 */     this.y = in.readFloat(); }
/*    */   
/*    */   public static MPPointF getInstance() { return (MPPointF)pool.get(); }
/*    */   
/* 88 */   public float getX() { return this.x; }
/*    */   public static MPPointF getInstance(MPPointF copy) { MPPointF result = (MPPointF)pool.get();
/*    */     result.x = copy.x;
/*    */     result.y = copy.y;
/* 92 */     return result; } public static void recycleInstance(MPPointF instance) { pool.recycle(instance); } public static void recycleInstances(List<MPPointF> instances) { pool.recycle(instances); } public float getY() { return this.y; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 97 */   protected ObjectPool.Poolable instantiate() { return new MPPointF(0.0F, 0.0F); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\MPPointF.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */