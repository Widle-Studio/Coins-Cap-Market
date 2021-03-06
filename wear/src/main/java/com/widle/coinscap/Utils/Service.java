package com.widle.coinscap.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import com.widle.coinscap.R;


import com.widle.coinscap.Model.Event;
import com.widle.coinscap.Model.Fav_Coins;
import com.widle.coinscap.Model.ICO;
import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.Model.coin;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by imperial-android on 19/12/17.
 */
public class Service {
    private static String TAG = "SOAP";
    private static final String CHARSET = "UTF-8";
    public static final String BASE_URL_API = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC,ETH,BCH,XRP,LTC,ADA,IOT,DASH,XEM,XMR,EOS,BTG,QTUM,XLM,NEO,ETC,ZEC,STEEM,XZC,STORJ,AION&tsyms=USD&extraParams=your_app_name";

    public static final String BASE_URL = "https://widle.studio/coinscapmarket/API/";

    public static String getReponseGet(String Baseurl, String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(Baseurl + url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(2000);
            c.setReadTimeout(2000);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
        } catch (IOException ex) {
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                }
            }
        }
        return null;
    }


    public static String getSoapResponsePost(String requestURL, RequestBody body) throws IOException {
        OkHttpClient client = new OkHttpClient();


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        client = builder.build();


//         body = RequestBody.create(JSON, json);
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(BASE_URL + requestURL)
                .post(body)
                .build();


        Response response = client.newCall(request).execute();

        Log.d(TAG, BASE_URL + requestURL);
        return response.body().string();
    }



    public static String getSoapResponsePostt(String requestURL) throws IOException {
        OkHttpClient client = new OkHttpClient();


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        client = builder.build();


//         body = RequestBody.create(JSON, json);
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(requestURL)
                .get()
                .build();


        Response response = client.newCall(request).execute();

        Log.d(TAG, BASE_URL + requestURL);
        return response.body().string();
    }




    /*---------------------------------------------------Get_Category_Api-------------------------------------------------------------*/


    public static ParsedResponse apiGetCoin(Context mContext, String Baseurl) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getReponseGet(Baseurl, "");

            Gson gson = new Gson();
            ArrayList<coin> arrList = new ArrayList<>();

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                JSONObject data = mainobj.getJSONObject("DISPLAY");
                JSONObject data1 = data.getJSONObject("BTC");

                JSONObject data2 = data1.getJSONObject("USD");
                coin model = new coin();
                model = gson.fromJson(data2.toString(), coin.class);
                arrList.add(model);

                JSONObject d1 = data.getJSONObject("ETH");
                JSONObject d2 = d1.getJSONObject("USD");
                coin m1 = new coin();
                m1 = gson.fromJson(d2.toString(), coin.class);
                arrList.add(m1);

                JSONObject d11 = data.getJSONObject("BCH");
                JSONObject d12 = d11.getJSONObject("USD");
                coin m11 = new coin();
                m11 = gson.fromJson(d12.toString(), coin.class);
                arrList.add(m11);

                JSONObject d21 = data.getJSONObject("XRP");
                JSONObject d22 = d21.getJSONObject("USD");
                coin m21 = new coin();
                m21 = gson.fromJson(d22.toString(), coin.class);
                arrList.add(m21);

                JSONObject d31 = data.getJSONObject("LTC");
                JSONObject d32 = d31.getJSONObject("USD");
                coin m31 = new coin();
                m31 = gson.fromJson(d32.toString(), coin.class);
                arrList.add(m31);

                JSONObject d41 = data.getJSONObject("ADA");
                JSONObject d42 = d41.getJSONObject("USD");
                coin m41 = new coin();
                m41 = gson.fromJson(d42.toString(), coin.class);
                arrList.add(m41);

                JSONObject d51 = data.getJSONObject("IOT");
                JSONObject d52 = d51.getJSONObject("USD");
                coin m51 = new coin();
                m51 = gson.fromJson(d52.toString(), coin.class);
                arrList.add(m51);

                JSONObject d61 = data.getJSONObject("DASH");
                JSONObject d62 = d61.getJSONObject("USD");
                coin m61 = new coin();
                m61 = gson.fromJson(d62.toString(), coin.class);
                arrList.add(m61);

                JSONObject d71 = data.getJSONObject("XEM");
                JSONObject d72 = d71.getJSONObject("USD");
                coin m71 = new coin();
                m71 = gson.fromJson(d72.toString(), coin.class);
                arrList.add(m71);

                JSONObject d81 = data.getJSONObject("XMR");
                JSONObject d82 = d81.getJSONObject("USD");
                coin m81 = new coin();
                m81 = gson.fromJson(d82.toString(), coin.class);
                arrList.add(m81);

                JSONObject d91 = data.getJSONObject("EOS");
                JSONObject d92 = d91.getJSONObject("USD");
                coin m91 = new coin();
                m91 = gson.fromJson(d92.toString(), coin.class);
                arrList.add(m91);

                JSONObject d111 = data.getJSONObject("BTG");
                JSONObject d112 = d111.getJSONObject("USD");
                coin m111 = new coin();
                m111 = gson.fromJson(d112.toString(), coin.class);
                arrList.add(m111);

                JSONObject d211 = data.getJSONObject("QTUM");
                JSONObject d212 = d211.getJSONObject("USD");
                coin m211 = new coin();
                m211 = gson.fromJson(d212.toString(), coin.class);
                arrList.add(m211);

                JSONObject d311 = data.getJSONObject("XLM");
                JSONObject d312 = d311.getJSONObject("USD");
                coin m311 = new coin();
                m311 = gson.fromJson(d312.toString(), coin.class);
                arrList.add(m311);

                JSONObject d411 = data.getJSONObject("NEO");
                JSONObject d412 = d411.getJSONObject("USD");
                coin m411 = new coin();
                m411 = gson.fromJson(d412.toString(), coin.class);
                arrList.add(m411);

                JSONObject d511 = data.getJSONObject("ETC");
                JSONObject d512 = d511.getJSONObject("USD");
                coin m511 = new coin();
                m511 = gson.fromJson(d512.toString(), coin.class);
                arrList.add(m511);

                JSONObject d611 = data.getJSONObject("ZEC");
                JSONObject d612 = d611.getJSONObject("USD");
                coin m611 = new coin();
                m611 = gson.fromJson(d612.toString(), coin.class);
                arrList.add(m611);

                JSONObject d711 = data.getJSONObject("STEEM");
                JSONObject d712 = d711.getJSONObject("USD");
                coin m711 = new coin();
                m711 = gson.fromJson(d712.toString(), coin.class);
                arrList.add(m711);

                JSONObject d811 = data.getJSONObject("XZC");
                JSONObject d812 = d811.getJSONObject("USD");
                coin m811 = new coin();
                m811 = gson.fromJson(d812.toString(), coin.class);
                arrList.add(m811);

                JSONObject d911 = data.getJSONObject("STORJ");
                JSONObject d912 = d911.getJSONObject("USD");
                coin m911 = new coin();
                m911 = gson.fromJson(d912.toString(), coin.class);
                arrList.add(m911);

                JSONObject d1011 = data.getJSONObject("AION");
                JSONObject d1012 = d1011.getJSONObject("USD");
                coin m1011 = new coin();
                m1011 = gson.fromJson(d1012.toString(), coin.class);
                arrList.add(m1011);

                JSONObject d1111 = data.getJSONObject("HT");
                JSONObject d1112 = d1111.getJSONObject("USD");
                coin m1111 = new coin();
                m1111 = gson.fromJson(d1112.toString(), coin.class);
                arrList.add(m1111);

                JSONObject d1211 = data.getJSONObject("TRX");
                JSONObject d1212 = d1211.getJSONObject("USD");
                coin m1211 = new coin();
                m1211 = gson.fromJson(d1212.toString(), coin.class);
                arrList.add(m1211);

                JSONObject d1311 = data.getJSONObject("XRB");
                JSONObject d1312 = d1311.getJSONObject("USD");
                coin m1311 = new coin();
                m1311 = gson.fromJson(d1312.toString(), coin.class);
                arrList.add(m1311);

                JSONObject d1411 = data.getJSONObject("OMG");
                JSONObject d1412 = d1411.getJSONObject("USD");
                coin m1411 = new coin();
                m1411 = gson.fromJson(d1412.toString(), coin.class);
                arrList.add(m1411);

                JSONObject d1511 = data.getJSONObject("ELA");
                JSONObject d1512 = d1511.getJSONObject("USD");
                coin m1511 = new coin();
                m1511 = gson.fromJson(d1512.toString(), coin.class);
                arrList.add(m1511);

                JSONObject d1611 = data.getJSONObject("BNB");
                JSONObject d1612 = d1611.getJSONObject("USD");
                coin m1611 = new coin();
                m1611 = gson.fromJson(d1612.toString(), coin.class);
                arrList.add(m1611);

                JSONObject d1711 = data.getJSONObject("VEN");
                JSONObject d1712 = d1711.getJSONObject("USD");
                coin m1711 = new coin();
                m1711 = gson.fromJson(d1712.toString(), coin.class);
                arrList.add(m1711);

                JSONObject d1811 = data.getJSONObject("ZCL");
                JSONObject d1812 = d1811.getJSONObject("USD");
                coin m1811 = new coin();
                m1811 = gson.fromJson(d1812.toString(), coin.class);
                arrList.add(m1811);

                JSONObject d1911 = data.getJSONObject("DGD");
                JSONObject d1912 = d1911.getJSONObject("USD");
                coin m1911 = new coin();
                m1911 = gson.fromJson(d1912.toString(), coin.class);
                arrList.add(m1911);

                JSONObject d2011 = data.getJSONObject("OCN");
                JSONObject d2012 = d2011.getJSONObject("USD");
                coin m2011 = new coin();
                m2011 = gson.fromJson(d2012.toString(), coin.class);
                arrList.add(m2011);

                JSONObject d2111 = data.getJSONObject("BCPT");
                JSONObject d2112 = d2111.getJSONObject("USD");
                coin m2111 = new coin();
                m2111 = gson.fromJson(d2112.toString(), coin.class);
                arrList.add(m2111);

                JSONObject d2211 = data.getJSONObject("LUN");
                JSONObject d2212 = d2211.getJSONObject("USD");
                coin m2211 = new coin();
                m2211 = gson.fromJson(d2212.toString(), coin.class);
                arrList.add(m2211);

                JSONObject d2311 = data.getJSONObject("IOST");
                JSONObject d2312 = d2311.getJSONObject("USD");
                coin m2311 = new coin();
                m2311 = gson.fromJson(d2312.toString(), coin.class);
                arrList.add(m2311);

                JSONObject d2411 = data.getJSONObject("HSR");
                JSONObject d2412 = d2411.getJSONObject("USD");
                coin m2411 = new coin();
                m2411 = gson.fromJson(d2412.toString(), coin.class);
                arrList.add(m2411);

                JSONObject d2511 = data.getJSONObject("ICX");
                JSONObject d2512 = d2511.getJSONObject("USD");
                coin m2511 = new coin();
                m2511 = gson.fromJson(d2512.toString(), coin.class);
                arrList.add(m2511);

                JSONObject d2611 = data.getJSONObject("LSK");
                JSONObject d2612 = d2611.getJSONObject("USD");
                coin m2611 = new coin();
                m2611 = gson.fromJson(d2612.toString(), coin.class);
                arrList.add(m2611);

                JSONObject d2711 = data.getJSONObject("NEBL");
                JSONObject d2712 = d2711.getJSONObject("USD");
                coin m2711 = new coin();
                m2711 = gson.fromJson(d2712.toString(), coin.class);
                arrList.add(m2711);

                JSONObject d2811 = data.getJSONObject("WAVES");
                JSONObject d2812 = d2811.getJSONObject("USD");
                coin m2811 = new coin();
                m2811 = gson.fromJson(d2812.toString(), coin.class);
                arrList.add(m2811);

                JSONObject d2911 = data.getJSONObject("BLZ");
                JSONObject d2912 = d2911.getJSONObject("USD");
                coin m2911 = new coin();
                m2911 = gson.fromJson(d2912.toString(), coin.class);
                arrList.add(m2911);

                JSONObject d3011 = data.getJSONObject("INK");
                JSONObject d3012 = d3011.getJSONObject("USD");
                coin m3011 = new coin();
                m3011 = gson.fromJson(d3012.toString(), coin.class);
                arrList.add(m3011);

                JSONObject d3111 = data.getJSONObject("ADX");
                JSONObject d3112 = d3111.getJSONObject("USD");
                coin m3111 = new coin();
                m3111 = gson.fromJson(d3112.toString(), coin.class);
                arrList.add(m3111);

                JSONObject d3211 = data.getJSONObject("XVG");
                JSONObject d3212 = d3211.getJSONObject("USD");
                coin m3211 = new coin();
                m3211 = gson.fromJson(d3212.toString(), coin.class);
                arrList.add(m3211);

                JSONObject d3411 = data.getJSONObject("MTL");
                JSONObject d3412 = d3411.getJSONObject("USD");
                coin m3411 = new coin();
                m3411 = gson.fromJson(d3412.toString(), coin.class);
                arrList.add(m3411);

                JSONObject d3511 = data.getJSONObject("SRN");
                JSONObject d3512 = d3511.getJSONObject("USD");
                coin m3511 = new coin();
                m3511 = gson.fromJson(d3512.toString(), coin.class);
                arrList.add(m3511);

                JSONObject d3611 = data.getJSONObject("ZRX");
                JSONObject d3612 = d3611.getJSONObject("USD");
                coin m3611 = new coin();
                m3611 = gson.fromJson(d3612.toString(), coin.class);
                arrList.add(m3611);

                JSONObject d3711 = data.getJSONObject("RLC");
                JSONObject d3712 = d3711.getJSONObject("USD");
                coin m3711 = new coin();
                m3711 = gson.fromJson(d3712.toString(), coin.class);
                arrList.add(m3711);

                JSONObject d3811 = data.getJSONObject("THETA");
                JSONObject d3812 = d3811.getJSONObject("USD");
                coin m3811 = new coin();
                m3811 = gson.fromJson(d3812.toString(), coin.class);
                arrList.add(m3811);

                JSONObject d3911 = data.getJSONObject("BCD");
                JSONObject d3912 = d3911.getJSONObject("USD");
                coin m3911 = new coin();
                m3911 = gson.fromJson(d3912.toString(), coin.class);
                arrList.add(m3911);

                JSONObject d4011 = data.getJSONObject("OAX");
                JSONObject d4012 = d4011.getJSONObject("USD");
                coin m4011 = new coin();
                m4011 = gson.fromJson(d4012.toString(), coin.class);
                arrList.add(m4011);

                JSONObject d4111 = data.getJSONObject("ELF");
                JSONObject d4112 = d4111.getJSONObject("USD");
                coin m4111 = new coin();
                m4111 = gson.fromJson(d4112.toString(), coin.class);
                arrList.add(m4111);

                JSONObject d4211 = data.getJSONObject("INS");
                JSONObject d4212 = d4211.getJSONObject("USD");
                coin m4211 = new coin();
                m4211 = gson.fromJson(d4212.toString(), coin.class);
                arrList.add(m4211);

                JSONObject d4311 = data.getJSONObject("ZIL");
                JSONObject d4312 = d4311.getJSONObject("USD");
                coin m4311 = new coin();
                m4311 = gson.fromJson(d4312.toString(), coin.class);
                arrList.add(m4311);

                JSONObject d4411 = data.getJSONObject("AE");
                JSONObject d4412 = d4411.getJSONObject("USD");
                coin m4411 = new coin();
                m4411 = gson.fromJson(d4412.toString(), coin.class);
                arrList.add(m4411);

                JSONObject d4511 = data.getJSONObject("PRO");
                JSONObject d4512 = d4511.getJSONObject("USD");
                coin m4511 = new coin();
                m4511 = gson.fromJson(d4512.toString(), coin.class);
                arrList.add(m4511);

                JSONObject d4611 = data.getJSONObject("DOGE");
                JSONObject d4612 = d4611.getJSONObject("USD");
                coin m4611 = new coin();
                m4611 = gson.fromJson(d4612.toString(), coin.class);
                arrList.add(m4611);

                JSONObject d4711 = data.getJSONObject("ITC");
                JSONObject d4712 = d4711.getJSONObject("USD");
                coin m4711 = new coin();
                m4711 = gson.fromJson(d4712.toString(), coin.class);
                arrList.add(m4711);

                JSONObject d4811 = data.getJSONObject("RDD");
                JSONObject d4812 = d4811.getJSONObject("USD");
                coin m4811 = new coin();
                m4811 = gson.fromJson(d4812.toString(), coin.class);
                arrList.add(m4811);

                JSONObject d4911 = data.getJSONObject("SWFTC");
                JSONObject d4912 = d4911.getJSONObject("USD");
                coin m4911 = new coin();
                m4911 = gson.fromJson(d4912.toString(), coin.class);
                arrList.add(m4911);

                JSONObject d5011 = data.getJSONObject("MCO");
                JSONObject d5012 = d5011.getJSONObject("USD");
                coin m5011 = new coin();
                m5011 = gson.fromJson(d5012.toString(), coin.class);
                arrList.add(m5011);

                JSONObject d5111 = data.getJSONObject("ARN");
                JSONObject d5112 = d5111.getJSONObject("USD");
                coin m5111 = new coin();
                m5111 = gson.fromJson(d5112.toString(), coin.class);
                arrList.add(m5111);

                JSONObject d5211 = data.getJSONObject("MTN*");
                JSONObject d5212 = d5211.getJSONObject("USD");
                coin m5211 = new coin();
                m5211 = gson.fromJson(d5212.toString(), coin.class);
                arrList.add(m5211);

                JSONObject d5311 = data.getJSONObject("BRD");
                JSONObject d5312 = d5311.getJSONObject("USD");
                coin m5311 = new coin();
                m5311 = gson.fromJson(d5312.toString(), coin.class);
                arrList.add(m5311);

                JSONObject d5411 = data.getJSONObject("SAN");
                JSONObject d5412 = d5411.getJSONObject("USD");
                coin m5411 = new coin();
                m5411 = gson.fromJson(d5412.toString(), coin.class);
                arrList.add(m5411);

                JSONObject d5511 = data.getJSONObject("NAS");
                JSONObject d5512 = d5511.getJSONObject("USD");
                coin m5511 = new coin();
                m5511 = gson.fromJson(d5512.toString(), coin.class);
                arrList.add(m5511);

                JSONObject d5611 = data.getJSONObject("GVT");
                JSONObject d5612 = d5611.getJSONObject("USD");
                coin m5611 = new coin();
                m5611 = gson.fromJson(d5612.toString(), coin.class);
                arrList.add(m5611);

                JSONObject d5711 = data.getJSONObject("QUN");
                JSONObject d5712 = d5711.getJSONObject("USD");
                coin m5711 = new coin();
                m5711 = gson.fromJson(d5712.toString(), coin.class);
                arrList.add(m5711);

                JSONObject d5811 = data.getJSONObject("WTC");
                JSONObject d5812 = d5811.getJSONObject("USD");
                coin m5811 = new coin();
                m5811 = gson.fromJson(d5812.toString(), coin.class);
                arrList.add(m5811);

                JSONObject d5911 = data.getJSONObject("FCT");
                JSONObject d5912 = d5911.getJSONObject("USD");
                coin m5911 = new coin();
                m5911 = gson.fromJson(d5912.toString(), coin.class);
                arrList.add(m5911);

                JSONObject d6011 = data.getJSONObject("CDT");
                JSONObject d6012 = d6011.getJSONObject("USD");
                coin m6011 = new coin();
                m6011 = gson.fromJson(d6012.toString(), coin.class);
                arrList.add(m6011);

                JSONObject d6111 = data.getJSONObject("RCN");
                JSONObject d6112 = d6111.getJSONObject("USD");
                coin m6111 = new coin();
                m6111 = gson.fromJson(d6112.toString(), coin.class);
                arrList.add(m6111);



                p.error = false;
                p.o = arrList;
