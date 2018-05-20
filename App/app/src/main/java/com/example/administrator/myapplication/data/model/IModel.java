package com.example.administrator.myapplication.data.model;

import com.example.administrator.myapplication.data.bean.Toutiao1;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public interface IModel {
    void requestData(String url, OnHomeDataChangeLister onHomeDataChangeLister);

    interface OnHomeDataChangeLister {
        //头条
        void onHomeData(Toutiao1 toutiao1);
    }
//    //视频
//    void VideoData(OnVideoDataChangeLister onVideoDataChangeLister);
//    interface OnVideoDataChangeLister{
//        void OnVideoLister(Video video);
//    }
}
