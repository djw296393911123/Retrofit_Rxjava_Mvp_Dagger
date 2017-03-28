package com.djw.dagger2.component;

import android.app.Activity;

import com.djw.dagger2.http.FragmentScope;
import com.djw.dagger2.moudel.FragmentModule;
import com.djw.dagger2.ui.gank.fragment.AllFragment;
import com.djw.dagger2.ui.gank.fragment.AndroidFragment;
import com.djw.dagger2.ui.gank.fragment.IOSFragment;
import com.djw.dagger2.ui.gank.fragment.MeiziFragment;
import com.djw.dagger2.ui.wx.fragment.WXFragment;
import com.djw.dagger2.ui.zhihu.fragment.DaypaperFragment;
import com.djw.dagger2.ui.zhihu.fragment.HotFragment;
import com.djw.dagger2.ui.zhihu.fragment.LongCommentFragment;
import com.djw.dagger2.ui.zhihu.fragment.SectionFragment;
import com.djw.dagger2.ui.zhihu.fragment.ShortCommentFragment;
import com.djw.dagger2.ui.zhihu.fragment.ThemFragment;
import com.djw.dagger2.ui.zhihu.fragment.ZhihuFragment;

import dagger.Component;

/**
 * Created by JasonDong on 2017/3/23.
 *
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

    void inject(AndroidFragment androidFragment);

    void inject(IOSFragment iosFragment);

    void inject(AllFragment allFragment);

    void inject(MeiziFragment meiziFragment);
}