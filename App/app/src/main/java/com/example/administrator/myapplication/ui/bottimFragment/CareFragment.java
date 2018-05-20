package com.example.administrator.myapplication.ui.bottimFragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.aboutactivity.AboutActivity;
import com.example.administrator.myapplication.ui.activity.FirstActivity;
import com.example.administrator.myapplication.ui.activity.ZoneActivity;
import com.example.administrator.myapplication.ui.waveview.WaveView;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CareFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private WaveView wave_view;
    private ImageView image;
    private LinearLayout zone;
    private LinearLayout togglebutton;
    private LinearLayout about;
    private ImageView add_img;
    private LinearLayout linerar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_care, container, false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        wave_view = (WaveView) view.findViewById(R.id.wave_view);
        image = (ImageView) view.findViewById(R.id.image);
        zone = (LinearLayout) view.findViewById(R.id.zone);
        togglebutton = (LinearLayout) view.findViewById(R.id.togglebutton);
        about = (LinearLayout) view.findViewById(R.id.about);
        add_img = (ImageView) view.findViewById(R.id.add_img);
        linerar = (LinearLayout) view.findViewById(R.id.linerar);
        add_img.setOnClickListener(this);
        linerar.setOnClickListener(this);
        togglebutton.setOnClickListener(this);
        about.setOnClickListener(this);
        zone.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zone:
                //好友动态
                startActivity(new Intent(getContext(), ZoneActivity.class));
                break;
            case R.id.togglebutton:
                //日夜切换
                SharedPreferences sp = getActivity().getSharedPreferences("daynitht_setting",MODE_PRIVATE);
                Boolean isnight = sp.getBoolean("night",false);
                if(isnight){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sp.edit().putBoolean("night",false).commit();
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sp.edit().putBoolean("night",true).commit();
                }
                getActivity().recreate();
                break;
            case R.id.about:
                //关于
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
            case R.id.add_img:
                //二维码
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            ((FirstActivity) getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            ((FirstActivity) getActivity()).recreate();
        }else {
            ((FirstActivity) getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            ((FirstActivity) getActivity()).recreate();
        }
    }
}
