/*    */ package com.elimei.elimei.utils;
/*    */ 
/*    */ import android.support.v4.app.Fragment;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class ImmersionFragment
/*    */   extends Fragment
/*    */ {
/*    */   public void setUserVisibleHint(boolean isVisibleToUser) {
/* 16 */     super.setUserVisibleHint(isVisibleToUser);
/* 17 */     if (isVisibleToUser && isResumed()) {
/* 18 */       onResume();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onResume() {
/* 24 */     super.onResume();
/* 25 */     if (getUserVisibleHint() && immersionEnabled()) {
/* 26 */       immersionInit();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/* 38 */   protected boolean immersionEnabled() { return true; }
/*    */   
/*    */   @Deprecated
/*    */   protected abstract void immersionInit();
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\ImmersionFragment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */