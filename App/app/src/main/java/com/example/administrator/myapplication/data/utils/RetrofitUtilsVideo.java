package com.example.administrator.myapplication.data.utils;

import com.example.administrator.myapplication.di.ApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class RetrofitUtilsVideo {
    private static RetrofitUtilsVideo retrofitUtils;
    private Retrofit retrofit;

    public RetrofitUtilsVideo() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://c.m.163.com/nc/video/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
   
    public static RetrofitUtilsVideo getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtilsVideo.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtilsVideo();
                }
            }
        }
        return retrofitUtils;
    }
    public ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }
}
