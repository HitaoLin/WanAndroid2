package com.yhcxxt.wanandroid.model;

import java.util.List;
/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:首页文章 model 二级
 * </pre>
 */
public class ArticleData {

//     "data": {
//        "curPage": 1,
//                "datas":[]
//                "offset": 0,
//                "over": false,
//                "pageCount": 318,
//                "size": 20,
//                "total": 6356

    private String curPage;
    private List<ArticleDatasBean> datas;

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public List<ArticleDatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleDatasBean> datas) {
        this.datas = datas;
    }
}
