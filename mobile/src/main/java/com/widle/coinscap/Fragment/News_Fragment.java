package com.widle.coinscap.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.widle.coinscap.Adapter.ViewPagerAdapter;
import com.widle.coinscap.Exchange_Activity;
import com.widle.coinscap.ICO_Activity;
import com.widle.coinscap.Price_Alert1_Activity;
import com.widle.coinscap.Profile_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.TopList_Activity;
import com.widle.coinscap.Utils.General;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;



/**
 * Created by imperial-web on 2/26/2018.
 */

public class News_Fragment extends Fragment implements  NavigationView.OnNavigationItemSelectedListener{

    public ProgressDialog mProgressDialog;

    private TextView tv_app_name;

    private CircleImageView iv_profilepic;

    private TextView tv_user_name;

    private String pro_pic = "", user_name = "";

    SharedPreferences preferences;

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    private Toolbar mToolbar;

    DrawerLayout drawer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        preferences = getActivity().getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        pro_pic = preferences.getString(General.PREFS_Picture, "");
        user_name = preferences.getString(General.PREFS_User_Name, "");

        tv_app_name = (TextView) view.findViewById(R.id.tv_app_name);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        iv_profilepic = header.findViewById(R.id.iv_profilepic);
        tv_user_name = header.findViewById(R.id.tv_user_name);


        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_app_name.setTypeface(type2);
        tv_user_name.setTypeface(type1);

        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);


        if (pro_pic.equals("")){
        } else{
            String url = "https://widle.studio/coinscapmarket/API/profile/";
            Glide.with(getActivity()).load(url + pro_pic).into(iv_profilepic);
        }

        tv_user_name.setText(user_name);


        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_exchange) {
            Intent intent =  new Intent(getActivity(), Exchange_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_toplist) {
            Intent intent =  new Intent(getActivity(), TopList_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_icolist) {
            Intent intent =  new Intent(getActivity(), ICO_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_pricealert){
            Intent intent = new Intent(getActivity(), Price_Alert1_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile){
            Intent intent = new Intent(getActivity(), Profile_Activity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

}
