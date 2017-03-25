package com.djw.retrofit_rxjava_mvp_dagger.base;


public interface BaseView {

    void showError(String msg);

    void showProgress();

    void dismissProgress();

}
