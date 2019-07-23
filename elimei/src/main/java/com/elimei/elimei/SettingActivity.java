
package com.elimei.elimei;

import android.content.Context;
import android.content.DialogInterface;
/*    */ import android.content.Intent;
/*    */ import android.databinding.DataBindingUtil;
/*    */ import android.net.wifi.WifiManager;
/*    */ import android.os.Bundle;
/*    */ import android.support.v7.app.AlertDialog;
/*    */ import android.support.v7.app.AppCompatActivity;
/*    */ import android.view.View;
/*    */ import com.elimei.R;

/*    */ import com.elimei.databinding.ActivitySettingBinding;
import com.elimei.elimei.utils.ImmersionBar;


public class SettingActivity
        extends AppCompatActivity {
    private ActivitySettingBinding binding;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.binding = (ActivitySettingBinding) DataBindingUtil.setContentView(this, R.layout.activity_setting);

        this.binding.back.setOnClickListener(new View.OnClickListener()
                /*    */ {
            /*    */
            public void onClick(View view) {

                SettingActivity.this.finish();

            }
            /*    */
        });

        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();

        this.binding.lianjie.setOnClickListener(new View.OnClickListener()
                /*    */ {
            /*    */
            public void onClick(View view) {

                WifiManager mwifiManager = (WifiManager) SettingActivity.this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                if (mwifiManager.isWifiEnabled()) {

                    if (mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "").contains("SMARP_")) {

                        Intent intent = new Intent(SettingActivity.this, WiFiSettingActivity.class);

                        SettingActivity.this.startActivity(intent);

                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);

                        builder.setMessage("请打开设备，连接设备WiFi").setPositiveButton("确定", new DialogInterface.OnClickListener()
                                /*    */ {
                            /*    */
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent();

                                intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");

                                SettingActivity.this.startActivity(intent);

                            }
                            /* 49 */
                        }).show();

                    }

                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);

                    builder.setMessage("请打开设备，连接设备WiFi").setPositiveButton("确定", new DialogInterface.OnClickListener()
                            /*    */ {
                        /*    */
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent();

                            intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");

                            startActivity(intent);

                        }
                        /* 60 */
                    }).show();

                }

            }
            /*    */
        });

    }

}