//
            } else {
                p.error = true;
//                p.o = mContext.getString(R.string.err_lodingdata);
            }
        } else {
            p.error = true;
//            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }




    /*---------------------------------------------------Get_City_Api-------------------------------------------------------------*/


    public static ParsedResponse apiUpdate(Context mContext, String device, String device_id) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            RequestBody formBody = new FormBody.Builder()
                    .add("action", "userdata")
                    .add("device", device)
                    .add("device_id", device_id)

                    .build();

            String res = Service.getSoapResponsePost("api.php",
                    formBody);

            Log.d("response", "" + res);

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                if (mainobj.getInt("status") == 200) {

                    p.error = false;
                    p.o = mainobj.getString("message");
                } else {
                    p.error = true;
                    p.o = mainobj.getString("message");
                }

            } else {
                p.error = true;
                p.o = mContext.getString(R.string.err_lodingdata);
            }
//
        } else {
            p.error = true;
            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }




    public static ParsedResponse apiFavouriteGetCoin(Context mContext, String Baseurl) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getReponseGet(Baseurl, "");

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                JSONObject songs= mainobj.getJSONObject("DISPLAY");
                Iterator x = songs.keys();
                JSONArray jsonArray = new JSONArray();

                while (x.hasNext()){
                    String key = (String) x.next();
                    jsonArray.put(songs.get(key));
                }


                Gson gson = new Gson();
                ArrayList<Fav_Coins> arrList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    Fav_Coins model = new Fav_Coins();
                    model = gson.fromJson(c.toString(), Fav_Coins.class);
                    arrList.add(model);

                    p.error = false;
                    p.o = arrList;
                }

            }

        } else {
            p.error = true;
//            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }



    //---------------------------------------------------Get_Podcast_Channel_Api-------------------------------------------------------------

    public static ParsedResponse apiGetPodcastChannel(Context mContext, String Baseurl) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getReponseGet(Baseurl, "");
            Log.d("response", "" + res);

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                JSONArray data = mainobj.getJSONArray("channels");
                Gson gson = new Gson();
                ArrayList<PodcastChanel> arrList = new ArrayList<>();
                for (int i = 0; i < data.length(); i++) {
                    JSONObject c = data.getJSONObject(i);
                    PodcastChanel model = new PodcastChanel();
                    model = gson.fromJson(c.toString(), PodcastChanel.class);
                    arrList.add(model);

                    p.error = false;
                    p.o = arrList;
                }
            } else {
                p.error = true;
//                p.o = mContext.getString(R.string.err_lodingdata);
            }
