package com.djw.dagger2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.dagger2.R;
import com.djw.dagger2.data.zhihu.DaypaperLongcommentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/24.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    private List<DaypaperLongcommentData.CommentsBean> list;

    private Context context;

    public CommentAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<DaypaperLongcommentData.CommentsBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        holder.comment.setText(list.get(position).getContent());
        holder.name.setText(list.get(position).getAuthor());
        Glide.with(context).load(list.get(position).getAvatar()).asBitmap().into(holder.head);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CommentHolder extends RecyclerView.ViewHolder {
        private final ImageView head;
        private final TextView comment;
        private final TextView name;

        public CommentHolder(View itemView) {
            super(itemView);
            head = ((ImageView) itemView.findViewById(R.id.iv_comment_head));
            comment = ((TextView) itemView.findViewById(R.id.tv_comment));
            name = ((TextView) itemView.findViewById(R.id.tv_name));
        }
    }
}
