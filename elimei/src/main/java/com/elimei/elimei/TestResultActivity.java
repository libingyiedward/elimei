package com.elimei.elimei;


import android.app.ProgressDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elimei.R;
import com.elimei.databinding.ActivityTestresultBinding;
import com.elimei.elimei.Model.MemBerModel;
import com.elimei.elimei.Presenter.TestResultPresenter;

import com.elimei.elimei.utils.ImmersionBar;
import com.elimei.elimei.utils.WifiUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


public class TestResultActivity
        extends AppCompatActivity {
    ImageView shuifeni;
    TextView shuifen;
    LinearLayout shuifenl;
    ImageView y1l;
    TextView y1t;
    LinearLayout y1;
    ImageView w1l;
    TextView w1t;
    LinearLayout w1;
    ImageView z1l;
    TextView z1t;
    LinearLayout z1;
    ImageView f1l;
    TextView f1t;
    LinearLayout f1;
    ImageView s1l;
    TextView s1t;
    LinearLayout s1;
    ImageView m1l;
    TextView m1t;
    LinearLayout m1;
    ImageView mkl;
    TextView mkt;
    LinearLayout mk;
    ImageView zhi;
    LinearLayout zh;
    NestedScrollView scrollView;
    TextView ceshizhi;
    TextView cankaozhi;
    TextView result;
    TextView no1;
    TextView chanpin;
    ViewPager viewapgere;
    LinearLayout container;
    ImageView jianakng;
    ImageView jiance;
    TextView wenti;
    TextView hufu;
    private int showposition = -1;
    private float dimiss = 1.0F;
    private float show = 1.2F;

    private ActivityTestresultBinding binding;
    private ArrayList<String> data;
    private String uuid;
    public TestResultPresenter testResultPresenter;
    public ProgressDialog progressDialog;
    private boolean isshow = true;
    private MemBerModel.ResultBean.MemberBean bean;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityTestresultBinding) DataBindingUtil.setContentView(this, R.layout.activity_testresult);
//        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();
        this.binding.wenti.getPaint().setFlags(8);
        this.binding.wenti.setTextColor(Color.parseColor("#ff9b00"));
        this.binding.hufu.getPaint().setFlags(8);
        this.binding.hufu.setTextColor(Color.parseColor("#ff9b00"));
        this.binding.hufuzc.getPaint().setFlags(8);
        this.binding.hufuzc.setTextColor(Color.parseColor("#ff9b00"));
        Glide.get(this).trimMemory(20);
        this.shuifen = (TextView) findViewById(R.id.shuifen);

        this.y1t = (TextView) findViewById(R.id.y1t);

        this.w1t = (TextView) findViewById(R.id.w1t);

        this.z1t = (TextView) findViewById(R.id.z1t);
        this.f1t = (TextView) findViewById(R.id.f1t);

        this.m1t = (TextView) findViewById(R.id.m1t);

        this.mkt = (TextView) findViewById(R.id.mkt);
        this.s1t = (TextView) findViewById(R.id.s1t);

        this.shuifeni = (ImageView) findViewById(R.id.shuifeni);
        this.y1l = (ImageView) findViewById(R.id.y1l);
        this.w1l = (ImageView) findViewById(R.id.w1l);
        this.z1l = (ImageView) findViewById(R.id.z1l);
        this.f1l = (ImageView) findViewById(R.id.f1l);
        this.m1l = (ImageView) findViewById(R.id.m1l);
        this.mkl = (ImageView) findViewById(R.id.mkl);
        this.s1l = (ImageView) findViewById(R.id.s1l);
        this.data = getIntent().getStringArrayListExtra("image");

        this.binding.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TestResultActivity.this.bean == null) {
                    TestResultActivity.this.onBackPressed();
                } else {
                    TestResultActivity.this.finish();
                }
            }
        });

        this.binding.baike.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                TestResultActivity.this.finish();
            }
        });

        Serializable serializableExtra = getIntent().getSerializableExtra("Model");

        if (serializableExtra != null) {
            this.bean = (MemBerModel.ResultBean.MemberBean) serializableExtra;
        }

        this.binding.again.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (TestResultActivity.this.bean == null) {
                    WifiManager mwifiManager = (WifiManager) TestResultActivity.this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                    if (mwifiManager.isWifiEnabled()) {
                        if (mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "").contains("SMARP_")) {
                            Intent intent = new Intent(TestResultActivity.this, VideoPalyerActivity.class);
                            TestResultActivity.this.startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(TestResultActivity.this);
                            builder.setMessage("请打开设备，连接设备WiFi").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    WifiUtils.starttowifi(TestResultActivity.this);
                                }
                                /* 157 */
                            }).show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TestResultActivity.this);
                        builder.setMessage("请打开设备，连接设备WiFi").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {

                                WifiUtils.starttowifi(TestResultActivity.this);
                            }
                        }).show();
                    }
                } else {
                    /* 169 */
                    Intent intent = new Intent(TestResultActivity.this.getBaseContext(), MemberDetail.class);
                    /* 170 */
                    intent.putExtra("data", TestResultActivity.this.bean);
                    /* 171 */
                    TestResultActivity.this.startActivity(intent);
                    /* 172 */
                    TestResultActivity.this.finish();
                }
            }
        });

        this.binding.tuijian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /* 179 */
                Intent intent = new Intent(TestResultActivity.this.getBaseContext(), ChanPingAcitivity.class);
                /* 180 */
                intent.putExtra("image", testResultPresenter.tuijian);
                /* 181 */
                intent.putExtra("text", testResultPresenter.tuijiant);
                /* 182 */
                intent.putExtra("h5", testResultPresenter.h5);
                /* 183 */
                TestResultActivity.this.startActivity(intent);
            }
        });

        if (this.bean != null) {

            this.isshow = true;
        } else {
            /* 195 */
            this.isshow = false;
        }
        /* 197 */
        String showposition = getIntent().getStringExtra("showposition");
        /* 198 */
        this.testResultPresenter = new TestResultPresenter(this.binding, this.isshow);
        /* 199 */
        String data = getIntent().getStringExtra("data");
        /* 200 */
        String text = getIntent().getStringExtra("text");
        /* 201 */
        anylizeResult(data, text);
        /* 202 */
        if (!TextUtils.isEmpty(showposition)) {
            /* 203 */
            init(7);
        }
    }

    private void anylizeResult(String response, String text) {
        /* 208 */
        Log.e("结果", response);
        //{"resultId":"bd3b6e19-337b-4f4e-b7c0-b9db33579c66","MemberCode":null,"result":"success","msg":null,"waterDegreeValue":7.2,"oilValue":5.4,"textureValue":2.6,"skinColorValue":8.6,"wrinkleValue":9.3,"inflammationValue":6.3,"pigmentValue":9.0,"poreSizeValue":0.8,"ImgIndex":"1,2,3,4,5,6,","fileUploadTime":"2019-07-22 22:51:09","resultCompleteTime":"2019-07-22 22:51:12","resultReturnTime":"2019-07-22 22:51:12","realAge":26,"mathAge":"28","compositeScore":7.1499999999999995,"Company":null,"dealarIdTo":null}
        /* 209 */
        this.testResultPresenter.setdata(response, this.data, this, text);
        /* 210 */
        this.testResultPresenter.initpop();

        /* 212 */
        init(9);
    }

    private void deleteImage() {
        /* 216 */
        for (String s : this.data) {
            /* 217 */
            if (!s.contains("http")) {
                /* 218 */
                File file = new File(s);
                /* 219 */
                file.deleteOnExit();
            }
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /* 226 */
        super.onActivityResult(requestCode, resultCode, data);
        /* 227 */
        WifiManager mwifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        /* 228 */
        if (mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "").contains("SMARP_")) {



            /* 232 */
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            /* 233 */
            builder.setMessage("设备已连接，是否开始检测？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    /* 236 */
                    Intent intent = new Intent(TestResultActivity.this, VideoPalyerActivity.class);
                    /* 237 */
                    intent.putExtra("from", "1");
                    /* 238 */
                    TestResultActivity.this.startActivity(intent);
                }
                /* 240 */
            }).show();
        } else {
            /* 242 */
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            /* 243 */
            builder.setMessage("重新上传？")
/* 244 */.setPositiveButton("确定", new DialogInterface.OnClickListener() {


                public void onClick(DialogInterface dialogInterface, int i) {
                }
                /* 249 */
            }).show();
        }
    }

    public void onViewClicked(View view) {
        /* 254 */
        int id = view.getId();
        /* 255 */
        if (id == R.id.shuifenl) {
            /* 256 */
            init(9);
        }
        /* 258 */
        if (id == R.id.y1) {
            /* 259 */
            init(8);
        }
        /* 261 */
        if (id == R.id.w1) {
            /* 262 */
            init(6);
        }
        /* 264 */
        if (id == R.id.z1) {
            /* 265 */
            init(5);
        }
        /* 267 */
        if (id == R.id.f1) {
            /* 268 */
            init(4);
        }
        /* 270 */
        if (id == R.id.s1) {
            /* 271 */
            init(3);
        }
        /* 273 */
        if (id == R.id.m1) {
            /* 274 */
            init(2);
        }
        /* 276 */
        if (id == R.id.mk) {
            /* 277 */
            init(1);
        }
        /* 279 */
        if (id == R.id.zh) {
            /* 280 */
            init(7);
        }
    }

    public void init(int position) {
        /* 285 */
        if (this.showposition == position) {
            return;
        }


        switch (this.showposition) {
            case 9:
                this.binding.shuifen.setTextColor(-16777216);

                this.binding.shuifen.setScaleY(this.dimiss);
                /* 293 */
                this.binding.shuifen.setScaleX(this.dimiss);
                /* 294 */
                this.binding.shuifeni.setScaleY(this.dimiss);
                /* 295 */
                this.binding.shuifeni.setScaleX(this.dimiss);
                break;
            case 8:
                /* 298 */
                this.y1t.setTextColor(-16777216);
                /* 299 */
                this.y1l.setScaleX(this.dimiss);
                /* 300 */
                this.y1l.setScaleY(this.dimiss);
                /* 301 */
                this.y1t.setScaleX(this.dimiss);
                /* 302 */
                this.y1t.setScaleY(this.dimiss);
                break;
            case 6:
                /* 305 */
                this.w1t.setTextColor(-16777216);
                /* 306 */
                this.w1l.setScaleX(this.dimiss);
                /* 307 */
                this.w1l.setScaleY(this.dimiss);
                /* 308 */
                this.w1t.setScaleX(this.dimiss);
                /* 309 */
                this.w1t.setScaleY(this.dimiss);
                break;
            case 5:
                /* 312 */
                this.z1t.setTextColor(-16777216);
                /* 313 */
                this.z1l.setScaleX(this.dimiss);
                /* 314 */
                this.z1l.setScaleY(this.dimiss);
                /* 315 */
                this.z1t.setScaleX(this.dimiss);
                /* 316 */
                this.z1t.setScaleY(this.dimiss);
                break;
            case 4:
                /* 319 */
                this.f1t.setTextColor(-16777216);
                /* 320 */
                this.f1l.setScaleX(this.dimiss);
                /* 321 */
                this.f1l.setScaleY(this.dimiss);
                /* 322 */
                this.f1t.setScaleX(this.dimiss);
                /* 323 */
                this.f1t.setScaleY(this.dimiss);
                break;
            case 3:
                /* 326 */
                this.s1t.setTextColor(-16777216);
                /* 327 */
                this.s1l.setScaleX(this.dimiss);
                /* 328 */
                this.s1l.setScaleY(this.dimiss);
                /* 329 */
                this.s1t.setScaleX(this.dimiss);
                /* 330 */
                this.s1t.setScaleY(this.dimiss);
                break;
            case 2:
                /* 333 */
                this.m1t.setTextColor(-16777216);
                /* 334 */
                this.m1l.setScaleX(this.dimiss);
                /* 335 */
                this.m1l.setScaleY(this.dimiss);
                /* 336 */
                this.m1t.setScaleX(this.dimiss);
                /* 337 */
                this.m1t.setScaleY(this.dimiss);
                break;
            case 1:
                /* 340 */
                this.mkt.setTextColor(-16777216);
                /* 341 */
                this.mkl.setScaleX(this.dimiss);
                /* 342 */
                this.mkl.setScaleY(this.dimiss);
                /* 343 */
                this.mkt.setScaleX(this.dimiss);
                /* 344 */
                this.mkt.setScaleY(this.dimiss);
                break;
            case 7:
                this.binding.zhi.setImageResource(R.drawable.btn_comprehensive_detection_normal);
                break;
        }



        switch (position) {
            case 9:
                /* 354 */
                this.binding.shuifen.setTextColor(Color.parseColor("#FF9B00"));
                /* 355 */
                this.binding.shuifen.setScaleY(this.show);
                /* 356 */
                this.binding.shuifen.setScaleX(this.show);
                /* 357 */
                this.binding.shuifeni.setScaleY(this.show);
                /* 358 */
                this.binding.shuifeni.setScaleX(this.show);
                break;
            case 8:
                /* 361 */
                this.y1t.setTextColor(Color.parseColor("#FF9B00"));
                /* 362 */
                this.y1l.setScaleX(this.show);
                /* 363 */
                this.y1l.setScaleY(this.show);
                /* 364 */
                this.y1t.setScaleX(this.show);
                /* 365 */
                this.y1t.setScaleY(this.show);
                break;
            case 6:
                /* 368 */
                this.w1t.setTextColor(Color.parseColor("#FF9B00"));
                /* 369 */
                this.w1l.setScaleX(this.show);
                /* 370 */
                this.w1l.setScaleY(this.show);
                /* 371 */
                this.w1t.setScaleX(this.show);
                /* 372 */
                this.w1t.setScaleY(this.show);
                break;
            case 5:
                /* 375 */
                this.z1t.setTextColor(Color.parseColor("#FF9B00"));
                /* 376 */
                this.z1l.setScaleX(this.show);
                /* 377 */
                this.z1l.setScaleY(this.show);
                /* 378 */
                this.z1t.setScaleX(this.show);
                /* 379 */
                this.z1t.setScaleY(this.show);
                break;
            case 4:
                /* 382 */
                this.f1t.setTextColor(Color.parseColor("#FF9B00"));
                /* 383 */
                this.f1l.setScaleX(this.show);
                /* 384 */
                this.f1l.setScaleY(this.show);
                /* 385 */
                this.f1t.setScaleX(this.show);
                /* 386 */
                this.f1t.setScaleY(this.show);
                break;
            case 3:
                /* 389 */
                this.s1t.setTextColor(Color.parseColor("#FF9B00"));
                /* 390 */
                this.s1l.setScaleX(this.show);
                /* 391 */
                this.s1l.setScaleY(this.show);
                /* 392 */
                this.s1t.setScaleX(this.show);
                /* 393 */
                this.s1t.setScaleY(this.show);
                break;
            case 2:
                /* 396 */
                this.m1t.setTextColor(Color.parseColor("#FF9B00"));
                /* 397 */
                this.m1l.setScaleX(this.show);
                /* 398 */
                this.m1l.setScaleY(this.show);
                /* 399 */
                this.m1t.setScaleX(this.show);
                /* 400 */
                this.m1t.setScaleY(this.show);
                break;
            case 1:

                this.mkt.setTextColor(Color.parseColor("#FF9B00"));

                this.mkl.setScaleX(this.show);

                this.mkl.setScaleY(this.show);
                this.mkt.setScaleX(this.show);
                this.mkt.setScaleY(this.show);
                break;
            case 7:
                this.binding.zhi.setImageResource(R.drawable.btn_comprehensive_detection_selected);
                break;
        }
        this.testResultPresenter.init(position);
        this.showposition = position;
    }

    public void onTJClick(View view) {
        /* 418 */
        int id = view.getId();
        /* 419 */
        String wiki = "";
        /* 420 */
        if (id == R.id.shuifen) {
            /* 421 */
            wiki = this.testResultPresenter.textModel.getProductImageDir() + this.testResultPresenter.textModel.getWaterProtectSkinWIKI().getGuidName();
        }
        /* 423 */
        if (id == R.id.youzhi) {

            wiki = this.testResultPresenter.textModel.getProductImageDir() + this.testResultPresenter.textModel.getOilProtectSkinWIKI().getGuidName();
        }
        if (id == R.id.wenli) {
            /* 427 */
            wiki = this.testResultPresenter.textModel.getProductImageDir() + this.testResultPresenter.textModel.getTextureProtectSkinWIKI().getGuidName();
        }
        /* 429 */
        if (id == R.id.zhouwen) {
            /* 430 */
            wiki = this.testResultPresenter.textModel.getProductImageDir() + this.testResultPresenter.textModel.getWrinkleProtectSkinWIKI().getGuidName();
        }
        /* 432 */
        if (id == R.id.fuse) {
            /* 433 */
            wiki = this.testResultPresenter.textModel.getProductImageDir() + this.testResultPresenter.textModel.getSkinColorProtectSkinWIKI().getGuidName();
        }
        /* 435 */
        if (id == R.id.sesu) {
            wiki = this.testResultPresenter.textModel.getProductImageDir() + this.testResultPresenter.textModel.getPigmentProtectSkinWIKI().getGuidName();
        }

        if (id == R.id.mingan) {

            wiki = this.testResultPresenter.textModel.getProductImageDir() + this.testResultPresenter.textModel.getInflammationProtectSkinWIKI().getGuidName();
        }
        if (id == R.id.maokong) {
            wiki = this.testResultPresenter.textModel.getProductImageDir() + this.testResultPresenter.textModel.getPoreSizeProtectSkinWIKI().getGuidName();
        }

        Intent intent = new Intent(getBaseContext(), HuFuJieMianActivity.class);
        intent.putExtra("data", wiki);
        startActivity(intent);
    }


    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ELiMeiActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}