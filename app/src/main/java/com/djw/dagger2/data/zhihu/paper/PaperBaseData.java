package com.djw.dagger2.data.zhihu.paper;

/**
 * Created by JasonDong on 2017/3/24.
 */

public class PaperBaseData {

    public static final int BANNER_TYPE = 0x9001;
    public static final int LIST_TYPE = 0x9002;
    public static final int TYPE_TYPE = 0x9003;
    public static final int FOUR_TYPE = 0x9004;
    public static final int FIVE_TYPE = 0x9005;
    public static final int SIX_TYPE = 0x9006;

    private int type;

    public PaperBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
