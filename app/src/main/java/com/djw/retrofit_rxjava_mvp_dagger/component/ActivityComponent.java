package com.djw.retrofit_rxjava_mvp_dagger.component;

import android.app.Activity;

import com.djw.retrofit_rxjava_mvp_dagger.http.ActivityScope;
import com.djw.retrofit_rxjava_mvp_dagger.moudel.ActivityModule;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity.CommentActivity;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity.PaperInfoActivity;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity.SectionActivity;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity.ThemInfoActivity;

import dagger.Component;

/**
 * Created by JasonDong on 2017/3/24.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(PaperInfoActivity paperInfoActivity);

    void inject(ThemInfoActivity themInfoActivity);

    void inject(SectionActivity sectionActivity);

}