package com.djw.retrofit_rxjava_mvp_dagger.ui.gank.presenter;

import com.djw.retrofit_rxjava_mvp_dagger.base.CommonSubscriber;
import com.djw.retrofit_rxjava_mvp_dagger.base.RxPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.data.GankHttpResponse;
import com.djw.retrofit_rxjava_mvp_dagger.data.gank.GankListItemData;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.ui.gank.contracts.AllContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.gank.contracts.MeiziContracts;
import com.djw.retrofit_rxjava_mvp_dagger.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/27.
 */

public class MeiziPresenter extends RxPresenter<MeiziContracts.View> implements MeiziContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public MeiziPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMeizi() {
        Subscription subscribe = helper.getMeizi("1")
                .compose(RxUtil.<GankHttpResponse<List<GankListItemData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showMeizi(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getMoreMeizi(String page) {
        Subscription subscribe = helper.getMeizi(page)
                .compose(RxUtil.<GankHttpResponse<List<GankListItemData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showMoreMeizi(list);
                    }
                });
        addSubscrebe(subscribe);
    }
}
