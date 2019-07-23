/*     */ package com.dvsonghan.utils;
/*     */ 
/*     */ import android.os.AsyncTask;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.math.BigInteger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GpsParser
/*     */ {
/*  17 */   static GpsParser instands = new GpsParser();
/*     */   
/*  19 */   public static GpsParser getInstands() { return instands; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   private List<GPSData> dataList = new ArrayList();
/*     */   
/*     */   public void loadAsync(File file, final OnGPSDataCallBack callback) {
/*  29 */     (new AsyncTask<File, Void, List<GPSData>>()
/*     */       {
/*     */         protected void onPreExecute()
/*     */         {
/*  33 */           if (callback != null)
/*     */           {
/*  35 */             callback.onPrepare();
/*     */           }
/*  37 */           super.onPreExecute();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*  42 */         protected List<GPSData> doInBackground(File... params) { return GpsParser.this.load(params[0]); }
/*     */ 
/*     */ 
/*     */         
/*     */         protected void onPostExecute(List<GPSData> result) {
/*  47 */           if (callback != null)
/*     */           {
/*  49 */             callback.onGPSData(result);
/*     */           }
/*  51 */           super.onPostExecute(result);
/*     */         }
/*  53 */       }).execute(new File[] { file });
/*     */   }
/*     */   
/*     */   public List<GPSData> load(File file) {
/*  57 */     this.dataList.clear();
/*     */     try {
/*  59 */       BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
/*     */ 
/*     */       
/*  62 */       byte[] beginSLLT = "SLLT".getBytes();
/*     */       
/*  64 */       byte[] gpsdata = new byte[28];
/*     */       
/*  66 */       byte[] temp = new byte[1];
/*  67 */       inputStream.skip((long)(inputStream.available() * 0.99D));
/*  68 */       label27: while (inputStream.read(temp) != -1) {
/*  69 */         for (int i = 0; i < 4 && 
/*  70 */           temp[0] == beginSLLT[i]; i++) {
/*     */           
/*  72 */           if (i == beginSLLT.length - 1) {
/*  73 */             inputStream.skip(beginSLLT.length);
/*  74 */             while (inputStream.read(gpsdata) != -1) {
/*  75 */               if ((new String(gpsdata)).contains("SGLT")) {
/*     */                 break label27;
/*     */               }
/*  78 */               parsetGPSData(gpsdata);
/*     */             } 
/*     */           } 
/*  81 */           inputStream.read(temp);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  87 */       inputStream.close();
/*  88 */     } catch (Exception e) {
/*  89 */       e.printStackTrace();
/*     */     } 
/*  91 */     return this.dataList;
/*     */   }
/*     */ 
/*     */   
/*  95 */   private int offset = 4;
/*     */ 
/*     */   
/*     */   public static interface OnGPSDataCallBack
/*     */   {
/*     */     void onPrepare();
/*     */ 
/*     */     
/*     */     void onGPSData(List<GPSData> param1List);
/*     */   }
/*     */   
/*     */   private void parsetGPSData(byte[] b) {
/* 107 */     double longitude = (new BigInteger(new byte[] { b[this.offset + 3], b[this.offset + 4], b[this.offset + 5], b[this.offset + 6] })).intValue() / 1.0E8D + (new BigInteger(new byte[] { b[this.offset + 1], b[this.offset + 2] })).intValue();
/*     */ 
/*     */ 
/*     */     
/* 111 */     double latitude = (new BigInteger(new byte[] { b[this.offset + 9], b[this.offset + 10], b[this.offset + 11], b[this.offset + 12] })).intValue() / 1.0E8D + (new BigInteger(new byte[] { b[this.offset + 7], b[this.offset + 8] })).intValue();
/*     */     
/* 113 */     GPSData gData = new GPSData();
/* 114 */     gData.setLatitude(latitude);
/* 115 */     gData.setLongitude(longitude);
/* 116 */     gData.setStatus(b[this.offset + 0] & 0xFF);
/* 117 */     gData.setAltitude(b[this.offset + 13] << 8 | b[this.offset + 14] & 0xFF);
/* 118 */     gData.setSpeed(b[this.offset + 15] << 8 | b[this.offset + 16] & 0xFF);
/* 119 */     gData.setDate(b[this.offset + 20] << 8 | b[this.offset + 21] & 0xFF, b[this.offset + 22] & 0xFF, b[this.offset + 23] & 0xFF, b[this.offset + 17] & 0xFF, b[this.offset + 18] & 0xFF, b[this.offset + 19] & 0xFF);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     this.dataList.add(gData);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\dvsongha\\utils\GpsParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */