package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter;

import com.djw.retrofit_rxjava_mvp_dagger.base.CommonSubscriber;
import com.djw.retrofit_rxjava_mvp_dagger.base.RxPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.util.RxUtil;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperLongcommentData;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.LongCommentContracts;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/24.
 */

public class LongCommentPresenter extends RxPresenter<LongCommentContracts.View> implements LongCommentContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    LongCommentPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getLongComment(String id) {
        Subscription subscribe = helper.getDaypaperLongcomment(id)
                .compose(RxUtil.<DaypaperLongcommentData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DaypaperLongcommentData>(mView) {
                    @Override
                    public void onNext(DaypaperLongcommentData daypaperLongcommentData) {
                        mView.showLongComment(daypaperLongcommentData);
                    }
                });
        addSubscrebe(subscribe);
    }

}
