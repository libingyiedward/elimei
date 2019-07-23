/*     */ package com.device;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class PreviewModeParse
/*     */ {
/*  22 */   int FPS = 0;
/*  23 */   int bitrate = 0;
/*  24 */   int resolution = 0;
/*     */ 
/*     */   
/*  27 */   private List<Map<String, Object>> CapabilityList = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   String FHD_30_8_DOT_5M = "FHD, 30fps, LOW";
/*  36 */   String FHD_30_10M = "FHD, 30fps, MID";
/*  37 */   String FHD_30_12M = "FHD, 30fps, HIGH";
/*     */ 
/*     */   
/*  40 */   String HD_60_6M = "HD, 60fps, LOW";
/*  41 */   String HD_60_8M = "HD, 60fps, MID";
/*  42 */   String HD_60_10M = "HD, 60fps, HIGH";
/*     */   
/*  44 */   String HD_45_4_dot_5M = "HD, 45fps, LOW";
/*  45 */   String HD_45_6M = "HD, 45fps, MID";
/*  46 */   String HD_45_7_dot_5M = "HD, 45fps, HIGH";
/*     */ 
/*     */   
/*  49 */   String HD_30_3M = "HD, 30fps, LOW";
/*  50 */   String HD_30_4M = "HD, 30fps, MID";
/*  51 */   String HD_30_5M = "HD, 30fps, HIGH";
/*  52 */   String VGA_60_2M = "VGA, 60fps";
/*     */ 
/*     */   
/*  55 */   String HD_60_2M = "HD, 60fps";
/*  56 */   String HD_45_1_DOT_5M = "HD, 45fps, LOW";
/*  57 */   String HD_45_2M = "HD, 45fps, HIGH";
/*     */   
/*  59 */   String HD_30_1M = "HD, 30fps, LOW";
/*  60 */   String HD_30_1_DOT_2M = "HD, 30fps, MID";
/*  61 */   String HD_30_1_DOT_5M = "HD, 30fps, HIGH";
/*     */   
/*  63 */   String HD_20_1M = "HD, 20fps, LOW";
/*  64 */   String HD_20_1_DOT_2M = "HD, 20fps, HIGH";
/*     */   
/*  66 */   String HD_15_1M = "HD, 15fps";
/*     */   
/*  68 */   String HD_12_1M = "HD, 12fps"; private static final int OPTION_FPS = 0;
/*  69 */   String VGA_30_700K = "VGA, 30fps";
/*     */   private static final int OPTION_BITRATE = 1;
/*  71 */   String VGA_15_500K = "VGA, 15fps"; private static final int OPTION_RESOLUTION = 2;
/*     */   private static final int OPTION_DESCRIBE = 3;
/*     */   
/*  74 */   private void setFPS(int Fps) { this.FPS = Fps; }
/*     */   public static final int OPTION_VGA = 0;
/*     */   public static final int OPTION_HD = 1;
/*     */   
/*  78 */   private void setResolution(int resolution) { this.resolution = resolution; }
/*     */   
/*     */   public static final int OPTION_FHD = 2;
/*     */   
/*  82 */   private void setBitrate(int bitrate) { this.FPS = bitrate; }
/*     */   public static final int SHOW_OPTION_PREVIEW = 0;
/*     */   public static final int SHOW_OPTION_PLAYBACK = 1;
/*     */   
/*     */   public void parseCapability(int para, int showSelect) {
/*  87 */     this.CapabilityList.clear();
/*  88 */     for (int i = 0; i < 32; i++) {
/*     */       
/*  90 */       int mfps = 0;
/*  91 */       int mresolution = 0;
/*  92 */       int mbitrate = 0;
/*  93 */       int tmpInt = (byte)(para >> i) & 1;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       if (tmpInt == 1)
/*     */       {
/* 100 */         if ((showSelect == 0) ? (
/* 101 */           i <= 12) : (
/*     */ 
/*     */           
/* 104 */           showSelect == 1 && 
/* 105 */           i > 12)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 111 */           String[] tmp = getDeviceParameterDef(i);
/* 112 */           Map<String, Object> optionMap = new HashMap<String, Object>();
/* 113 */           optionMap.put("describe", tmp[3]);
/* 114 */           optionMap.put("fps", tmp[0]);
/* 115 */           optionMap.put("resolution", tmp[2]);
/* 116 */           optionMap.put("bitrate", tmp[1]);
/* 117 */           this.CapabilityList.add(optionMap);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getShowArrayPosition(int resolution, int fps, int bitrate) {
/* 126 */     int tmp = -1;
/* 127 */     for (int i = 0; i < this.CapabilityList.size(); i++) {
/* 128 */       if (Integer.valueOf((String)((Map)this.CapabilityList.get(i)).get("resolution")).intValue() == resolution && 
/* 129 */         Integer.valueOf((String)((Map)this.CapabilityList.get(i)).get("fps")).intValue() == fps && 
/* 130 */         Integer.valueOf((String)((Map)this.CapabilityList.get(i)).get("bitrate")).intValue() == bitrate) {
/* 131 */         tmp = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     return tmp;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getShowArray() {
/* 142 */     String[] tmp = new String[this.CapabilityList.size()];
/* 143 */     for (int i = 0; i < this.CapabilityList.size(); i++) {
/* 144 */       tmp[i] = (String)((Map)this.CapabilityList.get(i)).get("describe");
/*     */     }
/* 146 */     return tmp;
/*     */   }
/*     */   
/*     */   private String[] getDeviceParameterDef(int opt) {
/* 150 */     String[] res = new String[4];
/*     */     
/* 152 */     switch (opt) {
/*     */       
/*     */       case 0:
/* 155 */         res[0] = String.valueOf(30);
/* 156 */         res[1] = String.valueOf(8912896);
/* 157 */         res[2] = String.valueOf(2);
/* 158 */         res[3] = this.FHD_30_8_DOT_5M;
/*     */         break;
/*     */       
/*     */       case 1:
/* 162 */         res[0] = String.valueOf(30);
/* 163 */         res[1] = String.valueOf(10485760);
/* 164 */         res[2] = String.valueOf(2);
/* 165 */         res[3] = this.FHD_30_10M;
/*     */         break;
/*     */       case 2:
/* 168 */         res[0] = String.valueOf(30);
/* 169 */         res[1] = String.valueOf(12582912);
/* 170 */         res[2] = String.valueOf(2);
/* 171 */         res[3] = this.FHD_30_12M;
/*     */         break;
/*     */       case 3:
/* 174 */         res[0] = String.valueOf(60);
/* 175 */         res[1] = String.valueOf(6291456);
/* 176 */         res[2] = String.valueOf(1);
/* 177 */         res[3] = this.HD_60_6M;
/*     */         break;
/*     */       case 4:
/* 180 */         res[0] = String.valueOf(60);
/* 181 */         res[1] = String.valueOf(8388608);
/* 182 */         res[2] = String.valueOf(1);
/* 183 */         res[3] = this.HD_60_8M;
/*     */         break;
/*     */       case 5:
/* 186 */         res[0] = String.valueOf(60);
/* 187 */         res[1] = String.valueOf(10485760);
/* 188 */         res[2] = String.valueOf(1);
/* 189 */         res[3] = this.HD_60_10M;
/*     */         break;
/*     */       case 6:
/* 192 */         res[0] = String.valueOf(30);
/* 193 */         res[1] = String.valueOf(3145728);
/* 194 */         res[2] = String.valueOf(1);
/* 195 */         res[3] = this.HD_30_3M;
/*     */         break;
/*     */       case 7:
/* 198 */         res[0] = String.valueOf(30);
/* 199 */         res[1] = String.valueOf(4194304);
/* 200 */         res[2] = String.valueOf(1);
/* 201 */         res[3] = this.HD_30_4M;
/*     */         break;
/*     */       case 8:
/* 204 */         res[0] = String.valueOf(30);
/* 205 */         res[1] = String.valueOf(5242880);
/* 206 */         res[2] = String.valueOf(1);
/* 207 */         res[3] = this.HD_30_5M;
/*     */         break;
/*     */       case 9:
/* 210 */         res[0] = String.valueOf(45);
/* 211 */         res[1] = String.valueOf(4718592);
/* 212 */         res[2] = String.valueOf(1);
/* 213 */         res[3] = this.HD_45_4_dot_5M;
/*     */         break;
/*     */       case 10:
/* 216 */         res[0] = String.valueOf(45);
/* 217 */         res[1] = String.valueOf(6291456);
/* 218 */         res[2] = String.valueOf(1);
/* 219 */         res[3] = this.HD_45_6M;
/*     */         break;
/*     */       case 11:
/* 222 */         res[0] = String.valueOf(45);
/* 223 */         res[1] = String.valueOf(7864320);
/* 224 */         res[2] = String.valueOf(1);
/* 225 */         res[3] = this.HD_45_7_dot_5M;
/*     */         break;
/*     */       case 12:
/* 228 */         res[0] = String.valueOf(60);
/* 229 */         res[1] = String.valueOf(2097152);
/* 230 */         res[2] = String.valueOf(0);
/* 231 */         res[3] = this.VGA_60_2M;
/*     */         break;
/*     */       case 13:
/* 234 */         res[0] = String.valueOf(60);
/* 235 */         res[1] = String.valueOf(2097152);
/* 236 */         res[2] = String.valueOf(1);
/* 237 */         res[3] = this.HD_60_2M;
/*     */         break;
/*     */       case 14:
/* 240 */         res[0] = String.valueOf(45);
/* 241 */         res[1] = String.valueOf(1572864);
/* 242 */         res[2] = String.valueOf(1);
/* 243 */         res[3] = this.HD_45_1_DOT_5M;
/*     */         break;
/*     */       case 15:
/* 246 */         res[0] = String.valueOf(45);
/* 247 */         res[1] = String.valueOf(2097152);
/* 248 */         res[2] = String.valueOf(1);
/* 249 */         res[3] = this.HD_45_2M;
/*     */         break;
/*     */       
/*     */       case 16:
/* 253 */         res[0] = String.valueOf(30);
/* 254 */         res[1] = String.valueOf(1048576);
/* 255 */         res[2] = String.valueOf(1);
/* 256 */         res[3] = this.HD_30_1M;
/*     */         break;
/*     */       case 17:
/* 259 */         res[0] = String.valueOf(30);
/* 260 */         res[1] = String.valueOf(1258291);
/* 261 */         res[2] = String.valueOf(1);
/* 262 */         res[3] = this.HD_30_1_DOT_2M;
/*     */         break;
/*     */       case 18:
/* 265 */         res[0] = String.valueOf(30);
/* 266 */         res[1] = String.valueOf(1572864);
/* 267 */         res[2] = String.valueOf(1);
/* 268 */         res[3] = this.HD_30_1_DOT_5M;
/*     */         break;
/*     */       case 19:
/* 271 */         res[0] = String.valueOf(20);
/* 272 */         res[1] = String.valueOf(1048576);
/* 273 */         res[2] = String.valueOf(1);
/* 274 */         res[3] = this.HD_20_1M;
/*     */         break;
/*     */       case 20:
/* 277 */         res[0] = String.valueOf(20);
/* 278 */         res[1] = String.valueOf(1258291);
/* 279 */         res[2] = String.valueOf(1);
/* 280 */         res[3] = this.HD_20_1_DOT_2M;
/*     */         break;
/*     */       case 22:
/* 283 */         res[0] = String.valueOf(15);
/* 284 */         res[1] = String.valueOf(1048576);
/* 285 */         res[2] = String.valueOf(1);
/* 286 */         res[3] = this.HD_15_1M;
/*     */         break;
/*     */       case 25:
/* 289 */         res[0] = String.valueOf(12);
/* 290 */         res[1] = String.valueOf(1048576);
/* 291 */         res[2] = String.valueOf(1);
/* 292 */         res[3] = this.HD_12_1M;
/*     */         break;
/*     */       case 28:
/* 295 */         res[0] = String.valueOf(30);
/* 296 */         res[1] = String.valueOf(716800);
/* 297 */         res[2] = String.valueOf(0);
/* 298 */         res[3] = this.VGA_30_700K;
/*     */         break;
/*     */       case 30:
/* 301 */         res[0] = String.valueOf(15);
/* 302 */         res[1] = String.valueOf(512000);
/* 303 */         res[2] = String.valueOf(0);
/* 304 */         res[3] = this.VGA_15_500K;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 316 */   public int getFPS(int position) { return Integer.valueOf((String)((Map)this.CapabilityList.get(position)).get("fps")).intValue(); }
/*     */ 
/*     */ 
/*     */   
/* 320 */   public int getResolution(int position) { return Integer.valueOf((String)((Map)this.CapabilityList.get(position)).get("resolution")).intValue(); }
/*     */ 
/*     */ 
/*     */   
/* 324 */   public int getBitrate(int position) { return Integer.valueOf((String)((Map)this.CapabilityList.get(position)).get("bitrate")).intValue(); }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\device\PreviewModeParse.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */