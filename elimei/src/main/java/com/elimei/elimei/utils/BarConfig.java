/*     */ package com.elimei.elimei.utils;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.annotation.TargetApi;
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.os.Build;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.util.TypedValue;
/*     */ import android.view.Display;
/*     */ import android.view.WindowManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BarConfig
/*     */ {
/*     */   private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
/*     */   private static final String NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height";
/*     */   private static final String NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape";
/*     */   private static final String NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width";
/*     */   private final int mStatusBarHeight;
/*     */   private final int mActionBarHeight;
/*     */   private final boolean mHasNavigationBar;
/*     */   private final int mNavigationBarHeight;
/*     */   private final int mNavigationBarWidth;
/*     */   private final boolean mInPortrait;
/*     */   private final float mSmallestWidthDp;
/*     */   
/*     */   public BarConfig(Activity activity) {
/*  36 */     Resources res = activity.getResources();
/*  37 */     this.mInPortrait = ((res.getConfiguration()).orientation == 1);
/*  38 */     this.mSmallestWidthDp = getSmallestWidthDp(activity);
/*  39 */     this.mStatusBarHeight = getInternalDimensionSize(res, "status_bar_height");
/*  40 */     this.mActionBarHeight = getActionBarHeight(activity);
/*  41 */     this.mNavigationBarHeight = getNavigationBarHeight(activity);
/*  42 */     this.mNavigationBarWidth = getNavigationBarWidth(activity);
/*  43 */     this.mHasNavigationBar = (this.mNavigationBarHeight > 0);
/*     */   }
/*     */   
/*     */   @TargetApi(14)
/*     */   private int getActionBarHeight(Context context) {
/*  48 */     int result = 0;
/*  49 */     if (Build.VERSION.SDK_INT >= 14) {
/*  50 */       TypedValue tv = new TypedValue();
/*  51 */       context.getTheme().resolveAttribute(16843499, tv, true);
/*  52 */       result = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
/*     */     } 
/*  54 */     return result;
/*     */   }
/*     */   
/*     */   @TargetApi(14)
/*     */   private int getNavigationBarHeight(Context context) {
/*  59 */     Resources res = context.getResources();
/*  60 */     int result = 0;
/*  61 */     if (Build.VERSION.SDK_INT >= 14 && 
/*  62 */       hasNavBar((Activity)context)) {
/*     */       String key;
/*  64 */       if (this.mInPortrait) {
/*  65 */         key = "navigation_bar_height";
/*     */       } else {
/*  67 */         key = "navigation_bar_height_landscape";
/*     */       } 
/*  69 */       return getInternalDimensionSize(res, key);
/*     */     } 
/*     */     
/*  72 */     return result;
/*     */   }
/*     */   
/*     */   @TargetApi(14)
/*     */   private int getNavigationBarWidth(Context context) {
/*  77 */     Resources res = context.getResources();
/*  78 */     int result = 0;
/*  79 */     if (Build.VERSION.SDK_INT >= 14 && 
/*  80 */       hasNavBar((Activity)context)) {
/*  81 */       return getInternalDimensionSize(res, "navigation_bar_width");
/*     */     }
/*     */     
/*  84 */     return result;
/*     */   }
/*     */   
/*     */   @TargetApi(14)
/*     */   private static boolean hasNavBar(Activity activity) {
/*  89 */     WindowManager windowManager = activity.getWindowManager();
/*  90 */     Display d = windowManager.getDefaultDisplay();
/*     */     
/*  92 */     DisplayMetrics realDisplayMetrics = new DisplayMetrics();
/*  93 */     if (Build.VERSION.SDK_INT >= 17) {
/*  94 */       d.getRealMetrics(realDisplayMetrics);
/*     */     }
/*     */     
/*  97 */     int realHeight = realDisplayMetrics.heightPixels;
/*  98 */     int realWidth = realDisplayMetrics.widthPixels;
/*     */     
/* 100 */     DisplayMetrics displayMetrics = new DisplayMetrics();
/* 101 */     d.getMetrics(displayMetrics);
/*     */     
/* 103 */     int displayHeight = displayMetrics.heightPixels;
/* 104 */     int displayWidth = displayMetrics.widthPixels;
/*     */     
/* 106 */     return (realWidth - displayWidth > 0 || realHeight - displayHeight > 0);
/*     */   }
/*     */   
/*     */   private int getInternalDimensionSize(Resources res, String key) {
/* 110 */     int result = 0;
/*     */     try {
/* 112 */       Class clazz = Class.forName("com.android.internal.R$dimen");
/* 113 */       Object object = clazz.newInstance();
/* 114 */       int resourceId = Integer.parseInt(clazz.getField(key).get(object).toString());
/* 115 */       if (resourceId > 0)
/* 116 */         result = res.getDimensionPixelSize(resourceId); 
/* 117 */     } catch (Exception e) {
/* 118 */       e.printStackTrace();
/*     */     } 
/* 120 */     return result;
/*     */   }
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   private float getSmallestWidthDp(Activity activity) {
/* 125 */     DisplayMetrics metrics = new DisplayMetrics();
/* 126 */     if (Build.VERSION.SDK_INT >= 16) {
/* 127 */       activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
/*     */     } else {
/*     */       
/* 130 */       activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
/*     */     } 
/* 132 */     float widthDp = metrics.widthPixels / metrics.density;
/* 133 */     float heightDp = metrics.heightPixels / metrics.density;
/* 134 */     return Math.min(widthDp, heightDp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public boolean isNavigationAtBottom() { return (this.mSmallestWidthDp >= 600.0F || this.mInPortrait); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public int getStatusBarHeight() { return this.mStatusBarHeight; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public int getActionBarHeight() { return this.mActionBarHeight; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public boolean hasNavigtionBar() { return this.mHasNavigationBar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   public int getNavigationBarHeight() { return this.mNavigationBarHeight; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public int getNavigationBarWidth() { return this.mNavigationBarWidth; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\BarConfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */