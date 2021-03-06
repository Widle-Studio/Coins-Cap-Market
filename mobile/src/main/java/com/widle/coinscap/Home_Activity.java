package com.widle.coinscap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.ads.consent.AdProvider;
import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.widle.coinscap.Utils.Model.Advertisement;
import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Home_Activity extends AppCompatActivity {

    private TextView tv_btc_name, tv_eth_name, tv_bch_name, tv_xrp_name, tv_ltc_name, tv_ada_name, tv_iot_name, tv_dash_name, tv_xem_name,
            tv_xmr_name, tv_eos_name, tv_btg_name, tv_qtum_name, tv_xlm_name, tv_neo_name, tv_etc_name, tv_zec_name, tv_steem_name,
            tv_xzc_name, tv_storj_name, tv_aion_name;

    private TextView tv_btc_sf, tv_eth_sf, tv_bch_sf, tv_xrp_sf, tv_ltc_sf, tv_ada_sf, tv_iot_sf, tv_dash_sf, tv_xem_sf, tv_xmr_sf, tv_eos_sf,
            tv_btg_sf, tv_qtum_sf, tv_xlm_sf, tv_neo_sf, tv_etc_sf, tv_zec_sf, tv_steem_sf, tv_xzc_sf, tv_storj_sf, tv_aion_sf;

    private TextView tv_app_name, tv_btc_mc, tv_eth_mc, tv_bch_mc, tv_xrp_mc, tv_ltc_mc, tv_ada_mc, tv_iot_mc, tv_dash_mc,
            tv_xem_mc, tv_xmr_mc, tv_eos_mc, tv_btg_mc, tv_qtum_mc, tv_xlm_mc, tv_neo_mc, tv_etc_mc, tv_zec_mc, tv_steem_mc,
            tv_xzc_mc, tv_storj_mc, tv_aion_mc;

    private TextView tv_btc_price, tv_eth_price, tv_bch_price, tv_xrp_price, tv_ltc_price, tv_ada_price, tv_iot_price, tv_dash_price,
            tv_xem_price, tv_xmr_price, tv_eos_price, tv_btg_price, tv_qtum_price, tv_xlm_price, tv_neo_price, tv_etc_price, tv_zec_price, tv_steem_price,
            tv_xzc_price, tv_storj_price, tv_aion_price;

    private TextView tv_btc_pct, tv_eth_pct, tv_bch_pct, tv_xrp_pct, tv_ltc_pct, tv_ada_pct, tv_iot_pct, tv_dash_pct,
            tv_xem_pct, tv_xmr_pct, tv_eos_pct, tv_btg_pct, tv_qtum_pct, tv_xlm_pct, tv_neo_pct, tv_etc_pct, tv_zec_pct, tv_steem_pct,
            tv_xzc_pct, tv_storj_pct, tv_aion_pct;

    private ImageView iv_setting;

    private ProgressDialog mProgressDialog;

    ArrayList<coin> mArrayList = new ArrayList<>();

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();

    PopupMenu popupmenu;

    ArrayList<Advertisement> mAdvertiseArrayList = new ArrayList<>();

    private static final String APP_ID = "ca-app-pub-7122988079171533~7969850323";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading.    ...");
        mProgressDialog.setCancelable(false);


          /*--------------------------------------------------------------------------------------*/
        ConsentInformation consentInformation = ConsentInformation.getInstance(Home_Activity.this);
        String[] publisherIds = {"pub-7122988079171533"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
//                Toast.makeText(HomeActivity.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
//                Toast.makeText(HomeActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        });

        addConsent();

        if (utils.isNetworkAvailable(Home_Activity.this)) {
            new apiGet_Advertise().execute();
        } else {
            utils.showAlertMessage(Home_Activity.this, getString(R.string.err_no_internet));
        }



        init();

//        new apiGet_Coins().execute();

    }

    public void init() {

        tv_app_name = (TextView) findViewById(R.id.tv_app_name);

        iv_setting = (ImageView) findViewById(R.id.iv_setting);

//        tv_btc_name = (TextView) findViewById(R.id.tv_btc_name);
//        tv_eth_name = (TextView) findViewById(R.id.tv_eth_name);
//        tv_bch_name = (TextView) findViewById(R.id.tv_bch_name);
//        tv_xrp_name = (TextView) findViewById(R.id.tv_xrp_name);
//        tv_ltc_name = (TextView) findViewById(R.id.tv_ltc_name);
//        tv_ada_name = (TextView) findViewById(R.id.tv_ada_name);
//        tv_iot_name = (TextView) findViewById(R.id.tv_iot_name);
//        tv_dash_name = (TextView) findViewById(R.id.tv_dash_name);
//        tv_xem_name = (TextView) findViewById(R.id.tv_xem_name);
//        tv_xmr_name = (TextView) findViewById(R.id.tv_xmr_name);
//        tv_eos_name = (TextView) findViewById(R.id.tv_eos_name);
//        tv_btg_name = (TextView) findViewById(R.id.tv_btg_name);
//        tv_qtum_name = (TextView) findViewById(R.id.tv_qtum_name);
//        tv_xlm_name = (TextView) findViewById(R.id.tv_xlm_name);
//        tv_neo_name = (TextView) findViewById(R.id.tv_neo_name);
//        tv_etc_name = (TextView) findViewById(R.id.tv_etc_name);
//        tv_zec_name = (TextView) findViewById(R.id.tv_zec_name);
//        tv_steem_name = (TextView) findViewById(R.id.tv_steem_name);
//        tv_xzc_name = (TextView) findViewById(R.id.tv_xzc_name);
//        tv_storj_name = (TextView) findViewById(R.id.tv_storj_name);
//        tv_aion_name = (TextView) findViewById(R.id.tv_aion_name);
//
//        tv_btc_sf = (TextView) findViewById(R.id.tv_btc_sf);
//        tv_eth_sf = (TextView) findViewById(R.id.tv_eth_sf);
//        tv_bch_sf = (TextView) findViewById(R.id.tv_bch_sf);
//        tv_xrp_sf = (TextView) findViewById(R.id.tv_xrp_sf);
//        tv_ltc_sf = (TextView) findViewById(R.id.tv_ltc_sf);
//        tv_ada_sf = (TextView) findViewById(R.id.tv_ada_sf);
//        tv_iot_sf = (TextView) findViewById(R.id.tv_iot_sf);
//        tv_dash_sf = (TextView) findViewById(R.id.tv_dash_sf);
//        tv_xem_sf = (TextView) findViewById(R.id.tv_xem_sf);
//        tv_xmr_sf = (TextView) findViewById(R.id.tv_xmr_sf);
//        tv_eos_sf = (TextView) findViewById(R.id.tv_eos_sf);
//        tv_btg_sf = (TextView) findViewById(R.id.tv_btg_sf);
//        tv_qtum_sf = (TextView) findViewById(R.id.tv_qtum_sf);
//        tv_xlm_sf = (TextView) findViewById(R.id.tv_xlm_sf);
//        tv_neo_sf = (TextView) findViewById(R.id.tv_neo_sf);
//        tv_etc_sf = (TextView) findViewById(R.id.tv_etc_sf);
//        tv_zec_sf = (TextView) findViewById(R.id.tv_zec_sf);
//        tv_steem_sf = (TextView) findViewById(R.id.tv_steem_sf);
//        tv_xzc_sf = (TextView) findViewById(R.id.tv_xzc_sf);
//        tv_storj_sf = (TextView) findViewById(R.id.tv_storj_sf);
//        tv_aion_sf = (TextView) findViewById(R.id.tv_aion_sf);
//
//
//        tv_btc_mc = (TextView) findViewById(R.id.tv_btc_mc);
//        tv_eth_mc = (TextView) findViewById(R.id.tv_eth_mc);
//        tv_bch_mc = (TextView) findViewById(R.id.tv_bch_mc);
//        tv_xrp_mc = (TextView) findViewById(R.id.tv_xrp_mc);
//        tv_ltc_mc = (TextView) findViewById(R.id.tv_ltc_mc);
//        tv_ada_mc = (TextView) findViewById(R.id.tv_ada_mc);
//        tv_iot_mc = (TextView) findViewById(R.id.tv_iot_mc);
//        tv_dash_mc = (TextView) findViewById(R.id.tv_dash_mc);
//        tv_xem_mc = (TextView) findViewById(R.id.tv_xem_mc);
//        tv_xmr_mc = (TextView) findViewById(R.id.tv_xmr_mc);
//        tv_eos_mc = (TextView) findViewById(R.id.tv_eos_mc);
//        tv_btg_mc = (TextView) findViewById(R.id.tv_btg_mc);
//        tv_qtum_mc = (TextView) findViewById(R.id.tv_qtum_mc);
//        tv_xlm_mc = (TextView) findViewById(R.id.tv_xlm_mc);
//        tv_neo_mc = (TextView) findViewById(R.id.tv_neo_mc);
//        tv_etc_mc = (TextView) findViewById(R.id.tv_etc_mc);
//        tv_zec_mc = (TextView) findViewById(R.id.tv_zec_mc);
//        tv_steem_mc = (TextView) findViewById(R.id.tv_steem_mc);
//        tv_xzc_mc = (TextView) findViewById(R.id.tv_xzc_mc);
//        tv_storj_mc = (TextView) findViewById(R.id.tv_storj_mc);
//        tv_aion_mc = (TextView) findViewById(R.id.tv_aion_mc);
//
//
//        tv_btc_price = (TextView) findViewById(R.id.tv_btc_price);
//        tv_eth_price = (TextView) findViewById(R.id.tv_eth_price);
//        tv_bch_price = (TextView) findViewById(R.id.tv_bch_price);
//        tv_xrp_price = (TextView) findViewById(R.id.tv_xrp_price);
//        tv_ltc_price = (TextView) findViewById(R.id.tv_ltc_price);
//        tv_ada_price = (TextView) findViewById(R.id.tv_ada_price);
//        tv_iot_price = (TextView) findViewById(R.id.tv_iot_price);
//        tv_dash_price = (TextView) findViewById(R.id.tv_dash_price);
//        tv_xem_price = (TextView) findViewById(R.id.tv_xem_price);
//        tv_xmr_price = (TextView) findViewById(R.id.tv_xmr_price);
//        tv_eos_price = (TextView) findViewById(R.id.tv_eos_price);
//        tv_btg_price = (TextView) findViewById(R.id.tv_btg_price);
//        tv_qtum_price = (TextView) findViewById(R.id.tv_qtum_price);
//        tv_xlm_price = (TextView) findViewById(R.id.tv_xlm_price);
//        tv_neo_price = (TextView) findViewById(R.id.tv_neo_price);
//        tv_etc_price = (TextView) findViewById(R.id.tv_etc_price);
//        tv_zec_price = (TextView) findViewById(R.id.tv_zec_price);
//        tv_steem_price = (TextView) findViewById(R.id.tv_steem_price);
//        tv_xzc_price = (TextView) findViewById(R.id.tv_xzc_price);
//        tv_storj_price = (TextView) findViewById(R.id.tv_storj_price);
//        tv_aion_price = (TextView) findViewById(R.id.tv_aion_price);
//
//        tv_btc_pct = (TextView) findViewById(R.id.tv_btc_pct);
//        tv_eth_pct = (TextView) findViewById(R.id.tv_eth_pct);
//        tv_bch_pct = (TextView) findViewById(R.id.tv_bch_pct);
//        tv_xrp_pct = (TextView) findViewById(R.id.tv_xrp_pct);
//        tv_ltc_pct = (TextView) findViewById(R.id.tv_ltc_pct);
//        tv_ada_pct = (TextView) findViewById(R.id.tv_ada_pct);
//        tv_iot_pct = (TextView) findViewById(R.id.tv_iot_pct);
//        tv_dash_pct = (TextView) findViewById(R.id.tv_dash_pct);
//        tv_xem_pct = (TextView) findViewById(R.id.tv_xem_pct);
//        tv_xmr_pct = (TextView) findViewById(R.id.tv_xmr_pct);
//        tv_eos_pct = (TextView) findViewById(R.id.tv_eos_pct);
//        tv_btg_pct = (TextView) findViewById(R.id.tv_btg_pct);
//        tv_qtum_pct = (TextView) findViewById(R.id.tv_qtum_ptc);
//        tv_xlm_pct = (TextView) findViewById(R.id.tv_xlm_pct);
//        tv_neo_pct = (TextView) findViewById(R.id.tv_neo_pct);
//        tv_etc_pct = (TextView) findViewById(R.id.tv_etc_pct);
//        tv_zec_pct = (TextView) findViewById(R.id.tv_zec_pct);
//        tv_steem_pct = (TextView) findViewById(R.id.tv_steem_ptc);
//        tv_xzc_pct = (TextView) findViewById(R.id.tv_xzc_ptc);
//        tv_storj_pct = (TextView) findViewById(R.id.tv_storj_ptc);
//        tv_aion_pct = (TextView) findViewById(R.id.tv_aion_ptc);


        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_app_name.setTypeface(type2);

//        tv_btc_name.setTypeface(type1);
//        tv_eth_name.setTypeface(type1);
//        tv_bch_name.setTypeface(type1);
//        tv_xrp_name.setTypeface(type1);
//        tv_ltc_name.setTypeface(type1);
//        tv_ada_name.setTypeface(type1);
//        tv_iot_name.setTypeface(type1);
//        tv_dash_name.setTypeface(type1);
//        tv_xem_name.setTypeface(type1);
//        tv_xmr_name.setTypeface(type1);
//        tv_eos_name.setTypeface(type1);
//        tv_btg_name.setTypeface(type1);
//        tv_qtum_name.setTypeface(type1);
//        tv_xlm_name.setTypeface(type1);
//        tv_neo_name.setTypeface(type1);
//        tv_etc_name.setTypeface(type1);
//        tv_zec_name.setTypeface(type1);
//        tv_steem_name.setTypeface(type1);
//        tv_xzc_name.setTypeface(type1);
//        tv_storj_name.setTypeface(type1);
//        tv_aion_name.setTypeface(type1);
//
//        tv_btc_sf.setTypeface(type1);
//        tv_eth_sf.setTypeface(type1);
//        tv_bch_sf.setTypeface(type1);
//        tv_xrp_sf.setTypeface(type1);
//        tv_ltc_sf.setTypeface(type1);
//        tv_ada_sf.setTypeface(type1);
//        tv_iot_sf.setTypeface(type1);
//        tv_dash_sf.setTypeface(type1);
//        tv_xem_sf.setTypeface(type1);
//        tv_xmr_sf.setTypeface(type1);
//        tv_eos_sf.setTypeface(type1);
//        tv_btg_sf.setTypeface(type1);
//        tv_qtum_sf.setTypeface(type1);
//        tv_xlm_sf.setTypeface(type1);
//        tv_neo_sf.setTypeface(type1);
//        tv_etc_sf.setTypeface(type1);
//        tv_zec_sf.setTypeface(type1);
//        tv_steem_sf.setTypeface(type1);
//        tv_xzc_sf.setTypeface(type1);
//        tv_storj_sf.setTypeface(type1);
//        tv_aion_sf.setTypeface(type1);
//
//        tv_btc_price.setTypeface(type);
//        tv_eth_price.setTypeface(type);
//        tv_bch_price.setTypeface(type);
//        tv_xrp_price.setTypeface(type);
//        tv_ltc_price.setTypeface(type);
//        tv_ada_price.setTypeface(type);
//        tv_iot_price.setTypeface(type);
//        tv_dash_price.setTypeface(type);
//        tv_xem_price.setTypeface(type);
//        tv_xmr_price.setTypeface(type);
//        tv_eos_price.setTypeface(type);
//        tv_btg_price.setTypeface(type);
//        tv_qtum_price.setTypeface(type);
//        tv_xlm_price.setTypeface(type);
//        tv_neo_price.setTypeface(type);
//        tv_etc_price.setTypeface(type);
//        tv_zec_price.setTypeface(type);
//        tv_steem_pct.setTypeface(type);
//        tv_xzc_price.setTypeface(type);
//        tv_storj_price.setTypeface(type);
//        tv_aion_price.setTypeface(type);
//
//        tv_btc_pct.setTypeface(type1);
//        tv_eth_pct.setTypeface(type1);
//        tv_bch_pct.setTypeface(type1);
//        tv_xrp_pct.setTypeface(type1);
//        tv_ltc_pct.setTypeface(type1);
//        tv_ada_pct.setTypeface(type1);
//        tv_iot_pct.setTypeface(type1);
//        tv_dash_pct.setTypeface(type1);
//        tv_xem_pct.setTypeface(type1);
//        tv_xmr_pct.setTypeface(type1);
//        tv_eos_pct.setTypeface(type1);
//        tv_btg_pct.setTypeface(type1);
//        tv_qtum_pct.setTypeface(type1);
//        tv_xlm_pct.setTypeface(type1);
//        tv_neo_pct.setTypeface(type1);
//        tv_etc_pct.setTypeface(type1);
//        tv_zec_pct.setTypeface(type1);
//        tv_steem_pct.setTypeface(type1);
//        tv_xzc_pct.setTypeface(type1);
//        tv_storj_pct.setTypeface(type1);
//        tv_aion_pct.setTypeface(type1);
//
//        tv_btc_pct.setTypeface(type1);
//        tv_eth_pct.setTypeface(type1);
//        tv_bch_pct.setTypeface(type1);
//        tv_xrp_pct.setTypeface(type1);
//        tv_ltc_pct.setTypeface(type1);
//        tv_ada_pct.setTypeface(type1);
//        tv_iot_pct.setTypeface(type1);
//        tv_dash_pct.setTypeface(type1);
//        tv_xem_pct.setTypeface(type1);
//        tv_xmr_pct.setTypeface(type1);
//        tv_eos_pct.setTypeface(type1);
//        tv_btg_pct.setTypeface(type1);
//        tv_qtum_pct.setTypeface(type1);
//        tv_xlm_pct.setTypeface(type1);
//        tv_neo_pct.setTypeface(type1);
//        tv_etc_pct.setTypeface(type1);
//        tv_zec_pct.setTypeface(type1);
//        tv_steem_pct.setTypeface(type1);
//        tv_xzc_pct.setTypeface(type1);
//        tv_storj_pct.setTypeface(type1);
//        tv_aion_pct.setTypeface(type1);
//
//        tv_btc_mc.setTypeface(type1);
//        tv_eth_mc.setTypeface(type1);
//        tv_bch_mc.setTypeface(type1);
//        tv_xrp_mc.setTypeface(type1);
//        tv_ltc_mc.setTypeface(type1);
//        tv_ada_mc.setTypeface(type1);
//        tv_iot_mc.setTypeface(type1);
//        tv_dash_mc.setTypeface(type1);
//        tv_xem_mc.setTypeface(type1);
//        tv_xmr_mc.setTypeface(type1);
//        tv_eos_mc.setTypeface(type1);
//        tv_btg_mc.setTypeface(type1);
//        tv_qtum_mc.setTypeface(type1);
//        tv_xlm_mc.setTypeface(type1);
//        tv_neo_mc.setTypeface(type1);
//        tv_etc_mc.setTypeface(type1);
//        tv_zec_mc.setTypeface(type1);
//        tv_steem_mc.setTypeface(type1);
//        tv_xzc_mc.setTypeface(type1);
//        tv_storj_mc.setTypeface(type1);
//        tv_aion_mc.setTypeface(type1);


        iv_setting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popupmenu = new PopupMenu(Home_Activity.this, view);
                PopMenuDisplay();
                return false;
            }
        });
    }




    public void addConsent (){

        URL privacyUrl = null;
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = new URL("https://widle.studio/coinscapmarket/privacy.html");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }
        ConsentForm form = new ConsentForm.Builder(Home_Activity.this, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // Consent form loaded successfully.
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                    }

                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed.
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // Consent form error.
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .withAdFreeOption()
                .build();



        List<AdProvider> adProviders =
                ConsentInformation.getInstance(Home_Activity.this).getAdProviders();

        ConsentInformation.getInstance(Home_Activity.this)
                .setConsentStatus(ConsentStatus.NON_PERSONALIZED);

        ConsentInformation.getInstance(Home_Activity.this).setTagForUnderAgeOfConsent(true);


    }



    //-----------------------------------------API_FOR_GET_COINS-------------------------------------------------------------------

    public class apiGet_Advertise extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                ParsedResponse p = Service.apiGetAdvertise(Home_Activity.this);
                error = p.error;
                if (!error) {
                    mAdvertiseArrayList = (ArrayList<Advertisement>) p.o;
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
            } else {
                utils.showAlertMessage(Home_Activity.this, msg);
            }
        }
    }


    public void PopMenuDisplay() {
        popupmenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about:
                        Intent intent = new Intent(Home_Activity.this, Acknowledgement_Activity.class);
                        intent.putExtra("type", "1");
                        startActivity(intent);
                        return true;

                    case R.id.help:
                        Intent i1 = new Intent(Home_Activity.this, Acknowledgement_Activity.class);
                        i1.putExtra("type", "2");
                        startActivity(i1);
                        return true;

                    case R.id.acknowledgement:
                        Intent i2 = new Intent(Home_Activity.this, Acknowledgement1_Activity.class);
                        startActivity(i2);
                        return true;

                    case R.id.privacy:
                        Intent i3 = new Intent(Home_Activity.this, Acknowledgement_Activity.class);
                        i3.putExtra("type", "4");
                        startActivity(i3);
                        return true;

                    case R.id.terms:
                        Intent i4 = new Intent(Home_Activity.this, Acknowledgement_Activity.class);
                        i4.putExtra("type", "5");
                        startActivity(i4);
                        return true;

                    case R.id.cookie:
                        Intent i5 = new Intent(Home_Activity.this, Acknowledgement_Activity.class);
                        i5.putExtra("type", "6");
                        startActivity(i5);
                        return true;

                    case R.id.rate:
                        Intent i6 = new Intent(Home_Activity.this, Acknowledgement_Activity.class);
                        i6.putExtra("type", "7");
                        startActivity(i6);
                        return true;
                }
                return false;
            }
        });
        popupmenu.show();
    }


    //-----------------------------------------API_FOR_GET_COINS-------------------------------------------------------------------
//
//
//    public class apiGet_Coins extends AsyncTask<Void, Void, Void> {
//
//        boolean error;
//        String msg;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            try {
//                ParsedResponse p = Service.apiGetCoin(Home_Activity.this, "");
//                error = p.error;
//                if (!error) {
//                    mArrayList = (ArrayList<coin>) p.o;
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
//            if (!error) {
//
//                for (int i = 0; i < mArrayList.size(); i++) {
//                    tv_btc_price.setText(mArrayList.get(0).PRICE);
//                    tv_eth_price.setText(mArrayList.get(1).PRICE);
//                    tv_bch_price.setText(mArrayList.get(2).PRICE);
//                    tv_xrp_price.setText(mArrayList.get(3).PRICE);
//                    tv_ltc_price.setText(mArrayList.get(4).PRICE);
//                    tv_ada_price.setText(mArrayList.get(5).PRICE);
//                    tv_iot_price.setText(mArrayList.get(6).PRICE);
//                    tv_dash_price.setText(mArrayList.get(7).PRICE);
//                    tv_xem_price.setText(mArrayList.get(8).PRICE);
//                    tv_xmr_price.setText(mArrayList.get(9).PRICE);
//                    tv_eos_price.setText(mArrayList.get(10).PRICE);
//                    tv_btg_price.setText(mArrayList.get(11).PRICE);
//                    tv_qtum_price.setText(mArrayList.get(12).PRICE);
//                    tv_xlm_price.setText(mArrayList.get(13).PRICE);
//                    tv_neo_price.setText(mArrayList.get(14).PRICE);
//                    tv_etc_price.setText(mArrayList.get(15).PRICE);
//                    tv_zec_price.setText(mArrayList.get(16).PRICE);
//                    tv_steem_price.setText(mArrayList.get(17).PRICE);
//                    tv_xzc_price.setText(mArrayList.get(18).PRICE);
//                    tv_storj_price.setText(mArrayList.get(19).PRICE);
//                    tv_aion_price.setText(mArrayList.get(20).PRICE);
//
//                    tv_btc_mc.setText("M.C:" + mArrayList.get(0).MKTCAP);
//                    tv_eth_mc.setText("M.C:" + mArrayList.get(1).MKTCAP);
//                    tv_bch_mc.setText("M.C:" + mArrayList.get(2).MKTCAP);
//                    tv_xrp_mc.setText("M.C:" + mArrayList.get(3).MKTCAP);
//                    tv_ltc_mc.setText("M.C:" + mArrayList.get(4).MKTCAP);
//                    tv_ada_mc.setText("M.C:" + mArrayList.get(5).MKTCAP);
//                    tv_iot_mc.setText("M.C:" + mArrayList.get(6).MKTCAP);
//                    tv_dash_mc.setText("M.C:" + mArrayList.get(7).MKTCAP);
//                    tv_xem_mc.setText("M.C:" + mArrayList.get(8).MKTCAP);
//                    tv_xmr_mc.setText("M.C:" + mArrayList.get(9).MKTCAP);
//                    tv_eos_mc.setText("M.C:" + mArrayList.get(10).MKTCAP);
//                    tv_btg_mc.setText("M.C:" + mArrayList.get(11).MKTCAP);
//                    tv_qtum_mc.setText("M.C:" + mArrayList.get(12).MKTCAP);
//                    tv_xlm_mc.setText("M.C:" + mArrayList.get(13).MKTCAP);
//                    tv_neo_mc.setText("M.C:" + mArrayList.get(14).MKTCAP);
//                    tv_etc_mc.setText("M.C:" + mArrayList.get(15).MKTCAP);
//                    tv_zec_mc.setText("M.C:" + mArrayList.get(16).MKTCAP);
//                    tv_steem_mc.setText("M.C:" + mArrayList.get(17).MKTCAP);
//                    tv_xzc_mc.setText("M.C:" + mArrayList.get(18).MKTCAP);
//                    tv_storj_mc.setText("M.C:" + mArrayList.get(19).MKTCAP);
//                    tv_aion_mc.setText("M.C:" + mArrayList.get(20).MKTCAP);
//
//
//                    if (mArrayList.get(0).CHANGEPCT24HOUR.contains("-")) {
//                        tv_btc_pct.setText(mArrayList.get(0).CHANGE24HOUR + " (" + mArrayList.get(0).CHANGEPCT24HOUR + "%)");
//                        tv_btc_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_btc_pct.setText(mArrayList.get(0).CHANGE24HOUR + " (" + mArrayList.get(0).CHANGEPCT24HOUR + "%)");
//                        tv_btc_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(1).CHANGEPCT24HOUR.contains("-")) {
//                        tv_eth_pct.setText(mArrayList.get(1).CHANGE24HOUR + " (" + mArrayList.get(1).CHANGEPCT24HOUR + "%)");
//                        tv_eth_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_eth_pct.setText(mArrayList.get(1).CHANGE24HOUR + " (" + mArrayList.get(1).CHANGEPCT24HOUR + "%)");
//                        tv_eth_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(2).CHANGEPCT24HOUR.contains("-")) {
//                        tv_bch_pct.setText(mArrayList.get(2).CHANGE24HOUR + " (" + mArrayList.get(2).CHANGEPCT24HOUR + "%)");
//                        tv_bch_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_bch_pct.setText(mArrayList.get(2).CHANGE24HOUR + " (" + mArrayList.get(2).CHANGEPCT24HOUR + "%)");
//                        tv_bch_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(3).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xrp_pct.setText(mArrayList.get(3).CHANGE24HOUR + " (" + mArrayList.get(3).CHANGEPCT24HOUR + "%)");
//                        tv_xrp_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xrp_pct.setText(mArrayList.get(3).CHANGE24HOUR + "(" + mArrayList.get(3).CHANGEPCT24HOUR + "%)");
//                        tv_xrp_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(4).CHANGEPCT24HOUR.contains("-")) {
//                        tv_ltc_pct.setText(mArrayList.get(4).CHANGE24HOUR + " (" + mArrayList.get(4).CHANGEPCT24HOUR + "%)");
//                        tv_ltc_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_ltc_pct.setText(mArrayList.get(4).CHANGE24HOUR + " (" + mArrayList.get(4).CHANGEPCT24HOUR + "%)");
//                        tv_ltc_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(5).CHANGEPCT24HOUR.contains("-")) {
//                        tv_ada_pct.setText(mArrayList.get(5).CHANGE24HOUR + " (" + mArrayList.get(5).CHANGEPCT24HOUR + "%)");
//                        tv_ada_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_ada_pct.setText(mArrayList.get(5).CHANGE24HOUR + " (" + mArrayList.get(5).CHANGEPCT24HOUR + "%)");
//                        tv_ada_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(6).CHANGEPCT24HOUR.contains("-")) {
//                        tv_iot_pct.setText(mArrayList.get(6).CHANGE24HOUR + " (" + mArrayList.get(6).CHANGEPCT24HOUR + "%)");
//                        tv_iot_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_iot_pct.setText(mArrayList.get(6).CHANGE24HOUR + " (" + mArrayList.get(6).CHANGEPCT24HOUR + "%)");
//                        tv_iot_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(7).CHANGEPCT24HOUR.contains("-")) {
//                        tv_dash_pct.setText(mArrayList.get(7).CHANGE24HOUR + " (" + mArrayList.get(7).CHANGEPCT24HOUR + "%)");
//                        tv_dash_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_dash_pct.setText(mArrayList.get(7).CHANGE24HOUR + " (" + mArrayList.get(7).CHANGEPCT24HOUR + "%)");
//                        tv_dash_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(8).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xem_pct.setText(mArrayList.get(8).CHANGE24HOUR + " (" + mArrayList.get(8).CHANGEPCT24HOUR + "%)");
//                        tv_xem_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xem_pct.setText(mArrayList.get(8).CHANGE24HOUR + " (" + mArrayList.get(8).CHANGEPCT24HOUR + "%)");
//                        tv_xem_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(9).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xmr_pct.setText(mArrayList.get(9).CHANGE24HOUR + " (" + mArrayList.get(9).CHANGEPCT24HOUR + "%)");
//                        tv_xmr_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xmr_pct.setText(mArrayList.get(9).CHANGE24HOUR + " (" + mArrayList.get(9).CHANGEPCT24HOUR + "%)");
//                        tv_xmr_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(10).CHANGEPCT24HOUR.contains("-")) {
//                        tv_eos_pct.setText(mArrayList.get(10).CHANGE24HOUR + " (" + mArrayList.get(10).CHANGEPCT24HOUR + "%)");
//                        tv_eos_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_eos_pct.setText(mArrayList.get(10).CHANGE24HOUR + " (" + mArrayList.get(10).CHANGEPCT24HOUR + "%)");
//                        tv_eos_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(11).CHANGEPCT24HOUR.contains("-")) {
//                        tv_btg_pct.setText(mArrayList.get(11).CHANGE24HOUR + " (" + mArrayList.get(11).CHANGEPCT24HOUR + "%)");
//                        tv_btg_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_btg_pct.setText(mArrayList.get(11).CHANGE24HOUR + " (" + mArrayList.get(11).CHANGEPCT24HOUR + "%)");
//                        tv_btg_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(12).CHANGEPCT24HOUR.contains("-")) {
//                        tv_qtum_pct.setText(mArrayList.get(12).CHANGE24HOUR + " (" + mArrayList.get(12).CHANGEPCT24HOUR + "%)");
//                        tv_qtum_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_qtum_pct.setText(mArrayList.get(12).CHANGE24HOUR + " (" + mArrayList.get(12).CHANGEPCT24HOUR + "%)");
//                        tv_qtum_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(13).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xlm_pct.setText(mArrayList.get(13).CHANGE24HOUR + " (" + mArrayList.get(13).CHANGEPCT24HOUR + "%)");
//                        tv_xlm_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xlm_pct.setText(mArrayList.get(13).CHANGE24HOUR + " (" + mArrayList.get(13).CHANGEPCT24HOUR + "%)");
//                        tv_xlm_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(14).CHANGEPCT24HOUR.contains("-")) {
//                        tv_neo_pct.setText(mArrayList.get(14).CHANGE24HOUR + " (" + mArrayList.get(14).CHANGEPCT24HOUR + "%)");
//                        tv_neo_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_neo_pct.setText(mArrayList.get(14).CHANGE24HOUR + " (" + mArrayList.get(14).CHANGEPCT24HOUR + "%)");
//                        tv_neo_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(15).CHANGEPCT24HOUR.contains("-")) {
//                        tv_etc_pct.setText(mArrayList.get(15).CHANGE24HOUR + " (" + mArrayList.get(15).CHANGEPCT24HOUR + "%)");
//                        tv_etc_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_etc_pct.setText(mArrayList.get(15).CHANGE24HOUR + " (" + mArrayList.get(15).CHANGEPCT24HOUR + "%)");
//                        tv_etc_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(16).CHANGEPCT24HOUR.contains("-")) {
//                        tv_zec_pct.setText(mArrayList.get(16).CHANGE24HOUR + " (" + mArrayList.get(16).CHANGEPCT24HOUR + "%)");
//                        tv_zec_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_zec_pct.setText(mArrayList.get(16).CHANGE24HOUR + " (" + mArrayList.get(16).CHANGEPCT24HOUR + "%)");
//                        tv_zec_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//
//                    if (mArrayList.get(17).CHANGEPCT24HOUR.contains("-")) {
//                        tv_steem_pct.setText(mArrayList.get(17).CHANGE24HOUR + " (" + mArrayList.get(17).CHANGEPCT24HOUR + "%)");
//                        tv_steem_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_steem_pct.setText(mArrayList.get(17).CHANGE24HOUR + " (" + mArrayList.get(17).CHANGEPCT24HOUR + "%)");
//                        tv_steem_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//
//                    if (mArrayList.get(18).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xzc_pct.setText(mArrayList.get(18).CHANGE24HOUR + " (" + mArrayList.get(18).CHANGEPCT24HOUR + "%)");
//                        tv_xzc_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xzc_pct.setText(mArrayList.get(18).CHANGE24HOUR + " (" + mArrayList.get(18).CHANGEPCT24HOUR + "%)");
//                        tv_xzc_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//
//                    if (mArrayList.get(19).CHANGEPCT24HOUR.contains("-")) {
//                        tv_storj_pct.setText(mArrayList.get(19).CHANGE24HOUR + " (" + mArrayList.get(19).CHANGEPCT24HOUR + "%)");
//                        tv_storj_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_storj_pct.setText(mArrayList.get(19).CHANGE24HOUR + " (" + mArrayList.get(19).CHANGEPCT24HOUR + "%)");
//                        tv_storj_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//
//                    if (mArrayList.get(20).CHANGEPCT24HOUR.contains("-")) {
//                        tv_aion_pct.setText(mArrayList.get(20).CHANGE24HOUR + " (" + mArrayList.get(20).CHANGEPCT24HOUR + "%)");
//                        tv_aion_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_aion_pct.setText(mArrayList.get(20).CHANGE24HOUR + " (" + mArrayList.get(20).CHANGEPCT24HOUR + "%)");
//                        tv_aion_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//                }
//
//                update();
//
//            } else {
//                utils.showAlertMessage(Home_Activity.this, msg);
//            }
//        }
//    }


//    /*-------------------------------------------------Update_Service-------------------------------------------------------*/
//
//    public void update() {
//        timer = new Timer();
//        //initialize the TimerTask's job
//        initializeChatTask();
//        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
//        timer.schedule(timerTask, 0, 3000); //
//    }
//
//
//    public void initializeChatTask() {
//        timerTask = new TimerTask() {
//
//            @Override
//            public void run() {
//
//                handler.post(new Runnable() {
//                    public void run() {
//                        new apiGet_Update_Coins().execute();
////                        if (utils.isNetworkAvailable(Home_Activity.this)) {
////                        } else {
////                            utils.showAlertMessage(Home_Activity.this, getString(R.string.err_no_internet));
////                        }
//
//                    }
//                });
//
//            }
//        };
//    }
//

//    //-----------------------------------------API_FOR_GET_COINS-------------------------------------------------------------------
//
//
//    public class apiGet_Update_Coins extends AsyncTask<Void, Void, Void> {
//
//        boolean error;
//        String msg;
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            try {
//                ParsedResponse p = Service.apiGetCoin(Home_Activity.this,"");
//                error = p.error;
//                if (!error) {
//                    mArrayList = (ArrayList<coin>) p.o;
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
//            if (!error) {
//
//                for (int i = 0; i < mArrayList.size(); i++) {
//                    tv_btc_price.setText(mArrayList.get(0).PRICE);
//                    tv_eth_price.setText(mArrayList.get(1).PRICE);
//                    tv_bch_price.setText(mArrayList.get(2).PRICE);
//                    tv_xrp_price.setText(mArrayList.get(3).PRICE);
//                    tv_ltc_price.setText(mArrayList.get(4).PRICE);
//                    tv_ada_price.setText(mArrayList.get(5).PRICE);
//                    tv_iot_price.setText(mArrayList.get(6).PRICE);
//                    tv_dash_price.setText(mArrayList.get(7).PRICE);
//                    tv_xem_price.setText(mArrayList.get(8).PRICE);
//                    tv_xmr_price.setText(mArrayList.get(9).PRICE);
//                    tv_eos_price.setText(mArrayList.get(10).PRICE);
//                    tv_btg_price.setText(mArrayList.get(11).PRICE);
//                    tv_qtum_price.setText(mArrayList.get(12).PRICE);
//                    tv_xlm_price.setText(mArrayList.get(13).PRICE);
//                    tv_neo_price.setText(mArrayList.get(14).PRICE);
//                    tv_etc_price.setText(mArrayList.get(15).PRICE);
//                    tv_zec_price.setText(mArrayList.get(16).PRICE);
//                    tv_steem_price.setText(mArrayList.get(17).PRICE);
//                    tv_xzc_price.setText(mArrayList.get(18).PRICE);
//                    tv_storj_price.setText(mArrayList.get(19).PRICE);
//                    tv_aion_price.setText(mArrayList.get(20).PRICE);
//
//                    tv_btc_mc.setText("M.C:" + mArrayList.get(0).MKTCAP);
//                    tv_eth_mc.setText("M.C:" + mArrayList.get(1).MKTCAP);
//                    tv_bch_mc.setText("M.C:" + mArrayList.get(2).MKTCAP);
//                    tv_xrp_mc.setText("M.C:" + mArrayList.get(3).MKTCAP);
//                    tv_ltc_mc.setText("M.C:" + mArrayList.get(4).MKTCAP);
//                    tv_ada_mc.setText("M.C:" + mArrayList.get(5).MKTCAP);
//                    tv_iot_mc.setText("M.C:" + mArrayList.get(6).MKTCAP);
//                    tv_dash_mc.setText("M.C:" + mArrayList.get(7).MKTCAP);
//                    tv_xem_mc.setText("M.C:" + mArrayList.get(8).MKTCAP);
//                    tv_xmr_mc.setText("M.C:" + mArrayList.get(9).MKTCAP);
//                    tv_eos_mc.setText("M.C:" + mArrayList.get(10).MKTCAP);
//                    tv_btg_mc.setText("M.C:" + mArrayList.get(11).MKTCAP);
//                    tv_qtum_mc.setText("M.C:" + mArrayList.get(12).MKTCAP);
//                    tv_xlm_mc.setText("M.C:" + mArrayList.get(13).MKTCAP);
//                    tv_neo_mc.setText("M.C:" + mArrayList.get(14).MKTCAP);
//                    tv_etc_mc.setText("M.C:" + mArrayList.get(15).MKTCAP);
//                    tv_zec_mc.setText("M.C:" + mArrayList.get(16).MKTCAP);
//                    tv_steem_mc.setText("M.C:" + mArrayList.get(17).MKTCAP);
//                    tv_xzc_mc.setText("M.C:" + mArrayList.get(18).MKTCAP);
//                    tv_storj_mc.setText("M.C:" + mArrayList.get(19).MKTCAP);
//                    tv_aion_mc.setText("M.C:" + mArrayList.get(20).MKTCAP);
//
//
//                    if (mArrayList.get(0).CHANGEPCT24HOUR.contains("-")) {
//                        tv_btc_pct.setText(mArrayList.get(0).CHANGE24HOUR + " (" + mArrayList.get(0).CHANGEPCT24HOUR + "%)");
//                        tv_btc_pct.setTextColor(Color.parseColor("#FF0000"));
//
//                    } else {
//                        tv_btc_pct.setText(mArrayList.get(0).CHANGE24HOUR + " (" + mArrayList.get(0).CHANGEPCT24HOUR + "%)");
//                        tv_btc_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(1).CHANGEPCT24HOUR.contains("-")) {
//                        tv_eth_pct.setText(mArrayList.get(1).CHANGE24HOUR + " (" + mArrayList.get(1).CHANGEPCT24HOUR + "%)");
//                        tv_eth_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_eth_pct.setText(mArrayList.get(1).CHANGE24HOUR + " (" + mArrayList.get(1).CHANGEPCT24HOUR + "%)");
//                        tv_eth_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(2).CHANGEPCT24HOUR.contains("-")) {
//                        tv_bch_pct.setText(mArrayList.get(2).CHANGE24HOUR + " (" + mArrayList.get(2).CHANGEPCT24HOUR + "%)");
//                        tv_bch_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_bch_pct.setText(mArrayList.get(2).CHANGE24HOUR + " (" + mArrayList.get(2).CHANGEPCT24HOUR + "%)");
//                        tv_bch_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(3).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xrp_pct.setText(mArrayList.get(3).CHANGE24HOUR + " (" + mArrayList.get(3).CHANGEPCT24HOUR + "%)");
//                        tv_xrp_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xrp_pct.setText(mArrayList.get(3).CHANGE24HOUR + " (" + mArrayList.get(3).CHANGEPCT24HOUR + "%)");
//                        tv_xrp_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(4).CHANGEPCT24HOUR.contains("-")) {
//                        tv_ltc_pct.setText(mArrayList.get(4).CHANGE24HOUR + " (" + mArrayList.get(4).CHANGEPCT24HOUR + "%)");
//                        tv_ltc_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_ltc_pct.setText(mArrayList.get(4).CHANGE24HOUR + " (" + mArrayList.get(4).CHANGEPCT24HOUR + "%)");
//                        tv_ltc_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(5).CHANGEPCT24HOUR.contains("-")) {
//                        tv_ada_pct.setText(mArrayList.get(5).CHANGE24HOUR + " (" + mArrayList.get(5).CHANGEPCT24HOUR + "%)");
//                        tv_ada_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_ada_pct.setText(mArrayList.get(5).CHANGE24HOUR + " (" + mArrayList.get(5).CHANGEPCT24HOUR + "%)");
//                        tv_ada_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(6).CHANGEPCT24HOUR.contains("-")) {
//                        tv_iot_pct.setText(mArrayList.get(6).CHANGE24HOUR + " (" + mArrayList.get(6).CHANGEPCT24HOUR + "%)");
//                        tv_iot_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_iot_pct.setText(mArrayList.get(6).CHANGE24HOUR + " (" + mArrayList.get(6).CHANGEPCT24HOUR + "%)");
//                        tv_iot_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(7).CHANGEPCT24HOUR.contains("-")) {
//                        tv_dash_pct.setText(mArrayList.get(7).CHANGE24HOUR + " (" + mArrayList.get(7).CHANGEPCT24HOUR + "%)");
//                        tv_dash_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_dash_pct.setText(mArrayList.get(7).CHANGE24HOUR + " (" + mArrayList.get(7).CHANGEPCT24HOUR + "%)");
//                        tv_dash_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(8).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xem_pct.setText(mArrayList.get(8).CHANGE24HOUR + " (" + mArrayList.get(8).CHANGEPCT24HOUR + "%)");
//                        tv_xem_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xem_pct.setText(mArrayList.get(8).CHANGE24HOUR + " (" + mArrayList.get(8).CHANGEPCT24HOUR + "%)");
//                        tv_xem_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(9).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xmr_pct.setText(mArrayList.get(9).CHANGE24HOUR + " (" + mArrayList.get(9).CHANGEPCT24HOUR + "%)");
//                        tv_xmr_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xmr_pct.setText(mArrayList.get(9).CHANGE24HOUR + " (" + mArrayList.get(9).CHANGEPCT24HOUR + "%)");
//                        tv_xmr_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(10).CHANGEPCT24HOUR.contains("-")) {
//                        tv_eos_pct.setText(mArrayList.get(10).CHANGE24HOUR + " (" + mArrayList.get(10).CHANGEPCT24HOUR + "%)");
//                        tv_eos_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_eos_pct.setText(mArrayList.get(10).CHANGE24HOUR + " (" + mArrayList.get(10).CHANGEPCT24HOUR + "%)");
//                        tv_eos_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(11).CHANGEPCT24HOUR.contains("-")) {
//                        tv_btg_pct.setText(mArrayList.get(11).CHANGE24HOUR + " (" + mArrayList.get(11).CHANGEPCT24HOUR + "%)");
//                        tv_btg_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_btg_pct.setText(mArrayList.get(11).CHANGE24HOUR + " (" + mArrayList.get(11).CHANGEPCT24HOUR + "%)");
//                        tv_btg_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(12).CHANGEPCT24HOUR.contains("-")) {
//                        tv_qtum_pct.setText(mArrayList.get(12).CHANGE24HOUR + " (" + mArrayList.get(12).CHANGEPCT24HOUR + "%)");
//                        tv_qtum_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_qtum_pct.setText(mArrayList.get(12).CHANGE24HOUR + " (" + mArrayList.get(12).CHANGEPCT24HOUR + "%)");
//                        tv_qtum_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(13).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xlm_pct.setText(mArrayList.get(13).CHANGE24HOUR + " (" + mArrayList.get(13).CHANGEPCT24HOUR + "%)");
//                        tv_xlm_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xlm_pct.setText(mArrayList.get(13).CHANGE24HOUR + " (" + mArrayList.get(13).CHANGEPCT24HOUR + "%)");
//                        tv_xlm_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(14).CHANGEPCT24HOUR.contains("-")) {
//                        tv_neo_pct.setText(mArrayList.get(14).CHANGE24HOUR + " (" + mArrayList.get(14).CHANGEPCT24HOUR + "%)");
//                        tv_neo_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_neo_pct.setText(mArrayList.get(14).CHANGE24HOUR + " (" + mArrayList.get(14).CHANGEPCT24HOUR + "%)");
//                        tv_neo_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(15).CHANGEPCT24HOUR.contains("-")) {
//                        tv_etc_pct.setText(mArrayList.get(15).CHANGE24HOUR + " (" + mArrayList.get(15).CHANGEPCT24HOUR + "%)");
//                        tv_etc_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_etc_pct.setText(mArrayList.get(15).CHANGE24HOUR + " (" + mArrayList.get(15).CHANGEPCT24HOUR + "%)");
//                        tv_etc_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(16).CHANGEPCT24HOUR.contains("-")) {
//                        tv_zec_pct.setText(mArrayList.get(16).CHANGE24HOUR + " (" + mArrayList.get(16).CHANGEPCT24HOUR + "%)");
//                        tv_zec_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_zec_pct.setText(mArrayList.get(16).CHANGE24HOUR + " (" + mArrayList.get(16).CHANGEPCT24HOUR + "%)");
//                        tv_zec_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//
//                    if (mArrayList.get(17).CHANGEPCT24HOUR.contains("-")) {
//                        tv_steem_pct.setText(mArrayList.get(17).CHANGE24HOUR + " (" + mArrayList.get(17).CHANGEPCT24HOUR + "%)");
//                        tv_steem_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_steem_pct.setText(mArrayList.get(17).CHANGE24HOUR + " (" + mArrayList.get(17).CHANGEPCT24HOUR + "%)");
//                        tv_steem_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//
//                    if (mArrayList.get(18).CHANGEPCT24HOUR.contains("-")) {
//                        tv_xzc_pct.setText(mArrayList.get(18).CHANGE24HOUR + " (" + mArrayList.get(18).CHANGEPCT24HOUR + "%)");
//                        tv_xzc_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_xzc_pct.setText(mArrayList.get(18).CHANGE24HOUR + " (" + mArrayList.get(18).CHANGEPCT24HOUR + "%)");
//                        tv_xzc_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//
//                    if (mArrayList.get(19).CHANGEPCT24HOUR.contains("-")) {
//                        tv_storj_pct.setText(mArrayList.get(19).CHANGE24HOUR + " (" + mArrayList.get(19).CHANGEPCT24HOUR + "%)");
//                        tv_storj_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_storj_pct.setText(mArrayList.get(19).CHANGE24HOUR + " (" + mArrayList.get(19).CHANGEPCT24HOUR + "%)");
//                        tv_storj_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//
//                    if (mArrayList.get(20).CHANGEPCT24HOUR.contains("-")) {
//                        tv_aion_pct.setText(mArrayList.get(20).CHANGE24HOUR + " (" + mArrayList.get(20).CHANGEPCT24HOUR + "%)");
//                        tv_aion_pct.setTextColor(Color.parseColor("#FF0000"));
//                    } else {
//                        tv_aion_pct.setText(mArrayList.get(20).CHANGE24HOUR + " (" + mArrayList.get(20).CHANGEPCT24HOUR + "%)");
//                        tv_aion_pct.setTextColor(Color.parseColor("#008000"));
//                    }
//                }
//            } else {
//                utils.showAlertMessage(Home_Activity.this, msg);
//            }
//        }
//    }


    @Override
    public void onBackPressed() {

        if (utils.isNetworkAvailable(Home_Activity.this)) {
//            final Dialog dialog = new Dialog(this);
//            dialog.setContentView(R.layout.custom_dialog);
//
//// Custom Android Allert Dialog Title
//
//
//            TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
//            ImageView iv_advertise = dialog.findViewById(R.id.iv_advertise);
//            iv_advertise.setVisibility(View.VISIBLE);
//            Button dialogButtonCancel = (Button) dialog.findViewById(R.id.customDialogCancel);
//            Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);
//
//
//
//            tv_msg.setText(getString(R.string.error_quit));
//            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//
//            String image = "http://marumodasa.com/coinscap/API/ad-image/";
//
//            Glide.with(this)
//                    .load(image + mAdvertiseArrayList.get(0).ad_image)
//                    .into(iv_advertise);
//
//
//            iv_advertise.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                Intent intent = new Intent(Home_Activity.this, Advertise_Activity.class);
////                intent.putExtra("ad_url", mAdvertiseArrayList.get(0).ad_url);
////                startActivity(intent);
////                finish();
//
//                    String url = mAdvertiseArrayList.get(0).ad_url;
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(url));
//                    startActivity(i);
//                }
//            });
//
//// Action for custom dialog ok button click
//
//            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//            dialog.show();


            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);

// Custom Android Allert Dialog Title

            TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
            ImageView iv_advertise = dialog.findViewById(R.id.iv_advertise);
            iv_advertise.setVisibility(View.GONE);
            Button dialogButtonCancel = (Button) dialog.findViewById(R.id.customDialogCancel);
            Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);


            tv_msg.setText(getString(R.string.error_quit));
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

