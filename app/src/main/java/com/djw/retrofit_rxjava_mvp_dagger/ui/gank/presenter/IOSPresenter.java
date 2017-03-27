package com.djw.retrofit_rxjava_mvp_dagger.ui.gank.presenter;

import com.djw.retrofit_rxjava_mvp_dagger.base.CommonSubscriber;
import com.djw.retrofit_rxjava_mvp_dagger.base.RxPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.data.GankHttpResponse;
import com.djw.retrofit_rxjava_mvp_dagger.data.gank.GankListItemData;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.ui.gank.contracts.AndroidContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.gank.contracts.IOSContracts;
import com.djw.retrofit_rxjava_mvp_dagger.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/27.
 */

public class IOSPresenter extends RxPresenter<IOSContracts.View> implements IOSContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public IOSPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getIOS() {
        Subscription subscribe = helper.getIOS("1")
                .compose(RxUtil.<GankHttpResponse<List<GankListItemData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showIOS(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getMoreIOS(String page) {
        Subscription subscribe = helper.getIOS(page)
                .compose(RxUtil.<GankHttpResponse<List<GankListItemData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showMoreIOS(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getRadomMeizi() {
        Subscription subscribe = helper.getRadomMeizi("1")
                .compose(RxUtil.<GankHttpResponse<List<GankListItemData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showRadomMeizi(list);
                    }
                });
        addSubscrebe(subscribe);
    }
}
