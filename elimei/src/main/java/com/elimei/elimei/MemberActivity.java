package com.elimei.elimei;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dash.Const;
import com.elimei.R;
import com.elimei.databinding.ActivityMemberBinding;
import com.elimei.elimei.Adapter.MemberAdapter;
import com.elimei.elimei.Model.MemBerModel;
import com.elimei.elimei.Model.Token;

import com.elimei.elimei.utils.ImmersionBar;
import com.elimei.elimei.utils.Toasty;
import com.google.gson.Gson;
import com.util.Net;
import com.util.Params;


public class MemberActivity
        extends AppCompatActivity {

    /*  30 */   private float show = 1.2F;
    /*  31 */   private float dimiss = 1.0F;

    private MemberAdapter adapter;
    private String check = "1";
    private String num = "";
    private String wxid = "";
    private String relname = "";
    private TextView memberid;
    private TextView membername;
    private TextView memberno;
    private RadioGroup radioGroup;
    private EditText edittext;
    private TextView sizenum;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_member);
//    ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();
        memberid = (TextView) findViewById(R.id.memberid);
        membername = (TextView) findViewById(R.id.membername);
        memberno = (TextView) findViewById(R.id.memberno);
        edittext = (EditText) findViewById(R.id.edittext);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        sizenum = (TextView) findViewById(R.id.num);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.memberid) {
                    memberid.setTextColor(Color.parseColor("#fe9900"));
                    memberid.setScaleX(MemberActivity.this.show);
                    memberid.setScaleY(MemberActivity.this.show);
                    edittext.setHint("请输入会员号");
                } else {
                    memberid.setTextColor(Color.parseColor("#000000"));
                    memberid.setScaleX(MemberActivity.this.dimiss);
                    memberid.setScaleY(MemberActivity.this.dimiss);
                }
                if (checkedId == R.id.membername) {
                    membername.setTextColor(Color.parseColor("#fe9900"));
                    membername.setScaleX(MemberActivity.this.show);
                    membername.setScaleY(MemberActivity.this.show);
                    edittext.setHint("请输入会员名");
                } else {
                    membername.setTextColor(Color.parseColor("#000000"));
                    membername.setScaleX(MemberActivity.this.dimiss);
                    membername.setScaleY(MemberActivity.this.dimiss);
                }
                if (checkedId == R.id.memberno) {
                    memberno.setTextColor(Color.parseColor("#fe9900"));
                    memberno.setScaleX(MemberActivity.this.show);
                    memberno.setScaleY(MemberActivity.this.show);
                    edittext.setHint("请输入电话");
                } else {
                    memberno.setTextColor(Color.parseColor("#000000"));
                    memberno.setScaleX(MemberActivity.this.dimiss);
                    memberno.setScaleY(MemberActivity.this.dimiss);
                }
            }
        });

        adapter = new MemberAdapter();
        RecyclerView recycleview = (RecyclerView) findViewById(R.id.recycleview);
        recycleview.setAdapter(this.adapter);
        recycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.setOnItemClickLIstener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MemberActivity.this.getApplicationContext(), MemberDetail.class);
                MemBerModel.ResultBean.MemberBean bean = (MemBerModel.ResultBean.MemberBean) MemberActivity.this.adapter.getData().get(position - 1);
                intent.putExtra("data", bean);
                MemberActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MemberActivity.this.finish();
            }
        });
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();
                String s = edittext.getText().toString().trim();

                if (TextUtils.isEmpty(s)) {
                    return;
                }

                if (id == memberid.getId()) {
                    /* 106 */
                    MemberActivity.this.num = s;
                    /* 107 */
                    MemberActivity.this.relname = "";
                    /* 108 */
                    MemberActivity.this.wxid = "";
                }
                if (id == membername.getId()) {
                    MemberActivity.this.relname = s;
                    MemberActivity.this.wxid = "";
                    MemberActivity.this.num = "";
                }

                if (id == memberno.getId()) {

                    MemberActivity.this.wxid = s;

                    MemberActivity.this.relname = "";

                    MemberActivity.this.num = "";
                }

                MemberActivity.this.init();
            }
        });

        init();
    }

    private void init() {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("获取数据中");

        progressDialog.show();

        String token = (new Token(this)).getToken();

        Params params1 = Params.newInstance();

        if (!TextUtils.isEmpty(num)) {

            params1.params("mobile", num);
        }

        if (!TextUtils.isEmpty(wxid)) {
            params1.params("wxid", wxid);
        }
        if (!TextUtils.isEmpty(relname)) {
            params1.params("relname", relname);
        }
        params1.params("check", this.check);
        Net.post(Const.GetVip, token, params1, null, new Net.HttpListener() {
            public void OnSuccess(String message) {
                progressDialog.dismiss();
                MemBerModel memBerModel = (MemBerModel) (new Gson()).fromJson(message, MemBerModel.class);
                if (memBerModel.getMsg().contains("成功")) {
                    MemberActivity.this.adapter.setdata(memBerModel.getResult().getMember());
                    sizenum.setText(memBerModel.getResult().getMember().size() + "");
                } else {
                    Toasty.warning(MemberActivity.this.getApplicationContext(), memBerModel.getMsg()).show();
                }
            }


            public void OnError(String e) {
                progressDialog.dismiss();
                Toasty.error(MemberActivity.this.getBaseContext(), Const.Warrning).show();
            }
        });
    }
}
