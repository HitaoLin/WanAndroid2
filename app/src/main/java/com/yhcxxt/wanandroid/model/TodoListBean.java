package com.yhcxxt.wanandroid.model;
/**
 * <pre>
 *     author:LHT
 *     date:20190720
 *     desc:新增Todo model 四级
 * </pre>
 */
public class TodoListBean {
    // "completeDate": null,
    //                        "completeDateStr": "",
    //                        "content": "详情",
    //                        "date": 1533052800000,
    //                        "dateStr": "2018-08-01",
    //                        "id": 11847,
    //                        "priority": 0,
    //                        "status": 0,
    //                        "title": "标题",
    //                        "type": 0,
    //                        "userId": 23071

    private String content;
    private String dateStr;
    private String id;
    private String status;//状态 未完成0，完成1
    private String title;
    private String type;
    private boolean expanded = true;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

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
