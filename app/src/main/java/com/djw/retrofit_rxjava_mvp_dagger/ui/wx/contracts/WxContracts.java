package com.djw.retrofit_rxjava_mvp_dagger.ui.wx.contracts;

import com.djw.retrofit_rxjava_mvp_dagger.base.BasePresenter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseView;
import com.djw.retrofit_rxjava_mvp_dagger.data.gank.GankListItemData;
import com.djw.retrofit_rxjava_mvp_dagger.data.wx.WxData;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface WxContracts {

    interface View extends BaseView {

        void showListContent(WxData list);

        void showSearchData(WxData list);

        void showMoreContent(WxData wxData);

    }

    interface Presenter extends BasePresenter<View> {

        void getListContent();

        void getSearchData(String page, String word);

        void getMoreContent(String page);

    }

}
