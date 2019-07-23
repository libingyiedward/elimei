/*    */ package com.dvsonghan.entity;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ListFile
/*    */   implements Serializable
/*    */ {
/*    */   private File file;
/*    */   private String type;
/*    */   
/* 16 */   public String getType() { return this.type; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public void setType(String type) { this.type = type; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public File getFile() { return this.file; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void setFile(File file) { this.file = file; }
/*    */ 
/*    */ 
/*    */   
/*    */   public ListFile() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public ListFile(File file, String type) {
/* 37 */     this.file = file;
/* 38 */     this.type = type;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\dvsonghan\entity\ListFile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */