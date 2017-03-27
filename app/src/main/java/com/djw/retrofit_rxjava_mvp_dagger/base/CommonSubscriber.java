package com.djw.retrofit_rxjava_mvp_dagger.base;

import android.text.TextUtils;
import android.util.Log;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by codeest on 2017/2/23.
 */

public abstract class CommonSubscriber<T> extends Subscriber<T> {
    private BaseView mView;
    private String mErrorMsg;

    protected CommonSubscriber(BaseView view) {
        this.mView = view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mView.showProgress();
    }

    protected CommonSubscriber(BaseView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    @Override
    public void onCompleted() {
        mView.dismissProgress();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mView.dismissProgress();
        Log.i("onError", "onerror");
        if (mView == null)
            return;
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showError(mErrorMsg);
        } else if (e instanceof ApiException) {
            mView.showError(e.getMessage());
        } else if (e instanceof HttpException) {
            mView.showError("数据加载失败ヽ(≧Д≦)ノ");
        } else {
            mView.showError("未知错误ヽ(≧Д≦)ノ");
        }
    }
}
