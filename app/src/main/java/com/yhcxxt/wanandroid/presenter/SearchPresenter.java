package com.yhcxxt.wanandroid.presenter;

import android.content.Context;
import android.util.Log;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.SearchModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.ArticleView;
import com.yhcxxt.wanandroid.view.SearchView;

import java.util.Map;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190528
 *     desc:搜索 presenter
 * </pre>
 */
public class SearchPresenter extends BasePresenter {

    private SearchView searchView;

    public SearchPresenter(SearchView searchView){
        this.searchView = searchView;
    }

    public void loadSearch(final Context context,String page,String content) {

        Map<String,String> params = getDefaultMD5Params(page,"json");
        params.put("k", content);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "article/query/"+page+"/json",
                params, new BaseDelegate.ResultCallback<SearchModel>() {

                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, ExceptionHelper.getMessage(e,context));
                    }

                    @Override
                    public void onResponse(SearchModel response, Object tag) {
                        searchView.getSearchData(response);
                    }
                },true);


    }
}
