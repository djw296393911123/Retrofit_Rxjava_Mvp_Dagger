package com.djw.retrofit_rxjava_mvp_dagger.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.retrofit_rxjava_mvp_dagger.R;
import com.djw.retrofit_rxjava_mvp_dagger.data.zhihu.DaypaperThemInfoData;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity.PaperInfoActivity;
import com.djw.retrofit_rxjava_mvp_dagger.ui.zhihu.activity.ThemInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class ThemInfoAdapter extends RecyclerView.Adapter<ThemInfoAdapter.TheminfoHolder> {

    private List<DaypaperThemInfoData.StoriesBean> list;

    private Context context;

    public ThemInfoAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<DaypaperThemInfoData.StoriesBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public TheminfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TheminfoHolder(LayoutInflater.from(context).inflate(R.layout.item_paper_news, parent, false));
    }

    @Override
    public void onBindViewHolder(TheminfoHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        List<String> images = list.get(position).getImages();
        if (images != null)
            Glide.with(context).load(images.get(0)).asBitmap().into(holder.head);
        else holder.head.setImageResource(R.mipmap.img_default_meizi);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", list.get(position).getId());
                ((ThemInfoActivity) context).startActivity(PaperInfoActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TheminfoHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView head;
        private final CardView cardView;

        public TheminfoHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
            title = ((TextView) itemView.findViewById(R.id.tv_paper_title));
            head = ((ImageView) itemView.findViewById(R.id.iv_paper_img));
        }
    }

}
