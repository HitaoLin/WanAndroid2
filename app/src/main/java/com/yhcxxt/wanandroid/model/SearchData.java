package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190528
 *     desc:搜索 model 二级
 * </pre>
 */
public class SearchData {

//     "data": {
//        "curPage": 1,
//                "datas":[]
//                "offset": 0,
//                "over": false,
//                "pageCount": 318,
//                "size": 20,
//                "total": 6356

    private String curPage;
    private List<SearchDatasBean> datas;
    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public List<SearchDatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<SearchDatasBean> datas) {
        this.datas = datas;
    }
}
