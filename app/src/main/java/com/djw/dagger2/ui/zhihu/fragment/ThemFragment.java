package com.djw.dagger2.ui.zhihu.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.dagger2.MainActivity;
import com.djw.dagger2.R;
import com.djw.dagger2.adapter.ThemAdapter;
import com.djw.dagger2.base.BaseFragment;
import com.djw.dagger2.data.zhihu.DaypaperThemData;
import com.djw.dagger2.ui.zhihu.contracts.ThemComtract;
import com.djw.dagger2.ui.zhihu.presenter.ThemPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemFragment extends BaseFragment<ThemPresenter> implements ThemComtract.View, SwipeRefreshLayout.OnRefreshListener {

    private ThemAdapter adapter;

    private boolean isSuccess = false;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void lazyLoad() {
        if (!isSuccess || !isVisible)
            return;
        mPresenter.getThemData();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_them));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_them);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
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
        adapter = new ThemAdapter(getActivity());
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
        return R.layout.fragment_them;
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
    public void showThemData(DaypaperThemData daypaperThemData) {
        adapter.notifyListChange(daypaperThemData.getOthers());
    }

    @Override
    public void onRefresh() {
        mPresenter.getThemData();
    }
}
