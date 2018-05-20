package com.example.administrator.myapplication.presenter.contract;

import com.example.administrator.myapplication.data.bean.VideoBean;
import com.example.administrator.myapplication.presenter.BasePresenter;
import com.example.administrator.myapplication.ui.BaseView;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public interface IVoideoContract {
    //P层接口
    interface IContractPresenter extends BasePresenter {
        void loading();
    }

    //V层接口
    interface IContractView<IContractPresenter> extends BaseView<IContractPresenter> {
        void showVideoBean(VideoBean videoBean);
    }
}
