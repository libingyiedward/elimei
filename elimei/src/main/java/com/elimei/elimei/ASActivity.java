package com.elimei.elimei;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.elimei.R;
import com.elimei.databinding.ActivityAsBinding;

import com.elimei.elimei.utils.ImmersionBar;


public class ASActivity
        extends AppCompatActivity {
    private ActivityAsBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = (ActivityAsBinding) DataBindingUtil.setContentView(this, R.layout.activity_as);

        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();

        this.binding.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                ASActivity.this.finish();
            }
        });

        this.binding.liuyanban.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(ASActivity.this.getBaseContext(), VideoActivity.class);

                ASActivity.this.startActivity(intent);
            }
        });

        this.binding.faq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ASActivity.this.getBaseContext(), FAQActivity.class);
                ASActivity.this.startActivity(intent);
            }
        });

        this.binding.ruanjianshiyongshouce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ASActivity.this.getBaseContext(), SoftWareUsesBook.class);
                ASActivity.this.startActivity(intent);
            }
        });

        this.binding.shebeijianjie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ASActivity.this.getBaseContext(), EquipmentDetail.class);
                ASActivity.this.startActivity(intent);
            }
        });
    }
}
