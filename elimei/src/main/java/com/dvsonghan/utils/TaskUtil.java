/*    */ package com.dvsonghan.utils;
/*    */ 
/*    */ import android.app.ActivityManager;
/*    */ import android.content.Context;
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
/*    */ public class TaskUtil
/*    */ {
/*    */   public static String getCurrentClassName(Context context) {
/* 18 */     ActivityManager mActivityManager = (ActivityManager)context.getSystemService("activity");
/* 19 */     List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
/* 20 */     return ((ActivityManager.RunningTaskInfo)rti.get(0)).topActivity.getClassName();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\dvsongha\\utils\TaskUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */