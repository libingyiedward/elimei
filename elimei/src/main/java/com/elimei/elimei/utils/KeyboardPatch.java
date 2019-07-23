/*     */ package com.elimei.elimei.utils;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.Dialog;
/*     */ import android.graphics.Rect;
/*     */ import android.os.Build;
/*     */ import android.view.View;
/*     */ import android.view.ViewTreeObserver;
/*     */ import android.view.Window;
/*     */ import android.widget.FrameLayout;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyboardPatch
/*     */ {
/*     */   private Activity mActivity;
/*     */   private Window mWindow;
/*     */   private View mDecorView;
/*     */   private View mContentView;
/*     */   private View mChildView;
/*     */   private BarParams mBarParams;
/*     */   private int paddingLeft;
/*     */   private int paddingTop;
/*     */   private int paddingRight;
/*     */   private int paddingBottom;
/*     */   private int keyboardHeightPrevious;
/*     */   private int statusBarHeight;
/*     */   private int actionBarHeight;
/*     */   private int navigationBarHeight;
/*     */   private boolean navigationAtBottom;
/*     */   private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
/*     */   
/*  39 */   private KeyboardPatch(Activity activity) { this(activity, ((FrameLayout)activity.getWindow().getDecorView().findViewById(16908290)).getChildAt(0)); }
/*     */ 
/*     */ 
/*     */   
/*  43 */   private KeyboardPatch(Activity activity, View contentView) { this(activity, null, "", contentView); }
/*     */ 
/*     */ 
/*     */   
/*  47 */   private KeyboardPatch(Activity activity, Dialog dialog, String tag) { this(activity, dialog, tag, dialog.getWindow().findViewById(16908290)); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private KeyboardPatch(Activity activity, Dialog dialog, String tag, View contentView) {
/* 137 */     this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener()
/*     */       {
/*     */         public void onGlobalLayout()
/*     */         {
/* 141 */           if (!KeyboardPatch.this.navigationAtBottom)
/*     */             return; 
/* 143 */           Rect r = new Rect();
/* 144 */           KeyboardPatch.this.mDecorView.getWindowVisibleDisplayFrame(r);
/*     */ 
/*     */           
/* 147 */           boolean isPopup = false;
/* 148 */           if (mBarParams.systemWindows) {
/* 149 */             int keyboardHeight = KeyboardPatch.this.mContentView.getHeight() - r.bottom - KeyboardPatch.this.navigationBarHeight;
/* 150 */             if (mBarParams.onKeyboardListener != null) {
/* 151 */               if (keyboardHeight > KeyboardPatch.this.navigationBarHeight)
/* 152 */                 isPopup = true; 
/* 153 */               mBarParams.onKeyboardListener.onKeyboardChange(isPopup, keyboardHeight);
/*     */             } 
/*     */             return;
/*     */           } 
/* 157 */           if (KeyboardPatch.this.mChildView != null)
/* 158 */           { int keyboardHeight; int diff; if (mBarParams.isSupportActionBar) {
/* 159 */               diff = KeyboardPatch.this.mContentView.getHeight() + KeyboardPatch.this.statusBarHeight + KeyboardPatch.this.actionBarHeight - r.bottom;
/* 160 */             } else if (mBarParams.fits) {
/* 161 */               diff = KeyboardPatch.this.mContentView.getHeight() + KeyboardPatch.this.statusBarHeight - r.bottom;
/*     */             } else {
/* 163 */               diff = KeyboardPatch.this.mContentView.getHeight() - r.bottom;
/* 164 */             }  if (mBarParams.fullScreen) {
/* 165 */               keyboardHeight = diff - KeyboardPatch.this.navigationBarHeight;
/*     */             } else {
/* 167 */               keyboardHeight = diff;
/* 168 */             }  if (mBarParams.fullScreen && diff == KeyboardPatch.this.navigationBarHeight) {
/* 169 */               diff -= KeyboardPatch.this.navigationBarHeight;
/*     */             }
/* 171 */             if (keyboardHeight != KeyboardPatch.this.keyboardHeightPrevious) {
/* 172 */               KeyboardPatch.this.mContentView.setPadding(KeyboardPatch.this.paddingLeft, KeyboardPatch.this.paddingTop, KeyboardPatch.this.paddingRight, diff + KeyboardPatch.this.paddingBottom);
/* 173 */               KeyboardPatch.this.keyboardHeightPrevious = keyboardHeight;
/* 174 */               if (mBarParams.onKeyboardListener != null) {
/* 175 */                 if (keyboardHeight > KeyboardPatch.this.navigationBarHeight)
/* 176 */                   isPopup = true; 
/* 177 */                 mBarParams.onKeyboardListener.onKeyboardChange(isPopup, keyboardHeight);
/*     */               } 
/*     */             }  }
/*     */           else
/* 181 */           { int keyboardHeight, diff = KeyboardPatch.this.mContentView.getHeight() - r.bottom;
/*     */             
/* 183 */             if (mBarParams.navigationBarEnable && mBarParams.navigationBarWithKitkatEnable) {
/* 184 */               if (Build.VERSION.SDK_INT == 19 || OSUtils.isEMUI3_1()) {
/* 185 */                 keyboardHeight = diff - KeyboardPatch.this.navigationBarHeight;
/*     */               }
/* 187 */               else if (!mBarParams.fullScreen) {
/* 188 */                 keyboardHeight = diff;
/*     */               } else {
/* 190 */                 keyboardHeight = diff - KeyboardPatch.this.navigationBarHeight;
/*     */               } 
/* 192 */               if (mBarParams.fullScreen && diff == KeyboardPatch.this.navigationBarHeight)
/* 193 */                 diff -= KeyboardPatch.this.navigationBarHeight; 
/*     */             } else {
/* 195 */               keyboardHeight = diff;
/* 196 */             }  if (keyboardHeight != KeyboardPatch.this.keyboardHeightPrevious)
/* 197 */             { if (mBarParams.isSupportActionBar) {
/* 198 */                 KeyboardPatch.this.mContentView.setPadding(0, KeyboardPatch.this.statusBarHeight + KeyboardPatch.this.actionBarHeight, 0, diff);
/* 199 */               } else if (mBarParams.fits) {
/* 200 */                 KeyboardPatch.this.mContentView.setPadding(0, KeyboardPatch.this.statusBarHeight, 0, diff);
/*     */               } else {
/* 202 */                 KeyboardPatch.this.mContentView.setPadding(0, 0, 0, diff);
/* 203 */               }  KeyboardPatch.this.keyboardHeightPrevious = keyboardHeight;
/* 204 */               if (mBarParams.onKeyboardListener != null)
/* 205 */               { if (keyboardHeight > KeyboardPatch.this.navigationBarHeight)
/* 206 */                   isPopup = true; 
/* 207 */                 mBarParams.onKeyboardListener.onKeyboardChange(isPopup, keyboardHeight); }  }  }  } }; this.mActivity = activity; this.mWindow = (dialog != null) ? dialog.getWindow() : activity.getWindow(); this.mDecorView = this.mWindow.getDecorView(); this.mContentView = (contentView != null) ? contentView : this.mWindow.getDecorView().findViewById(16908290); this.mBarParams = (dialog != null) ? ImmersionBar.with(activity, dialog, tag).getBarParams() : ImmersionBar.with(activity).getBarParams(); if (this.mBarParams == null) throw new IllegalArgumentException("先使用ImmersionBar初始化");  } private KeyboardPatch(Activity activity, Window window) { this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { public void onGlobalLayout() { if (!KeyboardPatch.this.navigationAtBottom) return;  Rect r = new Rect(); KeyboardPatch.this.mDecorView.getWindowVisibleDisplayFrame(r); boolean isPopup = false; if (mBarParams.systemWindows) { int keyboardHeight = KeyboardPatch.this.mContentView.getHeight() - r.bottom - KeyboardPatch.this.navigationBarHeight; if (mBarParams.onKeyboardListener != null) { if (keyboardHeight > KeyboardPatch.this.navigationBarHeight) isPopup = true;  mBarParams.onKeyboardListener.onKeyboardChange(isPopup, keyboardHeight); }  return; }  if (KeyboardPatch.this.mChildView != null) { int keyboardHeight; int diff; if (mBarParams.isSupportActionBar) { diff = KeyboardPatch.this.mContentView.getHeight() + KeyboardPatch.this.statusBarHeight + KeyboardPatch.this.actionBarHeight - r.bottom; } else if (mBarParams.fits) { diff = KeyboardPatch.this.mContentView.getHeight() + KeyboardPatch.this.statusBarHeight - r.bottom; } else { diff = KeyboardPatch.this.mContentView.getHeight() - r.bottom; }  if (mBarParams.fullScreen) { keyboardHeight = diff - KeyboardPatch.this.navigationBarHeight; } else { keyboardHeight = diff; }  if (mBarParams.fullScreen && diff == KeyboardPatch.this.navigationBarHeight) diff -= KeyboardPatch.this.navigationBarHeight;  if (keyboardHeight != KeyboardPatch.this.keyboardHeightPrevious) { KeyboardPatch.this.mContentView.setPadding(KeyboardPatch.this.paddingLeft, KeyboardPatch.this.paddingTop, KeyboardPatch.this.paddingRight, diff + KeyboardPatch.this.paddingBottom); KeyboardPatch.this.keyboardHeightPrevious = keyboardHeight; if (mBarParams.onKeyboardListener != null) { if (keyboardHeight > KeyboardPatch.this.navigationBarHeight) isPopup = true;  mBarParams.onKeyboardListener.onKeyboardChange(isPopup, keyboardHeight); }  }  } else { int keyboardHeight, diff = KeyboardPatch.this.mContentView.getHeight() - r.bottom; if (mBarParams.navigationBarEnable && mBarParams.navigationBarWithKitkatEnable) { if (Build.VERSION.SDK_INT == 19 || OSUtils.isEMUI3_1()) { keyboardHeight = diff - KeyboardPatch.this.navigationBarHeight; } else if (!mBarParams.fullScreen) { keyboardHeight = diff; } else { keyboardHeight = diff - KeyboardPatch.this.navigationBarHeight; }  if (mBarParams.fullScreen && diff == KeyboardPatch.this.navigationBarHeight) diff -= KeyboardPatch.this.navigationBarHeight;  } else { keyboardHeight = diff; }  if (keyboardHeight != KeyboardPatch.this.keyboardHeightPrevious) { if (mBarParams.isSupportActionBar) { KeyboardPatch.this.mContentView.setPadding(0, KeyboardPatch.this.statusBarHeight + KeyboardPatch.this.actionBarHeight, 0, diff); } else if (mBarParams.fits) { KeyboardPatch.this.mContentView.setPadding(0, KeyboardPatch.this.statusBarHeight, 0, diff); } else { KeyboardPatch.this.mContentView.setPadding(0, 0, 0, diff); }  KeyboardPatch.this.keyboardHeightPrevious = keyboardHeight; if (mBarParams.onKeyboardListener != null) { if (keyboardHeight > KeyboardPatch.this.navigationBarHeight) isPopup = true;  mBarParams.onKeyboardListener.onKeyboardChange(isPopup, keyboardHeight); }
/*     */                }
/*     */              }
/*     */            }
/*     */          }
/*     */       ;
/*     */     this.mActivity = activity;
/*     */     this.mWindow = window;
/*     */     this.mDecorView = this.mWindow.getDecorView();
/*     */     FrameLayout frameLayout = (FrameLayout)this.mDecorView.findViewById(16908290);
/*     */     this.mChildView = frameLayout.getChildAt(0);
/*     */     this.mContentView = (this.mChildView != null) ? this.mChildView : frameLayout;
/*     */     this.paddingLeft = this.mContentView.getPaddingLeft();
/*     */     this.paddingTop = this.mContentView.getPaddingTop();
/*     */     this.paddingRight = this.mContentView.getPaddingRight();
/*     */     this.paddingBottom = this.mContentView.getPaddingBottom();
/*     */     BarConfig barConfig = new BarConfig(this.mActivity);
/*     */     this.statusBarHeight = barConfig.getStatusBarHeight();
/*     */     this.navigationBarHeight = barConfig.getNavigationBarHeight();
/*     */     this.actionBarHeight = barConfig.getActionBarHeight();
/*     */     this.navigationAtBottom = barConfig.isNavigationAtBottom(); }
/*     */ 
/*     */   
/*     */   public static KeyboardPatch patch(Activity activity) { return new KeyboardPatch(activity); }
/*     */   
/*     */   public static KeyboardPatch patch(Activity activity, View contentView) { return new KeyboardPatch(activity, contentView); }
/*     */   
/*     */   public static KeyboardPatch patch(Activity activity, Dialog dialog, String tag) { return new KeyboardPatch(activity, dialog, tag); }
/*     */   
/*     */   public static KeyboardPatch patch(Activity activity, Dialog dialog, String tag, View contentView) { return new KeyboardPatch(activity, dialog, tag, contentView); }
/*     */   
/*     */   protected static KeyboardPatch patch(Activity activity, Window window) { return new KeyboardPatch(activity, window); }
/*     */   
/*     */   protected void setBarParams(BarParams barParams) { this.mBarParams = barParams; }
/*     */   
/*     */   public void enable() { enable(18); }
/*     */   
/*     */   public void enable(int mode) {
/*     */     if (Build.VERSION.SDK_INT >= 19) {
/*     */       this.mWindow.setSoftInputMode(mode);
/*     */       this.mDecorView.getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void disable() { disable(18); }
/*     */   
/*     */   public void disable(int mode) {
/*     */     if (Build.VERSION.SDK_INT >= 19) {
/*     */       this.mWindow.setSoftInputMode(mode);
/*     */       this.mDecorView.getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\KeyboardPatch.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */