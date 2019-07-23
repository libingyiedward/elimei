/*    */ package com.util.dialog;
/*    */ 
/*    */ import android.app.ProgressDialog;
/*    */ import android.content.Context;
/*    */ import android.os.Bundle;
/*    */ import android.support.annotation.Nullable;
/*    */ import android.widget.TextView;
/*    */ import com.elimei.R;
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
/*    */ class LoadingProgress
/*    */   extends ProgressDialog
/*    */ {
/*    */   private String message;
/*    */   private TextView progressMessage;
/*    */   
/* 48 */   public String getMessage() { return this.message; }
/*    */ 
/*    */   
/*    */   public void setMessage(String message) {
/* 52 */     this.message = message;
/* 53 */     if (this.progressMessage != null)
/*    */     {
/* 55 */       this.progressMessage.setText(message);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LoadingProgress(Context context, String message) {
/* 64 */     super(context, R.style.dialog);
/* 65 */     this.message = message;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onCreate(@Nullable Bundle savedInstanceState) {
/* 71 */     super.onCreate(savedInstanceState);
/* 72 */     setContentView(R.layout.dialog_loading);
/* 73 */     setCancelable(true);
/* 74 */     setCanceledOnTouchOutside(true);
/* 75 */     this.progressMessage = (TextView)findViewById(R.id.progressMessage);
/* 76 */     if (this.message != null)
/* 77 */       this.progressMessage.setText(this.message); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\co\\util\dialog\LoadingProgress.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */