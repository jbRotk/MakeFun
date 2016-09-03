package com.example.administrator.makefun.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.makefun.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoRecommendation extends Fragment {


    public VideoRecommendation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_recommendation, container, false);
    }

}
