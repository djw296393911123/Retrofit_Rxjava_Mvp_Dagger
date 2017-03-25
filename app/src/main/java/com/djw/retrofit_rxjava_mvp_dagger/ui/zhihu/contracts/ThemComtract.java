package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperThemData;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface ThemComtract {

    interface View extends BaseView {

        void showThemData(DaypaperThemData daypaperThemData);

    }

    interface Presenter extends BasePresenter<View> {

        void getThemData();

    }

}
