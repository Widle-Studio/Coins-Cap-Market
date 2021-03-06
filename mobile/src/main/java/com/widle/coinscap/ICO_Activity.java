package com.widle.coinscap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import com.widle.coinscap.Adapter.ICO_ViewPagerAdapter;
import com.widle.coinscap.Adapter.ViewPagerAdapter;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.utils;

public class ICO_Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;

    private TextView tv_app_name;

    private String pro_pic = "";

    SharedPreferences preferences;

    public ProgressDialog mProgressDialog;

    TabLayout tabLayout;

    ViewPager viewPager;

    ICO_ViewPagerAdapter ico_viewPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ico);

        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        pro_pic = preferences.getString(General.PREFS_Picture, "");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        init();

    }

    public void init(){

        iv_back = findViewById(R.id.iv_back);
        tv_app_name = findViewById(R.id.tv_app_name);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ico_viewPagerAdapter = new ICO_ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(ico_viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_app_name.setTypeface(type2);

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
}
