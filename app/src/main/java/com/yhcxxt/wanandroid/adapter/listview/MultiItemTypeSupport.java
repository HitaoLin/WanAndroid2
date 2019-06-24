package com.yhcxxt.wanandroid.adapter.listview;

/**
 * support multitype interface
 */
public interface MultiItemTypeSupport<T> {


    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int postion, T t);


}
