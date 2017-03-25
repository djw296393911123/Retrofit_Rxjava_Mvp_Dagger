package com.djw.retrofit_rxjava_mvp_dagger.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.djw.retrofit_rxjava_mvp_dagger.component.ActivityComponent;
import com.djw.retrofit_rxjava_mvp_dagger.moudel.ActivityModule;
import com.github.anzewei.parallaxbacklayout.ParallaxBackActivityHelper;
import com.github.anzewei.parallaxbacklayout.ParallaxBackLayout;
import com.roger.catloadinglibrary.CatLoadingView;
import com.zhy.autolayout.AutoLayoutActivity;

import javax.inject.Inject;

/**
 * Created by JasonDong on 2017/3/23.
 */

public abstract class SimpleActivity extends AutoLayoutActivity{

    private ParallaxBackActivityHelper mHelper;
    protected Context context;
    private CatLoadingView progressDialog;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        context = this;
        progressDialog = new CatLoadingView();
        initView();
        doBusiness();
    }

    public abstract void initView();

    public abstract void doBusiness();

    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void showProgress() {
        progressDialog.show(getSupportFragmentManager(), "progressdialog");
    }

    public void dismissProgress() {
        progressDialog.dismiss();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHelper.onActivityDestroy();
    }

    public ParallaxBackLayout getBackLayout() {
        return mHelper.getBackLayout();
    }

    public void setBackEnable(boolean enable) {
        getBackLayout().setEnableGesture(enable);
    }

    public void scrollToFinishActivity() {
        mHelper.scrollToFinishActivity();
    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mHelper = new ParallaxBackActivityHelper(this);
    }

}