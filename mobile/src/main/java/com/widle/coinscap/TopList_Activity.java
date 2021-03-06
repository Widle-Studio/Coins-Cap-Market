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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.widle.coinscap.Adapter.Exchange_Adapter;
import com.widle.coinscap.Adapter.Pair_Adapter;
import com.widle.coinscap.Adapter.TopList_Adapter;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class TopList_Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back, iv_share;

    private TextView tv_app_name, tv_popup1;

//    private CircleImageView iv_profilepic;

    SharedPreferences preferences;

    private String pro_pic = "";

    private RecyclerView rv_exchange;

    public ProgressDialog mProgressDialog;

    private String currency = "USD";

    private PopupWindow popupWindow;

    TopList_Adapter topList_adapter;

    ArrayList<coin> mArrayList = new ArrayList<>();

    ArrayList<coin> mPairArrayList = new ArrayList<>();

    Pair_Adapter pair_adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_list);

        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        pro_pic = preferences.getString(General.PREFS_Picture, "");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        init();

        if (utils.isNetworkAvailable(this)) {
//            new apiPairList().execute();
            new apiTopList(currency).execute();
        } else {
        }

    }


    public void init(){

        iv_back = findViewById(R.id.iv_back);
        iv_share = findViewById(R.id.iv_share);
        tv_app_name = findViewById(R.id.tv_app_name);
        tv_popup1 = findViewById(R.id.tv_popup1);
//        iv_profilepic = findViewById(R.id.iv_profilepic);

        rv_exchange = (RecyclerView) findViewById(R.id.rv_exchange);
        rv_exchange.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_app_name.setTypeface(type2);


        iv_back.setOnClickListener(this);
        tv_popup1.setOnClickListener(this);
        iv_share.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_popup1:
                showCurrency();
                break;

            case R.id.iv_share:
                SimpleDateFormat sdf = new SimpleDateFormat("dd, MMM yyyy");
                String currentDateandTime = sdf.format(new Date());

                String sAux = "Top 10 Coins - "+ currentDateandTime;

                String s1ux = "1. " + mArrayList.get(0).CurrencyFrom;
                String s2ux = "Supply - " + mArrayList.get(0).Supply + " " + currency;
                String s3ux = "24h Volume - " + mArrayList.get(0).TotalVolume24H + " " + currency;

                String s11ux = "2. "+mArrayList.get(1).CurrencyFrom;
                String s12ux = "Supply - " + mArrayList.get(1).Supply + " " + currency;
                String s13ux = "24h Volume - " + mArrayList.get(1).TotalVolume24H + " " + currency;

                String s21ux = "3. "+mArrayList.get(2).CurrencyFrom;
                String s22ux = "Supply - " + mArrayList.get(2).Supply + " " + currency;
                String s23ux = "24h Volume - " + mArrayList.get(2).TotalVolume24H + " " + currency;

                String s31ux = "4. "+mArrayList.get(3).CurrencyFrom;
                String s32ux = "Supply - " + mArrayList.get(3).Supply + " " + currency;
                String s33ux = "24h Volume - " + mArrayList.get(3).TotalVolume24H + " " + currency;

                String s41ux = "5. "+mArrayList.get(4).CurrencyFrom;
                String s42ux = "Supply - " + mArrayList.get(4).Supply + " " + currency;
                String s43ux = "24h Volume - " + mArrayList.get(4).TotalVolume24H + " " + currency;

                String s51ux = "6. "+mArrayList.get(5).CurrencyFrom;
                String s52ux = "Supply - " + mArrayList.get(5).Supply + " " + currency;
                String s53ux = "24h Volume - " + mArrayList.get(5).TotalVolume24H + " " + currency;

                String s61ux = "7. "+mArrayList.get(6).CurrencyFrom;
                String s62ux = "Supply - " + mArrayList.get(6).Supply + " " + currency;
                String s63ux = "24h Volume - " + mArrayList.get(6).TotalVolume24H + " " + currency;

                String s71ux = "8. "+mArrayList.get(7).CurrencyFrom;
                String s72ux = "Supply - " + mArrayList.get(7).Supply + " " + currency;
                String s73ux = "24h Volume - " + mArrayList.get(7).TotalVolume24H + " " + currency;

                String s81ux = "9. "+mArrayList.get(8).CurrencyFrom;
                String s82ux = "Supply - " + mArrayList.get(8).Supply + " " + currency;
                String s83ux = "24h Volume - " + mArrayList.get(8).TotalVolume24H + " " + currency;

                String s91ux = "10. "+mArrayList.get(9).CurrencyFrom;
                String s92ux = "Supply - " + mArrayList.get(9).Supply + " " + currency;
                String s93ux = "24h Volume - " + mArrayList.get(9).TotalVolume24H + " " + currency;


                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                sAux = "\n" +sAux + "\n\n";
                String sBux = s1ux +"\n" + s2ux +"\n" + s3ux + "\n\n";
                String sCux = s11ux +"\n" + s12ux +"\n" + s13ux + "\n\n";
                String sDux = s21ux +"\n" + s22ux +"\n" + s23ux + "\n\n";
                String sEux = s31ux +"\n" + s32ux +"\n" + s33ux + "\n\n";
                String sFux = s41ux +"\n" + s42ux +"\n" + s43ux + "\n\n";
                String sGux = s51ux +"\n" + s52ux +"\n" + s53ux + "\n\n";
                String sHux = s61ux +"\n" + s62ux +"\n" + s63ux + "\n\n";
                String sIux = s71ux +"\n" + s72ux +"\n" + s73ux + "\n\n";
                String sJux = s81ux +"\n" + s82ux +"\n" + s83ux + "\n\n";
                String sKux = s91ux +"\n" + s92ux +"\n" + s93ux + "\n\n";

                sAux = sAux + sBux + sCux + sDux + sEux + sFux + sGux + sHux + sIux + sJux + sKux + "Check out more Cryptocurrency Live Price, News, Podcast, Event, and more on CoinsCapMarket App - https://goo.gl/mFXBDn \n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));



                break;

        }
    }



    /*-------------------------------------------STUDENT_NAME_POPUP-----------------------------------------------------------*/


    private void showPopup() {
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

        ListView lv_school = view.findViewById(R.id.list_language);

        pair_adapter = new Pair_Adapter(TopList_Activity.this, mPairArrayList);
        lv_school.setAdapter(pair_adapter);

        lv_school.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popupWindow.dismiss();
            }
        });


        popupWindow.showAsDropDown(tv_popup1, 0, 2);
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
                new apiTopList(currency).execute();
                popupWindow.dismiss();

            }
        });

        tv_jpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup1.setText(tv_jpy.getText().toString());
                currency =  tv_jpy.getText().toString();
                new apiTopList(currency).execute();
                popupWindow.dismiss();

            }
        });

        tv_euro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup1.setText(tv_euro.getText().toString());
                currency = tv_euro.getText().toString();
                new apiTopList(currency).execute();
                popupWindow.dismiss();

            }
        });

        popupWindow.showAsDropDown(tv_popup1, 0, 2);
    }






    public class apiTopList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, currency;


        public apiTopList(String currency) {
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
                String url = "https://min-api.cryptocompare.com/data/top/totalvol?limit=10&tsym=" + currency;
                ParsedResponse p = Service.apiGetTopList(TopList_Activity.this, url);
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
                topList_adapter = new TopList_Adapter(TopList_Activity.this, mArrayList, currency);
                rv_exchange.setAdapter(topList_adapter);

            } else {
            }
        }
    }


    public class apiPairList extends AsyncTask<Void, Void, Void> {

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
                String url = "https://min-api.cryptocompare.com/data/top/pairs?fsym=BTC&limit=2000";
                ParsedResponse p = Service.apiGetPairList(TopList_Activity.this, url);
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
//            mProgressDialog.dismiss();
            if (!error) {

                new apiTopList(currency).execute();

            } else {
            }
        }
    }
}
