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

import com.widle.coinscap.Adapter.PodcastChanel_Adapter;
import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Podcast_Activity extends WearableActivity {

    private WearableRecyclerView recycler_view;

    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();

    private PodcastChanel_Adapter podcastChanel_adapter;

    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);

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

        if (utils.isNetworkAvailable(Podcast_Activity.this)) {
            new apiGet_PodcastChanel().execute();
        } else {
        }

        // Enables Always-on
        setAmbientEnabled();
    }



    public class apiGet_PodcastChanel extends AsyncTask<Void, Void, Void> {

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
                String Baseurl = "https://api.podcast.de/search.json?q=blockchain";
                ParsedResponse p = Service.apiGetPodcastChannel(Podcast_Activity.this, Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<PodcastChanel>) p.o;
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
                podcastChanel_adapter = new PodcastChanel_Adapter(Podcast_Activity.this, mArrayList);
                recycler_view.setAdapter(podcastChanel_adapter);
            } else {
            }
        }
    }
}
