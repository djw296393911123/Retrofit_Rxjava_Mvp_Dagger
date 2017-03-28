package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.zhihu.DaypaperSectionData;

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
