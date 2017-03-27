package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.djw.retrofit_rxjava_mvp_dagger.MainActivity;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.SectionAdapter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseFragment;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperSectionData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.SectionContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter.SectionPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionFragment extends BaseFragment<SectionPresenter> implements SectionContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private SectionAdapter adapter;

    private boolean isSuccess = false;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        mPresenter.getSectionList();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_section));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_section);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != 0) {
                    if (dy < 0)
                        ((MainActivity) getActivity()).isShowHide(false);
                    else
                        ((MainActivity) getActivity()).isShowHide(true);
                }
            }
        });
        adapter = new SectionAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        lazyLoad();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_section;
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showSectionList(DaypaperSectionData daypaperSectionData) {
        adapter.notifyListChange(daypaperSectionData.getData());
    }

    @Override
    public void onRefresh() {
        mPresenter.getSectionList();
    }
}
