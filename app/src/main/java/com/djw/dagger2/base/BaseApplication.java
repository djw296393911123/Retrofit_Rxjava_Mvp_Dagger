package com.djw.dagger2.base;

import android.app.Application;
//
import com.djw.dagger2.component.AppComponent;
import com.djw.dagger2.component.DaggerAppComponent;
import com.djw.dagger2.moudel.AppMoudel;
import com.djw.dagger2.moudel.HttpMoudel;

/**
 * Created by JasonDong on 2017/3/23.
 *
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
