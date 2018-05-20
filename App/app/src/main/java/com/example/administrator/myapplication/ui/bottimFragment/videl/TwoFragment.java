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
import com.example.administrator.myapplication.data.bean.VideoTwoBean;
import com.example.administrator.myapplication.presenter.IPresenterImpl.PresenterVideoTwoImpl;
import com.example.administrator.myapplication.presenter.contract.IVoideoTwoContract;
import com.example.administrator.myapplication.ui.activity.FirstActivity;
import com.example.administrator.myapplication.ui.adapter.voideadapter.VoiderTwo_Adapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment implements IVoideoTwoContract.IContractView<IVoideoTwoContract.IContractPresenter>, OnRefreshListener, OnLoadmoreListener {
    IVoideoTwoContract.IContractPresenter presenter;
    private List<VideoTwoBean._$00850FRBBean> mList = new ArrayList<>();
    private RecyclerView video_two;
    private VoiderTwo_Adapter voiderTwo_adapter;
    private SmartRefreshLayout smart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        initView(view);
        presenter = new PresenterVideoTwoImpl(this);
        presenter.loading();
        return view;
    }

    @Override
    public void setPresenter(IVoideoTwoContract.IContractPresenter iContractPresenter) {
        presenter = iContractPresenter;
    }

    @Override
    public void showVideoBean(VideoTwoBean videoBean) {
        if (mList.size()<=0){
            mList.addAll(videoBean.get_$00850FRB());
        }
        FirstActivity activity = (FirstActivity) getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //voiderTwo_adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void Login() {

    }

    private void initView(View view) {
        video_two = (RecyclerView) view.findViewById(R.id.video_two);
        video_two.setLayoutManager(new LinearLayoutManager(getContext()));

        voiderTwo_adapter = new VoiderTwo_Adapter(getContext(), mList);
        video_two.setAdapter(voiderTwo_adapter);
        smart = (SmartRefreshLayout) view.findViewById(R.id.smart);
        smart.setOnRefreshListener(this);
        smart.setOnLoadmoreListener(this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        voiderTwo_adapter.notifyDataSetChanged();
        smart.finishRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        voiderTwo_adapter.notifyDataSetChanged();
        smart.finishLoadmore();
    }
}
