package com.yhcxxt.wanandroid.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * <p>
 *
 * @author :高磊华
 * @date : 2018/08/22
 * desc    : 自定义webview
 * </pre>
 */


public class GlwWebView extends WebView {

    public GlwWebView(Context context) {
        this(context, null);
    }

    public GlwWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GlwWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 没有过度拉伸波纹效果.
     * 如果哪一个webview需要过度拉伸波纹,在代码中setMyOverScrollMode
     *
     * @param mode
     */
    @Override
    public void setOverScrollMode(int mode) {
        super.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public void setMyOverScrollMode(int type) {
        super.setOverScrollMode(type);
    }


    /**
     * 重写是否显示ScrollBars
     *
     * @return
     */
    @Override
    protected boolean awakenScrollBars() {
        return false;
    }

    /**
     * 对html进行修改  让他可以自动适应窗口大小
     */
    @SuppressLint("SetJavaScriptEnabled")
    public void loadHtmlDetail(String htmlBody) {
        this.getSettings().setJavaScriptEnabled(true);
        loadData(getHtmlData(htmlBody), "text/html;charset=utf-8", "utf-8");
    }

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    public void loadUrl(String url) {
        this.getSettings().setJavaScriptEnabled(true);
        super.loadUrl(url);
    }


    /**
     * 拼接body部分
     *
     * @param htmlBody body部分
     * @return 拼接后的body部分
     */
    private String getHtmlData(String htmlBody) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, " +
                "user-scalable=no\"> " +
                "<style>img{max-width:100% !important; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body style:'height:auto;max-width: 100%; width:auto;'>" +
                htmlBody + "</body></html>";
    }
}