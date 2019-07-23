
package com.elimei.elimei.utils;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.database.ContentObserver;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@TargetApi(19)
public class ImmersionBar {
    /*   45 */   private static Map<String, BarParams> mMap = new HashMap();
    /*   46 */   private static Map<String, BarParams> mTagMap = new HashMap();
    /*   47 */   private static Map<String, ArrayList<String>> mTagKeyMap = new HashMap();
    private static int height;

    private Activity mActivity;

    private Window mWindow;

    private ViewGroup mDecorView;

    private ViewGroup mContentView;

    private Dialog mDialog;

    private BarParams mBarParams;

    private BarConfig mConfig;

    private String mActivityName;

    private String mFragmentName;

    private String mImmersionBarName;
    private static final String NAVIGATIONBAR_IS_MIN = "navigationbar_is_min";


    private ImmersionBar(Activity activity) {
        /*   71 */
        WeakReference<Activity> activityWeakReference = new WeakReference<Activity>(activity);
        /*   72 */
        this.mActivity = (Activity) activityWeakReference.get();
        /*   73 */
        this.mWindow = this.mActivity.getWindow();
        /*   74 */
        this.mActivityName = activity.getClass().getName();
        /*   75 */
        this.mImmersionBarName = this.mActivityName;
        /*   76 */
        initParams();

    }


    /*   86 */
    private ImmersionBar(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }


    private ImmersionBar(Activity activity, Fragment fragment) {
        /*   90 */
        if (activity == null) {
            /*   91 */
            throw new IllegalArgumentException("Activity不能为空!!!");

        }
        /*   93 */
        WeakReference<Activity> activityWeakReference = new WeakReference<Activity>(activity);
        /*   94 */
        WeakReference<Fragment> fragmentWeakReference = new WeakReference<Fragment>(fragment);
        /*   95 */
        this.mActivity = (Activity) activityWeakReference.get();
        /*   96 */
        this.mWindow = this.mActivity.getWindow();
        /*   97 */
        this.mActivityName = this.mActivity.getClass().getName();
        /*   98 */
        this.mFragmentName = this.mActivityName + "_AND_" + ((Fragment) fragmentWeakReference.get()).getClass().getName();
        /*   99 */
        this.mImmersionBarName = this.mFragmentName;
        /*  100 */
        initParams();

    }


    private ImmersionBar(DialogFragment dialogFragment, Dialog dialog) {
        /*  104 */
        WeakReference<DialogFragment> dialogFragmentWeakReference = new WeakReference<DialogFragment>(dialogFragment);
        /*  105 */
        WeakReference<Dialog> dialogWeakReference = new WeakReference<Dialog>(dialog);
        /*  106 */
        this.mActivity = ((DialogFragment) dialogFragmentWeakReference.get()).getActivity();
        /*  107 */
        this.mDialog = (Dialog) dialogWeakReference.get();
        /*  108 */
        this.mWindow = this.mDialog.getWindow();
        /*  109 */
        this.mActivityName = this.mActivity.getClass().getName();
        /*  110 */
        this.mImmersionBarName = this.mActivityName + "_AND_" + ((DialogFragment) dialogFragmentWeakReference.get()).getClass().getName();
        /*  111 */
        initParams();

    }


    private ImmersionBar(Activity activity, Dialog dialog, String dialogTag) {
        /*  123 */
        WeakReference<Activity> activityWeakReference = new WeakReference<Activity>(activity);
        /*  124 */
        WeakReference<Dialog> dialogWeakReference = new WeakReference<Dialog>(dialog);
        /*  125 */
        this.mActivity = (Activity) activityWeakReference.get();
        /*  126 */
        this.mDialog = (Dialog) dialogWeakReference.get();
        /*  127 */
        this.mWindow = this.mDialog.getWindow();
        /*  128 */
        this.mActivityName = this.mActivity.getClass().getName();
        /*  129 */
        this.mImmersionBarName = this.mActivityName + "_AND_" + dialogTag;
        /*  130 */
        initParams();

    }


    private void initParams() {
        /*  138 */
        this.mDecorView = (ViewGroup) this.mWindow.getDecorView();
        /*  139 */
        this.mContentView = (ViewGroup) this.mDecorView.findViewById(16908290);
        /*  140 */
        this.mConfig = new BarConfig(this.mActivity);
        /*  141 */
        if (mMap.get(this.mImmersionBarName) == null) {
            /*  142 */
            this.mBarParams = new BarParams();
            /*  143 */
            if (!isEmpty(this.mFragmentName)) {
                /*  144 */
                if (mMap.get(this.mActivityName) == null)
                    /*  145 */
                    throw new IllegalArgumentException("在Fragment里使用时，请先在加载Fragment的Activity里初始化！！！");
                /*  146 */
                if (Build.VERSION.SDK_INT == 19 ||
                        /*  147 */           OSUtils.isEMUI3_1()) {
                    /*  148 */
                    this.mBarParams.statusBarView = ((BarParams) mMap.get(this.mActivityName)).statusBarView;
                    /*  149 */
                    this.mBarParams.navigationBarView = ((BarParams) mMap.get(this.mActivityName)).navigationBarView;

                }
                /*  151 */
                this.mBarParams.keyboardPatch = ((BarParams) mMap.get(this.mActivityName)).keyboardPatch;

            }
            /*  153 */
            mMap.put(this.mImmersionBarName, this.mBarParams);

        } else {
            /*  155 */
            this.mBarParams = (BarParams) mMap.get(this.mImmersionBarName);

        }

    }


    public static ImmersionBar with(@NonNull Activity activity) {
        /*  167 */
        if (activity == null)
            /*  168 */ throw new IllegalArgumentException("Activity不能为null");
        /*  169 */
        return new ImmersionBar(activity);

    }


    public static ImmersionBar with(@NonNull Fragment fragment) {
        /*  180 */
        if (fragment == null)
            /*  181 */ throw new IllegalArgumentException("Fragment不能为null");
        /*  182 */
        return new ImmersionBar(fragment);

    }


    public static ImmersionBar with(@NonNull Activity activity, @NonNull Fragment fragment) {
        /*  186 */
        if (activity == null)
            /*  187 */ throw new IllegalArgumentException("Activity不能为null");
        /*  188 */
        if (fragment == null)
            /*  189 */ throw new IllegalArgumentException("Fragment不能为null");
        /*  190 */
        return new ImmersionBar(activity, fragment);

    }


    public static ImmersionBar with(@NonNull DialogFragment dialogFragment, @NonNull Dialog dialog) {
        /*  194 */
        if (dialogFragment == null)
            /*  195 */ throw new IllegalArgumentException("DialogFragment不能为null");
        /*  196 */
        if (dialog == null)
            /*  197 */ throw new IllegalArgumentException("Dialog不能为null");
        /*  198 */
        return new ImmersionBar(dialogFragment, dialog);

    }


    public static ImmersionBar with(@NonNull Activity activity, @NonNull Dialog dialog, @NonNull String dialogTag) {
        /*  211 */
        if (activity == null)
            /*  212 */ throw new IllegalArgumentException("Activity不能为null");
        /*  213 */
        if (dialog == null)
            /*  214 */ throw new IllegalArgumentException("Dialog不能为null");
        /*  215 */
        if (isEmpty(dialogTag))
            /*  216 */ throw new IllegalArgumentException("tag不能为null或空");
        /*  217 */
        return new ImmersionBar(activity, dialog, dialogTag);

    }


    public ImmersionBar transparentStatusBar() {
        /*  226 */
        this.mBarParams.statusBarColor = 0;
        /*  227 */
        return this;

    }


    public ImmersionBar transparentNavigationBar() {
        /*  236 */
        this.mBarParams.navigationBarColor = 0;
        /*  237 */
        this.mBarParams.navigationBarColorTemp = this.mBarParams.navigationBarColor;
        /*  238 */
        this.mBarParams.fullScreen = true;
        /*  239 */
        return this;

    }


    public ImmersionBar transparentBar() {
        /*  248 */
        this.mBarParams.statusBarColor = 0;
        /*  249 */
        this.mBarParams.navigationBarColor = 0;
        /*  250 */
        this.mBarParams.navigationBarColorTemp = this.mBarParams.navigationBarColor;
        /*  251 */
        this.mBarParams.fullScreen = true;
        /*  252 */
        return this;

    }


    /*  262 */
    public ImmersionBar statusBarColor(@ColorRes int statusBarColor) {
        return statusBarColorInt(Color.WHITE);
    }


    /*  274 */
    public ImmersionBar statusBarColor(@ColorRes int statusBarColor, @FloatRange(from = 0.0D, to = 1.0D) float alpha) {
        return statusBarColorInt(Color.WHITE);
    }


    public ImmersionBar statusBarColor(@ColorRes int statusBarColor, @ColorRes int statusBarColorTransform, @FloatRange(from = 0.0D, to = 1.0D) float alpha) {
        /*  288 */
        return statusBarColorInt(Color.WHITE);
        
    }


    /*  301 */
    public ImmersionBar statusBarColor(String statusBarColor) {
        return statusBarColorInt(Color.parseColor(statusBarColor));
    }


    /*  313 */
    public ImmersionBar statusBarColor(String statusBarColor, @FloatRange(from = 0.0D, to = 1.0D) float alpha) {
        return statusBarColorInt(Color.parseColor(statusBarColor), alpha);
    }


    public ImmersionBar statusBarColor(String statusBarColor, String statusBarColorTransform, @FloatRange(from = 0.0D, to = 1.0D) float alpha) {
        /*  327 */
        return statusBarColorInt(Color.parseColor(statusBarColor),
                /*  328 */         Color.parseColor(statusBarColorTransform), alpha);

    }


    public ImmersionBar statusBarColorInt(@ColorInt int statusBarColor) {
        /*  339 */
        this.mBarParams.statusBarColor = statusBarColor;
        /*  340 */
        return this;

    }


    public ImmersionBar statusBarColorInt(@ColorInt int statusBarColor, @FloatRange(from = 0.0D, to = 1.0D) float alpha) {
        /*  352 */
        this.mBarParams.statusBarColor = statusBarColor;
        /*  353 */
        this.mBarParams.statusBarAlpha = alpha;
        /*  354 */
        return this;

    }


    public ImmersionBar statusBarColorInt(@ColorInt int statusBarColor, @ColorInt int statusBarColorTransform, @FloatRange(from = 0.0D, to = 1.0D) float alpha) {
        /*  368 */
        this.mBarParams.statusBarColor = statusBarColor;
        /*  369 */
        this.mBarParams.statusBarColorTransform = statusBarColorTransform;
        /*  370 */
        this.mBarParams.statusBarAlpha = alpha;
        /*  371 */
        return this;

    }


    /*  381 */
    public ImmersionBar navigationBarColor(@ColorRes int navigationBarColor) {
        return navigationBarColorInt(Color.WHITE);
    }


    /*  393 */
    public ImmersionBar navigationBarColor(@ColorRes int navigationBarColor, @FloatRange(from = 0.0D, to = 1.0D) float navigationAlpha) {
        return navigationBarColorInt(Color.WHITE);
    }


    public ImmersionBar navigationBarColor(@ColorRes int navigationBarColor, @ColorRes int navigationBarColorTransform, @FloatRange(from = 0.0D, to = 1.0D) float navigationAlpha) {
        /*  407 */
        return navigationBarColorInt(Color.WHITE);

    }


    /*  418 */
    public ImmersionBar navigationBarColor(String navigationBarColor) {
        return navigationBarColorInt(Color.parseColor(navigationBarColor));
    }


    /*  430 */
    public ImmersionBar navigationBarColor(String navigationBarColor, @FloatRange(from = 0.0D, to = 1.0D) float navigationAlpha) {
        return navigationBarColorInt(Color.parseColor(navigationBarColor), navigationAlpha);
    }


    public ImmersionBar navigationBarColor(String navigationBarColor, String navigationBarColorTransform, @FloatRange(from = 0.0D, to = 1.0D) float navigationAlpha) {
        /*  444 */
        return navigationBarColorInt(Color.parseColor(navigationBarColor),
                /*  445 */         Color.parseColor(navigationBarColorTransform), navigationAlpha);

    }


    public ImmersionBar navigationBarColorInt(@ColorInt int navigationBarColor) {
        /*  455 */
        this.mBarParams.navigationBarColor = navigationBarColor;
        /*  456 */
        this.mBarParams.navigationBarColorTemp = this.mBarParams.navigationBarColor;
        /*  457 */
        return this;

    }


    public ImmersionBar navigationBarColorInt(@ColorInt int navigationBarColor, @FloatRange(from = 0.0D, to = 1.0D) float navigationAlpha) {
        /*  469 */
        this.mBarParams.navigationBarColor = navigationBarColor;
        /*  470 */
        this.mBarParams.navigationBarAlpha = navigationAlpha;
        /*  471 */
        this.mBarParams.navigationBarColorTemp = this.mBarParams.navigationBarColor;
        /*  472 */
        return this;

    }


    public ImmersionBar navigationBarColorInt(@ColorInt int navigationBarColor, @ColorInt int navigationBarColorTransform, @FloatRange(from = 0.0D, to = 1.0D) float navigationAlpha) {
        /*  486 */
        this.mBarParams.navigationBarColor = navigationBarColor;
        /*  487 */
        this.mBarParams.navigationBarColorTransform = navigationBarColorTransform;
        /*  488 */
        this.mBarParams.navigationBarAlpha = navigationAlpha;
        /*  489 */
        this.mBarParams.navigationBarColorTemp = this.mBarParams.navigationBarColor;
        /*  490 */
        return this;

    }


    /*  500 */
    public ImmersionBar barColor(@ColorRes int barColor) {
        return barColorInt(Color.WHITE);
    }


    /*  511 */
    public ImmersionBar barColor(@ColorRes int barColor, @FloatRange(from = 0.0D, to = 1.0D) float barAlpha) {
        return barColorInt(Color.WHITE, barColor);
    }


    public ImmersionBar barColor(@ColorRes int barColor, @ColorRes int barColorTransform, @FloatRange(from = 0.0D, to = 1.0D) float barAlpha) {
        /*  525 */
        return barColorInt(Color.WHITE, 0f);

    }


    /*  536 */
    public ImmersionBar barColor(String barColor) {
        return barColorInt(Color.parseColor(barColor));
    }


    /*  547 */
    public ImmersionBar barColor(String barColor, @FloatRange(from = 0.0D, to = 1.0D) float barAlpha) {
        return barColorInt(Color.parseColor(barColor), barAlpha);
    }


    /*  561 */
    public ImmersionBar barColor(String barColor, String barColorTransform, @FloatRange(from = 0.0D, to = 1.0D) float barAlpha) {
        return barColorInt(Color.parseColor(barColor), Color.parseColor(barColorTransform), barAlpha);
    }


    public ImmersionBar barColorInt(@ColorInt int barColor) {
        /*  571 */
        this.mBarParams.statusBarColor = barColor;
        /*  572 */
        this.mBarParams.navigationBarColor = barColor;
        /*  573 */
        this.mBarParams.navigationBarColorTemp = this.mBarParams.navigationBarColor;
        /*  574 */
        return this;

    }


    public ImmersionBar barColorInt(@ColorInt int barColor, @FloatRange(from = 0.0D, to = 1.0D) float barAlpha) {
        /*  585 */
        this.mBarParams.statusBarColor = barColor;
        /*  586 */
        this.mBarParams.navigationBarColor = barColor;
        /*  587 */
        this.mBarParams.navigationBarColorTemp = this.mBarParams.navigationBarColor;
        /*  588 */
        this.mBarParams.statusBarAlpha = barAlpha;
        /*  589 */
        this.mBarParams.navigationBarAlpha = barAlpha;
        /*  590 */
        return this;

    }


    public ImmersionBar barColorInt(@ColorInt int barColor, @ColorInt int barColorTransform, @FloatRange(from = 0.0D, to = 1.0D) float barAlpha) {
        /*  604 */
        this.mBarParams.statusBarColor = barColor;
        /*  605 */
        this.mBarParams.navigationBarColor = barColor;
        /*  606 */
        this.mBarParams.navigationBarColorTemp = this.mBarParams.navigationBarColor;

        /*  608 */
        this.mBarParams.statusBarColorTransform = barColorTransform;
        /*  609 */
        this.mBarParams.navigationBarColorTransform = barColorTransform;

        /*  611 */
        this.mBarParams.statusBarAlpha = barAlpha;
        /*  612 */
        this.mBarParams.navigationBarAlpha = barAlpha;
        /*  613 */
        return this;

    }


    /*  624 */
    public ImmersionBar statusBarColorTransform(@ColorRes int statusBarColorTransform) {
        return statusBarColorTransformInt(Color.WHITE);
    }


    /*  634 */
    public ImmersionBar statusBarColorTransform(String statusBarColorTransform) {
        return statusBarColorTransformInt(Color.parseColor(statusBarColorTransform));
    }


    public ImmersionBar statusBarColorTransformInt(@ColorInt int statusBarColorTransform) {
        /*  644 */
        this.mBarParams.statusBarColorTransform = statusBarColorTransform;
        /*  645 */
        return this;

    }


    /*  655 */
    public ImmersionBar navigationBarColorTransform(@ColorRes int navigationBarColorTransform) {
        return navigationBarColorTransformInt(Color.WHITE);
    }


    /*  665 */
    public ImmersionBar navigationBarColorTransform(String navigationBarColorTransform) {
        return navigationBarColorTransformInt(Color.parseColor(navigationBarColorTransform));
    }


    public ImmersionBar navigationBarColorTransformInt(@ColorInt int navigationBarColorTransform) {
        /*  675 */
        this.mBarParams.navigationBarColorTransform = navigationBarColorTransform;
        /*  676 */
        return this;

    }


    /*  686 */
    public ImmersionBar barColorTransform(@ColorRes int barColorTransform) {
        return barColorTransformInt(Color.WHITE);
    }


    /*  696 */
    public ImmersionBar barColorTransform(String barColorTransform) {
        return barColorTransformInt(Color.parseColor(barColorTransform));
    }


    public ImmersionBar barColorTransformInt(@ColorInt int barColorTransform) {
        /*  706 */
        this.mBarParams.statusBarColorTransform = barColorTransform;
        /*  707 */
        this.mBarParams.navigationBarColorTransform = barColorTransform;
        /*  708 */
        return this;

    }


    /*  718 */
    public ImmersionBar addViewSupportTransformColor(View view) {
        return addViewSupportTransformColorInt(view, this.mBarParams.statusBarColorTransform);
    }


    /*  729 */
    public ImmersionBar addViewSupportTransformColor(View view, @ColorRes int viewColorAfterTransform) {
        return addViewSupportTransformColorInt(view, Color.WHITE);
    }


    public ImmersionBar addViewSupportTransformColor(View view, @ColorRes int viewColorBeforeTransform, @ColorRes int viewColorAfterTransform) {
        /*  742 */
        return addViewSupportTransformColorInt(view,
                /*  743 */        Color.WHITE,
                /*  744 */        Color.WHITE);

    }


    /*  755 */
    public ImmersionBar addViewSupportTransformColor(View view, String viewColorAfterTransform) {
        return addViewSupportTransformColorInt(view, Color.parseColor(viewColorAfterTransform));
    }


    public ImmersionBar addViewSupportTransformColor(View view, String viewColorBeforeTransform, String viewColorAfterTransform) {
        /*  768 */
        return addViewSupportTransformColorInt(view,
                /*  769 */         Color.parseColor(viewColorBeforeTransform),
                /*  770 */         Color.parseColor(viewColorAfterTransform));

    }


    public ImmersionBar addViewSupportTransformColorInt(View view, @ColorInt int viewColorAfterTransform) {
        /*  781 */
        if (view == null) {
            /*  782 */
            throw new IllegalArgumentException("View参数不能为空");

        }
        /*  784 */
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        /*  785 */
        map.put(Integer.valueOf(this.mBarParams.statusBarColor), Integer.valueOf(viewColorAfterTransform));
        /*  786 */
        this.mBarParams.viewMap.put(view, map);
        /*  787 */
        return this;

    }


    public ImmersionBar addViewSupportTransformColorInt(View view, @ColorInt int viewColorBeforeTransform, @ColorInt int viewColorAfterTransform) {
        /*  800 */
        if (view == null) {
            /*  801 */
            throw new IllegalArgumentException("View参数不能为空");

        }
        /*  803 */
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        /*  804 */
        map.put(Integer.valueOf(viewColorBeforeTransform), Integer.valueOf(viewColorAfterTransform));
        /*  805 */
        this.mBarParams.viewMap.put(view, map);
        /*  806 */
        return this;

    }


    public ImmersionBar viewAlpha(@FloatRange(from = 0.0D, to = 1.0D) float viewAlpha) {
        /*  817 */
        this.mBarParams.viewAlpha = viewAlpha;
        /*  818 */
        return this;

    }


    public ImmersionBar removeSupportView(View view) {
        /*  828 */
        if (view == null) {
            /*  829 */
            throw new IllegalArgumentException("View参数不能为空");

        }
        /*  831 */
        Map<Integer, Integer> map = (Map) this.mBarParams.viewMap.get(view);
        /*  832 */
        if (map.size() != 0) {
            /*  833 */
            this.mBarParams.viewMap.remove(view);

        }
        /*  835 */
        return this;

    }


    public ImmersionBar removeSupportAllView() {
        /*  844 */
        if (this.mBarParams.viewMap.size() != 0) {
            /*  845 */
            this.mBarParams.viewMap.clear();

        }
        /*  847 */
        return this;

    }


    public ImmersionBar fullScreen(boolean isFullScreen) {
        /*  857 */
        this.mBarParams.fullScreen = isFullScreen;
        /*  858 */
        return this;

    }


    public ImmersionBar statusBarAlpha(@FloatRange(from = 0.0D, to = 1.0D) float statusAlpha) {
        /*  868 */
        this.mBarParams.statusBarAlpha = statusAlpha;
        /*  869 */
        return this;

    }


    public ImmersionBar navigationBarAlpha(@FloatRange(from = 0.0D, to = 1.0D) float navigationAlpha) {
        /*  879 */
        this.mBarParams.navigationBarAlpha = navigationAlpha;
        /*  880 */
        return this;

    }


    public ImmersionBar barAlpha(@FloatRange(from = 0.0D, to = 1.0D) float barAlpha) {
        /*  890 */
        this.mBarParams.statusBarAlpha = barAlpha;
        /*  891 */
        this.mBarParams.navigationBarAlpha = barAlpha;
        /*  892 */
        return this;

    }


    /*  902 */
    public ImmersionBar statusBarDarkFont(boolean isDarkFont) {
        return statusBarDarkFont(isDarkFont, 0.0F);
    }


    public ImmersionBar statusBarDarkFont(boolean isDarkFont, @FloatRange(from = 0.0D, to = 1.0D) float statusAlpha) {
        /*  914 */
        this.mBarParams.darkFont = isDarkFont;
        /*  915 */
        if (!isDarkFont)
            /*  916 */ this.mBarParams.flymeOSStatusBarFontColor = 0;
        /*  917 */
        if (isSupportStatusBarDarkFont()) {
            /*  918 */
            this.mBarParams.statusBarAlpha = 0.0F;

        } else {
            /*  920 */
            this.mBarParams.statusBarAlpha = statusAlpha;

        }
        /*  922 */
        return this;

    }


    public ImmersionBar flymeOSStatusBarFontColor(@ColorRes int flymeOSStatusBarFontColor) {
        /*  933 */
        this.mBarParams.flymeOSStatusBarFontColor = Color.WHITE;
        /*  934 */
        return this;

    }


