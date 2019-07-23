/*     */ package com.elimei.elimei.utils;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.os.Build;
/*     */ import android.view.View;
/*     */ import android.view.Window;
/*     */ import android.view.WindowManager;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlymeOSStatusBarFontUtils
/*     */ {
/*     */   private static Method mSetStatusBarColorIcon;
/*     */   private static Method mSetStatusBarDarkIcon;
/*     */   private static Field mStatusBarColorFiled;
/*  21 */   private static int SYSTEM_UI_FLAG_LIGHT_STATUS_BAR = 0;
/*     */   
/*     */   static  {
/*     */     try {
/*  25 */       mSetStatusBarColorIcon = Activity.class.getMethod("setStatusBarDarkIcon", new Class[] { int.class });
/*  26 */     } catch (NoSuchMethodException field) {}
/*     */ 
/*     */     
/*     */     try {
/*  30 */       mSetStatusBarDarkIcon = Activity.class.getMethod("setStatusBarDarkIcon", new Class[] { boolean.class });
/*  31 */     } catch (NoSuchMethodException field) {}
/*     */ 
/*     */     
/*     */     try {
/*  35 */       mStatusBarColorFiled = WindowManager.LayoutParams.class.getField("statusBarColor");
/*  36 */     } catch (NoSuchFieldException field) {}
/*     */ 
/*     */     
/*     */     try {
/*  40 */      Field field = View.class.getField("SYSTEM_UI_FLAG_LIGHT_STATUS_BAR");
/*  41 */       SYSTEM_UI_FLAG_LIGHT_STATUS_BAR = field.getInt(null);
/*  42 */     } catch (NoSuchFieldException field) {
/*     */     
/*  44 */     } catch (IllegalAccessException field) {}
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
/*     */   
/*     */   public static boolean isBlackColor(int color, int level) {
/*  57 */     int grey = toGrey(color);
/*  58 */     return (grey < level);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int toGrey(int rgb) {
/*  69 */     int blue = rgb & 0xFF;
/*  70 */     int green = (rgb & 0xFF00) >> 8;
/*  71 */     int red = (rgb & 0xFF0000) >> 16;
/*  72 */     return red * 38 + green * 75 + blue * 15 >> 7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setStatusBarDarkIcon(Activity activity, int color) {
/*  82 */     if (mSetStatusBarColorIcon != null) {
/*     */       try {
/*  84 */         mSetStatusBarColorIcon.invoke(activity, new Object[] { Integer.valueOf(color) });
/*  85 */       } catch (IllegalAccessException e) {
/*  86 */         e.printStackTrace();
/*  87 */       } catch (InvocationTargetException e) {
/*  88 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*  91 */       boolean whiteColor = isBlackColor(color, 50);
/*  92 */       if (mStatusBarColorFiled != null) {
/*  93 */         setStatusBarDarkIcon(activity, whiteColor, whiteColor);
/*  94 */         setStatusBarDarkIcon(activity.getWindow(), color);
/*     */       } else {
/*  96 */         setStatusBarDarkIcon(activity, whiteColor);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setStatusBarDarkIcon(Window window, int color) {
/*     */     try {
/* 109 */       setStatusBarColor(window, color);
/* 110 */       if (Build.VERSION.SDK_INT > 22) {
/* 111 */         setStatusBarDarkIcon(window.getDecorView(), true);
/*     */       }
/* 113 */     } catch (Exception e) {
/* 114 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public static void setStatusBarDarkIcon(Activity activity, boolean dark) { setStatusBarDarkIcon(activity, dark, true); }
/*     */ 
/*     */   
/*     */   private static boolean changeMeizuFlag(WindowManager.LayoutParams winParams, String flagName, boolean on) {
/*     */     try {
/* 130 */       Field f = winParams.getClass().getDeclaredField(flagName);
/* 131 */       f.setAccessible(true);
/* 132 */       int bits = f.getInt(winParams);
/* 133 */       Field f2 = winParams.getClass().getDeclaredField("meizuFlags");
/* 134 */       f2.setAccessible(true);
/* 135 */       int meizuFlags = f2.getInt(winParams);
/* 136 */       int oldFlags = meizuFlags;
/* 137 */       if (on) {
/* 138 */         meizuFlags |= bits;
/*     */       } else {
/* 140 */         meizuFlags &= (bits ^ 0xFFFFFFFF);
/*     */       } 
/* 142 */       if (oldFlags != meizuFlags) {
/* 143 */         f2.setInt(winParams, meizuFlags);
/* 144 */         return true;
/*     */       } 
/* 146 */     } catch (NoSuchFieldException e) {
/* 147 */       e.printStackTrace();
/* 148 */     } catch (IllegalAccessException e) {
/* 149 */       e.printStackTrace();
/* 150 */     } catch (IllegalArgumentException e) {
/* 151 */       e.printStackTrace();
/* 152 */     } catch (Throwable e) {
/* 153 */       e.printStackTrace();
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setStatusBarDarkIcon(View view, boolean dark) {
/* 165 */     int oldVis = view.getSystemUiVisibility();
/* 166 */     int newVis = oldVis;
/* 167 */     if (dark) {
/* 168 */       newVis |= SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
/*     */     } else {
/* 170 */       newVis &= (SYSTEM_UI_FLAG_LIGHT_STATUS_BAR ^ 0xFFFFFFFF);
/*     */     } 
/* 172 */     if (newVis != oldVis) {
/* 173 */       view.setSystemUiVisibility(newVis);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setStatusBarColor(Window window, int color) {
/* 184 */     WindowManager.LayoutParams winParams = window.getAttributes();
/* 185 */     if (mStatusBarColorFiled != null) {
/*     */       try {
/* 187 */         int oldColor = mStatusBarColorFiled.getInt(winParams);
/* 188 */         if (oldColor != color) {
/* 189 */           mStatusBarColorFiled.set(winParams, Integer.valueOf(color));
/* 190 */           window.setAttributes(winParams);
/*     */         } 
/* 192 */       } catch (IllegalAccessException e) {
/* 193 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setStatusBarDarkIcon(Window window, boolean dark) {
/* 205 */     if (Build.VERSION.SDK_INT < 23) {
/* 206 */       changeMeizuFlag(window.getAttributes(), "MEIZU_FLAG_DARK_STATUS_BAR_ICON", dark);
/*     */     } else {
/* 208 */       View decorView = window.getDecorView();
/* 209 */       if (decorView != null) {
/* 210 */         setStatusBarDarkIcon(decorView, dark);
/* 211 */         setStatusBarColor(window, 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setStatusBarDarkIcon(Activity activity, boolean dark, boolean flag) {
/* 217 */     if (mSetStatusBarDarkIcon != null) {
/*     */       try {
/* 219 */         mSetStatusBarDarkIcon.invoke(activity, new Object[] { Boolean.valueOf(dark) });
/* 220 */       } catch (IllegalAccessException e) {
/* 221 */         e.printStackTrace();
/* 222 */       } catch (InvocationTargetException e) {
/* 223 */         e.printStackTrace();
/*     */       }
/*     */     
/* 226 */     } else if (flag) {
/* 227 */       setStatusBarDarkIcon(activity.getWindow(), dark);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\FlymeOSStatusBarFontUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */