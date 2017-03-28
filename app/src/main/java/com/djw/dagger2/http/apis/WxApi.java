package com.djw.dagger2.http.apis;

import com.djw.dagger2.data.WXHttpResponse;
import com.djw.dagger2.data.wx.WxData;

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
    Observable<WXHttpResponse<List<WxData.NewslistBean>>> getWx(@Query("key") String key, @Query("num") String num, @Query("page") String page);

    @GET("wxnew/")
    Observable<WXHttpResponse<List<WxData.NewslistBean>>> getSearchWx(@Query("key") String key, @Query("num") String num, @Query("page") String page, @Query("word") String wrod);

}
