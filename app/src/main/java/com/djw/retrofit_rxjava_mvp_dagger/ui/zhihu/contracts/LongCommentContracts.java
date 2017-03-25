package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperLongcommentData;

/**
 * Created by JasonDong on 2017/3/24.
 */

public interface LongCommentContracts {

    interface View extends BaseView {

        void showLongComment(DaypaperLongcommentData daypaperLongcommentData);

    }

    interface Presenter extends BasePresenter<View> {

        void getLongComment(String id);

    }

}
