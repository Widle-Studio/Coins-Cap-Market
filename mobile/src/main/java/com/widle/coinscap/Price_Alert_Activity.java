package com.widle.coinscap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Adapter.Pair_Adapter;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Servicess.MyForeGroundService;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Price_Alert_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;

    private TextView tv_app_name, tv_popup, tv_popup1, tv_price, lbl_price_above, tv_price_above, lbl_per, lbl_price_below, tv_price_below, lbl_per1;

    private PopupWindow popupWindow;

    private SeekBar seekbar, seekbar1;

    private String coins = "BTC", less = "", high = "", currency = "USD", current_price = "", price_above = "", price_below = "";

    private Button btn_submit;

    private static DatabaseHandler db;

    private LinearLayout ll_main;

    ArrayList<coin> mArrayList = new ArrayList<>();

    ArrayList<coin> mPairArrayList = new ArrayList<>();

    float sum = 0;

    private ListView list_category;

    Pair_Adapter pair_adapter;

    SharedPreferences preferences;

    private RadioButton rb_single, rb_multiple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_alert);
        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);



        db = new DatabaseHandler(Price_Alert_Activity.this);
        init();
        new apiGet_Coins().execute();


    }

    public void init() {

        iv_back = findViewById(R.id.iv_back);
        tv_app_name = findViewById(R.id.tv_app_name);
        tv_popup = findViewById(R.id.tv_popup);
        tv_popup1 = findViewById(R.id.tv_popup1);
        tv_price = findViewById(R.id.tv_price);
        lbl_price_above = findViewById(R.id.lbl_price_above);
        tv_price_above = findViewById(R.id.tv_price_above);
        lbl_per = findViewById(R.id.lbl_per);
        lbl_price_below = findViewById(R.id.lbl_price_below);
        tv_price_below = findViewById(R.id.tv_price_below);
        lbl_per1 = findViewById(R.id.lbl_per1);
        btn_submit = findViewById(R.id.btn_submit);
        ll_main = findViewById(R.id.ll_main);
        rb_single = findViewById(R.id.rb_single);
        rb_multiple = findViewById(R.id.rb_multiple);

        seekbar = findViewById(R.id.seekbar);
        seekbar1 = findViewById(R.id.seekbar1);

        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        tv_app_name.setTypeface(type2);
        btn_submit.setTypeface(type2);
        tv_price.setTypeface(type2);
        lbl_price_above.setTypeface(type2);
        tv_price_above.setTypeface(type2);
        lbl_per.setTypeface(type2);
        lbl_price_below.setTypeface(type2);
        tv_price_below.setTypeface(type2);
        lbl_per1.setTypeface(type2);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                lbl_per.setText(String.valueOf(i) + "%");
                int x = (int) (i * (float) (sum) / 100.0 + (sum));
                price_above = String.valueOf(x);
                tv_price_above.setText("$" + Integer.toString(x));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                lbl_per1.setText(i);
                lbl_per1.setText(String.valueOf(i) + "%");
                int x = (int) (i * (float) (sum) / 100.0 - (sum));
                price_below = String.valueOf(x);
                tv_price_below.setText("$" + Integer.toString(x));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        rb_single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if (ischecked == true){
                    rb_multiple.setChecked(false);
                }
            }
        });

        rb_multiple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if (ischecked == true){
                    rb_single.setChecked(false);
                }
            }
        });

        iv_back.setOnClickListener(this);
        tv_popup.setOnClickListener(this);
        tv_popup1.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_popup:
                showCoin();
                break;

            case R.id.tv_popup1:
                showCurrency();
                break;

            case R.id.btn_submit:
                if (price_below.equals("")){
                } else{
                    String value = price_below;
                    price_below = value.substring(1);
                }

                db.addAlertCoins(coins, current_price, price_above, price_below);

                Intent intent = new Intent(Price_Alert_Activity.this, MyForeGroundService.class);
                intent.setAction(MyForeGroundService.ACTION_START_FOREGROUND_SERVICE);
