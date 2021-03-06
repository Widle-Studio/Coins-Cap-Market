package com.widle.coinscap;

import android.app.Activity;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.widle.coinscap.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import app.com.tvrecyclerview.TvRecyclerView;
import com.widle.coinscap.Adapter.ICO_Adapter;
import com.widle.coinscap.Adapter.NormalAdapter;
import com.widle.coinscap.Model.ICO;
import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class ICO_Detail_Activity extends Activity {

    private String title;

    private TvRecyclerView mTvRecyclerView;

    ArrayList<ICO> mArrayList = new ArrayList<>();

    ICO_Adapter ico_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ico_detail);

        title = getIntent().getStringExtra("title");

        init();
    }

    public void init(){

        mTvRecyclerView = (TvRecyclerView) findViewById(R.id.tv_recycler_view);

        GridLayoutManager manager = new GridLayoutManager(ICO_Detail_Activity.this, 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mTvRecyclerView.setLayoutManager(manager);

        int itemSpace = getResources().
                getDimensionPixelSize(R.dimen.recyclerView_item_space);
        mTvRecyclerView.addItemDecoration(new ICO_Detail_Activity.SpaceItemDecoration(itemSpace));


        mTvRecyclerView.setSelectPadding(35, 34, 35, 38);


        if (utils.isNetworkAvailable(ICO_Detail_Activity.this)) {
            new apiGet_ICOList().execute();
        } else {
            utils.showAlertMessage(ICO_Detail_Activity.this, getString(R.string.err_no_internet));
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

    public class apiGet_ICOList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {
                String Baseurl = "https://api.icowatchlist.com/public/v1/" + title;
                ParsedResponse p = Service.apiIcoList(ICO_Detail_Activity.this, Baseurl, title);
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
            if (!error) {
                ico_adapter = new ICO_Adapter(ICO_Detail_Activity.this, mArrayList);
                mTvRecyclerView.setAdapter(ico_adapter);
            } else {
            }
        }
    }

}
