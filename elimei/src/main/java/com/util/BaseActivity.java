package com.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elimei.R;
import com.elimei.elimei.utils.ImmersionBar;


public abstract class BaseActivity
        extends Activity {
    private View view;
    public TextView tvTitle;
    private Button btnTitleRight;
    private Button btnTitleLeft;
    private View titleView;
    private ImageView ivTitleRight;
    private ImageView ivTitleLeft;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
    }


    public View getTitleView() {
        return this.titleView;
    }


    public void setContentView(int layoutResID) {
        this.view = getLayoutInflater().inflate(R.layout.activity_base, null);

        LinearLayout content = (LinearLayout) this.view.findViewById(R.id.ll_base_content);
        this.titleView = ((LinearLayout) this.view).getChildAt(0);
//        ImmersionBar.with(this).statusBarColor("#Fe9900").titleBar(this.titleView).init();
        this.ivTitleRight = (ImageView) this.view.findViewById(R.id.ivTitleRight);
        this.ivTitleLeft = (ImageView) this.view.findViewById(R.id.ivTitleLeft);
        getLayoutInflater().inflate(layoutResID, content, true);
        setContentView(this.view);
        initUI();
        parserIntent(getIntent());
        initData();
        initEvent();
    }

    protected void hideTitle() {
        /*  69 */
        if (this.titleView != null) {
            /*  70 */
            this.titleView.setVisibility(View.GONE);
        }
    }

    protected void showTitle() {
        /*  75 */
        if (this.titleView != null) {
            /*  76 */
            this.titleView.setVisibility(View.VISIBLE);
        }
    }


    /*  81 */
    protected void startToActivity(Class<?> toActivity) {
        startActivity(new Intent(this, toActivity));
    }


    public void setTitle(CharSequence title) {
        /*  86 */
        if (this.tvTitle == null)
            /*  87 */ this.tvTitle = (TextView) this.view.findViewById(R.id.tvTitle);
        /*  88 */
        this.tvTitle.setText(title);
        /*  89 */
        setViewShow(this.tvTitle, true);
    }


    /*  93 */
    protected void setViewShow(View v, boolean show) {
        v.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }


    /*  98 */
    public void setTitle(int titleId) {
        setTitle(getResources().getString(titleId));
    }


    protected Button getRightView(String text, int resId, View.OnClickListener click) {
        /* 102 */
        if (this.btnTitleRight == null)
            /* 103 */ this.btnTitleRight = (Button) this.view.findViewById(R.id.btnTitleRight);
        /* 104 */
        setViewShow(this.btnTitleRight, true);
        /* 105 */
        if (text != null) {
            /* 106 */
            this.btnTitleRight.setText(text);
        }
        /* 108 */
        if (resId != -1 && resId != 0) {
            /* 109 */
            this.ivTitleRight.setImageResource(resId);
        }
        /* 111 */
        if (click != null) {
            /* 112 */
            this.btnTitleRight.setOnClickListener(click);
        }
        /* 114 */
        return this.btnTitleRight;
    }

    protected Button getLeftView(String text, int resId, View.OnClickListener click) {
        /* 118 */
        if (this.btnTitleLeft == null)
            /* 119 */ this.btnTitleLeft = (Button) this.view.findViewById(R.id.btnTitleLeft);
        /* 120 */
        setViewShow(this.btnTitleLeft, true);
        /* 121 */
        if (text != null) {
            /* 122 */
            this.btnTitleLeft.setText(text);
        }
        /* 124 */
        if (resId != -1 && resId != 0) {
            /* 126 */
            this.ivTitleLeft.setImageResource(resId);
        }
        /* 128 */
        if (click != null) {
            /* 129 */
            this.btnTitleLeft.setOnClickListener(click);
        }
        /* 131 */
        return this.btnTitleLeft;
    }


    /* 135 */
    protected ImageView getLeftImageView() {
        return this.ivTitleLeft;
    }


    /* 139 */
    protected ImageView getRightImageView() {
        return this.ivTitleRight;
    }


    protected void setLeftViewWillBack() {
        /* 144 */
        getLeftView(null, R.drawable.nav_return_normal, new View.OnClickListener() {
            public void onClick(View v) {
                /* 148 */
                onBackPressed();
            }
        });
    }


    protected abstract void initUI();

    protected abstract void parserIntent(Intent paramIntent);

    protected abstract void initData();

    protected abstract void initEvent();

    public boolean dispatchTouchEvent(MotionEvent ev) {
        /* 163 */
        if (ev.getAction() == 0) {
            /* 164 */
            View v = getCurrentFocus();
            /* 165 */
            if (isShouldHideInput(v, ev)) {
                /* 166 */
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                /* 167 */
                if (imm != null) {
                    /* 168 */
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            /* 171 */
            return super.dispatchTouchEvent(ev);
        }
        /* 173 */
        if (getWindow().superDispatchTouchEvent(ev)) {
            /* 174 */
            return true;
        }
        /* 176 */
        return onTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        /* 180 */
        if (v != null && v instanceof android.widget.EditText) {
            /* 181 */
            int[] leftTop = {0, 0};
            /* 182 */
            v.getLocationInWindow(leftTop);
            /* 183 */
            int left = leftTop[0];
            /* 184 */
            int top = leftTop[1];
            /* 185 */
            int bottom = top + v.getHeight();
            /* 186 */
            int right = left + v.getWidth();
            /* 187 */
            if (event.getX() > left && event.getX() < right && event
/* 188 */.getY() > top && event.getY() < bottom) {
                /* 189 */
                return false;
            }
            /* 191 */
            return true;
        }

        /* 194 */
        return false;
    }
}
