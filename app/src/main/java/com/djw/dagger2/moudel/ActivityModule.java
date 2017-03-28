package com.djw.dagger2.moudel;

import android.app.Activity;

import com.djw.dagger2.http.ActivityScope;

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
