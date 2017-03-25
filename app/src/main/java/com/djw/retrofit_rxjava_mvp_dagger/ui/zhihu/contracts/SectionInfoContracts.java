package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperSectionInfoData;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface SectionInfoContracts {

    interface View extends BaseView {

        void showContentList(DaypaperSectionInfoData daypaperSectionInfoData);

    }

    interface Presenter extends BasePresenter<View> {

        void getContentList(String id);

    }

}
