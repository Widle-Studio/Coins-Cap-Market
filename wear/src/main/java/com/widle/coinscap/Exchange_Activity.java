package com.widle.coinscap;

import android.app.ProgressDialog;
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

import com.widle.coinscap.Adapter.Exchange_Adapter;
import com.widle.coinscap.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Exchange_Activity extends WearableActivity {


    private WearableRecyclerView recycler_view;

    public ProgressDialog mProgressDialog;

    private String coins = "BTC", currency = "USD";

    ArrayList<coin> mArrayList = new ArrayList<>();


    Exchange_Adapter exchange_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);


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

        if (utils.isNetworkAvailable(Exchange_Activity.this)) {
            new apiExchangeList().execute();
        } else {
        }

        // Enables Always-on
        setAmbientEnabled();
    }





    public class apiExchangeList extends AsyncTask<Void, Void, Void> {

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
                String url = "https://min-api.cryptocompare.com/data/top/exchanges/full?fsym=" + coins + "&tsym=" + currency +"&limit=20";
                ParsedResponse p = Service.apiGetExchange(Exchange_Activity.this, url);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<coin>) p.o;
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
                exchange_adapter = new Exchange_Adapter(Exchange_Activity.this, mArrayList);
                recycler_view.setAdapter(exchange_adapter);
             } else {
            }
        }
    }
}
