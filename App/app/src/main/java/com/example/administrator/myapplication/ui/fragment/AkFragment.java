package com.example.administrator.myapplication.ui.fragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.data.bean.Toutiao1;
import com.example.administrator.myapplication.data.utils.RetrofitUtils;
import com.example.administrator.myapplication.presenter.IPresenterImpl.PresenterImpl;
import com.example.administrator.myapplication.presenter.contract.IHomeContract;
import com.example.administrator.myapplication.ui.activity.bottomadapter.WebViewActivity;
import com.example.administrator.myapplication.ui.adapter.voideadapter.SectionAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkFragment extends Fragment implements IHomeContract.IContractView<IHomeContract.IContractPresenter>, View.OnClickListener, OnRefreshListener, OnLoadmoreListener {
    private List<Toutiao1.T1348647909107Bean> mHomeList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    IHomeContract.IContractPresenter presenter;
    private ImageView window_img;
    private SmartRefreshLayout refreshLayout;
    private SectionAdapter sectionAdapter;
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ak, container, false);
//        //通过 Bundle 传递诗句
//        Bundle arguments = getArguments();
//        url = arguments.getString("url");
//
//        new PresenterImpl(this);
//        presenter.loading(url);//发送到P层
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //悬浮窗控件 监听事件
        window_img = (ImageView) view.findViewById(R.id.window_img);
        window_img.setOnClickListener(this);
        //刷新动画
        if (mHomeList.size()!=0){
            refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
            refreshLayout.setOnRefreshListener(this);
            refreshLayout.setOnLoadmoreListener(this);
        }

    }

    //sectionAdapter 点击事件 WebView
    private void setLister(SectionAdapter sectionAdapter) {
        sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toutiao1.T1348647909107Bean toutiao1 = mHomeList.get(position);

                String webUrl = toutiao1.getUrl_3w();
                if (webUrl != null) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("webUrl", toutiao1);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "当前条目WEB为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    //滑动事件回到顶部
    private void setRecyclerScroll(RecyclerView mRecyclerView) {
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            //RecyclerView  滑动 事件 -1 表示 向上， 1 表示向下。
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(-1)) {
                } else if (!recyclerView.canScrollVertically(1)) {
                } else if (dy < 0) {
                    onScrolledUp();
                } else if (dy > 0) {
                    onScrolledDown();
                }
                super.onScrolled(recyclerView, dx, dy);
            }

            public void onScrolledUp() {
                View linearLayout = getActivity().findViewById(R.id.linearLayout);
                linearLayout.setVisibility(View.VISIBLE);
            }

            public void onScrolledDown() {
                View linearLayout = getActivity().findViewById(R.id.linearLayout);
                linearLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setPresenter(IHomeContract.IContractPresenter iContractPresenter) {
        presenter = iContractPresenter;
    }

    @Override
    public void showHomeBean(final Toutiao1 toutiao1) {
        if (mHomeList.size()<=0){
            mHomeList.addAll(toutiao1.getT1348647909107());
        }else {
           // Toast.makeText(getContext(), "数据加载完成等待显示...", Toast.LENGTH_SHORT).show();
        }
        //设置适配器
        setAdapter();
        AppCompatActivity compatActivity = (AppCompatActivity) getContext();
        if (compatActivity != null) {
            compatActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mHomeList.size() == 0) {
                        Toast.makeText(getContext(), "数据请求失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    /**
     * 点击悬浮窗回到顶部
     * 显示底部条目
     * 刷新适配器
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        mRecyclerView.scrollToPosition(0);
        View linearLayout = getActivity().findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(4000);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore(4000);
    }
    //滑动条目动画
    public void setScrollItem(SectionAdapter sectionAdapter){
        sectionAdapter.openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[]{
                        ObjectAnimator.ofFloat(view, "scaleY", 1, 0.8f, 1),
                        ObjectAnimator.ofFloat(view, "scaleX", 1, 0.8f, 1)
                };
            }
        });
        sectionAdapter.isFirstOnly(false);
    }

    public void setAdapter(){
        sectionAdapter = new SectionAdapter(R.layout.home_item, mHomeList);
        Log.e("22222",mHomeList.size()+"");
        //设置适配器
        mRecyclerView.setAdapter(sectionAdapter);
        //RecyclerView 条目动画
        sectionAdapter.openLoadAnimation();
        //RecyclerView 滑动事件方法//滑动事件回到顶部
        setRecyclerScroll(mRecyclerView);
        //滑动条目动画
        setScrollItem(sectionAdapter);
        //RecyclerView 点击事件方法WebView
        setLister(sectionAdapter);

    }
    public void setUserVisibleHint(boolean isVisibleToUser) {

        if (isVisibleToUser) {
            //通过 Bundle 传递诗句
            Bundle arguments = getArguments();
            url = arguments.getString("url");

            new PresenterImpl(this);
            presenter.loading(url);//发送到P层

        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
        //通过 Bundle 传递诗句
        Bundle arguments = getArguments();
        url = arguments.getString("url");
        new PresenterImpl(this);
        presenter.loading(url);//发送到P层
    }
}
