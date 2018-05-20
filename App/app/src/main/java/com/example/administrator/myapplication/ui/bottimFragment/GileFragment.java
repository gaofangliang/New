package com.example.administrator.myapplication.ui.bottimFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.data.bean.GirlBean;
import com.example.administrator.myapplication.ui.activity.FirstActivity;
import com.example.administrator.myapplication.ui.adapter.Girl_RecyclerView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GileFragment extends Fragment implements OnRefreshListener, OnLoadmoreListener {
    private RecyclerView girl_Recycler;
    private List<Integer> mImageView = new ArrayList<>();
    private List<GirlBean.ResultsBean> mListImage = new ArrayList<>();
    private int[] img;
    private String URL = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1";
    private SmartRefreshLayout smart;
    private Girl_RecyclerView girl_recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gile, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(URL).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                GirlBean girlBean = gson.fromJson(string, GirlBean.class);
                final List<GirlBean.ResultsBean> results = girlBean.getResults();
                if (results != null) {
                    FirstActivity activity = (FirstActivity) getActivity();
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListImage.addAll(results);
                                girl_recyclerView = new Girl_RecyclerView(mListImage);
                                girl_Recycler.setAdapter(girl_recyclerView);
                            }
                        });
                    } else {
                        // Toast.makeText(getContext(), "数据为空", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "数据为空", Toast.LENGTH_SHORT).show();
                }

            }
        });


//        img = new int[]{
//                R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g,
//                R.mipmap.h, R.mipmap.i, R.mipmap.j, R.mipmap.b, R.mipmap.o, R.mipmap.p, R.mipmap.q, R.mipmap.s,
//        };
//        for (int i = 0; i < img.length; i++) {
//            mImageView.add(img[i]);
//        }

    }

    private void initView(View view) {
        girl_Recycler = (RecyclerView) view.findViewById(R.id.girl_Recycler);
        girl_Recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        Girl_RecyclerView girl_recyclerView = new Girl_RecyclerView(mImageView);
        if (mListImage.size() != 0) {
            smart = (SmartRefreshLayout) view.findViewById(R.id.smart);
            smart.setOnRefreshListener(this);
            smart.setOnLoadmoreListener(this);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

        girl_recyclerView.notifyDataSetChanged();
        smart.finishRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        initData();
        girl_recyclerView.notifyDataSetChanged();
        smart.finishLoadmore();
    }
}
