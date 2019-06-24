package com.yhcxxt.wanandroid.model;

import java.util.List;

public class WechartListData {

//    "data": [
//    {
//        "children": [],
//        "courseId": 13,
//            "id": 408,
//            "name": "鸿洋",
//            "order": 190000,
//            "parentChapterId": 407,
//            "userControlSetTop": false,
//            "visible": 1
//    },

    private String id;//公众号ID
    private String name;//名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
