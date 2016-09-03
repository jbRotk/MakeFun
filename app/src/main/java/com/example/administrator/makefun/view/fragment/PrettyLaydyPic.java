package com.example.administrator.makefun.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.makefun.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrettyLaydyPic.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrettyLaydyPic#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrettyLaydyPic extends Fragment {
    public PrettyLaydyPic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pretty_laydy_pic, container, false);
    }
}
