/*    */ package com.elimei.elimei.Model;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.SharedPreferences;
/*    */ import android.text.TextUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Token
/*    */ {
/*    */   private SharedPreferences elimei;
/*    */   public String TOKEN;
/*    */   public String Address;
/*    */   
/*    */   public Token(Activity activity) {
/* 19 */     this.TOKEN = "token";
/* 20 */     this.Address = "address";
/*    */ 
/*    */     
/* 23 */     this.elimei = activity.getApplicationContext().getSharedPreferences("elimei", 0);
/*    */   }
/*    */   
/*    */   public void setToken(String token) {
/* 27 */     if (TextUtils.isEmpty(token)) {
/*    */       return;
/*    */     }
/* 30 */     this.elimei.edit().putString(this.TOKEN, token).commit();
/*    */   }
/*    */   
/*    */   public void setaddress(String token) {
/* 34 */     if (TextUtils.isEmpty(token)) {
/*    */       return;
/*    */     }
/* 37 */     this.elimei.edit().putString(this.Address, token).commit();
/*    */   }
/*    */   
/*    */   public String getToken() {
/* 41 */     String string = this.elimei.getString(this.TOKEN, "");
/* 42 */     if (TextUtils.isEmpty(string)) {
/* 43 */       throw new RuntimeException("getToken must be call setToken before,have you pass a token?");
/*    */     }
/* 45 */     return this.elimei.getString(this.TOKEN, "");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public String getAddress() { return this.elimei.getString(this.Address, ""); }
/*    */ }