    public ImmersionBar flymeOSStatusBarFontColor(String flymeOSStatusBarFontColor) {
        /*  945 */
        this.mBarParams.flymeOSStatusBarFontColor = Color.parseColor(flymeOSStatusBarFontColor);
        /*  946 */
        return this;

    }


    public ImmersionBar flymeOSStatusBarFontColorInt(@ColorInt int flymeOSStatusBarFontColor) {
        /*  957 */
        this.mBarParams.flymeOSStatusBarFontColor = flymeOSStatusBarFontColor;
        /*  958 */
        return this;

    }


    public ImmersionBar hideBar(BarHide barHide) {
        /*  968 */
        this.mBarParams.barHide = barHide;
        /*  969 */
        if (Build.VERSION.SDK_INT == 19 || OSUtils.isEMUI3_1()) {
            /*  970 */
            if (this.mBarParams.barHide == BarHide.FLAG_HIDE_NAVIGATION_BAR || this.mBarParams.barHide == BarHide.FLAG_HIDE_BAR) {

                /*  972 */
                this.mBarParams.navigationBarColor = 0;
                /*  973 */
                this.mBarParams.fullScreenTemp = true;

            } else {
                /*  975 */
                this.mBarParams.navigationBarColor = this.mBarParams.navigationBarColorTemp;
                /*  976 */
                this.mBarParams.fullScreenTemp = false;

            }

        }
        /*  979 */
        return this;

    }


    public ImmersionBar fitsSystemWindows(boolean fits) {
        /*  989 */
        this.mBarParams.fits = fits;
        /*  990 */
        return this;

    }


    /* 1002 */
    public ImmersionBar fitsSystemWindows(boolean fits, @ColorRes int statusBarColorContentView) {
        return fitsSystemWindows(fits, statusBarColorContentView, mActivity.getTitleColor(), 0.0F);
    }


    public ImmersionBar fitsSystemWindows(boolean fits, @ColorRes int statusBarColorContentView, @ColorRes int statusBarColorContentViewTransform, @FloatRange(from = 0.0D, to = 1.0D) float statusBarContentViewAlpha) {
        /* 1018 */
        this.mBarParams.fits = fits;
        /* 1019 */
        this.mBarParams.statusBarColorContentView =  mActivity.getTitleColor();
        /* 1020 */
        this.mBarParams.statusBarColorContentViewTransform =  mActivity.getTitleColor();
        /* 1021 */
        this.mBarParams.statusBarContentViewAlpha = statusBarContentViewAlpha;
        /* 1022 */
        this.mBarParams.statusBarColorContentView =  mActivity.getTitleColor();
        /* 1023 */
        this.mContentView.setBackgroundColor(Color.WHITE);

        /* 1025 */
        return this;

    }


    public ImmersionBar statusBarView(View view) {
        /* 1035 */
        if (view == null) {
            /* 1036 */
            throw new IllegalArgumentException("View参数不能为空");

        }
        /* 1038 */
        this.mBarParams.statusBarViewByHeight = view;
        /* 1039 */
        return this;

    }


    public ImmersionBar statusBarView(@IdRes int viewId) {
        /* 1049 */
        View view = this.mActivity.findViewById(viewId);
        /* 1050 */
        if (view == null) {
            /* 1051 */
            throw new IllegalArgumentException("未找到viewId");

        }
        /* 1053 */
        return statusBarView(view);

    }


    public ImmersionBar statusBarView(@IdRes int viewId, View rootView) {
        /* 1065 */
        View view = rootView.findViewById(viewId);
        /* 1066 */
        if (view == null) {
            /* 1067 */
            throw new IllegalArgumentException("未找到viewId");

        }
        /* 1069 */
        return statusBarView(view);

    }


    public ImmersionBar supportActionBar(boolean isSupportActionBar) {
        /* 1080 */
        this.mBarParams.isSupportActionBar = isSupportActionBar;
        /* 1081 */
        return this;

    }


    public ImmersionBar titleBar(View view) {
        /* 1092 */
        if (view == null) {
            /* 1093 */
            throw new IllegalArgumentException("View参数不能为空");

        }
        /* 1095 */
        return titleBar(view, true);

    }


    public ImmersionBar titleBar(View view, boolean statusBarFlag) {
        /* 1107 */
        if (view == null) {
            /* 1108 */
            throw new IllegalArgumentException("View参数不能为空");

        }
        /* 1110 */
        this.mBarParams.titleBarView = view;
        /* 1111 */
        this.mBarParams.statusBarFlag = statusBarFlag;
        /* 1112 */
        setTitleBar();
        /* 1113 */
        return this;

    }


    public ImmersionBar titleBar(@IdRes int viewId) {
        /* 1124 */
        View view = this.mActivity.findViewById(viewId);
        /* 1125 */
        if (view == null) {
            /* 1126 */
            throw new IllegalArgumentException("参数错误");

        }
        /* 1128 */
        return titleBar(view, true);

    }


    public ImmersionBar titleBar(@IdRes int viewId, boolean statusBarFlag) {
        /* 1139 */
        View view = this.mActivity.findViewById(viewId);
        /* 1140 */
        if (view == null) {
            /* 1141 */
            throw new IllegalArgumentException("参数错误");

        }
        /* 1143 */
        return titleBar(view, statusBarFlag);

    }


    public ImmersionBar titleBar(@IdRes int viewId, View rootView) {
        /* 1154 */
        View view = rootView.findViewById(viewId);
        /* 1155 */
        if (view == null) {
            /* 1156 */
            throw new IllegalArgumentException("参数错误");

        }
        /* 1158 */
        return titleBar(view, true);

    }


    public ImmersionBar titleBar(@IdRes int viewId, View rootView, boolean statusBarFlag) {
        /* 1171 */
        View view = rootView.findViewById(viewId);
        /* 1172 */
        if (view == null) {
            /* 1173 */
            throw new IllegalArgumentException("参数错误");

        }
        /* 1175 */
        return titleBar(view, statusBarFlag);

    }


    /* 1186 */
    public ImmersionBar titleBarMarginTop(@IdRes int viewId) {
        return titleBarMarginTop(this.mActivity.findViewById(viewId));
    }


    /* 1198 */
    public ImmersionBar titleBarMarginTop(@IdRes int viewId, View rootView) {
        return titleBarMarginTop(rootView.findViewById(viewId));
    }


    public ImmersionBar titleBarMarginTop(View view) {
        /* 1209 */
        if (view == null) {
            /* 1210 */
            throw new IllegalArgumentException("参数错误");

        }
        /* 1212 */
        this.mBarParams.titleBarViewMarginTop = view;
        /* 1213 */
        if (!this.mBarParams.titleBarViewMarginTopFlag)
            /* 1214 */ setTitleBarMarginTop();
        /* 1215 */
        return this;

    }


    public ImmersionBar statusBarColorTransformEnable(boolean statusBarFlag) {
        /* 1225 */
        this.mBarParams.statusBarFlag = statusBarFlag;
        /* 1226 */
        return this;

    }


    public ImmersionBar reset() {
        /* 1236 */
        BarParams barParamsTemp = this.mBarParams;
        /* 1237 */
        this.mBarParams = new BarParams();
        /* 1238 */
        if (Build.VERSION.SDK_INT == 19 || OSUtils.isEMUI3_1()) {
            /* 1239 */
            this.mBarParams.statusBarView = barParamsTemp.statusBarView;
            /* 1240 */
            this.mBarParams.navigationBarView = barParamsTemp.navigationBarView;

        }
        /* 1242 */
        this.mBarParams.keyboardPatch = barParamsTemp.keyboardPatch;
        /* 1243 */
        mMap.put(this.mImmersionBarName, this.mBarParams);
        /* 1244 */
        return this;

    }


