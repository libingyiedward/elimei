/*      */ package com.elimei.elimei.charts;
/*      */ 
/*      */ import android.annotation.SuppressLint;
/*      */ import android.annotation.TargetApi;
/*      */ import android.content.Context;
/*      */ import android.graphics.Canvas;
/*      */ import android.graphics.Color;
/*      */ import android.graphics.Matrix;
/*      */ import android.graphics.Paint;
/*      */ import android.graphics.RectF;
/*      */ import android.os.Build;
/*      */ import android.util.AttributeSet;
/*      */ import android.util.Log;
/*      */ import android.view.MotionEvent;
/*      */ import com.elimei.elimei.components.Legend;
/*      */ import com.elimei.elimei.components.XAxis;
/*      */ import com.elimei.elimei.components.YAxis;
/*      */ import com.elimei.elimei.data.BarLineScatterCandleBubbleData;
/*      */ import com.elimei.elimei.data.Entry;
/*      */ import com.elimei.elimei.highlight.ChartHighlighter;
/*      */ import com.elimei.elimei.highlight.Highlight;
/*      */ import com.elimei.elimei.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
/*      */ import com.elimei.elimei.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
/*      */ import com.elimei.elimei.jobs.AnimatedMoveViewJob;
/*      */ import com.elimei.elimei.jobs.AnimatedZoomJob;
/*      */ import com.elimei.elimei.jobs.MoveViewJob;
/*      */ import com.elimei.elimei.jobs.ZoomJob;
/*      */ import com.elimei.elimei.listener.BarLineChartTouchListener;
/*      */ import com.elimei.elimei.listener.OnDrawListener;
/*      */ import com.elimei.elimei.renderer.XAxisRenderer;
/*      */ import com.elimei.elimei.renderer.YAxisRenderer;
/*      */ import com.elimei.elimei.utils.MPPointD;
/*      */ import com.elimei.elimei.utils.MPPointF;
/*      */ import com.elimei.elimei.utils.Transformer;
/*      */ import com.elimei.elimei.utils.Utils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @SuppressLint({"RtlHardcoded"})
/*      */ public abstract class BarLineChartBase<T extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>
/*      */   extends Chart<T>
/*      */   implements BarLineScatterCandleBubbleDataProvider
/*      */ {
/*   53 */   protected int mMaxVisibleCount = 100;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean mAutoScaleMinMaxEnabled = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean mPinchZoomEnabled = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean mDoubleTapToZoomEnabled = true;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean mHighlightPerDragEnabled = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean mDragXEnabled = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean mDragYEnabled = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean mScaleXEnabled = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean mScaleYEnabled = true;
/*      */ 
/*      */ 
/*      */   
/*      */   protected Paint mGridBackgroundPaint;
/*      */ 
/*      */ 
/*      */   
/*      */   protected Paint mBorderPaint;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean mDrawGridBackground = false;
/*      */ 
/*      */   
/*      */   protected boolean mDrawBorders = false;
/*      */ 
/*      */   
/*      */   protected boolean mClipValuesToContent = false;
/*      */ 
/*      */   
/*  106 */   protected float mMinOffset = 15.0F;
/*      */   
/*      */   protected boolean mKeepPositionOnRotation = false;
/*      */   
/*      */   protected OnDrawListener mDrawListener;
/*      */   
/*      */   protected YAxis mAxisLeft;
/*      */   
/*      */   protected YAxis mAxisRight;
/*      */   
/*      */   protected YAxisRenderer mAxisRendererLeft;
/*      */   
/*      */   protected YAxisRenderer mAxisRendererRight;
/*      */   
/*      */   protected Transformer mLeftAxisTransformer;
/*      */   
/*      */   protected Transformer mRightAxisTransformer;
/*      */   
/*      */   protected XAxisRenderer mXAxisRenderer;
/*      */   
/*      */   private long totalTime;
/*      */   
/*      */   private long drawCycles;
/*      */   private RectF mOffsetsBuffer;
/*      */   protected Matrix mZoomMatrixBuffer;
/*      */   protected Matrix mFitScreenMatrixBuffer;
/*      */   private boolean mCustomViewPortEnabled;
/*      */   protected float[] mGetPositionBuffer;
/*      */   protected MPPointD posForGetLowestVisibleX;
/*      */   protected MPPointD posForGetHighestVisibleX;
/*      */   protected float[] mOnSizeChangedBuffer;
/*      */   
/*      */   public BarLineChartBase(Context context, AttributeSet attrs, int defStyle)
/*      */   {
/*  140 */     super(context, attrs, defStyle);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  183 */     this.totalTime = 0L;
/*  184 */     this.drawCycles = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  452 */     this.mOffsetsBuffer = new RectF();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  582 */     this.mZoomMatrixBuffer = new Matrix();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  719 */     this.mFitScreenMatrixBuffer = new Matrix();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  958 */     this.mCustomViewPortEnabled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1033 */     this.mGetPositionBuffer = new float[2];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1358 */     this.posForGetLowestVisibleX = MPPointD.getInstance(0.0D, 0.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1377 */     this.posForGetHighestVisibleX = MPPointD.getInstance(0.0D, 0.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1625 */     this.mOnSizeChangedBuffer = new float[2]; } public BarLineChartBase(Context context, AttributeSet attrs) { super(context, attrs); this.totalTime = 0L; this.drawCycles = 0L; this.mOffsetsBuffer = new RectF(); this.mZoomMatrixBuffer = new Matrix(); this.mFitScreenMatrixBuffer = new Matrix(); this.mCustomViewPortEnabled = false; this.mGetPositionBuffer = new float[2]; this.posForGetLowestVisibleX = MPPointD.getInstance(0.0D, 0.0D); this.posForGetHighestVisibleX = MPPointD.getInstance(0.0D, 0.0D); this.mOnSizeChangedBuffer = new float[2]; } public BarLineChartBase(Context context) { super(context); this.totalTime = 0L; this.drawCycles = 0L; this.mOffsetsBuffer = new RectF(); this.mZoomMatrixBuffer = new Matrix(); this.mFitScreenMatrixBuffer = new Matrix(); this.mCustomViewPortEnabled = false; this.mGetPositionBuffer = new float[2]; this.posForGetLowestVisibleX = MPPointD.getInstance(0.0D, 0.0D); this.posForGetHighestVisibleX = MPPointD.getInstance(0.0D, 0.0D); this.mOnSizeChangedBuffer = new float[2]; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/* 1631 */     this.mOnSizeChangedBuffer[1] = 0.0F; this.mOnSizeChangedBuffer[0] = 0.0F;
/*      */     
/* 1633 */     if (this.mKeepPositionOnRotation) {
/* 1634 */       this.mOnSizeChangedBuffer[0] = this.mViewPortHandler.contentLeft();
/* 1635 */       this.mOnSizeChangedBuffer[1] = this.mViewPortHandler.contentTop();
/* 1636 */       getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue(this.mOnSizeChangedBuffer);
/*      */     } 
/*      */ 
/*      */     
/* 1640 */     super.onSizeChanged(w, h, oldw, oldh);
/*      */     
/* 1642 */     if (this.mKeepPositionOnRotation) {
/*      */ 
/*      */       
/* 1645 */       getTransformer(YAxis.AxisDependency.LEFT).pointValuesToPixel(this.mOnSizeChangedBuffer);
/* 1646 */       this.mViewPortHandler.centerViewPort(this.mOnSizeChangedBuffer, this);
/*      */     } else {
/* 1648 */       this.mViewPortHandler.refresh(this.mViewPortHandler.getMatrixTouch(), this, true);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void init() {
/*      */     super.init();
/*      */     this.mAxisLeft = new YAxis(YAxis.AxisDependency.LEFT);
/*      */     this.mAxisRight = new YAxis(YAxis.AxisDependency.RIGHT);
/*      */     this.mLeftAxisTransformer = new Transformer(this.mViewPortHandler);
/*      */     this.mRightAxisTransformer = new Transformer(this.mViewPortHandler);
/*      */     this.mAxisRendererLeft = new YAxisRenderer(this.mViewPortHandler, this.mAxisLeft, this.mLeftAxisTransformer);
/*      */     this.mAxisRendererRight = new YAxisRenderer(this.mViewPortHandler, this.mAxisRight, this.mRightAxisTransformer);
/*      */     this.mXAxisRenderer = new XAxisRenderer(this.mViewPortHandler, this.mXAxis, this.mLeftAxisTransformer);
/*      */     setHighlighter(new ChartHighlighter(this));
/*      */     this.mChartTouchListener = new BarLineChartTouchListener(this, this.mViewPortHandler.getMatrixTouch(), 3.0F);
/*      */     this.mGridBackgroundPaint = new Paint();
/*      */     this.mGridBackgroundPaint.setStyle(Paint.Style.FILL);
/*      */     this.mGridBackgroundPaint.setColor(Color.rgb(240, 240, 240));
/*      */     this.mBorderPaint = new Paint();
/*      */     this.mBorderPaint.setStyle(Paint.Style.STROKE);
/*      */     this.mBorderPaint.setColor(-16777216);
/*      */     this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(1.0F));
/*      */   }
/*      */   
/*      */   protected void onDraw(Canvas canvas) {
/*      */     super.onDraw(canvas);
/*      */     if (this.mData == null)
/*      */       return; 
/*      */     long starttime = System.currentTimeMillis();
/*      */     drawGridBackground(canvas);
/*      */     if (this.mAutoScaleMinMaxEnabled)
/*      */       autoScale(); 
/*      */     if (this.mAxisLeft.isEnabled())
/*      */       this.mAxisRendererLeft.computeAxis(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisMaximum, this.mAxisLeft.isInverted()); 
/*      */     if (this.mAxisRight.isEnabled())
/*      */       this.mAxisRendererRight.computeAxis(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisMaximum, this.mAxisRight.isInverted()); 
/*      */     if (this.mXAxis.isEnabled())
/*      */       this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false); 
/*      */     this.mXAxisRenderer.renderAxisLine(canvas);
/*      */     this.mAxisRendererLeft.renderAxisLine(canvas);
/*      */     this.mAxisRendererRight.renderAxisLine(canvas);
/*      */     this.mXAxisRenderer.renderGridLines(canvas);
/*      */     this.mAxisRendererLeft.renderGridLines(canvas);
/*      */     this.mAxisRendererRight.renderGridLines(canvas);
/*      */     if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLimitLinesBehindDataEnabled())
/*      */       this.mXAxisRenderer.renderLimitLines(canvas); 
/*      */     if (this.mAxisLeft.isEnabled() && this.mAxisLeft.isDrawLimitLinesBehindDataEnabled())
/*      */       this.mAxisRendererLeft.renderLimitLines(canvas); 
/*      */     if (this.mAxisRight.isEnabled() && this.mAxisRight.isDrawLimitLinesBehindDataEnabled())
/*      */       this.mAxisRendererRight.renderLimitLines(canvas); 
/*      */     int clipRestoreCount = canvas.save();
/*      */     canvas.clipRect(this.mViewPortHandler.getContentRect());
/*      */     this.mRenderer.drawData(canvas);
/*      */     if (valuesToHighlight())
/*      */       this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight); 
/*      */     canvas.restoreToCount(clipRestoreCount);
/*      */     this.mRenderer.drawExtras(canvas);
/*      */     if (this.mXAxis.isEnabled() && !this.mXAxis.isDrawLimitLinesBehindDataEnabled())
/*      */       this.mXAxisRenderer.renderLimitLines(canvas); 
/*      */     if (this.mAxisLeft.isEnabled() && !this.mAxisLeft.isDrawLimitLinesBehindDataEnabled())
/*      */       this.mAxisRendererLeft.renderLimitLines(canvas); 
/*      */     if (this.mAxisRight.isEnabled() && !this.mAxisRight.isDrawLimitLinesBehindDataEnabled())
/*      */       this.mAxisRendererRight.renderLimitLines(canvas); 
/*      */     this.mXAxisRenderer.renderAxisLabels(canvas);
/*      */     this.mAxisRendererLeft.renderAxisLabels(canvas);
/*      */     this.mAxisRendererRight.renderAxisLabels(canvas);
/*      */     if (isClipValuesToContentEnabled()) {
/*      */       clipRestoreCount = canvas.save();
/*      */       canvas.clipRect(this.mViewPortHandler.getContentRect());
/*      */       this.mRenderer.drawValues(canvas);
/*      */       canvas.restoreToCount(clipRestoreCount);
/*      */     } else {
/*      */       this.mRenderer.drawValues(canvas);
/*      */     } 
/*      */     this.mLegendRenderer.renderLegend(canvas);
/*      */     drawDescription(canvas);
/*      */     drawMarkers(canvas);
/*      */     if (this.mLogEnabled) {
/*      */       long drawtime = System.currentTimeMillis() - starttime;
/*      */       this.totalTime += drawtime;
/*      */       this.drawCycles++;
/*      */       long average = this.totalTime / this.drawCycles;
/*      */       Log.i("MPAndroidChart", "Drawtime: " + drawtime + " ms, average: " + average + " ms, cycles: " + this.drawCycles);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void resetTracking() {
/*      */     this.totalTime = 0L;
/*      */     this.drawCycles = 0L;
/*      */   }
/*      */   
/*      */   protected void prepareValuePxMatrix() {
/*      */     if (this.mLogEnabled)
/*      */       Log.i("MPAndroidChart", "Preparing Value-Px Matrix, xmin: " + this.mXAxis.mAxisMinimum + ", xmax: " + this.mXAxis.mAxisMaximum + ", xdelta: " + this.mXAxis.mAxisRange); 
/*      */     this.mRightAxisTransformer.prepareMatrixValuePx(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisRange, this.mAxisRight.mAxisRange, this.mAxisRight.mAxisMinimum);
/*      */     this.mLeftAxisTransformer.prepareMatrixValuePx(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisRange, this.mAxisLeft.mAxisRange, this.mAxisLeft.mAxisMinimum);
/*      */   }
/*      */   
/*      */   protected void prepareOffsetMatrix() {
/*      */     this.mRightAxisTransformer.prepareMatrixOffset(this.mAxisRight.isInverted());
/*      */     this.mLeftAxisTransformer.prepareMatrixOffset(this.mAxisLeft.isInverted());
/*      */   }
/*      */   
/*      */   public void notifyDataSetChanged() {
/*      */     if (this.mData == null) {
/*      */       if (this.mLogEnabled)
/*      */         Log.i("MPAndroidChart", "Preparing... DATA NOT SET."); 
/*      */       return;
/*      */     } 
/*      */     if (this.mLogEnabled)
/*      */       Log.i("MPAndroidChart", "Preparing..."); 
/*      */     if (this.mRenderer != null)
/*      */       this.mRenderer.initBuffers(); 
/*      */     calcMinMax();
/*      */     this.mAxisRendererLeft.computeAxis(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisMaximum, this.mAxisLeft.isInverted());
/*      */     this.mAxisRendererRight.computeAxis(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisMaximum, this.mAxisRight.isInverted());
/*      */     this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
/*      */     if (this.mLegend != null)
/*      */       this.mLegendRenderer.computeLegend(this.mData); 
/*      */     calculateOffsets();
/*      */   }
/*      */   
/*      */   protected void autoScale() {
/*      */     float fromX = getLowestVisibleX();
/*      */     float toX = getHighestVisibleX();
/*      */     ((BarLineScatterCandleBubbleData)this.mData).calcMinMaxY(fromX, toX);
/*      */     this.mXAxis.calculate(((BarLineScatterCandleBubbleData)this.mData).getXMin(), ((BarLineScatterCandleBubbleData)this.mData).getXMax());
/*      */     if (this.mAxisLeft.isEnabled())
/*      */       this.mAxisLeft.calculate(((BarLineScatterCandleBubbleData)this.mData).getYMin(YAxis.AxisDependency.LEFT), ((BarLineScatterCandleBubbleData)this.mData).getYMax(YAxis.AxisDependency.LEFT)); 
/*      */     if (this.mAxisRight.isEnabled())
/*      */       this.mAxisRight.calculate(((BarLineScatterCandleBubbleData)this.mData).getYMin(YAxis.AxisDependency.RIGHT), ((BarLineScatterCandleBubbleData)this.mData).getYMax(YAxis.AxisDependency.RIGHT)); 
/*      */     calculateOffsets();
/*      */   }
/*      */   
/*      */   protected void calcMinMax() {
/*      */     this.mXAxis.calculate(((BarLineScatterCandleBubbleData)this.mData).getXMin(), ((BarLineScatterCandleBubbleData)this.mData).getXMax());
/*      */     this.mAxisLeft.calculate(((BarLineScatterCandleBubbleData)this.mData).getYMin(YAxis.AxisDependency.LEFT), ((BarLineScatterCandleBubbleData)this.mData).getYMax(YAxis.AxisDependency.LEFT));
/*      */     this.mAxisRight.calculate(((BarLineScatterCandleBubbleData)this.mData).getYMin(YAxis.AxisDependency.RIGHT), ((BarLineScatterCandleBubbleData)this.mData).getYMax(YAxis.AxisDependency.RIGHT));
/*      */   }
/*      */   
/*      */   protected void calculateLegendOffsets(RectF offsets) {
/*      */     offsets.left = 0.0F;
/*      */     offsets.right = 0.0F;
/*      */     offsets.top = 0.0F;
/*      */     offsets.bottom = 0.0F;
/*      */     if (this.mLegend != null && this.mLegend.isEnabled() && !this.mLegend.isDrawInsideEnabled())
/*      */       switch (this.mLegend.getOrientation()) {
///*      */         case VERTICAL:
///*      */           switch (this.mLegend.getHorizontalAlignment()) {
///*      */             case VERTICAL:
///*      */               offsets.left += Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + this.mLegend.getXOffset();
///*      */               break;
///*      */             case HORIZONTAL:
///*      */               offsets.right += Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + this.mLegend.getXOffset();
///*      */               break;
///*      */             case null:
///*      */             case null:
///*      */               break;
///*      */           }
///*      */           switch (this.mLegend.getVerticalAlignment()) {
///*      */             case VERTICAL:
///*      */               offsets.top += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
///*      */               break;
///*      */             case HORIZONTAL:
///*      */             case null:
///*      */               break;
///*      */           }
///*      */           offsets.bottom += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
///*      */           break;
///*      */         case HORIZONTAL:
///*      */           switch (this.mLegend.getVerticalAlignment()) {
///*      */             case VERTICAL:
///*      */               offsets.top += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
///*      */               if (getXAxis().isEnabled() && getXAxis().isDrawLabelsEnabled())
///*      */                 offsets.top += (getXAxis()).mLabelRotatedHeight;
///*      */               break;
///*      */             case HORIZONTAL:
///*      */             case null:
///*      */               break;
///*      */           }
///*      */           offsets.bottom += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
///*      */           if (getXAxis().isEnabled() && getXAxis().isDrawLabelsEnabled())
///*      */             offsets.bottom += (getXAxis()).mLabelRotatedHeight;
///*      */           break;
/*      */       }  
/*      */   }
/*      */   
/*      */   public void calculateOffsets() {
/*      */     if (!this.mCustomViewPortEnabled) {
/*      */       float offsetLeft = 0.0F, offsetRight = 0.0F, offsetTop = 0.0F, offsetBottom = 0.0F;
/*      */       calculateLegendOffsets(this.mOffsetsBuffer);
/*      */       offsetLeft += this.mOffsetsBuffer.left;
/*      */       offsetTop += this.mOffsetsBuffer.top;
/*      */       offsetRight += this.mOffsetsBuffer.right;
/*      */       offsetBottom += this.mOffsetsBuffer.bottom;
/*      */       if (this.mAxisLeft.needsOffset())
/*      */         offsetLeft += this.mAxisLeft.getRequiredWidthSpace(this.mAxisRendererLeft.getPaintAxisLabels()); 
/*      */       if (this.mAxisRight.needsOffset())
/*      */         offsetRight += this.mAxisRight.getRequiredWidthSpace(this.mAxisRendererRight.getPaintAxisLabels()); 
/*      */       if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
/*      */         float xlabelheight = this.mXAxis.mLabelRotatedHeight + this.mXAxis.getYOffset();
/*      */         if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
/*      */           offsetBottom += xlabelheight;
/*      */         } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.TOP) {
/*      */           offsetTop += xlabelheight;
/*      */         } else if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
/*      */           offsetBottom += xlabelheight;
/*      */           offsetTop += xlabelheight;
/*      */         } 
/*      */       } 
/*      */       offsetTop += getExtraTopOffset();
/*      */       offsetRight += getExtraRightOffset();
/*      */       offsetBottom += getExtraBottomOffset();
/*      */       offsetLeft += getExtraLeftOffset();
/*      */       float minOffset = Utils.convertDpToPixel(this.mMinOffset);
/*      */       this.mViewPortHandler.restrainViewPort(Math.max(minOffset, offsetLeft), Math.max(minOffset, offsetTop), Math.max(minOffset, offsetRight), Math.max(minOffset, offsetBottom));
/*      */       if (this.mLogEnabled) {
/*      */         Log.i("MPAndroidChart", "offsetLeft: " + offsetLeft + ", offsetTop: " + offsetTop + ", offsetRight: " + offsetRight + ", offsetBottom: " + offsetBottom);
/*      */         Log.i("MPAndroidChart", "Content: " + this.mViewPortHandler.getContentRect().toString());
/*      */       } 
/*      */     } 
/*      */     prepareOffsetMatrix();
/*      */     prepareValuePxMatrix();
/*      */   }
/*      */   
/*      */   protected void drawGridBackground(Canvas c) {
/*      */     if (this.mDrawGridBackground)
/*      */       c.drawRect(this.mViewPortHandler.getContentRect(), this.mGridBackgroundPaint); 
/*      */     if (this.mDrawBorders)
/*      */       c.drawRect(this.mViewPortHandler.getContentRect(), this.mBorderPaint); 
/*      */   }
/*      */   
/*      */   public Transformer getTransformer(YAxis.AxisDependency which) {
/*      */     if (which == YAxis.AxisDependency.LEFT)
/*      */       return this.mLeftAxisTransformer; 
/*      */     return this.mRightAxisTransformer;
/*      */   }
/*      */   
/*      */   public boolean onTouchEvent(MotionEvent event) {
/*      */     super.onTouchEvent(event);
/*      */     if (this.mChartTouchListener == null || this.mData == null)
/*      */       return false; 
/*      */     if (!this.mTouchEnabled)
/*      */       return false; 
/*      */     return this.mChartTouchListener.onTouch(this, event);
/*      */   }
/*      */   
/*      */   public void computeScroll() {
/*      */     if (this.mChartTouchListener instanceof BarLineChartTouchListener)
/*      */       ((BarLineChartTouchListener)this.mChartTouchListener).computeScroll(); 
/*      */   }
/*      */   
/*      */   public void zoomIn() {
/*      */     MPPointF center = this.mViewPortHandler.getContentCenter();
/*      */     this.mViewPortHandler.zoomIn(center.x, -center.y, this.mZoomMatrixBuffer);
/*      */     this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
/*      */     MPPointF.recycleInstance(center);
/*      */     calculateOffsets();
/*      */     postInvalidate();
/*      */   }
/*      */   
/*      */   public void zoomOut() {
/*      */     MPPointF center = this.mViewPortHandler.getContentCenter();
/*      */     this.mViewPortHandler.zoomOut(center.x, -center.y, this.mZoomMatrixBuffer);
/*      */     this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
/*      */     MPPointF.recycleInstance(center);
/*      */     calculateOffsets();
/*      */     postInvalidate();
/*      */   }
/*      */   
/*      */   public void resetZoom() {
/*      */     this.mViewPortHandler.resetZoom(this.mZoomMatrixBuffer);
/*      */     this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
/*      */     calculateOffsets();
/*      */     postInvalidate();
/*      */   }
/*      */   
/*      */   public void zoom(float scaleX, float scaleY, float x, float y) {
/*      */     this.mViewPortHandler.zoom(scaleX, scaleY, x, -y, this.mZoomMatrixBuffer);
/*      */     this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
/*      */     calculateOffsets();
/*      */     postInvalidate();
/*      */   }
/*      */   
/*      */   public void zoom(float scaleX, float scaleY, float xValue, float yValue, YAxis.AxisDependency axis) {
/*      */     ZoomJob zoomJob = ZoomJob.getInstance(this.mViewPortHandler, scaleX, scaleY, xValue, yValue, getTransformer(axis), axis, this);
/*      */     addViewportJob(zoomJob);
/*      */   }
/*      */   
/*      */   public void zoomToCenter(float scaleX, float scaleY) {
/*      */     MPPointF center = getCenterOffsets();
/*      */     Matrix save = this.mZoomMatrixBuffer;
/*      */     this.mViewPortHandler.zoom(scaleX, scaleY, center.x, -center.y, save);
/*      */     this.mViewPortHandler.refresh(save, this, false);
/*      */   }
/*      */   
/*      */   @TargetApi(11)
/*      */   public void zoomAndCenterAnimated(float scaleX, float scaleY, float xValue, float yValue, YAxis.AxisDependency axis, long duration) {
/*      */     if (Build.VERSION.SDK_INT >= 11) {
/*      */       MPPointD origin = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axis);
/*      */       AnimatedZoomJob animatedZoomJob = AnimatedZoomJob.getInstance(this.mViewPortHandler, this, getTransformer(axis), getAxis(axis), this.mXAxis.mAxisRange, scaleX, scaleY, this.mViewPortHandler.getScaleX(), this.mViewPortHandler.getScaleY(), xValue, yValue, (float)origin.x, (float)origin.y, duration);
/*      */       addViewportJob(animatedZoomJob);
/*      */       MPPointD.recycleInstance(origin);
/*      */     } else {
/*      */       Log.e("MPAndroidChart", "Unable to execute zoomAndCenterAnimated(...) on API level < 11");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fitScreen() {
/*      */     Matrix save = this.mFitScreenMatrixBuffer;
/*      */     this.mViewPortHandler.fitScreen(save);
/*      */     this.mViewPortHandler.refresh(save, this, false);
/*      */     calculateOffsets();
/*      */     postInvalidate();
/*      */   }
/*      */   
/*      */   public void setScaleMinima(float scaleX, float scaleY) {
/*      */     this.mViewPortHandler.setMinimumScaleX(scaleX);
/*      */     this.mViewPortHandler.setMinimumScaleY(scaleY);
/*      */   }
/*      */   
/*      */   public void setVisibleXRangeMaximum(float maxXRange) {
/*      */     float xScale = this.mXAxis.mAxisRange / maxXRange;
/*      */     this.mViewPortHandler.setMinimumScaleX(xScale);
/*      */   }
/*      */   
/*      */   public void setVisibleXRangeMinimum(float minXRange) {
/*      */     float xScale = this.mXAxis.mAxisRange / minXRange;
/*      */     this.mViewPortHandler.setMaximumScaleX(xScale);
/*      */   }
/*      */   
/*      */   public void setVisibleXRange(float minXRange, float maxXRange) {
/*      */     float minScale = this.mXAxis.mAxisRange / minXRange;
/*      */     float maxScale = this.mXAxis.mAxisRange / maxXRange;
/*      */     this.mViewPortHandler.setMinMaxScaleX(minScale, maxScale);
/*      */   }
/*      */   
/*      */   public void setVisibleYRangeMaximum(float maxYRange, YAxis.AxisDependency axis) {
/*      */     float yScale = getAxisRange(axis) / maxYRange;
/*      */     this.mViewPortHandler.setMinimumScaleY(yScale);
/*      */   }
/*      */   
/*      */   public void setVisibleYRangeMinimum(float minYRange, YAxis.AxisDependency axis) {
/*      */     float yScale = getAxisRange(axis) / minYRange;
/*      */     this.mViewPortHandler.setMaximumScaleY(yScale);
/*      */   }
/*      */   
/*      */   public void setVisibleYRange(float minYRange, float maxYRange, YAxis.AxisDependency axis) {
/*      */     float minScale = getAxisRange(axis) / minYRange;
/*      */     float maxScale = getAxisRange(axis) / maxYRange;
/*      */     this.mViewPortHandler.setMinMaxScaleY(minScale, maxScale);
/*      */   }
/*      */   
/*      */   public void moveViewToX(float xValue) {
/*      */     MoveViewJob moveViewJob = MoveViewJob.getInstance(this.mViewPortHandler, xValue, 0.0F, getTransformer(YAxis.AxisDependency.LEFT), this);
/*      */     addViewportJob(moveViewJob);
/*      */   }
/*      */   
/*      */   public void moveViewTo(float xValue, float yValue, YAxis.AxisDependency axis) {
/*      */     float yInView = getAxisRange(axis) / this.mViewPortHandler.getScaleY();
/*      */     MoveViewJob moveViewJob = MoveViewJob.getInstance(this.mViewPortHandler, xValue, yValue + yInView / 2.0F, getTransformer(axis), this);
/*      */     addViewportJob(moveViewJob);
/*      */   }
/*      */   
/*      */   @TargetApi(11)
/*      */   public void moveViewToAnimated(float xValue, float yValue, YAxis.AxisDependency axis, long duration) {
/*      */     if (Build.VERSION.SDK_INT >= 11) {
/*      */       MPPointD bounds = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axis);
/*      */       float yInView = getAxisRange(axis) / this.mViewPortHandler.getScaleY();
/*      */       AnimatedMoveViewJob animatedMoveViewJob = AnimatedMoveViewJob.getInstance(this.mViewPortHandler, xValue, yValue + yInView / 2.0F, getTransformer(axis), this, (float)bounds.x, (float)bounds.y, duration);
/*      */       addViewportJob(animatedMoveViewJob);
/*      */       MPPointD.recycleInstance(bounds);
/*      */     } else {
/*      */       Log.e("MPAndroidChart", "Unable to execute moveViewToAnimated(...) on API level < 11");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void centerViewToY(float yValue, YAxis.AxisDependency axis) {
/*      */     float valsInView = getAxisRange(axis) / this.mViewPortHandler.getScaleY();
/*      */     MoveViewJob moveViewJob = MoveViewJob.getInstance(this.mViewPortHandler, 0.0F, yValue + valsInView / 2.0F, getTransformer(axis), this);
/*      */     addViewportJob(moveViewJob);
/*      */   }
/*      */   
/*      */   public void centerViewTo(float xValue, float yValue, YAxis.AxisDependency axis) {
/*      */     float yInView = getAxisRange(axis) / this.mViewPortHandler.getScaleY();
/*      */     float xInView = (getXAxis()).mAxisRange / this.mViewPortHandler.getScaleX();
/*      */     MoveViewJob moveViewJob = MoveViewJob.getInstance(this.mViewPortHandler, xValue - xInView / 2.0F, yValue + yInView / 2.0F, getTransformer(axis), this);
/*      */     addViewportJob(moveViewJob);
/*      */   }
/*      */   
/*      */   @TargetApi(11)
/*      */   public void centerViewToAnimated(float xValue, float yValue, YAxis.AxisDependency axis, long duration) {
/*      */     if (Build.VERSION.SDK_INT >= 11) {
/*      */       MPPointD bounds = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axis);
/*      */       float yInView = getAxisRange(axis) / this.mViewPortHandler.getScaleY();
/*      */       float xInView = (getXAxis()).mAxisRange / this.mViewPortHandler.getScaleX();
/*      */       AnimatedMoveViewJob animatedMoveViewJob = AnimatedMoveViewJob.getInstance(this.mViewPortHandler, xValue - xInView / 2.0F, yValue + yInView / 2.0F, getTransformer(axis), this, (float)bounds.x, (float)bounds.y, duration);
/*      */       addViewportJob(animatedMoveViewJob);
/*      */       MPPointD.recycleInstance(bounds);
/*      */     } else {
/*      */       Log.e("MPAndroidChart", "Unable to execute centerViewToAnimated(...) on API level < 11");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setViewPortOffsets(final float left, final float top, final float right, final float bottom) {
/*      */     this.mCustomViewPortEnabled = true;
/*      */     post(new Runnable() {
/*      */           public void run() {
/*      */             BarLineChartBase.this.mViewPortHandler.restrainViewPort(left, top, right, bottom);
/*      */             BarLineChartBase.this.prepareOffsetMatrix();
/*      */             BarLineChartBase.this.prepareValuePxMatrix();
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public void resetViewPortOffsets() {
/*      */     this.mCustomViewPortEnabled = false;
/*      */     calculateOffsets();
/*      */   }
/*      */   
/*      */   protected float getAxisRange(YAxis.AxisDependency axis) {
/*      */     if (axis == YAxis.AxisDependency.LEFT)
/*      */       return this.mAxisLeft.mAxisRange; 
/*      */     return this.mAxisRight.mAxisRange;
/*      */   }
/*      */   
/*      */   public void setOnDrawListener(OnDrawListener drawListener) { this.mDrawListener = drawListener; }
/*      */   
/*      */   public OnDrawListener getDrawListener() { return this.mDrawListener; }
/*      */   
/*      */   public MPPointF getPosition(Entry e, YAxis.AxisDependency axis) {
/*      */     if (e == null)
/*      */       return null; 
/*      */     this.mGetPositionBuffer[0] = e.getX();
/*      */     this.mGetPositionBuffer[1] = e.getY();
/*      */     getTransformer(axis).pointValuesToPixel(this.mGetPositionBuffer);
/*      */     return MPPointF.getInstance(this.mGetPositionBuffer[0], this.mGetPositionBuffer[1]);
/*      */   }
/*      */   
/*      */   public void setMaxVisibleValueCount(int count) { this.mMaxVisibleCount = count; }
/*      */   
/*      */   public int getMaxVisibleCount() { return this.mMaxVisibleCount; }
/*      */   
/*      */   public void setHighlightPerDragEnabled(boolean enabled) { this.mHighlightPerDragEnabled = enabled; }
/*      */   
/*      */   public boolean isHighlightPerDragEnabled() { return this.mHighlightPerDragEnabled; }
/*      */   
/*      */   public void setGridBackgroundColor(int color) { this.mGridBackgroundPaint.setColor(color); }
/*      */   
/*      */   public void setDragEnabled(boolean enabled) {
/*      */     this.mDragXEnabled = enabled;
/*      */     this.mDragYEnabled = enabled;
/*      */   }
/*      */   
/*      */   public boolean isDragEnabled() { return (this.mDragXEnabled || this.mDragYEnabled); }
/*      */   
/*      */   public void setDragXEnabled(boolean enabled) { this.mDragXEnabled = enabled; }
/*      */   
/*      */   public boolean isDragXEnabled() { return this.mDragXEnabled; }
/*      */   
/*      */   public void setDragYEnabled(boolean enabled) { this.mDragYEnabled = enabled; }
/*      */   
/*      */   public boolean isDragYEnabled() { return this.mDragYEnabled; }
/*      */   
/*      */   public void setScaleEnabled(boolean enabled) {
/*      */     this.mScaleXEnabled = enabled;
/*      */     this.mScaleYEnabled = enabled;
/*      */   }
/*      */   
/*      */   public void setScaleXEnabled(boolean enabled) { this.mScaleXEnabled = enabled; }
/*      */   
/*      */   public void setScaleYEnabled(boolean enabled) { this.mScaleYEnabled = enabled; }
/*      */   
/*      */   public boolean isScaleXEnabled() { return this.mScaleXEnabled; }
/*      */   
/*      */   public boolean isScaleYEnabled() { return this.mScaleYEnabled; }
/*      */   
/*      */   public void setDoubleTapToZoomEnabled(boolean enabled) { this.mDoubleTapToZoomEnabled = enabled; }
/*      */   
/*      */   public boolean isDoubleTapToZoomEnabled() { return this.mDoubleTapToZoomEnabled; }
/*      */   
/*      */   public void setDrawGridBackground(boolean enabled) { this.mDrawGridBackground = enabled; }
/*      */   
/*      */   public void setDrawBorders(boolean enabled) { this.mDrawBorders = enabled; }
/*      */   
/*      */   public boolean isDrawBordersEnabled() { return this.mDrawBorders; }
/*      */   
/*      */   public void setClipValuesToContent(boolean enabled) { this.mClipValuesToContent = enabled; }
/*      */   
/*      */   public boolean isClipValuesToContentEnabled() { return this.mClipValuesToContent; }
/*      */   
/*      */   public void setBorderWidth(float width) { this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(width)); }
/*      */   
/*      */   public void setBorderColor(int color) { this.mBorderPaint.setColor(color); }
/*      */   
/*      */   public float getMinOffset() { return this.mMinOffset; }
/*      */   
/*      */   public void setMinOffset(float minOffset) { this.mMinOffset = minOffset; }
/*      */   
/*      */   public boolean isKeepPositionOnRotation() { return this.mKeepPositionOnRotation; }
/*      */   
/*      */   public void setKeepPositionOnRotation(boolean keepPositionOnRotation) { this.mKeepPositionOnRotation = keepPositionOnRotation; }
/*      */   
/*      */   public MPPointD getValuesByTouchPoint(float x, float y, YAxis.AxisDependency axis) {
/*      */     MPPointD result = MPPointD.getInstance(0.0D, 0.0D);
/*      */     getValuesByTouchPoint(x, y, axis, result);
/*      */     return result;
/*      */   }
/*      */   
/*      */   public void getValuesByTouchPoint(float x, float y, YAxis.AxisDependency axis, MPPointD outputPoint) { getTransformer(axis).getValuesByTouchPoint(x, y, outputPoint); }
/*      */   
/*      */   public MPPointD getPixelForValues(float x, float y, YAxis.AxisDependency axis) { return getTransformer(axis).getPixelForValues(x, y); }
/*      */   
/*      */   public Entry getEntryByTouchPoint(float x, float y) {
/*      */     Highlight h = getHighlightByTouchPoint(x, y);
/*      */     if (h != null)
/*      */       return ((BarLineScatterCandleBubbleData)this.mData).getEntryForHighlight(h); 
/*      */     return null;
/*      */   }
/*      */   
/*      */   public IBarLineScatterCandleBubbleDataSet getDataSetByTouchPoint(float x, float y) {
/*      */     Highlight h = getHighlightByTouchPoint(x, y);
/*      */     if (h != null)
/*      */       return (IBarLineScatterCandleBubbleDataSet)((BarLineScatterCandleBubbleData)this.mData).getDataSetByIndex(h.getDataSetIndex()); 
/*      */     return null;
/*      */   }
/*      */   
/*      */   public float getLowestVisibleX() {
/*      */     getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.posForGetLowestVisibleX);
/*      */     return (float)Math.max(this.mXAxis.mAxisMinimum, this.posForGetLowestVisibleX.x);
/*      */   }
/*      */   
/*      */   public float getHighestVisibleX() {
/*      */     getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.posForGetHighestVisibleX);
/*      */     return (float)Math.min(this.mXAxis.mAxisMaximum, this.posForGetHighestVisibleX.x);
/*      */   }
/*      */   
/*      */   public float getVisibleXRange() { return Math.abs(getHighestVisibleX() - getLowestVisibleX()); }
/*      */   
/*      */   public float getScaleX() {
/*      */     if (this.mViewPortHandler == null)
/*      */       return 1.0F; 
/*      */     return this.mViewPortHandler.getScaleX();
/*      */   }
/*      */   
/*      */   public float getScaleY() {
/*      */     if (this.mViewPortHandler == null)
/*      */       return 1.0F; 
/*      */     return this.mViewPortHandler.getScaleY();
/*      */   }
/*      */   
/*      */   public boolean isFullyZoomedOut() { return this.mViewPortHandler.isFullyZoomedOut(); }
/*      */   
/*      */   public YAxis getAxisLeft() { return this.mAxisLeft; }
/*      */   
/*      */   public YAxis getAxisRight() { return this.mAxisRight; }
/*      */   
/*      */   public YAxis getAxis(YAxis.AxisDependency axis) {
/*      */     if (axis == YAxis.AxisDependency.LEFT)
/*      */       return this.mAxisLeft; 
/*      */     return this.mAxisRight;
/*      */   }
/*      */   
/*      */   public boolean isInverted(YAxis.AxisDependency axis) { return getAxis(axis).isInverted(); }
/*      */   
/*      */   public void setPinchZoom(boolean enabled) { this.mPinchZoomEnabled = enabled; }
/*      */   
/*      */   public boolean isPinchZoomEnabled() { return this.mPinchZoomEnabled; }
/*      */   
/*      */   public void setDragOffsetX(float offset) { this.mViewPortHandler.setDragOffsetX(offset); }
/*      */   
/*      */   public void setDragOffsetY(float offset) { this.mViewPortHandler.setDragOffsetY(offset); }
/*      */   
/*      */   public boolean hasNoDragOffset() { return this.mViewPortHandler.hasNoDragOffset(); }
/*      */   
/*      */   public XAxisRenderer getRendererXAxis() { return this.mXAxisRenderer; }
/*      */   
/*      */   public void setXAxisRenderer(XAxisRenderer xAxisRenderer) { this.mXAxisRenderer = xAxisRenderer; }
/*      */   
/*      */   public YAxisRenderer getRendererLeftYAxis() { return this.mAxisRendererLeft; }
/*      */   
/*      */   public void setRendererLeftYAxis(YAxisRenderer rendererLeftYAxis) { this.mAxisRendererLeft = rendererLeftYAxis; }
/*      */   
/*      */   public YAxisRenderer getRendererRightYAxis() { return this.mAxisRendererRight; }
/*      */   
/*      */   public void setRendererRightYAxis(YAxisRenderer rendererRightYAxis) { this.mAxisRendererRight = rendererRightYAxis; }
/*      */   
/*      */   public float getYChartMax() { return Math.max(this.mAxisLeft.mAxisMaximum, this.mAxisRight.mAxisMaximum); }
/*      */   
/*      */   public float getYChartMin() { return Math.min(this.mAxisLeft.mAxisMinimum, this.mAxisRight.mAxisMinimum); }
/*      */   
/*      */   public boolean isAnyAxisInverted() {
/*      */     if (this.mAxisLeft.isInverted())
/*      */       return true; 
/*      */     if (this.mAxisRight.isInverted())
/*      */       return true; 
/*      */     return false;
/*      */   }
/*      */   
/*      */   public void setAutoScaleMinMaxEnabled(boolean enabled) { this.mAutoScaleMinMaxEnabled = enabled; }
/*      */   
/*      */   public boolean isAutoScaleMinMaxEnabled() { return this.mAutoScaleMinMaxEnabled; }
/*      */   
/*      */   public void setPaint(Paint p, int which) {
/*      */     super.setPaint(p, which);
/*      */     switch (which) {
/*      */       case 4:
/*      */         this.mGridBackgroundPaint = p;
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getPaint(int which) {
/*      */     Paint p = super.getPaint(which);
/*      */     if (p != null)
/*      */       return p; 
/*      */     switch (which) {
/*      */       case 4:
/*      */         return this.mGridBackgroundPaint;
/*      */     } 
/*      */     return null;
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\BarLineChartBase.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */