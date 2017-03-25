package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.gank.GankListItemData;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface ZhihuContracts {

    interface View extends BaseView {

        void showRadomMeizi(List<GankListItemData.ResultsBean> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getRadomMeizi(String num);

    }

}
