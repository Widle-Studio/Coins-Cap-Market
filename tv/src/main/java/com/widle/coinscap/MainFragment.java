/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.widle.coinscap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.CursorObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONException;

import com.widle.coinscap.Adapter.Custom_ArrayObjectAdapter;
import com.widle.coinscap.Adapter.Podcast_Detail_Adapter;
import com.widle.coinscap.Model.Event;
import com.widle.coinscap.Model.ICO;
import com.widle.coinscap.Model.News;
import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.Model.coin;

import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class MainFragment extends BrowseFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "MainFragment";

    private static final int BACKGROUND_UPDATE_DELAY = 300;
    private static final int GRID_ITEM_WIDTH = 200;
    private static final int GRID_ITEM_HEIGHT = 200;
    private static final int NUM_ROWS = 3;
    private static final int NUM_COLS = 15;

    private final Handler mHandler = new Handler();
    private ArrayObjectAdapter mRowsAdapter;
    private Drawable mDefaultBackground;
    private DisplayMetrics mMetrics;
    private Timer mBackgroundTimer;
    private String mBackgroundUri;
    private BackgroundManager mBackgroundManager;
    public ProgressDialog mProgressDialog;

    private ArrayList<News> newsArrayList;
    private ArrayList<coin> coinArrayList;
    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();
    ArrayList<coin> mExchangeArrayList = new ArrayList<>();
    ArrayList<coin> mTopArrayList = new ArrayList<>();

    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> stringList = new ArrayList<>();
    ArrayList<Event> mArrayEventList = new ArrayList<>();

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();

    Parcelable recylerViewState;

    private LinearLayoutManager mLayoutManager;

    // Maps a Loader Id to its CursorObjectAdapter.
    private Map<Integer, CursorObjectAdapter> mVideoCursorAdapters;

    private static final int CATEGORY_LOADER = 123; // Unique ID for Category Loader.

    ArrayList<coin> mItems = CoinsProvider.getMovieItems();

    SharedPreferences preferences;

    ArrayList<ICO> ICOlist = new ArrayList<ICO>();


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Create a list to contain all the CursorObjectAdapters.
        // Each adapter is used to render a specific row of videos in the MainFragment.
        mVideoCursorAdapters = new HashMap<>();

        // Start loading the categories from the database.
        getLoaderManager().initLoader(CATEGORY_LOADER, null, this);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onActivityCreated(savedInstanceState);

        prepareBackgroundManager();

        setupUIElements();

        //loadRows();

        // new apiCountryData().execute();

        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);
        // coinData = new ArrayList<>();


        for (int i = 0; i<3; i++){
            ICO ico = new ICO();
            if (i == 0){
                ico.title = "LIVE";
                ico.subtitle = "live";
                ICOlist.add(ico);
            } else if (i == 1){
                ico.title = "UPCOMING";
                ico.subtitle = "upcoming";
                ICOlist.add(ico);
            } else if (i == 2){
                ico.title = "FINISHED";
                ico.subtitle = "finished";
                ICOlist.add(ico);
            }
        }

        new apiNewsList().execute();
        setupEventListeners();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
        if (null != mBackgroundTimer) {
            Log.d(TAG, "onDestroy: " + mBackgroundTimer.toString());
            mBackgroundTimer.cancel();
        }
    }

    private void loadRows() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        HeaderItem gridItemPresenterHeader = new HeaderItem(0, "Coins");
        Coib_Persenter mGridPresenter = new Coib_Persenter(getActivity());

        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        for (int i = 0; i < coinArrayList.size(); i++) {
            gridRowAdapter.add(coinArrayList.get(i));
        }

        mRowsAdapter.clear();
        mRowsAdapter.add(new ListRow(gridItemPresenterHeader, gridRowAdapter));


//---------------------------------------------------------------------------------------------
        HeaderItem cardPresenterHeader = new HeaderItem(1, "News");
        CardPresenter cardPresenter = new CardPresenter();

        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);

        for (int j = 0; j < newsArrayList.size(); j++) {

            listRowAdapter.add(newsArrayList.get(j));
        }

        mRowsAdapter.add(new ListRow(cardPresenterHeader, listRowAdapter));

//---------------------------------------------------------------------------------------------
        HeaderItem cardPresenterHeader1 = new HeaderItem(2, "Podcast");
        CardPresenter1 cardPresenter1 = new CardPresenter1();

        ArrayObjectAdapter listRowAdapter1 = new ArrayObjectAdapter(cardPresenter1);

        for (int j = 0; j < mArrayList.size(); j++) {
            listRowAdapter1.add(mArrayList.get(j));
        }

        mRowsAdapter.add(new ListRow(cardPresenterHeader1, listRowAdapter1));

//---------------------------------------------------------------------------------------------
        HeaderItem cardPresenterHeader2 = new HeaderItem(3, "Event");
        CardPresenter2 cardPresenter2 = new CardPresenter2();

        ArrayObjectAdapter listRowAdapter2 = new ArrayObjectAdapter(cardPresenter2);

        for (int k = 0; k < mArrayEventList.size(); k++) {
            listRowAdapter2.add(mArrayEventList.get(k));
        }

        mRowsAdapter.add(new ListRow(cardPresenterHeader2, listRowAdapter2));



////---------------------------------------------------------------------------------------------


        HeaderItem cardPresenterHeader3 = new HeaderItem(4, "ICO List");
        CardPresenter3 cardPresenter3 = new CardPresenter3();

        ArrayObjectAdapter listRowAdapter3 = new ArrayObjectAdapter(cardPresenter3);

        for (int l = 0; l < ICOlist.size(); l++) {
            listRowAdapter3.add(ICOlist.get(l));
        }

        mRowsAdapter.add(new ListRow(cardPresenterHeader3, listRowAdapter3));
        setAdapter(mRowsAdapter);

    }

    private void prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        mDefaultBackground = getResources().getDrawable(R.drawable.default_background);
        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(
        // R.drawable.videos_by_google_banner));
        setTitle(getString(R.string.app_name)); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        setBrandColor(getResources().getColor(R.color.dark_blue));
        // set search icon color
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private void setupEventListeners() {
        setOnSearchClickedListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    protected void updateBackground(String uri) {
        int width = mMetrics.widthPixels;
        int height = mMetrics.heightPixels;
        Glide.with(getActivity())
                .load(uri)
                .centerCrop()
                .error(mDefaultBackground)
                .into(new SimpleTarget<GlideDrawable>(width, height) {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable>
                                                        glideAnimation) {
                        mBackgroundManager.setDrawable(resource);
                    }
                });
        mBackgroundTimer.cancel();
    }

    private void startBackgroundTimer() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
        }
        mBackgroundTimer = new Timer();
        mBackgroundTimer.schedule(new UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {


            if (row.getHeaderItem().getName().equals("Coins")) {

                if (item instanceof coin) {
                    coin item1 = (coin) item;
                    Intent intent = new Intent(getActivity(), Coins_DetailsActivity.class);
                    intent.putExtra("alldata", coinArrayList);
                    intent.putExtra("symbol", item1.getCOINSSTRINGNAME());
                    getActivity().startActivity(intent);
                }

            } else if (row.getHeaderItem().getName().equals("News")) {

                if (item instanceof News) {
                    News movie = (News) item;
                    Log.d(TAG, "Item: " + item.toString());
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("url", movie.url);
                    intent.putExtra("alldata", newsArrayList);

                    getActivity().startActivity(intent);

                } else if (item instanceof String) {
                    if (((String) item).contains(getString(R.string.error_fragment))) {
                        Intent intent = new Intent(getActivity(), BrowseErrorActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), ((String) item), Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            } else if (row.getHeaderItem().getName().equals("Podcast")) {

                PodcastChanel movie = (PodcastChanel) item;
                Log.d(TAG, "Item: " + item.toString());
                Intent intent = new Intent(getActivity(), Podcast_Detail_Activity.class);
                intent.putExtra("channel_id", movie.channel_id);
                getActivity().startActivity(intent);

            } else if (row.getHeaderItem().getName().equals("Event")) {
                Event event = (Event) item;
                Intent intent = new Intent(getActivity(), Event_DetailsActivity.class);
                intent.putExtra("coin", event.name + " (" + event.symbol + ")");
                intent.putExtra("date", event.date_event);
                intent.putExtra("title", event.title);
                intent.putExtra("description", event.description);
                getActivity().startActivity(intent);

            } else if (row.getHeaderItem().getName().equals("ICO List")){
                ICO ico  = (ICO) item;
                Intent intent = new Intent(getActivity(), ICO_Detail_Activity.class);
                intent.putExtra("title", ico.subtitle);
                getActivity().startActivity(intent);
            }
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof Movie) {
                mBackgroundUri = ((Movie) item).getBackgroundImageUrl();
                startBackgroundTimer();
            }
        }
    }

    private class UpdateBackgroundTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    updateBackground(mBackgroundUri);
                }
            });
        }
    }


    public class apiNewsList extends AsyncTask<Void, Void, Void> {

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
                ParsedResponse p = Service.apiGetNews(getActivity());
                error = p.error;
                if (!error) {
                    newsArrayList = (ArrayList<News>) p.o;
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
//            mProgressDialog.dismiss();
            if (!error) {
                /*news_adapter = new News_Adapter(getActivity(), mArrayList);
                rv_news.setAdapter(news_adapter);*/
                new apiGet_PodcastChanel().execute();

            } else {
            }
        }
    }

    public class apiGet_PodcastChanel extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String Baseurl = "https://api.podcast.de/search.json?q=blockchain";
                ParsedResponse p = Service.apiGetPodcastChannel(getActivity(), Baseurl);
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
//            mProgressDialog.dismiss();
            if (!error) {
                new apiGet_Token().execute();
//                new apiEventList().execute();
            } else {
                utils.showAlertMessage(getActivity(), msg);
            }
        }
    }


    public class apiGet_Token extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String Baseurl = "https://api.coinmarketcal.com/oauth/v2/token?grant_type=client_credentials&client_id=883_3nwaj37uduskg0cg00wgg40o8c8sw08o8sso4osccgks00ooss&client_secret=3k043tvj5l0ks40440ck04gk00wowkc4wkskws4sc4kow0g80k";
                ParsedResponse p = Service.apiGetToken(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    mArrayEventList = (ArrayList<Event>) p.o;
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
//            mProgressDialog.dismiss();
            if (!error) {
                new apiEventList().execute();
            } else {
            }
        }
    }


    public class apiEventList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            preferences = getActivity().getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
            String token = preferences.getString(General.PREFS_Token, "");
            try {
                String Baseurl = "https://api.coinmarketcal.com/v1/events?access_token=" + token + "&max=150";
                ParsedResponse p = Service.apiGetEvents(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    mArrayEventList = (ArrayList<Event>) p.o;
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


                new apiGet_Coins().execute();

            } else {
                utils.showAlertMessage(getActivity(), msg);
            }
        }
    }


//    public class apiExchangeList extends AsyncTask<Void, Void, Void> {
//
//        boolean error;
//        String msg;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
////            mProgressDialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            try {
//                String url = "https://min-api.cryptocompare.com/data/top/exchanges/full?fsym=BTC&tsym=USD&limit=20";
//                ParsedResponse p = Service.apiGetExchange(getActivity(), url);
//                error = p.error;
//                if (!error) {
//                    mExchangeArrayList = (ArrayList<coin>) p.o;
//                } else {
//                    msg = (String) p.o;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                error = true;
//                msg = e.getMessage();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
////            mProgressDialog.dismiss();
//            if (!error) {
//                new apiTopList().execute();
//            } else {
//            }
//        }
//    }


//
//    public class apiTopList extends AsyncTask<Void, Void, Void> {
//
//        boolean error;
//        String msg;
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
////            mProgressDialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            try {
//                String url = "https://min-api.cryptocompare.com/data/top/volumes?tsym=USD&limit=20";
//                ParsedResponse p = Service.apiGetTopList(getActivity(), url);
//                error = p.error;
//                if (!error) {
//                    mTopArrayList = (ArrayList<coin>) p.o;
//                } else {
//                    msg = (String) p.o;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                error = true;
//                msg = e.getMessage();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            mProgressDialog.dismiss();
//            if (!error) {
//            } else {
//            }
//        }
//    }


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
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC,ETH,BCH,XRP,LTC,ADA,IOT,DASH,XEM,XMR,EOS,BTG,QTUM,XLM,NEO,ETC,ZEC,STEEM,XZC,STORJ,AION,HT,TRX,XRB,OMG,ELA,BNB,VEN,ZCL,DGD,OCN,BCPT,LUN,IOST,HSR,ICX,LSK,NEBL,WAVES,BLZ,INK,ADX,XVG,MTL,SRN,ZRX,RLC,THETA,BCD,OAX,ELF,INS,ZIL,AE,PRO,DOGE,ITC,RDD,SWFTC,MCO,ARN,MTN*,BRD,SAN,NAS,GVT,QUN,WTC,FCT,CDT,RCN&tsyms=" + "USD" + "&extraParams=your_app_name";
                ParsedResponse p = Service.apiGetCoin(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    coinArrayList = (ArrayList<coin>) p.o;
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


                update();
                loadRows();

            } else {
//                utils.showAlertMessage(Home1_Activity.this, msg);
            }
        }
    }


    /*-------------------------------------------------Update_Service-------------------------------------------------------*/

    public void update() {
        timer = new Timer();
        //initialize the TimerTask's job
        initializeChatTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 0, 25000); //
    }

    public void initializeChatTask() {


        timerTask = new TimerTask() {

            @Override
            public void run() {

                handler.post(new Runnable() {
                    public void run() {
                        if (utils.isNetworkAvailable(getActivity())) {
                            new apiGet_Update_Coins().execute();
                        } else {
                            utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                        }

                    }
                });

            }
        };
    }


    //-----------------------------------------API_FOR_GET_UPDATE_COINS-------------------------------------------------------------------

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
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC,ETH,BCH,XRP,LTC,ADA,IOT,DASH,XEM,XMR,EOS,BTG,QTUM,XLM,NEO,ETC,ZEC,STEEM,XZC,STORJ,AION,HT,TRX,XRB,OMG,ELA,BNB,VEN,ZCL,DGD,OCN,BCPT,LUN,IOST,HSR,ICX,LSK,NEBL,WAVES,BLZ,INK,ADX,XVG,MTL,SRN,ZRX,RLC,THETA,BCD,OAX,ELF,INS,ZIL,AE,PRO,DOGE,ITC,RDD,SWFTC,MCO,ARN,MTN*,BRD,SAN,NAS,GVT,QUN,WTC,FCT,CDT,RCN&tsyms=" + "USD" + "&extraParams=your_app_name";
                ParsedResponse p = Service.apiGetCoin(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    coinArrayList = (ArrayList<coin>) p.o;
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
                loadRows();
            } else {
            }
        }
    }


    public boolean onBackPressed() {
        boolean consumeBack;

        int selectedRowPosition = getRowsFragment().getSelectedPosition();

        ListRowPresenter.ViewHolder selectedRow = (ListRowPresenter.ViewHolder) getRowsFragment().getRowViewHolder(selectedRowPosition);
        int selectedItemPosition = selectedRow.getSelectedPosition();

        if (selectedItemPosition == 0) {
            consumeBack = false;
        } else {
            consumeBack = true;
            getRowsFragment().setSelectedPosition(selectedRowPosition, true, new ListRowPresenter.SelectItemViewHolderTask(0));
        }
        return consumeBack;
    }


    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}
