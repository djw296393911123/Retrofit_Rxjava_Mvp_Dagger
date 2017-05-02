package com.djw.dagger2.ui.zhihu.presenter;

import com.djw.dagger2.base.CommonSubscribers;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.zhihu.WelcomeData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.zhihu.contracts.WelcomeContracts;
import com.djw.dagger2.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/26.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContracts.View> implements WelcomeContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    WelcomePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getWelcome() {
        Subscription subscribe = helper.getWelcome()
                .compose(RxUtil.<WelcomeData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<WelcomeData>(mView, false) {
                    @Override
                    public void onNext(WelcomeData welcomeData) {
                        mView.showWelcome(welcomeData.getCreatives().get(0).getUrl());
                    }
                });
        addSubscrebe(subscribe);
    }
}
