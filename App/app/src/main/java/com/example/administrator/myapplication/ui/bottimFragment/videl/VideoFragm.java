package com.example.administrator.myapplication.ui.bottimFragment.videl;


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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.data.bean.VideoBean;
import com.example.administrator.myapplication.presenter.IPresenterImpl.PresenterVideoImpl;
import com.example.administrator.myapplication.presenter.contract.IVoideoContract;
import com.example.administrator.myapplication.ui.adapter.voideadapter.Voider_Adapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragm extends Fragment implements IVoideoContract.IContractView<IVoideoContract.IContractPresenter>, View.OnClickListener, OnRefreshListener, OnLoadmoreListener {
    private RecyclerView video_recycler;
    IVoideoContract.IContractPresenter presenter;
    private List<VideoBean.V9LG4E6VRBean> mList = new ArrayList();

    private Voider_Adapter voider_adapter;
    private ImageView window_img;
    private SmartRefreshLayout smart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video2, container, false);
        initView(view);
        presenter = new PresenterVideoImpl(this);
        presenter.loading();
        return view;
    }

    private void initView(View view) {
        video_recycler = (RecyclerView) view.findViewById(R.id.video_recycler);
        video_recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        window_img = (ImageView) view.findViewById(R.id.window_img);
        window_img.setOnClickListener(this);
        if (mList.size()!=0){
            smart = (SmartRefreshLayout) view.findViewById(R.id.smart);
            smart.setOnRefreshListener(this);
            smart.setOnLoadmoreListener(this);
        }
    }

    private void setRecyclerScroll(RecyclerView video_recycler) {
        video_recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
    public void setPresenter(IVoideoContract.IContractPresenter iContractPresenter) {
        presenter = iContractPresenter;
    }

    @Override
    public void showVideoBean(VideoBean videoBean) {
        AppCompatActivity compatActivity = (AppCompatActivity) getContext();
        if (mList.size()<=0){
            mList.addAll(videoBean.getV9LG4E6VR());
        }
        voider_adapter = new Voider_Adapter(getContext(), mList);
        video_recycler.setAdapter(voider_adapter);
        //RecyclerView 滑动事件方法
        setRecyclerScroll(video_recycler);
        Log.e("111",mList.size()+"");

        compatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // voider_adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        video_recycler.scrollToPosition(0);
        View linearLayout = getActivity().findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.VISIBLE);
        voider_adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        voider_adapter.notifyDataSetChanged();
        smart.finishRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        smart.finishLoadmore();
    }
}
