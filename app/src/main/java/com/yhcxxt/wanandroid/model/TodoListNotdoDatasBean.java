package com.yhcxxt.wanandroid.model;

/**
 * <pre>
 *     author:LHT
 *     date:20190724
 *     desc:未完成Todo model 三级
 * </pre>
 */
public class TodoListNotdoDatasBean {
// {
//                "completeDate": null,
//                "completeDateStr": "",
//                "content": "jjj",
//                "date": 1563984000000,
//                "dateStr": "2019-07-25",
//                "id": 11878,
//                "priority": 0,
//                "status": 0,
//                "title": "jjfghjjhjjjk",
//                "type": 0,
//                "userId": 23071
//            },

    private String content;//内容
    private String dateStr;//时间
    private String id;
    private String status;//状态
    private String title; //标题
    private String type;//类型

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
