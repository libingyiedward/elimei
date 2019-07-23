/*    */ package com.dvsonghan.utils;
/*    */ 
/*    */ import android.os.Handler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeOutUtil
/*    */ {
/*    */   private static Runnable r;
/*    */   
/*    */   public static void removeTimeOut(Handler handler) {
/* 18 */     if (r != null) {
/* 19 */       handler.removeCallbacks(r);
/* 20 */       r = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 25 */   public static boolean hasTask() { return (r != null); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setTimeOut(final Handler handler, long delayMillis, final OnTimeOutListener tm) {
/* 32 */     removeTimeOut(handler);
/*    */     
/* 34 */     r = new Runnable()
/*    */       {
/*    */         public void run() {
/* 37 */           if (tm != null)
/* 38 */             tm.onTimeOut(); 
/* 39 */           TimeOutUtil.removeTimeOut(handler);
/*    */         }
/*    */       };
/* 42 */     handler.postDelayed(r, delayMillis);
/*    */   }
/*    */   
/*    */   public static interface OnTimeOutListener {
/*    */     void onTimeOut();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\dvsongha\\utils\TimeOutUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */