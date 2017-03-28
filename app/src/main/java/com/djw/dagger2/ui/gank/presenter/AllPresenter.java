package com.djw.dagger2.ui.gank.presenter;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.GankHttpResponse;
import com.djw.dagger2.data.gank.GankListItemData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.gank.contracts.AllContracts;
import com.djw.dagger2.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/27.
 */

public class AllPresenter extends RxPresenter<AllContracts.View> implements AllContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public AllPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getAll() {
        Subscription subscribe = helper.getAll("1")
                .compose(RxUtil.<GankHttpResponse<List<GankListItemData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showAll(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getMoreAll(String page) {
        Subscription subscribe = helper.getAll(page)
                .compose(RxUtil.<GankHttpResponse<List<GankListItemData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showMoreAll(list);
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
