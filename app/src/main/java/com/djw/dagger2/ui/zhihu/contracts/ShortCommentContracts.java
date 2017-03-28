package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.zhihu.DaypaperShortCommentData;

/**
 * Created by JasonDong on 2017/3/24.
 */

public interface ShortCommentContracts {

    interface View extends BaseView {

        void showShortComment(DaypaperShortCommentData daypaperShortCommentData);

    }

    interface Presenter extends BasePresenter<View> {

        void getShortComment(String id);
    }

}
