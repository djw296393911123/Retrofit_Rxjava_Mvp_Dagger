package com.djw.retrofit_rxjava_mvp_dagger.component;

import android.app.Activity;

import com.djw.retrofit_rxjava_mvp_dagger.http.FragmentScope;
import com.djw.retrofit_rxjava_mvp_dagger.moudel.FragmentModule;
import com.djw.retrofit_rxjava_mvp_dagger.ui.wx.fragment.WXFragment;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment.DaypaperFragment;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment.HotFragment;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment.LongCommentFragment;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment.SectionFragment;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment.ShortCommentFragment;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment.ThemFragment;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment.ZhihuFragment;

import dagger.Component;

/**
 * Created by JasonDong on 2017/3/23.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DaypaperFragment dailyFragment);

    void inject(HotFragment hotFragment);

    void inject(SectionFragment sectionFragment);

    void inject(ThemFragment themFragment);

    void inject(LongCommentFragment longCommentFragment);

    void inject(ShortCommentFragment shortCommentFragment);

    void inject(ZhihuFragment zhihuFragment);

    void inject(WXFragment wxFragment);
//
//    void inject(SectionFragment sectionFragment);
//
//    void inject(HotFragment hotFragment);
//
//    void inject(CommentFragment longCommentFragment);
//
//    void inject(TechFragment techFragment);
//
//    void inject(GirlFragment girlFragment);
//
//    void inject(LikeFragment likeFragment);
//
//    void inject(WechatMainFragment wechatMainFragment);
//
//    void inject(SettingFragment settingFragment);
//
//    void inject(GoldMainFragment goldMainFragment);
//
//    void inject(GoldPagerFragment goldPagerFragment);
//
//    void inject(VtexPagerFragment vtexPagerFragment);
}