package com.djw.dagger2.ui.gank.presenter;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.GankHttpResponse;
import com.djw.dagger2.data.gank.GankListItemData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.gank.contracts.AndroidContracts;
import com.djw.dagger2.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/27.
 */

public class AndroidPresenter extends RxPresenter<AndroidContracts.View> implements AndroidContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public AndroidPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getAndroid() {
        Subscription subscribe = helper.getAndroid("1")
                .compose(RxUtil.<GankHttpResponse<List<GankListItemData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showAndroid(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getMoreAndroid(String page) {
        Subscription subscribe = helper.getAndroid(page)
                .compose(RxUtil.<GankHttpResponse<List<GankListItemData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showMoreAndroid(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getRadmomMeizi() {
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
