/*     */ package com.elimei.elimei.matrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Vector3
/*     */ {
/*     */   public float x;
/*     */   public float y;
/*     */   public float z;
/*  12 */   public static final Vector3 ZERO = new Vector3(0.0F, 0.0F, 0.0F);
/*  13 */   public static final Vector3 UNIT_X = new Vector3(1.0F, 0.0F, 0.0F);
/*  14 */   public static final Vector3 UNIT_Y = new Vector3(0.0F, 1.0F, 0.0F);
/*  15 */   public static final Vector3 UNIT_Z = new Vector3(0.0F, 0.0F, 1.0F);
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector3() {}
/*     */ 
/*     */   
/*  22 */   public Vector3(float[] array) { set(array[0], array[1], array[2]); }
/*     */ 
/*     */ 
/*     */   
/*  26 */   public Vector3(float xValue, float yValue, float zValue) { set(xValue, yValue, zValue); }
/*     */ 
/*     */ 
/*     */   
/*  30 */   public Vector3(Vector3 other) { set(other); }
/*     */ 
/*     */   
/*     */   public final void add(Vector3 other) {
/*  34 */     this.x += other.x;
/*  35 */     this.y += other.y;
/*  36 */     this.z += other.z;
/*     */   }
/*     */   
/*     */   public final void add(float otherX, float otherY, float otherZ) {
/*  40 */     this.x += otherX;
/*  41 */     this.y += otherY;
/*  42 */     this.z += otherZ;
/*     */   }
/*     */   
/*     */   public final void subtract(Vector3 other) {
/*  46 */     this.x -= other.x;
/*  47 */     this.y -= other.y;
/*  48 */     this.z -= other.z;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void subtractMultiple(Vector3 other, float multiplicator) {
/*  53 */     this.x -= other.x * multiplicator;
/*  54 */     this.y -= other.y * multiplicator;
/*  55 */     this.z -= other.z * multiplicator;
/*     */   }
/*     */   
/*     */   public final void multiply(float magnitude) {
/*  59 */     this.x *= magnitude;
/*  60 */     this.y *= magnitude;
/*  61 */     this.z *= magnitude;
/*     */   }
/*     */   
/*     */   public final void multiply(Vector3 other) {
/*  65 */     this.x *= other.x;
/*  66 */     this.y *= other.y;
/*  67 */     this.z *= other.z;
/*     */   }
/*     */   
/*     */   public final void divide(float magnitude) {
/*  71 */     if (magnitude != 0.0F) {
/*  72 */       this.x /= magnitude;
/*  73 */       this.y /= magnitude;
/*  74 */       this.z /= magnitude;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void set(Vector3 other) {
/*  79 */     this.x = other.x;
/*  80 */     this.y = other.y;
/*  81 */     this.z = other.z;
/*     */   }
/*     */   
/*     */   public final void set(float xValue, float yValue, float zValue) {
/*  85 */     this.x = xValue;
/*  86 */     this.y = yValue;
/*  87 */     this.z = zValue;
/*     */   }
/*     */ 
/*     */   
/*  91 */   public final float dot(Vector3 other) { return this.x * other.x + this.y * other.y + this.z * other.z; }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public final Vector3 cross(Vector3 other) { return new Vector3(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z, this.x * other.y - this.y * other.x); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public final float length() { return (float)Math.sqrt(length2()); }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public final float length2() { return this.x * this.x + this.y * this.y + this.z * this.z; }
/*     */ 
/*     */   
/*     */   public final float distance2(Vector3 other) {
/* 109 */     float dx = this.x - other.x;
/* 110 */     float dy = this.y - other.y;
/* 111 */     float dz = this.z - other.z;
/* 112 */     return dx * dx + dy * dy + dz * dz;
/*     */   }
/*     */   
/*     */   public final float normalize() {
/* 116 */     float magnitude = length();
/*     */ 
/*     */     
/* 119 */     if (magnitude != 0.0F) {
/* 120 */       this.x /= magnitude;
/* 121 */       this.y /= magnitude;
/* 122 */       this.z /= magnitude;
/*     */     } 
/*     */     
/* 125 */     return magnitude;
/*     */   }
/*     */ 
/*     */   
/* 129 */   public final void zero() { set(0.0F, 0.0F, 0.0F); }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public final boolean pointsInSameDirection(Vector3 other) { return (dot(other) > 0.0F); }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\matrix\Vector3.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */