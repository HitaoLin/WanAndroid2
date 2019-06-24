package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.SystemArticleListModel;
import com.yhcxxt.wanandroid.model.SystemTreeModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.SystemArtcileListView;
import com.yhcxxt.wanandroid.view.SystemTreeView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190507
 *     desc:知识体系文章列表 presenter
 * </pre>
 */
public class SystemArticleListPresenter extends BasePresenter {

    private SystemArtcileListView systemArtcileListView;

    public SystemArticleListPresenter(SystemArtcileListView systemArtcileListView){
        this.systemArtcileListView = systemArtcileListView;
    }

    public void loadSystemArticleList(final Context context,String page,String id){
        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "article/list/"+ page + "/json?cid=" + id,
                new BaseDelegate.ResultCallback<SystemArticleListModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, ExceptionHelper.getMessage(e,context));
            }

            @Override
            public void onResponse(SystemArticleListModel response, Object tag) {
                systemArtcileListView.getSystemArtcileListData(response);
            }

        });

    }

}
