package com.djw.dagger2.ui.gank.presenter;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.GankHttpResponse;
import com.djw.dagger2.data.gank.GankListItemData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.gank.contracts.MeiziContracts;
import com.djw.dagger2.util.RxUtil;

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
