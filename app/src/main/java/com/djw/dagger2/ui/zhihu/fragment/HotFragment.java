package com.djw.dagger2.ui.zhihu.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.dagger2.MainActivity;
import com.djw.dagger2.R;
import com.djw.dagger2.adapter.HotAdapter;
import com.djw.dagger2.base.BaseFragment;
import com.djw.dagger2.data.zhihu.DaypaperHotData;
import com.djw.dagger2.ui.zhihu.contracts.HotContracts;
import com.djw.dagger2.ui.zhihu.presenter.HotPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment<HotPresenter> implements HotContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private HotAdapter adapter;

    private boolean isSuccess = false;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        mPresenter.getContentList();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_hot));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_hot);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        adapter = new HotAdapter(getActivity());
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
        return R.layout.fragment_hot;
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
    public void showContentList(DaypaperHotData daypaperHotData) {
        adapter.notifyListChange(daypaperHotData.getRecent());
    }

    @Override
    public void onRefresh() {
        mPresenter.getContentList();
    }
}
