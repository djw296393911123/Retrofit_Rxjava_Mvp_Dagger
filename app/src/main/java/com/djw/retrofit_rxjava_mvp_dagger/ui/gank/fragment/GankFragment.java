package com.djw.retrofit_rxjava_mvp_dagger.ui.gank.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.djw.retrofit_rxjava_mvp_dagger.MainActivity;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.GankViewpager;
import com.djw.retrofit_rxjava_mvp_dagger.base.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends SimpleFragment implements ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private TabLayout tabLayout;
    private AndroidFragment androidFragment;
    private IOSFragment iosFragment;
    private AllFragment allFragment;
    private MeiziFragment meiziFragment;

    @Override
    protected void initView(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tl_base);
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tv_toolbar_title)).setText(getString(R.string.ganhuo));
        pager = ((ViewPager) view.findViewById(R.id.vp_zhihu));
        tabLayout = ((TabLayout) view.findViewById(R.id.tl_zhihu));
    }

    @Override
    protected void doBusiness() {
        List<Fragment> fragments = new ArrayList<>();
        androidFragment = new AndroidFragment();
        fragments.add(androidFragment);
        iosFragment = new IOSFragment();
        fragments.add(iosFragment);
        allFragment = new AllFragment();
        fragments.add(allFragment);
        meiziFragment = new MeiziFragment();
        fragments.add(meiziFragment);
        pager.setAdapter(new GankViewpager(getChildFragmentManager(), fragments));
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(this);
    }

    public void refreshData() {
        switch (pager.getCurrentItem()) {
            case 0:
                androidFragment.mPresenter.getAndroid();
                break;
            case 1:
                iosFragment.mPresenter.getIOS();
                break;
            case 2:
                allFragment.mPresenter.getAll();
                break;
            case 3:
                meiziFragment.mPresenter.getMeizi();
                break;
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_zhihu;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ((MainActivity) getActivity()).isShowHide(false);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