//
        } else {
            p.error = true;
//            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }



    //---------------------------------------------------Get_Podcast_Part_Api-------------------------------------------------------------

    public static ParsedResponse apiGetPodcastPart(Context mContext, String Baseurl) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getReponseGet(Baseurl, "");
            Log.d("response", "" + res);

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                JSONObject data = mainobj.getJSONObject("channel");
                JSONArray data1 = data.getJSONArray("episodes");
                Gson gson = new Gson();
                ArrayList<PodcastChanel> arrList = new ArrayList<>();
                for (int i = 0; i < data1.length(); i++) {
                    JSONObject c = data1.getJSONObject(i);
                    PodcastChanel model = new PodcastChanel();
                    model = gson.fromJson(c.toString(), PodcastChanel.class);
                    arrList.add(model);

                    p.error = false;
                    p.o = arrList;
                }
            } else {
                p.error = true;
//                p.o = mContext.getString(R.string.err_lodingdata);
            }
//
        } else {
            p.error = true;
//            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }


    /*---------------------------------------------------API_Event_API-------------------------------------------------------------*/

    public static ParsedResponse apiGetToken(Context mContext, String url) throws IOException, JSONException {
        SharedPreferences preferences;
        preferences = mContext.getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getSoapResponsePostt(url);

            Log.d("response", "" + res);
            String v1 = "", v2 = "";
            if (!TextUtils.isEmpty(res)) {
                JSONObject c = new JSONObject(res);
                SharedPreferences.Editor e = preferences.edit();
                e.putString(General.PREFS_Token, c.getString("access_token"));
                e.apply();
                p.error = false;

            } else {
                p.error = true;
            }
//
        } else {
            p.error = true;
        }
        return p;
    }

    /*---------------------------------------------------API_Event_API-------------------------------------------------------------*/

    public static ParsedResponse apiGetEvents(Context mContext, String url) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getSoapResponsePostt(url);

            Log.d("response", "" + res);
            String v1 = "", v2 = "";
            if (!TextUtils.isEmpty(res)) {
                JSONArray data = new JSONArray(res);
                Gson gson = new Gson();
                ArrayList<Event> arrList = new ArrayList<>();
                for (int i = 0; i < data.length(); i++) {
                    JSONObject c = data.getJSONObject(i);

                    JSONArray a1 = c.getJSONArray("coins");
                    for (int j = 0; j < a1.length(); j++){
                        JSONObject c1 = a1.getJSONObject(j);
                        v1 = c1.getString("name");
                        v2 = c1.getString("symbol");
                    }
                    Event model = new Event();

                    model = gson.fromJson(c.toString(), Event.class);
                    model.symbol = v2;
                    model.name = v1;
                    arrList.add(model);

                    p.error = false;
                    p.o = arrList;
                }
            } else {
                p.error = true;
//                p.o = mContext.getString(R.string.err_lodingdata);
            }
//
        } else {
            p.error = true;
//            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }


    /*---------------------------------------------------API_EXCHANGE-------------------------------------------------------------*/

    public static ParsedResponse apiGetTopList(Context mContext, String url) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getSoapResponsePostt(url);

            Log.d("response", "" + res);
            if (!TextUtils.isEmpty(res)) {
                JSONObject data = new JSONObject(res);
                JSONArray array = data.getJSONArray("Data");
                Gson gson = new Gson();
                ArrayList<coin> arrList = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject c = array.getJSONObject(i);
                    coin model = new coin();
                    model = gson.fromJson(c.toString(), coin.class);
                    arrList.add(model);
                    p.error = false;
                    p.o = arrList;
                }
            } else {
                p.error = true;
//                p.o = mContext.getString(R.string.err_lodingdata);
            }
//
        } else {
            p.error = true;
//            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }






    /*---------------------------------------------------API_ICO_LIST-------------------------------------------------------------*/

    public static ParsedResponse apiIcoList(Context mContext, String url, String type) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getSoapResponsePostt(url);

            Log.d("response", "" + res);


            if (type.equals("live")) {

                if (!TextUtils.isEmpty(res)) {
                    JSONObject data = new JSONObject(res);
                    JSONObject data1 = data.getJSONObject("ico");
                    JSONArray array = data1.getJSONArray("live");
                    Gson gson = new Gson();
                    ArrayList<ICO> arrList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);
                        ICO model = new ICO();
                        model = gson.fromJson(c.toString(), ICO.class);
                        arrList.add(model);
                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mContext.getString(R.string.err_lodingdata);
                }


            } else if (type.equals("upcoming")) {

                if (!TextUtils.isEmpty(res)) {
                    JSONObject data = new JSONObject(res);
                    JSONObject data1 = data.getJSONObject("ico");
                    JSONArray array = data1.getJSONArray("upcoming");
                    Gson gson = new Gson();
                    ArrayList<ICO> arrList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);
                        ICO model = new ICO();
                        model = gson.fromJson(c.toString(), ICO.class);
                        arrList.add(model);
                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mContext.getString(R.string.err_lodingdata);
                }


            } else if (type.equals("finished")) {

                if (!TextUtils.isEmpty(res)) {
                    JSONObject data = new JSONObject(res);
                    JSONObject data1 = data.getJSONObject("ico");
                    JSONArray array = data1.getJSONArray("finished");
                    Gson gson = new Gson();
                    ArrayList<ICO> arrList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);
                        ICO model = new ICO();
                        model = gson.fromJson(c.toString(), ICO.class);
                        arrList.add(model);
                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mContext.getString(R.string.err_lodingdata);
                }
            }

        } else {
            p.error = true;
            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }




    /*---------------------------------------------------API_EXCHANGE-------------------------------------------------------------*/

    public static ParsedResponse apiGetExchange(Context mContext, String url) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getSoapResponsePostt(url);

            Log.d("response", "" + res);
            if (!TextUtils.isEmpty(res)) {
                JSONObject data = new JSONObject(res);
                JSONObject data1 = data.getJSONObject("Data");
                JSONArray array = data1.getJSONArray("Exchanges");
                Gson gson = new Gson();
                ArrayList<coin> arrList = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject c = array.getJSONObject(i);
                    coin model = new coin();
                    model = gson.fromJson(c.toString(), coin.class);
                    arrList.add(model);
                    p.error = false;
                    p.o = arrList;
                }
            } else {
                p.error = true;
//                p.o = mContext.getString(R.string.err_lodingdata);
            }
//
        } else {
            p.error = true;
//            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }


}
