/*     */ package com.elimei.elimei;
/*     */ 
/*     */ import android.app.ProgressDialog;
/*     */ import android.content.Intent;
/*     */ import android.databinding.DataBindingUtil;
/*     */ import android.graphics.Color;
/*     */ import android.os.Bundle;
/*     */ import android.support.v7.app.AppCompatActivity;
/*     */ import android.text.TextUtils;
/*     */ import android.view.View;
/*     */ import com.elimei.R;
import com.elimei.databinding.ActivityMemberHistoryBinding;
import com.elimei.elimei.Model.HistoryModel;
/*     */ import com.elimei.elimei.Model.MemBerModel;
/*     */ import com.elimei.elimei.Model.Result;
/*     */ import com.elimei.elimei.Model.TextModel;
/*     */ import com.elimei.elimei.Presenter.MemberPresenter;
/*     */ import com.elimei.elimei.charts.BarChart;
/*     */ import com.elimei.elimei.components.AxisBase;
/*     */ import com.elimei.elimei.components.Legend;
/*     */ import com.elimei.elimei.components.XAxis;
/*     */ import com.elimei.elimei.components.YAxis;
/*     */ import com.elimei.elimei.data.BarData;
/*     */ import com.elimei.elimei.data.BarDataSet;
/*     */ import com.elimei.elimei.data.BarEntry;

