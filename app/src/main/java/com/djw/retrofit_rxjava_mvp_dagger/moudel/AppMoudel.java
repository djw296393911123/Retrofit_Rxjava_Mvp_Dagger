package com.djw.retrofit_rxjava_mvp_dagger.moudel;

import com.djw.retrofit_rxjava_mvp_dagger.base.BaseApplication;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.http.apis.GankApi;
import com.djw.retrofit_rxjava_mvp_dagger.http.apis.WxApi;
import com.djw.retrofit_rxjava_mvp_dagger.http.apis.ZhihuApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JasonDong on 2017/3/23.
 */

@Module
public class AppMoudel {

    private final BaseApplication application;

    public AppMoudel(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BaseApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper(ZhihuApi zhihuApiService, GankApi gankApi, WxApi wxApi) {
        return new RetrofitHelper(zhihuApiService, gankApi, wxApi);
    }

}
