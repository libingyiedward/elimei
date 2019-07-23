/*     */ package com.elimei.elimei.renderer;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import com.elimei.elimei.components.AxisBase;
/*     */ import com.elimei.elimei.utils.MPPointD;
/*     */ import com.elimei.elimei.utils.Transformer;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
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
/*     */ public abstract class AxisRenderer
/*     */   extends Renderer
/*     */ {
/*     */   protected AxisBase mAxis;
/*     */   protected Transformer mTrans;
/*     */   protected Paint mGridPaint;
/*     */   protected Paint mAxisLabelPaint;
/*     */   protected Paint mAxisLinePaint;
/*     */   protected Paint mLimitLinePaint;
/*     */   
/*     */   public AxisRenderer(ViewPortHandler viewPortHandler, Transformer trans, AxisBase axis) {
/*  49 */     super(viewPortHandler);
/*     */     
/*  51 */     this.mTrans = trans;
/*  52 */     this.mAxis = axis;
/*     */     
/*  54 */     if (this.mViewPortHandler != null) {
/*     */       
/*  56 */       this.mAxisLabelPaint = new Paint();
/*     */       
/*  58 */       this.mGridPaint = new Paint();
/*  59 */       this.mGridPaint.setColor(-7829368);
/*  60 */       this.mGridPaint.setStrokeWidth(1.0F);
/*  61 */       this.mGridPaint.setStyle(Paint.Style.STROKE);
/*  62 */       this.mGridPaint.setAlpha(90);
/*     */       
/*  64 */       this.mAxisLinePaint = new Paint();
/*  65 */       this.mAxisLinePaint.setColor(-16777216);
/*  66 */       this.mAxisLinePaint.setStrokeWidth(1.0F);
/*  67 */       this.mAxisLinePaint.setStyle(Paint.Style.STROKE);
/*     */       
/*  69 */       this.mLimitLinePaint = new Paint();
/*  70 */       this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public Paint getPaintAxisLabels() { return this.mAxisLabelPaint; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public Paint getPaintGrid() { return this.mGridPaint; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public Paint getPaintAxisLine() { return this.mAxisLinePaint; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public Transformer getTransformer() { return this.mTrans; }
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
/*     */   public void computeAxis(float min, float max, boolean inverted) {
/* 122 */     if (this.mViewPortHandler != null && this.mViewPortHandler.contentWidth() > 10.0F && !this.mViewPortHandler.isFullyZoomedOutY()) {
/*     */       
/* 124 */       MPPointD p1 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
/* 125 */       MPPointD p2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom());
/*     */       
/* 127 */       if (!inverted) {
/*     */         
/* 129 */         min = (float)p2.y;
/* 130 */         max = (float)p1.y;
/*     */       } else {
/*     */         
/* 133 */         min = (float)p1.y;
/* 134 */         max = (float)p2.y;
/*     */       } 
/*     */       
/* 137 */       MPPointD.recycleInstance(p1);
/* 138 */       MPPointD.recycleInstance(p2);
/*     */     } 
/*     */     
/* 141 */     computeAxisValues(min, max);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void computeAxisValues(float min, float max) {
/* 151 */     float yMin = min;
/* 152 */     float yMax = max;
/*     */     
/* 154 */     int labelCount = this.mAxis.getLabelCount();
/* 155 */     double range = Math.abs(yMax - yMin);
/*     */     
/* 157 */     if (labelCount == 0 || range <= 0.0D || Double.isInfinite(range)) {
/* 158 */       this.mAxis.mEntries = new float[0];
/* 159 */       this.mAxis.mCenteredEntries = new float[0];
/* 160 */       this.mAxis.mEntryCount = 0;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 165 */     double rawInterval = range / labelCount;
/* 166 */     double interval = Utils.roundToNextSignificant(rawInterval);
/*     */ 
/*     */ 
/*     */     
/* 170 */     if (this.mAxis.isGranularityEnabled()) {
/* 171 */       interval = (interval < this.mAxis.getGranularity()) ? this.mAxis.getGranularity() : interval;
/*     */     }
/*     */     
/* 174 */     double intervalMagnitude = Utils.roundToNextSignificant(Math.pow(10.0D, (int)Math.log10(interval)));
/* 175 */     int intervalSigDigit = (int)(interval / intervalMagnitude);
/* 176 */     if (intervalSigDigit > 5)
/*     */     {
/*     */       
/* 179 */       interval = Math.floor(10.0D * intervalMagnitude);
/*     */     }
/*     */     
/* 182 */     int n = this.mAxis.isCenterAxisLabelsEnabled() ? 1 : 0;
/*     */ 
/*     */     
/* 185 */     if (this.mAxis.isForceLabelsEnabled()) {
/*     */       
/* 187 */       interval = ((float)range / (labelCount - 1));
/* 188 */       this.mAxis.mEntryCount = labelCount;
/*     */       
/* 190 */       if (this.mAxis.mEntries.length < labelCount)
/*     */       {
/* 192 */         this.mAxis.mEntries = new float[labelCount];
/*     */       }
/*     */       
/* 195 */       float v = min;
/*     */       
/* 197 */       for (int i = 0; i < labelCount; i++) {
/* 198 */         this.mAxis.mEntries[i] = v;
/* 199 */         v = (float)(v + interval);
/*     */       } 
/*     */       
/* 202 */       n = labelCount;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 207 */       double first = (interval == 0.0D) ? 0.0D : (Math.ceil(yMin / interval) * interval);
/* 208 */       if (this.mAxis.isCenterAxisLabelsEnabled()) {
/* 209 */         first -= interval;
/*     */       }
/*     */       
/* 212 */       double last = (interval == 0.0D) ? 0.0D : Utils.nextUp(Math.floor(yMax / interval) * interval);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 217 */       if (interval != 0.0D) {
/* 218 */         double f; for (f = first; f <= last; f += interval) {
/* 219 */           n++;
/*     */         }
/*     */       } 
/*     */       
/* 223 */       this.mAxis.mEntryCount = n;
/*     */       
/* 225 */       if (this.mAxis.mEntries.length < n)
/*     */       {
/* 227 */         this.mAxis.mEntries = new float[n]; } 
/*     */       double f;
/*     */       int i;
/* 230 */       for (f = first, i = 0; i < n; f += interval, i++) {
/*     */         
/* 232 */         if (f == 0.0D) {
/* 233 */           f = 0.0D;
/*     */         }
/* 235 */         this.mAxis.mEntries[i] = (float)f;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 240 */     if (interval < 1.0D) {
/* 241 */       this.mAxis.mDecimals = (int)Math.ceil(-Math.log10(interval));
/*     */     } else {
/* 243 */       this.mAxis.mDecimals = 0;
/*     */     } 
/*     */     
/* 246 */     if (this.mAxis.isCenterAxisLabelsEnabled()) {
/*     */       
/* 248 */       if (this.mAxis.mCenteredEntries.length < n) {
/* 249 */         this.mAxis.mCenteredEntries = new float[n];
/*     */       }
/*     */       
/* 252 */       float offset = (float)interval / 2.0F;
/*     */       
/* 254 */       for (int i = 0; i < n; i++)
/* 255 */         this.mAxis.mCenteredEntries[i] = this.mAxis.mEntries[i] + offset; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract void renderAxisLabels(Canvas paramCanvas);
/*     */   
/*     */   public abstract void renderGridLines(Canvas paramCanvas);
/*     */   
/*     */   public abstract void renderAxisLine(Canvas paramCanvas);
/*     */   
/*     */   public abstract void renderLimitLines(Canvas paramCanvas);
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\AxisRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */