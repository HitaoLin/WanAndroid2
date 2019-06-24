package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190507
 *     desc:知识体系文章列表 model 一级
 * </pre>
 */
public class SystemArticleListModel extends BaseModel{

    private SystemArticleListDatas data;

    public SystemArticleListDatas getData() {
        return data;
    }

    public void setData(SystemArticleListDatas data) {
        this.data = data;
    }
}
