package com.example.administrator.makefun.view.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.makefun.R;
import com.example.administrator.makefun.presenter.tucao_presenter;
import com.example.administrator.makefun.view.Adapter.Tucao_Adapter;
import com.example.administrator.makefun.view.intefce.ITuCao;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuCaoPic extends Fragment implements ITuCao{
    private View mview;
    @InjectView(R.id.tucao_recycle)
    protected RecyclerView tucao_recycle;
    protected Tucao_Adapter tucao_adapter;

    tucao_presenter tucao_presenter;

    public TuCaoPic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_tu_cao_pic, container, false);
        init();
        return mview;
    }

    private void init()
    {
        ButterKnife.inject(getActivity());
        tucao_recycle = (RecyclerView)mview.findViewById(R.id.tucao_recycle);
        tucao_recycle.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        tucao_recycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getActivity().getResources().getDimensionPixelSize(R.dimen.Recycler_item_line_width);
                outRect.right = getActivity().getResources().getDimensionPixelSize(R.dimen.Recycler_item_line_width);
            }
        });


        tucao_adapter = new Tucao_Adapter(getActivity());
        tucao_recycle.setAdapter(tucao_adapter);
        tucao_presenter = new tucao_presenter(getActivity(),this);
        tucao_presenter.getData();
    }

    @Override
    public Tucao_Adapter getTucao_Adapter() {
        return tucao_adapter;
    }
}
