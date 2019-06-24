package com.yhcxxt.wanandroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

public class LoadWebView extends WebView {

    public interface PlayFinish{
        void After();
    }
    PlayFinish df;
    public void setDf(PlayFinish playFinish) {
        this.df = playFinish;
    }
    public LoadWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public LoadWebView(Context context) {
        super(context);
    }
    //onDraw表示显示完毕
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        df.After();
    }

}
