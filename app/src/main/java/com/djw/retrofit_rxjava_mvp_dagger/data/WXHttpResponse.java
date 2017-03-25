package com.djw.retrofit_rxjava_mvp_dagger.data;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class WXHttpResponse<T> {

    private int code;

    private String msg;

    private T newsList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getNewsList() {
        return newsList;
    }

    public void setNewsList(T newsList) {
        this.newsList = newsList;
    }
}
