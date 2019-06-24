package com.yhcxxt.wanandroid.model;


/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:微信公众号文章 model 一级
 * </pre>
 */
public class WechartArticleModel extends BaseModel{

    private WechartArticleData data;

    public WechartArticleData getData() {
        return data;
    }

    public void setData(WechartArticleData data) {
        this.data = data;
    }
}
