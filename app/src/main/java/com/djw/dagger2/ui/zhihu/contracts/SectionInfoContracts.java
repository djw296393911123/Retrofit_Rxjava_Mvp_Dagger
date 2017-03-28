package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.zhihu.DaypaperSectionInfoData;

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
