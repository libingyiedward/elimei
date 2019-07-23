/*     */ package com.component;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.TextureView;
/*     */ import android.widget.RelativeLayout;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DualTextureView
/*     */   extends RelativeLayout
/*     */ {
/*     */   private Context ctx;
/*     */   private TextureView TextureViewLeft;
/*     */   private TextureView TextureViewRight;
/*     */   private boolean isDualMode = true;
/*  18 */   private int gl_width = 0;
/*  19 */   private int gl_height = 0;
/*  20 */   private int gl_padding_l = 0;
/*  21 */   private int gl_padding_r = 0;
/*  22 */   private int gl_padding_m = 0;
/*     */   
/*     */   public DualTextureView(Context context) {
/*  25 */     super(context);
/*  26 */     this.ctx = context;
/*  27 */     init();
/*     */   }
/*     */   
/*     */   public DualTextureView(Context context, AttributeSet attrs) {
/*  31 */     super(context, attrs);
/*  32 */     this.ctx = context;
/*  33 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*  37 */     inflate(this.ctx, this.ctx.getResources().getIdentifier("dual_textureview", "layout", this.ctx.getPackageName()), this);
/*     */     
/*  39 */     this.TextureViewLeft = (TextureView)findViewById(this.ctx.getResources().getIdentifier("TextureViewLeft", "id", this.ctx.getPackageName()));
/*  40 */     this.TextureViewRight = (TextureView)findViewById(this.ctx.getResources().getIdentifier("TextureViewRight", "id", this.ctx.getPackageName()));
/*  41 */     setDualView(this.isDualMode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDualView(boolean state) {
/*  47 */     this.isDualMode = state;
/*     */ 
/*     */     
/*  50 */     this.TextureViewLeft.setVisibility(0);
/*  51 */     this.TextureViewRight.setVisibility(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     updateUI();
/*     */   }
/*     */ 
/*     */   
/*  61 */   public boolean getDualView() { return this.isDualMode; }
/*     */ 
/*     */   
/*     */   public void setImageSize(int width, int height) {
/*  65 */     this.gl_width = width;
/*  66 */     this.gl_height = height;
/*  67 */     updateUI();
/*     */   }
/*     */   
/*     */   public void setPaddingLeft(int padding) {
/*  71 */     this.gl_padding_l = padding;
/*  72 */     updateUI();
/*     */   }
/*     */   
/*     */   public void setPaddingRight(int padding) {
/*  76 */     this.gl_padding_r = padding;
/*  77 */     updateUI();
/*     */   }
/*     */   
/*     */   public void setPaddingMeddle(int padding) {
/*  81 */     this.gl_padding_m = padding;
/*  82 */     updateUI();
/*     */   }
/*     */   
/*     */   private void updateUI() {
/*  86 */     if (this.isDualMode) {
/*  87 */       RelativeLayout.LayoutParams para_l = null;
/*  88 */       if (this.gl_width != 0 && this.gl_height != 0) {
/*  89 */         para_l = new RelativeLayout.LayoutParams(this.gl_width, this.gl_height);
/*     */       } else {
/*  91 */         para_l = new RelativeLayout.LayoutParams(-2, -2);
/*  92 */       }  para_l.setMargins(this.gl_padding_l, 0, this.gl_padding_m / 2, 0);
/*  93 */       para_l.addRule(15);
/*  94 */       this.TextureViewLeft.setLayoutParams(para_l);
/*  95 */       this.TextureViewLeft.setId(1234);
/*     */       
/*  97 */       RelativeLayout.LayoutParams para_r = null;
/*  98 */       if (this.gl_width != 0 && this.gl_height != 0) {
/*  99 */         para_r = new RelativeLayout.LayoutParams(this.gl_width, this.gl_height);
/*     */       } else {
/* 101 */         para_r = new RelativeLayout.LayoutParams(-2, -2);
/* 102 */       }  para_r.addRule(1, 1234);
/* 103 */       para_r.addRule(15);
/* 104 */       if (this.gl_padding_m % 2 == 0) {
/* 105 */         para_r.setMargins(this.gl_padding_m / 2 + this.gl_padding_m, 0, this.gl_padding_r, 0);
/*     */       } else {
/* 107 */         para_r.setMargins(this.gl_padding_m / 2 + 1 + this.gl_padding_m, 0, this.gl_padding_r, 0);
/* 108 */       }  this.TextureViewRight.setLayoutParams(para_r);
/*     */     } else {
/* 110 */       RelativeLayout.LayoutParams para = null;
/* 111 */       RelativeLayout.LayoutParams para1 = null;
/* 112 */       if (this.gl_width != 0 && this.gl_height != 0) {
/* 113 */         para = new RelativeLayout.LayoutParams(this.gl_width, this.gl_height);
/*     */       } else {
/* 115 */         para = new RelativeLayout.LayoutParams(-2, -2);
/* 116 */       }  para.addRule(13);
/*     */ 
/*     */       
/* 119 */       para1 = new RelativeLayout.LayoutParams(2, 2);
/*     */       
/* 121 */       this.TextureViewLeft.setLayoutParams(para1);
/* 122 */       this.TextureViewRight.setLayoutParams(para);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public TextureView getViewLeft() { return this.TextureViewLeft; }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public TextureView getViewRight() { return this.TextureViewRight; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\component\DualTextureView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */