package com.elimei.elimei;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.elimei.R;

import com.elimei.databinding.ActivitySoftWareUsesBookBinding;
import com.elimei.elimei.utils.ImmersionBar;


public class SoftWareUsesBook
        extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        /* 16 */
        super.onCreate(savedInstanceState);
        /* 17 */
        ActivitySoftWareUsesBookBinding binding = (ActivitySoftWareUsesBookBinding) DataBindingUtil.setContentView(this, R.layout.activity_soft_ware_uses_book);
        /* 18 */
        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(binding.toolbar).init();
        /* 19 */
        binding.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /* 22 */
                SoftWareUsesBook.this.finish();
            }
        });
        /* 25 */
        Glide.with(this).load(Integer.valueOf(R.mipmap.shiyongshouce)).into(binding.imageview);
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\SoftWareUsesBook.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */