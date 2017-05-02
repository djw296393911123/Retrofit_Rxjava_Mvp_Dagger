package com.djw.dagger2;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djw.dagger2.base.BaseActivity;
import com.djw.dagger2.ui.zhihu.contracts.WelcomeContracts;
import com.djw.dagger2.ui.zhihu.presenter.WelcomePresenter;
import com.djw.dagger2.util.RxUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;


public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContracts.View {

    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackEnable(false);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initView() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getWelcome();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showWelcome(String url) {

        Glide.with(this).load(url).asBitmap().into(ivWelcome);
        Observable.timer(3, TimeUnit.SECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startActivity(MainActivity.class);
                        finish();
                    }
                });
    }
}
