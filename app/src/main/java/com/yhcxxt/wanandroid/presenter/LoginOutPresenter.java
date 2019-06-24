package com.yhcxxt.wanandroid.presenter;

import android.content.Context;
import android.util.Log;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.LoginOutModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.ArticleView;
import com.yhcxxt.wanandroid.view.LoginOutView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190621
 *     desc:退出登录 presenter
 * </pre>
 */
public class LoginOutPresenter extends BasePresenter {

    private LoginOutView loginOutView;

    public LoginOutPresenter(LoginOutView loginOutView){
        this.loginOutView = loginOutView;
    }

    public void loadArticle(final Context context,String page) {

        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "user/logout/json", new BaseDelegate.ResultCallback<LoginOutModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, e.getMessage());
            }

            @Override
            public void onResponse(LoginOutModel response, Object tag) {
                loginOutView.getLoginOutData(response);
            }


        });

    }
}
