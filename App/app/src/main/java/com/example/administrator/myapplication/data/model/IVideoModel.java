package com.example.administrator.myapplication.data.model;

import com.example.administrator.myapplication.data.bean.VideoBean;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public interface IVideoModel {
    void requestVideoData(OnVideoDataChangeLister onVideoDataChangeLister);
    interface OnVideoDataChangeLister{
        void onVideoData(VideoBean videoBean);
    }
}
