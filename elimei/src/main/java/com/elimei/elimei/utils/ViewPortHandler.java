/*     */ package com.elimei.elimei.utils;
/*     */ 
/*     */ import android.graphics.Matrix;
/*     */ import android.graphics.RectF;
/*     */ import android.view.View;
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
/*     */ public class ViewPortHandler
/*     */ {
/*  19 */   protected final Matrix mMatrixTouch = new Matrix();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  24 */   protected RectF mContentRect = new RectF();
/*     */   
/*  26 */   protected float mChartWidth = 0.0F;
/*  27 */   protected float mChartHeight = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   private float mMinScaleY = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   private float mMaxScaleY = Float.MAX_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private float mMinScaleX = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   private float mMaxScaleX = Float.MAX_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   private float mScaleX = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   private float mScaleY = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   private float mTransX = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private float mTransY = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   private float mTransOffsetX = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private float mTransOffsetY = 0.0F;
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
/*     */   public void setChartDimens(float width, float height) {
/*  95 */     float offsetLeft = offsetLeft();
/*  96 */     float offsetTop = offsetTop();
/*  97 */     float offsetRight = offsetRight();
/*  98 */     float offsetBottom = offsetBottom();
/*     */     
/* 100 */     this.mChartHeight = height;
/* 101 */     this.mChartWidth = width;
/*     */     
/* 103 */     restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
/*     */   }
/*     */   
/*     */   public boolean hasChartDimens() {
/* 107 */     if (this.mChartHeight > 0.0F && this.mChartWidth > 0.0F) {
/* 108 */       return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void restrainViewPort(float offsetLeft, float offsetTop, float offsetRight, float offsetBottom) { this.mContentRect.set(offsetLeft, offsetTop, this.mChartWidth - offsetRight, this.mChartHeight - offsetBottom); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public float offsetLeft() { return this.mContentRect.left; }
/*     */ 
/*     */ 
/*     */   
/* 124 */   public float offsetRight() { return this.mChartWidth - this.mContentRect.right; }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public float offsetTop() { return this.mContentRect.top; }
/*     */ 
/*     */ 
/*     */   
/* 132 */   public float offsetBottom() { return this.mChartHeight - this.mContentRect.bottom; }
/*     */ 
/*     */ 
/*     */   
/* 136 */   public float contentTop() { return this.mContentRect.top; }
/*     */ 
/*     */ 
/*     */   
/* 140 */   public float contentLeft() { return this.mContentRect.left; }
/*     */ 
/*     */ 
/*     */   
/* 144 */   public float contentRight() { return this.mContentRect.right; }
/*     */ 
/*     */ 
/*     */   
/* 148 */   public float contentBottom() { return this.mContentRect.bottom; }
/*     */ 
/*     */ 
/*     */   
/* 152 */   public float contentWidth() { return this.mContentRect.width(); }
/*     */ 
/*     */ 
/*     */   
/* 156 */   public float contentHeight() { return this.mContentRect.height(); }
/*     */ 
/*     */ 
/*     */   
/* 160 */   public RectF getContentRect() { return this.mContentRect; }
/*     */ 
/*     */ 
/*     */   
/* 164 */   public MPPointF getContentCenter() { return MPPointF.getInstance(this.mContentRect.centerX(), this.mContentRect.centerY()); }
/*     */ 
/*     */ 
/*     */   
/* 168 */   public float getChartHeight() { return this.mChartHeight; }
/*     */ 
/*     */ 
/*     */   
/* 172 */   public float getChartWidth() { return this.mChartWidth; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public float getSmallestContentExtension() { return Math.min(this.mContentRect.width(), this.mContentRect.height()); }
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
/*     */   public Matrix zoomIn(float x, float y) {
/* 198 */     Matrix save = new Matrix();
/* 199 */     zoomIn(x, y, save);
/* 200 */     return save;
/*     */   }
/*     */   
/*     */   public void zoomIn(float x, float y, Matrix outputMatrix) {
/* 204 */     outputMatrix.reset();
/* 205 */     outputMatrix.set(this.mMatrixTouch);
/* 206 */     outputMatrix.postScale(1.4F, 1.4F, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix zoomOut(float x, float y) {
/* 215 */     Matrix save = new Matrix();
/* 216 */     zoomOut(x, y, save);
/* 217 */     return save;
/*     */   }
/*     */   
/*     */   public void zoomOut(float x, float y, Matrix outputMatrix) {
/* 221 */     outputMatrix.reset();
/* 222 */     outputMatrix.set(this.mMatrixTouch);
/* 223 */     outputMatrix.postScale(0.7F, 0.7F, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetZoom(Matrix outputMatrix) {
/* 231 */     outputMatrix.reset();
/* 232 */     outputMatrix.set(this.mMatrixTouch);
/* 233 */     outputMatrix.postScale(1.0F, 1.0F, 0.0F, 0.0F);
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
/*     */   public Matrix zoom(float scaleX, float scaleY) {
/* 245 */     Matrix save = new Matrix();
/* 246 */     zoom(scaleX, scaleY, save);
/* 247 */     return save;
/*     */   }
/*     */   
/*     */   public void zoom(float scaleX, float scaleY, Matrix outputMatrix) {
/* 251 */     outputMatrix.reset();
/* 252 */     outputMatrix.set(this.mMatrixTouch);
/* 253 */     outputMatrix.postScale(scaleX, scaleY);
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
/*     */   public Matrix zoom(float scaleX, float scaleY, float x, float y) {
/* 267 */     Matrix save = new Matrix();
/* 268 */     zoom(scaleX, scaleY, x, y, save);
/* 269 */     return save;
/*     */   }
/*     */   
/*     */   public void zoom(float scaleX, float scaleY, float x, float y, Matrix outputMatrix) {
/* 273 */     outputMatrix.reset();
/* 274 */     outputMatrix.set(this.mMatrixTouch);
/* 275 */     outputMatrix.postScale(scaleX, scaleY, x, y);
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
/*     */   public Matrix setZoom(float scaleX, float scaleY) {
/* 287 */     Matrix save = new Matrix();
/* 288 */     setZoom(scaleX, scaleY, save);
/* 289 */     return save;
/*     */   }
/*     */   
/*     */   public void setZoom(float scaleX, float scaleY, Matrix outputMatrix) {
/* 293 */     outputMatrix.reset();
/* 294 */     outputMatrix.set(this.mMatrixTouch);
/* 295 */     outputMatrix.setScale(scaleX, scaleY);
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
/*     */   public Matrix setZoom(float scaleX, float scaleY, float x, float y) {
/* 309 */     Matrix save = new Matrix();
/* 310 */     save.set(this.mMatrixTouch);
/*     */     
/* 312 */     save.setScale(scaleX, scaleY, x, y);
/*     */     
/* 314 */     return save;
/*     */   }
/*     */   
/* 317 */   protected float[] valsBufferForFitScreen = new float[9];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix fitScreen() {
/* 325 */     Matrix save = new Matrix();
/* 326 */     fitScreen(save);
/* 327 */     return save;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fitScreen(Matrix outputMatrix) {
/* 335 */     this.mMinScaleX = 1.0F;
/* 336 */     this.mMinScaleY = 1.0F;
/*     */     
/* 338 */     outputMatrix.set(this.mMatrixTouch);
/*     */     
/* 340 */     float[] vals = this.valsBufferForFitScreen;
/* 341 */     for (int i = 0; i < 9; i++) {
/* 342 */       vals[i] = 0.0F;
/*     */     }
/*     */     
/* 345 */     outputMatrix.getValues(vals);
/*     */ 
/*     */     
/* 348 */     vals[2] = 0.0F;
/* 349 */     vals[5] = 0.0F;
/* 350 */     vals[0] = 1.0F;
/* 351 */     vals[4] = 1.0F;
/*     */     
/* 353 */     outputMatrix.setValues(vals);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix translate(float[] transformedPts) {
/* 364 */     Matrix save = new Matrix();
/* 365 */     translate(transformedPts, save);
/* 366 */     return save;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(float[] transformedPts, Matrix outputMatrix) {
/* 376 */     outputMatrix.reset();
/* 377 */     outputMatrix.set(this.mMatrixTouch);
/* 378 */     float x = transformedPts[0] - offsetLeft();
/* 379 */     float y = transformedPts[1] - offsetTop();
/* 380 */     outputMatrix.postTranslate(-x, -y);
/*     */   }
/*     */   
/* 383 */   protected Matrix mCenterViewPortMatrixBuffer = new Matrix();
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
/*     */   public void centerViewPort(float[] transformedPts, View view) {
/* 397 */     Matrix save = this.mCenterViewPortMatrixBuffer;
/* 398 */     save.reset();
/* 399 */     save.set(this.mMatrixTouch);
/*     */     
/* 401 */     float x = transformedPts[0] - offsetLeft();
/* 402 */     float y = transformedPts[1] - offsetTop();
/*     */     
/* 404 */     save.postTranslate(-x, -y);
/*     */     
/* 406 */     refresh(save, view, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   protected final float[] matrixBuffer = new float[9];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix refresh(Matrix newMatrix, View chart, boolean invalidate) {
/* 422 */     this.mMatrixTouch.set(newMatrix);
/*     */ 
/*     */     
/* 425 */     limitTransAndScale(this.mMatrixTouch, this.mContentRect);
/*     */     
/* 427 */     if (invalidate) {
/* 428 */       chart.invalidate();
/*     */     }
/* 430 */     newMatrix.set(this.mMatrixTouch);
/* 431 */     return newMatrix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void limitTransAndScale(Matrix matrix, RectF content) {
/* 441 */     matrix.getValues(this.matrixBuffer);
/*     */     
/* 443 */     float curTransX = this.matrixBuffer[2];
/* 444 */     float curScaleX = this.matrixBuffer[0];
/*     */     
/* 446 */     float curTransY = this.matrixBuffer[5];
/* 447 */     float curScaleY = this.matrixBuffer[4];
/*     */ 
/*     */     
/* 450 */     this.mScaleX = Math.min(Math.max(this.mMinScaleX, curScaleX), this.mMaxScaleX);
/*     */ 
/*     */     
/* 453 */     this.mScaleY = Math.min(Math.max(this.mMinScaleY, curScaleY), this.mMaxScaleY);
/*     */     
/* 455 */     float width = 0.0F;
/* 456 */     float height = 0.0F;
/*     */     
/* 458 */     if (content != null) {
/* 459 */       width = content.width();
/* 460 */       height = content.height();
/*     */     } 
/*     */     
/* 463 */     float maxTransX = -width * (this.mScaleX - 1.0F);
/* 464 */     this.mTransX = Math.min(Math.max(curTransX, maxTransX - this.mTransOffsetX), this.mTransOffsetX);
/*     */     
/* 466 */     float maxTransY = height * (this.mScaleY - 1.0F);
/* 467 */     this.mTransY = Math.max(Math.min(curTransY, maxTransY + this.mTransOffsetY), -this.mTransOffsetY);
/*     */     
/* 469 */     this.matrixBuffer[2] = this.mTransX;
/* 470 */     this.matrixBuffer[0] = this.mScaleX;
/*     */     
/* 472 */     this.matrixBuffer[5] = this.mTransY;
/* 473 */     this.matrixBuffer[4] = this.mScaleY;
/*     */     
/* 475 */     matrix.setValues(this.matrixBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinimumScaleX(float xScale) {
/* 485 */     if (xScale < 1.0F) {
/* 486 */       xScale = 1.0F;
/*     */     }
/* 488 */     this.mMinScaleX = xScale;
/*     */     
/* 490 */     limitTransAndScale(this.mMatrixTouch, this.mContentRect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaximumScaleX(float xScale) {
/* 500 */     if (xScale == 0.0F) {
/* 501 */       xScale = Float.MAX_VALUE;
/*     */     }
/* 503 */     this.mMaxScaleX = xScale;
/*     */     
/* 505 */     limitTransAndScale(this.mMatrixTouch, this.mContentRect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinMaxScaleX(float minScaleX, float maxScaleX) {
/* 516 */     if (minScaleX < 1.0F) {
/* 517 */       minScaleX = 1.0F;
/*     */     }
/* 519 */     if (maxScaleX == 0.0F) {
/* 520 */       maxScaleX = Float.MAX_VALUE;
/*     */     }
/* 522 */     this.mMinScaleX = minScaleX;
/* 523 */     this.mMaxScaleX = maxScaleX;
/*     */     
/* 525 */     limitTransAndScale(this.mMatrixTouch, this.mContentRect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinimumScaleY(float yScale) {
/* 535 */     if (yScale < 1.0F) {
/* 536 */       yScale = 1.0F;
/*     */     }
/* 538 */     this.mMinScaleY = yScale;
/*     */     
/* 540 */     limitTransAndScale(this.mMatrixTouch, this.mContentRect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaximumScaleY(float yScale) {
/* 550 */     if (yScale == 0.0F) {
/* 551 */       yScale = Float.MAX_VALUE;
/*     */     }
/* 553 */     this.mMaxScaleY = yScale;
/*     */     
/* 555 */     limitTransAndScale(this.mMatrixTouch, this.mContentRect);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinMaxScaleY(float minScaleY, float maxScaleY) {
/* 560 */     if (minScaleY < 1.0F) {
/* 561 */       minScaleY = 1.0F;
/*     */     }
/* 563 */     if (maxScaleY == 0.0F) {
/* 564 */       maxScaleY = Float.MAX_VALUE;
/*     */     }
/* 566 */     this.mMinScaleY = minScaleY;
/* 567 */     this.mMaxScaleY = maxScaleY;
/*     */     
/* 569 */     limitTransAndScale(this.mMatrixTouch, this.mContentRect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 578 */   public Matrix getMatrixTouch() { return this.mMatrixTouch; }
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
/* 589 */   public boolean isInBoundsX(float x) { return (isInBoundsLeft(x) && isInBoundsRight(x)); }
/*     */ 
/*     */ 
/*     */   
/* 593 */   public boolean isInBoundsY(float y) { return (isInBoundsTop(y) && isInBoundsBottom(y)); }
/*     */ 
/*     */ 
/*     */   
/* 597 */   public boolean isInBounds(float x, float y) { return (isInBoundsX(x) && isInBoundsY(y)); }
/*     */ 
/*     */ 
/*     */   
/* 601 */   public boolean isInBoundsLeft(float x) { return (this.mContentRect.left <= x + 1.0F); }
/*     */ 
/*     */   
/*     */   public boolean isInBoundsRight(float x) {
/* 605 */     x = (int)(x * 100.0F) / 100.0F;
/* 606 */     return (this.mContentRect.right >= x - 1.0F);
/*     */   }
/*     */ 
/*     */   
/* 610 */   public boolean isInBoundsTop(float y) { return (this.mContentRect.top <= y); }
/*     */ 
/*     */   
/*     */   public boolean isInBoundsBottom(float y) {
/* 614 */     y = (int)(y * 100.0F) / 100.0F;
/* 615 */     return (this.mContentRect.bottom >= y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 622 */   public float getScaleX() { return this.mScaleX; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 629 */   public float getScaleY() { return this.mScaleY; }
/*     */ 
/*     */ 
/*     */   
/* 633 */   public float getMinScaleX() { return this.mMinScaleX; }
/*     */ 
/*     */ 
/*     */   
/* 637 */   public float getMaxScaleX() { return this.mMaxScaleX; }
/*     */ 
/*     */ 
/*     */   
/* 641 */   public float getMinScaleY() { return this.mMinScaleY; }
/*     */ 
/*     */ 
/*     */   
/* 645 */   public float getMaxScaleY() { return this.mMaxScaleY; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 654 */   public float getTransX() { return this.mTransX; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 663 */   public float getTransY() { return this.mTransY; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 673 */   public boolean isFullyZoomedOut() { return (isFullyZoomedOutX() && isFullyZoomedOutY()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 682 */   public boolean isFullyZoomedOutY() { return (this.mScaleY <= this.mMinScaleY && this.mMinScaleY <= 1.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 692 */   public boolean isFullyZoomedOutX() { return (this.mScaleX <= this.mMinScaleX && this.mMinScaleX <= 1.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 702 */   public void setDragOffsetX(float offset) { this.mTransOffsetX = Utils.convertDpToPixel(offset); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 712 */   public void setDragOffsetY(float offset) { this.mTransOffsetY = Utils.convertDpToPixel(offset); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 721 */   public boolean hasNoDragOffset() { return (this.mTransOffsetX <= 0.0F && this.mTransOffsetY <= 0.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 730 */   public boolean canZoomOutMoreX() { return (this.mScaleX > this.mMinScaleX); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 739 */   public boolean canZoomInMoreX() { return (this.mScaleX < this.mMaxScaleX); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 748 */   public boolean canZoomOutMoreY() { return (this.mScaleY > this.mMinScaleY); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 757 */   public boolean canZoomInMoreY() { return (this.mScaleY < this.mMaxScaleY); }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\ViewPortHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */