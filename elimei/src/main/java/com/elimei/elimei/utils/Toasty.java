package com.elimei.elimei.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elimei.R;


@SuppressLint({"InflateParams"})
public class Toasty {
    @ColorInt
    /*  41 */ private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");

    @ColorInt
    /*  44 */ private static final int ERROR_COLOR = Color.parseColor("#D50000");
    @ColorInt
    /*  46 */ private static final int INFO_COLOR = Color.parseColor("#3F51B5");
    @ColorInt
    /*  48 */ private static final int SUCCESS_COLOR = Color.parseColor("#388E3C");
    @ColorInt
    /*  50 */ private static final int WARNING_COLOR = Color.parseColor("#FFA900");


    private static final String TOAST_TYPEFACE = "sans-serif-condensed";


    @CheckResult
    /*  59 */ public static Toast normal(@NonNull Context context, @NonNull CharSequence message) {
        return normal(context, message, 0, null, false);
    }


    @CheckResult
    /*  64 */ public static Toast normal(@NonNull Context context, @NonNull CharSequence message, Drawable icon) {
        return normal(context, message, 0, icon, true);
    }


    @CheckResult
    /*  69 */ public static Toast normal(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return normal(context, message, duration, null, false);
    }


    @CheckResult
    /*  75 */ public static Toast normal(@NonNull Context context, @NonNull CharSequence message, int duration, Drawable icon) {
        return normal(context, message, duration, icon, true);
    }


    @CheckResult
    /*  81 */ public static Toast normal(@NonNull Context context, @NonNull CharSequence message, int duration, Drawable icon, boolean withIcon) {
        return custom(context, message, icon, DEFAULT_TEXT_COLOR, duration, withIcon);
    }


    @CheckResult
    /*  86 */ public static Toast warning(@NonNull Context context, @NonNull CharSequence message) {
        return warning(context, message, 0, true);
    }


    @CheckResult
    /*  91 */ public static Toast warning(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return warning(context, message, duration, true);
    }


    @CheckResult
    /*  96 */ public static Toast warning(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_error_outline_white_48dp), DEFAULT_TEXT_COLOR, WARNING_COLOR, duration, withIcon, true);
    }


    @CheckResult
    /* 103 */ public static Toast info(@NonNull Context context, @NonNull CharSequence message) {
        return info(context, message, 0, true);
    }


    @CheckResult
    /* 108 */ public static Toast info(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return info(context, message, duration, true);
    }


    @CheckResult
    /* 113 */ public static Toast info(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_info_outline_white_48dp), DEFAULT_TEXT_COLOR, INFO_COLOR, duration, withIcon, true);
    }


    @CheckResult
    /* 119 */ public static Toast success(@NonNull Context context, @NonNull CharSequence message) {
        return success(context, message, 0, true);
    }


    @CheckResult
    /* 124 */ public static Toast success(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return success(context, message, duration, true);
    }


    @CheckResult
    /* 129 */ public static Toast success(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_check_white_48dp), DEFAULT_TEXT_COLOR, SUCCESS_COLOR, duration, withIcon, true);
    }


    @CheckResult
    /* 135 */ public static Toast error(@NonNull Context context, @NonNull CharSequence message) {
        return error(context, message, 0, true);
    }


    @CheckResult
    /* 140 */ public static Toast error(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return error(context, message, duration, true);
    }


    @CheckResult
    /* 145 */ public static Toast error(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_clear_white_48dp), DEFAULT_TEXT_COLOR, ERROR_COLOR, duration, withIcon, true);
    }


    @CheckResult
    /* 152 */ public static Toast custom(@NonNull Context context, @NonNull CharSequence message, Drawable icon, @ColorInt int textColor, int duration, boolean withIcon) {
        return custom(context, message, icon, textColor, -1, duration, withIcon, false);
    }


    @CheckResult
    /* 159 */ public static Toast custom(@NonNull Context context, @NonNull CharSequence message, @DrawableRes int iconRes, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        return custom(context, message, ToastyUtils.getDrawable(context, iconRes), textColor, tintColor, duration, withIcon, shouldTint);
    }


    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull CharSequence message, Drawable icon, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        Drawable drawableFrame;
        /* 167 */
        Toast currentToast = new Toast(context);

        /* 169 */
        View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_layout, null);
        /* 170 */
        ImageView toastIcon = (ImageView) toastLayout.findViewById(R.id.toast_icon);
        /* 171 */
        TextView toastTextView = (TextView) toastLayout.findViewById(R.id.toast_text);


        /* 174 */
        if (shouldTint) {
            /* 175 */
            drawableFrame = ToastyUtils.tint9PatchDrawableFrame(context, tintColor);
        } else {
            /* 177 */
            drawableFrame = ToastyUtils.getDrawable(context, R.drawable.toast_frame);
            /* 178 */
        }
        ToastyUtils.setBackground(toastLayout, drawableFrame);

        /* 180 */
        if (withIcon) {
            /* 181 */
            if (icon == null)
                /* 182 */
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            /* 183 */
            ToastyUtils.setBackground(toastIcon, icon);
        } else {
            /* 185 */
            toastIcon.setVisibility(View.GONE);
        }
        /* 187 */
        toastTextView.setTextColor(textColor);
        /* 188 */
        toastTextView.setText(message);
        /* 189 */
        toastTextView.setTypeface(Typeface.create("sans-serif-condensed", Toast.LENGTH_SHORT));

        /* 191 */
        currentToast.setView(toastLayout);
        /* 192 */
        currentToast.setDuration(duration);
        /* 193 */
        return currentToast;
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\Toasty.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */