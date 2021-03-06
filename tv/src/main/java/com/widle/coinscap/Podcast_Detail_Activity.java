package com.widle.coinscap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import app.com.tvrecyclerview.TvRecyclerView;
import com.widle.coinscap.Adapter.NormalAdapter;
import com.widle.coinscap.Adapter.Podcast_Detail_Adapter;
import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Podcast_Detail_Activity extends Activity {

//    private TvRecyclerView mTvRecyclerView;
//
//    Parcelable recylerViewState;
//
    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();
//
    private String channel_id;
//
    Podcast_Detail_Adapter podcast_part_adapter;

    private TvRecyclerView mTvRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_detail_);

        channel_id = getIntent().getStringExtra("channel_id");

        mTvRecyclerView = (TvRecyclerView) findViewById(R.id.tv_recycler_view);
        init();

    }

    public void init(){
        GridLayoutManager manager = new GridLayoutManager(Podcast_Detail_Activity.this, 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mTvRecyclerView.setLayoutManager(manager);

        int itemSpace = getResources().
                getDimensionPixelSize(R.dimen.recyclerView_item_space);
        mTvRecyclerView.addItemDecoration(new SpaceItemDecoration(itemSpace));

        mTvRecyclerView.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
            @Override
            public void onItemViewClick(View view, int position) {
                Intent intent = new Intent(Podcast_Detail_Activity.this, VideoPlayerActivity.class);
                intent.putExtra("media_link", mArrayList.get(position).media_link);
                startActivity(intent);
            }

            @Override
            public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {

            }
        });
//        mTvRecyclerView.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
//            @Override
//            public void onItemViewClick(View view, int position) {
//                Toast.makeText(Podcast_Detail_Activity.this,
//            }
//
//            @Override
//            public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {
//            }
//        });
        mTvRecyclerView.setSelectPadding(35, 34, 35, 38);


        if (utils.isNetworkAvailable(Podcast_Detail_Activity.this)) {
            new apiGet_PodcastPart().execute();
        } else {
            utils.showAlertMessage(Podcast_Detail_Activity.this, getString(R.string.err_no_internet));
        }

    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.left = space;
            outRect.top = space;
        }
    }

    //-----------------------------------------API_FOR_GET_COINS-------------------------------------------------------------------

    public class apiGet_PodcastPart extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {
                String Baseurl = "https://api.podcast.de/channel/"+channel_id+".json?limit=5000";
                ParsedResponse p = Service.apiGetPodcastPart(Podcast_Detail_Activity.this, Baseurl);
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
            if (!error) {
//                podcast_part_adapter = new Podcast_Detail_Adapter(Podcast_Detail_Activity.this, mArrayList);
//                mTvRecyclerView.setAdapter(podcast_part_adapter);
                NormalAdapter mAdapter = new NormalAdapter(Podcast_Detail_Activity.this, mArrayList);
                mTvRecyclerView.setAdapter(mAdapter);
            } else {
            }
        }
    }

}



