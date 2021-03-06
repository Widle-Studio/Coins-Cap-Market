package com.widle.coinscap;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.widle.coinscap.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends WearableActivity implements View.OnClickListener{


    private ImageView iv_coin, iv_fav, iv_podcast, iv_setting;

    ArrayList<String> stringList = new ArrayList<>();

    Set<String> myScores = new HashSet<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_coin = findViewById(R.id.iv_coin);
//        iv_fav = findViewById(R.id.iv_fav);
        iv_podcast = findViewById(R.id.iv_podcast);
        iv_setting = findViewById(R.id.iv_setting);

        iv_coin.setOnClickListener(this);
//        iv_fav.setOnClickListener(this);
        iv_podcast.setOnClickListener(this);
        iv_setting.setOnClickListener(this);


        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_coin:
                startActivity(new Intent(MainActivity.this, Home_Activity.class));
                break;

//            case R.id.iv_fav:
//                Intent i = new Intent(MainActivity.this, Favourite_Activity.class);
//                i.putStringArrayListExtra("arraylist", (ArrayList<String>) stringList);
//                i.putStringArrayListExtra("namearraylist", (ArrayList<String>) stringList);
//                startActivity(i);
//                break;

            case R.id.iv_podcast:
                startActivity(new Intent(MainActivity.this, Podcast_Activity.class));
                break;

            case R.id.iv_setting:
                break;
        }
    }
}
