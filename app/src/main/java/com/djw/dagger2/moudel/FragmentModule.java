package com.djw.dagger2.moudel;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.djw.dagger2.http.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JasonDong on 2017/3/23.
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}