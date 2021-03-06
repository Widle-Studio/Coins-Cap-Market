package com.widle.coinscap;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class News_Details_Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back, iv_setting, iv_bg;

    private TextView tv_app_name, tv_title, tv_desc, tv_url;

    PopupMenu popupmenu;

    private String title, imageurl, url, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        title = getIntent().getStringExtra("title");
        imageurl = getIntent().getStringExtra("imageurl");
        url = getIntent().getStringExtra("url");
        body = getIntent().getStringExtra("body");

        init();
    }

    public void init(){
        iv_back = findViewById(R.id.iv_back);
        iv_setting = findViewById(R.id.iv_setting);
        iv_bg = findViewById(R.id.iv_bg);
        tv_app_name = findViewById(R.id.tv_app_name);
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_desc);
        tv_url = findViewById(R.id.tv_url);

        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_app_name.setTypeface(type2);
        tv_title.setTypeface(type);
        tv_desc.setTypeface(type2);
        tv_url.setTypeface(type1);

        tv_app_name.setText(title);
        tv_title.setText(title);
        tv_desc.setText(body);
        tv_url.setText(url);
        Glide.with(this)
                .load(imageurl)
                .into(iv_bg);



        iv_setting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popupmenu = new PopupMenu(News_Details_Activity.this, view);
                PopMenuDisplay();
                return false;
            }
        });

        iv_back.setOnClickListener(this);
        tv_url.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_url:
                Intent intent = new Intent(this, Advertise_Activity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "News");
                startActivity(intent);
                break;
        }
    }


    public void PopMenuDisplay() {
        popupmenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about:
                        Intent intent = new Intent(News_Details_Activity.this,Acknowledgement_Activity.class);
                        intent.putExtra("type", "1");
                        startActivity(intent);
                        return true;

                    case R.id.help:
                        Intent i1 = new Intent(News_Details_Activity.this, Acknowledgement_Activity.class);
                        i1.putExtra("type", "2");
                        startActivity(i1);
                        return true;

                    case R.id.acknowledgement:
                        Intent i2 = new Intent(News_Details_Activity.this, Acknowledgement1_Activity.class);
                        startActivity(i2);
                        return true;

                    case R.id.privacy:
                        Intent i3 = new Intent(News_Details_Activity.this, Acknowledgement_Activity.class);
                        i3.putExtra("type", "4");
                        startActivity(i3);
                        return true;

                    case R.id.terms:
                        Intent i4 = new Intent(News_Details_Activity.this, Acknowledgement_Activity.class);
                        i4.putExtra("type", "5");
                        startActivity(i4);
                        return true;

                    case R.id.cookie:
                        Intent i5 = new Intent(News_Details_Activity.this, Acknowledgement_Activity.class);
                        i5.putExtra("type", "6");
                        startActivity(i5);
                        return true;

                    case R.id.rate:
                        Intent i6 = new Intent(News_Details_Activity.this, Acknowledgement_Activity.class);
                        i6.putExtra("type", "7");
                        startActivity(i6);
                        return true;

                    case R.id.setting:
                        Intent i7 = new Intent(News_Details_Activity.this, Setting_Activity.class);
                        startActivity(i7);
                        return true;
                }
                return false;
            }
        });
        popupmenu.show();
    }

}
