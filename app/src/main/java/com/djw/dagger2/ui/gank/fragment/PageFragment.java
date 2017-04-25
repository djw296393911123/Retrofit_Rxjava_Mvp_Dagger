package com.djw.dagger2.ui.gank.fragment;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.djw.dagger2.R;
import com.djw.dagger2.adapter.ImagePageAdapter;
import com.djw.dagger2.util.ImagePager;
import com.djw.dagger2.util.ShareUtil;
import com.djw.dagger2.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends DialogFragment implements ViewPager.OnPageChangeListener {

    private int position;
    private ArrayList<String> urls;
    private Bitmap bitmap;
    private List<View> views;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        urls = bundle.getStringArrayList("urls");
        initPager();
    }

    private void initPager() {
        views = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_page, null);
            final PhotoView photoView = (PhotoView) view.findViewById(R.id.pv_item);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    dismiss();
                }
            });
            Glide.with(getActivity()).load(urls.get(i)).asBitmap().into(new SimpleTarget<Bitmap>() {


                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    bitmap = resource;
                    photoView.setImageBitmap(resource);
                }
            });
            views.add(view);
        }
    }

    public static PageFragment newInstance(List<String> urls, int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putStringArrayList("urls", (ArrayList<String>) urls);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.page_layout, null);
        final ImagePager pager = (ImagePager) view.findViewById(R.id.pager);
        pager.setAdapter(new ImagePageAdapter(views));
        pager.setCurrentItem(position);
        pager.addOnPageChangeListener(this);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tl_base);
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tv_toolbar_title)).setText("妹子");
        toolbar.findViewById(R.id.iv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriString = urls.get(pager.getCurrentItem());
                Log.i("uriString", uriString);
                SystemUtil.saveBitmapToFile(getActivity(), uriString, bitmap, view, true);
            }
        });
        AlertDialog alertDialog = new AlertDialog
                .Builder(getActivity(), android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen)
                .setView(view)
                .create();
        Window window = alertDialog.getWindow();
        window.setWindowAnimations(R.style.dialog_style);
        return alertDialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        System.gc();
//        Glide.get(BaseApplication.getInstance()).clearMemory();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
