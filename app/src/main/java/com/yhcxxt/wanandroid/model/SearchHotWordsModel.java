package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190524
 *     desc:搜索热词 model
 * </pre>
 */
public class SearchHotWordsModel extends BaseModel{

    List<SearchHotWordsDatas> data;

    public List<SearchHotWordsDatas> getData() {
        return data;
    }

    public void setData(List<SearchHotWordsDatas> data) {
        this.data = data;
    }
}
