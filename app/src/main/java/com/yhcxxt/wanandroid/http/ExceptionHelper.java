package com.yhcxxt.wanandroid.http;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.google.gson.JsonParseException;
import com.yhcxxt.wanandroid.utils.Utils;


import java.net.UnknownHostException;

public class ExceptionHelper {
	public static String getMessage(Exception exception, Context context) {
		if (isNetworkProblem(context, exception)) {
			return "网络异常！请链接网络";
		} else if (isServerProblem(exception)) {
			return "服务器异常！";
		} else if(isJsonParseException(exception)){
			return "数据解析异常！";
		}
		return "服务器异常";
	}

	private static boolean isNetworkProblem(Context context, Exception exception) {
		if (exception instanceof NetworkErrorException) {
			return true;
		}
		if (exception instanceof UnknownHostException) {
			return Utils.isNetworkAvailable(context) == 0;
		}
		return false;
	}

	private static boolean isServerProblem(Exception exception) {
		return (exception instanceof UnknownHostException);
	}

	private static boolean isJsonParseException(Exception exception) {
		return (exception instanceof JsonParseException);
	}

}
