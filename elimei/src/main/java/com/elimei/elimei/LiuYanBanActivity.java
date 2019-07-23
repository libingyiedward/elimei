package com.elimei.elimei;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.elimei.R;

import com.elimei.databinding.ActivityLiuYanBanBinding;
import com.elimei.elimei.utils.ImmersionBar;


public class LiuYanBanActivity
        extends AppCompatActivity {
    private ActivityLiuYanBanBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityLiuYanBanBinding) DataBindingUtil.setContentView(this, R.layout.activity_liu_yan_ban);
        ImmersionBar.with(this).statusBarColor("#Fe9900")
                .titleBar(this.binding.toolbar).init();
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LiuYanBanActivity.this.finish();
            }
        });
        this.binding.tijiao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String s = binding.liuyan.getText().toString().trim();
                if (TextUtils.isEmpty(s)) {
                    return;
                }
                Intent intent = new Intent("android.intent.action.SEND");
                String[] tos = {"way.ping.li@gmail.com"};
                intent.putExtra("android.intent.extra.EMAIL", tos);
                intent.putExtra("android.intent.extra.TEXT", s);
                intent.putExtra("android.intent.extra.SUBJECT", "subject");
                Intent.createChooser(intent, "Choose Email Client");
                LiuYanBanActivity.this.startActivity(intent);
            }
        });
    }
}

