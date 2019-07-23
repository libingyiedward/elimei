/*     */ package com.elimei.elimei.service;
/*     */ 
/*     */ import android.os.Handler;
/*     */ import android.util.Log;
/*     */ import com.android.AudioProc.AEC;
/*     */ import com.todoroo.aacenc.AACEncoder;
/*     */ import java.nio.ByteOrder;
/*     */ 
/*     */ public class PushTalkController
/*     */ {
/*     */   byte[] mOutputAac;
/*     */   private int SAMPLE_RATE;
/*     */   boolean mIsRecord;
/*     */   
/*     */   public PushTalkController(int sampleRate) {
/*  16 */     this.mOutputAac = new byte[20000];
/*     */     
/*  18 */     this.SAMPLE_RATE = 11025;
/*  19 */     this.mIsRecord = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     this.mIsRecord = false;
/*  30 */     this.mSocket = new UdpSocket();
/*  31 */     this.mSampleRate = sampleRate;
/*     */     
/*  33 */     this.mHandler = new Handler();
/*     */   }
/*     */ 
/*     */   
/*     */   UdpSocket mSocket;
/*     */   
/*     */   int mSampleRate;
/*     */   public Handler mHandler;
/*     */   
/*     */   public void sendData(short[] data) {
/*  43 */     if (this.mIsRecord) {
/*     */       
/*     */       try {
/*     */         
/*  47 */         int res = AACEncoder.encode(Shorts2Bytes(data), this.mOutputAac);
/*  48 */         if (res > 0) {
/*  49 */           byte[] tmpdata = new byte[res];
/*  50 */           System.arraycopy(this.mOutputAac, 0, tmpdata, 0, res);
/*  51 */           this.mSocket.sendData(tmpdata);
/*     */         } 
/*  53 */       } catch (Exception e) {
/*  54 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean testCPU() {
/*  60 */     if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
/*  61 */       return true;
/*     */     }
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   
/*  67 */   public byte[] getBytes(short s) { return getBytes(s, testCPU()); }
/*     */   
/*     */   public byte[] getBytes(long s, boolean bBigEnding) {
/*  70 */     byte[] buf = new byte[8];
/*  71 */     if (bBigEnding) {
/*  72 */       for (int i = buf.length - 1; i >= 0; i--) {
/*  73 */         buf[i] = (byte)(int)(s & 0xFFL);
/*  74 */         s >>= 8;
/*     */       } 
/*     */     } else {
/*  77 */       for (int i = 0; i < buf.length; i++) {
/*  78 */         buf[i] = (byte)(int)(s & 0xFFL);
/*  79 */         s >>= 8;
/*     */       } 
/*     */     } 
/*  82 */     return buf;
/*     */   }
/*     */   public byte[] Shorts2Bytes(short[] s) {
/*  85 */     byte bLength = 2;
/*  86 */     byte[] buf = new byte[s.length * bLength];
/*  87 */     for (int iLoop = 0; iLoop < s.length; iLoop++) {
/*  88 */       byte[] temp = getBytes(s[iLoop]);
/*  89 */       for (int jLoop = 0; jLoop < bLength; jLoop++) {
/*  90 */         buf[iLoop * bLength + jLoop] = temp[jLoop];
/*     */       }
/*     */     } 
/*  93 */     return buf;
/*     */   }
/*     */   
/*     */   public boolean startPushTalk(final String deviceAddr) {
/*  97 */     if (this.mIsRecord) {
/*  98 */       Log.d("Allen", "encode flag=" + this.mIsRecord);
/*  99 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 104 */     Thread t = new Thread(new Runnable()
/*     */         {
/*     */ 
/*     */           
/*     */           public void run()
/*     */           {
/* 110 */             PushTalkController.this.mSocket.connect(deviceAddr);
/*     */           }
/*     */         });
/*     */     
/* 114 */     t.start();
/*     */     try {
/* 116 */       t.join();
/* 117 */     } catch (InterruptedException e) {
/*     */       
/* 119 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 122 */     if (!this.mSocket.isSocketConnected()) {
/* 123 */       Log.d("Allen", "encode socket not connect ");
/* 124 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     int res = AACEncoder.init(16000, 1, 11025, 16);
/*     */ 
/*     */     
/* 134 */     if (res != 0) {
/* 135 */       Log.d("Allen", "encode init fail");
/* 136 */       return false;
/*     */     } 
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
/* 153 */     AEC.Init(this.SAMPLE_RATE, false, 0);
/* 154 */     AEC.createEngine();
/* 155 */     AEC.createAudioPlayer();
/* 156 */     this.mIsRecord = true;
/* 157 */     Log.d("Allen", "encode startPushTalk success");
/* 158 */     return true;
/*     */   }
/*     */   
/*     */   public void stopPushTalk() {
/* 162 */     if (this.mIsRecord) {
/* 163 */       AEC.stopRecording();
/* 164 */       AEC.shutdown();
/*     */     } 
/* 166 */     this.mIsRecord = false;
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
/* 177 */     AACEncoder.uninit();
/*     */     
/* 179 */     this.mSocket.close();
/*     */   }
/*     */ 
/*     */   
/* 183 */   public boolean isPushTalk() { return this.mIsRecord; }
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
/*     */ 
/*     */   
/*     */   private boolean WaitForThreadStop(Thread t) {
/* 259 */     int waitCount = 0;
/* 260 */     if (t == null) {
/* 261 */       return true;
/*     */     }
/* 263 */     t.interrupt();
/* 264 */     while (waitCount < 2) {
/* 265 */       if (!t.isAlive()) {
/* 266 */         return true;
/*     */       }
/*     */       try {
/* 269 */         t.sleep(20L);
/* 270 */       } catch (InterruptedException e) {
/* 271 */         e.printStackTrace();
/*     */       } 
/* 273 */       waitCount++;
/*     */     } 
/* 275 */     if (t.isAlive() == true) {
/*     */       try {
/* 277 */         t.stop();
/* 278 */         return true;
/* 279 */       } catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/* 283 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\service\PushTalkController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */