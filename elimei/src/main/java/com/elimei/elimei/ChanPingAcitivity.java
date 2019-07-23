/*    */
package com.elimei.elimei;
/*    */
/*    */

import android.content.Intent;
/*    */ import android.databinding.DataBindingUtil;
/*    */ import android.os.Bundle;
/*    */ import android.support.v7.app.AppCompatActivity;
/*    */ import android.view.View;
/*    */ import com.bumptech.glide.Glide;
/*    */ import com.elimei.R;

/*    */ import com.elimei.databinding.ActivityChanPingAcitivityBinding;
import com.elimei.elimei.utils.ImmersionBar;
/*    */ import java.util.ArrayList;

/*    */
/*    */
/*    */
/*    */ public class ChanPingAcitivity
        extends AppCompatActivity {
    private ActivityChanPingAcitivityBinding binding;
    private ArrayList<String> h5;

    /*    */
    /*    */
    protected void onCreate(Bundle savedInstanceState) {
        /* 22 */
        super.onCreate(savedInstanceState);
        /* 23 */
        this.binding = (ActivityChanPingAcitivityBinding) DataBindingUtil.setContentView(this, R.layout.activity_chan_ping_acitivity);
        /* 24 */
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            /*    */
            public void onClick(View view) {
                /* 27 */
                ChanPingAcitivity.this.finish();
                /*    */
            }
            /*    */
        });
        /* 30 */
        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.binding.toolbar).init();
        /* 31 */
        ArrayList<String> image = getIntent().getStringArrayListExtra("image");
        /* 32 */
        ArrayList<String> text = getIntent().getStringArrayListExtra("text");
        /* 33 */
        this.h5 = getIntent().getStringArrayListExtra("h5");
        /*    */
        /* 35 */
        if (image.size() >= 1) {
            /* 36 */
            Glide.with(this).load((String) image.get(0)).into(this.binding.tuijian1);
            /* 37 */
            this.binding.tuijiant1.setText((CharSequence) text.get(0));
            /* 38 */
            this.binding.tj1.setVisibility(0);
            /*    */
        }
        /* 40 */
        if (image.size() >= 2) {
            /* 41 */
            Glide.with(this).load((String) image.get(1)).into(this.binding.tuijian2);
            /* 42 */
            this.binding.tuijiant2.setText((CharSequence) text.get(1));
            /* 43 */
            this.binding.tj2.setVisibility(0);
            /*    */
        }
        /*    */
        /* 46 */
        if (image.size() >= 3) {
            /* 47 */
            Glide.with(this).load((String) image.get(2)).into(this.binding.tuijian2);
            /* 48 */
            this.binding.tuijiant2.setText((CharSequence) text.get(2));
            /* 49 */
            this.binding.tj2.setVisibility(0);
            /*    */
        }
        /*    */
        /* 52 */
        this.binding.home.setOnClickListener(new View.OnClickListener() {
            /*    */
            public void onClick(View view) {
                /* 55 */
                Intent intent = new Intent(ChanPingAcitivity.this.getBaseContext(), ELiMeiActivity.class);
                /* 56 */
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                /* 57 */
                ChanPingAcitivity.this.startActivity(intent);
                /*    */
            }
            /*    */
        });
        /*    */
        /* 61 */
        this.binding.tj1.setOnClickListener(new View.OnClickListener() {
            /*    */
            public void onClick(View view) {
                /* 64 */
                Intent intent = new Intent();
                /* 65 */
                intent.setAction("shopcar");
                /* 66 */
                intent.putExtra("h5", (String) ChanPingAcitivity.this.h5.get(0));
                /* 67 */
                ChanPingAcitivity.this.startActivity(intent);
                /*    */
            }
            /*    */
        });
        /*    */
        /* 71 */
        this.binding.tj2.setOnClickListener(new View.OnClickListener() {
            /*    */
            public void onClick(View view) {
                /* 74 */
                Intent intent = new Intent();
                /* 75 */
                intent.setAction("shopcar");
                /* 76 */
                intent.putExtra("h5", (String) ChanPingAcitivity.this.h5.get(1));
                /* 77 */
                ChanPingAcitivity.this.startActivity(intent);
                /*    */
            }
            /*    */
        });
        /*    */
        /* 81 */
        this.binding.tj3.setOnClickListener(new View.OnClickListener() {
            /*    */
            public void onClick(View view) {
                /* 84 */
                Intent intent = new Intent();
                /* 85 */
                intent.setAction("shopcar");
                /* 86 */
                intent.putExtra("h5", (String) ChanPingAcitivity.this.h5.get(2));
                /* 87 */
                ChanPingAcitivity.this.startActivity(intent);
                /*    */
            }
            /*    */
        });
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\ChanPingAcitivity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */