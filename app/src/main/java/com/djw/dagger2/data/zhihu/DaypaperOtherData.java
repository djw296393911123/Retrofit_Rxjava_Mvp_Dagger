package com.djw.dagger2.data.zhihu;

/**
 * Created by JasonDong on 2017/3/23.
 */

public class DaypaperOtherData {

    /**
     * long_comments : 12
     * popularity : 2
     * short_comments : 76
     * comments : 88
     */

    private int long_comments;
    private int popularity;
    private int short_comments;
    private int comments;

    public int getLong_comments() {
        return long_comments;
    }

    public void setLong_comments(int long_comments) {
        this.long_comments = long_comments;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getShort_comments() {
        return short_comments;
    }

    public void setShort_comments(int short_comments) {
        this.short_comments = short_comments;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
