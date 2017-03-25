package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.adapter.SectionInfoAdapter;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseActivity;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperSectionInfoData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.SectionInfoContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter.SectionInfoPresenter;

public class SectionActivity extends BaseActivity<SectionInfoPresenter> implements SectionInfoContracts.View {

    private SectionInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
    }

    @Override
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_section_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SectionInfoAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getContentList(getIntent().getExtras().getString("id"));
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContentList(DaypaperSectionInfoData daypaperSectionInfoData) {
        adapter.notifyListChange(daypaperSectionInfoData.getStories());
    }
}
