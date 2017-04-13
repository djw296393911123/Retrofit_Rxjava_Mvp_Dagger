package com.djw.dagger2.http.apis;

import com.djw.dagger2.data.douban.DoubanListItem;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JasonDong on 2017/4/7.
 */

public interface DoubanApi {

    String SERVICE = "https://api.douban.com/v2/";

    @GET("book/{id}")
    Observable<DoubanListItem> getBookInfo(@Path("id") String id);

}
