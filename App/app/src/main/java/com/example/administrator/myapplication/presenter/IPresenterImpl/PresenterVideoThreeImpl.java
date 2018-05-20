package com.example.administrator.myapplication.presenter.IPresenterImpl;

import com.example.administrator.myapplication.data.bean.VideoThreeBean;
import com.example.administrator.myapplication.data.bean.VideoTwoBean;
import com.example.administrator.myapplication.data.model.IVideoThreeModel;
import com.example.administrator.myapplication.data.model.IVideoTwoModel;
import com.example.administrator.myapplication.data.modelimpl.IVideoThreeModelImpl;
import com.example.administrator.myapplication.data.modelimpl.IVideoTwoModelImpl;
import com.example.administrator.myapplication.presenter.contract.IVoideoThreeContract;
import com.example.administrator.myapplication.presenter.contract.IVoideoTwoContract;
import com.example.administrator.myapplication.ui.bottimFragment.videl.ThreeFragment;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class PresenterVideoThreeImpl implements IVoideoThreeContract.IContractPresenter {
    IVoideoThreeContract.IContractView threeFragment;
    private final IVideoThreeModelImpl model;

    public PresenterVideoThreeImpl(IVoideoThreeContract.IContractView threeFragment) {
        this.threeFragment = threeFragment;
        threeFragment.setPresenter(this);
        model = new IVideoThreeModelImpl();
    }

    @Override
    public void loading() {
        threeFragment.Login();
        model.requestVideoData(new IVideoThreeModel.OnVideoDataChangeLister() {
            @Override
            public void onVideoData(VideoThreeBean videoThreeBean) {
                threeFragment.showVideoBean(videoThreeBean);
            }
        });
    }
}