    public ImmersionBar addTag(String tag) {
        /* 1255 */
        tag = this.mActivityName + "_TAG_" + tag;
        /* 1256 */
        if (!isEmpty(tag)) {
            /* 1257 */
            BarParams barParams = this.mBarParams.clone();
            /* 1258 */
            mTagMap.put(tag, barParams);
            /* 1259 */
            ArrayList<String> tagList = (ArrayList) mTagKeyMap.get(this.mActivityName);
            /* 1260 */
            if (tagList != null) {
                /* 1261 */
                if (!tagList.contains(tag))
                    /* 1262 */ tagList.add(tag);

            } else {
                /* 1264 */
                tagList = new ArrayList<String>();
                /* 1265 */
                tagList.add(tag);

            }
            /* 1267 */
            mTagKeyMap.put(this.mActivityName, tagList);

        }
        /* 1269 */
        return this;

    }


    public ImmersionBar getTag(String tag) {
        /* 1280 */
        if (!isEmpty(tag)) {
            /* 1281 */
            BarParams barParams = (BarParams) mTagMap.get(this.mActivityName + "_TAG_" + tag);
            /* 1282 */
            if (barParams != null) {
                /* 1283 */
                this.mBarParams = barParams.clone();

            }

        }
        /* 1286 */
        return this;

    }


    /* 1297 */
    public ImmersionBar keyboardEnable(boolean enable) {
        return keyboardEnable(enable, 18);
    }


    public ImmersionBar keyboardEnable(boolean enable, int keyboardMode) {
        /* 1309 */
        this.mBarParams.keyboardEnable = enable;
        /* 1310 */
        this.mBarParams.keyboardMode = keyboardMode;
        /* 1311 */
        return this;

    }


    public ImmersionBar keyboardMode(int keyboardMode) {
        /* 1322 */
        this.mBarParams.keyboardMode = keyboardMode;
        /* 1323 */
        return this;

    }


    public ImmersionBar setOnKeyboardListener(OnKeyboardListener onKeyboardListener) {
        /* 1334 */
        if (this.mBarParams.onKeyboardListener == null)
            /* 1335 */ this.mBarParams.onKeyboardListener = onKeyboardListener;
        /* 1336 */
        return this;

    }


    public ImmersionBar navigationBarEnable(boolean navigationBarEnable) {
        /* 1347 */
        this.mBarParams.navigationBarEnable = navigationBarEnable;
        /* 1348 */
        return this;

    }


    public ImmersionBar navigationBarWithKitkatEnable(boolean navigationBarWithKitkatEnable) {
        /* 1358 */
        this.mBarParams.navigationBarWithKitkatEnable = navigationBarWithKitkatEnable;
        /* 1359 */
        return this;

    }


    @Deprecated
    public ImmersionBar fixMarginAtBottom(boolean fixMarginAtBottom) {
        /* 1372 */
        this.mBarParams.fixMarginAtBottom = fixMarginAtBottom;
        /* 1373 */
        return this;

    }


    public void init() {
//        /* 1380 */
//        mMap.put(this.mImmersionBarName, this.mBarParams);
//        /* 1381 */
//        initBar();
//        /* 1382 */
//        setStatusBarView();
//        /* 1383 */
//        transformView();
//        /* 1384 */
//        keyboardEnable();
//        /* 1385 */
//        registerEMUI3_x();

    }


    public void destroy() {
        /* 1392 */
        unRegisterEMUI3_x();
        /* 1393 */
        if (this.mBarParams.keyboardPatch != null) {
            /* 1394 */
            this.mBarParams.keyboardPatch.disable(this.mBarParams.keyboardMode);
            /* 1395 */
            this.mBarParams.keyboardPatch = null;

        }
        /* 1397 */
        if (this.mDecorView != null)
            /* 1398 */ this.mDecorView = null;
        /* 1399 */
        if (this.mContentView != null)
            /* 1400 */ this.mContentView = null;
        /* 1401 */
        if (this.mConfig != null)
            /* 1402 */ this.mConfig = null;
        /* 1403 */
        if (this.mWindow != null)
            /* 1404 */ this.mWindow = null;
        /* 1405 */
        if (this.mDialog != null)
            /* 1406 */ this.mDialog = null;
        /* 1407 */
        if (this.mActivity != null)
            /* 1408 */ this.mActivity = null;
        /* 1409 */
        if (!isEmpty(this.mImmersionBarName)) {
            /* 1410 */
            if (this.mBarParams != null)
                /* 1411 */ this.mBarParams = null;
            /* 1412 */
            ArrayList<String> tagList = (ArrayList) mTagKeyMap.get(this.mActivityName);
            /* 1413 */
            if (tagList != null && tagList.size() > 0) {
                /* 1414 */
                for (String tag : tagList) {
                    /* 1415 */
                    mTagMap.remove(tag);

                }
                /* 1417 */
                mTagKeyMap.remove(this.mActivityName);

            }
            /* 1419 */
            mMap.remove(this.mImmersionBarName);

        }

    }


    private void initBar() {
        /* 1427 */
        if (Build.VERSION.SDK_INT >= 19) {
            /* 1428 */
            int uiFlags = 256;
            /* 1429 */
            if (Build.VERSION.SDK_INT >= 21 && !OSUtils.isEMUI3_1()) {
                /* 1430 */
                uiFlags = initBarAboveLOLLIPOP(uiFlags);
                /* 1431 */
                uiFlags = setStatusBarDarkFont(uiFlags);
                /* 1432 */
                supportActionBar();

            } else {
                /* 1434 */
                initBarBelowLOLLIPOP();
                /* 1435 */
                solveNavigation();

            }
            /* 1437 */
            uiFlags = hideBar(uiFlags);
            /* 1438 */
            this.mWindow.getDecorView().setSystemUiVisibility(uiFlags);

        }
        /* 1440 */
        if (OSUtils.isMIUI6Later())
            /* 1441 */ setMIUIStatusBarDarkFont(this.mWindow, this.mBarParams.darkFont);
        /* 1442 */
        if (OSUtils.isFlymeOS4Later()) {
            /* 1443 */
            if (this.mBarParams.flymeOSStatusBarFontColor != 0) {
                /* 1444 */
                FlymeOSStatusBarFontUtils.setStatusBarDarkIcon(this.mActivity, this.mBarParams.flymeOSStatusBarFontColor);

            }
            /* 1446 */
            else if (Build.VERSION.SDK_INT < 23) {
                /* 1447 */
                FlymeOSStatusBarFontUtils.setStatusBarDarkIcon(this.mActivity, this.mBarParams.darkFont);

            }

        }

    }


    private int initBarAboveLOLLIPOP(int uiFlags) {
        /* 1460 */
        uiFlags |= 0x400;
        /* 1461 */
        if (this.mBarParams.fullScreen && this.mBarParams.navigationBarEnable) {
            /* 1462 */
            uiFlags |= 0x200;

        }
        /* 1464 */
        this.mWindow.clearFlags(67108864);
        /* 1465 */
        if (this.mConfig.hasNavigtionBar()) {
            /* 1466 */
            this.mWindow.clearFlags(134217728);

        }
        /* 1468 */
        this.mWindow.addFlags(-2147483648);
        /* 1469 */
        if (this.mBarParams.statusBarFlag) {
            /* 1470 */
//            this.mWindow.setStatusBarColor(ColorUtils.blendARGB(this.mBarParams.statusBarColor, this.mBarParams.statusBarColorTransform, this.mBarParams.statusBarAlpha));

        } else {

            /* 1473 */
//            this.mWindow.setStatusBarColor(ColorUtils.blendARGB(this.mBarParams.statusBarColor, 0, this.mBarParams.statusBarAlpha));

        }
        /* 1475 */
        if (this.mBarParams.navigationBarEnable) {
            /* 1476 */
//            this.mWindow.setNavigationBarColor(ColorUtils.blendARGB(this.mBarParams.navigationBarColor, this.mBarParams.navigationBarColorTransform, this.mBarParams.navigationBarAlpha));

        }
        /* 1478 */
        return uiFlags;

    }


