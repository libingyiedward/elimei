package com.elimei.elimei.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.elimei.R;


final class ToastyUtils {
    static Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int tintColor) {
        /* 39 */
        NinePatchDrawable toastDrawable = (NinePatchDrawable) getDrawable(context, R.drawable.toast_frame);

        /* 41 */
        toastDrawable.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        /* 42 */
        return toastDrawable;
    }

    static void setBackground(@NonNull View view, Drawable drawable) {
        /* 46 */
        if (Build.VERSION.SDK_INT >= 16) {
            /* 47 */
            view.setBackground(drawable);
        } else {
            /* 49 */
            view.setBackgroundDrawable(drawable);
        }
    }

    static Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        /* 53 */
        if (Build.VERSION.SDK_INT >= 21) {
            /* 54 */
            return context.getDrawable(id);
        }
        /* 56 */
        return context.getResources().getDrawable(id);
    }
}
