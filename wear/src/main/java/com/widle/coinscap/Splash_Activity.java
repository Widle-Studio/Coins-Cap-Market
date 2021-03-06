package com.widle.coinscap;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.widle.coinscap.R;

public class Splash_Activity extends WearableActivity {

    private TextView tv_app_name;

    private ImageView iv_gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv_app_name = (TextView) findViewById(R.id.tv_app_name);

        Glide.with(this)
                .load(R.drawable.bgg)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into((ImageView)findViewById(R.id.iv_gif));

//        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_app_name.setTypeface(type);

        // Enables Always-on
        setAmbientEnabled();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_Activity.this, Home_Activity.class));
                finish();
            }
        }, 2500);
    }
}
