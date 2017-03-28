package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.zhihu.DaypaperThemData;

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
