package com.djw.retrofit_rxjava_mvp_dagger.ui.wx.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.retrofit_rxjava_mvp_dagger.MainActivity;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.WxAdapter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseFragment;
import com.djw.retrofit_rxjava_mvp_dagger.data.wx.WxData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.wx.contracts.WxContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.wx.presenter.WxPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.util.RecyclerUtils;
import com.djw.retrofit_rxjava_mvp_dagger.util.SearchPopWindows;

/**
 * A simple {@link Fragment} subclass.
 */
public class WXFragment extends BaseFragment<WxPresenter> implements WxContracts.View, TextView.OnEditorActionListener, SwipeRefreshLayout.OnRefreshListener {

    private WxAdapter adapter;

    private boolean isSuccess = false;
    private SearchPopWindows searchPopWindows;
    private Toolbar toolbar;
    private String keyword;
    private int page = 1;
    private boolean isLoading = false;
    private int index = 1;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        mPresenter.getListContent();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_wx));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        toolbar = (Toolbar) view.findViewById(R.id.tl_base);
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tv_toolbar_title)).setText(getString(R.string.wx));
        view.findViewById(R.id.iv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPopWindows = new SearchPopWindows(getActivity(), WXFragment.this);
                searchPopWindows.showAsDropDown(toolbar, 5, 5);
            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_wx);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerUtils.isSlideToBottom(recyclerView) && !isLoading) {
                    if (!keyword.equals(""))
                        mPresenter.getSearchData(String.valueOf(++page), keyword);
                    else
                        mPresenter.getMoreContent(String.valueOf(++index));
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
        adapter = new WxAdapter(getActivity());
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
        return R.layout.fragment_wx;
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
    public void showListContent(WxData list) {
        keyword = "";
        adapter.notifyListChange(list.getNewslist(), false);
    }

    @Override
    public void showSearchData(WxData list) {
        adapter.notifyListChange(list.getNewslist(), false);
    }

    @Override
    public void showMoreContent(WxData wxData) {
        keyword = "";
        adapter.notifyListChange(wxData.getNewslist(), true);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        keyword = v.getText().toString().trim();
        mPresenter.getSearchData("1", keyword);
        searchPopWindows.dismiss();
        return false;
    }

    @Override
    public void onRefresh() {
        index = 1;
        page = 1;
        mPresenter.getListContent();
    }
}
