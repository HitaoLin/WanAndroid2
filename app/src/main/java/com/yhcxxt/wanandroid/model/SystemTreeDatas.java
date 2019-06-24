package com.yhcxxt.wanandroid.model;

import java.util.List;

public class SystemTreeDatas {

//     "children": []
//       "courseId": 13,
//               "id": 150,
//               "name": "开发环境",
//               "order": 1,
//               "parentChapterId": 0,
//               "userControlSetTop": false,
//               "visible": 1

    private List<SystemTreeDatasBean> children;
    private String name;

    public List<SystemTreeDatasBean> getChildren() {
        return children;
    }

    public void setChildren(List<SystemTreeDatasBean> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
