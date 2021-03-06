package com.widle.coinscap.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.widle.coinscap.Acknowledgement1_Activity;
import com.widle.coinscap.Acknowledgement_Activity;
import com.widle.coinscap.Adapter.Favourite_Adapter;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Exchange_Activity;
import com.widle.coinscap.Help_Activity;
import com.widle.coinscap.ICO_Activity;
import com.widle.coinscap.Price_Alert1_Activity;
import com.widle.coinscap.Profile_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Setting_Activity;
import com.widle.coinscap.TopList_Activity;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.Fav_Coins;
import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by imperial-web1 on 9/3/18.
 */

public class Favourite_Fragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView rv_favourite;

    public ProgressDialog mProgressDialog;

    private TextView tv_app_name, tv_lbl;

    private ImageView iv_lbl;

    PopupMenu popupmenu;

    ArrayList<coin> mArrayList = new ArrayList<>();
    ArrayList<Fav_Coins> mFavArrayList = new ArrayList<>();

    private static DatabaseHandler db;

    private Favourite_Adapter favourite_adapter;

    String currency = "",pro_pic = "", user_name = "";

    SharedPreferences preferences;

    String s = "", skill = "";

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();

    Parcelable recylerViewState;

    private CircleImageView iv_profilepic;

    private TextView tv_user_name;

    private Toolbar mToolbar;

    DrawerLayout drawer;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        db = new DatabaseHandler(getActivity());

        preferences = getActivity().getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        currency = preferences.getString(General.PREFS_Currency, "");
        pro_pic = preferences.getString(General.PREFS_Picture, "");
        user_name = preferences.getString(General.PREFS_User_Name, "");

        rv_favourite = (RecyclerView) view.findViewById(R.id.rv_favourite);
        rv_favourite.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tv_app_name = (TextView) view.findViewById(R.id.tv_app_name);
        iv_lbl = view.findViewById(R.id.iv_lbl);
        tv_lbl = (TextView) view.findViewById(R.id.tv_lbl);

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
        tv_lbl.setTypeface(type1);
        tv_user_name.setTypeface(type1);

        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        mArrayList = db.getallcoins();

//        favourite_adapter = new Favourite_Adapter(getActivity(), mArrayList);
//        rv_favourite.setAdapter(favourite_adapter);

        for (int i = 0; i < mArrayList.size(); i++) {

            s = mArrayList.get(i).SYMBOL;
            skill += s + ",";
        }

        if (skill.endsWith(",")) {
            skill = skill.substring(0, skill.length() - 1);
        }

        if (utils.isNetworkAvailable(getActivity())) {
            rv_favourite.setVisibility(View.VISIBLE);
            tv_lbl.setVisibility(View.GONE);
            if (mArrayList.size() == 0){
                rv_favourite.setVisibility(View.GONE);
                tv_lbl.setVisibility(View.VISIBLE);
                iv_lbl.setImageResource(R.mipmap.ic_no_fav);
                tv_lbl.setText(R.string.error_fav);
            }else{
                new apiGet_Coins(skill, currency).execute();
            }
        }else{
            rv_favourite.setVisibility(View.GONE);
            tv_lbl.setVisibility(View.VISIBLE);
            tv_lbl.setText(R.string.err_no_internet);
        }

//
        if (pro_pic.equals("")){
        } else{
            String url = "https://widle.studio/coinscapmarket/API/profile/";
            Glide.with(getActivity()).load(url + pro_pic).into(iv_profilepic);
        }


        iv_profilepic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(getActivity(), Profile_Activity.class);
                startActivity(intent);
                return false;
            }
        });
        tv_user_name.setText(user_name);

        return view;
    }

    public void PopMenuDisplay() {
        popupmenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about:
                        Intent intent = new Intent(getActivity(), Acknowledgement_Activity.class);
                        intent.putExtra("type", "1");
                        startActivity(intent);
                        return true;

                    case R.id.help:
                        Intent i1 = new Intent(getActivity(), Help_Activity.class);
                        startActivity(i1);
                        return true;

                    case R.id.acknowledgement:
                        Intent i2 = new Intent(getActivity(), Acknowledgement1_Activity.class);
                        startActivity(i2);
                        return true;

                    case R.id.privacy:
                        Intent i3 = new Intent(getActivity(), Acknowledgement_Activity.class);
                        i3.putExtra("type", "4");
                        startActivity(i3);
                        return true;

                    case R.id.terms:
                        Intent i4 = new Intent(getActivity(), Acknowledgement_Activity.class);
                        i4.putExtra("type", "5");
                        startActivity(i4);
                        return true;

                    case R.id.cookie:
                        Intent i5 = new Intent(getActivity(), Acknowledgement_Activity.class);
                        i5.putExtra("type", "6");
                        startActivity(i5);
                        return true;

                    case R.id.rate:
                        String url = "https://play.google.com/store/apps/details?id=com.imperialinfosys.coinscap";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        return true;

                    case R.id.setting:
                        Intent i6 = new Intent(getActivity(), Setting_Activity.class);
                        startActivity(i6);
                        return true;
                }
                return false;
            }
        });
        popupmenu.show();
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


    public class apiGet_Coins extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, coins, currency;

        public apiGet_Coins(String coins, String currency) {
            this.coins = coins;
            this.currency = currency;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=" + coins + "&tsyms=" + "USD" + "&extraParams=your_app_name";

                ParsedResponse p = Service.apiFavouriteGetCoin(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    mFavArrayList = (ArrayList<Fav_Coins>) p.o;
                } else {
                    msg = (String) p.o;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                error = true;
                msg = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (!error) {
                favourite_adapter = new Favourite_Adapter(getActivity(), mFavArrayList, mArrayList);
                rv_favourite.setAdapter(favourite_adapter);

                update();

            } else {
//                utils.showAlertMessage(getActivity(), msg);
            }
        }
    }


     /*-------------------------------------------------Update_Service-------------------------------------------------------*/

    public void update() {
        timer = new Timer();
        //initialize the TimerTask's job
        initializeChatTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 0, 3000); //
    }


    public void initializeChatTask() {
        timerTask = new TimerTask() {

            @Override
            public void run() {

                handler.post(new Runnable() {
                    public void run() {
                        recylerViewState = rv_favourite.getLayoutManager().onSaveInstanceState();
                        new apiGet_Update_Coins().execute();
//                        if (utils.isNetworkAvailable(Home_Activity.this)) {
//                        } else {
//                            utils.showAlertMessage(Home_Activity.this, getString(R.string.err_no_internet));
//                        }

                    }
                });

            }
        };
    }


    public class apiGet_Update_Coins extends AsyncTask<Void, Void, Void> {
        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=" + skill + "&tsyms=" + "USD" + "&extraParams=your_app_name";

                ParsedResponse p = Service.apiFavouriteGetCoin(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    mFavArrayList = (ArrayList<Fav_Coins>) p.o;
                } else {
                    msg = (String) p.o;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                error = true;
                msg = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (!error) {
                favourite_adapter = new Favourite_Adapter(getActivity(), mFavArrayList, mArrayList);
                rv_favourite.setAdapter(favourite_adapter);
                rv_favourite.getLayoutManager().onRestoreInstanceState(recylerViewState);
            } else {
//                utils.showAlertMessage(getActivity(), msg);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopTimer();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}