/*     */ import com.elimei.elimei.formatter.IAxisValueFormatter;
/*     */ import com.elimei.elimei.interfaces.datasets.IBarDataSet;
/*     */ import com.elimei.elimei.utils.ImmersionBar;
/*     */ import com.google.gson.Gson;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class MemberHistoryActivity
/*     */   extends AppCompatActivity
/*     */ {
/*     */   private ActivityMemberHistoryBinding binding;
/*     */   private ProgressDialog progressDialog;
/*     */   private String data;
/*     */   private HistoryModel model;
/*     */   private MemBerModel.ResultBean.MemberBean memberBean;
/*     */   
/*     */   protected void onCreate(Bundle savedInstanceState) {
/*  44 */     super.onCreate(savedInstanceState);
/*  45 */     this.binding = (ActivityMemberHistoryBinding)DataBindingUtil.setContentView(this, R.layout.activity_member_history);
/*  46 */     MemberPresenter memberPresenter = new MemberPresenter(this.binding);
/*  47 */     ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();
/*  48 */     this.binding.back.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View view) {
/*  51 */             MemberHistoryActivity.this.finish();
/*     */           }
/*     */         });
/*  54 */     this.data = getIntent().getStringExtra("data");
/*  55 */     Serializable model = getIntent().getSerializableExtra("model");
/*  56 */     this.memberBean = (MemBerModel.ResultBean.MemberBean)model;
/*  57 */     String tele = getIntent().getStringExtra("tele");
/*  58 */     String name = getIntent().getStringExtra("name");
/*  59 */     this.binding.no.setText(this.data);
/*  60 */     this.binding.name.setText(name);
/*  61 */     this.binding.tele.setText(tele);
/*     */     
/*  63 */     initdata();
/*  64 */     initclick();
/*     */   }
/*     */   
/*     */   private void initclick() {
/*  68 */     this.binding.danxiang1.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View view) {
/*  71 */             MemberHistoryActivity.this.initmodel(1, null);
/*     */           }
/*     */         });
/*  74 */     this.binding.danxiang2.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View view) {
/*  77 */             MemberHistoryActivity.this.initmodel(2, null);
/*     */           }
/*     */         });
/*  80 */     this.binding.danxiang3.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View view) {
/*  83 */             MemberHistoryActivity.this.initmodel(3, null);
/*     */           }
/*     */         });
/*  86 */     this.binding.zonghe1.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View view) {
/*  89 */             MemberHistoryActivity.this.initmodel(1, "1");
/*     */           }
/*     */         });
/*  92 */     this.binding.zonghe2.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View view) {
/*  95 */             MemberHistoryActivity.this.initmodel(2, "1");
/*     */           }
/*     */         });
/*  98 */     this.binding.zonghe3.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View view) {
/* 101 */             MemberHistoryActivity.this.initmodel(3, "1");
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void initmodel(int i, String a) {
/* 107 */     HistoryModel.DataBean bean = null;
/* 108 */     Result result = new Result();
/* 109 */     switch (i) {
/*     */       case 1:
/* 111 */         bean = (HistoryModel.DataBean)this.model.getData().get(0);
/*     */         break;
/*     */       case 2:
/* 114 */         bean = (HistoryModel.DataBean)this.model.getData().get(1);
/*     */         break;
/*     */       case 3:
/* 117 */         bean = (HistoryModel.DataBean)this.model.getData().get(2);
/*     */         break;
/*     */     } 
/* 120 */     TextModel.DataBean dataBean = new TextModel.DataBean();
/*     */     
/* 122 */     dataBean.setPoreSizeProductId(bean.getPoreSizeProductId());
/* 123 */     dataBean.setPoreSizeProductList(bean.getPoreSizeProductList());
/* 124 */     dataBean.setPoreSizeQuestion(bean.getPoreSizeQuestion());
/* 125 */     dataBean.setPoreSizeStallText(bean.getPoreSizeStallText());
/* 126 */     dataBean.setPoreSizeTips(bean.getPoreSizeTips());
/* 127 */     result.setPoreSizeValue(bean.getPoreSizeValue());
/*     */     
/* 129 */     dataBean.setInflammationProductId(bean.getInflammationProductId());
/* 130 */     dataBean.setInflammationProductList(bean.getInflammationProductList());
/* 131 */     dataBean.setInflammationQuestion(bean.getInflammationQuestion());
/* 132 */     dataBean.setInflammationTips(bean.getInflammationTips());
/* 133 */     dataBean.setInflammationStallText(bean.getInflammationStallText());
/* 134 */     result.setInflammationValue(bean.getInflammationValue());
/*     */     
/* 136 */     dataBean.setPigmentProductId(bean.getPigmentProductId());
/* 137 */     dataBean.setPigmentProductList(bean.getPigmentProductList());
/* 138 */     dataBean.setPigmentQuestion(bean.getPigmentQuestion());
/* 139 */     dataBean.setPigmentStallText(bean.getPigmentStallText());
/* 140 */     dataBean.setPigmentTips(bean.getPigmentTips());
/* 141 */     result.setPigmentValue(bean.getPigmentValue());
/*     */     
/* 143 */     dataBean.setSkinColorQuestion(bean.getSkinColorQuestion());
/* 144 */     dataBean.setSkinColorStallText(bean.getSkinColorStallText());
/* 145 */     dataBean.setSkinColorTips(bean.getSkinColorTips());
/* 146 */     dataBean.setSkinColorProductId(bean.getSkinColorProductId());
/* 147 */     dataBean.setSkinColorProductList(bean.getSkinColorProductList());
/* 148 */     result.setSkinColorValue(bean.getSkinColorValue());
/*     */     
/* 150 */     dataBean.setWrinkleProductId(bean.getWrinkleProductId());
/* 151 */     dataBean.setWrinkleProductList(bean.getWrinkleProductList());
/* 152 */     dataBean.setWrinkleQuestion(bean.getWrinkleQuestion());
/* 153 */     dataBean.setWrinkleStallText(bean.getWrinkleStallText());
/* 154 */     dataBean.setWrinkleTips(bean.getWrinkleTips());
/* 155 */     result.setWrinkleValue(bean.getWrinkleValue());
/*     */     
/* 157 */     dataBean.setTextureProductId(bean.getTextureProductId());
/* 158 */     dataBean.setTextureProductList(bean.getTextureProductList());
/* 159 */     dataBean.setTextureQuestion(bean.getTextureQuestion());
/* 160 */     dataBean.setTextureStallText(bean.getTextureStallText());
/* 161 */     dataBean.setTextureTips(bean.getTextureTips());
/* 162 */     result.setTextureValue(bean.getTextureValue());
/*     */     
/* 164 */     dataBean.setOilProductId(bean.getOilProductId());
/* 165 */     dataBean.setOilProductList(bean.getOilProductList());
/* 166 */     dataBean.setOilQuestion(bean.getOilQuestion());
/* 167 */     dataBean.setOilStallText(bean.getOilStallText());
/* 168 */     dataBean.setOilTips(bean.getOilTips());
/* 169 */     result.setOilValue(bean.getOilValue());
/*     */     
/* 171 */     dataBean.setWaterSkinQuestion(bean.getWaterSkinQuestion());
/* 172 */     dataBean.setWaterStallText(bean.getWaterStallText());
/* 173 */     dataBean.setWaterProductId(bean.getWaterProductId());
/* 174 */     dataBean.setWaterTips(bean.getWaterTips());
/* 175 */     dataBean.setWaterProductList(bean.getWaterProductList());
/* 176 */     result.setWaterDegreeValue(bean.getWaterDegreeValue());
/*     */     
/* 178 */     dataBean.setProductImageDir(bean.getProductImageDir());
/* 179 */     dataBean.setSynthesizeItem(bean.getSynthesizeItem());
/* 180 */     result.setRealAge(bean.getRealAge());
/* 181 */     result.setMathAge(bean.getMathAge());
/*     */     
/* 183 */     dataBean.setSkinColorProtectSkinWIKI(bean.getSkinColorProtectSkinWIKI());
/* 184 */     dataBean.setPoreSizeProtectSkinWIKI(bean.getPoreSizeProtectSkinWIKI());
/* 185 */     dataBean.setPigmentProtectSkinWIKI(bean.getPigmentProtectSkinWIKI());
/* 186 */     dataBean.setInflammationProtectSkinWIKI(bean.getInflammationProtectSkinWIKI());
/* 187 */     dataBean.setWaterProtectSkinWIKI(bean.getWaterProtectSkinWIKI());
/* 188 */     dataBean.setWrinkleProtectSkinWIKI(bean.getWrinkleProtectSkinWIKI());
/* 189 */     dataBean.setTextureProtectSkinWIKI(bean.getTextureProtectSkinWIKI());
/* 190 */     dataBean.setOilProtectSkinWIKI(bean.getOilProtectSkinWIKI());
/* 191 */     result.setCompositeScore(bean.getCompositeScore());
/* 192 */     String[] split = bean.getImageName().split(",");
/*     */     
/* 194 */     ArrayList<String> list = new ArrayList<String>();
/* 195 */     for (String s : split) {
/* 196 */       list.add(bean.getTestImageDir() + "/" + s);
/*     */     }
/* 198 */     Intent intent = new Intent(getApplicationContext(), TestResultActivity.class);
/* 199 */     intent.putExtra("image", list);
/* 200 */     TextModel textModel = new TextModel();
/* 201 */     textModel.setData(dataBean);
/* 202 */     textModel.setMsg("成功");
/* 203 */     textModel.setResult("1");
/* 204 */     String s = (new Gson()).toJson(result);
/* 205 */     String s1 = (new Gson()).toJson(textModel);
/* 206 */     if (!TextUtils.isEmpty(a)) {
/* 207 */       intent.putExtra("showposition", "zonghe");
/*     */     }
/* 209 */     intent.putExtra("Model", this.memberBean);
/* 210 */     intent.putExtra("data", s);
/* 211 */     intent.putExtra("text", s1);
/* 212 */     startActivity(intent);
/*     */   }
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
/*     */   private void initdata() {
/* 240 */     String json = getIntent().getStringExtra("json");
/* 241 */     this.model = (HistoryModel)(new Gson()).fromJson(json, HistoryModel.class);
/* 242 */     initshuju();
/*     */   }
/*     */   
/*     */   private void initshuju() {
/* 246 */     List<HistoryModel.DataBean> data = this.model.getData();
/* 247 */     this.binding.l1.setVisibility(View.GONE);
/* 248 */     this.binding.l2.setVisibility(View.GONE );
/* 249 */     this.binding.l3.setVisibility(View.GONE);
/* 250 */     if (data.size() > 0) {
/* 251 */       this.binding.l1.setVisibility(View.VISIBLE);
/* 252 */       this.binding.time1.setText(((HistoryModel.DataBean)data.get(0)).getResultCompleteTime());
/*     */     } 
/* 254 */     if (data.size() > 1) {
/* 255 */       this.binding.l2.setVisibility(View.VISIBLE);
/* 256 */       this.binding.time2.setText(((HistoryModel.DataBean)data.get(1)).getResultCompleteTime());
/*     */     } 
/* 258 */     if (data.size() == 3) {
/* 259 */       this.binding.l3.setVisibility(View.VISIBLE);
/* 260 */       this.binding.time3.setText(((HistoryModel.DataBean)data.get(2)).getResultCompleteTime());
/*     */     } 
/* 262 */     initbarchat();
/*     */   }
/*     */   
/*     */   private void initbarchat() {
/* 266 */     BarChart mChart = this.binding.chart;
/* 267 */     int size = this.model.getData().size();
/* 268 */     mChart.setContentDescription("测试");
/* 269 */     mChart.getDescription().setEnabled(false);
/* 270 */     mChart.setClickable(false);
/* 271 */     mChart.setHighlightPerDragEnabled(false);
/* 272 */     mChart.setTouchEnabled(false);
/* 273 */     if (size > 1) {
/* 274 */       Legend l = mChart.getLegend();
/* 275 */       mChart.getXAxis().setAxisMinValue(0.0F);
/* 276 */       l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
/* 277 */       l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
/* 278 */       l.setDrawInside(false);
/* 279 */       l.setYOffset(0.0F);
/* 280 */       l.setYEntrySpace(0.0F);
/* 281 */       l.setTextSize(8.0F);
/*     */       
/* 283 */       XAxis xAxis = mChart.getXAxis();
/* 284 */       xAxis.setGranularity(1.0F);
/* 285 */       xAxis.setCenterAxisLabels(true);
/* 286 */       xAxis.setTextSize(10.0F);
/* 287 */       xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
/* 288 */       xAxis.setDrawAxisLine(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 295 */       xAxis.setValueFormatter(new IAxisValueFormatter()
/*     */           {
/* 297 */             protected String[] mMonths = { "水分", "油脂", "纹理", "皱纹", "肤色", "色素", "疲劳", "毛孔" };
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public String getFormattedValue(float value, AxisBase axis) {
/* 303 */               int value1 = (int)value;
/* 304 */               if (value1 < 0) {
/* 305 */                 value1 = 0;
/*     */               }
/* 307 */               if (value1 > 7) {
/* 308 */                 value1 = 7;
/*     */               }
/* 310 */               return this.mMonths[value1];
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 315 */             public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*     */ 
/*     */ 
/*     */             
/*     */             public int GetColor(float value) {
/* 320 */               return 0;
/*     */             }
/*     */           });
/* 323 */       YAxis leftAxis = mChart.getAxisLeft();
/* 324 */       leftAxis.setValueFormatter(new IAxisValueFormatter()
/*     */           {
/* 326 */             protected String[] mMonths = { "水分", "油脂", "纹理", "皱纹", "肤色", "色素", "疲劳", "毛孔" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 332 */             public String getFormattedValue(float value, AxisBase axis) { return String.valueOf((int)value); }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 337 */             public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*     */ 
/*     */ 
/*     */             
/*     */             public int GetColor(float value) {
/* 342 */               return 0;
/*     */             }
/*     */           });
/* 345 */       leftAxis.setDrawGridLines(true);
/* 346 */       leftAxis.setSpaceTop(35.0F);
/* 347 */       leftAxis.setAxisMinimum(0.0F);
/* 348 */       leftAxis.setAxisMaximum(10.0F);
/* 349 */       mChart.getAxisRight().setEnabled(false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 389 */       Legend l = mChart.getLegend();
/* 390 */       l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
/* 391 */       l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
/* 392 */       l.setDrawInside(false);
/* 393 */       l.setYOffset(0.0F);
/* 394 */       l.setYEntrySpace(0.0F);
/* 395 */       l.setTextSize(8.0F);
/* 396 */       mChart.setPinchZoom(false);
/*     */       
/* 398 */       mChart.setDrawBarShadow(false);
/* 399 */       mChart.setDrawGridBackground(false);
/*     */       
/* 401 */       XAxis xAxis = mChart.getXAxis();
/* 402 */       xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
/* 403 */       xAxis.setDrawGridLines(false);
/*     */       
/* 405 */       mChart.getAxisLeft().setDrawGridLines(false);
/* 406 */       xAxis.setValueFormatter(new IAxisValueFormatter()
/*     */           {
/* 408 */             protected String[] mMonths = { "水分", "油脂", "纹理", "皱纹", "肤色", "色素", "疲劳", "毛孔" };
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public String getFormattedValue(float value, AxisBase axis) {
/* 414 */               int value1 = (int)value;
/* 415 */               if (value1 < 0) {
/* 416 */                 value1 = 0;
/*     */               }
/* 418 */               if (value1 > 7) {
/* 419 */                 value1 = 7;
/*     */               }
/* 421 */               return this.mMonths[value1];
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 426 */             public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*     */ 
/*     */ 
/*     */             
/*     */             public int GetColor(float value) {
/* 431 */               return 0;
/*     */             }
/*     */           });
/* 434 */       YAxis leftAxis = mChart.getAxisLeft();
/* 435 */       leftAxis.setValueFormatter(new IAxisValueFormatter()
/*     */           {
/* 437 */             protected String[] mMonths = { "水分", "油脂", "纹理", "皱纹", "肤色", "色素", "疲劳", "毛孔" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 443 */             public String getFormattedValue(float value, AxisBase axis) { return String.valueOf((int)value); }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 448 */             public int getFormattedColor(float value, AxisBase axisBase) { return 0; }
/*     */ 
/*     */ 
/*     */             
/*     */             public int GetColor(float value) {
/* 453 */               return 0;
/*     */             }
/*     */           });
/* 456 */       leftAxis.setDrawGridLines(true);
/* 457 */       leftAxis.setSpaceTop(35.0F);
/* 458 */       leftAxis.setAxisMinimum(0.0F);
/* 459 */       leftAxis.setAxisMaximum(10.0F);
/* 460 */       mChart.getAxisRight().setEnabled(false);
/*     */     } 
/* 462 */     ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
/* 463 */     ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
/* 464 */     ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
/* 465 */     BarData data = null;
/* 466 */     BarDataSet set1 = null, set2 = null, set3 = null;
/*     */     
/* 468 */     int groupcount = 8;
/* 469 */     float groupSpace = 0.08F;
/* 470 */     float barSpace = 0.02F;
/* 471 */     float barWidth = 0.45F;
/* 472 */     if (size > 0) {
/* 473 */       HistoryModel.DataBean bean = (HistoryModel.DataBean)this.model.getData().get(0);
/* 474 */       yVals1.add(new BarEntry(0.0F, (float)bean.getWaterDegreeValue()));
/* 475 */       yVals1.add(new BarEntry(1.0F, (float)bean.getOilValue()));
/* 476 */       yVals1.add(new BarEntry(2.0F, (float)bean.getTextureValue()));
/* 477 */       yVals1.add(new BarEntry(3.0F, (float)bean.getWrinkleValue()));
/* 478 */       yVals1.add(new BarEntry(4.0F, (float)bean.getSkinColorValue()));
/* 479 */       yVals1.add(new BarEntry(5.0F, (float)bean.getPigmentValue()));
/* 480 */       yVals1.add(new BarEntry(6.0F, (float)bean.getInflammationValue()));
/* 481 */       yVals1.add(new BarEntry(7.0F, (float)bean.getPoreSizeValue()));
/* 482 */       set1 = new BarDataSet(yVals1, bean.getResultCompleteTime());
/* 483 */       set1.setColor(Color.rgb(0, 193, 45));
/* 484 */       set1.setDrawValues(false);
/* 485 */       data = new BarData(new IBarDataSet[] { set1 });
/*     */     } 
/*     */     
/* 488 */     if (size > 1) {
/* 489 */       HistoryModel.DataBean bean = (HistoryModel.DataBean)this.model.getData().get(1);
/* 490 */       yVals2.add(new BarEntry(0.0F, (float)bean.getWaterDegreeValue()));
/* 491 */       yVals2.add(new BarEntry(1.0F, (float)bean.getOilValue()));
/* 492 */       yVals2.add(new BarEntry(2.0F, (float)bean.getTextureValue()));
/* 493 */       yVals2.add(new BarEntry(3.0F, (float)bean.getWrinkleValue()));
/* 494 */       yVals2.add(new BarEntry(4.0F, (float)bean.getSkinColorValue()));
/* 495 */       yVals2.add(new BarEntry(5.0F, (float)bean.getPigmentValue()));
/* 496 */       yVals2.add(new BarEntry(6.0F, (float)bean.getInflammationValue()));
/* 497 */       yVals2.add(new BarEntry(7.0F, (float)bean.getPoreSizeValue()));
/* 498 */       set2 = new BarDataSet(yVals2, bean.getResultCompleteTime());
/* 499 */       set2.setColor(Color.rgb(254, 153, 0));
/* 500 */       set2.setDrawValues(false);
/* 501 */       data = new BarData(new IBarDataSet[] { set1, set2 });
/*     */     } 
/* 503 */     if (size > 2) {
/* 504 */       HistoryModel.DataBean bean = (HistoryModel.DataBean)this.model.getData().get(2);
/* 505 */       yVals3.add(new BarEntry(0.0F, (float)bean.getWaterDegreeValue()));
/* 506 */       yVals3.add(new BarEntry(1.0F, (float)bean.getOilValue()));
/* 507 */       yVals3.add(new BarEntry(2.0F, (float)bean.getTextureValue()));
/* 508 */       yVals3.add(new BarEntry(3.0F, (float)bean.getWrinkleValue()));
/* 509 */       yVals3.add(new BarEntry(4.0F, (float)bean.getSkinColorValue()));
/* 510 */       yVals3.add(new BarEntry(5.0F, (float)bean.getPigmentValue()));
/* 511 */       yVals3.add(new BarEntry(6.0F, (float)bean.getInflammationValue()));
/* 512 */       yVals3.add(new BarEntry(7.0F, (float)bean.getPoreSizeValue()));
/* 513 */       set3 = new BarDataSet(yVals3, bean.getResultCompleteTime());
/* 514 */       set3.setColor(Color.rgb(255, 0, 0));
/* 515 */       set3.setDrawValues(false);
/* 516 */       data = new BarData(new IBarDataSet[] { set1, set2, set3 });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 523 */     mChart.setData(data);
/* 524 */     if (size != 1)
/*     */     {
/*     */       
/* 527 */       if (size == 2) {
/* 528 */         groupSpace = 0.06F;
/* 529 */         barSpace = 0.02F;
/* 530 */         barWidth = 0.45F;
/* 531 */         data.setBarWidth(barWidth);
/* 532 */         mChart.groupBars(0.0F, groupSpace, barSpace);
/* 533 */         mChart.getXAxis().setAxisMinValue(0.0F);
/* 534 */         mChart.getXAxis().setAxisMaximum((barWidth * ((BarData)mChart.getData()).getDataSets().size() + barSpace) * 7.0F + groupSpace * 7.0F);
/* 535 */         mChart.invalidate();
/* 536 */       } else if (size == 3) {
/* 537 */         groupSpace = 0.07F;
/* 538 */         barSpace = 0.06F;
/* 539 */         barWidth = 0.25F;
/* 540 */         data.setBarWidth(barWidth);
/* 541 */         mChart.groupBars(0.0F, groupSpace, barSpace);
/* 542 */         mChart.getXAxis().setAxisMinValue(0.0F);
/* 543 */         mChart.getXAxis().setAxisMaximum((barWidth * 3.0F + barSpace * 2.0F) * 9.0F + groupSpace * 8.0F);
/* 544 */         mChart.invalidate();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\MemberHistoryActivity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */