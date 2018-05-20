package com.example.administrator.myapplication.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class GridViewAdapter extends BaseAdapter {
    Context context;
    private List<String> mList;
    private int hidePosition = AdapterView.INVALID_POSITION;
    public GridViewAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView btn_my_channel;
       // ViewHolder holder;
        if (null == convertView) {
            btn_my_channel = new TextView(context);
//            holder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.mychannel_item, null);
//            holder.btn_my_channel = convertView.findViewById(R.id.btn_my_channel);
//            convertView.setTag(holder);
        } else {
            btn_my_channel = (TextView)convertView;
//            holder = (ViewHolder) convertView.getTag();
        }
        if (position != hidePosition){
            btn_my_channel.setText(mList.get(position));
        }else {
            btn_my_channel.setText("");
        }
        btn_my_channel.setId(position);
//        holder.btn_my_channel.setText(mList.get(position));
        return btn_my_channel;
    }
    public void hideView(int pos) {
        hidePosition = pos;
        notifyDataSetChanged();
    }
    public void showHideView() {
        hidePosition = AdapterView.INVALID_POSITION;
        notifyDataSetChanged();
    }
    public void removeView(int pos) {
        mList.remove(pos);
        notifyDataSetChanged();
    }
    //更新拖动时的gridView
    public void swapView(int draggedPos, int destPos) {
        //从前向后拖动，其他item依次前移
        if(draggedPos < destPos) {
            mList.add(destPos+1, (String) getItem(draggedPos));
            mList.remove(draggedPos);
        }
        //从后向前拖动，其他item依次后移
        else if(draggedPos > destPos) {
            mList.add(destPos, (String) getItem(draggedPos));
            mList.remove(draggedPos+1);
        }
        hidePosition = destPos;
        notifyDataSetChanged();
    }
//    class ViewHolder {
//        private TextView btn_my_channel;
//    }
}
