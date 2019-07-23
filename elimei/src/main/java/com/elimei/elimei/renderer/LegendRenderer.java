/*     */
package com.elimei.elimei.renderer;
/*     */
/*     */

import android.graphics.Canvas;
/*     */ import android.graphics.DashPathEffect;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.Typeface;
/*     */ import com.elimei.elimei.components.Legend;
/*     */ import com.elimei.elimei.components.LegendEntry;
/*     */ import com.elimei.elimei.data.ChartData;
/*     */ import com.elimei.elimei.data.PieEntry;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
/*     */ import com.elimei.elimei.interfaces.datasets.ICandleDataSet;
/*     */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*     */ import com.elimei.elimei.interfaces.datasets.IPieDataSet;
/*     */ import com.elimei.elimei.utils.FSize;
/*     */ import com.elimei.elimei.utils.Utils;
/*     */ import com.elimei.elimei.utils.ViewPortHandler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ public class LegendRenderer
     extends Renderer
     {
   protected Paint mLegendLabelPaint;
   protected Paint mLegendFormPaint;
   protected Legend mLegend;
   protected List<LegendEntry> computedEntries;
   protected Paint.FontMetrics legendFontMetrics;
   private Path mLineFormPath;



    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {

        super(viewPortHandler);

        this.computedEntries = new ArrayList(16);

        this.legendFontMetrics = new Paint.FontMetrics();

        this.mLineFormPath = new Path();
    
        this.mLegend = legend;
    
        this.mLegendLabelPaint = new Paint();
    
        this.mLegendLabelPaint.setTextSize(Utils.convertDpToPixel(9.0F));
    
        this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
    
        this.mLegendFormPaint = new Paint();
    
        this.mLegendFormPaint.setStyle(Paint.Style.FILL);
    
    }




    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }



    protected void drawForm(Canvas c, float x, float y, LegendEntry entry, Legend legend) {
    
        DashPathEffect formLineDashEffect;
    
        float formLineWidth;
        /* 486 */
        if (entry.formColor == 1122868 || entry.formColor == 1122867 || entry.formColor == 0) {
        
            return;
        
        }
    
    
        /* 491 */
        int restoreCount = c.save();
    
        /* 493 */
        Legend.LegendForm form = entry.form;
        /* 494 */
        if (form == Legend.LegendForm.DEFAULT) {
            /* 495 */
            form = legend.getForm();
        
        }
        /* 497 */
        this.mLegendFormPaint.setColor(entry.formColor);
    
        /* 499 */
        float formSize = Utils.convertDpToPixel(
                /* 500 */         Float.isNaN(entry.formSize) ? legend
/* 501 */.getFormSize() : entry.formSize);
    
        /* 503 */
        float half = formSize / 2.0F;
    
        /* 505 */
        switch (form) {
        
        
        
        
        
        
        
        
        
            case DEFAULT:
            
            case CIRCLE:
                /* 516 */
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                /* 517 */
                c.drawCircle(x + half, y, half, this.mLegendFormPaint);
            
                break;
        
        
            case SQUARE:
                /* 521 */
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                /* 522 */
                c.drawRect(x, y - half, x + formSize, y + half, this.mLegendFormPaint);
            
                break;
        
        
        
            case LINE:
                /* 527 */
                formLineWidth = Utils.convertDpToPixel(
                        /* 528 */             Float.isNaN(entry.formLineWidth) ? legend
/* 529 */.getFormLineWidth() : entry.formLineWidth);
            
            
                /* 532 */
                formLineDashEffect = (entry.formLineDashEffect == null) ? legend.getFormLineDashEffect() : entry.formLineDashEffect;
            
                /* 534 */
                this.mLegendFormPaint.setStyle(Paint.Style.STROKE);
                /* 535 */
                this.mLegendFormPaint.setStrokeWidth(formLineWidth);
                /* 536 */
                this.mLegendFormPaint.setPathEffect(formLineDashEffect);
            
                /* 538 */
                this.mLineFormPath.reset();
                /* 539 */
                this.mLineFormPath.moveTo(x, y);
                /* 540 */
                this.mLineFormPath.lineTo(x + formSize, y);
                /* 541 */
                c.drawPath(this.mLineFormPath, this.mLegendFormPaint);
            
                break;
        
        }
    
    
        /* 546 */
        c.restoreToCount(restoreCount);
    
    }











    /* 558 */
    protected void drawLabel(Canvas c, float x, float y, String label) {
        c.drawText(label, x, y, this.mLegendLabelPaint);
    }



    public void computeLegend(ChartData<?> data) {
    
        if (!this.mLegend.isLegendCustom()) {
        
            this.computedEntries.clear();
        
            for (int i = 0; i < data.getDataSetCount(); i++) {
            
                IDataSet dataSet = data.getDataSetByIndex(i);
            
                List<Integer> clrs = dataSet.getColors();
            
                int entryCount = dataSet.getEntryCount();
            
                if (dataSet instanceof IBarDataSet && ((IBarDataSet) dataSet).isStacked()) {
                
                    IBarDataSet bds = (IBarDataSet) dataSet;
                
                    String[] sLabels = bds.getStackLabels();
                
                    for (int j = 0; j < clrs.size() && j < bds.getStackSize(); j++)
                    
                        this.computedEntries.add(new LegendEntry(sLabels[j % sLabels.length], dataSet.getForm(), dataSet.getFormSize(), dataSet.getFormLineWidth(), dataSet.getFormLineDashEffect(), ((Integer) clrs.get(j)).intValue()));
                
                    if (bds.getLabel() != null)
                    
                        this.computedEntries.add(new LegendEntry(dataSet.getLabel(), Legend.LegendForm.NONE, 0f, 0f, null, 1122867));
                
                } else if (dataSet instanceof IPieDataSet) {
                
                    IPieDataSet pds = (IPieDataSet) dataSet;
                
                    for (int j = 0; j < clrs.size() && j < entryCount; j++)
                    
                        this.computedEntries.add(new LegendEntry(((PieEntry) pds.getEntryForIndex(j)).getLabel(), dataSet.getForm(), dataSet.getFormSize(), dataSet.getFormLineWidth(), dataSet.getFormLineDashEffect(), ((Integer) clrs.get(j)).intValue()));
                
                    if (pds.getLabel() != null)
                    
                        this.computedEntries.add(new LegendEntry(dataSet.getLabel(), Legend.LegendForm.NONE, 0f, 0f, null, 1122867));
                
                } else if (dataSet instanceof ICandleDataSet && ((ICandleDataSet) dataSet).getDecreasingColor() != 1122867) {
                
                    int decreasingColor = ((ICandleDataSet) dataSet).getDecreasingColor();
                
                    int increasingColor = ((ICandleDataSet) dataSet).getIncreasingColor();
                
                    this.computedEntries.add(new LegendEntry(null, dataSet.getForm(), dataSet.getFormSize(), dataSet.getFormLineWidth(), dataSet.getFormLineDashEffect(), decreasingColor));
                
                    this.computedEntries.add(new LegendEntry(dataSet.getLabel(), dataSet.getForm(), dataSet.getFormSize(), dataSet.getFormLineWidth(), dataSet.getFormLineDashEffect(), increasingColor));
                
                } else {
                
                    for (int j = 0; j < clrs.size() && j < entryCount; j++) {
                    
                        String label;
                    
                        if (j < clrs.size() - 1 && j < entryCount - 1) {
                        
                            label = null;
                        
                        } else {
                        
                            label = data.getDataSetByIndex(i).getLabel();
                        
                        }
                    
                        this.computedEntries.add(new LegendEntry(label, dataSet.getForm(), dataSet.getFormSize(), dataSet.getFormLineWidth(), dataSet.getFormLineDashEffect(), ((Integer) clrs.get(j)).intValue()));
                    
                    }
                
                }
            
            }
        
            if (this.mLegend.getExtraEntries() != null)
             Collections.addAll(this.computedEntries, this.mLegend.getExtraEntries());
        
            this.mLegend.setEntries(this.computedEntries);
        
        }
    
        Typeface tf = this.mLegend.getTypeface();
    
        if (tf != null)
         this.mLegendLabelPaint.setTypeface(tf);
    
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
    
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
    
        this.mLegend.calculateDimensions(this.mLegendLabelPaint, this.mViewPortHandler);
    
    }



    public void renderLegend(Canvas c) {
    
        int count, i, lineIndex;
    
        float posY;

        boolean wasStacked;
    
        float stack;
    
        if (!this.mLegend.isEnabled())
         return;
    
        Typeface tf = this.mLegend.getTypeface();
    
        if (tf != null)
         this.mLegendLabelPaint.setTypeface(tf);
    
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
    
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
    
        float labelLineHeight = Utils.getLineHeight(this.mLegendLabelPaint, this.legendFontMetrics);
    
        float labelLineSpacing = Utils.getLineSpacing(this.mLegendLabelPaint, this.legendFontMetrics) + Utils.convertDpToPixel(this.mLegend.getYEntrySpace());
    
        float formYOffset = labelLineHeight - Utils.calcTextHeight(this.mLegendLabelPaint, "ABC") / 2.0F;
    
        LegendEntry[] entries = this.mLegend.getEntries();
    
        float formToTextSpace = Utils.convertDpToPixel(this.mLegend.getFormToTextSpace());
    
        float xEntrySpace = Utils.convertDpToPixel(this.mLegend.getXEntrySpace());
    
        Legend.LegendOrientation orientation = this.mLegend.getOrientation();
    
        Legend.LegendHorizontalAlignment horizontalAlignment = this.mLegend.getHorizontalAlignment();
    
        Legend.LegendVerticalAlignment verticalAlignment = this.mLegend.getVerticalAlignment();
    
        Legend.LegendDirection direction = this.mLegend.getDirection();
    
        float defaultFormSize = Utils.convertDpToPixel(this.mLegend.getFormSize());
    
        float stackSpace = Utils.convertDpToPixel(this.mLegend.getStackSpace());
    
        float yoffset = this.mLegend.getYOffset();
    
        float xoffset = this.mLegend.getXOffset();
    
        float originPosX = 0.0F;
    
        switch (horizontalAlignment) {
        
//            case NONE:
//
//                if (orientation == Legend.LegendOrientation.VERTICAL) {
//
//                    originPosX = xoffset;
//
//                } else {
//
//                    originPosX = this.mViewPortHandler.contentLeft() + xoffset;
//
//                }
//
//                if (direction == Legend.LegendDirection.RIGHT_TO_LEFT)
//                 originPosX += this.mLegend.mNeededWidth;
//
//                break;
//
//            case EMPTY:
//
//                if (orientation == Legend.LegendOrientation.VERTICAL) {
//
//                    originPosX = this.mViewPortHandler.getChartWidth() - xoffset;
//
//                } else {
//
//                    originPosX = this.mViewPortHandler.contentRight() - xoffset;
//
//                }
//
//                if (direction == Legend.LegendDirection.LEFT_TO_RIGHT)
//                 originPosX -= this.mLegend.mNeededWidth;
//
//                break;
//
//            case DEFAULT:
//
//                if (orientation == Legend.LegendOrientation.VERTICAL) {
//
//                    originPosX = this.mViewPortHandler.getChartWidth() / 2.0F;
//
//                } else {
//
//                    originPosX = this.mViewPortHandler.contentLeft() + this.mViewPortHandler.contentWidth() / 2.0F;
//
//                }
//
//                originPosX += ((direction == Legend.LegendDirection.LEFT_TO_RIGHT) ? xoffset : -xoffset);
//
//                if (orientation == Legend.LegendOrientation.VERTICAL)
//
//                    originPosX = (float) (originPosX + ((direction == Legend.LegendDirection.LEFT_TO_RIGHT) ? (-this.mLegend.mNeededWidth / 2.0D + xoffset) : (this.mLegend.mNeededWidth / 2.0D - xoffset)));
//
//                break;
        
        }
    
        switch (orientation) {
//
//            case NONE:
//
//                List<FSize> calculatedLineSizes = this.mLegend.getCalculatedLineSizes();
//
//                List<FSize> calculatedLabelSizes = this.mLegend.getCalculatedLabelSizes();
//
//                List<Boolean> calculatedLabelBreakPoints = this.mLegend.getCalculatedLabelBreakPoints();
//
//              float  posX = originPosX;
//
//                posY = 0.0F;
//
//                switch (verticalAlignment) {
//
//                    case NONE:
//
//                        posY = yoffset;
//
//                        break;
//
//                    case EMPTY:
//
//                        posY = this.mViewPortHandler.getChartHeight() - yoffset - this.mLegend.mNeededHeight;
//
//                        break;
//
//                    case DEFAULT:
//
//                        posY = (this.mViewPortHandler.getChartHeight() - this.mLegend.mNeededHeight) / 2.0F + yoffset;
//
//                        break;
//
//                }
//
//                lineIndex = 0;
//
//                for (i = 0, count = entries.length; i < count; i++) {
//
//                    LegendEntry e = entries[i];
//
//                    boolean drawingForm = (e.form != Legend.LegendForm.NONE);
//
//                    float formSize = Float.isNaN(e.formSize) ? defaultFormSize : Utils.convertDpToPixel(e.formSize);
//
//                    if (i < calculatedLabelBreakPoints.size() && ((Boolean) calculatedLabelBreakPoints.get(i)).booleanValue()) {
//
//                        posX = originPosX;
//
//                        posY += labelLineHeight + labelLineSpacing;
//
//                    }
//
//                    if (posX == originPosX && horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER && lineIndex < calculatedLineSizes.size()) {
//
//                        posX += ((direction == Legend.LegendDirection.RIGHT_TO_LEFT) ? ((FSize) calculatedLineSizes.get(lineIndex)).width : -((FSize) calculatedLineSizes.get(lineIndex)).width) / 2.0F;
//
//                        lineIndex++;
//
//                    }
//
//                    boolean isStacked = (e.label == null);
//
//                    if (drawingForm) {
//
//                        if (direction == Legend.LegendDirection.RIGHT_TO_LEFT)
//                         posX -= formSize;
//
//                        drawForm(c, posX, posY + formYOffset, e, this.mLegend);
//
//                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT)
//                         posX += formSize;
//
//                    }
//
//                    if (!isStacked) {
//
//                        if (drawingForm)
//
//                            posX += ((direction == Legend.LegendDirection.RIGHT_TO_LEFT) ? -formToTextSpace : formToTextSpace);
//
//                        if (direction == Legend.LegendDirection.RIGHT_TO_LEFT)
//                         posX -= ((FSize) calculatedLabelSizes.get(i)).width;
//
//                        drawLabel(c, posX, posY + labelLineHeight, e.label);
//
//                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT)
//                         posX += ((FSize) calculatedLabelSizes.get(i)).width;
//
//                        posX += ((direction == Legend.LegendDirection.RIGHT_TO_LEFT) ? -xEntrySpace : xEntrySpace);
//
//                    } else {
//
//                        posX += ((direction == Legend.LegendDirection.RIGHT_TO_LEFT) ? -stackSpace : stackSpace);
//
//                    }
//
//                }
//
//                break;
//
//            case EMPTY:
//
//                stack = 0.0F;
//
//                wasStacked = false;
//
//                posY = 0.0F;
//
//                switch (verticalAlignment) {
//
//                    case NONE:
//
//                        posY = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER) ? 0.0F : this.mViewPortHandler.contentTop();
//
//                        posY += yoffset;
//
//                        break;
//
//                    case EMPTY:
//
//                        posY = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER) ? this.mViewPortHandler.getChartHeight() : this.mViewPortHandler.contentBottom();
//
//                        posY -= this.mLegend.mNeededHeight + yoffset;
//
//                        break;
//
//                    case DEFAULT:
//
//                        posY = this.mViewPortHandler.getChartHeight() / 2.0F - this.mLegend.mNeededHeight / 2.0F + this.mLegend.getYOffset();
//
//                        break;
//
//                }
//
//                for (i = 0; i < entries.length; i++) {
//
//                    LegendEntry e = entries[i];
//
//                    boolean drawingForm = (e.form != Legend.LegendForm.NONE);
//
//                    float formSize = Float.isNaN(e.formSize) ? defaultFormSize : Utils.convertDpToPixel(e.formSize);
//
//                    float posX = originPosX;
//
//                    if (drawingForm) {
//
//                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
//
//                            posX += stack;
//
//                        } else {
//
//                            posX -= formSize - stack;
//
//                        }
//
//                        drawForm(c, posX, posY + formYOffset, e, this.mLegend);
//
//                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT)
//                         posX += formSize;
//
//                    }
//
//                    if (e.label != null) {
//
//                        if (drawingForm && !wasStacked) {
//
//                            posX += ((direction == Legend.LegendDirection.LEFT_TO_RIGHT) ? formToTextSpace : -formToTextSpace);
//
//                        } else if (wasStacked) {
//
//                            posX = originPosX;
//
//                        }
//
//                        if (direction == Legend.LegendDirection.RIGHT_TO_LEFT)
//                         posX -= Utils.calcTextWidth(this.mLegendLabelPaint, e.label);
//
//                        if (!wasStacked) {
//
//                            drawLabel(c, posX, posY + labelLineHeight, e.label);
//
//                        } else {
//
//                            posY += labelLineHeight + labelLineSpacing;
//
//                            drawLabel(c, posX, posY + labelLineHeight, e.label);
//
//                        }
//
//                        posY += labelLineHeight + labelLineSpacing;
//
//                        stack = 0.0F;
//
//                    } else {
//
//                        stack += formSize + stackSpace;
//
//                        wasStacked = true;
//
//                    }
//
//                }
//
//                break;
        
        }
    
    }

}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\renderer\LegendRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */