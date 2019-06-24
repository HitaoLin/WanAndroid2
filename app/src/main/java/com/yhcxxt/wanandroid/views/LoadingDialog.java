package com.yhcxxt.wanandroid.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;


import com.yhcxxt.wanandroid.R;

import java.util.Objects;

public class LoadingDialog extends ProgressDialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {

        //设置不可取消，点击其他区域不可取消，实际中可以抽出去封装供外包设置
        setCancelable(true);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.progress_loading);
        WindowManager.LayoutParams params = Objects.requireNonNull(getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

    }

    @Override
    public void show() {
        super.show();
    }

}
