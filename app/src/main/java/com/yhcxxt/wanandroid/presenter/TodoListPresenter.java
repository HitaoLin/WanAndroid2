package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.AddTodoModel;
import com.yhcxxt.wanandroid.model.TodoListModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.AddTodoView;
import com.yhcxxt.wanandroid.view.TodoListView;

import java.util.Map;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190720
 *     desc:Todo列表数据 presenter
 * </pre>
 */
public class TodoListPresenter extends BasePresenter {

    private TodoListView todoListView;

    public TodoListPresenter(TodoListView todoListView){
        this.todoListView = todoListView;
    }

    public void loadTodoList(final Context context,String type) {

        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "lg/todo/" + "list/" + type+"/json", new BaseDelegate.ResultCallback<TodoListModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, ExceptionHelper.getMessage(e,context));
            }

            @Override
            public void onResponse(TodoListModel response, Object tag) {
                todoListView.getTodoList(response);
            }

        },true);

    }
}
