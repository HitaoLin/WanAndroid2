package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190507
 *     desc:知识体系数据 model
 * </pre>
 */
public class SystemTreeModel extends BaseModel{

    List<SystemTreeDatas> data;

    public List<SystemTreeDatas> getData() {
        return data;
    }

    public void setData(List<SystemTreeDatas> data) {
        this.data = data;
    }
}
