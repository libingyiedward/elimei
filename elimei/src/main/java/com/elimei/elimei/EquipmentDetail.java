package com.elimei.elimei;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.elimei.R;
import com.elimei.databinding.ActivityEquipmentDetailBinding;

import com.elimei.elimei.utils.ImmersionBar;


public class EquipmentDetail
  extends AppCompatActivity
{
  private ActivityEquipmentDetailBinding binding;
  
  protected void onCreate(Bundle savedInstanceState) {
/* 17 */     super.onCreate(savedInstanceState);
/* 18 */     this.binding = (ActivityEquipmentDetailBinding)DataBindingUtil.setContentView(this, R.layout.activity_equipment_detail);
/* 19 */     ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();
/* 20 */     this.binding.back.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View view) {
/* 23 */             EquipmentDetail.this.finish();
          }
        });
  }
}

