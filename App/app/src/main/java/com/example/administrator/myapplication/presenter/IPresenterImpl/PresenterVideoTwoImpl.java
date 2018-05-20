package com.example.administrator.myapplication.presenter.IPresenterImpl;

import android.util.Log;

import com.example.administrator.myapplication.data.bean.VideoBean;
import com.example.administrator.myapplication.data.bean.VideoTwoBean;
import com.example.administrator.myapplication.data.model.IVideoModel;
import com.example.administrator.myapplication.data.model.IVideoTwoModel;
import com.example.administrator.myapplication.data.modelimpl.IVideoModelImpl;
import com.example.administrator.myapplication.data.modelimpl.IVideoTwoModelImpl;
import com.example.administrator.myapplication.presenter.contract.IVoideoContract;
import com.example.administrator.myapplication.presenter.contract.IVoideoTwoContract;
import com.example.administrator.myapplication.ui.bottimFragment.videl.ThreeFragment;
import com.example.administrator.myapplication.ui.bottimFragment.videl.TwoFragment;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class PresenterVideoTwoImpl implements IVoideoTwoContract.IContractPresenter {

    IVoideoTwoContract.IContractView threeFragment;
    private final IVideoTwoModelImpl model;

    public PresenterVideoTwoImpl(IVoideoTwoContract.IContractView threeFragment) {
        this.threeFragment = threeFragment;
        threeFragment.setPresenter(this);
        model = new IVideoTwoModelImpl();
    }
    @Override
    public void loading() {
        threeFragment.Login();
        model.requestVideoData(new IVideoTwoModel.OnVideoDataChangeLister() {
            @Override
            public void onVideoData(VideoTwoBean videoBean) {
                threeFragment.showVideoBean(videoBean);
            }
        });
    }
}

