package com.djw.dagger2.data.zhihu.paper;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/24.
 */

public class SelectFourData extends PaperBaseData {

    private List<String> title;

    private List<Integer> url;

    private List<Integer> ids;

    public SelectFourData(List<String> title, List<Integer> url,List<Integer> ids) {
        super(PaperBaseData.FOUR_TYPE);
        this.title = title;
        this.url = url;
        this.ids = ids;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<Integer> getUrl() {
        return url;
    }

    public void setUrl(List<Integer> url) {
        this.url = url;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
