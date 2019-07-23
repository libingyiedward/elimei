/*    */ package com.elimei.elimei.data.filter;
/*    */ 
/*    */ import android.annotation.TargetApi;
/*    */ import java.util.Arrays;
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
/*    */ public class Approximator
/*    */ {
/*    */   @TargetApi(9)
/*    */   public float[] reduceWithDouglasPeucker(float[] points, float tolerance) {
/* 20 */     int greatestIndex = 0;
/* 21 */     float greatestDistance = 0.0F;
/*    */     
/* 23 */     Line line = new Line(points[0], points[1], points[points.length - 2], points[points.length - 1]);
/*    */     
/* 25 */     for (int i = 2; i < points.length - 2; i += 2) {
/*    */       
/* 27 */       float distance = line.distance(points[i], points[i + 1]);
/*    */       
/* 29 */       if (distance > greatestDistance) {
/* 30 */         greatestDistance = distance;
/* 31 */         greatestIndex = i;
/*    */       } 
/*    */     } 
/*    */     
/* 35 */     if (greatestDistance > tolerance) {
/*    */       
/* 37 */       float[] reduced1 = reduceWithDouglasPeucker(Arrays.copyOfRange(points, 0, greatestIndex + 2), tolerance);
/* 38 */       float[] reduced2 = reduceWithDouglasPeucker(Arrays.copyOfRange(points, greatestIndex, points.length), tolerance);
/*    */ 
/*    */       
/* 41 */       float[] result1 = reduced1;
/* 42 */       float[] result2 = Arrays.copyOfRange(reduced2, 2, reduced2.length);
/*    */       
/* 44 */       return concat(new float[][] { result1, result2 });
/*    */     } 
/* 46 */     return line.getPoints();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   float[] concat(float[]... arrays) {
/* 57 */     int length = 0;
/* 58 */     for (float[] array : arrays) {
/* 59 */       length += array.length;
/*    */     }
/* 61 */     float[] result = new float[length];
/* 62 */     int pos = 0;
/* 63 */     for (float[] array : arrays) {
/* 64 */       for (float element : array) {
/* 65 */         result[pos] = element;
/* 66 */         pos++;
/*    */       } 
/*    */     } 
/* 69 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   private class Line
/*    */   {
/*    */     private float[] points;
/*    */     
/*    */     private float sxey;
/*    */     
/*    */     private float exsy;
/*    */     private float dx;
/*    */     private float dy;
/*    */     private float length;
/*    */     
/*    */     public Line(float x1, float y1, float x2, float y2) {
/* 85 */       this.dx = x1 - x2;
/* 86 */       this.dy = y1 - y2;
/* 87 */       this.sxey = x1 * y2;
/* 88 */       this.exsy = x2 * y1;
/* 89 */       this.length = (float)Math.sqrt((this.dx * this.dx + this.dy * this.dy));
/*    */       
/* 91 */       this.points = new float[] { x1, y1, x2, y2 };
/*    */     }
/*    */ 
/*    */     
/* 95 */     public float distance(float x, float y) { return Math.abs(this.dy * x - this.dx * y + this.sxey - this.exsy) / this.length; }
/*    */ 
/*    */ 
/*    */     
/* 99 */     public float[] getPoints() { return this.points; }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\filter\Approximator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */