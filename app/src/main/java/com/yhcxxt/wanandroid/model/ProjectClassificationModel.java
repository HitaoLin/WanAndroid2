package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190521
 *     desc:项目分类 model
 * </pre>
 */
public class ProjectClassificationModel extends BaseModel{

    List<ProjectClassificationDatas> data;

    public List<ProjectClassificationDatas> getData() {
        return data;
    }

    public void setData(List<ProjectClassificationDatas> data) {
        this.data = data;
    }
}
