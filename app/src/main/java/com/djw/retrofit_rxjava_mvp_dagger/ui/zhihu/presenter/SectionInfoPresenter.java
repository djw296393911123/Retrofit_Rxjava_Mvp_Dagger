package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter;

import com.djw.retrofit_rxjava_mvp_dagger.base.CommonSubscriber;
import com.djw.retrofit_rxjava_mvp_dagger.base.RxPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperSectionInfoData;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.SectionInfoContracts;
import com.djw.retrofit_rxjava_mvp_dagger.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class SectionInfoPresenter extends RxPresenter<SectionInfoContracts.View> implements SectionInfoContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public SectionInfoPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getContentList(String id) {
        Subscription subscribe = helper.getDaypaperSectionInfo(id)
                .compose(RxUtil.<DaypaperSectionInfoData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DaypaperSectionInfoData>(mView) {
                    @Override
                    public void onNext(DaypaperSectionInfoData daypaperSectionInfoData) {
                        mView.showContentList(daypaperSectionInfoData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
