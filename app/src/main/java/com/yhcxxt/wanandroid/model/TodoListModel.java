package com.yhcxxt.wanandroid.model;


/**
 * <pre>
 *     author:LHT
 *     date:20190720
 *     desc:Todo列表数据 model 一级
 * </pre>
 */
public class TodoListModel extends BaseModel{

    private ArticleData data;

    public ArticleData getData() {
        return data;
    }

    public void setData(ArticleData data) {
        this.data = data;
    }
}
