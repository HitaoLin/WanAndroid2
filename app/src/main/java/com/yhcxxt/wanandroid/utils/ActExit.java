package com.yhcxxt.wanandroid.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 杨 on 2018/3/4.
 */

public class ActExit {

    private static Stack<Activity> activityStack;
    private static ActExit instance;


//
//    /**
//     * 单一实例
//     */
    public static ActExit getAppManager() {
        if (instance == null) {
            instance = new ActExit();
        }
        return instance;
    }
//
//    /**
//     * 添加Activity到堆栈
//     */
//    public void addActivity(Activity activity) {
//        if (activityStack == null) {
//            activityStack = new Stack<Activity>();
//        }
//        activityStack.add(activity);
//    }
//
//    /**
//     * 获取当前Activity（堆栈中最后一个压入的）
//     */
//    public Activity currentActivity() {
//        Activity activity = activityStack.lastElement();
//        return activity;
//    }
//
//    /**
//     * 结束当前Activity（堆栈中最后一个压入的）
//     */
//    public void finishActivity() {
//        Activity activity = activityStack.lastElement();
//        finishActivity(activity);
//    }
//
//    /**
//     * 结束指定的Activity
//     */
//    public void finishActivity(Activity activity) {
//        if (activity != null) {
//            activityStack.remove(activity);
//            activity.finish();
//            activity = null;
//        }
//    }
//
//    /**
//     * 结束指定类名的Activity
//     */
//    public void finishActivity(Class<?> cls) {
//        for (Activity activity : activityStack) {
//            if (activity.getClass().equals(cls)) {
//                finishActivity(activity);
//            }
//        }
//    }
//
//    /**f
//     * 结束所有Activity
//     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
//
//    /**
//     * 退出应用程序
//     */
    public void exitApp(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    private List<Activity> activityList=new LinkedList<Activity>();


    private ActExit(){

    }

//单例模式获取唯一的exitapplication

    public static ActExit getInstance(){

        if(null==instance){

            instance=new ActExit();

        }

        return instance;

    }

//添加activity到容器中

    public void addActivity(Activity activity){

        activityList.add(activity);

    }

//遍历所有的Activiy并finish

    public void exit(){

        for(Activity activity:activityList){

            activity.finish();

        }

        System.exit(0);

    }


}
