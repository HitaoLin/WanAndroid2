package com.yhcxxt.wanandroid.model;

import java.util.List;
/**
 * <pre>
 *     author:LHT
 *     date:20190720
 *     desc:新增Todo model 三级
 * </pre>
 */
public class TodoListDataBean {
// "date": 1533052800000,
//                "todoList": []

    private List<TodoListBean> todoList;

    public List<TodoListBean> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoListBean> todoList) {
        this.todoList = todoList;
    }
}
