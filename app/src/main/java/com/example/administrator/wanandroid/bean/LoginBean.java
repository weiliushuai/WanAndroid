package com.example.administrator.wanandroid.bean;

import android.text.Editable;
import android.text.TextWatcher;

import java.io.Serializable;
import java.util.List;

/**
 * Created by： wls
 * Created in： 2018/3/20
 * Describption：
 */

public class LoginBean{

    /**
     * id : 1847
     * username : 猥琐的豆腐6
     * password : 123456
     * icon : null
     * type : 0
     * collectIds : [2239]
     */
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
        private int id;
        private String username;
        private String password;
        private Object icon;
        private int type;
        private List<Integer> collectIds;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }

        @Override
        public String toString() {
            return "LoginBean{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", icon=" + icon +
                    ", type=" + type +
                    ", collectIds=" + collectIds +
                    '}';
        }
    }
}
