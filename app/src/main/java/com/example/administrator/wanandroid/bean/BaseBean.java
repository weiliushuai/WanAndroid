package com.example.administrator.wanandroid.bean;

import java.io.Serializable;

/**
 * Created by： wls
 * Created in： 2018/3/22
 * Describption：
 */

public class BaseBean implements Serializable{

    private int errorCode;
    private String errorMsg;
    private DataBean data;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
