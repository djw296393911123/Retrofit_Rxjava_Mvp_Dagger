package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperThemInfoData;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface ThemInfoContracts {

    interface View extends BaseView {

        void showList(DaypaperThemInfoData daypaperThemInfoData);

    }

    interface Presetner extends BasePresenter<View> {

        void getList(String id);

    }

}
