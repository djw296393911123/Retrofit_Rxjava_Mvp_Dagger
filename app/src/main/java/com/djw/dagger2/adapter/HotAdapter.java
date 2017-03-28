package com.djw.dagger2.adapter;

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
import com.djw.dagger2.MainActivity;
import com.djw.dagger2.R;
import com.djw.dagger2.data.zhihu.DaypaperHotData;
import com.djw.dagger2.ui.zhihu.activity.PaperInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/25.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.SectionInfoHolder> {

    private List<DaypaperHotData.RecentBean> list;

    private Context context;

    public HotAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<DaypaperHotData.RecentBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public SectionInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SectionInfoHolder(LayoutInflater.from(context).inflate(R.layout.item_paper_news, parent, false));
    }

    @Override
    public void onBindViewHolder(SectionInfoHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getThumbnail()).asBitmap().into(holder.head);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", list.get(position).getNews_id());
                ((MainActivity) context).startActivity(PaperInfoActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SectionInfoHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView head;
        private final CardView cardView;

        public SectionInfoHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
            title = ((TextView) itemView.findViewById(R.id.tv_paper_title));
            head = ((ImageView) itemView.findViewById(R.id.iv_paper_img));
        }
    }

}
