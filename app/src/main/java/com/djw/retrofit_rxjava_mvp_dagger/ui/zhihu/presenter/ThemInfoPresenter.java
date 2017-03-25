package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter;

import com.djw.retrofit_rxjava_mvp_dagger.base.CommonSubscriber;
import com.djw.retrofit_rxjava_mvp_dagger.base.RxPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperThemInfoData;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.ThemInfoContracts;
import com.djw.retrofit_rxjava_mvp_dagger.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class ThemInfoPresenter extends RxPresenter<ThemInfoContracts.View> implements ThemInfoContracts.Presetner {

    private final RetrofitHelper helper;

    @Inject
    public ThemInfoPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getList(String id) {
        Subscription subscribe = helper.getDaypaperThemInfo(id)
                .compose(RxUtil.<DaypaperThemInfoData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DaypaperThemInfoData>(mView) {
                    @Override
                    public void onNext(DaypaperThemInfoData daypaperThemInfoData) {
                        mView.showList(daypaperThemInfoData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
