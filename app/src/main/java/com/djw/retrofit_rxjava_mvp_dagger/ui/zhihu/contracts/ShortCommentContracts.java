package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperShortCommentData;

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
