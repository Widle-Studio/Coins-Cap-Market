package com.widle.coinscap.Widget;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.widle.coinscap.Adapter.Favourite_Adapter;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.Fav_Coins;
import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Widget_Activity extends AppCompatActivity {

    private RecyclerView rv_favourite;

    public ProgressDialog mProgressDialog;

    private TextView tv_app_name;

    ArrayList<coin> mArrayList = new ArrayList<>();

    ArrayList<Fav_Coins> mFavArrayList = new ArrayList<>();

    private static DatabaseHandler db;

    private Favourite_Adapter favourite_adapter;

    String s = "", skill = "";

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        db = new DatabaseHandler(Widget_Activity.this);


        rv_favourite = (RecyclerView) findViewById(R.id.rv_favourite);
        rv_favourite.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mProgressDialog = new ProgressDialog(Widget_Activity.this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        mArrayList = db.getallcoins();

        for (int i = 0; i < mArrayList.size(); i++) {

            s = mArrayList.get(i).SYMBOL;
            skill += s + ",";
        }

        if (skill.endsWith(",")) {
            skill = skill.substring(0, skill.length() - 1);
        }

        new apiGet_Coins().execute();

    }


    public class apiGet_Coins extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, coins;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=" + skill + "&tsyms=" + "USD" + "&extraParams=your_app_name";

                ParsedResponse p = Service.apiFavouriteGetCoin(Widget_Activity.this, Baseurl);
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
                favourite_adapter = new Favourite_Adapter(Widget_Activity.this, mFavArrayList, mArrayList);
                rv_favourite.setAdapter(favourite_adapter);

                update();

            } else {
                utils.showAlertMessage(Widget_Activity.this, msg);
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
                ParsedResponse p = Service.apiFavouriteGetCoin(Widget_Activity.this, Baseurl);
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
                favourite_adapter = new Favourite_Adapter(Widget_Activity.this, mFavArrayList, mArrayList);
                rv_favourite.setAdapter(favourite_adapter);
            } else {
                utils.showAlertMessage(Widget_Activity.this, msg);
            }
        }
    }
}
