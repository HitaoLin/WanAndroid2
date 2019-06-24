package com.yhcxxt.wanandroid.presenter;

import android.content.Context;
import android.util.Log;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.LoginModel;
import com.yhcxxt.wanandroid.model.SearchModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.ArticleView;
import com.yhcxxt.wanandroid.view.LoginView;

import java.util.Map;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190621
 *     desc:登录 presenter
 * </pre>
 */
public class LoginPresenter extends BasePresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void loadLogin(final Context context,String username,String password) {

        Map<String,String> params = getDefaultMD5Params("user","login");
        params.put("username", username);
        params.put("password",password);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "user/login",
                params, new BaseDelegate.ResultCallback<LoginModel>() {

                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, ExceptionHelper.getMessage(e,context));
                    }

                    @Override
                    public void onResponse(LoginModel response, Object tag) {
                        loginView.getLoginData(response);
                    }

                },true);
    }
}
