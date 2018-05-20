package com.example.administrator.myapplication.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

import java.text.SimpleDateFormat;

public class ZoneActivity extends AppCompatActivity {
    private String URL = "https://weibo.com/";
    private WebView webView;
    private TextView tv_time;
    private SimpleDateFormat sDateFormat;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);
        sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        date = sDateFormat.format(new    java.util.Date());
        initView();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(URL);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time.setText("当前访问时间："+date);
    }


}
