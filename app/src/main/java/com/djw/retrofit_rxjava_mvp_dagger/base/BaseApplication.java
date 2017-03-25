package com.djw.retrofit_rxjava_mvp_dagger.base;

import android.app.Application;
//
import com.djw.retrofit_rxjava_mvp_dagger.component.AppComponent;
import com.djw.retrofit_rxjava_mvp_dagger.component.DaggerAppComponent;
import com.djw.retrofit_rxjava_mvp_dagger.moudel.AppMoudel;
import com.djw.retrofit_rxjava_mvp_dagger.moudel.HttpMoudel;

/**
 * Created by JasonDong on 2017/3/23.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appMoudel(new AppMoudel(instance))
                    .httpMoudel(new HttpMoudel())
                    .build();
        }
        return appComponent;
    }
}
