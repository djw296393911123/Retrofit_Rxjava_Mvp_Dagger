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
import com.djw.dagger2.data.zhihu.paper.BannerData;
import com.djw.dagger2.data.zhihu.paper.ListData;
import com.djw.dagger2.data.zhihu.paper.PaperBaseData;
import com.djw.dagger2.data.zhihu.paper.SelectFourData;
import com.djw.dagger2.data.zhihu.paper.TypeData;
import com.djw.dagger2.ui.zhihu.activity.PaperInfoActivity;
import com.djw.dagger2.ui.zhihu.activity.ThemInfoActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/24.
 */

public class DaypaperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnBannerClickListener, View.OnClickListener {

    private List<PaperBaseData> list;

    private Context context;

    public DaypaperAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<PaperBaseData> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case PaperBaseData.BANNER_TYPE:
                return new BannerHolder(LayoutInflater.from(context).inflate(R.layout.item_paper_banner, parent, false));
            case PaperBaseData.TYPE_TYPE:
                return new TypeHolder(LayoutInflater.from(context).inflate(R.layout.item_paper_type, parent, false));
            case PaperBaseData.LIST_TYPE:
                return new ListHolder(LayoutInflater.from(context).inflate(R.layout.item_paper_news, parent, false));
            case PaperBaseData.FOUR_TYPE:
                return new SelectHolder(LayoutInflater.from(context).inflate(R.layout.item_new_two, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case PaperBaseData.BANNER_TYPE:
                BannerHolder bannerHolder = (BannerHolder) holder;
                BannerData bannerData = (BannerData) list.get(position);
                Banner banner = bannerHolder.banner;
                banner.setImageLoader(new GlideImageLoader());
                banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                banner.setOffscreenPageLimit(1);
                banner.setOnBannerClickListener(this);
                banner.setBannerTitles(bannerData.getTitles());
                banner.setImages(bannerData.getImgs());
                banner.start();
                break;
            case PaperBaseData.TYPE_TYPE:
                ((TypeHolder) holder).type.setText(((TypeData) list.get(position)).getDate());
                break;
            case PaperBaseData.LIST_TYPE:
                ListHolder listHolder = (ListHolder) holder;
                final ListData listData = (ListData) list.get(position);
                listHolder.title.setText(listData.getTitle());
                Glide.with(context).load(listData.getUrl()).asBitmap().into(listHolder.head);
                listHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", listData.getId());
                        ((MainActivity) context).startActivity(PaperInfoActivity.class, bundle);
                    }
                });
                break;
            case PaperBaseData.FOUR_TYPE:
                SelectHolder twoHolder = (SelectHolder) holder;
                SelectFourData two = (SelectFourData) list.get(position);
                twoHolder.ivTowOne.setImageResource(two.getUrl().get(0));
                twoHolder.ivTowOne.setTag(two.getIds().get(0) + "," + two.getTitle().get(0));
                twoHolder.ivTowOne.setOnClickListener(this);
                twoHolder.ivTowTwo.setImageResource(two.getUrl().get(1));
                twoHolder.ivTowTwo.setTag(two.getIds().get(1) + "," + two.getTitle().get(1));
                twoHolder.ivTowTwo.setOnClickListener(this);
                twoHolder.ivTowThree.setImageResource(two.getUrl().get(2));
                twoHolder.ivTowThree.setTag(two.getIds().get(2) + "," + two.getTitle().get(2));
                twoHolder.ivTowThree.setOnClickListener(this);
                twoHolder.ivTowFour.setImageResource(two.getUrl().get(3));
                twoHolder.ivTowFour.setTag(two.getIds().get(3) + "," + two.getTitle().get(3));
                twoHolder.ivTowFour.setOnClickListener(this);
                twoHolder.tvTwoOne.setText(two.getTitle().get(0));
                twoHolder.tvTwoTwo.setText(two.getTitle().get(1));
                twoHolder.tvTwoThree.setText(two.getTitle().get(2));
                twoHolder.tvTwoFour.setText(two.getTitle().get(3));
                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void OnBannerClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", Integer.parseInt(((BannerData) list.get(0)).getIds().get(position - 1)));
        ((MainActivity) context).startActivity(PaperInfoActivity.class, bundle);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        String[] split = ((String) v.getTag()).split(",");
        bundle.putInt("id", Integer.parseInt(split[0]));
        bundle.putString("title", split[1]);
        ((MainActivity) context).startActivity(ThemInfoActivity.class, bundle);
    }

    private static class BannerHolder extends RecyclerView.ViewHolder {

        private final Banner banner;

        BannerHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            banner = ((Banner) itemView.findViewById(R.id.banner_paper));
        }
    }

    private static class ListHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView head;
        private final CardView cardView;

        ListHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
            title = ((TextView) itemView.findViewById(R.id.tv_paper_title));
            head = ((ImageView) itemView.findViewById(R.id.iv_paper_img));
        }
    }

    private static class TypeHolder extends RecyclerView.ViewHolder {

        private final TextView type;

        TypeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            type = ((TextView) itemView.findViewById(R.id.tv_paper_type));
        }
    }

    private static class SelectHolder extends RecyclerView.ViewHolder {
        ImageView ivTowOne;
        ImageView ivTowTwo;
        ImageView ivTowThree;
        ImageView ivTowFour;
        TextView tvTwoOne, tvTwoTwo, tvTwoThree, tvTwoFour;

        SelectHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            ivTowOne = ((ImageView) view.findViewById(R.id.iv_tow_one));
            ivTowTwo = ((ImageView) view.findViewById(R.id.iv_tow_two));
            ivTowThree = ((ImageView) view.findViewById(R.id.iv_tow_three));
            ivTowFour = ((ImageView) view.findViewById(R.id.iv_tow_four));
            tvTwoOne = ((TextView) view.findViewById(R.id.tv_two_one));
            tvTwoTwo = ((TextView) view.findViewById(R.id.tv_two_two));
            tvTwoThree = ((TextView) view.findViewById(R.id.tv_two_three));
            tvTwoFour = ((TextView) view.findViewById(R.id.tv_two_four));
        }
    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).asBitmap().into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            return new ImageView(context);
        }
    }
}
