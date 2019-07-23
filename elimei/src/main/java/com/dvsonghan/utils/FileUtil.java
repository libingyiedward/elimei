/*    */ package com.dvsonghan.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.Calendar;
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
/*    */ public class FileUtil
/*    */ {
/*    */   public static long getRemoteFileTime(File file) {
/*    */     try {
/* 19 */       String name = file.getName();
/* 20 */       int index = name.lastIndexOf(".");
/* 21 */       if (index > 0) {
/* 22 */         name = name.substring(0, index);
/*    */       }
/*    */       
/* 25 */       String[] arr = name.split("_");
/* 26 */       Calendar calendar = Calendar.getInstance();
/* 27 */       calendar.set(
/* 28 */           Integer.parseInt(arr[0]), 
/* 29 */           Integer.parseInt(arr[1]) - 1, 
/* 30 */           Integer.parseInt(arr[2]), 
/* 31 */           Integer.parseInt(arr[3]), 
/* 32 */           Integer.parseInt(arr[4]), 
/* 33 */           Integer.parseInt(arr[5]));
/*    */       
/* 35 */       return calendar.getTimeInMillis();
/* 36 */     } catch (Exception e) {
/* 37 */       e.printStackTrace();
/*    */       
/* 39 */       return -1L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\dvsongha\\utils\FileUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */