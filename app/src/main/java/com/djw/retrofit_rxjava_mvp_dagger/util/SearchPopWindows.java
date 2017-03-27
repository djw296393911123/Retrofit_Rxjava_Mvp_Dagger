package com.djw.retrofit_rxjava_mvp_dagger.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.djw.retrofit_rxjava_mvp_dagger.R;


/**
 * Created by JasonDong on 2017/3/17.
 */

public class SearchPopWindows extends PopupWindow {

    private EditText etSearch;

    public SearchPopWindows(Context context, TextView.OnEditorActionListener listener) {
        super(context);
        initView(context, listener);
    }

    private void initView(Context context, TextView.OnEditorActionListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_search, null);
        etSearch = ((EditText) view.findViewById(R.id.et_search));
        etSearch.setOnEditorActionListener(listener);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
//        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        this.setTouchable(true);
        ColorDrawable colorDrawable = new ColorDrawable(0xffffff);
        this.setBackgroundDrawable(colorDrawable);
//        bgView.setVisibility(View.VISIBLE);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }
}
