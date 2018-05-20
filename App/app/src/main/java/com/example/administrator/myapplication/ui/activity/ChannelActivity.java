package com.example.administrator.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.bottimFragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class ChannelActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_arrow_back;


    private List<String> channel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        initData();
        initView();

    }

    private void initData() {
        channel.add("移动");channel.add("足球");channel.add("消息");channel.add("房产");channel.add("笑话");channel.add("暴雪");
        channel.add("时尚");channel.add("情感");channel.add("游戏");channel.add("彩票");channel.add("教育");
        channel.add("手机");channel.add("博客");channel.add("旅游");channel.add("亲子");channel.add("精选");
        channel.add("电台");channel.add("CBA");channel.add("论坛");channel.add("数码");channel.add("娱乐");
        channel.add("汽车");channel.add("家居");channel.add("电影");channel.add("NBA");channel.add("社会");
    }

    private void initView() {
        img_arrow_back = (ImageView) findViewById(R.id.img_arrow_back);
        //Toolbar 上的点击事件
        img_arrow_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        finish();
    }

}
