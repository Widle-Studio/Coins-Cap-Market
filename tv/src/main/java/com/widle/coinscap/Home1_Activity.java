package com.widle.coinscap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
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

import org.json.JSONException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import app.com.tvrecyclerview.TvRecyclerView;
import com.widle.coinscap.Adapter.Home_Adapter;
import com.widle.coinscap.Model.Graph;
import com.widle.coinscap.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Home1_Activity extends Activity implements OnChartGestureListener,
        OnChartValueSelectedListener {

    private TvRecyclerView mTvRecyclerView;

    ArrayList<String> stringList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    ArrayList<coin> mArrayList = new ArrayList<>();
    ArrayList<Graph> mGraphArrayList = new ArrayList<>();

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();

    private LineChart mChart;

    private TextView tv_day, tv_week, tv_month, tv_year;

    Parcelable recylerViewState;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        init();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void init(){

        stringList.add("BTC");
        stringList.add("ETH");
        stringList.add("BCH");
        stringList.add("XRP");
        stringList.add("LTC");
        stringList.add("ADA");
        stringList.add("IOT");
        stringList.add("DASH");
        stringList.add("XEM");
        stringList.add("XMR");
        stringList.add("EOS");
        stringList.add("BTG");
        stringList.add("QTUM");
        stringList.add("XLM");
        stringList.add("NEO");
        stringList.add("ETC");
        stringList.add("ZEC");
        stringList.add("STEEM");
        stringList.add("XZC");
        stringList.add("STORJ");
        stringList.add("AION");
        stringList.add("HT");
        stringList.add("TRX");
        stringList.add("XRB");
        stringList.add("OMG");
        stringList.add("ELA");
        stringList.add("BNB");
        stringList.add("VEN");
        stringList.add("ZCL");
        stringList.add("DGD");
        stringList.add("OCN");
        stringList.add("BCPT");
        stringList.add("LUN");
        stringList.add("IOST");
        stringList.add("HSR");
        stringList.add("ICX");
        stringList.add("LSK");
        stringList.add("NEBL");
        stringList.add("WAVES");
        stringList.add("BLZ");
        stringList.add("INK");
        stringList.add("ADX");
        stringList.add("XVG");
        stringList.add("MTL");
        stringList.add("SRN");
        stringList.add("ZRX");
        stringList.add("RLC");
        stringList.add("THETA");
        stringList.add("BCD");
        stringList.add("OAX");
        stringList.add("ELF");
        stringList.add("INS");
        stringList.add("ZIL");
        stringList.add("AE");
        stringList.add("PRO");
        stringList.add("DOGE");
        stringList.add("ITC");
        stringList.add("ROD");
        stringList.add("SWFTC");
        stringList.add("MCO");
        stringList.add("ARN");
        stringList.add("MTN*");
        stringList.add("BRD");
        stringList.add("SAN");
        stringList.add("NAS");
        stringList.add("GVT");
        stringList.add("QUN");
        stringList.add("WTC");
        stringList.add("FCT");
        stringList.add("CDT");
        stringList.add("RCN");


        nameList.add("Bitcoin");
        nameList.add("Ethereum");
        nameList.add("Bitcoin Cash");
        nameList.add("Ripple");
        nameList.add("Litecoin");
        nameList.add("Cardano");
        nameList.add("IOTA");
        nameList.add("DigitalCash");
        nameList.add("NEM");
        nameList.add("Monero");
        nameList.add("EOS");
        nameList.add("Bitcoin Gold");
        nameList.add("Qtum");
        nameList.add("Stellar");
        nameList.add("NEO");
        nameList.add("Ethereum Classic");
        nameList.add("Zcash");
        nameList.add("Steem");
        nameList.add("ZCoin");
        nameList.add("Storj");
        nameList.add("Aion");
        nameList.add("Huobi Token");
        nameList.add("Tronix");
        nameList.add("Nano");
        nameList.add("OmiseGo");
        nameList.add("Elastos");
        nameList.add("Binance Coin");
        nameList.add("Vechain");
        nameList.add("ZClassic");
        nameList.add("Digix DAO");
        nameList.add("Odyssey");
        nameList.add("BlockMason Credit Protocol");
        nameList.add("Lunyr");
        nameList.add("IOS token");
        nameList.add("Hshare");
        nameList.add("ICON Project");
        nameList.add("Lisk");
        nameList.add("Neblio");
        nameList.add("Waves");
        nameList.add("Bluzelle");
        nameList.add("Ink");
        nameList.add("AdEx");
        nameList.add("Verge");
        nameList.add("Metal");
        nameList.add("SirinLabs");
        nameList.add("0x");
        nameList.add("iEx.ec");
        nameList.add("Theta");
        nameList.add("Bitcoin Diamond");
        nameList.add("OpenANX");
        nameList.add("aelf");
        nameList.add("INS Ecosystem");
        nameList.add("Zilliqa");
        nameList.add("Aeternity");
        nameList.add("Propy");
        nameList.add("Dogecoin");
        nameList.add("IoT Chain");
        nameList.add("ReddCoin");
        nameList.add("SwftCoin");
        nameList.add("Monaco");
        nameList.add("Aeron");
        nameList.add("Medicalchain");
        nameList.add("Bread token");
        nameList.add("Santiment");
        nameList.add("Nebulas");
        nameList.add("Genesis Vision");
        nameList.add("QunQun");
        nameList.add("Waltonchain");
        nameList.add("Factoids");
        nameList.add("CoinDash");
        nameList.add("Ripio");



        mTvRecyclerView = (TvRecyclerView) findViewById(R.id.tv_recycler_view);

        tv_day = findViewById(R.id.tv_day);
        tv_week = findViewById(R.id.tv_week);
        tv_month = findViewById(R.id.tv_month);
        tv_year = findViewById(R.id.tv_year);
//
        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_day.setTypeface(type);
        tv_week.setTypeface(type);
        tv_month.setTypeface(type);
        tv_year.setTypeface(type);

        mTvRecyclerView.setLayoutManager(new LinearLayoutManager(Home1_Activity.this, LinearLayoutManager.VERTICAL, false));
//
        recylerViewState = mTvRecyclerView.getLayoutManager().onSaveInstanceState();

        int itemSpace = 18;
        mTvRecyclerView.addItemDecoration(new SpaceItemDecoration(itemSpace));
        DefaultItemAnimator animator = new DefaultItemAnimator();
        mTvRecyclerView.setItemAnimator(animator);

        mTvRecyclerView.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
            @Override
            public void onItemViewClick(View view, int position) {

                new apiGet_GraphValue(stringList.get(position)).execute();
                new apiGet_WeekGraphValue(stringList.get(position)).execute();
                new apiGet_MonthGraphValue(stringList.get(position)).execute();
                new apiGet_YearGraphValue(stringList.get(position)).execute();

            }

            @Override
            public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {

            }
        });


        if (utils.isNetworkAvailable(Home1_Activity.this)) {
            new apiGet_Coins().execute();
        } else {
            utils.showAlertMessage(Home1_Activity.this, getString(R.string.err_no_internet));
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
                ParsedResponse p = Service.apiGetCoin(Home1_Activity.this, Baseurl);
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
            if (!error) {

                Home_Adapter home_adapter = new Home_Adapter(Home1_Activity.this, nameList, stringList, mArrayList);
                mTvRecyclerView.setAdapter(home_adapter);

                update();

            } else {
                utils.showAlertMessage(Home1_Activity.this, msg);
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

                        if (utils.isNetworkAvailable(Home1_Activity.this)) {
                            recylerViewState = mTvRecyclerView.getLayoutManager().onSaveInstanceState();
                            new apiGet_Update_Coins().execute();
                        } else {
                            utils.showAlertMessage(Home1_Activity.this, getString(R.string.err_no_internet));
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
                ParsedResponse p = Service.apiGetCoin(Home1_Activity.this, Baseurl);
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
            if (!error) {
                Home_Adapter home_adapter = new Home_Adapter(Home1_Activity.this, nameList, stringList, mArrayList);
                mTvRecyclerView.setAdapter(home_adapter);
                mTvRecyclerView.getLayoutManager().onRestoreInstanceState(recylerViewState);
            } else {
                utils.showAlertMessage(Home1_Activity.this, msg);
            }
        }
    }


    public class apiGet_GraphValue extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, symbol;

        public apiGet_GraphValue(String symbol) {
            this.symbol = symbol;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String Baseurl = "https://min-api.cryptocompare.com/data/histohour?fsym=" +symbol+"&tsym="+"USD"+"&limit=24&aggregate=1&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Home1_Activity.this,Baseurl);
                error = p.error;
                if (!error) {
                    mGraphArrayList = (ArrayList<Graph>) p.o;
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
                chart();
            } else {
                utils.showAlertMessage(Home1_Activity.this, msg);
            }
        }
    }


    //--------------------------------------------------------SET_GRAPH_DATA------------------------------------------------------------


    public void chart(){

        mChart = (LineChart) findViewById(R.id.linechart);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);


        // add data
        setData();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text

        mChart.setDescription("");
        mChart.setNoDataTextDescription("");

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


    private ArrayList<String> setXAxisValues(){

        int time1 = Integer.parseInt(mGraphArrayList.get(0).time);
        int time2 = Integer.parseInt(mGraphArrayList.get(1).time);
        int time3 = Integer.parseInt(mGraphArrayList.get(2).time);
        int time4 = Integer.parseInt(mGraphArrayList.get(3).time);
        int time5 = Integer.parseInt(mGraphArrayList.get(4).time);
        int time6 = Integer.parseInt(mGraphArrayList.get(5).time);
        int time7 = Integer.parseInt(mGraphArrayList.get(6).time);
        int time8 = Integer.parseInt(mGraphArrayList.get(7).time);
        int time9 = Integer.parseInt(mGraphArrayList.get(8).time);
        int time10 = Integer.parseInt(mGraphArrayList.get(9).time);
        int time11 = Integer.parseInt(mGraphArrayList.get(10).time);
        int time12 = Integer.parseInt(mGraphArrayList.get(11).time);
        int time13 = Integer.parseInt(mGraphArrayList.get(12).time);
        int time14 = Integer.parseInt(mGraphArrayList.get(13).time);
        int time15 = Integer.parseInt(mGraphArrayList.get(14).time);
        int time16 = Integer.parseInt(mGraphArrayList.get(15).time);
        int time17 = Integer.parseInt(mGraphArrayList.get(16).time);
        int time18 = Integer.parseInt(mGraphArrayList.get(17).time);
        int time19 = Integer.parseInt(mGraphArrayList.get(18).time);
        int time20 = Integer.parseInt(mGraphArrayList.get(19).time);
        int time21 = Integer.parseInt(mGraphArrayList.get(20).time);
        int time22 = Integer.parseInt(mGraphArrayList.get(21).time);
        int time23 = Integer.parseInt(mGraphArrayList.get(22).time);
        int time24 = Integer.parseInt(mGraphArrayList.get(23).time);
        int time25 = Integer.parseInt(mGraphArrayList.get(24).time);

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
        Date date17= new Date(time18 * 1000L);
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

    private ArrayList<Entry> setYAxisValues(){
        float c_price = Float.parseFloat(mGraphArrayList.get(0).close);
        float c_price1 = Float.parseFloat(mGraphArrayList.get(1).close);
        float c_price2 = Float.parseFloat(mGraphArrayList.get(2).close);
        float c_price3 = Float.parseFloat(mGraphArrayList.get(3).close);
        float c_price4 = Float.parseFloat(mGraphArrayList.get(4).close);
        float c_price5 = Float.parseFloat(mGraphArrayList.get(5).close);
        float c_price6 = Float.parseFloat(mGraphArrayList.get(6).close);
        float c_price7 = Float.parseFloat(mGraphArrayList.get(7).close);
        float c_price8 = Float.parseFloat(mGraphArrayList.get(8).close);
        float c_price9 = Float.parseFloat(mGraphArrayList.get(9).close);
        float c_price10 = Float.parseFloat(mGraphArrayList.get(10).close);
        float c_price11 = Float.parseFloat(mGraphArrayList.get(11).close);
        float c_price12 = Float.parseFloat(mGraphArrayList.get(12).close);
        float c_price13 = Float.parseFloat(mGraphArrayList.get(13).close);
        float c_price14 = Float.parseFloat(mGraphArrayList.get(14).close);
        float c_price15 = Float.parseFloat(mGraphArrayList.get(15).close);
        float c_price16 = Float.parseFloat(mGraphArrayList.get(16).close);
        float c_price17 = Float.parseFloat(mGraphArrayList.get(17).close);
        float c_price18 = Float.parseFloat(mGraphArrayList.get(18).close);
        float c_price19 = Float.parseFloat(mGraphArrayList.get(19).close);
        float c_price20 = Float.parseFloat(mGraphArrayList.get(20).close);
        float c_price21 = Float.parseFloat(mGraphArrayList.get(21).close);
        float c_price22 = Float.parseFloat(mGraphArrayList.get(22).close);
        float c_price23 = Float.parseFloat(mGraphArrayList.get(23).close);
        float c_price24 = Float.parseFloat(mGraphArrayList.get(24).close);

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


    //-----------------------------------------------WEEK_GRAPH--------------------------------------------------------------


    public class apiGet_WeekGraphValue extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, symbol;

        public apiGet_WeekGraphValue(String symbol) {
            this.symbol = symbol;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String Baseurl = "https://min-api.cryptocompare.com/data/histoday?fsym="+symbol+"&tsym="+"USD"+"&limit=7&aggregate=1&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Home1_Activity.this,Baseurl);
                error = p.error;
                if (!error) {
                    mGraphArrayList = (ArrayList<Graph>) p.o;
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
                Weekchart();
            } else {
                utils.showAlertMessage(Home1_Activity.this, msg);
            }
        }
    }

    public void Weekchart(){

        mChart = (LineChart) findViewById(R.id.linechart1);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);


        // add data
        WeeksetData();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription("");

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


    private void WeeksetData() {
        ArrayList<String> xVals = setWeekXAxisValues();

        ArrayList<Entry> yVals = setWeekYAxisValues();

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

    private ArrayList<String> setWeekXAxisValues(){

        int time1 = Integer.parseInt(mGraphArrayList.get(0).time);
        int time2 = Integer.parseInt(mGraphArrayList.get(1).time);
        int time3 = Integer.parseInt(mGraphArrayList.get(2).time);
        int time4 = Integer.parseInt(mGraphArrayList.get(3).time);
        int time5 = Integer.parseInt(mGraphArrayList.get(4).time);
        int time6 = Integer.parseInt(mGraphArrayList.get(5).time);
        int time7 = Integer.parseInt(mGraphArrayList.get(6).time);
        int time8 = Integer.parseInt(mGraphArrayList.get(7).time);

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

    private ArrayList<Entry> setWeekYAxisValues(){
        float c_price = Float.parseFloat(mGraphArrayList.get(0).close);
        float c_price1 = Float.parseFloat(mGraphArrayList.get(1).close);
        float c_price2 = Float.parseFloat(mGraphArrayList.get(2).close);
        float c_price3 = Float.parseFloat(mGraphArrayList.get(3).close);
        float c_price4 = Float.parseFloat(mGraphArrayList.get(4).close);
        float c_price5 = Float.parseFloat(mGraphArrayList.get(5).close);
        float c_price6 = Float.parseFloat(mGraphArrayList.get(6).close);
        float c_price7 = Float.parseFloat(mGraphArrayList.get(7).close);


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
        String msg, symbol;

        public apiGet_MonthGraphValue(String symbol) {
            this.symbol = symbol;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String Baseurl = "https://min-api.cryptocompare.com/data/histoday?fsym="+symbol+"&tsym="+"USD"+"&limit=30&aggregate=1&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Home1_Activity.this,Baseurl);
                error = p.error;
                if (!error) {
                    mGraphArrayList = (ArrayList<Graph>) p.o;
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
                Monthchart();
            } else {
                utils.showAlertMessage(Home1_Activity.this, msg);
            }
        }
    }


    public void Monthchart(){

        mChart = (LineChart) findViewById(R.id.linechart3);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        // add data
        MonthsetData();
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription("");

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


    private ArrayList<String> setMonthXAxisValues(){

        int time1 = Integer.parseInt(mGraphArrayList.get(0).time);
        int time2 = Integer.parseInt(mGraphArrayList.get(1).time);
        int time3 = Integer.parseInt(mGraphArrayList.get(2).time);
        int time4 = Integer.parseInt(mGraphArrayList.get(3).time);
        int time5 = Integer.parseInt(mGraphArrayList.get(4).time);
        int time6 = Integer.parseInt(mGraphArrayList.get(5).time);
        int time7 = Integer.parseInt(mGraphArrayList.get(6).time);
        int time8 = Integer.parseInt(mGraphArrayList.get(7).time);
        int time9 = Integer.parseInt(mGraphArrayList.get(8).time);
        int time10 = Integer.parseInt(mGraphArrayList.get(9).time);
        int time11 = Integer.parseInt(mGraphArrayList.get(10).time);
        int time12 = Integer.parseInt(mGraphArrayList.get(11).time);
        int time13 = Integer.parseInt(mGraphArrayList.get(12).time);
        int time14 = Integer.parseInt(mGraphArrayList.get(13).time);
        int time15 = Integer.parseInt(mGraphArrayList.get(14).time);
        int time16 = Integer.parseInt(mGraphArrayList.get(15).time);
        int time17 = Integer.parseInt(mGraphArrayList.get(16).time);
        int time18 = Integer.parseInt(mGraphArrayList.get(17).time);
        int time19 = Integer.parseInt(mGraphArrayList.get(18).time);
        int time20 = Integer.parseInt(mGraphArrayList.get(19).time);
        int time21 = Integer.parseInt(mGraphArrayList.get(20).time);
        int time22 = Integer.parseInt(mGraphArrayList.get(21).time);
        int time23 = Integer.parseInt(mGraphArrayList.get(22).time);
        int time24 = Integer.parseInt(mGraphArrayList.get(23).time);
        int time25 = Integer.parseInt(mGraphArrayList.get(24).time);
        int time26 = Integer.parseInt(mGraphArrayList.get(25).time);
        int time27 = Integer.parseInt(mGraphArrayList.get(26).time);
        int time28 = Integer.parseInt(mGraphArrayList.get(27).time);
        int time29 = Integer.parseInt(mGraphArrayList.get(28).time);
        int time30 = Integer.parseInt(mGraphArrayList.get(29).time);
        int time31 = Integer.parseInt(mGraphArrayList.get(30).time);

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


    private ArrayList<Entry> setMonthYAxisValues(){
        float c_price = Float.parseFloat(mGraphArrayList.get(0).close);
        float c_price1 = Float.parseFloat(mGraphArrayList.get(1).close);
        float c_price2 = Float.parseFloat(mGraphArrayList.get(2).close);
        float c_price3 = Float.parseFloat(mGraphArrayList.get(3).close);
        float c_price4 = Float.parseFloat(mGraphArrayList.get(4).close);
        float c_price5 = Float.parseFloat(mGraphArrayList.get(5).close);
        float c_price6 = Float.parseFloat(mGraphArrayList.get(6).close);
        float c_price7 = Float.parseFloat(mGraphArrayList.get(7).close);
        float c_price8 = Float.parseFloat(mGraphArrayList.get(8).close);
        float c_price9 = Float.parseFloat(mGraphArrayList.get(9).close);
        float c_price10 = Float.parseFloat(mGraphArrayList.get(10).close);
        float c_price11 = Float.parseFloat(mGraphArrayList.get(11).close);
        float c_price12 = Float.parseFloat(mGraphArrayList.get(12).close);
        float c_price13 = Float.parseFloat(mGraphArrayList.get(13).close);
        float c_price14 = Float.parseFloat(mGraphArrayList.get(14).close);
        float c_price15 = Float.parseFloat(mGraphArrayList.get(15).close);
        float c_price16 = Float.parseFloat(mGraphArrayList.get(16).close);
        float c_price17 = Float.parseFloat(mGraphArrayList.get(17).close);
        float c_price18 = Float.parseFloat(mGraphArrayList.get(18).close);
        float c_price19 = Float.parseFloat(mGraphArrayList.get(19).close);
        float c_price20 = Float.parseFloat(mGraphArrayList.get(20).close);
        float c_price21 = Float.parseFloat(mGraphArrayList.get(21).close);
        float c_price22 = Float.parseFloat(mGraphArrayList.get(22).close);
        float c_price23 = Float.parseFloat(mGraphArrayList.get(23).close);
        float c_price24 = Float.parseFloat(mGraphArrayList.get(24).close);
        float c_price25 = Float.parseFloat(mGraphArrayList.get(25).close);
        float c_price26 = Float.parseFloat(mGraphArrayList.get(26).close);
        float c_price27 = Float.parseFloat(mGraphArrayList.get(27).close);
        float c_price28 = Float.parseFloat(mGraphArrayList.get(28).close);
        float c_price29 = Float.parseFloat(mGraphArrayList.get(29).close);
        float c_price30 = Float.parseFloat(mGraphArrayList.get(30).close);


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
        String msg, symbol;

        public apiGet_YearGraphValue(String symbol) {
            this.symbol = symbol;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String Baseurl = "https://min-api.cryptocompare.com/data/histoday?fsym="+symbol+"&tsym="+"USD"+"&limit=12&aggregate=30&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Home1_Activity.this,Baseurl);
                error = p.error;
                if (!error) {
                    mGraphArrayList = (ArrayList<Graph>) p.o;
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
                Yearchart();
            } else {
                utils.showAlertMessage(Home1_Activity.this, msg);
            }
        }
    }


    public void Yearchart(){

        mChart = (LineChart) findViewById(R.id.linechart4);
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
        mChart.setNoDataTextDescription("");

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

    private ArrayList<String> setYearXAxisValues(){

        int time1 = Integer.parseInt(mGraphArrayList.get(0).time);
        int time2 = Integer.parseInt(mGraphArrayList.get(1).time);
        int time3 = Integer.parseInt(mGraphArrayList.get(2).time);
        int time4 = Integer.parseInt(mGraphArrayList.get(3).time);
        int time5 = Integer.parseInt(mGraphArrayList.get(4).time);
        int time6 = Integer.parseInt(mGraphArrayList.get(5).time);
        int time7 = Integer.parseInt(mGraphArrayList.get(6).time);
        int time8 = Integer.parseInt(mGraphArrayList.get(7).time);
        int time9 = Integer.parseInt(mGraphArrayList.get(8).time);
        int time10 = Integer.parseInt(mGraphArrayList.get(9).time);
        int time11 = Integer.parseInt(mGraphArrayList.get(10).time);
        int time12 = Integer.parseInt(mGraphArrayList.get(11).time);
        int time13 = Integer.parseInt(mGraphArrayList.get(12).time);


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


    private ArrayList<Entry> setYearYAxisValues(){
        float c_price = Float.parseFloat(mGraphArrayList.get(0).close);
        float c_price1 = Float.parseFloat(mGraphArrayList.get(1).close);
        float c_price2 = Float.parseFloat(mGraphArrayList.get(2).close);
        float c_price3 = Float.parseFloat(mGraphArrayList.get(3).close);
        float c_price4 = Float.parseFloat(mGraphArrayList.get(4).close);
        float c_price5 = Float.parseFloat(mGraphArrayList.get(5).close);
        float c_price6 = Float.parseFloat(mGraphArrayList.get(6).close);
        float c_price7 = Float.parseFloat(mGraphArrayList.get(7).close);
        float c_price8 = Float.parseFloat(mGraphArrayList.get(8).close);
        float c_price9 = Float.parseFloat(mGraphArrayList.get(9).close);
        float c_price10 = Float.parseFloat(mGraphArrayList.get(10).close);
        float c_price11 = Float.parseFloat(mGraphArrayList.get(11).close);
        float c_price12 = Float.parseFloat(mGraphArrayList.get(12).close);

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
}
