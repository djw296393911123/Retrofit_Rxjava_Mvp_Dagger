package com.djw.dagger2.ui.gank.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.djw.dagger2.MainActivity;
import com.djw.dagger2.R;
import com.djw.dagger2.adapter.MeiziAdapter;
import com.djw.dagger2.base.BaseFragment;
import com.djw.dagger2.data.gank.GankListItemData;
import com.djw.dagger2.ui.gank.contracts.MeiziContracts;
import com.djw.dagger2.ui.gank.presenter.MeiziPresenter;
import com.djw.dagger2.util.RecyclerUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends BaseFragment<MeiziPresenter> implements MeiziContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private MeiziAdapter adapter;
    private boolean isSuccess = false;
    private int index = 1;
    private boolean isLoading = false;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        mPresenter.getMeizi();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_meizi));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_meizi);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerUtils.isSlideToBottom(recyclerView) && !isLoading) {
                    mPresenter.getMoreMeizi(String.valueOf(++index));
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
        adapter = new MeiziAdapter(getActivity());
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
        return R.layout.fragment_meizi;
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
    public void showMeizi(List<GankListItemData.ResultsBean> list) {
        adapter.notifyDataChange(list, false);
    }

    @Override
    public void showMoreMeizi(List<GankListItemData.ResultsBean> list) {
        isLoading = false;
        adapter.notifyDataChange(list, true);
    }

    @Override
    public void onRefresh() {
        index = 1;
        mPresenter.getMeizi();
    }
}
