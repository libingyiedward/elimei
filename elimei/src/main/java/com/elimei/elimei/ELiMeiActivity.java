
package com.elimei.elimei;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.elimei.R;
import com.elimei.elimei.Model.Token;
import com.elimei.elimei.utils.ImmersionBar;
import com.elimei.elimei.utils.WifiUtils;

public class ELiMeiActivity
        extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimei);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        ImmersionBar.with(this).statusBarColor("#Fe9900").statusBarView(R.id.view).init();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
        toggle.syncState();
        String token = getIntent().getStringExtra("token");
        String address = getIntent().getStringExtra("address");
        (new Token(this)).setToken(token);
        (new Token(this)).setaddress(address);
        ImageView id = (ImageView) findViewById(R.id.finsh);
        id.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction("action.firstpage");
                /*  50 */
                startActivity(intent);

            }

        });

    }


    public void onBackPressed() {
        /*  57 */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*  58 */
        if (drawer.isDrawerOpen(8388611)) {
            /*  59 */
            drawer.closeDrawer(8388611);

        } else {
            /*  61 */
            super.onBackPressed();

        }

    }


    public void onViewClick(View v) {
        WifiManager mwifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int id = v.getId();
        if (id == R.id.nav_camera) {
            Intent intent = new Intent();
            intent.setAction("action.firstpage");
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

            if (mwifiManager.isWifiEnabled()) {

                if (mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "").contains("SMARP_")) {
                    Intent intent = new Intent(this, VideoPalyerActivity.class);
                    intent.putExtra("from", "1");
                    startActivity(intent);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("请打开设备，连接设备WiFi").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            WifiUtils.starttowifi(ELiMeiActivity.this);
                        }
                    }).show();

                }

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("请打开设备，连接设备WiFi").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        WifiUtils.starttowifi(ELiMeiActivity.this);
                    }
                }).show();

            }
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(), ASActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /* 130 */
        drawer.closeDrawer(8388611);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        WifiManager mwifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "").contains("SMARP_")) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("设备已连接，是否开始检测？").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent intent = new Intent(ELiMeiActivity.this, VideoPalyerActivity.class);
                    intent.putExtra("from", "1");
                    startActivity(intent);
                }
                /* 146 */
            }).show();

        }

    }

}
