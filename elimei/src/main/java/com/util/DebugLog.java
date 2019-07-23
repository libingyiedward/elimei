/*    */ package com.util;
/*    */ 
/*    */ import android.util.Log;
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
/*    */ public class DebugLog
/*    */ {
/*    */   static String className;
/*    */   static String methodName;
/*    */   
/* 20 */   public static boolean isDebuggable() { return true; }
/*    */ 
/*    */   
/*    */   private static String createLog(String log) {
/* 24 */     StringBuffer buffer = new StringBuffer();
/* 25 */     buffer.append(log);
/* 26 */     buffer.append("   [来自:");
/* 27 */     buffer.append(methodName);
/* 28 */     buffer.append("]");
/* 29 */     return buffer.toString();
/*    */   }
/*    */   
/*    */   private static void getMethodNames(StackTraceElement[] sElements) {
/* 33 */     className = sElements[1].getFileName() + " [线程:" + Thread.currentThread().getName() + "][第:" + sElements[1].getLineNumber() + "行]";
/*    */     
/*    */     try {
/* 36 */       if (sElements.length >= 4) {
/*    */ 
/*    */ 
/*    */         
/* 40 */         methodName = sElements[4].getMethodName() + ">" + sElements[3].getMethodName() + ">" + sElements[2].getMethodName() + ">" + sElements[1].getMethodName();
/*    */       } else {
/* 42 */         methodName = sElements[1].getMethodName();
/*    */       } 
/* 44 */     } catch (Exception e) {
/* 45 */       methodName = sElements[1].getMethodName();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void e(String message) {
/* 50 */     if (!isDebuggable()) {
/*    */       return;
/*    */     }
/* 53 */     getMethodNames((new Throwable()).getStackTrace());
/* 54 */     Log.e(className, createLog(message));
/*    */   }
/*    */   
/*    */   public static void i(String message) {
/* 58 */     if (!isDebuggable()) {
/*    */       return;
/*    */     }
/* 61 */     getMethodNames((new Throwable()).getStackTrace());
/* 62 */     Log.i(className, createLog(message));
/*    */   }
/*    */   
/*    */   public static void d(String message) {
/* 66 */     if (!isDebuggable()) {
/*    */       return;
/*    */     }
/* 69 */     getMethodNames((new Throwable()).getStackTrace());
/* 70 */     Log.d(className, createLog(message));
/*    */   }
/*    */   
/*    */   public static void v(String message) {
/* 74 */     if (!isDebuggable()) {
/*    */       return;
/*    */     }
/* 77 */     getMethodNames((new Throwable()).getStackTrace());
/* 78 */     Log.v(className, createLog(message));
/*    */   }
/*    */   
/*    */   public static void w(String message) {
/* 82 */     if (!isDebuggable()) {
/*    */       return;
/*    */     }
/* 85 */     getMethodNames((new Throwable()).getStackTrace());
/* 86 */     Log.w(className, createLog(message));
/*    */   }
/*    */   
/*    */   public static void wtf(String message) {
/* 90 */     if (!isDebuggable()) {
/*    */       return;
/*    */     }
/* 93 */     getMethodNames((new Throwable()).getStackTrace());
/* 94 */     Log.wtf(className, createLog(message));
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\co\\util\DebugLog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */