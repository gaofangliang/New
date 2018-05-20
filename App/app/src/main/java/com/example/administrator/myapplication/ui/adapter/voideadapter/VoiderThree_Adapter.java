package com.example.administrator.myapplication.ui.adapter.voideadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.data.bean.VideoThreeBean;
import com.example.administrator.myapplication.data.bean.VideoTwoBean;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class VoiderThree_Adapter extends RecyclerView.Adapter<VoiderThree_Adapter.Holder> {
    Context context;
    List<VideoThreeBean.V9LG4B3A0Bean> mList;

    public VoiderThree_Adapter(Context context, List<VideoThreeBean.V9LG4B3A0Bean> mList) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new Holder(view);
    }
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Log.e("asdfg",mList.get(position).getMp4_url());
        holder.videoplayer.setUp(mList.get(position).getMp4_url(),JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,mList.get(position).getTitle());
         holder.tv_playersize.setText(mList.get(position).getPlayersize()+"");
       Glide.with(context).load(mList.get(position).getTopicImg()).into(holder.iv_topicImg);
        if (mList.size()==0){
            Toast.makeText(context, "数据加载缓慢请稍后在试...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        private JZVideoPlayerStandard videoplayer;
        private TextView tv_playersize;
        private ImageView iv_topicImg;
        public Holder(View itemView) {
            super(itemView);
            videoplayer = itemView.findViewById(R.id.videoplayer);
            tv_playersize = itemView.findViewById(R.id.tv_playersize);
            iv_topicImg = itemView.findViewById(R.id.iv_topicImg);

        }
    }

}
