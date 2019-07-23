/*     */
package com.elimei.elimei;
/*     */
/*     */

import android.app.Service;
/*     */ import android.content.BroadcastReceiver;
/*     */ import android.content.ComponentName;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.IntentFilter;
/*     */ import android.content.ServiceConnection;
/*     */ import android.content.SharedPreferences;
/*     */ import android.net.ConnectivityManager;
/*     */ import android.net.NetworkInfo;
/*     */ import android.net.wifi.WifiInfo;
/*     */ import android.net.wifi.WifiManager;
/*     */ import android.os.Bundle;
/*     */ import android.os.IBinder;
/*     */ import android.preference.PreferenceManager;
/*     */ import android.view.View;
/*     */ import android.widget.Button;
/*     */ import android.widget.EditText;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.TextView;
/*     */ import android.widget.Toast;
/*     */ import com.device.DeviceCommand;
/*     */ import com.elimei.R;
import com.elimei.elimei.service.MessageService;
/*     */ import com.elimei.elimei.utils.ImmersionBar;
/*     */ import com.network.WifiAdmin;
/*     */ import com.util.BaseActivity;
/*     */ import com.util.MessageToast;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;

/*     */
/*     */
/*     */
/*     */ public class WiFiSettingActivity
        /*     */ extends BaseActivity
        /*     */ {
    /*  38 */   public static String WIFI_SETTING_TAG = "WIFI_SETTING_TAG";
    /*  39 */   public static int TAG_ENGINER = 1;
    /*     */
    /*     */   private Service service;
    /*     */   private boolean isBinded = false;
    /*  43 */ DeviceCommand cmd = new DeviceCommand();
    /*     */
    /*     */
    /*     */ RelativeLayout setting_ssid_layout;
    /*     */
    /*     */   private boolean isFirst = true;
    /*     */
    /*  50 */ int gl_channel = 1;
    /*  51 */ int gl_modify_channel = 1;
    /*     */
    /*     */   private Button btn_setting;
    /*     */ EditText wifi_ssid;
    /*     */ JSONObject obj;
    /*     */   private String gl_ssid;
    /*     */   private int gl_tx_retry;
    /*     */   private String gl_pwd;
    /*     */   private WifiAdmin wifiAdmin;
    /*     */   private WifiManager mwifiManager;
    /*     */   private boolean isScan = false;
    /*     */ TextView wifi_channel;
    /*     */   private boolean gl_isSetting_WiFi = false;
    /*     */ SharedPreferences prefs;
    /*  65 */ int gl_mode = 0;
    /*     */
    /*     */ EditText et_tx_retry;
    /*     */
    /*     */ EditText et_udp_type;
    /*     */
    /*     */   private int gl_udp_type;

    /*     */
    /*     */
    protected void onCreate(Bundle savedInstanceState) {
        /*  74 */
        super.onCreate(savedInstanceState);
        /*  75 */
        setContentView(R.layout.activity_set_wifi);
        /*  76 */
        ImmersionBar.with(this).statusBarColor("#Fe9900").init();
        /*  77 */
        setTitle("设备WiFi设置");
        /*  78 */
        setLeftViewWillBack();
        /*  79 */
        this.wifiAdmin = new WifiAdmin(this);
        /*  80 */
        this.mwifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        /*  81 */
        String s = this.mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "");
        /*  82 */
        String s1 = s.substring(s.indexOf("_") + 1, s.length());
        /*  83 */
        TextView a = (TextView) findViewById(R.id.chushi);
        /*  84 */
        a.setText(s1);
        /*  85 */
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        /*  86 */
        getParaFormIntent();
        /*     */
        /*  88 */
        doBind();
        /*     */
        /*     */
        /*     */
        /*     */
        try {
            /*  93 */
            IntentFilter intentFilter = new IntentFilter();
            /*  94 */
            intentFilter.addAction("BROADCAST_SET_WIFI_PARAMETER");
            /*  95 */
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            /*  96 */
            intentFilter.addAction("BROADCAST_GET_DEVICE_PARAMETER");
            /*  97 */
            registerReceiver(this.receiver, intentFilter);
            /*  98 */
        } catch (Exception e) {
            /*  99 */
            e.printStackTrace();
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    private void getExtra() {
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    protected void onDestroy() {
        /* 112 */
        super.onDestroy();
        /* 113 */
        unregisterReceiver(this.receiver);
        /* 114 */
        doUnbind();
        /*     */
    }

    /*     */
    /*     */
    /* 118 */
    private void getParaFormIntent() {
        this.gl_isSetting_WiFi = false;
    }

    /*     */
    /*     */
    /*     */
    protected void initUI() {
        /* 122 */
        getExtra();
        /*     */
        /*     */
        /*     */
        /*     */
        /*     */
        /* 128 */
        this.wifi_ssid = (EditText) findViewById(R.id.wifi_ssid);
        /*     */
        /*     */
        /* 131 */
        this.btn_setting = (Button) findViewById(R.id.btn_setting);
        /* 132 */
        this.btn_setting.setOnClickListener(new View.OnClickListener()
                /*     */ {
            /*     */
            public void onClick(View v)
            /*     */ {
                /* 136 */
                WiFiSettingActivity.this.obj = new JSONObject();
                /* 137 */
                boolean isModify = false;
                /*     */
                try {
                    /* 139 */
                    WiFiSettingActivity.this.obj.put("type", "setwifiparameters");
                    /* 140 */
                    if (WiFiSettingActivity.this.gl_channel != WiFiSettingActivity.this.gl_modify_channel) {
                        /* 141 */
                        WiFiSettingActivity.this.obj.put("channel", WiFiSettingActivity.this.gl_modify_channel);
                        /* 142 */
                        isModify = true;
                        /*     */
                    }
                    /* 144 */
                    String currentPrefix = WiFiSettingActivity.this.prefs.getString("SHARED_PREFERENCE_SSID_PREFIX_KEY", "SMARP_");
                    /* 145 */
                    if (!WiFiSettingActivity.this.wifi_ssid.getText().toString().equals("") &&
                            /* 146 */                 !(currentPrefix + WiFiSettingActivity.this.wifi_ssid.getText().toString()).equals(WiFiSettingActivity.this.gl_ssid)) {
                        /* 147 */
                        WiFiSettingActivity.this.obj.put("ssid", currentPrefix + WiFiSettingActivity.this.wifi_ssid.getText().toString());
                        /* 148 */
                        WiFiSettingActivity.this.gl_ssid = currentPrefix + WiFiSettingActivity.this.wifi_ssid.getText().toString();
                        /* 149 */
                        isModify = true;
                        /*     */
                    }

                    if (!isModify) {

                        Toast.makeText(WiFiSettingActivity.this, WiFiSettingActivity.this.getString(R.string.no_modify_wifi_setting), Toast.LENGTH_SHORT).show();

                        return;

                    }

                    WiFiSettingActivity.this.gl_isSetting_WiFi = true;

                    Thread t = new Thread()
                            /*     */ {
                        /*     */
                        public void run() {

                            boolean ret = ((MessageService) WiFiSettingActivity.this.service).send(WiFiSettingActivity.this.obj.toString());

                            if (!ret) {

                                runOnUiThread(new Runnable()
                                        /*     */ {
                                    /*     */
                                    public void run()
                                    /*     */ {

                                        Toast.makeText(WiFiSettingActivity.this, getString(R.string.send_command_fail), Toast.LENGTH_SHORT).show();

                                    }
                                    /*     */
                                });

                            }

                        }
                        /*     */
                    };

                    t.start();

                } catch (JSONException e) {

                    e.printStackTrace();

                }
                /*     */
            }
            /*     */
        });
        /*     */
        /* 210 */
        this.setting_ssid_layout = (RelativeLayout) findViewById(R.id.setting_ssid_layout);
        /*     */
    }


    protected void parserIntent(Intent intent) {
    }

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
    /*     */
    protected void initData() {
    }

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
    protected void initEvent() {
    }

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
    /* 261 */ BroadcastReceiver receiver = new BroadcastReceiver()
            /*     */ {
        /*     */
        public void onReceive(Context context, Intent intent)
        /*     */ {
            /* 265 */
            String action = intent.getAction();
            /* 266 */
            if (action.equals("BROADCAST_SET_WIFI_PARAMETER")) {
                /* 267 */
                int status = intent.getIntExtra("status", 1);
                /* 268 */
                MessageToast.show2(WiFiSettingActivity.this, status);
                /*     */
                /*     */
                /* 271 */
                if (MessageToast.isCommandSuccess(status)) {
                    /* 272 */
                    String currentPrefix = WiFiSettingActivity.this.prefs.getString("SHARED_PREFERENCE_SSID_PREFIX_KEY", "SMARP_");
                    /*     */
                    /* 274 */
                    String tmpSSID = WiFiSettingActivity.this.gl_ssid.replace(currentPrefix, "");
                    /*     */
                    /*     */
                    /* 277 */
                    WiFiSettingActivity.this.prefs.edit().putString("DEVICE_SSID", WiFiSettingActivity.this.gl_ssid).commit();
                    /* 278 */
                    WiFiSettingActivity.this.prefs.edit().putString("DEVICE_PWD", WiFiSettingActivity.this.gl_pwd).commit();
                    /*     */
                    /* 280 */
                    boolean bool = WiFiSettingActivity.this.wifiAdmin.addNetwork(WiFiSettingActivity.this.wifiAdmin.CreateWifiInfo(WiFiSettingActivity.this.gl_ssid, WiFiSettingActivity.this.gl_pwd, 1));
                    /*     */
                }
                /*     */
                /*     */
            }
            /* 284 */
            else if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                /* 285 */
                if (!WiFiSettingActivity.this.gl_isSetting_WiFi) {
                    /*     */
                    return;
                    /*     */
                }

                WifiManager mWifiManager = (WifiManager) WiFiSettingActivity.this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();

                ConnectivityManager connManager = (ConnectivityManager) WiFiSettingActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo mWifi = connManager.getNetworkInfo(1);

                if (mWifi.isConnected() &&
                        mWifiInfo.getSSID() != null && mWifiInfo.getSSID().equals("\"" + WiFiSettingActivity.this.gl_ssid + "\"")) {

                    WiFiSettingActivity.this.gl_isSetting_WiFi = false;

                    ((MessageService) WiFiSettingActivity.this.service).reconnect();

                }

            } else if (action.equals("BROADCAST_GET_DEVICE_PARAMETER")) {

                int resp_status = intent.getIntExtra("resp_status", 0);

                if (!MessageToast.isCommandSuccess(resp_status)) {

                    MessageToast.show2(WiFiSettingActivity.this, resp_status);

                }

                int year = intent.getIntExtra("year", 0);

                int month = intent.getIntExtra("month", 0);

                int day = intent.getIntExtra("day", 0);

                int hour = intent.getIntExtra("hour", 0);

                int min = intent.getIntExtra("min", 0);
                int sec = intent.getIntExtra("sec", 0);

                int tx_retry = intent.getIntExtra("tx_retry", 0);

                int udp_type = intent.getIntExtra("udp_type", 0);

                String timezone = intent.getStringExtra("timezone");

                int channel = intent.getIntExtra("channel", 1);

                WiFiSettingActivity.this.gl_channel = intent.getIntExtra("channel", 1);

                WiFiSettingActivity.this.gl_ssid = intent.getStringExtra("ssid");

                WiFiSettingActivity.this.gl_pwd = intent.getStringExtra("pwd");

                String currentPrefix = WiFiSettingActivity.this.prefs.getString("SHARED_PREFERENCE_SSID_PREFIX_KEY", "SMARP_");

                String tmpSSID = WiFiSettingActivity.this.gl_ssid.replace(currentPrefix, "");

                WiFiSettingActivity.this.gl_tx_retry = tx_retry;

                WiFiSettingActivity.this.gl_udp_type = udp_type;

            }

        }
        /*     */
    };

    public static int getAuth(String auth) {

        if (auth.contains("Enterprise"))
            return 4;

        if (auth.contains("EAP"))
            return 4;

        if (auth.contains("WPA2"))
            return 3;

        if (auth.contains("WPA"))
            return 2;

        if (auth.contains("WEP"))
            return 1;

        if (auth.equals(""))
            return 0;

        if (auth.equals("[ESS]")) {

            return 0;

        }

        return 5;

    }

    public void onBackPressed() {

        super.onBackPressed();

        finish();

    }

    private ServiceConnection conn = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder binder) {

            WiFiSettingActivity.this.isBinded = true;

            WiFiSettingActivity.this.service = ((MessageService.ServiceBinder) binder).getService();

            Thread t = new Thread() {
                /*     */
                public void run() {

                    boolean ret = ((MessageService) WiFiSettingActivity.this.service).
                            send(WiFiSettingActivity.this.cmd.GetDeviceParameter().toString());

                    if (!ret) {
                        runOnUiThread(new Runnable()
                                /*     */ {
                            /*     */
                            public void run()
                            /*     */ {

                                Toast.makeText(WiFiSettingActivity.this, getString(R.string.get_data_fail), Toast.LENGTH_SHORT).show();

                            }
                            /*     */
                        });

                    }

                }
                /*     */
            };

            t.start();

        }

        public void onServiceDisconnected(ComponentName name) {
            WiFiSettingActivity.this.isBinded = false;
        }

    };

    private void doBind() {

        Intent intent = new Intent();

        intent.setClass(this, MessageService.class);

        bindService(intent, this.conn, Context.BIND_AUTO_CREATE);

    }


    private void doUnbind() {

        if (this.isBinded) {

            unbindService(this.conn);

            this.isBinded = false;

        }

    }

}
