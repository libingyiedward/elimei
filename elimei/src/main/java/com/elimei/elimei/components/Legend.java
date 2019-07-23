package com.elimei.elimei.components;

import android.graphics.DashPathEffect;
import android.graphics.Paint;

import com.elimei.elimei.utils.FSize;
import com.elimei.elimei.utils.Utils;
import com.elimei.elimei.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;


public class Legend
        extends ComponentBase {
    @Deprecated
    public enum LegendPosition {
        RIGHT_OF_CHART, RIGHT_OF_CHART_CENTER, RIGHT_OF_CHART_INSIDE,
        LEFT_OF_CHART, LEFT_OF_CHART_CENTER, LEFT_OF_CHART_INSIDE,
        BELOW_CHART_LEFT, BELOW_CHART_RIGHT, BELOW_CHART_CENTER,
        ABOVE_CHART_LEFT, ABOVE_CHART_RIGHT, ABOVE_CHART_CENTER,
        PIECHART_CENTER;
    }


    public enum LegendForm {
        NONE,
        EMPTY,
        DEFAULT,
        SQUARE,
        CIRCLE,
        LINE;
    }

    public enum LegendHorizontalAlignment {
        /*   70 */     LEFT, CENTER, RIGHT;
    }

    public enum LegendVerticalAlignment {
        /*   74 */     TOP, CENTER, BOTTOM;
    }

    public enum LegendOrientation {
        /*   78 */     HORIZONTAL, VERTICAL;
    }

    public enum LegendDirection {
        /*   82 */     LEFT_TO_RIGHT, RIGHT_TO_LEFT;
    }


    /*   88 */   private LegendEntry[] mEntries = new LegendEntry[0];


    private LegendEntry[] mExtraEntries;


    private boolean mIsLegendCustom = false;


    /*  102 */   private LegendHorizontalAlignment mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
    /*  103 */   private LegendVerticalAlignment mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
    /*  104 */   private LegendOrientation mOrientation = LegendOrientation.HORIZONTAL;


    private boolean mDrawInside = false;


    /*  110 */   private LegendDirection mDirection = LegendDirection.LEFT_TO_RIGHT;


    /*  115 */   private LegendForm mShape = LegendForm.SQUARE;


    /*  120 */   private float mFormSize = 8.0F;


    /*  125 */   private float mFormLineWidth = 3.0F;


    /*  130 */   private DashPathEffect mFormLineDashEffect = null;


    /*  135 */   private float mXEntrySpace = 6.0F;


    /*  140 */   private float mYEntrySpace = 0.0F;


    /*  147 */   private float mFormToTextSpace = 5.0F;


    /*  152 */   private float mStackSpace = 3.0F;


    /*  157 */   private float mMaxSizePercent = 0.95F;


    public Legend() {
    }


    public Legend(LegendEntry[] entries) {
        /*  175 */
        this();

        /*  177 */
        if (entries == null) {
            /*  178 */
            throw new IllegalArgumentException("entries array is NULL");
        }

        /*  181 */
        this.mEntries = entries;
    }

    @Deprecated
    public Legend(int[] colors, String[] labels) {
        /*  186 */
        this();

        /*  188 */
        if (colors == null || labels == null) {
            /*  189 */
            throw new IllegalArgumentException("colors array or labels array is NULL");
        }

        /*  192 */
        if (colors.length != labels.length) {
            /*  193 */
            throw new IllegalArgumentException("colors array and labels array need to be of same size");
        }


        /*  197 */
        List<LegendEntry> entries = new ArrayList<LegendEntry>();

        /*  199 */
        for (int i = 0; i < Math.min(colors.length, labels.length); i++) {
            /*  200 */
            LegendEntry entry = new LegendEntry();
            /*  201 */
            entry.formColor = colors[i];
            /*  202 */
            entry.label = labels[i];

            /*  204 */
            if (entry.formColor == 1122868) {
                /*  205 */
                entry.form = LegendForm.NONE;
                /*  206 */
            } else if (entry.formColor == 1122867 || entry.formColor == 0) {

                /*  208 */
                entry.form = LegendForm.EMPTY;
            }
            /*  210 */
            entries.add(entry);
        }

        /*  213 */
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }


    @Deprecated
    /*  218 */ public Legend(List<Integer> colors, List<String> labels) {
        this(Utils.convertIntegers(colors), Utils.convertStrings(labels));
    }


    /*  227 */
    public void setEntries(List<LegendEntry> entries) {
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }


    /*  231 */
    public LegendEntry[] getEntries() {
        return this.mEntries;
    }


    public float getMaximumEntryWidth(Paint p) {
        /*  243 */
        float max = 0.0F;
        /*  244 */
        float maxFormSize = 0.0F;
        /*  245 */
        float formToTextSpace = Utils.convertDpToPixel(this.mFormToTextSpace);

        /*  247 */
        for (LegendEntry entry : this.mEntries) {
            /*  248 */
            float formSize = Utils.convertDpToPixel(
                    /*  249 */           Float.isNaN(entry.formSize) ? this.mFormSize : entry.formSize);

            /*  251 */
            if (formSize > maxFormSize) {
                /*  252 */
                maxFormSize = formSize;
            }
            /*  254 */
            String label = entry.label;
            /*  255 */
            if (label != null) {

                /*  257 */
                float length = Utils.calcTextWidth(p, label);

                /*  259 */
                if (length > max)
                    /*  260 */ max = length;
            }
        }
        /*  263 */
        return max + maxFormSize + formToTextSpace;
    }


    public float getMaximumEntryHeight(Paint p) {
        /*  274 */
        float max = 0.0F;

        /*  276 */
        for (LegendEntry entry : this.mEntries) {
            /*  277 */
            String label = entry.label;
            /*  278 */
            if (label != null) {

                /*  280 */
                float length = Utils.calcTextHeight(p, label);

                /*  282 */
                if (length > max)
                    /*  283 */ max = length;
            }
        }
        /*  286 */
        return max;
    }


    @Deprecated
    public int[] getColors() {
        /*  292 */
        int[] old = new int[this.mEntries.length];
        /*  293 */
        for (int i = 0; i < this.mEntries.length; i++) {
            /*  294 */
            old[i] = ((this.mEntries[i]).form == LegendForm.NONE) ? 1122868 : (((this.mEntries[i]).form == LegendForm.EMPTY) ? 1122867 : (this.mEntries[i]).formColor);
        }


        /*  298 */
        return old;
    }


    @Deprecated
    public String[] getLabels() {
        /*  304 */
        String[] old = new String[this.mEntries.length];
        /*  305 */
        for (int i = 0; i < this.mEntries.length; i++) {
            /*  306 */
            old[i] = (this.mEntries[i]).label;
        }
        /*  308 */
        return old;
    }


    @Deprecated
    public int[] getExtraColors() {
        /*  314 */
        int[] old = new int[this.mExtraEntries.length];
        /*  315 */
        for (int i = 0; i < this.mExtraEntries.length; i++) {
            /*  316 */
            old[i] = ((this.mExtraEntries[i]).form == LegendForm.NONE) ? 1122868 : (((this.mExtraEntries[i]).form == LegendForm.EMPTY) ? 1122867 : (this.mExtraEntries[i]).formColor);
        }


        /*  320 */
        return old;
    }


    @Deprecated
    public String[] getExtraLabels() {
        /*  326 */
        String[] old = new String[this.mExtraEntries.length];
        /*  327 */
        for (int i = 0; i < this.mExtraEntries.length; i++) {
            /*  328 */
            old[i] = (this.mExtraEntries[i]).label;
        }
        /*  330 */
        return old;
    }


    /*  335 */
    public LegendEntry[] getExtraEntries() {
        return this.mExtraEntries;
    }


    /*  339 */
    public void setExtra(List<LegendEntry> entries) {
        this.mExtraEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }


    public void setExtra(LegendEntry[] entries) {
        /*  343 */
        if (entries == null)
            /*  344 */ entries = new LegendEntry[0];
        /*  345 */
        this.mExtraEntries = entries;
    }


    @Deprecated
    /*  350 */ public void setExtra(List<Integer> colors, List<String> labels) {
        setExtra(Utils.convertIntegers(colors), Utils.convertStrings(labels));
    }


    public void setExtra(int[] colors, String[] labels) {
        /*  361 */
        List<LegendEntry> entries = new ArrayList<LegendEntry>();

        /*  363 */
        for (int i = 0; i < Math.min(colors.length, labels.length); i++) {
            /*  364 */
            LegendEntry entry = new LegendEntry();
            /*  365 */
            entry.formColor = colors[i];
            /*  366 */
            entry.label = labels[i];

            /*  368 */
            if (entry.formColor == 1122868 || entry.formColor == 0) {

                /*  370 */
                entry.form = LegendForm.NONE;
                /*  371 */
            } else if (entry.formColor == 1122867) {
                /*  372 */
                entry.form = LegendForm.EMPTY;
            }
            /*  374 */
            entries.add(entry);
        }

        /*  377 */
        this.mExtraEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }


    public void setCustom(LegendEntry[] entries) {
        /*  390 */
        this.mEntries = entries;
        /*  391 */
        this.mIsLegendCustom = true;
    }


    public void setCustom(List<LegendEntry> entries) {
        /*  404 */
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
        /*  405 */
        this.mIsLegendCustom = true;
    }


    /*  414 */
    public void resetCustom() {
        this.mIsLegendCustom = false;
    }


    /*  422 */
    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }


    @Deprecated
    public LegendPosition getPosition() {
        /*  432 */
        if (this.mOrientation == LegendOrientation.VERTICAL && this.mHorizontalAlignment == LegendHorizontalAlignment.CENTER && this.mVerticalAlignment == LegendVerticalAlignment.CENTER) {

            /*  435 */
            return LegendPosition.PIECHART_CENTER;
        }
        /*  436 */
        if (this.mOrientation == LegendOrientation.HORIZONTAL) {
            /*  437 */
            if (this.mVerticalAlignment == LegendVerticalAlignment.TOP) {
                /*  438 */
                return (this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT) ? LegendPosition.ABOVE_CHART_LEFT : ((this.mHorizontalAlignment == LegendHorizontalAlignment.RIGHT) ? LegendPosition.ABOVE_CHART_RIGHT : LegendPosition.ABOVE_CHART_CENTER);
            }




            /*  444 */
            return (this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT) ? LegendPosition.BELOW_CHART_LEFT : ((this.mHorizontalAlignment == LegendHorizontalAlignment.RIGHT) ? LegendPosition.BELOW_CHART_RIGHT : LegendPosition.BELOW_CHART_CENTER);
        }




        /*  450 */
        if (this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT) {
            /*  451 */
            return (this.mVerticalAlignment == LegendVerticalAlignment.TOP && this.mDrawInside) ? LegendPosition.LEFT_OF_CHART_INSIDE : ((this.mVerticalAlignment == LegendVerticalAlignment.CENTER) ? LegendPosition.LEFT_OF_CHART_CENTER : LegendPosition.LEFT_OF_CHART);
        }




        /*  457 */
        return (this.mVerticalAlignment == LegendVerticalAlignment.TOP && this.mDrawInside) ? LegendPosition.RIGHT_OF_CHART_INSIDE : ((this.mVerticalAlignment == LegendVerticalAlignment.CENTER) ? LegendPosition.RIGHT_OF_CHART_CENTER : LegendPosition.RIGHT_OF_CHART);
    }


    @Deprecated
    public void setPosition(LegendPosition newValue) {
        /*  472 */
        switch (newValue) {
//            case VERTICAL:
//            case HORIZONTAL:
//            case null:
//                /*  476 */
//                this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
//                /*  477 */
//                this.mVerticalAlignment = (newValue == LegendPosition.LEFT_OF_CHART_CENTER) ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
//
//
//                /*  480 */
//                this.mOrientation = LegendOrientation.VERTICAL;
//                break;
//
//            case null:
//            case null:
//            case null:
//                /*  486 */
//                this.mHorizontalAlignment = LegendHorizontalAlignment.RIGHT;
//                /*  487 */
//                this.mVerticalAlignment = (newValue == LegendPosition.RIGHT_OF_CHART_CENTER) ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
//
//
//                /*  490 */
//                this.mOrientation = LegendOrientation.VERTICAL;
//                break;
//
//            case null:
//            case null:
//            case null:
//                /*  496 */
//                this.mHorizontalAlignment = (newValue == LegendPosition.ABOVE_CHART_LEFT) ? LegendHorizontalAlignment.LEFT : ((newValue == LegendPosition.ABOVE_CHART_RIGHT) ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER);
//
//
//
//
//                /*  501 */
//                this.mVerticalAlignment = LegendVerticalAlignment.TOP;
//                /*  502 */
//                this.mOrientation = LegendOrientation.HORIZONTAL;
//                break;
//
//            case null:
//                /*  508 */
//                this.mHorizontalAlignment = (newValue == LegendPosition.BELOW_CHART_LEFT) ? LegendHorizontalAlignment.LEFT : ((newValue == LegendPosition.BELOW_CHART_RIGHT) ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER);
//
//
//
//
//                /*  513 */
//                this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
//                /*  514 */
//                this.mOrientation = LegendOrientation.HORIZONTAL;
//                break;
//
//            case null:
//                /*  518 */
//                this.mHorizontalAlignment = LegendHorizontalAlignment.CENTER;
//                /*  519 */
//                this.mVerticalAlignment = LegendVerticalAlignment.CENTER;
//                /*  520 */
//                this.mOrientation = LegendOrientation.VERTICAL;
//                break;
        }

        /*  524 */
        this.mDrawInside = (newValue == LegendPosition.LEFT_OF_CHART_INSIDE || newValue == LegendPosition.RIGHT_OF_CHART_INSIDE);
    }


    /*  534 */
    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }


    /*  543 */
    public void setHorizontalAlignment(LegendHorizontalAlignment value) {
        this.mHorizontalAlignment = value;
    }


    /*  552 */
    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }


    /*  561 */
    public void setVerticalAlignment(LegendVerticalAlignment value) {
        this.mVerticalAlignment = value;
    }


    /*  570 */
    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }


    /*  579 */
    public void setOrientation(LegendOrientation value) {
        this.mOrientation = value;
    }


    /*  588 */
    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }


    /*  597 */
    public void setDrawInside(boolean value) {
        this.mDrawInside = value;
    }


    /*  606 */
    public LegendDirection getDirection() {
        return this.mDirection;
    }


    /*  615 */
    public void setDirection(LegendDirection pos) {
        this.mDirection = pos;
    }


    /*  624 */
    public LegendForm getForm() {
        return this.mShape;
    }


    /*  633 */
    public void setForm(LegendForm shape) {
        this.mShape = shape;
    }


    /*  642 */
    public void setFormSize(float size) {
        this.mFormSize = size;
    }


    /*  651 */
    public float getFormSize() {
        return this.mFormSize;
    }


    /*  660 */
    public void setFormLineWidth(float size) {
        this.mFormLineWidth = size;
    }


    /*  669 */
    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }


    /*  678 */
    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }


    /*  685 */
    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }


    /*  695 */
    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }


    /*  705 */
    public void setXEntrySpace(float space) {
        this.mXEntrySpace = space;
    }


    /*  714 */
    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }


    /*  724 */
    public void setYEntrySpace(float space) {
        this.mYEntrySpace = space;
    }


    /*  733 */
    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }


    /*  743 */
    public void setFormToTextSpace(float space) {
        this.mFormToTextSpace = space;
    }


    /*  752 */
    public float getStackSpace() {
        return this.mStackSpace;
    }


    /*  761 */
    public void setStackSpace(float space) {
        this.mStackSpace = space;
    }


    /*  767 */   public float mNeededWidth = 0.0F;


    /*  772 */   public float mNeededHeight = 0.0F;

    /*  774 */   public float mTextHeightMax = 0.0F;

    /*  776 */   public float mTextWidthMax = 0.0F;


    private boolean mWordWrapEnabled = false;


    /*  793 */
    public void setWordWrapEnabled(boolean enabled) {
        this.mWordWrapEnabled = enabled;
    }


    /*  803 */
    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }


    /*  817 */
    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }


    /*  829 */
    public void setMaxSizePercent(float maxSize) {
        this.mMaxSizePercent = maxSize;
    }


    /*  832 */   private List<FSize> mCalculatedLabelSizes = new ArrayList(16);
    /*  833 */   private List<Boolean> mCalculatedLabelBreakPoints = new ArrayList(16);
    /*  834 */   private List<FSize> mCalculatedLineSizes = new ArrayList(16);


    /*  837 */
    public List<FSize> getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }


    /*  841 */
    public List<Boolean> getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }


    /*  845 */
    public List<FSize> getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }


    public void calculateDimensions(Paint labelpaint, ViewPortHandler viewPortHandler) {
        int i, stackedStartIndex;
        /*  857 */
        float requiredWidth, currentLineWidth, maxLineWidth, contentWidth, labelLineSpacing, maxHeight, labelLineHeight, defaultFormSize = Utils.convertDpToPixel(this.mFormSize);
        /*  858 */
        float stackSpace = Utils.convertDpToPixel(this.mStackSpace);
        /*  859 */
        float formToTextSpace = Utils.convertDpToPixel(this.mFormToTextSpace);
        /*  860 */
        float xEntrySpace = Utils.convertDpToPixel(this.mXEntrySpace);
        /*  861 */
        float yEntrySpace = Utils.convertDpToPixel(this.mYEntrySpace);
        /*  862 */
        boolean wordWrapEnabled = this.mWordWrapEnabled;
        /*  863 */
        LegendEntry[] entries = this.mEntries;
        /*  864 */
        int entryCount = entries.length;

        /*  866 */
        this.mTextWidthMax = getMaximumEntryWidth(labelpaint);
        /*  867 */
        this.mTextHeightMax = getMaximumEntryHeight(labelpaint);

        /*  869 */
        switch (this.mOrientation) {

            case VERTICAL:
//                /*  872 */
//                maxWidth = 0.0F;
//                maxHeight = 0.0F;
//                width = 0.0F;
//                /*  873 */
//                labelLineHeight = Utils.getLineHeight(labelpaint);
//                /*  874 */
//                wasStacked = false;
//
//                /*  876 */
//                for (i = 0; i < entryCount; i++) {
//
//                    /*  878 */
//                    LegendEntry e = entries[i];
//                    /*  879 */
//                    boolean drawingForm = (e.form != LegendForm.NONE);
//
//
//                    /*  882 */
//                    float formSize = Float.isNaN(e.formSize) ? defaultFormSize : Utils.convertDpToPixel(e.formSize);
//                    /*  883 */
//                    String label = e.label;
//
//                    /*  885 */
//                    if (!wasStacked) {
//                        /*  886 */
//                        width = 0.0F;
//                    }
//                    /*  888 */
//                    if (drawingForm) {
//                        /*  889 */
//                        if (wasStacked)
//                            /*  890 */ width += stackSpace;
//                        /*  891 */
//                        width += formSize;
//                    }
//
//
//                    /*  895 */
//                    if (label != null) {
//
//
//                        /*  898 */
//                        if (drawingForm && !wasStacked) {
//                            /*  899 */
//                            width += formToTextSpace;
//                            /*  900 */
//                        } else if (wasStacked) {
//                            /*  901 */
//                            maxWidth = Math.max(maxWidth, width);
//                            /*  902 */
//                            maxHeight += labelLineHeight + yEntrySpace;
//                            /*  903 */
//                            width = 0.0F;
//                            /*  904 */
//                            wasStacked = false;
//                        }
//
//                        /*  907 */
//                        width += Utils.calcTextWidth(labelpaint, label);
//
//                        /*  909 */
//                        if (i < entryCount - 1)
//                            /*  910 */ maxHeight += labelLineHeight + yEntrySpace;
//                    } else {
//                        /*  912 */
//                        wasStacked = true;
//                        /*  913 */
//                        width += formSize;
//                        /*  914 */
//                        if (i < entryCount - 1) {
//                            /*  915 */
//                            width += stackSpace;
//                        }
//                    }
//                    /*  918 */
//                    maxWidth = Math.max(maxWidth, width);
//                }
//
//                /*  921 */
//                this.mNeededWidth = maxWidth;
//                /*  922 */
//                this.mNeededHeight = maxHeight;
                break;


            case HORIZONTAL:
                /*  928 */
                labelLineHeight = Utils.getLineHeight(labelpaint);
                /*  929 */
                labelLineSpacing = Utils.getLineSpacing(labelpaint) + yEntrySpace;
                /*  930 */
                contentWidth = viewPortHandler.contentWidth() * this.mMaxSizePercent;


                /*  933 */
                maxLineWidth = 0.0F;
                /*  934 */
                currentLineWidth = 0.0F;
                /*  935 */
                requiredWidth = 0.0F;
                /*  936 */
                stackedStartIndex = -1;

                /*  938 */
                this.mCalculatedLabelBreakPoints.clear();
                /*  939 */
                this.mCalculatedLabelSizes.clear();
                /*  940 */
                this.mCalculatedLineSizes.clear();

                /*  942 */
                for (i = 0; i < entryCount; i++) {

                    /*  944 */
                    LegendEntry e = entries[i];
                    /*  945 */
                    boolean drawingForm = (e.form != LegendForm.NONE);


                    /*  948 */
                    float formSize = Float.isNaN(e.formSize) ? defaultFormSize : Utils.convertDpToPixel(e.formSize);
                    /*  949 */
                    String label = e.label;

                    /*  951 */
                    this.mCalculatedLabelBreakPoints.add(Boolean.valueOf(false));

                    /*  953 */
                    if (stackedStartIndex == -1) {


                        /*  956 */
                        requiredWidth = 0.0F;
                    } else {

                        /*  959 */
                        requiredWidth += stackSpace;
                    }


                    /*  963 */
                    if (label != null) {

                        /*  965 */
                        this.mCalculatedLabelSizes.add(Utils.calcTextSize(labelpaint, label));
                        /*  966 */
                        requiredWidth += (drawingForm ? (formToTextSpace + formSize) : 0.0F);
                        /*  967 */
                        requiredWidth += ((FSize) this.mCalculatedLabelSizes.get(i)).width;
                    } else {

                        /*  970 */
                        this.mCalculatedLabelSizes.add(FSize.getInstance(0.0F, 0.0F));
                        /*  971 */
                        requiredWidth += (drawingForm ? formSize : 0.0F);

                        /*  973 */
                        if (stackedStartIndex == -1) {
                            /*  975 */
                            stackedStartIndex = i;
                        }
                    }

                    /*  979 */
                    if (label != null || i == entryCount - 1) {

                        /*  981 */
                        float requiredSpacing = (currentLineWidth == 0.0F) ? 0.0F : xEntrySpace;

                        /*  983 */
                        if (!wordWrapEnabled || currentLineWidth == 0.0F || contentWidth - currentLineWidth >= requiredSpacing + requiredWidth) {






                            /*  990 */
                            currentLineWidth += requiredSpacing + requiredWidth;
                        } else {

                            /*  994 */
                            this.mCalculatedLineSizes.add(FSize.getInstance(currentLineWidth, labelLineHeight));
                            /*  995 */
                            maxLineWidth = Math.max(maxLineWidth, currentLineWidth);


                            /*  998 */
                            this.mCalculatedLabelBreakPoints.set((stackedStartIndex > -1) ? stackedStartIndex : i,

                                    /* 1000 */                   Boolean.valueOf(true));
                            /* 1001 */
                            currentLineWidth = requiredWidth;
                        }
                        if (i == entryCount - 1) {

                            this.mCalculatedLineSizes.add(FSize.getInstance(currentLineWidth, labelLineHeight));

                            maxLineWidth = Math.max(maxLineWidth, currentLineWidth);
                        }
                    }

                    stackedStartIndex = (label != null) ? -1 : stackedStartIndex;
                }

                this.mNeededWidth = maxLineWidth;

                this.mNeededHeight = labelLineHeight * this.mCalculatedLineSizes.size() +
                        labelLineSpacing * ((this.mCalculatedLineSizes.size() == 0) ? 0 : (this.mCalculatedLineSizes.size() - 1));
                break;
        }



        /* 1026 */
        this.mNeededHeight += this.mYOffset;
        /* 1027 */
        this.mNeededWidth += this.mXOffset;
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\components\Legend.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */