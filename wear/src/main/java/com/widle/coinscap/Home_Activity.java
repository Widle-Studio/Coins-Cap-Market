package com.widle.coinscap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.widle.coinscap.Model.coin;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Home_Activity extends WearableActivity implements View.OnClickListener, WearableNavigationDrawerView.OnItemSelectedListener{

    Gson gson = new Gson();

    private TextView tv_btc_name, tv_eth_name, tv_bch_name, tv_xrp_name, tv_ltc_name, tv_ada_name, tv_iot_name, tv_dash_name, tv_xem_name,
            tv_xmr_name, tv_eos_name, tv_btg_name, tv_qtum_name, tv_xlm_name, tv_neo_name, tv_etc_name, tv_zec_name, tv_steem_name,
            tv_xzc_name, tv_storj_name, tv_aion_name,tv_ht_name, tv_tronix_name, tv_nano_name, tv_omisego_name, tv_elastos_name, tv_binance_name, tv_vechain_name,
            tv_zclassic_name, tv_digix_name, tv_odyssey_name, tv_blockmason_name, tv_lunyr_name, tv_iostoken_name, tv_hshare_name, tv_iconproject_name, tv_lisk_name,
            tv_nebilo_name, tv_wavess_name, tv_bluzello_name, tv_inkk_name, tv_adex_name, tv_verge_name, tv_metal_name, tv_sirinlabs_name, tv_ox_name, tv_iex_name,
            tv_thetaa_name, tv_bitcoindimand_name, tv_openanx_name, tv_aelf_name, tv_insexosystem_name, tv_zilliqa_name, tv_aeternity_name, tv_propy_name, tv_dogecoin_name,
            tv_iotchain_name, tv_reddcoin_name, tv_swftcoin_name, tv_monaco_name, tv_aeron_name, tv_medicakchain_name, tv_breadtoken_name, tv_santiment_name, tv_nebulas_name,
            tv_genesis_name, tv_qunqun_name, tv_waltonchain_name, tv_factoids_name, tv_coinDash_name, tv_ripio_name;

    private TextView tv_btc_sf, tv_eth_sf, tv_bch_sf, tv_xrp_sf, tv_ltc_sf, tv_ada_sf, tv_iot_sf, tv_dash_sf, tv_xem_sf, tv_xmr_sf, tv_eos_sf,
            tv_btg_sf, tv_qtum_sf, tv_xlm_sf, tv_neo_sf, tv_etc_sf, tv_zec_sf, tv_steem_sf, tv_xzc_sf, tv_storj_sf, tv_aion_sf, tv_ht_sf, tv_tronix_sf,
            tv_nano_sf, tv_omisego_sf, tv_elastos_sf, tv_binance_sf, tv_vechain_sf, tv_zclassic_sf, tv_digix_sf, tv_odyssey_sf, tv_blockmason_sf, tv_lunyr_sf,
            tv_iostoken_sf, tv_hshare_sf, tv_iconproject_sf, tv_lisk_sf, tv_nebilo_sf, tv_wavess_sf, tv_bluzello_sf, tv_inkk_sf, tv_adex_sf, tv_verge_sf, tv_metal_sf,
            tv_sirinlabs_sf, tv_ox_sf, tv_iex_sf, tv_thetaa_sf, tv_bitcoindimand_sf, tv_openanx_sf, tv_aelf_sf, tv_insexosystem_sf, tv_zilliqa_sf, tv_aeternity_sf, tv_propy_sf,
            tv_dogecoin_sf, tv_iotchain_sf, tv_reddcoin_sf, tv_swftcoin_sf, tv_monaco_sf, tv_aeron_sf, tv_medicakchain_sf, tv_breadtoken_sf, tv_santiment_sf, tv_nebulas_sf, tv_genesis_sf,
            tv_qunqun_sf, tv_waltonchain_sf, tv_factoids_sf, tv_coinDash_sf, tv_ripio_sf;;

    private TextView tv_app_name, tv_btc_mc, tv_eth_mc, tv_bch_mc, tv_xrp_mc, tv_ltc_mc, tv_ada_mc, tv_iot_mc, tv_dash_mc,
            tv_xem_mc, tv_xmr_mc, tv_eos_mc, tv_btg_mc, tv_qtum_mc, tv_xlm_mc, tv_neo_mc, tv_etc_mc, tv_zec_mc, tv_steem_mc,
            tv_xzc_mc, tv_storj_mc, tv_aion_mc, tv_ht_mc, tv_tronix_mc, tv_nano_mc, tv_omisego_mc, tv_elastos_mc, tv_binance_mc, tv_vechain_mc, tv_zclassic_mc,
            tv_digix_mc, tv_odyssey_mc, tv_blockmason_mc, tv_lunyr_mc, tv_iostoken_mc, tv_hshare_mc, tv_iconproject_mc, tv_lisk_mc, tv_nebilo_mc, tv_wavess_mc, tv_bluzello_mc,
            tv_inkk_mc, tv_adex_mc, tv_verge_mc, tv_metal_mc, tv_sirinlabs_mc, tv_ox_mc, tv_iex_mc, tv_thetaa_mc, tv_bitcoindimand_mc, tv_openanx_mc, tv_aelf_mc, tv_insexosystem_mc,
            tv_zilliqa_mc, tv_aeternity_mc, tv_propy_mc, tv_dogecoin_mc, tv_iotchain_mc, tv_reddcoin_mc, tv_swftcoin_mc, tv_monaco_mc, tv_aeron_mc, tv_medicakchain_mc, tv_breadtoken_mc,
            tv_santiment_mc, tv_nebulas_mc, tv_genesis_mc, tv_qunqun_mc, tv_waltonchain_mc, tv_factoids_mc, tv_coinDash_mc, tv_ripio_mc;

    private TextView tv_btc_price, tv_eth_price, tv_bch_price, tv_xrp_price, tv_ltc_price, tv_ada_price, tv_iot_price, tv_dash_price,
            tv_xem_price, tv_xmr_price, tv_eos_price, tv_btg_price, tv_qtum_price, tv_xlm_price, tv_neo_price, tv_etc_price, tv_zec_price, tv_steem_price,
            tv_xzc_price, tv_storj_price, tv_aion_price, tv_ht_price, tv_tronix_price, tv_nano_price, tv_omisego_price, tv_elastos_price, tv_binance_price, tv_vechain_price,
            tv_zclassic_price, tv_digix_price, tv_odyssey_price, tv_blockmason_price, tv_lunyr_price, tv_iostoken_price, tv_hshare_price, tv_iconproject_price, tv_lisk_price,
            tv_nebilo_price, tv_wavess_price, tv_bluzello_price, tv_inkk_price, tv_adex_price, tv_verge_price, tv_metal_price, tv_sirinlabs_price, tv_ox_price, tv_iex_price,
            tv_thetaa_price, tv_bitcoindimand_price, tv_openanx_price, tv_aelf_price, tv_insexosystem_price, tv_zilliqa_price, tv_aeternity_price, tv_propy_price, tv_dogecoin_price,
            tv_iotchain_price, tv_reddcoin_price, tv_swftcoin_price, tv_monaco_price, tv_aeron_price, tv_medicakchain_price, tv_breadtoken_price, tv_santiment_price, tv_nebulas_price,
            tv_genesis_price, tv_qunqun_price, tv_waltonchain_price, tv_factoids_price, tv_coinDash_price, tv_ripio_price;

    private TextView tv_btc_pct, tv_eth_pct, tv_bch_pct, tv_xrp_pct, tv_ltc_pct, tv_ada_pct, tv_iot_pct, tv_dash_pct,
            tv_xem_pct, tv_xmr_pct, tv_eos_pct, tv_btg_pct, tv_qtum_pct, tv_xlm_pct, tv_neo_pct, tv_etc_pct, tv_zec_pct, tv_steem_pct,
            tv_xzc_pct, tv_storj_pct, tv_aion_pct, tv_ht_ptc, tv_tronix_ptc, tv_nano_ptc, tv_omisego_ptc, tv_elastos_ptc, tv_binance_ptc, tv_vechain_ptc,
            tv_zclassic_ptc, tv_digix_ptc, tv_odyssey_ptc, tv_blockmason_ptc, tv_lunyr_ptc, tv_iostoken_ptc, tv_hshare_ptc, tv_iconproject_ptc, tv_lisk_ptc, tv_nebilo_ptc,
            tv_wavess_ptc, tv_bluzello_ptc, tv_inkk_ptc, tv_adex_ptc, tv_verge_ptc, tv_metal_ptc, tv_sirinlabs_ptc, tv_ox_ptc, tv_iex_ptc, tv_thetaa_ptc, tv_bitcoindimand_ptc,
            tv_openanx_ptc, tv_aelf_ptc, tv_insexosystem_ptc, tv_zilliqa_ptc, tv_aeternity_ptc, tv_propy_ptc, tv_dogecoin_ptc, tv_iotchain_ptc,
            tv_reddcoin_ptc, tv_swftcoin_ptc, tv_monaco_ptc, tv_aeron_ptc, tv_medicakchain_ptc, tv_breadtoken_ptc, tv_santiment_ptc, tv_nebulas_ptc, tv_genesis_ptc, tv_qunqun_ptc,
            tv_waltonchain_ptc, tv_factoids_ptc, tv_coinDash_ptc, tv_ripio_ptc;

    private ImageView img_ht, img_trx, img_xrb, img_omg, img_ela, img_bnb, img_ven, img_zcl, img_dgd, img_ocn, img_bcpt, img_lun, img_iost,
            img_hsr, img_icx, img_lsk, img_nebl, img_waves, img_blz, img_ink, img_adx, img_xvg, img_mtl, img_srn, img_zrx, img_rlc, img_theta,
            img_bcd, img_oax, img_elf, img_ins, img_zil, img_ae, img_pro, img_doge, img_itc, img_rdd, img_swftc, img_mco, img_arn, img_mtn,
            img_brd, img_san, img_nas, img_gvt, img_qun, img_wtc, img_fct, img_cdt, img_rcn;

    private ImageView iv_un_fav, iv_un_fav1, iv_un_fav2, iv_un_fav3, iv_un_fav4, iv_un_fav5, iv_un_fav6, iv_un_fav7, iv_un_fav8, iv_un_fav9, iv_un_fav10,
            iv_un_fav11, iv_un_fav12, iv_un_fav13, iv_un_fav14, iv_un_fav15, iv_un_fav16, iv_un_fav17, iv_un_fav18, iv_un_fav19, iv_un_fav20,
            iv_un_fav21, iv_un_fav22, iv_un_fav23, iv_un_fav24, iv_un_fav25, iv_un_fav26, iv_un_fav27, iv_un_fav28, iv_un_fav29, iv_un_fav30,
            iv_un_fav31, iv_un_fav32, iv_un_fav33, iv_un_fav34, iv_un_fav35, iv_un_fav36, iv_un_fav37, iv_un_fav38, iv_un_fav39, iv_un_fav40,
            iv_un_fav41, iv_un_fav42, iv_un_fav43, iv_un_fav44, iv_un_fav45, iv_un_fav46, iv_un_fav47, iv_un_fav48, iv_un_fav49, iv_un_fav50,
            iv_un_fav51, iv_un_fav52, iv_un_fav53, iv_un_fav54, iv_un_fav55, iv_un_fav56, iv_un_fav57, iv_un_fav58, iv_un_fav59, iv_un_fav60,
            iv_un_fav61, iv_un_fav62, iv_un_fav63, iv_un_fav64, iv_un_fav65, iv_un_fav66, iv_un_fav67, iv_un_fav68, iv_un_fav69, iv_un_fav70;

    private ImageView iv_fav, iv_fav1, iv_fav2, iv_fav3, iv_fav4, iv_fav5, iv_fav6, iv_fav7, iv_fav8, iv_fav9, iv_fav10,
            iv_fav11, iv_fav12, iv_fav13, iv_fav14, iv_fav15, iv_fav16, iv_fav17, iv_fav18, iv_fav19, iv_fav20,
            iv_fav21, iv_fav22, iv_fav23, iv_fav24, iv_fav25, iv_fav26, iv_fav27, iv_fav28, iv_fav29, iv_fav30,
            iv_fav31, iv_fav32, iv_fav33, iv_fav34, iv_fav35, iv_fav36, iv_fav37, iv_fav38, iv_fav39, iv_fav40,
            iv_fav41, iv_fav42, iv_fav43, iv_fav44, iv_fav45, iv_fav46, iv_fav47, iv_fav48, iv_fav49, iv_fav50,
            iv_fav51, iv_fav52, iv_fav53, iv_fav54, iv_fav55, iv_fav56, iv_fav57, iv_fav58, iv_fav59, iv_fav60,
            iv_fav61, iv_fav62, iv_fav63, iv_fav64, iv_fav65, iv_fav66, iv_fav67, iv_fav68, iv_fav69, iv_fav70;

    private ProgressDialog mProgressDialog;

    ArrayList<coin> mArrayList = new ArrayList<>();

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();

    private  String symbol = "", skill = "", words = "";

    ArrayList<String> stringList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();

    SharedPreferences preferences;

    private LinearLayout ll_btc, ll_event;

    private String symbl = "";

    private WearableNavigationDrawerView mWearableNavigationDrawer;

    private WearableActionDrawerView mWearableActionDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferences = getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        init();

        if (utils.isNetworkAvailable(Home_Activity.this)) {
            new apiGet_Coins().execute();
        } else {
//            utils.showAlertMessage(Home_Activity.this, getString(R.string.err_no_internet));
        }
        // Enables Always-on
        setAmbientEnabled();
    }

    public void init() {


//        // Top Navigation Drawer
//        mWearableNavigationDrawer =
//                (WearableNavigationDrawerView) findViewById(R.id.top_navigation_drawer);
//        // Peeks navigation drawer on the top.
//        mWearableNavigationDrawer.getController().peekDrawer();
//        mWearableNavigationDrawer.addOnItemSelectedListener(this);
//
//        View view = LayoutInflater.from(Home_Activity.this).
//                inflate(R.layout.drawerview, null);
//        mWearableNavigationDrawer.setPeekContent(view);


        ll_btc = findViewById(R.id.ll_btc);
        ll_event = findViewById(R.id.ll_event);
        iv_un_fav = findViewById(R.id.iv_un_fav);
        iv_un_fav1 = findViewById(R.id.iv_un_fav1);
        iv_un_fav2 = findViewById(R.id.iv_un_fav2);
        iv_un_fav3 = findViewById(R.id.iv_un_fav3);
        iv_un_fav4 = findViewById(R.id.iv_un_fav4);
        iv_un_fav5 = findViewById(R.id.iv_un_fav5);
        iv_un_fav6 = findViewById(R.id.iv_un_fav6);
        iv_un_fav7 = findViewById(R.id.iv_un_fav7);
        iv_un_fav8 = findViewById(R.id.iv_un_fav8);
        iv_un_fav9 = findViewById(R.id.iv_un_fav9);
        iv_un_fav10 = findViewById(R.id.iv_un_fav10);
        iv_un_fav11 = findViewById(R.id.iv_un_fav11);
        iv_un_fav12 = findViewById(R.id.iv_un_fav12);
        iv_un_fav13 = findViewById(R.id.iv_un_fav13);
        iv_un_fav14 = findViewById(R.id.iv_un_fav14);
        iv_un_fav15 = findViewById(R.id.iv_un_fav15);
        iv_un_fav16 = findViewById(R.id.iv_un_fav16);
        iv_un_fav17 = findViewById(R.id.iv_un_fav17);
        iv_un_fav18 = findViewById(R.id.iv_un_fav18);
        iv_un_fav19 = findViewById(R.id.iv_un_fav19);
        iv_un_fav20 = findViewById(R.id.iv_un_fav20);
        iv_un_fav21 = findViewById(R.id.iv_un_fav21);
        iv_un_fav22 = findViewById(R.id.iv_un_fav22);
        iv_un_fav23 = findViewById(R.id.iv_un_fav23);
        iv_un_fav24 = findViewById(R.id.iv_un_fav24);
        iv_un_fav25 = findViewById(R.id.iv_un_fav25);
        iv_un_fav26 = findViewById(R.id.iv_un_fav26);
        iv_un_fav27 = findViewById(R.id.iv_un_fav27);
        iv_un_fav28 = findViewById(R.id.iv_un_fav28);
        iv_un_fav29 = findViewById(R.id.iv_un_fav29);
        iv_un_fav30 = findViewById(R.id.iv_un_fav30);
        iv_un_fav31 = findViewById(R.id.iv_un_fav31);
        iv_un_fav32 = findViewById(R.id.iv_un_fav32);
        iv_un_fav33 = findViewById(R.id.iv_un_fav33);
        iv_un_fav34 = findViewById(R.id.iv_un_fav34);
        iv_un_fav35 = findViewById(R.id.iv_un_fav35);
        iv_un_fav36 = findViewById(R.id.iv_un_fav36);
        iv_un_fav37 = findViewById(R.id.iv_un_fav37);
        iv_un_fav38 = findViewById(R.id.iv_un_fav38);
        iv_un_fav39 = findViewById(R.id.iv_un_fav39);
        iv_un_fav40 = findViewById(R.id.iv_un_fav40);
        iv_un_fav41 = findViewById(R.id.iv_un_fav41);
        iv_un_fav42 = findViewById(R.id.iv_un_fav42);
        iv_un_fav43 = findViewById(R.id.iv_un_fav43);
        iv_un_fav44 = findViewById(R.id.iv_un_fav44);
        iv_un_fav45 = findViewById(R.id.iv_un_fav45);
        iv_un_fav46 = findViewById(R.id.iv_un_fav46);
        iv_un_fav47 = findViewById(R.id.iv_un_fav47);
        iv_un_fav48 = findViewById(R.id.iv_un_fav48);
        iv_un_fav49 = findViewById(R.id.iv_un_fav49);
        iv_un_fav50 = findViewById(R.id.iv_un_fav50);
        iv_un_fav51 = findViewById(R.id.iv_un_fav51);
        iv_un_fav52 = findViewById(R.id.iv_un_fav52);
        iv_un_fav53 = findViewById(R.id.iv_un_fav53);
        iv_un_fav54 = findViewById(R.id.iv_un_fav54);
        iv_un_fav55 = findViewById(R.id.iv_un_fav55);
        iv_un_fav56 = findViewById(R.id.iv_un_fav56);
        iv_un_fav57 = findViewById(R.id.iv_un_fav57);
        iv_un_fav58 = findViewById(R.id.iv_un_fav58);
        iv_un_fav59 = findViewById(R.id.iv_un_fav59);
        iv_un_fav60 = findViewById(R.id.iv_un_fav60);
        iv_un_fav61 = findViewById(R.id.iv_un_fav61);
        iv_un_fav62 = findViewById(R.id.iv_un_fav62);
        iv_un_fav63 = findViewById(R.id.iv_un_fav63);
        iv_un_fav64 = findViewById(R.id.iv_un_fav64);
        iv_un_fav65 = findViewById(R.id.iv_un_fav65);
        iv_un_fav66 = findViewById(R.id.iv_un_fav66);
        iv_un_fav67 = findViewById(R.id.iv_un_fav67);
        iv_un_fav68 = findViewById(R.id.iv_un_fav68);
        iv_un_fav69 = findViewById(R.id.iv_un_fav69);
        iv_un_fav70 = findViewById(R.id.iv_un_fav70);

        iv_fav = findViewById(R.id.iv_fav);
        iv_fav1 = findViewById(R.id.iv_fav1);
        iv_fav2 = findViewById(R.id.iv_fav2);
        iv_fav3 = findViewById(R.id.iv_fav3);
        iv_fav4 = findViewById(R.id.iv_fav4);
        iv_fav5 = findViewById(R.id.iv_fav5);
        iv_fav6 = findViewById(R.id.iv_fav6);
        iv_fav7 = findViewById(R.id.iv_fav7);
        iv_fav8 = findViewById(R.id.iv_fav8);
        iv_fav9 = findViewById(R.id.iv_fav9);
        iv_fav10 = findViewById(R.id.iv_fav10);
        iv_fav11 = findViewById(R.id.iv_fav11);
        iv_fav12 = findViewById(R.id.iv_fav12);
        iv_fav13 = findViewById(R.id.iv_fav13);
        iv_fav14 = findViewById(R.id.iv_fav14);
        iv_fav15 = findViewById(R.id.iv_fav15);
        iv_fav16 = findViewById(R.id.iv_fav16);
        iv_fav17 = findViewById(R.id.iv_fav17);
        iv_fav18 = findViewById(R.id.iv_fav18);
        iv_fav19 = findViewById(R.id.iv_fav19);
        iv_fav20 = findViewById(R.id.iv_fav20);
        iv_fav21 = findViewById(R.id.iv_fav21);
        iv_fav22 = findViewById(R.id.iv_fav22);
        iv_fav23 = findViewById(R.id.iv_fav23);
        iv_fav24 = findViewById(R.id.iv_fav24);
        iv_fav25 = findViewById(R.id.iv_fav25);
        iv_fav26 = findViewById(R.id.iv_fav26);
        iv_fav27 = findViewById(R.id.iv_fav27);
        iv_fav28 = findViewById(R.id.iv_fav28);
        iv_fav29 = findViewById(R.id.iv_fav29);
        iv_fav30 = findViewById(R.id.iv_fav30);
        iv_fav31 = findViewById(R.id.iv_fav31);
        iv_fav32 = findViewById(R.id.iv_fav32);
        iv_fav33 = findViewById(R.id.iv_fav33);
        iv_fav34 = findViewById(R.id.iv_fav34);
        iv_fav35 = findViewById(R.id.iv_fav35);
        iv_fav36 = findViewById(R.id.iv_fav36);
        iv_fav37 = findViewById(R.id.iv_fav37);
        iv_fav38 = findViewById(R.id.iv_fav38);
        iv_fav39 = findViewById(R.id.iv_fav39);
        iv_fav40 = findViewById(R.id.iv_fav40);
        iv_fav41 = findViewById(R.id.iv_fav41);
        iv_fav42 = findViewById(R.id.iv_fav42);
        iv_fav43 = findViewById(R.id.iv_fav43);
        iv_fav44 = findViewById(R.id.iv_fav44);
        iv_fav45 = findViewById(R.id.iv_fav45);
        iv_fav46 = findViewById(R.id.iv_fav46);
        iv_fav47 = findViewById(R.id.iv_fav47);
        iv_fav48 = findViewById(R.id.iv_fav48);
        iv_fav49 = findViewById(R.id.iv_fav49);
        iv_fav50 = findViewById(R.id.iv_fav50);
        iv_fav51 = findViewById(R.id.iv_fav51);
        iv_fav52 = findViewById(R.id.iv_fav52);
        iv_fav53 = findViewById(R.id.iv_fav53);
        iv_fav54 = findViewById(R.id.iv_fav54);
        iv_fav55 = findViewById(R.id.iv_fav55);
        iv_fav56 = findViewById(R.id.iv_fav56);
        iv_fav57 = findViewById(R.id.iv_fav57);
        iv_fav58 = findViewById(R.id.iv_fav58);
        iv_fav59 = findViewById(R.id.iv_fav59);
        iv_fav60 = findViewById(R.id.iv_fav60);
        iv_fav61 = findViewById(R.id.iv_fav61);
        iv_fav62 = findViewById(R.id.iv_fav62);
        iv_fav63 = findViewById(R.id.iv_fav63);
        iv_fav64 = findViewById(R.id.iv_fav64);
        iv_fav65 = findViewById(R.id.iv_fav65);
        iv_fav66 = findViewById(R.id.iv_fav66);
        iv_fav67 = findViewById(R.id.iv_fav67);
        iv_fav68 = findViewById(R.id.iv_fav68);
        iv_fav69 = findViewById(R.id.iv_fav69);
        iv_fav70 = findViewById(R.id.iv_fav70);

        String jsonText = preferences.getString("key", null);
        String jsonName = preferences.getString("key_name", null);
        String[] strinList = gson.fromJson(jsonText, String[].class);
        String[] NameList = gson.fromJson(jsonName, String[].class);

        if(NameList!=null && NameList.length>0){
            for (int i = 0; i < NameList.length; i++) {
                nameList.add(NameList[i]);
            }
        }else
            System.out.println("Array is not initialized or empty");

        if(strinList!=null && strinList.length>0){
            for (int i = 0; i < strinList.length; i++) {
                    if ("BTC".equals(strinList[i])){
                        iv_un_fav.setVisibility(View.VISIBLE);
                        iv_fav.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    } else if ("ETH".equals(strinList[i])){
                        iv_un_fav1.setVisibility(View.VISIBLE);
                        iv_fav1.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("BCH".equals(strinList[i])){
                        iv_un_fav2.setVisibility(View.VISIBLE);
                        iv_fav2.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("XRP".equals(strinList[i])){
                        iv_un_fav3.setVisibility(View.VISIBLE);
                        iv_fav3.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("LTC".equals(strinList[i])){
                        iv_un_fav4.setVisibility(View.VISIBLE);
                        iv_fav4.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ADA".equals(strinList[i])){
                        iv_un_fav5.setVisibility(View.VISIBLE);
                        iv_fav5.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("IOT".equals(strinList[i])){
                        iv_un_fav6.setVisibility(View.VISIBLE);
                        iv_fav6.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("DASH".equals(strinList[i])){
                        iv_un_fav7.setVisibility(View.VISIBLE);
                        iv_fav7.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("XEM".equals(strinList[i])){
                        iv_un_fav8.setVisibility(View.VISIBLE);
                        iv_fav8.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("XMR".equals(strinList[i])){
                        iv_un_fav9.setVisibility(View.VISIBLE);
                        iv_fav9.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("EOS".equals(strinList[i])){
                        iv_un_fav10.setVisibility(View.VISIBLE);
                        iv_fav10.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("BTG".equals(strinList[i])){
                        iv_un_fav11.setVisibility(View.VISIBLE);
                        iv_fav11.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("QTUM".equals(strinList[i])){
                        iv_un_fav12.setVisibility(View.VISIBLE);
                        iv_fav12.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("XLM".equals(strinList[i])){
                        iv_un_fav13.setVisibility(View.VISIBLE);
                        iv_fav13.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("NEO".equals(strinList[i])){
                        iv_un_fav14.setVisibility(View.VISIBLE);
                        iv_fav14.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ETC".equals(strinList[i])){
                        iv_un_fav15.setVisibility(View.VISIBLE);
                        iv_fav15.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ZEC".equals(strinList[i])){
                        iv_un_fav16.setVisibility(View.VISIBLE);
                        iv_fav16.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("STEEM".equals(strinList[i])){
                        iv_un_fav17.setVisibility(View.VISIBLE);
                        iv_fav17.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("XZC".equals(strinList[i])){
                        iv_un_fav18.setVisibility(View.VISIBLE);
                        iv_fav18.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("STORJ".equals(strinList[i])){
                        iv_un_fav19.setVisibility(View.VISIBLE);
                        iv_fav19.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("AION".equals(strinList[i])){
                        iv_un_fav20.setVisibility(View.VISIBLE);
                        iv_fav20.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("HT".equals(strinList[i])){
                        iv_un_fav21.setVisibility(View.VISIBLE);
                        iv_fav21.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("TRX".equals(strinList[i])){
                        iv_un_fav22.setVisibility(View.VISIBLE);
                        iv_fav22.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("XRB".equals(strinList[i])){
                        iv_un_fav23.setVisibility(View.VISIBLE);
                        iv_fav23.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("OMG".equals(strinList[i])){
                        iv_un_fav24.setVisibility(View.VISIBLE);
                        iv_fav24.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ELA".equals(strinList[i])){
                        iv_un_fav25.setVisibility(View.VISIBLE);
                        iv_fav25.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("BNB".equals(strinList[i])){
                        iv_un_fav26.setVisibility(View.VISIBLE);
                        iv_fav26.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("VEN".equals(strinList[i])){
                        iv_un_fav27.setVisibility(View.VISIBLE);
                        iv_fav27.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ZCL".equals(strinList[i])){
                        iv_un_fav28.setVisibility(View.VISIBLE);
                        iv_fav28.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("DGD".equals(strinList[i])){
                        iv_un_fav29.setVisibility(View.VISIBLE);
                        iv_fav29.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("OCN".equals(strinList[i])){
                        iv_un_fav30.setVisibility(View.VISIBLE);
                        iv_fav30.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("BCPT".equals(strinList[i])){
                        iv_un_fav31.setVisibility(View.VISIBLE);
                        iv_fav31.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("LUN".equals(strinList[i])){
                        iv_un_fav32.setVisibility(View.VISIBLE);
                        iv_fav32.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("IOST".equals(strinList[i])){
                        iv_un_fav33.setVisibility(View.VISIBLE);
                        iv_fav33.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("HSR".equals(strinList[i])){
                        iv_un_fav34.setVisibility(View.VISIBLE);
                        iv_fav34.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ICX".equals(strinList[i])){
                        iv_un_fav35.setVisibility(View.VISIBLE);
                        iv_fav35.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("LSK".equals(strinList[i])){
                        iv_un_fav36.setVisibility(View.VISIBLE);
                        iv_fav36.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("NEBL".equals(strinList[i])){
                        iv_un_fav37.setVisibility(View.VISIBLE);
                        iv_fav37.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("WAVES".equals(strinList[i])){
                        iv_un_fav38.setVisibility(View.VISIBLE);
                        iv_fav38.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("BLZ".equals(strinList[i])){
                        iv_un_fav39.setVisibility(View.VISIBLE);
                        iv_fav39.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("INK".equals(strinList[i])){
                        iv_un_fav40.setVisibility(View.VISIBLE);
                        iv_fav40.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ADX".equals(strinList[i])){
                        iv_un_fav41.setVisibility(View.VISIBLE);
                        iv_fav41.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("XVG".equals(strinList[i])){
                        iv_un_fav42.setVisibility(View.VISIBLE);
                        iv_fav42.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("MTL".equals(strinList[i])){
                        iv_un_fav43.setVisibility(View.VISIBLE);
                        iv_fav43.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("SRN".equals(strinList[i])){
                        iv_un_fav44.setVisibility(View.VISIBLE);
                        iv_fav44.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ZRX".equals(strinList[i])){
                        iv_un_fav45.setVisibility(View.VISIBLE);
                        iv_fav45.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("RLC".equals(strinList[i])){
                        iv_un_fav46.setVisibility(View.VISIBLE);
                        iv_fav46.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("THETA".equals(strinList[i])){
                        iv_un_fav47.setVisibility(View.VISIBLE);
                        iv_fav47.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("BCD".equals(strinList[i])){
                        iv_un_fav48.setVisibility(View.VISIBLE);
                        iv_fav48.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("OAX".equals(strinList[i])){
                        iv_un_fav49.setVisibility(View.VISIBLE);
                        iv_fav49.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ELF".equals(strinList[i])){
                        iv_un_fav50.setVisibility(View.VISIBLE);
                        iv_fav50.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("INS".equals(strinList[i])){
                        iv_un_fav51.setVisibility(View.VISIBLE);
                        iv_fav51.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ZIL".equals(strinList[i])){
                        iv_un_fav52.setVisibility(View.VISIBLE);
                        iv_fav52.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("AE".equals(strinList[i])){
                        iv_un_fav53.setVisibility(View.VISIBLE);
                        iv_fav53.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("PRO".equals(strinList[i])){
                        iv_un_fav54.setVisibility(View.VISIBLE);
                        iv_fav54.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("DOGE".equals(strinList[i])){
                        iv_un_fav55.setVisibility(View.VISIBLE);
                        iv_fav55.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ITC".equals(strinList[i])){
                        iv_un_fav56.setVisibility(View.VISIBLE);
                        iv_fav56.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ROD".equals(strinList[i])){
                        iv_un_fav57.setVisibility(View.VISIBLE);
                        iv_fav57.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("SWFTC".equals(strinList[i])){
                        iv_un_fav58.setVisibility(View.VISIBLE);
                        iv_fav58.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("MCO".equals(strinList[i])){
                        iv_un_fav59.setVisibility(View.VISIBLE);
                        iv_fav59.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("ARN".equals(strinList[i])){
                        iv_un_fav60.setVisibility(View.VISIBLE);
                        iv_fav60.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("MTN*".equals(strinList[i])){
                        iv_un_fav61.setVisibility(View.VISIBLE);
                        iv_fav61.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("BRD".equals(strinList[i])){
                        iv_un_fav62.setVisibility(View.VISIBLE);
                        iv_fav62.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("SAN".equals(strinList[i])){
                        iv_un_fav63.setVisibility(View.VISIBLE);
                        iv_fav63.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("NAS".equals(strinList[i])){
                        iv_un_fav64.setVisibility(View.VISIBLE);
                        iv_fav64.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("GVT".equals(strinList[i])){
                        iv_un_fav65.setVisibility(View.VISIBLE);
                        iv_fav65.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("QUN".equals(strinList[i])){
                        iv_un_fav66.setVisibility(View.VISIBLE);
                        iv_fav66.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("WTC".equals(strinList[i])){
                        iv_un_fav67.setVisibility(View.VISIBLE);
                        iv_fav67.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("FCT".equals(strinList[i])){
                        iv_un_fav68.setVisibility(View.VISIBLE);
                        iv_fav68.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("CDT".equals(strinList[i])){
                        iv_un_fav69.setVisibility(View.VISIBLE);
                        iv_fav69.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }else if ("RCN".equals(strinList[i])){
                        iv_un_fav70.setVisibility(View.VISIBLE);
                        iv_fav70.setVisibility(View.GONE);
                        stringList.add(strinList[i]);
                    }
            }
        }else
            System.out.println("Array is not initialized or empty");


        tv_btc_name = (TextView) findViewById(R.id.tv_btc_name);
        tv_eth_name = (TextView) findViewById(R.id.tv_eth_name);
        tv_bch_name = (TextView) findViewById(R.id.tv_bch_name);
        tv_xrp_name = (TextView) findViewById(R.id.tv_xrp_name);
        tv_ltc_name = (TextView) findViewById(R.id.tv_ltc_name);
        tv_ada_name = (TextView) findViewById(R.id.tv_ada_name);
        tv_iot_name = (TextView) findViewById(R.id.tv_iot_name);
        tv_dash_name = (TextView) findViewById(R.id.tv_dash_name);
        tv_xem_name = (TextView) findViewById(R.id.tv_xem_name);
        tv_xmr_name = (TextView) findViewById(R.id.tv_xmr_name);
        tv_eos_name = (TextView) findViewById(R.id.tv_eos_name);
        tv_btg_name = (TextView) findViewById(R.id.tv_btg_name);
        tv_qtum_name = (TextView) findViewById(R.id.tv_qtum_name);
        tv_xlm_name = (TextView) findViewById(R.id.tv_xlm_name);
        tv_neo_name = (TextView) findViewById(R.id.tv_neo_name);
        tv_etc_name = (TextView) findViewById(R.id.tv_etc_name);
        tv_zec_name = (TextView) findViewById(R.id.tv_zec_name);
        tv_steem_name = (TextView) findViewById(R.id.tv_steem_name);
        tv_xzc_name = (TextView) findViewById(R.id.tv_xzc_name);
        tv_storj_name = (TextView) findViewById(R.id.tv_storj_name);
        tv_aion_name = (TextView) findViewById(R.id.tv_aion_name);
        tv_ht_name = (TextView) findViewById(R.id.tv_ht_name);
        tv_tronix_name = (TextView) findViewById(R.id.tv_tronix_name);
        tv_nano_name = (TextView) findViewById(R.id.tv_nano_name);
        tv_omisego_name = (TextView) findViewById(R.id.tv_omisego_name);
        tv_elastos_name = (TextView) findViewById(R.id.tv_elastos_name);
        tv_binance_name = (TextView) findViewById(R.id.tv_binance_name);
        tv_vechain_name = (TextView) findViewById(R.id.tv_vechain_name);
        tv_zclassic_name = (TextView) findViewById(R.id.tv_zclassic_name);
        tv_digix_name = (TextView) findViewById(R.id.tv_digix_name);
        tv_odyssey_name = (TextView) findViewById(R.id.tv_odyssey_name);
        tv_blockmason_name = (TextView) findViewById(R.id.tv_blockmason_name);
        tv_lunyr_name = (TextView) findViewById(R.id.tv_lunyr_name);
        tv_iostoken_name = (TextView) findViewById(R.id.tv_iostoken_name);
        tv_hshare_name = (TextView) findViewById(R.id.tv_hshare_name);
        tv_iconproject_name = (TextView) findViewById(R.id.tv_iconproject_name);
        tv_lisk_name = (TextView) findViewById(R.id.tv_lisk_name);
        tv_nebilo_name = (TextView) findViewById(R.id.tv_nebilo_name);
        tv_wavess_name = (TextView) findViewById(R.id.tv_wavess_name);
        tv_bluzello_name = (TextView) findViewById(R.id.tv_bluzello_name);
        tv_inkk_name = (TextView) findViewById(R.id.tv_inkk_name);
        tv_adex_name = (TextView) findViewById(R.id.tv_adex_name);
        tv_verge_name = (TextView) findViewById(R.id.tv_verge_name);
        tv_metal_name = (TextView) findViewById(R.id.tv_metal_name);
        tv_sirinlabs_name = (TextView) findViewById(R.id.tv_sirinlabs_name);
        tv_ox_name = (TextView) findViewById(R.id.tv_ox_name);
        tv_iex_name = (TextView) findViewById(R.id.tv_iex_name);
        tv_thetaa_name = (TextView) findViewById(R.id.tv_thetaa_name);
        tv_bitcoindimand_name = (TextView) findViewById(R.id.tv_bitcoindimand_name);
        tv_openanx_name = (TextView) findViewById(R.id.tv_openanx_name);
        tv_aelf_name = (TextView) findViewById(R.id.tv_aelf_name);
        tv_insexosystem_name = (TextView) findViewById(R.id.tv_insexosystem_name);
        tv_zilliqa_name = (TextView) findViewById(R.id.tv_zilliqa_name);
        tv_aeternity_name = (TextView) findViewById(R.id.tv_aeternity_name);
        tv_propy_name = (TextView) findViewById(R.id.tv_propy_name);
        tv_dogecoin_name = (TextView) findViewById(R.id.tv_dogecoin_name);
        tv_iotchain_name = (TextView) findViewById(R.id.tv_iotchain_name);
        tv_reddcoin_name = (TextView) findViewById(R.id.tv_reddcoin_name);
        tv_swftcoin_name = (TextView) findViewById(R.id.tv_swftcoin_name);
        tv_monaco_name = (TextView) findViewById(R.id.tv_monaco_name);
        tv_aeron_name = (TextView) findViewById(R.id.tv_aeron_name);
        tv_medicakchain_name = (TextView) findViewById(R.id.tv_medicakchain_name);
        tv_breadtoken_name = (TextView) findViewById(R.id.tv_breadtoken_name);
        tv_santiment_name = (TextView) findViewById(R.id.tv_santiment_name);
        tv_nebulas_name = (TextView) findViewById(R.id.tv_nebulas_name);
        tv_genesis_name = (TextView) findViewById(R.id.tv_genesis_name);
        tv_qunqun_name = (TextView) findViewById(R.id.tv_qunqun_name);
        tv_waltonchain_name = (TextView) findViewById(R.id.tv_waltonchain_name);
        tv_factoids_name = (TextView) findViewById(R.id.tv_factoids_name);
        tv_coinDash_name = (TextView) findViewById(R.id.tv_coinDash_name);
        tv_ripio_name = (TextView) findViewById(R.id.tv_ripio_name);

        tv_btc_sf = (TextView) findViewById(R.id.tv_btc_sf);
        tv_eth_sf = (TextView) findViewById(R.id.tv_eth_sf);
        tv_bch_sf = (TextView) findViewById(R.id.tv_bch_sf);
        tv_xrp_sf = (TextView) findViewById(R.id.tv_xrp_sf);
        tv_ltc_sf = (TextView) findViewById(R.id.tv_ltc_sf);
        tv_ada_sf = (TextView) findViewById(R.id.tv_ada_sf);
        tv_iot_sf = (TextView) findViewById(R.id.tv_iot_sf);
        tv_dash_sf = (TextView) findViewById(R.id.tv_dash_sf);
        tv_xem_sf = (TextView) findViewById(R.id.tv_xem_sf);
        tv_xmr_sf = (TextView) findViewById(R.id.tv_xmr_sf);
        tv_eos_sf = (TextView) findViewById(R.id.tv_eos_sf);
        tv_btg_sf = (TextView) findViewById(R.id.tv_btg_sf);
        tv_qtum_sf = (TextView) findViewById(R.id.tv_qtum_sf);
        tv_xlm_sf = (TextView) findViewById(R.id.tv_xlm_sf);
        tv_neo_sf = (TextView) findViewById(R.id.tv_neo_sf);
        tv_etc_sf = (TextView) findViewById(R.id.tv_etc_sf);
        tv_zec_sf = (TextView) findViewById(R.id.tv_zec_sf);
        tv_steem_sf = (TextView) findViewById(R.id.tv_steem_sf);
        tv_xzc_sf = (TextView) findViewById(R.id.tv_xzc_sf);
        tv_storj_sf = (TextView) findViewById(R.id.tv_storj_sf);
        tv_aion_sf = (TextView) findViewById(R.id.tv_aion_sf);
        tv_ht_sf = (TextView) findViewById(R.id.tv_ht_sf);
        tv_tronix_sf = (TextView) findViewById(R.id.tv_tronix_sf);
        tv_nano_sf = (TextView) findViewById(R.id.tv_nano_sf);
        tv_omisego_sf = (TextView) findViewById(R.id.tv_omisego_sf);
        tv_elastos_sf = (TextView) findViewById(R.id.tv_elastos_sf);
        tv_binance_sf = (TextView) findViewById(R.id.tv_binance_sf);
        tv_vechain_sf = (TextView) findViewById(R.id.tv_vechain_sf);
        tv_zclassic_sf = (TextView) findViewById(R.id.tv_zclassic_sf);
        tv_digix_sf = (TextView) findViewById(R.id.tv_digix_sf);
        tv_odyssey_sf = (TextView) findViewById(R.id.tv_odyssey_sf);
        tv_blockmason_sf = (TextView) findViewById(R.id.tv_blockmason_sf);
        tv_lunyr_sf = (TextView) findViewById(R.id.tv_lunyr_sf);
        tv_iostoken_sf = (TextView) findViewById(R.id.tv_iostoken_sf);
        tv_hshare_sf = (TextView) findViewById(R.id.tv_hshare_sf);
        tv_iconproject_sf = (TextView) findViewById(R.id.tv_iconproject_sf);
        tv_lisk_sf = (TextView) findViewById(R.id.tv_lisk_sf);
        tv_nebilo_sf = (TextView) findViewById(R.id.tv_nebilo_sf);
        tv_wavess_sf = (TextView) findViewById(R.id.tv_wavess_sf);
        tv_bluzello_sf = (TextView) findViewById(R.id.tv_bluzello_sf);
        tv_inkk_sf = (TextView) findViewById(R.id.tv_inkk_sf);
        tv_adex_sf = (TextView) findViewById(R.id.tv_adex_sf);
        tv_verge_sf = (TextView) findViewById(R.id.tv_verge_sf);
        tv_metal_sf = (TextView) findViewById(R.id.tv_metal_sf);
        tv_sirinlabs_sf = (TextView) findViewById(R.id.tv_sirinlabs_sf);
        tv_ox_sf = (TextView) findViewById(R.id.tv_ox_sf);
        tv_iex_sf = (TextView) findViewById(R.id.tv_iex_sf);
        tv_thetaa_sf = (TextView) findViewById(R.id.tv_thetaa_sf);
        tv_bitcoindimand_sf = (TextView) findViewById(R.id.tv_bitcoindimand_sf);
        tv_openanx_sf = (TextView) findViewById(R.id.tv_openanx_sf);
        tv_aelf_sf = (TextView) findViewById(R.id.tv_aelf_sf);
        tv_insexosystem_sf = (TextView) findViewById(R.id.tv_insexosystem_sf);
        tv_zilliqa_sf = (TextView) findViewById(R.id.tv_zilliqa_sf);
        tv_aeternity_sf = (TextView) findViewById(R.id.tv_aeternity_sf);
        tv_propy_sf = (TextView) findViewById(R.id.tv_propy_sf);
        tv_dogecoin_sf = (TextView) findViewById(R.id.tv_dogecoin_sf);
        tv_iotchain_sf = (TextView) findViewById(R.id.tv_iotchain_sf);
        tv_reddcoin_sf = (TextView) findViewById(R.id.tv_reddcoin_sf);
        tv_swftcoin_sf = (TextView) findViewById(R.id.tv_swftcoin_sf);
        tv_monaco_sf = (TextView) findViewById(R.id.tv_monaco_sf);
        tv_aeron_sf = (TextView) findViewById(R.id.tv_aeron_sf);
        tv_medicakchain_sf = (TextView) findViewById(R.id.tv_medicakchain_sf);
        tv_breadtoken_sf = (TextView) findViewById(R.id.tv_breadtoken_sf);
        tv_santiment_sf = (TextView) findViewById(R.id.tv_santiment_sf);
        tv_nebulas_sf = (TextView) findViewById(R.id.tv_nebulas_sf);
        tv_genesis_sf = (TextView) findViewById(R.id.tv_genesis_sf);
        tv_qunqun_sf = (TextView) findViewById(R.id.tv_qunqun_sf);
        tv_waltonchain_sf = (TextView) findViewById(R.id.tv_waltonchain_sf);
        tv_factoids_sf = (TextView) findViewById(R.id.tv_factoids_sf);
        tv_coinDash_sf = (TextView) findViewById(R.id.tv_coinDash_sf);
        tv_ripio_sf = (TextView) findViewById(R.id.tv_ripio_sf);

        tv_btc_mc = (TextView) findViewById(R.id.tv_btc_mc);
        tv_eth_mc = (TextView) findViewById(R.id.tv_eth_mc);
        tv_bch_mc = (TextView) findViewById(R.id.tv_bch_mc);
        tv_xrp_mc = (TextView) findViewById(R.id.tv_xrp_mc);
        tv_ltc_mc = (TextView) findViewById(R.id.tv_ltc_mc);
        tv_ada_mc = (TextView) findViewById(R.id.tv_ada_mc);
        tv_iot_mc = (TextView) findViewById(R.id.tv_iot_mc);
        tv_dash_mc = (TextView) findViewById(R.id.tv_dash_mc);
        tv_xem_mc = (TextView) findViewById(R.id.tv_xem_mc);
        tv_xmr_mc = (TextView) findViewById(R.id.tv_xmr_mc);
        tv_eos_mc = (TextView) findViewById(R.id.tv_eos_mc);
        tv_btg_mc = (TextView) findViewById(R.id.tv_btg_mc);
        tv_qtum_mc = (TextView) findViewById(R.id.tv_qtum_mc);
        tv_xlm_mc = (TextView) findViewById(R.id.tv_xlm_mc);
        tv_neo_mc = (TextView) findViewById(R.id.tv_neo_mc);
        tv_etc_mc = (TextView) findViewById(R.id.tv_etc_mc);
        tv_zec_mc = (TextView) findViewById(R.id.tv_zec_mc);
        tv_steem_mc = (TextView) findViewById(R.id.tv_steem_mc);
        tv_xzc_mc = (TextView) findViewById(R.id.tv_xzc_mc);
        tv_storj_mc = (TextView) findViewById(R.id.tv_storj_mc);
        tv_aion_mc = (TextView) findViewById(R.id.tv_aion_mc);
        tv_ht_mc = (TextView) findViewById(R.id.tv_ht_mc);
        tv_tronix_mc = (TextView) findViewById(R.id.tv_tronix_mc);
        tv_nano_mc = (TextView) findViewById(R.id.tv_nano_mc);
        tv_omisego_mc = (TextView) findViewById(R.id.tv_omisego_mc);
        tv_elastos_mc = (TextView) findViewById(R.id.tv_elastos_mc);
        tv_binance_mc = (TextView) findViewById(R.id.tv_binance_mc);
        tv_vechain_mc = (TextView) findViewById(R.id.tv_vechain_mc);
        tv_zclassic_mc = (TextView) findViewById(R.id.tv_zclassic_mc);
        tv_digix_mc = (TextView) findViewById(R.id.tv_digix_mc);
        tv_odyssey_mc = (TextView) findViewById(R.id.tv_odyssey_mc);
        tv_blockmason_mc = (TextView) findViewById(R.id.tv_blockmason_mc);
        tv_lunyr_mc = (TextView) findViewById(R.id.tv_lunyr_mc);
        tv_qtum_mc = (TextView) findViewById(R.id.tv_qtum_mc);
        tv_iostoken_mc = (TextView) findViewById(R.id.tv_iostoken_mc);
        tv_hshare_mc = (TextView) findViewById(R.id.tv_hshare_mc);
        tv_iconproject_mc = (TextView) findViewById(R.id.tv_iconproject_mc);
        tv_lisk_mc = (TextView) findViewById(R.id.tv_lisk_mc);
        tv_nebilo_mc = (TextView) findViewById(R.id.tv_nebilo_mc);
        tv_wavess_mc = (TextView) findViewById(R.id.tv_wavess_mc);
        tv_bluzello_mc = (TextView) findViewById(R.id.tv_bluzello_mc);
        tv_inkk_mc = (TextView) findViewById(R.id.tv_inkk_mc);
        tv_adex_mc = (TextView) findViewById(R.id.tv_adex_mc);
        tv_verge_mc = (TextView) findViewById(R.id.tv_verge_mc);
        tv_metal_mc = (TextView) findViewById(R.id.tv_metal_mc);
        tv_sirinlabs_mc = (TextView) findViewById(R.id.tv_sirinlabs_mc);
        tv_ox_mc = (TextView) findViewById(R.id.tv_ox_mc);
        tv_iex_mc = (TextView) findViewById(R.id.tv_iex_mc);
        tv_thetaa_mc = (TextView) findViewById(R.id.tv_thetaa_mc);
        tv_bitcoindimand_mc = (TextView) findViewById(R.id.tv_bitcoindimand_mc);
        tv_openanx_mc = (TextView) findViewById(R.id.tv_openanx_mc);
        tv_aelf_mc = (TextView) findViewById(R.id.tv_aelf_mc);
        tv_insexosystem_mc = (TextView) findViewById(R.id.tv_insexosystem_mc);
        tv_zilliqa_mc = (TextView) findViewById(R.id.tv_zilliqa_mc);
        tv_aeternity_mc = (TextView) findViewById(R.id.tv_aeternity_mc);
        tv_propy_mc = (TextView) findViewById(R.id.tv_propy_mc);
        tv_dogecoin_mc = (TextView) findViewById(R.id.tv_dogecoin_mc);
        tv_iotchain_mc = (TextView) findViewById(R.id.tv_iotchain_mc);
        tv_reddcoin_mc = (TextView) findViewById(R.id.tv_reddcoin_mc);
        tv_swftcoin_mc = (TextView) findViewById(R.id.tv_swftcoin_mc);
        tv_monaco_mc = (TextView) findViewById(R.id.tv_monaco_mc);
        tv_aeron_mc = (TextView) findViewById(R.id.tv_aeron_mc);
        tv_medicakchain_mc = (TextView) findViewById(R.id.tv_medicakchain_mc);
        tv_breadtoken_mc = (TextView) findViewById(R.id.tv_breadtoken_mc);
        tv_santiment_mc = (TextView) findViewById(R.id.tv_santiment_mc);
        tv_nebulas_mc = (TextView) findViewById(R.id.tv_nebulas_mc);
        tv_genesis_mc = (TextView) findViewById(R.id.tv_genesis_mc);
        tv_qunqun_mc = (TextView) findViewById(R.id.tv_qunqun_mc);
        tv_waltonchain_mc = (TextView) findViewById(R.id.tv_waltonchain_mc);
        tv_factoids_mc = (TextView) findViewById(R.id.tv_factoids_mc);
        tv_coinDash_mc = (TextView) findViewById(R.id.tv_coinDash_mc);
        tv_ripio_mc = (TextView) findViewById(R.id.tv_ripio_mc);

        tv_btc_price = (TextView) findViewById(R.id.tv_btc_price);
        tv_eth_price = (TextView) findViewById(R.id.tv_eth_price);
        tv_bch_price = (TextView) findViewById(R.id.tv_bch_price);
        tv_xrp_price = (TextView) findViewById(R.id.tv_xrp_price);
        tv_ltc_price = (TextView) findViewById(R.id.tv_ltc_price);
        tv_ada_price = (TextView) findViewById(R.id.tv_ada_price);
        tv_iot_price = (TextView) findViewById(R.id.tv_iot_price);
        tv_dash_price = (TextView) findViewById(R.id.tv_dash_price);
        tv_xem_price = (TextView) findViewById(R.id.tv_xem_price);
        tv_xmr_price = (TextView) findViewById(R.id.tv_xmr_price);
        tv_eos_price = (TextView) findViewById(R.id.tv_eos_price);
        tv_btg_price = (TextView) findViewById(R.id.tv_btg_price);
        tv_qtum_price = (TextView) findViewById(R.id.tv_qtum_price);
        tv_xlm_price = (TextView) findViewById(R.id.tv_xlm_price);
        tv_neo_price = (TextView) findViewById(R.id.tv_neo_price);
        tv_etc_price = (TextView) findViewById(R.id.tv_etc_price);
        tv_zec_price = (TextView) findViewById(R.id.tv_zec_price);
        tv_steem_price = (TextView) findViewById(R.id.tv_steem_price);
        tv_xzc_price = (TextView) findViewById(R.id.tv_xzc_price);
        tv_storj_price = (TextView) findViewById(R.id.tv_storj_price);
        tv_aion_price = (TextView) findViewById(R.id.tv_aion_price);
        tv_ht_price = (TextView) findViewById(R.id.tv_ht_price);
        tv_tronix_price = (TextView) findViewById(R.id.tv_tronix_price);
        tv_nano_price = (TextView) findViewById(R.id.tv_nano_price);
        tv_omisego_price = (TextView) findViewById(R.id.tv_omisego_price);
        tv_elastos_price = (TextView) findViewById(R.id.tv_elastos_price);
        tv_binance_price = (TextView) findViewById(R.id.tv_binance_price);
        tv_vechain_price = (TextView) findViewById(R.id.tv_vechain_price);
        tv_zclassic_price = (TextView) findViewById(R.id.tv_zclassic_price);
        tv_digix_price = (TextView) findViewById(R.id.tv_digix_price);
        tv_odyssey_price = (TextView) findViewById(R.id.tv_odyssey_price);
        tv_blockmason_price = (TextView) findViewById(R.id.tv_blockmason_price);
        tv_lunyr_price = (TextView) findViewById(R.id.tv_lunyr_price);
        tv_iostoken_price = (TextView) findViewById(R.id.tv_iostoken_price);
        tv_hshare_price = (TextView) findViewById(R.id.tv_hshare_price);
        tv_iconproject_price = (TextView) findViewById(R.id.tv_iconproject_price);
        tv_lisk_price = (TextView) findViewById(R.id.tv_lisk_price);
        tv_nebilo_price = (TextView) findViewById(R.id.tv_nebilo_price);
        tv_wavess_price = (TextView) findViewById(R.id.tv_wavess_price);
        tv_bluzello_price = (TextView) findViewById(R.id.tv_bluzello_price);
        tv_inkk_price = (TextView) findViewById(R.id.tv_inkk_price);
        tv_adex_price = (TextView) findViewById(R.id.tv_adex_price);
        tv_verge_price = (TextView) findViewById(R.id.tv_verge_price);
        tv_metal_price = (TextView) findViewById(R.id.tv_metal_price);
        tv_sirinlabs_price = (TextView) findViewById(R.id.tv_sirinlabs_price);
        tv_ox_price = (TextView) findViewById(R.id.tv_ox_price);
        tv_iex_price = (TextView) findViewById(R.id.tv_iex_price);
        tv_thetaa_price = (TextView) findViewById(R.id.tv_thetaa_price);
        tv_bitcoindimand_price = (TextView) findViewById(R.id.tv_bitcoindimand_price);
        tv_openanx_price = (TextView)findViewById(R.id.tv_openanx_price);
        tv_aelf_price = (TextView) findViewById(R.id.tv_aelf_price);
        tv_insexosystem_price = (TextView) findViewById(R.id.tv_insexosystem_price);
        tv_zilliqa_price = (TextView) findViewById(R.id.tv_zilliqa_price);
        tv_aeternity_price = (TextView) findViewById(R.id.tv_aeternity_price);
        tv_propy_price = (TextView) findViewById(R.id.tv_propy_price);
        tv_dogecoin_price = (TextView) findViewById(R.id.tv_dogecoin_price);
        tv_iotchain_price = (TextView) findViewById(R.id.tv_iotchain_price);
        tv_reddcoin_price = (TextView)findViewById(R.id.tv_reddcoin_price);
        tv_swftcoin_price = (TextView) findViewById(R.id.tv_swftcoin_price);
        tv_monaco_price = (TextView) findViewById(R.id.tv_monaco_price);
        tv_aeron_price = (TextView) findViewById(R.id.tv_aeron_price);
        tv_medicakchain_price = (TextView) findViewById(R.id.tv_medicakchain_price);
        tv_breadtoken_price = (TextView) findViewById(R.id.tv_breadtoken_price);
        tv_santiment_price = (TextView) findViewById(R.id.tv_santiment_price);
        tv_nebulas_price = (TextView) findViewById(R.id.tv_nebulas_price);
        tv_genesis_price = (TextView) findViewById(R.id.tv_genesis_price);
        tv_qunqun_price = (TextView) findViewById(R.id.tv_qunqun_price);
        tv_waltonchain_price = (TextView) findViewById(R.id.tv_waltonchain_price);
        tv_factoids_price = (TextView) findViewById(R.id.tv_factoids_price);
        tv_coinDash_price = (TextView) findViewById(R.id.tv_coinDash_price);
        tv_ripio_price = (TextView) findViewById(R.id.tv_ripio_price);

        tv_btc_pct = (TextView) findViewById(R.id.tv_btc_pct);
        tv_eth_pct = (TextView) findViewById(R.id.tv_eth_pct);
        tv_bch_pct = (TextView) findViewById(R.id.tv_bch_pct);
        tv_xrp_pct = (TextView) findViewById(R.id.tv_xrp_pct);
        tv_ltc_pct = (TextView) findViewById(R.id.tv_ltc_pct);
        tv_ada_pct = (TextView) findViewById(R.id.tv_ada_pct);
        tv_iot_pct = (TextView) findViewById(R.id.tv_iot_pct);
        tv_dash_pct = (TextView) findViewById(R.id.tv_dash_pct);
        tv_xem_pct = (TextView) findViewById(R.id.tv_xem_pct);
        tv_xmr_pct = (TextView) findViewById(R.id.tv_xmr_pct);
        tv_eos_pct = (TextView) findViewById(R.id.tv_eos_pct);
        tv_btg_pct = (TextView) findViewById(R.id.tv_btg_pct);
        tv_qtum_pct = (TextView) findViewById(R.id.tv_qtum_pct);
        tv_xlm_pct = (TextView) findViewById(R.id.tv_xlm_pct);
        tv_neo_pct = (TextView) findViewById(R.id.tv_neo_pct);
        tv_etc_pct = (TextView) findViewById(R.id.tv_etc_pct);
        tv_zec_pct = (TextView) findViewById(R.id.tv_zec_pct);
        tv_steem_pct = (TextView) findViewById(R.id.tv_steem_ptc);
        tv_xzc_pct = (TextView) findViewById(R.id.tv_xzc_ptc);
        tv_storj_pct = (TextView) findViewById(R.id.tv_storj_ptc);
        tv_aion_pct = (TextView) findViewById(R.id.tv_aion_ptc);
        tv_ht_ptc = (TextView) findViewById(R.id.tv_ht_ptc);
        tv_tronix_ptc = (TextView) findViewById(R.id.tv_tronix_ptc);
        tv_nano_ptc = (TextView) findViewById(R.id.tv_nano_ptc);
        tv_omisego_ptc = (TextView) findViewById(R.id.tv_omisego_ptc);
        tv_elastos_ptc = (TextView) findViewById(R.id.tv_elastos_ptc);
        tv_binance_ptc = (TextView) findViewById(R.id.tv_binance_ptc);
        tv_vechain_ptc = (TextView) findViewById(R.id.tv_vechain_ptc);
        tv_zclassic_ptc = (TextView) findViewById(R.id.tv_zclassic_ptc);
        tv_digix_ptc = (TextView) findViewById(R.id.tv_digix_ptc);
        tv_odyssey_ptc = (TextView) findViewById(R.id.tv_odyssey_ptc);
        tv_blockmason_ptc = (TextView) findViewById(R.id.tv_blockmason_ptc);
        tv_lunyr_ptc = (TextView) findViewById(R.id.tv_lunyr_ptc);
        tv_iostoken_ptc = (TextView) findViewById(R.id.tv_iostoken_ptc);
        tv_hshare_ptc = (TextView) findViewById(R.id.tv_hshare_ptc);
        tv_iconproject_ptc = (TextView) findViewById(R.id.tv_iconproject_ptc);
        tv_lisk_ptc = (TextView) findViewById(R.id.tv_lisk_ptc);
        tv_nebilo_ptc = (TextView) findViewById(R.id.tv_nebilo_ptc);
        tv_wavess_ptc = (TextView) findViewById(R.id.tv_wavess_ptc);
        tv_bluzello_ptc = (TextView) findViewById(R.id.tv_bluzello_ptc);
        tv_inkk_ptc = (TextView) findViewById(R.id.tv_inkk_ptc);
        tv_adex_ptc = (TextView) findViewById(R.id.tv_adex_ptc);
        tv_verge_ptc = (TextView) findViewById(R.id.tv_verge_ptc);
        tv_metal_ptc = (TextView) findViewById(R.id.tv_metal_ptc);
        tv_sirinlabs_ptc = (TextView) findViewById(R.id.tv_sirinlabs_ptc);
        tv_ox_ptc = (TextView) findViewById(R.id.tv_ox_ptc);
        tv_iex_ptc = (TextView) findViewById(R.id.tv_iex_ptc);
        tv_thetaa_ptc = (TextView) findViewById(R.id.tv_thetaa_ptc);
        tv_bitcoindimand_ptc = (TextView) findViewById(R.id.tv_bitcoindimand_ptc);
        tv_openanx_ptc = (TextView) findViewById(R.id.tv_openanx_ptc);
        tv_aelf_ptc = (TextView) findViewById(R.id.tv_aelf_ptc);
        tv_insexosystem_ptc = (TextView) findViewById(R.id.tv_insexosystem_ptc);
        tv_zilliqa_ptc = (TextView) findViewById(R.id.tv_zilliqa_ptc);
        tv_aeternity_ptc = (TextView) findViewById(R.id.tv_aeternity_ptc);
        tv_propy_ptc = (TextView) findViewById(R.id.tv_propy_ptc);
        tv_dogecoin_ptc = (TextView) findViewById(R.id.tv_dogecoin_ptc);
        tv_iotchain_ptc = (TextView) findViewById(R.id.tv_iotchain_ptc);
        tv_reddcoin_ptc = (TextView) findViewById(R.id.tv_reddcoin_ptc);
        tv_swftcoin_ptc = (TextView) findViewById(R.id.tv_swftcoin_ptc);
        tv_monaco_ptc = (TextView) findViewById(R.id.tv_monaco_ptc);
        tv_aeron_ptc = (TextView) findViewById(R.id.tv_aeron_ptc);
        tv_medicakchain_ptc = (TextView) findViewById(R.id.tv_medicakchain_ptc);
        tv_breadtoken_ptc = (TextView) findViewById(R.id.tv_breadtoken_ptc);
        tv_santiment_ptc = (TextView) findViewById(R.id.tv_santiment_ptc);
        tv_nebulas_ptc = (TextView) findViewById(R.id.tv_nebulas_ptc);
        tv_genesis_ptc = (TextView) findViewById(R.id.tv_genesis_ptc);
        tv_qunqun_ptc = (TextView) findViewById(R.id.tv_qunqun_ptc);
        tv_waltonchain_ptc = (TextView) findViewById(R.id.tv_waltonchain_ptc);
        tv_factoids_ptc = (TextView) findViewById(R.id.tv_factoids_ptc);
        tv_coinDash_ptc = (TextView) findViewById(R.id.tv_coinDash_ptc);
        tv_ripio_ptc = (TextView) findViewById(R.id.tv_ripio_ptc);
        tv_app_name = (TextView) findViewById(R.id.tv_app_name);


        img_ht = findViewById(R.id.img_ht);
        img_trx = findViewById(R.id.img_trx);
        img_xrb = findViewById(R.id.img_xrb);
        img_omg = findViewById(R.id.img_omg);
        img_ela = findViewById(R.id.img_ela);
        img_bnb = findViewById(R.id.img_bnb);
        img_ven = findViewById(R.id.img_ven);
        img_zcl = findViewById(R.id.img_zcl);
        img_dgd = findViewById(R.id.img_dgd);
        img_ocn = findViewById(R.id.img_ocn);
        img_bcpt = findViewById(R.id.img_bcpt);
        img_lun = findViewById(R.id.img_lun);
        img_iost = findViewById(R.id.img_iost);
        img_hsr = findViewById(R.id.img_hsr);
        img_icx = findViewById(R.id.img_icx);
        img_lsk = findViewById(R.id.img_lsk);
        img_nebl = findViewById(R.id.img_nebl);
        img_waves = findViewById(R.id.img_waves);
        img_blz = findViewById(R.id.img_blz);
        img_ink = findViewById(R.id.img_ink);
        img_adx = findViewById(R.id.img_adx);
        img_xvg = findViewById(R.id.img_xvg);
        img_mtl = findViewById(R.id.img_mtl);
        img_srn = findViewById(R.id.img_srn);
        img_zrx = findViewById(R.id.img_zrx);
        img_rlc = findViewById(R.id.img_rlc);
        img_theta = findViewById(R.id.img_theta);
        img_bcd = findViewById(R.id.img_bcd);
        img_oax = findViewById(R.id.img_oax);
        img_elf = findViewById(R.id.img_elf);
        img_ins = findViewById(R.id.img_ins);
        img_zil = findViewById(R.id.img_zil);
        img_ae = findViewById(R.id.img_ae);
        img_pro = findViewById(R.id.img_pro);
        img_doge = findViewById(R.id.img_doge);
        img_itc = findViewById(R.id.img_itc);
        img_rdd = findViewById(R.id.img_rdd);
        img_swftc = findViewById(R.id.img_swftc);
        img_mco = findViewById(R.id.img_mco);
        img_arn = findViewById(R.id.img_arn);
        img_mtn = findViewById(R.id.img_mtn);
        img_brd = findViewById(R.id.img_brd);
        img_san = findViewById(R.id.img_san);
        img_nas = findViewById(R.id.img_nas);
        img_gvt = findViewById(R.id.img_gvt);
        img_qun = findViewById(R.id.img_qun);
        img_wtc = findViewById(R.id.img_wtc);
        img_fct = findViewById(R.id.img_fct);
        img_cdt = findViewById(R.id.img_cdt);
        img_rcn = findViewById(R.id.img_rcn);

        Glide.with(this).load("https://www.cryptocompare.com/media/27010813/ht.png").into(img_ht);
        Glide.with(this).load("https://www.cryptocompare.com/media/12318089/trx.png").into(img_trx);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383674/xrb.png").into(img_xrb);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383814/omisego.png").into(img_omg);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010574/ela.png").into(img_ela);
        Glide.with(this).load("https://www.cryptocompare.com/media/1382967/bnb.png").into(img_bnb);
        Glide.with(this).load("https://www.cryptocompare.com/media/12318129/ven.png").into(img_ven);
        Glide.with(this).load("https://www.cryptocompare.com/media/351926/zcl.png").into(img_zcl);
        Glide.with(this).load("https://www.cryptocompare.com/media/350851/dgd.png").into(img_dgd);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010448/ocn.png").into(img_ocn);
        Glide.with(this).load("https://www.cryptocompare.com/media/16746476/bcpt.png").into(img_bcpt);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383112/lunyr-logo.png").into(img_lun);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010459/iost.png").into(img_iost);
        Glide.with(this).load("https://www.cryptocompare.com/media/12318137/hsr.png").into(img_hsr);
        Glide.with(this).load("https://www.cryptocompare.com/media/12318192/icx.png").into(img_icx);
        Glide.with(this).load("https://www.cryptocompare.com/media/27011060/lsk.png").into(img_lsk);
        Glide.with(this).load("https://www.cryptocompare.com/media/1384016/nebl.png").into(img_nebl);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010639/waves2.png").into(img_waves);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010607/1.png").into(img_blz);
        Glide.with(this).load("https://www.cryptocompare.com/media/20780781/ink.png").into(img_ink);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383667/adx1.png").into(img_adx);
        Glide.with(this).load("https://www.cryptocompare.com/media/12318032/xvg.png").into(img_xvg);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383743/mtl.png").into(img_mtl);
        Glide.with(this).load("https://www.cryptocompare.com/media/14913556/srn.png").into(img_srn);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383799/zrx.png").into(img_zrx);
        Glide.with(this).load("https://www.cryptocompare.com/media/12318418/rlc.png").into(img_rlc);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010450/theta.png").into(img_theta);
        Glide.with(this).load("https://www.cryptocompare.com/media/16404872/bcd.png").into(img_bcd);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383756/oax.png").into(img_oax);
        Glide.with(this).load("https://www.cryptocompare.com/media/20780600/elf.png").into(img_elf);
//        Glide.with(this).load("https://www.cryptocompare.com/media/27010464/zil.png").into(img_ins);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010464/zil.png").into(img_zil);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383836/ae.png").into(img_ae);
//        Glide.with(this).load("https://www.cryptocompare.com/media/27010813/ht.png").into(img_pro);
        Glide.with(this).load("https://www.cryptocompare.com/media/19684/doge.png").into(img_doge);
        Glide.with(this).load("https://www.cryptocompare.com/media/20780628/itc.png").into(img_itc);
        Glide.with(this).load("https://www.cryptocompare.com/media/19887/rdd.png").into(img_rdd);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010472/swftc.png").into(img_swftc);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383653/mco.jpg").into(img_mco);
        Glide.with(this).load("https://www.cryptocompare.com/media/12318261/arn.png").into(img_arn);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010631/mtn_logo.png").into(img_mtn);
        Glide.with(this).load("https://www.cryptocompare.com/media/20780589/brd.png").into(img_brd);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383730/san.png").into(img_san);
        Glide.with(this).load("https://www.cryptocompare.com/media/20780653/nas.png").into(img_nas);
        Glide.with(this).load("https://www.cryptocompare.com/media/14913634/gvt.png").into(img_gvt);
        Glide.with(this).load("https://www.cryptocompare.com/media/27010466/qun.png").into(img_qun);
        Glide.with(this).load("https://www.cryptocompare.com/media/12317959/wtc.png").into(img_wtc);
        Glide.with(this).load("https://www.cryptocompare.com/media/1382863/fct1.png").into(img_fct);
        Glide.with(this).load("https://www.cryptocompare.com/media/1383699/cdt.png").into(img_cdt);
        Glide.with(this).load("https://www.cryptocompare.com/media/12318046/rnc.png").into(img_rcn);


        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");

        tv_btc_name.setTypeface(type1);
        tv_eth_name.setTypeface(type1);
        tv_bch_name.setTypeface(type1);
        tv_xrp_name.setTypeface(type1);
        tv_ltc_name.setTypeface(type1);
        tv_ada_name.setTypeface(type1);
        tv_iot_name.setTypeface(type1);
        tv_dash_name.setTypeface(type1);
        tv_xem_name.setTypeface(type1);
        tv_xmr_name.setTypeface(type1);
        tv_eos_name.setTypeface(type1);
        tv_btg_name.setTypeface(type1);
        tv_qtum_name.setTypeface(type1);
        tv_xlm_name.setTypeface(type1);
        tv_neo_name.setTypeface(type1);
        tv_etc_name.setTypeface(type1);
        tv_zec_name.setTypeface(type1);
        tv_steem_name.setTypeface(type1);
        tv_xzc_name.setTypeface(type1);
        tv_storj_name.setTypeface(type1);
        tv_aion_name.setTypeface(type1);
        tv_ht_name.setTypeface(type1);
        tv_tronix_name.setTypeface(type1);
        tv_nano_name.setTypeface(type1);
        tv_omisego_name.setTypeface(type1);
        tv_elastos_name.setTypeface(type1);
        tv_binance_name.setTypeface(type1);
        tv_vechain_name.setTypeface(type1);
        tv_zclassic_name.setTypeface(type1);
        tv_digix_name.setTypeface(type1);
        tv_odyssey_name.setTypeface(type1);
        tv_blockmason_name.setTypeface(type1);
        tv_lunyr_name.setTypeface(type1);
        tv_iostoken_name.setTypeface(type1);
        tv_hshare_name.setTypeface(type1);
        tv_iconproject_name.setTypeface(type1);
        tv_lisk_name.setTypeface(type1);
        tv_nebilo_name.setTypeface(type1);
        tv_wavess_name.setTypeface(type1);
        tv_bluzello_name.setTypeface(type1);
        tv_inkk_name.setTypeface(type1);
        tv_adex_name.setTypeface(type1);
        tv_verge_name.setTypeface(type1);
        tv_metal_name.setTypeface(type1);
        tv_sirinlabs_name.setTypeface(type1);
        tv_ox_name.setTypeface(type1);
        tv_iex_name.setTypeface(type1);
        tv_thetaa_name.setTypeface(type1);
        tv_bitcoindimand_name.setTypeface(type1);
        tv_openanx_name.setTypeface(type1);
        tv_aelf_name.setTypeface(type1);
        tv_insexosystem_name.setTypeface(type1);
        tv_zilliqa_name.setTypeface(type1);
        tv_aeternity_name.setTypeface(type1);
        tv_propy_name.setTypeface(type1);
        tv_dogecoin_name.setTypeface(type1);
        tv_iotchain_name.setTypeface(type1);
        tv_reddcoin_name.setTypeface(type1);
        tv_swftcoin_name.setTypeface(type1);
        tv_monaco_name.setTypeface(type1);
        tv_aeron_name.setTypeface(type1);
        tv_medicakchain_name.setTypeface(type1);
        tv_breadtoken_name.setTypeface(type1);
        tv_santiment_name.setTypeface(type1);
        tv_nebulas_name.setTypeface(type1);
        tv_genesis_name.setTypeface(type1);
        tv_qunqun_name.setTypeface(type1);
        tv_waltonchain_name.setTypeface(type1);
        tv_factoids_name.setTypeface(type1);
        tv_coinDash_name.setTypeface(type1);
        tv_ripio_name.setTypeface(type1);

        tv_btc_sf.setTypeface(type1);
        tv_eth_sf.setTypeface(type1);
        tv_bch_sf.setTypeface(type1);
        tv_xrp_sf.setTypeface(type1);
        tv_ltc_sf.setTypeface(type1);
        tv_ada_sf.setTypeface(type1);
        tv_iot_sf.setTypeface(type1);
        tv_dash_sf.setTypeface(type1);
        tv_xem_sf.setTypeface(type1);
        tv_xmr_sf.setTypeface(type1);
        tv_eos_sf.setTypeface(type1);
        tv_btg_sf.setTypeface(type1);
        tv_qtum_sf.setTypeface(type1);
        tv_xlm_sf.setTypeface(type1);
        tv_neo_sf.setTypeface(type1);
        tv_etc_sf.setTypeface(type1);
        tv_zec_sf.setTypeface(type1);
        tv_steem_sf.setTypeface(type1);
        tv_xzc_sf.setTypeface(type1);
        tv_storj_sf.setTypeface(type1);
        tv_aion_sf.setTypeface(type1);
        tv_ht_sf.setTypeface(type1);
        tv_tronix_sf.setTypeface(type1);
        tv_nano_sf.setTypeface(type1);
        tv_omisego_sf.setTypeface(type1);
        tv_elastos_sf.setTypeface(type1);
        tv_binance_sf.setTypeface(type1);
        tv_vechain_sf.setTypeface(type1);
        tv_zclassic_sf.setTypeface(type1);
        tv_digix_sf.setTypeface(type1);
        tv_odyssey_sf.setTypeface(type1);
        tv_blockmason_sf.setTypeface(type1);
        tv_lunyr_sf.setTypeface(type1);
        tv_iostoken_sf.setTypeface(type1);
        tv_hshare_sf.setTypeface(type1);
        tv_iconproject_sf.setTypeface(type1);
        tv_lisk_sf.setTypeface(type1);
        tv_nebilo_sf.setTypeface(type1);
        tv_wavess_sf.setTypeface(type1);
        tv_bluzello_sf.setTypeface(type1);
        tv_inkk_sf.setTypeface(type1);
        tv_adex_sf.setTypeface(type1);
        tv_verge_sf.setTypeface(type1);
        tv_metal_sf.setTypeface(type1);
        tv_sirinlabs_sf.setTypeface(type1);
        tv_ox_sf.setTypeface(type1);
        tv_iex_sf.setTypeface(type1);
        tv_thetaa_sf.setTypeface(type1);
        tv_bitcoindimand_sf.setTypeface(type1);
        tv_openanx_sf.setTypeface(type1);
        tv_aelf_sf.setTypeface(type1);
        tv_insexosystem_sf.setTypeface(type1);
        tv_zilliqa_sf.setTypeface(type1);
        tv_aeternity_sf.setTypeface(type1);
        tv_propy_sf.setTypeface(type1);
        tv_dogecoin_sf.setTypeface(type1);
        tv_iotchain_sf.setTypeface(type1);
        tv_reddcoin_sf.setTypeface(type1);
        tv_swftcoin_sf.setTypeface(type1);
        tv_monaco_sf.setTypeface(type1);
        tv_aeron_sf.setTypeface(type1);
        tv_medicakchain_sf.setTypeface(type1);
        tv_breadtoken_sf.setTypeface(type1);
        tv_santiment_sf.setTypeface(type1);
        tv_nebulas_sf.setTypeface(type1);
        tv_genesis_sf.setTypeface(type1);
        tv_qunqun_sf.setTypeface(type1);
        tv_waltonchain_sf.setTypeface(type1);
        tv_factoids_sf.setTypeface(type1);
        tv_coinDash_sf.setTypeface(type1);
        tv_ripio_sf.setTypeface(type1);

        tv_btc_price.setTypeface(type);
        tv_eth_price.setTypeface(type);
        tv_bch_price.setTypeface(type);
        tv_xrp_price.setTypeface(type);
        tv_ltc_price.setTypeface(type);
        tv_ada_price.setTypeface(type);
        tv_iot_price.setTypeface(type);
        tv_dash_price.setTypeface(type);
        tv_xem_price.setTypeface(type);
        tv_xmr_price.setTypeface(type);
        tv_eos_price.setTypeface(type);
        tv_btg_price.setTypeface(type);
        tv_qtum_price.setTypeface(type);
        tv_xlm_price.setTypeface(type);
        tv_neo_price.setTypeface(type);
        tv_etc_price.setTypeface(type);
        tv_zec_price.setTypeface(type);
        tv_steem_pct.setTypeface(type);
        tv_xzc_price.setTypeface(type);
        tv_storj_price.setTypeface(type);
        tv_aion_price.setTypeface(type);
        tv_aion_price.setTypeface(type);
        tv_ht_price.setTypeface(type);
        tv_tronix_price.setTypeface(type);
        tv_nano_price.setTypeface(type);
        tv_omisego_price.setTypeface(type);
        tv_elastos_price.setTypeface(type);
        tv_binance_price.setTypeface(type);
        tv_vechain_price.setTypeface(type);
        tv_zclassic_price.setTypeface(type);
        tv_digix_price.setTypeface(type);
        tv_odyssey_price.setTypeface(type);
        tv_blockmason_price.setTypeface(type);
        tv_lunyr_price.setTypeface(type);
        tv_iostoken_price.setTypeface(type);
        tv_hshare_price.setTypeface(type);
        tv_iconproject_price.setTypeface(type);
        tv_lisk_price.setTypeface(type);
        tv_nebilo_price.setTypeface(type);
        tv_wavess_price.setTypeface(type);
        tv_bluzello_price.setTypeface(type);
        tv_inkk_price.setTypeface(type);
        tv_adex_price.setTypeface(type);
        tv_verge_price.setTypeface(type);
        tv_metal_price.setTypeface(type);
        tv_sirinlabs_price.setTypeface(type);
        tv_ox_price.setTypeface(type);
        tv_iex_price.setTypeface(type);
        tv_thetaa_price.setTypeface(type);
        tv_bitcoindimand_price.setTypeface(type);
        tv_openanx_price.setTypeface(type);
        tv_aelf_price.setTypeface(type);
        tv_insexosystem_price.setTypeface(type);
        tv_zilliqa_price.setTypeface(type);
        tv_aeternity_price.setTypeface(type);
        tv_propy_price.setTypeface(type);
        tv_dogecoin_price.setTypeface(type);
        tv_iotchain_price.setTypeface(type);
        tv_reddcoin_price.setTypeface(type);
        tv_swftcoin_price.setTypeface(type);
        tv_monaco_price.setTypeface(type);
        tv_aeron_price.setTypeface(type);
        tv_medicakchain_price.setTypeface(type);
        tv_breadtoken_price.setTypeface(type);
        tv_santiment_price.setTypeface(type);
        tv_nebulas_price.setTypeface(type);
        tv_genesis_price.setTypeface(type);
        tv_qunqun_price.setTypeface(type);
        tv_waltonchain_price.setTypeface(type);
        tv_factoids_price.setTypeface(type);
        tv_coinDash_price.setTypeface(type);
        tv_ripio_price.setTypeface(type);

        tv_btc_pct.setTypeface(type1);
        tv_eth_pct.setTypeface(type1);
        tv_bch_pct.setTypeface(type1);
        tv_xrp_pct.setTypeface(type1);
        tv_ltc_pct.setTypeface(type1);
        tv_ada_pct.setTypeface(type1);
        tv_iot_pct.setTypeface(type1);
        tv_dash_pct.setTypeface(type1);
        tv_xem_pct.setTypeface(type1);
        tv_xmr_pct.setTypeface(type1);
        tv_eos_pct.setTypeface(type1);
        tv_btg_pct.setTypeface(type1);
        tv_qtum_pct.setTypeface(type1);
        tv_xlm_pct.setTypeface(type1);
        tv_neo_pct.setTypeface(type1);
        tv_etc_pct.setTypeface(type1);
        tv_zec_pct.setTypeface(type1);
        tv_steem_pct.setTypeface(type1);
        tv_xzc_pct.setTypeface(type1);
        tv_storj_pct.setTypeface(type1);
        tv_aion_pct.setTypeface(type1);
        tv_ht_ptc.setTypeface(type1);
        tv_tronix_ptc.setTypeface(type1);
        tv_nano_ptc.setTypeface(type1);
        tv_omisego_ptc.setTypeface(type1);
        tv_elastos_ptc.setTypeface(type1);
        tv_binance_ptc.setTypeface(type1);
        tv_vechain_ptc.setTypeface(type1);
        tv_zclassic_ptc.setTypeface(type1);
        tv_digix_ptc.setTypeface(type1);
        tv_odyssey_ptc.setTypeface(type1);
        tv_blockmason_ptc.setTypeface(type1);
        tv_lunyr_ptc.setTypeface(type1);
        tv_iostoken_ptc.setTypeface(type1);
        tv_hshare_ptc.setTypeface(type1);
        tv_iconproject_ptc.setTypeface(type1);
        tv_lisk_ptc.setTypeface(type1);
        tv_nebilo_ptc.setTypeface(type1);
        tv_wavess_ptc.setTypeface(type1);
        tv_bluzello_ptc.setTypeface(type1);
        tv_inkk_ptc.setTypeface(type1);
        tv_adex_ptc.setTypeface(type1);
        tv_verge_ptc.setTypeface(type1);
        tv_metal_ptc.setTypeface(type1);
        tv_sirinlabs_ptc.setTypeface(type1);
        tv_ox_ptc.setTypeface(type1);
        tv_iex_ptc.setTypeface(type1);
        tv_thetaa_ptc.setTypeface(type1);
        tv_bitcoindimand_ptc.setTypeface(type1);
        tv_openanx_ptc.setTypeface(type1);
        tv_aelf_ptc.setTypeface(type1);
        tv_insexosystem_ptc.setTypeface(type1);
        tv_zilliqa_ptc.setTypeface(type1);
        tv_aeternity_ptc.setTypeface(type1);
        tv_propy_ptc.setTypeface(type1);
        tv_dogecoin_ptc.setTypeface(type1);
        tv_iotchain_ptc.setTypeface(type1);
        tv_reddcoin_ptc.setTypeface(type1);
        tv_swftcoin_ptc.setTypeface(type1);
        tv_monaco_ptc.setTypeface(type1);
        tv_aeron_ptc.setTypeface(type1);
        tv_medicakchain_ptc.setTypeface(type1);
        tv_breadtoken_ptc.setTypeface(type1);
        tv_santiment_ptc.setTypeface(type1);
        tv_nebulas_ptc.setTypeface(type1);
        tv_genesis_ptc.setTypeface(type1);
        tv_qunqun_ptc.setTypeface(type1);
        tv_waltonchain_ptc.setTypeface(type1);
        tv_factoids_ptc.setTypeface(type1);
        tv_coinDash_ptc.setTypeface(type1);
        tv_ripio_ptc.setTypeface(type1);

        tv_btc_pct.setTypeface(type1);
        tv_eth_pct.setTypeface(type1);
        tv_bch_pct.setTypeface(type1);
        tv_xrp_pct.setTypeface(type1);
        tv_ltc_pct.setTypeface(type1);
        tv_ada_pct.setTypeface(type1);
        tv_iot_pct.setTypeface(type1);
        tv_dash_pct.setTypeface(type1);
        tv_xem_pct.setTypeface(type1);
        tv_xmr_pct.setTypeface(type1);
        tv_eos_pct.setTypeface(type1);
        tv_btg_pct.setTypeface(type1);
        tv_qtum_pct.setTypeface(type1);
        tv_xlm_pct.setTypeface(type1);
        tv_neo_pct.setTypeface(type1);
        tv_etc_pct.setTypeface(type1);
        tv_zec_pct.setTypeface(type1);
        tv_steem_pct.setTypeface(type1);
        tv_xzc_pct.setTypeface(type1);
        tv_storj_pct.setTypeface(type1);
        tv_aion_pct.setTypeface(type1);

        tv_btc_mc.setTypeface(type1);
        tv_eth_mc.setTypeface(type1);
        tv_bch_mc.setTypeface(type1);
        tv_xrp_mc.setTypeface(type1);
        tv_ltc_mc.setTypeface(type1);
        tv_ada_mc.setTypeface(type1);
        tv_iot_mc.setTypeface(type1);
        tv_dash_mc.setTypeface(type1);
        tv_xem_mc.setTypeface(type1);
        tv_xmr_mc.setTypeface(type1);
        tv_eos_mc.setTypeface(type1);
        tv_btg_mc.setTypeface(type1);
        tv_qtum_mc.setTypeface(type1);
        tv_xlm_mc.setTypeface(type1);
        tv_neo_mc.setTypeface(type1);
        tv_etc_mc.setTypeface(type1);
        tv_zec_mc.setTypeface(type1);
        tv_steem_mc.setTypeface(type1);
        tv_xzc_mc.setTypeface(type1);
        tv_storj_mc.setTypeface(type1);
        tv_aion_mc.setTypeface(type1);
        tv_ht_mc.setTypeface(type1);
        tv_tronix_mc.setTypeface(type1);
        tv_nano_mc.setTypeface(type1);
        tv_omisego_mc.setTypeface(type1);
        tv_elastos_mc.setTypeface(type1);
        tv_binance_mc.setTypeface(type1);
        tv_vechain_mc.setTypeface(type1);
        tv_zclassic_mc.setTypeface(type1);
        tv_digix_mc.setTypeface(type1);
        tv_odyssey_mc.setTypeface(type1);
        tv_blockmason_mc.setTypeface(type1);
        tv_lunyr_mc.setTypeface(type1);
        tv_iostoken_mc.setTypeface(type1);
        tv_hshare_mc.setTypeface(type1);
        tv_iconproject_mc.setTypeface(type1);
        tv_lisk_mc.setTypeface(type1);
        tv_nebilo_mc.setTypeface(type1);
        tv_wavess_mc.setTypeface(type1);
        tv_bluzello_mc.setTypeface(type1);
        tv_inkk_mc.setTypeface(type1);
        tv_adex_mc.setTypeface(type1);
        tv_verge_mc.setTypeface(type1);
        tv_metal_mc.setTypeface(type1);
        tv_sirinlabs_mc.setTypeface(type1);
        tv_ox_mc.setTypeface(type1);
        tv_iex_mc.setTypeface(type1);
        tv_thetaa_mc.setTypeface(type1);
        tv_bitcoindimand_mc.setTypeface(type1);
        tv_openanx_mc.setTypeface(type1);
        tv_aelf_mc.setTypeface(type1);
        tv_insexosystem_mc.setTypeface(type1);
        tv_zilliqa_mc.setTypeface(type1);
        tv_aeternity_mc.setTypeface(type1);
        tv_propy_mc.setTypeface(type1);
        tv_dogecoin_mc.setTypeface(type1);
        tv_iotchain_mc.setTypeface(type1);
        tv_reddcoin_mc.setTypeface(type1);
        tv_swftcoin_mc.setTypeface(type1);
        tv_monaco_mc.setTypeface(type1);
        tv_aeron_mc.setTypeface(type1);
        tv_medicakchain_mc.setTypeface(type1);
        tv_breadtoken_mc.setTypeface(type1);
        tv_santiment_mc.setTypeface(type1);
        tv_nebulas_mc.setTypeface(type1);
        tv_genesis_mc.setTypeface(type1);
        tv_qunqun_mc.setTypeface(type1);
        tv_waltonchain_mc.setTypeface(type1);
        tv_factoids_mc.setTypeface(type1);
        tv_coinDash_mc.setTypeface(type1);
        tv_ripio_mc.setTypeface(type1);


        iv_un_fav.setOnClickListener(this);
        iv_un_fav1.setOnClickListener(this);
        iv_un_fav2.setOnClickListener(this);
        iv_un_fav3.setOnClickListener(this);
        iv_un_fav4.setOnClickListener(this);
        iv_un_fav5.setOnClickListener(this);
        iv_un_fav6.setOnClickListener(this);
        iv_un_fav7.setOnClickListener(this);
        iv_un_fav8.setOnClickListener(this);
        iv_un_fav9.setOnClickListener(this);
        iv_un_fav10.setOnClickListener(this);
        iv_un_fav11.setOnClickListener(this);
        iv_un_fav12.setOnClickListener(this);
        iv_un_fav13.setOnClickListener(this);
        iv_un_fav14.setOnClickListener(this);
        iv_un_fav15.setOnClickListener(this);
        iv_un_fav16.setOnClickListener(this);
        iv_un_fav17.setOnClickListener(this);
        iv_un_fav18.setOnClickListener(this);
        iv_un_fav19.setOnClickListener(this);
        iv_un_fav20.setOnClickListener(this);
        iv_un_fav21.setOnClickListener(this);
        iv_un_fav22.setOnClickListener(this);
        iv_un_fav23.setOnClickListener(this);
        iv_un_fav24.setOnClickListener(this);
        iv_un_fav25.setOnClickListener(this);
        iv_un_fav26.setOnClickListener(this);
        iv_un_fav27.setOnClickListener(this);
        iv_un_fav28.setOnClickListener(this);
        iv_un_fav29.setOnClickListener(this);
        iv_un_fav30.setOnClickListener(this);
        iv_un_fav31.setOnClickListener(this);
        iv_un_fav32.setOnClickListener(this);
        iv_un_fav33.setOnClickListener(this);
        iv_un_fav34.setOnClickListener(this);
        iv_un_fav35.setOnClickListener(this);
        iv_un_fav36.setOnClickListener(this);
        iv_un_fav37.setOnClickListener(this);
        iv_un_fav38.setOnClickListener(this);
        iv_un_fav39.setOnClickListener(this);
        iv_un_fav40.setOnClickListener(this);
        iv_un_fav41.setOnClickListener(this);
        iv_un_fav42.setOnClickListener(this);
        iv_un_fav43.setOnClickListener(this);
        iv_un_fav44.setOnClickListener(this);
        iv_un_fav45.setOnClickListener(this);
        iv_un_fav46.setOnClickListener(this);
        iv_un_fav47.setOnClickListener(this);
        iv_un_fav48.setOnClickListener(this);
        iv_un_fav49.setOnClickListener(this);
        iv_un_fav50.setOnClickListener(this);
        iv_un_fav51.setOnClickListener(this);
        iv_un_fav52.setOnClickListener(this);
        iv_un_fav53.setOnClickListener(this);
        iv_un_fav54.setOnClickListener(this);
        iv_un_fav55.setOnClickListener(this);
        iv_un_fav56.setOnClickListener(this);
        iv_un_fav57.setOnClickListener(this);
        iv_un_fav58.setOnClickListener(this);
        iv_un_fav59.setOnClickListener(this);
        iv_un_fav60.setOnClickListener(this);
        iv_un_fav61.setOnClickListener(this);
        iv_un_fav62.setOnClickListener(this);
        iv_un_fav63.setOnClickListener(this);
        iv_un_fav64.setOnClickListener(this);
        iv_un_fav65.setOnClickListener(this);
        iv_un_fav66.setOnClickListener(this);
        iv_un_fav67.setOnClickListener(this);
        iv_un_fav68.setOnClickListener(this);
        iv_un_fav69.setOnClickListener(this);
        iv_un_fav70.setOnClickListener(this);

        iv_fav.setOnClickListener(this);
        iv_fav1.setOnClickListener(this);
        iv_fav2.setOnClickListener(this);
        iv_fav3.setOnClickListener(this);
        iv_fav4.setOnClickListener(this);
        iv_fav5.setOnClickListener(this);
        iv_fav6.setOnClickListener(this);
        iv_fav7.setOnClickListener(this);
        iv_fav8.setOnClickListener(this);
        iv_fav9.setOnClickListener(this);
        iv_fav10.setOnClickListener(this);
        iv_fav11.setOnClickListener(this);
        iv_fav12.setOnClickListener(this);
        iv_fav13.setOnClickListener(this);
        iv_fav14.setOnClickListener(this);
        iv_fav15.setOnClickListener(this);
        iv_fav16.setOnClickListener(this);
        iv_fav17.setOnClickListener(this);
        iv_fav18.setOnClickListener(this);
        iv_fav19.setOnClickListener(this);
        iv_fav20.setOnClickListener(this);
        iv_fav21.setOnClickListener(this);
        iv_fav22.setOnClickListener(this);
        iv_fav23.setOnClickListener(this);
        iv_fav24.setOnClickListener(this);
        iv_fav25.setOnClickListener(this);
        iv_fav26.setOnClickListener(this);
        iv_fav27.setOnClickListener(this);
        iv_fav28.setOnClickListener(this);
        iv_fav29.setOnClickListener(this);
        iv_fav30.setOnClickListener(this);
        iv_fav31.setOnClickListener(this);
        iv_fav32.setOnClickListener(this);
        iv_fav33.setOnClickListener(this);
        iv_fav34.setOnClickListener(this);
        iv_fav35.setOnClickListener(this);
        iv_fav36.setOnClickListener(this);
        iv_fav37.setOnClickListener(this);
        iv_fav38.setOnClickListener(this);
        iv_fav39.setOnClickListener(this);
        iv_fav40.setOnClickListener(this);
        iv_fav41.setOnClickListener(this);
        iv_fav42.setOnClickListener(this);
        iv_fav43.setOnClickListener(this);
        iv_fav44.setOnClickListener(this);
        iv_fav45.setOnClickListener(this);
        iv_fav46.setOnClickListener(this);
        iv_fav47.setOnClickListener(this);
        iv_fav48.setOnClickListener(this);
        iv_fav49.setOnClickListener(this);
        iv_fav50.setOnClickListener(this);
        iv_fav51.setOnClickListener(this);
        iv_fav52.setOnClickListener(this);
        iv_fav53.setOnClickListener(this);
        iv_fav54.setOnClickListener(this);
        iv_fav55.setOnClickListener(this);
        iv_fav56.setOnClickListener(this);
        iv_fav57.setOnClickListener(this);
        iv_fav58.setOnClickListener(this);
        iv_fav59.setOnClickListener(this);
        iv_fav60.setOnClickListener(this);
        iv_fav61.setOnClickListener(this);
        iv_fav62.setOnClickListener(this);
        iv_fav63.setOnClickListener(this);
        iv_fav64.setOnClickListener(this);
        iv_fav65.setOnClickListener(this);
        iv_fav66.setOnClickListener(this);
        iv_fav67.setOnClickListener(this);
        iv_fav68.setOnClickListener(this);
        iv_fav69.setOnClickListener(this);
        iv_fav70.setOnClickListener(this);

        ll_btc.setOnClickListener(this);
        ll_event.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor e = preferences.edit();
        switch (v.getId()){

            case R.id.ll_btc:

                Log.d("size", "" + stringList.size());
                Intent i = new Intent(Home_Activity.this, Favourite_Activity.class);
                i.putStringArrayListExtra("arraylist", (ArrayList<String>) stringList);
                i.putStringArrayListExtra("namearraylist", (ArrayList<String>) nameList);
                startActivity(i);
                break;

            case R.id.ll_event:
                startActivity(new Intent(Home_Activity.this, More_Activity.class));
                break;
            case R.id.iv_un_fav:
                iv_un_fav.setVisibility(View.GONE);
                iv_fav.setVisibility(View.VISIBLE);
                stringList.remove("BTC");
                nameList.remove("Bitcoin");

                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove1 = gson.toJson(stringList);
                String nameremove1 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove1);
                e.putString("key_name", nameremove1);
                e.apply();

                break;
            case R.id.iv_un_fav1:
                iv_un_fav1.setVisibility(View.GONE);
                iv_fav1.setVisibility(View.VISIBLE);
                stringList.remove("ETH");
                nameList.remove("Ethereum");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove2 = gson.toJson(stringList);
                String nameremove2 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove2);
                e.putString("key_name", nameremove2);
                e.apply();

                break;
            case R.id.iv_un_fav2:
                iv_un_fav2.setVisibility(View.GONE);
                iv_fav2.setVisibility(View.VISIBLE);
                stringList.remove("BCH");
                nameList.remove("Bitcoin Cash");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove3 = gson.toJson(stringList);
                String nameremove3 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove3);
                e.putString("key_name", nameremove3);
                e.apply();

                break;
            case R.id.iv_un_fav3:
                iv_un_fav3.setVisibility(View.GONE);
                iv_fav3.setVisibility(View.VISIBLE);
                stringList.remove("XRP");
                nameList.remove("Ripple");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove4 = gson.toJson(stringList);
                String nameremove4 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove4);
                e.putString("key_name", nameremove4);
                e.apply();
//
                break;
            case R.id.iv_un_fav4:
                iv_un_fav4.setVisibility(View.GONE);
                iv_fav4.setVisibility(View.VISIBLE);
                stringList.remove("LTC");
                nameList.remove("Litecoin");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove5 = gson.toJson(stringList);
                String nameremove5 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove5);
                e.putString("key_name", nameremove5);
                e.apply();
//
                break;
            case R.id.iv_un_fav5:
                iv_un_fav5.setVisibility(View.GONE);
                iv_fav5.setVisibility(View.VISIBLE);
                stringList.remove("ADA");
                nameList.remove("Cardano");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove6 = gson.toJson(stringList);
                String nameremove6 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove6);
                e.putString("key_name", nameremove6);
                e.apply();
//
                break;
            case R.id.iv_un_fav6:
                iv_un_fav6.setVisibility(View.GONE);
                iv_fav6.setVisibility(View.VISIBLE);
                stringList.remove("IOT");
                nameList.remove("IOTA");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove7 = gson.toJson(stringList);
                String nameremove7 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove7);
                e.putString("key_name", nameremove7);
                e.apply();
//
                break;
            case R.id.iv_un_fav7:
                iv_un_fav7.setVisibility(View.GONE);
                iv_fav7.setVisibility(View.VISIBLE);
                stringList.remove("DASH");
                nameList.remove("DigitalCash");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove8 = gson.toJson(stringList);
                String nameremove8 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove8);
                e.putString("key_name", nameremove8);
                e.apply();
//
                break;
            case R.id.iv_un_fav8:
                iv_un_fav8.setVisibility(View.GONE);
                iv_fav8.setVisibility(View.VISIBLE);
                stringList.remove("XEM");
                nameList.remove("NEM");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove9 = gson.toJson(stringList);
                String nameremove9 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove9);
                e.putString("key_name", nameremove9);
                e.apply();
//
                break;
            case R.id.iv_un_fav9:
                iv_un_fav9.setVisibility(View.GONE);
                iv_fav9.setVisibility(View.VISIBLE);
                stringList.remove("XMR");
                nameList.remove("Monero");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove10 = gson.toJson(stringList);
                String nameremove10 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove10);
                e.putString("key_name", nameremove10);
                e.apply();
//
                break;
            case R.id.iv_un_fav10:
                iv_un_fav10.setVisibility(View.GONE);
                iv_fav10.setVisibility(View.VISIBLE);
                stringList.remove("EOS");
                nameList.remove("EOS");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove11 = gson.toJson(stringList);
                String nameremove11 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove11);
                e.putString("key_name", nameremove11);
                e.apply();
//
                break;
            case R.id.iv_un_fav11:
                iv_un_fav11.setVisibility(View.GONE);
                iv_fav11.setVisibility(View.VISIBLE);
                stringList.remove("BTG");
                nameList.remove("Bitcoin Gold");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove12 = gson.toJson(stringList);
                String nameremove12 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove12);
                e.putString("key_name", nameremove12);
                e.apply();
//
                break;
            case R.id.iv_un_fav12:
                iv_un_fav12.setVisibility(View.GONE);
                iv_fav12.setVisibility(View.VISIBLE);
                stringList.remove("QTUM");
                nameList.remove("Qtum");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove13 = gson.toJson(stringList);
                String nameremove13 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove13);
                e.putString("key_name", nameremove13);
                e.apply();
//
                break;
            case R.id.iv_un_fav13:
                iv_un_fav13.setVisibility(View.GONE);
                iv_fav13.setVisibility(View.VISIBLE);
                stringList.remove("XLM");
                nameList.remove("Stellar");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove14 = gson.toJson(stringList);
                String nameremove14 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove14);
                e.putString("key_name", nameremove14);
                e.apply();
                break;
            case R.id.iv_un_fav14:
                iv_un_fav14.setVisibility(View.GONE);
                iv_fav14.setVisibility(View.VISIBLE);
                stringList.remove("NEO");
                nameList.remove("NEO");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove15 = gson.toJson(stringList);
                String nameremove15 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove15);
                e.putString("key_name", nameremove15);
                e.apply();
//
                break;
            case R.id.iv_un_fav15:
                iv_un_fav15.setVisibility(View.GONE);
                iv_fav15.setVisibility(View.VISIBLE);
                stringList.remove("ETC");
                nameList.remove("EthereumClas");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove16 = gson.toJson(stringList);
                String nameremove16 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove16);
                e.putString("key_name", nameremove16);
                e.apply();
//
                break;
            case R.id.iv_un_fav16:
                iv_un_fav16.setVisibility(View.GONE);
                iv_fav16.setVisibility(View.VISIBLE);
                stringList.remove("ZEC");
                nameList.remove("Zcash");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove17 = gson.toJson(stringList);
                String nameremove17 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove17);
                e.putString("key_name", nameremove17);
                e.apply();
//
                break;
            case R.id.iv_un_fav17:
                iv_un_fav17.setVisibility(View.GONE);
                iv_fav17.setVisibility(View.VISIBLE);
                stringList.remove("STEEM");
                nameList.remove("Steem");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove18 = gson.toJson(stringList);
                String nameremove18 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove18);
                e.putString("key_name", nameremove18);
                e.apply();
//
                break;
            case R.id.iv_un_fav18:
                iv_un_fav18.setVisibility(View.GONE);
                iv_fav18.setVisibility(View.VISIBLE);
                stringList.remove("XZC");
                nameList.remove("ZCoin");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove19 = gson.toJson(stringList);
                String nameremove19 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove19);
                e.putString("key_name", nameremove19);
                e.apply();
//
                break;
            case R.id.iv_un_fav19:
                iv_un_fav19.setVisibility(View.GONE);
                iv_fav19.setVisibility(View.VISIBLE);
                stringList.remove("STORJ");
                nameList.remove("Storj");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove20 = gson.toJson(stringList);
                String nameremove20 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove20);
                e.putString("key_name", nameremove20);
                e.apply();
//
                break;
            case R.id.iv_un_fav20:
                iv_un_fav20.setVisibility(View.GONE);
                iv_fav20.setVisibility(View.VISIBLE);
                stringList.remove("AION");
                nameList.remove("Aion");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove21 = gson.toJson(stringList);
                String nameremove21 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove21);
                e.putString("key_name", nameremove21);
                e.apply();
//
                break;
            case R.id.iv_un_fav21:
                iv_un_fav21.setVisibility(View.GONE);
                iv_fav21.setVisibility(View.VISIBLE);
                stringList.remove("HT");
                nameList.remove("Huobi Token");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove22 = gson.toJson(stringList);
                String nameremove22 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove22);
                e.putString("key_name", nameremove22);
                e.apply();
//
                break;
            case R.id.iv_un_fav22:
                iv_un_fav22.setVisibility(View.GONE);
                iv_fav22.setVisibility(View.VISIBLE);
                stringList.remove("TRX");
                nameList.remove("Tronix");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove23 = gson.toJson(stringList);
                String nameremove23 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove23);
                e.putString("key_name", nameremove23);
                e.apply();
//
                break;
            case R.id.iv_un_fav23:
                iv_un_fav23.setVisibility(View.GONE);
                iv_fav23.setVisibility(View.VISIBLE);
                stringList.remove("XRB");
                nameList.remove("Nano");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove24 = gson.toJson(stringList);
                String nameremove24 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove24);
                e.putString("key_name", nameremove24);
                e.apply();
//
                break;
            case R.id.iv_un_fav24:
                iv_un_fav24.setVisibility(View.GONE);
                iv_fav24.setVisibility(View.VISIBLE);
                stringList.remove("OMG");
                nameList.remove("OmiseGo");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove25 = gson.toJson(stringList);
                String nameremove25 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove25);
                e.putString("key_name", nameremove25);
                e.apply();
//
                break;
            case R.id.iv_un_fav25:
                iv_un_fav25.setVisibility(View.GONE);
                iv_fav25.setVisibility(View.VISIBLE);
                stringList.remove("ELA");
                nameList.remove("Elastos");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove26 = gson.toJson(stringList);
                String nameremove26 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove26);
                e.putString("key_name", nameremove26);
                e.apply();
//
                break;
            case R.id.iv_un_fav26:
                iv_un_fav26.setVisibility(View.GONE);
                iv_fav26.setVisibility(View.VISIBLE);
                stringList.remove("BNB");
                nameList.remove("Binance Coin");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove27 = gson.toJson(stringList);
                String nameremove27 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove27);
                e.putString("key_name", nameremove27);
                e.apply();
//
                break;
            case R.id.iv_un_fav27:
                iv_un_fav27.setVisibility(View.GONE);
                iv_fav27.setVisibility(View.VISIBLE);
                stringList.remove("VEN");
                nameList.remove("Vechain");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove28 = gson.toJson(stringList);
                String nameremove28 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove28);
                e.putString("key_name", nameremove28);
                e.apply();
//
                break;
            case R.id.iv_un_fav28:
                iv_un_fav28.setVisibility(View.GONE);
                iv_fav28.setVisibility(View.VISIBLE);
                stringList.remove("ZCL");
                nameList.remove("ZClassic");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove29 = gson.toJson(stringList);
                String nameremove29 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove29);
                e.putString("key_name", nameremove29);
                e.apply();
//
                break;
            case R.id.iv_un_fav29:
                iv_un_fav29.setVisibility(View.GONE);
                iv_fav29.setVisibility(View.VISIBLE);
                stringList.remove("DGD");
                nameList.remove("Digix DAO");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove30 = gson.toJson(stringList);
                String nameremove30 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove30);
                e.putString("key_name", nameremove30);
                e.apply();
//
                break;
            case R.id.iv_un_fav30:
                iv_un_fav30.setVisibility(View.GONE);
                iv_fav30.setVisibility(View.VISIBLE);
                stringList.remove("OCN");
                nameList.remove("Odyssey");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove31 = gson.toJson(stringList);
                String nameremove31 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove31);
                e.putString("key_name", nameremove31);
                e.apply();
//
                break;
            case R.id.iv_un_fav31:
                iv_un_fav31.setVisibility(View.GONE);
                iv_fav31.setVisibility(View.VISIBLE);
                stringList.remove("BCPT");
                nameList.remove("BlockMason Credit P.");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove32 = gson.toJson(stringList);
                String nameremove32 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove32);
                e.putString("key_name", nameremove32);
                e.apply();
//
                break;
            case R.id.iv_un_fav32:
                iv_un_fav32.setVisibility(View.GONE);
                iv_fav32.setVisibility(View.VISIBLE);
                stringList.remove("LUN");
                nameList.remove("Lunyr");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove33 = gson.toJson(stringList);
                String nameremove33 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove33);
                e.putString("key_name", nameremove33);
                e.apply();
//
                break;
            case R.id.iv_un_fav33:
                iv_un_fav33.setVisibility(View.GONE);
                iv_fav33.setVisibility(View.VISIBLE);
                stringList.remove("IOST");
                nameList.remove("IOS token");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove34 = gson.toJson(stringList);
                String nameremove34 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove34);
                e.putString("key_name", nameremove34);
                e.apply();
//
                break;
            case R.id.iv_un_fav34:
                iv_un_fav34.setVisibility(View.GONE);
                iv_fav34.setVisibility(View.VISIBLE);
                stringList.remove("HSR");
                nameList.remove("Hshare");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove35 = gson.toJson(stringList);
                String nameremove35 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove35);
                e.putString("key_name", nameremove35);
                e.apply();
//
                break;
            case R.id.iv_un_fav35:
                iv_un_fav35.setVisibility(View.GONE);
                iv_fav35.setVisibility(View.VISIBLE);
                stringList.remove("ICX");
                nameList.remove("ICON Project");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove36 = gson.toJson(stringList);
                String nameremove36 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove36);
                e.putString("key_name", nameremove36);
                e.apply();
//
                break;
            case R.id.iv_un_fav36:
                iv_un_fav36.setVisibility(View.GONE);
                iv_fav36.setVisibility(View.VISIBLE);
                stringList.remove("LSK");
                nameList.remove("Lisk");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove37 = gson.toJson(stringList);
                String nameremove37 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove37);
                e.putString("key_name", nameremove37);
                e.apply();
//
                break;
            case R.id.iv_un_fav37:
                iv_un_fav37.setVisibility(View.GONE);
                iv_fav37.setVisibility(View.VISIBLE);
                stringList.remove("NEBL");
                nameList.remove("Neblio");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove38 = gson.toJson(stringList);
                String nameremove38 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove38);
                e.putString("key_name", nameremove38);
                e.apply();
//
                break;
            case R.id.iv_un_fav38:
                iv_un_fav38.setVisibility(View.GONE);
                iv_fav38.setVisibility(View.VISIBLE);
                stringList.remove("WAVES");
                nameList.remove("Waves");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove39 = gson.toJson(stringList);
                String nameremove39 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove39);
                e.putString("key_name", nameremove39);
                e.apply();
//
                break;
            case R.id.iv_un_fav39:
                iv_un_fav39.setVisibility(View.GONE);
                iv_fav39.setVisibility(View.VISIBLE);
                stringList.remove("BLZ");
                nameList.remove("Bluzelle");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove40 = gson.toJson(stringList);
                String nameremove40 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove40);
                e.putString("key_name", nameremove40);
                e.apply();
//
                break;
            case R.id.iv_un_fav40:
                iv_un_fav40.setVisibility(View.GONE);
                iv_fav40.setVisibility(View.VISIBLE);
                stringList.remove("INK");
                nameList.remove("Ink");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove41 = gson.toJson(stringList);
                String nameremove41 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove41);
                e.putString("key_name", nameremove41);
                e.apply();
//
                break;
            case R.id.iv_un_fav41:
                iv_un_fav41.setVisibility(View.GONE);
                iv_fav41.setVisibility(View.VISIBLE);
                stringList.remove("ADX");
                nameList.remove("AdEx");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove42 = gson.toJson(stringList);
                String nameremove42 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove42);
                e.putString("key_name", nameremove42);
                e.apply();
//
                break;
            case R.id.iv_un_fav42:
                iv_un_fav42.setVisibility(View.GONE);
                iv_fav42.setVisibility(View.VISIBLE);
                stringList.remove("XVG");
                nameList.remove("Verge");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove43 = gson.toJson(stringList);
                String nameremove43 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove43);
                e.putString("key_name", nameremove43);
                e.apply();
//
                break;
            case R.id.iv_un_fav43:
                iv_un_fav43.setVisibility(View.GONE);
                iv_fav43.setVisibility(View.VISIBLE);
                stringList.remove("MTL");
                nameList.remove("Metal");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove44 = gson.toJson(stringList);
                String nameremove44 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove44);
                e.putString("key_name", nameremove44);
                e.apply();
//
                break;
            case R.id.iv_un_fav44:
                iv_un_fav44.setVisibility(View.GONE);
                iv_fav44.setVisibility(View.VISIBLE);
                stringList.remove("SRN");
                nameList.remove("SirinLabs");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove45 = gson.toJson(stringList);
                String nameremove45 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove45);
                e.putString("key_name", nameremove45);
                e.apply();
//
                break;
            case R.id.iv_un_fav45:
                iv_un_fav45.setVisibility(View.GONE);
                iv_fav45.setVisibility(View.VISIBLE);
                stringList.remove("ZRX");
                nameList.remove("0x");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove46 = gson.toJson(stringList);
                String nameremove46 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove46);
                e.putString("key_name", nameremove46);
                e.apply();
//
                break;
            case R.id.iv_un_fav46:
                iv_un_fav46.setVisibility(View.GONE);
                iv_fav46.setVisibility(View.VISIBLE);
                stringList.remove("RLC");
                nameList.remove("iEx.ec");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove47 = gson.toJson(stringList);
                String nameremove47 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove47);
                e.putString("key_name", nameremove47);
                e.apply();
//
                break;
            case R.id.iv_un_fav47:
                iv_un_fav47.setVisibility(View.GONE);
                iv_fav47.setVisibility(View.VISIBLE);
                stringList.remove("THETA");
                nameList.remove("Theta");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove48 = gson.toJson(stringList);
                String nameremove48 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove48);
                e.putString("key_name", nameremove48);
                e.apply();
//
                break;
            case R.id.iv_un_fav48:
                iv_un_fav48.setVisibility(View.GONE);
                iv_fav48.setVisibility(View.VISIBLE);
                stringList.remove("BCD");
                nameList.remove("Bitcoin Diamond");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove49 = gson.toJson(stringList);
                String nameremove49 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove49);
                e.putString("key_name", nameremove49);
                e.apply();
//
                break;
            case R.id.iv_un_fav49:
                iv_un_fav49.setVisibility(View.GONE);
                iv_fav49.setVisibility(View.VISIBLE);
                stringList.remove("OAX");
                nameList.remove("OpenANX");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove50 = gson.toJson(stringList);
                String nameremove50 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove50);
                e.putString("key_name", nameremove50);
                e.apply();
//
                break;
            case R.id.iv_un_fav50:
                iv_un_fav50.setVisibility(View.GONE);
                iv_fav50.setVisibility(View.VISIBLE);
                stringList.remove("ELF");
                nameList.remove("aelf");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove51 = gson.toJson(stringList);
                String nameremove51 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove51);
                e.putString("key_name", nameremove51);
                e.apply();
//
                break;
            case R.id.iv_un_fav51:
                iv_un_fav51.setVisibility(View.GONE);
                iv_fav51.setVisibility(View.VISIBLE);
                stringList.remove("INS");
                nameList.remove("INS Ecosystem");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove52 = gson.toJson(stringList);
                String nameremove52 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove52);
                e.putString("key_name", nameremove52);
                e.apply();
//
                break;
            case R.id.iv_un_fav52:
                iv_un_fav52.setVisibility(View.GONE);
                iv_fav52.setVisibility(View.VISIBLE);
                stringList.remove("ZIL");
                nameList.remove("Zilliqa");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove53 = gson.toJson(stringList);
                String nameremove53 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove53);
                e.putString("key_name", nameremove53);
                e.apply();
//
                break;
            case R.id.iv_un_fav53:
                iv_un_fav53.setVisibility(View.GONE);
                iv_fav53.setVisibility(View.VISIBLE);
                stringList.remove("AE");
                nameList.remove("Aeternity");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove54 = gson.toJson(stringList);
                String nameremove54 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove54);
                e.putString("key_name", nameremove54);
                e.apply();
//
                break;
            case R.id.iv_un_fav54:
                iv_un_fav54.setVisibility(View.GONE);
                iv_fav54.setVisibility(View.VISIBLE);
                stringList.remove("PRO");
                nameList.remove("Propy");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove55 = gson.toJson(stringList);
                String nameremove55 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove55);
                e.putString("key_name", nameremove55);
                e.apply();
//
                break;
            case R.id.iv_un_fav55:
                iv_un_fav55.setVisibility(View.GONE);
                iv_fav55.setVisibility(View.VISIBLE);
                stringList.remove("DOGE");
                nameList.remove("Dogecoin");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove56 = gson.toJson(stringList);
                String nameremove56 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove56);
                e.putString("key_name", nameremove56);
                e.apply();
//
                break;
            case R.id.iv_un_fav56:
                iv_un_fav56.setVisibility(View.GONE);
                iv_fav56.setVisibility(View.VISIBLE);
                stringList.remove("ITC");
                nameList.remove("IoT Chain");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove57 = gson.toJson(stringList);
                String nameremove57 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove57);
                e.putString("key_name", nameremove57);
                e.apply();
//
                break;
            case R.id.iv_un_fav57:
                iv_un_fav57.setVisibility(View.GONE);
                iv_fav57.setVisibility(View.VISIBLE);
                stringList.remove("RDD");
                nameList.remove("ReddCoin");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove58 = gson.toJson(stringList);
                String nameremove58 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove58);
                e.putString("key_name", nameremove58);
                e.apply();
//
                break;
            case R.id.iv_un_fav58:
                iv_un_fav58.setVisibility(View.GONE);
                iv_fav58.setVisibility(View.VISIBLE);
                stringList.remove("SWFTC");
                nameList.remove("SwftCoin");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove59 = gson.toJson(stringList);
                String nameremove59 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove59);
                e.putString("key_name", nameremove59);
                e.apply();
//
                break;
            case R.id.iv_un_fav59:
                iv_un_fav59.setVisibility(View.GONE);
                iv_fav59.setVisibility(View.VISIBLE);
                stringList.remove("MCO");
                nameList.remove("Monaco");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove60 = gson.toJson(stringList);
                String nameremove60 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove60);
                e.putString("key_name", nameremove60);
                e.apply();
//
                break;
            case R.id.iv_un_fav60:
                iv_un_fav60.setVisibility(View.GONE);
                iv_fav60.setVisibility(View.VISIBLE);
                stringList.remove("ARN");
                nameList.remove("Aeron");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove61 = gson.toJson(stringList);
                String nameremove61 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove61);
                e.putString("key_name", nameremove61);
                e.apply();
//
                break;
            case R.id.iv_un_fav61:
                iv_un_fav61.setVisibility(View.GONE);
                iv_fav61.setVisibility(View.VISIBLE);
                stringList.remove("MTN*");
                nameList.remove("Medicalchain");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove62 = gson.toJson(stringList);
                String nameremove62 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove62);
                e.putString("key_name", nameremove62);
                e.apply();
