package com.djw.dagger2.adapter;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djw.dagger2.R;
import com.djw.dagger2.data.gank.GankListItemData;
import com.djw.dagger2.ui.gank.fragment.PageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/17.
 *
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.MeiziHolder> {

    private List<GankListItemData.ResultsBean> list;

    private Context context;

    private List<String> urls;

    public MeiziAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.urls = new ArrayList<>();
    }

    public void notifyDataChange(List<GankListItemData.ResultsBean> list, boolean isLoadMore) {

        if (!isLoadMore) {
            this.list.clear();
            this.urls.clear();
        }
        for (int i = 0; i < list.size(); i++) {
            urls.add(list.get(i).getUrl());
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MeiziHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeiziHolder(LayoutInflater.from(context).inflate(R.layout.item_meizi, parent, false));
    }

    @Override
    public void onBindViewHolder(MeiziHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getUrl()).asBitmap().placeholder(R.mipmap.img_default_meizi).error(R.mipmap.img_default_meizi).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.add(PageFragment.newInstance(urls, position), "img");
                transaction.commitAllowingStateLoss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MeiziHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final CardView cardView;

        public MeiziHolder(View itemView) {
            super(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.iv_meizi));
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
        }
    }
}
