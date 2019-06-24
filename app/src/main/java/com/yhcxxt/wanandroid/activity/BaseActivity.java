package com.yhcxxt.wanandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yhcxxt.wanandroid.utils.StatusBarUtil;


public class BaseActivity extends AppCompatActivity {
    private int baise = 0x00000000;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*//透明状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(baise);
        }*/
        //去掉状态栏
       /* getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );*/

        setStatusBar();

        mContext = this;

    }
    protected void setStatusBar() {
        //这里做了两件事情，1.使状态栏透明并使contentView填充到状态栏 2.预留出状态栏的位置，防止界面上的控件离顶部靠的太近。这样就可以实现开头说的第二种情况的沉浸式状态栏了
        StatusBarUtil.setTransparent(this);
    }




}
