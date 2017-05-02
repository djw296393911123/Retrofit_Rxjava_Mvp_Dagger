package com.djw.dagger2.ui.zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.djw.dagger2.R;
import com.djw.dagger2.adapter.ShortCommentAdapter;
import com.djw.dagger2.base.BaseFragment;
import com.djw.dagger2.data.zhihu.DaypaperShortCommentData;
import com.djw.dagger2.ui.zhihu.contracts.ShortCommentContracts;
import com.djw.dagger2.ui.zhihu.presenter.ShortCommentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShortCommentFragment extends BaseFragment<ShortCommentPresenter> implements ShortCommentContracts.View {

    private ShortCommentAdapter adapter;

    public static ShortCommentFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);
        ShortCommentFragment fragment = new ShortCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {


    }

    @Override
    protected void initView(View view) {
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
        mPresenter.getShortComment(getArguments().getString("id"));
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
