package com.widle.coinscap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.Graph;
import com.widle.coinscap.Utils.Model.Update_Coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

import org.json.JSONException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;


public class Coins_Detail_Activity extends AppCompatActivity implements View.OnClickListener, OnChartGestureListener,
        OnChartValueSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private ImageView iv_back, iv_symbol, iv_setting, iv_un_fav, iv_fav;

    private TextView tv_app_name, tv_symbol, tv_price, lbl_24_volume, tv_24_volume, lbl_market_cap, tv_market_cap, tv_pct,
            tv_1day, tv_1week, tv_1mnt, tv_1year;

    private String name, price, symbol, volume, market_cap, change24hr, changeday, device_id, fav_status = "";

    private LineChart mChart;

    ArrayList<Graph> mArrayList = new ArrayList<>();

    public ProgressDialog mProgressDialog;

    SharedPreferences preferences;

    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();

    ArrayList<Update_Coin> update_coin = new ArrayList<>();

    String sAux, sBux, sCux, sDux;

    private GoogleSignInOptions gso;

    private int RC_SIGN_IN = 100;

    // google plus
    private GoogleApiClient mGoogleApiClient;

    private Button google_login, google_logout;

    private static DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins_detail);

        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        device_id = preferences.getString(General.PREFS_Device_id, "");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        db = new DatabaseHandler(Coins_Detail_Activity.this);

        init();

        new apiGet_GraphValue().execute();
        update();

    }


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

                        if (utils.isNetworkAvailable(Coins_Detail_Activity.this)) {
                            new apiGet_Update_Coins().execute();
                        } else {
                            utils.showAlertMessage(Coins_Detail_Activity.this, getString(R.string.err_no_internet));
                        }

                    }
                });
            }
        };
    }


    public void init() {


//--------------------------------------------------------google plus---------------------------------------------------------------

        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(Coins_Detail_Activity.this)
                .enableAutoManage(Coins_Detail_Activity.this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

//-----------------------------------------------------------------------------------------------------------------------


        iv_back = findViewById(R.id.iv_back);
        iv_symbol = findViewById(R.id.iv_symbol);
        iv_un_fav = findViewById(R.id.iv_un_fav);
//        iv_fav = findViewById(R.id.iv_fav);
        tv_app_name = findViewById(R.id.tv_app_name);

        tv_symbol = findViewById(R.id.tv_symbol);
        tv_price = findViewById(R.id.tv_price);
        lbl_24_volume = findViewById(R.id.lbl_24_volume);
        iv_setting = findViewById(R.id.iv_setting);
        tv_24_volume = findViewById(R.id.tv_24_volume);
        lbl_market_cap = findViewById(R.id.lbl_market_cap);
        tv_market_cap = findViewById(R.id.tv_market_cap);
        tv_pct = findViewById(R.id.tv_pct);

        tv_1day = findViewById(R.id.tv_1day);
        tv_1week = findViewById(R.id.tv_1week);
        tv_1mnt = findViewById(R.id.tv_1mnt);
        tv_1year = findViewById(R.id.tv_1year);

        tv_1day.setBackgroundResource(R.color.light_white);


        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_app_name.setTypeface(type2);
        tv_symbol.setTypeface(type2);
        tv_price.setTypeface(type1);
        lbl_24_volume.setTypeface(type2);
        tv_24_volume.setTypeface(type1);
        lbl_market_cap.setTypeface(type2);
        tv_market_cap.setTypeface(type1);
        tv_pct.setTypeface(type1);

        tv_1day.setTypeface(type1);
        tv_1week.setTypeface(type1);
        tv_1mnt.setTypeface(type1);
        tv_1year.setTypeface(type1);


        name = getIntent().getStringExtra("name");
        symbol = getIntent().getStringExtra("symbol");
        volume = getIntent().getStringExtra("volume");
        market_cap = getIntent().getStringExtra("market_cap");
        change24hr = getIntent().getStringExtra("change24hr");
        changeday = getIntent().getStringExtra("changeday");
        price = getIntent().getStringExtra("price");
        fav_status = getIntent().getStringExtra("fav_status");


        tv_app_name.setText(name);
        tv_symbol.setText(symbol);
        tv_price.setText(price);
        tv_24_volume.setText(volume);
        tv_market_cap.setText(market_cap);

        if (fav_status.equals("0")){
            iv_un_fav.setVisibility(View.GONE);
//            iv_fav.setVisibility(View.VISIBLE);
        } else {
//            iv_fav.setVisibility(View.GONE);
            iv_un_fav.setVisibility(View.VISIBLE);
        }

        if (symbol.equals("BTC")) {
            iv_symbol.setImageResource(R.drawable.btc);
        } else if (symbol.equals("ETH")) {
            iv_symbol.setImageResource(R.drawable.eth);
        } else if (symbol.equals("BCH")) {
            iv_symbol.setImageResource(R.drawable.bch);
        } else if (symbol.equals("XRP")) {
            iv_symbol.setImageResource(R.drawable.ripple);
        } else if (symbol.equals("LTC")) {
            iv_symbol.setImageResource(R.drawable.litecoin);
        } else if (symbol.equals("ADA")) {
            iv_symbol.setImageResource(R.drawable.ada);
        } else if (symbol.equals("IOT")) {
            iv_symbol.setImageResource(R.drawable.iota);
        } else if (symbol.equals("DASH")) {
            iv_symbol.setImageResource(R.drawable.dash);
        } else if (symbol.equals("XEM")) {
            iv_symbol.setImageResource(R.drawable.xem);
        } else if (symbol.equals("XMR")) {
            iv_symbol.setImageResource(R.drawable.xmr);
        } else if (symbol.equals("EOS")) {
            iv_symbol.setImageResource(R.drawable.eos);
        } else if (symbol.equals("BTG")) {
            iv_symbol.setImageResource(R.drawable.btg);
        } else if (symbol.equals("QTUM")) {
            iv_symbol.setImageResource(R.drawable.qtum);
        } else if (symbol.equals("XLM")) {
            iv_symbol.setImageResource(R.drawable.str);
        } else if (symbol.equals("NEO")) {
            iv_symbol.setImageResource(R.drawable.neo);
        } else if (symbol.equals("ETC")) {
            iv_symbol.setImageResource(R.drawable.etc2);
        } else if (symbol.equals("ZEC")) {
            iv_symbol.setImageResource(R.drawable.zec);
        } else if (symbol.equals("STEEM")) {
            iv_symbol.setImageResource(R.drawable.steem);
        } else if (symbol.equals("XZC")) {
            iv_symbol.setImageResource(R.drawable.xzc1);
        } else if (symbol.equals("STORJ")) {
            iv_symbol.setImageResource(R.drawable.sjcx);
        } else if (symbol.equals("AION")) {
            iv_symbol.setImageResource(R.drawable.aion);
        } else if (symbol.equals("HT")) {
            String url = "https://www.cryptocompare.com/media/27010813/ht.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("TRX")) {
            String url = "https://www.cryptocompare.com/media/12318089/trx.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("XRB")) {
            String url = "https://www.cryptocompare.com/media/1383674/xrb.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("OMG")) {
            String url = "https://www.cryptocompare.com/media/1383814/omisego.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("ELA")) {
            String url = "https://www.cryptocompare.com/media/27010574/ela.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("BNB")) {
            String url = "https://www.cryptocompare.com/media/1382967/bnb.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("VEN")) {
            String url = "https://www.cryptocompare.com/media/12318129/ven.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("ZCL")) {
            String url = "https://www.cryptocompare.com/media/351926/zcl.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("DGD")) {
            String url = "https://www.cryptocompare.com/media/350851/dgd.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("OCN")) {
            String url = "https://www.cryptocompare.com/media/27010448/ocn.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("BCPT")) {
            String url = "https://www.cryptocompare.com/media/16746476/bcpt.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("LUN")) {
            String url = "https://www.cryptocompare.com/media/1383112/lunyr-logo.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("IOST")) {
            String url = "https://www.cryptocompare.com/media/27010459/iost.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("HSR")) {
            String url = "https://www.cryptocompare.com/media/12318137/hsr.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("ICX")) {
            String url = "https://www.cryptocompare.com/media/12318192/icx.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("LSK")) {
            String url = "https://www.cryptocompare.com/media/27011060/lsk.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("NEBL")) {
            String url = "https://www.cryptocompare.com/media/1384016/nebl.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("WAVES")) {
            String url = "https://www.cryptocompare.com/media/27010639/waves2.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("BLZ")) {
            String url = "https://www.cryptocompare.com/media/27010607/1.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("INK")) {
            String url = "https://www.cryptocompare.com/media/20780781/ink.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("ADX")) {
            String url = "https://www.cryptocompare.com/media/1383667/adx1.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("XVG")) {
            String url = "https://www.cryptocompare.com/media/12318032/xvg.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("MTL")) {
            String url = "https://www.cryptocompare.com/media/1383743/mtl.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("SRN")) {
            String url = "https://www.cryptocompare.com/media/14913556/srn.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("ZRX")) {
            String url = "https://www.cryptocompare.com/media/1383799/zrx.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("RLC")) {
            String url = "https://www.cryptocompare.com/media/12318418/rlc.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("THETA")) {
            String url = "https://www.cryptocompare.com/media/27010450/theta.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("BCD")) {
            String url = "https://www.cryptocompare.com/media/16404872/bcd.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("OAX")) {
            String url = "https://www.cryptocompare.com/media/1383756/oax.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("ELF")) {
            String url = "https://www.cryptocompare.com/media/20780600/elf.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("INS")) {

        } else if (symbol.equals("ZIL")) {
            String url = "https://www.cryptocompare.com/media/27010464/zil.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("AE")) {
            String url = "https://www.cryptocompare.com/media/1383836/ae.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("PRO")) {
        } else if (symbol.equals("DOGE")) {
            String url = "https://www.cryptocompare.com/media/19684/doge.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("ITC")) {
            String url = "https://www.cryptocompare.com/media/20780628/itc.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("RDD")) {
            String url = "https://www.cryptocompare.com/media/19887/rdd.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("SWFTC")) {
            String url = "https://www.cryptocompare.com/media/27010472/swftc.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("MCO")) {
            String url = "https://www.cryptocompare.com/media/1383653/mco.jpg";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("ARN")) {
            String url = "https://www.cryptocompare.com/media/12318261/arn.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("MTN*")) {
            String url = "https://www.cryptocompare.com/media/27010631/mtn_logo.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("BRD")) {
            String url = "https://www.cryptocompare.com/media/20780589/brd.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("SAN")) {
            String url = "https://www.cryptocompare.com/media/1383730/san.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("NAS")) {
            String url = "https://www.cryptocompare.com/media/20780653/nas.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("GVT")) {
            String url = "https://www.cryptocompare.com/media/14913634/gvt.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("QUN")) {
            String url = "https://www.cryptocompare.com/media/27010466/qun.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("WTC")) {
            String url = "https://www.cryptocompare.com/media/12317959/wtc.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("FCT")) {
            String url = "https://www.cryptocompare.com/media/1382863/fct1.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("CDT")) {
            String url = "https://www.cryptocompare.com/media/1383699/cdt.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        } else if (symbol.equals("RCN")) {
            String url = "https://www.cryptocompare.com/media/12318046/rnc.png";
            Glide.with(this)
                    .load(url)
                    .into(iv_symbol);
        }


        if (change24hr.contains("-")) {
            tv_pct.setText(change24hr + "%");
            tv_pct.setTextColor(Color.parseColor("#FF0000"));
        } else {
            tv_pct.setText(change24hr + "%");
            tv_pct.setTextColor(Color.parseColor("#008000"));
        }

        iv_back.setOnClickListener(this);
        tv_1day.setOnClickListener(this);
        tv_1week.setOnClickListener(this);
        tv_1mnt.setOnClickListener(this);
        tv_1year.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
        iv_un_fav.setOnClickListener(this);
//        iv_fav.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                stopTimer();
                finish();
                break;
            case R.id.tv_1day:
                tv_1day.setBackgroundResource(R.color.light_white);
                tv_1week.setBackgroundResource(R.color.white);
                tv_1mnt.setBackgroundResource(R.color.white);
                tv_1year.setBackgroundResource(R.color.white);
                new apiGet_GraphValue().execute();
                break;
            case R.id.tv_1week:
                tv_1day.setBackgroundResource(R.color.white);
                tv_1week.setBackgroundResource(R.color.light_white);
                tv_1mnt.setBackgroundResource(R.color.white);
                tv_1year.setBackgroundResource(R.color.white);
                new apiGet_WeekGraphValue().execute();
                break;
            case R.id.tv_1mnt:
                tv_1day.setBackgroundResource(R.color.white);
                tv_1week.setBackgroundResource(R.color.white);
                tv_1mnt.setBackgroundResource(R.color.light_white);
                tv_1year.setBackgroundResource(R.color.white);
                new apiGet_MonthGraphValue().execute();
                break;
            case R.id.tv_1year:
                tv_1day.setBackgroundResource(R.color.white);
                tv_1week.setBackgroundResource(R.color.white);
                tv_1mnt.setBackgroundResource(R.color.white);
                tv_1year.setBackgroundResource(R.color.light_white);
                new apiGet_YearGraphValue().execute();
                break;

            case R.id.iv_setting:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    sAux = "\n" +name + " (" +symbol+ ")\n\n";
                    sBux = "Current Price:" + price + "\n";
                    sCux = "Market Cap:" + market_cap + "\n";
                    sDux = "24h Volume:" + volume + "\n\n";
                    sAux = sAux + sBux + sCux + sDux + "Check out more Cryptocurrency Live Price, News, Podcast, Event, and more on CoinsCapMarket App - https://goo.gl/mFXBDn \n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
                break;

            case R.id.iv_un_fav:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(Coins_Detail_Activity.this)) {
                        new AlertTask(symbol).execute();
                        db.addCoins(name, symbol, market_cap, price, volume, change24hr);
                        iv_un_fav.setVisibility(View.GONE);
//                        iv_fav.setVisibility(View.VISIBLE);
                    } else {
                        utils.showAlertMessage(Coins_Detail_Activity.this, getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }
                break;

            case R.id.iv_fav:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(Coins_Detail_Activity.this)) {
                        new DeleteAlertTask(symbol).execute();
                        iv_un_fav.setVisibility(View.VISIBLE);
//                        iv_fav.setVisibility(View.GONE);
                        db.Deleteall(symbol);
                    } else {
                        utils.showAlertMessage(Coins_Detail_Activity.this, getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }
                break;
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public class apiGet_GraphValue extends AsyncTask<Void, Void, Void> {

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
                String Baseurl = "https://min-api.cryptocompare.com/data/histohour?fsym=" + symbol + "&tsym=" + "USD" + "&limit=24&aggregate=1&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Coins_Detail_Activity.this, Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<Graph>) p.o;
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
                chart();

            } else {
                utils.showAlertMessage(Coins_Detail_Activity.this, msg);
            }
        }
    }

