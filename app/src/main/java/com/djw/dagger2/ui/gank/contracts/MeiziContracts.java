package com.djw.dagger2.ui.gank.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.gank.GankListItemData;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/27.
 */

public interface MeiziContracts {

    interface View extends BaseView {

        void showMeizi(List<GankListItemData.ResultsBean> list);

        void showMoreMeizi(List<GankListItemData.ResultsBean> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getMeizi();

        void getMoreMeizi(String page);

    }

}
