package com.djw.retrofit_rxjava_mvp_dagger.http.apis;

import com.djw.retrofit_rxjava_mvp_dagger.data.WXHttpResponse;
import com.djw.retrofit_rxjava_mvp_dagger.data.wx.WxData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface WxApi {

    String SERVICE = "http://api.tianapi.com/";

    @GET("wxnew/")
    Observable<WxData> getWx(@Query("key") String key, @Query("num") String num, @Query("page") String page);

    @GET("wxnew/")
    Observable<WxData> getSearchWx(@Query("key") String key, @Query("num") String num, @Query("page") String page, @Query("word") String wrod);

}
