package com.djw.dagger2.base;

import android.text.TextUtils;
import android.util.Log;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by jasonDong on 2017/3/23.
 */

public abstract class CommonSubscribers<T> extends Subscriber<T> {
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShow;

    protected CommonSubscribers(BaseView view, boolean isShow) {
        this.mView = view;
        this.isShow = isShow;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isShow)
            mView.showProgress();
    }

    protected CommonSubscribers(BaseView view, String errorMsg) {
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
        Log.i("onError", "onerror");
        if (mView == null) return;
        mView.dismissProgress();
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
