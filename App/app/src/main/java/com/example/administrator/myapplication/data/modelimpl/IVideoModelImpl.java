package com.example.administrator.myapplication.data.modelimpl;

import android.util.Log;

import com.example.administrator.myapplication.data.bean.VideoBean;
import com.example.administrator.myapplication.data.model.IVideoModel;
import com.example.administrator.myapplication.data.utils.RetrofitUtils;
import com.example.administrator.myapplication.data.utils.RetrofitUtilsVideo;
import com.example.administrator.myapplication.di.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class IVideoModelImpl implements IVideoModel {
    @Override
    public void requestVideoData(final OnVideoDataChangeLister onVideoDataChangeLister) {
        //网络请求
        RetrofitUtilsVideo instance = RetrofitUtilsVideo.getInstance();
        ApiService apiService = instance.getApiService();
        Observable<VideoBean> video = apiService.getVideo();
        video.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoBean videoBean) {
                        onVideoDataChangeLister.onVideoData(videoBean);
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
