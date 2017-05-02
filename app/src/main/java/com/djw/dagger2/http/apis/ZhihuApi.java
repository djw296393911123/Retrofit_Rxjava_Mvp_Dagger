package com.djw.dagger2.http.apis;

import com.djw.dagger2.data.zhihu.DaypaperBeforeData;
import com.djw.dagger2.data.zhihu.DaypaperData;
import com.djw.dagger2.data.zhihu.DaypaperHotData;
import com.djw.dagger2.data.zhihu.DaypaperInfoData;
import com.djw.dagger2.data.zhihu.DaypaperLongcommentData;
import com.djw.dagger2.data.zhihu.DaypaperOtherData;
import com.djw.dagger2.data.zhihu.DaypaperSectionData;
import com.djw.dagger2.data.zhihu.DaypaperSectionInfoData;
import com.djw.dagger2.data.zhihu.DaypaperShortCommentData;
import com.djw.dagger2.data.zhihu.DaypaperThemData;
import com.djw.dagger2.data.zhihu.DaypaperThemInfoData;
import com.djw.dagger2.data.zhihu.WelcomeData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JasonDong on 2017/3/23.
 */

public interface ZhihuApi {

    String SERVICE = "http://news-at.zhihu.com/api/";

    @GET("4/news/latest")
    Observable<DaypaperData> getDaypaper();

    @GET("4/news/before/{data}")
    Observable<DaypaperBeforeData> getBeforPaper(@Path("data") String data);

    @GET("4/news/{id}")
    Observable<DaypaperInfoData> getPaperInfo(@Path("id") String id);

    @GET("4/story-extra/{id}")
    Observable<DaypaperOtherData> getPaperOther(@Path("id") String id);

    @GET("4/story/{id}/long-comments")
    Observable<DaypaperLongcommentData> getLongComment(@Path("id") String id);

    @GET("4/story/{id}/short-comments")
    Observable<DaypaperShortCommentData> getShortComment(@Path("id") String id);

    @GET("4/themes")
    Observable<DaypaperThemData> getThemData();

    @GET("4/theme/{id}")
    Observable<DaypaperThemInfoData> getInfoThem(@Path("id") String id);

    @GET("4/news/hot")
    Observable<DaypaperHotData> getHot();

    @GET("4/sections")
    Observable<DaypaperSectionData> getZhuanlan();

    @GET("4/section/{id}")
    Observable<DaypaperSectionInfoData> getInfoZhuanlan(@Path("id") String id);

    @GET("7/prefetch-launch-images/1080*1920")
    Observable<WelcomeData> getWelcome();

}
