package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.LoginModel;
import com.yhcxxt.wanandroid.model.RegisterModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.LoginView;
import com.yhcxxt.wanandroid.view.RegisterView;

import java.util.Map;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190621
 *     desc:注册 presenter
 * </pre>
 */
public class RegisterPresenter extends BasePresenter {

    private RegisterView registerView;

    public RegisterPresenter( RegisterView registerView){
        this.registerView = registerView;
    }

    public void loadRegister(final Context context,String username,String password,String repassword) {

        Map<String,String> params = getDefaultMD5Params("user","register");
        params.put("username", username);
        params.put("password",password);
        params.put("repassword",repassword);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "user/register",
                params, new BaseDelegate.ResultCallback<RegisterModel>() {

                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, ExceptionHelper.getMessage(e,context));
                    }

                    @Override
                    public void onResponse(RegisterModel response, Object tag) {
                        registerView.getRegisterData(response);
                    }

                },true);
    }
}
