package com.elimei.elimei.utils;

import android.text.TextUtils;

import java.lang.reflect.Method;


public class OSUtils {
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_EMUI_VERSION_NAME = "ro.build.version.emui";
    private static final String KEY_DISPLAY = "ro.build.display.id";

    private static String property;

    public static boolean isMIUI() {
        /*  24 */
        property = getSystemProperty("ro.miui.ui.version.name", "");
        /*  25 */
        return !TextUtils.isEmpty(property);
    }


    public static boolean isMIUI6Later() {
        /*  35 */
      String  version = getMIUIVersion();

        /*  37 */
        if (!version.isEmpty()) {
            try {
                /*  39 */
                int num = Integer.valueOf(version.substring(1)).intValue();
                /*  40 */
                return (num >= 6);
                /*  41 */
            } catch (NumberFormatException e) {
                /*  42 */
                return false;
            }
        }
        /*  45 */
        return false;
    }


    /*  55 */
    public static String getMIUIVersion() {
        return isMIUI() ? getSystemProperty("ro.miui.ui.version.name", "") : "";
    }


    public static boolean isEMUI() {
        /*  65 */
        property = getSystemProperty("ro.build.version.emui", "");
        /*  66 */
        return !TextUtils.isEmpty(property);
    }


    /*  76 */
    public static String getEMUIVersion() {
        return isEMUI() ? getSystemProperty("ro.build.version.emui", "") : "";
    }


    public static boolean isEMUI3_1() {
        /*  86 */
        property = getEMUIVersion();
        /*  87 */
        if ("EmotionUI 3".equals(property) || property.contains("EmotionUI_3.1")) {
            /*  88 */
            return true;
        }
        /*  90 */
        return false;
    }


    public static boolean isEMUI3_0() {
        /* 100 */
        property = getEMUIVersion();
        /* 101 */
        if (property.contains("EmotionUI_3.0")) {
            /* 102 */
            return true;
        }
        /* 104 */
        return false;
    }


    /* 114 */
    public static boolean isFlymeOS() {
        return getFlymeOSFlag().toLowerCase().contains("flyme");
    }


    public static boolean isFlymeOS4Later() {
        /* 124 */
      String  version = getFlymeOSVersion();

        /* 126 */
        if (!version.isEmpty()) {
            try {
                /* 128 */
                int num;
                if (version.toLowerCase().contains("os")) {
                    /* 129 */
                    num = Integer.valueOf(version.substring(9, 10)).intValue();
                } else {
                    /* 131 */
                    num = Integer.valueOf(version.substring(6, 7)).intValue();
                }
                /* 133 */
                return (num >= 4);
                /* 134 */
            } catch (NumberFormatException e) {
                /* 135 */
                return false;
            }
        }
        /* 138 */
        return false;
    }


    public static boolean isFlymeOS5() {
        /* 148 */
        String   version = getFlymeOSVersion();

        /* 150 */
        if (!version.isEmpty()) {
            try {
                /* 152 */
                int num;
                if (version.toLowerCase().contains("os")) {
                    /* 153 */
                    num = Integer.valueOf(version.substring(9, 10)).intValue();
                } else {
                    /* 155 */
                    num = Integer.valueOf(version.substring(6, 7)).intValue();
                }
                /* 157 */
                return (num == 5);
                /* 158 */
            } catch (NumberFormatException e) {
                /* 159 */
                return false;
            }
        }
        /* 162 */
        return false;
    }


    /* 173 */
    public static String getFlymeOSVersion() {
        return isFlymeOS() ? getSystemProperty("ro.build.display.id", "") : "";
    }


    /* 177 */
    private static String getFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }


    private static String getSystemProperty(String key, String defaultValue) {
        try {
            /* 182 */
            Class<?> clz = Class.forName("android.os.SystemProperties");
            /* 183 */
            Method get = clz.getMethod("get", new Class[]{String.class, String.class});
            /* 184 */
            return (String) get.invoke(clz, new Object[]{key, defaultValue});
            /* 185 */
        } catch (Exception e) {
            /* 186 */
            e.printStackTrace();

            /* 188 */
            return defaultValue;
        }
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elime\\utils\OSUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */