package com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.paper;

/**
 * Created by JasonDong on 2017/3/24.
 */

public class ListData extends PaperBaseData {

    private String title;

    private String url;

    private int id;

    public ListData(String title, String url, int id) {
        super(PaperBaseData.LIST_TYPE);
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "PaperData{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
