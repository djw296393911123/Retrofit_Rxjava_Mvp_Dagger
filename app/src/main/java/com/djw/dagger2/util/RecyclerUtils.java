package com.djw.dagger2.util;

import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JasonDong on 2017/3/24.
 */

public class RecyclerUtils {

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange() && recyclerView.canScrollVertically(-1))
            return true;
        return false;
    }

    public static long getBeforeDate(int date, int index) {
        long l = System.currentTimeMillis() - date * index;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(format.format(new Date(l)));
    }

}
