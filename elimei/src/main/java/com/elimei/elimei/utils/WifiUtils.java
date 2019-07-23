/*    */ package com.elimei.elimei.utils;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.Intent;
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
/*    */ public class WifiUtils
/*    */ {
/*    */   public static void starttowifi(Activity activity) {
/* 31 */     Intent intent = new Intent("android.settings.WIFI_SETTINGS");
/* 32 */     activity.startActivityForResult(intent, 10);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\WifiUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */