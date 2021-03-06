package com.widle.coinscap;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BrowseFragment;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import com.widle.coinscap.Model.Graph;
import com.widle.coinscap.Model.Update_Coin;
import com.widle.coinscap.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

/**
 * Created by imperial-web on 4/4/2018.
 */

public class Coins_DetailsActivity extends Activity implements OnChartGestureListener,
        OnChartValueSelectedListener {


    ArrayList<coin> coinArrayList = new ArrayList<>();
    ArrayList<Graph> mGraphArrayList = new ArrayList<>();
    ArrayList<Update_Coin> update_coin = new ArrayList<>();
    private LineChart mChart;
    String Symbol;
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
    Animation Blink;


    private TextView Price_D, Name_D, Name_Symbol_D, Ptc_D, Mc_D;
    private ImageView Image_D;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coins_activity_details);


        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        Image_D = (ImageView) findViewById(R.id.img_d);
        Name_D = (TextView) findViewById(R.id.tv_name_d);
        Name_Symbol_D = (TextView) findViewById(R.id.tv_name_symbol_D);
        Price_D = (TextView) findViewById(R.id.tv_price_d);
        Mc_D = (TextView) findViewById(R.id.tv_mc_d);
        Ptc_D = (TextView) findViewById(R.id.tv_pct_d);

        coinArrayList = (ArrayList<coin>) getIntent().getSerializableExtra("alldata");
        Symbol = getIntent().getStringExtra("symbol");
        Blink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);

        for (int i = 0; i < coinArrayList.size(); i++) {

            if (Symbol.equals(coinArrayList.get(i).getCOINSSTRINGNAME())) {


                Name_D.setText(coinArrayList.get(i).getCOINSNAME());
                Name_Symbol_D.setText(coinArrayList.get(i).getCOINSSTRINGNAME());
                Price_D.setText(coinArrayList.get(i).getPRICE());
                Mc_D.setText(coinArrayList.get(i).getMKTCAP());


                if (coinArrayList.get(i).getCHANGEPCT24HOUR().contains("-")) {

                    Ptc_D.setText(coinArrayList.get(i).getCHANGE24HOUR() + " (" + coinArrayList.get(i).getCHANGEPCT24HOUR() + "%)");
                    Ptc_D.setTextColor(Color.parseColor("#FF0000"));
                } else {

                    Ptc_D.setText(coinArrayList.get(i).getCHANGE24HOUR() + " (" + coinArrayList.get(i).getCHANGEPCT24HOUR() + "%)");
                    Ptc_D.setTextColor(Color.parseColor("#008000"));
                }

                if (Symbol.equals("BTC")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.btc)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("ETH")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.eth)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("BCH")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.bch)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("XRP")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.ripple)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("LTC")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.litecoin)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("ADA")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.ada)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("IOT")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.iota)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("DASH")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.dash)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("XEM")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.xem)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("XMR")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.xmr)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("EOS")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.eos)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("BTG")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.btg)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("QTUM")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.qtum)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("XLM")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.str)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("NEO")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.neo)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("ETC")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.etc2)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("ZEC")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.zec)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("STEEM")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.steem)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("XZC")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.xzc1)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("STORJ")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.sjcx)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("AION")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load(R.drawable.aion)
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("HT")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010813/ht.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("TRX")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/12318089/trx.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("XRB")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383674/xrb.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("OMG")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383814/omisego.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("ELA")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010574/ela.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("BNB")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1382967/bnb.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("VEN")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/12318129/ven.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("ZCL")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/351926/zcl.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("DGD")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/350851/dgd.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("OCN")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010448/ocn.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("BCPT")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/16746476/bcpt.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("LUN")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383112/lunyr-logo.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("IOST")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010459/iost.png")
                            .override(50, 50)
                            .into(Image_D);


                } else if (Symbol.equals("HSR")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/12318137/hsr.png")
                            .override(50, 50)
                            .into(Image_D);


                } else if (Symbol.equals("ICX")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/12318192/icx.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("LSK")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27011060/lsk.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("NEBL")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1384016/nebl.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("WAVES")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010639/waves2.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("BLZ")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010607/1.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("INK")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/20780781/ink.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("ADX")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383667/adx1.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("XVG")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/12318032/xvg.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("MTL")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383743/mtl.png")
                            .override(50, 50)
                            .into(Image_D);


                } else if (Symbol.equals("SRN")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/14913556/srn.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("ZRX")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383799/zrx.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("RLC")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/12318418/rlc.png")
                            .override(50, 50)
                            .into(Image_D);


                } else if (Symbol.equals("THETA")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010450/theta.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("BCD")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/16404872/bcd.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("OAX")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383756/oax.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("ELF")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/20780600/elf.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("INS")) {


                } else if (Symbol.equals("ZIL")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010464/zil.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("AE")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383836/ae.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("PRO")) {

                } else if (Symbol.equals("DOGE")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/19684/doge.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("ITC")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/20780628/itc.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("RDD")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/19887/rdd.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("SWFTC")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010472/swftc.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("MCO")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383653/mco.jpg")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("ARN")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/12318261/arn.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("MTN*")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010631/mtn_logo.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("BRD")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/20780589/brd.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("SAN")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383730/san.png")
                            .override(50, 50)
                            .into(Image_D);

                } else if (Symbol.equals("NAS")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/20780653/nas.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("GVT")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/14913634/gvt.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("QUN")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/27010466/qun.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("WTC")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/12317959/wtc.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("FCT")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1382863/fct1.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("CDT")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/1383699/cdt.png")
                            .override(50, 50)
                            .into(Image_D);
                } else if (Symbol.equals("RCN")) {
                    Glide.with(Coins_DetailsActivity.this)
                            .load("https://www.cryptocompare.com/media/12318046/rnc.png")
                            .override(50, 50)
                            .into(Image_D);
                }


            }


        }

        new apiGet_GraphValue(Symbol).execute();
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
                String Baseurl = "https://min-api.cryptocompare.com/data/histohour?fsym=" + symbol + "&tsym=" + "USD" + "&limit=24&aggregate=1&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Coins_DetailsActivity.this, Baseurl);
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
                utils.showAlertMessage(Coins_DetailsActivity.this, msg);
            }
        }
    }

    //--------------------------------------------------------SET_GRAPH_DATA------------------------------------------------------------


    public void chart() {

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


        new apiGet_WeekGraphValue(Symbol).execute();
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


    private ArrayList<String> setXAxisValues() {

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
                String Baseurl = "https://min-api.cryptocompare.com/data/histoday?fsym=" + symbol + "&tsym=" + "USD" + "&limit=7&aggregate=1&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Coins_DetailsActivity.this, Baseurl);
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
                utils.showAlertMessage(Coins_DetailsActivity.this, msg);
            }
        }
    }

    public void Weekchart() {

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

        new apiGet_MonthGraphValue(Symbol).execute();

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

    private ArrayList<String> setWeekXAxisValues() {

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

    private ArrayList<Entry> setWeekYAxisValues() {
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
                String Baseurl = "https://min-api.cryptocompare.com/data/histoday?fsym=" + symbol + "&tsym=" + "USD" + "&limit=30&aggregate=1&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Coins_DetailsActivity.this, Baseurl);
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
                update();
            } else {
                utils.showAlertMessage(Coins_DetailsActivity.this, msg);
            }
        }
    }


    public void Monthchart() {

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

        new apiGet_YearGraphValue(Symbol).execute();
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


    private ArrayList<Entry> setMonthYAxisValues() {
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
                String Baseurl = "https://min-api.cryptocompare.com/data/histoday?fsym=" + symbol + "&tsym=" + "USD" + "&limit=12&aggregate=30&e=CCCAGG";
                ParsedResponse p = Service.apiGetGraphValue(Coins_DetailsActivity.this, Baseurl);
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
                utils.showAlertMessage(Coins_DetailsActivity.this, msg);
            }
        }
    }


    public void Yearchart() {

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

    private ArrayList<String> setYearXAxisValues() {

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


    private ArrayList<Entry> setYearYAxisValues() {
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


    public void update() {
        timer = new Timer();
        //initialize the TimerTask's job
        initializeChatTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 0, 3000); //
    }
    BrowseFragment browseFragment = new BrowseFragment();

    public void initializeChatTask() {


        timerTask = new TimerTask() {

            @Override
            public void run() {

                handler.post(new Runnable() {
                    public void run() {

                        browseFragment.getSelectedPosition();
                        if (utils.isNetworkAvailable(Coins_DetailsActivity.this)) {
                            new apiGet_Update_Coins().execute();
                        } else {
                            utils.showAlertMessage(Coins_DetailsActivity.this, getString(R.string.err_no_internet));
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
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms="+Symbol+"&tsyms=" + "USD" + "&extraParams=your_app_name";
                ParsedResponse p = Service.apiGetOneCoin(Coins_DetailsActivity.this, Baseurl,Symbol);
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
               // loadRows();
//                browseFragment.setSelectedPosition(5);
                /*Home_Adapter home_adapter = new Home_Adapter(G.this, nameList, stringList, mArrayList);
                mTvRecyclerView.setAdapter(home_adapter);
                mTvRecyclerView.getLayoutManager().onRestoreInstanceState(recylerViewState);*/
               for (int i =0 ; i<update_coin.size(); i++){

                   Price_D.setText(update_coin.get(i).PRICE);
                   Mc_D.setText(update_coin.get(i).MKTCAP);


                   if (coinArrayList.get(i).getCHANGEPCT24HOUR().contains("-")) {

                       Ptc_D.setText(update_coin.get(i).CHANGE24HOUR + " (" + update_coin.get(i).CHANGEPCT24HOUR + "%)");
                       Ptc_D.setTextColor(Color.parseColor("#FF0000"));
                   } else {

                       Ptc_D.setText(update_coin.get(i).CHANGE24HOUR + " (" + update_coin.get(i).CHANGEPCT24HOUR + "%)");
                       Ptc_D.setTextColor(Color.parseColor("#008000"));
                   }

                   Price_D.startAnimation(Blink);
                 //  Mc_D.startAnimation(Blink);
                  // Ptc_D.startAnimation(Blink);
               }


            } else {
                // utils.showAlertMessage(Home1_Activity.this, msg);
            }
        }
    }
}
