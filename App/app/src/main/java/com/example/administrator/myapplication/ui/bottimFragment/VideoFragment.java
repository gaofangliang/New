package com.example.administrator.myapplication.ui.bottimFragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.activity.bottomadapter.Video_adapter;
import com.example.administrator.myapplication.ui.bottimFragment.videl.ThreeFragment;
import com.example.administrator.myapplication.ui.bottimFragment.videl.TwoFragment;
import com.example.administrator.myapplication.ui.bottimFragment.videl.VideoFragm;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    private TabLayout video_tabLayout;
    private ViewPager video_viewPager;
    List<Fragment> mFragment = new ArrayList<>();
    List<String> mList = new ArrayList<>();
    private Video_adapter video_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        initData();
        initView(view);
        return view;
    }
    private void initData() {
            mList.add("热点");
            mList.add("搞笑");
            mList.add("娱乐");
        VideoFragm videoFragm = new VideoFragm();
        TwoFragment twoFragment = new TwoFragment();
        ThreeFragment threeFragment = new ThreeFragment();
        if (mFragment.size()<=0){
            mFragment.add(videoFragm);
            mFragment.add(twoFragment);
            mFragment.add(threeFragment);
        }

    }

    private void initView(View view) {
        video_tabLayout = (TabLayout) view.findViewById(R.id.video_tabLayout);
        video_viewPager = (ViewPager) view.findViewById(R.id.video_viewPager);
        video_tabLayout.setupWithViewPager(video_viewPager);
        video_adapter = new Video_adapter(getFragmentManager(), mFragment, mList);
        video_viewPager.setAdapter(video_adapter);
    }
}
