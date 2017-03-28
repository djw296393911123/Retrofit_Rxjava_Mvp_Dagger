package com.djw.dagger2.data;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class WXHttpResponse<T> {

    private int code;

    private String msg;

    private T newslist;

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

    public T getNewslist() {
        return newslist;
    }

    public void setNewslist(T newslist) {
        this.newslist = newslist;
    }
}
