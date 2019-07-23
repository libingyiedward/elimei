package com.elimei.elimei.Presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elimei.R;
import com.elimei.databinding.ActivityTestresultBinding;
import com.elimei.elimei.Model.Result;
import com.elimei.elimei.Model.TextModel;

import com.elimei.elimei.charts.RadarChart;
import com.elimei.elimei.components.AxisBase;
import com.elimei.elimei.components.XAxis;
import com.elimei.elimei.components.YAxis;
import com.elimei.elimei.data.RadarData;
import com.elimei.elimei.data.RadarDataSet;
import com.elimei.elimei.data.RadarEntry;
import com.elimei.elimei.formatter.IAxisValueFormatter;
import com.elimei.elimei.interfaces.datasets.IRadarDataSet;
import com.google.gson.Gson;
import com.util.ViewPagerIndicator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestResultPresenter {
    ActivityTestresultBinding binding;
    private Result result;
    public ArrayList<String> tuijian;
    public ArrayList<String> tuijiant;
    private Activity activity;
    public int[] color;
    String baseurl;
    private List<String> imagelist;
    private int currentJK;
    private String currntJian;
    private ViewPagerIndicator viewPagerIndicator;
    private List<String> jiesao;
    public ArrayList<String> h5;
    private Imageadapter imageadapter;
    public TextModel.DataBean textModel;
    public List<String> list;
    public String Wiki;
    public boolean isshow;
    private Float a;
    private Float b;
    int red;
    int orange;
    int green;

    public void setdata(String data, List<String> list, Activity activity, String text) {
        this.result = (Result) (new Gson()).fromJson(data, Result.class);
        this.textModel = ((TextModel) (new Gson()).fromJson(text, TextModel.class)).getData();
        this.list = list;
        this.activity = activity;
        this.jiesao = new ArrayList();

    }

    public TestResultPresenter(ActivityTestresultBinding binding, boolean show) {
        this.tuijian = new ArrayList();
        /*  53 */
        this.tuijiant = new ArrayList();


        /*  56 */
        this.color = new int[]{-65536, -65536, -65536, -65536, -65536, -65536, -65536, -65536, -65536};
        /*  57 */
        this.baseurl = " ";
        /*  58 */
        this.imagelist = new ArrayList();

        /*  66 */
        this.h5 = new ArrayList();



        /*  70 */
        this.list = new ArrayList();

        this.a = Float.valueOf(3.0F);
        this.b = Float.valueOf(6.0F);

        this.red = Color.parseColor("#FF0000");
        this.orange = Color.parseColor("#ffe32c");

        this.green = Color.parseColor("#0ADA00");
        this.binding = binding;
        this.isshow = show;
    }

    public void init(int position) {
        double poreSizeValue, inflammationValue, pigmentValue, skinColorValue, textureValue, wrink, oil, value;
        String s, score;
        if (this.result == null) return;
        this.binding.main.setVisibility(View.VISIBLE);
        this.binding.zonghe.setVisibility(View.GONE);
        String wenti = "";
        String jianyi = "";
        String title = "";
        switch (position) {
            case 1:
                this.Wiki = this.textModel.getProductImageDir() + this.textModel.getPoreSizeProtectSkinWIKI().getGuidName();
                wenti = this.textModel.getPoreSizeQuestion();
                jianyi = this.textModel.getPoreSizeTips();
                title = this.textModel.getPoreSizeStallText();
                getwidth(this.result.getPoreSizeValue(), 2.0D);
                this.currentJK = R.drawable.jk_maokong;
                this.currntJian = (String) this.list.get(5);
                this.binding.jianakng.setImageResource(R.drawable.jk_maokong);
                this.binding.title.setText("毛孔检测结果");
                Glide.with(this.activity).load(Integer.valueOf(R.drawable.maokongck)).into(this.binding.cankaoimg);
                if (this.currntJian.contains("http")) {
                    Glide.with(this.activity).load(this.currntJian).into(this.binding.jiance);
                } else {
                    Glide.with(this.activity).load(new File(this.currntJian)).into(this.binding.jiance);
                }
                this.h5.clear();
                this.jiesao.clear();
                this.imagelist.clear();
                if (((this.textModel.getPoreSizeProductList() != null)) & ((this.textModel.getPoreSizeProductList().size() != 0)))
                    for (TextModel.DataBean.PoreSizeProductListBean bean : this.textModel.getPoreSizeProductList()) {
                        this.jiesao.add(bean.getProductName());
                        this.h5.add(this.baseurl + bean.getUrlCode());
                        this.imagelist.add(this.textModel.getProductImageDir() + bean.getGuidName());
                    }
                break;
            case 2:
                this.Wiki = this.textModel.getProductImageDir() + this.textModel.getInflammationProtectSkinWIKI().getGuidName();
                wenti = this.textModel.getInflammationQuestion();
                jianyi = this.textModel.getInflammationTips();
                title = this.textModel.getInflammationStallText();
                getwidth(this.result.getInflammationValue(), 6.0D);
                this.currentJK = R.drawable.jk_mingan;
                this.currntJian = (String) this.list.get(4);
                this.binding.jianakng.setImageResource(R.drawable.jk_mingan);
                if (this.currntJian.contains("http")) {
                    Glide.with(this.activity).load(this.currntJian).into(this.binding.jiance);
                } else {
                    Glide.with(this.activity).load(new File(this.currntJian)).into(this.binding.jiance);
                }
                this.h5.clear();
                this.jiesao.clear();
                this.imagelist.clear();
                if (((this.textModel.getInflammationProductList() != null)) & ((this.textModel.getInflammationProductList().size() != 0)))
                    for (TextModel.DataBean.InflammationProductListBean bean : this.textModel.getInflammationProductList()) {
                        this.jiesao.add(bean.getProductName());
                        this.h5.add(this.baseurl + bean.getUrlCode());
                        this.imagelist.add(this.textModel.getProductImageDir() + bean.getGuidName());
                    }
                this.binding.title.setText("疲劳检测结果");
                this.binding.cankaoimg.setImageResource(R.drawable.minganck);
                break;
            case 3:
                this.Wiki = this.textModel.getProductImageDir() + this.textModel.getPigmentProtectSkinWIKI().getGuidName();
                wenti = this.textModel.getPigmentQuestion();
                jianyi = this.textModel.getPigmentTips();
                title = this.textModel.getPigmentStallText();
                getwidth(this.result.getPigmentValue(), 2.0D);
                this.currentJK = R.drawable.jk_sesu;
                this.currntJian = (String) this.list.get(3);
                this.binding.jianakng.setImageResource(R.drawable.jk_sesu);
                if (this.currntJian.contains("http")) {
                    Glide.with(this.activity).load(this.currntJian).into(this.binding.jiance);
                } else {
                    Glide.with(this.activity).load(new File(this.currntJian)).into(this.binding.jiance);
                }
                this.h5.clear();
                this.jiesao.clear();
                this.imagelist.clear();
                if (((this.textModel.getPigmentProductList() != null)) & ((this.textModel.getPigmentProductList().size() != 0)))
                    for (TextModel.DataBean.PigmentProductListBean bean : this.textModel.getPigmentProductList()) {
                        this.jiesao.add(bean.getProductName());
                        this.h5.add(this.baseurl + bean.getUrlCode());
                        this.imagelist.add(this.textModel.getProductImageDir() + bean.getGuidName());
                    }
                this.binding.title.setText("色素检测结果");
                Glide.with(this.activity).load(Integer.valueOf(R.drawable.sesuck)).into(this.binding.cankaoimg);
                break;
            case 4:
                this.Wiki = this.textModel.getProductImageDir() + this.textModel.getSkinColorProtectSkinWIKI().getGuidName();
                wenti = this.textModel.getSkinColorQuestion();
                jianyi = this.textModel.getSkinColorTips();
                title = this.textModel.getSkinColorStallText();
                getwidth(this.result.getSkinColorValue(), 9.9D);
                this.currentJK = R.drawable.jk_fuse;
                this.currntJian = (String) this.list.get(1);
                this.binding.jianakng.setImageResource(R.drawable.jk_fuse);
                if (this.currntJian.contains("http")) {
                    Glide.with(this.activity).load(this.currntJian).into(this.binding.jiance);
                } else {
                    Glide.with(this.activity).load(new File(this.currntJian)).into(this.binding.jiance);
                }
                this.h5.clear();
                this.jiesao.clear();
                this.imagelist.clear();
                if (((this.textModel.getSkinColorProductList() != null)) & ((this.textModel.getSkinColorProductList().size() != 0)))
                    for (TextModel.DataBean.SkinColorProductListBean bean : this.textModel.getSkinColorProductList()) {
                        this.jiesao.add(bean.getProductName());
                        this.h5.add(this.baseurl + bean.getUrlCode());
                        this.imagelist.add(this.textModel.getProductImageDir() + bean.getGuidName());
                    }
                this.binding.title.setText("肤色检测结果");
                Glide.with(this.activity).load(Integer.valueOf(R.drawable.fuseck)).into(this.binding.cankaoimg);
                break;
            case 5:
                this.Wiki = this.textModel.getProductImageDir() + this.textModel.getWrinkleProtectSkinWIKI().getGuidName();
                wenti = this.textModel.getWrinkleQuestion();
                jianyi = this.textModel.getWrinkleTips();
                title = this.textModel.getWrinkleStallText();
                getwidth(this.result.getWrinkleValue(), 1.0D);
                this.currentJK = R.drawable.jkzhouwen;
                this.currntJian = (String) this.list.get(2);
                this.binding.jianakng.setImageResource(R.drawable.jkzhouwen);
                this.binding.title.setText("皱纹检测结果");
                if (this.currntJian.contains("http")) {
                    Glide.with(this.activity).load(this.currntJian).into(this.binding.jiance);
                } else {
                    Glide.with(this.activity).load(new File(this.currntJian)).into(this.binding.jiance);
                }
                this.h5.clear();
                this.jiesao.clear();
                this.imagelist.clear();
                if (((this.textModel.getWrinkleProductList() != null)) & ((this.textModel.getWrinkleProductList().size() != 0)))
                    for (TextModel.DataBean.WrinkleProductListBean bean : this.textModel.getWrinkleProductList()) {
                        this.jiesao.add(bean.getProductName());
                        this.imagelist.add(this.textModel.getProductImageDir() + bean.getGuidName());
                        this.h5.add(this.baseurl + bean.getUrlCode());
                    }
                Glide.with(this.activity).load(Integer.valueOf(R.drawable.zhouwenck)).into(this.binding.cankaoimg);
                break;
            case 6:
                wenti = this.textModel.getTextureQuestion();
                jianyi = this.textModel.getTextureTips();
                title = this.textModel.getTextureStallText();
                getwidth(this.result.getTextureValue(), 1.0D);
                this.binding.jianakng.setImageResource(R.drawable.jk_wenli);
                this.currentJK = R.drawable.jk_wenli;
                this.currntJian = (String) this.list.get(1);
                this.binding.title.setText("纹理检测结果");
                if (this.currntJian.contains("http")) {
                    Glide.with(this.activity).load(this.currntJian).into(this.binding.jiance);
                } else {
                    Glide.with(this.activity).load(new File(this.currntJian)).into(this.binding.jiance);
                }
                this.imagelist.clear();
                this.h5.clear();
                this.jiesao.clear();
                if (((this.textModel.getTextureProductList() != null)) & ((this.textModel.getTextureProductList().size() != 0)))
                    for (TextModel.DataBean.TextureProductListBean bean : this.textModel.getTextureProductList()) {
                        this.jiesao.add(bean.getProductName());
                        this.imagelist.add(this.textModel.getProductImageDir() + bean.getGuidName());
                        this.h5.add(this.baseurl + bean.getUrlCode());
                    }
                Glide.with(this.activity).load(Integer.valueOf(R.drawable.wenlick)).into(this.binding.cankaoimg);
                break;
            case 8:
                wenti = this.textModel.getOilQuestion();
                jianyi = this.textModel.getOilTips();
                title = this.textModel.getOilStallText();
                getwidth(this.result.getOilValue(), 4.0D);
                Glide.with(this.activity).load(Integer.valueOf(R.drawable.jk_youfen)).into(this.binding.jianakng);
                this.currentJK = R.drawable.jk_youfen;
                this.currntJian = (String) this.list.get(0);
                if (this.currntJian.contains("http")) {
                    Glide.with(this.activity).load(this.currntJian).into(this.binding.jiance);
                } else {
                    Glide.with(this.activity).load(new File((String) this.list.get(0))).into(this.binding.jiance);
                }
                this.h5.clear();
                this.imagelist.clear();
                this.jiesao.clear();
                if (((this.textModel.getOilProductList() != null)) & ((this.textModel.getOilProductList().size() != 0)))
                    for (TextModel.DataBean.OilProductListBean bean : this.textModel.getOilProductList()) {
                        this.jiesao.add(bean.getProductName());
                        this.h5.add(this.baseurl + bean.getUrlCode());
                        this.imagelist.add(this.textModel.getProductImageDir() + bean.getGuidName());
                    }
                this.binding.title.setText("油脂检测结果");
                Glide.with(this.activity).load(Integer.valueOf(R.drawable.youzhick)).into(this.binding.cankaoimg);
                break;
            case 9:
                wenti = this.textModel.getWaterSkinQuestion();
                jianyi = this.textModel.getWaterTips();
                title = this.textModel.getWaterStallText();
                getwidth(this.result.getWaterDegreeValue(), 7.5D);
                this.binding.jianakng.setImageResource(R.drawable.jk_shuifen);
                this.currentJK = R.drawable.jk_shuifen;
                this.currntJian = (String) this.list.get(1);
                if (this.currntJian.contains("http")) {
                    Glide.with(this.activity).load(this.currntJian).into(this.binding.jiance);
                } else {
                    Glide.with(this.activity).load(new File(this.currntJian)).into(this.binding.jiance);
                }
                this.h5.clear();
                this.imagelist.clear();
                this.jiesao.clear();
                if (((this.textModel.getWrinkleProductList() != null)) & ((this.textModel.getWrinkleProductList().size() != 0)))
                    for (TextModel.DataBean.WaterProductListBean bean : this.textModel.getWaterProductList()) {
                        this.jiesao.add(bean.getProductName());
                        this.h5.add(this.baseurl + bean.getUrlCode());
                        this.imagelist.add(this.textModel.getProductImageDir() + bean.getGuidName());
                    }
                Glide.with(this.activity).load(Integer.valueOf(R.drawable.shuifenck)).into(this.binding.cankaoimg);
                this.binding.title.setText("水分检测结果");
                break;
            case 7:
                System.gc();
                this.binding.baike.setVisibility(View.GONE);
                this.binding.main.setVisibility(View.GONE);
                this.binding.zonghe.setVisibility(View.VISIBLE);
                this.binding.title.setText("综合结果");
                score = this.result.getCompositeScore() + "";
                s = score.substring(0, score.indexOf(".") + 2);
                this.binding.fenshu.setText(s + "分");
                value = this.result.getWaterDegreeValue();
                if (value <= 0.5D) {
                    this.binding.shuifen.setBackgroundResource(R.drawable.backred);
                } else if (value > 0.5D && value <= 3.0D) {
                    this.binding.shuifen.setBackgroundResource(R.drawable.backorange);
                } else {
                    this.binding.shuifen.setBackgroundResource(R.drawable.backgreen);
                }
                oil = this.result.getOilValue();
                if (oil <= 1.5D) {
                    this.binding.youzhi.setBackgroundResource(R.drawable.backorange);
                } else if (((oil > 1.5D)) & ((oil <= 2.0D))) {
                    this.binding.youzhi.setBackgroundResource(R.drawable.backgreen);
                } else {
                    this.binding.youzhi.setBackgroundResource(R.drawable.backred);
                }
                wrink = this.result.getWrinkleValue();
                if (wrink <= 1.0D) {
                    this.binding.zhouwen.setBackgroundResource(R.drawable.backgreen);
                } else if (((wrink > 1.0D)) & ((wrink <= 4.0D))) {
                    this.binding.zhouwen.setBackgroundResource(R.drawable.backorange);
                } else {
                    this.binding.zhouwen.setBackgroundResource(R.drawable.backred);
                }
                textureValue = this.result.getTextureValue();
                if (textureValue <= 1.0D) {
                    this.binding.wenli.setBackgroundResource(R.drawable.backgreen);
                } else if (((textureValue > 1.0D)) & ((textureValue <= 3.0D))) {
                    this.binding.wenli.setBackgroundResource(R.drawable.backorange);
                } else {
                    this.binding.wenli.setBackgroundResource(R.drawable.backred);
                }
                skinColorValue = this.result.getSkinColorValue();
                if (skinColorValue <= 5.5D) {
                    this.binding.fuse.setBackgroundResource(R.drawable.backred);
                } else if (((skinColorValue > 5.5D)) & ((skinColorValue <= 6.8D))) {
                    this.binding.fuse.setBackgroundResource(R.drawable.backorange);
                } else {
                    this.binding.fuse.setBackgroundResource(R.drawable.backgreen);
                }
                pigmentValue = this.result.getPigmentValue();
                if (pigmentValue <= 1.0D) {
                    this.binding.sesu.setBackgroundResource(R.drawable.backgreen);
                } else if (((pigmentValue > 1.0D)) & ((pigmentValue <= 4.0D))) {
                    this.binding.sesu.setBackgroundResource(R.drawable.backorange);
                } else {
                    this.binding.sesu.setBackgroundResource(R.drawable.backred);
                }
                inflammationValue = this.result.getInflammationValue();
                if (inflammationValue <= 1.0D) {
                    this.binding.mingan.setBackgroundResource(R.drawable.backgreen);
                } else if (((inflammationValue > 1.0D)) & ((inflammationValue <= 8.0D))) {
                    this.binding.mingan.setBackgroundResource(R.drawable.backorange);
                } else {
                    this.binding.mingan.setBackgroundResource(R.drawable.backred);
                }
                poreSizeValue = this.result.getPoreSizeValue();
                if (poreSizeValue <= 1.0D) {
                    this.binding.maokong.setBackgroundResource(R.drawable.backgreen);
                } else if (((poreSizeValue > 1.0D)) & ((poreSizeValue <= 3.0D))) {
                    this.binding.maokong.setBackgroundResource(R.drawable.backorange);
                } else {
                    this.binding.maokong.setBackgroundResource(R.drawable.backred);
                }
                if (TextUtils.isEmpty(this.result.getMathAge())) this.result.setMathAge("26");
                this.binding.age.setText(this.result.getMathAge() + "岁");
                if (this.isshow) {
                    this.binding.ageall.setVisibility(View.VISIBLE);
                } else {
                    this.binding.ageall.setVisibility(View.INVISIBLE);
                }
//                initdata();
                initrader();
                initdata();
                break;
        }
        if (position == 7) {
            this.binding.baike.setVisibility(View.VISIBLE);
            this.binding.baike.setOnClickListener(new View.OnClickListener() {
                                                      public void onClick(View view) {
                                                          Intent intent = new Intent(TestResultPresenter.this.activity, com.elimei.elimei.ELiMeiActivity.class);
                                                          TestResultPresenter.this.activity.startActivity(intent);
                                                      }
                                                  }
            );
        } else {
            this.binding.wenti.setText("");
            this.binding.hufu.setText("");
            this.binding.wenti1.setText("");
            this.binding.hufu1.setText("");
            setText(wenti, this.binding.wenti);
            setText(wenti, this.binding.wenti1);
            setText(jianyi, this.binding.hufu);
            setText(jianyi, this.binding.hufu1);
            this.binding.result.setText(title);
            if (position != 7)
                this.binding.chanpin.setText((CharSequence) this.jiesao.get(0));
            if (this.viewPagerIndicator != null)
                this.binding.viewapgere.removeOnPageChangeListener(this.viewPagerIndicator);
            this.imageadapter = new Imageadapter();
            this.binding.viewapgere.setAdapter(this.imageadapter);
            this.viewPagerIndicator = new ViewPagerIndicator(this.activity, this.binding.viewapgere, this.binding.container, this.jiesao.size());
            this.binding.viewapgere.addOnPageChangeListener(this.viewPagerIndicator);
            this.binding.viewapgere.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                                                }

                                                                public void onPageSelected(int position) {
                                                                    binding.chanpin.setText((CharSequence) TestResultPresenter.this.jiesao.get(position));
                                                                }

                                                                public void onPageScrollStateChanged(int state) {
                                                                }
                                                            }
                    /* 606 */);
        }
        System.gc();
    }

    private void initdata() {
        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();

        entries1.add(new RadarEntry(10.0F - (float) this.result.getWaterDegreeValue()));
        /* 608 */
        entries1.add(new RadarEntry((float) this.result.getOilValue()));
        /* 609 */
        entries1.add(new RadarEntry((float) this.result.getTextureValue()));
        /* 610 */
        entries1.add(new RadarEntry((float) this.result.getWrinkleValue()));
        /* 611 */
        entries1.add(new RadarEntry(10.0F - (float) this.result.getSkinColorValue()));
        /* 612 */
        entries1.add(new RadarEntry((float) this.result.getPigmentValue()));
        /* 613 */
        entries1.add(new RadarEntry((float) this.result.getInflammationValue()));
        /* 614 */
        entries1.add(new RadarEntry((float) this.result.getPoreSizeValue()));
        RadarDataSet set1 = new RadarDataSet(entries1, "");
        set1.setDrawIcons(false);
        set1.setColor(Color.parseColor("#f3d09a"));

        set1.setFillColor(Color.parseColor("#f3d09a"));

        set1.setDrawFilled(true);

//        set1.setFillAlpha(180);

//        set1.setLineWidth(2.0F);

        set1.setDrawHighlightCircleEnabled(true);

        set1.setDrawHighlightIndicators(false);
        set1.setDrawValues(false);
        set1.setValueTextSize(8.0f);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();

        sets.add(set1);
        
        RadarData data = new RadarData(sets);
//        binding.radarchat.setData(data);
//        binding.radarchat.invalidate();
//        binding.radarchat.
    }

    public void initpop() {
        this.binding.jianakng.setOnClickListener(new View.OnClickListener() {
                                                     public void onClick(View view) {
                                                         final Dialog dialog = new Dialog(TestResultPresenter.this.activity, R.style.dialog);
                                                         View a = LayoutInflater.from(TestResultPresenter.this.activity).inflate(R.layout.item_yulan2, null);
                                                         dialog.setContentView(a);
                                                         a.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                                                                                                           public void onClick(View view) {
                                                                                                               dialog.dismiss();
                                                                                                           }
                                                                                                       }
                                                         );
                                                         Glide.with(TestResultPresenter.this.activity).load(Integer.valueOf(TestResultPresenter.this.currentJK)).into((ImageView) a.findViewById(R.id.imageview));
                                                         Glide.with(TestResultPresenter.this.activity).load(TestResultPresenter.this.currntJian).into((ImageView) a.findViewById(R.id.imageview1));
                                                         dialog.show();
                                                     }
                                                 }
        );
        this.binding.jiance.setOnClickListener(new View.OnClickListener() {
                                                   public void onClick(View view) {
                                                       final Dialog dialog = new Dialog(TestResultPresenter.this.activity, R.style.dialog);
                                                       View a = LayoutInflater.from(TestResultPresenter.this.activity).inflate(R.layout.item_yulan2, null);
                                                       a.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                                                                                                         public void onClick(View view) {
                                                                                                             dialog.dismiss();
                                                                                                         }
                                                                                                     }
                                                       );
                                                       dialog.setContentView(a);
                                                       Glide.with(TestResultPresenter.this.activity).load(Integer.valueOf(TestResultPresenter.this.currentJK)).into((ImageView) a.findViewById(R.id.imageview));
                                                       Glide.with(TestResultPresenter.this.activity).load(TestResultPresenter.this.currntJian).into((ImageView) a.findViewById(R.id.imageview1));
                                                       dialog.show();
                                                   }
                                               }
        );
    }

    private void initrader() {
        String s = this.result.getCompositeScore() + "";
        String s1 = s.substring(0, s.indexOf("."));
        int i = Integer.parseInt(s1);
        this.binding.star1.setImageResource(R.drawable.start_check);
        this.binding.star2.setImageResource(R.drawable.start_check);
        this.binding.star3.setImageResource(R.drawable.start_check);
        this.binding.star4.setImageResource(R.drawable.start_check);
        this.binding.star5.setImageResource(R.drawable.start_check);
        String s2 = s.substring(0, s.indexOf(".") + 2);
        double v = this.result.getCompositeScore();
        if (v < 8) binding.star5.setImageResource(R.drawable.start_uncheck);
        if (v < 6) binding.star4.setImageResource(R.drawable.start_uncheck);
        if (v < 4) binding.star3.setImageResource(R.drawable.start_uncheck);
        if (v < 2) binding.star2.setImageResource(R.drawable.start_uncheck);
        List<Double> list = new ArrayList<Double>();
        list.add(Double.valueOf(result.getWaterDegreeValue()));
        list.add(Double.valueOf(result.getOilValue()));
        list.add(Double.valueOf(result.getTextureValue()));
        list.add(Double.valueOf(result.getWrinkleValue()));
        list.add(Double.valueOf(result.getSkinColorValue()));
        list.add(Double.valueOf(result.getPigmentValue()));
        list.add(Double.valueOf(result.getInflammationValue()));
        list.add(Double.valueOf(result.getPoreSizeValue()));
        RadarChart mChart = this.binding.radarchat;
        mChart.setPadding(0, 0, 0, 0);
        mChart.setBackgroundColor(Color.parseColor("#f8f8f8"));
        mChart.getDescription().setEnabled(false);
        mChart.setWebLineWidth(1.0F);
        mChart.setWebLineWidthInner(1.0F);
        mChart.setWebAlpha(100);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(9.0F);
        xAxis.setYOffset(0.0F);
        xAxis.setXOffset(0.0F);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
                                    private String[] mActivities = {"水分", "油脂", "纹理", "皱纹", "肤色", "色素", "疲劳", "毛孔"};

                                    public String getFormattedValue(float value, AxisBase axis) {
                                        return this.mActivities[(int) value % this.mActivities.length];
                                    }

                                    public int getFormattedColor(float value, AxisBase axisBase) {
                                        int initcolor = TestResultPresenter.this.initcolor(value);
                                        if (initcolor != Color.parseColor("#FF0000")) return -16777216;
                                        return initcolor;
                                    }

                                    public int GetColor(float value) {
                                        return TestResultPresenter.this.initcolor(value);
                                    }
                                }
                /* 635 */);
        xAxis.setTextColor(-16777216);
        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(4, true);
        yAxis.setTextSize(9.0F);
        yAxis.setAxisMinimum(0.0F);
        yAxis.setAxisMaximum(10.0F);
        yAxis.setDrawLabels(false);
//        initdata();
    }

    private void getwidth(double a, double b) {
        int width = this.binding.ceshi.getWidth();
        /* 636 */
        if (width == 0) {
            /* 637 */
            width = dip2px(this.activity, 140.0F);
        }
        /* 639 */
        int i = width / 10;
        /* 640 */
        String s = a + "";
        /* 641 */
        String s1 = s.substring(0, s.indexOf("."));
        /* 642 */
        int i1 = Integer.parseInt(s1);
        /* 643 */
        if (s.length() > 3) {
            /* 644 */
            String ling = s.substring(s.indexOf(".") + 1, s.indexOf(".") + 2);
            /* 645 */
            if (Integer.parseInt(ling) > 4) {

                i1++;
            }
        }
        /* 649 */
        if (a <= 1.5D) {
            /* 650 */
            ViewGroup.LayoutParams params1 = this.binding.ceshizhi.getLayoutParams();
            /* 651 */
            params1.width = -2;
            /* 652 */
            this.binding.ceshizhi.setLayoutParams(params1);
            /* 653 */
            this.binding.ceshizhi.setTextSize(10.0F);
            /* 654 */
            this.binding.ceshizhi.setPadding(dip2px(this.activity, 2.0F), 0, dip2px(this.activity, 2.0F), 0);
            /* 655 */
        } else if (a <= 2.5D) {
            /* 656 */
            ViewGroup.LayoutParams params = this.binding.ceshizhi.getLayoutParams();
            /* 657 */
            params.width = (int) (a * i);
            /* 658 */
            this.binding.ceshizhi.setTextSize(10.0F);
            /* 659 */
            this.binding.ceshizhi.setLayoutParams(params);
            /* 660 */
            this.binding.ceshizhi.setPadding(dip2px(this.activity, 2.0F), 0, dip2px(this.activity, 5.0F), 0);
        } else {
            /* 662 */
            ViewGroup.LayoutParams params = this.binding.ceshizhi.getLayoutParams();
            /* 663 */
            params.width = i1 * i;
            /* 664 */
            this.binding.ceshizhi.setTextSize(10.0F);
            /* 665 */
            this.binding.ceshizhi.setLayoutParams(params);
            /* 666 */
            this.binding.ceshizhi.setPadding(dip2px(this.activity, 12.0F), 0, dip2px(this.activity, 5.0F), 0);
        }




        /* 672 */
        if (s.length() < 3) {
            /* 673 */
            this.binding.ceshizhi.setText(s);
        } else {
            /* 675 */
            String s2 = s.substring(0, s.indexOf(".") + 2);
            /* 676 */
            this.binding.ceshizhi.setText(s2);
        }
    }


    class Imageadapter
            extends PagerAdapter {
        /* 684 */
        public int getCount() {
            return TestResultPresenter.this.imagelist.size();
        }


        /* 689 */
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }


        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_yulan, container, false);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            ViewGroup.LayoutParams layoutParams1 = imageView.getLayoutParams();

            layoutParams1.height = -1;

            layoutParams1.width = -1;

            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();

            layoutParams.height = -1;
            /* 703 */
            layoutParams.width = -1;
            /* 704 */
            imageView.setLayoutParams(layoutParams);
            /* 705 */
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    Intent intent = new Intent();

                    intent.setAction("shopcar");
                    /* 710 */
                    intent.putExtra("h5", (String) TestResultPresenter.this.h5.get(position));
                    /* 711 */
                    TestResultPresenter.this.activity.startActivity(intent);
                }
            });

            Glide.with(container.getContext())
                    .load((String) TestResultPresenter.this.imagelist.get(position))
                    .into(imageView);

            container.addView(view);

            return view;
        }


        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    public static int dip2px(Context context, float dipValue) {
        /* 733 */
        float scale = (context.getResources().getDisplayMetrics()).density;
        /* 734 */
        return (int) (dipValue * scale + 0.5F);
    }

    public void setText(String text, TextView textView) {
        /* 738 */
        String s = text.replaceAll("//n", "\n");
        /* 739 */
        textView.setText(s);
    }

    private int initcolor(float value) {
        /* 743 */
        String s = String.valueOf((int) value);
        /* 744 */
        if (s.equals("0")) {
            /* 745 */
            double h = Double.parseDouble(String.valueOf(this.result.getWaterDegreeValue()));
            /* 746 */
            if (h <= 0.5D)
                /* 747 */ return this.red;
            /* 748 */
            if (h > 0.5D && h <= 3.0D) {
                /* 749 */
                return this.orange;
            }
            /* 751 */
            return this.green;
        }
        /* 753 */
        if (s.equals("1")) {
            /* 754 */
            double g = Double.parseDouble(String.valueOf(this.result.getOilValue()));

            /* 756 */
            if (g <= 1.5D)
                /* 757 */ return this.orange;
            /* 758 */
            if (((g > 1.5D)) & ((g <= 2.0D))) {
                /* 759 */
                return this.green;
            }
            /* 761 */
            return this.red;
        }
        /* 763 */
        if (s.equals("2")) {

            /* 765 */
            double f = Double.parseDouble(String.valueOf(this.result.getTextureValue()));
            /* 766 */
            if (f <= 1.0D)
                /* 767 */ return this.green;
            /* 768 */
            if (((f > 1.0D)) & ((f <= 3.0D))) {
                /* 769 */
                return this.orange;
            }
            /* 771 */
            return this.red;
        }
        /* 773 */
        if (s.equals("3")) {

            /* 775 */
            double e = Double.parseDouble(String.valueOf(this.result.getWrinkleValue()));
            /* 776 */
            if (e <= 1.0D)
                /* 777 */ return this.green;
            /* 778 */
            if (((e > 1.0D)) & ((e <= 4.0D))) {
                /* 779 */
                return this.orange;
            }
            /* 781 */
            return this.red;
        }
        /* 783 */
        if (s.equals("4")) {
            /* 784 */
            double d = Double.parseDouble(String.valueOf(this.result.getSkinColorValue()));
            /* 785 */
            if (d <= 5.5D)
                /* 786 */ return this.red;
            /* 787 */
            if (((d > 5.5D)) & ((d <= 6.8D))) {
                /* 788 */
                return this.orange;
            }
            /* 790 */
            return this.green;
        }
        /* 792 */
        if (s.equals("5")) {
            /* 793 */
            double c = Double.parseDouble(String.valueOf(this.result.getPigmentValue()));
            /* 794 */
            if (c <= 1.0D)
                /* 795 */ return this.green;
            /* 796 */
            if (((c > 1.0D)) & ((c <= 4.0D))) {
                /* 797 */
                return this.orange;
            }
            /* 799 */
            return this.red;
        }
        /* 801 */
        if (s.equals("6")) {
            /* 802 */
            double b = Double.parseDouble(String.valueOf(this.result.getInflammationValue()));
            /* 803 */
            if (b <= 1.0D)
                /* 804 */ return this.green;
            /* 805 */
            if (((b > 1.0D)) & ((b <= 8.0D))) {
                /* 806 */
                return this.orange;
            }
            /* 808 */
            return this.red;
        }
        if (s.equals("7")) {
            double a = Double.parseDouble(String.valueOf(this.result.getPoreSizeValue()));
            if (a <= 1.0D)
                return this.green;
            if (((a > 1.0D)) & ((a <= 3.0D))) {
                return this.orange;
            }

            return this.red;
        }


        return -16777216;
    }
}
