package com.example.administrator.myapplication.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.data.bean.GirlBean;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by Administrator on 2018/5/13 0013.
 */

public class Girl_RecyclerView extends RecyclerView.Adapter<Girl_RecyclerView.Holder> {
    Context context;
    List<GirlBean.ResultsBean> mImageView;
//    List<Integer> mImageBay= new ArrayList<>();
//    public Girl_RecyclerView(List<Integer> mImageView) {
//
//        this.mImageView = mImageView;
//    }
    public Girl_RecyclerView(List<GirlBean.ResultsBean> mListImage) {
        this.mImageView = mListImage;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.girl_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Glide.with(context).load(mImageView.get(position).getUrl()).into(holder.girl_img);
        holder.girl_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final PopupWindow popupWindow = new PopupWindow(context);
                //导入Pop 布局
                View inflate = LayoutInflater.from(context).inflate(R.layout.gile_pop, null);
                PhotoView pop_img = inflate.findViewById(R.id.pop_img);
                popupWindow.setContentView(inflate);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setOutsideTouchable(true);//是否被点击
                popupWindow.setBackgroundDrawable(new BitmapDrawable());//设置背景
                Glide.with(context).load(mImageView.get(position).getUrl()).into(pop_img);
                popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
                pop_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageView.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView girl_img;

        public Holder(View itemView) {
            super(itemView);
            girl_img = itemView.findViewById(R.id.girl_img);

        }
    }

    public interface OnClickLister {
        void onLister();
    }

    OnClickLister onClickLister;

    public void setOnClickLister(OnClickLister onClickLister) {
        this.onClickLister = onClickLister;
    }
}
