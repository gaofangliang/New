package com.example.administrator.myapplication.presenter.IPresenterImpl;

import com.example.administrator.myapplication.data.bean.VideoBean;
import com.example.administrator.myapplication.data.model.IVideoModel;
import com.example.administrator.myapplication.data.modelimpl.IVideoModelImpl;
import com.example.administrator.myapplication.presenter.contract.IVoideoContract;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class PresenterVideoImpl implements IVoideoContract.IContractPresenter {
    IVoideoContract.IContractView contexts;
    private IVideoModelImpl iVideoModel;

    public PresenterVideoImpl(IVoideoContract.IContractView contexts) {
        this.contexts = contexts;
        contexts.setPresenter(this);
        //创建M层对象
        iVideoModel = new IVideoModelImpl();
    }
    @Override
    public void loading() {
        iVideoModel.requestVideoData(new IVideoModel.OnVideoDataChangeLister() {
            @Override
            public void onVideoData(VideoBean videoBean) {
                contexts.showVideoBean(videoBean);
            }
        });
    }
}

