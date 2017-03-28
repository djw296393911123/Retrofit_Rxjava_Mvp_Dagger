package com.djw.dagger2.ui.wx.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.wx.WxData;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface WxContracts {

    interface View extends BaseView {

        void showListContent(List<WxData.NewslistBean> list);

        void showSearchData(List<WxData.NewslistBean> list);

        void showMoreContent(List<WxData.NewslistBean> wxData);

        void showMoreSearchData(List<WxData.NewslistBean> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getListContent();

        void getSearchData(String word);

        void getMoreContent(String page);

        void getMoreSearchData(String page, String word);

    }

}
