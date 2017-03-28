package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.gank.GankListItemData;

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
