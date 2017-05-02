package com.djw.dagger2.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.djw.dagger2.R;


/**
 * Created by JasonDong on 2017/3/17.
 */

public abstract class MorePopWindows extends PopupWindow {

    public MorePopWindows(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pop_more, null);
        view.findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClick();
                dismiss();
            }
        });
        view.findViewById(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMoreClick();
                dismiss();
            }
        });
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
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

    public abstract void onDeleteClick();

    public abstract void onMoreClick();

}
