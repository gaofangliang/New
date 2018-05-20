package com.example.administrator.myapplication.ui.aboutactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;

public class AboutActivity extends AppCompatActivity{

    private ImageView bg_img;
    private ImageView img_Return;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {
        bg_img = (ImageView) findViewById(R.id.bg_img);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        finish();
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//    }
}
