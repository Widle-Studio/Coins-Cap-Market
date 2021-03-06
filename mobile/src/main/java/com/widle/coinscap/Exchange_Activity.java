package com.widle.coinscap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.widle.coinscap.Adapter.Exchange_Adapter;
import com.widle.coinscap.Adapter.Reddit_News_Adapter;
import com.widle.coinscap.Fragment.Reddit_News_Fragment;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.News;
import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Exchange_Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;

    private TextView tv_app_name, tv_popup, tv_popup1, tv_popup2, tv_clear;

    private String pro_pic = "";

    SharedPreferences preferences;

    private RecyclerView rv_exchange;

    public ProgressDialog mProgressDialog;

    private String coins = "BTC", currency = "USD";

    private PopupWindow popupWindow;

    ArrayList<coin> mArrayList = new ArrayList<>();

    ArrayList<coin> mmArrayList = new ArrayList<>();

    Exchange_Adapter exchange_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        pro_pic = preferences.getString(General.PREFS_Picture, "");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        init();

        if (utils.isNetworkAvailable(this)) {
            new apiExchangeList(coins, currency).execute();
        } else {
        }
    }


    public void init(){

        iv_back = findViewById(R.id.iv_back);
        tv_app_name = findViewById(R.id.tv_app_name);
        tv_popup = findViewById(R.id.tv_popup);
        tv_popup1 = findViewById(R.id.tv_popup1);
        tv_popup2 = findViewById(R.id.tv_popup2);
        tv_clear = findViewById(R.id.tv_clear);
//        iv_profilepic = findViewById(R.id.iv_profilepic);


        rv_exchange = (RecyclerView) findViewById(R.id.rv_exchange);
        rv_exchange.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_app_name.setTypeface(type2);



        iv_back.setOnClickListener(this);
        tv_popup.setOnClickListener(this);
        tv_popup1.setOnClickListener(this);
        tv_popup2.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_popup:
                showCoin();
                break;

            case R.id.tv_popup1:
                showCurrency();
                break;

            case R.id.tv_popup2:
                showMarket();
                break;

            case R.id.tv_clear:
                tv_popup.setText("BTC");
                tv_popup1.setText("USD");
                tv_popup2.setText("All");
                if (utils.isNetworkAvailable(this)) {
                    new apiExchangeList("BTC", "USD").execute();
                } else {
                }
                break;
        }
    }




    /*-------------------------------------------COIN_POPUP-----------------------------------------------------------*/


    private void showCoin() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.popup_coin, null);
        popupWindow = new PopupWindow(view);
        int width = tv_popup.getWidth();
        if (width > 0) {
            popupWindow.setWidth(width);
        } else {
            popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        }
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c6c6c6")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        final ListView lv_coin = view.findViewById(R.id.lv_coin);

        final List<String> coin = new ArrayList<String>();
        coin.add("BTC");
        coin.add("ETH");
        coin.add("BCH");
        coin.add("XRP");
        coin.add("LTC");
        coin.add("ADA");
        coin.add("IOT");
        coin.add("DASH");
        coin.add("XEM");
        coin.add("XMR");
        coin.add("EOS");
        coin.add("BTG");
        coin.add("QTUM");
        coin.add("XLM");
        coin.add("NEO");
        coin.add("ETC");
        coin.add("ZEC");
        coin.add("STEEM");
        coin.add("XZC");
        coin.add("STORJ");
        coin.add("AION");
        coin.add("HT");
        coin.add("TRX");
        coin.add("XRB");
        coin.add("OMG");
        coin.add("ELA");
        coin.add("BNB");
        coin.add("VEN");
        coin.add("ZCL");
        coin.add("DGD");
        coin.add("OCN");
        coin.add("BCPT");
        coin.add("LUN");
        coin.add("IOST");
        coin.add("HSR");
        coin.add("ICX");
        coin.add("LSK");
        coin.add("NEBL");
        coin.add("WAVES");
        coin.add("BLZ");
        coin.add("INK");
        coin.add("ADX");
        coin.add("XVG");
        coin.add("MTL");
        coin.add("SRN");
        coin.add("ZRX");
        coin.add("RLC");
        coin.add("THETA");
        coin.add("BCD");
        coin.add("OAX");
        coin.add("ELF");
        coin.add("INS");
        coin.add("ZIL");
        coin.add("AE");
        coin.add("PRO");
        coin.add("DOGE");
        coin.add("ITC");
        coin.add("RDD");
        coin.add("SWFTC");
        coin.add("MCO");
        coin.add("ARN");
        coin.add("MTN*");
        coin.add("BRD");
        coin.add("SAN");
        coin.add("NAS");
        coin.add("GVT");
        coin.add("QUN");
        coin.add("WTC");
        coin.add("FCT");
        coin.add("CDT");
        coin.add("RCN");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, coin);

        lv_coin.setAdapter(arrayAdapter);

        lv_coin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                coins = coin.get(i);
                tv_popup.setText(coin.get(i));
                tv_popup2.setText("All");
                new apiExchangeList(coins, currency).execute();
                popupWindow.dismiss();
            }
        });


        popupWindow.showAsDropDown(tv_popup, 0, 2);
    }



    /*-------------------------------------------CURRENCY_POPUP-----------------------------------------------------------*/


    private void showCurrency() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.popup_currency, null);
        popupWindow = new PopupWindow(view);
        int width = tv_popup1.getWidth();
        if (width > 0) {
            popupWindow.setWidth(width);
        } else {
            popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        }
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c6c6c6")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        final TextView tv_usd = view.findViewById(R.id.tv_usd);
        final TextView tv_jpy = view.findViewById(R.id.tv_jpy);
        final TextView tv_euro = view.findViewById(R.id.tv_euro);

        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        tv_usd.setTypeface(type);
        tv_euro.setTypeface(type);
        tv_jpy.setTypeface(type);

        tv_usd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup1.setText(tv_usd.getText().toString());
                currency =  tv_usd.getText().toString();
                tv_popup2.setText("All");
                new apiExchangeList(coins, currency).execute();
                popupWindow.dismiss();

            }
        });

        tv_jpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup1.setText(tv_jpy.getText().toString());
                currency =  tv_jpy.getText().toString();
                tv_popup2.setText("All");
                new apiExchangeList(coins, currency).execute();
                popupWindow.dismiss();

            }
        });

        tv_euro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup1.setText(tv_euro.getText().toString());
                currency =  tv_euro.getText().toString();
                tv_popup2.setText("All");
                new apiExchangeList(coins, currency).execute();
                popupWindow.dismiss();

            }
        });

        popupWindow.showAsDropDown(tv_popup1, 0, 2);
    }




    /*-------------------------------------------COIN_POPUP-----------------------------------------------------------*/


    private void showMarket() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.popup_market, null);
        popupWindow = new PopupWindow(view);
        int width = tv_popup2.getWidth();
        if (width > 0) {
            popupWindow.setWidth(width);
        } else {
            popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        }
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c6c6c6")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        final ListView lv_market = view.findViewById(R.id.lv_market);

        final List<String> coin = new ArrayList<String>();

        for (int k = 0; k<mArrayList.size(); k++){
            coin.add(mArrayList.get(k).MARKET);
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, coin);

        lv_market.setAdapter(arrayAdapter);


        lv_market.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mmArrayList.clear();
                String market = coin.get(i);
                tv_popup2.setText(coin.get(i));
