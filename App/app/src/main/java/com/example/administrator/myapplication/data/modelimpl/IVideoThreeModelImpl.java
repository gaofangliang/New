package com.example.administrator.myapplication.data.modelimpl;

import com.example.administrator.myapplication.data.bean.VideoThreeBean;
import com.example.administrator.myapplication.data.bean.VideoTwoBean;
import com.example.administrator.myapplication.data.model.IVideoThreeModel;
import com.example.administrator.myapplication.data.model.IVideoTwoModel;
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

public class IVideoThreeModelImpl implements IVideoThreeModel {

    @Override
    public void requestVideoData(final OnVideoDataChangeLister onVideoDataChangeLister) {
        //网络请求
        RetrofitUtilsVideo instance = RetrofitUtilsVideo.getInstance();
        ApiService apiService = instance.getApiService();
        Observable<VideoThreeBean> threeVideo = apiService.getThreeVideo();
        threeVideo
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoThreeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoThreeBean videoThreeBean) {
                        onVideoDataChangeLister.onVideoData(videoThreeBean);
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
