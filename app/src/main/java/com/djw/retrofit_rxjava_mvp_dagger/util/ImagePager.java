package com.djw.retrofit_rxjava_mvp_dagger.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by JasonDong on 2017/2/24.
 */

public class ImagePager extends ViewPager {
    public ImagePager(Context context) {
        super(context);
    }

    public ImagePager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ignored) {
            ignored.printStackTrace();
        }

        return false;

    }

}
