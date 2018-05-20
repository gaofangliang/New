package com.example.administrator.myapplication.presenter.IPresenterImpl;

import com.example.administrator.myapplication.data.bean.Toutiao1;
import com.example.administrator.myapplication.data.model.IModel;
import com.example.administrator.myapplication.data.modelimpl.IModelImpl;
import com.example.administrator.myapplication.presenter.contract.IHomeContract;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class PresenterImpl implements IHomeContract.IContractPresenter {
    IHomeContract.IContractView iContractView;
    private  IModelImpl model;

    public PresenterImpl(IHomeContract.IContractView iContractView) {
        this.iContractView = iContractView;
        iContractView.setPresenter(this);
        //创建M层对象
        model = new IModelImpl();
    }
    @Override
    public void loading(String url) {

       model.requestData(url,new IModel.OnHomeDataChangeLister() {
           @Override
           public void onHomeData(Toutiao1 toutiao1) {
               iContractView.showHomeBean(toutiao1);
           }
       });
    }
}

