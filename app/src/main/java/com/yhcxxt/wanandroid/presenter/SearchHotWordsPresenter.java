package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.ProjectListModel;
import com.yhcxxt.wanandroid.model.SearchHotWordsModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.ProjectListView;
import com.yhcxxt.wanandroid.view.SearchHotWordsView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190524
 *     desc:搜索热词 presenter
 * </pre>
 */
public class SearchHotWordsPresenter extends BasePresenter {

    private SearchHotWordsView searchHotWordsView;

    public SearchHotWordsPresenter(SearchHotWordsView searchHotWordsView){
        this.searchHotWordsView = searchHotWordsView;
    }

    public void loadProjectList(final Context context){
        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "hotkey/json", new BaseDelegate.ResultCallback<SearchHotWordsModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, ExceptionHelper.getMessage(e,context));
            }

            @Override
            public void onResponse(SearchHotWordsModel response, Object tag) {
                searchHotWordsView.getSearchHotWordsData(response);
            }

        });

    }

}
