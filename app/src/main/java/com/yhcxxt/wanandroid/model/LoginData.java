package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190621
 *     desc:登录 model 二级
 * </pre>
 */
public class LoginData {

//    "admin": false,
//        "chapterTops": [],
//        "collectIds": [
//            8145,
//            7730
//        ],
//        "email": "",
//        "icon": "",
//        "id": 23071,
//        "password": "",
//        "token": "",
//        "type": 0,
//        "username": "linhaitao"

    private List<String> collectIds;
    private String username;

    public List<String> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<String> collectIds) {
        this.collectIds = collectIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
