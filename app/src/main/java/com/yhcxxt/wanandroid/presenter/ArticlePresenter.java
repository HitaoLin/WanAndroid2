package com.yhcxxt.wanandroid.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.BannerModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.ArticleView;
import com.yhcxxt.wanandroid.view.BannerView;

import java.util.Map;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:首页文章 presenter
 * </pre>
 */
public class ArticlePresenter extends BasePresenter {

    private ArticleView articleView;

    public ArticlePresenter(ArticleView articleView){
        this.articleView = articleView;
    }

    public void loadArticle(final Context context,String page) {

        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "article/list/" + page + "/json", new BaseDelegate.ResultCallback<ArticleModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, e.getMessage());
            }

            @Override
            public void onResponse(ArticleModel response, Object tag) {
                articleView.getArticleData((ArticleModel) response);
                Log.e("response",response+"");
            }

        });

    }
}
