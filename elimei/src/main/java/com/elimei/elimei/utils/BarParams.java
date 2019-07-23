/*    */ package com.elimei.elimei.utils;
/*    */ 
/*    */ import android.database.ContentObserver;
/*    */ import android.support.annotation.ColorInt;
/*    */ import android.support.annotation.FloatRange;
/*    */ import android.view.View;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BarParams
/*    */   implements Cloneable
/*    */ {
/*    */   @ColorInt
/* 18 */   public int statusBarColor = 0;
/*    */   @ColorInt
/* 20 */   public int navigationBarColor = -16777216;
/*    */   @FloatRange(from = 0.0D, to = 1.0D)
/* 22 */   public float statusBarAlpha = 0.0F;
/*    */   @FloatRange(from = 0.0D, to = 1.0D)
/* 24 */   float navigationBarAlpha = 0.0F;
/*    */   
/*    */   public boolean fullScreen = false;
/* 27 */   public boolean fullScreenTemp = this.fullScreen;
/* 28 */   public BarHide barHide = BarHide.FLAG_SHOW_BAR;
/*    */   public boolean darkFont = false;
/*    */   @ColorInt
/* 31 */   public int statusBarColorTransform = -16777216; public boolean statusBarFlag = true;
/*    */   @ColorInt
/* 33 */   public int navigationBarColorTransform = -16777216;
/*    */   
/* 35 */   public Map<View, Map<Integer, Integer>> viewMap = new HashMap(); @FloatRange(from = 0.0D, to = 1.0D)
/* 36 */   public float viewAlpha = 0.0F;
/*    */   public boolean fits = false;
/*    */   @ColorInt
/* 39 */   public int statusBarColorContentView = 0;
/*    */   @ColorInt
/* 41 */   public int statusBarColorContentViewTransform = -16777216;
/*    */   @FloatRange(from = 0.0D, to = 1.0D)
/* 43 */   public float statusBarContentViewAlpha = 0.0F;
/*    */   
/* 45 */   public int navigationBarColorTemp = this.navigationBarColor;
/*    */   public View statusBarView;
/*    */   public View navigationBarView;
/*    */   public View statusBarViewByHeight;
/*    */   @ColorInt
/*    */   public int flymeOSStatusBarFontColor;
/*    */   public boolean isSupportActionBar = false;
/*    */   public View titleBarView;
/*    */   public int titleBarHeight;
/*    */   public int titleBarPaddingTopHeight;
/*    */   public View titleBarViewMarginTop;
/*    */   public boolean titleBarViewMarginTopFlag = false;
/*    */   public boolean keyboardEnable = false;
/* 58 */   public int keyboardMode = 18;
/*    */   
/*    */   public boolean navigationBarEnable = true;
/*    */   
/*    */   public boolean navigationBarWithKitkatEnable = true;
/*    */   @Deprecated
/*    */   public boolean fixMarginAtBottom = false;
/*    */   public boolean systemWindows = false;
/*    */   public KeyboardPatch keyboardPatch;
/*    */   public OnKeyboardListener onKeyboardListener;
/*    */   public ContentObserver navigationStatusObserver;
/*    */   
/*    */   protected BarParams clone() {
/* 71 */     BarParams barParams = null;
/*    */     try {
/* 73 */       barParams = (BarParams)super.clone();
/* 74 */     } catch (CloneNotSupportedException e) {
/* 75 */       e.printStackTrace();
/*    */     } 
/* 77 */     return barParams;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\BarParams.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */