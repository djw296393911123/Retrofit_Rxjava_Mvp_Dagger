package com.djw.dagger2.http;

import com.djw.dagger2.data.GankHttpResponse;
import com.djw.dagger2.data.WXHttpResponse;
import com.djw.dagger2.data.douban.DoubanListItem;
import com.djw.dagger2.data.gank.GankListItemData;
import com.djw.dagger2.data.wx.WxData;
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
import com.djw.dagger2.http.apis.DoubanApi;
import com.djw.dagger2.http.apis.GankApi;
import com.djw.dagger2.http.apis.WxApi;
import com.djw.dagger2.http.apis.ZhihuApi;

import java.util.List;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/23.
 */

public class RetrofitHelper {

    private ZhihuApi zhihuApi;

    private GankApi gankApi;

    private WxApi wxApi;

    private DoubanApi doubanApi;

    public RetrofitHelper(ZhihuApi zhihuApi, GankApi gankApi, WxApi wxApi, DoubanApi doubanApi) {
        this.zhihuApi = zhihuApi;
        this.gankApi = gankApi;
        this.wxApi = wxApi;
        this.doubanApi = doubanApi;
    }

    public Observable<DaypaperData> getDaypaperList() {
        return zhihuApi.getDaypaper();
    }

    public Observable<DaypaperBeforeData> getBeforePaperList(String date) {
        return zhihuApi.getBeforPaper(date);
    }

    public Observable<DaypaperInfoData> getDaypaperInfoList(String id) {
        return zhihuApi.getPaperInfo(id);
    }

    public Observable<DaypaperOtherData> getDaypaperOther(String id) {
        return zhihuApi.getPaperOther(id);
    }

    public Observable<DaypaperLongcommentData> getDaypaperLongcomment(String id) {
        return zhihuApi.getLongComment(id);
    }

    public Observable<DaypaperShortCommentData> getDaypaperShortcomment(String id) {
        return zhihuApi.getShortComment(id);
    }

    public Observable<DaypaperThemData> getDaypaperThem() {
        return zhihuApi.getThemData();
    }

    public Observable<DaypaperThemInfoData> getDaypaperThemInfo(String id) {
        return zhihuApi.getInfoThem(id);
    }

    public Observable<DaypaperHotData> getDaypaperHot() {
        return zhihuApi.getHot();
    }

    public Observable<DaypaperSectionData> getDaypaperSection() {
        return zhihuApi.getZhuanlan();
    }

    public Observable<DaypaperSectionInfoData> getDaypaperSectionInfo(String id) {
        return zhihuApi.getInfoZhuanlan(id);
    }

    public Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getRadomMeizi(String num) {
        return gankApi.getRadomMeizi(num);
    }

    public Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getAndroid(String page) {
        return gankApi.getAndroid(page);
    }

    public Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getIOS(String page) {
        return gankApi.getIos(page);
    }

    public Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getAll(String page) {
        return gankApi.getAll(page);
    }

    public Observable<GankHttpResponse<List<GankListItemData.ResultsBean>>> getMeizi(String page) {
        return gankApi.getMeizi(page);
    }

    public Observable<WXHttpResponse<List<WxData.NewslistBean>>> getWxData(String num, String page) {
        return wxApi.getWx("1f4051e5e61866d7ead573e2a39d857c", num, page);
    }

    public Observable<WXHttpResponse<List<WxData.NewslistBean>>> getSearchWxData(String num, String page, String keyWords) {
        return wxApi.getSearchWx("1f4051e5e61866d7ead573e2a39d857c", num, page, keyWords);
    }

    public Observable<DoubanListItem> getBookInfo(String id) {
        return doubanApi.getBookInfo(id);
    }

}
