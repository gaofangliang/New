package com.example.administrator.myapplication.ui;

/**
 * Created by Administrator on 2018/5/12 0012.
 */
//V层 跟接口
public interface BaseView<T> {
    void setPresenter(T t);
}
