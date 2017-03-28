package com.djw.dagger2.base;


public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