//            String image = "http://marumodasa.com/coinscap/API/ad-image/";
//
//            Glide.with(this)
//                    .load(image + mAdvertiseArrayList.get(0).ad_image)
//                    .into(iv_advertise);


//            iv_advertise.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                Intent intent = new Intent(Home_Activity.this, Advertise_Activity.class);
////                intent.putExtra("ad_url", mAdvertiseArrayList.get(0).ad_url);
////                startActivity(intent);
////                finish();
//
//                    String url = mAdvertiseArrayList.get(0).ad_url;
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(url));
//                    startActivity(i);
//                }
//            });

// Action for custom dialog ok button click

            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            dialog.show();

        } else {

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);

// Custom Android Allert Dialog Title

            TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
            ImageView iv_advertise = dialog.findViewById(R.id.iv_advertise);
            iv_advertise.setVisibility(View.GONE);
            Button dialogButtonCancel = (Button) dialog.findViewById(R.id.customDialogCancel);
            Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);


            tv_msg.setText(getString(R.string.error_quit));
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

//            String image = "http://marumodasa.com/coinscap/API/ad-image/";
//
//            Glide.with(this)
//                    .load(image + mAdvertiseArrayList.get(0).ad_image)
//                    .into(iv_advertise);


//            iv_advertise.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                Intent intent = new Intent(Home_Activity.this, Advertise_Activity.class);
////                intent.putExtra("ad_url", mAdvertiseArrayList.get(0).ad_url);
////                startActivity(intent);
////                finish();
//
//                    String url = mAdvertiseArrayList.get(0).ad_url;
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(url));
//                    startActivity(i);
//                }
//            });

// Action for custom dialog ok button click

            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            dialog.show();

        }
    }

}
