/*    */ package com.elimei.elimei;
/*    */ 
/*    */ import android.databinding.DataBindingUtil;
/*    */ import android.os.Bundle;
/*    */ import android.support.v7.app.AppCompatActivity;
/*    */ import android.view.View;
/*    */ import com.elimei.R;

/*    */ import com.elimei.databinding.ActivityHardWareBinding;
import com.elimei.elimei.utils.ImmersionBar;
/*    */ 
/*    */ 
/*    */ public class HardWareActivity
/*    */   extends AppCompatActivity
/*    */ {
/*    */   protected void onCreate(Bundle savedInstanceState) {
/* 15 */     super.onCreate(savedInstanceState);
/* 16 */     ActivityHardWareBinding binding = (ActivityHardWareBinding)DataBindingUtil.setContentView(this, R.layout.activity_hard_ware);
/* 17 */     ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(binding.toolbar).init();
/* 18 */     binding.back.setOnClickListener(new View.OnClickListener()
/*    */         {
/*    */           public void onClick(View view) {
/* 21 */             HardWareActivity.this.finish();
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\HardWareActivity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */