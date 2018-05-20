package com.example.administrator.myapplication.data.modelimpl;

import android.util.Log;

import com.example.administrator.myapplication.data.bean.VideoBean;
import com.example.administrator.myapplication.data.bean.VideoTwoBean;
import com.example.administrator.myapplication.data.model.IVideoModel;
import com.example.administrator.myapplication.data.model.IVideoTwoModel;
import com.example.administrator.myapplication.data.utils.RetrofitUtilsVideo;
import com.example.administrator.myapplication.di.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class IVideoTwoModelImpl implements IVideoTwoModel {
    @Override
    public void requestVideoData(final OnVideoDataChangeLister onVideoDataChangeLister) {
        //网络请求
        RetrofitUtilsVideo instance = RetrofitUtilsVideo.getInstance();
        ApiService apiService = instance.getApiService();
        Observable<VideoTwoBean> twoVideo = apiService.getTwoVideo();
        twoVideo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoTwoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(VideoTwoBean videoTwoBean) {
                        onVideoDataChangeLister.onVideoData(videoTwoBean);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
         });
    }
}
