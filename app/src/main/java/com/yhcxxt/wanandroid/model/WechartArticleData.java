package com.yhcxxt.wanandroid.model;

import java.util.List;
/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:微信公众号文章 model 二级
 * </pre>
 */
public class WechartArticleData {

//     "data": {
//        "curPage": 1,
//                "datas":[]
//                "offset": 0,
//                "over": false,
//                "pageCount": 318,
//                "size": 20,
//                "total": 6356

    private String curPage;
    private List<WechartArticleDatasBean> datas;

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public List<WechartArticleDatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<WechartArticleDatasBean> datas) {
        this.datas = datas;
    }
}
