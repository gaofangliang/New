package com.example.administrator.myapplication.ui.activity.bottomadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class Video_adapter extends FragmentPagerAdapter {

    List<Fragment> mFragment;
    List<String> mList;
    public Video_adapter(FragmentManager fragmentManager, List<Fragment> mFragment, List<String> mList) {
        super(fragmentManager);
        this.mFragment = mFragment;
        this.mList = mList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size()==0?0:mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}
