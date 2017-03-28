package com.djw.dagger2.ui.zhihu.presenter;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.util.RxUtil;
import com.djw.dagger2.data.zhihu.DaypaperThemData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.zhihu.contracts.ThemComtract;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class ThemPresenter extends RxPresenter<ThemComtract.View> implements ThemComtract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public ThemPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getThemData() {
        Subscription subscribe = helper.getDaypaperThem()
                .compose(RxUtil.<DaypaperThemData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DaypaperThemData>(mView) {
                    @Override
                    public void onNext(DaypaperThemData daypaperThemData) {
                        mView.showThemData(daypaperThemData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
