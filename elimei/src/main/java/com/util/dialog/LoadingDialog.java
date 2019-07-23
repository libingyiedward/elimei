/*    */ package com.util.dialog;
/*    */ 
/*    */ import android.content.Context;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoadingDialog
/*    */ {
/*    */   private static LoadingProgress dialog;
/*    */   
/*    */   public static LoadingProgress show(Context context, String message) {
/* 18 */     dialog = new LoadingProgress(context, message);
/* 19 */     dialog.setIndeterminate(false);
/* 20 */     dialog.setCancelable(false);
/* 21 */     dialog.setCanceledOnTouchOutside(false);
/* 22 */     if (dialog.isShowing())
/*    */     {
/* 24 */       dialog.dismiss();
/*    */     }
/* 26 */     dialog.show();
/* 27 */     return dialog;
/*    */   }
/*    */   
/* 30 */   public static boolean isShowing() { return (dialog == null) ? false : dialog.isShowing(); }
/*    */ 
/*    */   
/*    */   public static void dismiss() {
/*    */     try {
/* 35 */       if (dialog != null)
/* 36 */         dialog.dismiss(); 
/* 37 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\co\\util\dialog\LoadingDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */