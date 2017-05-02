package com.djw.dagger2.ui.zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.dagger2.R;
import com.djw.dagger2.adapter.CommentAdapter;
import com.djw.dagger2.base.BaseFragment;
import com.djw.dagger2.data.zhihu.DaypaperLongcommentData;
import com.djw.dagger2.ui.zhihu.activity.CommentActivity;
import com.djw.dagger2.ui.zhihu.contracts.LongCommentContracts;
import com.djw.dagger2.ui.zhihu.presenter.LongCommentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LongCommentFragment extends BaseFragment<LongCommentPresenter> implements LongCommentContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private CommentAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_long));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_long);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommentAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void doBusiness() {

    }

    public static LongCommentFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);
        LongCommentFragment fragment = new LongCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getLongComment(getArguments().getString("id"));
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_long_comment;
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
    public void showLongComment(DaypaperLongcommentData daypaperLongcommentData) {
        adapter.notifyListChange(daypaperLongcommentData.getComments());
    }

    @Override
    public void onRefresh() {
        mPresenter.getLongComment(getArguments().getString("id"));
    }
}
