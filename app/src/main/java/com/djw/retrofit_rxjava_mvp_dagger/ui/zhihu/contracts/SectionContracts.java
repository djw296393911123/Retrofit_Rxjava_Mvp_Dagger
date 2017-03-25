package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperSectionData;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface SectionContracts {

    interface View extends BaseView {

        void showSectionList(DaypaperSectionData daypaperSectionData);

    }

    interface Presenter extends BasePresenter<View> {

        void getSectionList();

    }

}
