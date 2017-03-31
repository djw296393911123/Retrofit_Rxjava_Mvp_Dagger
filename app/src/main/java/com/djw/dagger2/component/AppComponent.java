package com.djw.dagger2.component;

import com.djw.dagger2.base.BaseApplication;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.module.AppMoudel;
import com.djw.dagger2.module.HttpMoudel;

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
