package com.djw.retrofit_rxjava_mvp_dagger.ui.wx.presenter;

import android.util.Log;

import com.djw.retrofit_rxjava_mvp_dagger.base.CommonSubscriber;
import com.djw.retrofit_rxjava_mvp_dagger.base.RxPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.data.GankHttpResponse;
import com.djw.retrofit_rxjava_mvp_dagger.data.WXHttpResponse;
import com.djw.retrofit_rxjava_mvp_dagger.data.gank.GankListItemData;
import com.djw.retrofit_rxjava_mvp_dagger.data.wx.WxData;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.ui.wx.contracts.WxContracts;
import com.djw.retrofit_rxjava_mvp_dagger.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class WxPresenter extends RxPresenter<WxContracts.View> implements WxContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public WxPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getListContent() {
        Subscription subscribe = helper.getWxData("20", "1")
                .compose(RxUtil.<WXHttpResponse<List<WxData.NewslistBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WxData.NewslistBean>>handleWXResult())
                .subscribe(new CommonSubscriber<List<WxData.NewslistBean>>(mView) {
                    @Override
                    public void onNext(List<WxData.NewslistBean> list) {
                        mView.showListContent(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getSearchData(String word) {
        Subscription subscribe = helper.getSearchWxData("20", "1", word)
                .compose(RxUtil.<WXHttpResponse<List<WxData.NewslistBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WxData.NewslistBean>>handleWXResult())
                .subscribe(new CommonSubscriber<List<WxData.NewslistBean>>(mView) {
                    @Override
                    public void onNext(List<WxData.NewslistBean> list) {
                        mView.showSearchData(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getMoreContent(String page) {
        Subscription subscribe = helper.getWxData("20", page)
                .compose(RxUtil.<WXHttpResponse<List<WxData.NewslistBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WxData.NewslistBean>>handleWXResult())
                .subscribe(new CommonSubscriber<List<WxData.NewslistBean>>(mView) {
                    @Override
                    public void onNext(List<WxData.NewslistBean> list) {
                        mView.showMoreContent(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getMoreSearchData(String page, String word) {
        Subscription subscribe = helper.getSearchWxData("20", page, word)
                .compose(RxUtil.<WXHttpResponse<List<WxData.NewslistBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WxData.NewslistBean>>handleWXResult())
                .subscribe(new CommonSubscriber<List<WxData.NewslistBean>>(mView) {
                    @Override
                    public void onNext(List<WxData.NewslistBean> list) {
                        mView.showMoreSearchData(list);
                    }
                });
        addSubscrebe(subscribe);
    }

}