//                intent.putExtra("coins", coins);
//                intent.putExtra("currency", currency);
//                intent.putExtra("price_above", price_above);
//                intent.putExtra("price_below", price_below);
                startService(intent);


                finish();

                break;

        }
    }

    private boolean isValidInput() {

        if (less.toString().trim().length() < 1) {
            utils.showAlertMessage(Price_Alert_Activity.this, getString(R.string.less));
            return false;
        }

        if (high.toString().trim().length() < 1) {
            utils.showAlertMessage(Price_Alert_Activity.this, getString(R.string.less));
            return false;
        }

        return true;
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
                new apiGet_Coins().execute();
                popupWindow.dismiss();
            }
        });


        popupWindow.showAsDropDown(tv_popup, 0, 2);
    }




    /*-------------------------------------------CURRENCY_POPUP-----------------------------------------------------------*/


    private void showCurrency() {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(TopList_Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.popup_category, null);
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

        list_category = (ListView) view.findViewById(R.id.list_language);

        new apiPairList().execute();


        list_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tv_popup1.setText(mPairArrayList.get(i).toSymbol);
                currency = mPairArrayList.get(i).toSymbol;
                new apiGet_Coins().execute();
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(tv_popup1, 0, 2);
    }

//    private void showCurrency() {
//        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.popup_currency, null);
//        popupWindow = new PopupWindow(view);
//        int width = tv_popup1.getWidth();
//        if (width > 0) {
//            popupWindow.setWidth(width);
//        } else {
//            popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
//        }
//        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c6c6c6")));
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setTouchable(true);
//        popupWindow.setFocusable(true);
//
//        final TextView tv_usd = view.findViewById(R.id.tv_usd);
//        final TextView tv_jpy = view.findViewById(R.id.tv_jpy);
//        final TextView tv_euro = view.findViewById(R.id.tv_euro);
//
//        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
//        tv_usd.setTypeface(type);
//        tv_euro.setTypeface(type);
//        tv_jpy.setTypeface(type);
//
//        tv_usd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tv_popup1.setText(tv_usd.getText().toString());
//                currency =  tv_usd.getText().toString();
//                new apiGet_Coins().execute();
//                popupWindow.dismiss();
//
//            }
//        });
//
//        tv_jpy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tv_popup1.setText(tv_jpy.getText().toString());
//                currency =  tv_jpy.getText().toString();
//                new apiGet_Coins().execute();
//                popupWindow.dismiss();
//
//            }
//        });
//
//        tv_euro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tv_popup1.setText(tv_euro.getText().toString());
//                currency =  tv_euro.getText().toString();
//                new apiGet_Coins().execute();
//                popupWindow.dismiss();
//
//            }
//        });
//
//        popupWindow.showAsDropDown(tv_popup1, 0, 2);
//    }


    public class apiPairList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String url = "https://min-api.cryptocompare.com/data/top/pairs?fsym=" + coins + "&limit=2000";
                ParsedResponse p = Service.apiGetPairList(Price_Alert_Activity.this, url);
                error = p.error;
                if (!error) {
                    mPairArrayList = (ArrayList<coin>) p.o;
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
                pair_adapter = new Pair_Adapter(Price_Alert_Activity.this, mPairArrayList);
                list_category.setAdapter(pair_adapter);
            } else {
            }
        }
    }


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
                String Baseurl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=" + coins + "&tsyms=" + currency + "&extraParams=your_app_name";
                ParsedResponse p = Service.apiGetAlertCoin(Price_Alert_Activity.this, Baseurl, coins, currency);
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
                tv_price.setText(mArrayList.get(0).PRICE);
                tv_price_above.setText(mArrayList.get(0).PRICE);
                tv_price_below.setText(mArrayList.get(0).PRICE);
                current_price = mArrayList.get(0).PRICE;

                String value = mArrayList.get(0).PRICE;
                value = value.substring(2);

                String price = value;
                String s = "";

                if (price.contains(",")) {
//                    price.replace(",", "");
                    price = price.replace(",", "");
                } else {

                }
                try {
                    sum = Float.valueOf(price.trim()).floatValue();
                } catch (NumberFormatException e) {

                }

                ll_main.setVisibility(View.VISIBLE);
            } else {
                utils.showAlertMessage(Price_Alert_Activity.this, msg);
            }
        }
    }
}
