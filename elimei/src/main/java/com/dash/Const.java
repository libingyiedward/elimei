package com.dash;

import android.os.Environment;

public class Const {

    //phone snapshot pic size
    public static final int SNAPSHOT_HD = 3686400;//1280*720*4
    public static final int SNAPSHOT_VGA = 921600;//640*360*4

    //ap2sta flag
    public static boolean RETURN_PREVIEW = false;
    public static boolean RETURN_WIFI_SELECT = false;

    //for playback use
    public static boolean IS_PREVIEW_DISCONNECT_WIFI = true;

    //Version
    public static final int VERSION_1_10 = 0;
    public static final int VERSION_1_20 = 1;
    public static int Current_version = -1;


    public static final String DEVICE_DEFAULT_IP = "192.168.99.1";
    public static int DEVICE_CURRENT_EZSETUP_MODE = -1;
    public static String DevicesPor = "8088";

    // Jni Notify
    public static String CURRENT_DEVICE_IP = DEVICE_DEFAULT_IP;
    public static final int JNI_NOTIFY_SD_STATUS = 0;
    public static final int JNI_NOTIFY_RETRY = 1;
    public static final int JNI_NOTIFY_RESOLUTION_CHANGE = 2;
    public static final int JNI_NOTIFY_REC_RUNNING = 3;
    public static final int JNI_NOTIFY_SD_CAPACITY = 4;
    public static final int JNI_NOTIFY_SD_FORMAT = 5;
    public static final int JNI_NOTIFY_RESET_SURFACE = 6;
    public static final int JNI_NOTIFY_RETURN_EXTRA = 7;
    public static final int JNI_NOTIFY_SNAPSHOT_FINISH = 9;
    public static final int JNI_NOTIFY_CURREENT_RESOLUTION = 10;
    public static final int JNI_NOTIFY_READ_RTSP_DATA_TIMEOUT = 11;
    public static final int JNI_NOTIFY_CREATE_RTSP_SOCKET = 12;
    public static final int JNI_NOTIFY_PACKET_LOST = 13;

    // Database
    public static final String LIBVLC_SN = "SONIX-9yjp-10eb-bu11-76at";
    public static final String FILE_FOLDER = "SMARP_DCam";
    //	public static final String WIFI_PREFIX = "SMARP_";
    public static final String SSID_PREFIX = "SMARP_";
    public static final int DEFAULT_COMMAND_TIMEOUT = 10 * 1000;
    public static final int DEFAULT_DOWNLOAD_TIMEOUT = 30 * 1000;
    public static final int DEFAULT_UPLOAD_FW_TIMEOUT = 10 * 1000;
    public static final String LOG_PATH = Environment.getExternalStorageDirectory() + "/" + FILE_FOLDER + "/Log";
    public static final String file_list_path = Environment.getExternalStorageDirectory() + "/" + FILE_FOLDER + "/file_list.txt";
    public static final String pic_file_index_path = Environment.getExternalStorageDirectory() + "/" + FILE_FOLDER + "/pic_file_index.txt";
    public static final String protect_file_index_path = Environment.getExternalStorageDirectory() + "/" + FILE_FOLDER + "/protect_file_index.txt";

    public static final String download_path = Environment.getExternalStorageDirectory() + "/" + FILE_FOLDER;
    public static final String PHONE_RECORD_PATH = Environment.getExternalStorageDirectory() + "/" + FILE_FOLDER + "/phone_record";
    public static final String PHONE_RECORD_THUMBNAIL_PATH = Environment.getExternalStorageDirectory() + FILE_FOLDER + "/phone_record/thumbnail";
    public static final String DEVICE_VERSION_LIST = "DEVICE_VERSION_LIST";
    // Broadcast
    public static final String BROADCAST_FORMATE_SD_CARD = "BROADCAST_FORMATE_SD_CARD";
    public static final String BROADCAST_DOWNLOAD = "BROADCAST_DOWNLOAD";
    public static final String BROADCAST_DOWNLOAD_SHOW_PICTURE = "BROADCAST_DOWNLOAD_SHOW_PICTURE";
    public static final String BROADCAST_OPEN_LOCAL_FILE = "BROADCAST_OPEN_LOCAL_FILE";
    public static final String BROADCAST_SET_CHANNEL = "BROADCAST_SET_CHANNEL";
    public static final String BROADCAST_SET_PWD = "BROADCAST_SET_PWD";
    public static final String BROADCAST_SET_WDR = "BROADCAST_SET_WDR";
    public static final String BROADCAST_GET_SD_SPACE = "BROADCAST_GET_SD_SPACE";
    public static final String BROADCAST_SET_SD_FORMAT = "BROADCAST_SET_SD_FORMAT";
    public static final String BROADCAST_SET_RECORD_STATUS = "BROADCAST_SET_RECORD_STATUS";
    public static final String BROADCAST_TAKE_PICTURE = "BROADCAST_TAKE_PICTURE";
    public static final String BROADCAST_SYNC_TIME = "BROADCAST_SYNC_TIME";
    public static final String BROADCAST_GET_TIME = "BROADCAST_GET_TIME";
    public static final String BROADCAST_GET_BATTERY_STATUS = "BROADCAST_GET_BATTERY_STATUS";
    public static final String BROADCAST_GET_VIDEO_LIST = "BROADCAST_GET_VIDEO_LIST";
    public static final String BROADCAST_DOWNLOAD_FILE = "BROADCAST_DOWNLOAD_FILE";
    public static final String BROADCAST_DOWNLOAD_FILE_FINISH = "BROADCAST_DOWNLOAD_FILE_FINISH";
    public static final String BROADCAST_GET_INDEX_FILE = "BROADCAST_GET_INDEX_FILE";
    public static final String BROADCAST_DELETE_FILE = "BROADCAST_DELETE_FILE";
    public static final String BROADCAST_STREAM_VIDEO = "BROADCAST_STREAM_VIDEO";
    public static final String BROADCAST_STREAM_VIDEO_FINISH = "BROADCAST_STREAM_VIDEO_FINISH";
    public static final String BROADCAST_GET_VIDEO_STATUS = "BROADCAST_GET_VIDEO_STATUS";
    public static final String BROADCAST_GET_RECORD_STATUS = "BROADCAST_GET_RECORD_STATUS";
    public static final String BROADCAST_GET_DEVICE_PARAMETER = "BROADCAST_GET_DEVICE_PARAMETER";
    public static final String BROADCAST_SEND_FONT_FILE = "BROADCAST_SEND_FONT_FILE";
    public static final String BROADCAST_SEND_FW_FILE = "BROADCAST_SEND_FW_FILE";
    public static final String BROADCAST_SET_MIRROR = "BROADCAST_SET_MIRROR";
    public static final String BROADCAST_SET_FLIP = "BROADCAST_SET_FLIP";
    public static final String BROADCAST_GET_WIFI_SETTING = "BROADCAST_GET_WIFI_SETTING";
    public static final String BROADCAST_SET_WIFI = "BROADCAST_SET_WIFI";
    public static final String BROADCAST_UPGRADE_FW = "BROADCAST_UPGRADE_FW";
    public static final String BROADCAST_SET_LOOP_RECORD = "BROADCAST_SET_LOOP_RECORD";
    public static final String BROADCAST_SET_RECORD_VOLUMN = "BROADCAST_SET_RECORD_VOLUMN";
    public static final String BROADCAST_SET_RECORD_LENGTH = "BROADCAST_SET_RECORD_LENGTH";
    public static final String BROADCAST_SET_WIFI_PARAMETER = "BROADCAST_SET_WIFI_PARAMETER";
    public static final String BROADCAST_SET_RECORD_PARAMETER = "BROADCAST_SET_RECORD_PARAMETER";
    public static final String BROADCAST_SET_VIDEO_PARAMETER = "BROADCAST_SET_VIDEO_PARAMETER";
    public static final String BROADCAST_SET_POWER_FRQUENCY = "BROADCAST_SET_POWER_FRQUENCY";
    public static final String BROADCAST_USBD_MODE = "BROADCAST_USBD_MODE";

    public static final String BROADCAST_GET_RECORD_CAPABILITY = "BROADCAST_GET_RECORD_CAPABILITY";
    public static final String BROADCAST_GET_OSD_STATUS = "BROADCAST_GET_OSD_STATUS";
    public static final String BROADCAST_SET_OSD_ON_OFF = "BROADCAST_SET_OSD_ON_OFF";
    public final static String BROADCAST_DOWNLOAD_PERCENT = "broadcast_download_percent";
    public final static String BROADCAST_SOCKET_CONNECT = "broadcast_socket_connect";

    public static final String BROADCAST_SET_GSENSOR = "BROADCAST_SET_GSENSOR";
    public static final String BROADCAST_PREVIEW_RETRY = "BROADCAST_PREVIEW_RETRY";

