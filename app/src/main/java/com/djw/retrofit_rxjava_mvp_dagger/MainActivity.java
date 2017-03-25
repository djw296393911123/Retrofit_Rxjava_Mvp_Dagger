package com.djw.retrofit_rxjava_mvp_dagger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.MainViewpager;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseActivity;
import com.djw.retrofit_rxjava_mvp_dagger.interfacts.OnShowOrHideBarListener;
import com.djw.retrofit_rxjava_mvp_dagger.ui.gank.fragment.GankFragment;
import com.djw.retrofit_rxjava_mvp_dagger.ui.wx.fragment.WXFragment;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment.ZhihuFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener, OnShowOrHideBarListener {

    private BottomNavigationBar bar;
    private ViewPager pager;
    private ZhihuFragment zhihuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackEnable(false);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        bar = (BottomNavigationBar) findViewById(R.id.bnb_main);
        pager = (ViewPager) findViewById(R.id.vp_main);
    }

    @Override
    public void doBusiness() {

        bar.setMode(BottomNavigationBar.MODE_FIXED);
        bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bar
                .addItem(new BottomNavigationItem(R.drawable.ic_school_black_24dp, getString(R.string.zhihuribao)))
                .addItem(new BottomNavigationItem(R.drawable.ic_textsms_black_24dp, getString(R.string.wx)))
                .addItem(new BottomNavigationItem(R.drawable.ic_airplanemode_active_black_24dp, getString(R.string.ganhuo)))
                .initialise();
        bar.setTabSelectedListener(this);

        List<Fragment> fragments = new ArrayList<>();
        zhihuFragment = new ZhihuFragment();
        fragments.add(zhihuFragment);
        fragments.add(new WXFragment());
        fragments.add(new GankFragment());
        pager.setAdapter(new MainViewpager(getSupportFragmentManager(), fragments));
        pager.setOffscreenPageLimit(fragments.size());
        pager.addOnPageChangeListener(this);
    }

    @Override
    public void inject() {

    }

    @Override
    public void onTabSelected(int position) {
        pager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
        switch (position) {
            case 0:
                zhihuFragment.refreshData(position);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void isShowHide(boolean isShowHide) {
        if (isShowHide)
            bar.animate().translationY(bar.getHeight());
        else
            bar.animate().translationY(0);
    }

    @Override
    public void showError(String msg) {

    }
}
