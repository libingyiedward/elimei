/*    */ package com.device;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.net.wifi.WifiManager;
/*    */ import android.util.Log;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WifiApController
/*    */ {
/*    */   Context mContext;
/*    */   WifiManager mwifiManager;
/*    */   
/*    */   public WifiApController(Context context) {
/* 18 */     this.mContext = context;
/* 19 */     this.mwifiManager = (WifiManager)this.mContext.getSystemService(Context.WIFI_SERVICE);
/*    */   }
/*    */   
/*    */   public enum WIFI_AP_STATE {
/* 23 */     WIFI_AP_STATE_DISABLING, WIFI_AP_STATE_DISABLED, WIFI_AP_STATE_ENABLING, WIFI_AP_STATE_ENABLED, WIFI_AP_STATE_FAILED;
/*    */   }
/*    */ 
/*    */   
/*    */   public WIFI_AP_STATE getWifiApState() {
/*    */     try {
/* 29 */       Method method = this.mwifiManager.getClass().getMethod("getWifiApState", new Class[0]);
/* 30 */       int tmp = ((Integer)method.invoke(this.mwifiManager, new Object[0])).intValue();
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 35 */       if (tmp >= 10) {
/* 36 */         tmp -= 10;
/*    */       }
/* 38 */       return WIFI_AP_STATE.class.getEnumConstants()[tmp];
/*    */     }
/* 40 */     catch (Exception e) {
/*    */       
/* 42 */       Log.e(getClass().toString(), "", e);
/*    */       
/* 44 */       return WIFI_AP_STATE.WIFI_AP_STATE_FAILED;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\device\WifiApController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */