package com.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.dash.Const;
import com.elimei.R;


public class MessageToast {
    public static final int TOAST_IN_MILLISECOND = 1000;
    public static final int SET_CHANNEL_SUCCESS = 65536;
    public static final int SET_CHANNEL_NVRAN_FAILED = 65638;
    public static final int SET_CHANNEL_GET_JSON_OBJECT_FAILED = 65678;
    public static final int SET_WDR_SUCCESS = 66048;
    public static final int SET_WDR_INVALID_PARAMTERS = 66149;
    public static final int SET_WDR_NVRAM_FAILED = 66150;
    public static final int SET_WDR_SET_WDR_ERROR = 66153;
    public static final int SET_WDR_JSON_OBJECT_FAILED = 66190;
    public static final int SET_SD_FORMAT_SUCCESS = 66816;
    public static final int SET_RECORD_STATUS_SUCCESS = 67072;
    public static final int SET_RECORD_STATUS_FAILD_RECORD_FULL = 67075;
    public static final int GET_RECORD_STATUS_OFF = 67328;
    public static final int GET_RECORD_STATUS_ON = 67329;
    public static final int GET_VIDEO_LIST_SUCCESS = 68864;
    public static final int GET_VIDEO_LIST_FAIL = 68865;
    public static final int GET_INDEX_FILE_SUCCESS = 69120;
    public static final int GET_INDEX_FILE_FAIL = 69121;
    public static final int GET_INDEX_FILE_NOSDCARD = 69122;
    public static final int DOWNLOAD_FILE_SUCCESS = 69376;
    public static final int DOWNLOAD_FILE_PARAMETER_ERROR = 69377;
    public static final int DOWNLOAD_FILE_NOT_EXIST = 69378;
    public static final int DOWNLOAD_FILE_COUNT_LIMIT = 69379;
    public static final int DOWNLOAD_FILE_TASK_FAIL = 69380;
    public static final int DOWNLOAD_FILE_COMMAND_FAIL = 17760513;
    public static final int DOWNLOAD_FILE_FAIL = 1110033;
    public static final int DELETE_FILE_SUCCESS = 69888;
    public static final int DELETE_FILE_FAIL = 69889;
    public static final int DELETE_FILE_SD_NOT_EXIST = 69890;
    public static final int STREAM_VIDEO_SUCCESS = 70144;
    public static final int STREAM_VIDEO_SD_CARD_READ_FAIL = 70145;
    public static final int STREAM_VIDEO_MEMORY_ALLOC_FAIL = 70146;
    public static final int STREAM_VIDEO_CONNECT_USER_FULL = 70147;
    public static final int STREAM_VIDEO_PLAY_FILE_FAIL = 70148;
    public static final int SEND_FONT_FILE_SUCCESS = 70656;
    public static final int SEND_FONT_FW_SUCCESS = 74752;
    public static final int SEND_FONT_FW_FAIL = 74753;
    public static final int UPGRDAE_FW_SUCCESS = 75520;
    public static final int UPGRDAE_FW_SIZE_ERROR = 75521;
    public static final int UPGRDAE_FW_VERSION_ERROR = 75522;
    public static final int UPGRDAE_FW_MD5_ERROR = 75523;
    public static final int UPGRDAE_FW_INIT_FAILED = 75524;
    public static final int SET_PWD_SUEECSE = 65792;
    public static final int SET_PWD_INVALID_PARAMTERS = 65893;
    public static final int SET_PWD_NVRAM_FAILED = 65894;
    public static final int SET_PWD_JSON_OBJECT_FAILED = 65934;
    public static final int SET_LOOP_RECORD_SUCCESS = 74240;
    public static final int SET_LOOP_RECORD_FAIL = 74241;
    public static final int SET_LOOP_RECORD_SD_NOT_EXIST = 74242;
    public static final int SET_RECORD_VOLUMN_SUCCESS = 72704;
    public static final int SET_RECORD_VOLUMN_FAIL = 72705;
    public static final int SET_RECORD_VOLUMN_SD_NOT_EXIST = 72706;
    public static final int SET_RECORD_LENGTH_SUCCESS = 72960;
    public static final int SET_RECORD_LENGTH_FAIL = 72961;
    public static final int SET_RECORD_LENGTH_REBOOT_PLEASE_RETRY = 72962;
    public static final int SET_RECORD_LENGTH_SD_NOT_EXIST = 72963;
    public static final int SET_WIFI_PARAMETER_SUCCESS = 75776;
    public static final int SET_WIFI_PARAMETER_FAIL = 75777;
    public static final int SET_WIFI_PARAMETER_INVALID_PARAMETERS = 75877;
    public static final int SET_WIFI_PARAMETER_NVRAM_FAILED = 75878;
    public static final int SET_SSID_SUCCESS = 71424;
    public static final int SET_SSID_INVALID_PARAMETERS = 71525;
    public static final int SET_SSID_NVRAM_FAILED = 71526;
    public static final int SET_SSID_GET_JSON_OBJECT_FAILED = 6030;
    public static final int SET_RECORD_PARAMETER_SUCCESS = 73216;
    public static final int SET_RECORD_PARAMETER_FAIL = 73217;
    public static final int SET_RECORD_PARAMETER_REBOOTING_PLEASE_TRY_AGAIN = 73218;
    public static final int SET_RECORD_PARAMETER_SD_NOT_EXIST = 73219;
    public static final int SET_VIDEO_PARAMETER_SUCCESS = 71680;
    public static final int SET_VIDEO_PARAMETER_FAIL = 71681;
    public static final int SET_POWER_FREQUENCY_SUCCESS = 74496;
    public static final int SET_POWER_FREQUENCY_FAIL = 74497;
    public static final int TAKE_PICTURE_SUCCESS = 67584;
    public static final int TAKE_PICTURE_FAIL = 67585;
    public static final int TAKE_PICTURE_FAIL_NO_SDCARD = 67586;
    public static final int TAKE_PICTURE_FAIL_TASK_IS_ALREADY_RUNNING = 67587;
    public static final int SET_MIRROR_SUCCESS = 70912;
    public static final int SET_MIRROR_FAIL = 70913;
    public static final int SET_MIRROR_INVALID_PARAMTERS = 71013;
    public static final int SET_MIRROR_NVRAM_FAILED = 71014;
    public static final int SET_MIRROR_SET_MIRROR_ERROR = 71018;
    public static final int SET_MIRROR_JSON_OBJECT_FAILED = 71054;
    public static final int SET_FLIP_SUCCESS = 71168;
    public static final int SET_FLIP_FAIL = 71169;
    public static final int SET_FLIP_INVALID_PARAMTERS = 71269;
    public static final int SET_FLIP_NVRAM_FAILED = 71270;
    public static final int SET_FLIP_SET_VIDEO_FLIP_ERROR = 71275;
    public static final int SET_FLIP_JSON_OBJECT_FAILED = 71310;
    public static final int SET_WIFI_SUCCESS = 0;
    public static final int SET_WIFI_FAIL = 0;
    public static final int GET_WIFI_SUCCESS = 0;
    public static final int GET_WIFI_FAIL = 0;
    public static final int SET_RECORD_STATUS_FAIL = 67073;
    public static final int SET_RECORD_STATUS_SD_NOT_EXIST = 67074;
    public static final int SET_SD_FORMAT_RECORD_STOP_FAIL = 66817;
    public static final int SET_SD_FORMAT_DATA_SENDING = 66818;
    public static final int SET_SD_FORMAT_FAIL = 66819;
    public static final int SET_SD_FORMAT_PLAYBACK = 66820;
    public static final int SET_SD_FORMAT_TAKING_SNAPSHOT = 66821;
    public static final int SET_SD_FORMAT_SD_NOT_EXIST = 66822;
    public static final int GET_OSD_STATUS_SUCCESS = 78080;
    public static final int GET_OSD_STATUS_FAIL = 78081;
    public static final int GET_OSD_STATUS_NVRAM_NO_OSD_DATA = 78082;
    public static final int SET_OSD_ON_OFF_SUCCESS = 78336;
    public static final int SET_OSD_ON_OFF_FAIL = 78337;
    public static final int SET_OSD_ON_OFF_NVRAM_NO_OSD_DATA = 78338;
    public static final int SET_GSENSOR_SUCCESS = 77312;
    public static final int SET_GSENSOR_FAIL = 77313;
    public static final int RESET_TO_DEFAULT_SUCCESS = 77824;
    public static final int RESET_TO_DEFAULT_FAIL = 77825;
    public static final int RESET_TO_DEFAULT_REBOOT_FAIL = 77826;
    public static final int SYNCTIME_SUCCESS = 67840;

    public class DEVICE_CMD_TYPE {
        public static final int js_set_wdr = 2;
        public static final int js_set_mirror = 21;
        public static final int js_set_flip = 22;
        public static final int js_get_videostatus = 3;
        public static final int js_set_videoparameters = 24;
        public static final int js_set_videobitrate = 25;
        public static final int js_set_videofps = 26;
        public static final int js_set_videoresolution = 27;
        public static final int js_get_sdspace = 4;
        public static final int js_get_format_status = 48;
        public static final int js_set_sdformat = 5;
        public static final int js_set_recordstatus = 6;
        public static final int js_get_recordstatus = 7;
        public static final int js_takepicture = 8;
        public static final int js_set_recordaudiostatus = 28;
        public static final int js_set_recordlength = 29;
        public static final int js_set_recordparameters = 30;
        public static final int js_set_looprecordstatus = 34;
        public static final int js_get_recordcapability = 37;
        public static final int js_set_protectlength = 45;
        public static final int js_synctime = 9;
        public static final int js_get_time = 10;
        public static final int js_get_batterystatus = 11;
        public static final int js_get_deviceparameter = 12;
        public static final int js_set_powerfrequency = 35;
        public static final int js_nvramresettodefault = 46;
        public static final int js_get_iqversion = 43;
        public static final int js_set_gsensorparamter = 44;
        public static final int js_usbdclassmode = 47;
        public static final int js_get_videolist = 13;
        public static final int js_get_indexfile = 14;
        public static final int js_downloadfile = 15;
        public static final int js_downloadfilefinish = 16;
        public static final int js_deletefile = 17;
        public static final int js_streamvideo = 18;
        public static final int js_streamvideofinish = 19;
        public static final int js_sendfontfile = 20;
        public static final int js_sendfwbin = 36;
        public static final int js_upgradefw = 39;
        public static final int js_set_wifiparameters = 40;
        public static final int js_get_osdstatus = 41;
        public static final int js_set_osdonoff = 42;
        public static final int js_downloadfilestart = 49;
        public static final int json_get_sdtest_status = 50;
        public static final int json_set_sdtest = 51;
    }

    public class DEVICE_STATUS_CODE {
        public static final int OK = 0;
        public static final int INVALID_PARAM = 101;
        public static final int NVRAM_ERR = 102;
        public static final int GET_FILE_SIZE_ERR = 103;
        public static final int TASK_HAVE_NOT_INIT_OK = 104;
        public static final int SET_WDR_ERR = 105;
        public static final int SET_MIRROR_ERR = 106;
        public static final int SET_FLIP_ERR = 107;
        public static final int SET_POWER_FREQ_ERR = 108;
        public static final int GET_IQ_VERSION_ERR = 109;
        public static final int CREATE_TASK_ERR = 110;
        public static final int SYSTEM_IS_REBOOTING = 111;
        public static final int GET_OSD_STRING_ERR = 112;
        public static final int MEMORY_NOT_ENOUGH = 113;
        public static final int PHOTO_FOLDER_IS_FULL = 114;
        public static final int CONTINUE_SHOOTING_IS_RUNNING = 115;
        public static final int OVER_SNAPSHOT_MAX_QUEUE_NUM = 116;
        public static final int TAKE_PICTURE_ERR = 117;
        public static final int RECORD_FOLDER_IS_FULL = 118;
        public static final int TIMELAPSE_FOLDER_IS_FULL = 119;
        public static final int GET_RESOLUTION_ERR = 120;
        public static final int GET_INDEX_ERR = 121;
        public static final int UPLOAD_FILE_ERR = 122;
        public static final int STOP_UPLOAD_FILE_ERR = 123;
        public static final int DELETE_FILE_ERR = 124;
        public static final int FWUPGRADE_IS_RUNNING = 125;
        public static final int CREATE_DOWNLOAD_SOCKET_ERR = 126;
        public static final int FWUPGRADE_INIT_ERR = 127;
        public static final int PALYBACK_IS_ON_WORGING = 128;
        public static final int SET_RECORD_PARAM_ERR = 129;
        public static final int UPLOAD_FILE_NOT_EXIST = 130;
        public static final int UPLOAD_FILE_USER_IS_FULL = 131;
        public static final int UPLOAD_CREATE_TASK_FAILED = 132;
        public static final int UPLOAD_WAIT_PRE_TASK_CLOSE = 133;
        public static final int PB_INIT_FAIL = 134;
        public static final int PB_USER_IS_FULL = 135;
        public static final int PB_CHECK_FILE_ERR = 136;
        public static final int UPGRADE_RECEIVE_SIZE_ERR = 137;
        public static final int UPGRADE_VERSION_ERR = 138;
        public static final int UPGRADE_MD5_ERR = 139;
        public static final int UPGRADE_FW_AND_PLATFORM_DISMATCH = 140;
        public static final int UPGRADE_IS_RUNNING = 141;
        public static final int GET_OBJECT_FAIL = 142;
        public static final int OVER_DOWLOD_AND_PB_MAX_CONN = 143;
        public static final int DOWNLOD_FONE_TASK_IS_RUNNGIN = 144;
        public static final int CREATE_DOWNLOD_SOCKET_ERR = 145;
        public static final int MEMORY_IS_NOT_ENOUGH = 146;
        public static final int GET_INDEXFILE_ERR = 147;
        public static final int UPLOAD_INDEXFILE_ERR = 148;
        public static final int UPLOAD_CLIENT_ADDRESS_NOT_FOUND = 149;
        public static final int FILELIST_IS_UPLOADING = 150;
        public static final int PREVIEW_IS_ON_WORKING = 151;
        public static final int WAIT_SD_CARD_READY = 153;
        public static final int SD_OK = 0;
        public static final int SD_ERR_BASE = 200;
        public static final int SD_NOT_EXIST = 201;
        public static final int SD_FOTMAT_ERR = 202;
        public static final int SD_IS_FORMATTING = 203;
        public static final int SD_GET_SPACE_ERR = 204;
        public static final int SD_FORMAT_FILE_UPLOADING_ERR = 205;
        public static final int SD_FORMAT_PLAYBAK_ON_WORKING_ERR = 206;
        public static final int SD_FORMAT_SNAPSHOT_ON_WORKING_ERR = 207;
        public static final int SD_FORMAT_THUMBNAIL_ON_WORKING_ERR = 208;
        public static final int SD_FORMAT_FAIL = 209;
        public static final int SD_FORMAT_WAITING = 210;
        public static final int SD_TEST_ON_WORKING = 211;
        public static final int SD_STILL_IN_TESTING = 212;
        public static final int SD_TEST_FAILED = 213;
    }

    public static void show2(Context context, int status, int millisecond) {
        if (Const.Current_version == 1) {
            show2inMillsecond(context, status, millisecond);
        } else {
            showInMillsecond(context, status, millisecond);
        }
    }

    public static void show2inMillsecond(Context context, int status, int millisecond) {
        String tmpResultString = "";
        int cmdType = (status & 0xFF00) >> 8;

        int cmdStatus = status & 0xFF;
        switch (cmdStatus) {

            case 0:
                switch (cmdType) {
                    case 15:
                        tmpResultString = context.getString(R.string.download_success);
                        break;
                    case 20:
                        tmpResultString = context.getString(R.string.send_file_finish);
                        break;
                    case 36:
                        tmpResultString = context.getString(R.string.send_file_finish);
                        break;
                    case 46:
                        tmpResultString = context.getString(R.string.reset_to_default_succcess);
                        break;
                    case 42:
                        tmpResultString = context.getString(R.string.set_osd_status_success);
                        break;
                    case 41:
                        tmpResultString = context.getString(R.string.get_osd_status_success);
                        break;
                    case 24:
                        tmpResultString = context.getString(R.string.set_video_parameter_success);
                        break;
                    case 30:
                        tmpResultString = context.getString(R.string.set_record_parameter_success);
                        break;
                    case 40:
                        tmpResultString = context.getString(R.string.set_wifi_parameter_success);
                        break;
                    case 29:
                        tmpResultString = context.getString(R.string.set_record_length_success);
                        break;
                    case 34:
                        tmpResultString = context.getString(R.string.set_loop_record_success);
                        break;
                    case 39:
                        tmpResultString = context.getString(R.string.upgrade_fw_success);
                        break;
                    case 8:
                        tmpResultString = context.getString(R.string.take_picture_success);
                        break;

                    case 48:
                        break;
                    case 17:
                        tmpResultString = context.getString(R.string.delete_success);
                        break;
                }

                tmpResultString = context.getString(R.string.setting_command_success);
                break;
        }


        toast(context, millisecond, tmpResultString);
    }

    public static void show2(Context context, int status) {
        if (Const.Current_version == 1) {
            show2inMillsecond(context, status, 1000);
        } else {
            showInMillsecond(context, status, 1000);
        }
    }


    public static void showInMillsecond(Context context, int status, int millisecond) {
        String tmpResultString = "";
        int cmdStatus = status & 0xFF;
        int cmdType = status & 0xFF00;

        switch (status) {
            case 0:
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 1:
                tmpResultString = context.getString(R.string.setting_command_fail);
                Toast.makeText(context, context.getString(R.string.setting_command_fail), Toast.LENGTH_SHORT)
                        .show();
                break;


            case 65536:
                /*  600 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 65537:
                /*  606 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 65792:
                /*  612 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 65793:
                /*  618 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 66048:
                /*  626 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 66049:
                /*  632 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 66304:
                /*  638 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 66305:
                /*  644 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 70912:
                /*  650 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 70913:
                /*  656 */
                tmpResultString = context.getString(R.string.setting_command_fail);


            case 71168:
                /*  662 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), Toast.LENGTH_SHORT)
/*  663 */.show();
                break;
            case 71169:
                /*  666 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 66560:
                /*  674 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 66561:
                /*  680 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 66816:
                /*  686 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 66817:
                /*  692 */
                tmpResultString = context.getString(R.string.format_record_stop_fail);
                break;


            case 66818:
                /*  698 */
                tmpResultString = context.getString(R.string.format_file_transfer);
                break;


            case 66819:
                /*  704 */
                tmpResultString = context.getString(R.string.format_fail);
                break;


            case 66820:
                /*  709 */
                tmpResultString = context.getString(R.string.format_playback);
                break;


            case 66821:
                /*  715 */
                tmpResultString = context.getString(R.string.format_snapshot);
                break;


            case 66822:
                /*  721 */
                tmpResultString = context.getString(R.string.please_input_sdcard);
                break;


            case 67072:
                /*  729 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 67073:
                /*  735 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 67075:
                break;


            case 67074:
                /*  744 */
                tmpResultString = context.getString(R.string.please_input_sdcard);
                break;


            case 67328:
                /*  750 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 67329:
                /*  756 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 67584:
                /*  763 */
                tmpResultString = context.getString(R.string.take_picture_success);
                break;


            case 67585:
                /*  771 */
                tmpResultString = context.getString(R.string.take_picture_fail);
                break;


            case 67586:
                /*  782 */
                tmpResultString = context.getString(R.string.please_input_sdcard);
                break;


            case 67587:
                /*  788 */
                tmpResultString = context.getString(R.string.take_picture_failed_task_is_already_running);
                break;


            case 67840:
                /*  794 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 67841:
                /*  800 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 68096:
                /*  806 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 68097:
                /*  813 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 68352:
                /*  819 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 68353:
                /*  825 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 68608:
                /*  831 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 68609:
                /*  837 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 68864:
                /*  845 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 68865:
                /*  851 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 69120:
                /*  857 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 69121:
                /*  863 */
                tmpResultString = context.getString(R.string.get_index_file_error);
                break;


            case 69376:
                /*  869 */
                tmpResultString = context.getString(R.string.download_success);
                break;


            case 69377:
                /*  875 */
                tmpResultString = context.getString(R.string.download_fail_parameter_error);
                break;


            case 69378:
                /*  881 */
                tmpResultString = context.getString(R.string.download_fail_file_not_exist);
                break;


            case 69379:
                /*  887 */
                tmpResultString = context.getString(R.string.download_fail_reach_limit);
                break;


            case 69632:
                /*  893 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 69633:
                /*  899 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 69888:
                /*  905 */
                tmpResultString = context.getString(R.string.delete_success);
                break;


            case 69889:
                /*  911 */
                tmpResultString = context.getString(R.string.delete_fail);
                break;


            case 69890:
                /*  916 */
                tmpResultString = context.getString(R.string.please_input_sdcard);
                break;


            case 70144:
                /*  923 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 70145:
                /*  931 */
                tmpResultString = context.getString(R.string.stream_sdcard_read_fail);
                break;


            case 70146:
                /*  937 */
                tmpResultString = context.getString(R.string.stream_memory_alloc_fail);
                break;


            case 70147:
                /*  943 */
                tmpResultString = context.getString(R.string.stream_connect_user_is_full);
                break;


            case 70148:
                /*  949 */
                tmpResultString = context.getString(R.string.stream_playfile_is_fail);
                break;


            case 70400:
                /*  955 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 70401:
                /*  961 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 70656:
                /*  967 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 70657:
                /*  973 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 75520:
                /*  981 */
                tmpResultString = context.getString(R.string.upgrade_fw_success);
                break;


            case 75521:
                /*  988 */
                tmpResultString = context.getString(R.string.upgrade_fw_size_error);
                break;


            case 75522:
                /*  994 */
                tmpResultString = context.getString(R.string.upgrade_fw_version_error);
                break;


            case 75523:
                /* 1000 */
                tmpResultString = context.getString(R.string.upgrade_fw_md5_error);
                break;


            case 75524:
                /* 1006 */
                tmpResultString = context.getString(R.string.upgradefw_init_fail);
                break;


            case 74753:
                /* 1012 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 74240:
                /* 1019 */
                tmpResultString = context.getString(R.string.set_loop_record_success);
                break;


            case 74241:
                /* 1025 */
                tmpResultString = context.getString(R.string.set_loop_record_fail);
                break;


            case 74242:
                /* 1031 */
                tmpResultString = context.getString(R.string.please_input_sdcard);
                break;


            case 72704:
                /* 1037 */
                tmpResultString = context.getString(R.string.set_record_volumn_success);
                break;


            case 72705:
                /* 1043 */
                tmpResultString = context.getString(R.string.set_record_volumn_fail);
                break;


            case 72706:
                /* 1049 */
                tmpResultString = context.getString(R.string.please_input_sdcard);
                break;


            case 72960:
                /* 1055 */
                tmpResultString = context.getString(R.string.set_record_length_success);
                break;


            case 72961:
                /* 1061 */
                tmpResultString = context.getString(R.string.set_record_length_fail);
                break;


            case 72962:
                /* 1068 */
                tmpResultString = context.getString(R.string.device_rebooting_please_try_again);
                break;


            case 72963:
                /* 1074 */
                tmpResultString = context.getString(R.string.please_input_sdcard);
                break;


            case 75776:
                /* 1081 */
                tmpResultString = context.getString(R.string.set_wifi_parameter_success);
                break;


            case 75777:
                /* 1087 */
                tmpResultString = context.getString(R.string.set_wifi_parameter_fail);
                break;


            case 73216:
                /* 1093 */
                tmpResultString = context.getString(R.string.set_record_parameter_success);
                break;


            case 73217:
                /* 1099 */
                tmpResultString = context.getString(R.string.set_record_parameter_fail);
                break;


            case 73218:
                /* 1105 */
                tmpResultString = context.getString(R.string.device_rebooting_please_try_again);
                break;


            case 73219:
                /* 1111 */
                tmpResultString = context.getString(R.string.please_input_sdcard);
                break;


            case 71680:
                /* 1118 */
                tmpResultString = context.getString(R.string.set_video_parameter_success);
                break;


            case 71681:
                /* 1124 */
                tmpResultString = context.getString(R.string.set_video_parameter_fail);
                break;


            case 74496:
                /* 1130 */
                tmpResultString = context.getString(R.string.set_power_frequency_success);
                break;


            case 74497:
                /* 1136 */
                tmpResultString = context.getString(R.string.set_power_frequency_fail);
                break;


            case 69122:
                /* 1142 */
                tmpResultString = context.getString(R.string.please_input_sdcard);
                break;


            case 78080:
                /* 1148 */
                tmpResultString = context.getString(R.string.get_osd_status_success);
                break;


            case 78081:
                /* 1154 */
                tmpResultString = context.getString(R.string.get_osd_status_fail);
                break;


            case 78082:
                /* 1160 */
                tmpResultString = context.getString(R.string.get_osd_status_fail_no_osd_data);
                break;


            case 78336:
                /* 1167 */
                tmpResultString = context.getString(R.string.set_osd_status_success);
                break;


            case 78337:
                /* 1173 */
                tmpResultString = context.getString(R.string.set_osd_status_fail);
                break;


            case 78338:
                /* 1179 */
                tmpResultString = context.getString(R.string.set_osd_status_fail_no_osd_data);
                break;


            case 77312:
                /* 1186 */
                tmpResultString = context.getString(R.string.setting_command_success);
                break;


            case 77313:
                /* 1192 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;


            case 77824:
                /* 1200 */
                tmpResultString = context.getString(R.string.reset_to_default_succcess);
                break;


            case 77825:
                /* 1206 */
                tmpResultString = context.getString(R.string.reset_to_default_fail);
                break;


            case 77826:
                /* 1212 */
                tmpResultString = context.getString(R.string.reset_to_default_reboot_fail);
                break;


            case 74752:
                /* 1218 */
                tmpResultString = context.getString(R.string.send_file_finish);
                break;


            default:
                /* 1225 */
                tmpResultString = context.getString(R.string.setting_command_fail);
                break;
        }



        /* 1231 */
        toast(context, millisecond, tmpResultString);
    }


    /* 1235 */
    public static int getCommandType(int status) {
        return (status & 0xFF00) >> 8;
    }


    /* 1240 */
    public static int getCommandStatus(int status) {
        return status & 0xFF;
    }


    public static boolean isCommandSuccess(int status) {
        /* 1245 */
        int cmdStatus = status & 0xFF;
        /* 1246 */
        if (cmdStatus == 0) {
            /* 1247 */
            return true;
        }
        /* 1249 */
        return false;
    }


    public static void toast(Context context, int millisec, String msg) {
        /* 1255 */
        if (msg.equals("") || msg == null) {
            return;
        }
        /* 1258 */
        Handler handler = null;
        /* 1259 */
        final Toast toasts = Toast.makeText(context, msg, Toast.LENGTH_SHORT);

        /* 1261 */
        toasts.show();

        /* 1263 */
        if (handler == null) {
            /* 1264 */
            handler = new Handler();
            /* 1265 */
            handler.postDelayed(new Runnable() {
                public void run() {
                    /* 1269 */
                    toasts.cancel();
                }
            }, millisec);
        }
    }
}

