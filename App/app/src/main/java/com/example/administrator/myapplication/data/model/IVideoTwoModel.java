package com.example.administrator.myapplication.data.model;

import com.example.administrator.myapplication.data.bean.VideoBean;
import com.example.administrator.myapplication.data.bean.VideoTwoBean;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public interface IVideoTwoModel {
    void requestVideoData(OnVideoDataChangeLister onVideoDataChangeLister);
    interface OnVideoDataChangeLister{
        void onVideoData(VideoTwoBean videoBean);
    }
}
