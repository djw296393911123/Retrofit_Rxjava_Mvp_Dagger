package com.djw.dagger2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.djw.dagger2.adapter.MainViewpager;
import com.djw.dagger2.base.SimpleActivity;
import com.djw.dagger2.interfacts.OnShowOrHideBarListener;
import com.djw.dagger2.ui.gank.fragment.GankFragment;
import com.djw.dagger2.ui.wx.fragment.WXFragment;
import com.djw.dagger2.ui.zhihu.fragment.ZhihuFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SimpleActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener, OnShowOrHideBarListener {

    private BottomNavigationBar bar;
    private ViewPager pager;
    private ZhihuFragment zhihuFragment;
    private WXFragment wxFragment;
    private GankFragment gankFragment;
    private long exitTime;

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
        wxFragment = new WXFragment();
        fragments.add(wxFragment);
        gankFragment = new GankFragment();
        fragments.add(gankFragment);
        pager.setAdapter(new MainViewpager(getSupportFragmentManager(), fragments));
        pager.setOffscreenPageLimit(fragments.size());
        pager.addOnPageChangeListener(this);
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
                zhihuFragment.refreshData();
                break;
            case 1:
                wxFragment.mPresenter.getListContent();
                break;
            case 2:
                gankFragment.refreshData();
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        isShowHide(false);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
