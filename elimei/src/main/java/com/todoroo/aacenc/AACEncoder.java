/*    */ package com.todoroo.aacenc;
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
/*    */ public class AACEncoder
/*    */ {
/*    */   public static native int init(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*    */   
/*    */   public static native int encode(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);
/*    */   
/*    */   public static native void uninit();
/*    */   
/*    */   static  {
/* 26 */     System.loadLibrary("aac-encoder");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\todoroo\aacenc\AACEncoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */