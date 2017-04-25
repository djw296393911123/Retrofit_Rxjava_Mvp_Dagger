package com.djw.dagger2.ui.wx.presenter;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.CommonSubscribers;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.WXHttpResponse;
import com.djw.dagger2.data.wx.WxData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.wx.contracts.SearchContracts;
import com.djw.dagger2.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/25.
 */

public class SearchPresenter extends RxPresenter<SearchContracts.View> implements SearchContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public SearchPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getSearchData(String keyword, int page, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getSearchWxData("20", String.valueOf(page), keyword)
                .compose(RxUtil.<WXHttpResponse<List<WxData.NewslistBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WxData.NewslistBean>>handleWXResult())
                .subscribe(new CommonSubscribers<List<WxData.NewslistBean>>(mView, isShowProgress) {
                    @Override
                    public void onNext(List<WxData.NewslistBean> list) {
                        mView.showSearchData(list, isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
