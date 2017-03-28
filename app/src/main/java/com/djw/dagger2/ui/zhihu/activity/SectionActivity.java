package com.djw.dagger2.ui.zhihu.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.djw.dagger2.R;
import com.djw.dagger2.adapter.SectionInfoAdapter;
import com.djw.dagger2.base.BaseActivity;
import com.djw.dagger2.data.zhihu.DaypaperSectionInfoData;
import com.djw.dagger2.ui.zhihu.contracts.SectionInfoContracts;
import com.djw.dagger2.ui.zhihu.presenter.SectionInfoPresenter;

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
