package com.djw.dagger2.data.zhihu.paper;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/24.
 */

public class BannerData extends PaperBaseData {

    private List<String> titles;

    private List<String> imgs;

    private List<String> ids;

    public BannerData(List<String> titles, List<String> imgs, List<String> ids) {
        super(PaperBaseData.BANNER_TYPE);
        this.titles = titles;
        this.imgs = imgs;
        this.ids = ids;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "BannerData{" +
                "titles=" + titles +
                ", imgs=" + imgs +
                ", ids=" + ids +
                '}';
    }
}
