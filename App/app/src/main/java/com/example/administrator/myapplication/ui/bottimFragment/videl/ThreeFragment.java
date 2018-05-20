package com.example.administrator.myapplication.ui.bottimFragment.videl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.data.bean.VideoThreeBean;
import com.example.administrator.myapplication.presenter.IPresenterImpl.PresenterVideoThreeImpl;
import com.example.administrator.myapplication.presenter.contract.IVoideoThreeContract;
import com.example.administrator.myapplication.ui.activity.FirstActivity;
import com.example.administrator.myapplication.ui.adapter.voideadapter.VoiderThree_Adapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment implements IVoideoThreeContract.IContractView<IVoideoThreeContract.IContractPresenter>, OnRefreshListener, OnLoadmoreListener {
    private RecyclerView video_three;
    private List<VideoThreeBean.V9LG4B3A0Bean> mList = new ArrayList<>();
    IVoideoThreeContract.IContractPresenter presenter;
    private VoiderThree_Adapter adapter;
    private SmartRefreshLayout smart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        initView(view);
        presenter = new PresenterVideoThreeImpl(this);
        presenter.loading();
        return view;
    }

    private void initView(View view) {
        video_three = (RecyclerView) view.findViewById(R.id.video_three);
        video_three.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VoiderThree_Adapter(getContext(), mList);
        video_three.setAdapter(adapter);

        smart = (SmartRefreshLayout) view.findViewById(R.id.smart);
        smart.setOnRefreshListener(this);
        smart.setOnLoadmoreListener(this);
    }

    @Override
    public void setPresenter(IVoideoThreeContract.IContractPresenter iContractPresenter) {
        presenter = iContractPresenter;
    }

    @Override
    public void showVideoBean(VideoThreeBean videoThreeBean) {
        if (mList.size() <= 0) {
            mList.addAll(videoThreeBean.getV9LG4B3A0());
        }
        FirstActivity activity = (FirstActivity) getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    @Override
    public void Login() {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        adapter.notifyDataSetChanged();
        smart.finishRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        adapter.notifyDataSetChanged();
        smart.finishLoadmore();
    }
}
