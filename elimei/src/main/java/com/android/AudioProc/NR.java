/*    */ package com.android.AudioProc;
/*    */ 
/*    */ public class NR {
/*    */   public static native boolean Init(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3);
/*    */   
/*    */   public static native boolean process(short[] paramArrayOfShort1, short paramShort1, short[] paramArrayOfShort2, short paramShort2);
/*    */   
/*    */   static  {
/*    */     try {
/* 10 */       System.loadLibrary("AECRTSP");
/* 11 */     } catch (UnsatisfiedLinkError ule) {
/* 12 */       System.out.println("loadLibrary(AECRTSP)," + ule.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\android\AudioProc\NR.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */