package com.widle.coinscap;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

import com.widle.coinscap.Adapter.Exchange_Adapter;

public class More_Activity extends WearableActivity implements View.OnClickListener{

    private TextView tv_podcast, tv_toplist, tv_icolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        tv_podcast = (TextView) findViewById(R.id.tv_podcast);
        tv_toplist = (TextView) findViewById(R.id.tv_toplist);
        tv_icolist = (TextView) findViewById(R.id.tv_icolist);

        // Enables Always-on
        setAmbientEnabled();


        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_podcast.setTypeface(type1);
        tv_toplist.setTypeface(type1);
        tv_icolist.setTypeface(type1);


        tv_podcast.setOnClickListener(this);
        tv_toplist.setOnClickListener(this);
        tv_icolist.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.tv_exchange:
//                startActivity(new Intent(More_Activity.this, Exchange_Activity.class));
//                break;

            case R.id.tv_podcast:
                startActivity(new Intent(More_Activity.this, Event_Activity.class));
                break;

            case R.id.tv_toplist:
                startActivity(new Intent(More_Activity.this, Top_List_Activity.class));
                break;

            case R.id.tv_icolist:
                startActivity(new Intent(More_Activity.this, ICO_Activity.class));
                break;
        }
    }
}
