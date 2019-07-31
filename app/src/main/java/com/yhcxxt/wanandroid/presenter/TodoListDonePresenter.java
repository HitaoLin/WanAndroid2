package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.TodoListDoneModel;
import com.yhcxxt.wanandroid.model.TodoListModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.TodoListDoneView;
import com.yhcxxt.wanandroid.view.TodoListView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190724
 *     desc:Todo列表已完成 presenter
 * </pre>
 */
public class TodoListDonePresenter extends BasePresenter {

    private TodoListDoneView todoListDoneView;

    public TodoListDonePresenter(TodoListDoneView todoListDoneView){
        this.todoListDoneView = todoListDoneView;
    }

    public void loadTodoList(final Context context,String type,String page) {

        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "lg/todo/" + "listdone/" + type+"/json/"+page, new BaseDelegate.ResultCallback<TodoListDoneModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, ExceptionHelper.getMessage(e,context));
            }

            @Override
            public void onResponse(TodoListDoneModel response, Object tag) {
                todoListDoneView.getTodoListDone(response);
            }

        },true);

    }
}