    private void initBarBelowLOLLIPOP() {
        /* 1485 */
        this.mWindow.addFlags(67108864);
        /* 1486 */
        setupStatusBarView();
        /* 1487 */
        if (this.mConfig.hasNavigtionBar()) {
            /* 1488 */
            if (this.mBarParams.navigationBarEnable && this.mBarParams.navigationBarWithKitkatEnable) {
                /* 1489 */
                this.mWindow.addFlags(134217728);

            } else {
                /* 1491 */
                this.mWindow.clearFlags(134217728);
                /* 1492 */
            }
            setupNavBarView();

        }

    }


    private void setupStatusBarView() {
        /* 1500 */
        if (this.mBarParams.statusBarView == null) {
            /* 1501 */
            this.mBarParams.statusBarView = new View(this.mActivity);

        }

        /* 1504 */
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, this.mConfig.getStatusBarHeight());
        /* 1505 */
        params.gravity = 48;
        /* 1506 */
        this.mBarParams.statusBarView.setLayoutParams(params);
        /* 1507 */
        if (this.mBarParams.statusBarFlag) {
            /* 1508 */
            this.mBarParams.statusBarView.
                    setBackgroundColor(Color.WHITE);

        } else {

            /* 1511 */
            this.mBarParams.statusBarView.setBackgroundColor(Color.WHITE);

        }
        /* 1513 */
        this.mBarParams.statusBarView.setVisibility(View.VISIBLE);
        /* 1514 */
        ViewGroup viewGroup = (ViewGroup) this.mBarParams.statusBarView.getParent();
        /* 1515 */
        if (viewGroup != null)
            /* 1516 */ viewGroup.removeView(this.mBarParams.statusBarView);
        /* 1517 */
        this.mDecorView.addView(this.mBarParams.statusBarView);

    }


    private void setupNavBarView() {

        FrameLayout.LayoutParams params;
        /* 1524 */
        if (this.mBarParams.navigationBarView == null) {
            /* 1525 */
            this.mBarParams.navigationBarView = new View(this.mActivity);

        }

        /* 1528 */
        if (this.mConfig.isNavigationAtBottom()) {
            /* 1529 */
            params = new FrameLayout.LayoutParams(-1, this.mConfig.getNavigationBarHeight());
            /* 1530 */
            params.gravity = 80;

        } else {
            /* 1532 */
            params = new FrameLayout.LayoutParams(this.mConfig.getNavigationBarWidth(), -1);
            /* 1533 */
            params.gravity = 8388613;

        }
        /* 1535 */
        this.mBarParams.navigationBarView.setLayoutParams(params);
        /* 1536 */
        if (this.mBarParams.navigationBarEnable && this.mBarParams.navigationBarWithKitkatEnable) {
            /* 1537 */
            if (!this.mBarParams.fullScreen && this.mBarParams.navigationBarColorTransform == 0) {
                /* 1538 */
//                this.mBarParams.navigationBarView.setBackgroundColor(ColorUtils.blendARGB(this.mBarParams.navigationBarColor, -16777216, this.mBarParams.navigationBarAlpha));

            } else {

                /* 1541 */
//                this.mBarParams.navigationBarView.setBackgroundColor(ColorUtils.blendARGB(this.mBarParams.navigationBarColor, this.mBarParams.navigationBarColorTransform, this.mBarParams.navigationBarAlpha));

            }

        } else {

            /* 1545 */
            this.mBarParams.navigationBarView.setBackgroundColor(0);
            /* 1546 */
        }
        this.mBarParams.navigationBarView.setVisibility(View.VISIBLE);
        /* 1547 */
        ViewGroup viewGroup = (ViewGroup) this.mBarParams.navigationBarView.getParent();
        /* 1548 */
        if (viewGroup != null)
            /* 1549 */ viewGroup.removeView(this.mBarParams.navigationBarView);
        /* 1550 */
        this.mDecorView.addView(this.mBarParams.navigationBarView);

    }


    private void solveNavigation() {
        /* 1557 */
        for (int i = 0, count = this.mContentView.getChildCount(); i < count; i++) {
            /* 1558 */
            View childView = this.mContentView.getChildAt(i);
            /* 1559 */
            if (childView instanceof ViewGroup) {
                /* 1560 */
                if (childView instanceof DrawerLayout) {
                    /* 1561 */
                    View childAt1 = ((DrawerLayout) childView).getChildAt(0);
                    /* 1562 */
                    if (childAt1 != null) {
                        /* 1563 */
                        this.mBarParams.systemWindows = childAt1.getFitsSystemWindows();
                        /* 1564 */
                        if (this.mBarParams.systemWindows) {
                            /* 1565 */
                            this.mContentView.setPadding(0, 0, 0, 0);

                            return;

                        }

                    }

                } else {
                    /* 1570 */
                    this.mBarParams.systemWindows = childView.getFitsSystemWindows();
                    /* 1571 */
                    if (this.mBarParams.systemWindows) {
                        /* 1572 */
                        this.mContentView.setPadding(0, 0, 0, 0);


                        return;

                    }

                }

            }

        }

        /* 1580 */
        if (this.mConfig.hasNavigtionBar() && !this.mBarParams.fullScreenTemp && !this.mBarParams.fullScreen) {
            /* 1581 */
            if (this.mConfig.isNavigationAtBottom()) {
                /* 1582 */
                if (!this.mBarParams.isSupportActionBar) {
                    /* 1583 */
                    if (this.mBarParams.navigationBarEnable && this.mBarParams.navigationBarWithKitkatEnable) {
                        /* 1584 */
                        if (this.mBarParams.fits) {
                            /* 1585 */
                            this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight(), 0, this.mConfig
/* 1586 */.getNavigationBarHeight());

                        } else {
                            /* 1588 */
                            this.mContentView.setPadding(0, 0, 0, this.mConfig.getNavigationBarHeight());

                        }
                        /* 1590 */
                    } else if (this.mBarParams.fits) {
                        /* 1591 */
                        this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight(), 0, 0);

                    } else {
                        /* 1593 */
                        this.mContentView.setPadding(0, 0, 0, 0);

                    }


                }
                /* 1597 */
                else if (this.mBarParams.navigationBarEnable && this.mBarParams.navigationBarWithKitkatEnable) {
                    /* 1598 */
                    this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight() + this.mConfig
/* 1599 */.getActionBarHeight() + 10, 0, this.mConfig.getNavigationBarHeight());

                } else {
                    /* 1601 */
                    this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight() + this.mConfig
/* 1602 */.getActionBarHeight() + 10, 0, 0);

                }

                /* 1605 */
            } else if (!this.mBarParams.isSupportActionBar) {
                /* 1606 */
                if (this.mBarParams.navigationBarEnable && this.mBarParams.navigationBarWithKitkatEnable) {
                    /* 1607 */
                    if (this.mBarParams.fits) {
                        /* 1608 */
                        this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight(), this.mConfig
/* 1609 */.getNavigationBarWidth(), 0);

                    } else {
                        /* 1611 */
                        this.mContentView.setPadding(0, 0, this.mConfig.getNavigationBarWidth(), 0);

                    }
                    /* 1613 */
                } else if (this.mBarParams.fits) {
                    /* 1614 */
                    this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight(), 0, 0);

                } else {
                    /* 1616 */
                    this.mContentView.setPadding(0, 0, 0, 0);

                }


            }
            /* 1620 */
            else if (this.mBarParams.navigationBarEnable && this.mBarParams.navigationBarWithKitkatEnable) {
                /* 1621 */
                this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight() + this.mConfig
/* 1622 */.getActionBarHeight() + 10, this.mConfig.getNavigationBarWidth(), 0);

            } else {
                /* 1624 */
                this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight() + this.mConfig
/* 1625 */.getActionBarHeight() + 10, 0, 0);

            }


        }
        /* 1629 */
        else if (!this.mBarParams.isSupportActionBar) {
            /* 1630 */
            if (this.mBarParams.fits) {
                /* 1631 */
                this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight(), 0, 0);

            } else {
                /* 1633 */
                this.mContentView.setPadding(0, 0, 0, 0);

            }

        } else {
            /* 1636 */
            this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight() + this.mConfig.getActionBarHeight() + 10, 0, 0);

        }

    }


    private void registerEMUI3_x() {
        /* 1646 */
        if ((OSUtils.isEMUI3_1() || OSUtils.isEMUI3_0()) && this.mConfig.hasNavigtionBar() && this.mBarParams.navigationBarEnable && this.mBarParams.navigationBarWithKitkatEnable) {

            /* 1648 */
            if (this.mBarParams.navigationStatusObserver == null && this.mBarParams.navigationBarView != null)
                /* 1649 */
                this.mBarParams.navigationStatusObserver = new ContentObserver(new Handler()) {

                    public void onChange(boolean selfChange) {
                        /* 1652 */
                        int navigationBarIsMin = Settings.System.getInt(ImmersionBar.this.mActivity.getContentResolver(), "navigationbar_is_min", 0);

                        /* 1654 */
                        if (navigationBarIsMin == 1) {

                            /* 1656 */
                            mBarParams.navigationBarView.setVisibility(View.GONE);
                            /* 1657 */
                            ImmersionBar.this.mContentView.setPadding(0, ImmersionBar.this.mContentView.getPaddingTop(), 0, 0);

                        } else {

                            /* 1660 */
                            mBarParams.navigationBarView.setVisibility(View.VISIBLE);
                            /* 1661 */
                            if (!mBarParams.systemWindows)
                                /* 1662 */ {
                                if (ImmersionBar.this.mConfig.isNavigationAtBottom()) {
                                    /* 1663 */
                                    ImmersionBar.this.mContentView.setPadding(0, ImmersionBar.this.mContentView.getPaddingTop(), 0, ImmersionBar.this.mConfig.getNavigationBarHeight());

                                } else {
                                    /* 1665 */
                                    ImmersionBar.this.mContentView.setPadding(0, ImmersionBar.this.mContentView.getPaddingTop(), ImmersionBar.this.mConfig.getNavigationBarWidth(), 0);

                                }
                            }
                            /* 1667 */
                            else {
                                ImmersionBar.this.mContentView.setPadding(0, ImmersionBar.this.mContentView.getPaddingTop(), 0, 0);
                            }


                        }

                    }

                };
            /* 1672 */
            this.mActivity.getContentResolver().registerContentObserver(
                    /* 1673 */           Settings.System.getUriFor("navigationbar_is_min"), true, this.mBarParams.navigationStatusObserver);

        }

    }


    private void unRegisterEMUI3_x() {
        /* 1682 */
        if ((OSUtils.isEMUI3_1() || OSUtils.isEMUI3_0()) && this.mConfig.hasNavigtionBar() && this.mBarParams.navigationBarEnable && this.mBarParams.navigationBarWithKitkatEnable) {
            /* 1684 */
            if (this.mBarParams.navigationStatusObserver != null && this.mBarParams.navigationBarView != null) {
                /* 1685 */
                this.mActivity.getContentResolver().unregisterContentObserver(this.mBarParams.navigationStatusObserver);

            }

        }

    }


    private int hideBar(int uiFlags) {
        /* 1698 */
        if (Build.VERSION.SDK_INT >= 16) {
            /* 1699 */
            switch (this.mBarParams.barHide) {

                case FLAG_HIDE_BAR:
                    /* 1701 */
                    uiFlags |= 0x206;

                    break;


                case FLAG_HIDE_STATUS_BAR:
                    /* 1706 */
                    uiFlags |= 0x404;

                    break;

                case FLAG_HIDE_NAVIGATION_BAR:
                    /* 1709 */
                    uiFlags |= 0x202;

                    break;


                case FLAG_SHOW_BAR:
                    /* 1713 */
                    uiFlags |= 0x0;

                    break;

            }

        }
        /* 1717 */
        return uiFlags | 0x1000;

    }


    private int setStatusBarDarkFont(int uiFlags) {
        /* 1725 */
        if (Build.VERSION.SDK_INT >= 23 && this.mBarParams.darkFont) {
            /* 1726 */
            return uiFlags | 0x2000;

        }
        /* 1728 */
        return uiFlags;

    }


    private void transformView() {
        /* 1738 */
        if (this.mBarParams.viewMap.size() != 0) {
            /* 1739 */
            Set<Map.Entry<View, Map<Integer, Integer>>> entrySet = this.mBarParams.viewMap.entrySet();
            /* 1740 */
            for (Map.Entry<View, Map<Integer, Integer>> entry : entrySet) {
                /* 1741 */
                View view = (View) entry.getKey();
                /* 1742 */
                Map<Integer, Integer> map = (Map) entry.getValue();
                /* 1743 */
                Integer colorBefore = Integer.valueOf(this.mBarParams.statusBarColor);
                /* 1744 */
                Integer colorAfter = Integer.valueOf(this.mBarParams.statusBarColorTransform);
                /* 1745 */
                for (Map.Entry<Integer, Integer> integerEntry : map.entrySet()) {
                    /* 1746 */
                    colorBefore = (Integer) integerEntry.getKey();
                    /* 1747 */
                    colorAfter = (Integer) integerEntry.getValue();

                }
                /* 1749 */
                if (view != null) {
                    /* 1750 */
                    if (Math.abs(this.mBarParams.viewAlpha - 0.0F) == 0.0F) {
                        /* 1751 */
//                        view.setBackgroundColor(ColorUtils.blendARGB(colorBefore.intValue(), colorAfter.intValue(), this.mBarParams.statusBarAlpha));
                        continue;

                    }
                    /* 1753 */
//                    view.setBackgroundColor(ColorUtils.blendARGB(colorBefore.intValue(), colorAfter.intValue(), this.mBarParams.viewAlpha));

                }

            }

        }

    }


    private void setStatusBarView() {
        /* 1764 */
        if (Build.VERSION.SDK_INT >= 19 && this.mBarParams.statusBarViewByHeight != null) {
            /* 1765 */
            ViewGroup.LayoutParams params = this.mBarParams.statusBarViewByHeight.getLayoutParams();
            /* 1766 */
            params.height = this.mConfig.getStatusBarHeight();
            /* 1767 */
            this.mBarParams.statusBarViewByHeight.setLayoutParams(params);

        }

    }


    private void setTitleBar() {
        /* 1776 */
        if (Build.VERSION.SDK_INT >= 19 && this.mBarParams.titleBarView != null) {
            /* 1777 */
            final ViewGroup.LayoutParams layoutParams = this.mBarParams.titleBarView.getLayoutParams();
            /* 1778 */
            if (layoutParams.height == -2 || layoutParams.height == -1) {

                /* 1780 */
                this.mBarParams.titleBarView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    public void onGlobalLayout() {
                        /* 1783 */
                        mBarParams.titleBarView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        /* 1784 */
                        if (mBarParams.titleBarHeight == 0)
                            /* 1785 */
                            mBarParams.titleBarHeight = mBarParams.titleBarView.getHeight() + ImmersionBar.this.mConfig.getStatusBarHeight();
                        /* 1786 */
                        if (mBarParams.titleBarPaddingTopHeight == 0)
                            mBarParams
                                    /* 1788 */.titleBarPaddingTopHeight = mBarParams.titleBarView.getPaddingTop() + ImmersionBar.this.mConfig.getStatusBarHeight();
                        /* 1789 */
                        int height = mBarParams.titleBarHeight;
                        /* 1790 */
                        mBarParams.titleBarView.setPadding(mBarParams.titleBarView.getPaddingLeft(),
                                /* 1791 */                     mBarParams.titleBarPaddingTopHeight,
                                /* 1792 */                     mBarParams.titleBarView.getPaddingRight(),
                                /* 1793 */                     mBarParams.titleBarView.getPaddingBottom());
                        /* 1794 */
                        mBarParams.titleBarView.setLayoutParams(layoutParams);

                    }

                });

            } else {
                /* 1798 */
                if (this.mBarParams.titleBarHeight == 0)
                    /* 1799 */
                    this.mBarParams.titleBarHeight = layoutParams.height + this.mConfig.getStatusBarHeight();
                /* 1800 */
                if (this.mBarParams.titleBarPaddingTopHeight == 0)
                    /* 1801 */ this.mBarParams
                        /* 1802 */.titleBarPaddingTopHeight = this.mBarParams.titleBarView.getPaddingTop() + this.mConfig.getStatusBarHeight();
                /* 1803 */
                layoutParams.height = this.mBarParams.titleBarHeight;
                /* 1804 */
                this.mBarParams.titleBarView.setPadding(this.mBarParams.titleBarView.getPaddingLeft(), this.mBarParams.titleBarPaddingTopHeight, this.mBarParams.titleBarView

/* 1806 */.getPaddingRight(), this.mBarParams.titleBarView
/* 1807 */.getPaddingBottom());
                /* 1808 */
                this.mBarParams.titleBarView.setLayoutParams(layoutParams);

            }

        }

    }


    private void setTitleBarMarginTop() {
        /* 1818 */
        if (Build.VERSION.SDK_INT >= 19) {
            /* 1819 */
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) this.mBarParams.titleBarViewMarginTop.getLayoutParams();
            /* 1820 */
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin + this.mConfig
/* 1821 */.getStatusBarHeight(), layoutParams.rightMargin, layoutParams.bottomMargin);


            /* 1824 */
            this.mBarParams.titleBarViewMarginTopFlag = true;

        }

    }


    private void supportActionBar() {
        /* 1833 */
        if (Build.VERSION.SDK_INT >= 21 && !OSUtils.isEMUI3_1()) {
            /* 1834 */
            for (int i = 0, count = this.mContentView.getChildCount(); i < count; i++) {
                /* 1835 */
                View childView = this.mContentView.getChildAt(i);
                /* 1836 */
                if (childView instanceof ViewGroup) {
                    /* 1837 */
                    this.mBarParams.systemWindows = childView.getFitsSystemWindows();
                    /* 1838 */
                    if (this.mBarParams.systemWindows) {
                        /* 1839 */
                        this.mContentView.setPadding(0, 0, 0, 0);

                        return;

                    }

                }

            }
            /* 1844 */
            if (this.mBarParams.isSupportActionBar) {
                /* 1845 */
                this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight() + this.mConfig.getActionBarHeight(), 0, 0);

            }
            /* 1847 */
            else if (this.mBarParams.fits) {
                /* 1848 */
                this.mContentView.setPadding(0, this.mConfig.getStatusBarHeight(), 0, 0);

            } else {
                /* 1850 */
                this.mContentView.setPadding(0, 0, 0, 0);

            }

        }

    }


    private void keyboardEnable() {
        /* 1860 */
        if (Build.VERSION.SDK_INT >= 19) {
            /* 1861 */
            if (this.mBarParams.keyboardPatch == null) {
                /* 1862 */
                this.mBarParams.keyboardPatch = KeyboardPatch.patch(this.mActivity, this.mWindow);

            }
            /* 1864 */
            this.mBarParams.keyboardPatch.setBarParams(this.mBarParams);
            /* 1865 */
            if (this.mBarParams.keyboardEnable) {
                /* 1866 */
                this.mBarParams.keyboardPatch.enable(this.mBarParams.keyboardMode);

            } else {
                /* 1868 */
                this.mBarParams.keyboardPatch.disable(this.mBarParams.keyboardMode);

            }

        }

    }


    private void setMIUIStatusBarDarkFont(Window window, boolean darkFont) {
        /* 1879 */
        if (window != null) {
            /* 1880 */
            Class clazz = window.getClass();


            try {
                /* 1883 */
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                /* 1884 */
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                /* 1885 */
                int darkModeFlag = field.getInt(layoutParams);
                /* 1886 */
                Method extraFlagField = clazz.getMethod("setExtraFlags", new Class[]{int.class, int.class});
                /* 1887 */
                if (darkFont) {
                    /* 1888 */
                    extraFlagField.invoke(window, new Object[]{Integer.valueOf(darkModeFlag), Integer.valueOf(darkModeFlag)});

                } else {
                    /* 1890 */
                    extraFlagField.invoke(window, new Object[]{Integer.valueOf(0), Integer.valueOf(darkModeFlag)});

                }
                /* 1892 */
            } catch (Exception e) {
                /* 1893 */
                e.printStackTrace();

            }

        }

    }


    public static void setTitleBar(final Activity activity, final View view) {
        /* 1906 */
        if (Build.VERSION.SDK_INT >= 19) {
            /* 1907 */
            final ViewGroup.LayoutParams lp = view.getLayoutParams();
            /* 1908 */
            if (lp.height == -2) {
                /* 1909 */
                view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    public void onGlobalLayout() {
                        /* 1912 */
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        /* 1913 */
                        height = view.getHeight() + ImmersionBar.getStatusBarHeight(activity);
                        /* 1914 */
                        view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + ImmersionBar.getStatusBarHeight(activity), view
/* 1915 */.getPaddingRight(), view.getPaddingBottom());

                    }

                });

            } else {
                /* 1919 */
                lp.height += getStatusBarHeight(activity);
                /* 1920 */
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(activity), view
/* 1921 */.getPaddingRight(), view.getPaddingBottom());

            }

        }

    }


    public static void setStatusBarView(Activity activity, View view) {
        /* 1934 */
        if (Build.VERSION.SDK_INT >= 19) {
            /* 1935 */
            ViewGroup.LayoutParams params = view.getLayoutParams();
            /* 1936 */
            params.height = getStatusBarHeight(activity);
            /* 1937 */
            view.setLayoutParams(params);

        }

    }


    public static void setTitleBarMarginTop(Activity activity, @NonNull View view) {
        /* 1949 */
        if (Build.VERSION.SDK_INT >= 19) {
            /* 1950 */
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            /* 1951 */
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin +
                    /* 1952 */           getStatusBarHeight(activity), layoutParams.rightMargin, layoutParams.bottomMargin);

        }

    }





    @TargetApi(14)
    public static boolean hasNavigationBar(Activity activity) {
        /* 1984 */
        BarConfig config = new BarConfig(activity);
        /* 1985 */
        return config.hasNavigtionBar();

    }


    @TargetApi(14)
    public static int getNavigationBarHeight(Activity activity) {
        /* 1997 */
        BarConfig config = new BarConfig(activity);
        /* 1998 */
        return config.getNavigationBarHeight();

    }


    @TargetApi(14)
    public static int getNavigationBarWidth(Activity activity) {
        /* 2010 */
        BarConfig config = new BarConfig(activity);
        /* 2011 */
        return config.getNavigationBarWidth();

    }


    @TargetApi(14)
    public static boolean isNavigationAtBottom(Activity activity) {
        /* 2023 */
        BarConfig config = new BarConfig(activity);
        /* 2024 */
        return config.isNavigationAtBottom();

    }


    @TargetApi(14)
    public static int getStatusBarHeight(Activity activity) {
        /* 2036 */
        BarConfig config = new BarConfig(activity);
        /* 2037 */
        return config.getStatusBarHeight();

    }


    @TargetApi(14)
    public static int getActionBarHeight(Activity activity) {
        /* 2049 */
        BarConfig config = new BarConfig(activity);
        /* 2050 */
        return config.getActionBarHeight();

    }


    public static boolean isSupportStatusBarDarkFont() {
        /* 2060 */
        if (OSUtils.isMIUI6Later() || OSUtils.isFlymeOS4Later() || Build.VERSION.SDK_INT >= 23) {
            /* 2062 */
            return true;

        }
        /* 2064 */
        return false;

    }


    /* 2074 */
    public static void hideStatusBar(Window window) {
        window.setFlags(1024, 1024);
    }


    /* 2084 */
    public BarParams getBarParams() {
        return this.mBarParams;
    }


    public BarParams getTagBarParams(String tag) {
        /* 2088 */
        BarParams barParams = null;
        /* 2089 */
        if (!isEmpty(tag)) {
            /* 2090 */
            barParams = (BarParams) mTagMap.get(this.mActivityName + "_TAG_" + tag);

        }
        /* 2092 */
        return barParams;

    }


    /* 2096 */
    private static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\ImmersionBar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */