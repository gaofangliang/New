package com.example.administrator.myapplication.presenter.contract;

import com.example.administrator.myapplication.data.bean.Toutiao1;
import com.example.administrator.myapplication.presenter.BasePresenter;
import com.example.administrator.myapplication.ui.BaseView;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public interface IHomeContract {
    //P层接口
    interface IContractPresenter extends BasePresenter{
        void loading(String url);
    }
    //V层接口
    interface IContractView<IContractPresenter> extends BaseView<IContractPresenter>{
        void showHomeBean(Toutiao1 toutiao1);
    }
}
