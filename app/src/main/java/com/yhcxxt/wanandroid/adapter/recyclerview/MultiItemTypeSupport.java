package com.yhcxxt.wanandroid.adapter.recyclerview;

/**
 * 支持多种布局
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);

}
