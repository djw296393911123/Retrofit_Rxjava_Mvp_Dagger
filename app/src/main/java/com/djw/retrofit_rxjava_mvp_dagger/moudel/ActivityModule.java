package com.djw.retrofit_rxjava_mvp_dagger.moudel;

import android.app.Activity;

import com.djw.retrofit_rxjava_mvp_dagger.http.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JasonDong on 2017/3/24.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
