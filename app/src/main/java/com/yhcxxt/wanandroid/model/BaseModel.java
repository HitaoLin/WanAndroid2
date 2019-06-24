package com.yhcxxt.wanandroid.model;

import java.io.Serializable;

public class BaseModel implements Serializable {

    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
