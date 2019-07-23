/*    */ package com.elimei.elimei;
/*    */ 
/*    */ import android.databinding.DataBindingUtil;
/*    */ import android.os.Bundle;
/*    */ import android.support.v7.app.AppCompatActivity;
/*    */ import com.elimei.R;
import com.elimei.databinding.ActivityTuiJianChanPingBinding;

/*    */ 
/*    */ 
/*    */ public class TuiJianChanPing
/*    */   extends AppCompatActivity
/*    */ {
/*    */   private ActivityTuiJianChanPingBinding binding;
/*    */   
/*    */   protected void onCreate(Bundle savedInstanceState) {
/* 15 */     super.onCreate(savedInstanceState);
/* 16 */     this.binding = (ActivityTuiJianChanPingBinding)DataBindingUtil.setContentView(this, R.layout.activity_tui_jian_chan_ping);
/* 17 */     String h5 = getIntent().getStringExtra("h5");
/* 18 */     this.binding.webview.loadUrl(h5);
/*    */   }
/*    */ }

