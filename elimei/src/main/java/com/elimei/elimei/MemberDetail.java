
package com.elimei.elimei;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dash.Const;
import com.elimei.R;
import com.elimei.databinding.ActivityMemberDetailBinding;
import com.elimei.elimei.Model.HistoryModel;
import com.elimei.elimei.Model.MemBerModel;
import com.elimei.elimei.Model.Token;

import com.elimei.elimei.utils.ImmersionBar;
import com.elimei.elimei.utils.Toasty;
import com.elimei.elimei.utils.WifiUtils;
import com.google.gson.Gson;
import com.util.Net;
import com.util.Params;

import org.json.JSONException;
import org.json.JSONObject;


public class MemberDetail
        extends Activity {
    private ActivityMemberDetailBinding binding;
    private MemBerModel.ResultBean.MemberBean data;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.binding = (ActivityMemberDetailBinding) DataBindingUtil.setContentView(this, R.layout.activity_member_detail);
        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();

        this.binding.history.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(MemberDetail.this);

                progressDialog.setMessage("加载中。。。");

                progressDialog.show();

                Net.post(Const.MemBerHistory, (new Token(MemberDetail.this)).getToken(), Params.newInstance().params("userId", MemberDetail.this.data.getWxid()), null, new Net.HttpListener() {

                    public void OnSuccess(String message) {
                        /*  50 */
                        String response = message;
                        /*  51 */
                        progressDialog.dismiss();
                        /*  52 */
                        Log.e("网络结果", response);
                        /*  53 */
                        HistoryModel model = (HistoryModel) (new Gson()).fromJson(response, HistoryModel.class);
                        /*  54 */
                        if (!model.getResult().contains("fail")) {
                            /*  55 */
                            if (model.getData().size() != 0) {
                                /*  56 */
                                Intent intent = new Intent(getBaseContext(), MemberHistoryActivity.class);
                                /*  57 */
                                intent.putExtra("data", data.getWxid());
                                /*  58 */
                                intent.putExtra("name", data.getRelname());
                                /*  59 */
                                intent.putExtra("tele", data.getMobile());
                                /*  60 */
                                intent.putExtra("model", data);
                                /*  61 */
                                intent.putExtra("json", message);
                                /*  62 */
                                startActivity(intent);

                            } else {
                                /*  64 */
                                Toasty.info(getApplicationContext(), "暂无历史记录").show();

                            }

                        } else {
                            /*  67 */
                            Toasty.warning(getApplicationContext(), model.getMsg()).show();

                        }

                    }


                    public void OnError(String e) {
                        /*  73 */
                        progressDialog.dismiss();
                        /*  74 */
                        Toasty.error(getBaseContext(), Const.Warrning).show();

                    }

                });

            }

        });
        /*  79 */
        this.binding.test.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                /*  82 */
                WifiManager mwifiManager = (WifiManager) MemberDetail.this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                /*  83 */
                if (mwifiManager.isWifiEnabled()) {
                    if (mwifiManager.getConnectionInfo().getSSID().toString().replace("\"", "").contains("SMARP_")) {

                        Intent intent = new Intent(MemberDetail.this, VideoPalyerActivity.class);

                        intent.putExtra("data", MemberDetail.this.data);
                        MemberDetail.this.startActivity(intent);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MemberDetail.this);
                        builder.setMessage("请打开设备，连接设备WiFi").setPositiveButton("好的", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int i) {
                                WifiUtils.starttowifi(MemberDetail.this);

                            }
                            /*  95 */
                        }).show();
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberDetail.this);
                    builder.setMessage("请打开设备，连接设备WiFi").setPositiveButton("好的", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            WifiUtils.starttowifi(MemberDetail.this);
                        }
                    }).show();
                }
            }
        });
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MemberDetail.this.finish();
            }
        });
        this.binding.sendl.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(MemberDetail.this);
                progressDialog.setMessage("加载中");
                progressDialog.show();
                Params params = Params.newInstance().params("url", Const.result + MemberDetail.this.data.getWxid()).params("openid", MemberDetail.this.data.getWxid());
                Net.post(Const.sendTestResult, params, null, new Net.HttpListener() {

                    public void OnSuccess(String message) {
                        Log.e("结果", message);

                        try {
                            JSONObject jsonObject = new JSONObject(message);
                            String msg = jsonObject.getString("msg");
                            if (msg.contains("成功")) {
                                Toasty.success(getApplicationContext(), "发送成功").show();

                            } else {
                                Toasty.warning(getApplicationContext(), "发送失败").show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        progressDialog.dismiss();

                    }


                    public void OnError(String e) {
                        Log.e("结果", "错误" + e);
                        progressDialog.dismiss();

                    }

                });

            }

        });
        init();

    }


    private void init() {
        this.data = (MemBerModel.ResultBean.MemberBean) getIntent().getSerializableExtra("data");
        this.binding.biaoghao.setText(this.data.getWxid());
        this.binding.name.setText(this.data.getRelname());
        this.binding.tele.setText(this.data.getMobile());
        this.binding.mail.setText(this.data.getEmail());
        this.binding.birthday.setText(this.data.getBirthday());
        this.binding.zhiye.setText(this.data.getProfession());
        this.binding.jinianri.setText(this.data.getCommemorate());
        String sex = this.data.getSex();
        if (sex.equals("2")) {
            this.binding.nan.setBackgroundResource(R.drawable.femail);
            this.binding.nv.setBackgroundResource(R.drawable.man);

        } else {
            this.binding.nan.setBackgroundResource(R.drawable.man);
            this.binding.nv.setBackgroundResource(R.drawable.femail);

        }
        Glide.with(this).load(this.data.getHeadimgurl()).into(this.binding.touxiang);
        this.binding.shopaddress.setText((new Token(this)).getAddress());
        this.binding.tuijian.setText(this.data.getDealeridto());

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent a) {
        super.onActivityResult(requestCode, resultCode, a);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("设备已连接，是否开始检测？").setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int i) {
                /* 204 */
                Intent intent = new Intent(MemberDetail.this, VideoPalyerActivity.class);
                /* 205 */
                intent.putExtra("data", MemberDetail.this.data);
                /* 206 */
                MemberDetail.this.startActivity(intent);

            }
            /* 208 */
        }).show();


    }

}
