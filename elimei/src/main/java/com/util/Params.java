/*    */ package com.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Params
/*    */   extends HashMap<String, String>
/*    */ {
/* 16 */   public static Params newInstance() { return new Params(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public Params params(String key, String value) {
/* 21 */     put(key, value);
/* 22 */     return this;
/*    */   }
/*    */   
/*    */   public Params params(String key, int value) {
/* 26 */     put(key, value + "");
/* 27 */     return this;
/*    */   }
/*    */   
/*    */   public Params params(String key, long value) {
/* 31 */     put(key, value + "");
/* 32 */     return this;
/*    */   }
/*    */   
/*    */   public Params params(String key, double value) {
/* 36 */     put(key, value + "");
/* 37 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\co\\util\Params.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */