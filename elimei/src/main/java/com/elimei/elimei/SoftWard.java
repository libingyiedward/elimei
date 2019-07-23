package com.elimei.elimei;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.elimei.R;
import com.elimei.databinding.ActivitySoftWardBinding;
import com.elimei.elimei.utils.ImmersionBar;


public class SoftWard
        extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySoftWardBinding binding = (ActivitySoftWardBinding) DataBindingUtil.setContentView(this, R.layout.activity_soft_ward);
        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(binding.toolbar).init();
        binding.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SoftWard.this.finish();
            }
        });
    }
}
