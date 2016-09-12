package com.example.administrator.makefun.bean.inter_api;

import com.example.administrator.makefun.bean.tuku_bean;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by BrazZ on 2016/9/11.
 */
public interface tuku_api {
    @GET("makefun/{path}")
    Observable<tuku_bean> getFeed(@Path("path") String path);
}
