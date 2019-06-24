package com.yhcxxt.wanandroid.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
import com.yhcxxt.wanandroid.utils.TypefacesUtil;
import com.yhcxxt.wanandroid.widget.Lead;
import com.yhcxxt.wanandroid.widget.LeadTextView;

/**
 * <pre>
 *      author:LHT
 *      time:2019/4/19
 *      desc:启动页
 * </pre>
 */
public class SplashActivity extends BaseActivity {

    LeadTextView leadTv;
    private static final int mDuration = 3000;

    private int mColor;

    @Override
    protected void setStatusBar() {
        mColor = getResources().getColor(R.color.white);
        StatusBarUtil.setColor(this, mColor, 0);
        if (mColor == R.color.white) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//白色
        } else {
             getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initLeadText();

    }

    private void initLeadText(){
        leadTv = findViewById(R.id.leadTv);
        leadTv.setTypeface(TypefacesUtil.get(this,"Satisfy-Regular.ttf"));
        final Lead lead = new Lead(mDuration);
        lead.start(leadTv);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        },mDuration);
    }
}
