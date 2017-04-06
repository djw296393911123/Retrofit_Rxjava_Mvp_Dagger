package com.djw.dagger2.module;

import android.util.Log;

import com.djw.dagger2.base.BaseApplication;
import com.djw.dagger2.http.GankUrl;
import com.djw.dagger2.http.WxUrl;
import com.djw.dagger2.http.ZhihuUrl;
import com.djw.dagger2.http.apis.GankApi;
import com.djw.dagger2.http.apis.WxApi;
import com.djw.dagger2.http.apis.ZhihuApi;
import com.djw.dagger2.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    OkHttpClient provideClient(OkHttpClient.Builder builder, Cache cache, Interceptor interceptor) {

        return builder.cache(cache).addNetworkInterceptor(interceptor).build();
    }

    @Singleton
    @Provides
    Cache provideCache() {
        return new Cache(new File(BaseApplication.getInstance().getApplicationContext().getCacheDir(), "dagger2"), 1024 * 1024 * 20);
    }

    @Singleton
    @Provides
    Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
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
