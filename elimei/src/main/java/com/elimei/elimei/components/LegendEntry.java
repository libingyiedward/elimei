/*    */ package com.elimei.elimei.components;
/*    */ 
/*    */ import android.graphics.DashPathEffect;
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
/*    */ public class LegendEntry
/*    */ {
/*    */   public String label;
/*    */   
/*    */   public LegendEntry() {}
/*    */   
/*    */   public LegendEntry(String label, Legend.LegendForm form, float formSize, float formLineWidth, DashPathEffect formLineDashEffect, int formColor) {
/* 29 */     this.label = label;
/* 30 */     this.form = form;
/* 31 */     this.formSize = formSize;
/* 32 */     this.formLineWidth = formLineWidth;
/* 33 */     this.formLineDashEffect = formLineDashEffect;
/* 34 */     this.formColor = formColor;
/*    */   }
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
/* 50 */   public Legend.LegendForm form = Legend.LegendForm.DEFAULT;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public float formSize;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public float formLineWidth ;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   public DashPathEffect formLineDashEffect = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   public int formColor = 1122867;
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\LegendEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */