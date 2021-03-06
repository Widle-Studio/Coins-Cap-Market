package com.widle.coinscap.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import com.widle.coinscap.Acknowledgement1_Activity;
import com.widle.coinscap.Acknowledgement_Activity;
import com.widle.coinscap.Adapter.Currency_Adapter;
import com.widle.coinscap.Coins_Activity;
import com.widle.coinscap.Coins_Detail_Activity;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Exchange_Activity;
import com.widle.coinscap.ICO_Activity;
import com.widle.coinscap.Price_Alert1_Activity;
import com.widle.coinscap.Profile_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Setting_Activity;
import com.widle.coinscap.Splash_Activity;
import com.widle.coinscap.TopList_Activity;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.Currency;
import com.widle.coinscap.Utils.Model.Fav_Coins;
import com.widle.coinscap.Utils.Model.News;
import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;
import com.widle.coinscap.WSCallerVersionListener;

/**
 * Created by imperial-web on 2/26/2018.
 */

public class Coins_Fragment extends Fragment implements View.OnClickListener, DataClient.OnDataChangedListener,
        MessageClient.OnMessageReceivedListener, CapabilityClient.OnCapabilityChangedListener,
        NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private TextView tv_app_name, tv_btc_name, tv_eth_name, tv_bch_name, tv_xrp_name, tv_ltc_name, tv_ada_name, tv_iot_name, tv_dash_name, tv_xem_name,
            tv_xmr_name, tv_eos_name, tv_btg_name, tv_qtum_name, tv_xlm_name, tv_neo_name, tv_etc_name, tv_zec_name, tv_steem_name,
            tv_xzc_name, tv_storj_name, tv_aion_name, tv_ht_name, tv_tronix_name, tv_nano_name, tv_omisego_name, tv_elastos_name, tv_binance_name, tv_vechain_name,
            tv_zclassic_name, tv_digix_name, tv_odyssey_name, tv_blockmason_name, tv_lunyr_name, tv_iostoken_name, tv_hshare_name, tv_iconproject_name, tv_lisk_name,
            tv_nebilo_name, tv_wavess_name, tv_bluzello_name, tv_inkk_name, tv_adex_name, tv_verge_name, tv_metal_name, tv_sirinlabs_name, tv_ox_name, tv_iex_name,
            tv_thetaa_name, tv_bitcoindimand_name, tv_openanx_name, tv_aelf_name, tv_insexosystem_name, tv_zilliqa_name, tv_aeternity_name, tv_propy_name, tv_dogecoin_name,
            tv_iotchain_name, tv_reddcoin_name, tv_swftcoin_name, tv_monaco_name, tv_aeron_name, tv_medicakchain_name, tv_breadtoken_name, tv_santiment_name, tv_nebulas_name,
            tv_genesis_name, tv_qunqun_name, tv_waltonchain_name, tv_factoids_name, tv_coinDash_name, tv_ripio_name, tv_main;

    private TextView tv_btc_sf, tv_eth_sf, tv_bch_sf, tv_xrp_sf, tv_ltc_sf, tv_ada_sf, tv_iot_sf, tv_dash_sf, tv_xem_sf, tv_xmr_sf, tv_eos_sf,
            tv_btg_sf, tv_qtum_sf, tv_xlm_sf, tv_neo_sf, tv_etc_sf, tv_zec_sf, tv_steem_sf, tv_xzc_sf, tv_storj_sf, tv_aion_sf, tv_ht_sf, tv_tronix_sf,
            tv_nano_sf, tv_omisego_sf, tv_elastos_sf, tv_binance_sf, tv_vechain_sf, tv_zclassic_sf, tv_digix_sf, tv_odyssey_sf, tv_blockmason_sf, tv_lunyr_sf,
            tv_iostoken_sf, tv_hshare_sf, tv_iconproject_sf, tv_lisk_sf, tv_nebilo_sf, tv_wavess_sf, tv_bluzello_sf, tv_inkk_sf, tv_adex_sf, tv_verge_sf, tv_metal_sf,
            tv_sirinlabs_sf, tv_ox_sf, tv_iex_sf, tv_thetaa_sf, tv_bitcoindimand_sf, tv_openanx_sf, tv_aelf_sf, tv_insexosystem_sf, tv_zilliqa_sf, tv_aeternity_sf, tv_propy_sf,
            tv_dogecoin_sf, tv_iotchain_sf, tv_reddcoin_sf, tv_swftcoin_sf, tv_monaco_sf, tv_aeron_sf, tv_medicakchain_sf, tv_breadtoken_sf, tv_santiment_sf, tv_nebulas_sf, tv_genesis_sf,
            tv_qunqun_sf, tv_waltonchain_sf, tv_factoids_sf, tv_coinDash_sf, tv_ripio_sf;

    private TextView tv_btc_mc, tv_eth_mc, tv_bch_mc, tv_xrp_mc, tv_ltc_mc, tv_ada_mc, tv_iot_mc, tv_dash_mc,
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
    ;

    private TextView tv_btc_pct, tv_eth_pct, tv_bch_pct, tv_xrp_pct, tv_ltc_pct, tv_ada_pct, tv_iot_pct, tv_dash_pct,
            tv_xem_pct, tv_xmr_pct, tv_eos_pct, tv_btg_pct, tv_qtum_pct, tv_xlm_pct, tv_neo_pct, tv_etc_pct, tv_zec_pct, tv_steem_pct,
            tv_xzc_pct, tv_storj_pct, tv_aion_pct, tv_ht_ptc, tv_tronix_ptc, tv_nano_ptc, tv_omisego_ptc, tv_elastos_ptc, tv_binance_ptc, tv_vechain_ptc,
            tv_zclassic_ptc, tv_digix_ptc, tv_odyssey_ptc, tv_blockmason_ptc, tv_lunyr_ptc, tv_iostoken_ptc, tv_hshare_ptc, tv_iconproject_ptc, tv_lisk_ptc, tv_nebilo_ptc,
            tv_wavess_ptc, tv_bluzello_ptc, tv_inkk_ptc, tv_adex_ptc, tv_verge_ptc, tv_metal_ptc, tv_sirinlabs_ptc, tv_ox_ptc, tv_iex_ptc, tv_thetaa_ptc, tv_bitcoindimand_ptc,
            tv_openanx_ptc, tv_aelf_ptc, tv_insexosystem_ptc, tv_zilliqa_ptc, tv_aeternity_ptc, tv_propy_ptc, tv_dogecoin_ptc, tv_iotchain_ptc,
            tv_reddcoin_ptc, tv_swftcoin_ptc, tv_monaco_ptc, tv_aeron_ptc, tv_medicakchain_ptc, tv_breadtoken_ptc, tv_santiment_ptc, tv_nebulas_ptc, tv_genesis_ptc, tv_qunqun_ptc,
            tv_waltonchain_ptc, tv_factoids_ptc, tv_coinDash_ptc, tv_ripio_ptc;

    private LinearLayout ll_btc, ll_eth, ll_bch, ll_xrp, ll_ltc, ll_ada, ll_iot, ll_dash, ll_xem, ll_xmr, ll_eos, ll_btg, ll_qtum,
            ll_xlm, ll_neo, ll_etc, ll_zec, ll_steem, ll_xzc, ll_storj, ll_aion, ll_ht, ll_trx, ll_xrb, ll_omg, ll_ela, ll_bnb, ll_ven,
            ll_zcl, ll_dgd, ll_ocn, ll_bcpt, ll_lun, ll_iost, ll_hsr, ll_icx, ll_lsk, ll_nebl, ll_waves, ll_blz, ll_ink, ll_adx, ll_xvg,
            ll_mtl, ll_srn, ll_zrx, ll_rlc, ll_theta, ll_bcd, ll_oax, ll_elf, ll_ins, ll_ZIL, ll_ae, ll_pro, ll_doge, ll_itc, ll_rdd, ll_swftc,
            ll_mco, ll_arn, ll_mtn, ll_brd, ll_san, ll_nas, ll_gvt, ll_qun, ll_wtc, ll_fct, ll_cdt, ll_rcn;

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

    private String btc = "1", eth = "1", bch = "1", xrp = "1", ltc = "1", ada = "1", iot = "1", dash = "1", xem = "1", xmr = "1", eos = "1", btg = "1", qtum = "1", xlm = "1", neo = "1", etc = "1", zec = "1", steem = "1", xzc = "1",
    storj = "1", aion = "1", ht = "1", trx = "1", xrb = "1", omg = "1", ela = "1", bnb = "1", ven = "1", zcl = "1", dgd = "1", ocn = "1", bcpt = "1", lun = "1", iost = "1", hsr = "1", icx = "1", lsk = "1", nebl = "1", waves = "1", blz = "1",
    ink = "1", adx = "1", xvg = "1", mtl = "1", srn = "1", zrx = "1", rlc = "1", theta = "1", bcd = "1", oax = "1", elf = "1", ins = "1", zil = "1", ae = "1", pro = "1", doge = "1", itc = "1", rdd = "1", swftc = "1", mco = "1", arn = "1", mtn = "1",
    brd = "1", san = "1", nas = "1", gvt = "1", qun = "1", wtc = "1", fct = "1", cdt = "1", rcn = "1";

    private TextView tv_user_name;

    private CircleImageView iv_profilepic;

    private String pro_pic = "", device_id = "", user_id = "", user_name = "";

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();

    ArrayList<coin> mArrayList = new ArrayList<>();

    SharedPreferences preferences;

    public ProgressDialog mProgressDialog;

    private static DatabaseHandler db;

    ArrayList<coin> mDbArrayList = new ArrayList<>();

    private LinearLayout ll_main;

    private Toolbar mToolbar;

    DrawerLayout drawer;

    boolean isForceUpdate = true;

    private GoogleSignInOptions gso;

    private int RC_SIGN_IN = 100;

    // google plus
    private GoogleApiClient mGoogleApiClient;

    private Button google_login, google_logout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coins, container, false);

//        new ForceUpdateChecker(getActivity().getApplicationContext(), this).execute();

        preferences = getActivity().getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        device_id = preferences.getString(General.PREFS_Device_id, "");
        user_name = preferences.getString(General.PREFS_User_Name, "");


        pro_pic = preferences.getString(General.PREFS_Picture, "");

        db = new DatabaseHandler(getActivity());

        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        init(view);

        mArrayList = db.getallmaincoins();
        Log.e("mArrayList", "" + mArrayList.size());
        load();

