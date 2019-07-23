/*    */ package com.util;
/*    */ 
/*    */ import android.util.SparseArray;
/*    */ import android.view.View;
/*    */ import android.widget.Button;
/*    */ import android.widget.ImageView;
/*    */ import android.widget.TextView;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaseViewHolder
/*    */ {
/*    */   private SparseArray<View> viewHolder;
/*    */   private View view;
/*    */   
/*    */   public static BaseViewHolder getViewHolder(View view) {
/* 18 */     BaseViewHolder viewHolder = (BaseViewHolder)view.getTag();
/* 19 */     if (viewHolder == null) {
/* 20 */       viewHolder = new BaseViewHolder(view);
/* 21 */       view.setTag(viewHolder);
/*    */     } 
/* 23 */     return viewHolder;
/*    */   }
/*    */   private BaseViewHolder(View view) {
/* 26 */     this.view = view;
/* 27 */     this.viewHolder = new SparseArray();
/* 28 */     view.setTag(this.viewHolder);
/*    */   }
/*    */   public <T extends View> T getView(int id) {
/* 31 */     View childView = (View)this.viewHolder.get(id);
/* 32 */     if (childView == null) {
/* 33 */       childView = this.view.findViewById(id);
/* 34 */       this.viewHolder.put(id, childView);
/*    */     } 
/* 36 */     return (T)childView;
/*    */   }
/*    */ 
/*    */   
/* 40 */   public View getConvertView() { return this.view; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public TextView getTextView(int id) { return (TextView)getView(id); }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public Button getButton(int id) { return (Button)getView(id); }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public ImageView getImageView(int id) { return (ImageView)getView(id); }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public void setTextView(int id, CharSequence charSequence) { getTextView(id).setText(charSequence); }
/*    */   
/* 59 */   public void setImagetView(int id, int resId) { getImageView(id).setImageResource(resId); }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\co\\util\BaseViewHolder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */