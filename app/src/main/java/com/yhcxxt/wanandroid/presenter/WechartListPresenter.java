package com.yhcxxt.wanandroid.presenter;

import android.content.Context;
import android.util.Log;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.WechartArticleModel;
import com.yhcxxt.wanandroid.model.WechartListModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.WechartArticleView;
import com.yhcxxt.wanandroid.view.WechartListView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:获取公众号列表 presenter
 * </pre>
 */
public class WechartListPresenter extends BasePresenter {

    private WechartListView wechartListView;

    public WechartListPresenter(WechartListView wechartListView){
        this.wechartListView = wechartListView;
    }

    public void loadWechartList(final Context context) {

        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "wxarticle/chapters"+"/json", new BaseDelegate.ResultCallback<WechartListModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, e.getMessage());
            }

            @Override
            public void onResponse(WechartListModel response, Object tag) {
                wechartListView.getWechartListData(response);
            }

        });

    }
}
