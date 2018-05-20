package com.example.administrator.myapplication.di;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5afbdcdeb27b0a65c8000025"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setQQZone("1106841746","afToyUkscz832oz9");
          }
}
