package com.yhcxxt.wanandroid.http;

import android.content.Context;


import com.yhcxxt.wanandroid.utils.Utils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetDelegate extends BaseDelegate {
	public GetDelegate(OkHttpClient mOkHttpClient) {
		super(mOkHttpClient);
	}

	private Request buildGetRequest(Context context, String url, boolean forceNetWork, Object tag) {
		Request.Builder builder = null;
		if (forceNetWork) {// 强制走网络 对于特殊请求
			builder = new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(url);
		} else if (Utils.isNetworkAvailable(context) == 0) {// 没有网络则强制请求缓存中数据
			builder = new Request.Builder().cacheControl(CacheControl.FORCE_CACHE).url(url);
		} else {// 否则走网络
			builder = new Request.Builder().url(url);
		}
		if (tag != null) {
			builder.tag(tag);
		}
		return builder.build();
	}

	private Request buildGetRequest(String url, Object tag) {
		return buildGetRequest(null, url, false, tag);
	}

	/**
	 * 通用的方法
	 */
	public Response get(Request request) throws IOException {
		Call call = mOkHttpClient.newCall(request);
		Response execute = call.execute();
		return execute;
	}

	/**
	 * 同步的Get请求
	 */
	public Response get(String url) throws IOException {
		return get(url, null);
	}

	public Response get(String url, Object tag) throws IOException {
		final Request request = buildGetRequest(url, tag);
		return get(request);
	}

	/**
	 * 同步的Get请求
	 */
	public String getAsString(String url) throws IOException {
		return getAsString(url, null);
	}

	public String getAsString(String url, Object tag) throws IOException {
		Response execute = get(url, tag);
		return execute.body().string();
	}

	/**
	 * 通用的方法
	 */
	public void getAsyn(Context context, Request request, ResultCallback callback) {
		deliveryResult(context, callback, request);
	}

	/**
	 * 异步的get请求
	 */
	public void getAsyn(Context context, String url, final ResultCallback callback, boolean forceNetWork) {
		getAsyn(context, url, callback, forceNetWork, null);
	}

	public void getAsyn(Context context, String url, final ResultCallback callback, boolean forceNetWork, Object tag) {
		final Request request = buildGetRequest(context, url, forceNetWork, tag);
		getAsyn(context, request, callback);
	}
}
