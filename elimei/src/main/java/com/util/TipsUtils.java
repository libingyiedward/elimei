/*    */ package com.util;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.widget.Toast;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TipsUtils
/*    */ {
/* 13 */   public static void toast(Context context, String text) { toast(context, text, 0); }
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static void toast(Context context, int textRes) { toast(context, textRes, 0); }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static void toast(Context context, int textRes, int duration) { toast(context, context.getResources().getString(textRes), duration); }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void toast(Context context, String text, int duration) {
/* 26 */     Toast makeText = Toast.makeText(context, text, duration);
/* 27 */     makeText.show();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\co\\util\TipsUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */