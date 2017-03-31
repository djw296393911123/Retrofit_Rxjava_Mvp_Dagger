package com.djw.dagger2.component;

import android.app.Activity;

import com.djw.dagger2.http.ActivityScope;
import com.djw.dagger2.module.ActivityModule;
import com.djw.dagger2.ui.zhihu.activity.PaperInfoActivity;
import com.djw.dagger2.ui.zhihu.activity.SectionActivity;
import com.djw.dagger2.ui.zhihu.activity.ThemInfoActivity;

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