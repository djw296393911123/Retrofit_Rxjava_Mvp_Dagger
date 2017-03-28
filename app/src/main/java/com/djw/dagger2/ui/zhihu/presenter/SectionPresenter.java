package com.djw.dagger2.ui.zhihu.presenter;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.zhihu.DaypaperSectionData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.zhihu.contracts.SectionContracts;
import com.djw.dagger2.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class SectionPresenter extends RxPresenter<SectionContracts.View> implements SectionContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public SectionPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getSectionList() {
        Subscription subscribe = helper.getDaypaperSection()
                .compose(RxUtil.<DaypaperSectionData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DaypaperSectionData>(mView) {
                    @Override
                    public void onNext(DaypaperSectionData daypaperSectionData) {
                        mView.showSectionList(daypaperSectionData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
