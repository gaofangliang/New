package com.example.administrator.myapplication.ui.bottimFragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.adapter.GridViewAdapter;
import com.example.administrator.myapplication.ui.adapter.HomeFragmentAdapter;
import com.example.administrator.myapplication.ui.adapter.MyGridViewAdapter;
import com.example.administrator.myapplication.ui.fragment.AkFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private List<String> channel = new ArrayList<>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private HomeFragmentAdapter homeFragmentAdapter;
    private AkFragment akFragment;
    private TextView imageView;
    private ImageView img_arrow_back;
    private MyGridViewAdapter myGridViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private GridView grid_my_channel;
    private Toolbar toolBar;
    private List<String> urls = new ArrayList<>();
    private List<String> mList;
    private List<Fragment> mFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initDataShow();
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);

        imageView = (TextView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        toolBar = (Toolbar) view.findViewById(R.id.toolBar);
        toolBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                final PopupWindow popupWindow = new PopupWindow(getContext());
                //导入Pop 布局
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.activity_channel, null);
                popupWindow.setContentView(inflate);
                img_arrow_back = inflate.findViewById(R.id.img_arrow_back);
                //我的频道适配器
                grid_my_channel = inflate.findViewById(R.id.grid_my_channel);

                gridViewAdapter = new GridViewAdapter(getContext(), mList);
                grid_my_channel.setAdapter(gridViewAdapter);
                setMyGridLister(grid_my_channel);
                //跟多频道适配器
                GridView grid_channel = inflate.findViewById(R.id.grid_channel);
                myGridViewAdapter = new MyGridViewAdapter(getContext(), channel);
                grid_channel.setAdapter(myGridViewAdapter);
                setGridChangeLister(grid_channel);

                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setOutsideTouchable(true);//是否被点击
                popupWindow.setBackgroundDrawable(new BitmapDrawable());//设置背景
                popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);

                img_arrow_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragment.clear();
                        for (int i = 0; i < mList.size(); i++) {
                            akFragment = new AkFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("url", urls.get(i));
                            akFragment.setArguments(bundle);
                            mFragment.add(akFragment);
                        }
                        homeFragmentAdapter = new HomeFragmentAdapter(getChildFragmentManager(), mFragment, mList);
                        mViewPager.setAdapter(homeFragmentAdapter);
                        homeFragmentAdapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });
                break;
        }
    }

    //我的频道适配器点击事件
    private void setMyGridLister(GridView grid_my_channel) {
        grid_my_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= 4) {
                    return;
                }
                channel.add(mList.get(position));
                mList.remove(position);
                gridViewAdapter.notifyDataSetChanged();
                myGridViewAdapter.notifyDataSetChanged();
            }
        });
    }

    //跟多频道适配器点击事件
    private void setGridChangeLister(GridView grid_channel) {
        grid_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = channel.get(position);
                mList.add(s);
                channel.remove(position);
                gridViewAdapter.notifyDataSetChanged();
                myGridViewAdapter.notifyDataSetChanged();
            }
        });
    }

    //第一次添加Title
    private void initData() {

        mList = new ArrayList<>();
            mList.add("头条");
            mList.add("科技");
            mList.add("财经");
            mList.add("军事");
            mList.add("体育");

        if (urls.size() <= 0) {
            //头条接口：
            urls.add("headline/T1348647909107/0-20.html");
            //科技接口：
            urls.add("list/T1348648756099/0-20.html");
            //财经接口：
            urls.add("list/T1348648141035/0-20.html");
            //军事接口：
            urls.add("list/T1348649079062/0-20.html");
            //体育接口：
            urls.add("list/T1348649079062/0-20.html");
            //房产接口：
            urls.add("list/T1399700447917/0-20.html");
            //足球接口：
            urls.add("list/T1348648517839/0-20.html");
            //娱乐接口：
            urls.add("list/T1348648650048/0-20.html");
            //电影接口：
            urls.add("list/T1348654060988/0-20.html");
            //汽车接口：
            urls.add("list/T1350383429665/0-20.html");
            //笑话接口：
            urls.add("list/T1348654151579/0-20.html");
            // 游戏接口：
            urls.add("list/T1348650593803/0-20.html");
            //时尚接口：
            urls.add("list/T1348650839000/0-20.html");
            //情感接口：
            urls.add("list/T1370583240249/0-20.html");
            // 精选接口：
            urls.add("list/T1379038288239/0-20.html");
            //电台接口：
            urls.add("list/T1348649145984/0-20.html");
            // NBA接口：
            urls.add("list/T1348649776727/0-20.html");
            // 数码接口：
            urls.add("list/T1351233117091/0-20.html");
            //移动接口：
            urls.add("list/T1356600029035/0-20.html");
            //彩票接口：
            urls.add("list/T1348654225495/0-20.html");
            //教育接口：
            urls.add("list/T1349837670307/0-20.html");
            //论坛接口：
            urls.add("list/T1348654204705/0-20.html");
            //旅游接口：
            urls.add("list/T1348649654285/0-20.html");
            //手机接口：
            urls.add("list/T1349837698345/0-20.html");
            //博客接口：
            urls.add("list/T1348648037603/0-20.html");
            //社会接口：
            urls.add("list/T1348654105308/0-20.html");
            //家居接口：
            urls.add("list/T1397016069906/0-20.html");
            //暴雪接口：
            urls.add("list/T1397116135282/0-20.html");
            //亲子接口：
            urls.add("list/T1348649475931/0-20.html");
            //CBA接口：
            urls.add("list/T1371543208049/0-20.html");
        }
        mFragment = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            akFragment = new AkFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", urls.get(i));
            akFragment.setArguments(bundle);
            mFragment.add(akFragment);
        }
        homeFragmentAdapter = new HomeFragmentAdapter(getFragmentManager(), mFragment, mList);
        mViewPager.setAdapter(homeFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


//        homeFragmentAdapter.notifyDataSetChanged();
    }

    // 加号  添加 Title
    private void initDataShow() {
        channel.add("移动");
        channel.add("足球");
        channel.add("消息");
        channel.add("房产");
        channel.add("笑话");
        channel.add("暴雪");
        channel.add("时尚");
        channel.add("情感");
        channel.add("游戏");
        channel.add("彩票");
        channel.add("教育");
        channel.add("手机");
        channel.add("博客");
        channel.add("旅游");
        channel.add("亲子");
        channel.add("精选");
        channel.add("电台");
        channel.add("CBA");
        channel.add("论坛");
        channel.add("数码");
        channel.add("娱乐");
        channel.add("汽车");
        channel.add("家居");
        channel.add("电影");
        channel.add("NBA");
        channel.add("社会");
    }
}
