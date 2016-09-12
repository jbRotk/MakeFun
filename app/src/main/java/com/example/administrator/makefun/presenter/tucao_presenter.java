package com.example.administrator.makefun.presenter;

import android.content.Context;

import com.example.administrator.makefun.bean.tuku_bean;
import com.example.administrator.makefun.model.api.Callback;
import com.example.administrator.makefun.model.tuku_model;
import com.example.administrator.makefun.view.intefce.ITuCao;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by BrazZ on 2016/9/11.
 */
public class tucao_presenter {
    Context context;
    ITuCao tuCao;
    tuku_model tuku_model;
    public tucao_presenter(Context context, ITuCao tuCao)
    {
        this.context = context;
        this.tuCao = tuCao;
        tuku_model = new tuku_model();
    }

    public void getData()
    {
        tuku_model.getTuCaoData(new Callback<tuku_bean>() {
            @Override
            public void onSccuss(tuku_bean data) {

                tuCao.getTucao_Adapter().setTuku(data);
                System.out.println("success");
            }

            @Override
            public void onLoading() {

            }

            @Override
            public void onFaild() {

            }
        })
                .subscribeOn(Schedulers.io())// 在非UI线程中执行getUser
                .observeOn(AndroidSchedulers.mainThread())// 在UI线程中执行结果
                .subscribe();

    }
}
