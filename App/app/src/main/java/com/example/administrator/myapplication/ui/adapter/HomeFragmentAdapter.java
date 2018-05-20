package com.example.administrator.myapplication.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.administrator.myapplication.base.GreenBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragment;
    List<String> mList = new ArrayList<>();
    public HomeFragmentAdapter(FragmentManager fm, List<Fragment> mFragment, List<String> mList) {
        super(fm);
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

    /**
     *   主要是给tablayout 设置item 标签
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {

        return mList.get(position);
    }

}
