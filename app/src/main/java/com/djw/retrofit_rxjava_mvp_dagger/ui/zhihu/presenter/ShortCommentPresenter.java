package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter;

import com.djw.retrofit_rxjava_mvp_dagger.base.CommonSubscriber;
import com.djw.retrofit_rxjava_mvp_dagger.base.RxPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.util.RxUtil;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperShortCommentData;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.ShortCommentContracts;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/24.
 */

public class ShortCommentPresenter extends RxPresenter<ShortCommentContracts.View> implements ShortCommentContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    ShortCommentPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getShortComment(String id) {
        Subscription subscribe = helper.getDaypaperShortcomment(id)
                .compose(RxUtil.<DaypaperShortCommentData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DaypaperShortCommentData>(mView) {
                    @Override
                    public void onNext(DaypaperShortCommentData daypaperShortcommentData) {
                        mView.showShortComment(daypaperShortcommentData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
