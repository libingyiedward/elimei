package com.elimei.elimei.charts;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.elimei.elimei.animation.ChartAnimator;
import com.elimei.elimei.animation.Easing;
import com.elimei.elimei.animation.EasingFunction;
import com.elimei.elimei.components.Description;
import com.elimei.elimei.components.IMarker;
import com.elimei.elimei.components.Legend;
import com.elimei.elimei.components.XAxis;
import com.elimei.elimei.data.ChartData;
import com.elimei.elimei.data.Entry;
import com.elimei.elimei.formatter.DefaultValueFormatter;
import com.elimei.elimei.formatter.IValueFormatter;
import com.elimei.elimei.highlight.ChartHighlighter;
import com.elimei.elimei.highlight.Highlight;
import com.elimei.elimei.highlight.IHighlighter;
import com.elimei.elimei.interfaces.dataprovider.ChartInterface;
import com.elimei.elimei.interfaces.datasets.IDataSet;
import com.elimei.elimei.listener.ChartTouchListener;
import com.elimei.elimei.listener.OnChartGestureListener;
import com.elimei.elimei.listener.OnChartValueSelectedListener;
import com.elimei.elimei.renderer.DataRenderer;
import com.elimei.elimei.renderer.LegendRenderer;
import com.elimei.elimei.utils.MPPointF;
import com.elimei.elimei.utils.Utils;
import com.elimei.elimei.utils.ViewPortHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


