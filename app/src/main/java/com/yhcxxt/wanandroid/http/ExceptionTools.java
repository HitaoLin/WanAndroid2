package com.yhcxxt.wanandroid.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

/**
 * Created by momo on 2015/10/26.
 */
public class ExceptionTools {


    /**
     * 异常的处理
     * @param context
     * @param e
     */
    public static void handlerException(@NonNull Context context, @NonNull Exception e) {

        if (e != null) {
            if (e instanceof TimeoutException) {
                //链接超时


            }else if (e instanceof ConnectException) {
                //链接错误,没有网络


            }else if (e instanceof JsonSyntaxException) {
                //json 预防错误


            }else if (e instanceof JsonParseException) {
                //json 解析错误


            }


        }


    }


}
