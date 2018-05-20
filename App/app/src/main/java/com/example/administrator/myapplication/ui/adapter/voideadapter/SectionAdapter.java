package com.example.administrator.myapplication.ui.adapter.voideadapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.data.bean.Toutiao1;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class SectionAdapter extends BaseQuickAdapter<Toutiao1.T1348647909107Bean , BaseViewHolder> {
    public SectionAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Toutiao1.T1348647909107Bean  item) {
            helper.setText(R.id.tv_ltitle,item.getTitle());
            helper.setText(R.id.tv_digest,item.getDigest());
            helper.setText(R.id.tv_ptime,item.getPtime());
        Glide.with(mContext).load(item.getImgsrc()).crossFade().into((ImageView)helper.getView(R.id.home_img));
    }
}