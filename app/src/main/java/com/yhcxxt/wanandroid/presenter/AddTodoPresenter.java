package com.yhcxxt.wanandroid.presenter;

import android.content.Context;
import android.util.Log;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.AddTodoModel;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.LoginModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.AddTodoView;
import com.yhcxxt.wanandroid.view.ArticleView;

import java.util.Map;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190720
 *     desc:新增Todo presenter
 * </pre>
 */
public class AddTodoPresenter extends BasePresenter {

    private AddTodoView addTodoView;

    public AddTodoPresenter(AddTodoView addTodoView){
        this.addTodoView = addTodoView;
    }

    public void loadArticle(final Context context,String title,String content,String date,String type) {

        Map<String,String> params = getDefaultMD5Params("add","json");
        params.put("title", title);
        params.put("content",content);
        params.put("date",date);
        params.put("type",type);

        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "lg/todo/"+"add/json",
                params, new BaseDelegate.ResultCallback<AddTodoModel>() {

                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, ExceptionHelper.getMessage(e,context));
                    }

                    @Override
                    public void onResponse(AddTodoModel response, Object tag) {
                        addTodoView.getAddTodo(response);
                    }

                },true);

    }
}
