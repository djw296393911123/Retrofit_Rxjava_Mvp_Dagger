package com.djw.retrofit_rxjava_mvp_dagger.http.apis;

import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperBeforeData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperHotData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperInfoData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperLongcommentData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperOtherData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperSectionData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperSectionInfoData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperShortCommentData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperThemData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperThemInfoData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JasonDong on 2017/3/23.
 *
 */

public interface ZhihuApi {

    String SERVICE = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<DaypaperData> getDaypaper();

    @GET("news/before/{data}")
    Observable<DaypaperBeforeData> getBeforPaper(@Path("data") String data);

    @GET("news/{id}")
    Observable<DaypaperInfoData> getPaperInfo(@Path("id") String id);

    @GET("story-extra/{id}")
    Observable<DaypaperOtherData> getPaperOther(@Path("id") String id);

    @GET("story/{id}/long-comments")
    Observable<DaypaperLongcommentData> getLongComment(@Path("id") String id);

    @GET("story/{id}/short-comments")
    Observable<DaypaperShortCommentData> getShortComment(@Path("id") String id);

    @GET("themes")
    Observable<DaypaperThemData> getThemData();

    @GET("theme/{id}")
    Observable<DaypaperThemInfoData> getInfoThem(@Path("id") String id);

    @GET("news/hot")
    Observable<DaypaperHotData> getHot();

    @GET("sections")
    Observable<DaypaperSectionData> getZhuanlan();

    @GET("section/{id}")
    Observable<DaypaperSectionInfoData> getInfoZhuanlan(@Path("id") String id);
}
