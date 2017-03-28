package com.djw.dagger2.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.djw.dagger2.component.DaggerFragmentComponent;
import com.djw.dagger2.component.FragmentComponent;
import com.djw.dagger2.moudel.FragmentModule;

import javax.inject.Inject;

/**
 * Created by JasonDong on 2017/3/23.
 *
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    protected boolean isVisible;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Inject
    public T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayout(), container, false);
        initView(view);
        inject();
        doBusiness();
        return view;
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected abstract void initView(View view);

    protected abstract void doBusiness();

    protected abstract void inject();

    protected abstract int bindLayout();

    protected void onInvisible() {
        System.gc();
    }

}