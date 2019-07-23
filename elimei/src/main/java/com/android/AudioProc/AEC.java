/*    */ package com.android.AudioProc;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AEC
/*    */ {
/*    */   static AECCallback callback;
/*    */   
/* 12 */   public static void setAECCallback(AECCallback cb) { callback = cb; }
/*    */ 
/*    */   
/*    */   public static native void GetSPK(short[] paramArrayOfShort, int paramInt);
/*    */ 
/*    */   
/*    */   public static native void Init(int paramInt1, boolean paramBoolean, int paramInt2);
/*    */ 
/*    */   
/*    */   public static native void createEngine();
/*    */ 
/*    */   
/*    */   public static native void createAudioPlayer();
/*    */   
/* 26 */   public static void onAECValue(int value) { callback.callback(value); } public static native void shutdown();
/*    */   public static native boolean createAudioRecorder();
/*    */   public static native void startRecording();
/*    */   public static native void stopRecording();
/* 30 */   public static void onAECData(short[] data) { callback.onDateReceive(data); }
/*    */ 
/*    */   
/*    */   static  {
/*    */     try {
/* 35 */       System.loadLibrary("AECRTSP");
/* 36 */     } catch (UnsatisfiedLinkError ule) {
/* 37 */       System.out.println("loadLibrary(AECRTSP)," + ule.getMessage());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static interface AECCallback {
/*    */     void callback(int param1Int);
/*    */     
/*    */     void onDateReceive(short[] param1ArrayOfShort);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\android\AudioProc\AEC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */