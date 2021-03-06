package com.widle.coinscap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import com.widle.coinscap.Utils.General;

public class ICO_Link_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;

    private TextView tv_app_name;

    SharedPreferences preferences;

    public ProgressDialog mProgressDialog;

    private WebView webview;

    private String name = "", link = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ico_link);

        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        name = getIntent().getStringExtra("name");
        link = getIntent().getStringExtra("link");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        init();

    }

    public void init() {

        iv_back = findViewById(R.id.iv_back);
        tv_app_name = findViewById(R.id.tv_app_name);
        webview = findViewById(R.id.webview);

        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_app_name.setTypeface(type2);

        tv_app_name.setText(name);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(link);

        iv_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }


}
