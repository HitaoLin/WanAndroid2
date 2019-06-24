package com.yhcxxt.wanandroid.model;


/**
 * <pre>
 *     author:LHT
 *     date:20190621
 *     desc:退出登录  model
 * </pre>
 */
public class LoginOutModel extends BaseModel{

    private LoginData data;

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
