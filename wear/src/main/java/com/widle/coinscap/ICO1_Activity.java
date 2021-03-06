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

import com.widle.coinscap.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import com.widle.coinscap.Adapter.ICO_Adapter;
import com.widle.coinscap.Model.ICO;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class ICO1_Activity extends WearableActivity {

    private WearableRecyclerView recycler_view;

    public ProgressDialog mProgressDialog;

    ArrayList<ICO> mArrayList = new ArrayList<>();

    private String type;

    private ICO_Adapter ico_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ico1);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        type = getIntent().getStringExtra("type");

        recycler_view = (WearableRecyclerView) findViewById(R.id.recycler_view);

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                OrientationHelper.VERTICAL, false);
        recycler_view.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler_view.getContext(),
                linearLayoutManager.getOrientation());
        recycler_view.addItemDecoration(dividerItemDecoration);

        if (utils.isNetworkAvailable(ICO1_Activity.this)) {
            new apiICO().execute();
        } else {
        }


        // Enables Always-on
        setAmbientEnabled();
    }



    public class apiICO extends AsyncTask<Void, Void, Void> {

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
                String url = "https://api.icowatchlist.com/public/v1/" + type;
                ParsedResponse p = Service.apiIcoList(ICO1_Activity.this, url, type);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<ICO>) p.o;
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
                ico_adapter = new ICO_Adapter(ICO1_Activity.this, mArrayList, type);
                recycler_view.setAdapter(ico_adapter);
            } else {
            }
        }
    }
}
