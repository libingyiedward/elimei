package com.elimei.elimei;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.elimei.R;

import com.elimei.databinding.ActivityHuFuJieMianBinding;
import com.elimei.elimei.utils.ImmersionBar;


public class HuFuJieMianActivity
        extends AppCompatActivity {
    private ActivityHuFuJieMianBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        /* 19 */
        super.onCreate(savedInstanceState);
        /* 20 */
        this.binding = (ActivityHuFuJieMianBinding) DataBindingUtil.setContentView(this, R.layout.activity_hu_fu_jie_mian);
        /* 21 */
        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();
        /* 22 */
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /* 25 */
                HuFuJieMianActivity.this.finish();
            }
        });
        /* 28 */
        this.binding.close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /* 31 */
                HuFuJieMianActivity.this.finish();
            }
        });
        /* 34 */
        String data = getIntent().getStringExtra("data");
        /* 35 */
        Glide.with(this).load(data).into(this.binding.image);
        /* 36 */
        findViewById(R.id.elimei).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /* 39 */
                Intent intent = new Intent(HuFuJieMianActivity.this.getBaseContext(), ELiMeiActivity.class);
                /* 40 */
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                /* 41 */
                HuFuJieMianActivity.this.startActivity(intent);
            }
        });
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\HuFuJieMianActivity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */