package com.yhcxxt.wanandroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * <p>
 *
 * @author :高磊华
 * @date : 2018/11/19
 * desc    : 监听webview加载完 回调的监听
 * </pre>
 */

public class LoadListenerWebView extends GlwWebView {

    public interface DisplayFinish {
        void After();
    }

    DisplayFinish df;

    public void setDf(DisplayFinish df) {
        this.df = df;
    }

    public LoadListenerWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadListenerWebView(Context context) {
        super(context);
    }


    /**
     * onDraw表示显示完毕
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (df != null) {
            df.After();
        }
    }
}