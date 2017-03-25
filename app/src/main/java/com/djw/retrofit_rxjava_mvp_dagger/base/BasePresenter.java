package com.djw.retrofit_rxjava_mvp_dagger.base;


public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
