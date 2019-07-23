package util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.elimei.R;


public class MessageToast {
    public static final int SET_CHANNEL_SUCCESS = 65536;
    public static final int SET_WDR_SUCCESS = 66048;
    public static final int SET_SD_FORMAT_SUCCESS = 66816;
    public static final int SET_RECORD_STATUS_SUCCESS = 67072;
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
    public static final int SET_PWD_FAIL = 65793;
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
    public static final int SET_FLIP_SUCCESS = 71168;
    public static final int SET_FLIP_FAIL = 71169;
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

    public static void show(Context context, int status) {
        /* 122 */
        switch (status) {
            case 0:
                /* 124 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 1:
                /* 127 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;


            case 65536:
                /* 132 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 65537:
                /* 135 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 65792:
                /* 138 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 65793:
                /* 141 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;


            case 66048:
                /* 148 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 66049:
                /* 151 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 66304:
                /* 154 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 66305:
                /* 157 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 70912:
                /* 160 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 70913:
                /* 163 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 71168:
                /* 166 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 71169:
                /* 169 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;


            case 66560:
                /* 174 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 66561:
                /* 177 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 66816:
                /* 180 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 66817:
                /* 183 */
                Toast.makeText(context, context.getString(R.string.format_record_stop_fail), 0).show();
                return;
            case 66818:
                /* 186 */
                Toast.makeText(context, context.getString(R.string.format_file_transfer), 0).show();
                return;
            case 66819:
                /* 189 */
                Toast.makeText(context, context.getString(R.string.format_fail), 0).show();
                return;
            case 66820:
                /* 192 */
                Toast.makeText(context, context.getString(R.string.format_playback), 0).show();
                return;
            case 66821:
                /* 195 */
                Toast.makeText(context, context.getString(R.string.format_snapshot), 0).show();
                return;
            case 66822:
                /* 198 */
                Toast.makeText(context, context.getString(R.string.please_input_sdcard), 0).show();
                return;


            case 67072:
                /* 203 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 67073:
                /* 206 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 67074:
                /* 209 */
                Toast.makeText(context, context.getString(R.string.please_input_sdcard), 0).show();
                return;
            case 67328:
                /* 212 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 67329:
                /* 215 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;


            case 67584:
                /* 220 */
                toast(context, 300, context.getString(R.string.take_picture_success));
                return;

            case 67585:
                /* 224 */
                toast(context, 300, context.getString(R.string.take_picture_fail));
                return;

            case 67586:
                /* 228 */
                toast(context, 300, context.getString(R.string.please_input_sdcard));
                return;
            case 67587:
                /* 231 */
                toast(context, 300, context.getString(R.string.take_picture_failed_task_is_already_running));
                return;

            case 67840:
                /* 235 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 67841:
                /* 238 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 68096:
                /* 241 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 68097:
                /* 244 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 68352:
                /* 247 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 68353:
                /* 250 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 68608:
                /* 253 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 68609:
                /* 256 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;


            case 68864:
                /* 261 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 68865:
                /* 264 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 69120:
                /* 267 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 69121:
                /* 270 */
                Toast.makeText(context, context.getString(R.string.get_index_file_error), 0).show();
                return;
            case 69376:
                /* 273 */
                Toast.makeText(context, context.getString(R.string.download_success), 0).show();
                return;
            case 69377:
                /* 276 */
                Toast.makeText(context, context.getString(R.string.download_fail_parameter_error), 0).show();
                return;
            case 69378:
                /* 279 */
                Toast.makeText(context, context.getString(R.string.download_fail_file_not_exist), 0).show();
                return;
            case 69379:
                /* 282 */
                Toast.makeText(context, context.getString(R.string.download_fail_reach_limit), 0).show();
                return;
            case 69632:
                /* 285 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 69633:
                /* 288 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 69888:
                /* 291 */
                Toast.makeText(context, context.getString(R.string.delete_success), 0).show();
                return;
            case 69889:
                /* 294 */
                Toast.makeText(context, context.getString(R.string.delete_fail), 0).show();
                return;
            case 69890:
                /* 297 */
                Toast.makeText(context, context.getString(R.string.please_input_sdcard), 0).show();
                return;


            case 70144:
                /* 302 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;


            case 70145:
                /* 307 */
                Toast.makeText(context, context.getString(R.string.stream_sdcard_read_fail), 0).show();
                return;
            case 70146:
                /* 310 */
                Toast.makeText(context, context.getString(R.string.stream_memory_alloc_fail), 0).show();
                return;
            case 70147:
                /* 313 */
                Toast.makeText(context, context.getString(R.string.stream_connect_user_is_full), 0).show();
                return;
            case 70148:
                /* 316 */
                Toast.makeText(context, context.getString(R.string.stream_playfile_is_fail), 0).show();
                return;
            case 70400:
                /* 319 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 70401:
                /* 322 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;
            case 70656:
                /* 325 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 70657:
                /* 328 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;


            case 75520:
                /* 333 */
                Toast.makeText(context, context.getString(R.string.upgrade_fw_success), 0).show();
                return;
            case 75521:
                /* 336 */
                Toast.makeText(context, context.getString(R.string.upgrade_fw_size_error), 0).show();
                return;
            case 75522:
                /* 339 */
                Toast.makeText(context, context.getString(R.string.upgrade_fw_version_error), 0).show();
                return;
            case 75523:
                /* 342 */
                Toast.makeText(context, context.getString(R.string.upgrade_fw_md5_error), 0).show();
                return;
            case 75524:
                /* 345 */
                Toast.makeText(context, context.getString(R.string.upgradefw_init_fail), 0).show();
                return;
            case 74753:
                /* 348 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;

            case 74240:
                /* 352 */
                Toast.makeText(context, context.getString(R.string.set_loop_record_success), 0).show();
                return;
            case 74241:
                /* 355 */
                Toast.makeText(context, context.getString(R.string.set_loop_record_fail), 0).show();
                return;
            case 74242:
                /* 358 */
                Toast.makeText(context, context.getString(R.string.please_input_sdcard), 0).show();
                return;
            case 72704:
                /* 361 */
                Toast.makeText(context, context.getString(R.string.set_record_volumn_success), 0).show();
                return;
            case 72705:
                /* 364 */
                Toast.makeText(context, context.getString(R.string.set_record_volumn_fail), 0).show();
                return;
            case 72706:
                /* 367 */
                Toast.makeText(context, context.getString(R.string.please_input_sdcard), 0).show();
                return;
            case 72960:
                /* 370 */
                Toast.makeText(context, context.getString(R.string.set_record_length_success), 0).show();
                return;
            case 72961:
                /* 373 */
                Toast.makeText(context, context.getString(R.string.set_record_length_fail), 0).show();
                return;

            case 72962:
                /* 377 */
                Toast.makeText(context, context.getString(R.string.device_rebooting_please_try_again), 0).show();
                return;
            case 72963:
                /* 380 */
                Toast.makeText(context, context.getString(R.string.please_input_sdcard), 0).show();
                return;

            case 75776:
                /* 384 */
                Toast.makeText(context, context.getString(R.string.set_wifi_parameter_success), 0).show();
                return;
            case 75777:
                /* 387 */
                Toast.makeText(context, context.getString(R.string.set_wifi_parameter_fail), 0).show();
                return;
            case 73216:
                /* 390 */
                Toast.makeText(context, context.getString(R.string.set_record_parameter_success), 0).show();
                return;
            case 73217:
                /* 393 */
                Toast.makeText(context, context.getString(R.string.set_record_parameter_fail), 0).show();
                return;
            case 73218:
                /* 396 */
                Toast.makeText(context, context.getString(R.string.device_rebooting_please_try_again), 0).show();
                return;
            case 73219:
                /* 399 */
                Toast.makeText(context, context.getString(R.string.please_input_sdcard), 0).show();
                return;

            case 71680:
                /* 403 */
                Toast.makeText(context, context.getString(R.string.set_video_parameter_success), 0).show();
                return;
            case 71681:
                /* 406 */
                Toast.makeText(context, context.getString(R.string.set_video_parameter_fail), 0).show();
                return;
            case 74496:
                /* 409 */
                Toast.makeText(context, context.getString(R.string.set_power_frequency_success), 0).show();
                return;
            case 74497:
                /* 412 */
                Toast.makeText(context, context.getString(R.string.set_power_frequency_fail), 0).show();
                return;
            case 69122:
                /* 415 */
                Toast.makeText(context, context.getString(R.string.please_input_sdcard), 0).show();
                return;
            case 78080:
                /* 418 */
                Toast.makeText(context, context.getString(R.string.get_osd_status_success), 0).show();
                return;
            case 78081:
                /* 421 */
                Toast.makeText(context, context.getString(R.string.get_osd_status_fail), 0).show();
                return;
            case 78082:
                /* 424 */
                Toast.makeText(context, context.getString(R.string.get_osd_status_fail_no_osd_data), 0).show();
                return;

            case 78336:
                /* 428 */
                Toast.makeText(context, context.getString(R.string.set_osd_status_success), 0).show();
                return;
            case 78337:
                /* 431 */
                Toast.makeText(context, context.getString(R.string.set_osd_status_fail), 0).show();
                return;
            case 78338:
                /* 434 */
                Toast.makeText(context, context.getString(R.string.set_osd_status_fail_no_osd_data), 0).show();
                return;

            case 77312:
                /* 438 */
                Toast.makeText(context, context.getString(R.string.setting_command_success), 0).show();
                return;
            case 77313:
                /* 441 */
                Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
                return;

            case 77824:
                /* 445 */
                Toast.makeText(context, context.getString(R.string.reset_to_default_succcess), 0).show();
                return;
            case 77825:
                /* 448 */
                Toast.makeText(context, context.getString(R.string.reset_to_default_fail), 0).show();
                return;
            case 77826:
                /* 451 */
                Toast.makeText(context, context.getString(R.string.reset_to_default_reboot_fail), 0).show();
                return;
            case 74752:
                /* 454 */
                Toast.makeText(context, context.getString(R.string.send_file_finish), 0).show();
                return;
        }


        /* 459 */
        Toast.makeText(context, context.getString(R.string.setting_command_fail), 0).show();
    }


    public static void toast(Context context, int millisec, String msg) {
        /* 465 */
        Handler handler = null;
        /* 466 */
        final Toast toasts = Toast.makeText(context, msg, 0);

        /* 468 */
        toasts.show();
        /* 469 */
        if (handler == null) {
            /* 470 */
            handler = new Handler();
            /* 471 */
            handler.postDelayed(new Runnable() {
                public void run() {
                    /* 474 */
                    toasts.cancel();
                }
            }, millisec);
        }
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar\\util\MessageToast.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */