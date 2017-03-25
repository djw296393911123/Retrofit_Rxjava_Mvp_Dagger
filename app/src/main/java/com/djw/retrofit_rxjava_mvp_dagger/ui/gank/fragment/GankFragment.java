package com.djw.retrofit_rxjava_mvp_dagger.ui.gank.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djw.retrofit_rxjava_mvp_dagger.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends Fragment {


    public GankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gank, container, false);
    }

}
