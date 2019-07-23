package com.rtspclient;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.media.AudioTrack;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.AudioProc.AEC;
import com.android.VideoCodec.AacDecode;
import com.bumptech.glide.Glide;
import com.dash.Const;
import com.device.BroadCastController;
import com.device.DeviceCommand;
import com.device.WifiApController;
import com.elimei.R;
import com.elimei.elimei.Model.MemBerModel;
import com.elimei.elimei.Model.Result;


import com.elimei.elimei.service.MessageService;
import com.elimei.elimei.service.UdpSocket;
import com.elimei.elimei.utils.SaveBitamptoBmp;
import com.elimei.elimei.utils.Toasty;
import com.elimei.elimei.utils.WifiUtils;
import com.google.gson.Gson;
import com.network.WifiAdmin;

import com.todoroo.aacenc.AACEncoder;
import com.util.BaseActivity;
import com.util.DebugLog;
import com.util.MessageToast;
import com.util.Net;
import com.util.Params;
import com.util.TipsUtils;
import com.util.dialog.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.nio.ByteOrder;
import java.util.ArrayList;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class RTSPClient
        extends BaseActivity
        implements SurfaceHolder.Callback {
    static final String BROADCAST_PUSHTALK_UI = "BROADCAST_PUSHTALK_UI";
    static final String BROADCAST_STOP_RECEIVE = "BROADCAST_STOP_RECEIVE";
    public static AlphaAnimation tipAnimation = new AlphaAnimation(0.1F, 1.0F);

    static final int JNI_INIT_RETURN_EXTRA_COUNT = 0;

    private static boolean gl_isStartRtsp = false;

    public static final int LIBVLC_DECODER = 1;

    public static final int SOFTWARE_DECODER = 2;
    public static final int SOFTWARE_DEFODE_FPS = 120;
    public static final int MSG_TYPE_RESULT = 1;
    private static final int SURFACE_SIZE = 3;
    private static final int MESSAGE_RENDER_VIDEO = 100;
    private static final int MESSAGE_UPDATE_BITMAP = 101;
    private static final int MESSAGE_UPDATE_UI = 102;
    private static final int MESSAGE_START_FPS_THREAD = 103;
    private static final int CHECK_RECEIVE_TIME = 10000;
    private static final String URL = "192.168.99.1";
    final int MSG_PROGRAM_EXIT = -1;
    final int MSG_SHOW_CONNECT_DIALOG = 101;
    final int MSG_DISMISS_CONNECT_DIALOG = 102;
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private String strVideoPath = "rtsp://192.168.99.1/media/stream2";

    private int mDisplayWidth;

    private int mDisplayHeight;
    private RelativeLayout mMainLayout;
    private Intent serviceIntent;
    /*  148 */   private static Handler mHandler = null;

    /*  150 */   private Dialog dialog = null;


    private Thread t_connect;


    private LinearLayout bot_layout;


    /*  160 */   private Handler h = new Handler();

    AlertDialog.Builder alertBuilder;

    AlertDialog alertDialog;

    private static boolean isFirst = true;
    private boolean isScan = false;
    protected static boolean isPause = true;
    private boolean isBinded = false;
    private boolean isShowClick = true;
    private boolean mIsConnection = false;
    private boolean isAlreadyAlert = true;
    private boolean isConnectDevice = false;
    private static boolean isShowResolution = true;
    private static boolean isVideoNeedSleep = false;
    private static boolean isAudioNeedSleep = false;
    /*  177 */ DeviceCommand cmd = new DeviceCommand();

    protected static Service service;
    /*  180 */   private String filename = "";

    /*  182 */   private long exitTime = 0L;
    /*  183 */   private static int frameCount = 0;
    /*  184 */   private static int errorCount = 0;

    /*  186 */   private static final Lock onscreenLock = new ReentrantLock();


    /*  190 */   static int gl_sd_status = -1;
    /*  191 */   static int gl_rec_running = -1;
    /*  192 */   static int gl_sd_capacity = -1;
    /*  193 */   static int gl_sd_fromat = -1;

    /*  195 */   private static int gl_width = 0;

    /*  197 */   private static Handler uiHandler = new Handler();

    private static boolean gl_isfirstSetResolution = true;

    private static boolean gl_isFirstStartStream = true;
    /*  202 */   private static int CURR_RESOLUTION = 0;


    private static SharedPreferences prefs;


    private static boolean isGetData = false;


    private static Decoder vgaDecoder;

    private static Decoder vgaDecoder2;

    private static boolean gl_isStopGetVideo = false;

    private PowerManager.WakeLock mWakeLock;

    private boolean gl_isFirstStartActivity = true;

    /*  221 */   private long preTimeMillis = 0L;
    /*  222 */   private int SEEKBAR_MAX_VALUE = 250;

    private static boolean isRecordOn = false;

    private SharedPreferences mSettings;

    private boolean mSwitchingView;

    private String mLocation;

    private boolean mCanSeek;

    private int mVideoHeight;

    private int mVideoWidth;

    private int mVideoVisibleHeight;
    private int mVideoVisibleWidth;
    private int mSarNum;
    private int mSarDen;
    private LinearLayout Llayout_preview;
    private static boolean bGoDecode = false;
    private boolean isGetVideoPath = false;
    /*  245 */   private int debugCount = 0;
    /*  246 */   private long preTime = 0L;
    private boolean bDebugMode = false;
    private boolean isWiFiConnect = true;
    private static Context gl_ctx;
    private static boolean isCanSwitchDual = false;
    /*  251 */   private String gl_rtspName = "";
    /*  252 */   private String gl_mask = "";
    /*  253 */   private int gl_fps = 0;
    /*  254 */   private static int gl_resolution = 0;


    private boolean gl_isHomeKeyPress = false;

    private long gl_bitrate;

    private static boolean gl_bool_isNoSdCard = false;

    private static boolean isRestartActivity = false;

    public static Activity gl_activity;

    static byte[] sps;

    static byte[] pps;

    static int gl_isStartRecordState = 0;
    static int gl_recordstatus = 0;
    static byte[] gl_SPSpps;
    static byte[] frameHeader = {0, 0, 0, 1};

    private long gl_startRecordTime = 0L;
    private ProgressDialog recordWaidDialog;
    private ProgressDialog pushtalkWaitDialog;
    private boolean isGoRemote = false;
    private boolean isSyncTime = false;
    public static int gl_FFmpeg = 1;
    private static boolean isInit = false;
    private static byte[] tmpAudio = new byte[20000];
    private static final Lock audioDecodeLock = new ReentrantLock();
    private static AudioTrack mAudioTrack = null;

    private Handler mAudioHanHandler;
    private LayoutInflater mInflater;
    private boolean mIsDoRtspFlow = false;
    Thread mCheckDataThread = null;
    boolean mIsCheckRun = false;
    public static int CHECK_TIME = 3000;

    public static boolean isAudioPlay = true;

    private static WifiApController mApController;

    private boolean mIsHotspot = false;
    private static String mStationDeviceIp = "";
    public static int mSDFormatStatus = -1;
    public static int mSDCapacityStatus = -1;

    public static boolean mIsBroadCastDevice = false;
    private static BroadCastController mBcController;
    public Button btn_title_right;
    private JSONObject mListObject;
    private static boolean mIsDeBugMode;
    private static boolean isInRtspclient = false;
    PowerManager mPowerManager;
    private static UdpSocket mSocket;
    private byte[] mOutputAac = new byte[20000];
    private static int Sample_Rate = 11025;

    private static boolean isTalk = false;

    public static TextureView sf_preview2;

    public static TextureView sf_preview4;

    public static TextureView surfaceView;
    public static TextureView sf_preview;
    public static TextureView sf_preview5;
    public static TextureView sf_preview6;
    public static Surface surface1;
    public static Surface surface2;
    public static Surface surface3;
    public static Surface surface4;
    public static Surface surface5;
    public static Surface surface6;
    /*  332 */   private int currentnunm = 2;

    public ProgressDialog progressDialog;

    private ImageView tupian;

    private String url1;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
    public String url6;
    private String s;
    private ImageView zhishi;
    private ImageView back1;
    private ImageView back2;
    private ImageView back3;
    private ImageView back4;
    private ImageView back5;
    private ImageView jiancejieguoi;
    private TextView jiancejieguot;
    private LinearLayout testresult;
    private MemBerModel.ResultBean.MemberBean bean;
    private String from;
    public int currentnum;
    public boolean isrechoice = false;
    public boolean isclosr = true;
    public WifiAdmin wifiAdmin;

    public void setAudioPlayFlag(boolean isPlay) {
        isAudioPlay = isPlay;
        if (!isPlay) ;
    }


    public static void onRgbaDataReceive(byte[] data) {
    }


    public static void onAudioData(byte[] data, int timeStamp) {
        if (!isAudioPlay) {
            return;
        }
        audioDecodeLock.lock();
        if (mAudioTrack != null) {
            int audioLength = AacDecode.audioDecode(data, data.length, tmpAudio);
            if (audioLength != -1) {

                try {

                    mAudioTrack.write(tmpAudio, 0, audioLength);
                } catch (Exception e) {

                    Log.e("错误", "390" + e.getMessage());
                }
            }
        }

        audioDecodeLock.unlock();
    }


    public static void onVideoData(byte[] data, int timeStamp) {

        byte[] tmpFrame = new byte[data.length + 4];

        System.arraycopy(frameHeader, 0, tmpFrame, 0, frameHeader.length);

        System.arraycopy(data, 0, tmpFrame, frameHeader.length, data.length);

        Log.d("Allen", "video data len=" + data.length);

        if (!isInit) {

            if (data[0] == 104) {

                pps = tmpFrame;
            }


            if (data[0] == 103) {
                sps = tmpFrame;
            }

            if (sps != null && pps != null) {
                gl_SPSpps = new byte[sps.length + pps.length];
                System.arraycopy(sps, 0, gl_SPSpps, 0, sps.length);
                System.arraycopy(pps, 0, gl_SPSpps, sps.length, pps.length);
                isInit = true;
            } else {
                return;
            }
        }

        if (gl_FFmpeg == 0) {

            if (data[0] == 103) {
                for (int i = 0; i < data.length; i++) {
                    if (data[i] != Decoder.SPS_HD[i]) {
                        isRestartActivity = true;
                        sendBroadCast("BROADCAST_STOP_RECEIVE", "");


                        return;
                    }
                }
            }


            if (vgaDecoder == null) {
                if (surface1 == null) {
                    return;
                }
                MediaCodecRun(surface1, surface1);
            }
            if (data[0] == 101) {
                byte[] tmpI = new byte[gl_SPSpps.length + data.length + 4];
                System.arraycopy(gl_SPSpps, 0, tmpI, 0, gl_SPSpps.length);
                System.arraycopy(tmpFrame, 0, tmpI, gl_SPSpps.length, tmpFrame.length);
                vgaDecoder.decode(tmpI);
                vgaDecoder2.decode(tmpI);
            } else {
                vgaDecoder.decode(tmpFrame);
                vgaDecoder2.decode(tmpFrame);
            }
        }
    }

    private void releaseAudioTrack() {
        try {
            /*  462 */
            if (mAudioTrack != null) {
                /*  463 */
                mAudioTrack.stop();
                /*  464 */
                mAudioTrack.release();
            }
            /*  466 */
        } catch (Exception e) {

            /*  468 */
            Log.e("错误", "471" + e.getMessage());
        } finally {
            /*  470 */
            mAudioTrack = null;
        }
    }

    public void setAudioTrack() {
        /*  475 */
        int sampleRate = 11025;
        /*  476 */
        int buffsize = 0;
        /*  477 */
        buffsize = AudioTrack.getMinBufferSize(sampleRate, 4, 2);


        /*  480 */
        mAudioTrack = new AudioTrack(3, sampleRate, 4, 2, buffsize, 1);


        /*  483 */
        this.mAudioHanHandler.post(new Runnable() {

            public void run() {
                /*  488 */
                mAudioTrack.play();
            }
        });
    }

//    public static void onExtraData(byte[] data, int length) {
//        /*  494 */
//        LoadingDialog.dismiss();
//        /*  495 */
//        if (data[5] == 0) {
//            /*  496 */
//            if (gl_sd_status != (data[2] & true)) {
//                /*  497 */
//                gl_sd_status = data[2] & true;
//                /*  498 */
//                onNotify(String.valueOf(gl_sd_status), 0);
//            }
//            /*  500 */
//            if (gl_sd_status == 1) {
//                /*  501 */
//                if (gl_rec_running != (data[2] >> 1 & true)) {
//                    /*  502 */
//                    gl_rec_running = data[2] >> 1 & true;
//                    /*  503 */
//                    onNotify(String.valueOf(gl_rec_running), 3);
//                }
//
//                /*  506 */
//                if (gl_sd_capacity != (data[2] >> 2 & true)) {
//                    /*  507 */
//                    gl_sd_capacity = data[2] >> 2 & true;
//                    /*  508 */
//                    onNotify(String.valueOf(gl_sd_capacity), 4);
//                }
//                /*  510 */
//                if (gl_sd_fromat != (data[2] >> 3 & true)) {
//                    /*  511 */
//                    gl_sd_fromat = data[2] >> 3 & true;
//                    /*  512 */
//                    onNotify(String.valueOf(gl_sd_fromat), 5);
//                }
//            } else {
//                /*  515 */
//                gl_sd_capacity = 0;
//                /*  516 */
//                gl_sd_fromat = 1;
//                /*  517 */
//                gl_rec_running = -1;
//            }
//        }
//    }


    public static void onError(int status) {
    }


    public static void onNotify(String data, int type) {
        final int tmpResolution;
        int valFormat;
        int valCapa;
        /*  539 */
        switch (type) {
            case 0:
                /*  541 */
                Log.d("Allen", "notify status");
                /*  542 */
                if (Integer.valueOf(data).intValue() == 0) {
                    /*  543 */
                    gl_bool_isNoSdCard = true;
                    /*  544 */
                    recRunningUISetting(Integer.valueOf(data).intValue(), 0);
                    break;
                }
                /*  546 */
                gl_bool_isNoSdCard = false;
                /*  547 */
                mSDFormatStatus = -1;
                /*  548 */
                mSDCapacityStatus = -1;
                break;


            case 1:
                /*  553 */
                Log.d("Allen", "NOTIFY_RETRY");
                /*  554 */
                sendBroadCast("BROADCAST_PREVIEW_RETRY", "");
                break;


            case 2:
                /*  559 */
                if (Integer.valueOf(data).intValue() == 640) {
                    /*  560 */
                    gl_resolution = 0;
                    /*  561 */
                } else if (Integer.valueOf(data).intValue() == 1280) {
                    /*  562 */
                    gl_resolution = 1;
                }
                /*  564 */
                isRestartActivity = true;

                /*  566 */
                sendBroadCast("BROADCAST_STOP_RECEIVE", "");
                break;


            case 3:
                /*  573 */
                gl_bool_isNoSdCard = false;
                /*  574 */
                recRunningUISetting(Integer.valueOf(data).intValue(), 3);
                break;

            case 4:
                /*  578 */
                valCapa = Integer.valueOf(data).intValue();
                /*  579 */
                if (mSDCapacityStatus != valCapa) {
                    /*  580 */
                    mSDCapacityStatus = valCapa;
                    /*  581 */
                    if (valCapa == 1) {
                        /*  582 */
                        sendBroadCast("BROADCAST_FORMATE_SD_CARD", gl_activity.getString(R.string.sd_remaining_size_is_too_small));
                    }
                }
                break;

            case 5:
                valFormat = Integer.valueOf(data).intValue();
                if (mSDFormatStatus != valFormat) {
                    mSDFormatStatus = valFormat;
                    if (valFormat == 0) {
                        sendBroadCast("BROADCAST_FORMATE_SD_CARD", gl_activity.getString(R.string.sd_card_type_error));
                    }
                }
                break;


            case 10:
                sendBroadCast("BROADCAST_PUSHTALK_UI", "");
                Log.d("Allen", "BROADCAST_PUSHTALK_UI 0000");

                tmpResolution = Integer.valueOf(data).intValue();

                if (tmpResolution == 640) {

                    gl_resolution = 0;
                } else if (tmpResolution == 1280) {

                    gl_resolution = 1;
                }

                gl_width = tmpResolution;
                /*  617 */
                if (tmpResolution == 1280 && getPerformanceState() == 1) {
                    /*  618 */
                    setFFmpegDraw(0);
                    /*  619 */
                    gl_FFmpeg = 0;
                }
                /*  621 */
                gl_activity.runOnUiThread(new Runnable() {

                    public void run() {
                        /*  626 */
                        if (tmpResolution != 1280) {

                            /*  629 */
                            if (tmpResolution == 640) ;
                        }
                    }
                });
                break;


            case 11:
                /*  637 */
                showDebugMsg("Read RTSP data timeout");
                break;

            case 12:
                /*  641 */
                showDebugMsg("Create RTSP socket");
                break;
            case 13:
                /*  644 */
                showDebugMsg("packet lost");
                break;
        }
    }


    private static void recRunningUISetting(final int state, final int type) {
        /*  652 */
        uiHandler.post(new Runnable() {

            public void run() {
                /*  657 */
                switch (type) {
                    case 0:
                        /*  659 */
                        if (state == 0) {


                            /*  663 */
                            isRecordOn = false;
                        }
                        break;
                    case 3:
                        /*  667 */
                        if (state == 1) {



                            /*  671 */
                            isRecordOn = true;

                            break;
                        }

                        /*  676 */
                        isRecordOn = false;
                        break;
                }
            }
        });
    }


    private static void sendBroadCast(String tag, String msg) {
        /*  685 */
        Intent intent = new Intent(tag);
        /*  686 */
        intent.putExtra("msg", msg);
        /*  687 */
        gl_activity.sendBroadcast(intent);
    }

    private static void toastMessage(final String msg) {
        /*  691 */
        uiHandler.post(new Runnable() {

            public void run() {
                /*  696 */
                Toast.makeText(RTSPClient.gl_activity, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    private void acquireWakeLock() {
        /*  712 */
        if (this.mWakeLock == null) {
            /*  713 */
            PowerManager mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            /*  714 */
            this.mWakeLock = mPowerManager.newWakeLock(26, getClass().getCanonicalName());
            /*  715 */
            this.mWakeLock.acquire();
        }
    }

    private void releaseWakeLock() {
        /*  720 */
        if (this.mWakeLock != null && this.mWakeLock.isHeld()) {
            /*  721 */
            this.mWakeLock.release();
            /*  722 */
            this.mWakeLock = null;
        }
    }


    private class ViewHolder2 {
        TextView textname;


        TextView text_hotspot;
    }


    public WifiConfiguration getWifiApConfiguration() {
        try {
            /*  892 */
            Method method = this.wifiManager.getClass().getMethod("getWifiApConfiguration", new Class[0]);

            /*  894 */
            return (WifiConfiguration) method.invoke(this.wifiManager, new Object[0]);
        }
        /*  896 */ catch (Exception e) {

            /*  898 */
            Log.e(getClass().toString(), "", e);

            Log.e("错误", "902" + e.getMessage());

            return null;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        acquireWakeLock();
        this.from = getIntent().getStringExtra("from");

        setLeftViewWillBack();
        this.mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mIsBroadCastDevice = true;
        Const.CURRENT_DEVICE_IP = "192.168.99.1";
        mBcController = new BroadCastController(null);
        mBcController.createSocket(false);
        mBcController.setTimeout(1000);
        CreateMessageHandler();
        this.mInflater = LayoutInflater.from(this);
        mApController = new WifiApController(this);
        this.isScreenOff = false;
        gl_activity = this;
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("BROADCAST_PREVIEW_RETRY");
            intentFilter.addAction(Const.SNAPSHOT);
            registerReceiver(this.receiverUnlock, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("错误", "946" + e.getMessage());
        }
        setTitle("肌肤检测");
        this.testresult = (LinearLayout) findViewById(R.id.testresult);

        Serializable data = getIntent().getSerializableExtra("data");

        if (data != null) {
            bean = (MemBerModel.ResultBean.MemberBean) data;
        }
        findViewById(R.id.testresult).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                RTSPClient.this.onRTSPStop();

                RTSPClient.this.nest();
            }
        });

        initRTSPstart();
    }

    private void nest() {
        /*  967 */
        WifiManager mwifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        /*  968 */
        if (mwifiManager.isWifiEnabled()) {
            /*  969 */
            if (mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "").contains("SMARP_")) {
                /*  970 */
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                /*  971 */
                builder.setMessage("拍照完毕，请关闭设备并连结本地wifi").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*  974 */
                        WifiUtils.starttowifi(RTSPClient.this);
                    }
                    /*  976 */
                }).show();
            } else {
                /*  978 */
//                post();
            }
        } else {
            /*  981 */
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            /*  982 */
            builder.setMessage("拍照完毕，请关闭设备并连结本地wifi").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    /*  985 */
                    WifiUtils.starttowifi(RTSPClient.this);
                }
                /*  987 */
            }).show();
        }
    }

    public void post() {

        if (TextUtils.isEmpty(this.url1)) {
            Toasty.warning(getBaseContext(), "未选择额头处").show();
            return;
        }

        if (TextUtils.isEmpty(this.url2)) {
            Toasty.warning(getBaseContext(), "未选择脸颊处").show();
            return;
        }

        if (TextUtils.isEmpty(this.url3)) {
            Toasty.warning(getBaseContext(), "未选择眼尾处").show();
            return;
        }

        if (TextUtils.isEmpty(this.url4)) {

            Toasty.warning(getBaseContext(), "未选择色素处").show();
            return;
        }

        if (TextUtils.isEmpty(this.url5)) {
            Toasty.warning(getBaseContext(), "未选择敏感处").show();
            return;
        }
        if (TextUtils.isEmpty(this.url6)) {
            Toasty.warning(getBaseContext(), "未选择鼻翼处").show();

            return;
        }


        ArrayList<String> list = new ArrayList<String>();

        list.add(this.url1);

        list.add(this.url2);

        list.add(this.url3);

        list.add(this.url4);

        list.add(this.url5);

        list.add(this.url6);
        initMessag(list);
    }


    /* 1029 */ BroadcastReceiver receiverUnlock = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            /* 1032 */
            if (intent.getAction().equals("android.intent.action.USER_PRESENT")) {
                /* 1033 */
                Log.d("Allen", "Intent.ACTION_USER_PRESENT 000=" + RTSPClient.this.isScreenOff);
                /* 1034 */
                if (RTSPClient.this.isScreenOff) {
                    /* 1035 */
                    Log.d("Allen", "powerkey press offscreen RTSPTempActivity");
                    /* 1036 */
                    if (RTSPClient.this.mCheckDataThread != null) {
                        /* 1037 */
                        RTSPClient.this.WaitForThreadStop(RTSPClient.this.mCheckDataThread);
                        RTSPClient.this.mCheckDataThread = null;
                    }
                    uiHandler.postDelayed(new Runnable() {
                        public void run() {
                        }
                    }, 300L);
                }
            }
            /* 1059 */
            else if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {




                /* 1064 */
                RTSPClient.this.isScreenOff = true;
            }

            /* 1067 */
            if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                /* 1068 */
                String reason = intent.getStringExtra("reason");
                /* 1069 */
                if ("homekey".equals(reason)) {
                    /* 1070 */
                    RTSPClient.mIsBroadCastDevice = true;
                    /* 1071 */
                    Const.CURRENT_DEVICE_IP = "192.168.99.1";
                    /* 1072 */
                    if (RTSPClient.service != null) {
                        /* 1073 */
                        ((MessageService) RTSPClient.service).close();
                    }
                    /* 1075 */
                    if (!RTSPClient.isBackground(RTSPClient.gl_activity)) ;


                }


            }
            /* 1097 */
            else if (intent.getAction().equals("BROADCAST_PREVIEW_RETRY")) {
                /* 1098 */
                gl_isStartRtsp = false;
            }


        }
    };


    int rtspCount;


    public static boolean isBackground(Context context) {
        /* 1121 */
        Log.d("Allen", "isBackground 0000");
        /* 1122 */
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        /* 1123 */
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        /* 1124 */
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            /* 1125 */
            if (appProcess.processName.equals(context.getPackageName())) {

                /* 1127 */
                Log.i(context.getPackageName(), "此appimportace =" + appProcess.importance + ",context.getClass.getName=" + context.getClass().getName());
                /* 1128 */
                if (appProcess.importance != 100) {
                    /* 1129 */
                    Log.i(context.getPackageName(), "處於後台" + appProcess.processName);


                    /* 1132 */
                    return true;
                }

                /* 1135 */
                return false;
            }
        }

        /* 1139 */
        return false;
    }


    protected void initUI() {
    }


    protected void parserIntent(Intent intent) {
    }


    @SuppressLint({"NewApi"})
    protected void initData() {
        /* 1156 */
        gl_isfirstSetResolution = true;

        /* 1158 */
        DisplayMetrics dm = new DisplayMetrics();
        /* 1159 */
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        /* 1160 */
        this.mDisplayWidth = dm.widthPixels;
        /* 1161 */
        this.mDisplayHeight = dm.heightPixels;

        gl_ctx = this;
        /* 1165 */
        checkIsFwFolderCreate();

        /* 1167 */
        this.mSettings = PreferenceManager.getDefaultSharedPreferences(this);


        /* 1170 */
        this.serviceIntent = new Intent(this, MessageService.class);
        /* 1171 */
        startService(this.serviceIntent);


        /* 1174 */
        init(1);
        /* 1175 */
        doBind();

        /* 1177 */
        this.wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        /* 1178 */
        this.wifiAdmin = new WifiAdmin(this);
        /* 1179 */
        this.gl_isFirstStartActivity = true;
    }

    protected void initEvent() {
        /* 1185 */
        initDone();
        /* 1186 */
        setOrientation((getResources().getConfiguration()).orientation);
    }


    @Override
    public void onResume() {
        super.onResume();

        Log.d("Allen", "Lifecycle onResume");

        DebugLog.i("RTSP onResume");

        if (this.isScreenOff) {
            return;
        }

        if (!this.isRTSPStop) {
            onRTSPResume();
        }
    }

    private void openPushTalkProgressDialog(String title, String msg) {

        this.pushtalkWaitDialog = new ProgressDialog(this);

        this.pushtalkWaitDialog.setTitle(title);

        this.pushtalkWaitDialog.setMessage(msg);

        this.pushtalkWaitDialog.setIndeterminate(true);

        this.pushtalkWaitDialog.setCanceledOnTouchOutside(false);

        this.pushtalkWaitDialog.setCancelable(false);

        this.pushtalkWaitDialog.show();
    }

    protected void onRTSPResume() {
        this.mSwitchingView = false;
        if (!this.isScreenOff) {
            checkDrawTime();
        }
    }

    private void initRTSPstart() {

        mStationDeviceIp = prefs.getString("SHARED_PREFERENCE_DEVICE_IP", "");

        String tmpList = prefs.getString("DEVICE_VERSION_LIST", "");

        int tmpForceI = prefs.getInt("SP_FORCE_I", 0);

        int tmpDebugFlag = prefs.getInt("SP_DEBUG_MODE", 0);
        Log.d("Allen", "tmpDebugFlag =" + tmpDebugFlag);
        if (tmpDebugFlag == 1) {
            mIsDeBugMode = true;
        } else {
            mIsDeBugMode = false;
        }

        if (tmpForceI == 1) {

            setForceITag(1);

        } else if (tmpForceI == 0) {
            setForceITag(0);
        }
        int tmpDrop = prefs.getInt("dropflag", 1);
        gl_isStartRecordState = 0;

        if (tmpDrop == 1) {
            setDropFrame(1);
        } else if (tmpDrop == 0) {
            setDropFrame(0);
        }
        try {
            this.mListObject = new JSONObject(tmpList);
        } catch (JSONException e1) {
            e1.printStackTrace();
            Log.e("错误", "1305" + e1.getMessage());
            this.mListObject = new JSONObject();
        }
        isInRtspclient = true;
        sps = null;
        pps = null;
        isInit = false;
        gl_FFmpeg = 1;
        gl_isStartRtsp = false;
        DebugLog.i("RTSP onStart");
        if (!this.isScreenOff) {
            onRTSPStart();
        }
        if (this.isGoRemote == true) {

            this.isGoRemote = false;
            mHandler.postDelayed(new Runnable() {
                public void run() {
                }
            }, 3000L);
        } else {
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    /* 1335 */
                    LoadingDialog.dismiss();
                }
            }, 3000L);
        }

    }

    protected boolean isRTSPStart = false;

    private int getCurrentRunVersion() {
        /* 1344 */
        int res = -1;
        /* 1345 */
        String tmpSssid = getCurrentSsid();

        /* 1347 */
        if (this.mListObject.has(tmpSssid)) {
            try {
                /* 1349 */
                res = this.mListObject.getInt(tmpSssid);
                /* 1350 */
            } catch (JSONException e) {

                /* 1352 */
                Log.e("错误", "1356" + e.getMessage());
                /* 1353 */
                e.printStackTrace();
            }
        }
        /* 1356 */
        return res;
    }

    private void getIQVersion(int i) {
        /* 1360 */
        Log.d("Allen", "getIQVersion i=" + i);
        /* 1361 */
        int tmpVersion = getCurrentRunVersion();
        /* 1362 */
        if (tmpVersion == 0) {

            /* 1364 */
            Const.Current_version = 0;
            /* 1365 */
            startRTSP();
            /* 1366 */
            runOnUiThread(new Runnable() {


                public void run() {
                }
            });
            /* 1375 */
        } else if (tmpVersion == 1) {
            /* 1376 */
            Const.Current_version = 1;

            /* 1378 */
            if (!isCmdSocketConnect() &&
                    /* 1379 */         service != null) {
                /* 1380 */
                Log.d("Allen", "service socket reconnect");
                /* 1381 */
                ((MessageService) service).reconnect();
            }


            /* 1385 */
            openRTSP(4);


        } else {




            /* 1396 */
            Thread t = new Thread(new Runnable() {


                public void run() {
                    /* 1402 */
                    if (RTSPClient.service != null && ((MessageService) RTSPClient.service).isSocketAlive()) {
                        /* 1403 */
                        boolean bool = ((MessageService) RTSPClient.service).send(RTSPClient.this.cmd.getIqVersion().toString());
                    }

                    /* 1406 */
                    RTSPClient.CHECK_TIME = 3000;
                }
            });


            /* 1411 */
            t.start();
        }
    }


    protected void onRTSPStart() {
        /* 1417 */
        this.isRTSPStop = false;
        /* 1418 */
        this.isRTSPStart = true;
        /* 1419 */
        this.gl_isHomeKeyPress = false;
        /* 1420 */
        isRestartActivity = false;
        /* 1421 */
        gl_isStartRecordState = 0;

        /* 1423 */
        Log.d("Allen", "Receiveraaaaqa register ");

        try {
            /* 1426 */
            IntentFilter intentFilter = new IntentFilter();
            /* 1427 */
            intentFilter.addAction("BROADCAST_SET_RECORD_STATUS");
            /* 1428 */
            intentFilter.addAction("BROADCAST_SET_RECORD_PARAMETER");
            /* 1429 */
            intentFilter.addAction("BROADCAST_SET_RECORD_VOLUMN");
            /* 1430 */
            intentFilter.addAction("BROADCAST_GET_BATTERY_STATUS");
            /* 1431 */
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            /* 1432 */
            intentFilter.addAction("BROADCAST_STREAM_VIDEO");
            /* 1433 */
            intentFilter.addAction("BROADCAST_GET_RECORD_STATUS");
            /* 1434 */
            intentFilter.addAction("BROADCAST_TAKE_PICTURE");
            /* 1435 */
            intentFilter.addAction("broadcast_socket_connect");
            /* 1436 */
            intentFilter.addAction("BROADCAST_FORMATE_SD_CARD");
            /* 1437 */
            intentFilter.addAction("BROADCAST_SET_SD_FORMAT");
            /* 1438 */
            intentFilter.addAction("BROADCAST_STOP_RECEIVE");
            /* 1439 */
            intentFilter.addAction("BROADCAST_GET_IQ_VERSION");
            /* 1440 */
            intentFilter.addAction("BROADCAST_PUSHTALK_UI");
            intentFilter.addAction(Const.SNAPSHOT);
            /* 1441 */
            registerReceiver(this.receiver, intentFilter);
            /* 1442 */
        } catch (Exception e) {
            /* 1443 */
            e.printStackTrace();
            /* 1444 */
            Log.e("错误", "1448" + e.getMessage());
        }

        gl_width = 0;

        /* 1468 */
        vgaDecoder = null;
        /* 1469 */
        vgaDecoder2 = null;

        /* 1486 */
        gl_isStopGetVideo = false;
        /* 1487 */
        isShowResolution = true;





        /* 1493 */
        prefs.getString("DEVICE_SSID", "");
        /* 1494 */
        isPause = false;
        /* 1495 */
        (new Thread(new Runnable() {
            public void run() {
                /* 1498 */
                RTSPClient.this.connect(0);
            }
            /* 1500 */
        })).start();

        /* 1502 */
        int res = AacDecode.audioDecodeInit();
        /* 1503 */
        this.mAudioHanHandler = new Handler();
        /* 1504 */
        setAudioTrack();

        try {
            /* 1507 */
            Thread.sleep(100L);
            /* 1508 */
        } catch (InterruptedException e) {
            /* 1509 */
            e.printStackTrace();
            /* 1510 */
            Log.e("错误", "1514" + e.getMessage());
        }
    }


    private boolean isScreenOff = false;
    private boolean mIsWifiAutoConnect = false;

    private boolean connect(int i) {
        /* 1519 */
        Log.d("Allen", "connect aaaaa");
        /* 1520 */
        initialDecode(false, 2, 0);
        /* 1521 */
        gl_sd_status = -1;

        /* 1523 */
        gl_rec_running = -1;
        /* 1524 */
        gl_sd_capacity = -1;
        /* 1525 */
        gl_sd_fromat = -1;

        /* 1527 */
        String gL__current_ssid = prefs.getString("DEVICE_SSID", "").replace("\"", "");

        /* 1529 */
        String gL_wifiManager_ssid = this.wifiManager.getConnectionInfo().getSSID().toString().replace("\"", "");
        /* 1530 */
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        /* 1531 */
        NetworkInfo mWifi = connManager.getNetworkInfo(1);
        /* 1532 */
        if (isWiFiConnected()) {
            /* 1533 */
            String currentPrefix = prefs.getString("SHARED_PREFERENCE_SSID_PREFIX_KEY", "SMARP_");

            printLog("connect() mStationDeviceIp=" + mStationDeviceIp);

            printLog("connect() mIsBroadCastDevice=" + mIsBroadCastDevice);

            printLog("connect() gL_wifiManager_ssid=" + gL_wifiManager_ssid);

            if (!getCurrentSsid().contains("SMARP_")) {
                /* 1539 */
                if (mIsBroadCastDevice == true) {
                    /* 1540 */
                    List<JSONObject> list = mBcController.startScanSync();
                    /* 1541 */
                    if (list.isEmpty()) {
                        /* 1542 */
                        return false;
                    }
                    /* 1544 */
                    JSONObject obj = (JSONObject) list.get(0);
                    try {
                        /* 1546 */
                        String devIp = obj.getString("ip");
                        /* 1547 */
                        mStationDeviceIp = devIp;
                        /* 1548 */
                        Const.CURRENT_DEVICE_IP = devIp;
                        /* 1549 */
                        mIsBroadCastDevice = false;
                        /* 1550 */
                    } catch (JSONException e) {
                        /* 1551 */
                        e.printStackTrace();
                        /* 1552 */
                        Log.e("错误", "1558" + e.getMessage());
                        /* 1553 */
                        return false;
                    }

                } else {

                    /* 1558 */
                    this.strVideoPath = "rtsp://" + Const.CURRENT_DEVICE_IP + "/media/stream2";
                }
            } else {
                /* 1561 */
                this.strVideoPath = this.strVideoPath.replace(Const.CURRENT_DEVICE_IP, "192.168.99.1");
                /* 1562 */
                Const.CURRENT_DEVICE_IP = "192.168.99.1";
            }

            if (Const.CURRENT_DEVICE_IP != "192.168.99.1" || gL_wifiManager_ssid.contains(currentPrefix)) {
                /* 1583 */
                if (service != null) {
                    /* 1584 */
                    if (!((MessageService) service).isSocketAlive()) {
                        /* 1585 */
                        Log.d("Allen", "connect function message socket connect");
                        /* 1586 */
                        ((MessageService) service).reconnect();
                    }
                } else {
                    /* 1589 */
                    Log.d("Allen", "service isnull");
                }
                /* 1591 */
                if (!this.isScreenOff) {
                    /* 1592 */
                    getIQVersion(1);
                }

                /* 1595 */
                return true;
            }
        }
        /* 1598 */
        if (this.mIsWifiAutoConnect == true) {
            /* 1600 */
            Log.d("Allen", "connect 2222");
        }


        /* 1604 */
        int CHECK_TIME = 3000;
        /* 1605 */
        return false;
    }


    private void startRTSP() {
        /* 1610 */
        Thread t = new Thread(new Runnable() {

            public void run() {
                /* 1615 */
                RTSPClient.showDebugMsg("StreamVideo");
                /* 1616 */
                if (RTSPClient.service != null && ((MessageService) RTSPClient.service).isSocketAlive()) {
                    boolean bool = ((MessageService) RTSPClient.service).send(RTSPClient.this.cmd.StreamVideo("", 1, -1, RTSPClient.this.gl_mask)
/* 1618 */.toString());
                }

                RTSPClient.CHECK_TIME = 3000;
            }
        });
        t.start();
    }

    @Override
    protected void onStop() {
        try {
            /* 1629 */
            Log.d("Allen", "Lifecycle onstop");
            /* 1630 */
            if (this.mCheckDataThread != null) {
                /* 1631 */
                WaitForThreadStop(this.mCheckDataThread);
                /* 1632 */
                this.mCheckDataThread = null;
            }
            /* 1634 */
            if (!this.isScreenOff) {
                /* 1635 */
                onRTSPStop();
            }
            /* 1637 */
        } catch (Exception e) {
            /* 1638 */
            e.printStackTrace();
            /* 1639 */
            Log.e("错误", "1649" + e.getMessage());
        }
        /* 1641 */
        super.onStop();
    }

    protected boolean isRTSPStop = false;

    protected void onRTSPStop() {
        /* 1647 */
        if (this.isScreenOff) {
            return;
        }
        /* 1650 */
        this.isRTSPStart = false;
        /* 1651 */
        this.isRTSPStop = true;
        /* 1652 */
        isPause = true;
        /* 1653 */
        isCanSwitchDual = false;
        /* 1654 */
        this.isStartRecvDataThread = false;
        /* 1655 */
        gl_isStartRecordState = 0;
        /* 1656 */
        this.gl_isFirstStartActivity = false;
        /* 1657 */
        gl_isStopGetVideo = true;
        /* 1658 */
        gl_isfirstSetResolution = true;
        /* 1659 */
        gl_isFirstStartStream = true;
        /* 1660 */
        boolean ret = true;
        /* 1661 */
        this.mIsCheckRun = false;
        /* 1662 */
        stopJniLive(0);
        /* 1663 */
        this.isGetVideoPath = false;
        /* 1664 */
        if (this.getFPSTimer != null) {
            /* 1665 */
            this.getFPSTimer.cancel();
            /* 1666 */
            this.getFPSTimer = null;
        }
        /* 1668 */
        this.mAudioHanHandler = null;
        /* 1669 */
        releaseAudioTrack();


        try {
            /* 1673 */
            Thread.sleep(200L);
            /* 1674 */
        } catch (InterruptedException e) {
            /* 1675 */
            e.printStackTrace();
            /* 1676 */
            Log.e("错误", "1690" + e.getMessage());
        }


















        /* 1696 */
        System.gc();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /* 1702 */
        super.onActivityResult(requestCode, resultCode, data);
        /* 1703 */
        Log.d("Allen", "Lifecycle requestCode =" + requestCode);
        /* 1704 */
        Log.d("Allen", "Lifecycle resultCode =" + resultCode);
        /* 1705 */
        if (requestCode == 203 && resultCode == -1) {
            /* 1706 */
            Log.d("Allen", "Lifecycle onActivityResult");
            /* 1707 */
            this.isScreenOff = false;
        }
        /* 1709 */
//        WifiManager mwifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        /* 1710 */
//        if (!mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "").contains("SMARP_") || !this.wifiManager.isWifiEnabled()) {
        if (this.url2 == null) {
            return;
        }
        if (this.url3 == null) {
            return;
        }
        if (this.url4 == null) {
            return;
        }
        if (this.url5 == null) {
            return;
        }
        if (this.url6 == null) {
            return;
        }
        ImageView viewById = (ImageView) findViewById(R.id.back);
        Glide.with(this).load(this.url2).into(this.back1);
        Glide.with(this).load(this.url3).into(this.back2);
        Glide.with(this).load(this.url4).into(this.back3);
        Glide.with(this).load(this.url5).into(this.back4);
        Glide.with(this).load(this.url6).into(this.back5);
        Glide.with(this).load(this.url1).into(viewById);
        viewById.setVisibility(View.VISIBLE);
        this.back1.setVisibility(View.VISIBLE);
        this.back2.setVisibility(View.VISIBLE);
        this.back3.setVisibility(View.VISIBLE);
        this.back4.setVisibility(View.VISIBLE);
        this.back5.setVisibility(View.VISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("数据已连接，是否开始上传？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                post();
            }
            /* 1745 */
        }).show();
//        }
    }


    public void onPause() {
        /* 1751 */
        super.onPause();
        /* 1752 */
        Log.d("Allen", "Lifecycle onpause");
    }

    private static void showDebugMsg(final String msg) {
        /* 1756 */
        if (mIsDeBugMode) {
            /* 1757 */
            uiHandler.post(new Runnable() {

                public void run() {
                    /* 1762 */
                    MessageToast.toast(RTSPClient.gl_activity, 800, msg);
                }
            });
        }
    }


    public void stopJniLive(int i) {
        /* 1786 */
        if (previewFunctionSelect() == 2 && gl_isStartRtsp == true) {

            gl_isStartRtsp = false;
            stopRecv();
            tearDown();
        }
    }

    @Override
    protected void onDestroy() {
        /* 1800 */
        releaseWakeLock();
        /* 1801 */
        unregisterReceiver(this.receiverUnlock);
        unregisterReceiver(receiver);
        /* 1802 */
        super.onDestroy();

        DebugLog.i("RTSP onDestroy");
        if (service != null) {
            ((MessageService) service).close();
        }

        if (!this.wifiManager.isWifiEnabled() ||
                /* 1808 */       isRestartActivity ||
                /* 1809 */       Const.IS_PREVIEW_DISCONNECT_WIFI) ;





        /* 1815 */
        doUnbind();
        /* 1816 */
        if (this.t_connect != null) {
            /* 1817 */
            WaitForThreadStop(this.t_connect);
            /* 1818 */
            this.t_connect = null;
        }

        /* 1821 */
        if (this.serviceIntent != null) {
            /* 1822 */
            stopService(this.serviceIntent);
        }

        /* 1825 */
        surface1 = null;
        /* 1826 */
        surface2 = null;
        /* 1827 */
        surface3 = null;
        /* 1828 */
        surface4 = null;
        /* 1829 */
        surface5 = null;
        /* 1830 */
        surface6 = null;

        /* 1832 */
        sf_preview.setOnClickListener(null);
        /* 1833 */
        sf_preview2.setOnClickListener(null);
        /* 1834 */
        sf_preview4.setOnClickListener(null);
        /* 1835 */
        sf_preview5.setOnClickListener(null);
        /* 1836 */
        sf_preview6.setOnClickListener(null);
        /* 1837 */
        surfaceView.setOnClickListener(null);

        /* 1839 */
        sf_preview = null;
        /* 1840 */
        sf_preview2 = null;
        /* 1841 */
        sf_preview4 = null;
        /* 1842 */
        surfaceView = null;
        /* 1843 */
        sf_preview5 = null;
        /* 1844 */
        sf_preview6 = null;
        /* 1845 */
        this.handler.removeMessages(0);
        /* 1846 */
        System.gc();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /* 1850 */
        if (keyCode == 4 && event.getAction() == 0) {
            if ((getResources().getConfiguration()).orientation == 2) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return true;
            }
            if (System.currentTimeMillis() - this.exitTime > 2000L) {
                /* 1857 */
                this.exitTime = System.currentTimeMillis();
                /* 1858 */
                Toast.makeText(this, getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show();
            } else {
                /* 1860 */
                finish();
            }

            /* 1863 */
            return true;
        }
        /* 1865 */
        return false;
    }

    private void barDisplay() {
        /* 1869 */
        if ((getResources().getConfiguration()).orientation == 1) {

            /* 1871 */
            if (!isWifiConnectedToSMARP() && !gl_isStartRtsp) {


                /* 1874 */
                this.alertBuilder = new AlertDialog.Builder(this);
                /* 1875 */
                this.alertBuilder.setTitle(getString(R.string.device_not_connect));
                /* 1876 */
                this.alertBuilder.setMessage(getString(R.string.device_not_connect_message));
                /* 1877 */
                this.alertBuilder.setPositiveButton(getString(R.string.connect_now), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        /* 1880 */
                        Intent intent = new Intent();
                        /* 1881 */
                        intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                        /* 1882 */
                        RTSPClient.this.startActivityForResult(intent, 10);
                    }
                    /* 1884 */
                }).setNegativeButton(getString(R.string.cancel), null);
                /* 1885 */
                this.alertDialog = this.alertBuilder.show();
            }
            return;
        }
        /* 1889 */
        this.isShowClick = !this.isShowClick;
    }


    public void onConfigurationChanged(Configuration newConfig) {
        /* 1895 */
        super.onConfigurationChanged(newConfig);
        /* 1896 */
        this.isShowClick = false;
        /* 1897 */
        setOrientation(newConfig.orientation);
    }

    public boolean isConnected() {
        /* 1901 */
        if (service == null)
            /* 1902 */ return false;
        /* 1903 */
        return ((MessageService) service).isSocketAlive();
    }

    private int previewFunctionSelect() {
        /* 1907 */
        if (this.gl_fps <= 120) {
            /* 1908 */
            return 2;
        }
        /* 1910 */
        return 1;
    }


    private void init(int ori_config) {
        /* 1919 */
        initVolSeekbar();
        /* 1920 */
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        /* 1921 */
        String device_ssid = prefs.getString("DEVICE_SSID", "");

        /* 1923 */
        this.Llayout_preview = (LinearLayout) findViewById(R.id.Llayout_preview);
        /* 1924 */
        DisplayMetrics metrics = new DisplayMetrics();
        /* 1925 */
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        /* 1926 */
        initsurface();
        /* 1927 */
        this.mIsConnection = false;
        /* 1928 */
        setShowDrawView(1);
    }

    public int carenum = 0;

    private void initsurface() {
        /* 1932 */
        this.jiancejieguoi = (ImageView) findViewById(R.id.jiacejieguo);
        /* 1933 */
        this.jiancejieguot = (TextView) findViewById(R.id.jiacejieguot);
        /* 1934 */
        this.jiancejieguoi.setImageResource(R.drawable.btn_result_enabled);
        /* 1935 */
        final ScrollView viewById = (ScrollView) findViewById(R.id.scoll);
        /* 1936 */
        this.zhishi = (ImageView) findViewById(R.id.zhishi);
        /* 1937 */
        this.tupian = (ImageView) findViewById(R.id.tupina);

        /* 1939 */
        this.back1 = (ImageView) findViewById(R.id.back1);
        /* 1940 */
        this.back2 = (ImageView) findViewById(R.id.back2);
        /* 1941 */
        this.back3 = (ImageView) findViewById(R.id.back3);
        /* 1942 */
        this.back4 = (ImageView) findViewById(R.id.back4);
        /* 1943 */
        this.back5 = (ImageView) findViewById(R.id.back5);

        /* 1945 */
        sf_preview = (TextureView) findViewById(R.id.sf_preview);
        /* 1946 */
        sf_preview.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                surface1 = new Surface(surfaceTexture);
                changlight(1);
                carenum = 1;
                DrawInit(RTSPClient.surface1);
            }


            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
            }


            /* 1960 */
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }


            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        });
        /* 1969 */
        sf_preview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (TextUtils.isEmpty(RTSPClient.this.url1)) {
                    carenum = 2;
                    changlight(1);
                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);
                    progressDialog.setMessage("保存中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Bitmap bitmap = RTSPClient.sf_preview.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
//                    Toasty.normal(getBaseContext(), url1).show();
                    RTSPClient.this.s = UUID.randomUUID().toString();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_1");
                    progressDialog.dismiss();
                    RTSPClient.this.url1 = file.getAbsolutePath();
                    if (RTSPClient.this.isWifiConnectedToSMARP() || gl_isStartRtsp) {
                        RTSPClient.this.back1.setVisibility(View.GONE);
                        RTSPClient.this.tupian.setImageResource(R.drawable.lianjia);
                        DrawDestroy();
                        DrawInit(RTSPClient.surface2);
                    }
                } else if (RTSPClient.this.isrechoice) {
                    if (RTSPClient.this.currentnum != 1) {
                        return;
                    }
                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);
                    progressDialog.setMessage("保存中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Bitmap bitmap = RTSPClient.sf_preview.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    RTSPClient.this.s = UUID.randomUUID().toString();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_1");
                    RTSPClient.this.url1 = file.getAbsolutePath();
                    progressDialog.dismiss();
                    Draw2Destroy();
//                    RTSPClient.DrawDestroy();
                    RTSPClient.this.currentnum = -1;
                    RTSPClient.this.isrechoice = false;
                } else {

                    RTSPClient.this.currentnum = 1;
                    carenum = 1;
                    RTSPClient.this.isrechoice = true;
                    DrawDestroy();
                    Draw2Destroy();
                    Draw2Init(surface1);
                    RTSPClient.this.tupian.setImageResource(R.drawable.etou);
//                    DrawInit(RTSPClient.surface1);
                    changlight(1);
                }
            }
        });
        sf_preview2 = (TextureView) findViewById(R.id.sf_preview2);
        sf_preview2.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                RTSPClient.surface2 = new Surface(surfaceTexture);
            }


            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
            }


            /* 2031 */
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }


            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        });
        sf_preview2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                if (TextUtils.isEmpty(RTSPClient.this.url1)) {
                    Toasty.warning(RTSPClient.this.getBaseContext(), "请先选择上一个").show();
                    return;
                }
                if (TextUtils.isEmpty(RTSPClient.this.url2)) {
                    carenum = 3;

                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);
                    progressDialog.setMessage("保存中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Bitmap bitmap = RTSPClient.sf_preview2.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_2");
                    progressDialog.dismiss();
                    RTSPClient.this.url2 = file.getAbsolutePath();
//                    Toasty.normal(getBaseContext(), url2).show();
                    if (RTSPClient.this.isWifiConnectedToSMARP() || gl_isStartRtsp) {

                        RTSPClient.this.back2.setVisibility(View.GONE);

                        RTSPClient.this.tupian.setImageResource(R.drawable.yanwei);
                        DrawDestroy();
                        DrawInit(RTSPClient.surface3);
                    }
                    changlight(1);

                } else if (RTSPClient.this.isrechoice) {
                    if (RTSPClient.this.currentnum != 2) {
                        return;
                    }

                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);

                    progressDialog.setMessage("保存中");

                    progressDialog.setCancelable(false);

                    progressDialog.show();

                    Bitmap bitmap = RTSPClient.sf_preview2.getBitmap(640, 480);

                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();

                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_2");

                    progressDialog.dismiss();

                    RTSPClient.this.url2 = file.getAbsolutePath();
                    Draw2Destroy();
//                    RTSPClient.DrawDestroy();

                    RTSPClient.this.currentnum = -1;

                    RTSPClient.this.isrechoice = false;
                } else {
                    RTSPClient.this.tupian.setImageResource(R.drawable.lianjia);
                    changlight(1);
                    DrawDestroy();
                    RTSPClient.this.currentnum = 2;
                    carenum = 2;
                    RTSPClient.this.isrechoice = true;
                    Draw2Destroy();
                    Draw2Init(surface2);
//                    DrawInit(RTSPClient.surface2);
                }
            }
        });

        surfaceView = (TextureView) findViewById(R.id.sf_preview3);

        surfaceView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                RTSPClient.surface3 = new Surface(surfaceTexture);
            }


            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }


            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        });

        surfaceView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                if (TextUtils.isEmpty(RTSPClient.this.url2)) {

                    Toasty.warning(RTSPClient.this.getBaseContext(), "请先选择上一个");
                    return;
                }

                if (TextUtils.isEmpty(RTSPClient.this.url3)) {
                    carenum = 4;

                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);
                    progressDialog.setMessage("保存中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Bitmap bitmap = RTSPClient.surfaceView.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_3");
                    progressDialog.dismiss();
                    RTSPClient.this.url3 = file.getAbsolutePath();
//                    Toasty.normal(getBaseContext(), url3).show();
                    if (RTSPClient.this.isWifiConnectedToSMARP() || gl_isStartRtsp) {
                        RTSPClient.this.back3.setVisibility(View.GONE);
                        RTSPClient.this.tupian.setImageResource(R.drawable.sesu);
                        DrawDestroy();
                        DrawInit(RTSPClient.surface4);
                    }
                    changlight(2);
                } else if (RTSPClient.this.isrechoice) {
                    if (currentnum != 3) {
                        return;
                    }
                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);
                    progressDialog.setMessage("保存中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Bitmap bitmap = RTSPClient.surfaceView.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_3");
                    progressDialog.dismiss();
                    RTSPClient.this.url3 = file.getAbsolutePath();
//                    DrawDestroy();
                    Draw2Destroy();
                    RTSPClient.this.isrechoice = false;
                    RTSPClient.this.currentnum = -1;
                } else {
                    RTSPClient.this.tupian.setImageResource(R.drawable.yanwei);

                    carenum = 3;
                    RTSPClient.this.isrechoice = true;
                    RTSPClient.this.currentnum = 3;
//                    DrawInit(RTSPClient.surface3);
                    DrawDestroy();
                    Draw2Destroy();
                    Draw2Init(surface3);
                    changlight(1);
                }
            }
        });
        sf_preview4 = (TextureView) findViewById(R.id.sf_preview4);
        sf_preview4.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                RTSPClient.surface4 = new Surface(surfaceTexture);
            }


            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }


            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        });
        sf_preview4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                if (TextUtils.isEmpty(RTSPClient.this.url3)) {
                    Toasty.warning(RTSPClient.this.getBaseContext(), "请先选择上一个").show();
                    return;
                }
                if (TextUtils.isEmpty(RTSPClient.this.url4)) {
                    carenum = 5;

                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);
                    progressDialog.setMessage("保存中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Bitmap bitmap = RTSPClient.sf_preview4.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_4");
                    progressDialog.dismiss();
                    RTSPClient.this.url4 = file.getAbsolutePath();
//                    Toasty.normal(getBaseContext(), url4).show();
                    if (RTSPClient.this.isWifiConnectedToSMARP() || gl_isStartRtsp) {
                        RTSPClient.this.back4.setVisibility(View.GONE);
                        RTSPClient.this.tupian.setImageResource(R.drawable.mingan);
                        viewById.scrollTo(viewById.getBottom(), viewById.getBottom());
                        RTSPClient.this.zhishi.setImageResource(R.drawable.up);
                        Draw2Destroy();
//                        DrawDestroy();
                        DrawInit(RTSPClient.surface5);

                    }
                    changlight(2);
                } else if (RTSPClient.this.isrechoice) {
                    if (RTSPClient.this.currentnum != 4) {
                        return;
                    }
                    RTSPClient.this.isrechoice = false;
                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);
                    progressDialog.setMessage("保存中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    Bitmap bitmap = RTSPClient.sf_preview4.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_4");
                    progressDialog.dismiss();
                    RTSPClient.this.url4 = file.getAbsolutePath();
//                    DrawDestroy();
                    Draw2Destroy();
                    RTSPClient.this.currentnum = -1;
                } else {
                    RTSPClient.this.tupian.setImageResource(R.drawable.sesu);
                    RTSPClient.this.isrechoice = true;
                    carenum = 4;
                    RTSPClient.this.currentnum = 4;
                    Draw2Destroy();
                    DrawDestroy();
                    Draw2Init(RTSPClient.surface4);
                    changlight(2);
                }
            }
        });
        sf_preview5 = (TextureView) findViewById(R.id.sf_preview5);
        sf_preview5.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                RTSPClient.surface5 = new Surface(surfaceTexture);
            }


            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }


            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        });
        sf_preview5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(RTSPClient.this.url4)) {
                    Toasty.warning(RTSPClient.this.getBaseContext(), "请先选择上一个").show();
                    return;
                }
                if (TextUtils.isEmpty(RTSPClient.this.url5)) {

                    carenum = 6;
                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);
                    progressDialog.setMessage("保存中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Bitmap bitmap = RTSPClient.sf_preview5.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_5");
                    progressDialog.dismiss();
                    RTSPClient.this.url5 = file.getAbsolutePath();
//                    Toasty.normal(getBaseContext(), url5).show();
                    if (RTSPClient.this.isWifiConnectedToSMARP() || gl_isStartRtsp) {
                        RTSPClient.this.back5.setVisibility(View.GONE);
                        RTSPClient.this.tupian.setImageResource(R.drawable.biyi);
                        DrawDestroy();
                        RTSPClient.DrawInit(RTSPClient.surface6);
                    }
                    changlight(3);
                } else if (RTSPClient.this.isrechoice) {
                    RTSPClient.this.isrechoice = false;
                    if (RTSPClient.this.currentnum != 5) {
                        return;
                    }
                    ProgressDialog progressDialog = new ProgressDialog(RTSPClient.this);
                    progressDialog.setMessage("保存中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Bitmap bitmap = RTSPClient.sf_preview5.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_5");
                    progressDialog.dismiss();
                    RTSPClient.this.url5 = file.getAbsolutePath();
//                    RTSPClient.DrawDestroy();
                    Draw2Destroy();
                    RTSPClient.this.currentnum = -1;
                } else {
                    RTSPClient.this.tupian.setImageResource(R.drawable.mingan);

                    RTSPClient.this.isrechoice = true;
                    RTSPClient.this.currentnum = 5;
                    carenum = 5;
                    Draw2Destroy();
                    DrawDestroy();
                    Draw2Init(RTSPClient.surface5);
                    changlight(2);
                }
            }
        });

        sf_preview6 = (TextureView) findViewById(R.id.sf_preview6);
        sf_preview6.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                RTSPClient.surface6 = new Surface(surfaceTexture);
            }


            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }


            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }
        });

        sf_preview6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(RTSPClient.this.url5)) {
                    Toasty.warning(RTSPClient.this.getBaseContext(), "请先选择上一个").show();
                    return;
                }

                if (TextUtils.isEmpty(RTSPClient.this.url6)) {
                    carenum = 7;
                    Bitmap bitmap = RTSPClient.sf_preview6.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_6");
                    RTSPClient.this.url6 = file.getAbsolutePath();

                    RTSPClient.DrawDestroy();
                    RTSPClient.this.testresult.setEnabled(true);
                    RTSPClient.this.jiancejieguoi.setImageResource(R.drawable.btn_project_normal);
                } else if (RTSPClient.this.isrechoice) {
                    if (RTSPClient.this.currentnum != 6) {
                        return;
                    }
                    RTSPClient.this.isrechoice = false;
                    RTSPClient.this.currentnum = -1;
                    Bitmap bitmap = RTSPClient.sf_preview6.getBitmap(640, 480);
                    SaveBitamptoBmp bitamptoBmp = new SaveBitamptoBmp();
                    File file = bitamptoBmp.Compress(RTSPClient.this.zoomImg(bitmap), RTSPClient.this.s + "_6");
                    RTSPClient.this.url6 = file.getAbsolutePath();
                    RTSPClient.Draw2Destroy();
                } else {
                    RTSPClient.this.tupian.setImageResource(R.drawable.biyi);
                    RTSPClient.this.isrechoice = true;
                    RTSPClient.this.currentnum = 6;
                    carenum = 6;
                    DrawDestroy();
                    Draw2Destroy();
                    Draw2Init(RTSPClient.surface6);
                    changlight(3);
                }
            }
        });
    }


    public static void MediaCodecRun(Surface surface1, Surface surface2) {
        vgaDecoder = new Decoder(surface1, 1);
        vgaDecoder2 = new Decoder(surface2, 1);
        vgaDecoder.initialHD();

        vgaDecoder2.initialHD();
    }

    private void takePic() {
        /* 2371 */
        if (!isConnected()) {

            TipsUtils.toast(this.context, getString(R.string.connect_not_ready));
            return;
        }

        if (gl_bool_isNoSdCard) {

            TipsUtils.toast(this.context, getString(R.string.please_input_sdcard));
            return;
        }

        if (service != null && ((MessageService) service).isSocketAlive()) {

            Thread t = new Thread() {
                public void run() {
                    /* 2383 */
                    int coutinus_count = prefs.getInt("SHARED_PREFERENCE_CONTINUS_PIC_LENGTH", 1);
                    /* 2384 */
                    boolean ret = ((MessageService) RTSPClient.service).send(RTSPClient.this.cmd.TakePicture(coutinus_count).toString());
                }
            };
            /* 2387 */
            t.start();
        }
    }

    private void recordStatusChange() {
        final int status;
        /* 2393 */
        if (!isConnected()) {
            /* 2394 */
            MessageToast.toast(this, 300, getString(R.string.connect_not_ready));
            return;
        }
        /* 2397 */
        if (gl_bool_isNoSdCard) {
            /* 2398 */
            MessageToast.toast(this, 300, getString(R.string.please_input_sdcard));

            return;
        }
        /* 2402 */
        if (isRecordOn) {
            /* 2403 */
            status = 0;
        } else {
            /* 2405 */
            status = 1;
        }
        /* 2407 */
        Thread t = new Thread(new Runnable() {
            public void run() {
                /* 2410 */
                boolean ret = ((MessageService) RTSPClient.service).send(RTSPClient.this.cmd.SetRecordStatus(status).toString());
            }
        });

        /* 2414 */
        t.start();
    }

    private String intToDate(int second) {
        /* 2418 */
        String result = "00:00:00";
        /* 2419 */
        String hours = "00";
        /* 2420 */
        String minute = "00";


        if (second > 3600) {
            hours = String.format("%02d", new Object[]{Integer.valueOf(second / 3600)});
            second %= 3600;
        }
        if (second > 60) {
            minute = String.format("%02d", new Object[]{Integer.valueOf(second / 60)});
            second %= 60;
        }
        return hours + ":" + minute + ":" + String.format("%02d", new Object[]{Integer.valueOf(second)});
    }


    private static int getPerformanceState() {
        return prefs.getInt("SHARED_PREFERENCE_SPECIAL_PREVIEW", 0);
    }


    public void CreateMessageHandler() {
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                int i;
                switch (msg.what) {
                    case 1:
                        i = msg.getData().getInt("Result");
                        break;
                }
            }
        }
        ;
    }


    AEC.AECCallback mAecCallback = new AEC.AECCallback() {
        public void callback(int value) {
        }

        public void onDateReceive(short[] data) {
            int res = AACEncoder.encode(RTSPClient.this.Shorts2Bytes(data), RTSPClient.this.mOutputAac);
            if (res > 0) {
                byte[] tmpdata = new byte[res];
                System.arraycopy(RTSPClient.this.mOutputAac, 0, tmpdata, 0, res);
                mSocket.sendData(tmpdata);
            }
        }
    };

    BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("BROADCAST_STREAM_VIDEO")) {
                LoadingDialog.dismiss();
                RTSPClient.this.filename = intent.getStringExtra("filename");
                if (RTSPClient.getPerformanceState() == 1 && gl_resolution == 1) {
                    RTSPClient.gl_FFmpeg = 0;
                } else {
                    RTSPClient.gl_FFmpeg = 1;
                }

                if (RTSPClient.this.openWithPath(RTSPClient.this.strVideoPath) == 0) {
                    /* 2493 */
                    if (!gl_isStartRtsp) {

                        if (RTSPClient.this.setup() == 0) {

                            /* 2497 */
                            gl_isStartRtsp = true;

                            RTSPClient.setGetFrameTag(0);

                            RTSPClient.this.setNetworkOption(3, 6);

                            RTSPClient.setFFmpegDraw(RTSPClient.gl_FFmpeg);

                            if (RTSPClient.gl_FFmpeg == 0) {

                                RTSPClient.MediaCodecRun(RTSPClient.surface2, RTSPClient.surface2);
                            }

                            RTSPClient.gl_sd_status = -1;

                            RTSPClient.gl_rec_running = -1;

                            RTSPClient.gl_sd_capacity = -1;

                            RTSPClient.gl_sd_fromat = -1;


                            if (gl_resolution == 0) {
                                /* 2514 */
                                RTSPClient.this.startReceive(640, 0);
                            } else {
                                /* 2516 */
                                RTSPClient.this.startReceive(1280, 0);
                            }
                            /* 2518 */
                            mHandler.post(new Runnable() {
                                public void run() {
                                    /* 2521 */
                                    findViewById(R.id.back).setVisibility(View.GONE);
                                    /* 2522 */
                                    changlight(1);
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    }
                }
                /* 2528 */
                RTSPClient.this.getRecordStatus();

                return;
            }
            /* 2532 */
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                /* 2533 */
                ConnectivityManager connManager = (ConnectivityManager) RTSPClient.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                /* 2534 */
                NetworkInfo mWifi = connManager.getNetworkInfo(1);
                /* 2535 */
                if (!mWifi.isConnected()) {
                    /* 2536 */
                    Log.d("Allen", "wifi and socket close stop rtsp");
                }

                if (RTSPClient.isPause)
                    return;
                /* 2548 */
                WifiInfo mWifiInfo = RTSPClient.this.wifiManager.getConnectionInfo();
                /* 2549 */
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(RTSPClient.this);
                /* 2550 */
                String device_ssid = prefs.getString("DEVICE_SSID", "");
                /* 2551 */
                if (RTSPClient.this.isConnectDevice) {
                    /* 2552 */
                    if (mWifiInfo.getSSID() != null && mWifiInfo.getSSID().equals(device_ssid)) {
                        Log.d("Sonix", "WiFi Connected: " + device_ssid);
                        RTSPClient.this.isConnectDevice = false;
                        if (RTSPClient.service != null) {
                            ((MessageService) RTSPClient.service).reconnect();
                        }
                        RTSPClient.this.gl_isFirstStartActivity = true;
                    } else if (mWifiInfo.getSSID() != null && mWifiInfo.getSSID().equals("\"" + device_ssid + "\"")) {
                        Log.d("Sonix", "WiFi Connected: " + device_ssid);
                        if (RTSPClient.service != null) {
                            ((MessageService) RTSPClient.service).reconnect();
                        }
                        RTSPClient.this.gl_isFirstStartActivity = true;
                    }
                }
            } else if (intent.getAction().equals("BROADCAST_GET_RECORD_STATUS")) {
                int resp_status = intent.getExtras().getInt("resp_status");
                if (!MessageToast.isCommandSuccess(resp_status)) {
                    MessageToast.show2(RTSPClient.this, resp_status);
                }
                int tmp = intent.getExtras().getInt("recstatus");
                int recrunning = intent.getExtras().getInt("recrunning", 0);
                RTSPClient.this.gl_volume = intent.getIntExtra("volume", 0);
                RTSPClient.this.gl_bitrate = intent.getLongExtra("bitrate", 0L);
                RTSPClient.this.gl_volume = intent.getIntExtra("volume", 0);
                RTSPClient.this.gl_fps = intent.getIntExtra("fps", 0);
                gl_resolution = intent.getIntExtra("resolution", 0);
                RTSPClient.this.showCurrentRecordStatus(gl_resolution, RTSPClient.this.gl_fps, (int) RTSPClient.this.gl_bitrate);
                if (recrunning == 1) {
                    isRecordOn = true;
                } else {
                    isRecordOn = false;
                }
            } else if (action.equals("BROADCAST_TAKE_PICTURE")) {
                int status = intent.getIntExtra("status", 1);
                MessageToast.show2(RTSPClient.this, status);
            } else if (action.equals("broadcast_socket_connect")) {
                DebugLog.i("test BROADCAST_SOCKET_CONNECT");
                RTSPClient.this.getConnectStatus();
            } else if (action.equals("BROADCAST_SET_RECORD_STATUS")) {
                int tmp = intent.getExtras().getInt("status");
                if (MessageToast.isCommandSuccess(tmp)) {
                    isRecordOn = !isRecordOn;
                    if (isRecordOn) ;
                } else {
                    MessageToast.show2(RTSPClient.this, tmp, 500);
                }
            } else if (!action.equals("BROADCAST_GET_VIDEO_STATUS")) {
                if (action.equals("BROADCAST_SET_RECORD_VOLUMN")) {
                    int status = intent.getIntExtra("status", 1);
                    if (MessageToast.isCommandSuccess(status)) {
                        TipsUtils.toast(context, RTSPClient.this.getString(R.string.current_volumn) + RTSPClient.this.gl_volume);
                    } else {
                        MessageToast.show2(context, status);
                    }
                } else if (action.equals("BROADCAST_SET_RECORD_PARAMETER")) {
                    int status = intent.getIntExtra("status", 1);
                    MessageToast.show2(RTSPClient.this, status);
                    if (MessageToast.isCommandSuccess(status)) {
                        RTSPClient.this.showCurrentRecordStatus(gl_resolution, RTSPClient.this.gl_fps, (int) RTSPClient.this.gl_bitrate);
                    }
                } else if (action.equals("BROADCAST_FORMATE_SD_CARD")) {
                    String msg = intent.getStringExtra("msg");
                    RTSPClient.this.showFormatDialog(msg);
                } else if (action.equals("BROADCAST_SET_SD_FORMAT")) {
                    int status = intent.getIntExtra("status", 1);
                    MessageToast.show2(RTSPClient.this, status);
                } else if (action.equals("BROADCAST_STOP_RECEIVE")) {
                    RTSPClient.this.stopJniLive(4);
                } else if (!action.equals("BROADCAST_SYNC_TIME")) {
                    if (action.equals("BROADCAST_GET_IQ_VERSION")) {
                        try {
                            RTSPClient.this.mListObject.put(RTSPClient.this.getCurrentSsid(), Const.Current_version);
                            prefs.edit().putString("DEVICE_VERSION_LIST", RTSPClient.this.mListObject.toString()).commit();
                        } catch (JSONException e) {
                            Log.e("错误", "2659" + e.getMessage());
                            e.printStackTrace();
                        }
                        if (Const.Current_version == 1) {
                            RTSPClient.this.openRTSP(4);
                        } else if (Const.Current_version == 0) {
                            RTSPClient.this.startRTSP();
                        }
                    } else if (action.equals("BROADCAST_PUSHTALK_UI")) {
                        RTSPClient.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Log.d("Allen", "BROADCAST_PUSHTALK_UI 1111");
                                if (RTSPClient.hasAudio()) {
                                    Log.d("Allen", "has audio aaaa");
                                    Log.d("Allen", "BROADCAST_PUSHTALK_UI 2222");
                                }
                            }
                        });
                    } else if (action.equals(Const.SNAPSHOT)) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toasty.error(getBaseContext(), "点了硬件拍照").show();
//                            }
//                        });
                        switch (carenum) {
                            case 1:
                                sf_preview.performClick();
                                break;
                            case 2:
                                sf_preview2.performClick();
                                break;
                            case 3:
                                surfaceView.performClick();
                                break;
                            case 4:
                                sf_preview4.performClick();
                                break;
                            case 5:
                                sf_preview5.performClick();
                                break;
                            case 6:
                                sf_preview6.performClick();
                                break;
                        }
                    }
                }
            }
        }
    };


    private void getRecordStatus() {
        /* 2833 */
        Thread t = new Thread() {
            public void run() {
                /* 2835 */
                boolean ret = ((MessageService) RTSPClient.service).send(RTSPClient.this.cmd.GetRecordStatus().toString());
                /* 2836 */
                if (!ret) {
                    /* 2837 */
                    RTSPClient.this.runOnUiThread(new Runnable() {
                        public void run() {
                            /* 2840 */
                            Toast.makeText(RTSPClient.this, getString(R.string.send_command_fail), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };
        /* 2846 */
        t.start();
    }


    private void doBind() {
        /* 2851 */
        Intent intent = new Intent();
        /* 2852 */
        intent.setClass(this, MessageService.class);
        /* 2853 */
        bindService(intent, this.conn, Context.BIND_AUTO_CREATE);
        /* 2854 */
        DebugLog.i("service doBind");
    }

    private void doUnbind() {
        /* 2858 */
        if (this.isBinded) {
            /* 2859 */
            unbindService(this.conn);
            /* 2860 */
            this.isBinded = false;
        }
    }


    private ServiceConnection conn = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder binder) {

            RTSPClient.this.isBinded = true;


            RTSPClient.service = ((MessageService.ServiceBinder) binder).getService();

            DebugLog.i("service=" + RTSPClient.service);

            ((MessageService) RTSPClient.service).setActivity(RTSPClient.this);

            Log.d("ming", "jianming onServiceConnected");

            RTSPClient.this.getConnectStatus();

            if (!RTSPClient.this.isConnected()) {
                return;
            }
        }


        /* 2885 */
        public void onServiceDisconnected(ComponentName name) {
            RTSPClient.this.isBinded = false;
        }
    };

    private AlertDialog showFormatDialog;

    private void showFormatDialog(String title) {
        if (this.showFormatDialog != null && this.showFormatDialog.isShowing()) {
            return;
        }
        this.showFormatDialog = (new AlertDialog.Builder(this)).setTitle(title).setMessage(R.string.format_message).setCancelable(false)
                .setPositiveButton(R.string.connecting, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Thread t = new Thread() {
                            public void run() {
                                boolean ret = ((MessageService) RTSPClient.service).send(RTSPClient.this.cmd.SetSDFormat().toString());
                                if (!ret) RTSPClient.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(RTSPClient.this, getString(R.string.send_command_fail), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        };
                        t.start();
                        dialog.cancel();
                    }
                }).setNegativeButton(getString(R.string.cancel), null).show();
    }

    int count_test = 0;

    private void getConnectStatus() {
        if (this.t_connect != null) {
            return;
        }

        this.t_connect = new Thread() {
            public void run() {
                while (RTSPClient.this.isBinded == true) {


                    boolean ret = ((MessageService) RTSPClient.service).isSocketAlive();

                    if (!ret) {
                        try {

                            sleep(50L);
                            continue;
                        } catch (InterruptedException e) {

                            Log.e("错误", "2817" + e.getMessage());

                            e.printStackTrace();
                            continue;
                        }
                    }

                    if (RTSPClient.this.count_test == 40) {

                        ((MessageService) RTSPClient.service).send(RTSPClient.this.cmd.getHeartBeatByte().toString());

                        RTSPClient.this.count_test = 0;
                    }
                    try {

                        sleep(500L);

                    } catch (InterruptedException e) {
                        /* 2958 */
                        Log.e("错误", "2832" + e.getMessage());
                        /* 2959 */
                        e.printStackTrace();
                    }
                    /* 2961 */
                    RTSPClient.this.count_test++;
                }
            }
        };
        /* 2965 */
        this.t_connect.start();
    }

    private boolean WaitForThreadStop(Thread t) {
        /* 2969 */
        int waitCount = 0;
        /* 2970 */
        if (t == null) {
            /* 2971 */
            return true;
        }
        /* 2973 */
        t.interrupt();
        /* 2974 */
        while (waitCount < 2) {
            /* 2975 */
            if (!t.isAlive()) {
                /* 2976 */
                return true;
            }
            try {
                /* 2979 */
                Thread.sleep(20L);
                /* 2980 */
            } catch (InterruptedException e) {
                /* 2981 */
                e.printStackTrace();
                /* 2982 */
                Log.e("错误", "2856" + e.getMessage());
            }
            /* 2984 */
            waitCount++;
        }
        /* 2986 */
        if (t.isAlive() == true) {
            try {
                /* 2988 */
                t.stop();
                /* 2989 */
                return true;
                /* 2990 */
            } catch (Exception e) {
                /* 2991 */
                e.printStackTrace();
                /* 2992 */
                Log.e("错误", "2866" + e.getMessage());
            }
        }
        /* 2995 */
        return false;
    }

    /* 2998 */   static FileOutputStream output = null;

    /* 3000 */   static int testCount = 0;


    /* 3003 */   static int gl_count = 0;

    boolean isStartRecvDataThread = false;

    /* 3007 */ long start_t = 0L;
    long stop_t = 0L;
    long diff_t = 0L;
    /* 3008 */   static int count = 0;
    private int showDrawView;

    public void setShowDrawView(int showDrawView) {
        /* 3012 */
        this.showDrawView = showDrawView;
        /* 3013 */
        setDrawView(showDrawView);
        /* 3014 */
        DisplayMetrics dm = new DisplayMetrics();
        /* 3015 */
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        /* 3016 */
        this.mDisplayWidth = dm.widthPixels;
        /* 3017 */
        this.mDisplayHeight = dm.heightPixels;
        /* 3018 */
        if (showDrawView == 2) {

            int h = this.mDisplayWidth * 9 / 32;

            RelativeLayout.LayoutParams para = new RelativeLayout.LayoutParams(this.mDisplayWidth, h);

            para.addRule(13);

        } else {


            /* 3029 */
            int h = this.mDisplayWidth * 9 / 16;
            /* 3030 */
            RelativeLayout.LayoutParams para = new RelativeLayout.LayoutParams(this.mDisplayWidth, h);
            /* 3031 */
            para.addRule(13);
        }
    }


    protected void setOrientation(int orientation) {
        /* 3040 */
        if (orientation == 2) {

            hideTitle();
            fullscreen(true);
            /* 3049 */
        } else if (orientation == 1) {

            /* 3051 */
            showTitle();
            /* 3052 */
            fullscreen(false);
        }

        /* 3060 */
        onOrientationCallBack(orientation);
        /* 3061 */
        setShowDrawView(this.showDrawView);
    }


    /* 3065 */ int gl_filter_count = 2;


    static {
        /* 3121 */
        System.loadLibrary("AECRTSP");
    }

    /* 3124 */ Timer getFPSTimer = null;


    /* 3127 */
    private static boolean isPreviewOn() {
        return gl_isStartRtsp;
    }


    Runnable checkRunnable = new Runnable() {

        public void run() {
            /* 3135 */
            int retryCountWifi = 0;

            /* 3137 */
            int retryCountpreview = 0;
            /* 3138 */
            while (RTSPClient.this.mIsCheckRun) {

                /* 3140 */
                if (RTSPClient.isWiFiConnected()) {
                    /* 3141 */
                    RTSPClient.this.printLog("checkRunnable mStationDeviceIp=" + mStationDeviceIp);
                    /* 3142 */
                    RTSPClient.this.printLog("checkRunnable mIsBroadCastDevice=" + RTSPClient.mIsBroadCastDevice);
                    /* 3143 */
                    RTSPClient.this.printLog("checkRunnable getCurrentSsid()=" + RTSPClient.this.getCurrentSsid());

                    /* 3145 */
                    if (!RTSPClient.this.getCurrentSsid().contains("SMARP_")) {
                        /* 3146 */
                        RTSPClient.this.printLog("checkRunnable 000");
                        /* 3147 */
                        if (RTSPClient.mIsBroadCastDevice == true) {

                            /* 3149 */
                            RTSPClient.showDebugMsg("BroadCast");
                            /* 3150 */
                            List<JSONObject> list = mBcController.startScanSync();
                            /* 3151 */
                            if (list.isEmpty()) {

                                try {
                                    /* 3154 */
                                    Thread.sleep(RTSPClient.CHECK_TIME);
                                    continue;
                                    /* 3155 */
                                } catch (InterruptedException e) {

                                    /* 3157 */
                                    e.printStackTrace();
                                    /* 3158 */
                                    Log.e("错误", "3032" + e.getMessage());
                                    continue;
                                }
                            }
                            /* 3162 */
                            RTSPClient.this.printLog("checkRunnable ccccc");
                            /* 3163 */
                            JSONObject obj = (JSONObject) list.get(0);
                            try {

                                String devIp = obj.getString("ip");
                                /* 3166 */
                                mStationDeviceIp = devIp;
                                /* 3167 */
                                Const.CURRENT_DEVICE_IP = devIp;
                                /* 3168 */
                                RTSPClient.mIsBroadCastDevice = false;

                                RTSPClient.this.strVideoPath = "rtsp://" + Const.CURRENT_DEVICE_IP + "/media/stream2";
                                /* 3170 */
                            } catch (JSONException e) {

                                e.printStackTrace();

                                RTSPClient.this.printLog("checkRunnable aaaaa");
                                /* 3173 */
                                Log.e("错误", "3047" + e.getMessage());


                                continue;
                            }
                        } else {
                            /* 3179 */
                            RTSPClient.this.printLog("checkRunnable 2222");
                            /* 3180 */
                            RTSPClient.this.strVideoPath = "rtsp://" + Const.CURRENT_DEVICE_IP + "/media/stream2";
                        }
                    } else {
                        /* 3183 */
                        RTSPClient.this.printLog("checkRunnable 3333");
                        /* 3184 */
                        RTSPClient.this.strVideoPath = RTSPClient.this.strVideoPath.replace(Const.CURRENT_DEVICE_IP, "192.168.99.1");
                        /* 3185 */
                        Const.CURRENT_DEVICE_IP = "192.168.99.1";
                    }




                    /* 3191 */
                    retryCountWifi = 0;

                    /* 3193 */
                    if (!RTSPClient.isCmdSocketConnect() &&
                            /* 3194 */               RTSPClient.service != null) {

                        /* 3196 */
                        ((MessageService) RTSPClient.service).reconnect();
                        /* 3197 */
                        retryCountpreview = 0;
                    }


                    /* 3201 */
                    if (RTSPClient.this.mIsDoRtspFlow == true) {
                        /* 3202 */
                        RTSPClient.CHECK_TIME = 3000;

                    }
                    /* 3205 */
                    else if (RTSPClient.isPreviewOn()) {
                        /* 3206 */
                        RTSPClient.CHECK_TIME = 3000;
                    } else {

                        /* 3209 */
                        RTSPClient.CHECK_TIME = 3000;


                        /* 3212 */
                        RTSPClient.this.stopJniLive(2);
                        /* 3213 */
                        if (!isTalk) {
                            /* 3214 */
                            RTSPClient.this.runOnUiThread(new Runnable() {
                                public void run() {
                                }
                            });
                        }


                        try {
                            /* 3223 */
                            Thread.sleep(100L);
                            /* 3224 */
                        } catch (InterruptedException e) {

                            /* 3226 */
                            e.printStackTrace();
                            /* 3227 */
                            Log.e("错误", "3101" + e.getMessage());
                        }
                        /* 3229 */
                        RTSPClient.this.getIQVersion(2);

                    }


                } else {


                    /* 3238 */
                    RTSPClient.CHECK_TIME = 500;
                    /* 3239 */
                    retryCountWifi++;
                    /* 3240 */
                    if (retryCountWifi % 6 != 1 ||
                            /* 3241 */               RTSPClient.this.mIsWifiAutoConnect) ;
                }


                try {
                    /* 3247 */
                    Thread.sleep(RTSPClient.CHECK_TIME);
                    /* 3248 */
                } catch (InterruptedException e) {

                    /* 3250 */
                    e.printStackTrace();
                    /* 3251 */
                    Log.e("错误", "3126" + e.getMessage());
                }
            }
        }
    };

    private static boolean isWiFiConnected() {
        /* 3258 */
        WifiManager mwifiManager = (WifiManager) gl_activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        /* 3259 */
        String ssid = mwifiManager.getConnectionInfo().getSSID().toString();

        /* 3261 */
        ConnectivityManager connManager = (ConnectivityManager) gl_activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        /* 3262 */
        NetworkInfo mWifi = connManager.getNetworkInfo(1);
        /* 3263 */
        if (mWifi.isConnected()) {
            /* 3264 */
            mBcController.setHotSpotFlag(false);
            /* 3265 */
            return true;
        }









        /* 3276 */
        Log.d("Allen", "isWiFiConnected 000");
        /* 3277 */
        if (mApController.getWifiApState() == WifiApController.WIFI_AP_STATE.WIFI_AP_STATE_ENABLED) {


            /* 3280 */
            Log.d("Allen", "isWiFiConnected 1111");
            /* 3281 */
            mBcController.setHotSpotFlag(true);
            /* 3282 */
            return true;
        }



        /* 3287 */
        return false;
    }

    private static boolean isCmdSocketConnect() {
        /* 3291 */
        if (service != null && (
                /* 3292 */       (MessageService) service).isSocketAlive()) {
            /* 3293 */
            return true;
        }

        /* 3296 */
        return false;
    }


    private void checkDrawTime() {
        /* 3301 */
        if (this.mCheckDataThread == null) {
            /* 3302 */
            this.mIsCheckRun = true;
            /* 3303 */
            this.mCheckDataThread = new Thread(this.checkRunnable);
            /* 3304 */
            mHandler.postDelayed(new Runnable() {

                public void run() {
                    /* 3309 */
                    if (RTSPClient.this.mCheckDataThread != null) {
                        /* 3310 */
                        RTSPClient.this.gl_isFirstStartActivity = false;

                        /* 3312 */
                        if (!RTSPClient.this.mCheckDataThread.isAlive()) {
                            /* 3313 */
                            RTSPClient.this.mCheckDataThread.start();
                        }
                    }
                }
            }, 5000L);
        }

        TimerTask tFPSTask = new TimerTask() {
            public void run() {
                RTSPClient.this.runOnUiThread(new Runnable() {


                    public void run() {

                        int fps = 0;
                        if (RTSPClient.gl_FFmpeg == 1) {
                            fps = RTSPClient.getFPS();
                            errorCount = fps;
                            frameCount = 0;
                            isCanSwitchDual = true;
                        } else if (vgaDecoder != null) {
                            fps = vgaDecoder.getFPS();
                            errorCount = fps;
                            frameCount = 0;
                            isCanSwitchDual = true;
                        }

                        if (RTSPClient.isPreviewOn()) {
                            if (mApController.getWifiApState() == WifiApController.WIFI_AP_STATE.WIFI_AP_STATE_ENABLED)
                                ;


                            if (fps >= 30) {
                                fps = 30;
                            }
                        }
                    }
                });
            }
        };


        if (this.getFPSTimer == null) {
            this.getFPSTimer = new Timer();
            this.getFPSTimer.schedule(tFPSTask, 1000L, 1000L);
        }
    }


    private void checkIsFwFolderCreate() {
        File f = new File(Const.download_path + "/firmware");
        if (!f.exists()) {
            f.mkdir();
        }
    }

    private void fullscreen(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags = 1024;
            getWindow().setAttributes(lp);
            getWindow().addFlags(512);
        } else {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags &= 0xFFFFFBFF;
            getWindow().setAttributes(lp);
            getWindow().clearFlags(512);
        }
    }


    /* 3403 */   private String[] modeSet = {"FHD, 30fps, High", "FHD, 30fps, MID", "FHD, 30fps, LOW", "HD, 60fps, HIGH", "HD, 60fps, MID", "HD, 60fps, LOW", "HD, 30fps, HIGH", "HD, 30fps, MID", "HD, 30fps, LOW"};


    private boolean gl_mode_first = true;


    private int gl_volume;


    private WifiManager wifiManager;


    private NetworkInfo gl_wifiInfo;


    private void initVolSeekbar() {
    }


    private void showCurrentRecordStatus(int resolution, int fps, int bitrate) {
        /* 3441 */
        int select = 0;
        /* 3442 */
        switch (resolution) {


            case 1:
                /* 3447 */
                if (fps == 60) {
                    /* 3448 */
                    switch (bitrate) {
                        case 10485760:
                            /* 3450 */
                            select = 3;
                            break;
                        case 8388608:
                            /* 3453 */
                            select = 4;
                            break;
                        case 6291456:
                        case 0:
                            /* 3456 */
                            break;
                    }
                    select = 5;

                    break;
                }

                /* 3461 */
                if (fps == 30) {
                    /* 3462 */
                    switch (bitrate) {
                        case 5242880:
                            /* 3464 */
                            select = 6;
                            break;
                        case 4194304:
                            /* 3467 */
                            select = 7;
                            break;
                        case 3145728:
                        case 0:
                            /* 3470 */
                            break;
                    }
                    select = 8;
                }
                break;


            case 2:
                /* 3478 */
                switch (bitrate) {
                    case 10485760:
                        /* 3480 */
                        select = 0;
                        break;
                    case 8912896:
                        /* 3483 */
                        select = 1;
                        break;
                    case 7340032:
                    case 0:
                        /* 3486 */
                        break;
                }
                select = 2;
                break;
        }






        /* 3495 */
        int tmp = select;
    }


    /* 3503 */   private String device_ssid = "";

    protected boolean isWifiConnectedToSMARP() {
        /* 3506 */
        this.wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        /* 3508 */
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        /* 3509 */
        this.gl_wifiInfo = connManager.getNetworkInfo(1);
        /* 3510 */
        if (!this.wifiManager.isWifiEnabled()) {
            /* 3512 */
            return false;
        }
        /* 3514 */
        this.device_ssid = this.wifiManager.getConnectionInfo().getSSID().toString().replace("\"", "");

        /* 3516 */
        if (this.gl_wifiInfo.isConnected()) {
            /* 3517 */
            String currentPrefix = prefs.getString("SHARED_PREFERENCE_SSID_PREFIX_KEY", "SMARP_");
            /* 3518 */
            if (this.device_ssid.contains(currentPrefix)) {
                /* 3519 */
                return true;
            }
        }
        /* 3522 */
        return false;
    }


    public void openLayoutProgress(int stopInmilesecond) {
    }


    @SuppressLint("WrongConstant")
    private String getCurrentSsid() {
        /* 3536 */
        String ssid = "";
        /* 3537 */
        NetworkInfo wifiInfo = null;
        /* 3538 */
        ConnectivityManager connManager = null;
        /* 3539 */
        WifiManager mwifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
        /* 3540 */
        ssid = mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "");

        /* 3542 */
        if (ssid.startsWith("SMARP_")) ;




        /* 3547 */
        if (wifiInfo == null) {
            /* 3548 */
            connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            /* 3549 */
            wifiInfo = connManager.getNetworkInfo(1);
        }


        /* 3553 */
        if (!wifiInfo.isConnected()) {
            /* 3555 */
            ssid = "";
        }
        /* 3557 */
        return ssid;
    }


    private void openRecordProgressDialog(String title, String msg) {
        /* 3611 */
        this.recordWaidDialog = new ProgressDialog(this);
        /* 3612 */
        this.recordWaidDialog.setTitle(title);
        /* 3613 */
        this.recordWaidDialog.setMessage(msg);
        /* 3614 */
        this.recordWaidDialog.setIndeterminate(true);
        /* 3615 */
        this.recordWaidDialog.setCanceledOnTouchOutside(false);
        /* 3616 */
        this.recordWaidDialog.setCancelable(false);
        /* 3617 */
        this.recordWaidDialog.show();
    }

    private float getPixels(int dp) {
        /* 3621 */
        float scale = (getResources().getDisplayMetrics()).density;
        /* 3622 */
        int px = (int) (dp * scale + 0.5F);
        /* 3623 */
        return px;
    }

    private void openRTSP(int i) {
        /* 3627 */
        mSocket = new UdpSocket();
        /* 3628 */
        Thread t = new Thread(new Runnable() {
            public void run() {
                /* 3631 */
                mSocket.connect(Const.CURRENT_DEVICE_IP);
            }
        });
        /* 3634 */
        t.start();

        /* 3636 */
        int res = AACEncoder.init(16000, 1, Sample_Rate, 16);
        /* 3637 */
        if (res != 0) {
            /* 3638 */
            Toast.makeText(this, "Audio encoder init fail.", Toast.LENGTH_SHORT).show();
        }




        /* 3644 */
        printLog("openRTSP() strVideoPath=" + this.strVideoPath + " i=" + i);
        /* 3645 */
        if (!this.mIsDoRtspFlow) {
            /* 3646 */
            this.mIsDoRtspFlow = true;
            /* 3647 */
            if (!gl_isStartRtsp &&
                    /* 3648 */         service != null && ((MessageService) service).isSocketAlive()) {
                /* 3649 */
                printLog("openRTSP() openWithPath");
                /* 3650 */
                if (openWithPath(this.strVideoPath) == 0) {
                    /* 3652 */
                    if (setup() == 0) {
                        /* 3653 */
                        gl_isStartRtsp = true;

                        /* 3655 */
                        setNetworkOption(3, 1);
                        /* 3656 */
                        setFFmpegDraw(1);
                        /* 3657 */
                        if (gl_FFmpeg == 0) ;



                        /* 3661 */
                        gl_sd_status = -1;

                        /* 3663 */
                        gl_rec_running = -1;

                        /* 3665 */
                        gl_sd_capacity = -1;
                        /* 3666 */
                        gl_sd_fromat = -1;
                        /* 3667 */
                        if (gl_resolution == 0) {
                            /* 3668 */
                            startReceive(640, 0);
                        } else {
                            /* 3670 */
                            startReceive(1280, 0);
                        }
                        /* 3672 */
                        mHandler.post(new Runnable() {


                            public void run() {
                            }
                        });
                    } else {

                        stopJniLive(3);
                    }
                }
            }

            this.mIsDoRtspFlow = false;
            return;
        }
    }

    public boolean testCPU() {

        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {

            return true;
        }

        return false;
    }

    public byte[] getBytes(short s) {
        return getBytes(s, testCPU());
    }


    public byte[] getBytes(long s, boolean bBigEnding) {

        byte[] buf = new byte[8];

        if (bBigEnding) {

            for (int i = buf.length - 1; i >= 0; i--) {

                buf[i] = (byte) (int) (s & 0xFFL);

                s >>= 8;
            }
        } else {

            for (int i = 0; i < buf.length; i++) {

                buf[i] = (byte) (int) (s & 0xFFL);

                s >>= 8;
            }
        }

        return buf;
    }

    public byte[] Shorts2Bytes(short[] s) {

        byte bLength = 2;

        byte[] buf = new byte[s.length * bLength];

        for (int iLoop = 0; iLoop < s.length; iLoop++) {

            byte[] temp = getBytes(s[iLoop]);

            for (int jLoop = 0; jLoop < bLength; jLoop++) {

                buf[iLoop * bLength + jLoop] = temp[jLoop];
            }
        }

        return buf;
    }


    private void printLog(String msg) {
        Log.d("Allen", "RTSPClient: " + msg);
    }


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            if (RTSPClient.this.progressDialog != null && RTSPClient.this.progressDialog.isShowing()) {

                RTSPClient.this.progressDialog.setButton(-2, "取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {

                        OkHttpUtils.getInstance().cancelTag(this);
                    }
                });
            }
        }
    };

    private void initMessag(final List<String> list) {

        this.progressDialog = new ProgressDialog(this);

        this.progressDialog.setMessage("获取结果中");

        this.progressDialog.setCancelable(false);

        this.progressDialog.show();

        this.handler.sendEmptyMessageDelayed(0, 30000L);

        Params params = Params.newInstance();

        if (bean != null) {

            String age = bean.getAge();

            if (TextUtils.isEmpty(age)) {

                bean.setAge("26");
            }

            if (bean.getAge().equals("0")) {

                bean.setAge("26");
            }

            params.params("sex", bean.getSex())
                    .params("address", bean.getAddress())
                    .params("phone", bean.getPhone())
                    .params("cid", bean.getCid())
                    .params("country", bean.getCountry())
                    .params("language", bean.getLanguage())
                    .params("profession", bean.getProfession())
                    .params("wxid", bean.getWxid())
                    .params("province", bean.getProvince())
                    .params("subscribe_time", bean.getSubscribe_time())
                    .params("marriage", bean.getMarriage())
                    .params("qq_time", bean.getQq())
                    .params("ctime", bean.getCtime())
                    .params("city", bean.getCity())
                    .params("jf", bean.getJf())
                    .params("age", bean.getAge())
                    .params("email", bean.getEmail())
                    .params("headimgurl", bean.getHeadimgurl())
                    .params("district", bean.getDistrict())
                    .params("relname", bean.getRelname())
                    .params("commemorate", bean.getCommemorate())
                    .params("dealer_id", bean.getDealer_id())
                    .params("realAge", bean.getAge())
                    .params("dealeridto", bean.getDealeridto());
        } else {

            params.params("realAge", "26");
        }

        List<File> list1 = new ArrayList<File>();

        for (int i = 0; i < list.size(); i++) {

            File file = new File((String) list.get(i));

            list1.add(file);
        }
        Net.post(Const.UploadURl, params, list1, new Net.HttpListener() {
            public void OnSuccess(String message) {
                try {
                    /* 3805 */
                    JSONObject jsonObject = new JSONObject(message);
                    /* 3806 */
                    String result = jsonObject.getString("result");
                    /* 3807 */
                    if (result.contains("success")) {
                        /* 3808 */
                        String guid = jsonObject.getString("Guid");
                        /* 3809 */
                        OkHttpUtils.get()
                                .tag(this)
                                .url(Const.GetResule)
                                .addParams("guid", guid)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(okhttp3.Call call, Exception e, int id) {
                                        Log.e("错误", e.getMessage());
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                progressDialog.dismiss();
                                                Toasty.warning(getBaseContext(), Const.Warrning).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        final String jieguo = response;
                                        Result result = (Result) (new Gson()).fromJson(response, Result.class);
                                        OkHttpUtils.post()
                                                .tag(this)
                                                .url(Const.GetText)
                                                .addParams("waterDegreeValue", result.getWaterDegreeValue() + "")
                                                .addParams("oilValue", result.getOilValue() + "")
                                                .addParams("texttureValue", result.getTextureValue() + "")
                                                .addParams("wrinkleValue", result.getWrinkleValue() + "")
                                                .addParams("pigmentValue", result.getPigmentValue() + "")
                                                .addParams("inflamationValue", result.getInflammationValue() + "")
                                                .addParams("proeSizeValue", result.getPoreSizeValue() + "")
                                                .addParams("skinColorValue", result.getSkinColorValue() + "")
                                                .addParams("compositeScore", result.getCompositeScore() + "")
                                                .build()
                                                .execute(new StringCallback() {
                                                    @Override
                                                    public void onError(okhttp3.Call call, Exception e, int id) {
                                                        progressDialog.dismiss();

                                                        Toasty.warning(getBaseContext(), Const.Warrning).
                                                                show();
                                                    }

                                                    @Override
                                                    public void onResponse(String response, int id) {
                                                        Log.e("网络结果", response);
//{"result":"success","msg":"成功","data":{"poreSizeTips":"1.饮食规律，营养搭配合理。//n2.预防紫外线照射。//n3.继续做好皮肤保养护理。","poreSizeProductList":[{"id":8,"productName":"雪非雪悦颜沁爽护肤系列 ","urlCode":"ASP00053","fileName":"雪非雪悦颜沁爽护肤系列.jpg","guidName":"8f68933f71304314af7304115892cace.jpg"},{"id":38,"productName":"雪非雪悦颜轻透防晒霜","urlCode":"ASP00025","fileName":"雪非雪轻透防晒霜.jpg","guidName":"ff6a1126b12d405cb3d6708fe5e81b74.jpg"}],"pigmentQuestion":"1.基底发生变化,色素沉积增多,色斑明显增多。//n2.过度的日晒，加速色素沉积。//n3.新陈代谢紊乱，色素问题加剧。","inflammationQuestion":"1.皮肤干燥，肤色较差，没有光泽。//n2.有黑眼圈或者明显的眼袋。","skinColorStallText":"肌肤白皙光泽\r\n","synthesizeItem":3,"oilStallText":"肌肤油脂分泌旺盛\r\n","waterStallText":"肌肤水分含量适中\r\n","skinColorProtectSkinWIKI":{"id":46,"productName":"肤色-绿","fileName":"肤色-绿.jpg","guidName":"4de57197f3434f9c83ab84dbb05764e2.jpg"},"textureProductList":[{"id":25,"productName":"汉娜尚妃天地韵幼容护肤系列","urlCode":"ARS00009","fileName":"汉娜尚妃天地韵幼容护肤系列.jpg","guidName":"b3c3ebbf1c5a47f8906f2d8cf1a0a597.jpg"},{"id":27,"productName":"汉娜尚妃天地韵幼容睡眠面膜","urlCode":"ARS00014","fileName":"汉娜尚妃天地韵幼容睡眠面膜.jpg","guidName":"888ebf80661441c7a49350899b607869.jpg"},{"id":24,"productName":"珂露曼防晒精华乳","urlCode":"AGE00067","fileName":"珂露曼防晒精华乳.jpg","guidName":"67fcef551d614af4ac07fde95666807a.jpg"}],"oilProductId":"71,75,77","waterSkinQuestion":"1.肌肤水分含量适中，水分均衡，请继续保持。","wrinkleQuestion":"1.出现衰老导致的不可控皱纹。//n2.出现忽视保养导致的皱纹。","poreSizeProtectSkinWIKI":{"id":49,"productName":"毛孔-绿","fileName":"毛孔-绿.jpg","guidName":"62e6c3a1a5534d9790f2ff9ccebd1fb8.jpg"},"skinColorProductId":"37,43","oilProductList":[{"id":71,"productName":"美之娇水之缘清润护肤系列","urlCode":"AMZ10066","fileName":"2018AMZ10066美之娇水之缘清润护肤系列.jpg","guidName":"a64fd1f4c5704c4883d5462fc9ad6d9d.jpg"},{"id":75,"productName":"相娥花语控油精华液","urlCode":"AXE00034","fileName":"2018相娥花语控油精华液.jpg","guidName":"323cbb4ec907402c9f7d0fe4b73692ba.jpg"},{"id":77,"productName":"相娥花语控油面膜","urlCode":"AXE10006","fileName":"2018相娥花语控油面膜.jpg","guidName":"31649532715e4a1c920c114d65608bf3.jpg"}],"waterProductList":[{"id":73,"productName":"相娥青果菜舒韵护肤系列","urlCode":"AXE00053","fileName":"2018相娥青果菜舒韵护肤系列.jpg","guidName":"fee6a84e955546aeab6bec48227bdb94.jpg"},{"id":74,"productName":"雪非雪悦颜沁爽护肤系列","urlCode":"ASP00053","fileName":"2018雪非雪悦颜沁爽护肤套装.jpg","guidName":"63c2fbf7f10f43b7a053c93d6117269e.jpg"},{"id":28,"productName":"荟馨纯净橄榄护肤系列","urlCode":"AHX00029","fileName":"荟馨纯净橄榄护肤系列.jpg","guidName":"3dd952bc772b43b2a591b79b22d2718a.jpg"}],"wrinkleTips":"1.使用抗皱、修护类护肤品。//n2.每周2次皮肤护理，增加皮肤弹性。","oilTips":"1.注重面部清洁，加强老化角质代谢。//n2.选择控油补水的产品，保持水油平衡。","pigmentStallText":"色斑较为严重\r\n","poreSizeQuestion":"1.毛孔通透细腻光滑，请继续注重日常清洁补水保养，维持健康的肌理。","oilProtectSkinWIKI":{"id":62,"productName":"油脂-红","fileName":"油脂-红.jpg","guidName":"c1fe21e58a854c339986db8a5d2ff958.jpg"},"wrinkleProductList":[{"id":25,"productName":"汉娜尚妃天地韵幼容护肤系列","urlCode":"ARS00009","fileName":"汉娜尚妃天地韵幼容护肤系列.jpg","guidName":"b3c3ebbf1c5a47f8906f2d8cf1a0a597.jpg"},{"id":27,"productName":"汉娜尚妃天地韵幼容睡眠面膜","urlCode":"ARS00014","fileName":"汉娜尚妃天地韵幼容睡眠面膜.jpg","guidName":"888ebf80661441c7a49350899b607869.jpg"},{"id":26,"productName":"汉娜尚妃天地韵幼容按摩霜","urlCode":"ARS00016","fileName":"汉娜尚妃天地韵幼容按摩霜.jpg","guidName":"4482d0a4e8474204a47e17a14cac298b.jpg"}],"wrinkleStallText":"肌肤皱纹明显\r\n","waterProtectSkinWIKI":{"id":58,"productName":"水分-绿","fileName":"水分-绿.jpg","guidName":"ca6f1c1d2e1f4e36831b90565a025752.jpg"},"skinColorTips":"1.坚持日常亮肤护理，注意防晒，预防紫外线伤害。","poreSizeProductId":"8,38","productImageDir":"http://101.132.148.26:80/newlife/product/","pigmentProductList":[{"id":39,"productName":"珂露曼雪漾焕彩护肤系列","urlCode":"AGE00046","fileName":"珂露曼雪漾焕彩护肤系列.jpg","guidName":"cdfed44a093c4885b66c344e7e983f0e.jpg"},{"id":41,"productName":"珂露曼雪漾焕彩组合","urlCode":"AGE00052","fileName":"珂露曼雪漾焕彩组合.jpg","guidName":"c40fdceea58e467f8bebe2e545e24a54.jpg"},{"id":24,"productName":"珂露曼防晒精华乳","urlCode":"AGE00067","fileName":"珂露曼防晒精华乳.jpg","guidName":"67fcef551d614af4ac07fde95666807a.jpg"}],"textureProtectSkinWIKI":{"id":60,"productName":"纹理-黄","fileName":"纹理-黄.jpg","guidName":"1e3283f3c94c419ab0b2c9b9542c912d.jpg"},"textureStallText":"肌肤纹理不规则\r\n","textureQuestion":"1.缺乏营养，表皮增生能力开始减退。//n2.可通过去角质及滋养类护肤品调整肌肤纹理。","textureProductId":"25,27,24","waterTips":"1.坚持每日保养，保持皮肤良好状态。","wrinkleProductId":"25,27,26","oilQuestion":"1.油脂旺盛，水油不平衡。//n2.肌肤代谢能力较差，需加强肌肤新陈代谢。","textureTips":"1.使用滋养、抗皱类护肤品。//n2.注意补水、防晒及清洁等护肤环节。","inflammationStallText":"肌肤处于轻度疲劳","skinColorQuestion":"1.肌肤白皙红润，继续保持日常肌肤护理。","skinColorProductList":[{"id":37,"productName":"雪非雪悦颜亮采护肤系列","urlCode":"ASP00056","fileName":"雪非雪悦颜亮采护肤系列.jpg","guidName":"43723bc3386a47b08d9bc1656fe133ad.jpg"},{"id":43,"productName":"雪非雪悦颜轻透防晒霜","urlCode":"ASP00025","fileName":"雪非雪悦颜轻透防晒霜.jpg","guidName":"43deaf128f564f7195fb9e8e5b0d504d.jpg"}],"inflammationProtectSkinWIKI":{"id":51,"productName":"敏感-黄","fileName":"敏感-黄.jpg","guidName":"2a56c61e8be0436f906d245a764f9295.jpg"},"pigmentProtectSkinWIKI":{"id":53,"productName":"色素-红","fileName":"色素-红.jpg","guidName":"62575131cd834722b4505a9ed28b3969.jpg"},"pigmentProductId":"39,41,24","wrinkleProtectSkinWIKI":{"id":65,"productName":"皱纹-红","fileName":"皱纹-红.jpg","guidName":"f5c89e464d234c4296c40e84d706d3e6.jpg"},"inflammationTips":"1.保持充足的睡眠，良好的饮食习惯。//n 2.注意皮肤保湿的加强及定期给皮肤去除角质。","pigmentTips":"1.适当脸部按摩促进基底循环。//n2.集中护理，注意防晒，预防色斑形成。","poreSizeStallText":"毛孔通透细腻光滑\r\n","inflammationProductId":"78,33,68","waterProductId":"73,74,28","inflammationProductList":[{"id":78,"productName":"珂露曼经典保湿护肤系列","urlCode":"AGE00081","fileName":"2018珂露曼经典保湿护肤系列.jpg","guidName":"cbe3d07d640047d38468c3410d03d263.jpg"},{"id":33,"productName":"溯妍时光赋活安瓶精华","urlCode":"ASY00013","fileName":"溯妍时光赋活安瓶精华.jpg","guidName":"e8a4fc1be370401cb64cfccc3dac7dfa.jpg"},{"id":68,"productName":"珂露曼按摩霜","urlCode":"AGE00022","fileName":"珂露曼按摩霜.jpg","guidName":"8638231fb1a04d559bc932a20d971c08.jpg"}]}}

                                                        Intent intent = new Intent(getApplicationContext(), com.elimei.elimei.TestResultActivity.class);
                                                        intent.putExtra("data", jieguo);
                                                        intent.putExtra("text", response);
                                                        if (bean != null) {
                                                            from = bean.getWxid();
                                                            intent.putExtra("from", from);
                                                            intent.putExtra("Model", bean);
                                                        }
                                                        intent.putStringArrayListExtra("image", (ArrayList) list);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });
                                    }
                                });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            public void OnError(String e) {
                Log.e("结果错误", e + "");
            }
        });
    }


    public Bitmap zoomImg(Bitmap bm) {
        Bitmap bitmap = bm;
        try {
            bitmap = Bitmap.createBitmap(bm, 0, 0, 600, 480);
        } catch (Exception e) {
            Log.e("结果", e.getMessage());
        }
        return bitmap;
    }

    protected abstract void initDone();

    protected abstract void onOrientationCallBack(int paramInt);

    public static native boolean hasAudio();

    public static native void setForceITag(int paramInt);

    public static native void setFFmpegDraw(int paramInt);

    public static native int resetWidth();

    public static native int setGetFrameTag(int paramInt);

    public static native void setDropFrame(int paramInt);

    private native int setNetworkOption(int paramInt1, int paramInt2);

    private native void initialDecode(boolean paramBoolean, int paramInt1, int paramInt2);

    private native void startReceive(int paramInt1, int paramInt2);

    private native int setup();

    private native int openWithPath(String paramString);

    private native void stopRecv();

    private native void tearDown();

    private static native int getWidth();

    private static native int getHeight();

    public static native void codecDeAlloc2();

    public static native void codecInit2();

    public static native void setDrawView(int paramInt);

    public static native void DrawInit(Surface paramSurface);

    public static native void DrawDestroy();

    public static native void Draw2Init(Surface paramSurface);

    public static native void Draw2Destroy();

    public static native int getFPS();


    private void changlight(final int a) {

        new Thread() {
            @Override
            public void run() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("type", "setrgbledstatus");
                    jsonObject.put("rgbstatus", "" + a);
                    if (service != null) {
                        boolean ret = ((MessageService) service).send(jsonObject.toString());
                        if (!ret) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    toastMessage("失败");
                                }
                            });
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.start();

    }
}
