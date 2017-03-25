package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.CommentAdapter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseFragment;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperLongcommentData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity.CommentActivity;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.LongCommentContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter.LongCommentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LongCommentFragment extends BaseFragment<LongCommentPresenter> implements LongCommentContracts.View {

    private CommentAdapter adapter;

    private boolean isSuccess = false;

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        mPresenter.getLongComment(getArguments().getString("id"));
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
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
        lazyLoad();
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
        ((CommentActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((CommentActivity) getActivity()).dismissProgress();
    }

    @Override
    public void showLongComment(DaypaperLongcommentData daypaperLongcommentData) {
        adapter.notifyListChange(daypaperLongcommentData.getComments());
    }
}
