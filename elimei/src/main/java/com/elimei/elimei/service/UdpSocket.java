/*    */ package com.elimei.elimei.service;
/*    */ 
/*    */ import android.util.Log;
/*    */ import java.io.IOException;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ import java.net.InetAddress;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.SocketAddress;
/*    */ import java.net.SocketException;
/*    */ import java.net.UnknownHostException;
/*    */ 
/*    */ public class UdpSocket
/*    */ {
/* 15 */   int UDP_SERVER_PORT = 8828;
/* 16 */   String UDP_SERVER_ADDRESS = "192.168.99.1";
/*    */ 
/*    */   
/*    */   private DatagramSocket mSocket;
/*    */ 
/*    */   
/*    */   InetAddress serverAddr;
/*    */   
/*    */   SocketAddress sockaddr;
/*    */ 
/*    */   
/*    */   public boolean connect(String deviceAddr) {
/* 28 */     boolean res = false;
/*    */     try {
/* 30 */       this.sockaddr = new InetSocketAddress(deviceAddr, this.UDP_SERVER_PORT);
/* 31 */       this.serverAddr = InetAddress.getByName(deviceAddr);
/* 32 */     } catch (UnknownHostException e) {
/*    */       
/* 34 */       e.printStackTrace();
/*    */     } 
/* 36 */     Log.d("Allen", "socket connect 0000");
/*    */     try {
/* 38 */       Log.d("Allen", "socket connect 11111");
/*    */       
/* 40 */       this.mSocket = new DatagramSocket();
/* 41 */       this.mSocket.setSoTimeout(3000);
/*    */       
/* 43 */       this.mSocket.connect(this.sockaddr);
/* 44 */       res = true;
/* 45 */     } catch (SocketException e) {
/*    */       
/* 47 */       e.printStackTrace();
/* 48 */       Log.d("Allen", "socket connect 2222");
/*    */     } 
/* 50 */     return res;
/*    */   }
/*    */   
/*    */   public boolean sendData(byte[] data) {
/* 54 */     if (this.mSocket != null && this.mSocket.isConnected()) {
/*    */       
/*    */       try {
/* 57 */         DatagramPacket pack = new DatagramPacket(data, data.length, this.serverAddr, this.UDP_SERVER_PORT);
/*    */         
/* 59 */         this.mSocket.send(pack);
/* 60 */         return true;
/* 61 */       } catch (IOException e) {
/*    */         
/* 63 */         e.printStackTrace();
/*    */       } 
/*    */     }
/*    */     
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isSocketConnected() {
/* 71 */     boolean res = false;
/*    */     try {
/* 73 */       res = this.mSocket.isConnected();
/* 74 */     } catch (Exception e) {
/*    */       
/* 76 */       res = false;
/*    */     } 
/* 78 */     return res;
/*    */   }
/*    */   
/*    */   public void close() {
/*    */     try {
/* 83 */       this.mSocket.close();
/* 84 */     } catch (Exception exception) {
/*    */     
/*    */     } finally {
/* 87 */       this.mSocket = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\service\UdpSocket.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */