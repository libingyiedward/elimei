/*      */
package com.elimei.elimei.service;
/*      */
/*      */

import android.app.Service;
/*      */ import android.content.BroadcastReceiver;
/*      */ import android.content.Context;
/*      */ import android.content.Intent;
/*      */ import android.content.IntentFilter;
/*      */ import android.os.Binder;
/*      */ import android.os.Environment;
/*      */ import android.os.Handler;
/*      */ import android.os.IBinder;
/*      */ import android.os.StatFs;
/*      */ import android.util.Log;
/*      */ import com.dash.Const;
/*      */ import com.device.DeviceCommand;
/*      */ import com.network.WifiAdmin;
/*      */ import com.rtspclient.RTSPClient;
/*      */ import com.util.DebugLog;
/*      */ import com.util.MessageToast;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.File;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.PrintWriter;
/*      */ import java.net.InetAddress;
/*      */ import java.net.Socket;
/*      */ import java.net.UnknownHostException;
/*      */ import java.text.NumberFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Locale;
/*      */ import java.util.concurrent.locks.Lock;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;

/*      */
/*      */
/*      */ public class MessageService
        extends Service {
    public static final int TAG_DOWNLOAD_DOWNLOADUTIL = 1;
    public static final int TAG_DOWNLOAD_MESSAGESERVICE = 0;
    public static final int TAG_DOWNLOAD_PIC = 2;
    public static final int TAG_DOWNLOAD_RECORD = 3;
    public static final int TAG_DOWNLOAD_PROTECT = 4;
    public static final int TAG_DOWNLOAD_REMOTEPAGE = 5;
    /*   51 */   public static final String RECORD_FILE_THUMBNAIL = Const.download_path + "/record/thumbnail";
    /*   52 */   public static final String PROTECT_FILE_THUMBNAIL = Const.download_path + "/protect/thumbnail";
    /*   53 */   public static final String PIC_THUMBNAILPICTURE_TEMP = Const.download_path + "/tmp/pic";
    /*   54 */   public static final String RECORD_THUMBNAILPICTURE_TEMP = Const.download_path + "/tmp/record";
    /*   55 */   public static final String PROTECT_THUMBNAILPICTURE_TEMP = Const.download_path + "/tmp/protect";

    public static final int PIC_INDEX_FILE = 101;
    public static final int PROTECT_INDEX_FILE = 100;
    public static final int VIDEO_INDEX_FILE = 102;
    /*   60 */   private final int NOTIFY_ID = 1;
    /*   61 */   private final int gl_progressMax = 100;

    /*   63 */   private int gl_progress = 0;

    private boolean gl_indeterminate = false;
    /*   66 */   private String filename = "";
    /*   67 */   private int filetype = 0;
    /*   68 */   private int gl_file_size = 0;
    /*   69 */   private int gl_recv_size = 0;

    /*   71 */   private int gl_record_file_size = 0;
    /*   72 */   private long gl_record_recv_size = 0L;

    private boolean isRunning = false;
    private boolean isConnected = false;
    private boolean isFirst = true;
    private boolean isGetList = false;
    private boolean isGetListFirst = false;
    private static Socket socket;
    /*   80 */   private ServiceBinder mBinder = new ServiceBinder();
    private Thread recv;
    /*   82 */   private Lock lock = new ReentrantLock();
    /*   83 */   private Lock listlock = new ReentrantLock();
    private boolean gl_isDownloadcontinue = false;
    /*   85 */   private String gl_currentDownFileName = "";

    Handler gl_message_handler;

    RTSPClient rtspClientActivity;
    /*   90 */ Socket mSocket_BG = null;
    /*   91 */ Socket mSocket_FG = null;
    /*   92 */   private int gl_indexfileSelect = 0;
    /*   93 */   private int gl_port_BG = 0;
    /*   94 */   private int gl_port_FG = 0;


    public boolean downloadContinueFlag = false;


    /*  100 */
    public boolean getRestartFgSocketFlag() {
        return this.downloadContinueFlag;
    }


    /*  104 */
    public void setDownloadContinueFlag(boolean isRestart) {
        this.downloadContinueFlag = isRestart;
    }


    /*  108 */
    public int getIndexFileTag() {
        return this.gl_indexfileSelect;
    }


    /*  112 */
    public void setIndexfileTag(int index) {
        this.gl_indexfileSelect = index;
    }


    /*  126 */
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }


    /*  131 */
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    public void onCreate() {
        /*  136 */
        super.onCreate();
        /*  137 */
        registerReceiver(this.receiver, new IntentFilter("android.intent.action.ACTION_SHUTDOWN"));






        /*  144 */
        if (this.isFirst) {
            /*  145 */
            Thread t = new Thread() {

                public void run() {
                    /*  147 */
                    MessageService.this.connect(Const.CURRENT_DEVICE_IP, 8080);

                }

            };

            /*  151 */
            t.start();
            /*  152 */
            this.isFirst = false;

        }

    }


    /*  157 */
    public void setActivity(RTSPClient rtspClient) {
        this.rtspClientActivity = rtspClient;
    }


    public void onDestroy() {
        /*  165 */
        super.onDestroy();
        /*  166 */
        unregisterReceiver(this.receiver);
        /*  167 */
        this.isRunning = false;

        /*  169 */
        WaitForThreadStop(this.recv);
        /*  170 */
        close();

    }


    public boolean connectFileReceiveSocket(int method, int port) {

        try {
            /*  176 */
            if (method == 0) {
                /*  177 */
                if (this.mSocket_BG != null &&
                        /*  178 */           this.mSocket_BG.isConnected()) {
                    /*  179 */
                    return true;

                }

                /*  182 */
                this.mSocket_BG = new Socket(Const.CURRENT_DEVICE_IP, port);
                /*  183 */
                this.mSocket_BG.setTcpNoDelay(true);
                /*  184 */
                this.mSocket_BG.setReceiveBufferSize(65024);
                /*  185 */
                this.mSocket_BG.setSoTimeout(30000);
                /*  186 */
            } else if (method == 1) {
                /*  187 */
                if (this.mSocket_FG != null &&
                        /*  188 */           this.mSocket_FG.isConnected()) {
                    /*  189 */
                    return true;

                }

                /*  192 */
                this.mSocket_FG = new Socket(Const.CURRENT_DEVICE_IP, port);
                /*  193 */
                this.mSocket_FG.setTcpNoDelay(true);
                /*  194 */
                this.mSocket_FG.setReceiveBufferSize(65024);
                /*  195 */
                this.mSocket_FG.setSoTimeout(30000);

            }

            /*  198 */
            return true;
            /*  199 */
        } catch (UnknownHostException e) {

            /*  201 */
            e.printStackTrace();
            /*  202 */
        } catch (IOException e) {

            /*  204 */
            e.printStackTrace();
            /*  205 */
        } catch (Exception e) {

            /*  207 */
            e.printStackTrace();

        }
        /*  209 */
        return false;

    }


    /*  213 */ long preTime = 0L;


    public boolean connect(String ip, int port) {
        /*  217 */
        long connectTime = System.currentTimeMillis();
        /*  218 */
        if (connectTime - this.preTime < 1000L) {
            /*  219 */
            return false;

        }
        /*  221 */
        this.preTime = connectTime;

        try {
            /*  223 */
            Log.d("ming", "socket connect");
            /*  224 */
            DebugLog.i("连接到" + ip + ":" + port);
            /*  225 */
            InetAddress targetAddr = InetAddress.getByName(ip);
            /*  226 */
            socket = null;
            /*  227 */
            socket = new Socket(targetAddr, port);
            /*  228 */
            socket.setReceiveBufferSize(256000);
            /*  229 */
            socket.setTcpNoDelay(true);


            try {
                /*  233 */
                receive();
                /*  234 */
            } catch (Exception e) {
                /*  235 */
                e.printStackTrace();

            }
            /*  237 */
            this.isRunning = true;
            /*  238 */
            this.isConnected = true;
            /*  239 */
            DebugLog.i("Send Const.BROADCAST_SOCKET_CONNECT");
            /*  240 */
            Intent i = new Intent("broadcast_socket_connect");
            /*  241 */
            sendBroadcast(i);
            /*  242 */
            syncTime();
            /*  243 */
            Log.d("ming", "socket connect finish");
            /*  244 */
            return true;

        }
        /*  246 */ catch (Exception e) {
            /*  247 */
            Log.d("ming", "socket connect Exception: " + e.toString());
            /*  248 */
            DebugLog.i("连接异常" + e.toString());
            /*  249 */
            e.printStackTrace();













            /*  263 */
            return false;

        }

    }

    /*  266 */ DeviceCommand cmd = new DeviceCommand();


    private String getTimeZone() {
        /*  269 */
//        timeZone = "";x
        /*  270 */
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        /*  271 */
        String str = (new SimpleDateFormat("Z")).format(cal.getTime());

        /*  273 */
        return "GMT" + str.substring(0, 3) + ":" + str.substring(3, 5);

    }


    private void syncTime() {
        /*  278 */
        Thread t = new Thread() {

            public void run() {
                /*  280 */
                Calendar c = Calendar.getInstance();
                /*  281 */
                int year = c.get(1);
                /*  282 */
                int month = c.get(2) + 1;
                /*  283 */
                int day = c.get(5);
                /*  284 */
                int hour = c.get(11);
                /*  285 */
                int min = c.get(12);
                /*  286 */
                int sec = c.get(13);

                /*  288 */
                boolean ret = MessageService.this.send(MessageService.this.cmd.SyncTime(year, month, day, hour, min, sec, MessageService.this.getTimeZone()).toString());

            }

        };
        /*  291 */
        t.start();

    }


    public boolean CheckSocketIsAlive() {
        /*  296 */
        if (socket.isConnected() &&
                /*  297 */       this.isConnected) {
            /*  298 */
            return true;

        }


        /*  302 */
        return false;

    }


    public boolean send(String data) {
        /*  307 */
        if (!isSocketAlive()) {
            /*  308 */
            if (!data.contains("streamvideo")) {
                /*  309 */
                Log.d("Allen", "socket reconnect ccc data=" + data);
                /*  310 */
                reconnect();
            }
            /*  312 */
            return false;

        }

        /*  315 */
        this.lock.lock();
        /*  316 */
        int count = 0;
        /*  317 */
        boolean result = false;


        do {
            /*  320 */
            if (socket != null) {


                try {
                    /*  323 */
                    Log.d("Sonix", "Send Socket to Device: " + data);


                    /*  326 */
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                    /*  328 */
                    out.println(data);

                    /*  330 */
                    if (this.recv.isAlive()) ;




                    /*  335 */
                    result = true;

                    break;
                    /*  337 */
                } catch (IOException e) {
                    /*  338 */
                    e.printStackTrace();


                }
                /*  341 */ catch (Exception e) {
                    e.printStackTrace();

                }
            } else {
                this.isRunning = false;

            }
            count++;

            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        } while (count < 1);
        this.lock.unlock();
        return result;
    }


    public boolean send(byte[] data) {

        this.lock.lock();
        int count = 0;
        boolean result = false;

        do {

            if (socket != null) {

                try {

                    Log.d("Sonix", "Send Socket to Device: " + new String(data));

                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                    out.println(data);

                    if (this.recv.isAlive()) ;
                    result = true;
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            } else {

                this.isRunning = false;

                connect(Const.CURRENT_DEVICE_IP, 8080);

            }

            count++;

            try {

                Thread.sleep(20L);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        } while (count < 1);

        this.lock.unlock();

        return false;

    }


    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        try {

            this.rtspClientActivity.stopJniLive(6);

            Log.d("Allen", "onTaskRemovedaaaaaa");

            if (socket != null && this.isConnected == true) {

                socket.close();

            }

            WifiAdmin wifiAdmin = new WifiAdmin(this);

            wifiAdmin.disconnectWifi();

        } catch (Exception exception) {
        }

        stopSelf();

    }


    private int percent(long diliverNum, long queryMailNum) {

        int result = 0;

        NumberFormat numberFormat = NumberFormat.getInstance();

        numberFormat.setMaximumFractionDigits(0);

        String tmpResult = numberFormat.format(((float) diliverNum / (float) queryMailNum * 100.0F));

        result = Integer.valueOf(tmpResult).intValue();


        if (result >= 100) {

            if (diliverNum >= queryMailNum) {

                result = 100;

            } else {

                result = 99;

            }

        }

        return result;

    }

    BroadcastReceiver receiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            /*  469 */
            String action = intent.getAction();
            /*  470 */
            if (action.equals("android.intent.action.ACTION_SHUTDOWN") &&
                    /*  471 */           MessageService.this.isConnected) {
                /*  472 */
                MessageService.this.close();

            }

        }

    };


    /*  480 */
    public boolean isSocketAlive() {
        return this.isConnected;
    }


    public void closeFileReceiveSocket(int method) {
        /*  485 */
        if (method == 0) {

            try {
                /*  487 */
                this.mSocket_BG.close();
                /*  488 */
            } catch (IOException e) {

                /*  490 */
                e.printStackTrace();

            }
            /*  492 */
            this.mSocket_BG = null;
            /*  493 */
        } else if (method == 1) {

            try {
                /*  495 */
                this.mSocket_FG.close();
                /*  496 */
            } catch (IOException e) {

                /*  498 */
                e.printStackTrace();

            }
            /*  500 */
            this.mSocket_FG = null;

        }

    }


    public void reconnectFileReceiveSocket() {

        try {
            /*  508 */
            if (this.mSocket_BG != null) {
                /*  509 */
                this.mSocket_BG.close();

            }
            /*  511 */
            if (this.mSocket_FG != null) {
                /*  512 */
                this.mSocket_FG.close();

            }
            /*  514 */
        } catch (IOException e) {

            /*  516 */
            e.printStackTrace();

        }
        /*  518 */
        this.mSocket_BG = null;
        /*  519 */
        this.mSocket_FG = null;
        /*  520 */
        send(this.cmd.downloadFileStart(1, 0).toString());

    }


    public void reconnect() {
        /*  525 */
        Log.d("ming", "socket reconnect");
        /*  526 */
        DebugLog.i("重连...");
        /*  527 */
        Thread t = new Thread() {

            public void run() {
                /*  529 */
                MessageService.this.isRunning = false;
                /*  530 */
                if (socket != null && MessageService.this.isConnected) {
                    /*  531 */
                    MessageService.this.isConnected = false;

                    try {
                        /*  533 */
                        MessageService.this.WaitForThreadStop(MessageService.this.recv);
                        /*  534 */
                        MessageService.this.recv = null;

                        /*  536 */
                        socket.close();
                        /*  537 */
                    } catch (IOException e) {
                        /*  538 */
                        e.printStackTrace();
                        /*  539 */
                        Log.d("reconnect", "socket exception =" + e.toString());
                        /*  540 */
                    } catch (Exception e) {
                        /*  541 */
                        Log.d("reconnect", "socket exception =" + e.toString());

                    }

                }
                /*  544 */
                socket = null;
                /*  545 */
                MessageService.this.connect(Const.CURRENT_DEVICE_IP, 8080);

            }

        };
        /*  548 */
        t.start();

    }


    public void getList() {
        /*  552 */
        this.isGetList = true;
        /*  553 */
        this.isGetListFirst = true;

    }


    private boolean WaitForThreadStop(Thread t) {
        int waitCount = 0;
        if (t == null) {
            return true;
        }
        t.interrupt();
        while (waitCount < 2) {
            if (!t.isAlive()) {
                return true;
            }
            try {
                t.sleep(20L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitCount++;
        }
        if (t.isAlive() == true) {
            try {
                t.stop();
                return true;
            } catch (Exception exception) {
            }
        }
        return false;
    }


    private void receive() {
        this.recv = new Thread() {
            public void run() {
                while (MessageService.this.isRunning) {
                    byte[] buffer = new byte[1024];
                    try {
                        if (socket == null) {
                            return;
                        }
                        InputStream input = socket.getInputStream();
                        int num = 0;
                        while ((num = input.read(buffer)) != -1 && MessageService.this.isRunning == true) {
                            byte[] resp = new byte[num];
                            System.arraycopy(buffer, 0, resp, 0, num);
                            Log.d("Sonix", "Receive Socket from Device: " + new String(resp));

                            try {
                                JSONObject json = new JSONObject(new String(resp));
                                String cmd_type = json.getString("type");
                                if (cmd_type.equals("setchannel_res")) {

                                    int status;
                                    if (json.has("resp_status")) {
                                        status = json.getInt("resp_status");
                                    } else {
                                        status = json.getInt("status");

                                    }

                                    Intent intent = new Intent("BROADCAST_SET_CHANNEL");
                                    intent.putExtra("status", status);
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                }
                                if (cmd_type.equals("setpwd_res")) {

                                    int status;
                                    if (json.has("resp_status")) {
                                        status = json.getInt("resp_status");
                                    } else {
                                        status = json.getInt("status");
                                    }

                                    Intent intent = new Intent("BROADCAST_SET_PWD");
                                    /*  627 */
                                    intent.putExtra("status", status);
                                    /*  628 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  629 */
                                }
                                if (cmd_type.equals("setwdr_res")) {

                                    int status;
                                    /*  631 */
                                    if (json.has("resp_status")) {
                                        /*  632 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /*  634 */
                                        status = json.getInt("status");

                                    }
                                    /*  636 */
                                    Intent intent = new Intent("BROADCAST_SET_WDR");
                                    /*  637 */
                                    intent.putExtra("status", status);
                                    /*  638 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  639 */
                                }
                                if (cmd_type.equals("setmirror_res")) {

                                    int status;
                                    /*  641 */
                                    if (json.has("resp_status")) {
                                        /*  642 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /*  644 */
                                        status = json.getInt("status");

                                    }
                                    /*  646 */
                                    Intent intent = new Intent("BROADCAST_SET_MIRROR");
                                    /*  647 */
                                    intent.putExtra("status", status);
                                    /*  648 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  649 */
                                }
                                if (cmd_type.equals("setflip_res")) {

                                    int status;
                                    /*  651 */
                                    if (json.has("resp_status")) {
                                        /*  652 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /*  654 */
                                        status = json.getInt("status");

                                    }
                                    /*  656 */
                                    Intent intent = new Intent("BROADCAST_SET_FLIP");
                                    /*  657 */
                                    intent.putExtra("status", status);
                                    /*  658 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  659 */
                                }
                                if (cmd_type.equals("getsdspace_res")) {
                                    /*  660 */
                                    int available = json.getInt("sdspace");
                                    /*  661 */
                                    int total = json.getInt("totalspace");
                                    /*  662 */
                                    int errorcode = json.getInt("errorcode");
                                    /*  663 */
                                    Intent intent = new Intent("BROADCAST_GET_SD_SPACE");
                                    /*  664 */
                                    intent.putExtra("available", available);
                                    /*  665 */
                                    intent.putExtra("total", total);
                                    /*  666 */
                                    intent.putExtra("errorcode", errorcode);
                                    /*  667 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  668 */
                                }
                                if (cmd_type.equals("setsdformat_res")) {

                                    int status;
                                    /*  670 */
                                    if (json.has("resp_status")) {
                                        /*  671 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /*  673 */
                                        status = json.getInt("status");

                                    }
                                    /*  675 */
                                    Intent intent = new Intent("BROADCAST_SET_SD_FORMAT");
                                    /*  676 */
                                    intent.putExtra("status", status);
                                    /*  677 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  678 */
                                }
                                if (cmd_type.equals("setrecordstatus_res")) {

                                    int status;
                                    /*  680 */
                                    if (json.has("resp_status")) {
                                        /*  681 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /*  683 */
                                        status = json.getInt("status");

                                    }
                                    /*  685 */
                                    Intent intent = new Intent("BROADCAST_SET_RECORD_STATUS");
                                    /*  686 */
                                    intent.putExtra("status", status);
                                    /*  687 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  688 */
                                }
                                if (cmd_type.equals("takepicture_res")) {

                                    int status;
                                    /*  690 */
                                    if (json.has("resp_status")) {
                                        /*  691 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /*  693 */
                                        status = json.getInt("status");

                                    }
                                    /*  695 */
                                    Log.d("Allen", "takepicture_res status =" + status);
                                    /*  696 */
                                    Intent intent = new Intent("BROADCAST_TAKE_PICTURE");
                                    /*  697 */
                                    intent.putExtra("status", status);
                                    /*  698 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  699 */
                                }
                                if (cmd_type.equals("synctime_res")) {

                                    int status;
                                    /*  701 */
                                    if (json.has("resp_status")) {
                                        /*  702 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /*  704 */
                                        status = json.getInt("status");

                                    }
                                    /*  706 */
                                    Intent intent = new Intent("BROADCAST_SYNC_TIME");
                                    /*  707 */
                                    intent.putExtra("status", status);
                                    /*  708 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  709 */
                                }
                                if (cmd_type.equals("gettime_res")) {
                                    /*  710 */
                                    int year = json.getInt("year");
                                    /*  711 */
                                    int month = json.getInt("month");
                                    /*  712 */
                                    int day = json.getInt("day");
                                    /*  713 */
                                    int hour = json.getInt("hour");
                                    /*  714 */
                                    int min = json.getInt("min");
                                    /*  715 */
                                    int sec = json.getInt("sec");
                                    /*  716 */
                                    String str = json.getString("timezone");
                                    continue;
                                    /*  717 */
                                }
                                if (cmd_type.equals("getbatterystatus_res")) {
                                    /*  718 */
                                    int battery = json.getInt("batterylevel");

                                    /*  720 */
                                    Intent intent = new Intent("BROADCAST_GET_BATTERY_STATUS");
                                    /*  721 */
                                    intent.putExtra("battery", battery);
                                    /*  722 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /*  723 */
                                }
                                if (cmd_type.equals("downloadfile_res")) {

                                    /*  725 */
                                    if (json.has("status")) {
                                        /*  726 */
                                        int status = json.getInt("status");
                                        /*  727 */
                                        int tag = 0;
                                        /*  728 */
                                        if (json.has("tag")) {
                                            /*  729 */
                                            tag = json.getInt("tag");

                                        }

                                        /*  732 */
                                        if (status != 69376) {



                                            /*  736 */
                                            Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");

                                            /*  738 */
                                            intent.putExtra("status", 17760513);

                                            /*  740 */
                                            intent.putExtra("filename", MessageService.this.filename);
                                            /*  741 */
                                            intent.putExtra("error", status);
                                            /*  742 */
                                            intent.putExtra("tag", tag);
                                            /*  743 */
                                            MessageService.this.sendBroadcast(intent);

                                            continue;

                                        }
                                        /*  746 */
                                        String fileSaveName = "";
                                        /*  747 */
                                        if (MessageService.this.output != null) {
                                            /*  748 */
                                            MessageService.this.output.close();
                                            /*  749 */
                                            MessageService.this.output = null;

                                        }
                                        /*  751 */
                                        if (MessageService.this.filetype == 0) {
                                            /*  752 */
                                            File mFilePath = new File(Const.download_path + "/record");

                                            /*  754 */
                                            if (!mFilePath.exists()) {
                                                /*  755 */
                                                mFilePath.mkdirs();

                                            }
                                            /*  757 */
                                            String filePath = "";
                                            /*  758 */
                                            if (!MessageService.this.gl_isDownloadcontinue || MessageService.this
/*  759 */.gl_currentDownFileName.equals("")) {

                                                /*  761 */
                                                if (MessageService.this.filename.contains("avi")) {
                                                    /*  762 */
                                                    filePath = MessageService.this.getFileSaveName(Const.download_path + "/record/" + MessageService.this
                                                            /*  763 */.filename, ".avi");
                                                    /*  764 */
                                                } else if (MessageService.this.filename.contains("mp4")) {
                                                    /*  765 */
                                                    filePath = MessageService.this.getFileSaveName(Const.download_path + "/record/" + MessageService.this
                                                            /*  766 */.filename, ".mp4");

                                                }
                                                /*  768 */
                                                MessageService.this.gl_currentDownFileName = filePath;

                                            } else {
                                                /*  770 */
                                                filePath = MessageService.this.gl_currentDownFileName;

                                            }

                                            /*  773 */
                                            MessageService.this
                                                    /*  774 */.output = new FileOutputStream(filePath, MessageService.this.gl_isDownloadcontinue);
                                            /*  775 */
                                        } else if (MessageService.this.filetype == 1) {
                                            /*  776 */
                                            File mFilePath = new File(Const.download_path + "/protect");

                                            /*  778 */
                                            if (!mFilePath.exists()) {
                                                /*  779 */
                                                mFilePath.mkdirs();

                                            }




                                            /*  785 */
                                            String filePath = "";
                                            /*  786 */
                                            if (!MessageService.this.gl_isDownloadcontinue || MessageService.this
/*  787 */.gl_currentDownFileName.equals("")) {
                                                /*  788 */
                                                if (MessageService.this.filename.contains("avi")) {
                                                    /*  789 */
                                                    filePath = MessageService.this.getFileSaveName(Const.download_path + "/protect/" + MessageService.this
                                                            /*  790 */.filename, ".avi");
                                                    /*  791 */
                                                } else if (MessageService.this.filename.contains("mp4")) {
                                                    /*  792 */
                                                    filePath = MessageService.this.getFileSaveName(Const.download_path + "/protect/" + MessageService.this
                                                            /*  793 */.filename, ".mp4");

                                                }

                                                /*  796 */
                                                MessageService.this.gl_currentDownFileName = filePath;

                                            } else {
                                                /*  798 */
                                                filePath = MessageService.this.gl_currentDownFileName;

                                            }
                                            /*  800 */
                                            MessageService.this
                                                    /*  801 */.output = new FileOutputStream(filePath, MessageService.this.gl_isDownloadcontinue);
                                            /*  802 */
                                        } else if (MessageService.this.filetype == 2) {
                                            /*  803 */
                                            File mFilePath = new File(Const.download_path + "/picture");

                                            /*  805 */
                                            if (!mFilePath.exists()) {
                                                /*  806 */
                                                mFilePath.mkdirs();

                                            }
                                            /*  808 */
                                            String filePath = MessageService.this.getFileSaveName(Const.download_path + "/picture/" + MessageService.this
                                                    /*  809 */.filename, ".jpg");
                                            /*  810 */
                                            String[] tmpFilePath = filePath.split("/");
                                            /*  811 */
                                            fileSaveName = tmpFilePath[tmpFilePath.length - 1];

                                            /*  813 */
                                            MessageService.this.output = new FileOutputStream(filePath);
                                            /*  814 */
                                        } else if (MessageService.this.filetype == 3) {
                                            /*  815 */
                                            File mFilePath = new File(MessageService.PIC_THUMBNAILPICTURE_TEMP);


                                            /*  818 */
                                            if (!mFilePath.exists()) {
                                                /*  819 */
                                                mFilePath.mkdirs();

                                            }





                                            /*  826 */
                                            String filePath = MessageService.PIC_THUMBNAILPICTURE_TEMP + "/" + MessageService.this.filename;
                                            /*  827 */
                                            MessageService.this.output = new FileOutputStream(filePath);

                                        }
                                        /*  829 */
                                        else if (MessageService.this.filetype == 4) {



                                            /*  833 */
                                            String filePath = MessageService.RECORD_THUMBNAILPICTURE_TEMP;
                                            /*  834 */
                                            File mFilePath = new File(filePath);
                                            /*  835 */
                                            if (!mFilePath.exists()) {
                                                /*  836 */
                                                mFilePath.mkdirs();

                                            }

                                            /*  839 */
                                            filePath = filePath + "/" + MessageService.this.filename;

                                            try {
                                                /*  841 */
                                                MessageService.this.output = new FileOutputStream(filePath);

                                            }
                                            /*  843 */ catch (Exception e) {


                                                continue;

                                            }

                                            /*  848 */
                                        } else if (MessageService.this.filetype == 5) {
                                            /*  849 */
                                            File mFilePath = new File(MessageService.RECORD_FILE_THUMBNAIL);
                                            /*  850 */
                                            if (!mFilePath.exists()) {
                                                /*  851 */
                                                mFilePath.mkdirs();

                                            }
                                            /*  853 */
                                            String filePath = MessageService.this.getFileSaveName(MessageService.RECORD_FILE_THUMBNAIL + "/" + MessageService.this
                                                    /*  854 */.filename, ".jpg");
                                            /*  855 */
                                            MessageService.this.output = new FileOutputStream(filePath);
                                            /*  856 */
                                        } else if (MessageService.this.filetype == 6) {
                                            /*  857 */
                                            File mFilePath = new File(MessageService.PROTECT_FILE_THUMBNAIL);
                                            /*  858 */
                                            if (!mFilePath.exists()) {
                                                /*  859 */
                                                mFilePath.mkdirs();

                                            }
                                            /*  861 */
                                            String filePath = MessageService.this.getFileSaveName(MessageService.PROTECT_FILE_THUMBNAIL + "/" + MessageService.this
                                                    /*  862 */.filename, ".jpg");
                                            /*  863 */
                                            MessageService.this.output = new FileOutputStream(filePath);
                                            /*  864 */
                                        } else if (MessageService.this.filetype == 7) {
                                            /*  865 */
                                            String filePath = MessageService.PROTECT_THUMBNAILPICTURE_TEMP;
                                            /*  866 */
                                            File mFilePath = new File(filePath);
                                            /*  867 */
                                            if (!mFilePath.exists()) {
                                                /*  868 */
                                                mFilePath.mkdirs();

                                            }

                                            /*  871 */
                                            filePath = filePath + "/" + MessageService.this.filename;

                                            try {
                                                /*  873 */
                                                MessageService.this.output = new FileOutputStream(filePath);

                                            }
                                            /*  875 */ catch (Exception e) {

                                                continue;

                                            }

                                        }

                                        /*  880 */
                                        if (MessageService.this.filetype == 2) {

                                            /*  882 */
                                            MessageService.this.respDownloadFile(resp, fileSaveName, MessageService.this.filetype);
                                            continue;

                                        }
                                        /*  884 */
                                        MessageService.this.respDownloadFile(resp, MessageService.this.filename, MessageService.this.filetype);


                                        continue;

                                    }

                                    /*  889 */
                                    int status = json.getInt("resp_status");
                                    /*  890 */
                                    int tag = 0;
                                    /*  891 */
                                    if (json.has("tag")) {
                                        /*  892 */
                                        tag = json.getInt("tag");

                                    }

                                    /*  895 */
                                    Log.d("Allen", "downloadfile_res 00000");
                                    /*  896 */
                                    if (!MessageToast.isCommandSuccess(status)) {


                                        /*  899 */
                                        Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                                        /*  900 */
                                        intent.putExtra("status", status);
                                        /*  901 */
                                        intent.putExtra("filename", MessageService.this.filename);

                                        /*  903 */
                                        intent.putExtra("tag", tag);

                                        /*  905 */
                                        MessageService.this.sendBroadcast(intent);

                                        continue;

                                    }
                                    /*  908 */
                                    String fileSaveName = "";
                                    /*  909 */
                                    if (MessageService.this.output != null) {
                                        /*  910 */
                                        MessageService.this.output.close();
                                        /*  911 */
                                        MessageService.this.output = null;

                                    }
                                    /*  913 */
                                    if (MessageService.this.filetype == 0) {
                                        /*  914 */
                                        Log.d("Allen", "downloadfile_res 1111");
                                        /*  915 */
                                        File mFilePath = new File(Const.download_path + "/record");
                                        /*  916 */
                                        if (!mFilePath.exists()) {
                                            /*  917 */
                                            mFilePath.mkdirs();

                                        }
                                        /*  919 */
                                        String filePath = "";
                                        /*  920 */
                                        if (!MessageService.this.gl_isDownloadcontinue || MessageService.this.gl_currentDownFileName.equals("")) {
                                            /*  921 */
                                            if (MessageService.this.filename.contains("avi")) {
                                                /*  922 */
                                                filePath = MessageService.this.getFileSaveName(Const.download_path + "/record/" + MessageService.this.filename, ".avi");
                                                /*  923 */
                                                MessageService.this.gl_currentDownFileName = filePath;
                                                /*  924 */
                                            } else if (MessageService.this.filename.contains("mp4")) {
                                                /*  925 */
                                                filePath = MessageService.this.getFileSaveName(Const.download_path + "/record/" + MessageService.this.filename, ".mp4");
                                                /*  926 */
                                                MessageService.this.gl_currentDownFileName = filePath;

                                            }

                                        } else {
                                            /*  929 */
                                            filePath = MessageService.this.gl_currentDownFileName;

                                        }

                                        /*  932 */
                                        Log.d("Allen", "downloadfile_res 22222 filePath=" + filePath);


                                        /*  935 */
                                        MessageService.this.output = new FileOutputStream(filePath, MessageService.this.gl_isDownloadcontinue);






                                        /*  942 */
                                        Log.d("Allen", "downloadfile_res 33333");
                                        /*  943 */
                                    } else if (MessageService.this.filetype == 1) {
                                        /*  944 */
                                        File mFilePath = new File(Const.download_path + "/protect");
                                        /*  945 */
                                        if (!mFilePath.exists()) {
                                            /*  946 */
                                            mFilePath.mkdirs();

                                        }

                                        /*  949 */
                                        String filePath = "";
                                        /*  950 */
                                        if (!MessageService.this.gl_isDownloadcontinue || MessageService.this.gl_currentDownFileName.equals("")) {
                                            /*  951 */
                                            if (MessageService.this.filename.contains("avi")) {
                                                /*  952 */
                                                filePath = MessageService.this.getFileSaveName(Const.download_path + "/protect/" + MessageService.this.filename, ".avi");
                                                /*  953 */
                                                MessageService.this.gl_currentDownFileName = filePath;
                                                /*  954 */
                                            } else if (MessageService.this.filename.contains("mp4")) {
                                                /*  955 */
                                                filePath = MessageService.this.getFileSaveName(Const.download_path + "/protect/" + MessageService.this.filename, ".mp4");
                                                /*  956 */
                                                MessageService.this.gl_currentDownFileName = filePath;

                                            }

                                        } else {
                                            /*  959 */
                                            filePath = MessageService.this.gl_currentDownFileName;

                                        }
                                        /*  961 */
                                        MessageService.this.output = new FileOutputStream(filePath, MessageService.this.gl_isDownloadcontinue);
                                        /*  962 */
                                    } else if (MessageService.this.filetype == 2) {
                                        /*  963 */
                                        File mFilePath = new File(Const.download_path + "/picture");
                                        /*  964 */
                                        if (!mFilePath.exists()) {
                                            /*  965 */
                                            mFilePath.mkdirs();

                                        }
                                        /*  967 */
                                        String filePath = MessageService.this.getFileSaveName(Const.download_path + "/picture/" + MessageService.this.filename, ".jpg");
                                        /*  968 */
                                        String[] tmpFilePath = filePath.split("/");
                                        /*  969 */
                                        fileSaveName = tmpFilePath[tmpFilePath.length - 1];

                                        /*  971 */
                                        MessageService.this.output = new FileOutputStream(filePath);
                                        /*  972 */
                                    } else if (MessageService.this.filetype == 3) {
                                        /*  973 */
                                        File mFilePath = new File(MessageService.PIC_THUMBNAILPICTURE_TEMP);
                                        /*  974 */
                                        if (!mFilePath.exists()) {
                                            /*  975 */
                                            mFilePath.mkdirs();

                                        }

                                        /*  978 */
                                        String filePath = MessageService.PIC_THUMBNAILPICTURE_TEMP + "/" + MessageService.this.filename;
                                        /*  979 */
                                        MessageService.this.output = new FileOutputStream(filePath);
                                        /*  980 */
                                    } else if (MessageService.this.filetype == 4) {

                                        /*  982 */
                                        String filePath = MessageService.RECORD_THUMBNAILPICTURE_TEMP;
                                        /*  983 */
                                        File mFilePath = new File(filePath);
                                        /*  984 */
                                        if (!mFilePath.exists()) {
                                            /*  985 */
                                            mFilePath.mkdirs();

                                        }

                                        /*  988 */
                                        filePath = filePath + "/" + MessageService.this.filename;

                                        try {
                                            /*  990 */
                                            MessageService.this.output = new FileOutputStream(filePath);

                                        }
                                        /*  992 */ catch (Exception e) {


                                            continue;

                                        }

                                        /*  997 */
                                    } else if (MessageService.this.filetype == 5) {
                                        /*  998 */
                                        File mFilePath = new File(MessageService.RECORD_FILE_THUMBNAIL);
                                        /*  999 */
                                        if (!mFilePath.exists()) {
                                            /* 1000 */
                                            mFilePath.mkdirs();

                                        }
                                        /* 1002 */
                                        String filePath = MessageService.this.getFileSaveName(MessageService.RECORD_FILE_THUMBNAIL + "/" + MessageService.this.filename, ".jpg");
                                        /* 1003 */
                                        MessageService.this.output = new FileOutputStream(filePath);
                                        /* 1004 */
                                    } else if (MessageService.this.filetype == 6) {
                                        /* 1005 */
                                        File mFilePath = new File(MessageService.PROTECT_FILE_THUMBNAIL);
                                        /* 1006 */
                                        if (!mFilePath.exists()) {
                                            /* 1007 */
                                            mFilePath.mkdirs();

                                        }
                                        /* 1009 */
                                        String filePath = MessageService.this.getFileSaveName(MessageService.PROTECT_FILE_THUMBNAIL + "/" + MessageService.this.filename, ".jpg");
                                        /* 1010 */
                                        MessageService.this.output = new FileOutputStream(filePath);
                                        /* 1011 */
                                    } else if (MessageService.this.filetype == 7) {
                                        /* 1012 */
                                        String filePath = MessageService.PROTECT_THUMBNAILPICTURE_TEMP;
                                        /* 1013 */
                                        File mFilePath = new File(filePath);
                                        /* 1014 */
                                        if (!mFilePath.exists()) {
                                            /* 1015 */
                                            mFilePath.mkdirs();

                                        }

                                        /* 1018 */
                                        filePath = filePath + "/" + MessageService.this.filename;

                                        try {
                                            /* 1020 */
                                            MessageService.this.output = new FileOutputStream(filePath);

                                        }
                                        /* 1022 */ catch (Exception e) {

                                            continue;

                                        }

                                    }

                                    /* 1027 */
                                    switch (MessageService.this.filetype) {
                                        case 7:
                                            /* 1029 */
                                            MessageService.this.respDownloadFile_FG(resp, MessageService.this.filename, MessageService.this.filetype);

                                            continue;

                                        case 4:
                                            /* 1032 */
                                            MessageService.this.respDownloadFile_FG(resp, MessageService.this.filename, MessageService.this.filetype);

                                            continue;

                                        case 3:
                                            /* 1035 */
                                            MessageService.this.respDownloadFile_FG(resp, MessageService.this.filename, MessageService.this.filetype);

                                            continue;

                                        case 2:
                                            /* 1038 */
                                            MessageService.this.respDownloadFile_FG(resp, fileSaveName, MessageService.this.filetype);

                                            continue;

                                        case 0:
                                            /* 1041 */
                                            Log.d("Allen", "downloadfile_res 33333");
                                            /* 1042 */
                                            Log.d("Allen", "download000 filetype=" + MessageService.this.filetype);
                                            /* 1043 */
                                            MessageService.this.respDownloadFile_FG(resp, fileSaveName, MessageService.this.filetype);

                                            continue;

                                        case 1:
                                            /* 1046 */
                                            MessageService.this.respDownloadFile_FG(resp, fileSaveName, MessageService.this.filetype);

                                            continue;

                                        case 5:
                                            /* 1049 */
                                            MessageService.this.respDownloadFile_FG(resp, fileSaveName, MessageService.this.filetype);
                                            continue;

                                        case 6:
                                            break;

                                        default:
                                            /* 1052 */
                                            continue;
                                    }
                                    MessageService.this.respDownloadFile_FG(resp, fileSaveName, MessageService.this.filetype);


                                    continue;

                                }



                                /* 1061 */
                                if (cmd_type.equals("downloadfilefinish_res")) {




                                    /* 1066 */
                                    if (json.has("status")) {
                                        /* 1067 */
                                        int status = json.getInt("status");
                                        /* 1068 */
                                        int tag = 0;

                                        /* 1070 */
                                        String filename = "";
                                        /* 1071 */
                                        if (json.has("tag")) {
                                            /* 1072 */
                                            tag = json.getInt("tag");

                                        }
                                        /* 1074 */
                                        if (json.has("filename")) {
                                            /* 1075 */
                                            filename = json.getString("filename");

                                        } else {
                                            /* 1077 */
                                            filename = "";

                                        }

                                        /* 1080 */
                                        Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE_FINISH");

                                        /* 1082 */
                                        intent.putExtra("status", status);
                                        /* 1083 */
                                        intent.putExtra("tag", tag);
                                        /* 1084 */
                                        intent.putExtra("filename", filename);

                                        /* 1086 */
                                        MessageService.this.sendBroadcast(intent);

                                        continue;

                                    }
                                    /* 1089 */
                                    int status = json.getInt("resp_status");
                                    /* 1090 */
                                    int download_method = json.getInt("download_method");
                                    /* 1091 */
                                    MessageService.this.closeFileReceiveSocket(download_method);
                                    /* 1092 */
                                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE_FINISH");
                                    /* 1093 */
                                    intent.putExtra("status", status);
                                    /* 1094 */
                                    if (json.has("tag")) {
                                        /* 1095 */
                                        intent.putExtra("tag", json.getInt("tag"));

                                    }
                                    /* 1097 */
                                    intent.putExtra("download_method", download_method);

                                    /* 1099 */
                                    MessageService.this.sendBroadcast(intent);


                                    continue;

                                }








                                /* 1119 */
                                if (cmd_type.equals("getindexfile_res")) {
                                    /* 1120 */
                                    if (json.has("status")) {
                                        /* 1121 */
                                        int status = json.getInt("status");
                                        /* 1122 */
                                        int seed = 0;
                                        /* 1123 */
                                        if (json.has("seed")) {
                                            /* 1124 */
                                            seed = json.getInt("seed");

                                        }
                                        /* 1126 */
                                        Intent mIntent = new Intent("BROADCAST_SEED");
                                        /* 1127 */
                                        mIntent.putExtra("seed", seed);
                                        /* 1128 */
                                        mIntent.putExtra("status", status);
                                        /* 1129 */
                                        MessageService.this.sendBroadcast(mIntent);

                                        /* 1131 */
                                        if (status != 69120) {
                                            /* 1132 */
                                            Intent intent = new Intent("BROADCAST_GET_INDEX_FILE_FILE_FAIL");

                                            /* 1134 */
                                            intent.putExtra("status", status);
                                            /* 1135 */
                                            MessageService.this.sendBroadcast(intent);


                                            continue;

                                        }
                                        /* 1139 */
                                        if (MessageService.this.output != null) {
                                            /* 1140 */
                                            MessageService.this.output.close();
                                            /* 1141 */
                                            MessageService.this.output = null;

                                        }

                                        /* 1144 */
                                        File mFilePath = new File(Const.download_path);
                                        /* 1145 */
                                        if (!mFilePath.exists()) {
                                            /* 1146 */
                                            mFilePath.mkdirs();

                                        }

                                        /* 1149 */
                                        if (MessageService.this.gl_indexfileSelect == 101) {
                                            /* 1150 */
                                            MessageService.this.output = new FileOutputStream(Const.pic_file_index_path);

                                        }
                                        /* 1152 */
                                        else if (MessageService.this.gl_indexfileSelect == 100) {

                                            /* 1154 */
                                            MessageService.this.output = new FileOutputStream(Const.protect_file_index_path);

                                        }


                                        /* 1158 */
                                        MessageService.this.respGetIndexFile1_0(resp);


                                        continue;

                                    }
                                    /* 1162 */
                                    int status = json.getInt("resp_status");
                                    /* 1163 */
                                    int seed = 0;
                                    /* 1164 */
                                    if (json.has("seed")) {
                                        /* 1165 */
                                        seed = json.getInt("seed");

                                    }
                                    /* 1167 */
                                    Intent mIntent = new Intent("BROADCAST_SEED");
                                    /* 1168 */
                                    mIntent.putExtra("seed", seed);
                                    /* 1169 */
                                    mIntent.putExtra("status", status);
                                    /* 1170 */
                                    MessageService.this.sendBroadcast(mIntent);

                                    /* 1172 */
                                    if (!MessageToast.isCommandSuccess(status)) {
                                        /* 1173 */
                                        Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                                        /* 1174 */
                                        intent.putExtra("status", status);
                                        /* 1175 */
                                        MessageService.this.sendBroadcast(intent);
                                        continue;

                                    }
                                    /* 1177 */
                                    if (MessageService.this.output != null) {
                                        /* 1178 */
                                        MessageService.this.output.close();
                                        /* 1179 */
                                        MessageService.this.output = null;

                                    }

                                    /* 1182 */
                                    File mFilePath = new File(Const.download_path);
                                    /* 1183 */
                                    if (!mFilePath.exists()) {
                                        /* 1184 */
                                        mFilePath.mkdirs();

                                    }

                                    /* 1187 */
                                    if (MessageService.this.gl_indexfileSelect == 101) {
                                        /* 1188 */
                                        MessageService.this.output = new FileOutputStream(Const.pic_file_index_path);
                                        /* 1189 */
                                    } else if (MessageService.this.gl_indexfileSelect == 100) {

                                        /* 1191 */
                                        MessageService.this.output = new FileOutputStream(Const.protect_file_index_path);
                                        /* 1192 */
                                    } else if (MessageService.this.gl_indexfileSelect == 102) {
                                        /* 1193 */
                                        MessageService.this.output = new FileOutputStream(Const.file_list_path);

                                    }

                                    /* 1196 */
                                    MessageService.this.respGetIndexFile(resp);
                                    continue;

                                }
                                /* 1198 */
                                if (cmd_type.equals("deletefile_res")) {

                                    int status;
                                    /* 1200 */
                                    if (json.has("resp_status")) {
                                        /* 1201 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1203 */
                                        status = json.getInt("status");

                                    }

                                    /* 1206 */
                                    Intent intent = new Intent("BROADCAST_DELETE_FILE");
                                    /* 1207 */
                                    intent.putExtra("status", status);
                                    /* 1208 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1209 */
                                }
                                if (cmd_type.equals("streamvideo_res")) {

                                    int status;
                                    /* 1211 */
                                    if (json.has("resp_status")) {
                                        /* 1212 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1214 */
                                        status = json.getInt("status");

                                    }
                                    /* 1216 */
                                    String name = json.getString("rtspname");
                                    /* 1217 */
                                    int live = json.getInt("live");


                                    /* 1220 */
                                    Intent intent = new Intent("BROADCAST_STREAM_VIDEO");
                                    /* 1221 */
                                    intent.putExtra("filename", name);
                                    /* 1222 */
                                    intent.putExtra("live", live);
                                    /* 1223 */
                                    intent.putExtra("status", status);
                                    /* 1224 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1225 */
                                }
                                if (cmd_type.equals("streamvideofinish_res")) {
                                    /* 1226 */
                                    int status = json.getInt("status");

                                    /* 1228 */
                                    Intent intent = new Intent("BROADCAST_STREAM_VIDEO_FINISH");
                                    /* 1229 */
                                    intent.putExtra("status", status);
                                    /* 1230 */
                                    Log.d("Allen", "streamvideofinish_res123");
                                    /* 1231 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1232 */
                                }
                                if (cmd_type.equals("usbdclassmode_res")) {

                                    int status;
                                    /* 1234 */
                                    if (json.has("resp_status")) {
                                        /* 1235 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1237 */
                                        status = json.getInt("status");

                                    }
                                    /* 1239 */
                                    Intent intent = new Intent("BROADCAST_USBD_MODE");
                                    /* 1240 */
                                    if (status == 78080) {
                                        /* 1241 */
                                        intent.putExtra("status", 0);
                                        /* 1242 */
                                    } else if (status == 78081) {
                                        /* 1243 */
                                        intent.putExtra("status", 1);
                                        /* 1244 */
                                    }
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1245 */
                                }
                                if (cmd_type.equals("sendfontfile_res")) {

                                    int status;
                                    /* 1247 */
                                    if (json.has("resp_status")) {
                                        /* 1248 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1250 */
                                        status = json.getInt("status");

                                    }
                                    /* 1252 */
                                    int port = json.getInt("port");

                                    /* 1254 */
                                    Intent intent = new Intent("BROADCAST_SEND_FONT_FILE");
                                    /* 1255 */
                                    intent.putExtra("status", status);
                                    /* 1256 */
                                    intent.putExtra("port", port);
                                    /* 1257 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1258 */
                                }
                                if (cmd_type.equals("getvideostatus_res")) {
                                    /* 1259 */
                                    int wdr = json.getInt("wdr");
                                    /* 1260 */
                                    int mirror = json.getInt("mirror");
                                    /* 1261 */
                                    int flip = json.getInt("flip");
                                    /* 1262 */
                                    int fps = json.getInt("fps");
                                    /* 1263 */
                                    long bitrate = json.getLong("bitrate");
                                    /* 1264 */
                                    int resolution = json.getInt("resolution");
                                    /* 1265 */
                                    int pre_ext_pframe_num = 0;
                                    /* 1266 */
                                    int pre_ext_qp_range = 0;
                                    /* 1267 */
                                    int pre_ext_qp_max = 0;
                                    /* 1268 */
                                    int pre_ext_upper_pframe = 0;
                                    /* 1269 */
                                    int pre_ext_upper_pframe_dup1 = 0;
                                    /* 1270 */
                                    int pre_qp_max = 0;
                                    /* 1271 */
                                    int pre_qp_min = 0;
                                    /* 1272 */
                                    int resume = 0;
                                    /* 1273 */
                                    int suspend = 0;
                                    /* 1274 */
                                    int gop = 0;
                                    /* 1275 */
                                    int usbdclassmode = 0;
                                    /* 1276 */
                                    if (json.has("gop")) {
                                        /* 1277 */
                                        gop = json.getInt("gop");

                                    }
                                    /* 1279 */
                                    if (json.has("pre_ext_pframe_num")) {
                                        /* 1280 */
                                        pre_ext_pframe_num = json.getInt("pre_ext_pframe_num");

                                    }
                                    /* 1282 */
                                    if (json.has("pre_ext_qp_range")) {
                                        /* 1283 */
                                        pre_ext_qp_range = json.getInt("pre_ext_qp_range");

                                    }
                                    /* 1285 */
                                    if (json.has("pre_ext_qp_max")) {
                                        /* 1286 */
                                        pre_ext_qp_max = json.getInt("pre_ext_qp_max");

                                    }
                                    /* 1288 */
                                    if (json.has("pre_ext_upper_pframe")) {
                                        /* 1289 */
                                        pre_ext_upper_pframe = json.getInt("pre_ext_upper_pframe");

                                    }
                                    /* 1291 */
                                    if (json.has("pre_ext_upper_pframe_dup1")) {
                                        /* 1292 */
                                        pre_ext_upper_pframe_dup1 = json.getInt("pre_ext_upper_pframe_dup1");

                                    }
                                    /* 1294 */
                                    if (json.has("pre_qp_max")) {
                                        /* 1295 */
                                        pre_qp_max = json.getInt("pre_qp_max");

                                    }
                                    /* 1297 */
                                    if (json.has("pre_qp_min")) {
                                        /* 1298 */
                                        pre_qp_min = json.getInt("pre_qp_min");

                                    }
                                    /* 1300 */
                                    if (json.has("resume")) {
                                        /* 1301 */
                                        resume = json.getInt("resume");

                                    }
                                    /* 1303 */
                                    if (json.has("suspend")) {
                                        /* 1304 */
                                        suspend = json.getInt("suspend");

                                    }
                                    /* 1306 */
                                    if (json.has("usbdclassmode")) {
                                        /* 1307 */
                                        usbdclassmode = json.getInt("usbdclassmode");

                                    }
                                    /* 1309 */
                                    Intent intent = new Intent("BROADCAST_GET_VIDEO_STATUS");
                                    /* 1310 */
                                    intent.putExtra("wdr", wdr);
                                    /* 1311 */
                                    intent.putExtra("mirror", mirror);
                                    /* 1312 */
                                    intent.putExtra("flip", flip);
                                    /* 1313 */
                                    intent.putExtra("fps", fps);
                                    /* 1314 */
                                    intent.putExtra("bitrate", bitrate);
                                    /* 1315 */
                                    intent.putExtra("resolution", resolution);
                                    /* 1316 */
                                    intent.putExtra("gop", gop);
                                    /* 1317 */
                                    intent.putExtra("pre_ext_pframe_num", pre_ext_pframe_num);
                                    /* 1318 */
                                    intent.putExtra("pre_ext_qp_range", pre_ext_qp_range);
                                    /* 1319 */
                                    intent.putExtra("pre_ext_qp_max", pre_ext_qp_max);
                                    /* 1320 */
                                    intent.putExtra("pre_ext_upper_pframe", pre_ext_upper_pframe);
                                    /* 1321 */
                                    intent.putExtra("pre_ext_upper_pframe_dup1", pre_ext_upper_pframe_dup1);
                                    /* 1322 */
                                    intent.putExtra("pre_qp_max", pre_qp_max);
                                    /* 1323 */
                                    intent.putExtra("pre_qp_min", pre_qp_min);
                                    /* 1324 */
                                    intent.putExtra("resume", resume);
                                    /* 1325 */
                                    intent.putExtra("suspend", suspend);
                                    /* 1326 */
                                    intent.putExtra("usbdclassmode", usbdclassmode);
                                    /* 1327 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1328 */
                                }
                                if (cmd_type.equals("getrecordstatus_res")) {

                                    /* 1330 */
                                    int status = json.getInt("recstatus");
                                    /* 1331 */
                                    int volume = json.getInt("volume");
                                    /* 1332 */
                                    int length = json.getInt("length");
                                    /* 1333 */
                                    int fps = json.getInt("fps");
                                    /* 1334 */
                                    long bitrate = json.getLong("bitrate");
                                    /* 1335 */
                                    int resolution = json.getInt("resolution");
                                    /* 1336 */
                                    int loop = json.getInt("loop");
                                    /* 1337 */
                                    int capability = json.getInt("capability");
                                    /* 1338 */
                                    int recrunning = 0;
                                    /* 1339 */
                                    int gop = json.getInt("gop");
                                    /* 1340 */
                                    int rec_ext_pframe_num = 0;
                                    /* 1341 */
                                    int rec_ext_qp_range = 0;
                                    /* 1342 */
                                    int rec_ext_qp_max = 0;
                                    /* 1343 */
                                    int rec_ext_upper_pframe = 0;
                                    /* 1344 */
                                    int rec_qp_max = 0;
                                    /* 1345 */
                                    int rec_qp_min = 0;
                                    /* 1346 */
                                    int resp_status = 0;
                                    /* 1347 */
                                    if (json.has("resp_status")) {
                                        /* 1348 */
                                        resp_status = json.getInt("resp_status");

                                    }
                                    /* 1350 */
                                    if (json.has("recrunning")) {
                                        /* 1351 */
                                        recrunning = json.getInt("recrunning");

                                    }
                                    /* 1353 */
                                    if (json.has("rec_ext_pframe_num")) {
                                        /* 1354 */
                                        rec_ext_pframe_num = json.getInt("rec_ext_pframe_num");

                                    }
                                    /* 1356 */
                                    if (json.has("rec_ext_qp_range")) {
                                        /* 1357 */
                                        rec_ext_qp_range = json.getInt("rec_ext_qp_range");

                                    }
                                    /* 1359 */
                                    if (json.has("rec_ext_qp_max")) {
                                        /* 1360 */
                                        rec_ext_qp_max = json.getInt("rec_ext_qp_max");

                                    }
                                    /* 1362 */
                                    if (json.has("rec_ext_upper_pframe")) {
                                        /* 1363 */
                                        rec_ext_upper_pframe = json.getInt("rec_ext_upper_pframe");

                                    }
                                    /* 1365 */
                                    if (json.has("rec_qp_max")) {
                                        /* 1366 */
                                        rec_qp_max = json.getInt("rec_qp_max");

                                    }
                                    /* 1368 */
                                    if (json.has("rec_qp_min")) {
                                        /* 1369 */
                                        rec_qp_min = json.getInt("rec_qp_min");

                                    }
                                    /* 1371 */
                                    Intent intent = new Intent("BROADCAST_GET_RECORD_STATUS");
                                    /* 1372 */
                                    intent.putExtra("recstatus", status);
                                    /* 1373 */
                                    intent.putExtra("volume", volume);
                                    /* 1374 */
                                    intent.putExtra("length", length);
                                    /* 1375 */
                                    intent.putExtra("fps", fps);
                                    /* 1376 */
                                    intent.putExtra("bitrate", bitrate);
                                    /* 1377 */
                                    intent.putExtra("resolution", resolution);
                                    /* 1378 */
                                    intent.putExtra("loop", loop);
                                    /* 1379 */
                                    intent.putExtra("capability", capability);
                                    /* 1380 */
                                    intent.putExtra("recrunning", recrunning);
                                    /* 1381 */
                                    intent.putExtra("gop", gop);
                                    /* 1382 */
                                    intent.putExtra("rec_ext_pframe_num", rec_ext_pframe_num);
                                    /* 1383 */
                                    intent.putExtra("rec_ext_qp_range", rec_ext_qp_range);
                                    /* 1384 */
                                    intent.putExtra("rec_ext_qp_max", rec_ext_qp_max);
                                    /* 1385 */
                                    intent.putExtra("rec_ext_upper_pframe", rec_ext_upper_pframe);
                                    /* 1386 */
                                    intent.putExtra("rec_qp_max", rec_qp_max);
                                    /* 1387 */
                                    intent.putExtra("rec_qp_min", rec_qp_min);
                                    /* 1388 */
                                    intent.putExtra("resp_status", resp_status);

                                    /* 1390 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1391 */
                                }
                                if (cmd_type.equals("getdeviceparameter_res")) {
                                    /* 1392 */
                                    int year = json.getInt("year");
                                    /* 1393 */
                                    int month = json.getInt("month");
                                    /* 1394 */
                                    int day = json.getInt("day");
                                    /* 1395 */
                                    int hour = json.getInt("hour");
                                    /* 1396 */
                                    int min = json.getInt("min");
                                    /* 1397 */
                                    int sec = json.getInt("sec");
                                    /* 1398 */
                                    int resp_status = 0;
                                    /* 1399 */
                                    int tx_retry = 0;
                                    /* 1400 */
                                    int udp_type = 0;
                                    /* 1401 */
                                    if (json.has("tx_retry")) {
                                        /* 1402 */
                                        tx_retry = json.getInt("tx_retry");

                                    }
                                    /* 1404 */
                                    if (json.has("udp_type")) {
                                        /* 1405 */
                                        udp_type = json.getInt("udp_type");

                                    }
                                    /* 1407 */
                                    if (json.has("resp_status")) {
                                        /* 1408 */
                                        resp_status = json.getInt("resp_status");

                                    }
                                    /* 1410 */
                                    String timezone = json.getString("timezone");
                                    /* 1411 */
                                    int channel = json.getInt("wifichannel");
                                    /* 1412 */
                                    String ssid = json.getString("ssid");
                                    /* 1413 */
                                    String pwd = json.getString("pwd");
                                    /* 1414 */
                                    String iqversion = "";
                                    /* 1415 */
                                    int gsensor = 0;
                                    /* 1416 */
                                    if (json.has("iqversion"))
                                        /* 1417 */
                                        iqversion = Integer.toHexString(json.getInt("iqversion"));
                                    /* 1418 */
                                    if (json.has("gsensor_sensitivity"))
                                        /* 1419 */ gsensor = json.getInt("gsensor_sensitivity");
                                    /* 1420 */
                                    String fwversion = json.getString("fwversion");
                                    /* 1421 */
                                    int powerfrequency = json.getInt("powerfrequency");
                                    /* 1422 */
                                    Intent intent = new Intent("BROADCAST_GET_DEVICE_PARAMETER");
                                    /* 1423 */
                                    intent.putExtra("resp_status", resp_status);
                                    /* 1424 */
                                    intent.putExtra("year", year);
                                    /* 1425 */
                                    intent.putExtra("month", month);
                                    /* 1426 */
                                    intent.putExtra("day", day);
                                    /* 1427 */
                                    intent.putExtra("hour", hour);
                                    /* 1428 */
                                    intent.putExtra("min", min);
                                    /* 1429 */
                                    intent.putExtra("sec", sec);
                                    /* 1430 */
                                    intent.putExtra("timezone", timezone);
                                    /* 1431 */
                                    intent.putExtra("channel", channel);
                                    /* 1432 */
                                    intent.putExtra("ssid", ssid);
                                    /* 1433 */
                                    intent.putExtra("pwd", pwd);
                                    /* 1434 */
                                    intent.putExtra("tx_retry", tx_retry);
                                    /* 1435 */
                                    intent.putExtra("powerfrequency", powerfrequency);
                                    /* 1436 */
                                    intent.putExtra("udp_type", udp_type);
                                    /* 1437 */
                                    if (json.has("iqversion"))
                                        /* 1438 */ intent.putExtra("iqversion", iqversion);
                                    /* 1439 */
                                    if (json.has("gsensor_sensitivity"))
                                        /* 1440 */ intent.putExtra("gsensor_sensitivity", gsensor);
                                    /* 1441 */
                                    intent.putExtra("fwversion", fwversion);
                                    /* 1442 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1443 */
                                }
                                if (cmd_type.equals("sendfwbin_res")) {

                                    int status;




                                    /* 1449 */
                                    if (json.has("resp_status")) {
                                        /* 1450 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1452 */
                                        status = json.getInt("status");

                                    }
                                    /* 1454 */
                                    int port = json.getInt("port");
                                    /* 1455 */
                                    Intent intent = new Intent("BROADCAST_SEND_FW_FILE");
                                    /* 1456 */
                                    intent.putExtra("status", status);
                                    /* 1457 */
                                    intent.putExtra("port", port);
                                    /* 1458 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1459 */
                                }
                                if (cmd_type.equals("upgradefw_res")) {

                                    int status;









                                    /* 1470 */
                                    if (json.has("resp_status")) {
                                        /* 1471 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1473 */
                                        status = json.getInt("status");

                                    }

                                    /* 1476 */
                                    Intent intent = new Intent("BROADCAST_UPGRADE_FW");
                                    /* 1477 */
                                    intent.putExtra("status", status);

                                    /* 1479 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;

                                }
                                /* 1481 */
                                if (cmd_type.equals("setlooprecordstatus_res")) {

                                    int status;







                                    /* 1490 */
                                    if (json.has("resp_status")) {
                                        /* 1491 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1493 */
                                        status = json.getInt("status");

                                    }
                                    /* 1495 */
                                    Intent intent = new Intent("BROADCAST_SET_LOOP_RECORD");
                                    /* 1496 */
                                    intent.putExtra("status", status);

                                    /* 1498 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1499 */
                                }
                                if (cmd_type.equals("setrecordaudiostatus_res")) {

                                    int status;






                                    /* 1507 */
                                    if (json.has("resp_status")) {
                                        /* 1508 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1510 */
                                        status = json.getInt("status");

                                    }
                                    /* 1512 */
                                    Intent intent = new Intent("BROADCAST_SET_RECORD_VOLUMN");
                                    /* 1513 */
                                    intent.putExtra("status", status);

                                    /* 1515 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1516 */
                                }
                                if (cmd_type.equals("setrecordlength_res")) {






                                    /* 1523 */
                                    int status, rec_length = 0;
                                    /* 1524 */
                                    if (json.has("resp_status")) {
                                        /* 1525 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1527 */
                                        status = json.getInt("status");

                                    }
                                    /* 1529 */
                                    if (json.has("rec_length")) {
                                        /* 1530 */
                                        rec_length = json.getInt("rec_length");

                                    }
                                    /* 1532 */
                                    Intent intent = new Intent("BROADCAST_SET_RECORD_LENGTH");
                                    /* 1533 */
                                    intent.putExtra("status", status);

                                    /* 1535 */
                                    intent.putExtra("rec_length", rec_length);
                                    /* 1536 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1537 */
                                }
                                if (cmd_type.equals("setwifiparameters_res")) {

                                    int status;






                                    /* 1545 */
                                    if (json.has("resp_status")) {
                                        /* 1546 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1548 */
                                        status = json.getInt("status");

                                    }
                                    /* 1550 */
                                    Intent intent = new Intent("BROADCAST_SET_WIFI_PARAMETER");
                                    /* 1551 */
                                    intent.putExtra("status", status);

                                    /* 1553 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1554 */
                                }
                                if (cmd_type.equals("setrecordparameters_res")) {

                                    int status;






                                    /* 1562 */
                                    if (json.has("resp_status")) {
                                        /* 1563 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1565 */
                                        status = json.getInt("status");

                                    }
                                    /* 1567 */
                                    Intent intent = new Intent("BROADCAST_SET_RECORD_PARAMETER");
                                    /* 1568 */
                                    intent.putExtra("status", status);

                                    /* 1570 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1571 */
                                }
                                if (cmd_type.equals("setvideoparameters_res")) {

                                    int status;






                                    /* 1579 */
                                    if (json.has("resp_status")) {
                                        /* 1580 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1582 */
                                        status = json.getInt("status");

                                    }
                                    /* 1584 */
                                    Intent intent = new Intent("BROADCAST_SET_VIDEO_PARAMETER");
                                    /* 1585 */
                                    intent.putExtra("status", status);

                                    /* 1587 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1588 */
                                }
                                if (cmd_type.equals("setpowerfrequency_res")) {

                                    int status;






                                    /* 1596 */
                                    if (json.has("resp_status")) {
                                        /* 1597 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1599 */
                                        status = json.getInt("status");

                                    }
                                    /* 1601 */
                                    Intent intent = new Intent("BROADCAST_SET_POWER_FRQUENCY");
                                    /* 1602 */
                                    intent.putExtra("status", status);


                                    /* 1605 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1606 */
                                }
                                if (cmd_type.equals("getrecordcapability_res")) {

                                    /* 1608 */
                                    int capability = json.getInt("capability");

                                    /* 1610 */
                                    Intent intent = new Intent("BROADCAST_GET_RECORD_CAPABILITY");

                                    /* 1612 */
                                    intent.putExtra("capability", capability);


                                    /* 1615 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1616 */
                                }
                                if (cmd_type.equals("getosdstatus_res")) {

                                    int status;

                                    /* 1619 */
                                    if (json.has("resp_status")) {
                                        /* 1620 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1622 */
                                        status = json.getInt("status");

                                    }
                                    /* 1624 */
                                    int osd = json.getInt("osd");
                                    /* 1625 */
                                    String txt_osd = "";
                                    /* 1626 */
                                    if (json.has("unicode")) {
                                        /* 1627 */
                                        JSONArray unicode = json.getJSONArray("unicode");

                                        /* 1629 */
                                        for (int i = 0; i < unicode.length(); i++) {
                                            /* 1631 */
                                            txt_osd = txt_osd + Character.toString((char) unicode.getInt(i));

                                        }

                                    }

                                    /* 1635 */
                                    Intent intent = new Intent("BROADCAST_GET_OSD_STATUS");
                                    /* 1636 */
                                    intent.putExtra("status", status);
                                    /* 1637 */
                                    intent.putExtra("osd", osd);
                                    /* 1638 */
                                    intent.putExtra("txt_osd", txt_osd);
                                    /* 1639 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;

                                }
                                /* 1641 */
                                if (cmd_type.equals("setosdonoff_res")) {

                                    int status;

                                    /* 1644 */
                                    if (json.has("resp_status")) {
                                        /* 1645 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1647 */
                                        status = json.getInt("status");

                                    }
                                    /* 1649 */
                                    Intent intent = new Intent("BROADCAST_SET_OSD_ON_OFF");
                                    /* 1650 */
                                    intent.putExtra("status", status);

                                    /* 1652 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1653 */
                                }
                                if (cmd_type.equals("setgsensorparameter_res")) {

                                    int status;

                                    /* 1656 */
                                    if (json.has("resp_status")) {
                                        /* 1657 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1659 */
                                        status = json.getInt("status");

                                    }
                                    /* 1661 */
                                    Intent intent = new Intent("BROADCAST_SET_GSENSOR");
                                    /* 1662 */
                                    intent.putExtra("status", status);

                                    /* 1664 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1665 */
                                }
                                if (cmd_type.equals("nvramresettodefault_res")) {

                                    int status;


                                    /* 1669 */
                                    if (json.has("resp_status")) {
                                        /* 1670 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1672 */
                                        status = json.getInt("status");

                                    }
                                    /* 1674 */
                                    Intent intent = new Intent("BROADCAST_RESET_TO_DEFAULT");
                                    /* 1675 */
                                    intent.putExtra("status", status);
                                    /* 1676 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1677 */
                                }
                                if (cmd_type.equals("getsdformat_res")) {

                                    int status;

                                    /* 1680 */
                                    if (json.has("resp_status")) {
                                        /* 1681 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1683 */
                                        status = json.getInt("status");

                                    }
                                    /* 1685 */
                                    Intent intent = new Intent("BROADCAST_GET_FORMAT_STATUS");
                                    /* 1686 */
                                    intent.putExtra("status", status);
                                    /* 1687 */
                                    if (json.has("sdspace")) {
                                        /* 1688 */
                                        int available = json.getInt("sdspace");
                                        /* 1689 */
                                        intent.putExtra("available", available);

                                    }


                                    /* 1693 */
                                    if (json.has("totalspace")) {
                                        /* 1694 */
                                        int total = json.getInt("totalspace");
                                        /* 1695 */
                                        intent.putExtra("total", total);

                                    }


                                    /* 1699 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1700 */
                                }
                                if (cmd_type.equals("downloadfilestart_res")) {
                                    /* 1701 */
                                    int status = json.getInt("resp_status");
                                    /* 1702 */
                                    int port = json.getInt("port");
                                    /* 1703 */
                                    int download_method = json.getInt("download_method");

                                    /* 1705 */
                                    if (MessageToast.isCommandSuccess(status)) {
                                        /* 1706 */
                                        if (download_method == 0) {
                                            /* 1707 */
                                            MessageService.this.gl_port_BG = port;
                                            /* 1708 */
                                            MessageService.this.connectFileReceiveSocket(0, MessageService.this
                                                    /* 1709 */.gl_port_BG);
                                            /* 1710 */
                                        } else if (download_method == 1) {
                                            /* 1711 */
                                            MessageService.this.gl_port_FG = port;
                                            /* 1712 */
                                            MessageService.this.connectFileReceiveSocket(1, MessageService.this
                                                    /* 1713 */.gl_port_FG);

                                        }

                                    }







                                    /* 1723 */
                                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE_START");
                                    /* 1724 */
                                    intent.putExtra("status", status);
                                    /* 1725 */
                                    intent.putExtra("port", port);
                                    /* 1726 */
                                    if (json.has("tag")) {
                                        /* 1727 */
                                        intent.putExtra("tag", json.getInt("tag"));

                                    }
                                    /* 1729 */
                                    intent.putExtra("restart", MessageService.this.getRestartFgSocketFlag());
                                    /* 1730 */
                                    Log.d("Allen", "restart flag 000=" + MessageService.this.getRestartFgSocketFlag());
                                    /* 1731 */
                                    intent.putExtra("download_method", download_method);
                                    /* 1732 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;

                                }
                                /* 1734 */
                                if (cmd_type.equals("setsdtest_res")) {

                                    int status;

                                    /* 1737 */
                                    if (json.has("resp_status")) {
                                        /* 1738 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1740 */
                                        status = json.getInt("status");

                                    }
                                    /* 1742 */
                                    Intent intent = new Intent("BROADCAST_SET_SD_TEST");
                                    /* 1743 */
                                    intent.putExtra("status", status);
                                    /* 1744 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1745 */
                                }
                                if (cmd_type.equals("getsdtest_res")) {

                                    int status;

                                    /* 1748 */
                                    if (json.has("resp_status")) {
                                        /* 1749 */
                                        status = json.getInt("resp_status");

                                    } else {
                                        /* 1751 */
                                        status = json.getInt("status");

                                    }
                                    /* 1753 */
                                    String avg_write = json.getString("avg_write");
                                    /* 1754 */
                                    String avg_read = json.getString("avg_read");
                                    /* 1755 */
                                    Intent intent = new Intent("BROADCAST_GET_SD_TEST");
                                    /* 1756 */
                                    intent.putExtra("status", status);
                                    /* 1757 */
                                    intent.putExtra("avg_write", avg_write);
                                    /* 1758 */
                                    intent.putExtra("avg_read", avg_read);

                                    /* 1760 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1761 */
                                }
                                if (cmd_type.equals("setwificonnection_res")) {
                                    /* 1762 */
                                    int status = json.getInt("resp_status");
                                    /* 1763 */
                                    Intent intent = new Intent("BROADCAST_SET_WIFI_CONNECTION");
                                    /* 1764 */
                                    intent.putExtra("status", status);
                                    /* 1765 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1766 */
                                }
                                if (cmd_type.equals("getezsetup_res")) {
                                    /* 1767 */
                                    int status = json.getInt("resp_status");
                                    /* 1768 */
                                    int current_mode = json.getInt("current_mode");
                                    /* 1769 */
                                    int support_mode = json.getInt("support_mode");
                                    /* 1770 */
                                    Intent intent = new Intent("BROADCAST_GET_EZSETUP");
                                    /* 1771 */
                                    intent.putExtra("status", status);
                                    /* 1772 */
                                    intent.putExtra("current_mode", current_mode);
                                    /* 1773 */
                                    intent.putExtra("support_mode", support_mode);
                                    /* 1774 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1775 */
                                }
                                if (cmd_type.equals("setezsetup_res")) {
                                    /* 1776 */
                                    int status = json.getInt("resp_status");
                                    /* 1777 */
                                    Intent intent = new Intent("BROADCAST_SET_EZSETUP");
                                    /* 1778 */
                                    intent.putExtra("status", status);
                                    /* 1779 */
                                    MessageService.this.sendBroadcast(intent);
                                    continue;
                                    /* 1780 */
                                }
                                if (cmd_type.equals("getiqversion_res")) {

                                    int status;
                                    /* 1782 */
                                    if (json.has("resp_status")) {

                                        /* 1784 */
                                        Const.Current_version = 1;
                                        /* 1785 */
                                        status = json.getInt("resp_status");

                                    } else {

                                        /* 1788 */
                                        Const.Current_version = 0;
                                        /* 1789 */
                                        status = json.getInt("status");

                                    }
                                    /* 1791 */
                                    Intent intent = new Intent("BROADCAST_GET_IQ_VERSION");
                                    /* 1792 */
                                    intent.putExtra("status", status);


                                    /* 1795 */
                                    MessageService.this.sendBroadcast(intent);

                                    /* 1797 */
                                    Log.d("Allen", "iqversion =" + json.toString());

                                } else if (cmd_type.equals("zx_snapshot")) {
                                    int status = json.getInt("status");
                                    Intent intent = new Intent(Const.SNAPSHOT);
                                    intent.putExtra("status", status);
                                    sendBroadcast(intent);
                                }

                                /* 1800 */
                            } catch (JSONException e1) {
                                /* 1801 */
                                e1.printStackTrace();
                                /* 1829 */
                                if (MessageService.this.isGetList) {
                                    /* 1830 */
                                    MessageService.this.listlock.lock();
                                    /* 1831 */
                                    if (MessageService.this.isGetListFirst) {
                                        /* 1832 */
                                        if (MessageService.this.output != null) {
                                            /* 1833 */
                                            MessageService.this.output.close();
                                            /* 1834 */
                                            MessageService.this.output = null;

                                        }
                                        /* 1836 */
                                        File mFilePath = new File(Const.download_path);
                                        /* 1837 */
                                        if (!mFilePath.exists()) {
                                            /* 1838 */
                                            mFilePath.mkdirs();

                                        }
                                        /* 1840 */
                                        MessageService.this.output = new FileOutputStream(Const.file_list_path);

                                        /* 1842 */
                                        MessageService.this.isGetListFirst = false;
                                        /* 1843 */
                                        MessageService.this.gl_file_size = 0;
                                        /* 1844 */
                                        MessageService.this.gl_recv_size = 0;

                                        /* 1846 */
                                        for (int i = 0; i < 4; i++) {
                                            /* 1847 */
                                            int value = resp[i];
                                            /* 1848 */
                                            if (value < 0)
                                                /* 1849 */ value += 256;
                                            /* 1850 */
                                            MessageService.this.gl_file_size = (int) (MessageService.this.gl_file_size + value * Math.pow(256.0D, (3 - i)));

                                        }

                                        /* 1853 */
                                        MessageService.this.gl_recv_size = MessageService.this.gl_recv_size + resp.length - 4;

                                        /* 1855 */
                                        byte[] data = new byte[resp.length - 4];
                                        /* 1856 */
                                        System.arraycopy(resp, 4, data, 0, resp.length - 4);
                                        /* 1857 */
                                        MessageService.this.writeToFile(data);
                                        /* 1858 */
                                        if (MessageService.this.gl_recv_size == MessageService.this.gl_file_size) {
                                            /* 1859 */
                                            MessageService.this.isGetList = false;
                                            /* 1860 */
                                            Intent intent = new Intent("BROADCAST_GET_VIDEO_LIST");
                                            /* 1861 */
                                            intent.putExtra("status", 68864);

                                            /* 1863 */
                                            MessageService.this.sendBroadcast(intent);

                                        }

                                        try {
                                            /* 1866 */
                                            sleep(50L);
                                            /* 1867 */
                                        } catch (InterruptedException e) {
                                            /* 1868 */
                                            e.printStackTrace();

                                        }

                                    } else {
                                        /* 1871 */
                                        MessageService.this.gl_recv_size = MessageService.this.gl_recv_size + resp.length;

                                        /* 1873 */
                                        MessageService.this.writeToFile(resp);
                                        /* 1874 */
                                        if (MessageService.this.gl_recv_size == MessageService.this.gl_file_size) {
                                            /* 1875 */
                                            MessageService.this.isGetList = false;
                                            /* 1876 */
                                            Intent intent = new Intent("BROADCAST_GET_VIDEO_LIST");
                                            /* 1877 */
                                            intent.putExtra("status", 68864);
                                            /* 1878 */
                                            MessageService.this.sendBroadcast(intent);

                                        }

                                    }


                                    /* 1881 */
                                    MessageService.this.listlock.unlock();

                                }
                                /* 1883 */
                            } catch (Exception exception) {
                            }

                        }



                        /* 1888 */
                        MessageService.this.isConnected = false;
                        /* 1889 */
                        MessageService.this.isRunning = false;

                        /* 1891 */
                        if (socket != null) {
                            /* 1892 */
                            socket.close();
                            /* 1893 */
                            socket = null;

                        }
                        /* 1895 */
                    } catch (IOException e) {
                        /* 1896 */
                        e.printStackTrace();
                        /* 1897 */
                        MessageService.this.isConnected = false;
                        /* 1898 */
                        MessageService.this.isRunning = false;
                        /* 1899 */
                        if (socket != null) {

                            try {
                                /* 1901 */
                                socket.close();
                                /* 1902 */
                            } catch (IOException e1) {

                                /* 1904 */
                                e1.printStackTrace();

                            }
                            /* 1906 */
                            socket = null;

                        }

                    }

                }

                /* 1911 */
                if (MessageService.this.output != null) {

                    try {
                        /* 1913 */
                        MessageService.this.output.close();
                        /* 1914 */
                    } catch (IOException e) {
                        /* 1915 */
                        e.printStackTrace();

                    }

                }

            }

        };
        /* 1920 */
        this.recv.start();

    }


    public void close() {
        /* 1924 */
        this.isRunning = false;

        /* 1926 */
        if (socket != null) {

            try {
                /* 1928 */
                socket.close();
                /* 1929 */
            } catch (IOException e) {
                /* 1930 */
                e.printStackTrace();
                /* 1931 */
            } catch (Exception exception) {
            }

        }


        /* 1935 */
        socket = null;

    }


    public class ServiceBinder
            extends Binder {
        /* 1941 */
        public Service getService() {
            return MessageService.this;
        }

    }


    public void setDownloadFile(String file, boolean isContinueDownload) {
        /* 1946 */
        this.filename = file;
        /* 1947 */
        this.gl_isDownloadcontinue = isContinueDownload;
        /* 1948 */
        if (!isContinueDownload) {
            /* 1949 */
            this.gl_record_recv_size = 0L;

        }

    }


    /* 1954 */
    public void setDownloadFileType(int type) {
        this.filetype = type;
    }


    /* 1957 */ long recv_size = 0L;


    private void respDownloadFile_FG(final byte[] resp, final String filename, final int downloadtype) {
        /* 1963 */
        Thread t = new Thread() {

            public void run() {
                /* 1965 */
                int tag = 0;


                try {
                    /* 1968 */
                    JSONObject obj = new JSONObject(new String(resp));
                    /* 1969 */
                    int status = obj.getInt("resp_status");

                    /* 1971 */
                    int port = MessageService.this.gl_port_FG;
                    /* 1972 */
                    if (obj.has("tag")) {
                        /* 1973 */
                        tag = obj.getInt("tag");

                    }


                    /* 1977 */
                    if (MessageToast.isCommandSuccess(status)) {
                        /* 1979 */
                        Log.d("Allen", "respDownloadFile 111111 gl_port_FG=" + MessageService.this.gl_port_FG);
                        /* 1980 */
                        if (MessageService.this.mSocket_FG == null || !MessageService.this.mSocket_FG.isConnected()) {
                            /* 1981 */
                            MessageService.this.mSocket_FG = new Socket(Const.CURRENT_DEVICE_IP, port);
                            /* 1982 */
                            MessageService.this.mSocket_FG.setTcpNoDelay(true);
                            /* 1983 */
                            MessageService.this.mSocket_FG.setReceiveBufferSize(65024);
                            /* 1984 */
                            MessageService.this.mSocket_FG.setSoTimeout(30000);

                        }
                        /* 1986 */
                        InputStream mInput = MessageService.this.mSocket_FG.getInputStream();

                        /* 1988 */
                        byte[] recvBuf = new byte[64000];
                        /* 1989 */
                        int num = 0;
                        /* 1990 */
                        Log.d("Allen", "download000 eeeee");
                        /* 1991 */
                        MessageService.this.recv_size = 0L;
                        /* 1992 */
                        long file_size = 0L;
                        /* 1993 */
                        boolean first = true;

                        /* 1995 */
                        int tmpPercent = 0;
                        /* 1996 */
                        int nowPercent = 0;
                        /* 1997 */
                        while ((num = mInput.read(recvBuf)) != -1) {

                            /* 1999 */
                            int offset = 0;

                            /* 2001 */
                            if (first) {

                                /* 2003 */
                                first = false;
                                /* 2004 */
                                for (int i = 0; i < num; i++) {
                                    /* 2005 */
                                    int value = recvBuf[i];
                                    /* 2006 */
                                    if (value < 0)
                                        /* 2007 */ value += 256;
                                    /* 2008 */
                                    file_size = (long) (file_size + value * Math.pow(256.0D, (3 - i)));

                                }

                                /* 2011 */
                                if (file_size == 0L)
                                    break;
                                /* 2013 */
                                if (MessageService.this.gl_isDownloadcontinue) {
                                    /* 2015 */
                                    MessageService.this.recv_size += MessageService.this.gl_record_recv_size;

                                }
                                /* 2017 */
                                if (num == 4) {

                                    continue;

                                }
                                /* 2020 */
                                offset = 4;

                            }


                            /* 2024 */
                            byte[] data = new byte[num - offset];
                            /* 2025 */
                            Log.d("Allen", "file_size =" + file_size);
                            /* 2026 */
                            System.arraycopy(recvBuf, offset, data, 0, num - offset);

                            /* 2028 */
                            MessageService.this.recv_size += (num - offset);

                            /* 2030 */
                            if (filename.contains("jpg")) {

                                /* 2032 */
                                if (MessageService.this.recv_size >= file_size) {
                                    /* 2033 */
                                    int index = 0;
                                    /* 2034 */
                                    for (int i = data.length - 1; i > 0; i--) {
                                        /* 2035 */
                                        if (data[i] == -39 &&
                                                /* 2036 */                         data[i - 1] == -1) {
                                            /* 2037 */
                                            index = i;


                                            break;

                                        }

                                    }

                                    /* 2044 */
                                    MessageService.this.writeToFile(data, data.length - data.length - 1 - index);

                                } else {
                                    /* 2046 */
                                    MessageService.this.writeToFile(data);

                                }

                            } else {
                                /* 2049 */
                                MessageService.this.writeToFile(data);

                            }

                            /* 2052 */
                            nowPercent = MessageService.this.percent(MessageService.this.recv_size, file_size);

                            /* 2054 */
                            if (tmpPercent != nowPercent) {


                                /* 2057 */
                                tmpPercent = nowPercent;
                                /* 2058 */
                                Intent i = new Intent("broadcast_download_percent");
                                /* 2059 */
                                i.putExtra("percent", tmpPercent);
                                /* 2060 */
                                MessageService.this.sendBroadcast(i);

                            }


                            /* 2064 */
                            if (MessageService.this.recv_size >= file_size) {

                                break;

                            }

                        }

                        /* 2069 */
                        if (MessageService.this.recv_size >= file_size) {
                            /* 2071 */
                            MessageService.this.gl_isDownloadcontinue = false;
                            /* 2072 */
                            MessageService.this.gl_currentDownFileName = "";
                            /* 2073 */
                            Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                            /* 2074 */
                            intent.putExtra("filename", filename);
                            /* 2075 */
                            intent.putExtra("tag", tag);

                            /* 2077 */
                            intent.putExtra("status", 69376);
                            /* 2078 */
                            MessageService.this.gl_record_recv_size = 0L;

                            /* 2080 */
                            MessageService.this.sendBroadcast(intent);

                        } else {
                            /* 2084 */
                            if (downloadtype == 2) {
                                /* 2085 */
                                MessageService.this.deleteLocalPic(filename);

                            }
                            /* 2087 */
                            Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                            /* 2088 */
                            intent.putExtra("filename", filename);
                            /* 2089 */
                            intent.putExtra("status", 1110033);
                            /* 2090 */
                            intent.putExtra("tag", tag);
                            /* 2091 */
                            intent.putExtra("pos", MessageService.this.recv_size);

                            /* 2093 */
                            MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                            /* 2094 */
                            MessageService.this.sendBroadcast(intent);


                        }


                    }


                }
                /* 2102 */ catch (JSONException e) {

                    /* 2104 */
                    if (downloadtype == 2) {
                        /* 2105 */
                        MessageService.this.deleteLocalPic(filename);

                    }

                    /* 2108 */
                    e.printStackTrace();
                    /* 2109 */
                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                    /* 2110 */
                    intent.putExtra("filename", filename);
                    /* 2111 */
                    intent.putExtra("status", 1110033);
                    /* 2112 */
                    intent.putExtra("pos", MessageService.this.recv_size);
                    /* 2113 */
                    intent.putExtra("tag", tag);
                    /* 2114 */
                    MessageService.this.gl_record_recv_size = MessageService.this.recv_size;

                    /* 2116 */
                    MessageService.this.sendBroadcast(intent);


                }
                /* 2127 */ catch (IOException e) {

                    /* 2129 */
                    if (downloadtype == 2) {
                        /* 2130 */
                        MessageService.this.deleteLocalPic(filename);

                    }
                    /* 2132 */
                    e.printStackTrace();
                    /* 2133 */
                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                    /* 2134 */
                    intent.putExtra("filename", filename);

                    /* 2136 */
                    intent.putExtra("status", 1110033);
                    /* 2137 */
                    intent.putExtra("pos", MessageService.this.recv_size);
                    /* 2138 */
                    intent.putExtra("tag", tag);
                    /* 2139 */
                    MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                    /* 2140 */
                    MessageService.this.sendBroadcast(intent);


                }
                /* 2150 */ catch (Exception e) {


                    /* 2153 */
                    if (downloadtype == 2) {
                        /* 2154 */
                        MessageService.this.deleteLocalPic(filename);

                    }
                    /* 2156 */
                    e.printStackTrace();

                    /* 2158 */
                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                    /* 2159 */
                    intent.putExtra("filename", filename);
                    /* 2160 */
                    intent.putExtra("tag", tag);
                    /* 2161 */
                    intent.putExtra("status", 1110033);
                    /* 2162 */
                    intent.putExtra("pos", MessageService.this.recv_size);
                    /* 2163 */
                    MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                    /* 2164 */
                    MessageService.this.sendBroadcast(intent);

                }









                /* 2175 */
                if (MessageService.this.output != null) {

                    try {
                        /* 2177 */
                        Log.d("Allen", "downloadfile_res out put close");
                        /* 2178 */
                        MessageService.this.output.close();
                        /* 2179 */
                    } catch (IOException e) {

                        /* 2181 */
                        e.printStackTrace();

                    }

                }

            }

        };
        /* 2186 */
        t.start();

    }


    private void respDownloadFile_BG(final byte[] resp, final String filename, final int downloadtype) {
        /* 2192 */
        Thread t = new Thread() {

            public void run() {
                /* 2194 */
                int tag = 0;


                try {
                    /* 2197 */
                    JSONObject obj = new JSONObject(new String(resp));
                    /* 2198 */
                    int status = obj.getInt("resp_status");

                    /* 2200 */
                    int port = MessageService.this.gl_port_BG;
                    /* 2201 */
                    if (obj.has("tag")) {
                        /* 2202 */
                        tag = obj.getInt("tag");

                    }


                    /* 2206 */
                    if (MessageToast.isCommandSuccess(status)) {
                        /* 2208 */
                        Log.d("Allen", "respDownloadFile 111111");
                        /* 2209 */
                        if (MessageService.this.mSocket_BG == null || !MessageService.this.mSocket_BG.isConnected()) {

                            /* 2211 */
                            MessageService.this.mSocket_BG = new Socket(Const.CURRENT_DEVICE_IP, port);
                            /* 2212 */
                            MessageService.this.mSocket_BG.setTcpNoDelay(true);
                            /* 2213 */
                            MessageService.this.mSocket_BG.setReceiveBufferSize(65024);
                            /* 2214 */
                            MessageService.this.mSocket_BG.setSoTimeout(30000);

                        }
                        /* 2216 */
                        InputStream mInput = MessageService.this.mSocket_BG.getInputStream();

                        /* 2218 */
                        byte[] recvBuf = new byte[64000];
                        /* 2219 */
                        int num = 0;

                        /* 2221 */
                        MessageService.this.recv_size = 0L;
                        /* 2222 */
                        long file_size = 0L;
                        /* 2223 */
                        boolean first = true;

                        /* 2225 */
                        int tmpPercent = 0;
                        /* 2226 */
                        int nowPercent = 0;
                        /* 2227 */
                        while ((num = mInput.read(recvBuf)) != -1) {

                            /* 2229 */
                            int offset = 0;

                            /* 2231 */
                            if (first) {

                                /* 2233 */
                                first = false;
                                /* 2234 */
                                for (int i = 0; i < num; i++) {
                                    /* 2235 */
                                    int value = recvBuf[i];
                                    /* 2236 */
                                    if (value < 0)
                                        /* 2237 */ value += 256;
                                    /* 2238 */
                                    file_size = (long) (file_size + value * Math.pow(256.0D, (3 - i)));

                                }

                                /* 2241 */
                                if (file_size == 0L)
                                    break;
                                /* 2243 */
                                if (MessageService.this.gl_isDownloadcontinue) {
                                    /* 2245 */
                                    MessageService.this.recv_size += MessageService.this.gl_record_recv_size;

                                }
                                /* 2247 */
                                if (num == 4) {

                                    continue;

                                }
                                /* 2250 */
                                offset = 4;

                            }


                            /* 2254 */
                            byte[] data = new byte[num - offset];

                            /* 2256 */
                            System.arraycopy(recvBuf, offset, data, 0, num - offset);

                            /* 2258 */
                            MessageService.this.recv_size += (num - offset);

                            /* 2260 */
                            if (filename.contains("jpg")) {

                                /* 2262 */
                                if (MessageService.this.recv_size >= file_size) {
                                    /* 2263 */
                                    int index = 0;
                                    /* 2264 */
                                    for (int i = data.length - 1; i > 0; i--) {
                                        /* 2265 */
                                        if (data[i] == -39 &&
                                                /* 2266 */                         data[i - 1] == -1) {
                                            /* 2267 */
                                            index = i;


                                            break;

                                        }

                                    }

                                    /* 2274 */
                                    MessageService.this.writeToFile(data, data.length - data.length - 1 - index);

                                } else {
                                    /* 2276 */
                                    MessageService.this.writeToFile(data);

                                }

                            } else {
                                /* 2279 */
                                MessageService.this.writeToFile(data);

                            }

                            /* 2282 */
                            nowPercent = MessageService.this.percent(MessageService.this.recv_size, file_size);

                            /* 2284 */
                            if (tmpPercent != nowPercent) {


                                /* 2287 */
                                tmpPercent = nowPercent;
                                /* 2288 */
                                Intent i = new Intent("broadcast_download_percent");
                                /* 2289 */
                                i.putExtra("percent", tmpPercent);
                                /* 2290 */
                                MessageService.this.sendBroadcast(i);

                            }


                            /* 2294 */
                            if (MessageService.this.recv_size >= file_size) {

                                break;

                            }

                        }

                        /* 2299 */
                        if (MessageService.this.recv_size >= file_size) {
                            /* 2301 */
                            MessageService.this.gl_isDownloadcontinue = false;
                            /* 2302 */
                            Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                            /* 2303 */
                            intent.putExtra("filename", filename);
                            /* 2304 */
                            intent.putExtra("tag", tag);

                            /* 2306 */
                            intent.putExtra("status", 69376);
                            /* 2307 */
                            MessageService.this.gl_record_recv_size = 0L;

                            /* 2309 */
                            MessageService.this.sendBroadcast(intent);

                        } else {
                            /* 2313 */
                            if (downloadtype == 2) {
                                /* 2314 */
                                MessageService.this.deleteLocalPic(filename);

                            }
                            /* 2316 */
                            Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                            /* 2317 */
                            intent.putExtra("filename", filename);
                            /* 2318 */
                            intent.putExtra("status", 1110033);
                            /* 2319 */
                            intent.putExtra("tag", tag);
                            /* 2320 */
                            intent.putExtra("pos", MessageService.this.recv_size);

                            /* 2322 */
                            MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                            /* 2323 */
                            MessageService.this.sendBroadcast(intent);


                        }


                    }


                } catch (JSONException e) {

                    /* 2333 */
                    if (downloadtype == 2) {
                        /* 2334 */
                        MessageService.this.deleteLocalPic(filename);

                    }

                    /* 2337 */
                    e.printStackTrace();
                    /* 2338 */
                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                    /* 2339 */
                    intent.putExtra("filename", filename);
                    /* 2340 */
                    intent.putExtra("status", 1110033);
                    /* 2341 */
                    intent.putExtra("pos", MessageService.this.recv_size);
                    /* 2342 */
                    intent.putExtra("tag", tag);
                    /* 2343 */
                    MessageService.this.gl_record_recv_size = MessageService.this.recv_size;

                    /* 2345 */
                    MessageService.this.sendBroadcast(intent);


                }
                /* 2356 */ catch (IOException e) {

                    /* 2358 */
                    if (downloadtype == 2) {
                        /* 2359 */
                        MessageService.this.deleteLocalPic(filename);

                    }
                    /* 2361 */
                    e.printStackTrace();
                    /* 2362 */
                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                    /* 2363 */
                    intent.putExtra("filename", filename);

                    /* 2365 */
                    intent.putExtra("status", 1110033);
                    /* 2366 */
                    intent.putExtra("pos", MessageService.this.recv_size);
                    /* 2367 */
                    intent.putExtra("tag", tag);
                    /* 2368 */
                    MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                    /* 2369 */
                    MessageService.this.sendBroadcast(intent);


                }
                /* 2379 */ catch (Exception e) {


                    /* 2382 */
                    if (downloadtype == 2) {
                        /* 2383 */
                        MessageService.this.deleteLocalPic(filename);

                    }
                    /* 2385 */
                    e.printStackTrace();

                    /* 2387 */
                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                    /* 2388 */
                    intent.putExtra("filename", filename);
                    /* 2389 */
                    intent.putExtra("tag", tag);
                    /* 2390 */
                    intent.putExtra("status", 1110033);
                    /* 2391 */
                    intent.putExtra("pos", MessageService.this.recv_size);
                    /* 2392 */
                    MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                    /* 2393 */
                    MessageService.this.sendBroadcast(intent);

                }









                /* 2404 */
                if (MessageService.this.output != null) {

                    try {
                        /* 2406 */
                        MessageService.this.output.close();
                        /* 2407 */
                    } catch (IOException e) {

                        /* 2409 */
                        e.printStackTrace();

                    }

                }

            }

        };
        /* 2414 */
        t.start();

    }


    private void respDownloadFile(final byte[] resp, final String filename, final int downloadtype) {
        /* 2419 */
        Thread t = new Thread() {

            public void run() {
                /* 2421 */
                int tag = 0;
                /* 2422 */
                Socket mSocket = null;

                try {
                    /* 2424 */
                    JSONObject obj = new JSONObject(new String(resp));
                    /* 2425 */
                    int status = obj.getInt("status");
                    /* 2426 */
                    int port = obj.getInt("port");

                    /* 2428 */
                    if (obj.has("tag")) {
                        /* 2429 */
                        tag = obj.getInt("tag");

                    }


                    /* 2433 */
                    if (MessageToast.isCommandSuccess(status)) {
                        /* 2434 */
                        mSocket = new Socket(Const.CURRENT_DEVICE_IP, port);
                        /* 2435 */
                        mSocket.setTcpNoDelay(true);
                        /* 2436 */
                        mSocket.setReceiveBufferSize(65024);
                        /* 2437 */
                        mSocket.setSoTimeout(30000);

                        /* 2439 */
                        InputStream mInput = mSocket.getInputStream();

                        /* 2441 */
                        byte[] recvBuf = new byte[64000];
                        /* 2442 */
                        int num = 0;

                        /* 2444 */
                        MessageService.this.recv_size = 0L;
                        /* 2445 */
                        long file_size = 0L;
                        /* 2446 */
                        boolean first = true;

                        /* 2448 */
                        int tmpPercent = 0;
                        /* 2449 */
                        int nowPercent = 0;
                        /* 2450 */
                        boolean isEnough = true;
                        /* 2451 */
                        while ((num = mInput.read(recvBuf)) != -1) {
                            /* 2452 */
                            int offset = 0;
                            /* 2453 */
                            if (first) {

                                /* 2455 */
                                first = false;
                                /* 2456 */
                                for (int i = 0; i < num; i++) {
                                    /* 2457 */
                                    int value = recvBuf[i];
                                    /* 2458 */
                                    if (value < 0)
                                        /* 2459 */ value += 256;
                                    /* 2460 */
                                    file_size = (long) (file_size + value * Math.pow(256.0D, (3 - i)));

                                }

                                /* 2463 */
                                if (file_size == 0L)
                                    break;
                                /* 2465 */
                                if (MessageService.this.gl_isDownloadcontinue) {
                                    /* 2467 */
                                    MessageService.this.recv_size += MessageService.this.gl_record_recv_size;

                                }

                                /* 2470 */
                                if (file_size > MessageService.this.getSDCardFreeSize()) {
                                    /* 2471 */
                                    isEnough = false;
                                    /* 2472 */
                                    Intent intent = new Intent("BROADCAST_SD_CARD_NO_SPACE");
                                    /* 2473 */
                                    intent.putExtra("space", file_size);
                                    /* 2474 */
                                    MessageService.this.sendBroadcast(intent);

                                    break;

                                }
                                /* 2477 */
                                if (num == 4) {

                                    continue;

                                }
                                /* 2480 */
                                offset = 4;

                            }



                            /* 2485 */
                            byte[] data = new byte[num - offset];
                            /* 2486 */
                            System.arraycopy(recvBuf, offset, data, 0, num - offset);
                            /* 2487 */
                            MessageService.this.recv_size += (num - offset);

                            /* 2489 */
                            if (filename.contains("jpg")) {
                                /* 2490 */
                                if (MessageService.this.recv_size >= file_size) {
                                    /* 2491 */
                                    int index = 0;
                                    /* 2492 */
                                    for (int i = data.length - 1; i > 0; i--) {
                                        /* 2493 */
                                        if (data[i] == -39 &&
                                                /* 2494 */                         data[i - 1] == -1) {
                                            /* 2495 */
                                            index = i;
                                            /* 2496 */
                                            Log.d("Allen", "cut zero index= " + index);


                                            break;

                                        }

                                    }

                                    /* 2502 */
                                    MessageService.this.writeToFile(data, data.length - data.length - 1 - index);

                                } else {
                                    /* 2504 */
                                    MessageService.this.writeToFile(data);

                                }

                            } else {
                                /* 2507 */
                                MessageService.this.writeToFile(data);

                            }


                            /* 2511 */
                            nowPercent = MessageService.this.percent(MessageService.this.recv_size, file_size);

                            /* 2513 */
                            if (tmpPercent != nowPercent) {


                                /* 2516 */
                                tmpPercent = nowPercent;
                                /* 2517 */
                                Intent i = new Intent("broadcast_download_percent");
                                /* 2518 */
                                i.putExtra("percent", tmpPercent);
                                /* 2519 */
                                MessageService.this.sendBroadcast(i);

                            }


                            /* 2523 */
                            if (MessageService.this.recv_size >= file_size) {

                                break;

                            }

                        }

                        /* 2528 */
                        if (isEnough) {
                            /* 2529 */
                            if (MessageService.this.recv_size == file_size) {


                                /* 2532 */
                                MessageService.this.gl_isDownloadcontinue = false;
                                /* 2533 */
                                Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                                /* 2534 */
                                intent.putExtra("filename", filename);
                                /* 2535 */
                                intent.putExtra("tag", tag);

                                /* 2537 */
                                intent.putExtra("status", 69376);
                                /* 2538 */
                                MessageService.this.gl_record_recv_size = 0L;
                                /* 2539 */
                                MessageService.this.sendBroadcast(intent);

                            } else {

                                /* 2543 */
                                if (downloadtype == 2) {
                                    /* 2544 */
                                    MessageService.this.deleteLocalPic(filename);

                                }
                                /* 2546 */
                                Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                                /* 2547 */
                                intent.putExtra("filename", filename);
                                /* 2548 */
                                intent.putExtra("status", 1110033);
                                /* 2549 */
                                intent.putExtra("tag", tag);
                                /* 2550 */
                                intent.putExtra("pos", MessageService.this.recv_size);
                                /* 2551 */
                                MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                                /* 2552 */
                                MessageService.this.sendBroadcast(intent);

                            }

                        }
                        /* 2555 */
                        mInput.close();
                        /* 2556 */
                        mSocket.close();
                        /* 2557 */
                        mSocket = null;

                    }
                    /* 2559 */
                } catch (JSONException e) {

                    /* 2561 */
                    if (downloadtype == 2) {
                        /* 2562 */
                        MessageService.this.deleteLocalPic(filename);

                    }

                    /* 2565 */
                    e.printStackTrace();
                    /* 2566 */
                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                    /* 2567 */
                    intent.putExtra("filename", filename);
                    /* 2568 */
                    intent.putExtra("status", 1110033);
                    /* 2569 */
                    intent.putExtra("pos", MessageService.this.recv_size);
                    /* 2570 */
                    intent.putExtra("tag", tag);
                    /* 2571 */
                    MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                    /* 2572 */
                    MessageService.this.sendBroadcast(intent);

                    try {
                        /* 2574 */
                        mSocket.close();
                        /* 2575 */
                    } catch (IOException e1) {
                        /* 2576 */
                        e1.printStackTrace();
                        /* 2577 */
                    } catch (Exception exception) {
                        /* 2578 */
                        exception.printStackTrace();

                    }
                    /* 2580 */
                    mSocket = null;
                    /* 2581 */
                } catch (IOException e) {

                    /* 2583 */
                    if (downloadtype == 2) {
                        /* 2584 */
                        MessageService.this.deleteLocalPic(filename);

                    }
                    /* 2586 */
                    e.printStackTrace();
                    /* 2587 */
                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                    /* 2588 */
                    intent.putExtra("filename", filename);

                    /* 2590 */
                    intent.putExtra("status", 1110033);
                    /* 2591 */
                    intent.putExtra("pos", MessageService.this.recv_size);
                    /* 2592 */
                    intent.putExtra("tag", tag);
                    /* 2593 */
                    MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                    /* 2594 */
                    MessageService.this.sendBroadcast(intent);

                    try {
                        /* 2596 */
                        if (mSocket != null)
                            /* 2597 */ mSocket.close();
                        /* 2598 */
                    } catch (IOException e1) {
                        /* 2599 */
                        e1.printStackTrace();
                        /* 2600 */
                    } catch (Exception e1) {
                        /* 2601 */
                        e1.printStackTrace();

                    }
                    /* 2603 */
                    mSocket = null;
                    /* 2604 */
                } catch (Exception e) {

                    /* 2606 */
                    if (downloadtype == 2) {
                        /* 2607 */
                        MessageService.this.deleteLocalPic(filename);

                    }
                    /* 2609 */
                    e.printStackTrace();
                    /* 2610 */
                    Intent intent = new Intent("BROADCAST_DOWNLOAD_FILE");
                    /* 2611 */
                    intent.putExtra("filename", filename);
                    /* 2612 */
                    intent.putExtra("tag", tag);
                    /* 2613 */
                    intent.putExtra("status", 1110033);
                    /* 2614 */
                    intent.putExtra("pos", MessageService.this.recv_size);
                    /* 2615 */
                    MessageService.this.gl_record_recv_size = MessageService.this.recv_size;
                    /* 2616 */
                    MessageService.this.sendBroadcast(intent);

                    try {
                        /* 2618 */
                        mSocket.close();
                        /* 2619 */
                    } catch (IOException e1) {
                        /* 2620 */
                        e1.printStackTrace();
                        /* 2621 */
                    } catch (Exception exception) {
                        /* 2622 */
                        exception.printStackTrace();

                    }
                    /* 2624 */
                    mSocket = null;

                }
                /* 2626 */
                if (MessageService.this.output != null) {

                    try {
                        /* 2628 */
                        MessageService.this.output.close();
                        /* 2629 */
                    } catch (IOException e) {
                        /* 2630 */
                        e.printStackTrace();

                    }

                }

            }

        };
        /* 2635 */
        t.start();

    }


    public void deleteRecordFile(String pathDir) {
        /* 2641 */
        String filePath = this.gl_currentDownFileName;
        /* 2642 */
        Log.d("Allen", "gl_currentDownloadFile =" + this.gl_currentDownFileName);

        /* 2644 */
        File file = new File(filePath);
        /* 2645 */
        if (!file.exists()) {

            return;

        }

        /* 2649 */
        file.delete();
        /* 2650 */
        File fPic = null;
        /* 2651 */
        if (this.gl_currentDownFileName.contains(".avi")) {

            /* 2653 */
            fPic = new File(this.gl_currentDownFileName.replace(".avi", ".jpg").replace("/record/", "/" + pathDir + "/thumbnail/"));


        }
        /* 2656 */
        else if (this.gl_currentDownFileName.contains(".mp4")) {
            /* 2657 */
            fPic = new File(this.gl_currentDownFileName.replace(".mp4", ".jpg").replace("/record/", "/" + pathDir + "/thumbnail/"));

        }


        /* 2661 */
        if (fPic != null && fPic.exists()) {

            /* 2663 */
            fPic.delete();
            /* 2664 */
            Log.d("Allen", "deleteRecordFile aaaaa");

        }

    }


    public void deleteLocalPic(String filename) {
        /* 2682 */
        String filePath = Const.download_path + "/picture/" + filename;
        /* 2683 */
        File file = new File(filePath);
        /* 2684 */
        if (!file.exists()) {

            return;

        }
        /* 2687 */
        file.delete();

    }


    private void respGetIndexFile1_0(final byte[] resp) {
        /* 2691 */
        Thread t = new Thread() {

            public void run() {

                try {
                    /* 2695 */
                    JSONObject obj = new JSONObject(new String(resp));
                    /* 2696 */
                    int status = obj.getInt("status");
                    /* 2697 */
                    int port = obj.getInt("port");
                    /* 2698 */
                    if (status == 69120) {
                        /* 2699 */
                        Socket mSocket = new Socket("192.168.99.1", port);
                        /* 2700 */
                        mSocket.setSoTimeout(10000);
                        /* 2701 */
                        mSocket.setTcpNoDelay(true);
                        /* 2702 */
                        InputStream mInput = mSocket.getInputStream();
                        /* 2703 */
                        byte[] recvBuf = new byte[64000];

                        /* 2705 */
                        int num = 0;
                        /* 2706 */
                        long recv_size = 0L;
                        /* 2707 */
                        long file_size = 0L;
                        /* 2708 */
                        boolean first = true;

                        /* 2710 */
                        while ((num = mInput.read(recvBuf)) != -1) {

                            /* 2712 */
                            int offset = 0;
                            /* 2713 */
                            if (first) {
                                /* 2714 */
                                first = false;
                                /* 2715 */
                                for (int i = 0; i < num; i++) {
                                    /* 2716 */
                                    int value = recvBuf[i];
                                    /* 2717 */
                                    if (value < 0)
                                        /* 2718 */ value += 256;
                                    /* 2719 */
                                    file_size = (long) (file_size + value * Math.pow(256.0D, (3 - i)));

                                }


                                /* 2723 */
                                if (file_size == 0L) {

                                    break;

                                }


                                /* 2728 */
                                if (num == 4) {

                                    continue;

                                }
                                /* 2731 */
                                offset = 4;

                            }


                            /* 2735 */
                            byte[] data = new byte[num - offset];
                            /* 2736 */
                            System.arraycopy(recvBuf, offset, data, 0, num - offset);
                            /* 2737 */
                            recv_size += (num - offset);

                            /* 2739 */
                            MessageService.this.writeToFile(data);

                            /* 2741 */
                            if (recv_size == file_size) {

                                break;

                            }

                        }


                        /* 2747 */
                        if (recv_size >= file_size) {
                            /* 2748 */
                            Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                            /* 2749 */
                            intent.putExtra("status", 69120);

                            /* 2751 */
                            if (obj.has("sdisfull"))
                                /* 2752 */ intent.putExtra("sdisfull", obj.getInt("sdisfull"));
                            /* 2753 */
                            MessageService.this.sendBroadcast(intent);

                        } else {
                            /* 2755 */
                            Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                            /* 2756 */
                            intent.putExtra("status", 69121);
                            /* 2757 */
                            if (obj.has("sdisfull"))
                                /* 2758 */ intent.putExtra("sdisfull", obj.getInt("sdisfull"));
                            /* 2759 */
                            MessageService.this.sendBroadcast(intent);

                        }
                        /* 2761 */
                    } else if (status == 69121) {
                        /* 2762 */
                        Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                        /* 2763 */
                        intent.putExtra("status", 69121);

                        /* 2765 */
                        MessageService.this.sendBroadcast(intent);

                    }
                    /* 2767 */
                } catch (JSONException e) {

                    /* 2769 */
                    e.printStackTrace();
                    /* 2770 */
                    Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                    /* 2771 */
                    intent.putExtra("status", 69121);
                    /* 2772 */
                    MessageService.this.sendBroadcast(intent);
                    /* 2773 */
                } catch (IOException e) {

                    /* 2775 */
                    e.printStackTrace();
                    /* 2776 */
                    Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                    /* 2777 */
                    intent.putExtra("status", 69121);
                    /* 2778 */
                    MessageService.this.sendBroadcast(intent);
                    /* 2779 */
                } catch (Exception e) {

                    /* 2781 */
                    e.printStackTrace();
                    /* 2782 */
                    Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                    /* 2783 */
                    intent.putExtra("status", 69121);
                    /* 2784 */
                    MessageService.this.sendBroadcast(intent);

                }
                /* 2786 */
                if (MessageService.this.output != null) {

                    try {
                        /* 2788 */
                        MessageService.this.output.close();
                        /* 2789 */
                    } catch (IOException e) {

                        /* 2791 */
                        e.printStackTrace();

                    }

                }

            }

        };
        /* 2796 */
        t.start();

    }


    private void respGetIndexFile(final byte[] resp) {
        /* 2800 */
        Thread t = new Thread() {

            public void run() {

                try {
                    /* 2804 */
                    JSONObject obj = new JSONObject(new String(resp));
                    /* 2805 */
                    int status = obj.getInt("resp_status");
                    /* 2806 */
                    int port = obj.getInt("port");
                    /* 2807 */
                    if (MessageToast.isCommandSuccess(status)) {

                        /* 2809 */
                        if (MessageService.this.mSocket_FG == null || !MessageService.this.mSocket_FG.isConnected()) {

                            /* 2811 */
                            MessageService.this.mSocket_FG = new Socket(Const.CURRENT_DEVICE_IP, port);
                            /* 2812 */
                            MessageService.this.mSocket_FG.setSoTimeout(10000);
                            /* 2813 */
                            MessageService.this.mSocket_FG.setTcpNoDelay(true);
                            /* 2814 */
                            MessageService.this.gl_port_FG = port;

                        }


                        /* 2818 */
                        InputStream mInput = MessageService.this.mSocket_FG.getInputStream();
                        /* 2819 */
                        byte[] recvBuf = new byte[64000];

                        /* 2821 */
                        int num = 0;
                        /* 2822 */
                        long recv_size = 0L;
                        /* 2823 */
                        long file_size = 0L;
                        /* 2824 */
                        boolean first = true;

                        /* 2826 */
                        while ((num = mInput.read(recvBuf)) != -1) {

                            /* 2828 */
                            int offset = 0;
                            /* 2829 */
                            if (first) {
                                /* 2830 */
                                first = false;
                                /* 2831 */
                                for (int i = 0; i < num; i++) {
                                    /* 2832 */
                                    int value = recvBuf[i];
                                    /* 2833 */
                                    if (value < 0)
                                        /* 2834 */ value += 256;
                                    /* 2835 */
                                    file_size = (long) (file_size + value * Math.pow(256.0D, (3 - i)));

                                }


                                /* 2839 */
                                if (file_size == 0L) {

                                    break;

                                }


                                /* 2844 */
                                if (num == 4) {

                                    continue;

                                }
                                /* 2847 */
                                offset = 4;

                            }




                            /* 2853 */
                            byte[] data = new byte[num - offset];
                            /* 2854 */
                            System.arraycopy(recvBuf, offset, data, 0, num - offset);
                            /* 2855 */
                            recv_size += (num - offset);
                            /* 2856 */
                            Log.d("Allen", "recv_size =" + recv_size);
                            /* 2857 */
                            MessageService.this.writeToFile(data);

                            /* 2859 */
                            if (recv_size == file_size) {

                                break;

                            }

                        }


                        /* 2865 */
                        if (recv_size >= file_size) {
                            /* 2866 */
                            Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                            /* 2867 */
                            intent.putExtra("status", 69120);
                            /* 2868 */
                            Log.d("Allen", "get index file success");
                            /* 2869 */
                            if (obj.has("sdisfull"))
                                /* 2870 */ intent.putExtra("sdisfull", obj.getInt("sdisfull"));
                            /* 2871 */
                            MessageService.this.sendBroadcast(intent);

                        } else {
                            /* 2873 */
                            Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                            /* 2874 */
                            intent.putExtra("status", 69121);
                            /* 2875 */
                            if (obj.has("sdisfull"))
                                /* 2876 */ intent.putExtra("sdisfull", obj.getInt("sdisfull"));
                            /* 2877 */
                            MessageService.this.sendBroadcast(intent);

                        }
                        /* 2879 */
                    } else if (!MessageToast.isCommandSuccess(status)) {


                        /* 2882 */
                        Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                        /* 2883 */
                        intent.putExtra("status", 69121);

                        /* 2885 */
                        MessageService.this.sendBroadcast(intent);

                    }
                    /* 2887 */
                } catch (JSONException e) {

                    /* 2889 */
                    e.printStackTrace();
                    /* 2890 */
                    Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                    /* 2891 */
                    intent.putExtra("status", 69121);
                    /* 2892 */
                    MessageService.this.sendBroadcast(intent);
                    /* 2893 */
                } catch (IOException e) {

                    /* 2895 */
                    e.printStackTrace();
                    /* 2896 */
                    Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                    /* 2897 */
                    intent.putExtra("status", 69121);
                    /* 2898 */
                    MessageService.this.sendBroadcast(intent);
                    /* 2899 */
                } catch (Exception e) {

                    /* 2901 */
                    e.printStackTrace();
                    /* 2902 */
                    Intent intent = new Intent("BROADCAST_GET_INDEX_FILE");
                    /* 2903 */
                    intent.putExtra("status", 69121);
                    /* 2904 */
                    MessageService.this.sendBroadcast(intent);

                }
                /* 2906 */
                if (MessageService.this.output != null) {

                    try {
                        /* 2908 */
                        MessageService.this.output.close();
                        /* 2909 */
                    } catch (IOException e) {

                        /* 2911 */
                        e.printStackTrace();

                    }

                }

            }

        };
        /* 2916 */
        t.start();

    }


    /* 2919 */ FileOutputStream output = null;


    private void writeToFile(byte[] data) {

        try {
            /* 2923 */
            if (this.output == null) {
                /* 2924 */
                File mFilePath = new File(Const.download_path);
                /* 2925 */
                if (!mFilePath.exists()) {
                    /* 2926 */
                    mFilePath.mkdirs();

                }
                /* 2928 */
                this.output = new FileOutputStream(Const.download_path + "/unknow.avi");

            }
            /* 2930 */
            this.output.write(data);
            /* 2931 */
        } catch (FileNotFoundException fileNotFoundException) {

            /* 2933 */
        } catch (IOException iOException) {
        }

    }


    private void writeToFile(byte[] data, int length) {

        try {
            /* 2940 */
            if (this.output == null) {
                /* 2941 */
                File mFilePath = new File(Const.download_path);
                /* 2942 */
                if (!mFilePath.exists()) {
                    /* 2943 */
                    mFilePath.mkdirs();

                }
                /* 2945 */
                this.output = new FileOutputStream(Const.download_path + "/unknow.avi");

            }
            /* 2947 */
            this.output.write(data, 0, length);

        }
        /* 2949 */ catch (FileNotFoundException fileNotFoundException) {

            /* 2951 */
        } catch (IOException iOException) {
        }

    }


    private String getFileSaveName(String name, String type) {
        /* 2957 */
        String strFileName = name;
        /* 2958 */
        File file = new File(name);
        /* 2959 */
        if (file.exists()) {
            /* 2960 */
            for (int i = 1; ; i++) {
                /* 2961 */
                strFileName = name.replace(type, " (" + i + ")" + type);
                /* 2962 */
                File tmpFile = new File(strFileName);
                /* 2963 */
                if (!tmpFile.exists()) {

                    break;

                }

            }

        }
        /* 2968 */
        return strFileName;

    }


    public long getSDCardFreeSize() {
        /* 2972 */
        String sDcString = Environment.getExternalStorageState();
        /* 2973 */
        if (sDcString.equals("mounted")) {
            /* 2974 */
            File pathFile = Environment.getExternalStorageDirectory();
            /* 2975 */
            StatFs statfs = new StatFs(pathFile.getPath());

            /* 2977 */
            long nTotalBlocks = statfs.getBlockCount();
            /* 2978 */
            long nBlocSize = statfs.getBlockSize();

            /* 2980 */
            long nAvailaBlock = statfs.getAvailableBlocks();
            /* 2981 */
            long nFreeBlock = statfs.getFreeBlocks();

            /* 2983 */
            long nSDTotalSize = nTotalBlocks * nBlocSize / 1024L / 1024L;
            /* 2984 */
            return nAvailaBlock * nBlocSize;

        }


        /* 2988 */
        return 0L;

    }

}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\service\MessageService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */