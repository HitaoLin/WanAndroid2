package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.TodoListDoneModel;
import com.yhcxxt.wanandroid.model.TodoListNotdoModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.TodoListDoneView;
import com.yhcxxt.wanandroid.view.TodoListNotdoView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190724
 *     desc:Todo列表未完成 presenter
 * </pre>
 */
public class TodoListNotdoPresenter extends BasePresenter {

    private TodoListNotdoView todoListNotdoView;

    public TodoListNotdoPresenter(TodoListNotdoView todoListNotdoView){
        this.todoListNotdoView = todoListNotdoView;
    }

    public void loadTodoList(final Context context,String type,String page) {

        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "lg/todo/" + "listnotdo/" + type+"/json/"+page, new BaseDelegate.ResultCallback<TodoListNotdoModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, ExceptionHelper.getMessage(e,context));
            }

            @Override
            public void onResponse(TodoListNotdoModel response, Object tag) {
                todoListNotdoView.getTodoListNotdo(response);
            }

        },true);

    }
}
