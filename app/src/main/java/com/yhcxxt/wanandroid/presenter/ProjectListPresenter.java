package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.ProjectClassificationModel;
import com.yhcxxt.wanandroid.model.ProjectListModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.ProjectClassificationView;
import com.yhcxxt.wanandroid.view.ProjectListView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190521
 *     desc:项目列表数据 presenter
 * </pre>
 */
public class ProjectListPresenter extends BasePresenter {

    private ProjectListView projectListView;

    public ProjectListPresenter(ProjectListView projectListView){
        this.projectListView = projectListView;
    }

    public void loadProjectList(final Context context,String page,String cid){
        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "project/list/"+page+"/json?cid="+cid , new BaseDelegate.ResultCallback<ProjectListModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, ExceptionHelper.getMessage(e,context));
            }

            @Override
            public void onResponse(ProjectListModel response, Object tag) {
               projectListView.getProjectListData(response);
            }

        });

    }

}