//
                break;
            case R.id.iv_un_fav62:
                iv_un_fav62.setVisibility(View.GONE);
                iv_fav62.setVisibility(View.VISIBLE);
                stringList.remove("BRD");
                nameList.remove("Bread token");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove63 = gson.toJson(stringList);
                String nameremove63 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove63);
                e.putString("key_name", nameremove63);
                e.apply();
//
                break;
            case R.id.iv_un_fav63:
                iv_un_fav63.setVisibility(View.GONE);
                iv_fav63.setVisibility(View.VISIBLE);
                stringList.remove("SAN");
                nameList.remove("Santiment");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove64 = gson.toJson(stringList);
                String nameremove64 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove64);
                e.putString("key_name", nameremove64);
                e.apply();
//
                break;
            case R.id.iv_un_fav64:
                iv_un_fav64.setVisibility(View.GONE);
                iv_fav64.setVisibility(View.VISIBLE);
                stringList.remove("NAS");
                nameList.remove("Nebulas");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove65 = gson.toJson(stringList);
                String nameremove65 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove65);
                e.putString("key_name", nameremove65);
                e.apply();
//
                break;
            case R.id.iv_un_fav65:
                iv_un_fav65.setVisibility(View.GONE);
                iv_fav65.setVisibility(View.VISIBLE);
                stringList.remove("GVT");
                nameList.remove("Genesis Vision");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove66 = gson.toJson(stringList);
                String nameremove66 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove66);
                e.putString("key_name", nameremove66);
                e.apply();
