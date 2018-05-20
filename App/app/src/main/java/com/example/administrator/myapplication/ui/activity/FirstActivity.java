package com.example.administrator.myapplication.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.bottimFragment.CareFragment;
import com.example.administrator.myapplication.ui.bottimFragment.GileFragment;
import com.example.administrator.myapplication.ui.bottimFragment.HomeFragment;
import com.example.administrator.myapplication.ui.bottimFragment.VideoFragment;


public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_home;
    private TextView tv_girl;
    private TextView tv_video;
    private TextView tv_care;
    FrameLayout fragment;
    HomeFragment homeFragment;
    GileFragment gileFragment;
    VideoFragment videoFragment;
    CareFragment careFragment;
    private FragmentManager supportFragmentManager;
    private ImageView iv_first;
    private TextView androidFile_master;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        homeFragment = new HomeFragment();
        initAnim();
        initData();
        initView();


        replaceFragment(homeFragment);
    }

    private void initAnim() {
        iv_first = (ImageView) findViewById(R.id.iv_first);
        androidFile_master = (TextView) findViewById(R.id.androidFile_master);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(androidFile_master, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(iv_first, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                iv_first.setVisibility(View.GONE);
                androidFile_master.setVisibility(View.GONE);
                fragment.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animatorSet.start();

    }


    private void initData() {
        homeFragment = new HomeFragment();
        gileFragment = new GileFragment();
        videoFragment = new VideoFragment();
        careFragment = new CareFragment();
    }

    private void initView() {
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_girl = (TextView) findViewById(R.id.tv_girl);
        tv_video = (TextView) findViewById(R.id.tv_video);
        tv_care = (TextView) findViewById(R.id.tv_care);
        fragment = (FrameLayout) findViewById(R.id.fragment);
        tv_home.setOnClickListener(this);
        tv_girl.setOnClickListener(this);
        tv_video.setOnClickListener(this);
        tv_care.setOnClickListener(this);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home:
                replaceFragment(homeFragment);
                break;
            case R.id.tv_girl:
                replaceFragment(gileFragment);
                break;

            case R.id.tv_video:
                replaceFragment(videoFragment);
                break;

            case R.id.tv_care:
                replaceFragment(careFragment);
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }
}
