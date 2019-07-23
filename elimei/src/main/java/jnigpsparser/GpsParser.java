/*     */ package jnigpsparser;
/*     */ 
/*     */ import android.os.AsyncTask;
/*     */ import com.dvsonghan.utils.GPSData;
/*     */ import java.io.File;
/*     */ import java.math.BigInteger;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class GpsParser
/*     */ {
/*     */   static GpsParser instands;
/*     */   
/*     */   static  {
/*  18 */     System.loadLibrary("getgps");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  23 */     instands = new GpsParser();
/*     */   }
/*     */   
/*  26 */   public static GpsParser getInstands() { return instands; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private List<GPSData> dataList = new ArrayList();
/*     */   
/*     */   public void loadAsync(File file, final OnGPSDataCallBack callback) {
/*  38 */     (new AsyncTask<File, Void, List<GPSData>>()
/*     */       {
/*     */         protected void onPreExecute()
/*     */         {
/*  42 */           if (callback != null) {
/*  43 */             callback.onPrepare();
/*     */           }
/*  45 */           super.onPreExecute();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*  50 */         protected List<GPSData> doInBackground(File... params) { return GpsParser.this.load(params[0]); }
/*     */ 
/*     */ 
/*     */         
/*     */         protected void onPostExecute(List<GPSData> result) {
/*  55 */           if (callback != null) {
/*  56 */             callback.onGPSData(result);
/*     */           }
/*  58 */           super.onPostExecute(result);
/*     */         }
/*  60 */       }).execute(new File[] { file });
/*     */   }
/*     */   
/*     */   public List<GPSData> load(File file) {
/*  64 */     this.dataList.clear();
/*  65 */     if (file.exists()) {
/*  66 */       byte[] mSLLTByte = getSLLTByteArray(file.getAbsolutePath());
/*  67 */       int l = mSLLTByte.length;
/*     */       
/*  69 */       if (l == 0) {
/*  70 */         return this.dataList;
/*     */       }
/*     */       
/*  73 */       byte[] length = new byte[4];
/*  74 */       System.arraycopy(mSLLTByte, 4, length, 0, 4);
/*     */       
/*  76 */       byte[] b = new byte[28];
/*  77 */       int k = 0;
/*  78 */       for (int i = 12; i < ByteBuffer.wrap(length).order(ByteOrder.LITTLE_ENDIAN).getInt(); i++) {
/*  79 */         b[k] = mSLLTByte[i];
/*  80 */         k++;
/*  81 */         if (k % 28 == 0) {
/*  82 */           parsetGPSData(b);
/*  83 */           k = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     return this.dataList;
/*     */   } public static interface OnGPSDataCallBack {
/*     */     void onPrepare(); void onGPSData(List<GPSData> param1List); }
/*     */   private int byteArrayToInt(byte[] b) {
/*  92 */     int value = 0;
/*  93 */     for (int i = 0; i < 4; i++) {
/*  94 */       int shift = (3 - i) * 8;
/*  95 */       value += ((b[i] & 0xFF) << shift);
/*     */     } 
/*  97 */     return value;
/*     */   }
/*     */   
/* 100 */   private int offset = 0;
/*     */   
/*     */   private void parsetGPSData(byte[] b) {
/* 103 */     StringBuffer sbBuffer = new StringBuffer();
/* 104 */     for (byte c : b) {
/* 105 */       sbBuffer.append(String.format("%02X ", new Object[] { Byte.valueOf(c) }));
/*     */     } 
/* 107 */     System.out.println(sbBuffer);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     double longitude = (new BigInteger(new byte[] { b[this.offset + 3], b[this.offset + 4], b[this.offset + 5], b[this.offset + 6] })).intValue() / 1.0E8D + (new BigInteger(new byte[] { b[this.offset + 1], b[this.offset + 2] })).intValue();
/*     */ 
/*     */ 
/*     */     
/* 116 */     double latitude = (new BigInteger(new byte[] { b[this.offset + 9], b[this.offset + 10], b[this.offset + 11], b[this.offset + 12] })).intValue() / 1.0E8D + (new BigInteger(new byte[] { b[this.offset + 7], b[this.offset + 8] })).intValue();
/*     */     
/* 118 */     GPSData gData = new GPSData();
/* 119 */     gData.setLatitude(latitude);
/* 120 */     gData.setLongitude(longitude);
/* 121 */     gData.setStatus(b[this.offset + 0] & 0xFF);
/* 122 */     gData.setAltitude(b[this.offset + 13] << 8 | b[this.offset + 14] & 0xFF);
/* 123 */     gData.setSpeed(b[this.offset + 15] << 8 | b[this.offset + 16] & 0xFF);
/* 124 */     gData.setDate(b[this.offset + 20] << 8 | b[this.offset + 21] & 0xFF, b[this.offset + 22] & 0xFF, b[this.offset + 23] & 0xFF, b[this.offset + 17] & 0xFF, b[this.offset + 18] & 0xFF, b[this.offset + 19] & 0xFF);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     this.dataList.add(gData);
/*     */   }
/*     */   
/*     */   public native byte[] getSLLTByteArray(String paramString);
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\jnigpsparser\GpsParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */