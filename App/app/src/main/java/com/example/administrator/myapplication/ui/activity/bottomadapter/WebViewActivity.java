package com.example.administrator.myapplication.ui.activity.bottomadapter;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.data.bean.Toutiao1;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.io.Serializable;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener, UMShareListener {

    private WebView web_webView;
    private String url;
    private ImageView bg_img;
    private Toolbar toolbar;
    private CollapsingToolbarLayout coll;
    private String title;
    private String imgsrc;
    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
            Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.WRITE_APN_SETTINGS};
    private FloatingActionButton sher;
    private ImageView img_Return;
    private Toutiao1.T1348647909107Bean webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        webUrl = (Toutiao1.T1348647909107Bean) intent.getSerializableExtra("webUrl");
        webUrl.getUrl_3w();
        webUrl.getUrl();
        url = webUrl.getUrl();
        title = webUrl.getTitle();
        imgsrc = webUrl.getImgsrc();
        initView();
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
        setData();
    }

    private void initView() {
        web_webView = (WebView) findViewById(R.id.web_webView);
        bg_img = (ImageView) findViewById(R.id.bg_img);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        coll = (CollapsingToolbarLayout) findViewById(R.id.coll);
        setSupportActionBar(toolbar);
        sher = (FloatingActionButton) findViewById(R.id.sher);
        sher.setOnClickListener(this);
        img_Return = (ImageView) findViewById(R.id.img_Return);
        img_Return.setOnClickListener(this);
    }

    private void setData() {
        web_webView.loadUrl(url);
        web_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        Glide.with(this).load(imgsrc).into(bg_img);
        setSupportActionBar(toolbar);
        coll.setTitle(title);
        coll.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sher:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, webUrl);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "分享"));
                break;
            case R.id.img_Return:
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }


    @Override
    public void onStart(SHARE_MEDIA share_media) {
        Toast.makeText(this, "分享开始", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        Toast.makeText(this, "分享结果", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        Toast.makeText(this, "分享错误", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        Toast.makeText(this, "取消分享", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.meun, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_webView:
                Toast.makeText(this, "WebView", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_internet:
                Intent intent = getIntent();
                //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                startActivity(intent);
                break;
        }
        return true;
    }
}
