package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.BannerModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.BannerView;

import java.util.Map;

import okhttp3.Request;
/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:轮播图 presenter
 * </pre>
 */
public class BannerPresenter extends BasePresenter {

    private BannerView bannerView;

    public BannerPresenter(BannerView bannerView){
        this.bannerView = bannerView;
    }

    public void loadBanner(final Context context){
        Map<String,String> params = getDefaultMD5Params("banner","json");
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "banner/json",
                params, new BaseDelegate.ResultCallback<BannerModel>() {

                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, ExceptionHelper.getMessage(e,context));
                    }

                    @Override
                    public void onResponse(BannerModel response, Object tag) {
                        bannerView.getBannerData(response);
                    }

                },true);
    }

}
