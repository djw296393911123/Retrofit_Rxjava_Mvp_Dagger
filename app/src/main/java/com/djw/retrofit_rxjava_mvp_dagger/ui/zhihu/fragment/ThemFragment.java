package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.retrofit_rxjava_mvp_dagger.MainActivity;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.ThemAdapter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseFragment;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperThemData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.ThemComtract;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter.ThemPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.util.RecyclerUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemFragment extends BaseFragment<ThemPresenter> implements ThemComtract.View {

    private ThemAdapter adapter;

    private boolean isSuccess = false;

    @Override
    protected void lazyLoad() {
        if (!isSuccess || !isVisible)
            return;
        mPresenter.getThemData();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_them);
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
        ((MainActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((MainActivity) getActivity()).dismissProgress();
    }

    @Override
    public void showThemData(DaypaperThemData daypaperThemData) {
        adapter.notifyListChange(daypaperThemData.getOthers());
    }
}
