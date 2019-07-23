/*     */ package com.component;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.SurfaceView;
/*     */ import android.widget.RelativeLayout;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DualView
/*     */   extends RelativeLayout
/*     */ {
/*     */   private Context ctx;
/*     */   private SurfaceView SurfaceViewLeft;
/*     */   private SurfaceView SurfaceViewRight;
/*     */   private boolean isDualMode = true;
/*  19 */   private int gl_width = 0;
/*  20 */   private int gl_height = 0;
/*  21 */   private int gl_padding_l = 0;
/*  22 */   private int gl_padding_r = 0;
/*  23 */   private int gl_padding_m = 0;
/*     */   
/*     */   public DualView(Context context) {
/*  26 */     super(context);
/*  27 */     this.ctx = context;
/*  28 */     init();
/*     */   }
/*     */   
/*     */   public DualView(Context context, AttributeSet attrs) {
/*  32 */     super(context, attrs);
/*  33 */     this.ctx = context;
/*  34 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*  38 */     inflate(this.ctx, this.ctx.getResources().getIdentifier("dualview", "layout", this.ctx.getPackageName()), this);
/*     */     
/*  40 */     this.SurfaceViewLeft = (SurfaceView)findViewById(this.ctx.getResources().getIdentifier("SurfaceViewLeft", "id", this.ctx.getPackageName()));
/*  41 */     this.SurfaceViewRight = (SurfaceView)findViewById(this.ctx.getResources().getIdentifier("SurfaceViewRight", "id", this.ctx.getPackageName()));
/*  42 */     setDualView(this.isDualMode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public SurfaceView getRightSurfaceView() { return this.SurfaceViewRight; }
/*     */ 
/*     */ 
/*     */   
/*  51 */   public SurfaceView getLeftSurfaceView() { return this.SurfaceViewLeft; }
/*     */   
/*     */   public void setDualView(boolean state) {
/*  54 */     this.isDualMode = state;
/*     */ 
/*     */     
/*  57 */     this.SurfaceViewLeft.setVisibility(0);
/*  58 */     this.SurfaceViewRight.setVisibility(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     updateUI();
/*     */   }
/*     */ 
/*     */   
/*  68 */   public boolean getDualView() { return this.isDualMode; }
/*     */ 
/*     */   
/*     */   public void setImageSize(int width, int height) {
/*  72 */     this.gl_width = width;
/*  73 */     this.gl_height = height;
/*  74 */     updateUI();
/*     */   }
/*     */   
/*     */   public void setPaddingLeft(int padding) {
/*  78 */     this.gl_padding_l = padding;
/*  79 */     updateUI();
/*     */   }
/*     */   
/*     */   public void setPaddingRight(int padding) {
/*  83 */     this.gl_padding_r = padding;
/*  84 */     updateUI();
/*     */   }
/*     */   
/*     */   public void setPaddingMeddle(int padding) {
/*  88 */     this.gl_padding_m = padding;
/*  89 */     updateUI();
/*     */   }
/*     */   
/*     */   private void updateUI() {
/*  93 */     if (this.isDualMode) {
/*  94 */       RelativeLayout.LayoutParams para_l = null;
/*  95 */       if (this.gl_width != 0 && this.gl_height != 0) {
/*  96 */         para_l = new RelativeLayout.LayoutParams(this.gl_width, this.gl_height);
/*     */       } else {
/*  98 */         para_l = new RelativeLayout.LayoutParams(-2, -2);
/*  99 */       }  para_l.setMargins(this.gl_padding_l, 0, this.gl_padding_m / 2, 0);
/* 100 */       para_l.addRule(15);
/* 101 */       this.SurfaceViewLeft.setLayoutParams(para_l);
/* 102 */       this.SurfaceViewLeft.setId(1234);
/*     */       
/* 104 */       RelativeLayout.LayoutParams para_r = null;
/* 105 */       if (this.gl_width != 0 && this.gl_height != 0) {
/* 106 */         para_r = new RelativeLayout.LayoutParams(this.gl_width, this.gl_height);
/*     */       } else {
/* 108 */         para_r = new RelativeLayout.LayoutParams(-2, -2);
/* 109 */       }  para_r.addRule(1, 1234);
/* 110 */       para_r.addRule(15);
/* 111 */       if (this.gl_padding_m % 2 == 0) {
/* 112 */         para_r.setMargins(this.gl_padding_m / 2 + this.gl_padding_m, 0, this.gl_padding_r, 0);
/*     */       } else {
/* 114 */         para_r.setMargins(this.gl_padding_m / 2 + 1 + this.gl_padding_m, 0, this.gl_padding_r, 0);
/* 115 */       }  this.SurfaceViewRight.setLayoutParams(para_r);
/*     */     } else {
/* 117 */       RelativeLayout.LayoutParams para = null;
/* 118 */       RelativeLayout.LayoutParams para1 = null;
/* 119 */       if (this.gl_width != 0 && this.gl_height != 0) {
/* 120 */         para = new RelativeLayout.LayoutParams(this.gl_width, this.gl_height);
/*     */       } else {
/* 122 */         para = new RelativeLayout.LayoutParams(-2, -2);
/* 123 */       }  para.addRule(13);
/*     */ 
/*     */       
/* 126 */       para1 = new RelativeLayout.LayoutParams(2, 2);
/*     */       
/* 128 */       this.SurfaceViewLeft.setLayoutParams(para1);
/* 129 */       this.SurfaceViewRight.setLayoutParams(para);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public SurfaceView getViewLeft() { return this.SurfaceViewLeft; }
/*     */ 
/*     */ 
/*     */   
/* 142 */   public SurfaceView getViewRight() { return this.SurfaceViewRight; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\component\DualView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */