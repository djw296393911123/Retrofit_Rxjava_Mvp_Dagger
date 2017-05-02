package com.djw.dagger2.data.zhihu;

/**
 * Created by JasonDong on 2017/4/26.
 */

public class ThemNewData {

    private String author;

    private String title;

    private String head;

    private String url;

    private int id;

    public ThemNewData(String author, String title, String head, String url,int id) {
        this.author = author;
        this.title = title;
        this.head = head;
        this.url = url;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