//-------------------------------------------------CHACK_VALUE_FROM_DATABASE----------------------------------------------------------
        mDbArrayList = db.getallcoins();

        if (!mDbArrayList.isEmpty()) {

            for (int i = 0; i < mDbArrayList.size(); i++) {

                if (mDbArrayList.get(i).SYMBOL.equals("BTC")) {
                    iv_fav.setVisibility(View.VISIBLE);
                    iv_un_fav.setVisibility(View.GONE);
                    btc = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ETH")) {
                    iv_fav1.setVisibility(View.VISIBLE);
                    iv_un_fav1.setVisibility(View.GONE);
                    eth = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("BCH")) {
                    iv_fav2.setVisibility(View.VISIBLE);
                    iv_un_fav2.setVisibility(View.GONE);
                    bch = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("XRP")) {
                    iv_fav3.setVisibility(View.VISIBLE);
                    iv_un_fav3.setVisibility(View.GONE);
                    xrp = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("LTC")) {
                    iv_fav4.setVisibility(View.VISIBLE);
                    iv_un_fav4.setVisibility(View.GONE);
                    ltc = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ADA")) {
                    iv_fav5.setVisibility(View.VISIBLE);
                    iv_un_fav5.setVisibility(View.GONE);
                    ada = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("IOT")) {
                    iv_fav6.setVisibility(View.VISIBLE);
                    iv_un_fav6.setVisibility(View.GONE);
                    iot = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("DASH")) {
                    iv_fav7.setVisibility(View.VISIBLE);
                    iv_un_fav7.setVisibility(View.GONE);
                    dash = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("XEM")) {
                    iv_fav8.setVisibility(View.VISIBLE);
                    iv_un_fav8.setVisibility(View.GONE);
                    xem = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("XMR")) {
                    iv_fav9.setVisibility(View.VISIBLE);
                    iv_un_fav9.setVisibility(View.GONE);
                    xmr = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("EOS")) {
                    iv_fav10.setVisibility(View.VISIBLE);
                    iv_un_fav10.setVisibility(View.GONE);
                    eos = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("BTG")) {
                    iv_fav11.setVisibility(View.VISIBLE);
                    iv_un_fav11.setVisibility(View.GONE);
                    btg = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("QTUM")) {
                    iv_fav12.setVisibility(View.VISIBLE);
                    iv_un_fav12.setVisibility(View.GONE);
                    qtum = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("XLM")) {
                    iv_fav13.setVisibility(View.VISIBLE);
                    iv_un_fav13.setVisibility(View.GONE);
                    xlm = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("NEO")) {
                    iv_fav14.setVisibility(View.VISIBLE);
                    iv_un_fav14.setVisibility(View.GONE);
                    neo = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ETC")) {
                    iv_fav15.setVisibility(View.VISIBLE);
                    iv_un_fav15.setVisibility(View.GONE);
                    etc = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ZEC")) {
                    iv_fav16.setVisibility(View.VISIBLE);
                    iv_un_fav16.setVisibility(View.GONE);
                    zec = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("STEEM")) {
                    iv_fav17.setVisibility(View.VISIBLE);
                    iv_un_fav17.setVisibility(View.GONE);
                    steem = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("XZC")) {
                    iv_fav18.setVisibility(View.VISIBLE);
                    iv_un_fav18.setVisibility(View.GONE);
                    xzc = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("STORJ")) {
                    iv_fav19.setVisibility(View.VISIBLE);
                    iv_un_fav19.setVisibility(View.GONE);
                    storj = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("AION")) {
                    iv_fav20.setVisibility(View.VISIBLE);
                    iv_un_fav20.setVisibility(View.GONE);
                    aion = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("HT")) {
                    iv_fav21.setVisibility(View.VISIBLE);
                    iv_un_fav21.setVisibility(View.GONE);
                    ht = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("TRX")) {
                    iv_fav22.setVisibility(View.VISIBLE);
                    iv_un_fav22.setVisibility(View.GONE);
                    trx = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("XRB")) {
                    iv_fav23.setVisibility(View.VISIBLE);
                    iv_un_fav23.setVisibility(View.GONE);
                    xrb = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("OMG")) {
                    iv_fav24.setVisibility(View.VISIBLE);
                    iv_un_fav24.setVisibility(View.GONE);
                    omg = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ELA")) {
                    iv_fav25.setVisibility(View.VISIBLE);
                    iv_un_fav25.setVisibility(View.GONE);
                    ela = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("BNB")) {
                    iv_fav26.setVisibility(View.VISIBLE);
                    iv_un_fav26.setVisibility(View.GONE);

                } else if (mDbArrayList.get(i).SYMBOL.equals("VEN")) {
                    iv_fav27.setVisibility(View.VISIBLE);
                    iv_un_fav27.setVisibility(View.GONE);
                    ven = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ZCL")) {
                    iv_fav28.setVisibility(View.VISIBLE);
                    iv_un_fav28.setVisibility(View.GONE);
                    zcl = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("DGD")) {
                    iv_fav29.setVisibility(View.VISIBLE);
                    iv_un_fav29.setVisibility(View.GONE);
                    dgd = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("OCN")) {
                    iv_fav30.setVisibility(View.VISIBLE);
                    iv_un_fav30.setVisibility(View.GONE);
                    ocn = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("BCPT")) {
                    iv_fav31.setVisibility(View.VISIBLE);
                    iv_un_fav31.setVisibility(View.GONE);
                    bcpt = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("LUN")) {
                    iv_fav32.setVisibility(View.VISIBLE);
                    iv_un_fav32.setVisibility(View.GONE);
                    lun = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("IOST")) {
                    iv_fav33.setVisibility(View.VISIBLE);
                    iv_un_fav33.setVisibility(View.GONE);
                    iost = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("HSR")) {
                    iv_fav34.setVisibility(View.VISIBLE);
                    iv_un_fav34.setVisibility(View.GONE);
                    hsr = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ICX")) {
                    iv_fav35.setVisibility(View.VISIBLE);
                    iv_un_fav35.setVisibility(View.GONE);
                    icx = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("LSK")) {
                    iv_fav36.setVisibility(View.VISIBLE);
                    iv_un_fav36.setVisibility(View.GONE);
                    lsk = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("NEBL")) {
                    iv_fav37.setVisibility(View.VISIBLE);
                    iv_un_fav37.setVisibility(View.GONE);
                    nebl = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("WAVES")) {
                    iv_fav38.setVisibility(View.VISIBLE);
                    iv_un_fav38.setVisibility(View.GONE);
                    waves = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("BLZ")) {
                    iv_fav39.setVisibility(View.VISIBLE);
                    iv_un_fav39.setVisibility(View.GONE);
                    blz = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("INK")) {
                    iv_fav40.setVisibility(View.VISIBLE);
                    iv_un_fav40.setVisibility(View.GONE);
                    ink = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ADX")) {
                    iv_fav41.setVisibility(View.VISIBLE);
                    iv_un_fav41.setVisibility(View.GONE);
                    adx = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("XVG")) {
                    iv_fav42.setVisibility(View.VISIBLE);
                    iv_un_fav42.setVisibility(View.GONE);
                    xvg = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("MTL")) {
                    iv_fav43.setVisibility(View.VISIBLE);
                    iv_un_fav43.setVisibility(View.GONE);
                    mtl = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("SRN")) {
                    iv_fav44.setVisibility(View.VISIBLE);
                    iv_un_fav44.setVisibility(View.GONE);
                    srn = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ZRX")) {
                    iv_fav45.setVisibility(View.VISIBLE);
                    iv_un_fav45.setVisibility(View.GONE);
                    zrx = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("RLC")) {
                    iv_fav46.setVisibility(View.VISIBLE);
                    iv_un_fav46.setVisibility(View.GONE);
                    rlc = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("THETA")) {
                    iv_fav47.setVisibility(View.VISIBLE);
                    iv_un_fav47.setVisibility(View.GONE);
                    theta = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("BCD")) {
                    iv_fav48.setVisibility(View.VISIBLE);
                    iv_un_fav48.setVisibility(View.GONE);
                    bcd = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("OAX")) {
                    iv_fav49.setVisibility(View.VISIBLE);
                    iv_un_fav49.setVisibility(View.GONE);
                    oax = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ELF")) {
                    iv_fav50.setVisibility(View.VISIBLE);
                    iv_un_fav50.setVisibility(View.GONE);
                    elf = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("INS")) {
                    iv_fav51.setVisibility(View.VISIBLE);
                    iv_un_fav51.setVisibility(View.GONE);
                    ins = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ZIL")) {
                    iv_fav52.setVisibility(View.VISIBLE);
                    iv_un_fav52.setVisibility(View.GONE);
                    zil = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("AE")) {
                    iv_fav53.setVisibility(View.VISIBLE);
                    iv_un_fav53.setVisibility(View.GONE);
                    ae = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("PRO")) {
                    iv_fav54.setVisibility(View.VISIBLE);
                    iv_un_fav54.setVisibility(View.GONE);
                    pro = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("DOGE")) {
                    iv_fav55.setVisibility(View.VISIBLE);
                    iv_un_fav55.setVisibility(View.GONE);
                    doge = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ITC")) {
                    iv_fav56.setVisibility(View.VISIBLE);
                    iv_un_fav56.setVisibility(View.GONE);
                    itc = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("RDD")) {
                    iv_fav57.setVisibility(View.VISIBLE);
                    iv_un_fav57.setVisibility(View.GONE);
                    rdd = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("SWFTC")) {
                    iv_fav58.setVisibility(View.VISIBLE);
                    iv_un_fav58.setVisibility(View.GONE);
                    swftc = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("MCO")) {
                    iv_fav59.setVisibility(View.VISIBLE);
                    iv_un_fav59.setVisibility(View.GONE);
                    mco = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("ARN")) {
                    iv_fav60.setVisibility(View.VISIBLE);
                    iv_un_fav60.setVisibility(View.GONE);
                    arn = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("MTN")) {
                    iv_fav61.setVisibility(View.VISIBLE);
                    iv_un_fav61.setVisibility(View.GONE);
                    mtn = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("BRD")) {
                    iv_fav62.setVisibility(View.VISIBLE);
                    iv_un_fav62.setVisibility(View.GONE);
                    brd = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("SAN*")) {
                    iv_fav63.setVisibility(View.VISIBLE);
                    iv_un_fav63.setVisibility(View.GONE);
                    san = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("NAS")) {
                    iv_fav64.setVisibility(View.VISIBLE);
                    iv_un_fav64.setVisibility(View.GONE);
                    nas = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("GVT")) {
                    iv_fav65.setVisibility(View.VISIBLE);
                    iv_un_fav65.setVisibility(View.GONE);
                    gvt = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("QUN")) {
                    iv_fav66.setVisibility(View.VISIBLE);
                    iv_un_fav66.setVisibility(View.GONE);
                    qun = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("WTC")) {
                    iv_fav67.setVisibility(View.VISIBLE);
                    iv_un_fav67.setVisibility(View.GONE);
                    wtc = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("FCT")) {
                    iv_fav68.setVisibility(View.VISIBLE);
                    iv_un_fav68.setVisibility(View.GONE);
                    fct = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("CDT")) {
                    iv_fav69.setVisibility(View.VISIBLE);
                    iv_un_fav69.setVisibility(View.GONE);
                    cdt = "0";
                } else if (mDbArrayList.get(i).SYMBOL.equals("RCN")) {
                    iv_fav70.setVisibility(View.VISIBLE);
                    iv_un_fav70.setVisibility(View.GONE);
                    rcn = "0";
                }
            }
        }

        return view;
    }

    public void init(View view) {


//--------------------------------------------------------google plus---------------------------------------------------------------

        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

//-----------------------------------------------------------------------------------------------------------------------


        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        iv_profilepic = header.findViewById(R.id.iv_profilepic);
        tv_user_name = header.findViewById(R.id.tv_user_name);


        ll_main = view.findViewById(R.id.ll_main);
        tv_main = view.findViewById(R.id.tv_main);
        iv_un_fav = view.findViewById(R.id.iv_un_fav);
        iv_un_fav1 = view.findViewById(R.id.iv_un_fav1);
        iv_un_fav2 = view.findViewById(R.id.iv_un_fav2);
        iv_un_fav3 = view.findViewById(R.id.iv_un_fav3);
        iv_un_fav4 = view.findViewById(R.id.iv_un_fav4);
        iv_un_fav5 = view.findViewById(R.id.iv_un_fav5);
        iv_un_fav6 = view.findViewById(R.id.iv_un_fav6);
        iv_un_fav7 = view.findViewById(R.id.iv_un_fav7);
        iv_un_fav8 = view.findViewById(R.id.iv_un_fav8);
        iv_un_fav9 = view.findViewById(R.id.iv_un_fav9);
        iv_un_fav10 = view.findViewById(R.id.iv_un_fav10);
        iv_un_fav11 = view.findViewById(R.id.iv_un_fav11);
        iv_un_fav12 = view.findViewById(R.id.iv_un_fav12);
        iv_un_fav13 = view.findViewById(R.id.iv_un_fav13);
        iv_un_fav14 = view.findViewById(R.id.iv_un_fav14);
        iv_un_fav15 = view.findViewById(R.id.iv_un_fav15);
        iv_un_fav16 = view.findViewById(R.id.iv_un_fav16);
        iv_un_fav17 = view.findViewById(R.id.iv_un_fav17);
        iv_un_fav18 = view.findViewById(R.id.iv_un_fav18);
        iv_un_fav19 = view.findViewById(R.id.iv_un_fav19);
        iv_un_fav20 = view.findViewById(R.id.iv_un_fav20);
        iv_un_fav21 = view.findViewById(R.id.iv_un_fav21);
        iv_un_fav22 = view.findViewById(R.id.iv_un_fav22);
        iv_un_fav23 = view.findViewById(R.id.iv_un_fav23);
        iv_un_fav24 = view.findViewById(R.id.iv_un_fav24);
        iv_un_fav25 = view.findViewById(R.id.iv_un_fav25);
        iv_un_fav26 = view.findViewById(R.id.iv_un_fav26);
        iv_un_fav27 = view.findViewById(R.id.iv_un_fav27);
        iv_un_fav28 = view.findViewById(R.id.iv_un_fav28);
        iv_un_fav29 = view.findViewById(R.id.iv_un_fav29);
        iv_un_fav30 = view.findViewById(R.id.iv_un_fav30);
        iv_un_fav31 = view.findViewById(R.id.iv_un_fav31);
        iv_un_fav32 = view.findViewById(R.id.iv_un_fav32);
        iv_un_fav33 = view.findViewById(R.id.iv_un_fav33);
        iv_un_fav34 = view.findViewById(R.id.iv_un_fav34);
        iv_un_fav35 = view.findViewById(R.id.iv_un_fav35);
        iv_un_fav36 = view.findViewById(R.id.iv_un_fav36);
        iv_un_fav37 = view.findViewById(R.id.iv_un_fav37);
        iv_un_fav38 = view.findViewById(R.id.iv_un_fav38);
        iv_un_fav39 = view.findViewById(R.id.iv_un_fav39);
        iv_un_fav40 = view.findViewById(R.id.iv_un_fav40);
        iv_un_fav41 = view.findViewById(R.id.iv_un_fav41);
        iv_un_fav42 = view.findViewById(R.id.iv_un_fav42);
        iv_un_fav43 = view.findViewById(R.id.iv_un_fav43);
        iv_un_fav44 = view.findViewById(R.id.iv_un_fav44);
        iv_un_fav45 = view.findViewById(R.id.iv_un_fav45);
        iv_un_fav46 = view.findViewById(R.id.iv_un_fav46);
        iv_un_fav47 = view.findViewById(R.id.iv_un_fav47);
        iv_un_fav48 = view.findViewById(R.id.iv_un_fav48);
        iv_un_fav49 = view.findViewById(R.id.iv_un_fav49);
        iv_un_fav50 = view.findViewById(R.id.iv_un_fav50);
        iv_un_fav51 = view.findViewById(R.id.iv_un_fav51);
        iv_un_fav52 = view.findViewById(R.id.iv_un_fav52);
        iv_un_fav53 = view.findViewById(R.id.iv_un_fav53);
        iv_un_fav54 = view.findViewById(R.id.iv_un_fav54);
        iv_un_fav55 = view.findViewById(R.id.iv_un_fav55);
        iv_un_fav56 = view.findViewById(R.id.iv_un_fav56);
        iv_un_fav57 = view.findViewById(R.id.iv_un_fav57);
        iv_un_fav58 = view.findViewById(R.id.iv_un_fav58);
        iv_un_fav59 = view.findViewById(R.id.iv_un_fav59);
        iv_un_fav60 = view.findViewById(R.id.iv_un_fav60);
        iv_un_fav61 = view.findViewById(R.id.iv_un_fav61);
        iv_un_fav62 = view.findViewById(R.id.iv_un_fav62);
        iv_un_fav63 = view.findViewById(R.id.iv_un_fav63);
        iv_un_fav64 = view.findViewById(R.id.iv_un_fav64);
        iv_un_fav65 = view.findViewById(R.id.iv_un_fav65);
        iv_un_fav66 = view.findViewById(R.id.iv_un_fav66);
        iv_un_fav67 = view.findViewById(R.id.iv_un_fav67);
        iv_un_fav68 = view.findViewById(R.id.iv_un_fav68);
        iv_un_fav69 = view.findViewById(R.id.iv_un_fav69);
        iv_un_fav70 = view.findViewById(R.id.iv_un_fav70);

        iv_fav = view.findViewById(R.id.iv_fav);
        iv_fav1 = view.findViewById(R.id.iv_fav1);
        iv_fav2 = view.findViewById(R.id.iv_fav2);
        iv_fav3 = view.findViewById(R.id.iv_fav3);
        iv_fav4 = view.findViewById(R.id.iv_fav4);
        iv_fav5 = view.findViewById(R.id.iv_fav5);
        iv_fav6 = view.findViewById(R.id.iv_fav6);
        iv_fav7 = view.findViewById(R.id.iv_fav7);
        iv_fav8 = view.findViewById(R.id.iv_fav8);
        iv_fav9 = view.findViewById(R.id.iv_fav9);
        iv_fav10 = view.findViewById(R.id.iv_fav10);
        iv_fav11 = view.findViewById(R.id.iv_fav11);
        iv_fav12 = view.findViewById(R.id.iv_fav12);
        iv_fav13 = view.findViewById(R.id.iv_fav13);
        iv_fav14 = view.findViewById(R.id.iv_fav14);
        iv_fav15 = view.findViewById(R.id.iv_fav15);
        iv_fav16 = view.findViewById(R.id.iv_fav16);
        iv_fav17 = view.findViewById(R.id.iv_fav17);
        iv_fav18 = view.findViewById(R.id.iv_fav18);
        iv_fav19 = view.findViewById(R.id.iv_fav19);
        iv_fav20 = view.findViewById(R.id.iv_fav20);
        iv_fav21 = view.findViewById(R.id.iv_fav21);
        iv_fav22 = view.findViewById(R.id.iv_fav22);
        iv_fav23 = view.findViewById(R.id.iv_fav23);
        iv_fav24 = view.findViewById(R.id.iv_fav24);
        iv_fav25 = view.findViewById(R.id.iv_fav25);
        iv_fav26 = view.findViewById(R.id.iv_fav26);
        iv_fav27 = view.findViewById(R.id.iv_fav27);
        iv_fav28 = view.findViewById(R.id.iv_fav28);
        iv_fav29 = view.findViewById(R.id.iv_fav29);
        iv_fav30 = view.findViewById(R.id.iv_fav30);
        iv_fav31 = view.findViewById(R.id.iv_fav31);
        iv_fav32 = view.findViewById(R.id.iv_fav32);
        iv_fav33 = view.findViewById(R.id.iv_fav33);
        iv_fav34 = view.findViewById(R.id.iv_fav34);
        iv_fav35 = view.findViewById(R.id.iv_fav35);
        iv_fav36 = view.findViewById(R.id.iv_fav36);
        iv_fav37 = view.findViewById(R.id.iv_fav37);
        iv_fav38 = view.findViewById(R.id.iv_fav38);
        iv_fav39 = view.findViewById(R.id.iv_fav39);
        iv_fav40 = view.findViewById(R.id.iv_fav40);
        iv_fav41 = view.findViewById(R.id.iv_fav41);
        iv_fav42 = view.findViewById(R.id.iv_fav42);
        iv_fav43 = view.findViewById(R.id.iv_fav43);
        iv_fav44 = view.findViewById(R.id.iv_fav44);
        iv_fav45 = view.findViewById(R.id.iv_fav45);
        iv_fav46 = view.findViewById(R.id.iv_fav46);
        iv_fav47 = view.findViewById(R.id.iv_fav47);
        iv_fav48 = view.findViewById(R.id.iv_fav48);
        iv_fav49 = view.findViewById(R.id.iv_fav49);
        iv_fav50 = view.findViewById(R.id.iv_fav50);
        iv_fav51 = view.findViewById(R.id.iv_fav51);
        iv_fav52 = view.findViewById(R.id.iv_fav52);
        iv_fav53 = view.findViewById(R.id.iv_fav53);
        iv_fav54 = view.findViewById(R.id.iv_fav54);
        iv_fav55 = view.findViewById(R.id.iv_fav55);
        iv_fav56 = view.findViewById(R.id.iv_fav56);
        iv_fav57 = view.findViewById(R.id.iv_fav57);
        iv_fav58 = view.findViewById(R.id.iv_fav58);
        iv_fav59 = view.findViewById(R.id.iv_fav59);
        iv_fav60 = view.findViewById(R.id.iv_fav60);
        iv_fav61 = view.findViewById(R.id.iv_fav61);
        iv_fav62 = view.findViewById(R.id.iv_fav62);
        iv_fav63 = view.findViewById(R.id.iv_fav63);
        iv_fav64 = view.findViewById(R.id.iv_fav64);
        iv_fav65 = view.findViewById(R.id.iv_fav65);
        iv_fav66 = view.findViewById(R.id.iv_fav66);
        iv_fav67 = view.findViewById(R.id.iv_fav67);
        iv_fav68 = view.findViewById(R.id.iv_fav68);
        iv_fav69 = view.findViewById(R.id.iv_fav69);
        iv_fav70 = view.findViewById(R.id.iv_fav70);

        tv_btc_name = (TextView) view.findViewById(R.id.tv_btc_name);
        tv_eth_name = (TextView) view.findViewById(R.id.tv_eth_name);
        tv_bch_name = (TextView) view.findViewById(R.id.tv_bch_name);
        tv_xrp_name = (TextView) view.findViewById(R.id.tv_xrp_name);
        tv_ltc_name = (TextView) view.findViewById(R.id.tv_ltc_name);
        tv_ada_name = (TextView) view.findViewById(R.id.tv_ada_name);
        tv_iot_name = (TextView) view.findViewById(R.id.tv_iot_name);
        tv_dash_name = (TextView) view.findViewById(R.id.tv_dash_name);
        tv_xem_name = (TextView) view.findViewById(R.id.tv_xem_name);
        tv_xmr_name = (TextView) view.findViewById(R.id.tv_xmr_name);
        tv_eos_name = (TextView) view.findViewById(R.id.tv_eos_name);
        tv_btg_name = (TextView) view.findViewById(R.id.tv_btg_name);
        tv_qtum_name = (TextView) view.findViewById(R.id.tv_qtum_name);
        tv_xlm_name = (TextView) view.findViewById(R.id.tv_xlm_name);
        tv_neo_name = (TextView) view.findViewById(R.id.tv_neo_name);
        tv_etc_name = (TextView) view.findViewById(R.id.tv_etc_name);
        tv_zec_name = (TextView) view.findViewById(R.id.tv_zec_name);
        tv_steem_name = (TextView) view.findViewById(R.id.tv_steem_name);
        tv_xzc_name = (TextView) view.findViewById(R.id.tv_xzc_name);
        tv_storj_name = (TextView) view.findViewById(R.id.tv_storj_name);
        tv_aion_name = (TextView) view.findViewById(R.id.tv_aion_name);
        tv_ht_name = (TextView) view.findViewById(R.id.tv_ht_name);
        tv_tronix_name = (TextView) view.findViewById(R.id.tv_tronix_name);
        tv_nano_name = (TextView) view.findViewById(R.id.tv_nano_name);
        tv_omisego_name = (TextView) view.findViewById(R.id.tv_omisego_name);
        tv_elastos_name = (TextView) view.findViewById(R.id.tv_elastos_name);
        tv_binance_name = (TextView) view.findViewById(R.id.tv_binance_name);
        tv_vechain_name = (TextView) view.findViewById(R.id.tv_vechain_name);
        tv_zclassic_name = (TextView) view.findViewById(R.id.tv_zclassic_name);
        tv_digix_name = (TextView) view.findViewById(R.id.tv_digix_name);
        tv_odyssey_name = (TextView) view.findViewById(R.id.tv_odyssey_name);
        tv_blockmason_name = (TextView) view.findViewById(R.id.tv_blockmason_name);
        tv_lunyr_name = (TextView) view.findViewById(R.id.tv_lunyr_name);
        tv_iostoken_name = (TextView) view.findViewById(R.id.tv_iostoken_name);
        tv_hshare_name = (TextView) view.findViewById(R.id.tv_hshare_name);
        tv_iconproject_name = (TextView) view.findViewById(R.id.tv_iconproject_name);
        tv_lisk_name = (TextView) view.findViewById(R.id.tv_lisk_name);
        tv_nebilo_name = (TextView) view.findViewById(R.id.tv_nebilo_name);
        tv_wavess_name = (TextView) view.findViewById(R.id.tv_wavess_name);
        tv_bluzello_name = (TextView) view.findViewById(R.id.tv_bluzello_name);
        tv_inkk_name = (TextView) view.findViewById(R.id.tv_inkk_name);
        tv_adex_name = (TextView) view.findViewById(R.id.tv_adex_name);
        tv_verge_name = (TextView) view.findViewById(R.id.tv_verge_name);
        tv_metal_name = (TextView) view.findViewById(R.id.tv_metal_name);
        tv_sirinlabs_name = (TextView) view.findViewById(R.id.tv_sirinlabs_name);
        tv_ox_name = (TextView) view.findViewById(R.id.tv_ox_name);
        tv_iex_name = (TextView) view.findViewById(R.id.tv_iex_name);
        tv_thetaa_name = (TextView) view.findViewById(R.id.tv_thetaa_name);
        tv_bitcoindimand_name = (TextView) view.findViewById(R.id.tv_bitcoindimand_name);
        tv_openanx_name = (TextView) view.findViewById(R.id.tv_openanx_name);
        tv_aelf_name = (TextView) view.findViewById(R.id.tv_aelf_name);
        tv_insexosystem_name = (TextView) view.findViewById(R.id.tv_insexosystem_name);
        tv_zilliqa_name = (TextView) view.findViewById(R.id.tv_zilliqa_name);
        tv_aeternity_name = (TextView) view.findViewById(R.id.tv_aeternity_name);
        tv_propy_name = (TextView) view.findViewById(R.id.tv_propy_name);
        tv_dogecoin_name = (TextView) view.findViewById(R.id.tv_dogecoin_name);
        tv_iotchain_name = (TextView) view.findViewById(R.id.tv_iotchain_name);
        tv_reddcoin_name = (TextView) view.findViewById(R.id.tv_reddcoin_name);
        tv_swftcoin_name = (TextView) view.findViewById(R.id.tv_swftcoin_name);
        tv_monaco_name = (TextView) view.findViewById(R.id.tv_monaco_name);
        tv_aeron_name = (TextView) view.findViewById(R.id.tv_aeron_name);
        tv_medicakchain_name = (TextView) view.findViewById(R.id.tv_medicakchain_name);
        tv_breadtoken_name = (TextView) view.findViewById(R.id.tv_breadtoken_name);
        tv_santiment_name = (TextView) view.findViewById(R.id.tv_santiment_name);
        tv_nebulas_name = (TextView) view.findViewById(R.id.tv_nebulas_name);
        tv_genesis_name = (TextView) view.findViewById(R.id.tv_genesis_name);
        tv_qunqun_name = (TextView) view.findViewById(R.id.tv_qunqun_name);
        tv_waltonchain_name = (TextView) view.findViewById(R.id.tv_waltonchain_name);
        tv_factoids_name = (TextView) view.findViewById(R.id.tv_factoids_name);
        tv_coinDash_name = (TextView) view.findViewById(R.id.tv_coinDash_name);
        tv_ripio_name = (TextView) view.findViewById(R.id.tv_ripio_name);

        tv_btc_sf = (TextView) view.findViewById(R.id.tv_btc_sf);
        tv_eth_sf = (TextView) view.findViewById(R.id.tv_eth_sf);
        tv_bch_sf = (TextView) view.findViewById(R.id.tv_bch_sf);
        tv_xrp_sf = (TextView) view.findViewById(R.id.tv_xrp_sf);
        tv_ltc_sf = (TextView) view.findViewById(R.id.tv_ltc_sf);
        tv_ada_sf = (TextView) view.findViewById(R.id.tv_ada_sf);
        tv_iot_sf = (TextView) view.findViewById(R.id.tv_iot_sf);
        tv_dash_sf = (TextView) view.findViewById(R.id.tv_dash_sf);
        tv_xem_sf = (TextView) view.findViewById(R.id.tv_xem_sf);
        tv_xmr_sf = (TextView) view.findViewById(R.id.tv_xmr_sf);
        tv_eos_sf = (TextView) view.findViewById(R.id.tv_eos_sf);
        tv_btg_sf = (TextView) view.findViewById(R.id.tv_btg_sf);
        tv_qtum_sf = (TextView) view.findViewById(R.id.tv_qtum_sf);
        tv_xlm_sf = (TextView) view.findViewById(R.id.tv_xlm_sf);
        tv_neo_sf = (TextView) view.findViewById(R.id.tv_neo_sf);
        tv_etc_sf = (TextView) view.findViewById(R.id.tv_etc_sf);
        tv_zec_sf = (TextView) view.findViewById(R.id.tv_zec_sf);
        tv_steem_sf = (TextView) view.findViewById(R.id.tv_steem_sf);
        tv_xzc_sf = (TextView) view.findViewById(R.id.tv_xzc_sf);
        tv_storj_sf = (TextView) view.findViewById(R.id.tv_storj_sf);
        tv_aion_sf = (TextView) view.findViewById(R.id.tv_aion_sf);
        tv_ht_sf = (TextView) view.findViewById(R.id.tv_ht_sf);
        tv_tronix_sf = (TextView) view.findViewById(R.id.tv_tronix_sf);
        tv_nano_sf = (TextView) view.findViewById(R.id.tv_nano_sf);
        tv_omisego_sf = (TextView) view.findViewById(R.id.tv_omisego_sf);
        tv_elastos_sf = (TextView) view.findViewById(R.id.tv_elastos_sf);
        tv_binance_sf = (TextView) view.findViewById(R.id.tv_binance_sf);
        tv_vechain_sf = (TextView) view.findViewById(R.id.tv_vechain_sf);
        tv_zclassic_sf = (TextView) view.findViewById(R.id.tv_zclassic_sf);
        tv_digix_sf = (TextView) view.findViewById(R.id.tv_digix_sf);
        tv_odyssey_sf = (TextView) view.findViewById(R.id.tv_odyssey_sf);
        tv_blockmason_sf = (TextView) view.findViewById(R.id.tv_blockmason_sf);
        tv_lunyr_sf = (TextView) view.findViewById(R.id.tv_lunyr_sf);
        tv_iostoken_sf = (TextView) view.findViewById(R.id.tv_iostoken_sf);
        tv_hshare_sf = (TextView) view.findViewById(R.id.tv_hshare_sf);
        tv_iconproject_sf = (TextView) view.findViewById(R.id.tv_iconproject_sf);
        tv_lisk_sf = (TextView) view.findViewById(R.id.tv_lisk_sf);
        tv_nebilo_sf = (TextView) view.findViewById(R.id.tv_nebilo_sf);
        tv_wavess_sf = (TextView) view.findViewById(R.id.tv_wavess_sf);
        tv_bluzello_sf = (TextView) view.findViewById(R.id.tv_bluzello_sf);
        tv_inkk_sf = (TextView) view.findViewById(R.id.tv_inkk_sf);
        tv_adex_sf = (TextView) view.findViewById(R.id.tv_adex_sf);
        tv_verge_sf = (TextView) view.findViewById(R.id.tv_verge_sf);
        tv_metal_sf = (TextView) view.findViewById(R.id.tv_metal_sf);
        tv_sirinlabs_sf = (TextView) view.findViewById(R.id.tv_sirinlabs_sf);
        tv_ox_sf = (TextView) view.findViewById(R.id.tv_ox_sf);
        tv_iex_sf = (TextView) view.findViewById(R.id.tv_iex_sf);
        tv_thetaa_sf = (TextView) view.findViewById(R.id.tv_thetaa_sf);
        tv_bitcoindimand_sf = (TextView) view.findViewById(R.id.tv_bitcoindimand_sf);
        tv_openanx_sf = (TextView) view.findViewById(R.id.tv_openanx_sf);
        tv_aelf_sf = (TextView) view.findViewById(R.id.tv_aelf_sf);
        tv_insexosystem_sf = (TextView) view.findViewById(R.id.tv_insexosystem_sf);
        tv_zilliqa_sf = (TextView) view.findViewById(R.id.tv_zilliqa_sf);
        tv_aeternity_sf = (TextView) view.findViewById(R.id.tv_aeternity_sf);
        tv_propy_sf = (TextView) view.findViewById(R.id.tv_propy_sf);
        tv_dogecoin_sf = (TextView) view.findViewById(R.id.tv_dogecoin_sf);
        tv_iotchain_sf = (TextView) view.findViewById(R.id.tv_iotchain_sf);
        tv_reddcoin_sf = (TextView) view.findViewById(R.id.tv_reddcoin_sf);
        tv_swftcoin_sf = (TextView) view.findViewById(R.id.tv_swftcoin_sf);
        tv_monaco_sf = (TextView) view.findViewById(R.id.tv_monaco_sf);
        tv_aeron_sf = (TextView) view.findViewById(R.id.tv_aeron_sf);
        tv_medicakchain_sf = (TextView) view.findViewById(R.id.tv_medicakchain_sf);
        tv_breadtoken_sf = (TextView) view.findViewById(R.id.tv_breadtoken_sf);
        tv_santiment_sf = (TextView) view.findViewById(R.id.tv_santiment_sf);
        tv_nebulas_sf = (TextView) view.findViewById(R.id.tv_nebulas_sf);
        tv_genesis_sf = (TextView) view.findViewById(R.id.tv_genesis_sf);
        tv_qunqun_sf = (TextView) view.findViewById(R.id.tv_qunqun_sf);
        tv_waltonchain_sf = (TextView) view.findViewById(R.id.tv_waltonchain_sf);
        tv_factoids_sf = (TextView) view.findViewById(R.id.tv_factoids_sf);
        tv_coinDash_sf = (TextView) view.findViewById(R.id.tv_coinDash_sf);
        tv_ripio_sf = (TextView) view.findViewById(R.id.tv_ripio_sf);


        tv_btc_mc = (TextView) view.findViewById(R.id.tv_btc_mc);
        tv_eth_mc = (TextView) view.findViewById(R.id.tv_eth_mc);
        tv_bch_mc = (TextView) view.findViewById(R.id.tv_bch_mc);
        tv_xrp_mc = (TextView) view.findViewById(R.id.tv_xrp_mc);
        tv_ltc_mc = (TextView) view.findViewById(R.id.tv_ltc_mc);
        tv_ada_mc = (TextView) view.findViewById(R.id.tv_ada_mc);
        tv_iot_mc = (TextView) view.findViewById(R.id.tv_iot_mc);
        tv_dash_mc = (TextView) view.findViewById(R.id.tv_dash_mc);
        tv_xem_mc = (TextView) view.findViewById(R.id.tv_xem_mc);
        tv_xmr_mc = (TextView) view.findViewById(R.id.tv_xmr_mc);
        tv_eos_mc = (TextView) view.findViewById(R.id.tv_eos_mc);
        tv_btg_mc = (TextView) view.findViewById(R.id.tv_btg_mc);
        tv_qtum_mc = (TextView) view.findViewById(R.id.tv_qtum_mc);
        tv_xlm_mc = (TextView) view.findViewById(R.id.tv_xlm_mc);
        tv_neo_mc = (TextView) view.findViewById(R.id.tv_neo_mc);
        tv_etc_mc = (TextView) view.findViewById(R.id.tv_etc_mc);
        tv_zec_mc = (TextView) view.findViewById(R.id.tv_zec_mc);
        tv_steem_mc = (TextView) view.findViewById(R.id.tv_steem_mc);
        tv_xzc_mc = (TextView) view.findViewById(R.id.tv_xzc_mc);
        tv_storj_mc = (TextView) view.findViewById(R.id.tv_storj_mc);
        tv_aion_mc = (TextView) view.findViewById(R.id.tv_aion_mc);
        tv_ht_mc = (TextView) view.findViewById(R.id.tv_ht_mc);
        tv_tronix_mc = (TextView) view.findViewById(R.id.tv_tronix_mc);
        tv_nano_mc = (TextView) view.findViewById(R.id.tv_nano_mc);
        tv_omisego_mc = (TextView) view.findViewById(R.id.tv_omisego_mc);
        tv_elastos_mc = (TextView) view.findViewById(R.id.tv_elastos_mc);
        tv_binance_mc = (TextView) view.findViewById(R.id.tv_binance_mc);
        tv_vechain_mc = (TextView) view.findViewById(R.id.tv_vechain_mc);
        tv_zclassic_mc = (TextView) view.findViewById(R.id.tv_zclassic_mc);
        tv_digix_mc = (TextView) view.findViewById(R.id.tv_digix_mc);
        tv_odyssey_mc = (TextView) view.findViewById(R.id.tv_odyssey_mc);
        tv_blockmason_mc = (TextView) view.findViewById(R.id.tv_blockmason_mc);
        tv_lunyr_mc = (TextView) view.findViewById(R.id.tv_lunyr_mc);
        tv_qtum_mc = (TextView) view.findViewById(R.id.tv_qtum_mc);
        tv_iostoken_mc = (TextView) view.findViewById(R.id.tv_iostoken_mc);
        tv_hshare_mc = (TextView) view.findViewById(R.id.tv_hshare_mc);
        tv_iconproject_mc = (TextView) view.findViewById(R.id.tv_iconproject_mc);
        tv_lisk_mc = (TextView) view.findViewById(R.id.tv_lisk_mc);
        tv_nebilo_mc = (TextView) view.findViewById(R.id.tv_nebilo_mc);
        tv_wavess_mc = (TextView) view.findViewById(R.id.tv_wavess_mc);
        tv_bluzello_mc = (TextView) view.findViewById(R.id.tv_bluzello_mc);
        tv_inkk_mc = (TextView) view.findViewById(R.id.tv_inkk_mc);
        tv_adex_mc = (TextView) view.findViewById(R.id.tv_adex_mc);
        tv_verge_mc = (TextView) view.findViewById(R.id.tv_verge_mc);
        tv_metal_mc = (TextView) view.findViewById(R.id.tv_metal_mc);
        tv_sirinlabs_mc = (TextView) view.findViewById(R.id.tv_sirinlabs_mc);
        tv_ox_mc = (TextView) view.findViewById(R.id.tv_ox_mc);
        tv_iex_mc = (TextView) view.findViewById(R.id.tv_iex_mc);
        tv_thetaa_mc = (TextView) view.findViewById(R.id.tv_thetaa_mc);
        tv_bitcoindimand_mc = (TextView) view.findViewById(R.id.tv_bitcoindimand_mc);
        tv_openanx_mc = (TextView) view.findViewById(R.id.tv_openanx_mc);
        tv_aelf_mc = (TextView) view.findViewById(R.id.tv_aelf_mc);
        tv_insexosystem_mc = (TextView) view.findViewById(R.id.tv_insexosystem_mc);
        tv_zilliqa_mc = (TextView) view.findViewById(R.id.tv_zilliqa_mc);
        tv_aeternity_mc = (TextView) view.findViewById(R.id.tv_aeternity_mc);
        tv_propy_mc = (TextView) view.findViewById(R.id.tv_propy_mc);
        tv_dogecoin_mc = (TextView) view.findViewById(R.id.tv_dogecoin_mc);
        tv_iotchain_mc = (TextView) view.findViewById(R.id.tv_iotchain_mc);
        tv_reddcoin_mc = (TextView) view.findViewById(R.id.tv_reddcoin_mc);
        tv_swftcoin_mc = (TextView) view.findViewById(R.id.tv_swftcoin_mc);
        tv_monaco_mc = (TextView) view.findViewById(R.id.tv_monaco_mc);
        tv_aeron_mc = (TextView) view.findViewById(R.id.tv_aeron_mc);
        tv_medicakchain_mc = (TextView) view.findViewById(R.id.tv_medicakchain_mc);
        tv_breadtoken_mc = (TextView) view.findViewById(R.id.tv_breadtoken_mc);
        tv_santiment_mc = (TextView) view.findViewById(R.id.tv_santiment_mc);
        tv_nebulas_mc = (TextView) view.findViewById(R.id.tv_nebulas_mc);
        tv_genesis_mc = (TextView) view.findViewById(R.id.tv_genesis_mc);
        tv_qunqun_mc = (TextView) view.findViewById(R.id.tv_qunqun_mc);
        tv_waltonchain_mc = (TextView) view.findViewById(R.id.tv_waltonchain_mc);
        tv_factoids_mc = (TextView) view.findViewById(R.id.tv_factoids_mc);
        tv_coinDash_mc = (TextView) view.findViewById(R.id.tv_coinDash_mc);
        tv_ripio_mc = (TextView) view.findViewById(R.id.tv_ripio_mc);


        tv_btc_price = (TextView) view.findViewById(R.id.tv_btc_price);
        tv_eth_price = (TextView) view.findViewById(R.id.tv_eth_price);
        tv_bch_price = (TextView) view.findViewById(R.id.tv_bch_price);
        tv_xrp_price = (TextView) view.findViewById(R.id.tv_xrp_price);
        tv_ltc_price = (TextView) view.findViewById(R.id.tv_ltc_price);
        tv_ada_price = (TextView) view.findViewById(R.id.tv_ada_price);
        tv_iot_price = (TextView) view.findViewById(R.id.tv_iot_price);
        tv_dash_price = (TextView) view.findViewById(R.id.tv_dash_price);
        tv_xem_price = (TextView) view.findViewById(R.id.tv_xem_price);
        tv_xmr_price = (TextView) view.findViewById(R.id.tv_xmr_price);
        tv_eos_price = (TextView) view.findViewById(R.id.tv_eos_price);
        tv_btg_price = (TextView) view.findViewById(R.id.tv_btg_price);
        tv_qtum_price = (TextView) view.findViewById(R.id.tv_qtum_price);
        tv_xlm_price = (TextView) view.findViewById(R.id.tv_xlm_price);
        tv_neo_price = (TextView) view.findViewById(R.id.tv_neo_price);
        tv_etc_price = (TextView) view.findViewById(R.id.tv_etc_price);
        tv_zec_price = (TextView) view.findViewById(R.id.tv_zec_price);
        tv_steem_price = (TextView) view.findViewById(R.id.tv_steem_price);
        tv_xzc_price = (TextView) view.findViewById(R.id.tv_xzc_price);
        tv_storj_price = (TextView) view.findViewById(R.id.tv_storj_price);
        tv_aion_price = (TextView) view.findViewById(R.id.tv_aion_price);
        tv_ht_price = (TextView) view.findViewById(R.id.tv_ht_price);
        tv_tronix_price = (TextView) view.findViewById(R.id.tv_tronix_price);
        tv_nano_price = (TextView) view.findViewById(R.id.tv_nano_price);
        tv_omisego_price = (TextView) view.findViewById(R.id.tv_omisego_price);
        tv_elastos_price = (TextView) view.findViewById(R.id.tv_elastos_price);
        tv_binance_price = (TextView) view.findViewById(R.id.tv_binance_price);
        tv_vechain_price = (TextView) view.findViewById(R.id.tv_vechain_price);
        tv_zclassic_price = (TextView) view.findViewById(R.id.tv_zclassic_price);
        tv_digix_price = (TextView) view.findViewById(R.id.tv_digix_price);
        tv_odyssey_price = (TextView) view.findViewById(R.id.tv_odyssey_price);
        tv_blockmason_price = (TextView) view.findViewById(R.id.tv_blockmason_price);
        tv_lunyr_price = (TextView) view.findViewById(R.id.tv_lunyr_price);
        tv_iostoken_price = (TextView) view.findViewById(R.id.tv_iostoken_price);
        tv_hshare_price = (TextView) view.findViewById(R.id.tv_hshare_price);
        tv_iconproject_price = (TextView) view.findViewById(R.id.tv_iconproject_price);
        tv_lisk_price = (TextView) view.findViewById(R.id.tv_lisk_price);
        tv_nebilo_price = (TextView) view.findViewById(R.id.tv_nebilo_price);
        tv_wavess_price = (TextView) view.findViewById(R.id.tv_wavess_price);
        tv_bluzello_price = (TextView) view.findViewById(R.id.tv_bluzello_price);
        tv_inkk_price = (TextView) view.findViewById(R.id.tv_inkk_price);
        tv_adex_price = (TextView) view.findViewById(R.id.tv_adex_price);
        tv_verge_price = (TextView) view.findViewById(R.id.tv_verge_price);
        tv_metal_price = (TextView) view.findViewById(R.id.tv_metal_price);
        tv_sirinlabs_price = (TextView) view.findViewById(R.id.tv_sirinlabs_price);
        tv_ox_price = (TextView) view.findViewById(R.id.tv_ox_price);
        tv_iex_price = (TextView) view.findViewById(R.id.tv_iex_price);
        tv_thetaa_price = (TextView) view.findViewById(R.id.tv_thetaa_price);
        tv_bitcoindimand_price = (TextView) view.findViewById(R.id.tv_bitcoindimand_price);
        tv_openanx_price = (TextView) view.findViewById(R.id.tv_openanx_price);
        tv_aelf_price = (TextView) view.findViewById(R.id.tv_aelf_price);
        tv_insexosystem_price = (TextView) view.findViewById(R.id.tv_insexosystem_price);
        tv_zilliqa_price = (TextView) view.findViewById(R.id.tv_zilliqa_price);
        tv_aeternity_price = (TextView) view.findViewById(R.id.tv_aeternity_price);
        tv_propy_price = (TextView) view.findViewById(R.id.tv_propy_price);
        tv_dogecoin_price = (TextView) view.findViewById(R.id.tv_dogecoin_price);
        tv_iotchain_price = (TextView) view.findViewById(R.id.tv_iotchain_price);
        tv_reddcoin_price = (TextView) view.findViewById(R.id.tv_reddcoin_price);
        tv_swftcoin_price = (TextView) view.findViewById(R.id.tv_swftcoin_price);
        tv_monaco_price = (TextView) view.findViewById(R.id.tv_monaco_price);
        tv_aeron_price = (TextView) view.findViewById(R.id.tv_aeron_price);
        tv_medicakchain_price = (TextView) view.findViewById(R.id.tv_medicakchain_price);
        tv_breadtoken_price = (TextView) view.findViewById(R.id.tv_breadtoken_price);
        tv_santiment_price = (TextView) view.findViewById(R.id.tv_santiment_price);
        tv_nebulas_price = (TextView) view.findViewById(R.id.tv_nebulas_price);
        tv_genesis_price = (TextView) view.findViewById(R.id.tv_genesis_price);
        tv_qunqun_price = (TextView) view.findViewById(R.id.tv_qunqun_price);
        tv_waltonchain_price = (TextView) view.findViewById(R.id.tv_waltonchain_price);
        tv_factoids_price = (TextView) view.findViewById(R.id.tv_factoids_price);
        tv_coinDash_price = (TextView) view.findViewById(R.id.tv_coinDash_price);
        tv_ripio_price = (TextView) view.findViewById(R.id.tv_ripio_price);


        tv_btc_pct = (TextView) view.findViewById(R.id.tv_btc_pct);
        tv_eth_pct = (TextView) view.findViewById(R.id.tv_eth_pct);
        tv_bch_pct = (TextView) view.findViewById(R.id.tv_bch_pct);
        tv_xrp_pct = (TextView) view.findViewById(R.id.tv_xrp_pct);
        tv_ltc_pct = (TextView) view.findViewById(R.id.tv_ltc_pct);
        tv_ada_pct = (TextView) view.findViewById(R.id.tv_ada_pct);
        tv_iot_pct = (TextView) view.findViewById(R.id.tv_iot_pct);
        tv_dash_pct = (TextView) view.findViewById(R.id.tv_dash_pct);
        tv_xem_pct = (TextView) view.findViewById(R.id.tv_xem_pct);
        tv_xmr_pct = (TextView) view.findViewById(R.id.tv_xmr_pct);
        tv_eos_pct = (TextView) view.findViewById(R.id.tv_eos_pct);
        tv_btg_pct = (TextView) view.findViewById(R.id.tv_btg_pct);
        tv_qtum_pct = (TextView) view.findViewById(R.id.tv_qtum_ptc);
        tv_xlm_pct = (TextView) view.findViewById(R.id.tv_xlm_pct);
        tv_neo_pct = (TextView) view.findViewById(R.id.tv_neo_pct);
        tv_etc_pct = (TextView) view.findViewById(R.id.tv_etc_pct);
        tv_zec_pct = (TextView) view.findViewById(R.id.tv_zec_pct);
        tv_steem_pct = (TextView) view.findViewById(R.id.tv_steem_ptc);
        tv_xzc_pct = (TextView) view.findViewById(R.id.tv_xzc_ptc);
        tv_storj_pct = (TextView) view.findViewById(R.id.tv_storj_ptc);
        tv_aion_pct = (TextView) view.findViewById(R.id.tv_aion_ptc);
        tv_ht_ptc = (TextView) view.findViewById(R.id.tv_ht_ptc);
        tv_tronix_ptc = (TextView) view.findViewById(R.id.tv_tronix_ptc);
        tv_nano_ptc = (TextView) view.findViewById(R.id.tv_nano_ptc);
        tv_omisego_ptc = (TextView) view.findViewById(R.id.tv_omisego_ptc);
        tv_elastos_ptc = (TextView) view.findViewById(R.id.tv_elastos_ptc);
        tv_binance_ptc = (TextView) view.findViewById(R.id.tv_binance_ptc);
        tv_vechain_ptc = (TextView) view.findViewById(R.id.tv_vechain_ptc);
        tv_zclassic_ptc = (TextView) view.findViewById(R.id.tv_zclassic_ptc);
        tv_digix_ptc = (TextView) view.findViewById(R.id.tv_digix_ptc);
        tv_odyssey_ptc = (TextView) view.findViewById(R.id.tv_odyssey_ptc);
        tv_blockmason_ptc = (TextView) view.findViewById(R.id.tv_blockmason_ptc);
        tv_lunyr_ptc = (TextView) view.findViewById(R.id.tv_lunyr_ptc);
        tv_iostoken_ptc = (TextView) view.findViewById(R.id.tv_iostoken_ptc);
        tv_hshare_ptc = (TextView) view.findViewById(R.id.tv_hshare_ptc);
        tv_iconproject_ptc = (TextView) view.findViewById(R.id.tv_iconproject_ptc);
        tv_lisk_ptc = (TextView) view.findViewById(R.id.tv_lisk_ptc);
        tv_nebilo_ptc = (TextView) view.findViewById(R.id.tv_nebilo_ptc);
        tv_wavess_ptc = (TextView) view.findViewById(R.id.tv_wavess_ptc);
        tv_bluzello_ptc = (TextView) view.findViewById(R.id.tv_bluzello_ptc);
        tv_inkk_ptc = (TextView) view.findViewById(R.id.tv_inkk_ptc);
        tv_adex_ptc = (TextView) view.findViewById(R.id.tv_adex_ptc);
        tv_verge_ptc = (TextView) view.findViewById(R.id.tv_verge_ptc);
        tv_metal_ptc = (TextView) view.findViewById(R.id.tv_metal_ptc);
        tv_sirinlabs_ptc = (TextView) view.findViewById(R.id.tv_sirinlabs_ptc);
        tv_ox_ptc = (TextView) view.findViewById(R.id.tv_ox_ptc);
        tv_iex_ptc = (TextView) view.findViewById(R.id.tv_iex_ptc);
        tv_thetaa_ptc = (TextView) view.findViewById(R.id.tv_thetaa_ptc);
        tv_bitcoindimand_ptc = (TextView) view.findViewById(R.id.tv_bitcoindimand_ptc);
        tv_openanx_ptc = (TextView) view.findViewById(R.id.tv_openanx_ptc);
        tv_aelf_ptc = (TextView) view.findViewById(R.id.tv_aelf_ptc);
        tv_insexosystem_ptc = (TextView) view.findViewById(R.id.tv_insexosystem_ptc);
        tv_zilliqa_ptc = (TextView) view.findViewById(R.id.tv_zilliqa_ptc);
        tv_aeternity_ptc = (TextView) view.findViewById(R.id.tv_aeternity_ptc);
        tv_propy_ptc = (TextView) view.findViewById(R.id.tv_propy_ptc);
        tv_dogecoin_ptc = (TextView) view.findViewById(R.id.tv_dogecoin_ptc);
        tv_iotchain_ptc = (TextView) view.findViewById(R.id.tv_iotchain_ptc);
        tv_reddcoin_ptc = (TextView) view.findViewById(R.id.tv_reddcoin_ptc);
        tv_swftcoin_ptc = (TextView) view.findViewById(R.id.tv_swftcoin_ptc);
        tv_monaco_ptc = (TextView) view.findViewById(R.id.tv_monaco_ptc);
        tv_aeron_ptc = (TextView) view.findViewById(R.id.tv_aeron_ptc);
        tv_medicakchain_ptc = (TextView) view.findViewById(R.id.tv_medicakchain_ptc);
        tv_breadtoken_ptc = (TextView) view.findViewById(R.id.tv_breadtoken_ptc);
        tv_santiment_ptc = (TextView) view.findViewById(R.id.tv_santiment_ptc);
        tv_nebulas_ptc = (TextView) view.findViewById(R.id.tv_nebulas_ptc);
        tv_genesis_ptc = (TextView) view.findViewById(R.id.tv_genesis_ptc);
        tv_qunqun_ptc = (TextView) view.findViewById(R.id.tv_qunqun_ptc);
        tv_waltonchain_ptc = (TextView) view.findViewById(R.id.tv_waltonchain_ptc);
        tv_factoids_ptc = (TextView) view.findViewById(R.id.tv_factoids_ptc);
        tv_coinDash_ptc = (TextView) view.findViewById(R.id.tv_coinDash_ptc);
        tv_ripio_ptc = (TextView) view.findViewById(R.id.tv_ripio_ptc);
        tv_app_name = (TextView) view.findViewById(R.id.tv_app_name);


        ll_btc = (LinearLayout) view.findViewById(R.id.ll_btc);
        ll_eth = (LinearLayout) view.findViewById(R.id.ll_eth);
        ll_bch = (LinearLayout) view.findViewById(R.id.ll_bch);
        ll_xrp = (LinearLayout) view.findViewById(R.id.ll_xrp);
        ll_ltc = (LinearLayout) view.findViewById(R.id.ll_ltc);
        ll_ada = (LinearLayout) view.findViewById(R.id.ll_ada);
        ll_iot = (LinearLayout) view.findViewById(R.id.ll_iot);
        ll_dash = (LinearLayout) view.findViewById(R.id.ll_dash);
        ll_xem = (LinearLayout) view.findViewById(R.id.ll_xem);
        ll_xmr = (LinearLayout) view.findViewById(R.id.ll_xmr);
        ll_eos = (LinearLayout) view.findViewById(R.id.ll_eos);
        ll_btg = (LinearLayout) view.findViewById(R.id.ll_btg);
        ll_qtum = (LinearLayout) view.findViewById(R.id.ll_qtum);
        ll_xlm = (LinearLayout) view.findViewById(R.id.ll_xlm);
        ll_neo = (LinearLayout) view.findViewById(R.id.ll_neo);
        ll_etc = (LinearLayout) view.findViewById(R.id.ll_etc);
        ll_zec = (LinearLayout) view.findViewById(R.id.ll_zec);
        ll_steem = (LinearLayout) view.findViewById(R.id.ll_steem);
        ll_xzc = (LinearLayout) view.findViewById(R.id.ll_xzc);
        ll_storj = (LinearLayout) view.findViewById(R.id.ll_storj);
        ll_aion = (LinearLayout) view.findViewById(R.id.ll_aion);
        ll_ht = (LinearLayout) view.findViewById(R.id.ll_ht);
        ll_trx = (LinearLayout) view.findViewById(R.id.ll_trx);
        ll_xrb = (LinearLayout) view.findViewById(R.id.ll_xrb);
        ll_omg = (LinearLayout) view.findViewById(R.id.ll_omg);
        ll_ela = (LinearLayout) view.findViewById(R.id.ll_ela);
        ll_bnb = (LinearLayout) view.findViewById(R.id.ll_bnb);
        ll_ven = (LinearLayout) view.findViewById(R.id.ll_ven);
        ll_zcl = (LinearLayout) view.findViewById(R.id.ll_zcl);
        ll_dgd = (LinearLayout) view.findViewById(R.id.ll_dgd);
        ll_ocn = (LinearLayout) view.findViewById(R.id.ll_ocn);
        ll_bcpt = (LinearLayout) view.findViewById(R.id.ll_bcpt);
        ll_lun = (LinearLayout) view.findViewById(R.id.ll_lun);
        ll_iost = (LinearLayout) view.findViewById(R.id.ll_iost);
        ll_hsr = (LinearLayout) view.findViewById(R.id.ll_hsr);
        ll_icx = (LinearLayout) view.findViewById(R.id.ll_icx);
        ll_lsk = (LinearLayout) view.findViewById(R.id.ll_lsk);
        ll_nebl = (LinearLayout) view.findViewById(R.id.ll_nebl);
        ll_waves = (LinearLayout) view.findViewById(R.id.ll_waves);
        ll_blz = (LinearLayout) view.findViewById(R.id.ll_blz);
        ll_ink = (LinearLayout) view.findViewById(R.id.ll_ink);
        ll_adx = (LinearLayout) view.findViewById(R.id.ll_adx);
        ll_xvg = (LinearLayout) view.findViewById(R.id.ll_xvg);
        ll_mtl = (LinearLayout) view.findViewById(R.id.ll_mtl);
        ll_srn = (LinearLayout) view.findViewById(R.id.ll_srn);
        ll_zrx = (LinearLayout) view.findViewById(R.id.ll_zrx);
        ll_rlc = (LinearLayout) view.findViewById(R.id.ll_rlc);
        ll_theta = (LinearLayout) view.findViewById(R.id.ll_theta);
        ll_bcd = (LinearLayout) view.findViewById(R.id.ll_bcd);
        ll_oax = (LinearLayout) view.findViewById(R.id.ll_oax);
        ll_elf = (LinearLayout) view.findViewById(R.id.ll_elf);
        ll_ins = (LinearLayout) view.findViewById(R.id.ll_ins);
        ll_ZIL = (LinearLayout) view.findViewById(R.id.ll_ZIL);
        ll_ae = (LinearLayout) view.findViewById(R.id.ll_ae);
        ll_pro = (LinearLayout) view.findViewById(R.id.ll_pro);
        ll_doge = (LinearLayout) view.findViewById(R.id.ll_doge);
        ll_itc = (LinearLayout) view.findViewById(R.id.ll_itc);
        ll_rdd = (LinearLayout) view.findViewById(R.id.ll_rdd);
        ll_swftc = (LinearLayout) view.findViewById(R.id.ll_swftc);
        ll_mco = (LinearLayout) view.findViewById(R.id.ll_mco);
        ll_arn = (LinearLayout) view.findViewById(R.id.ll_arn);
        ll_mtn = (LinearLayout) view.findViewById(R.id.ll_mtn);
        ll_brd = (LinearLayout) view.findViewById(R.id.ll_brd);
        ll_san = (LinearLayout) view.findViewById(R.id.ll_san);
        ll_nas = (LinearLayout) view.findViewById(R.id.ll_nas);
        ll_gvt = (LinearLayout) view.findViewById(R.id.ll_gvt);
        ll_qun = (LinearLayout) view.findViewById(R.id.ll_qun);
        ll_wtc = (LinearLayout) view.findViewById(R.id.ll_wtc);
        ll_fct = (LinearLayout) view.findViewById(R.id.ll_fct);
        ll_cdt = (LinearLayout) view.findViewById(R.id.ll_cdt);
        ll_rcn = (LinearLayout) view.findViewById(R.id.ll_rcn);


        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_user_name.setTypeface(type1);
        tv_main.setTypeface(type1);
//        tv_currency.setTypeface(type);
        tv_app_name.setTypeface(type2);
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


//        tv_currency.setText(currency);

        if (utils.isNetworkAvailable(getActivity())) {
            ll_main.setVisibility(View.VISIBLE);
            tv_main.setVisibility(View.GONE);
//
            new apiGet_Coins().execute();
        } else {
            ll_main.setVisibility(View.GONE);
            tv_main.setVisibility(View.VISIBLE);
            tv_main.setText(R.string.err_no_internet);
        }


        if (pro_pic.equals("")) {
        } else {
            String url = "https://widle.studio/coinscapmarket/API/profile/";
            Glide.with(getActivity()).load(url + pro_pic).into(iv_profilepic);
        }

        tv_user_name.setText(user_name);


        ll_btc.setOnClickListener(this);
        ll_eth.setOnClickListener(this);
        ll_bch.setOnClickListener(this);
        ll_xrp.setOnClickListener(this);
        ll_ltc.setOnClickListener(this);
        ll_ada.setOnClickListener(this);
        ll_iot.setOnClickListener(this);
        ll_dash.setOnClickListener(this);
        ll_xem.setOnClickListener(this);
        ll_xmr.setOnClickListener(this);
        ll_eos.setOnClickListener(this);
        ll_btg.setOnClickListener(this);
        ll_qtum.setOnClickListener(this);
        ll_xlm.setOnClickListener(this);
        ll_neo.setOnClickListener(this);
        ll_etc.setOnClickListener(this);
        ll_zec.setOnClickListener(this);
        ll_steem.setOnClickListener(this);
        ll_xzc.setOnClickListener(this);
        ll_storj.setOnClickListener(this);
        ll_aion.setOnClickListener(this);
        ll_ocn.setOnClickListener(this);
        ll_ht.setOnClickListener(this);
        ll_trx.setOnClickListener(this);
        ll_xrb.setOnClickListener(this);
        ll_omg.setOnClickListener(this);
        ll_ela.setOnClickListener(this);
        ll_bnb.setOnClickListener(this);
        ll_ven.setOnClickListener(this);
        ll_zcl.setOnClickListener(this);
        ll_dgd.setOnClickListener(this);
        ll_ocn.setOnClickListener(this);
        ll_bcpt.setOnClickListener(this);
        ll_lun.setOnClickListener(this);
        ll_iost.setOnClickListener(this);
        ll_hsr.setOnClickListener(this);
        ll_icx.setOnClickListener(this);
        ll_lsk.setOnClickListener(this);
        ll_nebl.setOnClickListener(this);
        ll_waves.setOnClickListener(this);
        ll_blz.setOnClickListener(this);
        ll_ink.setOnClickListener(this);
        ll_adx.setOnClickListener(this);
        ll_xvg.setOnClickListener(this);
        ll_mtl.setOnClickListener(this);
        ll_srn.setOnClickListener(this);
        ll_zrx.setOnClickListener(this);
        ll_rlc.setOnClickListener(this);
        ll_theta.setOnClickListener(this);
        ll_bcd.setOnClickListener(this);
        ll_oax.setOnClickListener(this);
        ll_elf.setOnClickListener(this);
        ll_ins.setOnClickListener(this);
        ll_ZIL.setOnClickListener(this);
        ll_ae.setOnClickListener(this);
        ll_pro.setOnClickListener(this);
        ll_doge.setOnClickListener(this);
        ll_itc.setOnClickListener(this);
        ll_rdd.setOnClickListener(this);
        ll_swftc.setOnClickListener(this);
        ll_mco.setOnClickListener(this);
        ll_arn.setOnClickListener(this);
        ll_mtn.setOnClickListener(this);
        ll_brd.setOnClickListener(this);
        ll_san.setOnClickListener(this);
        ll_nas.setOnClickListener(this);
        ll_gvt.setOnClickListener(this);
        ll_qun.setOnClickListener(this);
        ll_wtc.setOnClickListener(this);
        ll_fct.setOnClickListener(this);
        ll_cdt.setOnClickListener(this);
        ll_rcn.setOnClickListener(this);

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


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), Coins_Detail_Activity.class);
        switch (v.getId()) {
            case R.id.ll_btc:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Bitcoin");
                    intent.putExtra("symbol", "BTC");
                    intent.putExtra("price", mArrayList.get(0).PRICE);
                    intent.putExtra("volume", mArrayList.get(0).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(0).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(0).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(0).CHANGEPCTDAY);
                    intent.putExtra("fav_status", btc);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }

                break;
            case R.id.ll_eth:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Ethereum");
                    intent.putExtra("symbol", "ETH");
                    intent.putExtra("price", mArrayList.get(1).PRICE);
                    intent.putExtra("volume", mArrayList.get(1).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(1).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(1).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(1).CHANGEPCTDAY);
                    intent.putExtra("fav_status", eth);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }

                break;
            case R.id.ll_bch:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Bitcoin Cash");
                    intent.putExtra("symbol", "BCH");
                    intent.putExtra("price", mArrayList.get(2).PRICE);
                    intent.putExtra("volume", mArrayList.get(2).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(2).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(2).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(2).CHANGEPCTDAY);
                    intent.putExtra("fav_status", bch);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }

                break;
            case R.id.ll_xrp:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Ripple");
                    intent.putExtra("symbol", "XRP");
                    intent.putExtra("price", mArrayList.get(3).PRICE);
                    intent.putExtra("volume", mArrayList.get(3).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(3).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(3).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(3).CHANGEPCTDAY);
                    intent.putExtra("fav_status", xrp);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }

                break;
            case R.id.ll_ltc:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Litecoin");
                    intent.putExtra("symbol", "LTC");
                    intent.putExtra("price", mArrayList.get(4).PRICE);
                    intent.putExtra("volume", mArrayList.get(4).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(4).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(4).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(4).CHANGEPCTDAY);
                    intent.putExtra("fav_status", ltc);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }

                break;
            case R.id.ll_ada:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Cardano");
                    intent.putExtra("symbol", "ADA");
                    intent.putExtra("price", mArrayList.get(5).PRICE);
                    intent.putExtra("volume", mArrayList.get(5).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(5).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(5).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(5).CHANGEPCTDAY);
                    intent.putExtra("fav_status", ada);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_iot:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "IOTA");
                    intent.putExtra("symbol", "IOT");
                    intent.putExtra("price", mArrayList.get(6).PRICE);
                    intent.putExtra("volume", mArrayList.get(6).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(6).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(6).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(6).CHANGEPCTDAY);
                    intent.putExtra("fav_status", iot);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_dash:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "DigitalCash");
                    intent.putExtra("symbol", "DASH");
                    intent.putExtra("price", mArrayList.get(7).PRICE);
                    intent.putExtra("volume", mArrayList.get(7).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(7).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(7).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(7).CHANGEPCTDAY);
                    intent.putExtra("fav_status", dash);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_xem:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "NEM");
                    intent.putExtra("symbol", "XEM");
                    intent.putExtra("price", mArrayList.get(8).PRICE);
                    intent.putExtra("volume", mArrayList.get(8).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(8).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(8).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(8).CHANGEPCTDAY);
                    intent.putExtra("fav_status", xem);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_xmr:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Monero");
                    intent.putExtra("symbol", "XMR");
                    intent.putExtra("price", mArrayList.get(9).PRICE);
                    intent.putExtra("volume", mArrayList.get(9).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(9).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(9).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(9).CHANGEPCTDAY);
                    intent.putExtra("fav_status", xmr);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_eos:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "EOS");
                    intent.putExtra("symbol", "EOS");
                    intent.putExtra("price", mArrayList.get(10).PRICE);
                    intent.putExtra("volume", mArrayList.get(10).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(10).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(10).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(10).CHANGEPCTDAY);
                    intent.putExtra("fav_status", eos);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_btg:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Bitcoin Gold");
                    intent.putExtra("symbol", "BTG");
                    intent.putExtra("price", mArrayList.get(11).PRICE);
                    intent.putExtra("volume", mArrayList.get(11).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(11).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(11).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(11).CHANGEPCTDAY);
                    intent.putExtra("fav_status", btg);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_qtum:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Qtum");
                    intent.putExtra("symbol", "QTUM");
                    intent.putExtra("price", mArrayList.get(12).PRICE);
                    intent.putExtra("volume", mArrayList.get(12).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(12).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(12).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(12).CHANGEPCTDAY);
                    intent.putExtra("fav_status", qtum);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_xlm:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Stellar");
                    intent.putExtra("symbol", "XLM");
                    intent.putExtra("price", mArrayList.get(13).PRICE);
                    intent.putExtra("volume", mArrayList.get(13).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(13).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(13).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(13).CHANGEPCTDAY);
                    intent.putExtra("fav_status", xlm);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_neo:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "NEO");
                    intent.putExtra("symbol", "NEO");
                    intent.putExtra("price", mArrayList.get(14).PRICE);
                    intent.putExtra("volume", mArrayList.get(14).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(14).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(14).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(14).CHANGEPCTDAY);
                    intent.putExtra("fav_status", neo);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_etc:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "EthereumClas");
                    intent.putExtra("symbol", "ETC");
                    intent.putExtra("price", mArrayList.get(15).PRICE);
                    intent.putExtra("volume", mArrayList.get(15).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(15).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(15).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(15).CHANGEPCTDAY);
                    intent.putExtra("fav_status", etc);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_zec:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Zcash");
                    intent.putExtra("symbol", "ZEC");
                    intent.putExtra("price", mArrayList.get(16).PRICE);
                    intent.putExtra("volume", mArrayList.get(16).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(16).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(16).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(16).CHANGEPCTDAY);
                    intent.putExtra("fav_status", zec);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_steem:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Steem");
                    intent.putExtra("symbol", "STEEM");
                    intent.putExtra("price", mArrayList.get(17).PRICE);
                    intent.putExtra("volume", mArrayList.get(17).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(17).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(17).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(17).CHANGEPCTDAY);
                    intent.putExtra("fav_status", steem);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_xzc:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "ZCoin");
                    intent.putExtra("symbol", "XZC");
                    intent.putExtra("price", mArrayList.get(18).PRICE);
                    intent.putExtra("volume", mArrayList.get(18).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(18).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(18).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(18).CHANGEPCTDAY);
                    intent.putExtra("fav_status", xzc);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_storj:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Strorj");
                    intent.putExtra("symbol", "STORJ");
                    intent.putExtra("price", mArrayList.get(19).PRICE);
                    intent.putExtra("volume", mArrayList.get(19).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(19).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(19).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(19).CHANGEPCTDAY);
                    intent.putExtra("fav_status", storj);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_aion:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Aion");
                    intent.putExtra("symbol", "AION");
                    intent.putExtra("price", mArrayList.get(20).PRICE);
                    intent.putExtra("volume", mArrayList.get(20).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(20).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(20).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(20).CHANGEPCTDAY);
                    intent.putExtra("fav_status", aion);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_ht:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Huobi Token");
                    intent.putExtra("symbol", "HT");
                    intent.putExtra("price", mArrayList.get(21).PRICE);
                    intent.putExtra("volume", mArrayList.get(21).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(21).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(21).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(21).CHANGEPCTDAY);
                    intent.putExtra("fav_status", ht);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_trx:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Tronix");
                    intent.putExtra("symbol", "TRX");
                    intent.putExtra("price", mArrayList.get(22).PRICE);
                    intent.putExtra("volume", mArrayList.get(22).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(22).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(22).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(22).CHANGEPCTDAY);
                    intent.putExtra("fav_status", trx);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_xrb:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Nano");
                    intent.putExtra("symbol", "XRB");
                    intent.putExtra("price", mArrayList.get(23).PRICE);
                    intent.putExtra("volume", mArrayList.get(23).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(23).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(23).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(23).CHANGEPCTDAY);
                    intent.putExtra("fav_status", xrb);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_omg:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "OmiseGo");
                    intent.putExtra("symbol", "OMG");
                    intent.putExtra("price", mArrayList.get(24).PRICE);
                    intent.putExtra("volume", mArrayList.get(24).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(24).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(24).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(24).CHANGEPCTDAY);
                    intent.putExtra("fav_status", omg);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_ela:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Elastos");
                    intent.putExtra("symbol", "ELA");
                    intent.putExtra("price", mArrayList.get(25).PRICE);
                    intent.putExtra("volume", mArrayList.get(25).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(25).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(25).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(25).CHANGEPCTDAY);
                    intent.putExtra("fav_status", ela);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_bnb:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Binance Coin");
                    intent.putExtra("symbol", "BNB");
                    intent.putExtra("price", mArrayList.get(26).PRICE);
                    intent.putExtra("volume", mArrayList.get(26).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(26).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(26).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(26).CHANGEPCTDAY);
                    intent.putExtra("fav_status", bnb);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_ven:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Vechain");
                    intent.putExtra("symbol", "VEN");
                    intent.putExtra("price", mArrayList.get(27).PRICE);
                    intent.putExtra("volume", mArrayList.get(27).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(27).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(27).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(27).CHANGEPCTDAY);
                    intent.putExtra("fav_status", ven);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_zcl:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "ZClassic");
                    intent.putExtra("symbol", "ZCL");
                    intent.putExtra("price", mArrayList.get(28).PRICE);
                    intent.putExtra("volume", mArrayList.get(28).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(28).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(28).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(28).CHANGEPCTDAY);
                    intent.putExtra("fav_status", zcl);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_dgd:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Digix DAO");
                    intent.putExtra("symbol", "DGD");
                    intent.putExtra("price", mArrayList.get(29).PRICE);
                    intent.putExtra("volume", mArrayList.get(29).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(29).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(29).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(29).CHANGEPCTDAY);
                    intent.putExtra("fav_status", dgd);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_ocn:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Odyssey");
                    intent.putExtra("symbol", "OCN");
                    intent.putExtra("price", mArrayList.get(30).PRICE);
                    intent.putExtra("volume", mArrayList.get(30).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(30).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(30).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(30).CHANGEPCTDAY);
                    intent.putExtra("fav_status", ocn);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_bcpt:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "BlockMason Credit P.");
                    intent.putExtra("symbol", "BCPT");
                    intent.putExtra("price", mArrayList.get(31).PRICE);
                    intent.putExtra("volume", mArrayList.get(31).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(31).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(31).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(31).CHANGEPCTDAY);
                    intent.putExtra("fav_status", bcpt);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
                break;
            case R.id.ll_lun:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Lunyr");
                    intent.putExtra("symbol", "LUN");
                    intent.putExtra("price", mArrayList.get(32).PRICE);
                    intent.putExtra("volume", mArrayList.get(32).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(32).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(32).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(32).CHANGEPCTDAY);
                    intent.putExtra("fav_status", lun);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }

                break;
            case R.id.ll_iost:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "IOS token");
                    intent.putExtra("symbol", "IOST");
                    intent.putExtra("price", mArrayList.get(33).PRICE);
                    intent.putExtra("volume", mArrayList.get(33).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(33).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(33).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(33).CHANGEPCTDAY);
                    intent.putExtra("fav_status", iost);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }

                break;
            case R.id.ll_hsr:
                if (utils.isNetworkAvailable(getActivity())) {
                    intent.putExtra("name", "Hshare");
                    intent.putExtra("symbol", "HSR");
                    intent.putExtra("price", mArrayList.get(34).PRICE);
                    intent.putExtra("volume", mArrayList.get(34).CHANGE24HOUR);
                    intent.putExtra("market_cap", mArrayList.get(34).MKTCAP);
                    intent.putExtra("change24hr", mArrayList.get(34).CHANGEPCT24HOUR);
                    intent.putExtra("changeday", mArrayList.get(34).CHANGEPCTDAY);
                    intent.putExtra("fav_status", hsr);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }

                break;
            case R.id.ll_icx:

                intent.putExtra("name", "ICON Project");
                intent.putExtra("symbol", "ICX");
                intent.putExtra("price", mArrayList.get(35).PRICE);
                intent.putExtra("volume", mArrayList.get(35).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(35).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(35).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(35).CHANGEPCTDAY);
                intent.putExtra("fav_status", icx);
                startActivity(intent);
                break;
            case R.id.ll_lsk:
                intent.putExtra("name", "Lisk");
                intent.putExtra("symbol", "LSK");
                intent.putExtra("price", mArrayList.get(36).PRICE);
                intent.putExtra("volume", mArrayList.get(36).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(36).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(36).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(36).CHANGEPCTDAY);
                intent.putExtra("fav_status", lsk);
                startActivity(intent);
                break;
            case R.id.ll_nebl:
                intent.putExtra("name", "Neblio");
                intent.putExtra("symbol", "NEBL");
                intent.putExtra("price", mArrayList.get(37).PRICE);
                intent.putExtra("volume", mArrayList.get(37).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(37).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(37).CHANGEPCT24HOUR);
                intent.putExtra("fav_status", nebl);
                startActivity(intent);
                break;
            case R.id.ll_waves:
                intent.putExtra("name", "Waves");
                intent.putExtra("symbol", "WAVES");
                intent.putExtra("price", mArrayList.get(38).PRICE);
                intent.putExtra("volume", mArrayList.get(38).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(38).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(38).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(38).CHANGEPCTDAY);
                intent.putExtra("fav_status", waves);
                startActivity(intent);
                break;
            case R.id.ll_blz:
                intent.putExtra("name", "Bluzelle");
                intent.putExtra("symbol", "BLZ");
                intent.putExtra("price", mArrayList.get(39).PRICE);
                intent.putExtra("volume", mArrayList.get(39).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(39).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(39).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(39).CHANGEPCTDAY);
                intent.putExtra("fav_status", blz);
                startActivity(intent);
                break;
            case R.id.ll_ink:
                intent.putExtra("name", "Ink");
                intent.putExtra("symbol", "INK");
                intent.putExtra("price", mArrayList.get(40).PRICE);
                intent.putExtra("volume", mArrayList.get(40).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(40).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(40).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(40).CHANGEPCTDAY);
                intent.putExtra("fav_status", ink);
                startActivity(intent);
                break;
            case R.id.ll_adx:
                intent.putExtra("name", "AdEx");
                intent.putExtra("symbol", "ADX");
                intent.putExtra("price", mArrayList.get(41).PRICE);
                intent.putExtra("volume", mArrayList.get(41).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(41).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(41).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(41).CHANGEPCTDAY);
                intent.putExtra("fav_status", adx);
                startActivity(intent);
                break;
            case R.id.ll_xvg:
                intent.putExtra("name", "Verge");
                intent.putExtra("symbol", "XVG");
                intent.putExtra("price", mArrayList.get(42).PRICE);
                intent.putExtra("volume", mArrayList.get(42).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(42).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(42).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(42).CHANGEPCTDAY);
                intent.putExtra("fav_status", xvg);
                startActivity(intent);
                break;
            case R.id.ll_mtl:
                intent.putExtra("name", "Metal");
                intent.putExtra("symbol", "MTL");
                intent.putExtra("price", mArrayList.get(43).PRICE);
                intent.putExtra("volume", mArrayList.get(43).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(43).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(43).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(43).CHANGEPCTDAY);
                intent.putExtra("fav_status", mtl);
                startActivity(intent);
                break;
            case R.id.ll_srn:
                intent.putExtra("name", "SirinLabs");
                intent.putExtra("symbol", "SRN");
                intent.putExtra("price", mArrayList.get(44).PRICE);
                intent.putExtra("volume", mArrayList.get(44).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(44).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(44).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(44).CHANGEPCTDAY);
                intent.putExtra("fav_status", srn);
                startActivity(intent);
                break;
            case R.id.ll_zrx:
                intent.putExtra("name", "0x");
                intent.putExtra("symbol", "ZRX");
                intent.putExtra("price", mArrayList.get(45).PRICE);
                intent.putExtra("volume", mArrayList.get(45).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(45).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(45).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(45).CHANGEPCTDAY);
                intent.putExtra("fav_status", zrx);
                startActivity(intent);
                break;
            case R.id.ll_rlc:
                intent.putExtra("name", "iEx.ec");
                intent.putExtra("symbol", "RLC");
                intent.putExtra("price", mArrayList.get(46).PRICE);
                intent.putExtra("volume", mArrayList.get(46).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(46).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(46).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(46).CHANGEPCTDAY);
                intent.putExtra("fav_status", rlc);
                startActivity(intent);
                break;
            case R.id.ll_theta:
                intent.putExtra("name", "Theta");
                intent.putExtra("symbol", "THETA");
                intent.putExtra("price", mArrayList.get(47).PRICE);
                intent.putExtra("volume", mArrayList.get(47).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(47).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(47).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(47).CHANGEPCTDAY);
                intent.putExtra("fav_status", theta);
                startActivity(intent);
                break;
            case R.id.ll_bcd:
                intent.putExtra("name", "Bitcoin Diamond");
                intent.putExtra("symbol", "BCD");
                intent.putExtra("price", mArrayList.get(48).PRICE);
                intent.putExtra("volume", mArrayList.get(48).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(48).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(48).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(48).CHANGEPCTDAY);
                intent.putExtra("fav_status", bcd);
                startActivity(intent);
                break;
            case R.id.ll_oax:
                intent.putExtra("name", "OpenANX");
                intent.putExtra("symbol", "OAX");
                intent.putExtra("price", mArrayList.get(49).PRICE);
                intent.putExtra("volume", mArrayList.get(49).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(49).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(49).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(49).CHANGEPCTDAY);
                intent.putExtra("fav_status", oax);
                startActivity(intent);
                break;
            case R.id.ll_elf:
                intent.putExtra("name", "aelf");
                intent.putExtra("symbol", "ELF");
                intent.putExtra("price", mArrayList.get(50).PRICE);
                intent.putExtra("volume", mArrayList.get(50).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(50).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(50).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(50).CHANGEPCTDAY);
                intent.putExtra("fav_status", elf);
                startActivity(intent);
                break;
            case R.id.ll_ins:
                intent.putExtra("name", "INS Ecosystem");
                intent.putExtra("symbol", "INS");
                intent.putExtra("price", mArrayList.get(51).PRICE);
                intent.putExtra("volume", mArrayList.get(51).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(51).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(51).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(51).CHANGEPCTDAY);
                intent.putExtra("fav_status", ins);
                startActivity(intent);
                break;
            case R.id.ll_ZIL:
                intent.putExtra("name", "Zilliqa");
                intent.putExtra("symbol", "ZIL");
                intent.putExtra("price", mArrayList.get(52).PRICE);
                intent.putExtra("volume", mArrayList.get(52).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(52).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(52).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(52).CHANGEPCTDAY);
                intent.putExtra("fav_status", zil);
                startActivity(intent);
                break;
            case R.id.ll_ae:
                intent.putExtra("name", "Aeternity");
                intent.putExtra("symbol", "AE");
                intent.putExtra("price", mArrayList.get(53).PRICE);
                intent.putExtra("volume", mArrayList.get(53).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(53).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(53).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(53).CHANGEPCTDAY);
                intent.putExtra("fav_status", ae);
                startActivity(intent);
                break;
            case R.id.ll_pro:
                intent.putExtra("name", "Propy");
                intent.putExtra("symbol", "PRO");
                intent.putExtra("price", mArrayList.get(54).PRICE);
                intent.putExtra("volume", mArrayList.get(54).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(54).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(54).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(54).CHANGEPCTDAY);
                intent.putExtra("fav_status", pro);
                startActivity(intent);
                break;
            case R.id.ll_doge:
                intent.putExtra("name", "Dogecoin");
                intent.putExtra("symbol", "DOGE");
                intent.putExtra("price", mArrayList.get(55).PRICE);
                intent.putExtra("volume", mArrayList.get(55).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(55).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(55).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(55).CHANGEPCTDAY);
                intent.putExtra("fav_status", doge);
                startActivity(intent);
                break;
            case R.id.ll_itc:
                intent.putExtra("name", "IoT Chain");
                intent.putExtra("symbol", "ITC");
                intent.putExtra("price", mArrayList.get(56).PRICE);
                intent.putExtra("volume", mArrayList.get(56).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(56).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(56).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(56).CHANGEPCTDAY);
                intent.putExtra("fav_status", itc);
                startActivity(intent);
                break;
            case R.id.ll_rdd:
                intent.putExtra("name", "ReddCoin");
                intent.putExtra("symbol", "RDD");
                intent.putExtra("price", mArrayList.get(57).PRICE);
                intent.putExtra("volume", mArrayList.get(57).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(57).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(57).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(57).CHANGEPCTDAY);
                intent.putExtra("fav_status", rdd);
                startActivity(intent);
                break;
            case R.id.ll_swftc:
                intent.putExtra("name", "SwftCoin");
                intent.putExtra("symbol", "SWFTC");
                intent.putExtra("price", mArrayList.get(58).PRICE);
                intent.putExtra("volume", mArrayList.get(58).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(58).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(58).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(58).CHANGEPCTDAY);
                intent.putExtra("fav_status", swftc);
                startActivity(intent);
                break;
            case R.id.ll_mco:
                intent.putExtra("name", "Monaco");
                intent.putExtra("symbol", "MCO");
                intent.putExtra("price", mArrayList.get(59).PRICE);
                intent.putExtra("volume", mArrayList.get(59).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(59).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(59).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(59).CHANGEPCTDAY);
                intent.putExtra("fav_status", mco);
                startActivity(intent);
                break;
            case R.id.ll_arn:
                intent.putExtra("name", "Aeron");
                intent.putExtra("symbol", "arn");
                intent.putExtra("price", mArrayList.get(60).PRICE);
                intent.putExtra("volume", mArrayList.get(60).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(60).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(60).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(60).CHANGEPCTDAY);
                intent.putExtra("fav_status", arn);
                startActivity(intent);
                break;
            case R.id.ll_mtn:
                intent.putExtra("name", "Medicalchain");
                intent.putExtra("symbol", "MTN*");
                intent.putExtra("price", mArrayList.get(61).PRICE);
                intent.putExtra("volume", mArrayList.get(61).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(61).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(61).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(61).CHANGEPCTDAY);
                intent.putExtra("fav_status", mtn);
                startActivity(intent);
                break;
            case R.id.ll_brd:
                intent.putExtra("name", "Bread token");
                intent.putExtra("symbol", "BRD");
                intent.putExtra("price", mArrayList.get(62).PRICE);
                intent.putExtra("volume", mArrayList.get(62).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(62).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(62).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(62).CHANGEPCTDAY);
                intent.putExtra("fav_status", brd);
                startActivity(intent);
                break;
            case R.id.ll_san:
                intent.putExtra("name", "Santiment");
                intent.putExtra("symbol", "SAN");
                intent.putExtra("price", mArrayList.get(63).PRICE);
                intent.putExtra("volume", mArrayList.get(63).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(63).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(63).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(63).CHANGEPCTDAY);
                intent.putExtra("fav_status", san);
                startActivity(intent);
                break;
            case R.id.ll_nas:
                intent.putExtra("name", "Nebulas");
                intent.putExtra("symbol", "NAS");
                intent.putExtra("price", mArrayList.get(64).PRICE);
                intent.putExtra("volume", mArrayList.get(64).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(64).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(64).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(64).CHANGEPCTDAY);
                intent.putExtra("fav_status", nas);
                startActivity(intent);
                break;
            case R.id.ll_gvt:
                intent.putExtra("name", "Genesis Vision");
                intent.putExtra("symbol", "GVT");
                intent.putExtra("price", mArrayList.get(65).PRICE);
                intent.putExtra("volume", mArrayList.get(65).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(65).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(65).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(65).CHANGEPCTDAY);
                intent.putExtra("fav_status", gvt);
                startActivity(intent);
                break;
            case R.id.ll_qun:
                intent.putExtra("name", "QunQun");
                intent.putExtra("symbol", "QUN");
                intent.putExtra("price", mArrayList.get(66).PRICE);
                intent.putExtra("volume", mArrayList.get(66).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(66).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(66).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(66).CHANGEPCTDAY);
                intent.putExtra("fav_status", qun);
                startActivity(intent);
                break;
            case R.id.ll_wtc:
                intent.putExtra("name", "Waltonchain");
                intent.putExtra("symbol", "WTC");
                intent.putExtra("price", mArrayList.get(67).PRICE);
                intent.putExtra("volume", mArrayList.get(67).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(67).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(67).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(67).CHANGEPCTDAY);
                intent.putExtra("fav_status", wtc);
                startActivity(intent);
                break;
            case R.id.ll_fct:
                intent.putExtra("name", "Factoids");
                intent.putExtra("symbol", "FCT");
                intent.putExtra("price", mArrayList.get(68).PRICE);
                intent.putExtra("volume", mArrayList.get(68).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(68).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(68).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(68).CHANGEPCTDAY);
                intent.putExtra("fav_status", fct);
                startActivity(intent);
                break;
            case R.id.ll_cdt:
                intent.putExtra("name", "CoinDash");
                intent.putExtra("symbol", "CDT");
                intent.putExtra("price", mArrayList.get(69).PRICE);
                intent.putExtra("volume", mArrayList.get(69).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(69).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(69).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(69).CHANGEPCTDAY);
                intent.putExtra("fav_status", cdt);
                startActivity(intent);
                break;
            case R.id.ll_rcn:
                intent.putExtra("name", "Ripio");
                intent.putExtra("symbol", "RCN");
                intent.putExtra("price", mArrayList.get(70).PRICE);
                intent.putExtra("volume", mArrayList.get(70).CHANGE24HOUR);
                intent.putExtra("market_cap", mArrayList.get(70).MKTCAP);
                intent.putExtra("change24hr", mArrayList.get(70).CHANGEPCT24HOUR);
                intent.putExtra("changeday", mArrayList.get(70).CHANGEPCTDAY);
                intent.putExtra("fav_status", rcn);
                startActivity(intent);
                break;

            case R.id.iv_un_fav:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        btc = "0";
                        new AlertTask("BTC").execute();
                        db.addCoins("Bitcoin", "BTC", mArrayList.get(0).MKTCAP, mArrayList.get(0).PRICE, mArrayList.get(0).CHANGE24HOUR, mArrayList.get(0).CHANGEPCT24HOUR);
                        iv_un_fav.setVisibility(View.GONE);
                        iv_fav.setVisibility(View.VISIBLE);
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav1:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        eth = "0";
                        db.addCoins("Ethereum", "ETH", mArrayList.get(1).MKTCAP, mArrayList.get(1).PRICE, mArrayList.get(1).CHANGE24HOUR, mArrayList.get(1).CHANGEPCT24HOUR);
                        iv_un_fav1.setVisibility(View.GONE);
                        iv_fav1.setVisibility(View.VISIBLE);
                        new AlertTask("ETH").execute();
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav2:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Bitcoin Cash", "BCH", mArrayList.get(2).MKTCAP, mArrayList.get(2).PRICE, mArrayList.get(2).CHANGE24HOUR, mArrayList.get(2).CHANGEPCT24HOUR);
                        iv_un_fav2.setVisibility(View.GONE);
                        iv_fav2.setVisibility(View.VISIBLE);
                        new AlertTask("BCH").execute();
                        bch = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav3:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Ripple", "XRP", mArrayList.get(3).MKTCAP, mArrayList.get(3).PRICE, mArrayList.get(3).CHANGE24HOUR, mArrayList.get(3).CHANGEPCT24HOUR);
                        iv_un_fav3.setVisibility(View.GONE);
                        iv_fav3.setVisibility(View.VISIBLE);
                        new AlertTask("XRP").execute();
                        xrp = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav4:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Litecoin", "LTC", mArrayList.get(4).MKTCAP, mArrayList.get(4).PRICE, mArrayList.get(4).CHANGE24HOUR, mArrayList.get(4).CHANGEPCT24HOUR);
                        iv_un_fav4.setVisibility(View.GONE);
                        iv_fav4.setVisibility(View.VISIBLE);
                        new AlertTask("LTC").execute();
                        ltc = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav5:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Cardano", "ADA", mArrayList.get(5).MKTCAP, mArrayList.get(5).PRICE, mArrayList.get(5).CHANGE24HOUR, mArrayList.get(5).CHANGEPCT24HOUR);
                        iv_un_fav5.setVisibility(View.GONE);
                        iv_fav5.setVisibility(View.VISIBLE);
                        new AlertTask("ADA").execute();
                        ada = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav6:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("IOTA", "IOT", mArrayList.get(6).MKTCAP, mArrayList.get(6).PRICE, mArrayList.get(6).CHANGE24HOUR, mArrayList.get(6).CHANGEPCT24HOUR);
                        iv_un_fav6.setVisibility(View.GONE);
                        iv_fav6.setVisibility(View.VISIBLE);
                        new AlertTask("IOT").execute();
                        iot = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav7:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("DigitalCash", "DASH", mArrayList.get(7).MKTCAP, mArrayList.get(7).PRICE, mArrayList.get(7).CHANGE24HOUR, mArrayList.get(7).CHANGEPCT24HOUR);
                        iv_un_fav7.setVisibility(View.GONE);
                        iv_fav7.setVisibility(View.VISIBLE);
                        new AlertTask("DASH").execute();
                        dash = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav8:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("NEM", "XEM", mArrayList.get(8).MKTCAP, mArrayList.get(8).PRICE, mArrayList.get(8).CHANGE24HOUR, mArrayList.get(8).CHANGEPCT24HOUR);
                        iv_un_fav8.setVisibility(View.GONE);
                        iv_fav8.setVisibility(View.VISIBLE);
                        new AlertTask("XEM").execute();
                        xem = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav9:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Monero", "XMR", mArrayList.get(9).MKTCAP, mArrayList.get(9).PRICE, mArrayList.get(9).CHANGE24HOUR, mArrayList.get(9).CHANGEPCT24HOUR);
                        iv_un_fav9.setVisibility(View.GONE);
                        iv_fav9.setVisibility(View.VISIBLE);
                        new AlertTask("XMR").execute();
                        xmr = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav10:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("EOS", "EOS", mArrayList.get(10).MKTCAP, mArrayList.get(10).PRICE, mArrayList.get(10).CHANGE24HOUR, mArrayList.get(10).CHANGEPCT24HOUR);
                        iv_un_fav10.setVisibility(View.GONE);
                        iv_fav10.setVisibility(View.VISIBLE);
                        new AlertTask("EOS").execute();
                        eos = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav11:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Bitcoin Gold", "BTG", mArrayList.get(11).MKTCAP, mArrayList.get(11).PRICE, mArrayList.get(11).CHANGE24HOUR, mArrayList.get(11).CHANGEPCT24HOUR);
                        iv_un_fav11.setVisibility(View.GONE);
                        iv_fav11.setVisibility(View.VISIBLE);
                        new AlertTask("BTG").execute();
                        btg = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav12:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Qtum", "QTUM", mArrayList.get(12).MKTCAP, mArrayList.get(12).PRICE, mArrayList.get(12).CHANGE24HOUR, mArrayList.get(12).CHANGEPCT24HOUR);
                        iv_un_fav12.setVisibility(View.GONE);
                        iv_fav12.setVisibility(View.VISIBLE);
                        new AlertTask("QTUM").execute();
                        qtum = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav13:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Stellar", "XLM", mArrayList.get(13).MKTCAP, mArrayList.get(13).PRICE, mArrayList.get(13).CHANGE24HOUR, mArrayList.get(13).CHANGEPCT24HOUR);
                        iv_un_fav13.setVisibility(View.GONE);
                        iv_fav13.setVisibility(View.VISIBLE);
                        new AlertTask("XLM").execute();
                        xlm = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav14:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("NEO", "NEO", mArrayList.get(14).MKTCAP, mArrayList.get(14).PRICE, mArrayList.get(14).CHANGE24HOUR, mArrayList.get(14).CHANGEPCT24HOUR);
                        iv_un_fav14.setVisibility(View.GONE);
                        iv_fav14.setVisibility(View.VISIBLE);
                        new AlertTask("NEO").execute();
                        neo = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav15:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("EthereumClas.", "ETC", mArrayList.get(15).MKTCAP, mArrayList.get(15).PRICE, mArrayList.get(15).CHANGE24HOUR, mArrayList.get(15).CHANGEPCT24HOUR);
                        iv_un_fav15.setVisibility(View.GONE);
                        iv_fav15.setVisibility(View.VISIBLE);
                        new AlertTask("ETC").execute();
                        etc = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav16:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Zcash", "ZEC", mArrayList.get(16).MKTCAP, mArrayList.get(16).PRICE, mArrayList.get(16).CHANGE24HOUR, mArrayList.get(16).CHANGEPCT24HOUR);
                        iv_un_fav16.setVisibility(View.GONE);
                        iv_fav16.setVisibility(View.VISIBLE);
                        new AlertTask("ZEC").execute();
                        zec = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav17:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Steem", "STEEM", mArrayList.get(17).MKTCAP, mArrayList.get(17).PRICE, mArrayList.get(17).CHANGE24HOUR, mArrayList.get(17).CHANGEPCT24HOUR);
                        iv_un_fav17.setVisibility(View.GONE);
                        iv_fav17.setVisibility(View.VISIBLE);
                        new AlertTask("STEEM").execute();
                        steem = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav18:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("ZCoin", "XZC", mArrayList.get(18).MKTCAP, mArrayList.get(18).PRICE, mArrayList.get(18).CHANGE24HOUR, mArrayList.get(18).CHANGEPCT24HOUR);
                        iv_un_fav18.setVisibility(View.GONE);
                        iv_fav18.setVisibility(View.VISIBLE);
                        new AlertTask("XZC").execute();
                        xzc = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav19:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Storj", "STORJ", mArrayList.get(19).MKTCAP, mArrayList.get(19).PRICE, mArrayList.get(19).CHANGE24HOUR, mArrayList.get(19).CHANGEPCT24HOUR);
                        iv_un_fav19.setVisibility(View.GONE);
                        iv_fav19.setVisibility(View.VISIBLE);
                        new AlertTask("STORJ").execute();
                        storj = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav20:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Aion", "AION", mArrayList.get(20).MKTCAP, mArrayList.get(20).PRICE, mArrayList.get(20).CHANGE24HOUR, mArrayList.get(20).CHANGEPCT24HOUR);
                        iv_un_fav20.setVisibility(View.GONE);
                        iv_fav20.setVisibility(View.VISIBLE);
                        new AlertTask("AION").execute();
                        aion = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav21:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Huobi Token", "HT", mArrayList.get(21).MKTCAP, mArrayList.get(21).PRICE, mArrayList.get(21).CHANGE24HOUR, mArrayList.get(21).CHANGEPCT24HOUR);
                        iv_un_fav21.setVisibility(View.GONE);
                        iv_fav21.setVisibility(View.VISIBLE);
                        new AlertTask("HT").execute();
                        ht = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav22:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Tronix", "TRX", mArrayList.get(22).MKTCAP, mArrayList.get(22).PRICE, mArrayList.get(22).CHANGE24HOUR, mArrayList.get(22).CHANGEPCT24HOUR);
                        iv_un_fav22.setVisibility(View.GONE);
                        iv_fav22.setVisibility(View.VISIBLE);
                        new AlertTask("TRX").execute();
                        trx = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav23:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Nano", "XRB", mArrayList.get(23).MKTCAP, mArrayList.get(23).PRICE, mArrayList.get(23).CHANGE24HOUR, mArrayList.get(23).CHANGEPCT24HOUR);
                        iv_un_fav23.setVisibility(View.GONE);
                        iv_fav23.setVisibility(View.VISIBLE);
                        new AlertTask("XRB").execute();
                        xrb = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav24:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("OmiseGo", "OMG", mArrayList.get(24).MKTCAP, mArrayList.get(24).PRICE, mArrayList.get(24).CHANGE24HOUR, mArrayList.get(24).CHANGEPCT24HOUR);
                        iv_un_fav24.setVisibility(View.GONE);
                        iv_fav24.setVisibility(View.VISIBLE);
                        new AlertTask("OMG").execute();
                        omg = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav25:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Elastos", "ELA", mArrayList.get(25).MKTCAP, mArrayList.get(5).PRICE, mArrayList.get(25).CHANGE24HOUR, mArrayList.get(25).CHANGEPCT24HOUR);
                        iv_un_fav25.setVisibility(View.GONE);
                        iv_fav25.setVisibility(View.VISIBLE);
                        new AlertTask("ELA").execute();
                        ela = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav26:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Binance Coin", "BNB", mArrayList.get(26).MKTCAP, mArrayList.get(26).PRICE, mArrayList.get(26).CHANGE24HOUR, mArrayList.get(26).CHANGEPCT24HOUR);
                        iv_un_fav26.setVisibility(View.GONE);
                        iv_fav26.setVisibility(View.VISIBLE);
                        new AlertTask("BNB").execute();
                        bnb = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav27:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Vechain", "VEN", mArrayList.get(27).MKTCAP, mArrayList.get(27).PRICE, mArrayList.get(27).CHANGE24HOUR, mArrayList.get(27).CHANGEPCT24HOUR);
                        iv_un_fav27.setVisibility(View.GONE);
                        iv_fav27.setVisibility(View.VISIBLE);
                        new AlertTask("VEN").execute();
                        ven = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav28:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("ZClassic", "ZCL", mArrayList.get(28).MKTCAP, mArrayList.get(28).PRICE, mArrayList.get(28).CHANGE24HOUR, mArrayList.get(28).CHANGEPCT24HOUR);
                        iv_un_fav28.setVisibility(View.GONE);
                        iv_fav28.setVisibility(View.VISIBLE);
                        new AlertTask("ZCL").execute();
                        zcl = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav29:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Digix DAO", "DGD", mArrayList.get(29).MKTCAP, mArrayList.get(29).PRICE, mArrayList.get(29).CHANGE24HOUR, mArrayList.get(29).CHANGEPCT24HOUR);
                        iv_un_fav29.setVisibility(View.GONE);
                        iv_fav29.setVisibility(View.VISIBLE);
                        new AlertTask("DGD").execute();
                        dgd = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav30:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Odyssey", "OCN", mArrayList.get(30).MKTCAP, mArrayList.get(30).PRICE, mArrayList.get(30).CHANGE24HOUR, mArrayList.get(30).CHANGEPCT24HOUR);
                        iv_un_fav30.setVisibility(View.GONE);
                        iv_fav30.setVisibility(View.VISIBLE);
                        new AlertTask("OCN").execute();
                        ocn = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav31:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("BlockMason Credit P.", "BCPT", mArrayList.get(31).MKTCAP, mArrayList.get(31).PRICE, mArrayList.get(31).CHANGE24HOUR, mArrayList.get(31).CHANGEPCT24HOUR);
                        iv_un_fav31.setVisibility(View.GONE);
                        iv_fav31.setVisibility(View.VISIBLE);
                        new AlertTask("BCPT").execute();
                        bcpt = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav32:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Lunyr", "LUN", mArrayList.get(32).MKTCAP, mArrayList.get(32).PRICE, mArrayList.get(32).CHANGE24HOUR, mArrayList.get(32).CHANGEPCT24HOUR);
                        iv_un_fav32.setVisibility(View.GONE);
                        iv_fav32.setVisibility(View.VISIBLE);
                        new AlertTask("LUN").execute();
                        lun = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav33:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("IOS token", "IOST", mArrayList.get(33).MKTCAP, mArrayList.get(33).PRICE, mArrayList.get(33).CHANGE24HOUR, mArrayList.get(33).CHANGEPCT24HOUR);
                        iv_un_fav33.setVisibility(View.GONE);
                        iv_fav33.setVisibility(View.VISIBLE);
                        new AlertTask("IOST").execute();
                        iost = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav34:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Hshare", "HSR", mArrayList.get(34).MKTCAP, mArrayList.get(34).PRICE, mArrayList.get(34).CHANGE24HOUR, mArrayList.get(34).CHANGEPCT24HOUR);
                        iv_un_fav34.setVisibility(View.GONE);
                        iv_fav34.setVisibility(View.VISIBLE);
                        new AlertTask("HSR").execute();
                        hsr = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav35:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("ICON Project", "ICX", mArrayList.get(35).MKTCAP, mArrayList.get(35).PRICE, mArrayList.get(35).CHANGE24HOUR, mArrayList.get(35).CHANGEPCT24HOUR);
                        iv_un_fav35.setVisibility(View.GONE);
                        iv_fav35.setVisibility(View.VISIBLE);
                        new AlertTask("ICX").execute();
                        icx = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }


                break;
            case R.id.iv_un_fav36:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Lisk", "LSK", mArrayList.get(36).MKTCAP, mArrayList.get(36).PRICE, mArrayList.get(36).CHANGE24HOUR, mArrayList.get(36).CHANGEPCT24HOUR);
                        iv_un_fav36.setVisibility(View.GONE);
                        iv_fav36.setVisibility(View.VISIBLE);
                        new AlertTask("LSK").execute();
                        lsk = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }


                break;
            case R.id.iv_un_fav37:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Neblio", "NEBL", mArrayList.get(37).MKTCAP, mArrayList.get(37).PRICE, mArrayList.get(37).CHANGE24HOUR, mArrayList.get(37).CHANGEPCT24HOUR);
                        iv_un_fav37.setVisibility(View.GONE);
                        iv_fav37.setVisibility(View.VISIBLE);
                        new AlertTask("NEBL").execute();
                        nebl = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav38:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Waves", "WAVES", mArrayList.get(38).MKTCAP, mArrayList.get(38).PRICE, mArrayList.get(38).CHANGE24HOUR, mArrayList.get(38).CHANGEPCT24HOUR);
                        iv_un_fav38.setVisibility(View.GONE);
                        iv_fav38.setVisibility(View.VISIBLE);
                        new AlertTask("WAVES").execute();
                        waves = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav39:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Bluzelle", "BLZ", mArrayList.get(39).MKTCAP, mArrayList.get(39).PRICE, mArrayList.get(39).CHANGE24HOUR, mArrayList.get(39).CHANGEPCT24HOUR);
                        iv_un_fav39.setVisibility(View.GONE);
                        iv_fav39.setVisibility(View.VISIBLE);
                        new AlertTask("BLZ").execute();
                        blz = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav40:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Ink", "INK", mArrayList.get(40).MKTCAP, mArrayList.get(40).PRICE, mArrayList.get(40).CHANGE24HOUR, mArrayList.get(40).CHANGEPCT24HOUR);
                        iv_un_fav40.setVisibility(View.GONE);
                        iv_fav40.setVisibility(View.VISIBLE);
                        new AlertTask("INK").execute();
                        ink = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav41:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("AdEx", "ADX", mArrayList.get(41).MKTCAP, mArrayList.get(41).PRICE, mArrayList.get(41).CHANGE24HOUR, mArrayList.get(41).CHANGEPCT24HOUR);
                        iv_un_fav41.setVisibility(View.GONE);
                        iv_fav41.setVisibility(View.VISIBLE);
                        new AlertTask("ADX").execute();
                        adx = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav42:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Verge", "XVG", mArrayList.get(42).MKTCAP, mArrayList.get(42).PRICE, mArrayList.get(42).CHANGE24HOUR, mArrayList.get(42).CHANGEPCT24HOUR);
                        iv_un_fav42.setVisibility(View.GONE);
                        iv_fav42.setVisibility(View.VISIBLE);
                        new AlertTask("XVG").execute();
                        xvg = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav43:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Metal", "MTL", mArrayList.get(43).MKTCAP, mArrayList.get(43).PRICE, mArrayList.get(43).CHANGE24HOUR, mArrayList.get(43).CHANGEPCT24HOUR);
                        iv_un_fav43.setVisibility(View.GONE);
                        iv_fav43.setVisibility(View.VISIBLE);
                        new AlertTask("MTL").execute();
                        mtl = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav44:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("SirinLabs", "SRN", mArrayList.get(44).MKTCAP, mArrayList.get(44).PRICE, mArrayList.get(44).CHANGE24HOUR, mArrayList.get(44).CHANGEPCT24HOUR);
                        iv_un_fav44.setVisibility(View.GONE);
                        iv_fav44.setVisibility(View.VISIBLE);
                        new AlertTask("SRN").execute();
                        srn = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav45:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("0x", "ZRX", mArrayList.get(45).MKTCAP, mArrayList.get(45).PRICE, mArrayList.get(45).CHANGE24HOUR, mArrayList.get(45).CHANGEPCT24HOUR);
                        iv_un_fav45.setVisibility(View.GONE);
                        iv_fav45.setVisibility(View.VISIBLE);
                        new AlertTask("ZRX").execute();
                        zrx = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav46:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("iEx.ec", "RLC", mArrayList.get(46).MKTCAP, mArrayList.get(46).PRICE, mArrayList.get(46).CHANGE24HOUR, mArrayList.get(46).CHANGEPCT24HOUR);
                        iv_un_fav46.setVisibility(View.GONE);
                        iv_fav46.setVisibility(View.VISIBLE);
                        new AlertTask("RLC").execute();
                        rlc = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav47:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Theta", "THETA", mArrayList.get(47).MKTCAP, mArrayList.get(47).PRICE, mArrayList.get(47).CHANGE24HOUR, mArrayList.get(47).CHANGEPCT24HOUR);
                        iv_un_fav47.setVisibility(View.GONE);
                        iv_fav47.setVisibility(View.VISIBLE);
                        new AlertTask("THETA").execute();
                        theta = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav48:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Bitcoin Diamond", "BCD", mArrayList.get(48).MKTCAP, mArrayList.get(48).PRICE, mArrayList.get(48).CHANGE24HOUR, mArrayList.get(48).CHANGEPCT24HOUR);
                        iv_un_fav48.setVisibility(View.GONE);
                        iv_fav48.setVisibility(View.VISIBLE);
                        new AlertTask("BCD").execute();
                        bcd = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav49:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("OpenANX", "OAX", mArrayList.get(49).MKTCAP, mArrayList.get(49).PRICE, mArrayList.get(49).CHANGE24HOUR, mArrayList.get(49).CHANGEPCT24HOUR);
                        iv_un_fav49.setVisibility(View.GONE);
                        iv_fav49.setVisibility(View.VISIBLE);
                        new AlertTask("OAX").execute();
                        oax = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav50:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("aelf", "ELF", mArrayList.get(50).MKTCAP, mArrayList.get(50).PRICE, mArrayList.get(50).CHANGE24HOUR, mArrayList.get(50).CHANGEPCT24HOUR);
                        iv_un_fav50.setVisibility(View.GONE);
                        iv_fav50.setVisibility(View.VISIBLE);
                        new AlertTask("ELF").execute();
                        elf = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav51:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("INS Ecosystem", "INS", mArrayList.get(51).MKTCAP, mArrayList.get(51).PRICE, mArrayList.get(51).CHANGE24HOUR, mArrayList.get(51).CHANGEPCT24HOUR);
                        iv_un_fav51.setVisibility(View.GONE);
                        iv_fav51.setVisibility(View.VISIBLE);
                        new AlertTask("INS").execute();
                        ins = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav52:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Zilliqa", "ZIL", mArrayList.get(52).MKTCAP, mArrayList.get(52).PRICE, mArrayList.get(52).CHANGE24HOUR, mArrayList.get(52).CHANGEPCT24HOUR);
                        iv_un_fav52.setVisibility(View.GONE);
                        iv_fav52.setVisibility(View.VISIBLE);
                        new AlertTask("ZIL").execute();
                        zil = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav53:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Aeternity", "AE", mArrayList.get(53).MKTCAP, mArrayList.get(53).PRICE, mArrayList.get(53).CHANGE24HOUR, mArrayList.get(53).CHANGEPCT24HOUR);
                        iv_un_fav53.setVisibility(View.GONE);
                        iv_fav53.setVisibility(View.VISIBLE);
                        new AlertTask("AE").execute();
                        ae = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav54:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Propy", "PRO", mArrayList.get(54).MKTCAP, mArrayList.get(54).PRICE, mArrayList.get(54).CHANGE24HOUR, mArrayList.get(54).CHANGEPCT24HOUR);
                        iv_un_fav54.setVisibility(View.GONE);
                        iv_fav54.setVisibility(View.VISIBLE);
                        new AlertTask("PRO").execute();
                        pro = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav55:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Dogecoin", "DOGE", mArrayList.get(55).MKTCAP, mArrayList.get(55).PRICE, mArrayList.get(55).CHANGE24HOUR, mArrayList.get(55).CHANGEPCT24HOUR);
                        iv_un_fav55.setVisibility(View.GONE);
                        iv_fav55.setVisibility(View.VISIBLE);
                        new AlertTask("DOGE").execute();
                        doge = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav56:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("IoT Chain", "ITC", mArrayList.get(56).MKTCAP, mArrayList.get(56).PRICE, mArrayList.get(56).CHANGE24HOUR, mArrayList.get(56).CHANGEPCT24HOUR);
                        iv_un_fav56.setVisibility(View.GONE);
                        iv_fav56.setVisibility(View.VISIBLE);
                        new AlertTask("ITC").execute();
                        itc = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav57:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("ReddCoin", "RDD", mArrayList.get(57).MKTCAP, mArrayList.get(57).PRICE, mArrayList.get(57).CHANGE24HOUR, mArrayList.get(57).CHANGEPCT24HOUR);
                        iv_un_fav57.setVisibility(View.GONE);
                        iv_fav57.setVisibility(View.VISIBLE);
                        new AlertTask("RDD").execute();
                        rdd = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav58:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("SwftCoin", "SWFTC", mArrayList.get(58).MKTCAP, mArrayList.get(58).PRICE, mArrayList.get(58).CHANGE24HOUR, mArrayList.get(558).CHANGEPCT24HOUR);
                        iv_un_fav58.setVisibility(View.GONE);
                        iv_fav58.setVisibility(View.VISIBLE);
                        new AlertTask("SWFTC").execute();
                        swftc = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }


                break;
            case R.id.iv_un_fav59:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Monaco", "MCO", mArrayList.get(59).MKTCAP, mArrayList.get(59).PRICE, mArrayList.get(59).CHANGE24HOUR, mArrayList.get(59).CHANGEPCT24HOUR);
                        iv_un_fav59.setVisibility(View.GONE);
                        iv_fav59.setVisibility(View.VISIBLE);
                        new AlertTask("MCO").execute();
                        mco = "0";

                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav60:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Aeron", "ARN", mArrayList.get(60).MKTCAP, mArrayList.get(60).PRICE, mArrayList.get(60).CHANGE24HOUR, mArrayList.get(60).CHANGEPCT24HOUR);
                        iv_un_fav60.setVisibility(View.GONE);
                        iv_fav60.setVisibility(View.VISIBLE);
                        new AlertTask("ARN").execute();
                        arn = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav61:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Medicalchain", "MTN*", mArrayList.get(61).MKTCAP, mArrayList.get(61).PRICE, mArrayList.get(61).CHANGE24HOUR, mArrayList.get(61).CHANGEPCT24HOUR);
                        iv_un_fav61.setVisibility(View.GONE);
                        iv_fav61.setVisibility(View.VISIBLE);
                        new AlertTask("MTN*").execute();
                        mtn = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav62:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Bread token", "BRD", mArrayList.get(62).MKTCAP, mArrayList.get(62).PRICE, mArrayList.get(62).CHANGE24HOUR, mArrayList.get(62).CHANGEPCT24HOUR);
                        iv_un_fav62.setVisibility(View.GONE);
                        iv_fav62.setVisibility(View.VISIBLE);
                        new AlertTask("BRD").execute();
                        brd = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav63:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Santiment", "SAN", mArrayList.get(63).MKTCAP, mArrayList.get(63).PRICE, mArrayList.get(63).CHANGE24HOUR, mArrayList.get(63).CHANGEPCT24HOUR);

                        iv_un_fav63.setVisibility(View.GONE);
                        iv_fav63.setVisibility(View.VISIBLE);
                        new AlertTask("SAN").execute();
                        san = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav64:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Nebulas", "NAS", mArrayList.get(64).MKTCAP, mArrayList.get(64).PRICE, mArrayList.get(64).CHANGE24HOUR, mArrayList.get(64).CHANGEPCT24HOUR);
                        iv_un_fav64.setVisibility(View.GONE);
                        iv_fav64.setVisibility(View.VISIBLE);
                        new AlertTask("NAS").execute();
                        nas = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav65:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Genesis Vision", "GVT", mArrayList.get(65).MKTCAP, mArrayList.get(65).PRICE, mArrayList.get(65).CHANGE24HOUR, mArrayList.get(65).CHANGEPCT24HOUR);
                        iv_un_fav65.setVisibility(View.GONE);
                        iv_fav65.setVisibility(View.VISIBLE);
                        new AlertTask("GVT").execute();
                        gvt = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav66:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("QunQun", "QUN", mArrayList.get(66).MKTCAP, mArrayList.get(66).PRICE, mArrayList.get(66).CHANGE24HOUR, mArrayList.get(66).CHANGEPCT24HOUR);
                        iv_un_fav66.setVisibility(View.GONE);
                        iv_fav66.setVisibility(View.VISIBLE);
                        new AlertTask("QUN").execute();
                        qun = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav67:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Waltonchain", "WTC", mArrayList.get(67).MKTCAP, mArrayList.get(67).PRICE, mArrayList.get(67).CHANGE24HOUR, mArrayList.get(67).CHANGEPCT24HOUR);
                        iv_un_fav67.setVisibility(View.GONE);
                        iv_fav67.setVisibility(View.VISIBLE);
                        new AlertTask("WTC").execute();
                        wtc = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav68:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Factoids", "FCT", mArrayList.get(68).MKTCAP, mArrayList.get(68).PRICE, mArrayList.get(68).CHANGE24HOUR, mArrayList.get(68).CHANGEPCT24HOUR);
                        iv_un_fav68.setVisibility(View.GONE);
                        iv_fav68.setVisibility(View.VISIBLE);
                        new AlertTask("FCT").execute();
                        fct = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav69:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("CoinDash", "CDT", mArrayList.get(69).MKTCAP, mArrayList.get(69).PRICE, mArrayList.get(69).CHANGE24HOUR, mArrayList.get(69).CHANGEPCT24HOUR);
                        iv_un_fav69.setVisibility(View.GONE);
                        iv_fav69.setVisibility(View.VISIBLE);
                        new AlertTask("CDT").execute();
                        cdt = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_un_fav70:

                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        db.addCoins("Ripio", "RCN", mArrayList.get(70).MKTCAP, mArrayList.get(70).PRICE, mArrayList.get(70).CHANGE24HOUR, mArrayList.get(70).CHANGEPCT24HOUR);
                        iv_un_fav70.setVisibility(View.GONE);
                        iv_fav70.setVisibility(View.VISIBLE);
                        new AlertTask("RCN").execute();
                        rcn = "0";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }
                break;


            case R.id.iv_fav:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("BTC").execute();
                        iv_un_fav.setVisibility(View.VISIBLE);
                        iv_fav.setVisibility(View.GONE);
                        db.Deleteall("BTC");
                        btc = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav1:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ETH").execute();
                        iv_un_fav1.setVisibility(View.VISIBLE);
                        iv_fav1.setVisibility(View.GONE);
                        db.Deleteall("ETH");
                        eth = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }
                break;
            case R.id.iv_fav2:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("BCH").execute();
                        iv_un_fav2.setVisibility(View.VISIBLE);
                        iv_fav2.setVisibility(View.GONE);
                        db.Deleteall("BCH");
                        bch = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }
                break;
            case R.id.iv_fav3:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("XRP").execute();
                        iv_un_fav3.setVisibility(View.VISIBLE);
                        iv_fav3.setVisibility(View.GONE);
                        db.Deleteall("XRP");
                        xrp = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }
                break;
            case R.id.iv_fav4:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("LTC").execute();
                        iv_un_fav4.setVisibility(View.VISIBLE);
                        iv_fav4.setVisibility(View.GONE);
                        db.Deleteall("LTC");
                        ltc = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }
                break;
            case R.id.iv_fav5:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ADA").execute();
                        iv_un_fav5.setVisibility(View.VISIBLE);
                        iv_fav5.setVisibility(View.GONE);
                        db.Deleteall("ADA");
                        ada = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav6:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("IOT").execute();
                        iv_un_fav6.setVisibility(View.VISIBLE);
                        iv_fav6.setVisibility(View.GONE);
                        db.Deleteall("IOT");
                        iot = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav7:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("DASH").execute();
                        iv_un_fav7.setVisibility(View.VISIBLE);
                        iv_fav7.setVisibility(View.GONE);
                        db.Deleteall("DASH");
                        dash = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav8:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("XEM").execute();
                        iv_un_fav8.setVisibility(View.VISIBLE);
                        iv_fav8.setVisibility(View.GONE);
                        db.Deleteall("XEM");
                        xem = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav9:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("XMR").execute();
                        iv_un_fav9.setVisibility(View.VISIBLE);
                        iv_fav9.setVisibility(View.GONE);
                        db.Deleteall("XMR");
                        xmr = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav10:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("EOS").execute();
                        iv_un_fav10.setVisibility(View.VISIBLE);
                        iv_fav10.setVisibility(View.GONE);
                        db.Deleteall("EOS");
                        eos = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav11:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("BTG").execute();
                        iv_un_fav11.setVisibility(View.VISIBLE);
                        iv_fav11.setVisibility(View.GONE);
                        db.Deleteall("BTG");
                        btg = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav12:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("QTUM").execute();
                        iv_un_fav12.setVisibility(View.VISIBLE);
                        iv_fav12.setVisibility(View.GONE);
                        db.Deleteall("QTUM");
                        qtum = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav13:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("XLM").execute();
                        iv_un_fav13.setVisibility(View.VISIBLE);
                        iv_fav13.setVisibility(View.GONE);
                        db.Deleteall("XLM");
                        xlm = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav14:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("NEO").execute();
                        iv_un_fav14.setVisibility(View.VISIBLE);
                        iv_fav14.setVisibility(View.GONE);
                        db.Deleteall("NEO");
                        neo = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav15:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ETC").execute();
                        iv_un_fav15.setVisibility(View.VISIBLE);
                        iv_fav15.setVisibility(View.GONE);
                        db.Deleteall("ETC");
                        etc = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav16:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ZEC").execute();
                        iv_un_fav16.setVisibility(View.VISIBLE);
                        iv_fav16.setVisibility(View.GONE);
                        db.Deleteall("ZEC");
                        zec = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav17:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("STEEM").execute();
                        iv_un_fav17.setVisibility(View.VISIBLE);
                        iv_fav17.setVisibility(View.GONE);
                        db.Deleteall("STEEM");
                        steem = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav18:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("XZC").execute();
                        iv_un_fav18.setVisibility(View.VISIBLE);
                        iv_fav18.setVisibility(View.GONE);
                        db.Deleteall("XZC");
                        xzc = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav19:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("STORJ").execute();
                        iv_un_fav19.setVisibility(View.VISIBLE);
                        iv_fav19.setVisibility(View.GONE);
                        db.Deleteall("STORJ");
                        storj = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav20:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("AION").execute();
                        iv_un_fav20.setVisibility(View.VISIBLE);
                        iv_fav20.setVisibility(View.GONE);
                        db.Deleteall("AION");
                        aion = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav21:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("HT").execute();
                        iv_un_fav21.setVisibility(View.VISIBLE);
                        iv_fav21.setVisibility(View.GONE);
                        db.Deleteall("HT");
                        ht = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav22:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("TRX").execute();
                        iv_un_fav22.setVisibility(View.VISIBLE);
                        iv_fav22.setVisibility(View.GONE);
                        db.Deleteall("TRX");
                        trx = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav23:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("XRB").execute();
                        iv_un_fav23.setVisibility(View.VISIBLE);
                        iv_fav23.setVisibility(View.GONE);
                        db.Deleteall("XRB");
                        xrb = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav24:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("OMG").execute();
                        iv_un_fav24.setVisibility(View.VISIBLE);
                        iv_fav24.setVisibility(View.GONE);
                        db.Deleteall("OMG");
                        omg = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav25:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ELA").execute();
                        iv_un_fav25.setVisibility(View.VISIBLE);
                        iv_fav25.setVisibility(View.GONE);
                        db.Deleteall("ELA");
                        ela = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav26:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("BNB").execute();
                        iv_un_fav26.setVisibility(View.VISIBLE);
                        iv_fav26.setVisibility(View.GONE);
                        db.Deleteall("BNB");
                        bnb = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav27:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("VEN").execute();
                        iv_un_fav27.setVisibility(View.VISIBLE);
                        iv_fav27.setVisibility(View.GONE);
                        db.Deleteall("VEN");
                        ven = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav28:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ZCL").execute();
                        iv_un_fav28.setVisibility(View.VISIBLE);
                        iv_fav28.setVisibility(View.GONE);
                        db.Deleteall("ZCL");
                        zcl = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav29:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("DGD").execute();
                        iv_un_fav29.setVisibility(View.VISIBLE);
                        iv_fav29.setVisibility(View.GONE);
                        db.Deleteall("DGD");
                        dgd = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav30:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("OCN").execute();
                        iv_un_fav30.setVisibility(View.VISIBLE);
                        iv_fav30.setVisibility(View.GONE);
                        db.Deleteall("OCN");
                        ocn = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav31:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("BCPT").execute();
                        iv_un_fav31.setVisibility(View.VISIBLE);
                        iv_fav31.setVisibility(View.GONE);
                        db.Deleteall("BCPT");
                        bcpt = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav32:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("LUN").execute();
                        iv_un_fav32.setVisibility(View.VISIBLE);
                        iv_fav32.setVisibility(View.GONE);
                        db.Deleteall("LUN");
                        lun = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav33:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("IOST").execute();
                        iv_un_fav33.setVisibility(View.VISIBLE);
                        iv_fav33.setVisibility(View.GONE);
                        db.Deleteall("IOST");
                        iost = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav34:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("HSR").execute();
                        iv_un_fav34.setVisibility(View.VISIBLE);
                        iv_fav34.setVisibility(View.GONE);
                        db.Deleteall("HSR");
                        hsr = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav35:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ICX").execute();
                        iv_un_fav35.setVisibility(View.VISIBLE);
                        iv_fav35.setVisibility(View.GONE);
                        db.Deleteall("ICX");
                        icx = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav36:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("LSK").execute();
                        iv_un_fav36.setVisibility(View.VISIBLE);
                        iv_fav36.setVisibility(View.GONE);
                        db.Deleteall("LSK");
                        lsk = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav37:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("NEBL").execute();
                        iv_un_fav37.setVisibility(View.VISIBLE);
                        iv_fav37.setVisibility(View.GONE);
                        db.Deleteall("NEBL");
                        nebl = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav38:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("WAVES").execute();
                        iv_un_fav38.setVisibility(View.VISIBLE);
                        iv_fav38.setVisibility(View.GONE);
                        db.Deleteall("WAVES");
                        waves = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav39:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("BLZ").execute();
                        iv_un_fav39.setVisibility(View.VISIBLE);
                        iv_fav39.setVisibility(View.GONE);
                        db.Deleteall("BLZ");
                        blz = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav40:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("INK").execute();
                        iv_un_fav40.setVisibility(View.VISIBLE);
                        iv_fav40.setVisibility(View.GONE);
                        db.Deleteall("INK");
                        ink = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav41:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ADX").execute();
                        iv_un_fav41.setVisibility(View.VISIBLE);
                        iv_fav41.setVisibility(View.GONE);
                        db.Deleteall("ADX");
                        adx = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav42:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("XVG").execute();
                        iv_un_fav42.setVisibility(View.VISIBLE);
                        iv_fav42.setVisibility(View.GONE);
                        db.Deleteall("XVG");
                        xvg = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav43:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("MTL").execute();
                        iv_un_fav43.setVisibility(View.VISIBLE);
                        iv_fav43.setVisibility(View.GONE);
                        db.Deleteall("MTL");
                        mtl = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav44:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("SRN").execute();
                        iv_un_fav44.setVisibility(View.VISIBLE);
                        iv_fav44.setVisibility(View.GONE);
                        db.Deleteall("SRN");
                        srn = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav45:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ZRX").execute();
                        iv_un_fav45.setVisibility(View.VISIBLE);
                        iv_fav45.setVisibility(View.GONE);
                        db.Deleteall("ZRX");
                        zrx = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav46:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("RLC").execute();
                        iv_un_fav46.setVisibility(View.VISIBLE);
                        iv_fav46.setVisibility(View.GONE);
                        db.Deleteall("RLC");
                        rlc = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav47:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("THETA").execute();
                        iv_un_fav47.setVisibility(View.VISIBLE);
                        iv_fav47.setVisibility(View.GONE);
                        db.Deleteall("THETA");
                        theta = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav48:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("BCD").execute();
                        iv_un_fav48.setVisibility(View.VISIBLE);
                        iv_fav48.setVisibility(View.GONE);
                        db.Deleteall("BCD");
                        bcd = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav49:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("OAX").execute();
                        iv_un_fav49.setVisibility(View.VISIBLE);
                        iv_fav49.setVisibility(View.GONE);
                        db.Deleteall("OAX");
                        oax = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav50:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ELF").execute();
                        iv_un_fav50.setVisibility(View.VISIBLE);
                        iv_fav50.setVisibility(View.GONE);
                        db.Deleteall("ELF");
                        elf = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav51:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("INS").execute();
                        iv_un_fav51.setVisibility(View.VISIBLE);
                        iv_fav51.setVisibility(View.GONE);
                        db.Deleteall("INS");
                        ins = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav52:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ZIL").execute();
                        iv_un_fav52.setVisibility(View.VISIBLE);
                        iv_fav52.setVisibility(View.GONE);
                        db.Deleteall("ZIL");
                        zil = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav53:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("AE").execute();
                        iv_un_fav53.setVisibility(View.VISIBLE);
                        iv_fav53.setVisibility(View.GONE);
                        db.Deleteall("AE");
                        ae = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav54:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("PRO").execute();
                        iv_un_fav54.setVisibility(View.VISIBLE);
                        iv_fav54.setVisibility(View.GONE);
                        db.Deleteall("PRO");
                        pro = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav55:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("DOGE").execute();
                        iv_un_fav55.setVisibility(View.VISIBLE);
                        iv_fav55.setVisibility(View.GONE);
                        db.Deleteall("DOGE");
                        doge = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav56:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ITC").execute();
                        iv_un_fav56.setVisibility(View.VISIBLE);
                        iv_fav56.setVisibility(View.GONE);
                        db.Deleteall("ITC");
                        itc = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav57:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("RDD").execute();
                        iv_un_fav57.setVisibility(View.VISIBLE);
                        iv_fav57.setVisibility(View.GONE);
                        db.Deleteall("RDD");
                        rdd = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav58:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("SWFTC").execute();
                        iv_un_fav58.setVisibility(View.VISIBLE);
                        iv_fav58.setVisibility(View.GONE);
                        db.Deleteall("SWFTC");
                        swftc = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav59:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("MCO").execute();
                        iv_un_fav59.setVisibility(View.VISIBLE);
                        iv_fav59.setVisibility(View.GONE);
                        db.Deleteall("MCO");
                        mco = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav60:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("ARN").execute();
                        iv_un_fav60.setVisibility(View.VISIBLE);
                        iv_fav60.setVisibility(View.GONE);
                        db.Deleteall("ARN");
                        arn = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav61:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("MTN*").execute();
                        iv_un_fav61.setVisibility(View.VISIBLE);
                        iv_fav61.setVisibility(View.GONE);
                        db.Deleteall("MTN*");
                        mtn = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav62:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("BRD").execute();
                        iv_un_fav62.setVisibility(View.VISIBLE);
                        iv_fav62.setVisibility(View.GONE);
                        db.Deleteall("BRD");
                        brd = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav63:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("SAN").execute();
                        iv_un_fav63.setVisibility(View.VISIBLE);
                        iv_fav63.setVisibility(View.GONE);
                        db.Deleteall("SAN");
                        san = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav64:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("NAS").execute();
                        iv_un_fav64.setVisibility(View.VISIBLE);
                        iv_fav64.setVisibility(View.GONE);
                        db.Deleteall("NAS");
                        nas = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav65:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("GVT").execute();
                        iv_un_fav65.setVisibility(View.VISIBLE);
                        iv_fav65.setVisibility(View.GONE);
                        db.Deleteall("GVT");
                        gvt = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav66:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("QUN").execute();
                        iv_un_fav66.setVisibility(View.VISIBLE);
                        iv_fav66.setVisibility(View.GONE);
                        db.Deleteall("QUN");
                        qun = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav67:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("WTC").execute();
                        iv_un_fav67.setVisibility(View.VISIBLE);
                        iv_fav67.setVisibility(View.GONE);
                        db.Deleteall("WTC");
                        wtc = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav68:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("FCT").execute();
                        iv_un_fav68.setVisibility(View.VISIBLE);
                        iv_fav68.setVisibility(View.GONE);
                        db.Deleteall("FCT");
                        fct = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav69:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("CDT").execute();
                        iv_un_fav69.setVisibility(View.VISIBLE);
                        iv_fav69.setVisibility(View.GONE);
                        db.Deleteall("CDT");
                        cdt = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
            case R.id.iv_fav70:
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(getActivity())) {
                        new DeleteAlertTask("RCN").execute();
                        iv_un_fav70.setVisibility(View.VISIBLE);
                        iv_fav70.setVisibility(View.GONE);
                        db.Deleteall("RCN");
                        rcn = "1";
                    } else {
                        utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                    }
                } else {
                    loginPopupViewControls();
                }

                break;
        }
    }

    @Override
    public void onCapabilityChanged(@NonNull CapabilityInfo capabilityInfo) {

    }

    @Override
    public void onDataChanged(@NonNull DataEventBuffer dataEventBuffer) {

    }

    @Override
    public void onMessageReceived(@NonNull MessageEvent messageEvent) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_exchange) {
            Intent intent = new Intent(getActivity(), Exchange_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_toplist) {
            Intent intent = new Intent(getActivity(), TopList_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_icolist) {
            Intent intent = new Intent(getActivity(), ICO_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_pricealert) {
            if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                if (utils.isNetworkAvailable(getActivity())) {
                    Intent intent = new Intent(getActivity(), Price_Alert1_Activity.class);
                    startActivity(intent);
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
                }
            } else {
                loginPopupViewControls();
            }

        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(getActivity(), Profile_Activity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
                ParsedResponse p = Service.apiGetCoin(getActivity(), Baseurl);
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

                db.Deleteallcoins();
                for (int i = 0; i < mArrayList.size(); i++) {
                    db.addMainCoins("Bitcoin", "BTC", mArrayList.get(i).MKTCAP, mArrayList.get(i).PRICE, mArrayList.get(i).CHANGE24HOUR, mArrayList.get(i).CHANGEPCT24HOUR);
                }

                load();
                update();

            } else {
                utils.showAlertMessage(getActivity(), msg);
            }
        }


    }

    public void load() {

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
                tv_eth_pct.setText(mArrayList.get(0).CHANGE24HOUR + " (" + mArrayList.get(0).CHANGEPCT24HOUR + "%)");
                tv_eth_pct.setTextColor(Color.parseColor("#008000"));
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

                ParsedResponse p = Service.apiGetCoin(getActivity(), Baseurl);
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
                utils.showAlertMessage(getActivity(), msg);
            }
        }
    }


    /* Initialize popup dialog view and ui controls in the popup dialog. */
    private void loginPopupViewControls() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.popup_login_dialog);

        FrameLayout FrameLayout2 = dialog.findViewById(R.id.FrameLayout2);
        google_login = (Button) dialog.findViewById(R.id.google_login);
        google_logout = (Button) dialog.findViewById(R.id.google_logout);

        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (utils.isNetworkAvailable(getActivity())) {
                    dialog.dismiss();
                    signIn();
                } else {
                    utils.showAlertMessage(getActivity(), getString(R.string.err_no_internet));
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
                ParsedResponse p = Service.apiLogin(getActivity(), name, email, picture, google_id, "0", device_id);
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
                utils.showAlertMessage(getActivity(), msg);
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
            user_id = preferences.getString(General.PREFS_User_id, "");
            try {
                ParsedResponse p = Service.apiAlert(getActivity(), user_id, coin_symbol, "0", "0", device_id);
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
                utils.showAlertMessage(getActivity(), msg);
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
            user_id = preferences.getString(General.PREFS_User_id, "");
            try {
                ParsedResponse p = Service.apiDeleteAlert(getActivity(), user_id, coin_symbol, "1", "0", device_id);
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
                utils.showAlertMessage(getActivity(), msg);
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

}