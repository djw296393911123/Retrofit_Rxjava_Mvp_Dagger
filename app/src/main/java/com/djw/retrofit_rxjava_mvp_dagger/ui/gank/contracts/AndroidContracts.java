package com.djw.retrofit_rxjava_mvp_dagger.ui.gank.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.gank.GankListItemData;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/27.
 */

public interface AndroidContracts {

    interface View extends BaseView {

        void showAndroid(List<GankListItemData.ResultsBean> list);

        void showMoreAndroid(List<GankListItemData.ResultsBean> list);

        void showRadomMeizi(List<GankListItemData.ResultsBean> list);
    }

    interface Presenter extends BasePresenter<View> {

        void getAndroid();

        void getMoreAndroid(String page);

        void getRadmomMeizi();

    }

}