//--------------------------------------------------------SET_GRAPH_DATA------------------------------------------------------------


    public void chart() {

        mChart = (LineChart) findViewById(R.id.linechart);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");
        mChart.setNoDataTextDescription(".");


        // add data
        setData();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        mChart.setTouchEnabled(true);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
//        mChart.setVisibleXRangeMaximum(20); // allow 20 values to be displayed at once on the x-axis, not more
        mChart.moveViewToX(10);
        mChart.setVisibleXRangeMaximum(7.0f);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//        leftAxis.setAxisMaxValue(5000f);
//        leftAxis.setAxisMinValue(-50f);
        leftAxis.setTextColor(Color.WHITE);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.setAxisLineColor(Color.WHITE);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);


        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        mChart.animateX(2500);

        //  dont forget to refresh the drawing
        mChart.invalidate();
    }


    private ArrayList<String> setXAxisValues() {

        int time1 = Integer.parseInt(mArrayList.get(0).time);
        int time2 = Integer.parseInt(mArrayList.get(1).time);
        int time3 = Integer.parseInt(mArrayList.get(2).time);
        int time4 = Integer.parseInt(mArrayList.get(3).time);
        int time5 = Integer.parseInt(mArrayList.get(4).time);
        int time6 = Integer.parseInt(mArrayList.get(5).time);
        int time7 = Integer.parseInt(mArrayList.get(6).time);
        int time8 = Integer.parseInt(mArrayList.get(7).time);
        int time9 = Integer.parseInt(mArrayList.get(8).time);
        int time10 = Integer.parseInt(mArrayList.get(9).time);
        int time11 = Integer.parseInt(mArrayList.get(10).time);
        int time12 = Integer.parseInt(mArrayList.get(11).time);
        int time13 = Integer.parseInt(mArrayList.get(12).time);
        int time14 = Integer.parseInt(mArrayList.get(13).time);
        int time15 = Integer.parseInt(mArrayList.get(14).time);
        int time16 = Integer.parseInt(mArrayList.get(15).time);
        int time17 = Integer.parseInt(mArrayList.get(16).time);
        int time18 = Integer.parseInt(mArrayList.get(17).time);
        int time19 = Integer.parseInt(mArrayList.get(18).time);
        int time20 = Integer.parseInt(mArrayList.get(19).time);
        int time21 = Integer.parseInt(mArrayList.get(20).time);
        int time22 = Integer.parseInt(mArrayList.get(21).time);
        int time23 = Integer.parseInt(mArrayList.get(22).time);
        int time24 = Integer.parseInt(mArrayList.get(23).time);
        int time25 = Integer.parseInt(mArrayList.get(24).time);

        Date date = new Date(time1 * 1000L);
        Date date1 = new Date(time2 * 1000L);
        Date date2 = new Date(time3 * 1000L);
        Date date3 = new Date(time4 * 1000L);
        Date date4 = new Date(time5 * 1000L);
        Date date5 = new Date(time6 * 1000L);
        Date date6 = new Date(time7 * 1000L);
        Date date7 = new Date(time8 * 1000L);
        Date date8 = new Date(time9 * 1000L);
        Date date9 = new Date(time10 * 1000L);
        Date date10 = new Date(time11 * 1000L);
        Date date11 = new Date(time12 * 1000L);
        Date date12 = new Date(time13 * 1000L);
        Date date13 = new Date(time14 * 1000L);
        Date date14 = new Date(time15 * 1000L);
        Date date15 = new Date(time16 * 1000L);
        Date date16 = new Date(time17 * 1000L);
        Date date17 = new Date(time18 * 1000L);
        Date date18 = new Date(time19 * 1000L);
        Date date19 = new Date(time20 * 1000L);
        Date date20 = new Date(time21 * 1000L);
        Date date21 = new Date(time22 * 1000L);
        Date date22 = new Date(time23 * 1000L);
        Date date23 = new Date(time24 * 1000L);
        Date date24 = new Date(time25 * 1000L);

        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        String formatted1 = format.format(date1);
        String formatted2 = format.format(date2);
        String formatted3 = format.format(date3);
        String formatted4 = format.format(date4);
        String formatted5 = format.format(date5);
        String formatted6 = format.format(date6);
        String formatted7 = format.format(date7);
        String formatted8 = format.format(date8);
        String formatted9 = format.format(date9);
        String formatted10 = format.format(date10);
        String formatted11 = format.format(date11);
        String formatted12 = format.format(date12);
        String formatted13 = format.format(date13);
        String formatted14 = format.format(date14);
        String formatted15 = format.format(date15);
        String formatted16 = format.format(date16);
        String formatted17 = format.format(date17);
        String formatted18 = format.format(date18);
        String formatted19 = format.format(date19);
        String formatted20 = format.format(date20);
        String formatted21 = format.format(date21);
        String formatted22 = format.format(date22);
        String formatted23 = format.format(date23);
        String formatted24 = format.format(date24);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(formatted);
        xVals.add(formatted1);
        xVals.add(formatted2);
        xVals.add(formatted3);
        xVals.add(formatted4);
        xVals.add(formatted5);
        xVals.add(formatted6);
        xVals.add(formatted7);
        xVals.add(formatted8);
        xVals.add(formatted9);
        xVals.add(formatted10);
        xVals.add(formatted11);
        xVals.add(formatted12);
        xVals.add(formatted13);
        xVals.add(formatted14);
        xVals.add(formatted15);
        xVals.add(formatted16);
        xVals.add(formatted17);
        xVals.add(formatted18);
        xVals.add(formatted19);
        xVals.add(formatted20);
        xVals.add(formatted21);
        xVals.add(formatted22);
        xVals.add(formatted23);
        xVals.add(formatted24);

        return xVals;
    }

    private ArrayList<Entry> setYAxisValues() {
        float c_price = Float.parseFloat(mArrayList.get(0).close);
        float c_price1 = Float.parseFloat(mArrayList.get(1).close);
        float c_price2 = Float.parseFloat(mArrayList.get(2).close);
        float c_price3 = Float.parseFloat(mArrayList.get(3).close);
        float c_price4 = Float.parseFloat(mArrayList.get(4).close);
        float c_price5 = Float.parseFloat(mArrayList.get(5).close);
        float c_price6 = Float.parseFloat(mArrayList.get(6).close);
        float c_price7 = Float.parseFloat(mArrayList.get(7).close);
        float c_price8 = Float.parseFloat(mArrayList.get(8).close);
        float c_price9 = Float.parseFloat(mArrayList.get(9).close);
        float c_price10 = Float.parseFloat(mArrayList.get(10).close);
        float c_price11 = Float.parseFloat(mArrayList.get(11).close);
        float c_price12 = Float.parseFloat(mArrayList.get(12).close);
        float c_price13 = Float.parseFloat(mArrayList.get(13).close);
        float c_price14 = Float.parseFloat(mArrayList.get(14).close);
        float c_price15 = Float.parseFloat(mArrayList.get(15).close);
        float c_price16 = Float.parseFloat(mArrayList.get(16).close);
        float c_price17 = Float.parseFloat(mArrayList.get(17).close);
        float c_price18 = Float.parseFloat(mArrayList.get(18).close);
        float c_price19 = Float.parseFloat(mArrayList.get(19).close);
        float c_price20 = Float.parseFloat(mArrayList.get(20).close);
        float c_price21 = Float.parseFloat(mArrayList.get(21).close);
        float c_price22 = Float.parseFloat(mArrayList.get(22).close);
        float c_price23 = Float.parseFloat(mArrayList.get(23).close);
        float c_price24 = Float.parseFloat(mArrayList.get(24).close);

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(c_price, 0));
        yVals.add(new Entry(c_price1, 1));
        yVals.add(new Entry(c_price2, 2));
        yVals.add(new Entry(c_price3, 3));
        yVals.add(new Entry(c_price4, 4));
        yVals.add(new Entry(c_price5, 5));
        yVals.add(new Entry(c_price6, 6));
        yVals.add(new Entry(c_price7, 7));
        yVals.add(new Entry(c_price8, 8));
        yVals.add(new Entry(c_price9, 9));
        yVals.add(new Entry(c_price10, 10));
        yVals.add(new Entry(c_price11, 11));
        yVals.add(new Entry(c_price12, 12));
        yVals.add(new Entry(c_price13, 13));
        yVals.add(new Entry(c_price14, 14));
        yVals.add(new Entry(c_price15, 15));
        yVals.add(new Entry(c_price16, 16));
        yVals.add(new Entry(c_price17, 17));
        yVals.add(new Entry(c_price18, 18));
        yVals.add(new Entry(c_price19, 19));
        yVals.add(new Entry(c_price20, 20));
        yVals.add(new Entry(c_price21, 21));
        yVals.add(new Entry(c_price22, 22));
        yVals.add(new Entry(c_price23, 23));
        yVals.add(new Entry(c_price24, 24));

        return yVals;
    }

    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "");

//        set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setValueTextColor(Color.WHITE);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(7f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);

    }


//-----------------------------------------------WEEK_GRAPH--------------------------------------------------------------


    public class apiGet_WeekGraphValue extends AsyncTask<Void, Void, Void> {

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
                String Baseurl = "https://min-api.cryptocompare.com/data/histoday?fsym=" + symbol + "&tsym=" + "USD" + "&limit=7&aggregate=1&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Coins_Detail_Activity.this, Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<Graph>) p.o;
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
                Weekchart();

            } else {
                utils.showAlertMessage(Coins_Detail_Activity.this, msg);
            }
        }
    }


    public void Weekchart() {

        mChart = (LineChart) findViewById(R.id.linechart);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");
        mChart.setNoDataTextDescription(".");


        // add data
        WeeksetData();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
//        mChart.setVisibleXRangeMaximum(20); // allow 20 values to be displayed at once on the x-axis, not more
        mChart.moveViewToX(10);
        mChart.setVisibleXRangeMaximum(7.0f);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.setAxisLineColor(Color.WHITE);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);


        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);

        mChart.animateX(2500);

        //  dont forget to refresh the drawing
        mChart.invalidate();
    }


    private void WeeksetData() {
        ArrayList<String> xVals = setWeekXAxisValues();

        ArrayList<Entry> yVals = setWeekYAxisValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "");

        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setValueTextColor(Color.WHITE);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(7f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);

    }


    private ArrayList<String> setWeekXAxisValues() {

        int time1 = Integer.parseInt(mArrayList.get(0).time);
        int time2 = Integer.parseInt(mArrayList.get(1).time);
        int time3 = Integer.parseInt(mArrayList.get(2).time);
        int time4 = Integer.parseInt(mArrayList.get(3).time);
        int time5 = Integer.parseInt(mArrayList.get(4).time);
        int time6 = Integer.parseInt(mArrayList.get(5).time);
        int time7 = Integer.parseInt(mArrayList.get(6).time);
        int time8 = Integer.parseInt(mArrayList.get(7).time);

        Date date = new Date(time1 * 1000L);
        Date date1 = new Date(time2 * 1000L);
        Date date2 = new Date(time3 * 1000L);
        Date date3 = new Date(time4 * 1000L);
        Date date4 = new Date(time5 * 1000L);
        Date date5 = new Date(time6 * 1000L);
        Date date6 = new Date(time7 * 1000L);
        Date date7 = new Date(time8 * 1000L);


        DateFormat format = new SimpleDateFormat("MMM dd");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        String formatted1 = format.format(date1);
        String formatted2 = format.format(date2);
        String formatted3 = format.format(date3);
        String formatted4 = format.format(date4);
        String formatted5 = format.format(date5);
        String formatted6 = format.format(date6);
        String formatted7 = format.format(date7);


        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(formatted);
        xVals.add(formatted1);
        xVals.add(formatted2);
        xVals.add(formatted3);
        xVals.add(formatted4);
        xVals.add(formatted5);
        xVals.add(formatted6);
        xVals.add(formatted7);


        return xVals;
    }

    private ArrayList<Entry> setWeekYAxisValues() {
        float c_price = Float.parseFloat(mArrayList.get(0).close);
        float c_price1 = Float.parseFloat(mArrayList.get(1).close);
        float c_price2 = Float.parseFloat(mArrayList.get(2).close);
        float c_price3 = Float.parseFloat(mArrayList.get(3).close);
        float c_price4 = Float.parseFloat(mArrayList.get(4).close);
        float c_price5 = Float.parseFloat(mArrayList.get(5).close);
        float c_price6 = Float.parseFloat(mArrayList.get(6).close);
        float c_price7 = Float.parseFloat(mArrayList.get(7).close);

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(c_price, 0));
        yVals.add(new Entry(c_price1, 1));
        yVals.add(new Entry(c_price2, 2));
        yVals.add(new Entry(c_price3, 3));
        yVals.add(new Entry(c_price4, 4));
        yVals.add(new Entry(c_price5, 5));
        yVals.add(new Entry(c_price6, 6));
        yVals.add(new Entry(c_price7, 7));


        return yVals;
    }


    //-----------------------------------------------Month_GRAPH--------------------------------------------------------------


    public class apiGet_MonthGraphValue extends AsyncTask<Void, Void, Void> {

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
                String Baseurl = "https://min-api.cryptocompare.com/data/histoday?fsym=" + symbol + "&tsym=" + "USD" + "&limit=30&aggregate=1&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Coins_Detail_Activity.this, Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<Graph>) p.o;
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
                Monthchart();
            } else {
                utils.showAlertMessage(Coins_Detail_Activity.this, msg);
            }
        }
    }


    public void Monthchart() {

        mChart = (LineChart) findViewById(R.id.linechart);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");
        mChart.setNoDataTextDescription(".");
        // add data
        MonthsetData();
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);
        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
