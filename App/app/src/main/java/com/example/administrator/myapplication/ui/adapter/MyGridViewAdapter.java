package com.example.administrator.myapplication.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class MyGridViewAdapter extends BaseAdapter {
    Context context;
    private List<String> mList;
    public MyGridViewAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }
    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.mychannel_item,null);
            holder.btn_my_channel = convertView.findViewById(R.id.btn_my_channel);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.btn_my_channel.setText(mList.get(position));
        return convertView;
    }
    class ViewHolder{
        private TextView btn_my_channel;
    }
}
