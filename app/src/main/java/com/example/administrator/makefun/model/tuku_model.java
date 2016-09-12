package com.example.administrator.makefun.model;

import com.example.administrator.makefun.bean.inter_api.tuku_api;
import com.example.administrator.makefun.bean.tuku_bean;
import com.example.administrator.makefun.model.api.Callback;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by BrazZ on 2016/9/11.
 */
public class tuku_model {
    public Observable<tuku_bean> getTuCaoData(final Callback<tuku_bean> mCallback)
    {
        return  rx.Observable.create(new rx.Observable.OnSubscribe<tuku_bean>() {
            @Override
            public void call(Subscriber<? super tuku_bean> subscriber) {
                //获取数据
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://120.24.186.88/")
                        .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
                        .build();
                tuku_api luKouAPI = retrofit.create(tuku_api.class);
                luKouAPI.getFeed("Tucao.php")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<tuku_bean>() {
                            @Override
                            public void onCompleted() {
                                System.out.println("Complete");
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println(e.getMessage());
                            }

                            @Override
                            public void onNext(tuku_bean contributors) {
                                mCallback.onSccuss(contributors);
                            }
                        });
            }
        });
    }
}