//        mChart.setVisibleXRangeMaximum(20); // allow 20 values to be displayed at once on the x-axis, not more
        mChart.moveViewToX(10);
        mChart.setVisibleXRangeMaximum(7.0f);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//        leftAxis.setAxisMaxValue(5000f);
//        leftAxis.setAxisMinValue(-50f);
        leftAxis.setTextColor(Color.WHITE);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.setAxisLineColor(Color.WHITE);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);


        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        mChart.animateX(2500);

        //  dont forget to refresh the drawing
        mChart.invalidate();
    }


    private void MonthsetData() {
        ArrayList<String> xVals = setMonthXAxisValues();

        ArrayList<Entry> yVals = setMonthYAxisValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "");

//        set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setValueTextColor(Color.WHITE);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(7f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);

    }


    private ArrayList<String> setMonthXAxisValues() {

        int time1 = Integer.parseInt(mArrayList.get(0).time);
        int time2 = Integer.parseInt(mArrayList.get(1).time);
        int time3 = Integer.parseInt(mArrayList.get(2).time);
        int time4 = Integer.parseInt(mArrayList.get(3).time);
        int time5 = Integer.parseInt(mArrayList.get(4).time);
        int time6 = Integer.parseInt(mArrayList.get(5).time);
        int time7 = Integer.parseInt(mArrayList.get(6).time);
        int time8 = Integer.parseInt(mArrayList.get(7).time);
        int time9 = Integer.parseInt(mArrayList.get(8).time);
        int time10 = Integer.parseInt(mArrayList.get(9).time);
        int time11 = Integer.parseInt(mArrayList.get(10).time);
        int time12 = Integer.parseInt(mArrayList.get(11).time);
        int time13 = Integer.parseInt(mArrayList.get(12).time);
        int time14 = Integer.parseInt(mArrayList.get(13).time);
        int time15 = Integer.parseInt(mArrayList.get(14).time);
        int time16 = Integer.parseInt(mArrayList.get(15).time);
        int time17 = Integer.parseInt(mArrayList.get(16).time);
        int time18 = Integer.parseInt(mArrayList.get(17).time);
        int time19 = Integer.parseInt(mArrayList.get(18).time);
        int time20 = Integer.parseInt(mArrayList.get(19).time);
        int time21 = Integer.parseInt(mArrayList.get(20).time);
        int time22 = Integer.parseInt(mArrayList.get(21).time);
        int time23 = Integer.parseInt(mArrayList.get(22).time);
        int time24 = Integer.parseInt(mArrayList.get(23).time);
        int time25 = Integer.parseInt(mArrayList.get(24).time);
        int time26 = Integer.parseInt(mArrayList.get(25).time);
        int time27 = Integer.parseInt(mArrayList.get(26).time);
        int time28 = Integer.parseInt(mArrayList.get(27).time);
        int time29 = Integer.parseInt(mArrayList.get(28).time);
        int time30 = Integer.parseInt(mArrayList.get(29).time);
        int time31 = Integer.parseInt(mArrayList.get(30).time);

        Date date = new Date(time1 * 1000L);
        Date date1 = new Date(time2 * 1000L);
        Date date2 = new Date(time3 * 1000L);
        Date date3 = new Date(time4 * 1000L);
        Date date4 = new Date(time5 * 1000L);
        Date date5 = new Date(time6 * 1000L);
        Date date6 = new Date(time7 * 1000L);
        Date date7 = new Date(time8 * 1000L);
        Date date8 = new Date(time9 * 1000L);
        Date date9 = new Date(time10 * 1000L);
        Date date10 = new Date(time11 * 1000L);
        Date date11 = new Date(time12 * 1000L);
        Date date12 = new Date(time13 * 1000L);
        Date date13 = new Date(time14 * 1000L);
        Date date14 = new Date(time15 * 1000L);
        Date date15 = new Date(time16 * 1000L);
        Date date16 = new Date(time17 * 1000L);
        Date date17 = new Date(time18 * 1000L);
        Date date18 = new Date(time19 * 1000L);
        Date date19 = new Date(time20 * 1000L);
        Date date20 = new Date(time21 * 1000L);
        Date date21 = new Date(time22 * 1000L);
        Date date22 = new Date(time23 * 1000L);
        Date date23 = new Date(time24 * 1000L);
        Date date24 = new Date(time25 * 1000L);
        Date date25 = new Date(time26 * 1000L);
        Date date26 = new Date(time27 * 1000L);
        Date date27 = new Date(time28 * 1000L);
        Date date28 = new Date(time29 * 1000L);
        Date date29 = new Date(time30 * 1000L);
        Date date30 = new Date(time31 * 1000L);


        DateFormat format = new SimpleDateFormat("MMM dd");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        String formatted1 = format.format(date1);
        String formatted2 = format.format(date2);
        String formatted3 = format.format(date3);
        String formatted4 = format.format(date4);
        String formatted5 = format.format(date5);
        String formatted6 = format.format(date6);
        String formatted7 = format.format(date7);
        String formatted8 = format.format(date8);
        String formatted9 = format.format(date9);
        String formatted10 = format.format(date10);
        String formatted11 = format.format(date11);
        String formatted12 = format.format(date12);
        String formatted13 = format.format(date13);
        String formatted14 = format.format(date14);
        String formatted15 = format.format(date15);
        String formatted16 = format.format(date16);
        String formatted17 = format.format(date17);
        String formatted18 = format.format(date18);
        String formatted19 = format.format(date19);
        String formatted20 = format.format(date20);
        String formatted21 = format.format(date21);
        String formatted22 = format.format(date22);
        String formatted23 = format.format(date23);
        String formatted24 = format.format(date24);
        String formatted25 = format.format(date25);
        String formatted26 = format.format(date26);
        String formatted27 = format.format(date27);
        String formatted28 = format.format(date28);
        String formatted29 = format.format(date29);
        String formatted30 = format.format(date30);


        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(formatted);
        xVals.add(formatted1);
        xVals.add(formatted2);
        xVals.add(formatted3);
        xVals.add(formatted4);
        xVals.add(formatted5);
        xVals.add(formatted6);
        xVals.add(formatted7);
        xVals.add(formatted8);
        xVals.add(formatted9);
        xVals.add(formatted10);
        xVals.add(formatted11);
        xVals.add(formatted12);
        xVals.add(formatted13);
        xVals.add(formatted14);
        xVals.add(formatted15);
        xVals.add(formatted16);
        xVals.add(formatted17);
        xVals.add(formatted18);
        xVals.add(formatted19);
        xVals.add(formatted20);
        xVals.add(formatted21);
        xVals.add(formatted22);
        xVals.add(formatted23);
        xVals.add(formatted24);
        xVals.add(formatted25);
        xVals.add(formatted26);
        xVals.add(formatted27);
        xVals.add(formatted28);
        xVals.add(formatted29);
        xVals.add(formatted30);


        return xVals;
    }

    private ArrayList<Entry> setMonthYAxisValues() {
        float c_price = Float.parseFloat(mArrayList.get(0).close);
        float c_price1 = Float.parseFloat(mArrayList.get(1).close);
        float c_price2 = Float.parseFloat(mArrayList.get(2).close);
        float c_price3 = Float.parseFloat(mArrayList.get(3).close);
        float c_price4 = Float.parseFloat(mArrayList.get(4).close);
        float c_price5 = Float.parseFloat(mArrayList.get(5).close);
        float c_price6 = Float.parseFloat(mArrayList.get(6).close);
        float c_price7 = Float.parseFloat(mArrayList.get(7).close);
        float c_price8 = Float.parseFloat(mArrayList.get(8).close);
        float c_price9 = Float.parseFloat(mArrayList.get(9).close);
        float c_price10 = Float.parseFloat(mArrayList.get(10).close);
        float c_price11 = Float.parseFloat(mArrayList.get(11).close);
        float c_price12 = Float.parseFloat(mArrayList.get(12).close);
        float c_price13 = Float.parseFloat(mArrayList.get(13).close);
        float c_price14 = Float.parseFloat(mArrayList.get(14).close);
        float c_price15 = Float.parseFloat(mArrayList.get(15).close);
        float c_price16 = Float.parseFloat(mArrayList.get(16).close);
        float c_price17 = Float.parseFloat(mArrayList.get(17).close);
        float c_price18 = Float.parseFloat(mArrayList.get(18).close);
        float c_price19 = Float.parseFloat(mArrayList.get(19).close);
        float c_price20 = Float.parseFloat(mArrayList.get(20).close);
        float c_price21 = Float.parseFloat(mArrayList.get(21).close);
        float c_price22 = Float.parseFloat(mArrayList.get(22).close);
        float c_price23 = Float.parseFloat(mArrayList.get(23).close);
        float c_price24 = Float.parseFloat(mArrayList.get(24).close);
        float c_price25 = Float.parseFloat(mArrayList.get(25).close);
        float c_price26 = Float.parseFloat(mArrayList.get(26).close);
        float c_price27 = Float.parseFloat(mArrayList.get(27).close);
        float c_price28 = Float.parseFloat(mArrayList.get(28).close);
        float c_price29 = Float.parseFloat(mArrayList.get(29).close);
        float c_price30 = Float.parseFloat(mArrayList.get(30).close);


        ArrayList<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(c_price, 0));
        yVals.add(new Entry(c_price1, 1));
        yVals.add(new Entry(c_price2, 2));
        yVals.add(new Entry(c_price3, 3));
        yVals.add(new Entry(c_price4, 4));
        yVals.add(new Entry(c_price5, 5));
        yVals.add(new Entry(c_price6, 6));
        yVals.add(new Entry(c_price7, 7));
        yVals.add(new Entry(c_price8, 8));
        yVals.add(new Entry(c_price9, 9));
        yVals.add(new Entry(c_price10, 10));
        yVals.add(new Entry(c_price11, 11));
        yVals.add(new Entry(c_price12, 12));
        yVals.add(new Entry(c_price13, 13));
        yVals.add(new Entry(c_price14, 14));
        yVals.add(new Entry(c_price15, 15));
        yVals.add(new Entry(c_price16, 16));
        yVals.add(new Entry(c_price17, 17));
        yVals.add(new Entry(c_price18, 18));
        yVals.add(new Entry(c_price19, 19));
        yVals.add(new Entry(c_price20, 20));
        yVals.add(new Entry(c_price21, 21));
        yVals.add(new Entry(c_price22, 22));
        yVals.add(new Entry(c_price23, 23));
        yVals.add(new Entry(c_price24, 24));
        yVals.add(new Entry(c_price25, 25));
        yVals.add(new Entry(c_price26, 26));
        yVals.add(new Entry(c_price27, 27));
        yVals.add(new Entry(c_price28, 28));
        yVals.add(new Entry(c_price29, 29));
        yVals.add(new Entry(c_price30, 30));


        return yVals;
    }


    //-----------------------------------------------YEAR_GRAPH--------------------------------------------------------------


    public class apiGet_YearGraphValue extends AsyncTask<Void, Void, Void> {

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
                String Baseurl = "https://min-api.cryptocompare.com/data/histoday?fsym=" + symbol + "&tsym=" + "USD" + "&limit=12&aggregate=30&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Coins_Detail_Activity.this, Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<Graph>) p.o;
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
                Yearchart();
            } else {
                utils.showAlertMessage(Coins_Detail_Activity.this, msg);
            }
        }
    }


    public void Yearchart() {

        mChart = (LineChart) findViewById(R.id.linechart);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);

        // add data
        YearsetData();
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription(".");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
//        mChart.setVisibleXRangeMaximum(20); // allow 20 values to be displayed at once on the x-axis, not more
        mChart.moveViewToX(10);
        mChart.setVisibleXRangeMaximum(7.0f);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//        leftAxis.setAxisMaxValue(5000f);
//        leftAxis.setAxisMinValue(-50f);
        leftAxis.setTextColor(Color.WHITE);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.setAxisLineColor(Color.WHITE);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);


        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        mChart.animateX(2500);

        //  dont forget to refresh the drawing
        mChart.invalidate();
    }


    private void YearsetData() {
        ArrayList<String> xVals = setYearXAxisValues();

        ArrayList<Entry> yVals = setYearYAxisValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "");

//        set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setValueTextColor(Color.WHITE);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(7f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);

    }


    private ArrayList<String> setYearXAxisValues() {

        int time1 = Integer.parseInt(mArrayList.get(0).time);
        int time2 = Integer.parseInt(mArrayList.get(1).time);
        int time3 = Integer.parseInt(mArrayList.get(2).time);
        int time4 = Integer.parseInt(mArrayList.get(3).time);
        int time5 = Integer.parseInt(mArrayList.get(4).time);
        int time6 = Integer.parseInt(mArrayList.get(5).time);
        int time7 = Integer.parseInt(mArrayList.get(6).time);
        int time8 = Integer.parseInt(mArrayList.get(7).time);
        int time9 = Integer.parseInt(mArrayList.get(8).time);
        int time10 = Integer.parseInt(mArrayList.get(9).time);
        int time11 = Integer.parseInt(mArrayList.get(10).time);
        int time12 = Integer.parseInt(mArrayList.get(11).time);
        int time13 = Integer.parseInt(mArrayList.get(12).time);


        Date date = new Date(time1 * 1000L);
        Date date1 = new Date(time2 * 1000L);
        Date date2 = new Date(time3 * 1000L);
        Date date3 = new Date(time4 * 1000L);
        Date date4 = new Date(time5 * 1000L);
        Date date5 = new Date(time6 * 1000L);
        Date date6 = new Date(time7 * 1000L);
        Date date7 = new Date(time8 * 1000L);
        Date date8 = new Date(time9 * 1000L);
        Date date9 = new Date(time10 * 1000L);
        Date date10 = new Date(time11 * 1000L);
        Date date11 = new Date(time12 * 1000L);
        Date date12 = new Date(time13 * 1000L);


        DateFormat format = new SimpleDateFormat("MMM");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        String formatted1 = format.format(date1);
        String formatted2 = format.format(date2);
        String formatted3 = format.format(date3);
        String formatted4 = format.format(date4);
        String formatted5 = format.format(date5);
        String formatted6 = format.format(date6);
        String formatted7 = format.format(date7);
        String formatted8 = format.format(date8);
        String formatted9 = format.format(date9);
        String formatted10 = format.format(date10);
        String formatted11 = format.format(date11);
        String formatted12 = format.format(date12);


        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(formatted);
        xVals.add(formatted1);
        xVals.add(formatted2);
        xVals.add(formatted3);
        xVals.add(formatted4);
        xVals.add(formatted5);
        xVals.add(formatted6);
        xVals.add(formatted7);
        xVals.add(formatted8);
        xVals.add(formatted9);
        xVals.add(formatted10);
        xVals.add(formatted11);
        xVals.add(formatted12);


        return xVals;
    }

    private ArrayList<Entry> setYearYAxisValues() {
        float c_price = Float.parseFloat(mArrayList.get(0).close);
        float c_price1 = Float.parseFloat(mArrayList.get(1).close);
        float c_price2 = Float.parseFloat(mArrayList.get(2).close);
        float c_price3 = Float.parseFloat(mArrayList.get(3).close);
        float c_price4 = Float.parseFloat(mArrayList.get(4).close);
        float c_price5 = Float.parseFloat(mArrayList.get(5).close);
        float c_price6 = Float.parseFloat(mArrayList.get(6).close);
        float c_price7 = Float.parseFloat(mArrayList.get(7).close);
        float c_price8 = Float.parseFloat(mArrayList.get(8).close);
        float c_price9 = Float.parseFloat(mArrayList.get(9).close);
        float c_price10 = Float.parseFloat(mArrayList.get(10).close);
        float c_price11 = Float.parseFloat(mArrayList.get(11).close);
        float c_price12 = Float.parseFloat(mArrayList.get(12).close);


        ArrayList<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(c_price, 0));
        yVals.add(new Entry(c_price1, 1));
        yVals.add(new Entry(c_price2, 2));
        yVals.add(new Entry(c_price3, 3));
        yVals.add(new Entry(c_price4, 4));
        yVals.add(new Entry(c_price5, 5));
        yVals.add(new Entry(c_price6, 6));
        yVals.add(new Entry(c_price7, 7));
        yVals.add(new Entry(c_price8, 8));
        yVals.add(new Entry(c_price9, 9));
        yVals.add(new Entry(c_price10, 10));
        yVals.add(new Entry(c_price11, 11));
        yVals.add(new Entry(c_price12, 12));


        return yVals;
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
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=" + symbol + "&tsyms=" + "USD" + "&extraParams=your_app_name";
                ParsedResponse p = Service.apiGetOneCoin(Coins_Detail_Activity.this, Baseurl, symbol);
                error = p.error;
                if (!error) {
                    update_coin = (ArrayList<Update_Coin>) p.o;
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
                for (int i = 0; i < update_coin.size(); i++) {
                    tv_price.setText(update_coin.get(i).PRICE);
                    tv_market_cap.setText(update_coin.get(i).MKTCAP);
                    tv_24_volume.setText(update_coin.get(i).CHANGE24HOUR);

                    if (update_coin.get(i).CHANGEPCT24HOUR.contains("-")) {
                        tv_pct.setText(update_coin.get(i).CHANGE24HOUR + " (" + update_coin.get(i).CHANGEPCT24HOUR + "%)");
                        tv_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_pct.setText(update_coin.get(i).CHANGE24HOUR + " (" + update_coin.get(i).CHANGEPCT24HOUR + "%)");
                        tv_pct.setTextColor(Color.parseColor("#008000"));
                    }
                }

            } else {
            }
        }
    }





    /* Initialize popup dialog view and ui controls in the popup dialog. */
    private void loginPopupViewControls() {
        final Dialog dialog = new Dialog(Coins_Detail_Activity.this);
        dialog.setContentView(R.layout.popup_login_dialog);

        FrameLayout FrameLayout2 = dialog.findViewById(R.id.FrameLayout2);
        google_login = (Button) dialog.findViewById(R.id.google_login);
        google_logout = (Button) dialog.findViewById(R.id.google_logout);

        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (utils.isNetworkAvailable(Coins_Detail_Activity.this)) {
                    dialog.dismiss();
                    signIn();
                } else {
                    utils.showAlertMessage(Coins_Detail_Activity.this, getString(R.string.err_no_internet));
                }
            }
        });
        dialog.show();
    }


    /*-------------------------------------------------------------------Google_Sign_in----------------------------------*/

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }

    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();

            new socialLoginTask(acct.getId(), acct.getDisplayName(), acct.getEmail(), acct.getPhotoUrl().toString()).execute();

        } else {
            Log.e("Signed out", "unauthenticated UI");
        }
    }




    // -----------------------------------------Api socialLogin-------------------------------------------------------------------


    public class socialLoginTask extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;
        private String google_id = "", name = "", email = "", picture = "";

        public socialLoginTask(String google_id, String name, String email, String picture) {
            this.name = name;
            this.email = email;
            this.picture = picture;
            this.google_id = google_id;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                ParsedResponse p = Service.apiLogin(Coins_Detail_Activity.this, name, email, picture, google_id, "0", device_id);
                error = p.error;
                if (!error) {
                    msg = (String) p.o;
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
                SharedPreferences.Editor e = preferences.edit();
                e.putBoolean(General.PREFS_IsLogin, true);
                e.apply();
            } else {
                utils.showAlertMessage(Coins_Detail_Activity.this, msg);
            }
        }
    }



    /*--------------------------------------------------------SET_ALERT--------------------------------------------------------------*/


    public class AlertTask extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, coin_symbol = "";

        public AlertTask(String coin_symbol) {
            this.coin_symbol = coin_symbol;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String user_id = preferences.getString(General.PREFS_User_id, "");
            try {
                ParsedResponse p = Service.apiAlert(Coins_Detail_Activity.this, user_id, coin_symbol, "0", "0", device_id);
                error = p.error;
                if (!error) {
                    msg = (String) p.o;
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
                SharedPreferences.Editor e = preferences.edit();
                e.putBoolean(General.PREFS_IsLogin, true);
                e.apply();
            } else {
                utils.showAlertMessage(Coins_Detail_Activity.this, msg);
            }
        }
    }



    /*--------------------------------------------------------DELETE_ALERT--------------------------------------------------------------*/


    public class DeleteAlertTask extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, coin_symbol = "";


        public DeleteAlertTask(String coin_symbol) {
            this.coin_symbol = coin_symbol;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
           String user_id = preferences.getString(General.PREFS_User_id, "");
            try {
                ParsedResponse p = Service.apiDeleteAlert(Coins_Detail_Activity.this, user_id, coin_symbol, "1", "0", device_id);
                error = p.error;
                if (!error) {
                    msg = (String) p.o;
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
                SharedPreferences.Editor e = preferences.edit();
                e.putBoolean(General.PREFS_IsLogin, true);
                e.apply();
            } else {
                utils.showAlertMessage(Coins_Detail_Activity.this, msg);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(Coins_Detail_Activity.this);
        mGoogleApiClient.disconnect();
    }
}
