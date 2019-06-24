package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190507
 *     desc:知识体系文章列表 model 二级
 * </pre>
 */
public class SystemArticleListDatas {

//     "curPage": 1,
//             "datas":[]
//             "offset": 0,
//             "over": false,
//             "pageCount": 3,
//             "size": 20,
//             "total": 41

    private List<SystemArticleListDatasBean> datas;

    public List<SystemArticleListDatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<SystemArticleListDatasBean> datas) {
        this.datas = datas;
    }
}
