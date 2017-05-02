package com.djw.dagger2.ui.wx.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.dagger2.R;
import com.djw.dagger2.adapter.WxAdapter;
import com.djw.dagger2.base.BaseActivity;
import com.djw.dagger2.data.wx.WxData;
import com.djw.dagger2.ui.wx.contracts.SearchContracts;
import com.djw.dagger2.ui.wx.presenter.SearchPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContracts.View, XRecyclerView.LoadingListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.xrv_search)
    XRecyclerView xrvSearch;
    private WxAdapter adapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void initView() {
        xrvSearch.setLayoutManager(new LinearLayoutManager(this));
        xrvSearch.setLoadingListener(this);
        adapter = new WxAdapter(this);
        xrvSearch.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSearchData(List<WxData.NewslistBean> wxData, boolean isLoadMore) {
        xrvSearch.loadMoreComplete();
        xrvSearch.refreshComplete();
        adapter.notifyListChange(wxData, isLoadMore);
    }

    @OnClick(R.id.tv_search)
    public void searchByKeywords() {
        page = 1;
        String search = etSearch.getText().toString().trim();
        if (search.equals("")) Toast.makeText(context, "请输入关键字", Toast.LENGTH_SHORT).show();
        else mPresenter.getSearchData(search, page, false, true);
    }

    @OnClick(R.id.iv_back)
    public void finishActivity() {
        finish();
    }

    @Override
    public void onRefresh() {
        page = 1;
        mPresenter.getSearchData(etSearch.getText().toString().trim(), page, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getSearchData(etSearch.getText().toString().trim(), ++page, true, false);
    }
}
