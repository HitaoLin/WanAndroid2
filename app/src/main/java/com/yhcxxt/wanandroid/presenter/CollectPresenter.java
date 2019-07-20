package com.yhcxxt.wanandroid.presenter;

import android.content.Context;
import android.util.Log;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.CollectModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.ArticleView;
import com.yhcxxt.wanandroid.view.CollectView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190718
 *     desc:收藏 presenter
 * </pre>
 */
public class CollectPresenter extends BasePresenter {

    private CollectView collectView;

    public CollectPresenter(CollectView collectView){
        this.collectView = collectView;
    }

    public void loadCollect(final Context context,String page) {

        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "lg/collect/list/" + page + "/json", new BaseDelegate.ResultCallback<CollectModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, e.getMessage());
            }

            @Override
            public void onResponse(CollectModel response, Object tag) {
                collectView.getCollect(response);
                Log.e("response",response+"");
            }

        });

    }
}