//
                break;
            case R.id.iv_un_fav66:
                iv_un_fav66.setVisibility(View.GONE);
                iv_fav66.setVisibility(View.VISIBLE);
                stringList.remove("QUN");
                nameList.remove("QunQun");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove67 = gson.toJson(stringList);
                String nameremove67 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove67);
                e.putString("key_name", nameremove67);
                e.apply();
//
                break;
            case R.id.iv_un_fav67:
                iv_un_fav67.setVisibility(View.GONE);
                iv_fav67.setVisibility(View.VISIBLE);
                stringList.remove("WTC");
                nameList.remove("Waltonchain");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove68 = gson.toJson(stringList);
                String nameremove68 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove68);
                e.putString("key_name", nameremove68);
                e.apply();
//
                break;
            case R.id.iv_un_fav68:
                iv_un_fav68.setVisibility(View.GONE);
                iv_fav68.setVisibility(View.VISIBLE);
                stringList.remove("FCT");
                nameList.remove("Factoids");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove69 = gson.toJson(stringList);
                String nameremove69 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove69);
                e.putString("key_name", nameremove69);
                e.apply();
//
                break;
            case R.id.iv_un_fav69:
                iv_un_fav69.setVisibility(View.GONE);
                iv_fav69.setVisibility(View.VISIBLE);
                stringList.remove("CDT");
                nameList.remove("CoinDash");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove70 = gson.toJson(stringList);
                String nameremove70 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove70);
                e.putString("key_name", nameremove70);
                e.apply();
//
                break;
            case R.id.iv_un_fav70:
                iv_un_fav70.setVisibility(View.GONE);
                iv_fav70.setVisibility(View.VISIBLE);
                stringList.remove("RCN");
                nameList.remove("Ripio");
                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonTextRemove71 = gson.toJson(stringList);
                String nameremove71 = gson.toJson(nameList);
                e.putString("key", jsonTextRemove71);
                e.putString("key_name", nameremove71);
                e.apply();
