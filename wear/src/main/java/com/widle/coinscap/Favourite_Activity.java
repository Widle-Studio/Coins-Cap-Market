package com.widle.coinscap;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.widle.coinscap.Adapter.Favourite_Adapter;
import com.widle.coinscap.Model.Fav_Coins;
import com.widle.coinscap.Model.coin;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Favourite_Activity extends WearableActivity {

    private WearableRecyclerView recycler_view;

    private Favourite_Adapter favourite_adapter;

    private SharedPreferences preferences;

    private String symbol = "";

    ArrayList<coin> mArrayList = new ArrayList<>();

    ArrayList<Fav_Coins> mFavArrayList = new ArrayList<>();

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();

    List<String> stringList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();

    Parcelable recylerViewState;

    private static final String TAG = "DataLayerService";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
//        symbol = preferences.getString(General.PREFS_Symbol, "");

        stringList = getIntent().getStringArrayListExtra("arraylist");
        nameList = getIntent().getStringArrayListExtra("namearraylist");

        for (String s : stringList) {
            symbol += s + ",";

                }

        if (symbol.endsWith(",")) {
            symbol = symbol.substring(0, symbol.length() - 1);
        }

        recycler_view = (WearableRecyclerView) findViewById(R.id.recycler_view);

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                OrientationHelper.VERTICAL, false);
        recycler_view.setLayoutManager(linearLayoutManager);


        if (utils.isNetworkAvailable(Favourite_Activity.this)) {
            new apiGet_Coins().execute();
        } else {
//            utils.showAlertMessage(Favourite_Activity.this, getString(R.string.err_no_internet));
        }


        // Enables Always-on
        setAmbientEnabled();
    }



    public class apiGet_Coins extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms="+symbol+"&tsyms=" + "USD" + "&extraParams=your_app_name";
                ParsedResponse p = Service.apiFavouriteGetCoin(Favourite_Activity.this, Baseurl);
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
                favourite_adapter = new Favourite_Adapter(Favourite_Activity.this,mFavArrayList, stringList, nameList);
                recycler_view.setAdapter(favourite_adapter);

                update();

            } else {
//                utils.showAlertMessage(Favourite_Activity.this, msg);
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

                        if (utils.isNetworkAvailable(Favourite_Activity.this)) {
                            recylerViewState = recycler_view.getLayoutManager().onSaveInstanceState();
                            new apiGet_Update_Coins().execute();
                        } else {
//                            utils.showAlertMessage(Favourite_Activity.this, getString(R.string.err_no_internet));
                        }

                    }
                });

            }
        };
    }


    //-----------------------------------------API_FOR_GET_COINS-------------------------------------------------------------------


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
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms="+symbol+"&tsyms=" + "USD" + "&extraParams=your_app_name";
                ParsedResponse p = Service.apiFavouriteGetCoin(Favourite_Activity.this, Baseurl);
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
                favourite_adapter = new Favourite_Adapter(Favourite_Activity.this,mFavArrayList, stringList, nameList);
                recycler_view.setAdapter(favourite_adapter);
                recycler_view.getLayoutManager().onRestoreInstanceState(recylerViewState);
            } else {
//                utils.showAlertMessage(Favourite_Activity.this, msg);
            }
        }
    }
}
