package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190724
 *     desc:未完成Todo model 二级
 * </pre>
 */
public class TodoListNotdoData {

//  "data": {
//        "curPage": 1,
//        "datas": [],
//        "offset": 0,
//        "over": true,
//        "pageCount": 0,
//        "size": 20,
//        "total": 0
//    },

    private List<TodoListNotdoDatasBean> datas;
    private String total;

    public List<TodoListNotdoDatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<TodoListNotdoDatasBean> datas) {
        this.datas = datas;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
