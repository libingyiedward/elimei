/*     */ package com.device;
/*     */ 
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
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
/*     */ 
/*     */ public class DeviceCommand
/*     */ {
/*     */   public class Download
/*     */   {
/*     */     public static final int DOWNLOAD_BG = 0;
/*     */     public static final int DOWNLOAD_FG = 1;
/*     */   }
/*     */   
/*     */   public class IndexFile
/*     */   {
/*     */     public static final int RECORD = 0;
/*     */     public static final int PROTECT = 1;
/*     */     public static final int PICTURE = 2;
/*     */   }
/*     */   
/*     */   public class Mode
/*     */   {
/*     */     public static final int APMODE = 1;
/*     */     public static final int QRCODEMODE = 2;
/*     */     public static final int TONEMODE = 4;
/*     */     public static final int SINGLEMODE = 3;
/*     */     public static final int BOTHMODE = 7;
/*     */   }
/*     */   
/*     */   public JSONObject setwificonnection(String ssid, String pswd) {
/*  45 */     JSONObject obj = new JSONObject();
/*     */     try {
/*  47 */       obj.put("type", "setwificonnection");
/*  48 */       obj.put("ssid", ssid);
/*  49 */       obj.put("pswd", pswd);
/*     */     }
/*  51 */     catch (JSONException e) {
/*  52 */       e.printStackTrace();
/*     */     } 
/*  54 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject getezsetup() {
/*  58 */     JSONObject obj = new JSONObject();
/*     */     try {
/*  60 */       obj.put("type", "getezsetup");
/*     */     }
/*  62 */     catch (JSONException e) {
/*  63 */       e.printStackTrace();
/*     */     } 
/*  65 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public JSONObject setezsetup(int mode) {
/*  70 */     JSONObject obj = new JSONObject();
/*     */     try {
/*  72 */       obj.put("type", "setezsetup");
/*  73 */       obj.put("mode", mode);
/*  74 */     } catch (JSONException e) {
/*  75 */       e.printStackTrace();
/*     */     } 
/*  77 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject getSdTest() {
/*  81 */     JSONObject obj = new JSONObject();
/*     */     try {
/*  83 */       obj.put("type", "getsdtest");
/*     */     }
/*  85 */     catch (JSONException e) {
/*  86 */       e.printStackTrace();
/*     */     } 
/*  88 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject setSdTest() {
/*  92 */     JSONObject obj = new JSONObject();
/*     */     try {
/*  94 */       obj.put("type", "setsdtest");
/*     */     }
/*  96 */     catch (JSONException e) {
/*  97 */       e.printStackTrace();
/*     */     } 
/*  99 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject getIqVersion() {
/* 103 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 105 */       obj.put("type", "getiqversion");
/* 106 */     } catch (Exception e) {
/*     */       
/* 108 */       e.printStackTrace();
/*     */     } 
/* 110 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject downloadFileFinish(int download_method, int tag) {
/* 114 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 116 */       obj.put("type", "downloadfilefinish");
/* 117 */       obj.put("download_method", download_method);
/* 118 */       obj.put("tag", tag);
/* 119 */     } catch (JSONException e) {
/* 120 */       e.printStackTrace();
/*     */     } 
/* 122 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject downloadFile(String filename, int pos, int filetype, int download_method, int tag) {
/* 126 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 128 */       obj.put("type", "downloadfile");
/* 129 */       obj.put("filename", filename);
/* 130 */       obj.put("pos", pos);
/* 131 */       obj.put("filetype", filetype);
/* 132 */       obj.put("download_method", download_method);
/* 133 */       obj.put("tag", tag);
/*     */     }
/* 135 */     catch (JSONException e) {
/* 136 */       e.printStackTrace();
/*     */     } 
/* 138 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public JSONObject downloadFileStart(int method, int tag) {
/* 143 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 145 */       obj.put("type", "downloadfilestart");
/* 146 */       obj.put("download_method", method);
/* 147 */       obj.put("tag", tag);
/*     */     }
/* 149 */     catch (JSONException e) {
/* 150 */       e.printStackTrace();
/*     */     } 
/* 152 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject getFormatStatus() {
/* 156 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 158 */       obj.put("type", "getsdformat");
/*     */     }
/* 160 */     catch (JSONException e) {
/* 161 */       e.printStackTrace();
/*     */     } 
/* 163 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject NvramResetToDefault() {
/* 167 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 169 */       obj.put("type", "nvramresettodefault");
/*     */     }
/* 171 */     catch (JSONException e) {
/* 172 */       e.printStackTrace();
/*     */     } 
/* 174 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetOSDOnOff(int osd) {
/* 178 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 180 */       obj.put("type", "setosdonoff");
/* 181 */       obj.put("osd", osd);
/*     */     
/*     */     }
/* 184 */     catch (JSONException e) {
/* 185 */       e.printStackTrace();
/*     */     } 
/* 187 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetOSDStatus() {
/* 191 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 193 */       obj.put("type", "getosdstatus");
/*     */     
/*     */     }
/* 196 */     catch (JSONException e) {
/* 197 */       e.printStackTrace();
/*     */     } 
/* 199 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetRecordCapability() {
/* 203 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 205 */       obj.put("type", "getrecordcapability");
/*     */     
/*     */     }
/* 208 */     catch (JSONException e) {
/* 209 */       e.printStackTrace();
/*     */     } 
/* 211 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject StreamVideo(String filename, int mode, int filetype, String mask) {
/* 215 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 217 */       obj.put("type", "streamvideo");
/* 218 */       obj.put("live", mode);
/* 219 */       obj.put("filename", filename);
/* 220 */       obj.put("filetype", filetype);
/* 221 */       obj.put("mask", mask);
/*     */     }
/* 223 */     catch (JSONException e) {
/* 224 */       e.printStackTrace();
/*     */     } 
/* 226 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetGSensor(int sensitivity) {
/* 230 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 232 */       obj.put("type", "setgsensorparameter");
/* 233 */       obj.put("sensitivity", sensitivity);
/* 234 */     } catch (JSONException e) {
/* 235 */       e.printStackTrace();
/*     */     } 
/* 237 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetPowerFrequency(int frequency) {
/* 241 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 243 */       obj.put("type", "setpowerfrequency");
/* 244 */       obj.put("frequency", frequency);
/* 245 */     } catch (JSONException e) {
/*     */       
/* 247 */       e.printStackTrace();
/*     */     } 
/* 249 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public JSONObject SetVideoParameters(int bitrate, int fps, int resolution, int gop, int pre_ext_pframe_num, int pre_ext_qp_range, int pre_ext_qp_max, int pre_ext_upper_pframe, int pre_ext_upper_pframe_dup1, int pre_qp_max, int pre_qp_min, int resume, int suspend) {
/* 254 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 256 */       obj.put("type", "setvideoparameters");
/* 257 */       obj.put("bitrate", bitrate);
/* 258 */       obj.put("fps", fps);
/* 259 */       obj.put("resolution", resolution);
/* 260 */       obj.put("gop", gop);
/* 261 */       obj.put("pre_ext_pframe_num", pre_ext_pframe_num);
/* 262 */       obj.put("pre_ext_qp_range", pre_ext_qp_range);
/* 263 */       obj.put("pre_ext_qp_max", pre_ext_qp_max);
/* 264 */       obj.put("pre_ext_upper_pframe", pre_ext_upper_pframe);
/* 265 */       obj.put("pre_ext_upper_pframe_dup1", pre_ext_upper_pframe_dup1);
/* 266 */       obj.put("pre_qp_max", pre_qp_max);
/* 267 */       obj.put("pre_qp_min", pre_qp_min);
/* 268 */       obj.put("resume", resume);
/* 269 */       obj.put("suspend", suspend);
/*     */     }
/* 271 */     catch (JSONException e) {
/*     */       
/* 273 */       e.printStackTrace();
/*     */     } 
/* 275 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetVideoParameters(long bitrate, int fps, int resolution) {
/* 279 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 281 */       obj.put("type", "setvideoparameters");
/* 282 */       obj.put("bitrate", bitrate);
/* 283 */       obj.put("fps", fps);
/* 284 */       obj.put("resolution", resolution);
/*     */     
/*     */     }
/* 287 */     catch (JSONException e) {
/*     */       
/* 289 */       e.printStackTrace();
/*     */     } 
/* 291 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public JSONObject SetRecordParameters(int bitrate, int fps, int resolution, int gop, int rec_ext_pframe_num, int rec_ext_qp_range, int rec_ext_qp_max, int rec_ext_upper_pframe, int rec_qp_max, int rec_qp_min) {
/* 296 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 298 */       obj.put("type", "setrecordparameters");
/* 299 */       obj.put("bitrate", bitrate);
/* 300 */       obj.put("fps", fps);
/* 301 */       obj.put("resolution", resolution);
/* 302 */       obj.put("gop", gop);
/* 303 */       obj.put("rec_ext_pframe_num", rec_ext_pframe_num);
/* 304 */       obj.put("rec_ext_qp_range", rec_ext_qp_range);
/* 305 */       obj.put("rec_ext_qp_max", rec_ext_qp_max);
/* 306 */       obj.put("rec_ext_upper_pframe", rec_ext_upper_pframe);
/* 307 */       obj.put("rec_qp_max", rec_qp_max);
/* 308 */       obj.put("rec_qp_min", rec_qp_min);
/*     */     }
/* 310 */     catch (JSONException e) {
/*     */       
/* 312 */       e.printStackTrace();
/*     */     } 
/* 314 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetRecordParameters(long bitrate, int fps, int resolution) {
/* 318 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 320 */       obj.put("type", "setrecordparameters");
/* 321 */       obj.put("bitrate", bitrate);
/* 322 */       obj.put("fps", fps);
/* 323 */       obj.put("resolution", resolution);
/*     */     
/*     */     }
/* 326 */     catch (JSONException e) {
/*     */       
/* 328 */       e.printStackTrace();
/*     */     } 
/* 330 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetWiFiParameters(int channel, String ssid, String pwd) {
/* 334 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 336 */       obj.put("type", "setwifiparameters");
/* 337 */       obj.put("channel", channel);
/* 338 */       obj.put("ssid", ssid);
/* 339 */       obj.put("pwd", pwd);
/* 340 */     } catch (JSONException e) {
/*     */       
/* 342 */       e.printStackTrace();
/*     */     } 
/* 344 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetRecordLength(int minutes) {
/* 348 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 350 */       obj.put("type", "setrecordlength");
/* 351 */       obj.put("minutes", minutes);
/* 352 */     } catch (JSONException e) {
/*     */       
/* 354 */       e.printStackTrace();
/*     */     } 
/* 356 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetRecordAudioStatus(int level) {
/* 360 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 362 */       obj.put("type", "setrecordaudiostatus");
/* 363 */       obj.put("level", level);
/* 364 */     } catch (JSONException e) {
/*     */       
/* 366 */       e.printStackTrace();
/*     */     } 
/* 368 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetLoopRecordStatus(int status) {
/* 372 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 374 */       obj.put("type", "setlooprecordstatus");
/* 375 */       obj.put("status", status);
/* 376 */     } catch (JSONException e) {
/*     */       
/* 378 */       e.printStackTrace();
/*     */     } 
/* 380 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public JSONObject SendFWBin(int fileSize) {
/* 385 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 387 */       obj.put("type", "sendfwbin");
/* 388 */       obj.put("fileSize", fileSize);
/* 389 */     } catch (JSONException jSONException) {}
/*     */ 
/*     */     
/* 392 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject upgradeFW() {
/* 396 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 398 */       obj.put("type", "upgradefw");
/* 399 */     } catch (JSONException jSONException) {}
/*     */ 
/*     */     
/* 402 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject setWifi(int channel, String ssid, String pwd) {
/* 406 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 408 */       obj.put("type", "");
/* 409 */       obj.put("channel", channel);
/* 410 */       obj.put("ssid", ssid);
/* 411 */       obj.put("pwd", pwd);
/* 412 */     } catch (JSONException e) {
/*     */       
/* 414 */       e.printStackTrace();
/*     */     } 
/* 416 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetChannel(int channel) {
/* 420 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 422 */       obj.put("type", "setchannel");
/* 423 */       obj.put("channel", channel);
/* 424 */     } catch (JSONException e) {
/* 425 */       e.printStackTrace();
/*     */     } 
/* 427 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetPWD(String pwd) {
/* 431 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 433 */       obj.put("type", "setpwd");
/* 434 */       obj.put("pwd", pwd);
/* 435 */     } catch (JSONException e) {
/* 436 */       e.printStackTrace();
/*     */     } 
/* 438 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetWDR(int status) {
/* 442 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 444 */       obj.put("type", "setwdr");
/* 445 */       obj.put("status", status);
/* 446 */     } catch (JSONException e) {
/* 447 */       e.printStackTrace();
/*     */     } 
/* 449 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetMIRROR(int status) {
/* 453 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 455 */       obj.put("type", "setmirror");
/* 456 */       obj.put("status", status);
/* 457 */     } catch (JSONException e) {
/* 458 */       e.printStackTrace();
/*     */     } 
/* 460 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetFLIP(int status) {
/* 464 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 466 */       obj.put("type", "setflip");
/* 467 */       obj.put("status", status);
/* 468 */     } catch (JSONException e) {
/* 469 */       e.printStackTrace();
/*     */     } 
/* 471 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetSDSpace() {
/* 475 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 477 */       obj.put("type", "getsdspace");
/* 478 */     } catch (JSONException e) {
/* 479 */       e.printStackTrace();
/*     */     } 
/* 481 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetSDFormat() {
/* 485 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 487 */       obj.put("type", "setsdformat");
/* 488 */     } catch (JSONException e) {
/* 489 */       e.printStackTrace();
/*     */     } 
/* 491 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetRecordStatus(int status) {
/* 495 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 497 */       obj.put("type", "setrecordstatus");
/* 498 */       obj.put("status", status);
/* 499 */     } catch (JSONException e) {
/* 500 */       e.printStackTrace();
/*     */     } 
/* 502 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public JSONObject TakePicture(int pic_num) {
/* 507 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 509 */       obj.put("type", "takepicture");
/* 510 */       obj.put("pic_num", pic_num);
/* 511 */     } catch (JSONException e) {
/* 512 */       e.printStackTrace();
/*     */     } 
/* 514 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject TakePicture() {
/* 518 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 520 */       obj.put("type", "takepicture");
/*     */     }
/* 522 */     catch (JSONException e) {
/* 523 */       e.printStackTrace();
/*     */     } 
/* 525 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SyncTime(int year, int month, int day, int hour, int min, int sec, String timezone) {
/* 529 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 531 */       obj.put("type", "synctime");
/* 532 */       obj.put("year", year);
/* 533 */       obj.put("month", month);
/* 534 */       obj.put("day", day);
/* 535 */       obj.put("hour", hour);
/* 536 */       obj.put("min", min);
/* 537 */       obj.put("sec", sec);
/* 538 */       obj.put("timezone", timezone);
/* 539 */     } catch (JSONException e) {
/* 540 */       e.printStackTrace();
/*     */     } 
/* 542 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetTime(int status) {
/* 546 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 548 */       obj.put("type", "gettime");
/* 549 */       obj.put("status", status);
/* 550 */     } catch (JSONException e) {
/* 551 */       e.printStackTrace();
/*     */     } 
/* 553 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetBatteryStatus() {
/* 557 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 559 */       obj.put("type", "getbatterystatus");
/* 560 */     } catch (JSONException e) {
/* 561 */       e.printStackTrace();
/*     */     } 
/* 563 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetVideoList() {
/* 567 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 569 */       obj.put("type", "getvideolist");
/* 570 */     } catch (JSONException e) {
/* 571 */       e.printStackTrace();
/*     */     } 
/* 573 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject DownloadFile(String filename, int pos, int filetype, int tag) {
/* 577 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 579 */       obj.put("type", "downloadfile");
/* 580 */       obj.put("filename", filename);
/* 581 */       obj.put("pos", pos);
/* 582 */       obj.put("filetype", filetype);
/* 583 */       obj.put("tag", tag);
/* 584 */     } catch (JSONException e) {
/* 585 */       e.printStackTrace();
/*     */     } 
/* 587 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject DownloadFile(String filename, int pos, int filetype) {
/* 591 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 593 */       obj.put("type", "downloadfile");
/* 594 */       obj.put("filename", filename);
/* 595 */       obj.put("pos", pos);
/* 596 */       obj.put("filetype", filetype);
/* 597 */     } catch (JSONException e) {
/* 598 */       e.printStackTrace();
/*     */     } 
/* 600 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public JSONObject DownloadFileFinish(String filename, int filetype, int tag) {
/* 605 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 607 */       obj.put("type", "downloadfilefinish");
/* 608 */       obj.put("filename", filename);
/* 609 */       obj.put("filetype", filetype);
/* 610 */       obj.put("tag", tag);
/* 611 */     } catch (JSONException e) {
/* 612 */       e.printStackTrace();
/*     */     } 
/* 614 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject DownloadFileFinish(String filename, int filetype) {
/* 618 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 620 */       obj.put("type", "downloadfilefinish");
/* 621 */       obj.put("filename", filename);
/* 622 */       obj.put("filetype", filetype);
/* 623 */     } catch (JSONException e) {
/* 624 */       e.printStackTrace();
/*     */     } 
/* 626 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetIndexFile(int type) {
/* 630 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 632 */       obj.put("type", "getindexfile");
/* 633 */       obj.put("list", type);
/* 634 */     } catch (JSONException e) {
/* 635 */       e.printStackTrace();
/*     */     } 
/* 637 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject DeleteFile(String filename, int filetype) {
/* 641 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 643 */       obj.put("type", "deletefile");
/* 644 */       obj.put("filename", filename);
/* 645 */       obj.put("filetype", filetype);
/* 646 */     } catch (JSONException e) {
/* 647 */       e.printStackTrace();
/*     */     } 
/* 649 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject StreamVideo(String filename, int mode, int filetype) {
/* 653 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 655 */       obj.put("type", "streamvideo");
/* 656 */       obj.put("live", mode);
/* 657 */       obj.put("filename", filename);
/* 658 */       obj.put("filetype", filetype);
/* 659 */     } catch (JSONException e) {
/* 660 */       e.printStackTrace();
/*     */     } 
/* 662 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject StreamVideoFinish(String filename, String mask) {
/* 666 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 668 */       obj.put("type", "streamvideofinish");
/* 669 */       obj.put("filename", filename);
/* 670 */       obj.put("mask", mask);
/* 671 */     } catch (JSONException e) {
/* 672 */       e.printStackTrace();
/*     */     } 
/* 674 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject StreamVideoFinish(String filename) {
/* 678 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 680 */       obj.put("type", "streamvideofinish");
/* 681 */       obj.put("filename", filename);
/* 682 */     } catch (JSONException e) {
/* 683 */       e.printStackTrace();
/*     */     } 
/* 685 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SendFontFile(String show) {
/* 689 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 691 */       obj.put("type", "sendfontfile");
/* 692 */       obj.put("showString", show);
/* 693 */     } catch (JSONException e) {
/* 694 */       e.printStackTrace();
/*     */     } 
/* 696 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SendFontFile(int[] show, int fileSize) {
/* 700 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 702 */       JSONArray array = new JSONArray();
/* 703 */       for (int i = 0; i < show.length; i++) {
/* 704 */         JSONObject json = new JSONObject();
/* 705 */         json.put("unicode", show[i]);
/* 706 */         array.put(json);
/*     */       } 
/* 708 */       obj.put("type", "sendfontfile");
/* 709 */       obj.put("showString", array);
/* 710 */       obj.put("fileSize", fileSize);
/* 711 */     } catch (JSONException e) {
/* 712 */       e.printStackTrace();
/*     */     } 
/* 714 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetDeviceParameter() {
/* 718 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 720 */       obj.put("type", "getdeviceparameter");
/* 721 */     } catch (JSONException e) {
/* 722 */       e.printStackTrace();
/*     */     } 
/* 724 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetRTSPURL(String filename) {
/* 728 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 730 */       obj.put("type", "getrtspurl");
/* 731 */       obj.put("filename", filename);
/* 732 */     } catch (JSONException e) {
/* 733 */       e.printStackTrace();
/*     */     } 
/* 735 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetVideoStatus() {
/* 739 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 741 */       obj.put("type", "getvideostatus");
/* 742 */     } catch (JSONException e) {
/* 743 */       e.printStackTrace();
/*     */     } 
/* 745 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject GetRecordStatus() {
/* 749 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 751 */       obj.put("type", "getrecordstatus");
/* 752 */     } catch (JSONException e) {
/* 753 */       e.printStackTrace();
/*     */     } 
/* 755 */     return obj;
/*     */   }
/*     */   
/*     */   public JSONObject SetUsbdClassStatus(int status) {
/* 759 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 761 */       obj.put("type", "usbdclassmode");
/* 762 */       obj.put("usbdclassmode", status);
/* 763 */     } catch (JSONException e) {
/* 764 */       e.printStackTrace();
/*     */     } 
/* 766 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSONObject getHeartBeatByte() {
/* 774 */     JSONObject obj = new JSONObject();
/*     */     try {
/* 776 */       obj.put("type", "heartbeat");
/* 777 */     } catch (JSONException e) {
/* 778 */       e.printStackTrace();
/*     */     } 
/* 780 */     return obj;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\device\DeviceCommand.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */