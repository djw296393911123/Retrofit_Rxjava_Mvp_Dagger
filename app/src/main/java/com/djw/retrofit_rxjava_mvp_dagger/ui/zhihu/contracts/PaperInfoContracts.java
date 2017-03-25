package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperInfoData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperOtherData;

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
