package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperHotData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperSectionInfoData;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface HotContracts {

    interface View extends BaseView {

        void showContentList(DaypaperHotData daypaperHotData);

    }

    interface Presenter extends BasePresenter<View> {

        void getContentList();

    }

}
