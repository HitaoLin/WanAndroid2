package com.yhcxxt.wanandroid.utils;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 杨 on 2018/3/29.
 */

public class ActivityManagerApplication extends Application {
    private static List<Activity>  activityList=new LinkedList<Activity>();
    public ActivityManagerApplication(){

    }
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    public static void finishActivity(){
        for (Activity activity:activityList){
            activity.finish();
        }
        if (activityList.size()==0){
            activityList.clear();
        }
    }
}
