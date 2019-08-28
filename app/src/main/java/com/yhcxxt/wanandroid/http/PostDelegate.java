package com.yhcxxt.wanandroid.http;

import android.content.Context;
import android.util.Log;


import com.yhcxxt.wanandroid.config.SPConfig;
import com.yhcxxt.wanandroid.utils.SPUtils;
import com.yhcxxt.wanandroid.utils.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//import okhttp3.FormEncodingBuilder;
//import okhttp3.FormEncodingBuilder;

//====================PostDelegate=======================
public class PostDelegate extends BaseDelegate {
    public PostDelegate(OkHttpClient mOkHttpClient) {
        super(mOkHttpClient);
    }

    private Request buildPostFormRequest(Context context, String url, Map<String, String> params, boolean forceNetWork, Object tag) {

        if (params == null) {
            params = new HashMap<String, String>();
        }

        //demo
//		FormBody body = new FormBody.Builder()
//				.add("your_param_1", "your_value_1")
//				.add("your_param_2", "your_value_2")
//				.build();

        FormBody.Builder builder = null;

        if (params.size() > 0) {
            builder = new FormBody.Builder();
        }

        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
//				System.out.println("Key = " + entry.getKey() + ", Value = "+ entry.getValue());
            builder.add(entry.getKey(), entry.getValue());
        }

        //		Set<Map.Entry<String, String>> entries = params.entrySet();
//		for (Map.Entry<String, String> entry : entries) {
//		 new FormBody.Builder() = body.addFormDataPart(entry.getKey(), entry.getValue());
//		//	.build();
//		}

        RequestBody body = builder.build();


//		//new

        Request.Builder reqBuilder = null;
        if (forceNetWork) {// 强制走网络 对于特殊请求
            reqBuilder = new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(url);

        } else if (Utils.isNetworkAvailable(context) == 0) {// 没有网络则强制请求缓存中数据
            reqBuilder = new Request.Builder().cacheControl(CacheControl.FORCE_CACHE).url(url);
        } else {// 否则走网络
            reqBuilder = new Request.Builder().url(url);
        }
        if (builder != null) {
//			RequestBody requestBody = builder.build();
//			RequestBody requestBody =  new FormBody.Builder().build();
            reqBuilder.post(body);
        }
        if (tag != null) {
            reqBuilder.tag(tag);
        }
      String cookie= (String) SPUtils.get(context, SPConfig.COOKIE,"");

        if (!cookie.isEmpty())
            reqBuilder.addHeader("Cookie",cookie.replace(" ",""));
        Log.e("TAG2",cookie);
        return reqBuilder.build();

    }

    /**
     * 同步的Post请求
     */
    public Response post(Context context, String url, Map<String, String> params) throws IOException {
        return post(context, url, params, false, null);
    }

    public Response post(Context context, String url, Map<String, String> params, boolean forceNetWork) throws IOException {
        return post(context, url, params, false, null);
    }

    public Response post(Context context, String url, Map<String, String> params, boolean forceNetWork, Object tag) throws IOException {
        Request request = buildPostFormRequest(context, url, params, forceNetWork, tag);
        Response response = mOkHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 异步的Post请求
     */
    public void postAsyn(Context context, String url, Map<String, String> params, final ResultCallback callback) {
        postAsyn(context, url, params, callback, false, null);
    }

    public void postAsyn(Context context, String url, Map<String, String> params, final ResultCallback callback, boolean forceNetWork) {
        postAsyn(context, url, params, callback, forceNetWork, null);
    }

    public void postAsyn(Context context, String url, Map<String, String> params, final ResultCallback callback, Object tag) {
        postAsyn(context, url, params, callback, false, null);
    }

    public void postAsyn(Context context, String url, Map<String, String> params, final ResultCallback callback, boolean forceNetWork, Object tag) {
        Request request = buildPostFormRequest(context, url, params, forceNetWork, tag);
        deliveryResult(context, callback, request);
    }

}
