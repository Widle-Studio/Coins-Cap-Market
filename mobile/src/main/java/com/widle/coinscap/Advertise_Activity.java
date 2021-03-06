package com.widle.coinscap;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.widle.coinscap.Utils.utils;

public class Advertise_Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;

    private TextView tv_title, tv_lbl;

    private WebView webview;

    private String url = "", title;

    private LinearLayout ll_point_found, ll_point_not_found;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");

        init();

    }

    public void init() {

        iv_back = (ImageView) findViewById(R.id.iv_back);
        webview = (WebView) findViewById(R.id.webview);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_lbl = (TextView) findViewById(R.id.tv_lbl);
        ll_point_found = (LinearLayout) findViewById(R.id.ll_point_found);
        ll_point_not_found = (LinearLayout) findViewById(R.id.ll_point_not_found);

        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        tv_title.setTypeface(type);
        tv_lbl.setTypeface(type1);

        tv_title.setText(title);

        if (utils.isNetworkAvailable(Advertise_Activity.this)) {
            ll_point_found.setVisibility(View.VISIBLE);
            ll_point_not_found.setVisibility(View.GONE);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setWebViewClient(new WebViewClient());
            webview.loadUrl(url);

        }else {
            ll_point_found.setVisibility(View.GONE);
            ll_point_not_found.setVisibility(View.VISIBLE);
            tv_lbl.setText(R.string.err_no_internet);
        }

        iv_back.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
              finish();
                break;
        }
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(this, Home_Activity.class);
//        startActivity(intent);
//        finish();
//    }
}
