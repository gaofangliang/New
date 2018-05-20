package com.example.administrator.myapplication.data.utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.di.ApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private Retrofit retrofit;

    public OkHttpClient getOkHttp() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if (TextUtils.isEmpty(response.cacheControl().toString())) {

//生成新的response对象 往新的对象中手动添加Cache-Control 让其支持缓存
                    Response newReponse = response.newBuilder().addHeader("Cache-Control", "max-age=" + 60 * 60 * 2).build();
                    return newReponse;
                }
                return response;
            }
        };
        Cache cache = new Cache(Environment.getDownloadCacheDirectory(), 1024 * 1024 * 20);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).addNetworkInterceptor(interceptor).build();
        return okHttpClient;
    }

    public RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://c.m.163.com/nc/article/")
                .client(getOkHttp())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
