package com.widle.coinscap;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

public class ICO_Activity extends WearableActivity implements View.OnClickListener{

    private TextView tv_live, tv_upcoming, tv_finished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ico);

        tv_live = (TextView) findViewById(R.id.tv_live);
        tv_upcoming = (TextView) findViewById(R.id.tv_upcoming);
        tv_finished = (TextView) findViewById(R.id.tv_finished);

        // Enables Always-on
        setAmbientEnabled();

        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_live.setTypeface(type2);
        tv_upcoming.setTypeface(type2);
        tv_finished.setTypeface(type2);

        tv_live.setOnClickListener(this);
        tv_upcoming.setOnClickListener(this);
        tv_finished.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(ICO_Activity.this, ICO1_Activity.class);

        switch (view.getId()){
            case R.id.tv_live:
                intent.putExtra("type", "live");
                startActivity(intent);
                break;

            case R.id.tv_upcoming:
                intent.putExtra("type", "upcoming");
                startActivity(intent);
                break;

            case R.id.tv_finished:
                intent.putExtra("type", "finished");
                startActivity(intent);
                break;
        }
    }
}
