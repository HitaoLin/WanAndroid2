package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190724
 *     desc:已完成Todo model 二级
 * </pre>
 */
public class TodoListDoneData {

//  "data": {
//        "curPage": 1,
//        "datas": [],
//        "offset": 0,
//        "over": true,
//        "pageCount": 0,
//        "size": 20,
//        "total": 0
//    },

    private List<TodoListDoneDatasBean> datas;
    private String total;

    public List<TodoListDoneDatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<TodoListDoneDatasBean> datas) {
        this.datas = datas;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
