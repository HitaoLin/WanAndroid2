package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.BannerModel;
import com.yhcxxt.wanandroid.model.SystemTreeModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.BannerView;
import com.yhcxxt.wanandroid.view.SystemTreeView;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190507
 *     desc:知识体系数据 presenter
 * </pre>
 */
public class SystemTreePresenter extends BasePresenter {

    private SystemTreeView systemTreeView;

    public SystemTreePresenter(SystemTreeView systemTreeView){
        this.systemTreeView = systemTreeView;
    }

    public void loadSystemTree(final Context context){
        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "tree/json", new BaseDelegate.ResultCallback<SystemTreeModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, ExceptionHelper.getMessage(e,context));
            }

            @Override
            public void onResponse(SystemTreeModel response, Object tag) {
                systemTreeView.getSystemTreeData(response);
            }

        });

    }

}
