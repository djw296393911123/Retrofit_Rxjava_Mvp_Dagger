package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.CommentAdapter;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.ShortCommentAdapter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseFragment;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperShortCommentData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.ShortCommentContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter.ShortCommentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShortCommentFragment extends BaseFragment<ShortCommentPresenter> implements ShortCommentContracts.View {

    private ShortCommentAdapter adapter;

    private boolean isSuccess = false;

    public static ShortCommentFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);
        ShortCommentFragment fragment = new ShortCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        mPresenter.getShortComment(getArguments().getString("id"));
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_long);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ShortCommentAdapter(getActivity());
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
        return R.layout.fragment_long_comment;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showShortComment(DaypaperShortCommentData daypaperShortCommentData) {
        adapter.notifyListChange(daypaperShortCommentData.getComments());
    }
}
