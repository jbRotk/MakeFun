package com.example.administrator.makefun.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.makefun.R;
import com.example.administrator.makefun.bean.tuku_bean;

/**
 * Created by BrazZ on 2016/9/11.
 */
public class Tucao_Adapter extends RecyclerView.Adapter<Tucao_Adapter.Tucao_ViewHolder> {

    tuku_bean tuku;
    Context context;
    public Tucao_Adapter(Context context)
    {
        this.context = context;
    }
    @Override
    public Tucao_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Tucao_ViewHolder tucao_viewHolder = new Tucao_ViewHolder(LayoutInflater.from(context).inflate(R.layout.tuku_adapter_view,null));
        return tucao_viewHolder;
    }

    @Override
    public void onBindViewHolder(Tucao_ViewHolder holder, int position) {
        Glide.with(context).load(tuku.getTucao().get(position).getImg_src()).into(holder.tuku_pic);
        holder.tuku_context.setText(tuku.getTucao().get(position).getContext());
        holder.tuku_uptime.setText("上传时间："+tuku.getTucao().get(position).getTime());
    }

    @Override
    public int getItemCount() {
        if(tuku == null)
        {
            return 0;
        }
        return tuku.getTucao().size();
    }

    public void setTuku(tuku_bean tuku) {
        this.tuku = tuku;
        this.notifyDataSetChanged();
    }

    public tuku_bean getTuku() {
        return tuku;
    }

    class Tucao_ViewHolder extends RecyclerView.ViewHolder
    {

       public ImageView tuku_pic;

       public TextView tuku_context;

        public TextView tuku_uptime;

        public Tucao_ViewHolder(View itemView) {
            super(itemView);
            tuku_pic = (ImageView)itemView.findViewById(R.id.tuku_pic);
            tuku_context = (TextView)itemView.findViewById(R.id.tuku_content);
            tuku_uptime = (TextView)itemView.findViewById(R.id.tuku_uptime);
        }
    }
}
