package com.example.administrator.myapplication.di;

import com.example.administrator.myapplication.data.bean.Toutiao1;
import com.example.administrator.myapplication.data.bean.VideoBean;
import com.example.administrator.myapplication.data.bean.VideoThreeBean;
import com.example.administrator.myapplication.data.bean.VideoTwoBean;
import com.example.administrator.myapplication.data.url.URLAddress;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public interface ApiService {
    //    @GET(URLAddress.URL_HOME)
//    //Observable<Toutiao1> getHome(@Url String url);
//   Observable<Toutiao1> getHome();
    @GET
    Observable<Toutiao1> getHome(@Url String url);

    //热点
    @GET(URLAddress.VIDEO_HOT)
    Observable<VideoBean> getVideo();

    //娱乐
    @GET(URLAddress.VIDEO_TWO)
    Observable<VideoTwoBean> getTwoVideo();

    //搞笑
    @GET(URLAddress.VIDEO_THREE)
    Observable<VideoThreeBean> getThreeVideo();
}
