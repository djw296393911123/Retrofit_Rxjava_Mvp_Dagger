package com.djw.dagger2.ui.zhihu.presenter;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.zhihu.DaypaperHotData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.zhihu.contracts.HotContracts;
import com.djw.dagger2.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class HotPresenter extends RxPresenter<HotContracts.View> implements HotContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public HotPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getContentList() {
        Subscription subscribe = helper.getDaypaperHot()
                .compose(RxUtil.<DaypaperHotData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DaypaperHotData>(mView) {
                    @Override
                    public void onNext(DaypaperHotData daypaperHotData) {
                        mView.showContentList(daypaperHotData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
