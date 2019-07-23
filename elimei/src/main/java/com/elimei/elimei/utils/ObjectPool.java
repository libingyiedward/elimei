/*     */ package com.elimei.elimei.utils;
/*     */ 
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
/*     */ public class ObjectPool<T extends ObjectPool.Poolable>
/*     */   extends Object
/*     */ {
/*  20 */   private static int ids = 0;
/*     */ 
/*     */   
/*     */   private int poolId;
/*     */   
/*     */   private int desiredCapacity;
/*     */   
/*     */   private Object[] objects;
/*     */   
/*     */   private int objectsPointer;
/*     */   
/*     */   private T modelObject;
/*     */   
/*     */   private float replenishPercentage;
/*     */ 
/*     */   
/*  36 */   public int getPoolId() { return this.poolId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ObjectPool create(int withCapacity, Poolable object) {
/*  47 */     ObjectPool result = new ObjectPool(withCapacity, object);
/*  48 */     result.poolId = ids;
/*  49 */     ids++;
/*     */     
/*  51 */     return result;
/*     */   }
/*     */   
/*     */   private ObjectPool(int withCapacity, T object) {
/*  55 */     if (withCapacity <= 0) {
/*  56 */       throw new IllegalArgumentException("Object Pool must be instantiated with a capacity greater than 0!");
/*     */     }
/*  58 */     this.desiredCapacity = withCapacity;
/*  59 */     this.objects = new Object[this.desiredCapacity];
/*  60 */     this.objectsPointer = 0;
/*  61 */     this.modelObject = object;
/*  62 */     this.replenishPercentage = 1.0F;
/*  63 */     refillPool();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReplenishPercentage(float percentage) {
/*  73 */     float p = percentage;
/*  74 */     if (p > 1.0F) {
/*  75 */       p = 1.0F;
/*     */     }
/*  77 */     else if (p < 0.0F) {
/*  78 */       p = 0.0F;
/*     */     } 
/*  80 */     this.replenishPercentage = p;
/*     */   }
/*     */ 
/*     */   
/*  84 */   public float getReplenishPercentage() { return this.replenishPercentage; }
/*     */ 
/*     */ 
/*     */   
/*  88 */   private void refillPool() { refillPool(this.replenishPercentage); }
/*     */ 
/*     */   
/*     */   private void refillPool(float percentage) {
/*  92 */     int portionOfCapacity = (int)(this.desiredCapacity * percentage);
/*     */     
/*  94 */     if (portionOfCapacity < 1) {
/*  95 */       portionOfCapacity = 1;
/*  96 */     } else if (portionOfCapacity > this.desiredCapacity) {
/*  97 */       portionOfCapacity = this.desiredCapacity;
/*     */     } 
/*     */     
/* 100 */     for (int i = 0; i < portionOfCapacity; i++) {
/* 101 */       this.objects[i] = this.modelObject.instantiate();
/*     */     }
/* 103 */     this.objectsPointer = portionOfCapacity - 1;
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
/*     */   public T get() {
/* 115 */     if (this.objectsPointer == -1 && this.replenishPercentage > 0.0F) {
/* 116 */       refillPool();
/*     */     }
/*     */     
/* 119 */     T result = (T)(Poolable)this.objects[this.objectsPointer];
/* 120 */     result.currentOwnerId = Poolable.NO_OWNER;
/* 121 */     this.objectsPointer--;
/*     */     
/* 123 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recycle(T object) {
/* 133 */     if (object.currentOwnerId != Poolable.NO_OWNER) {
/* 134 */       if (object.currentOwnerId == this.poolId) {
/* 135 */         throw new IllegalArgumentException("The object passed is already stored in this pool!");
/*     */       }
/* 137 */       throw new IllegalArgumentException("The object to recycle already belongs to poolId " + object.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
/*     */     } 
/*     */ 
/*     */     
/* 141 */     this.objectsPointer++;
/* 142 */     if (this.objectsPointer >= this.objects.length) {
/* 143 */       resizePool();
/*     */     }
/*     */     
/* 146 */     object.currentOwnerId = this.poolId;
/* 147 */     this.objects[this.objectsPointer] = object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recycle(List<T> objects) {
/* 158 */     while (objects.size() + this.objectsPointer + 1 > this.desiredCapacity) {
/* 159 */       resizePool();
/*     */     }
/* 161 */     int objectsListSize = objects.size();
/*     */ 
/*     */     
/* 164 */     for (int i = 0; i < objectsListSize; i++) {
/* 165 */       T object = (T)(Poolable)objects.get(i);
/* 166 */       if (object.currentOwnerId != Poolable.NO_OWNER) {
/* 167 */         if (object.currentOwnerId == this.poolId) {
/* 168 */           throw new IllegalArgumentException("The object passed is already stored in this pool!");
/*     */         }
/* 170 */         throw new IllegalArgumentException("The object to recycle already belongs to poolId " + object.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
/*     */       } 
/*     */       
/* 173 */       object.currentOwnerId = this.poolId;
/* 174 */       this.objects[this.objectsPointer + 1 + i] = object;
/*     */     } 
/* 176 */     this.objectsPointer += objectsListSize;
/*     */   }
/*     */   
/*     */   private void resizePool() {
/* 180 */     int oldCapacity = this.desiredCapacity;
/* 181 */     this.desiredCapacity *= 2;
/* 182 */     Object[] temp = new Object[this.desiredCapacity];
/* 183 */     for (int i = 0; i < oldCapacity; i++) {
/* 184 */       temp[i] = this.objects[i];
/*     */     }
/* 186 */     this.objects = temp;
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
/* 197 */   public int getPoolCapacity() { return this.objects.length; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public int getPoolCount() { return this.objectsPointer + 1; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class Poolable
/*     */   {
/* 212 */     public static int NO_OWNER = -1;
/* 213 */     int currentOwnerId = NO_OWNER;
/*     */     
/*     */     protected abstract Poolable instantiate();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\ObjectPool.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */