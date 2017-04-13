package com.djw.dagger2.ui.zhihu.presenter;

import android.util.Log;

import com.djw.dagger2.base.CommonSubscriber;
import com.djw.dagger2.base.RxPresenter;
import com.djw.dagger2.data.douban.DoubanListItem;
import com.djw.dagger2.util.RxUtil;
import com.djw.dagger2.data.zhihu.DaypaperBeforeData;
import com.djw.dagger2.data.zhihu.DaypaperData;
import com.djw.dagger2.data.zhihu.paper.BannerData;
import com.djw.dagger2.data.zhihu.paper.ListData;
import com.djw.dagger2.data.zhihu.paper.PaperBaseData;
import com.djw.dagger2.data.zhihu.paper.TypeData;
import com.djw.dagger2.http.RetrofitHelper;
import com.djw.dagger2.ui.zhihu.contracts.PaperContracts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/3/23.
 */

public class PaperPresenter extends RxPresenter<PaperContracts.View> implements PaperContracts.Presenter {

    private final RetrofitHelper helper;

    @Inject
    PaperPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getPaperData() {
        Subscription subscribe = helper.getDaypaperList()
                .compose(RxUtil.<DaypaperData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DaypaperData>(mView) {
                    @Override
                    public void onNext(DaypaperData daypaperData) {
                        List<DaypaperData.StoriesBean> stories = daypaperData.getStories();
                        List<DaypaperData.TopStoriesBean> top_stories = daypaperData.getTop_stories();
                        List<PaperBaseData> list = new LinkedList<>();
                        List<String> urls = new ArrayList<>();
                        List<String> titles = new ArrayList<>();
                        List<String> ids = new ArrayList<>();
                        for (int i = 0; i < top_stories.size(); i++) {
                            DaypaperData.TopStoriesBean topStoriesBean = top_stories.get(i);
                            urls.add(topStoriesBean.getImage());
                            titles.add(topStoriesBean.getTitle());
                            ids.add(String.valueOf(topStoriesBean.getId()));
                        }
                        list.add(new BannerData(titles, urls, ids));
                        list.add(new TypeData(daypaperData.getDate()));
                        for (int i = 0; i < stories.size(); i++) {
                            DaypaperData.StoriesBean storiesBean = stories.get(i);
                            list.add(new ListData(storiesBean.getTitle(), storiesBean.getImages().get(0), storiesBean.getId()));
                        }
                        mView.showPaperData(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getBeforeData(String date) {
        Subscription subscribe = helper.getBeforePaperList(date)
                .compose(RxUtil.<DaypaperBeforeData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DaypaperBeforeData>(mView) {
                    @Override
                    public void onNext(DaypaperBeforeData daypaperBeforeData) {
                        List<DaypaperBeforeData.StoriesBean> stories = daypaperBeforeData.getStories();
                        List<PaperBaseData> list = new ArrayList<>();
                        list.add(new TypeData(daypaperBeforeData.getDate()));
                        for (int i = 0; i < stories.size(); i++) {
                            DaypaperBeforeData.StoriesBean storiesBean = stories.get(i);
                            list.add(new ListData(storiesBean.getTitle(), storiesBean.getImages().get(0), storiesBean.getId()));
                        }
                        mView.showBeforeData(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getDouban() {
        helper.getBookInfo("6548683")
                .compose(RxUtil.<DoubanListItem>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<DoubanListItem>(mView) {
                    @Override
                    public void onNext(DoubanListItem doubanListItem) {
                        Log.i("doubanlistitem", doubanListItem.toString());
                    }
                });
    }
}
