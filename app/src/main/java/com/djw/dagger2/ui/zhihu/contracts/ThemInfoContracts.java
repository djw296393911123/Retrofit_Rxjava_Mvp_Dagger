package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.zhihu.DaypaperThemInfoData;

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
