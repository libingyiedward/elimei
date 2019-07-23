
package com.elimei.elimei;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.SurfaceHolder;

import com.rtspclient.RTSPClient;


public class VideoPalyerActivity
        extends RTSPClient {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initDone() {
    }


    @Override
    protected void onOrientationCallBack(int orientation) {
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (TextUtils.isEmpty(url6)) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("初始化中");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
//            post();
        }

    }

}
