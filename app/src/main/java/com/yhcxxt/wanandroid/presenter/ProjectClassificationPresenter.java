package com.yhcxxt.wanandroid.presenter;

import android.content.Context;

import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.http.BaseDelegate;
import com.yhcxxt.wanandroid.http.ExceptionHelper;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.ProjectClassificationModel;
import com.yhcxxt.wanandroid.model.SystemTreeModel;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.ProjectClassificationView;
import com.yhcxxt.wanandroid.view.SystemTreeView;

import okhttp3.Request;

/**
 * <pre>
 *     author:LHT
 *     date:20190521
 *     desc:项目分类 presenter
 * </pre>
 */
public class ProjectClassificationPresenter extends BasePresenter {

    private ProjectClassificationView projectClassificationView;

    public ProjectClassificationPresenter(ProjectClassificationView projectClassificationView){
        this.projectClassificationView = projectClassificationView;
    }

    public void loadProjectClassification(final Context context){
        OkHttpClientManager.getAsyn(context, ConfigValue.APP_IP + "project/tree/json", new BaseDelegate.ResultCallback<ProjectClassificationModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                Utils.showToast(context, ExceptionHelper.getMessage(e,context));
            }

            @Override
            public void onResponse(ProjectClassificationModel response, Object tag) {
               projectClassificationView.getProjectClassificationData(response);
            }

        });

    }

}
