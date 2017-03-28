package com.djw.dagger2.http.apis;

import com.djw.dagger2.data.GankHttpResponse;
import com.djw.dagger2.data.gank.GankListItemData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JasonDong on 2017/3/25.
 */

public interface GankApi {

    String SERVICE = "http://gank.io/api/";

    @GET("random/data/福利/{num}")
    Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getRadomMeizi(@Path("num") String num);

    @GET("data/Android/20/{page}")
    Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getAndroid(@Path("page") String page);

    @GET("data/all/20/{page}")
    Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getAll(@Path("page") String page);

    @GET("data/福利/20/{page}")
    Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getMeizi(@Path("page") String page);

    @GET("data/iOS/20/{page}")
    Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getIos(@Path("page") String page);

}
