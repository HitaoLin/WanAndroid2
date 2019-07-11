package com.yhcxxt.wanandroid.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
import com.yhcxxt.wanandroid.views.IosLoadDialog;
import com.yhcxxt.wanandroid.views.ProgressWebview;

/**
 * <pre>
 *      author:LHT
 *      time:2019/4/30
 *      desc:轮播图文章 Activity
 * </pre>
 */
public class BannerActivity extends BaseActivity {

    private Toolbar toolbar;
    //    private WebView webView;
    private ProgressWebview webView;
    String url;
    String title;

//    private IosLoadDialog dialog;
    private boolean isLoading;

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
        setContentView(R.layout.activity_banner);

//        dialog = new IosLoadDialog(this);
//        dialog.show();

        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.webview);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");

        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        webView.getSettings().setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        webView.loadUrl(url);          //调用loadUrl方法为WebView加入链接

//
////        webView.getSettings().setJavaScriptEnabled(true);
//
//        //防止webview启动系统浏览器
//        webView.setWebViewClient(new WebViewClient() {
//            //覆盖shouldOverrideUrlLoading 方法
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });
//
//        //WebView保留缩放功能但隐藏缩放控件
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setDisplayZoomControls(false);
//
//
//        webView.loadUrl(url);
//*
//         * date:20190430
//         * problem:webview重复加载
//         * solve:https://www.jianshu.com/p/1366529e7941
//
//
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                isLoading = true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                if (isLoading) {
//                    isLoading = false;
//                    //做些处理
//                    dialog.dismiss();
//                    return;
//                }
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return false;
//            }
//        });
//


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_banner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                //调用系统自带分享
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "标题：" + title + "\n" + url);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
//                Toast.makeText(this, "敬请期待！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_collect:
                Toast.makeText(this, "敬请期待！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_open:
//                Toast.makeText(this, "我是第三个", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse(url);
                Intent intentAbout = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentAbout);
                break;

        }
        return true;
    }


}