    public static final String BROADCAST_RESET_TO_DEFAULT = "BROADCAST_RESET_TO_DEFAULT";
    public final static String BROADCAST_GET_INDEX_FILE_FILE_FAIL = "BROADCAST_GET_INDEX_FILE_FILE_FAIL";
    public final static String BROADCAST_SD_CARD_IS_READING = "BROADCAST_SD_CARD_IS_READING";
    public final static String BROADCAST_SD_CARD_DOES_NOT_EXIST = "BROADCAST_SD_CARD_DOES_NOT_EXIST";
    public static final String BROADCAST_SD_CARD_NO_SPACE = "BROADCAST_SD_CARD_NO_SPACE";
    public static final String BROADCAST_SEED = "BROADCAST_SEED";
    public static final String BROADCAST_GET_FORMAT_STATUS = "BROADCAST_GET_FORMAT_STATUS";
    public static final String BROADCAST_DOWNLOAD_FILE_START = "BROADCAST_DOWNLOAD_FILE_START";
    public static final String BROADCAST_SET_SD_TEST = "BROADCAST_SET_SD_TEST";
    public static final String BROADCAST_GET_SD_TEST = "BROADCAST_GET_SD_TEST";
    public static final String BROADCAST_SET_WIFI_CONNECTION = "BROADCAST_SET_WIFI_CONNECTION";
    public static final String BROADCAST_GET_EZSETUP = "BROADCAST_GET_EZSETUP";
    public static final String BROADCAST_SET_EZSETUP = "BROADCAST_SET_EZSETUP";
    public static final String BROADCAST_GET_IQ_VERSION = "BROADCAST_GET_IQ_VERSION";
    //sharepreference
    public static final String SHARED_PREFERENCE_PHONE_RECORD_LENGTH = "SHARED_PREFERENCE_PHONE_RECORD_LENGTH";
    public static final String SHARED_PREFERENCE_CONTINUS_PIC_LENGTH = "SHARED_PREFERENCE_CONTINUS_PIC_LENGTH";
    public static final String SHARED_PREFERENCE_PROTECT_SEED = "SHARED_PREFERENCE_PROTECT_SEED";
    public static final String SHARED_PREFERENCE_SSID_PREFIX_KEY = "SHARED_PREFERENCE_SSID_PREFIX_KEY";

    public static final String SPECIAL_LIST = "Xiaomi-MI PAD";
    public static final String SHARED_PREFERENCE_DEVICE_CONNECT_MODE = "SHARED_PREFERENCE_DEVICE_CONNECT_MODE";
    public static final String SHARED_PREFERENCE_DEVICE_IP = "SHARED_PREFERENCE_DEVICE_IP";
    public static final String SHARED_PREFERENCE_DEBUG_MODE = "SP_DEBUG_MODE";
    public static final String SHARED_PREFERENCE_FORCE_I = "SP_FORCE_I";
    public static final String SHARED_PREFERENCE_DROP_FLAG = "dropflag";

    public static final int AP_MODE = 0;
    public static final int QR_MODE = 1;
    public static final int TONE_DETECTION_MODE = 2;
    public static final String SNAPSHOT = "BROADCAST_SNAPSORT";
    public static String Warrning = "联网失败，请稍后重试";

    //MANUFACTURE-MODEL
    public final static String PREVIEW_SPECIAL_LIST = "LeMobile-Le X620,HUAWEI-HUAWEI MT,Xiaomi-HM 1S,Xiaomi-MI PAD";///HUAWEI-HUAWEI MT7-TL00

    public static final String SHARED_PREFERENCE_SPECIAL_PREVIEW = "SHARED_PREFERENCE_SPECIAL_PREVIEW";

    public static final int LOW_PERF = 1;
    public static final int HiGH_PERF = 2;
    public static final int YES = 1;
    public static final int NO = 0;
    // DialogKit.showShareDialog(getActivity());
    public static String BaseUrl = "https://e-jxs.newlifegroup.com.cn:48092";
    //    public static String UploadURl = BaseUrl + "analysisResult/uploadTakeImgExForNewLife";
    public static String UploadURl = "http://115.28.149.54:8080/analysisResult/uploadTakeImgExForNewLife";
    public static String GetResule = "http://115.28.149.54:8080/elimeiAnalysis/analysisResult/getAnalysisResultEx";

    //    public static String GetVip = "https://e-jxs.newlifegroup.com.cn:48092/interface/V1/NewApi/getVipByIDNew";
//    public static String GetVip = "http://api.newlifegroup.com.cn/interface/V1/NewApi/getVipByIDNew";
    public static String GetVip = "http://106.14.216.129:8092/Interface/V1/NewApi/getVipByIDNew";
    //    https://e-jxs.newlifegroup.com.cn:48092/NewApi/getVipByIDNew
    public static String MemBerHistory = "http://101.132.148.26:80/newlife/historyRecordQuery/queryUserTestResult";
    public static String GetText = "http://101.132.148.26:80/newlife/historyRecordQuery/queryTestStallText";

    public static String result = "http://e-jxs-mr.newlifegroup.com.cn/newlife/historyRecordQuery/userResultShow?userId=";
    //    public static String sendTestResult = "http://e-jxs-test.newlifegroup.com.cn" + "/app/index.php?i=8&c=entry&do=sendCustom&m=yhh_new";
    public static String sendTestResult = "http://new.pospi.com/app/index.php?i=8&c=entry&do=sendCustom&m=yhh_new";
}
