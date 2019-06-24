package com.yhcxxt.wanandroid.model;


/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:首页文章 model 一级
 * </pre>
 */
public class ArticleModel extends BaseModel{

    private ArticleData data;

    public ArticleData getData() {
        return data;
    }

    public void setData(ArticleData data) {
        this.data = data;
    }
}
