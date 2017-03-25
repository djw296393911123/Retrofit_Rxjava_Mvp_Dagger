package com.djw.retrofit_rxjava_mvp_dagger.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.retrofit_rxjava_mvp_dagger.MainActivity;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperThemData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity.ThemInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class ThemAdapter extends RecyclerView.Adapter<ThemAdapter.ThemHolder> {

    private List<DaypaperThemData.OthersBean> list;

    private Context context;

    public ThemAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<DaypaperThemData.OthersBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ThemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemHolder(LayoutInflater.from(context).inflate(R.layout.them_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ThemHolder holder, final int position) {
        holder.textView.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getThumbnail()).bitmapTransform(new RoundedCornersTransformation(context, 0, 0)).into(holder.imageView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", list.get(position).getId());
                ((MainActivity) context).startActivity(ThemInfoActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ThemHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        private final FrameLayout layout;

        public ThemHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.iv_them));
            textView = ((TextView) itemView.findViewById(R.id.tv_them));
            layout = ((FrameLayout) itemView.findViewById(R.id.fl_them));
        }
    }
}
