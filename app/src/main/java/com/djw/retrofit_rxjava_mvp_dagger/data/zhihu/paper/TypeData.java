package com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.paper;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class TypeData extends PaperBaseData {

    private String date;

    public TypeData(String data) {
        super(PaperBaseData.TYPE_TYPE);
        this.date = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TypeData{" +
                "date='" + date + '\'' +
                '}';
    }
}
