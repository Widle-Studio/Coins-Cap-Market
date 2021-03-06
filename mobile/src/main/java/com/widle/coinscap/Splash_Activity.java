package com.widle.coinscap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.utils;


public class Splash_Activity extends AppCompatActivity {

    private TextView tv_name;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(Splash_Activity.this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        preferences = this.getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);

        Glide.with(this)
                .load(R.drawable.bg)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into((ImageView)findViewById(R.id.iv_gif));

        tv_name = (TextView) findViewById(R.id.tv_name);

        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_name.setTypeface(type);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                device_id = preferences.getString(General.PREFS_Device_id, "");
                if (utils.isNetworkAvailable(Splash_Activity.this)) {
//                    new apiUpdatedata().execute();
                    startActivity(new Intent(Splash_Activity.this, Coins_Activity.class));
                    finish();
                } else {
                    utils.showAlertMessage(Splash_Activity.this, getString(R.string.err_no_internet));
                }
            }
        }, 4500);
    }

}
