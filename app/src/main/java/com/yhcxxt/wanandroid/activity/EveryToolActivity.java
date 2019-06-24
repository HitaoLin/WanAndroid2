package com.yhcxxt.wanandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
/**
 * <pre>
 *      author:LHT
 *      time:20190622
 *      desc:每个工具 Activity
 * </pre>
 */
public class EveryToolActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout linear_back;//返回
    private TextView tv_title;//标题

    private WebView webView;

    private int mColor;

    @Override
    protected void setStatusBar() {
        mColor = getResources().getColor(R.color.wks_bg);
        StatusBarUtil.setColor(this, mColor, 0);
        if (mColor == getResources().getColor(R.color.white)) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//白色
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_tool);

        initView();

    }

    private void initView() {

        linear_back = findViewById(R.id.linear_back);
        tv_title = findViewById(R.id.tv_title);

        linear_back.setOnClickListener(this);
        tv_title.setText("工具");

        webView = findViewById(R.id.webview);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back://返回
                finish();
                break;
        }
    }
}