//                new apiExchangeList(coins, currency).execute();

                coin c = new coin();
                for (int j = 0; j<mArrayList.size(); j++){

                    if (market.equals(mArrayList.get(j).MARKET)){
                        c.MARKET = mArrayList.get(j).MARKET;
                        c.PRICE = mArrayList.get(j).PRICE;
                        c.LASTVOLUME = mArrayList.get(j).LASTVOLUME;
                        c.LASTTRADEID = mArrayList.get(j).LASTTRADEID;
                        c.VOLUME24HOUR = mArrayList.get(j).VOLUME24HOUR;
                        c.OPEN24HOUR = mArrayList.get(j).OPEN24HOUR;
                        c.HIGH24HOUR = mArrayList.get(j).HIGH24HOUR;
                        c.LOW24HOUR = mArrayList.get(j).LOW24HOUR;
                        c.CHANGEPCT24HOUR = mArrayList.get(j).CHANGEPCT24HOUR;
                        c.CHANGE24HOUR = mArrayList.get(j).CHANGE24HOUR;
                        mmArrayList.add(c);
                    } else{

                    }
                }
                exchange_adapter = new Exchange_Adapter(Exchange_Activity.this, mmArrayList, market, coins, currency);
                rv_exchange.setAdapter(exchange_adapter);
                popupWindow.dismiss();
            }
        });


        popupWindow.showAsDropDown(tv_popup2, 0, 2);
    }






    public class apiExchangeList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, coins, currency;


        public apiExchangeList(String coins, String currency) {
            this.coins = coins;
            this.currency = currency;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String url = "https://min-api.cryptocompare.com/data/top/exchanges/full?fsym=" + coins + "&tsym=" + currency +"&limit=20";
                ParsedResponse p = Service.apiGetExchange(Exchange_Activity.this, url);
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
            mProgressDialog.dismiss();
            if (!error) {
                exchange_adapter = new Exchange_Adapter(Exchange_Activity.this, mArrayList, "", coins, currency);
                rv_exchange.setAdapter(exchange_adapter);

            } else {
            }
        }
    }
}
