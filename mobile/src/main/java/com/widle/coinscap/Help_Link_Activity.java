package com.widle.coinscap;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Help_Link_Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;

    private TextView tv_title;

    private WebView webview;

    private String typee = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        typee = getIntent().getStringExtra("type");

        init();

    }

    public void init() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        webview = (WebView) findViewById(R.id.webview);
        tv_title = (TextView) findViewById(R.id.tv_title);


        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_title.setTypeface(type2);

        if (typee.equals("1")) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSfqzr2w2tJtetd9pReQK7vaOfHZalwDEitjYUblCvGJbZkiSQ/viewform");
            tv_title.setText("Coin");
        } else if (typee.equals("2")) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdMohfxERZyvzmr-tTUBhJ-N3LYCxDXbzpGQ8k9GC5tNXHLnw/viewform#start=openform");
            tv_title.setText("ICOs");
        } else if (typee.equals("3")) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSe07aY-WGfOdiIheAfWw88g3LwBTqycKl4J5z7BBF5wvSw0nw/viewform");
            tv_title.setText("ICO Listing");
        } else if (typee.equals("4")) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSc3BWJYBrLulf4dpSdjzrogBkjQA9JLFeA-cHKp0V1ZvYLYqA/viewform");
            tv_title.setText("Mining Equipment");
        } else if (typee.equals("5")) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSekAy6ZApTPEVyKicvH0VbQKifPGbgxAwwXAiD1iBr_spZ0bw/viewform");
            tv_title.setText("Mining Pool");
        } else if (typee.equals("6")) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSceruLX5iVS6YjPgYbPpW0Lhbrg8-lwIQ-AXroO3br-um83og/viewform");
            tv_title.setText("Wallet");
        }

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
