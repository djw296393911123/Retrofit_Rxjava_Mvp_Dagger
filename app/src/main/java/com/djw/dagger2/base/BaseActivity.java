package com.djw.dagger2.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.djw.dagger2.R;
import com.djw.dagger2.component.ActivityComponent;
import com.djw.dagger2.component.DaggerActivityComponent;
import com.djw.dagger2.module.ActivityModule;
import com.github.anzewei.parallaxbacklayout.ParallaxBackActivityHelper;
import com.github.anzewei.parallaxbacklayout.ParallaxBackLayout;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.zhy.autolayout.AutoLayoutActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by JasonDong on 2017/3/23.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AutoLayoutActivity implements BaseView {
    @Inject
    public T mPresenter;
    private ParallaxBackActivityHelper mHelper;
    protected Context context;
    private ProgressDialog progressDialog;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        context = this;
        progressDialog = new ProgressDialog(this);
        DoubleBounce doubleBounce = new DoubleBounce();
        doubleBounce.setColor(R.color.colorAccent);
        progressDialog.setIndeterminateDrawable(doubleBounce);
        progressDialog.setMessage("正在加载...");
//        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                //取消网络请求
//                if (mPresenter != null) mPresenter.detachView();
//            }
//        });
        initView();
        doBusiness();
        inject();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public abstract void initView();

    public abstract void doBusiness();

    public abstract void inject();

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
        progressDialog.show();
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
        if (mPresenter != null) mPresenter.detachView();
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