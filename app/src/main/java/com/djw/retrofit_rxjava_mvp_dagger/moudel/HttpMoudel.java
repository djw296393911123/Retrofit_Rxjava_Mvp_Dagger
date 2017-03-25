package com.djw.retrofit_rxjava_mvp_dagger.moudel;

import com.djw.retrofit_rxjava_mvp_dagger.http.GankUrl;
import com.djw.retrofit_rxjava_mvp_dagger.http.WxUrl;
import com.djw.retrofit_rxjava_mvp_dagger.http.ZhihuUrl;
import com.djw.retrofit_rxjava_mvp_dagger.http.apis.GankApi;
import com.djw.retrofit_rxjava_mvp_dagger.http.apis.WxApi;
import com.djw.retrofit_rxjava_mvp_dagger.http.apis.ZhihuApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JasonDong on 2017/3/23.
 */
@Module
public class HttpMoudel {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @ZhihuUrl
    Retrofit provideZhihuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ZhihuApi.SERVICE);
    }

    @Singleton
    @Provides
    @GankUrl
    Retrofit provideGankRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GankApi.SERVICE);
    }

    @Singleton
    @Provides
    @WxUrl
    Retrofit provideWxRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, WxApi.SERVICE);
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Singleton
    @Provides
    ZhihuApi provideZhihuService(@ZhihuUrl Retrofit retrofit) {
        return retrofit.create(ZhihuApi.class);
    }

    @Singleton
    @Provides
    GankApi provideGankService(@GankUrl Retrofit retrofit) {
        return retrofit.create(GankApi.class);
    }

    @Singleton
    @Provides
    WxApi provideWxService(@WxUrl Retrofit retrofit) {
        return retrofit.create(WxApi.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
