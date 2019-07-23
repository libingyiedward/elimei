package com.elimei.elimei;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.elimei.R;

import com.elimei.databinding.ActivityFaqBinding;
import com.elimei.elimei.utils.ImmersionBar;


public class FAQActivity
  extends AppCompatActivity
{
  protected void onCreate(Bundle savedInstanceState) {
/* 16 */     super.onCreate(savedInstanceState);
/* 17 */     ActivityFaqBinding binding = (ActivityFaqBinding)DataBindingUtil.setContentView(this, R.layout.activity_faq);
/* 18 */     ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(binding.toolbar).init();
/* 19 */     binding.back.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View view) {
/* 22 */             FAQActivity.this.finish();
          }
        });
/* 25 */     binding.lianjie.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View view) {
/* 28 */             Intent intent = new Intent(FAQActivity.this.getBaseContext(), HardWareActivity.class);
/* 29 */             FAQActivity.this.startActivity(intent);
          }
        });
/* 32 */     binding.ruanjian.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View view) {
/* 35 */             FAQActivity.this.startActivity(new Intent(FAQActivity.this.getBaseContext(), SoftWard.class));
          }
        });
  }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\FAQActivity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */