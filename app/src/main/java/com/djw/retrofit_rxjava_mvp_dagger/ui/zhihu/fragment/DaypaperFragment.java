package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.retrofit_rxjava_mvp_dagger.MainActivity;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.DaypaperAdapter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseFragment;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.paper.PaperBaseData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.PaperContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter.PaperPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.util.RecyclerUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaypaperFragment extends BaseFragment<PaperPresenter> implements PaperContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private DaypaperAdapter adapter;
    private boolean isLoading = false;
    private int index = 1;
    private RecyclerView recyclerView;
    private boolean isSuccess = false;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void lazyLoad() {
        if (!isSuccess || !isVisible)
            return;
        mPresenter.getPaperData();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_paper));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_daypaper);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerUtils.isSlideToBottom(recyclerView) && !isLoading) {
                    mPresenter.getBeforeData(String.valueOf(RecyclerUtils.getBeforeDate(1000 * 24 * 60 * 60 * index++)));
                    isLoading = true;
                }
            }

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
        adapter = new DaypaperAdapter(getActivity());
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
        return R.layout.fragment_daypaper;
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
    public void showPaperData(List<PaperBaseData> daypaperData) {
        adapter.notifyListChange(daypaperData, false);
    }

    @Override
    public void showBeforeData(List<PaperBaseData> daypaperBeforeData) {
        isLoading = false;
        adapter.notifyListChange(daypaperBeforeData, true);
    }

    @Override
    public void onRefresh() {
        index = 1;
        mPresenter.getPaperData();
    }
}
