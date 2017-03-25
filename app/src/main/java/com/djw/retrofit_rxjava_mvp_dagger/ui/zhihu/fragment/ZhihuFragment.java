package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.ZhihuViewpager;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseFragment;
import com.djw.retrofit_rxjava_mvp_dagger.data.gank.GankListItemData;
import com.djw.retrofit_rxjava_mvp_dagger.http.RetrofitHelper;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.ZhihuContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter.ZhihuPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuFragment extends BaseFragment<ZhihuPresenter> implements ZhihuContracts.View {

    private ViewPager pager;
    private TabLayout tabLayout;
    private DaypaperFragment daypaperFragment;
    private ImageView head;
    private ThemFragment themFragment;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tl_base);
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tv_toolbar_title)).setText(getString(R.string.zhihuribao));
        pager = ((ViewPager) view.findViewById(R.id.vp_zhihu));
        tabLayout = ((TabLayout) view.findViewById(R.id.tl_zhihu));
        head = ((ImageView) view.findViewById(R.id.iv_zhihu));
    }

    @Override
    protected void doBusiness() {
        List<Fragment> fragments = new ArrayList<>();
        daypaperFragment = new DaypaperFragment();
        fragments.add(daypaperFragment);
        themFragment = new ThemFragment();
        fragments.add(themFragment);
        fragments.add(new SectionFragment());
        fragments.add(new HotFragment());
        pager.setAdapter(new ZhihuViewpager(getChildFragmentManager(), fragments));
        tabLayout.setupWithViewPager(pager);
    }

    public void refreshData(int position) {
        switch (position) {
            case 0:
                daypaperFragment.mPresenter.getPaperData();
                break;
            case 1:
                themFragment.mPresenter.getThemData();
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
//        mPresenter.getRadomMeizi("1");
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_zhihu;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showRadomMeizi(List<GankListItemData.ResultsBean> list) {
        Glide.with(getActivity()).load(list.get(0).getUrl()).asBitmap().into(head);
    }
}