@SuppressLint({"NewApi"})
public abstract class Chart<T extends ChartData<? extends IDataSet<? extends Entry>>>
        extends ViewGroup
        implements ChartInterface {
    public static final String LOG_TAG = "MPAndroidChart";
    protected boolean mLogEnabled = true;
    /*   79 */   protected T mData = null;


    protected boolean mHighLightPerTapEnabled = true;


    private boolean mDragDecelerationEnabled = true;


    /*   97 */   private float mDragDecelerationFrictionCoef = 0.9F;


    /*  102 */   protected DefaultValueFormatter mDefaultValueFormatter = new DefaultValueFormatter(0);


    protected Paint mDescPaint;


    protected Paint mInfoPaint;


    protected XAxis mXAxis;


    protected boolean mTouchEnabled = true;


    protected Description mDescription;


    protected Legend mLegend;


    protected OnChartValueSelectedListener mSelectionListener;


    protected ChartTouchListener mChartTouchListener;


    /*  146 */   private String mNoDataText = "No chart data available.";


    private OnChartGestureListener mGestureListener;


    protected LegendRenderer mLegendRenderer;


    protected DataRenderer mRenderer;


    protected IHighlighter mHighlighter;


    /*  165 */   protected ViewPortHandler mViewPortHandler = new ViewPortHandler();


    protected ChartAnimator mAnimator;


    /*  175 */   private float mExtraTopOffset = 0.0F;
    private float mExtraRightOffset = 0.0F;
    private float mExtraBottomOffset = 0.0F;
    private float mExtraLeftOffset = 0.0F;
    private boolean mOffsetsCalculated;
    protected Highlight[] mIndicesToHighlight;
    protected float mMaxHighlightDistance;
    protected boolean mDrawMarkers;
    protected IMarker mMarker;
    public static final int PAINT_GRID_BACKGROUND = 4;
    public static final int PAINT_INFO = 7;
    public static final int PAINT_DESCRIPTION = 11;
    public static final int PAINT_HOLE = 13;
    public static final int PAINT_CENTER_TEXT = 14;
    public static final int PAINT_LEGEND_LABEL = 18;
    protected ArrayList<Runnable> mJobs;
    private boolean mUnbind;

    /*  184 */
    public Chart(Context context) {
        super(context);

















































































































































































































        /*  394 */
        this.mOffsetsCalculated = false;




































































        /*  463 */
        this.mMaxHighlightDistance = 0.0F;





















































































































































































































































        /*  709 */
        this.mDrawMarkers = true;











































































































































































































































































































































































































































































































































































































































































































































































































































































































































        /* 1617 */
        this.mJobs = new ArrayList();



















































































































        /* 1733 */
        this.mUnbind = false;
        init();
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mOffsetsCalculated = false;
        this.mMaxHighlightDistance = 0.0F;
        this.mDrawMarkers = true;
        this.mJobs = new ArrayList();
        this.mUnbind = false;
        init();
    }

    protected void init() {
        setWillNotDraw(false);
        if (Build.VERSION.SDK_INT < 11) {
            this.mAnimator = new ChartAnimator();
        } else {
            this.mAnimator = new ChartAnimator(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Chart.this.postInvalidate();
                }
            });
        }
        Utils.init(getContext());
        this.mMaxHighlightDistance = Utils.convertDpToPixel(500.0F);
        this.mDescription = new Description();
        this.mLegend = new Legend();
        this.mLegendRenderer = new LegendRenderer(this.mViewPortHandler, this.mLegend);
        this.mXAxis = new XAxis();
        this.mDescPaint = new Paint();
        this.mInfoPaint = new Paint();
        this.mInfoPaint.setColor(Color.rgb(247, 189, 51));
        this.mInfoPaint.setTextAlign(Paint.Align.CENTER);
        this.mInfoPaint.setTextSize(Utils.convertDpToPixel(12.0F));
        if (this.mLogEnabled) Log.i("", "Chart.init()");
    }

    public void setData(T data) {
        this.mData = data;
        this.mOffsetsCalculated = false;
        if (data == null) return;
        setupDefaultFormatter(data.getYMin(), data.getYMax());
        for (IDataSet set : this.mData.getDataSets()) {
            if (set.needsFormatter() || set.getValueFormatter() == this.mDefaultValueFormatter)
                set.setValueFormatter(this.mDefaultValueFormatter);
        }
        notifyDataSetChanged();
        if (this.mLogEnabled) Log.i("MPAndroidChart", "Data is set.");
    }

    public void clear() {
        this.mData = null;
        this.mOffsetsCalculated = false;
        this.mIndicesToHighlight = null;
        this.mChartTouchListener.setLastHighlighted(null);
        invalidate();
    }

    public void clearValues() {
        this.mData.clearValues();
        invalidate();
    }

    public boolean isEmpty() {
        if (this.mData == null) return true;
        if (this.mData.getEntryCount() <= 0) return true;
        return false;
    }

    public abstract void notifyDataSetChanged();

    protected abstract void calculateOffsets();

    protected abstract void calcMinMax();

    protected void setupDefaultFormatter(float min, float max) {
        float reference = 0.0F;
        if (this.mData == null || this.mData.getEntryCount() < 2) {
            reference = Math.max(Math.abs(min), Math.abs(max));
        } else {
            reference = Math.abs(max - min);
        }
        int digits = Utils.getDecimals(reference);
        this.mDefaultValueFormatter.setup(digits);
    }

    protected void onDraw(Canvas canvas) {
        if (this.mData == null) {
            boolean hasText = !TextUtils.isEmpty(this.mNoDataText);
            if (hasText) {
                MPPointF c = getCenter();
                canvas.drawText(this.mNoDataText, c.x, c.y, this.mInfoPaint);
            }
            return;
        }
        if (!this.mOffsetsCalculated) {
            calculateOffsets();
            this.mOffsetsCalculated = true;
        }
    }

    protected void drawDescription(Canvas c) {
        if (this.mDescription != null && this.mDescription.isEnabled()) {
            float y, x;
            MPPointF position = this.mDescription.getPosition();
            this.mDescPaint.setTypeface(this.mDescription.getTypeface());
            this.mDescPaint.setTextSize(this.mDescription.getTextSize());
            this.mDescPaint.setColor(this.mDescription.getTextColor());
            this.mDescPaint.setTextAlign(this.mDescription.getTextAlign());
            if (position == null) {
                x = getWidth() - this.mViewPortHandler.offsetRight() - this.mDescription.getXOffset();
                y = getHeight() - this.mViewPortHandler.offsetBottom() - this.mDescription.getYOffset();
            } else {
                x = position.x;
                y = position.y;
            }
            c.drawText(this.mDescription.getText(), x, y, this.mDescPaint);
        }
    }

    public float getMaxHighlightDistance() {
        return this.mMaxHighlightDistance;
    }

    public void setMaxHighlightDistance(float distDp) {
        this.mMaxHighlightDistance = Utils.convertDpToPixel(distDp);
    }

    public Highlight[] getHighlighted() {
        return this.mIndicesToHighlight;
    }

    public boolean isHighlightPerTapEnabled() {
        return this.mHighLightPerTapEnabled;
    }

    public void setHighlightPerTapEnabled(boolean enabled) {
        this.mHighLightPerTapEnabled = enabled;
    }

    public boolean valuesToHighlight() {
        return !(this.mIndicesToHighlight == null || this.mIndicesToHighlight.length <= 0 || this.mIndicesToHighlight[0] == null);
    }

    protected void setLastHighlighted(Highlight[] highs) {
        if (highs == null || highs.length <= 0 || highs[0] == null) {
            this.mChartTouchListener.setLastHighlighted(null);
        } else {
            this.mChartTouchListener.setLastHighlighted(highs[0]);
        }
    }

    public void highlightValues(Highlight[] highs) {
        this.mIndicesToHighlight = highs;
        setLastHighlighted(highs);
        invalidate();
    }

    public void highlightValue(float x, int dataSetIndex) {
        highlightValue(x, dataSetIndex, true);
    }

    public void highlightValue(float x, float y, int dataSetIndex) {
        highlightValue(x, y, dataSetIndex, true);
    }

    public void highlightValue(float x, int dataSetIndex, boolean callListener) {
        highlightValue(x, 0f, dataSetIndex, callListener);
    }

    public void highlightValue(float x, float y, int dataSetIndex, boolean callListener) {
        if (dataSetIndex < 0 || dataSetIndex >= this.mData.getDataSetCount()) {
            highlightValue(null, callListener);
        } else {
            highlightValue(new Highlight(x, y, dataSetIndex), callListener);
        }
    }

    public void highlightValue(Highlight highlight) {
        highlightValue(highlight, false);
    }

    public void highlightValue(Highlight high, boolean callListener) {
        Entry e = null;
        if (high == null) {
            this.mIndicesToHighlight = null;
        } else {
            if (this.mLogEnabled) Log.i("MPAndroidChart", "Highlighted: " + high.toString());
            e = this.mData.getEntryForHighlight(high);
            if (e == null) {
                this.mIndicesToHighlight = null;
                high = null;
            } else {
                this.mIndicesToHighlight = new Highlight[]{high};
            }
        }
        setLastHighlighted(this.mIndicesToHighlight);
        if (callListener && this.mSelectionListener != null) if (!valuesToHighlight()) {
            this.mSelectionListener.onNothingSelected();
        } else {
            this.mSelectionListener.onValueSelected(e, high);
        }
        invalidate();
    }

    public Highlight getHighlightByTouchPoint(float x, float y) {
        if (this.mData == null) {
            Log.e("MPAndroidChart", "Can't select by touch. No data set.");
            return null;
        }
        return getHighlighter().getHighlight(x, y);
    }

    public void setOnTouchListener(ChartTouchListener l) {
        this.mChartTouchListener = l;
    }

    public Chart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mOffsetsCalculated = false;
        this.mMaxHighlightDistance = 0.0F;
        this.mDrawMarkers = true;
        this.mJobs = new ArrayList();
        this.mUnbind = false;
        init();
    }

    public ChartTouchListener getOnTouchListener() {
        return this.mChartTouchListener;
    }

    protected void drawMarkers(Canvas canvas) {
        if (this.mMarker == null || !isDrawMarkersEnabled() || !valuesToHighlight())
            return;
        for (int i = 0; i < this.mIndicesToHighlight.length; i++) {
            Highlight highlight = this.mIndicesToHighlight[i];
            IDataSet set = this.mData.getDataSetByIndex(highlight.getDataSetIndex());
            Entry e = this.mData.getEntryForHighlight(this.mIndicesToHighlight[i]);
            int entryIndex = set.getEntryIndex(e);
            if (e != null && entryIndex <= set.getEntryCount() * this.mAnimator.getPhaseX()) {
                float[] pos = getMarkerPosition(highlight);
                if (this.mViewPortHandler.isInBounds(pos[0], pos[1])) {
                    this.mMarker.refreshContent(e, highlight);
                    this.mMarker.draw(canvas, pos[0], pos[1]);
                }
            }
        }
    }

    protected float[] getMarkerPosition(Highlight high) {
        return new float[]{high.getDrawX(), high.getDrawY()};
    }

    public ChartAnimator getAnimator() {
        return this.mAnimator;
    }

    public boolean isDragDecelerationEnabled() {
        return this.mDragDecelerationEnabled;
    }

    public void setDragDecelerationEnabled(boolean enabled) {
        this.mDragDecelerationEnabled = enabled;
    }

    public float getDragDecelerationFrictionCoef() {
        return this.mDragDecelerationFrictionCoef;
    }

    public void setDragDecelerationFrictionCoef(float newValue) {
        if (newValue < 0.0F)
            newValue = 0.0F;
        if (newValue >= 1.0F)
            newValue = 0.999F;
        this.mDragDecelerationFrictionCoef = newValue;
    }

    public void animateXY(int durationMillisX, int durationMillisY, EasingFunction easingX, EasingFunction easingY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY, easingX, easingY);
    }

    public void animateX(int durationMillis, EasingFunction easing) {
        this.mAnimator.animateX(durationMillis, easing);
    }

    public void animateY(int durationMillis, EasingFunction easing) {
        this.mAnimator.animateY(durationMillis, easing);
    }

    public void animateXY(int durationMillisX, int durationMillisY, Easing.EasingOption easingX, Easing.EasingOption easingY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY, easingX, easingY);
    }

    public void animateX(int durationMillis, Easing.EasingOption easing) {
        this.mAnimator.animateX(durationMillis, easing);
    }

    public void animateY(int durationMillis, Easing.EasingOption easing) {
        this.mAnimator.animateY(durationMillis, easing);
    }

    public void animateX(int durationMillis) {
        this.mAnimator.animateX(durationMillis);
    }

    public void animateY(int durationMillis) {
        this.mAnimator.animateY(durationMillis);
    }

    public void animateXY(int durationMillisX, int durationMillisY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY);
    }

    public XAxis getXAxis() {
        return this.mXAxis;
    }

    public IValueFormatter getDefaultValueFormatter() {
        return this.mDefaultValueFormatter;
    }

    public void setOnChartValueSelectedListener(OnChartValueSelectedListener l) {
        this.mSelectionListener = l;
    }

    public void setOnChartGestureListener(OnChartGestureListener l) {
        this.mGestureListener = l;
    }

    public OnChartGestureListener getOnChartGestureListener() {
        return this.mGestureListener;
    }

    public float getYMax() {
        return this.mData.getYMax();
    }

    public float getYMin() {
        return this.mData.getYMin();
    }

    public float getXChartMax() {
        return this.mXAxis.mAxisMaximum;
    }

    public float getXChartMin() {
        return this.mXAxis.mAxisMinimum;
    }

    public float getXRange() {
        return this.mXAxis.mAxisRange;
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            /* 1750 */
            ((ViewGroup) view).removeAllViews();
        }
    }

    public MPPointF getCenter() {
        return MPPointF.getInstance(getWidth() / 2.0F, getHeight() / 2.0F);
    }

    public MPPointF getCenterOffsets() {
        return this.mViewPortHandler.getContentCenter();
    }

    public void setExtraOffsets(float left, float top, float right, float bottom) {
        setExtraLeftOffset(left);
        setExtraTopOffset(top);
        setExtraRightOffset(right);
        setExtraBottomOffset(bottom);
    }

    public void setExtraTopOffset(float offset) {
        this.mExtraTopOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraTopOffset() {
        return this.mExtraTopOffset;
    }

    public void setExtraRightOffset(float offset) {
        this.mExtraRightOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraRightOffset() {
        return this.mExtraRightOffset;
    }

    public void setExtraBottomOffset(float offset) {
        this.mExtraBottomOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraBottomOffset() {
        return this.mExtraBottomOffset;
    }

    public void setExtraLeftOffset(float offset) {
        this.mExtraLeftOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraLeftOffset() {
        return this.mExtraLeftOffset;
    }

    public void setLogEnabled(boolean enabled) {
        this.mLogEnabled = enabled;
    }

    public boolean isLogEnabled() {
        return this.mLogEnabled;
    }

    public void setNoDataText(String text) {
        this.mNoDataText = text;
    }

    public void setNoDataTextColor(int color) {
        this.mInfoPaint.setColor(color);
    }

    public void setNoDataTextTypeface(Typeface tf) {
        this.mInfoPaint.setTypeface(tf);
    }

    public void setTouchEnabled(boolean enabled) {
        this.mTouchEnabled = enabled;
    }

    public void setMarker(IMarker marker) {
        this.mMarker = marker;
    }

    public IMarker getMarker() {
        return this.mMarker;
    }

    @Deprecated
    public void setMarkerView(IMarker v) {
        setMarker(v);
    }

    @Deprecated
    public IMarker getMarkerView() {
        return getMarker();
    }

    public void setDescription(Description desc) {
        this.mDescription = desc;
    }

    public Description getDescription() {
        return this.mDescription;
    }

    public Legend getLegend() {
        return this.mLegend;
    }

    public LegendRenderer getLegendRenderer() {
        return this.mLegendRenderer;
    }

    public RectF getContentRect() {
        return this.mViewPortHandler.getContentRect();
    }

    public void disableScroll() {
        ViewParent parent = getParent();
        if (parent != null) parent.requestDisallowInterceptTouchEvent(true);
    }

    public void enableScroll() {
        ViewParent parent = getParent();
        if (parent != null) parent.requestDisallowInterceptTouchEvent(false);
    }

    public void setPaint(Paint p, int which) {
        switch (which) {
            case 7:
                this.mInfoPaint = p;
                break;
            case 11:
                this.mDescPaint = p;
                break;
        }
    }

    public Paint getPaint(int which) {
        switch (which) {
            case 7:
                return this.mInfoPaint;
            case 11:
                return this.mDescPaint;
        }
        return null;
    }

    @Deprecated
    public boolean isDrawMarkerViewsEnabled() {
        return isDrawMarkersEnabled();
    }

    @Deprecated
    public void setDrawMarkerViews(boolean enabled) {
        setDrawMarkers(enabled);
    }

    public boolean isDrawMarkersEnabled() {
        return this.mDrawMarkers;
    }

    public void setDrawMarkers(boolean enabled) {
        this.mDrawMarkers = enabled;
    }

    public T getData() {
        return (T) this.mData;
    }

    public ViewPortHandler getViewPortHandler() {
        return this.mViewPortHandler;
    }

    public DataRenderer getRenderer() {
        return this.mRenderer;
    }

    public void setRenderer(DataRenderer renderer) {
        if (renderer != null) this.mRenderer = renderer;
    }

    public IHighlighter getHighlighter() {
        return this.mHighlighter;
    }

    public void setHighlighter(ChartHighlighter highlighter) {
        this.mHighlighter = highlighter;
    }

    public MPPointF getCenterOfView() {
        return getCenter();
    }

    public Bitmap getChartBitmap() {
        Bitmap returnedBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        draw(canvas);
        return returnedBitmap;
    }

    public boolean saveToPath(String title, String pathOnSD) {
        Bitmap b = getChartBitmap();
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + pathOnSD + "/" + title + ".png");
            b.compress(Bitmap.CompressFormat.PNG, 40, stream);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveToGallery(String fileName, String subFolderPath, String fileDescription, Bitmap.CompressFormat format, int quality) {
        if (quality < 0 || quality > 100) quality = 50;
        long currentTime = System.currentTimeMillis();
        File extBaseDir = Environment.getExternalStorageDirectory();
        File file = new File(extBaseDir.getAbsolutePath() + "/DCIM/" + subFolderPath);
        if (!file.exists() && !file.mkdirs()) return false;
        String mimeType = "";
        switch (format) {
            case PNG:
                mimeType = "image/png";
                if (!fileName.endsWith(".png")) fileName = fileName + ".png";
                break;
            case WEBP:
                mimeType = "image/webp";
                if (!fileName.endsWith(".webp")) fileName = fileName + ".webp";
                break;
            default:
                mimeType = "image/jpeg";
                if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg"))
                    fileName = fileName + ".jpg";
                break;
        }
        String filePath = file.getAbsolutePath() + "/" + fileName;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            Bitmap b = getChartBitmap();
            b.compress(format, quality, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        long size = (new File(filePath)).length();
        ContentValues values = new ContentValues(8);
        values.put("title", fileName);
        values.put("_display_name", fileName);
        values.put("date_added", Long.valueOf(currentTime));
        values.put("mime_type", mimeType);
        values.put("description", fileDescription);
        values.put("orientation", Integer.valueOf(0));
        values.put("_data", filePath);
        values.put("_size", Long.valueOf(size));
        return (getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) != null);
    }

    public boolean saveToGallery(String fileName, int quality) {
        return saveToGallery(fileName, "", "MPAndroidChart-Library Save", Bitmap.CompressFormat.JPEG, quality);
    }

    public void removeViewportJob(Runnable job) {
        this.mJobs.remove(job);
    }

    public void clearAllViewportJobs() {
        this.mJobs.clear();
    }

    public void addViewportJob(Runnable job) {
        if (this.mViewPortHandler.hasChartDimens()) {
            post(job);
        } else {
            this.mJobs.add(job);
        }
    }

    public ArrayList<Runnable> getJobs() {
        return this.mJobs;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) getChildAt(i).layout(left, top, right, bottom);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = (int) Utils.convertDpToPixel(50.0F);
        setMeasuredDimension(Math.max(getSuggestedMinimumWidth(), resolveSize(size, widthMeasureSpec)), Math.max(getSuggestedMinimumHeight(), resolveSize(size, heightMeasureSpec)));
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (this.mLogEnabled) Log.i("MPAndroidChart", "OnSizeChanged()");
        if (w > 0 && h > 0 && w < 10000 && h < 10000) {
            if (this.mLogEnabled)
                Log.i("MPAndroidChart", "Setting chart dimens, width: " + w + ", height: " + h);
            this.mViewPortHandler.setChartDimens(w, h);
        } else if (this.mLogEnabled) {
            Log.w("MPAndroidChart", "*Avoiding* setting chart dimens! width: " + w + ", height: " + h);
        }
        notifyDataSetChanged();
        for (Runnable r : this.mJobs) post(r);
        this.mJobs.clear();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setHardwareAccelerationEnabled(boolean enabled) {
        if (Build.VERSION.SDK_INT >= 11) {
            if (enabled) {
                setLayerType(2, null);
            } else {
                setLayerType(1, null);
            }
        } else {
            Log.e("MPAndroidChart", "Cannot enable/disable hardware acceleration for devices below API level 11.");
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mUnbind) unbindDrawables(this);
    }

    /* 1763 */
    public void setUnbindEnabled(boolean enabled) {
        this.mUnbind = enabled;
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\charts\Chart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */