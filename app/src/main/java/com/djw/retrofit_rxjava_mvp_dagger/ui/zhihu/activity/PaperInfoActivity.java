package com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.base.BaseActivity;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperInfoData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperLongcommentData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperOtherData;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperShortCommentData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.contracts.PaperInfoContracts;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.presenter.PaperInfoPresenter;
import com.djw.retrofit_rxjava_mvp_dagger.util.ShareUtil;

public class PaperInfoActivity extends BaseActivity<PaperInfoPresenter> implements PaperInfoContracts.View, View.OnClickListener {

    private WebView webView;
    public final static String CSS_STYLE = "<style>* {font-size:16px;line-height:20px;} p {color:#666666;  padding:3px;} a {color:#3E62A6;} img {max-width:310px;}pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;}</style>";
    private Toolbar toolbar;
    private ImageView head;
    private TextView pl;
    private TextView zan;
    private String share_url;
    private NestedScrollView scrollView;
    private LinearLayout bottom;
    private boolean isBottomShow = true;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_info);
    }

    @Override
    public void initView() {
        bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        scrollView = (NestedScrollView) findViewById(R.id.nsv_web);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                    isBottomShow = false;
                    bottom.animate().translationY(scrollView.getHeight());
                } else if (scrollY - oldScrollY < 0 && !isBottomShow) {    //上移出现
                    isBottomShow = true;
                    bottom.animate().translationY(0);
                }
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        head = (ImageView) findViewById(R.id.iv_head);
        webView = (WebView) findViewById(R.id.wv_zhihu);
        findViewById(R.id.ll_one).setOnClickListener(this);
        findViewById(R.id.ll_three).setOnClickListener(this);
        findViewById(R.id.ll_two).setOnClickListener(this);
        pl = (TextView) findViewById(R.id.tv_pinglun);
        zan = (TextView) findViewById(R.id.tv_zan);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {

        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        id = getIntent().getExtras().getInt("id");
        mPresenter.getContent(String.valueOf(id));
        mPresenter.getContentOther(String.valueOf(id));
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContent(DaypaperInfoData daypaperInfoData) {
        share_url = daypaperInfoData.getShare_url();
        Glide.with(this).load(daypaperInfoData.getImage()).asBitmap().into(head);
        toolbar.setTitle(daypaperInfoData.getTitle());
        webView.loadData(CSS_STYLE + daypaperInfoData.getBody(), "text/html; charset=utf-8", "utf-8");
    }

    @Override
    public void showContentOther(DaypaperOtherData daypaperOtherData) {
        zan.setText(daypaperOtherData.getPopularity() + "个赞");
        pl.setText(daypaperOtherData.getComments() + "条评论");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_one:
                break;
            case R.id.ll_two:
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(id));
                startActivity(CommentActivity.class, bundle);
                break;
            case R.id.ll_three:
                ShareUtil.shareText(this, share_url, "分享一篇文章");
                break;
        }
    }
}
