/*     */ package com.elimei.elimei.utils;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Build;
/*     */ import android.text.Layout;
/*     */ import android.text.StaticLayout;
/*     */ import android.text.TextPaint;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.util.Log;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.VelocityTracker;
/*     */ import android.view.View;
/*     */ import android.view.ViewConfiguration;
/*     */ import com.elimei.elimei.formatter.DefaultValueFormatter;
/*     */ import com.elimei.elimei.formatter.IValueFormatter;
/*     */ import java.util.List;
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
/*     */ public abstract class Utils
/*     */ {
/*     */   private static DisplayMetrics mMetrics;
/*  38 */   private static int mMinimumFlingVelocity = 50;
/*  39 */   private static int mMaximumFlingVelocity = 8000;
/*     */   
/*     */   public static final double DEG2RAD = 0.017453292519943295D;
/*     */   
/*     */   public static final float FDEG2RAD = 0.017453292F;
/*  44 */   public static final double DOUBLE_EPSILON = Double.longBitsToDouble(1L);
/*     */ 
/*     */   
/*  47 */   public static final float FLOAT_EPSILON = Float.intBitsToFloat(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init(Context context) {
/*  57 */     if (context == null) {
/*     */       
/*  59 */       mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
/*     */       
/*  61 */       mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
/*     */       
/*  63 */       Log.e("MPChartLib-Utils", "Utils.init(...) PROVIDED CONTEXT OBJECT IS NULL");
/*     */     }
/*     */     else {
/*     */       
/*  67 */       ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
/*  68 */       mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
/*  69 */       mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
/*     */       
/*  71 */       Resources res = context.getResources();
/*  72 */       mMetrics = res.getDisplayMetrics();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void init(Resources res) {
/*  85 */     mMetrics = res.getDisplayMetrics();
/*     */ 
/*     */     
/*  88 */     mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
/*     */     
/*  90 */     mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
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
/*     */ 
/*     */   
/*     */   public static float convertDpToPixel(float dp) {
/* 104 */     if (mMetrics == null) {
/*     */       
/* 106 */       Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertDpToPixel(...). Otherwise conversion does not take place.");
/*     */ 
/*     */ 
/*     */       
/* 110 */       return dp;
/*     */     } 
/*     */     
/* 113 */     return dp * mMetrics.density;
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
/*     */   public static float convertPixelsToDp(float px) {
/* 125 */     if (mMetrics == null) {
/*     */       
/* 127 */       Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertPixelsToDp(...). Otherwise conversion does not take place.");
/*     */ 
/*     */ 
/*     */       
/* 131 */       return px;
/*     */     } 
/*     */     
/* 134 */     return px / mMetrics.density;
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
/*     */   
/* 146 */   public static int calcTextWidth(Paint paint, String demoText) { return (int)paint.measureText(demoText); }
/*     */ 
/*     */   
/* 149 */   private static Rect mCalcTextHeightRect = new Rect();
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
/*     */   public static int calcTextHeight(Paint paint, String demoText) {
/* 161 */     Rect r = mCalcTextHeightRect;
/* 162 */     r.set(0, 0, 0, 0);
/* 163 */     paint.getTextBounds(demoText, 0, demoText.length(), r);
/* 164 */     return r.height();
/*     */   }
/*     */   
/* 167 */   private static Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();
/*     */ 
/*     */   
/* 170 */   public static float getLineHeight(Paint paint) { return getLineHeight(paint, mFontMetrics); }
/*     */ 
/*     */   
/*     */   public static float getLineHeight(Paint paint, Paint.FontMetrics fontMetrics) {
/* 174 */     paint.getFontMetrics(fontMetrics);
/* 175 */     return fontMetrics.descent - fontMetrics.ascent;
/*     */   }
/*     */ 
/*     */   
/* 179 */   public static float getLineSpacing(Paint paint) { return getLineSpacing(paint, mFontMetrics); }
/*     */ 
/*     */   
/*     */   public static float getLineSpacing(Paint paint, Paint.FontMetrics fontMetrics) {
/* 183 */     paint.getFontMetrics(fontMetrics);
/* 184 */     return fontMetrics.ascent - fontMetrics.top + fontMetrics.bottom;
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
/*     */ 
/*     */   
/*     */   public static FSize calcTextSize(Paint paint, String demoText) {
/* 198 */     FSize result = FSize.getInstance(0.0F, 0.0F);
/* 199 */     calcTextSize(paint, demoText, result);
/* 200 */     return result;
/*     */   }
/*     */   
/* 203 */   private static Rect mCalcTextSizeRect = new Rect();
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
/*     */   public static void calcTextSize(Paint paint, String demoText, FSize outputFSize) {
/* 215 */     Rect r = mCalcTextSizeRect;
/* 216 */     r.set(0, 0, 0, 0);
/* 217 */     paint.getTextBounds(demoText, 0, demoText.length(), r);
/* 218 */     outputFSize.width = r.width();
/* 219 */     outputFSize.height = r.height();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   private static final int[] POW_10 = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };
/*     */ 
/*     */ 
/*     */   
/* 232 */   private static IValueFormatter mDefaultValueFormatter = generateDefaultValueFormatter();
/*     */ 
/*     */   
/* 235 */   private static IValueFormatter generateDefaultValueFormatter() { return new DefaultValueFormatter(1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public static IValueFormatter getDefaultValueFormatter() { return mDefaultValueFormatter; }
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
/* 255 */   public static String formatNumber(float number, int digitCount, boolean separateThousands) { return formatNumber(number, digitCount, separateThousands, '.'); }
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
/*     */   public static String formatNumber(float number, int digitCount, boolean separateThousands, char separateChar) {
/* 271 */     char[] out = new char[35];
/*     */     
/* 273 */     boolean neg = false;
/* 274 */     if (number == 0.0F) {
/* 275 */       return "0";
/*     */     }
/*     */     
/* 278 */     boolean zero = false;
/* 279 */     if (number < 1.0F && number > -1.0F) {
/* 280 */       zero = true;
/*     */     }
/*     */     
/* 283 */     if (number < 0.0F) {
/* 284 */       neg = true;
/* 285 */       number = -number;
/*     */     } 
/*     */     
/* 288 */     if (digitCount > POW_10.length) {
/* 289 */       digitCount = POW_10.length - 1;
/*     */     }
/*     */     
/* 292 */     number *= POW_10[digitCount];
/* 293 */     long lval = Math.round(number);
/* 294 */     int ind = out.length - 1;
/* 295 */     int charCount = 0;
/* 296 */     boolean decimalPointAdded = false;
/*     */     
/* 298 */     while (lval != 0L || charCount < digitCount + 1) {
/* 299 */       int digit = (int)(lval % 10L);
/* 300 */       lval /= 10L;
/* 301 */       out[ind--] = (char)(digit + 48);
/* 302 */       charCount++;
/*     */ 
/*     */       
/* 305 */       if (charCount == digitCount) {
/* 306 */         out[ind--] = ',';
/* 307 */         charCount++;
/* 308 */         decimalPointAdded = true;
/*     */         continue;
/*     */       } 
/* 311 */       if (separateThousands && lval != 0L && charCount > digitCount) {
/*     */         
/* 313 */         if (decimalPointAdded) {
/*     */           
/* 315 */           if ((charCount - digitCount) % 4 == 0) {
/* 316 */             out[ind--] = separateChar;
/* 317 */             charCount++;
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 322 */         if ((charCount - digitCount) % 4 == 3) {
/* 323 */           out[ind--] = separateChar;
/* 324 */           charCount++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 331 */     if (zero) {
/* 332 */       out[ind--] = '0';
/* 333 */       charCount++;
/*     */     } 
/*     */ 
/*     */     
/* 337 */     if (neg) {
/* 338 */       out[ind--] = '-';
/* 339 */       charCount++;
/*     */     } 
/*     */     
/* 342 */     int start = out.length - charCount;
/*     */ 
/*     */     
/* 345 */     return String.valueOf(out, start, out.length - start);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float roundToNextSignificant(double number) {
/* 355 */     if (Double.isInfinite(number) || 
/* 356 */       Double.isNaN(number) || number == 0.0D)
/*     */     {
/* 358 */       return 0.0F;
/*     */     }
/* 360 */     float d = (float)Math.ceil((float)Math.log10((number < 0.0D) ? -number : number));
/* 361 */     int pw = 1 - (int)d;
/* 362 */     float magnitude = (float)Math.pow(10.0D, pw);
/* 363 */     long shifted = Math.round(number * magnitude);
/* 364 */     return (float)shifted / magnitude;
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
/*     */   public static int getDecimals(float number) {
/* 376 */     float i = roundToNextSignificant(number);
/*     */     
/* 378 */     if (Float.isInfinite(i)) {
/* 379 */       return 0;
/*     */     }
/* 381 */     return (int)Math.ceil(-Math.log10(i)) + 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] convertIntegers(List<Integer> integers) {
/* 392 */     int[] ret = new int[integers.size()];
/*     */     
/* 394 */     copyIntegers(integers, ret);
/*     */     
/* 396 */     return ret;
/*     */   }
/*     */   
/*     */   public static void copyIntegers(List<Integer> from, int[] to) {
/* 400 */     int count = (to.length < from.size()) ? to.length : from.size();
/* 401 */     for (int i = 0; i < count; i++) {
/* 402 */       to[i] = ((Integer)from.get(i)).intValue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] convertStrings(List<String> strings) {
/* 414 */     String[] ret = new String[strings.size()];
/*     */     
/* 416 */     for (int i = 0; i < ret.length; i++) {
/* 417 */       ret[i] = (String)strings.get(i);
/*     */     }
/*     */     
/* 420 */     return ret;
/*     */   }
/*     */   
/*     */   public static void copyStrings(List<String> from, String[] to) {
/* 424 */     int count = (to.length < from.size()) ? to.length : from.size();
/* 425 */     for (int i = 0; i < count; i++) {
/* 426 */       to[i] = (String)from.get(i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double nextUp(double d) {
/* 438 */     if (d == Double.POSITIVE_INFINITY) {
/* 439 */       return d;
/*     */     }
/* 441 */     d += 0.0D;
/* 442 */     return Double.longBitsToDouble(Double.doubleToRawLongBits(d) + ((d >= 0.0D) ? 1L : -1L));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MPPointF getPosition(MPPointF center, float dist, float angle) {
/* 459 */     MPPointF p = MPPointF.getInstance(0.0F, 0.0F);
/* 460 */     getPosition(center, dist, angle, p);
/* 461 */     return p;
/*     */   }
/*     */   
/*     */   public static void getPosition(MPPointF center, float dist, float angle, MPPointF outputPoint) {
/* 465 */     outputPoint.x = (float)(center.x + dist * Math.cos(Math.toRadians(angle)));
/* 466 */     outputPoint.y = (float)(center.y + dist * Math.sin(Math.toRadians(angle)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void velocityTrackerPointerUpCleanUpIfNecessary(MotionEvent ev, VelocityTracker tracker) {
/* 474 */     tracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
/* 475 */     int upIndex = ev.getActionIndex();
/* 476 */     int id1 = ev.getPointerId(upIndex);
/* 477 */     float x1 = tracker.getXVelocity(id1);
/* 478 */     float y1 = tracker.getYVelocity(id1);
/* 479 */     for (int i = 0, count = ev.getPointerCount(); i < count; i++) {
/* 480 */       if (i != upIndex) {
/*     */ 
/*     */         
/* 483 */         int id2 = ev.getPointerId(i);
/* 484 */         float x = x1 * tracker.getXVelocity(id2);
/* 485 */         float y = y1 * tracker.getYVelocity(id2);
/*     */         
/* 487 */         float dot = x + y;
/* 488 */         if (dot < 0.0F) {
/* 489 */           tracker.clear();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   public static void postInvalidateOnAnimation(View view) {
/* 503 */     if (Build.VERSION.SDK_INT >= 16) {
/* 504 */       view.postInvalidateOnAnimation();
/*     */     } else {
/* 506 */       view.postInvalidateDelayed(10L);
/*     */     } 
/*     */   }
/*     */   
/* 510 */   public static int getMinimumFlingVelocity() { return mMinimumFlingVelocity; }
/*     */ 
/*     */ 
/*     */   
/* 514 */   public static int getMaximumFlingVelocity() { return mMaximumFlingVelocity; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getNormalizedAngle(float angle) {
/* 521 */     while (angle < 0.0F) {
/* 522 */       angle += 360.0F;
/*     */     }
/* 524 */     return angle % 360.0F;
/*     */   }
/*     */   
/* 527 */   private static Rect mDrawableBoundsCache = new Rect();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawImage(Canvas canvas, Drawable drawable, int x, int y, int width, int height) {
/* 534 */     MPPointF drawOffset = MPPointF.getInstance();
/* 535 */     drawOffset.x = (x - width / 2);
/* 536 */     drawOffset.y = (y - height / 2);
/*     */     
/* 538 */     drawable.copyBounds(mDrawableBoundsCache);
/* 539 */     drawable.setBounds(mDrawableBoundsCache.left, mDrawableBoundsCache.top, mDrawableBoundsCache.left + width, mDrawableBoundsCache.top + width);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 545 */     int saveId = canvas.save();
/*     */     
/* 547 */     canvas.translate(drawOffset.x, drawOffset.y);
/* 548 */     drawable.draw(canvas);
/* 549 */     canvas.restoreToCount(saveId);
/*     */   }
/*     */   
/* 552 */   private static Rect mDrawTextRectBuffer = new Rect();
/* 553 */   private static Paint.FontMetrics mFontMetricsBuffer = new Paint.FontMetrics();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawXAxisValue(Canvas c, String text, float x, float y, Paint paint, MPPointF anchor, float angleDegrees) {
/* 559 */     float drawOffsetX = 0.0F;
/* 560 */     float drawOffsetY = 0.0F;
/*     */     
/* 562 */     float lineHeight = paint.getFontMetrics(mFontMetricsBuffer);
/* 563 */     paint.getTextBounds(text, 0, text.length(), mDrawTextRectBuffer);
/*     */ 
/*     */     
/* 566 */     drawOffsetX -= mDrawTextRectBuffer.left;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 571 */     drawOffsetY += -mFontMetricsBuffer.ascent;
/*     */ 
/*     */     
/* 574 */     Paint.Align originalTextAlign = paint.getTextAlign();
/* 575 */     paint.setTextAlign(Paint.Align.LEFT);
/*     */     
/* 577 */     if (angleDegrees != 0.0F) {
/*     */ 
/*     */       
/* 580 */       drawOffsetX -= mDrawTextRectBuffer.width() * 0.5F;
/* 581 */       drawOffsetY -= lineHeight * 0.5F;
/*     */       
/* 583 */       float translateX = x;
/* 584 */       float translateY = y;
/*     */ 
/*     */       
/* 587 */       if (anchor.x != 0.5F || anchor.y != 0.5F) {
/* 588 */         FSize rotatedSize = getSizeOfRotatedRectangleByDegrees(mDrawTextRectBuffer
/* 589 */             .width(), lineHeight, angleDegrees);
/*     */ 
/*     */ 
/*     */         
/* 593 */         translateX -= rotatedSize.width * (anchor.x - 0.5F);
/* 594 */         translateY -= rotatedSize.height * (anchor.y - 0.5F);
/* 595 */         FSize.recycleInstance(rotatedSize);
/*     */       } 
/*     */       
/* 598 */       c.save();
/* 599 */       c.translate(translateX, translateY);
/* 600 */       c.rotate(angleDegrees);
/*     */       
/* 602 */       c.drawText(text, drawOffsetX, drawOffsetY, paint);
/*     */       
/* 604 */       c.restore();
/*     */     } else {
/* 606 */       if (anchor.x != 0.0F || anchor.y != 0.0F) {
/*     */         
/* 608 */         drawOffsetX -= mDrawTextRectBuffer.width() * anchor.x;
/* 609 */         drawOffsetY -= lineHeight * anchor.y;
/*     */       } 
/*     */       
/* 612 */       drawOffsetX += x;
/* 613 */       drawOffsetY += y;
/*     */       
/* 615 */       c.drawText(text, drawOffsetX, drawOffsetY, paint);
/*     */     } 
/*     */     
/* 618 */     paint.setTextAlign(originalTextAlign);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawXAxisValue(Canvas c, String text, float x, float y, Paint paint, MPPointF anchor, float angleDegrees, int color) {
/* 626 */     float drawOffsetX = 0.0F;
/* 627 */     float drawOffsetY = 0.0F;
/*     */     
/* 629 */     float lineHeight = paint.getFontMetrics(mFontMetricsBuffer);
/* 630 */     paint.getTextBounds(text, 0, text.length(), mDrawTextRectBuffer);
/*     */ 
/*     */     
/* 633 */     drawOffsetX -= mDrawTextRectBuffer.left;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 638 */     drawOffsetY += -mFontMetricsBuffer.ascent;
/*     */ 
/*     */     
/* 641 */     Paint.Align originalTextAlign = paint.getTextAlign();
/* 642 */     paint.setTextAlign(Paint.Align.LEFT);
/*     */     
/* 644 */     if (angleDegrees != 0.0F) {
/*     */ 
/*     */       
/* 647 */       drawOffsetX -= mDrawTextRectBuffer.width() * 0.5F;
/* 648 */       drawOffsetY -= lineHeight * 0.5F;
/*     */       
/* 650 */       float translateX = x;
/* 651 */       float translateY = y;
/*     */ 
/*     */       
/* 654 */       if (anchor.x != 0.5F || anchor.y != 0.5F) {
/* 655 */         FSize rotatedSize = getSizeOfRotatedRectangleByDegrees(mDrawTextRectBuffer
/* 656 */             .width(), lineHeight, angleDegrees);
/*     */ 
/*     */ 
/*     */         
/* 660 */         translateX -= rotatedSize.width * (anchor.x - 0.5F);
/* 661 */         translateY -= rotatedSize.height * (anchor.y - 0.5F);
/* 662 */         FSize.recycleInstance(rotatedSize);
/*     */       } 
/*     */       
/* 665 */       c.save();
/* 666 */       c.translate(translateX, translateY);
/* 667 */       c.rotate(angleDegrees);
/* 668 */       paint.setColor(color);
/* 669 */       c.drawText(text, drawOffsetX, drawOffsetY, paint);
/*     */       
/* 671 */       c.restore();
/*     */     } else {
/* 673 */       if (anchor.x != 0.0F || anchor.y != 0.0F) {
/*     */         
/* 675 */         drawOffsetX -= mDrawTextRectBuffer.width() * anchor.x;
/* 676 */         drawOffsetY -= lineHeight * anchor.y;
/*     */       } 
/*     */       
/* 679 */       drawOffsetX += x;
/* 680 */       drawOffsetY += y;
/* 681 */       paint.setColor(color);
/* 682 */       c.drawText(text, drawOffsetX, drawOffsetY, paint);
/*     */     } 
/*     */     
/* 685 */     paint.setTextAlign(originalTextAlign);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawMultilineText(Canvas c, StaticLayout textLayout, float x, float y, TextPaint paint, MPPointF anchor, float angleDegrees) {
/* 694 */     float drawOffsetX = 0.0F;
/* 695 */     float drawOffsetY = 0.0F;
/*     */ 
/*     */ 
/*     */     
/* 699 */     float lineHeight = paint.getFontMetrics(mFontMetricsBuffer);
/*     */     
/* 701 */     float drawWidth = textLayout.getWidth();
/* 702 */     float drawHeight = textLayout.getLineCount() * lineHeight;
/*     */ 
/*     */     
/* 705 */     drawOffsetX -= mDrawTextRectBuffer.left;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 710 */     drawOffsetY += drawHeight;
/*     */ 
/*     */     
/* 713 */     Paint.Align originalTextAlign = paint.getTextAlign();
/* 714 */     paint.setTextAlign(Paint.Align.LEFT);
/*     */     
/* 716 */     if (angleDegrees != 0.0F) {
/*     */ 
/*     */       
/* 719 */       drawOffsetX -= drawWidth * 0.5F;
/* 720 */       drawOffsetY -= drawHeight * 0.5F;
/*     */       
/* 722 */       float translateX = x;
/* 723 */       float translateY = y;
/*     */ 
/*     */       
/* 726 */       if (anchor.x != 0.5F || anchor.y != 0.5F) {
/* 727 */         FSize rotatedSize = getSizeOfRotatedRectangleByDegrees(drawWidth, drawHeight, angleDegrees);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 732 */         translateX -= rotatedSize.width * (anchor.x - 0.5F);
/* 733 */         translateY -= rotatedSize.height * (anchor.y - 0.5F);
/* 734 */         FSize.recycleInstance(rotatedSize);
/*     */       } 
/*     */       
/* 737 */       c.save();
/* 738 */       c.translate(translateX, translateY);
/* 739 */       c.rotate(angleDegrees);
/*     */       
/* 741 */       c.translate(drawOffsetX, drawOffsetY);
/* 742 */       textLayout.draw(c);
/*     */       
/* 744 */       c.restore();
/*     */     } else {
/* 746 */       if (anchor.x != 0.0F || anchor.y != 0.0F) {
/*     */         
/* 748 */         drawOffsetX -= drawWidth * anchor.x;
/* 749 */         drawOffsetY -= drawHeight * anchor.y;
/*     */       } 
/*     */       
/* 752 */       drawOffsetX += x;
/* 753 */       drawOffsetY += y;
/*     */       
/* 755 */       c.save();
/*     */       
/* 757 */       c.translate(drawOffsetX, drawOffsetY);
/* 758 */       textLayout.draw(c);
/*     */       
/* 760 */       c.restore();
/*     */     } 
/*     */     
/* 763 */     paint.setTextAlign(originalTextAlign);
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
/*     */   public static void drawMultilineText(Canvas c, String text, float x, float y, TextPaint paint, FSize constrainedToSize, MPPointF anchor, float angleDegrees) {
/* 775 */     StaticLayout textLayout = new StaticLayout(text, 0, text.length(), paint, (int)Math.max(Math.ceil(constrainedToSize.width), 1.0D), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
/*     */ 
/*     */ 
/*     */     
/* 779 */     drawMultilineText(c, textLayout, x, y, paint, anchor, angleDegrees);
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
/*     */   public static FSize getSizeOfRotatedRectangleByDegrees(FSize rectangleSize, float degrees) {
/* 791 */     float radians = degrees * 0.017453292F;
/* 792 */     return getSizeOfRotatedRectangleByRadians(rectangleSize.width, rectangleSize.height, radians);
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
/*     */ 
/*     */   
/* 805 */   public static FSize getSizeOfRotatedRectangleByRadians(FSize rectangleSize, float radians) { return getSizeOfRotatedRectangleByRadians(rectangleSize.width, rectangleSize.height, radians); }
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
/*     */   public static FSize getSizeOfRotatedRectangleByDegrees(float rectangleWidth, float rectangleHeight, float degrees) {
/* 820 */     float radians = degrees * 0.017453292F;
/* 821 */     return getSizeOfRotatedRectangleByRadians(rectangleWidth, rectangleHeight, radians);
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
/*     */ 
/*     */   
/*     */   public static FSize getSizeOfRotatedRectangleByRadians(float rectangleWidth, float rectangleHeight, float radians) {
/* 835 */     return FSize.getInstance(
/* 836 */         Math.abs(rectangleWidth * (float)Math.cos(radians)) + Math.abs(rectangleHeight * 
/* 837 */           (float)Math.sin(radians)), 
/* 838 */         Math.abs(rectangleWidth * (float)Math.sin(radians)) + Math.abs(rectangleHeight * 
/* 839 */           (float)Math.cos(radians)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 844 */   public static int getSDKInt() { return Build.VERSION.SDK_INT; }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\Utils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */