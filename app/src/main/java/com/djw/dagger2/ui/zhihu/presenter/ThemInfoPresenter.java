package com.djw.dagger2.ui.zhihu.presenter;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.zhihu.DaypaperThemInfoData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.zhihu.contracts.ThemInfoContracts;
import com.djw.dagger2.util.RxUtil;

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
