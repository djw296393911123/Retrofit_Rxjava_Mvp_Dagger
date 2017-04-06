package com.djw.dagger2.ui.wx.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.dagger2.MainActivity;
import com.djw.dagger2.R;
import com.djw.dagger2.adapter.WxAdapter;
import com.djw.dagger2.base.BaseFragment;
import com.djw.dagger2.data.wx.WxData;
import com.djw.dagger2.ui.wx.contracts.WxContracts;
import com.djw.dagger2.ui.wx.presenter.WxPresenter;
import com.djw.dagger2.util.SearchPopWindows;

import java.util.List;

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
                int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {
                    if (!isLoading) {
                        if (!keyword.equals(""))
                            mPresenter.getMoreSearchData(String.valueOf(++page), keyword);
                        else
                            mPresenter.getMoreContent(String.valueOf(++index));
                        isLoading = true;
                    }
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
    public void showListContent(List<WxData.NewslistBean> list) {
        keyword = "";
        adapter.notifyListChange(list, false);
    }

    @Override
    public void showSearchData(List<WxData.NewslistBean> list) {
        Log.i("showSearchData", list.toString());
        adapter.notifyListChange(list, false);
    }

    @Override
    public void showMoreContent(List<WxData.NewslistBean> wxData) {
        keyword = "";
        adapter.notifyListChange(wxData, true);
    }

    @Override
    public void showMoreSearchData(List<WxData.NewslistBean> list) {
        adapter.notifyListChange(list, true);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        keyword = v.getText().toString().trim();
        mPresenter.getSearchData(keyword);
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
