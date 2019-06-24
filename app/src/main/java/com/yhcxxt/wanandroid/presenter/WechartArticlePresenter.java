package com.yhcxxt.wanandroid.presenter;

import android.content.Context;
import android.util.Log;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.WechartArticleModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.ArticleView;
import com.yhcxxt.wanandroid.view.WechartArticleView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:微信公众号文章 presenter
 * </pre>
 */
public class WechartArticlePresenter extends BasePresenter {

    private WechartArticleView wechartArticleView;

    public WechartArticlePresenter(WechartArticleView wechartArticleView){
        this.wechartArticleView = wechartArticleView;
    }

    public void loadArticle(final Context context,String chapterId,String page) {

        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "wxarticle/list/"+chapterId +"/" + page + "/json", new BaseDelegate.ResultCallback<WechartArticleModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, e.getMessage());
            }

            @Override
            public void onResponse(WechartArticleModel response, Object tag) {
                wechartArticleView.getWechartArticleData(response);
                Log.e("response",response+"");
            }

        });

    }
}
