package com.yhcxxt.wanandroid.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.utils.DigestUtils;
import com.yhcxxt.wanandroid.utils.TimeUtils;
import com.yhcxxt.wanandroid.views.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class BasePresenter {

    protected ProgressDialog mLoadingDialog;
    private static final String densityKey = "";

    protected  void initLoadDialog(Context context){

        //新的提示框
        mLoadingDialog = new LoadingDialog(context, R.style.CustomDialog);

    }

    /**
     *  MD5的Token
     * @return
     *          <p>model+action+时间戳+key做md5加密传递到服务器99-k ymd</p>
     */
    @CheckResult
    protected String getMD5OfToken(String model , String action) {

        if (TextUtils.isEmpty(model) || TextUtils.isEmpty(action)) {
            throw new IllegalArgumentException("paramter exception");
        }

        StringBuffer md5String = new StringBuffer();

        md5String.append(model);
        md5String.append(action);

        String timeTemplater = TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyyMMdd"));

        md5String.append(timeTemplater);

        md5String.append(densityKey);

        try {
            return  DigestUtils.md5(md5String.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 返回一个带有 token 的 MAP
     * @param model
     * @param action
     * @return
     */
    @CheckResult
    protected Map<String ,String> getDefaultMD5Params(@NonNull String model , @NonNull String action){
        Map<String, String> params = new HashMap<>();
        try {
            String token = getMD5OfToken(model, action);
            if (TextUtils.isEmpty(token)) return  null;

            params.put("api_token", token);
            return params;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 关闭数据加载框
     */
    protected void dismiss(){
        if (mLoadingDialog != null || mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }


}
