package com.widle.coinscap;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.widle.coinscap.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Event_DetailsActivity extends Activity {

    private TextView tv_coin, tv_date, tv_title, tv_desc;

    private String coin, date, title, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        coin = getIntent().getStringExtra("coin");
        date = getIntent().getStringExtra("date");
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("description");

        init();

    }

    public void init(){
        tv_coin = findViewById(R.id.tv_coin);
        tv_date = findViewById(R.id.tv_date);
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_desc);

        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_coin.setTypeface(type1);
        tv_date.setTypeface(type1);
        tv_title.setTypeface(type1);
        tv_desc.setTypeface(type2);

        tv_coin.setText(coin);
        tv_title.setText(title);
        tv_desc.setText(desc);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        Date result;
        try {
            result = df.parse(date);
            System.out.println("date:"+result); //prints date in current locale
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println(sdf.format(result)); //prints date in the format sdf
            tv_date.setText(sdf.format(result));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
