package com.widle.coinscap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.widle.coinscap.Adapter.Favourite_Adapter;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.Fav_Coins;

import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Price_Alert1_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;

    private TextView tv_app_name, tv_lbl;

//    ArrayList<Currency> mArrayList = new ArrayList<>();

    private RecyclerView rv_db;

//    Alert_Adapter alert_adapter;

    public ProgressDialog mProgressDialog;

    SharedPreferences preferences;

    private String user_id = "";

    ArrayList<coin> mArrayList = new ArrayList<>();
    ArrayList<Fav_Coins> mFavArrayList = new ArrayList<>();
    private static DatabaseHandler db;
    private Favourite_Adapter favourite_adapter;
    String currency = "",pro_pic = "";
    String s = "", skill = "";

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();

    Parcelable recylerViewState;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_alert1);

        db = new DatabaseHandler(Price_Alert1_Activity.this);

        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        user_id = preferences.getString(General.PREFS_User_id, "");
        currency = preferences.getString(General.PREFS_Currency, "");
        pro_pic = preferences.getString(General.PREFS_Picture, "");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        iv_back = findViewById(R.id.iv_back);
        tv_app_name = findViewById(R.id.tv_app_name);
        tv_lbl = (TextView) findViewById(R.id.tv_lbl);

        rv_db = findViewById(R.id.rv_db);
        rv_db.setLayoutManager(new LinearLayoutManager(Price_Alert1_Activity.this, LinearLayoutManager.VERTICAL, false));


        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_app_name.setTypeface(type2);


        mArrayList = db.getallcoins();

        for (int i = 0; i < mArrayList.size(); i++) {

            s = mArrayList.get(i).SYMBOL;
            skill += s + ",";
        }

        if (skill.endsWith(",")) {
            skill = skill.substring(0, skill.length() - 1);
        }

        if (utils.isNetworkAvailable(Price_Alert1_Activity.this)) {
            rv_db.setVisibility(View.VISIBLE);
            tv_lbl.setVisibility(View.GONE);
            if (mArrayList.size() == 0){
                rv_db.setVisibility(View.GONE);
                tv_lbl.setVisibility(View.VISIBLE);
//                iv_lbl.setImageResource(R.mipmap.ic_no_fav);
                tv_lbl.setText(R.string.error_alert);
            }else{
                new apiGet_Coins(skill, currency).execute();
            }
        }else{
            rv_db.setVisibility(View.GONE);
            tv_lbl.setVisibility(View.VISIBLE);
            tv_lbl.setText(R.string.err_no_internet);
        }



//        if (utils.isNetworkAvailable(this)) {
//            new apiGet_Alert_Coin().execute();
//        } else {
//            utils.showAlertMessage(Price_Alert1_Activity.this, getString(R.string.err_no_internet));
//        }

        iv_back.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mArrayList = db.getallalert();

//
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
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

                ParsedResponse p = Service.apiFavouriteGetCoin(Price_Alert1_Activity.this, Baseurl);
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
                favourite_adapter = new Favourite_Adapter(Price_Alert1_Activity.this, mFavArrayList, mArrayList);
                rv_db.setAdapter(favourite_adapter);

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
                        recylerViewState = rv_db.getLayoutManager().onSaveInstanceState();
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

                ParsedResponse p = Service.apiFavouriteGetCoin(Price_Alert1_Activity.this, Baseurl);
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
                favourite_adapter = new Favourite_Adapter(Price_Alert1_Activity.this, mFavArrayList, mArrayList);
                rv_db.setAdapter(favourite_adapter);
                rv_db.getLayoutManager().onRestoreInstanceState(recylerViewState);



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

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}
