
package com.elimei.elimei;


import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.elimei.R;

import com.elimei.databinding.ActivityTestBinding;
import com.elimei.elimei.service.MessageService;
import com.elimei.elimei.service.UdpSocket;
import com.elimei.elimei.utils.ImmersionBar;
import com.network.WifiAdmin;

import java.io.File;


public class TestActivity
        extends AppCompatActivity {
    private ActivityTestBinding binding;
    private WifiManager wifiManager;
    private WifiAdmin wifiAdmin;
    private boolean gl_isFirstStartActivity;
    private Intent serviceIntent;
    private int mDisplayWidth;
    private int mDisplayHeight;
    private boolean gl_isfirstSetResolution;
    private static Context gl_ctx;
    private SharedPreferences mSettings;
    private Service service;
    private boolean isBinded;
    private static UdpSocket mSocket;
    private boolean mIsDoRtspFlow = false;
    private static boolean gl_isStartRtsp = false;
    /*  47 */   static int gl_sd_status = -1;
    /*  48 */   static int gl_rec_running = -1;
    /*  49 */   static int gl_sd_capacity = -1;
    /*  50 */   static int gl_sd_fromat = -1;

    /*  52 */   private static int gl_resolution = 0;


    protected void onCreate(Bundle savedInstanceState) {
        /*  55 */
        super.onCreate(savedInstanceState);
        /*  56 */
        this.binding = (ActivityTestBinding) DataBindingUtil.setContentView(this, R.layout.activity_test);
        /*  57 */
        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();
        /*  58 */
        this.binding.back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                /*  61 */
                TestActivity.this.finish();

            }

        });
        /*  64 */
        initData();

    }


    @SuppressLint({"NewApi"})
    protected void initData() {
        /*  69 */
        this.gl_isfirstSetResolution = true;

        /*  71 */
        DisplayMetrics dm = new DisplayMetrics();
        /*  72 */
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        /*  73 */
        this.mDisplayWidth = dm.widthPixels;
        /*  74 */
        this.mDisplayHeight = dm.heightPixels;


        /*  77 */
        checkIsFwFolderCreate();

        /*  79 */
        this.mSettings = PreferenceManager.getDefaultSharedPreferences(this);


        /*  82 */
        this.serviceIntent = new Intent(this, MessageService.class);
        /*  83 */
        startService(this.serviceIntent);



        /*  87 */
        doBind();

        /*  89 */
        this.wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        /*  90 */
        this.wifiAdmin = new WifiAdmin(this);
        /*  91 */
        this.gl_isFirstStartActivity = true;

    }


    private void checkIsFwFolderCreate() {
        /*  95 */
        File f = new File(Environment.getExternalStorageDirectory() + "/SMARP_DCam/firmware");
        /*  96 */
        if (!f.exists()) {
            /*  97 */
            f.mkdir();

        }

    }


    private void doBind() {
        /* 103 */
        Intent intent = new Intent();
        /* 104 */
        intent.setClass(this, MessageService.class);
        /* 105 */
        bindService(intent, this.conn, Context.BIND_AUTO_CREATE);

    }


    /* 108 */   private ServiceConnection conn = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder binder) {
            /* 111 */
            TestActivity.this.isBinded = true;

            /* 113 */
            TestActivity.this.service = ((MessageService.ServiceBinder) binder).getService();

        }


        /* 127 */
        public void onServiceDisconnected(ComponentName name) {
            TestActivity.this.isBinded = false;
        }

    };

}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\TestActivity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */