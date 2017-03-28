package com.djw.dagger2.ui.zhihu.presenter;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.gank.GankListItemData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.zhihu.contracts.ZhihuContracts;
import com.djw.dagger2.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class ZhihuPresenter extends RxPresenter<ZhihuContracts.View> implements ZhihuContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public ZhihuPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getRadomMeizi(String num) {
        Subscription subscribe = helper.getRadomMeizi(num)
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>handleResult())
                .compose(RxUtil.<List<GankListItemData.ResultsBean>>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<List<GankListItemData.ResultsBean>>(mView) {
                    @Override
                    public void onNext(List<GankListItemData.ResultsBean> list) {
                        mView.showRadomMeizi(list);
                    }
                });
        addSubscrebe(subscribe);
    }
}
