/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Parcel;
/*     */ import android.os.ParcelFormatException;
/*     */ import android.os.Parcelable;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Entry
/*     */   extends BaseEntry
/*     */   implements Parcelable
/*     */ {
/*  21 */   private float x = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry(float x, float y) {
/*  34 */     super(y);
/*  35 */     this.x = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry(float x, float y, Object data) {
/*  46 */     super(y, data);
/*  47 */     this.x = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry(float x, float y, Drawable icon) {
/*  58 */     super(y, icon);
/*  59 */     this.x = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry(float x, float y, Drawable icon, Object data) {
/*  71 */     super(y, icon, data);
/*  72 */     this.x = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public float getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void setX(float x) { this.x = x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public Entry copy() { return new Entry(this.x, getY(), getData()); }
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
/*     */   public boolean equalTo(Entry e) {
/* 113 */     if (e == null) {
/* 114 */       return false;
/*     */     }
/* 116 */     if (e.getData() != getData()) {
/* 117 */       return false;
/*     */     }
/* 119 */     if (Math.abs(e.x - this.x) > Utils.FLOAT_EPSILON) {
/* 120 */       return false;
/*     */     }
/* 122 */     if (Math.abs(e.getY() - getY()) > Utils.FLOAT_EPSILON) {
/* 123 */       return false;
/*     */     }
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public String toString() { return "Entry, x: " + this.x + " y: " + getY(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public int describeContents() { return 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToParcel(Parcel dest, int flags) {
/* 143 */     dest.writeFloat(this.x);
/* 144 */     dest.writeFloat(getY());
/* 145 */     if (getData() != null) {
/* 146 */       if (getData() instanceof Parcelable) {
/* 147 */         dest.writeInt(1);
/* 148 */         dest.writeParcelable((Parcelable)getData(), flags);
/*     */       } else {
/* 150 */         throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
/*     */       } 
/*     */     } else {
/* 153 */       dest.writeInt(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Entry(Parcel in) {
/* 158 */     this.x = in.readFloat();
/* 159 */     setY(in.readFloat());
/* 160 */     if (in.readInt() == 1) {
/* 161 */       setData(in.readParcelable(Object.class.getClassLoader()));
/*     */     }
/*     */   }
/*     */   
/* 165 */   public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>()
/*     */     {
/* 167 */       public Entry createFromParcel(Parcel source) { return new Entry(source); }
/*     */ 
/*     */ 
/*     */       
/* 171 */       public Entry[] newArray(int size) { return new Entry[size]; }
/*     */     };
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\Entry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */