package com.yhcxxt.wanandroid.model;


import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:微信公众号文章 model
 * </pre>
 */
public class WechartListModel extends BaseModel{

    private List<WechartListData> data;

    public List<WechartListData> getData() {
        return data;
    }

    public void setData(List<WechartListData> data) {
        this.data = data;
    }
}
