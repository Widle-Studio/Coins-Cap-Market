package com.widle.coinscap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import com.widle.coinscap.Adapter.Event_Adapter;
import com.widle.coinscap.Adapter.PodcastChanel_Adapter;
import com.widle.coinscap.Model.Event;
import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Event_Activity extends WearableActivity {

    private TextView mTextView;

    private WearableRecyclerView recycler_view;

    public ProgressDialog mProgressDialog;

    ArrayList<Event> mArrayList = new ArrayList<>();

    private Event_Adapter event_adapter;

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        recycler_view = (WearableRecyclerView) findViewById(R.id.recycler_view);

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                OrientationHelper.VERTICAL, false);
        recycler_view.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler_view.getContext(),
                linearLayoutManager.getOrientation());
        recycler_view.addItemDecoration(dividerItemDecoration);

        if (utils.isNetworkAvailable(Event_Activity.this)) {
            new apiGet_Token().execute();
        } else {
        }


        // Enables Always-on
        setAmbientEnabled();
    }



    public class apiGet_Token extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String Baseurl = "https://api.coinmarketcal.com/oauth/v2/token?grant_type=client_credentials&client_id=883_3nwaj37uduskg0cg00wgg40o8c8sw08o8sso4osccgks00ooss&client_secret=3k043tvj5l0ks40440ck04gk00wowkc4wkskws4sc4kow0g80k";
                ParsedResponse p = Service.apiGetToken(Event_Activity.this, Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<Event>) p.o;
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
                new apiGet_Event().execute();
            } else {
            }
        }
    }


    public class apiGet_Event extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
            String token = preferences.getString(General.PREFS_Token, "");
            try {
                String Baseurl = "https://api.coinmarketcal.com/v1/events?access_token=" + token + "&max=150";
                ParsedResponse p = Service.apiGetEvents(Event_Activity.this, Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<Event>) p.o;
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
            mProgressDialog.dismiss();
            if (!error) {
                event_adapter = new Event_Adapter(Event_Activity.this, mArrayList);
                recycler_view.setAdapter(event_adapter);
            } else {
            }
        }
    }
}
