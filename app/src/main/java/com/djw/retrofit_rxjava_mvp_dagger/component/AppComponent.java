package com.djw.retrofit_rxjava_mvp_dagger.component;

import com.djw.retrofit_rxjava_mvp_dagger.base.BaseApplication;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.moudel.AppMoudel;
import com.djw.retrofit_rxjava_mvp_dagger.moudel.HttpMoudel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JasonDong on 2017/3/23.
 */

@Singleton
@Component(modules = {AppMoudel.class, HttpMoudel.class})
public interface AppComponent {

    BaseApplication getContext();

    RetrofitHelper retrofitHelper();

}