//
                break;


            case R.id.iv_fav:
                stringList.clear();
                iv_un_fav.setVisibility(View.VISIBLE);
                iv_fav.setVisibility(View.GONE);
                symbol = "BTC";
                stringList.add(symbol);
                nameList.add("Bitcoin");

                for (String s : stringList) {
                    words += s + ",";
                }
                //Set the values
                String jsonText1 = gson.toJson(stringList);
                String name1 = gson.toJson(nameList);
                e.putString("key", jsonText1);
                e.putString("key_name", name1);
                e.apply();

                break;
            case R.id.iv_fav1:
                iv_un_fav1.setVisibility(View.VISIBLE);
                iv_fav1.setVisibility(View.GONE);
                symbol = "ETH";
                stringList.add(symbol);
                nameList.add("Ethereum");

                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText2 = gson.toJson(stringList);
                String name2 = gson.toJson(nameList);
                e.putString("key", jsonText2);
                e.putString("key_name", name2);
                e.apply();

                break;
            case R.id.iv_fav2:
                iv_un_fav2.setVisibility(View.VISIBLE);
                iv_fav2.setVisibility(View.GONE);
                symbol = "BCH";
                stringList.add(symbol);
                nameList.add("Bitcoin Cash");

                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText3 = gson.toJson(stringList);
                String name3 = gson.toJson(nameList);
                e.putString("key", jsonText3);
                e.putString("key_name", name3);
                e.apply();

                break;
            case R.id.iv_fav3:
                iv_un_fav3.setVisibility(View.VISIBLE);
                iv_fav3.setVisibility(View.GONE);
                symbol = "XRP";
                stringList.add(symbol);
                nameList.add("Ripple");

                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText4 = gson.toJson(stringList);
                String name4 = gson.toJson(nameList);
                e.putString("key", jsonText4);
                e.putString("key_name", name4);
                e.apply();

                break;
            case R.id.iv_fav4:
                iv_un_fav4.setVisibility(View.VISIBLE);
                iv_fav4.setVisibility(View.GONE);
                symbol = "LTC";
                stringList.add(symbol);
                nameList.add("Litecoin");

                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText5 = gson.toJson(stringList);
                String name5 = gson.toJson(nameList);
                e.putString("key", jsonText5);
                e.putString("key_name", name5);
                e.apply();
                break;
            case R.id.iv_fav5:
                iv_un_fav5.setVisibility(View.VISIBLE);
                iv_fav5.setVisibility(View.GONE);
                symbol = "ADA";
                stringList.add(symbol);
                nameList.add("Cardano");

                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText6 = gson.toJson(stringList);
                String name6 = gson.toJson(nameList);
                e.putString("key", jsonText6);
                e.putString("key_name", name6);
                e.apply();

                break;
            case R.id.iv_fav6:
                iv_un_fav6.setVisibility(View.VISIBLE);
                iv_fav6.setVisibility(View.GONE);
                symbol = "IOT";
                stringList.add(symbol);
                nameList.add("IOTA");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText7 = gson.toJson(stringList);
                String name7 = gson.toJson(nameList);
                e.putString("key", jsonText7);
                e.putString("key_name", name7);
                e.apply();
                break;
            case R.id.iv_fav7:
                iv_un_fav7.setVisibility(View.VISIBLE);
                iv_fav7.setVisibility(View.GONE);
                symbol = "DASH";
                stringList.add(symbol);
                nameList.add("DigitalCash");

                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText8 = gson.toJson(stringList);
                String name8 = gson.toJson(nameList);
                e.putString("key", jsonText8);
                e.putString("key_name", name8);
                e.apply();

                break;
            case R.id.iv_fav8:
                iv_un_fav8.setVisibility(View.VISIBLE);
                iv_fav8.setVisibility(View.GONE);
                symbol = "XEM";
                stringList.add(symbol);
                nameList.add("NEM");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText9 = gson.toJson(stringList);
                String name9 = gson.toJson(nameList);
                e.putString("key", jsonText9);
                e.putString("key_name", name9);
                e.apply();

                break;
            case R.id.iv_fav9:
                iv_un_fav9.setVisibility(View.VISIBLE);
                iv_fav9.setVisibility(View.GONE);
                symbol = "XMR";
                stringList.add(symbol);
                nameList.add("Monero");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText10 = gson.toJson(stringList);
                String name10 = gson.toJson(nameList);
                e.putString("key", jsonText10);
                e.putString("key_name", name10);
                e.apply();
                break;
            case R.id.iv_fav10:
                iv_un_fav10.setVisibility(View.VISIBLE);
                iv_fav10.setVisibility(View.GONE);
                symbol = "EOS";
                stringList.add(symbol);
                nameList.add("EOS");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText11 = gson.toJson(stringList);
                String name11 = gson.toJson(nameList);
                e.putString("key", jsonText11);
                e.putString("key_name", name11);
                e.apply();
                break;
            case R.id.iv_fav11:
                iv_un_fav11.setVisibility(View.VISIBLE);
                iv_fav11.setVisibility(View.GONE);
                symbol = "BTG";
                stringList.add(symbol);
                nameList.add("Bitcoin Gold");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText12 = gson.toJson(stringList);
                String name12 = gson.toJson(nameList);
                e.putString("key", jsonText12);
                e.putString("key_name", name12);
                e.apply();

                break;
            case R.id.iv_fav12:
                iv_un_fav12.setVisibility(View.VISIBLE);
                iv_fav12.setVisibility(View.GONE);
                symbol = "QTUM";
                stringList.add(symbol);
                nameList.add("Qtum");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText13 = gson.toJson(stringList);
                String name13 = gson.toJson(nameList);
                e.putString("key", jsonText13);
                e.putString("key_name", name13);
                e.apply();
                break;
            case R.id.iv_fav13:
                iv_un_fav13.setVisibility(View.VISIBLE);
                iv_fav13.setVisibility(View.GONE);
                symbol = "XLM";
                stringList.add(symbol);
                nameList.add("Stellar");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText14 = gson.toJson(stringList);
                String name14 = gson.toJson(nameList);
                e.putString("key", jsonText14);
                e.putString("key_name", name14);
                e.apply();
                break;
            case R.id.iv_fav14:
                iv_un_fav14.setVisibility(View.VISIBLE);
                iv_fav14.setVisibility(View.GONE);
                symbol = "NEO";
                stringList.add(symbol);
                nameList.add("NEO");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText15 = gson.toJson(stringList);
                String name15 = gson.toJson(nameList);
                e.putString("key", jsonText15);
                e.putString("key_name", name15);
                e.apply();
                break;
            case R.id.iv_fav15:
                iv_un_fav15.setVisibility(View.VISIBLE);
                iv_fav15.setVisibility(View.GONE);
                symbol = "ETC";
                stringList.add(symbol);
                nameList.add("EthereumClas.");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText16 = gson.toJson(stringList);
                String name16 = gson.toJson(nameList);
                e.putString("key", jsonText16);
                e.putString("key_name", name16);
                e.apply();
                break;
            case R.id.iv_fav16:
                iv_un_fav16.setVisibility(View.VISIBLE);
                iv_fav16.setVisibility(View.GONE);
                symbol = "ZEC";
                stringList.add(symbol);
                nameList.add("Zcash");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText17 = gson.toJson(stringList);
                String name17 = gson.toJson(nameList);
                e.putString("key", jsonText17);
                e.putString("key_name", name17);
                e.apply();
                break;
            case R.id.iv_fav17:
                iv_un_fav17.setVisibility(View.VISIBLE);
                iv_fav17.setVisibility(View.GONE);
                symbol = "STEEM";
                stringList.add(symbol);
                nameList.add("Steem");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText18 = gson.toJson(stringList);
                String name18 = gson.toJson(nameList);
                e.putString("key", jsonText18);
                e.putString("key_name", name18);
                e.apply();
                break;
            case R.id.iv_fav18:
                iv_un_fav18.setVisibility(View.VISIBLE);
                iv_fav18.setVisibility(View.GONE);
                symbol = "XZC";
                stringList.add(symbol);
                nameList.add("ZCoin");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText19 = gson.toJson(stringList);
                String name19 = gson.toJson(nameList);
                e.putString("key", jsonText19);
                e.putString("key_name", name19);
                e.apply();
                break;
            case R.id.iv_fav19:
                iv_un_fav19.setVisibility(View.VISIBLE);
                iv_fav19.setVisibility(View.GONE);
                symbol = "STORJ";
                stringList.add(symbol);
                nameList.add("Storj");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText20 = gson.toJson(stringList);
                String name20 = gson.toJson(nameList);
                e.putString("key", jsonText20);
                e.putString("key_name", name20);
                e.apply();
                break;
            case R.id.iv_fav20:
                iv_un_fav20.setVisibility(View.VISIBLE);
                iv_fav20.setVisibility(View.GONE);
                symbol = "AION";
                stringList.add(symbol);
                nameList.add("Aion");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText21 = gson.toJson(stringList);
                String name21 = gson.toJson(nameList);
                e.putString("key", jsonText21);
                e.putString("key_name", name21);
                e.apply();
                break;
            case R.id.iv_fav21:
                iv_un_fav21.setVisibility(View.VISIBLE);
                iv_fav21.setVisibility(View.GONE);
                symbol = "HT";
                stringList.add(symbol);
                nameList.add("Huobi Token");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText22 = gson.toJson(stringList);
                String name22 = gson.toJson(nameList);
                e.putString("key", jsonText22);
                e.putString("key_name", name22);
                e.apply();                break;
            case R.id.iv_fav22:
                iv_un_fav22.setVisibility(View.VISIBLE);
                iv_fav22.setVisibility(View.GONE);
                symbol = "TRX";
                stringList.add(symbol);
                nameList.add("Tronix");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText23 = gson.toJson(stringList);
                String name23 = gson.toJson(nameList);
                e.putString("key", jsonText23);
                e.putString("key_name", name23);
                e.apply();
                break;
            case R.id.iv_fav23:
                iv_un_fav23.setVisibility(View.VISIBLE);
                iv_fav23.setVisibility(View.GONE);
                symbol = "XRB";
                stringList.add(symbol);
                nameList.add("Nano");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText24 = gson.toJson(stringList);
                String name24 = gson.toJson(nameList);
                e.putString("key", jsonText24);
                e.putString("key_name", name24);
                e.apply();
                break;
            case R.id.iv_fav24:
                iv_un_fav24.setVisibility(View.VISIBLE);
                iv_fav24.setVisibility(View.GONE);
                symbol = "OMG";
                stringList.add(symbol);
                nameList.add("OmiseGo");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText25 = gson.toJson(stringList);
                String name25 = gson.toJson(nameList);
                e.putString("key", jsonText25);
                e.putString("key_name", name25);
                e.apply();
                break;
            case R.id.iv_fav25:
                iv_un_fav25.setVisibility(View.VISIBLE);
                iv_fav25.setVisibility(View.GONE);
                symbol = "ELA";
                stringList.add(symbol);
                nameList.add("Elastos");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText26 = gson.toJson(stringList);
                String name26 = gson.toJson(nameList);
                e.putString("key", jsonText26);
                e.putString("key_name", name26);
                e.apply();
                break;
            case R.id.iv_fav26:
                iv_un_fav26.setVisibility(View.VISIBLE);
                iv_fav26.setVisibility(View.GONE);
                symbol = "BNB";
                stringList.add(symbol);
                nameList.add("Binance Coin");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText27 = gson.toJson(stringList);
                String name27 = gson.toJson(nameList);
                e.putString("key", jsonText27);
                e.putString("key_name", name27);
                e.apply();
                break;
            case R.id.iv_fav27:
                iv_un_fav27.setVisibility(View.VISIBLE);
                iv_fav27.setVisibility(View.GONE);
                symbol = "VEN";
                stringList.add(symbol);
                nameList.add("Vechain");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText28 = gson.toJson(stringList);
                String name28 = gson.toJson(nameList);
                e.putString("key", jsonText28);
                e.putString("key_name", name28);
                e.apply();
                break;
            case R.id.iv_fav28:
                iv_un_fav28.setVisibility(View.VISIBLE);
                iv_fav28.setVisibility(View.GONE);
                symbol = "ZCL";
                stringList.add(symbol);
                nameList.add("ZClassic");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText29 = gson.toJson(stringList);
                String name29 = gson.toJson(nameList);
                e.putString("key", jsonText29);
                e.putString("key_name", name29);
                e.apply();
                break;
            case R.id.iv_fav29:
                iv_un_fav29.setVisibility(View.VISIBLE);
                iv_fav29.setVisibility(View.GONE);
                symbol = "DGD";
                stringList.add(symbol);
                nameList.add("Digix DAO");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText30 = gson.toJson(stringList);
                String name30 = gson.toJson(nameList);
                e.putString("key", jsonText30);
                e.putString("key_name", name30);
                e.apply();
                break;
            case R.id.iv_fav30:
                iv_un_fav30.setVisibility(View.VISIBLE);
                iv_fav30.setVisibility(View.GONE);
                symbol = "OCN";
                stringList.add(symbol);
                nameList.add("Odyssey");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText31 = gson.toJson(stringList);
                String name31= gson.toJson(nameList);
                e.putString("key", jsonText31);
                e.putString("key_name", name31);
                e.apply();
                break;
            case R.id.iv_fav31:
                iv_un_fav31.setVisibility(View.VISIBLE);
                iv_fav31.setVisibility(View.GONE);
                symbol = "BCPT";
                stringList.add(symbol);
                nameList.add("BlockMason Credit P.");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText32 = gson.toJson(stringList);
                String name32= gson.toJson(nameList);
                e.putString("key", jsonText32);
                e.putString("key_name", name32);
                e.apply();
                break;
            case R.id.iv_fav32:
                iv_un_fav32.setVisibility(View.VISIBLE);
                iv_fav32.setVisibility(View.GONE);
                symbol = "LUN";
                stringList.add(symbol);
                nameList.add("Lunyr");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText33 = gson.toJson(stringList);
                String name33= gson.toJson(nameList);
                e.putString("key", jsonText33);
                e.putString("key_name", name33);
                e.apply();
                break;
            case R.id.iv_fav33:
                iv_un_fav33.setVisibility(View.VISIBLE);
                iv_fav33.setVisibility(View.GONE);
                symbol = "IOST";
                stringList.add(symbol);
                nameList.add("IOS token");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText34 = gson.toJson(stringList);
                String name34= gson.toJson(nameList);
                e.putString("key", jsonText34);
                e.putString("key_name", name34);
                e.apply();
                break;
            case R.id.iv_fav34:
                iv_un_fav34.setVisibility(View.VISIBLE);
                iv_fav34.setVisibility(View.GONE);
                symbol = "HSR";
                stringList.add(symbol);
                nameList.add("Hshare");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText35 = gson.toJson(stringList);
                String name35= gson.toJson(nameList);
                e.putString("key", jsonText35);
                e.putString("key_name", name35);
                e.apply();
                break;
            case R.id.iv_fav35:
                iv_un_fav35.setVisibility(View.VISIBLE);
                iv_fav35.setVisibility(View.GONE);
                symbol = "ICX";
                stringList.add(symbol);
                nameList.add("ICON Project");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText36 = gson.toJson(stringList);
                String name36= gson.toJson(nameList);
                e.putString("key", jsonText36);
                e.putString("key_name", name36);
                e.apply();
                break;
            case R.id.iv_fav36:
                iv_un_fav36.setVisibility(View.VISIBLE);
                iv_fav36.setVisibility(View.GONE);
                symbol = "LSK";
                stringList.add(symbol);
                nameList.add("Lisk");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText37 = gson.toJson(stringList);
                String name37= gson.toJson(nameList);
                e.putString("key", jsonText37);
                e.putString("key_name", name37);
                e.apply();
                break;
            case R.id.iv_fav37:
                iv_un_fav37.setVisibility(View.VISIBLE);
                iv_fav37.setVisibility(View.GONE);
                symbol = "NEBL";
                stringList.add(symbol);
                nameList.add("Neblio");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText38 = gson.toJson(stringList);
                String name38= gson.toJson(nameList);
                e.putString("key", jsonText38);
                e.putString("key_name", name38);
                e.apply();
                break;
            case R.id.iv_fav38:
                iv_un_fav38.setVisibility(View.VISIBLE);
                iv_fav38.setVisibility(View.GONE);
                symbol = "WAVES";
                stringList.add(symbol);
                nameList.add("Waves");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText39 = gson.toJson(stringList);
                String name39= gson.toJson(nameList);
                e.putString("key", jsonText39);
                e.putString("key_name", name39);
                e.apply();
                break;
            case R.id.iv_fav39:
                iv_un_fav39.setVisibility(View.VISIBLE);
                iv_fav39.setVisibility(View.GONE);
                symbol = "BLZ";
                stringList.add(symbol);
                nameList.add("Bluzelle");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText40 = gson.toJson(stringList);
                String name40= gson.toJson(nameList);
                e.putString("key", jsonText40);
                e.putString("key_name", name40);
                e.apply();
                break;
            case R.id.iv_fav40:
                iv_un_fav40.setVisibility(View.VISIBLE);
                iv_fav40.setVisibility(View.GONE);
                symbol = "INK";
                stringList.add(symbol);
                nameList.add("Ink");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText41 = gson.toJson(stringList);
                String name41= gson.toJson(nameList);
                e.putString("key", jsonText41);
                e.putString("key_name", name41);
                e.apply();
                break;
            case R.id.iv_fav41:
                iv_un_fav41.setVisibility(View.VISIBLE);
                iv_fav41.setVisibility(View.GONE);
                symbol = "ADX";
                stringList.add(symbol);
                nameList.add("AdEx");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText42 = gson.toJson(stringList);
                String name42= gson.toJson(nameList);
                e.putString("key", jsonText42);
                e.putString("key_name", name42);
                e.apply();
                break;
            case R.id.iv_fav42:
                iv_un_fav42.setVisibility(View.VISIBLE);
                iv_fav42.setVisibility(View.GONE);
                symbol = "XVG";
                stringList.add(symbol);
                nameList.add("Verge");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText43 = gson.toJson(stringList);
                String name43= gson.toJson(nameList);
                e.putString("key", jsonText43);
                e.putString("key_name", name43);
                e.apply();
                break;
            case R.id.iv_fav43:
                iv_un_fav43.setVisibility(View.VISIBLE);
                iv_fav43.setVisibility(View.GONE);
                symbol = "MTL";
                stringList.add(symbol);
                nameList.add("Metal");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText44 = gson.toJson(stringList);
                String name44= gson.toJson(nameList);
                e.putString("key", jsonText44);
                e.putString("key_name", name44);
                e.apply();
                break;
            case R.id.iv_fav44:
                iv_un_fav44.setVisibility(View.VISIBLE);
                iv_fav44.setVisibility(View.GONE);
                symbol = "SRN";
                stringList.add(symbol);
                nameList.add("SirinLabs");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText45 = gson.toJson(stringList);
                String name45= gson.toJson(nameList);
                e.putString("key", jsonText45);
                e.putString("key_name", name45);
                e.apply();
                break;
            case R.id.iv_fav45:
                iv_un_fav45.setVisibility(View.VISIBLE);
                iv_fav45.setVisibility(View.GONE);
                symbol = "ZRX";
                stringList.add(symbol);
                nameList.add("0x");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText46 = gson.toJson(stringList);
                String name46= gson.toJson(nameList);
                e.putString("key", jsonText46);
                e.putString("key_name", name46);
                e.apply();
                break;
            case R.id.iv_fav46:
                iv_un_fav46.setVisibility(View.VISIBLE);
                iv_fav46.setVisibility(View.GONE);
                symbol = "RLC";
                stringList.add(symbol);
                nameList.add("iEx.ec");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText47 = gson.toJson(stringList);
                String name47= gson.toJson(nameList);
                e.putString("key", jsonText47);
                e.putString("key_name", name47);
                e.apply();
                break;
            case R.id.iv_fav47:
                iv_un_fav47.setVisibility(View.VISIBLE);
                iv_fav47.setVisibility(View.GONE);
                symbol = "THETA";
                stringList.add(symbol);
                nameList.add("Theta");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText48 = gson.toJson(stringList);
                String name48= gson.toJson(nameList);
                e.putString("key", jsonText48);
                e.putString("key_name", name48);
                e.apply();
                break;
            case R.id.iv_fav48:
                iv_un_fav48.setVisibility(View.VISIBLE);
                iv_fav48.setVisibility(View.GONE);
                symbol = "BCD";
                stringList.add(symbol);
                nameList.add("Bitcoin Diamond");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText49 = gson.toJson(stringList);
                String name49= gson.toJson(nameList);
                e.putString("key", jsonText49);
                e.putString("key_name", name49);
                e.apply();
                break;
            case R.id.iv_fav49:
                iv_un_fav49.setVisibility(View.VISIBLE);
                iv_fav49.setVisibility(View.GONE);
                symbol = "OAX";
                stringList.add(symbol);
                nameList.add("OpenANX");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText50 = gson.toJson(stringList);
                String name50= gson.toJson(nameList);
                e.putString("key", jsonText50);
                e.putString("key_name", name50);
                e.apply();
                break;
            case R.id.iv_fav50:
                iv_un_fav50.setVisibility(View.VISIBLE);
                iv_fav50.setVisibility(View.GONE);
                symbol = "ELF";
                stringList.add(symbol);
                nameList.add("aelf");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText51 = gson.toJson(stringList);
                String name51= gson.toJson(nameList);
                e.putString("key", jsonText51);
                e.putString("key_name", name51);
                e.apply();
                break;
            case R.id.iv_fav51:
                iv_un_fav51.setVisibility(View.VISIBLE);
                iv_fav51.setVisibility(View.GONE);
                symbol = "INS";
                stringList.add(symbol);
                nameList.add("INS Ecosystem");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText52 = gson.toJson(stringList);
                String name52= gson.toJson(nameList);
                e.putString("key", jsonText52);
                e.putString("key_name", name52);
                e.apply();
                break;
            case R.id.iv_fav52:
                iv_un_fav52.setVisibility(View.VISIBLE);
                iv_fav52.setVisibility(View.GONE);
                symbol = "ZIL";
                stringList.add(symbol);
                nameList.add("Zilliqa");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText53 = gson.toJson(stringList);
                String name53= gson.toJson(nameList);
                e.putString("key", jsonText53);
                e.putString("key_name", name53);
                e.apply();
                break;
            case R.id.iv_fav53:
                iv_un_fav53.setVisibility(View.VISIBLE);
                iv_fav53.setVisibility(View.GONE);
                symbol = "AE";
                stringList.add(symbol);
                nameList.add("Aeternity");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText54 = gson.toJson(stringList);
                String name54= gson.toJson(nameList);
                e.putString("key", jsonText54);
                e.putString("key_name", name54);
                e.apply();
                break;
            case R.id.iv_fav54:
                iv_un_fav54.setVisibility(View.VISIBLE);
                iv_fav54.setVisibility(View.GONE);
                symbol = "PRO";
                stringList.add(symbol);
                nameList.add("Propy");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText55 = gson.toJson(stringList);
                String name55= gson.toJson(nameList);
                e.putString("key", jsonText55);
                e.putString("key_name", name55);
                e.apply();
                break;
            case R.id.iv_fav55:
                iv_un_fav55.setVisibility(View.VISIBLE);
                iv_fav55.setVisibility(View.GONE);
                symbol = "DOGE";
                stringList.add(symbol);
                nameList.add("Dogecoin");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText56 = gson.toJson(stringList);
                String name56= gson.toJson(nameList);
                e.putString("key", jsonText56);
                e.putString("key_name", name56);
                e.apply();
                break;
            case R.id.iv_fav56:
                iv_un_fav56.setVisibility(View.VISIBLE);
                iv_fav56.setVisibility(View.GONE);
                symbol = "ITC";
                stringList.add(symbol);
                nameList.add("IoT Chain");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText57 = gson.toJson(stringList);
                String name57= gson.toJson(nameList);
                e.putString("key", jsonText57);
                e.putString("key_name", name57);
                e.apply();
                break;
            case R.id.iv_fav57:
                iv_un_fav57.setVisibility(View.VISIBLE);
                iv_fav57.setVisibility(View.GONE);
                symbol = "RDD";
                stringList.add(symbol);
                nameList.add("ReddCoin");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText58 = gson.toJson(stringList);
                String name58= gson.toJson(nameList);
                e.putString("key", jsonText58);
                e.putString("key_name", name58);
                e.apply();
                break;
            case R.id.iv_fav58:
                iv_un_fav58.setVisibility(View.VISIBLE);
                iv_fav58.setVisibility(View.GONE);
                symbol = "SWFTC";
                stringList.add(symbol);
                nameList.add("SwftCoin");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText59 = gson.toJson(stringList);
                String name59= gson.toJson(nameList);
                e.putString("key", jsonText59);
                e.putString("key_name", name59);
                e.apply();
                break;
            case R.id.iv_fav59:
                iv_un_fav59.setVisibility(View.VISIBLE);
                iv_fav59.setVisibility(View.GONE);
                symbol = "MCO";
                stringList.add(symbol);
                nameList.add("Monaco");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText60 = gson.toJson(stringList);
                String name60= gson.toJson(nameList);
                e.putString("key", jsonText60);
                e.putString("key_name", name60);
                e.apply();
                break;
            case R.id.iv_fav60:
                iv_un_fav60.setVisibility(View.VISIBLE);
                iv_fav60.setVisibility(View.GONE);
                symbol = "ARN";
                stringList.add(symbol);
                nameList.add("Aeron");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText61 = gson.toJson(stringList);
                String name61= gson.toJson(nameList);
                e.putString("key", jsonText61);
                e.putString("key_name", name61);
                e.apply();
                break;
            case R.id.iv_fav61:
                iv_un_fav61.setVisibility(View.VISIBLE);
                iv_fav61.setVisibility(View.GONE);
                symbol = "MTN*";
                stringList.add(symbol);
                nameList.add("Medicalchain");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText62 = gson.toJson(stringList);
                String name62= gson.toJson(nameList);
                e.putString("key", jsonText62);
                e.putString("key_name", name62);
                e.apply();
                break;
            case R.id.iv_fav62:
                iv_un_fav62.setVisibility(View.VISIBLE);
                iv_fav62.setVisibility(View.GONE);
                symbol = "BRD";
                stringList.add(symbol);
                nameList.add("Bread token");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText63 = gson.toJson(stringList);
                String name63= gson.toJson(nameList);
                e.putString("key", jsonText63);
                e.putString("key_name", name63);
                e.apply();
                break;
            case R.id.iv_fav63:
                iv_un_fav63.setVisibility(View.VISIBLE);
                iv_fav63.setVisibility(View.GONE);
                symbol = "SAN";
                stringList.add(symbol);
                nameList.add("Santiment");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText64 = gson.toJson(stringList);
                String name64= gson.toJson(nameList);
                e.putString("key", jsonText64);
                e.putString("key_name", name64);
                e.apply();
                break;
            case R.id.iv_fav64:
                iv_un_fav64.setVisibility(View.VISIBLE);
                iv_fav64.setVisibility(View.GONE);
                symbol = "NAS";
                stringList.add(symbol);
                nameList.add("Nebulas");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText65 = gson.toJson(stringList);
                String name65= gson.toJson(nameList);
                e.putString("key", jsonText65);
                e.putString("key_name", name65);
                e.apply();
                break;
            case R.id.iv_fav65:
                iv_un_fav65.setVisibility(View.VISIBLE);
                iv_fav65.setVisibility(View.GONE);
                symbol = "GVT";
                stringList.add(symbol);
                nameList.add("Genesis Vision");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText66 = gson.toJson(stringList);
                String name66= gson.toJson(nameList);
                e.putString("key", jsonText66);
                e.putString("key_name", name66);
                e.apply();
                break;
            case R.id.iv_fav66:
                iv_un_fav66.setVisibility(View.VISIBLE);
                iv_fav66.setVisibility(View.GONE);
                symbol = "QUN";
                stringList.add(symbol);
                nameList.add("QunQun");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText67 = gson.toJson(stringList);
                String name67= gson.toJson(nameList);
                e.putString("key", jsonText67);
                e.putString("key_name", name67);
                e.apply();
                break;
            case R.id.iv_fav67:
                iv_un_fav67.setVisibility(View.VISIBLE);
                iv_fav67.setVisibility(View.GONE);
                symbol = "WTC";
                stringList.add(symbol);
                nameList.add("Waltonchain");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText68 = gson.toJson(stringList);
                String name68= gson.toJson(nameList);
                e.putString("key", jsonText68);
                e.putString("key_name", name68);
                e.apply();
                break;
            case R.id.iv_fav68:
                iv_un_fav68.setVisibility(View.VISIBLE);
                iv_fav68.setVisibility(View.GONE);
                symbol = "ECT";
                stringList.add(symbol);
                nameList.add("Factoids");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText69 = gson.toJson(stringList);
                String name69= gson.toJson(nameList);
                e.putString("key", jsonText69);
                e.putString("key_name", name69);
                e.apply();
                break;
            case R.id.iv_fav69:
                iv_un_fav69.setVisibility(View.VISIBLE);
                iv_fav69.setVisibility(View.GONE);
                symbol = "CDT";
                nameList.add("CoinDash");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText70 = gson.toJson(stringList);
                String name70= gson.toJson(nameList);
                e.putString("key", jsonText70);
                e.putString("key_name", name70);
                e.apply();
                break;
            case R.id.iv_fav70:
                iv_un_fav70.setVisibility(View.VISIBLE);
                iv_fav70.setVisibility(View.GONE);
                symbol = "RCN";
                nameList.add("Ripio");
                for (String s : stringList) {
                    words += s + ",";
                }
                String jsonText71 = gson.toJson(stringList);
                String name71= gson.toJson(nameList);
                e.putString("key", jsonText71);
                e.putString("key_name", name71);
                e.apply();
                break;

        }
    }

    @Override
    public void onItemSelected(int pos) {

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
                ParsedResponse p = Service.apiGetCoin(Home_Activity.this, Baseurl);
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
                for (int i = 0; i < mArrayList.size(); i++) {
                    tv_btc_price.setText(mArrayList.get(0).PRICE);
                    tv_eth_price.setText(mArrayList.get(1).PRICE);
                    tv_bch_price.setText(mArrayList.get(2).PRICE);
                    tv_xrp_price.setText(mArrayList.get(3).PRICE);
                    tv_ltc_price.setText(mArrayList.get(4).PRICE);
                    tv_ada_price.setText(mArrayList.get(5).PRICE);
                    tv_iot_price.setText(mArrayList.get(6).PRICE);
                    tv_dash_price.setText(mArrayList.get(7).PRICE);
                    tv_xem_price.setText(mArrayList.get(8).PRICE);
                    tv_xmr_price.setText(mArrayList.get(9).PRICE);
                    tv_eos_price.setText(mArrayList.get(10).PRICE);
                    tv_btg_price.setText(mArrayList.get(11).PRICE);
                    tv_qtum_price.setText(mArrayList.get(12).PRICE);
                    tv_xlm_price.setText(mArrayList.get(13).PRICE);
                    tv_neo_price.setText(mArrayList.get(14).PRICE);
                    tv_etc_price.setText(mArrayList.get(15).PRICE);
                    tv_zec_price.setText(mArrayList.get(16).PRICE);
                    tv_steem_price.setText(mArrayList.get(17).PRICE);
                    tv_xzc_price.setText(mArrayList.get(18).PRICE);
                    tv_storj_price.setText(mArrayList.get(19).PRICE);
                    tv_aion_price.setText(mArrayList.get(20).PRICE);
                    tv_ht_price.setText(mArrayList.get(21).PRICE);
                    tv_tronix_price.setText(mArrayList.get(22).PRICE);
                    tv_nano_price.setText(mArrayList.get(23).PRICE);
                    tv_omisego_price.setText(mArrayList.get(24).PRICE);
                    tv_elastos_price.setText(mArrayList.get(25).PRICE);
                    tv_binance_price.setText(mArrayList.get(26).PRICE);
                    tv_vechain_price.setText(mArrayList.get(27).PRICE);
                    tv_zclassic_price.setText(mArrayList.get(28).PRICE);
                    tv_digix_price.setText(mArrayList.get(29).PRICE);
                    tv_odyssey_price.setText(mArrayList.get(30).PRICE);
                    tv_blockmason_price.setText(mArrayList.get(31).PRICE);
                    tv_lunyr_price.setText(mArrayList.get(32).PRICE);
                    tv_iostoken_price.setText(mArrayList.get(33).PRICE);
                    tv_hshare_price.setText(mArrayList.get(34).PRICE);
                    tv_iconproject_price.setText(mArrayList.get(35).PRICE);
                    tv_lisk_price.setText(mArrayList.get(36).PRICE);
                    tv_nebilo_price.setText(mArrayList.get(37).PRICE);
                    tv_wavess_price.setText(mArrayList.get(38).PRICE);
                    tv_bluzello_price.setText(mArrayList.get(39).PRICE);
                    tv_inkk_price.setText(mArrayList.get(40).PRICE);
                    tv_adex_price.setText(mArrayList.get(41).PRICE);
                    tv_verge_price.setText(mArrayList.get(42).PRICE);
                    tv_metal_price.setText(mArrayList.get(43).PRICE);
                    tv_sirinlabs_price.setText(mArrayList.get(44).PRICE);
                    tv_ox_price.setText(mArrayList.get(45).PRICE);
                    tv_iex_price.setText(mArrayList.get(46).PRICE);
                    tv_thetaa_price.setText(mArrayList.get(47).PRICE);
                    tv_bitcoindimand_price.setText(mArrayList.get(48).PRICE);
                    tv_openanx_price.setText(mArrayList.get(49).PRICE);
                    tv_aelf_price.setText(mArrayList.get(50).PRICE);
                    tv_insexosystem_price.setText(mArrayList.get(51).PRICE);
                    tv_zilliqa_price.setText(mArrayList.get(52).PRICE);
                    tv_aeternity_price.setText(mArrayList.get(53).PRICE);
                    tv_propy_price.setText(mArrayList.get(54).PRICE);
                    tv_dogecoin_price.setText(mArrayList.get(55).PRICE);
                    tv_iotchain_price.setText(mArrayList.get(56).PRICE);
                    tv_reddcoin_price.setText(mArrayList.get(57).PRICE);
                    tv_swftcoin_price.setText(mArrayList.get(58).PRICE);
                    tv_monaco_price.setText(mArrayList.get(59).PRICE);
                    tv_aeron_price.setText(mArrayList.get(60).PRICE);
                    tv_medicakchain_price.setText(mArrayList.get(61).PRICE);
                    tv_breadtoken_price.setText(mArrayList.get(62).PRICE);
                    tv_santiment_price.setText(mArrayList.get(63).PRICE);
                    tv_nebulas_price.setText(mArrayList.get(64).PRICE);
                    tv_genesis_price.setText(mArrayList.get(65).PRICE);
                    tv_qunqun_price.setText(mArrayList.get(66).PRICE);
                    tv_waltonchain_price.setText(mArrayList.get(67).PRICE);
                    tv_factoids_price.setText(mArrayList.get(68).PRICE);
                    tv_coinDash_price.setText(mArrayList.get(69).PRICE);
                    tv_ripio_price.setText(mArrayList.get(70).PRICE);

                    tv_btc_mc.setText("M.C:" + mArrayList.get(0).MKTCAP);
                    tv_eth_mc.setText("M.C:" + mArrayList.get(1).MKTCAP);
                    tv_bch_mc.setText("M.C:" + mArrayList.get(2).MKTCAP);
                    tv_xrp_mc.setText("M.C:" + mArrayList.get(3).MKTCAP);
                    tv_ltc_mc.setText("M.C:" + mArrayList.get(4).MKTCAP);
                    tv_ada_mc.setText("M.C:" + mArrayList.get(5).MKTCAP);
                    tv_iot_mc.setText("M.C:" + mArrayList.get(6).MKTCAP);
                    tv_dash_mc.setText("M.C:" + mArrayList.get(7).MKTCAP);
                    tv_xem_mc.setText("M.C:" + mArrayList.get(8).MKTCAP);
                    tv_xmr_mc.setText("M.C:" + mArrayList.get(9).MKTCAP);
                    tv_eos_mc.setText("M.C:" + mArrayList.get(10).MKTCAP);
                    tv_btg_mc.setText("M.C:" + mArrayList.get(11).MKTCAP);
                    tv_qtum_mc.setText("M.C:" + mArrayList.get(12).MKTCAP);
                    tv_xlm_mc.setText("M.C:" + mArrayList.get(13).MKTCAP);
                    tv_neo_mc.setText("M.C:" + mArrayList.get(14).MKTCAP);
                    tv_etc_mc.setText("M.C:" + mArrayList.get(15).MKTCAP);
                    tv_zec_mc.setText("M.C:" + mArrayList.get(16).MKTCAP);
                    tv_steem_mc.setText("M.C:" + mArrayList.get(17).MKTCAP);
                    tv_xzc_mc.setText("M.C:" + mArrayList.get(18).MKTCAP);
                    tv_storj_mc.setText("M.C:" + mArrayList.get(19).MKTCAP);
                    tv_aion_mc.setText("M.C:" + mArrayList.get(20).MKTCAP);
                    tv_ht_mc.setText("M.C:" + mArrayList.get(21).MKTCAP);
                    tv_tronix_mc.setText("M.C:" + mArrayList.get(22).MKTCAP);
                    tv_nano_mc.setText("M.C:" + mArrayList.get(23).MKTCAP);
                    tv_omisego_mc.setText("M.C:" + mArrayList.get(24).MKTCAP);
                    tv_elastos_mc.setText("M.C:" + mArrayList.get(25).MKTCAP);
                    tv_binance_mc.setText("M.C:" + mArrayList.get(26).MKTCAP);
                    tv_vechain_mc.setText("M.C:" + mArrayList.get(27).MKTCAP);
                    tv_zclassic_mc.setText("M.C:" + mArrayList.get(28).MKTCAP);
                    tv_digix_mc.setText("M.C:" + mArrayList.get(29).MKTCAP);
                    tv_odyssey_mc.setText("M.C:" + mArrayList.get(30).MKTCAP);
                    tv_blockmason_mc.setText("M.C:" + mArrayList.get(31).MKTCAP);
                    tv_lunyr_mc.setText("M.C:" + mArrayList.get(32).MKTCAP);
                    tv_iostoken_mc.setText("M.C:" + mArrayList.get(33).MKTCAP);
                    tv_hshare_mc.setText("M.C:" + mArrayList.get(34).MKTCAP);
                    tv_iconproject_mc.setText("M.C:" + mArrayList.get(35).MKTCAP);
                    tv_lisk_mc.setText("M.C:" + mArrayList.get(36).MKTCAP);
                    tv_nebilo_mc.setText("M.C:" + mArrayList.get(37).MKTCAP);
                    tv_wavess_mc.setText("M.C:" + mArrayList.get(38).MKTCAP);
                    tv_bluzello_mc.setText("M.C:" + mArrayList.get(39).MKTCAP);
                    tv_inkk_mc.setText("M.C:" + mArrayList.get(40).MKTCAP);
                    tv_adex_mc.setText("M.C:" + mArrayList.get(41).MKTCAP);
                    tv_verge_mc.setText("M.C:" + mArrayList.get(42).MKTCAP);
                    tv_metal_mc.setText("M.C:" + mArrayList.get(43).MKTCAP);
                    tv_sirinlabs_mc.setText("M.C:" + mArrayList.get(44).MKTCAP);
                    tv_ox_mc.setText("M.C:" + mArrayList.get(45).MKTCAP);
                    tv_iex_mc.setText("M.C:" + mArrayList.get(46).MKTCAP);
                    tv_thetaa_mc.setText("M.C:" + mArrayList.get(47).MKTCAP);
                    tv_bitcoindimand_mc.setText("M.C:" + mArrayList.get(48).MKTCAP);
                    tv_openanx_mc.setText("M.C:" + mArrayList.get(49).MKTCAP);
                    tv_aelf_mc.setText("M.C:" + mArrayList.get(50).MKTCAP);
                    tv_insexosystem_mc.setText("M.C:" + mArrayList.get(51).MKTCAP);
                    tv_zilliqa_mc.setText("M.C:" + mArrayList.get(52).MKTCAP);
                    tv_aeternity_mc.setText("M.C:" + mArrayList.get(53).MKTCAP);
                    tv_propy_mc.setText("M.C:" + mArrayList.get(54).MKTCAP);
                    tv_dogecoin_mc.setText("M.C:" + mArrayList.get(55).MKTCAP);
                    tv_iotchain_mc.setText("M.C:" + mArrayList.get(56).MKTCAP);
                    tv_reddcoin_mc.setText("M.C:" + mArrayList.get(57).MKTCAP);
                    tv_swftcoin_mc.setText("M.C:" + mArrayList.get(58).MKTCAP);
                    tv_monaco_mc.setText("M.C:" + mArrayList.get(59).MKTCAP);
                    tv_aeron_mc.setText("M.C:" + mArrayList.get(60).MKTCAP);
                    tv_medicakchain_mc.setText("M.C:" + mArrayList.get(61).MKTCAP);
                    tv_breadtoken_mc.setText("M.C:" + mArrayList.get(62).MKTCAP);
                    tv_santiment_mc.setText("M.C:" + mArrayList.get(63).MKTCAP);
                    tv_nebulas_mc.setText("M.C:" + mArrayList.get(64).MKTCAP);
                    tv_genesis_mc.setText("M.C:" + mArrayList.get(65).MKTCAP);
                    tv_qunqun_mc.setText("M.C:" + mArrayList.get(66).MKTCAP);
                    tv_waltonchain_mc.setText("M.C:" + mArrayList.get(67).MKTCAP);
                    tv_factoids_mc.setText("M.C:" + mArrayList.get(68).MKTCAP);
                    tv_coinDash_mc.setText("M.C:" + mArrayList.get(69).MKTCAP);
                    tv_ripio_mc.setText("M.C:" + mArrayList.get(70).MKTCAP);



                    if (mArrayList.get(0).CHANGEPCT24HOUR.contains("-")) {
                        tv_btc_pct.setText(mArrayList.get(0).CHANGE24HOUR + " (" + mArrayList.get(0).CHANGEPCT24HOUR + "%)");
                        tv_btc_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_btc_pct.setText(mArrayList.get(0).CHANGE24HOUR + " (" + mArrayList.get(0).CHANGEPCT24HOUR + "%)");
                        tv_btc_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(1).CHANGEPCT24HOUR.contains("-")) {
                        tv_eth_pct.setText(mArrayList.get(1).CHANGE24HOUR + " (" + mArrayList.get(1).CHANGEPCT24HOUR + "%)");
                        tv_eth_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_eth_pct.setText(mArrayList.get(1).CHANGE24HOUR + " (" + mArrayList.get(1).CHANGEPCT24HOUR + "%)");
                        tv_eth_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(2).CHANGEPCT24HOUR.contains("-")) {
                        tv_bch_pct.setText(mArrayList.get(2).CHANGE24HOUR + " (" + mArrayList.get(2).CHANGEPCT24HOUR + "%)");
                        tv_bch_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_bch_pct.setText(mArrayList.get(2).CHANGE24HOUR + " (" + mArrayList.get(2).CHANGEPCT24HOUR + "%)");
                        tv_bch_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(3).CHANGEPCT24HOUR.contains("-")) {
                        tv_xrp_pct.setText(mArrayList.get(3).CHANGE24HOUR + " (" + mArrayList.get(3).CHANGEPCT24HOUR + "%)");
                        tv_xrp_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xrp_pct.setText(mArrayList.get(3).CHANGE24HOUR + "(" + mArrayList.get(3).CHANGEPCT24HOUR + "%)");
                        tv_xrp_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(4).CHANGEPCT24HOUR.contains("-")) {
                        tv_ltc_pct.setText(mArrayList.get(4).CHANGE24HOUR + " (" + mArrayList.get(4).CHANGEPCT24HOUR + "%)");
                        tv_ltc_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ltc_pct.setText(mArrayList.get(4).CHANGE24HOUR + " (" + mArrayList.get(4).CHANGEPCT24HOUR + "%)");
                        tv_ltc_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(5).CHANGEPCT24HOUR.contains("-")) {
                        tv_ada_pct.setText(mArrayList.get(5).CHANGE24HOUR + " (" + mArrayList.get(5).CHANGEPCT24HOUR + "%)");
                        tv_ada_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ada_pct.setText(mArrayList.get(5).CHANGE24HOUR + " (" + mArrayList.get(5).CHANGEPCT24HOUR + "%)");
                        tv_ada_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(6).CHANGEPCT24HOUR.contains("-")) {
                        tv_iot_pct.setText(mArrayList.get(6).CHANGE24HOUR + " (" + mArrayList.get(6).CHANGEPCT24HOUR + "%)");
                        tv_iot_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iot_pct.setText(mArrayList.get(6).CHANGE24HOUR + " (" + mArrayList.get(6).CHANGEPCT24HOUR + "%)");
                        tv_iot_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(7).CHANGEPCT24HOUR.contains("-")) {
                        tv_dash_pct.setText(mArrayList.get(7).CHANGE24HOUR + " (" + mArrayList.get(7).CHANGEPCT24HOUR + "%)");
                        tv_dash_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_dash_pct.setText(mArrayList.get(7).CHANGE24HOUR + " (" + mArrayList.get(7).CHANGEPCT24HOUR + "%)");
                        tv_dash_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(8).CHANGEPCT24HOUR.contains("-")) {
                        tv_xem_pct.setText(mArrayList.get(8).CHANGE24HOUR + " (" + mArrayList.get(8).CHANGEPCT24HOUR + "%)");
                        tv_xem_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xem_pct.setText(mArrayList.get(8).CHANGE24HOUR + " (" + mArrayList.get(8).CHANGEPCT24HOUR + "%)");
                        tv_xem_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(9).CHANGEPCT24HOUR.contains("-")) {
                        tv_xmr_pct.setText(mArrayList.get(9).CHANGE24HOUR + " (" + mArrayList.get(9).CHANGEPCT24HOUR + "%)");
                        tv_xmr_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xmr_pct.setText(mArrayList.get(9).CHANGE24HOUR + " (" + mArrayList.get(9).CHANGEPCT24HOUR + "%)");
                        tv_xmr_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(10).CHANGEPCT24HOUR.contains("-")) {
                        tv_eos_pct.setText(mArrayList.get(10).CHANGE24HOUR + " (" + mArrayList.get(10).CHANGEPCT24HOUR + "%)");
                        tv_eos_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_eos_pct.setText(mArrayList.get(10).CHANGE24HOUR + " (" + mArrayList.get(10).CHANGEPCT24HOUR + "%)");
                        tv_eos_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(11).CHANGEPCT24HOUR.contains("-")) {
                        tv_btg_pct.setText(mArrayList.get(11).CHANGE24HOUR + " (" + mArrayList.get(11).CHANGEPCT24HOUR + "%)");
                        tv_btg_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_btg_pct.setText(mArrayList.get(11).CHANGE24HOUR + " (" + mArrayList.get(11).CHANGEPCT24HOUR + "%)");
                        tv_btg_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(12).CHANGEPCT24HOUR.contains("-")) {
                        tv_qtum_pct.setText(mArrayList.get(12).CHANGE24HOUR + " (" + mArrayList.get(12).CHANGEPCT24HOUR + "%)");
                        tv_qtum_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_qtum_pct.setText(mArrayList.get(12).CHANGE24HOUR + " (" + mArrayList.get(12).CHANGEPCT24HOUR + "%)");
                        tv_qtum_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(13).CHANGEPCT24HOUR.contains("-")) {
                        tv_xlm_pct.setText(mArrayList.get(13).CHANGE24HOUR + " (" + mArrayList.get(13).CHANGEPCT24HOUR + "%)");
                        tv_xlm_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xlm_pct.setText(mArrayList.get(13).CHANGE24HOUR + " (" + mArrayList.get(13).CHANGEPCT24HOUR + "%)");
                        tv_xlm_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(14).CHANGEPCT24HOUR.contains("-")) {
                        tv_neo_pct.setText(mArrayList.get(14).CHANGE24HOUR + " (" + mArrayList.get(14).CHANGEPCT24HOUR + "%)");
                        tv_neo_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_neo_pct.setText(mArrayList.get(14).CHANGE24HOUR + " (" + mArrayList.get(14).CHANGEPCT24HOUR + "%)");
                        tv_neo_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(15).CHANGEPCT24HOUR.contains("-")) {
                        tv_etc_pct.setText(mArrayList.get(15).CHANGE24HOUR + " (" + mArrayList.get(15).CHANGEPCT24HOUR + "%)");
                        tv_etc_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_etc_pct.setText(mArrayList.get(15).CHANGE24HOUR + " (" + mArrayList.get(15).CHANGEPCT24HOUR + "%)");
                        tv_etc_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(16).CHANGEPCT24HOUR.contains("-")) {
                        tv_zec_pct.setText(mArrayList.get(16).CHANGE24HOUR + " (" + mArrayList.get(16).CHANGEPCT24HOUR + "%)");
                        tv_zec_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_zec_pct.setText(mArrayList.get(16).CHANGE24HOUR + " (" + mArrayList.get(16).CHANGEPCT24HOUR + "%)");
                        tv_zec_pct.setTextColor(Color.parseColor("#008000"));
                    }


                    if (mArrayList.get(17).CHANGEPCT24HOUR.contains("-")) {
                        tv_steem_pct.setText(mArrayList.get(17).CHANGE24HOUR + " (" + mArrayList.get(17).CHANGEPCT24HOUR + "%)");
                        tv_steem_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_steem_pct.setText(mArrayList.get(17).CHANGE24HOUR + " (" + mArrayList.get(17).CHANGEPCT24HOUR + "%)");
                        tv_steem_pct.setTextColor(Color.parseColor("#008000"));
                    }


                    if (mArrayList.get(18).CHANGEPCT24HOUR.contains("-")) {
                        tv_xzc_pct.setText(mArrayList.get(18).CHANGE24HOUR + " (" + mArrayList.get(18).CHANGEPCT24HOUR + "%)");
                        tv_xzc_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xzc_pct.setText(mArrayList.get(18).CHANGE24HOUR + " (" + mArrayList.get(18).CHANGEPCT24HOUR + "%)");
                        tv_xzc_pct.setTextColor(Color.parseColor("#008000"));
                    }


                    if (mArrayList.get(19).CHANGEPCT24HOUR.contains("-")) {
                        tv_storj_pct.setText(mArrayList.get(19).CHANGE24HOUR + " (" + mArrayList.get(19).CHANGEPCT24HOUR + "%)");
                        tv_storj_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_storj_pct.setText(mArrayList.get(19).CHANGE24HOUR + " (" + mArrayList.get(19).CHANGEPCT24HOUR + "%)");
                        tv_storj_pct.setTextColor(Color.parseColor("#008000"));
                    }


                    if (mArrayList.get(20).CHANGEPCT24HOUR.contains("-")) {
                        tv_aion_pct.setText(mArrayList.get(20).CHANGE24HOUR + " (" + mArrayList.get(20).CHANGEPCT24HOUR + "%)");
                        tv_aion_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_aion_pct.setText(mArrayList.get(20).CHANGE24HOUR + " (" + mArrayList.get(20).CHANGEPCT24HOUR + "%)");
                        tv_aion_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(21).CHANGEPCT24HOUR.contains("-")) {
                        tv_ht_ptc.setText(mArrayList.get(21).CHANGE24HOUR + " (" + mArrayList.get(21).CHANGEPCT24HOUR + "%)");
                        tv_ht_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ht_ptc.setText(mArrayList.get(21).CHANGE24HOUR + " (" + mArrayList.get(21).CHANGEPCT24HOUR + "%)");
                        tv_ht_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(22).CHANGEPCT24HOUR.contains("-")) {
                        tv_tronix_ptc.setText(mArrayList.get(22).CHANGE24HOUR + " (" + mArrayList.get(22).CHANGEPCT24HOUR + "%)");
                        tv_tronix_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_tronix_ptc.setText(mArrayList.get(22).CHANGE24HOUR + " (" + mArrayList.get(22).CHANGEPCT24HOUR + "%)");
                        tv_tronix_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(23).CHANGEPCT24HOUR.contains("-")) {
                        tv_nano_ptc.setText(mArrayList.get(23).CHANGE24HOUR + " (" + mArrayList.get(23).CHANGEPCT24HOUR + "%)");
                        tv_nano_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_nano_ptc.setText(mArrayList.get(23).CHANGE24HOUR + " (" + mArrayList.get(23).CHANGEPCT24HOUR + "%)");
                        tv_nano_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(24).CHANGEPCT24HOUR.contains("-")) {
                        tv_omisego_ptc.setText(mArrayList.get(24).CHANGE24HOUR + " (" + mArrayList.get(24).CHANGEPCT24HOUR + "%)");
                        tv_omisego_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_omisego_ptc.setText(mArrayList.get(24).CHANGE24HOUR + " (" + mArrayList.get(24).CHANGEPCT24HOUR + "%)");
                        tv_omisego_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(25).CHANGEPCT24HOUR.contains("-")) {
                        tv_elastos_ptc.setText(mArrayList.get(25).CHANGE24HOUR + " (" + mArrayList.get(25).CHANGEPCT24HOUR + "%)");
                        tv_elastos_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_elastos_ptc.setText(mArrayList.get(25).CHANGE24HOUR + " (" + mArrayList.get(25).CHANGEPCT24HOUR + "%)");
                        tv_elastos_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(26).CHANGEPCT24HOUR.contains("-")) {
                        tv_binance_ptc.setText(mArrayList.get(26).CHANGE24HOUR + " (" + mArrayList.get(26).CHANGEPCT24HOUR + "%)");
                        tv_binance_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_binance_ptc.setText(mArrayList.get(26).CHANGE24HOUR + " (" + mArrayList.get(26).CHANGEPCT24HOUR + "%)");
                        tv_binance_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(27).CHANGEPCT24HOUR.contains("-")) {
                        tv_vechain_ptc.setText(mArrayList.get(27).CHANGE24HOUR + " (" + mArrayList.get(27).CHANGEPCT24HOUR + "%)");
                        tv_vechain_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_vechain_ptc.setText(mArrayList.get(27).CHANGE24HOUR + " (" + mArrayList.get(27).CHANGEPCT24HOUR + "%)");
                        tv_vechain_ptc.setTextColor(Color.parseColor("#008000"));
                    }


                    if (mArrayList.get(28).CHANGEPCT24HOUR.contains("-")) {
                        tv_zclassic_ptc.setText(mArrayList.get(28).CHANGE24HOUR + " (" + mArrayList.get(28).CHANGEPCT24HOUR + "%)");
                        tv_zclassic_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_zclassic_ptc.setText(mArrayList.get(28).CHANGE24HOUR + " (" + mArrayList.get(28).CHANGEPCT24HOUR + "%)");
                        tv_zclassic_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(29).CHANGEPCT24HOUR.contains("-")) {
                        tv_digix_ptc.setText(mArrayList.get(29).CHANGE24HOUR + " (" + mArrayList.get(29).CHANGEPCT24HOUR + "%)");
                        tv_digix_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_digix_ptc.setText(mArrayList.get(29).CHANGE24HOUR + " (" + mArrayList.get(29).CHANGEPCT24HOUR + "%)");
                        tv_digix_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(30).CHANGEPCT24HOUR.contains("-")) {
                        tv_odyssey_ptc.setText(mArrayList.get(30).CHANGE24HOUR + " (" + mArrayList.get(30).CHANGEPCT24HOUR + "%)");
                        tv_odyssey_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_odyssey_ptc.setText(mArrayList.get(30).CHANGE24HOUR + " (" + mArrayList.get(30).CHANGEPCT24HOUR + "%)");
                        tv_odyssey_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(31).CHANGEPCT24HOUR.contains("-")) {
                        tv_blockmason_ptc.setText(mArrayList.get(31).CHANGE24HOUR + " (" + mArrayList.get(31).CHANGEPCT24HOUR + "%)");
                        tv_blockmason_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_blockmason_ptc.setText(mArrayList.get(31).CHANGE24HOUR + " (" + mArrayList.get(31).CHANGEPCT24HOUR + "%)");
                        tv_blockmason_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(32).CHANGEPCT24HOUR.contains("-")) {
                        tv_lunyr_ptc.setText(mArrayList.get(32).CHANGE24HOUR + " (" + mArrayList.get(32).CHANGEPCT24HOUR + "%)");
                        tv_lunyr_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_lunyr_ptc.setText(mArrayList.get(32).CHANGE24HOUR + " (" + mArrayList.get(32).CHANGEPCT24HOUR + "%)");
                        tv_lunyr_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(33).CHANGEPCT24HOUR.contains("-")) {
                        tv_iostoken_ptc.setText(mArrayList.get(33).CHANGE24HOUR + " (" + mArrayList.get(33).CHANGEPCT24HOUR + "%)");
                        tv_iostoken_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iostoken_ptc.setText(mArrayList.get(33).CHANGE24HOUR + " (" + mArrayList.get(33).CHANGEPCT24HOUR + "%)");
                        tv_iostoken_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(34).CHANGEPCT24HOUR.contains("-")) {
                        tv_hshare_ptc.setText(mArrayList.get(34).CHANGE24HOUR + " (" + mArrayList.get(34).CHANGEPCT24HOUR + "%)");
                        tv_hshare_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_hshare_ptc.setText(mArrayList.get(34).CHANGE24HOUR + " (" + mArrayList.get(34).CHANGEPCT24HOUR + "%)");
                        tv_hshare_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(35).CHANGEPCT24HOUR.contains("-")) {
                        tv_iconproject_ptc.setText(mArrayList.get(35).CHANGE24HOUR + " (" + mArrayList.get(35).CHANGEPCT24HOUR + "%)");
                        tv_iconproject_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iconproject_ptc.setText(mArrayList.get(35).CHANGE24HOUR + " (" + mArrayList.get(35).CHANGEPCT24HOUR + "%)");
                        tv_iconproject_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(36).CHANGEPCT24HOUR.contains("-")) {
                        tv_lisk_ptc.setText(mArrayList.get(36).CHANGE24HOUR + " (" + mArrayList.get(36).CHANGEPCT24HOUR + "%)");
                        tv_lisk_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_lisk_ptc.setText(mArrayList.get(36).CHANGE24HOUR + " (" + mArrayList.get(36).CHANGEPCT24HOUR + "%)");
                        tv_lisk_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(37).CHANGEPCT24HOUR.contains("-")) {
                        tv_nebilo_ptc.setText(mArrayList.get(37).CHANGE24HOUR + " (" + mArrayList.get(37).CHANGEPCT24HOUR + "%)");
                        tv_nebilo_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_nebilo_ptc.setText(mArrayList.get(37).CHANGE24HOUR + " (" + mArrayList.get(37).CHANGEPCT24HOUR + "%)");
                        tv_nebilo_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(38).CHANGEPCT24HOUR.contains("-")) {
                        tv_wavess_ptc.setText(mArrayList.get(38).CHANGE24HOUR + " (" + mArrayList.get(38).CHANGEPCT24HOUR + "%)");
                        tv_wavess_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_wavess_ptc.setText(mArrayList.get(38).CHANGE24HOUR + " (" + mArrayList.get(38).CHANGEPCT24HOUR + "%)");
                        tv_wavess_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(39).CHANGEPCT24HOUR.contains("-")) {
                        tv_bluzello_ptc.setText(mArrayList.get(39).CHANGE24HOUR + " (" + mArrayList.get(39).CHANGEPCT24HOUR + "%)");
                        tv_bluzello_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_bluzello_ptc.setText(mArrayList.get(39).CHANGE24HOUR + " (" + mArrayList.get(39).CHANGEPCT24HOUR + "%)");
                        tv_bluzello_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(40).CHANGEPCT24HOUR.contains("-")) {
                        tv_inkk_ptc.setText(mArrayList.get(40).CHANGE24HOUR + " (" + mArrayList.get(40).CHANGEPCT24HOUR + "%)");
                        tv_inkk_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_inkk_ptc.setText(mArrayList.get(40).CHANGE24HOUR + " (" + mArrayList.get(40).CHANGEPCT24HOUR + "%)");
                        tv_inkk_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(41).CHANGEPCT24HOUR.contains("-")) {
                        tv_adex_ptc.setText(mArrayList.get(41).CHANGE24HOUR + " (" + mArrayList.get(41).CHANGEPCT24HOUR + "%)");
                        tv_adex_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_adex_ptc.setText(mArrayList.get(41).CHANGE24HOUR + " (" + mArrayList.get(41).CHANGEPCT24HOUR + "%)");
                        tv_adex_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(42).CHANGEPCT24HOUR.contains("-")) {
                        tv_verge_ptc.setText(mArrayList.get(42).CHANGE24HOUR + " (" + mArrayList.get(42).CHANGEPCT24HOUR + "%)");
                        tv_verge_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_verge_ptc.setText(mArrayList.get(42).CHANGE24HOUR + " (" + mArrayList.get(42).CHANGEPCT24HOUR + "%)");
                        tv_verge_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(43).CHANGEPCT24HOUR.contains("-")) {
                        tv_metal_ptc.setText(mArrayList.get(43).CHANGE24HOUR + " (" + mArrayList.get(43).CHANGEPCT24HOUR + "%)");
                        tv_metal_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_metal_ptc.setText(mArrayList.get(43).CHANGE24HOUR + " (" + mArrayList.get(43).CHANGEPCT24HOUR + "%)");
                        tv_metal_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(44).CHANGEPCT24HOUR.contains("-")) {
                        tv_sirinlabs_ptc.setText(mArrayList.get(44).CHANGE24HOUR + " (" + mArrayList.get(44).CHANGEPCT24HOUR + "%)");
                        tv_sirinlabs_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_sirinlabs_ptc.setText(mArrayList.get(44).CHANGE24HOUR + " (" + mArrayList.get(44).CHANGEPCT24HOUR + "%)");
                        tv_sirinlabs_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(45).CHANGEPCT24HOUR.contains("-")) {
                        tv_ox_ptc.setText(mArrayList.get(45).CHANGE24HOUR + " (" + mArrayList.get(45).CHANGEPCT24HOUR + "%)");
                        tv_ox_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ox_ptc.setText(mArrayList.get(45).CHANGE24HOUR + " (" + mArrayList.get(45).CHANGEPCT24HOUR + "%)");
                        tv_ox_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(46).CHANGEPCT24HOUR.contains("-")) {
                        tv_iex_ptc.setText(mArrayList.get(46).CHANGE24HOUR + " (" + mArrayList.get(46).CHANGEPCT24HOUR + "%)");
                        tv_iex_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iex_ptc.setText(mArrayList.get(46).CHANGE24HOUR + " (" + mArrayList.get(46).CHANGEPCT24HOUR + "%)");
                        tv_iex_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(47).CHANGEPCT24HOUR.contains("-")) {
                        tv_thetaa_ptc.setText(mArrayList.get(47).CHANGE24HOUR + " (" + mArrayList.get(47).CHANGEPCT24HOUR + "%)");
                        tv_thetaa_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_thetaa_ptc.setText(mArrayList.get(47).CHANGE24HOUR + " (" + mArrayList.get(47).CHANGEPCT24HOUR + "%)");
                        tv_thetaa_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(48).CHANGEPCT24HOUR.contains("-")) {
                        tv_bitcoindimand_ptc.setText(mArrayList.get(48).CHANGE24HOUR + " (" + mArrayList.get(48).CHANGEPCT24HOUR + "%)");
                        tv_bitcoindimand_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_bitcoindimand_ptc.setText(mArrayList.get(48).CHANGE24HOUR + " (" + mArrayList.get(48).CHANGEPCT24HOUR + "%)");
                        tv_bitcoindimand_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(49).CHANGEPCT24HOUR.contains("-")) {
                        tv_openanx_ptc.setText(mArrayList.get(49).CHANGE24HOUR + " (" + mArrayList.get(49).CHANGEPCT24HOUR + "%)");
                        tv_openanx_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_openanx_ptc.setText(mArrayList.get(49).CHANGE24HOUR + " (" + mArrayList.get(49).CHANGEPCT24HOUR + "%)");
                        tv_openanx_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(50).CHANGEPCT24HOUR.contains("-")) {
                        tv_aelf_ptc.setText(mArrayList.get(50).CHANGE24HOUR + " (" + mArrayList.get(50).CHANGEPCT24HOUR + "%)");
                        tv_aelf_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_aelf_ptc.setText(mArrayList.get(50).CHANGE24HOUR + " (" + mArrayList.get(50).CHANGEPCT24HOUR + "%)");
                        tv_aelf_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(51).CHANGEPCT24HOUR.contains("-")) {
                        tv_insexosystem_ptc.setText(mArrayList.get(51).CHANGE24HOUR + " (" + mArrayList.get(51).CHANGEPCT24HOUR + "%)");
                        tv_insexosystem_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_insexosystem_ptc.setText(mArrayList.get(51).CHANGE24HOUR + " (" + mArrayList.get(51).CHANGEPCT24HOUR + "%)");
                        tv_insexosystem_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(52).CHANGEPCT24HOUR.contains("-")) {
                        tv_zilliqa_ptc.setText(mArrayList.get(52).CHANGE24HOUR + " (" + mArrayList.get(52).CHANGEPCT24HOUR + "%)");
                        tv_zilliqa_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_zilliqa_ptc.setText(mArrayList.get(52).CHANGE24HOUR + " (" + mArrayList.get(52).CHANGEPCT24HOUR + "%)");
                        tv_zilliqa_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(53).CHANGEPCT24HOUR.contains("-")) {
                        tv_aeternity_ptc.setText(mArrayList.get(53).CHANGE24HOUR + " (" + mArrayList.get(53).CHANGEPCT24HOUR + "%)");
                        tv_aeternity_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_aeternity_ptc.setText(mArrayList.get(53).CHANGE24HOUR + " (" + mArrayList.get(53).CHANGEPCT24HOUR + "%)");
                        tv_aeternity_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(54).CHANGEPCT24HOUR.contains("-")) {
                        tv_propy_ptc.setText(mArrayList.get(54).CHANGE24HOUR + " (" + mArrayList.get(54).CHANGEPCT24HOUR + "%)");
                        tv_propy_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_propy_ptc.setText(mArrayList.get(54).CHANGE24HOUR + " (" + mArrayList.get(54).CHANGEPCT24HOUR + "%)");
                        tv_propy_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(55).CHANGEPCT24HOUR.contains("-")) {
                        tv_dogecoin_ptc.setText(mArrayList.get(55).CHANGE24HOUR + " (" + mArrayList.get(55).CHANGEPCT24HOUR + "%)");
                        tv_dogecoin_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_dogecoin_ptc.setText(mArrayList.get(55).CHANGE24HOUR + " (" + mArrayList.get(55).CHANGEPCT24HOUR + "%)");
                        tv_dogecoin_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(56).CHANGEPCT24HOUR.contains("-")) {
                        tv_iotchain_ptc.setText(mArrayList.get(56).CHANGE24HOUR + " (" + mArrayList.get(56).CHANGEPCT24HOUR + "%)");
                        tv_iotchain_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iotchain_ptc.setText(mArrayList.get(56).CHANGE24HOUR + " (" + mArrayList.get(56).CHANGEPCT24HOUR + "%)");
                        tv_iotchain_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(57).CHANGEPCT24HOUR.contains("-")) {
                        tv_reddcoin_ptc.setText(mArrayList.get(57).CHANGE24HOUR + " (" + mArrayList.get(57).CHANGEPCT24HOUR + "%)");
                        tv_reddcoin_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_reddcoin_ptc.setText(mArrayList.get(57).CHANGE24HOUR + " (" + mArrayList.get(57).CHANGEPCT24HOUR + "%)");
                        tv_reddcoin_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(58).CHANGEPCT24HOUR.contains("-")) {
                        tv_swftcoin_ptc.setText(mArrayList.get(58).CHANGE24HOUR + " (" + mArrayList.get(58).CHANGEPCT24HOUR + "%)");
                        tv_swftcoin_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_swftcoin_ptc.setText(mArrayList.get(58).CHANGE24HOUR + " (" + mArrayList.get(58).CHANGEPCT24HOUR + "%)");
                        tv_swftcoin_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(59).CHANGEPCT24HOUR.contains("-")) {
                        tv_monaco_ptc.setText(mArrayList.get(59).CHANGE24HOUR + " (" + mArrayList.get(59).CHANGEPCT24HOUR + "%)");
                        tv_monaco_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_monaco_ptc.setText(mArrayList.get(59).CHANGE24HOUR + " (" + mArrayList.get(59).CHANGEPCT24HOUR + "%)");
                        tv_monaco_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(60).CHANGEPCT24HOUR.contains("-")) {
                        tv_aeron_ptc.setText(mArrayList.get(60).CHANGE24HOUR + " (" + mArrayList.get(60).CHANGEPCT24HOUR + "%)");
                        tv_aeron_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_aeron_ptc.setText(mArrayList.get(60).CHANGE24HOUR + " (" + mArrayList.get(60).CHANGEPCT24HOUR + "%)");
                        tv_aeron_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(61).CHANGEPCT24HOUR.contains("-")) {
                        tv_medicakchain_ptc.setText(mArrayList.get(61).CHANGE24HOUR + " (" + mArrayList.get(61).CHANGEPCT24HOUR + "%)");
                        tv_medicakchain_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_medicakchain_ptc.setText(mArrayList.get(61).CHANGE24HOUR + " (" + mArrayList.get(61).CHANGEPCT24HOUR + "%)");
                        tv_medicakchain_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(62).CHANGEPCT24HOUR.contains("-")) {
                        tv_breadtoken_ptc.setText(mArrayList.get(62).CHANGE24HOUR + " (" + mArrayList.get(62).CHANGEPCT24HOUR + "%)");
                        tv_breadtoken_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_breadtoken_ptc.setText(mArrayList.get(62).CHANGE24HOUR + " (" + mArrayList.get(62).CHANGEPCT24HOUR + "%)");
                        tv_breadtoken_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(63).CHANGEPCT24HOUR.contains("-")) {
                        tv_santiment_ptc.setText(mArrayList.get(63).CHANGE24HOUR + " (" + mArrayList.get(63).CHANGEPCT24HOUR + "%)");
                        tv_santiment_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_santiment_ptc.setText(mArrayList.get(63).CHANGE24HOUR + " (" + mArrayList.get(63).CHANGEPCT24HOUR + "%)");
                        tv_santiment_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(64).CHANGEPCT24HOUR.contains("-")) {
                        tv_nebulas_ptc.setText(mArrayList.get(64).CHANGE24HOUR + " (" + mArrayList.get(64).CHANGEPCT24HOUR + "%)");
                        tv_nebulas_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_nebulas_ptc.setText(mArrayList.get(64).CHANGE24HOUR + " (" + mArrayList.get(64).CHANGEPCT24HOUR + "%)");
                        tv_nebulas_ptc.setTextColor(Color.parseColor("#008000"));
                    }
                    if (mArrayList.get(65).CHANGEPCT24HOUR.contains("-")) {
                        tv_genesis_ptc.setText(mArrayList.get(65).CHANGE24HOUR + " (" + mArrayList.get(65).CHANGEPCT24HOUR + "%)");
                        tv_genesis_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_genesis_ptc.setText(mArrayList.get(65).CHANGE24HOUR + " (" + mArrayList.get(65).CHANGEPCT24HOUR + "%)");
                        tv_genesis_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(66).CHANGEPCT24HOUR.contains("-")) {
                        tv_qunqun_ptc.setText(mArrayList.get(66).CHANGE24HOUR + " (" + mArrayList.get(66).CHANGEPCT24HOUR + "%)");
                        tv_qunqun_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_qunqun_ptc.setText(mArrayList.get(66).CHANGE24HOUR + " (" + mArrayList.get(66).CHANGEPCT24HOUR + "%)");
                        tv_qunqun_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(67).CHANGEPCT24HOUR.contains("-")) {
                        tv_waltonchain_ptc.setText(mArrayList.get(67).CHANGE24HOUR + " (" + mArrayList.get(67).CHANGEPCT24HOUR + "%)");
                        tv_waltonchain_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_waltonchain_ptc.setText(mArrayList.get(67).CHANGE24HOUR + " (" + mArrayList.get(67).CHANGEPCT24HOUR + "%)");
                        tv_waltonchain_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(68).CHANGEPCT24HOUR.contains("-")) {
                        tv_factoids_ptc.setText(mArrayList.get(68).CHANGE24HOUR + " (" + mArrayList.get(68).CHANGEPCT24HOUR + "%)");
                        tv_factoids_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_factoids_ptc.setText(mArrayList.get(68).CHANGE24HOUR + " (" + mArrayList.get(68).CHANGEPCT24HOUR + "%)");
                        tv_factoids_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(69).CHANGEPCT24HOUR.contains("-")) {
                        tv_coinDash_ptc.setText(mArrayList.get(69).CHANGE24HOUR + " (" + mArrayList.get(69).CHANGEPCT24HOUR + "%)");
                        tv_coinDash_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_coinDash_ptc.setText(mArrayList.get(69).CHANGE24HOUR + " (" + mArrayList.get(69).CHANGEPCT24HOUR + "%)");
                        tv_coinDash_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(70).CHANGEPCT24HOUR.contains("-")) {
                        tv_ripio_ptc.setText(mArrayList.get(70).CHANGE24HOUR + " (" + mArrayList.get(70).CHANGEPCT24HOUR + "%)");
                        tv_ripio_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ripio_ptc.setText(mArrayList.get(70).CHANGE24HOUR + " (" + mArrayList.get(70).CHANGEPCT24HOUR + "%)");
                        tv_ripio_ptc.setTextColor(Color.parseColor("#008000"));
                    }
                }
                update();

            } else {
//                utils.showAlertMessage(Home_Activity.this, msg);
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

                        if (utils.isNetworkAvailable(Home_Activity.this)) {
                            new apiGet_Update_Coins().execute();
                        } else {
//                            utils.showAlertMessage(Home_Activity.this, getString(R.string.err_no_internet));
                        }

                    }
                });

            }
        };
    }


    //-----------------------------------------API_FOR_GET_COINS-------------------------------------------------------------------


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
                ParsedResponse p = Service.apiGetCoin(Home_Activity.this, Baseurl);
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

                for (int i = 0; i < mArrayList.size(); i++) {
                    tv_btc_price.setText(mArrayList.get(0).PRICE);
                    tv_eth_price.setText(mArrayList.get(1).PRICE);
                    tv_bch_price.setText(mArrayList.get(2).PRICE);
                    tv_xrp_price.setText(mArrayList.get(3).PRICE);
                    tv_ltc_price.setText(mArrayList.get(4).PRICE);
                    tv_ada_price.setText(mArrayList.get(5).PRICE);
                    tv_iot_price.setText(mArrayList.get(6).PRICE);
                    tv_dash_price.setText(mArrayList.get(7).PRICE);
                    tv_xem_price.setText(mArrayList.get(8).PRICE);
                    tv_xmr_price.setText(mArrayList.get(9).PRICE);
                    tv_eos_price.setText(mArrayList.get(10).PRICE);
                    tv_btg_price.setText(mArrayList.get(11).PRICE);
                    tv_qtum_price.setText(mArrayList.get(12).PRICE);
                    tv_xlm_price.setText(mArrayList.get(13).PRICE);
                    tv_neo_price.setText(mArrayList.get(14).PRICE);
                    tv_etc_price.setText(mArrayList.get(15).PRICE);
                    tv_zec_price.setText(mArrayList.get(16).PRICE);
                    tv_steem_price.setText(mArrayList.get(17).PRICE);
                    tv_xzc_price.setText(mArrayList.get(18).PRICE);
                    tv_storj_price.setText(mArrayList.get(19).PRICE);
                    tv_aion_price.setText(mArrayList.get(20).PRICE);
                    tv_ht_price.setText(mArrayList.get(21).PRICE);
                    tv_tronix_price.setText(mArrayList.get(22).PRICE);
                    tv_nano_price.setText(mArrayList.get(23).PRICE);
                    tv_omisego_price.setText(mArrayList.get(24).PRICE);
                    tv_elastos_price.setText(mArrayList.get(25).PRICE);
                    tv_binance_price.setText(mArrayList.get(26).PRICE);
                    tv_vechain_price.setText(mArrayList.get(27).PRICE);
                    tv_zclassic_price.setText(mArrayList.get(28).PRICE);
                    tv_digix_price.setText(mArrayList.get(29).PRICE);
                    tv_odyssey_price.setText(mArrayList.get(30).PRICE);
                    tv_blockmason_price.setText(mArrayList.get(31).PRICE);
                    tv_lunyr_price.setText(mArrayList.get(32).PRICE);
                    tv_iostoken_price.setText(mArrayList.get(33).PRICE);
                    tv_hshare_price.setText(mArrayList.get(34).PRICE);
                    tv_iconproject_price.setText(mArrayList.get(35).PRICE);
                    tv_lisk_price.setText(mArrayList.get(36).PRICE);
                    tv_nebilo_price.setText(mArrayList.get(37).PRICE);
                    tv_wavess_price.setText(mArrayList.get(38).PRICE);
                    tv_bluzello_price.setText(mArrayList.get(39).PRICE);
                    tv_inkk_price.setText(mArrayList.get(40).PRICE);
                    tv_adex_price.setText(mArrayList.get(41).PRICE);
                    tv_verge_price.setText(mArrayList.get(42).PRICE);
                    tv_metal_price.setText(mArrayList.get(43).PRICE);
                    tv_sirinlabs_price.setText(mArrayList.get(44).PRICE);
                    tv_ox_price.setText(mArrayList.get(45).PRICE);
                    tv_iex_price.setText(mArrayList.get(46).PRICE);
                    tv_thetaa_price.setText(mArrayList.get(47).PRICE);
                    tv_bitcoindimand_price.setText(mArrayList.get(48).PRICE);
                    tv_openanx_price.setText(mArrayList.get(49).PRICE);
                    tv_aelf_price.setText(mArrayList.get(50).PRICE);
                    tv_insexosystem_price.setText(mArrayList.get(51).PRICE);
                    tv_zilliqa_price.setText(mArrayList.get(52).PRICE);
                    tv_aeternity_price.setText(mArrayList.get(53).PRICE);
                    tv_propy_price.setText(mArrayList.get(54).PRICE);
                    tv_dogecoin_price.setText(mArrayList.get(55).PRICE);
                    tv_iotchain_price.setText(mArrayList.get(56).PRICE);
                    tv_reddcoin_price.setText(mArrayList.get(57).PRICE);
                    tv_swftcoin_price.setText(mArrayList.get(58).PRICE);
                    tv_monaco_price.setText(mArrayList.get(59).PRICE);
                    tv_aeron_price.setText(mArrayList.get(60).PRICE);
                    tv_medicakchain_price.setText(mArrayList.get(61).PRICE);
                    tv_breadtoken_price.setText(mArrayList.get(62).PRICE);
                    tv_santiment_price.setText(mArrayList.get(63).PRICE);
                    tv_nebulas_price.setText(mArrayList.get(64).PRICE);
                    tv_genesis_price.setText(mArrayList.get(65).PRICE);
                    tv_qunqun_price.setText(mArrayList.get(66).PRICE);
                    tv_waltonchain_price.setText(mArrayList.get(67).PRICE);
                    tv_factoids_price.setText(mArrayList.get(68).PRICE);
                    tv_coinDash_price.setText(mArrayList.get(69).PRICE);
                    tv_ripio_price.setText(mArrayList.get(70).PRICE);

                    tv_btc_mc.setText("M.C:" + mArrayList.get(0).MKTCAP);
                    tv_eth_mc.setText("M.C:" + mArrayList.get(1).MKTCAP);
                    tv_bch_mc.setText("M.C:" + mArrayList.get(2).MKTCAP);
                    tv_xrp_mc.setText("M.C:" + mArrayList.get(3).MKTCAP);
                    tv_ltc_mc.setText("M.C:" + mArrayList.get(4).MKTCAP);
                    tv_ada_mc.setText("M.C:" + mArrayList.get(5).MKTCAP);
                    tv_iot_mc.setText("M.C:" + mArrayList.get(6).MKTCAP);
                    tv_dash_mc.setText("M.C:" + mArrayList.get(7).MKTCAP);
                    tv_xem_mc.setText("M.C:" + mArrayList.get(8).MKTCAP);
                    tv_xmr_mc.setText("M.C:" + mArrayList.get(9).MKTCAP);
                    tv_eos_mc.setText("M.C:" + mArrayList.get(10).MKTCAP);
                    tv_btg_mc.setText("M.C:" + mArrayList.get(11).MKTCAP);
                    tv_qtum_mc.setText("M.C:" + mArrayList.get(12).MKTCAP);
                    tv_xlm_mc.setText("M.C:" + mArrayList.get(13).MKTCAP);
                    tv_neo_mc.setText("M.C:" + mArrayList.get(14).MKTCAP);
                    tv_etc_mc.setText("M.C:" + mArrayList.get(15).MKTCAP);
                    tv_zec_mc.setText("M.C:" + mArrayList.get(16).MKTCAP);
                    tv_steem_mc.setText("M.C:" + mArrayList.get(17).MKTCAP);
                    tv_xzc_mc.setText("M.C:" + mArrayList.get(18).MKTCAP);
                    tv_storj_mc.setText("M.C:" + mArrayList.get(19).MKTCAP);
                    tv_aion_mc.setText("M.C:" + mArrayList.get(20).MKTCAP);
                    tv_ht_mc.setText("M.C:" + mArrayList.get(21).MKTCAP);
                    tv_tronix_mc.setText("M.C:" + mArrayList.get(22).MKTCAP);
                    tv_nano_mc.setText("M.C:" + mArrayList.get(23).MKTCAP);
                    tv_omisego_mc.setText("M.C:" + mArrayList.get(24).MKTCAP);
                    tv_elastos_mc.setText("M.C:" + mArrayList.get(25).MKTCAP);
                    tv_binance_mc.setText("M.C:" + mArrayList.get(26).MKTCAP);
                    tv_vechain_mc.setText("M.C:" + mArrayList.get(27).MKTCAP);
                    tv_zclassic_mc.setText("M.C:" + mArrayList.get(28).MKTCAP);
                    tv_digix_mc.setText("M.C:" + mArrayList.get(29).MKTCAP);
                    tv_odyssey_mc.setText("M.C:" + mArrayList.get(30).MKTCAP);
                    tv_blockmason_mc.setText("M.C:" + mArrayList.get(31).MKTCAP);
                    tv_lunyr_mc.setText("M.C:" + mArrayList.get(32).MKTCAP);
                    tv_iostoken_mc.setText("M.C:" + mArrayList.get(33).MKTCAP);
                    tv_hshare_mc.setText("M.C:" + mArrayList.get(34).MKTCAP);
                    tv_iconproject_mc.setText("M.C:" + mArrayList.get(35).MKTCAP);
                    tv_lisk_mc.setText("M.C:" + mArrayList.get(36).MKTCAP);
                    tv_nebilo_mc.setText("M.C:" + mArrayList.get(37).MKTCAP);
                    tv_wavess_mc.setText("M.C:" + mArrayList.get(38).MKTCAP);
                    tv_bluzello_mc.setText("M.C:" + mArrayList.get(39).MKTCAP);
                    tv_inkk_mc.setText("M.C:" + mArrayList.get(40).MKTCAP);
                    tv_adex_mc.setText("M.C:" + mArrayList.get(41).MKTCAP);
                    tv_verge_mc.setText("M.C:" + mArrayList.get(42).MKTCAP);
                    tv_metal_mc.setText("M.C:" + mArrayList.get(43).MKTCAP);
                    tv_sirinlabs_mc.setText("M.C:" + mArrayList.get(44).MKTCAP);
                    tv_ox_mc.setText("M.C:" + mArrayList.get(45).MKTCAP);
                    tv_iex_mc.setText("M.C:" + mArrayList.get(46).MKTCAP);
                    tv_thetaa_mc.setText("M.C:" + mArrayList.get(47).MKTCAP);
                    tv_bitcoindimand_mc.setText("M.C:" + mArrayList.get(48).MKTCAP);
                    tv_openanx_mc.setText("M.C:" + mArrayList.get(49).MKTCAP);
                    tv_aelf_mc.setText("M.C:" + mArrayList.get(50).MKTCAP);
                    tv_insexosystem_mc.setText("M.C:" + mArrayList.get(51).MKTCAP);
                    tv_zilliqa_mc.setText("M.C:" + mArrayList.get(52).MKTCAP);
                    tv_aeternity_mc.setText("M.C:" + mArrayList.get(53).MKTCAP);
                    tv_propy_mc.setText("M.C:" + mArrayList.get(54).MKTCAP);
                    tv_dogecoin_mc.setText("M.C:" + mArrayList.get(55).MKTCAP);
                    tv_iotchain_mc.setText("M.C:" + mArrayList.get(56).MKTCAP);
                    tv_reddcoin_mc.setText("M.C:" + mArrayList.get(57).MKTCAP);
                    tv_swftcoin_mc.setText("M.C:" + mArrayList.get(58).MKTCAP);
                    tv_monaco_mc.setText("M.C:" + mArrayList.get(59).MKTCAP);
                    tv_aeron_mc.setText("M.C:" + mArrayList.get(60).MKTCAP);
                    tv_medicakchain_mc.setText("M.C:" + mArrayList.get(61).MKTCAP);
                    tv_breadtoken_mc.setText("M.C:" + mArrayList.get(62).MKTCAP);
                    tv_santiment_mc.setText("M.C:" + mArrayList.get(63).MKTCAP);
                    tv_nebulas_mc.setText("M.C:" + mArrayList.get(64).MKTCAP);
                    tv_genesis_mc.setText("M.C:" + mArrayList.get(65).MKTCAP);
                    tv_qunqun_mc.setText("M.C:" + mArrayList.get(66).MKTCAP);
                    tv_waltonchain_mc.setText("M.C:" + mArrayList.get(67).MKTCAP);
                    tv_factoids_mc.setText("M.C:" + mArrayList.get(68).MKTCAP);
                    tv_coinDash_mc.setText("M.C:" + mArrayList.get(69).MKTCAP);
                    tv_ripio_mc.setText("M.C:" + mArrayList.get(70).MKTCAP);


                    if (mArrayList.get(0).CHANGEPCT24HOUR.contains("-")) {
                        tv_btc_pct.setText(mArrayList.get(0).CHANGE24HOUR + " (" + mArrayList.get(0).CHANGEPCT24HOUR + "%)");
                        tv_btc_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_btc_pct.setText(mArrayList.get(0).CHANGE24HOUR + " (" + mArrayList.get(0).CHANGEPCT24HOUR + "%)");
                        tv_btc_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(1).CHANGEPCT24HOUR.contains("-")) {
                        tv_eth_pct.setText(mArrayList.get(1).CHANGE24HOUR + " (" + mArrayList.get(1).CHANGEPCT24HOUR + "%)");
                        tv_eth_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_eth_pct.setText(mArrayList.get(1).CHANGE24HOUR + " (" + mArrayList.get(1).CHANGEPCT24HOUR + "%)");
                        tv_eth_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(2).CHANGEPCT24HOUR.contains("-")) {
                        tv_bch_pct.setText(mArrayList.get(2).CHANGE24HOUR + " (" + mArrayList.get(2).CHANGEPCT24HOUR + "%)");
                        tv_bch_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_bch_pct.setText(mArrayList.get(2).CHANGE24HOUR + " (" + mArrayList.get(2).CHANGEPCT24HOUR + "%)");
                        tv_bch_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(3).CHANGEPCT24HOUR.contains("-")) {
                        tv_xrp_pct.setText(mArrayList.get(3).CHANGE24HOUR + " (" + mArrayList.get(3).CHANGEPCT24HOUR + "%)");
                        tv_xrp_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xrp_pct.setText(mArrayList.get(3).CHANGE24HOUR + " (" + mArrayList.get(3).CHANGEPCT24HOUR + "%)");
                        tv_xrp_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(4).CHANGEPCT24HOUR.contains("-")) {
                        tv_ltc_pct.setText(mArrayList.get(4).CHANGE24HOUR + " (" + mArrayList.get(4).CHANGEPCT24HOUR + "%)");
                        tv_ltc_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ltc_pct.setText(mArrayList.get(4).CHANGE24HOUR + " (" + mArrayList.get(4).CHANGEPCT24HOUR + "%)");
                        tv_ltc_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(5).CHANGEPCT24HOUR.contains("-")) {
                        tv_ada_pct.setText(mArrayList.get(5).CHANGE24HOUR + " (" + mArrayList.get(5).CHANGEPCT24HOUR + "%)");
                        tv_ada_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ada_pct.setText(mArrayList.get(5).CHANGE24HOUR + " (" + mArrayList.get(5).CHANGEPCT24HOUR + "%)");
                        tv_ada_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(6).CHANGEPCT24HOUR.contains("-")) {
                        tv_iot_pct.setText(mArrayList.get(6).CHANGE24HOUR + " (" + mArrayList.get(6).CHANGEPCT24HOUR + "%)");
                        tv_iot_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iot_pct.setText(mArrayList.get(6).CHANGE24HOUR + " (" + mArrayList.get(6).CHANGEPCT24HOUR + "%)");
                        tv_iot_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(7).CHANGEPCT24HOUR.contains("-")) {
                        tv_dash_pct.setText(mArrayList.get(7).CHANGE24HOUR + " (" + mArrayList.get(7).CHANGEPCT24HOUR + "%)");
                        tv_dash_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_dash_pct.setText(mArrayList.get(7).CHANGE24HOUR + " (" + mArrayList.get(7).CHANGEPCT24HOUR + "%)");
                        tv_dash_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(8).CHANGEPCT24HOUR.contains("-")) {
                        tv_xem_pct.setText(mArrayList.get(8).CHANGE24HOUR + " (" + mArrayList.get(8).CHANGEPCT24HOUR + "%)");
                        tv_xem_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xem_pct.setText(mArrayList.get(8).CHANGE24HOUR + " (" + mArrayList.get(8).CHANGEPCT24HOUR + "%)");
                        tv_xem_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(9).CHANGEPCT24HOUR.contains("-")) {
                        tv_xmr_pct.setText(mArrayList.get(9).CHANGE24HOUR + " (" + mArrayList.get(9).CHANGEPCT24HOUR + "%)");
                        tv_xmr_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xmr_pct.setText(mArrayList.get(9).CHANGE24HOUR + " (" + mArrayList.get(9).CHANGEPCT24HOUR + "%)");
                        tv_xmr_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(10).CHANGEPCT24HOUR.contains("-")) {
                        tv_eos_pct.setText(mArrayList.get(10).CHANGE24HOUR + " (" + mArrayList.get(10).CHANGEPCT24HOUR + "%)");
                        tv_eos_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_eos_pct.setText(mArrayList.get(10).CHANGE24HOUR + " (" + mArrayList.get(10).CHANGEPCT24HOUR + "%)");
                        tv_eos_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(11).CHANGEPCT24HOUR.contains("-")) {
                        tv_btg_pct.setText(mArrayList.get(11).CHANGE24HOUR + " (" + mArrayList.get(11).CHANGEPCT24HOUR + "%)");
                        tv_btg_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_btg_pct.setText(mArrayList.get(11).CHANGE24HOUR + " (" + mArrayList.get(11).CHANGEPCT24HOUR + "%)");
                        tv_btg_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(12).CHANGEPCT24HOUR.contains("-")) {
                        tv_qtum_pct.setText(mArrayList.get(12).CHANGE24HOUR + " (" + mArrayList.get(12).CHANGEPCT24HOUR + "%)");
                        tv_qtum_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_qtum_pct.setText(mArrayList.get(12).CHANGE24HOUR + " (" + mArrayList.get(12).CHANGEPCT24HOUR + "%)");
                        tv_qtum_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(13).CHANGEPCT24HOUR.contains("-")) {
                        tv_xlm_pct.setText(mArrayList.get(13).CHANGE24HOUR + " (" + mArrayList.get(13).CHANGEPCT24HOUR + "%)");
                        tv_xlm_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xlm_pct.setText(mArrayList.get(13).CHANGE24HOUR + " (" + mArrayList.get(13).CHANGEPCT24HOUR + "%)");
                        tv_xlm_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(14).CHANGEPCT24HOUR.contains("-")) {
                        tv_neo_pct.setText(mArrayList.get(14).CHANGE24HOUR + " (" + mArrayList.get(14).CHANGEPCT24HOUR + "%)");
                        tv_neo_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_neo_pct.setText(mArrayList.get(14).CHANGE24HOUR + " (" + mArrayList.get(14).CHANGEPCT24HOUR + "%)");
                        tv_neo_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(15).CHANGEPCT24HOUR.contains("-")) {
                        tv_etc_pct.setText(mArrayList.get(15).CHANGE24HOUR + " (" + mArrayList.get(15).CHANGEPCT24HOUR + "%)");
                        tv_etc_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_etc_pct.setText(mArrayList.get(15).CHANGE24HOUR + " (" + mArrayList.get(15).CHANGEPCT24HOUR + "%)");
                        tv_etc_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(16).CHANGEPCT24HOUR.contains("-")) {
                        tv_zec_pct.setText(mArrayList.get(16).CHANGE24HOUR + " (" + mArrayList.get(16).CHANGEPCT24HOUR + "%)");
                        tv_zec_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_zec_pct.setText(mArrayList.get(16).CHANGE24HOUR + " (" + mArrayList.get(16).CHANGEPCT24HOUR + "%)");
                        tv_zec_pct.setTextColor(Color.parseColor("#008000"));
                    }


                    if (mArrayList.get(17).CHANGEPCT24HOUR.contains("-")) {
                        tv_steem_pct.setText(mArrayList.get(17).CHANGE24HOUR + " (" + mArrayList.get(17).CHANGEPCT24HOUR + "%)");
                        tv_steem_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_steem_pct.setText(mArrayList.get(17).CHANGE24HOUR + " (" + mArrayList.get(17).CHANGEPCT24HOUR + "%)");
                        tv_steem_pct.setTextColor(Color.parseColor("#008000"));
                    }


                    if (mArrayList.get(18).CHANGEPCT24HOUR.contains("-")) {
                        tv_xzc_pct.setText(mArrayList.get(18).CHANGE24HOUR + " (" + mArrayList.get(18).CHANGEPCT24HOUR + "%)");
                        tv_xzc_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_xzc_pct.setText(mArrayList.get(18).CHANGE24HOUR + " (" + mArrayList.get(18).CHANGEPCT24HOUR + "%)");
                        tv_xzc_pct.setTextColor(Color.parseColor("#008000"));
                    }


                    if (mArrayList.get(19).CHANGEPCT24HOUR.contains("-")) {
                        tv_storj_pct.setText(mArrayList.get(19).CHANGE24HOUR + " (" + mArrayList.get(19).CHANGEPCT24HOUR + "%)");
                        tv_storj_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_storj_pct.setText(mArrayList.get(19).CHANGE24HOUR + " (" + mArrayList.get(19).CHANGEPCT24HOUR + "%)");
                        tv_storj_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(20).CHANGEPCT24HOUR.contains("-")) {
                        tv_aion_pct.setText(mArrayList.get(20).CHANGE24HOUR + " (" + mArrayList.get(20).CHANGEPCT24HOUR + "%)");
                        tv_aion_pct.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_aion_pct.setText(mArrayList.get(20).CHANGE24HOUR + " (" + mArrayList.get(20).CHANGEPCT24HOUR + "%)");
                        tv_aion_pct.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(21).CHANGEPCT24HOUR.contains("-")) {
                        tv_ht_ptc.setText(mArrayList.get(21).CHANGE24HOUR + " (" + mArrayList.get(21).CHANGEPCT24HOUR + "%)");
                        tv_ht_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ht_ptc.setText(mArrayList.get(21).CHANGE24HOUR + " (" + mArrayList.get(21).CHANGEPCT24HOUR + "%)");
                        tv_ht_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(22).CHANGEPCT24HOUR.contains("-")) {
                        tv_tronix_ptc.setText(mArrayList.get(22).CHANGE24HOUR + " (" + mArrayList.get(22).CHANGEPCT24HOUR + "%)");
                        tv_tronix_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_tronix_ptc.setText(mArrayList.get(22).CHANGE24HOUR + " (" + mArrayList.get(22).CHANGEPCT24HOUR + "%)");
                        tv_tronix_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(23).CHANGEPCT24HOUR.contains("-")) {
                        tv_nano_ptc.setText(mArrayList.get(23).CHANGE24HOUR + " (" + mArrayList.get(23).CHANGEPCT24HOUR + "%)");
                        tv_nano_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_nano_ptc.setText(mArrayList.get(23).CHANGE24HOUR + " (" + mArrayList.get(23).CHANGEPCT24HOUR + "%)");
                        tv_nano_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(24).CHANGEPCT24HOUR.contains("-")) {
                        tv_omisego_ptc.setText(mArrayList.get(24).CHANGE24HOUR + " (" + mArrayList.get(24).CHANGEPCT24HOUR + "%)");
                        tv_omisego_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_omisego_ptc.setText(mArrayList.get(24).CHANGE24HOUR + " (" + mArrayList.get(24).CHANGEPCT24HOUR + "%)");
                        tv_omisego_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(25).CHANGEPCT24HOUR.contains("-")) {
                        tv_elastos_ptc.setText(mArrayList.get(25).CHANGE24HOUR + " (" + mArrayList.get(25).CHANGEPCT24HOUR + "%)");
                        tv_elastos_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_elastos_ptc.setText(mArrayList.get(25).CHANGE24HOUR + " (" + mArrayList.get(25).CHANGEPCT24HOUR + "%)");
                        tv_elastos_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(26).CHANGEPCT24HOUR.contains("-")) {
                        tv_binance_ptc.setText(mArrayList.get(26).CHANGE24HOUR + " (" + mArrayList.get(26).CHANGEPCT24HOUR + "%)");
                        tv_binance_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_binance_ptc.setText(mArrayList.get(26).CHANGE24HOUR + " (" + mArrayList.get(26).CHANGEPCT24HOUR + "%)");
                        tv_binance_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(27).CHANGEPCT24HOUR.contains("-")) {
                        tv_vechain_ptc.setText(mArrayList.get(27).CHANGE24HOUR + " (" + mArrayList.get(27).CHANGEPCT24HOUR + "%)");
                        tv_vechain_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_vechain_ptc.setText(mArrayList.get(27).CHANGE24HOUR + " (" + mArrayList.get(27).CHANGEPCT24HOUR + "%)");
                        tv_vechain_ptc.setTextColor(Color.parseColor("#008000"));
                    }


                    if (mArrayList.get(28).CHANGEPCT24HOUR.contains("-")) {
                        tv_zclassic_ptc.setText(mArrayList.get(28).CHANGE24HOUR + " (" + mArrayList.get(28).CHANGEPCT24HOUR + "%)");
                        tv_zclassic_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_zclassic_ptc.setText(mArrayList.get(28).CHANGE24HOUR + " (" + mArrayList.get(28).CHANGEPCT24HOUR + "%)");
                        tv_zclassic_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(29).CHANGEPCT24HOUR.contains("-")) {
                        tv_digix_ptc.setText(mArrayList.get(29).CHANGE24HOUR + " (" + mArrayList.get(29).CHANGEPCT24HOUR + "%)");
                        tv_digix_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_digix_ptc.setText(mArrayList.get(29).CHANGE24HOUR + " (" + mArrayList.get(29).CHANGEPCT24HOUR + "%)");
                        tv_digix_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(30).CHANGEPCT24HOUR.contains("-")) {
                        tv_odyssey_ptc.setText(mArrayList.get(30).CHANGE24HOUR + " (" + mArrayList.get(30).CHANGEPCT24HOUR + "%)");
                        tv_odyssey_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_odyssey_ptc.setText(mArrayList.get(30).CHANGE24HOUR + " (" + mArrayList.get(30).CHANGEPCT24HOUR + "%)");
                        tv_odyssey_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(31).CHANGEPCT24HOUR.contains("-")) {
                        tv_blockmason_ptc.setText(mArrayList.get(31).CHANGE24HOUR + " (" + mArrayList.get(31).CHANGEPCT24HOUR + "%)");
                        tv_blockmason_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_blockmason_ptc.setText(mArrayList.get(31).CHANGE24HOUR + " (" + mArrayList.get(31).CHANGEPCT24HOUR + "%)");
                        tv_blockmason_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(32).CHANGEPCT24HOUR.contains("-")) {
                        tv_lunyr_ptc.setText(mArrayList.get(32).CHANGE24HOUR + " (" + mArrayList.get(32).CHANGEPCT24HOUR + "%)");
                        tv_lunyr_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_lunyr_ptc.setText(mArrayList.get(32).CHANGE24HOUR + " (" + mArrayList.get(32).CHANGEPCT24HOUR + "%)");
                        tv_lunyr_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(33).CHANGEPCT24HOUR.contains("-")) {
                        tv_iostoken_ptc.setText(mArrayList.get(33).CHANGE24HOUR + " (" + mArrayList.get(33).CHANGEPCT24HOUR + "%)");
                        tv_iostoken_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iostoken_ptc.setText(mArrayList.get(33).CHANGE24HOUR + " (" + mArrayList.get(33).CHANGEPCT24HOUR + "%)");
                        tv_iostoken_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(34).CHANGEPCT24HOUR.contains("-")) {
                        tv_hshare_ptc.setText(mArrayList.get(34).CHANGE24HOUR + " (" + mArrayList.get(34).CHANGEPCT24HOUR + "%)");
                        tv_hshare_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_hshare_ptc.setText(mArrayList.get(34).CHANGE24HOUR + " (" + mArrayList.get(34).CHANGEPCT24HOUR + "%)");
                        tv_hshare_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(35).CHANGEPCT24HOUR.contains("-")) {
                        tv_iconproject_ptc.setText(mArrayList.get(35).CHANGE24HOUR + " (" + mArrayList.get(35).CHANGEPCT24HOUR + "%)");
                        tv_iconproject_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iconproject_ptc.setText(mArrayList.get(35).CHANGE24HOUR + " (" + mArrayList.get(35).CHANGEPCT24HOUR + "%)");
                        tv_iconproject_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(36).CHANGEPCT24HOUR.contains("-")) {
                        tv_lisk_ptc.setText(mArrayList.get(36).CHANGE24HOUR + " (" + mArrayList.get(36).CHANGEPCT24HOUR + "%)");
                        tv_lisk_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_lisk_ptc.setText(mArrayList.get(36).CHANGE24HOUR + " (" + mArrayList.get(36).CHANGEPCT24HOUR + "%)");
                        tv_lisk_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(37).CHANGEPCT24HOUR.contains("-")) {
                        tv_nebilo_ptc.setText(mArrayList.get(37).CHANGE24HOUR + " (" + mArrayList.get(37).CHANGEPCT24HOUR + "%)");
                        tv_nebilo_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_nebilo_ptc.setText(mArrayList.get(37).CHANGE24HOUR + " (" + mArrayList.get(37).CHANGEPCT24HOUR + "%)");
                        tv_nebilo_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(38).CHANGEPCT24HOUR.contains("-")) {
                        tv_wavess_ptc.setText(mArrayList.get(38).CHANGE24HOUR + " (" + mArrayList.get(38).CHANGEPCT24HOUR + "%)");
                        tv_wavess_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_wavess_ptc.setText(mArrayList.get(38).CHANGE24HOUR + " (" + mArrayList.get(38).CHANGEPCT24HOUR + "%)");
                        tv_wavess_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(39).CHANGEPCT24HOUR.contains("-")) {
                        tv_bluzello_ptc.setText(mArrayList.get(39).CHANGE24HOUR + " (" + mArrayList.get(39).CHANGEPCT24HOUR + "%)");
                        tv_bluzello_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_bluzello_ptc.setText(mArrayList.get(39).CHANGE24HOUR + " (" + mArrayList.get(39).CHANGEPCT24HOUR + "%)");
                        tv_bluzello_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(40).CHANGEPCT24HOUR.contains("-")) {
                        tv_inkk_ptc.setText(mArrayList.get(40).CHANGE24HOUR + " (" + mArrayList.get(40).CHANGEPCT24HOUR + "%)");
                        tv_inkk_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_inkk_ptc.setText(mArrayList.get(40).CHANGE24HOUR + " (" + mArrayList.get(40).CHANGEPCT24HOUR + "%)");
                        tv_inkk_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(41).CHANGEPCT24HOUR.contains("-")) {
                        tv_adex_ptc.setText(mArrayList.get(41).CHANGE24HOUR + " (" + mArrayList.get(41).CHANGEPCT24HOUR + "%)");
                        tv_adex_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_adex_ptc.setText(mArrayList.get(41).CHANGE24HOUR + " (" + mArrayList.get(41).CHANGEPCT24HOUR + "%)");
                        tv_adex_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(42).CHANGEPCT24HOUR.contains("-")) {
                        tv_verge_ptc.setText(mArrayList.get(42).CHANGE24HOUR + " (" + mArrayList.get(42).CHANGEPCT24HOUR + "%)");
                        tv_verge_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_verge_ptc.setText(mArrayList.get(42).CHANGE24HOUR + " (" + mArrayList.get(42).CHANGEPCT24HOUR + "%)");
                        tv_verge_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(43).CHANGEPCT24HOUR.contains("-")) {
                        tv_metal_ptc.setText(mArrayList.get(43).CHANGE24HOUR + " (" + mArrayList.get(43).CHANGEPCT24HOUR + "%)");
                        tv_metal_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_metal_ptc.setText(mArrayList.get(43).CHANGE24HOUR + " (" + mArrayList.get(43).CHANGEPCT24HOUR + "%)");
                        tv_metal_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(44).CHANGEPCT24HOUR.contains("-")) {
                        tv_sirinlabs_ptc.setText(mArrayList.get(44).CHANGE24HOUR + " (" + mArrayList.get(44).CHANGEPCT24HOUR + "%)");
                        tv_sirinlabs_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_sirinlabs_ptc.setText(mArrayList.get(44).CHANGE24HOUR + " (" + mArrayList.get(44).CHANGEPCT24HOUR + "%)");
                        tv_sirinlabs_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(45).CHANGEPCT24HOUR.contains("-")) {
                        tv_ox_ptc.setText(mArrayList.get(45).CHANGE24HOUR + " (" + mArrayList.get(45).CHANGEPCT24HOUR + "%)");
                        tv_ox_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ox_ptc.setText(mArrayList.get(45).CHANGE24HOUR + " (" + mArrayList.get(45).CHANGEPCT24HOUR + "%)");
                        tv_ox_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(46).CHANGEPCT24HOUR.contains("-")) {
                        tv_iex_ptc.setText(mArrayList.get(46).CHANGE24HOUR + " (" + mArrayList.get(46).CHANGEPCT24HOUR + "%)");
                        tv_iex_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iex_ptc.setText(mArrayList.get(46).CHANGE24HOUR + " (" + mArrayList.get(46).CHANGEPCT24HOUR + "%)");
                        tv_iex_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(47).CHANGEPCT24HOUR.contains("-")) {
                        tv_thetaa_ptc.setText(mArrayList.get(47).CHANGE24HOUR + " (" + mArrayList.get(47).CHANGEPCT24HOUR + "%)");
                        tv_thetaa_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_thetaa_ptc.setText(mArrayList.get(47).CHANGE24HOUR + " (" + mArrayList.get(47).CHANGEPCT24HOUR + "%)");
                        tv_thetaa_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(48).CHANGEPCT24HOUR.contains("-")) {
                        tv_bitcoindimand_ptc.setText(mArrayList.get(48).CHANGE24HOUR + " (" + mArrayList.get(48).CHANGEPCT24HOUR + "%)");
                        tv_bitcoindimand_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_bitcoindimand_ptc.setText(mArrayList.get(48).CHANGE24HOUR + " (" + mArrayList.get(48).CHANGEPCT24HOUR + "%)");
                        tv_bitcoindimand_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(49).CHANGEPCT24HOUR.contains("-")) {
                        tv_openanx_ptc.setText(mArrayList.get(49).CHANGE24HOUR + " (" + mArrayList.get(49).CHANGEPCT24HOUR + "%)");
                        tv_openanx_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_openanx_ptc.setText(mArrayList.get(49).CHANGE24HOUR + " (" + mArrayList.get(49).CHANGEPCT24HOUR + "%)");
                        tv_openanx_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(50).CHANGEPCT24HOUR.contains("-")) {
                        tv_aelf_ptc.setText(mArrayList.get(50).CHANGE24HOUR + " (" + mArrayList.get(50).CHANGEPCT24HOUR + "%)");
                        tv_aelf_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_aelf_ptc.setText(mArrayList.get(50).CHANGE24HOUR + " (" + mArrayList.get(50).CHANGEPCT24HOUR + "%)");
                        tv_aelf_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(51).CHANGEPCT24HOUR.contains("-")) {
                        tv_insexosystem_ptc.setText(mArrayList.get(51).CHANGE24HOUR + " (" + mArrayList.get(51).CHANGEPCT24HOUR + "%)");
                        tv_insexosystem_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_insexosystem_ptc.setText(mArrayList.get(51).CHANGE24HOUR + " (" + mArrayList.get(51).CHANGEPCT24HOUR + "%)");
                        tv_insexosystem_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(52).CHANGEPCT24HOUR.contains("-")) {
                        tv_zilliqa_ptc.setText(mArrayList.get(52).CHANGE24HOUR + " (" + mArrayList.get(52).CHANGEPCT24HOUR + "%)");
                        tv_zilliqa_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_zilliqa_ptc.setText(mArrayList.get(52).CHANGE24HOUR + " (" + mArrayList.get(52).CHANGEPCT24HOUR + "%)");
                        tv_zilliqa_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(53).CHANGEPCT24HOUR.contains("-")) {
                        tv_aeternity_ptc.setText(mArrayList.get(53).CHANGE24HOUR + " (" + mArrayList.get(53).CHANGEPCT24HOUR + "%)");
                        tv_aeternity_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_aeternity_ptc.setText(mArrayList.get(53).CHANGE24HOUR + " (" + mArrayList.get(53).CHANGEPCT24HOUR + "%)");
                        tv_aeternity_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(54).CHANGEPCT24HOUR.contains("-")) {
                        tv_propy_ptc.setText(mArrayList.get(54).CHANGE24HOUR + " (" + mArrayList.get(54).CHANGEPCT24HOUR + "%)");
                        tv_propy_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_propy_ptc.setText(mArrayList.get(54).CHANGE24HOUR + " (" + mArrayList.get(54).CHANGEPCT24HOUR + "%)");
                        tv_propy_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(55).CHANGEPCT24HOUR.contains("-")) {
                        tv_dogecoin_ptc.setText(mArrayList.get(55).CHANGE24HOUR + " (" + mArrayList.get(55).CHANGEPCT24HOUR + "%)");
                        tv_dogecoin_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_dogecoin_ptc.setText(mArrayList.get(55).CHANGE24HOUR + " (" + mArrayList.get(55).CHANGEPCT24HOUR + "%)");
                        tv_dogecoin_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(56).CHANGEPCT24HOUR.contains("-")) {
                        tv_iotchain_ptc.setText(mArrayList.get(56).CHANGE24HOUR + " (" + mArrayList.get(56).CHANGEPCT24HOUR + "%)");
                        tv_iotchain_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_iotchain_ptc.setText(mArrayList.get(56).CHANGE24HOUR + " (" + mArrayList.get(56).CHANGEPCT24HOUR + "%)");
                        tv_iotchain_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(57).CHANGEPCT24HOUR.contains("-")) {
                        tv_reddcoin_ptc.setText(mArrayList.get(57).CHANGE24HOUR + " (" + mArrayList.get(57).CHANGEPCT24HOUR + "%)");
                        tv_reddcoin_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_reddcoin_ptc.setText(mArrayList.get(57).CHANGE24HOUR + " (" + mArrayList.get(57).CHANGEPCT24HOUR + "%)");
                        tv_reddcoin_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(58).CHANGEPCT24HOUR.contains("-")) {
                        tv_swftcoin_ptc.setText(mArrayList.get(58).CHANGE24HOUR + " (" + mArrayList.get(58).CHANGEPCT24HOUR + "%)");
                        tv_swftcoin_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_swftcoin_ptc.setText(mArrayList.get(58).CHANGE24HOUR + " (" + mArrayList.get(58).CHANGEPCT24HOUR + "%)");
                        tv_swftcoin_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(59).CHANGEPCT24HOUR.contains("-")) {
                        tv_monaco_ptc.setText(mArrayList.get(59).CHANGE24HOUR + " (" + mArrayList.get(59).CHANGEPCT24HOUR + "%)");
                        tv_monaco_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_monaco_ptc.setText(mArrayList.get(59).CHANGE24HOUR + " (" + mArrayList.get(59).CHANGEPCT24HOUR + "%)");
                        tv_monaco_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(60).CHANGEPCT24HOUR.contains("-")) {
                        tv_aeron_ptc.setText(mArrayList.get(60).CHANGE24HOUR + " (" + mArrayList.get(60).CHANGEPCT24HOUR + "%)");
                        tv_aeron_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_aeron_ptc.setText(mArrayList.get(60).CHANGE24HOUR + " (" + mArrayList.get(60).CHANGEPCT24HOUR + "%)");
                        tv_aeron_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(61).CHANGEPCT24HOUR.contains("-")) {
                        tv_medicakchain_ptc.setText(mArrayList.get(61).CHANGE24HOUR + " (" + mArrayList.get(61).CHANGEPCT24HOUR + "%)");
                        tv_medicakchain_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_medicakchain_ptc.setText(mArrayList.get(61).CHANGE24HOUR + " (" + mArrayList.get(61).CHANGEPCT24HOUR + "%)");
                        tv_medicakchain_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(62).CHANGEPCT24HOUR.contains("-")) {
                        tv_breadtoken_ptc.setText(mArrayList.get(62).CHANGE24HOUR + " (" + mArrayList.get(62).CHANGEPCT24HOUR + "%)");
                        tv_breadtoken_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_breadtoken_ptc.setText(mArrayList.get(62).CHANGE24HOUR + " (" + mArrayList.get(62).CHANGEPCT24HOUR + "%)");
                        tv_breadtoken_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(63).CHANGEPCT24HOUR.contains("-")) {
                        tv_santiment_ptc.setText(mArrayList.get(63).CHANGE24HOUR + " (" + mArrayList.get(63).CHANGEPCT24HOUR + "%)");
                        tv_santiment_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_santiment_ptc.setText(mArrayList.get(63).CHANGE24HOUR + " (" + mArrayList.get(63).CHANGEPCT24HOUR + "%)");
                        tv_santiment_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(64).CHANGEPCT24HOUR.contains("-")) {
                        tv_nebulas_ptc.setText(mArrayList.get(64).CHANGE24HOUR + " (" + mArrayList.get(64).CHANGEPCT24HOUR + "%)");
                        tv_nebulas_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_nebulas_ptc.setText(mArrayList.get(64).CHANGE24HOUR + " (" + mArrayList.get(64).CHANGEPCT24HOUR + "%)");
                        tv_nebulas_ptc.setTextColor(Color.parseColor("#008000"));
                    }
                    if (mArrayList.get(65).CHANGEPCT24HOUR.contains("-")) {
                        tv_genesis_ptc.setText(mArrayList.get(65).CHANGE24HOUR + " (" + mArrayList.get(65).CHANGEPCT24HOUR + "%)");
                        tv_genesis_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_genesis_ptc.setText(mArrayList.get(65).CHANGE24HOUR + " (" + mArrayList.get(65).CHANGEPCT24HOUR + "%)");
                        tv_genesis_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(66).CHANGEPCT24HOUR.contains("-")) {
                        tv_qunqun_ptc.setText(mArrayList.get(66).CHANGE24HOUR + " (" + mArrayList.get(66).CHANGEPCT24HOUR + "%)");
                        tv_qunqun_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_qunqun_ptc.setText(mArrayList.get(66).CHANGE24HOUR + " (" + mArrayList.get(66).CHANGEPCT24HOUR + "%)");
                        tv_qunqun_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(67).CHANGEPCT24HOUR.contains("-")) {
                        tv_waltonchain_ptc.setText(mArrayList.get(67).CHANGE24HOUR + " (" + mArrayList.get(67).CHANGEPCT24HOUR + "%)");
                        tv_waltonchain_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_waltonchain_ptc.setText(mArrayList.get(67).CHANGE24HOUR + " (" + mArrayList.get(67).CHANGEPCT24HOUR + "%)");
                        tv_waltonchain_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(68).CHANGEPCT24HOUR.contains("-")) {
                        tv_factoids_ptc.setText(mArrayList.get(68).CHANGE24HOUR + " (" + mArrayList.get(68).CHANGEPCT24HOUR + "%)");
                        tv_factoids_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_factoids_ptc.setText(mArrayList.get(68).CHANGE24HOUR + " (" + mArrayList.get(68).CHANGEPCT24HOUR + "%)");
                        tv_factoids_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(69).CHANGEPCT24HOUR.contains("-")) {
                        tv_coinDash_ptc.setText(mArrayList.get(69).CHANGE24HOUR + " (" + mArrayList.get(69).CHANGEPCT24HOUR + "%)");
                        tv_coinDash_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_coinDash_ptc.setText(mArrayList.get(69).CHANGE24HOUR + " (" + mArrayList.get(69).CHANGEPCT24HOUR + "%)");
                        tv_coinDash_ptc.setTextColor(Color.parseColor("#008000"));
                    }

                    if (mArrayList.get(70).CHANGEPCT24HOUR.contains("-")) {
                        tv_ripio_ptc.setText(mArrayList.get(70).CHANGE24HOUR + " (" + mArrayList.get(70).CHANGEPCT24HOUR + "%)");
                        tv_ripio_ptc.setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        tv_ripio_ptc.setText(mArrayList.get(70).CHANGE24HOUR + " (" + mArrayList.get(70).CHANGEPCT24HOUR + "%)");
                        tv_ripio_ptc.setTextColor(Color.parseColor("#008000"));
                    }
                }

            } else {
//                utils.showAlertMessage(Home_Activity.this, msg);
            }
        }
    }

}
