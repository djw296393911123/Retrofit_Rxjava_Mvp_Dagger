package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.zhihu.DaypaperInfoData;
import com.djw.dagger2.data.zhihu.DaypaperOtherData;

/**
 * Created by JasonDong on 2017/3/24.
 */

public interface PaperInfoContracts {

    interface View extends BaseView {

        void showContent(DaypaperInfoData daypaperInfoData);

        void showContentOther(DaypaperOtherData daypaperOtherData);

    }

    interface Presenter extends BasePresenter<View> {

        void getContent(String id);

        void getContentOther(String id);

    }

}
