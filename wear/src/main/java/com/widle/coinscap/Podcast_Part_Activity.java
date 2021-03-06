package com.widle.coinscap;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import com.widle.coinscap.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import com.widle.coinscap.Adapter.Podcast_Part_Adapter;
import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Podcast_Part_Activity extends WearableActivity {


    private WearableRecyclerView recycler_view;

    private String channel_id, title, description, subtitle, image;

    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();

    private Podcast_Part_Adapter podcast_part_adapter;

    public ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_part);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        recycler_view = (WearableRecyclerView) findViewById(R.id.recycler_view);

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                OrientationHelper.VERTICAL, false);
        recycler_view.setLayoutManager(linearLayoutManager);

        channel_id = getIntent().getStringExtra("channel_id");
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        subtitle = getIntent().getStringExtra("subtitle");
        image = getIntent().getStringExtra("image");


        if (utils.isNetworkAvailable(this)) {
            new apiGet_PodcastPart().execute();
        } else {
        }
        // Enables Always-on
        setAmbientEnabled();
    }


    public class apiGet_PodcastPart extends AsyncTask<Void, Void, Void> {

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
                String Baseurl = "https://api.podcast.de/channel/"+channel_id+".json?limit=5000";
                ParsedResponse p = Service.apiGetPodcastPart(Podcast_Part_Activity.this, Baseurl);
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
                podcast_part_adapter = new Podcast_Part_Adapter(Podcast_Part_Activity.this, mArrayList);
                recycler_view.setAdapter(podcast_part_adapter);
            } else {
            }
        }
    }

}
