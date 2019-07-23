/*    */ package com.dvsonghan.utils;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GPSData
/*    */ {
/*    */   private int status;
/*    */   private int altitude;
/*    */   private int speed;
/*    */   private double longitude;
/*    */   private double latitude;
/*    */   private Date date;
/*    */   
/* 16 */   public int getStatus() { return this.status; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public void setStatus(int status) { this.status = status; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public int getAltitude() { return this.altitude; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void setAltitude(int altitude) { this.altitude = altitude; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public int getSpeed() { return this.speed; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public void setSpeed(int speed) { this.speed = speed; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public double getLongitude() { return this.longitude; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setLongitude(double longitude) { this.longitude = longitude; }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public double getLatitude() { return this.latitude; }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public void setLatitude(double latitude) { this.latitude = latitude; }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public Date getDate() { return this.date; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public void setDate(int year, int month, int day, int hour, int min, int second) { this.date = new Date(year - 1900, month, day, hour, min, second); }
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
/* 72 */   public String toString() { return "GPSData [status=" + this.status + ", altitude=" + this.altitude + ", speed=" + this.speed + ", longitude=" + this.longitude + ", latitude=" + this.latitude + ", date=" + this.date + "]"; }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\dvsongha\\utils\GPSData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */