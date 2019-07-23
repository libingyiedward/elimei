/*    */ package com.elimei.elimei.utils;
/*    */ 
/*    */ import com.elimei.elimei.data.Entry;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntryXComparator
/*    */   extends Object
/*    */   implements Comparator<Entry>
/*    */ {
/*    */   public int compare(Entry entry1, Entry entry2) {
/* 14 */     float diff = entry1.getX() - entry2.getX();
/*    */     
/* 16 */     if (diff == 0.0F) return 0;
/*    */     
/* 18 */     if (diff > 0.0F) return 1; 
/* 19 */     return -1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\EntryXComparator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */