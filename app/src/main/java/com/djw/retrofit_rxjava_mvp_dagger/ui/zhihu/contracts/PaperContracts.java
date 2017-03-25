package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperBeforeData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.paper.PaperBaseData;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/23.
 */

public interface PaperContracts {

    interface View extends BaseView {

        void showPaperData(List<PaperBaseData> daypaperData);

        void showBeforeData(List<PaperBaseData> daypaperBeforeData);

    }

    interface Presenter extends BasePresenter<View> {

        void getPaperData();

        void getBeforeData(String date);

    }

}
