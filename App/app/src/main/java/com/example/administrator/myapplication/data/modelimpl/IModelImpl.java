package com.example.administrator.myapplication.data.modelimpl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.example.administrator.myapplication.data.bean.Toutiao1;
import com.example.administrator.myapplication.data.model.IModel;
import com.example.administrator.myapplication.data.utils.RetrofitUtils;
import com.example.administrator.myapplication.di.ApiService;
import com.example.administrator.myapplication.ui.activity.FirstActivity;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class IModelImpl implements IModel {
    @Override
    public void requestData(String url ,final OnHomeDataChangeLister onHomeDataChangeLister) {
        textCache(url);
        //网络请求
        RetrofitUtils instance = RetrofitUtils.getInstance();
        ApiService apiService = instance.getApiService();
        Observable<Toutiao1> home = apiService.getHome(url);
        home
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Toutiao1>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Toutiao1 toutiao1) {
                        onHomeDataChangeLister.onHomeData(toutiao1);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
    public void textCache(final String url){
        String defaultDiskCacheDir = ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
        //缓存文件夹
        File cacheFile = new File(defaultDiskCacheDir,"/cache");
        //缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
        //创建缓存对象
        final Cache cache = new Cache(cacheFile,cacheSize);

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder()
                        .cache(cache)
                        .build();
                //官方的一个示例的url

                String s =  "http://c.m.163.com/nc/article/";
                Request request = new Request.Builder()
                        .url(s+url)
                        .build();
                Call call1 = client.newCall(request);
                Response response1 = null;
                try {
                    //第一次网络请求
                    response1 = call1.execute();
                    response1.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call call12 = client.newCall(request);

                try {
                    //第二次网络请求
                    Response response2 = call12.execute();
                    response2.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
