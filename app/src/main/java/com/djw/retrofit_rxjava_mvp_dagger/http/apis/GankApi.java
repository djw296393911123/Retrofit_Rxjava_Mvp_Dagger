package com.djw.retrofit_rxjava_mvp_dagger.http.apis;

import com.djw.retrofit_rxjava_mvp_dagger.data.GankHttpResponse;
import com.djw.retrofit_rxjava_mvp_dagger.data.gank.GankListItemData;

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
}
