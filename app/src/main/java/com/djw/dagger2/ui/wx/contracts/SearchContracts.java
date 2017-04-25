package com.djw.dagger2.ui.wx.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.wx.WxData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/25.
 */

public interface SearchContracts {

    interface View extends BaseView {

        void showSearchData(List<WxData.NewslistBean> wxData, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getSearchData(String keyword, int page, boolean isLoadMore, boolean isShowProgress);

    }

}
