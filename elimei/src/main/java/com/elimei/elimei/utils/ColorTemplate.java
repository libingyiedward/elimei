/*     */ package com.elimei.elimei.utils;
/*     */ 
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class ColorTemplate
/*     */ {
/*     */   public static final int COLOR_NONE = 1122867;
/*     */   public static final int COLOR_SKIP = 1122868;
/*  35 */   public static final int[] LIBERTY_COLORS = { Color.rgb(207, 248, 246), Color.rgb(148, 212, 212), Color.rgb(136, 180, 187), 
/*  36 */       Color.rgb(118, 174, 175), Color.rgb(42, 109, 130) };
/*     */ 
/*     */   
/*  39 */   public static final int[] JOYFUL_COLORS = { Color.rgb(217, 80, 138), Color.rgb(254, 149, 7), Color.rgb(254, 247, 120), 
/*  40 */       Color.rgb(106, 167, 134), Color.rgb(53, 194, 209) };
/*     */ 
/*     */   
/*  43 */   public static final int[] PASTEL_COLORS = { Color.rgb(64, 89, 128), Color.rgb(149, 165, 124), Color.rgb(217, 184, 162), 
/*  44 */       Color.rgb(191, 134, 134), Color.rgb(179, 48, 80) };
/*     */ 
/*     */   
/*  47 */   public static final int[] COLORFUL_COLORS = { Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0), 
/*  48 */       Color.rgb(106, 150, 31), Color.rgb(179, 100, 53) };
/*     */ 
/*     */   
/*  51 */   public static final int[] VORDIPLOM_COLORS = { Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, 208, 140), 
/*  52 */       Color.rgb(140, 234, 255), Color.rgb(255, 140, 157) };
/*     */ 
/*     */   
/*  55 */   public static final int[] MATERIAL_COLORS = { rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int rgb(String hex) {
/*  65 */     int color = (int)Long.parseLong(hex.replace("#", ""), 16);
/*  66 */     int r = color >> 16 & 0xFF;
/*  67 */     int g = color >> 8 & 0xFF;
/*  68 */     int b = color >> 0 & 0xFF;
/*  69 */     return Color.rgb(r, g, b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static int getHoloBlue() { return Color.rgb(51, 181, 229); }
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
/*  89 */   public static int colorWithAlpha(int color, int alpha) { return color & 0xFFFFFF | (alpha & 0xFF) << 24; }
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
/*     */   public static List<Integer> createColors(Resources r, int[] colors) {
/* 102 */     List<Integer> result = new ArrayList<Integer>();
/*     */     
/* 104 */     for (int i : colors) {
/* 105 */       result.add(Integer.valueOf(r.getColor(i)));
/*     */     }
/*     */     
/* 108 */     return result;
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
/*     */   public static List<Integer> createColors(int[] colors) {
/* 120 */     List<Integer> result = new ArrayList<Integer>();
/*     */     
/* 122 */     for (int i : colors) {
/* 123 */       result.add(Integer.valueOf(i));
/*     */     }
/*     */     
/* 126 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\ColorTemplate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */