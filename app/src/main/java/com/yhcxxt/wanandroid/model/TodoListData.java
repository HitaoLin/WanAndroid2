package com.yhcxxt.wanandroid.model;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190720
 *     desc:新增Todo model 二级
 * </pre>
 */
public class TodoListData {

//    "data": {
//        "doneList": [],
//        "todoList":[],
//        "type": 0
//    },

private List<TodoListDataBean> todoList;
private List<TodoListDataBean> doneList;
private String type;

    public List<TodoListDataBean> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoListDataBean> todoList) {
        this.todoList = todoList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TodoListDataBean> getDoneList() {
        return doneList;
    }

    public void setDoneList(List<TodoListDataBean> doneList) {
        this.doneList = doneList;
    }
}
