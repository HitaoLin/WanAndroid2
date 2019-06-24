package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190430
 *     desc:轮播图 model
 * </pre>
 */
public class BannerModel extends BaseModel{

    List<BannerBean> data;

    public List<BannerBean> getData() {
        return data;
    }

    public void setData(List<BannerBean> data) {
        this.data = data;
    }
}
