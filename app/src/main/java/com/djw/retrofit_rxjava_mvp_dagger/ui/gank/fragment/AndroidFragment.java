package com.djw.retrofit_rxjava_mvp_dagger.ui.gank.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.djw.retrofit_rxjava_mvp_dagger.MainActivity;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.GankAdapter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseFragment;
import com.djw.retrofit_rxjava_mvp_dagger.data.gank.GankListItemData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.gank.contracts.AndroidContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.gank.presenter.AndroidPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.util.RecyclerUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends BaseFragment<AndroidPresenter> implements AndroidContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private GankAdapter adapter;

    private boolean isSuccess = false;
    private boolean isLoading = false;
    private int index = 1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Override
    protected void lazyLoad() {
        if (!isSuccess || !isVisible)
            return;
        mPresenter.getRadmomMeizi();
        mPresenter.getAndroid();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_gank));
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_gank);
    }

    @Override
    protected void doBusiness() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerUtils.isSlideToBottom(recyclerView) && !isLoading) {
                    mPresenter.getMoreAndroid(String.valueOf(++index));
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
        adapter = new GankAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        lazyLoad();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_android;
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
    public void showAndroid(List<GankListItemData.ResultsBean> list) {
        adapter.notifyDataChange(list, false);
    }

    @Override
    public void showMoreAndroid(List<GankListItemData.ResultsBean> list) {
        isLoading = false;
        adapter.notifyDataChange(list, true);
    }

    @Override
    public void showRadomMeizi(List<GankListItemData.ResultsBean> list) {
        final FutureTarget<File> future = Glide.with(getActivity())
                .load(list.get(0).getUrl())
                .downloadOnly(500, 500);
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(future.get().getAbsolutePath());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                recyclerView.setBackground(Drawable.createFromPath(s));
            }
        });
    }

    @Override
    public void onRefresh() {
        index = 1;
        mPresenter.getAndroid();
    }
}
