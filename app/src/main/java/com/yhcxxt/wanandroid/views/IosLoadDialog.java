package com.yhcxxt.wanandroid.views;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.yhcxxt.wanandroid.R;


public class IosLoadDialog extends Dialog {

    ProgressBar loadingDialogImg;

    public IosLoadDialog(Context context) {
        super(context, R.style.loading_dialog);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (IosLoadDialog.this.isShowing())
                    IosLoadDialog.this.dismiss();
                break;
        }
        return true;
    }

    private void initView() {
        setContentView(R.layout.ios_loading);
        loadingDialogImg = findViewById(R.id.loading_dialog_img);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.8f;
        getWindow().setAttributes(attributes);
    }
}
