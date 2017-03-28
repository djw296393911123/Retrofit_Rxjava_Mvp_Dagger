package com.djw.dagger2.moudel;

import com.djw.dagger2.base.BaseApplication;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.http.apis.GankApi;
import com.djw.dagger2.http.apis.WxApi;
import com.djw.dagger2.http.apis.ZhihuApi;

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
