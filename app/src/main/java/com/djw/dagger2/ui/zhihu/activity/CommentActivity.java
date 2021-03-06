package com.djw.dagger2.ui.zhihu.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.djw.dagger2.R;
import com.djw.dagger2.adapter.CommentViewpager;
import com.djw.dagger2.base.SimpleActivity;
import com.djw.dagger2.ui.zhihu.fragment.LongCommentFragment;
import com.djw.dagger2.ui.zhihu.fragment.ShortCommentFragment;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends SimpleActivity {

    private TabLayout tabLayout;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tl_comment);
        pager = (ViewPager) findViewById(R.id.vp_comment);
    }

    @Override
    public void doBusiness() {
        String id = getIntent().getExtras().getString("id");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(LongCommentFragment.newInstance(id));
        fragments.add(ShortCommentFragment.newInstance(id));
        pager.setAdapter(new CommentViewpager(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(pager);
    }
}
