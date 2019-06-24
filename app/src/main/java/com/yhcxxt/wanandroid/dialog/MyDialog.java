package com.yhcxxt.wanandroid.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;


/**
 * Created by sks on 2015/10/22.
 * 自定义dialog
 */
public class MyDialog extends Dialog {

    private TextView txtDialogTitle;
    private TextView txtDialogContent;
    private TextView cancel,sure;
    private String title,content,leftText,rightText;
    private View.OnClickListener onClickListener;
    private Activity context;
    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Activity context, int themeResId, String title, String content, String leftText, String rightText,
                    View.OnClickListener onClickListener) {
        super(context, themeResId);
        this.context = context;
        this.title = title;
        this.content = content;
        this.leftText = leftText;
        this.rightText = rightText;
        this.onClickListener = onClickListener;
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog_view);

        txtDialogTitle = (TextView) findViewById(R.id.txtDialogTitle);
        txtDialogContent = (TextView) findViewById(R.id.txtDialogContent);
        cancel = (TextView) findViewById(R.id.txtDialogCancel);
        sure = (TextView) findViewById(R.id.txtDialogSure);
        txtDialogTitle.setText(title);
        txtDialogContent.setText(content);
        if (!leftText.equals("")){
            cancel.setText(leftText);
        }
        if (!rightText.equals("")){
            sure.setText(rightText);
        }
        cancel.setOnClickListener(onClickListener);
        sure.setOnClickListener(onClickListener);
        Window dialogWindow = getWindow();
        /*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay();// 获取屏幕宽、高
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
//        p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.85); // 宽度设置为屏幕的0.85
        dialogWindow.setAttributes(p);

    }
}
