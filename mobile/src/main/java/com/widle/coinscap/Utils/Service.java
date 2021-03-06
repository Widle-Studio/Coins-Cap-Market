package com.widle.coinscap.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Utils.Model.Advertisement;
import com.widle.coinscap.Utils.Model.Currency;
import com.widle.coinscap.Utils.Model.Graph;
import com.widle.coinscap.Utils.Model.News;
import com.widle.coinscap.Utils.Model.coin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Fragment.Crypto_Fragment;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.Advertisement;
import com.widle.coinscap.Utils.Model.Coins_List;
import com.widle.coinscap.Utils.Model.Currency;
import com.widle.coinscap.Utils.Model.Event;
import com.widle.coinscap.Utils.Model.Fav_Coins;
import com.widle.coinscap.Utils.Model.Graph;
import com.widle.coinscap.Utils.Model.ICO;
import com.widle.coinscap.Utils.Model.NewCoins;
import com.widle.coinscap.Utils.Model.News;
import com.widle.coinscap.Utils.Model.PodcastChanel;
import com.widle.coinscap.Utils.Model.Update_Coin;
import com.widle.coinscap.Utils.Model.coin;
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
    //    public static final String BASE_URL_API = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=42,300,365,404,611,808,888,1337,2015,BTC,ETH,LTC,XMR,NXT,ETC,DOG,ZEC,STEEM,XZC,STORJ,AION&tsyms=USD&extraParams=your_app_name";
    public static final String BASE_URL_API = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC,ETH,BCH,XRP,LTC,ADA,IOT,DASH,XEM,XMR,EOS,BTG,QTUM,XLM,NEO,ETC,ZEC,STEEM,XZC,STORJ,AION&tsyms=USD&extraParams=your_app_name";

//    public static final String BASE_URL = "http://coinscapmarket.com/API/";
    public static final String BASE_URL = "https://widle.studio/coinscapmarket/API/";


    private static DatabaseHandler db;

    public static final String BASE_URL_API_COINS = "https://min-api.cryptocompare.com/data/news/?lang=EN";

    public static String getReponseGetNews(String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(BASE_URL_API_COINS + url);
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
//                p.o = mContext.getString(R.string.err_lodingdata);
            }
//
        } else {
            p.error = true;
//            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }





    /*---------------------------------------------------Get_Advertise_Api-------------------------------------------------------------*/

    public static ParsedResponse apiGetAdvertise(Context mContext) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            RequestBody formBody = new FormBody.Builder()
                    .add("action", "ads")
                    .build();

            String res = Service.getSoapResponsePost("api.php",
                    formBody);

            Log.d("response", "" + res);

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                if (mainobj.getInt("status") == 200) {
                    JSONArray data = mainobj.getJSONArray("data");
                    Gson gson = new Gson();
                    ArrayList<Advertisement> arrList = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);
                        Advertisement model = new Advertisement();
                        model = gson.fromJson(c.toString(), Advertisement.class);
                        arrList.add(model);

                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mainobj.getString("message");
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




    /*---------------------------------------------------API_NEWS_API-------------------------------------------------------------*/

    public static ParsedResponse apiGetNews(Context mContext) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getReponseGetNews("");

            Log.d("response", "" + res);

            if (!TextUtils.isEmpty(res)) {
                JSONArray data = new JSONArray(res);
                Gson gson = new Gson();
                ArrayList<News> arrList = new ArrayList<>();
                for (int i = 0; i < data.length(); i++) {
                    JSONObject c = data.getJSONObject(i);
                    News model = new News();
                    model = gson.fromJson(c.toString(), News.class);
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




    /*---------------------------------------------------API_NEWS_API-------------------------------------------------------------*/

    public static ParsedResponse apiGetRedditNews(Context mContext, String url) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getSoapResponsePostt(url);

            Log.d("response", "" + res);
            if (!TextUtils.isEmpty(res)) {
                JSONObject data = new JSONObject(res);
                JSONObject data1 = data.getJSONObject("data");
                JSONArray array = data1.getJSONArray("children");
                Gson gson = new Gson();
                ArrayList<News> arrList = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject c = array.getJSONObject(i);
                    JSONObject c1 = c.getJSONObject("data");
//                    JSONObject c2 = c1.getJSONObject("preview");
//                    JSONArray array1 = c2.getJSONArray("images");
                    News model = new News();

//                    for (int j = 0; j < array1.length(); j++) {
//                        JSONObject c3 = array1.getJSONObject(j);
//                        JSONObject c4 = c3.getJSONObject("source");
//                        model = gson.fromJson(c4.toString(), News.class);
//                    }
                    model = gson.fromJson(c1.toString(), News.class);
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






    /*---------------------------------------------------API_CURRENCY_API-------------------------------------------------------------*/


    public static ParsedResponse apiGetCurrency(Context mContext) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            RequestBody formBody = new FormBody.Builder()
                    .add("action", "currency-list")
                    .build();

            String res = Service.getSoapResponsePost("api.php",
                    formBody);

            Log.d("response", "" + res);

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                if (mainobj.getInt("status") == 200) {
                    JSONArray data = mainobj.getJSONArray("data");
                    Gson gson = new Gson();
                    ArrayList<Currency> arrList = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);
                        Currency model = new Currency();
                        model = gson.fromJson(c.toString(), Currency.class);
                        arrList.add(model);

                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mainobj.getString("message");
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

    /*---------------------------------------------------GET_GRAPH_VALUE_API-------------------------------------------------------------*/

    public static ParsedResponse apiGetGraphValue(Context mContext, String Baseurl) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

//            String Baseurl = "https://min-api.cryptocompare.com/data/histohour?fsym=" +coins+"&tsym="+currency+"&limit=24&aggregate=1&e=CCCAGG";
            String res = Service.getReponseGet(Baseurl, "");

            Log.d("response", "" + res);

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                JSONArray data = mainobj.getJSONArray("Data");
                Gson gson = new Gson();
                ArrayList<Graph> arrList = new ArrayList<>();
                for (int i = 0; i < data.length(); i++) {
                    JSONObject c = data.getJSONObject(i);
                    Graph model = new Graph();
                    model = gson.fromJson(c.toString(), Graph.class);
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


    public static ParsedResponse apiFavouriteGetCoin(Context mContext, String Baseurl) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getReponseGet(Baseurl, "");

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                JSONObject songs = mainobj.getJSONObject("DISPLAY");
                Iterator x = songs.keys();
                JSONArray jsonArray = new JSONArray();

                while (x.hasNext()) {
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









    /*--------------------------------------------------GET_COINS_CURRENCY_WISE-----------------------------------------------------*/


    public static ParsedResponse apiGetCoin(Context mContext, String Baseurl) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

            String res = Service.getReponseGet(Baseurl, "");
//            Log.e("res", res);

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
            } else {
                p.error = true;
            }
        return p;
    }



    /*---------------------------------------------------Get_OnE_COin_Data_Api-------------------------------------------------------------*/


    public static ParsedResponse apiGetOneCoin(Context mContext, String Baseurl, String Symbol) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getReponseGet(Baseurl, "");

            Gson gson = new Gson();
            ArrayList<Update_Coin> arrList = new ArrayList<>();

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                JSONObject data = mainobj.getJSONObject("DISPLAY");
                JSONObject data1 = data.getJSONObject(Symbol);

                JSONObject data2 = data1.getJSONObject("USD");
                Update_Coin model = new Update_Coin();

                model = gson.fromJson(data2.toString(), Update_Coin.class);

                arrList.add(model);


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






    /*---------------------------------------------------Gmail_Login_Api-------------------------------------------------------------*/


    public static ParsedResponse apiLogin(Context mContext, String name, String email, String picture, String google_id, String device, String device_id) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();
        SharedPreferences preferences;
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        preferences = mContext.getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);

        if (utils.isNetworkAvailable(mContext)) {

            RequestBody formBody = new FormBody.Builder()
                    .add("action", "google-login")
                    .add("name", name)
                    .add("email", email)
                    .add("picture", picture)
                    .add("google_id", google_id)
                    .add("device", device)
                    .add("device_id", device_id)

                    .build();

            String res = Service.getSoapResponsePost("api.php",
                    formBody);

            Log.d("response", "" + res);

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                if (mainobj.getInt("status") == 200) {
                    JSONArray data = mainobj.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);

                        SharedPreferences.Editor e = preferences.edit();
                        e.putString(General.PREFS_User_id, c.getString("id"));
                        e.putString(General.PREFS_Picture, c.getString("profile_picture"));
                        e.putBoolean(General.PREFS_IsLogin, true);
                        e.apply();

                        p.error = false;
                        p.o = mainobj.getString("message");
                    }
                } else {
                    p.error = true;
                    p.o = mainobj.getString("message");
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



    /*---------------------------------------------------ALERT_API-------------------------------------------------------------*/


    public static ParsedResponse apiAlert(Context mContext, String user_id, String coin_symbol, String status, String device, String device_id) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();
        SharedPreferences preferences;
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        preferences = mContext.getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);

        if (utils.isNetworkAvailable(mContext)) {

            RequestBody formBody = new FormBody.Builder()
                    .add("action", "alert-coin")
                    .add("user_id", user_id)
                    .add("coin_symbol", coin_symbol)
                    .add("status", status)
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
//                p.o = mContext.getString(R.string.err_lodingdata);
            }
//
        } else {
            p.error = true;
//            p.o = mContext.getString(R.string.err_no_internet);
        }
        return p;
    }



    /*---------------------------------------------------DELETE_ALERT_API-------------------------------------------------------------*/


    public static ParsedResponse apiDeleteAlert(Context mContext, String user_id, String coin_symbol, String status, String device, String device_id) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();
        SharedPreferences preferences;
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        preferences = mContext.getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);

        if (utils.isNetworkAvailable(mContext)) {

            RequestBody formBody = new FormBody.Builder()
                    .add("action", "delete-alert")
                    .add("user_id", user_id)
                    .add("coin_symbol", coin_symbol)
                    .add("status", status)
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
//                p.o = arrList;

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



    /*---------------------------------------------------API_EVENT_CATEGORY -------------------------------------------------------------*/

    public static ParsedResponse apiGetEventsCategory(Context mContext, String url) throws IOException, JSONException {
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
                    Event model = new Event();
                    model = gson.fromJson(c.toString(), Event.class);
                    arrList.add(model);

                    p.error = false;
                    p.o = arrList;

                }
            } else {
                p.error = true;
            }
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
                    for (int j = 0; j < a1.length(); j++) {
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


    //---------------------------------------------------Get_Podcast_Part_Api-------------------------------------------------------------

    public static ParsedResponse apiGetCoins(Context mContext, String Baseurl) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getReponseGet(Baseurl, "");
            Log.d("response", "" + res);

            if (!TextUtils.isEmpty(res)) {
                JSONArray mainobj = new JSONArray(res);
                Gson gson = new Gson();
                ArrayList<NewCoins> arrList = new ArrayList<>();
                for (int i = 0; i < mainobj.length(); i++) {
                    JSONObject c = mainobj.getJSONObject(i);
                    NewCoins model = new NewCoins();
                    model = gson.fromJson(c.toString(), NewCoins.class);
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



    /*---------------------------------------------------API_EXCHANGE-------------------------------------------------------------*/

    public static ParsedResponse apiGetPairList(Context mContext, String url) throws IOException, JSONException {
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



    /*---------------------------------------------------API_EXCHANGE-------------------------------------------------------------*/

    public static ParsedResponse apiGetTopList(Context mContext, String url) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getSoapResponsePostt(url);

            Log.d("response", "" + res);
            if (!TextUtils.isEmpty(res)) {
                JSONObject data = new JSONObject(res);
                JSONArray array = data.getJSONArray("Data");
//                Gson gson = new Gson();
                ArrayList<coin> arrList = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject c = array.getJSONObject(i);
                    JSONObject c1 = c.getJSONObject("ConversionInfo");
                    coin model = new coin();
//                    model = gson.fromJson(c1.toString(), coin.class);

                    if ((c1.getString("Supply").contains("."))){
                        DecimalFormat df1 = new DecimalFormat("000.00");
                        double number = Double.parseDouble(c1.getString("Supply"));
                        String volume1 = df1.format(number);
                        model.Supply = volume1;
                    } else{
                        String value = c1.getString("Supply");
                        model.Supply = value;
                    }

                    if ((c1.getString("TotalVolume24H").contains("."))){
                        DecimalFormat df1 = new DecimalFormat("000.00");
                        double number = Double.parseDouble(c1.getString("TotalVolume24H"));
                        String volume1 = df1.format(number);
                        model.TotalVolume24H = volume1;
                    } else {
                        String value = c1.getString("TotalVolume24H");
                        model.TotalVolume24H = value;
                    }

                    String val = c1.getString("CurrencyFrom");
                    model.CurrencyFrom = val;

//                    model.Supply = Float.valueOf(c1.getString("Supply"));
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







    /*---------------------------------------------------API_CATEGORY-------------------------------------------------------------*/

    public static ParsedResponse apiCategoryAnalysis(Context mContext, String url, String type) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getSoapResponsePostt(url);

            Log.d("response", "" + res);


            if (type.equals("ANALYSIS")) {

                if (!TextUtils.isEmpty(res)) {
                    JSONObject data = new JSONObject(res);
                    JSONArray array = data.getJSONArray("analysis");
                    Gson gson = new Gson();
                    ArrayList<News> arrList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);
                        News model = new News();
//                    model = gson.fromJson(c.toString(), News.class);
                        model.title = c.getString("title");
                        model.originalImageUrl = c.getString("originalImageUrl");
                        model.url = c.getString("url");
                        arrList.add(model);
                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mContext.getString(R.string.err_lodingdata);
                }


            } else if (type.equals("BLOCKCHAIN")) {

                if (!TextUtils.isEmpty(res)) {
                    JSONObject data = new JSONObject(res);
                    JSONArray array = data.getJSONArray("blockchain");
                    Gson gson = new Gson();
                    ArrayList<News> arrList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);
                        News model = new News();
//                    model = gson.fromJson(c.toString(), News.class);
                        model.title = c.getString("title");
                        model.originalImageUrl = c.getString("originalImageUrl");
                        model.url = c.getString("url");
                        arrList.add(model);
                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mContext.getString(R.string.err_lodingdata);
                }

            } else if (type.equals("EXCHANGES")) {

                if (!TextUtils.isEmpty(res)) {
                    JSONObject data = new JSONObject(res);
                    JSONArray array = data.getJSONArray("exchanges");
                    Gson gson = new Gson();
                    ArrayList<News> arrList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);
                        News model = new News();
//                    model = gson.fromJson(c.toString(), News.class);
                        model.title = c.getString("title");
                        model.originalImageUrl = c.getString("originalImageUrl");
                        model.url = c.getString("url");
                        arrList.add(model);
                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mContext.getString(R.string.err_lodingdata);
                }

            } else if (type.equals("GOVERNMENT")) {

                if (!TextUtils.isEmpty(res)) {
                    JSONObject data = new JSONObject(res);
                    JSONArray array = data.getJSONArray("government");
                    Gson gson = new Gson();
                    ArrayList<News> arrList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);
                        News model = new News();
//                    model = gson.fromJson(c.toString(), News.class);
                        model.title = c.getString("title");
                        model.originalImageUrl = c.getString("originalImageUrl");
                        model.url = c.getString("url");
                        arrList.add(model);
                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mContext.getString(R.string.err_lodingdata);
                }

            } else if (type.equals("MINING")) {

                if (!TextUtils.isEmpty(res)) {
                    JSONObject data = new JSONObject(res);
                    JSONArray array = data.getJSONArray("mining");
                    Gson gson = new Gson();
                    ArrayList<News> arrList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);
                        News model = new News();
//                    model = gson.fromJson(c.toString(), News.class);
                        model.title = c.getString("title");
                        model.originalImageUrl = c.getString("originalImageUrl");
                        model.url = c.getString("url");
                        arrList.add(model);
                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mContext.getString(R.string.err_lodingdata);
                }

            } else if (type.equals("ICOS")) {

                if (!TextUtils.isEmpty(res)) {
                    JSONObject data = new JSONObject(res);
                    JSONArray array = data.getJSONArray("ico");
                    Gson gson = new Gson();
                    ArrayList<News> arrList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);
                        News model = new News();
//                    model = gson.fromJson(c.toString(), News.class);
                        model.title = c.getString("title");
                        model.originalImageUrl = c.getString("originalImageUrl");
                        model.url = c.getString("url");
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


    /*-----------------------------------------------------GET_ALERT_COINS-----------------------------------------------------*/



    public static ParsedResponse apiGetAlertCoin(Context mContext, String Baseurl, String coins, String currency) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();


        if (utils.isNetworkAvailable(mContext)) {

            String res = Service.getReponseGet(Baseurl, "");
//            Log.e("res", res);

            Gson gson = new Gson();
            ArrayList<coin> arrList = new ArrayList<>();
            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                JSONObject data = mainobj.getJSONObject("DISPLAY");

                if (coins.equals("BTC")){
                    if (currency.equals("USD")){
                        JSONObject data1 = data.getJSONObject("BTC");
                        JSONObject data2 = data1.getJSONObject("USD");
                        coin model = new coin();
                        model = gson.fromJson(data2.toString(), coin.class);
                        arrList.add(model);
                    } else if (currency.equals("JPY")){
                        JSONObject data1 = data.getJSONObject("BTC");
                        JSONObject data2 = data1.getJSONObject("JPY");
                        coin model = new coin();
                        model = gson.fromJson(data2.toString(), coin.class);
                        arrList.add(model);
                    } else if (currency.equals("EUR")){
                        JSONObject data1 = data.getJSONObject("BTC");
                        JSONObject data2 = data1.getJSONObject("EUR");
                        coin model = new coin();
                        model = gson.fromJson(data2.toString(), coin.class);
                        arrList.add(model);
                    }
                } else if (coins.equals(("ETH"))){
                    if (currency.equals("USD")){
                        JSONObject d1 = data.getJSONObject("ETH");
                        JSONObject d2 = d1.getJSONObject("USD");
                        coin m1 = new coin();
                        m1 = gson.fromJson(d2.toString(), coin.class);
                        arrList.add(m1);
                    } else if (currency.equals("JPY")){
                        JSONObject d1 = data.getJSONObject("ETH");
                        JSONObject d2 = d1.getJSONObject("JPY");
                        coin m1 = new coin();
                        m1 = gson.fromJson(d2.toString(), coin.class);
                        arrList.add(m1);
                    } else if (currency.equals("EUR")){
                        JSONObject d1 = data.getJSONObject("ETH");
                        JSONObject d2 = d1.getJSONObject("EUR");
                        coin m1 = new coin();
                        m1 = gson.fromJson(d2.toString(), coin.class);
                        arrList.add(m1);
                    }
                } else if (coins.equals(("BCH"))){
                    if (currency.equals("USD")){
                        JSONObject d11 = data.getJSONObject("BCH");
                        JSONObject d12 = d11.getJSONObject("USD");
                        coin m11 = new coin();
                        m11 = gson.fromJson(d12.toString(), coin.class);
                        arrList.add(m11);
                    } else if (currency.equals("JPY")){
                        JSONObject d11 = data.getJSONObject("BCH");
                        JSONObject d12 = d11.getJSONObject("JPY");
                        coin m11 = new coin();
                        m11 = gson.fromJson(d12.toString(), coin.class);
                        arrList.add(m11);
                    } else if (currency.equals("EUR")){
                        JSONObject d11 = data.getJSONObject("BCH");
                        JSONObject d12 = d11.getJSONObject("EUR");
                        coin m11 = new coin();
                        m11 = gson.fromJson(d12.toString(), coin.class);
                        arrList.add(m11);
                    }
                } else if (coins.equals(("XRP"))){
                    if (currency.equals("USD")){
                        JSONObject d21 = data.getJSONObject("XRP");
                        JSONObject d22 = d21.getJSONObject("USD");
                        coin m21 = new coin();
                        m21 = gson.fromJson(d22.toString(), coin.class);
                        arrList.add(m21);
                    } else if (currency.equals("JPY")){
                        JSONObject d21 = data.getJSONObject("XRP");
                        JSONObject d22 = d21.getJSONObject("JPY");
                        coin m21 = new coin();
                        m21 = gson.fromJson(d22.toString(), coin.class);
                        arrList.add(m21);
                    } else if (currency.equals("EUR")){
                        JSONObject d21 = data.getJSONObject("XRP");
                        JSONObject d22 = d21.getJSONObject("EUR");
                        coin m21 = new coin();
                        m21 = gson.fromJson(d22.toString(), coin.class);
                        arrList.add(m21);
                    }

                } else if (coins.equals(("LTC"))){
                    if (currency.equals("USD")){
                        JSONObject d31 = data.getJSONObject("LTC");
                        JSONObject d32 = d31.getJSONObject("USD");
                        coin m31 = new coin();
                        m31 = gson.fromJson(d32.toString(), coin.class);
                        arrList.add(m31);
                    } else if (currency.equals("JPY")){
                        JSONObject d31 = data.getJSONObject("LTC");
                        JSONObject d32 = d31.getJSONObject("JPY");
                        coin m31 = new coin();
                        m31 = gson.fromJson(d32.toString(), coin.class);
                        arrList.add(m31);
                    } else if (currency.equals("EUR")){
                        JSONObject d31 = data.getJSONObject("LTC");
                        JSONObject d32 = d31.getJSONObject("EUR");
                        coin m31 = new coin();
                        m31 = gson.fromJson(d32.toString(), coin.class);
                        arrList.add(m31);
                    }

                } else if (coins.equals(("ADA"))){
                    if (currency.equals("USD")){
                        JSONObject d41 = data.getJSONObject("ADA");
                        JSONObject d42 = d41.getJSONObject("USD");
                        coin m41 = new coin();
                        m41 = gson.fromJson(d42.toString(), coin.class);
                        arrList.add(m41);
                    } else if (currency.equals("JPY")){
                        JSONObject d41 = data.getJSONObject("ADA");
                        JSONObject d42 = d41.getJSONObject("JPY");
                        coin m41 = new coin();
                        m41 = gson.fromJson(d42.toString(), coin.class);
                        arrList.add(m41);
                    } else if (currency.equals("EUR")){
                        JSONObject d41 = data.getJSONObject("ADA");
                        JSONObject d42 = d41.getJSONObject("EUR");
                        coin m41 = new coin();
                        m41 = gson.fromJson(d42.toString(), coin.class);
                        arrList.add(m41);
                    }

                } else if (coins.equals(("IOT"))){
                    if (currency.equals("USD")){
                        JSONObject d51 = data.getJSONObject("IOT");
                        JSONObject d52 = d51.getJSONObject("USD");
                        coin m51 = new coin();
                        m51 = gson.fromJson(d52.toString(), coin.class);
                        arrList.add(m51);
                    } else if (currency.equals("JPY")){
                        JSONObject d51 = data.getJSONObject("IOT");
                        JSONObject d52 = d51.getJSONObject("JPY");
                        coin m51 = new coin();
                        m51 = gson.fromJson(d52.toString(), coin.class);
                        arrList.add(m51);
                    } else if (currency.equals("EUR")){
                        JSONObject d51 = data.getJSONObject("IOT");
                        JSONObject d52 = d51.getJSONObject("EUR");
                        coin m51 = new coin();
                        m51 = gson.fromJson(d52.toString(), coin.class);
                        arrList.add(m51);
                    }

                } else if (coins.equals(("DASH"))){
                    if (currency.equals("USD")){
                        JSONObject d61 = data.getJSONObject("DASH");
                        JSONObject d62 = d61.getJSONObject("USD");
                        coin m61 = new coin();
                        m61 = gson.fromJson(d62.toString(), coin.class);
                        arrList.add(m61);
                    } else if (currency.equals("JPY")){
                        JSONObject d61 = data.getJSONObject("DASH");
                        JSONObject d62 = d61.getJSONObject("JPY");
                        coin m61 = new coin();
                        m61 = gson.fromJson(d62.toString(), coin.class);
                        arrList.add(m61);
                    } else if (currency.equals("EUR")){
                        JSONObject d61 = data.getJSONObject("DASH");
                        JSONObject d62 = d61.getJSONObject("EUR");
                        coin m61 = new coin();
                        m61 = gson.fromJson(d62.toString(), coin.class);
                        arrList.add(m61);
                    }

                } else if (coins.equals(("XEM"))){
                    if (currency.equals("USD")){
                        JSONObject d71 = data.getJSONObject("XEM");
                        JSONObject d72 = d71.getJSONObject("USD");
                        coin m71 = new coin();
                        m71 = gson.fromJson(d72.toString(), coin.class);
                        arrList.add(m71);
                    } else if (currency.equals("JPY")){
                        JSONObject d71 = data.getJSONObject("XEM");
                        JSONObject d72 = d71.getJSONObject("JPY");
                        coin m71 = new coin();
                        m71 = gson.fromJson(d72.toString(), coin.class);
                        arrList.add(m71);
                    } else if (currency.equals("EUR")){
                        JSONObject d71 = data.getJSONObject("XEM");
                        JSONObject d72 = d71.getJSONObject("EUR");
                        coin m71 = new coin();
                        m71 = gson.fromJson(d72.toString(), coin.class);
                        arrList.add(m71);
                    }

                } else if (coins.equals(("XMR"))){
                    if (currency.equals("USD")){
                        JSONObject d81 = data.getJSONObject("XMR");
                        JSONObject d82 = d81.getJSONObject("USD");
                        coin m81 = new coin();
                        m81 = gson.fromJson(d82.toString(), coin.class);
                        arrList.add(m81);
                    } else if (currency.equals("JPY")){
                        JSONObject d81 = data.getJSONObject("XMR");
                        JSONObject d82 = d81.getJSONObject("JPY");
                        coin m81 = new coin();
                        m81 = gson.fromJson(d82.toString(), coin.class);
                        arrList.add(m81);
                    } else if (currency.equals("EUR")){
                        JSONObject d81 = data.getJSONObject("XMR");
                        JSONObject d82 = d81.getJSONObject("EUR");
                        coin m81 = new coin();
                        m81 = gson.fromJson(d82.toString(), coin.class);
                        arrList.add(m81);
                    }

                } else if (coins.equals(("EOS"))){
                    if (currency.equals("USD")){
                        JSONObject d91 = data.getJSONObject("EOS");
                        JSONObject d92 = d91.getJSONObject("USD");
                        coin m91 = new coin();
                        m91 = gson.fromJson(d92.toString(), coin.class);
                        arrList.add(m91);
                    } else if (currency.equals("JPY")){
                        JSONObject d91 = data.getJSONObject("EOS");
                        JSONObject d92 = d91.getJSONObject("JPY");
                        coin m91 = new coin();
                        m91 = gson.fromJson(d92.toString(), coin.class);
                        arrList.add(m91);
                    } else if (currency.equals("EUR")){
                        JSONObject d91 = data.getJSONObject("EOS");
                        JSONObject d92 = d91.getJSONObject("EUR");
                        coin m91 = new coin();
                        m91 = gson.fromJson(d92.toString(), coin.class);
                        arrList.add(m91);
                    }

                } else if (coins.equals(("BTG"))){
                    if (currency.equals("USD")){
                        JSONObject d111 = data.getJSONObject("BTG");
                        JSONObject d112 = d111.getJSONObject("USD");
                        coin m111 = new coin();
                        m111 = gson.fromJson(d112.toString(), coin.class);
                        arrList.add(m111);
                    } else if (currency.equals("JPY")){
                        JSONObject d111 = data.getJSONObject("BTG");
                        JSONObject d112 = d111.getJSONObject("JPY");
                        coin m111 = new coin();
                        m111 = gson.fromJson(d112.toString(), coin.class);
                        arrList.add(m111);
                    } else if (currency.equals("EUR")){
                        JSONObject d111 = data.getJSONObject("BTG");
                        JSONObject d112 = d111.getJSONObject("EUR");
                        coin m111 = new coin();
                        m111 = gson.fromJson(d112.toString(), coin.class);
                        arrList.add(m111);
                    }

                } else if (coins.equals(("QTUM"))){
                    if (currency.equals("USD")){
                        JSONObject d211 = data.getJSONObject("QTUM");
                        JSONObject d212 = d211.getJSONObject("USD");
                        coin m211 = new coin();
                        m211 = gson.fromJson(d212.toString(), coin.class);
                        arrList.add(m211);
                    } else if (currency.equals("JPY")){
                        JSONObject d211 = data.getJSONObject("QTUM");
                        JSONObject d212 = d211.getJSONObject("JPY");
                        coin m211 = new coin();
                        m211 = gson.fromJson(d212.toString(), coin.class);
                        arrList.add(m211);
                    } else if (currency.equals("EUR")){
                        JSONObject d211 = data.getJSONObject("QTUM");
                        JSONObject d212 = d211.getJSONObject("EUR");
                        coin m211 = new coin();
                        m211 = gson.fromJson(d212.toString(), coin.class);
                        arrList.add(m211);
                    }

                } else if (coins.equals(("XLM"))){
                    if (currency.equals("USD")){
                        JSONObject d311 = data.getJSONObject("XLM");
                        JSONObject d312 = d311.getJSONObject("USD");
                        coin m311 = new coin();
                        m311 = gson.fromJson(d312.toString(), coin.class);
                        arrList.add(m311);
                    } else if (currency.equals("JPY")){
                        JSONObject d311 = data.getJSONObject("XLM");
                        JSONObject d312 = d311.getJSONObject("JPY");
                        coin m311 = new coin();
                        m311 = gson.fromJson(d312.toString(), coin.class);
                        arrList.add(m311);
                    } else if (currency.equals("EUR")){
                        JSONObject d311 = data.getJSONObject("XLM");
                        JSONObject d312 = d311.getJSONObject("EUR");
                        coin m311 = new coin();
                        m311 = gson.fromJson(d312.toString(), coin.class);
                        arrList.add(m311);
                    }

                } else if (coins.equals(("NEO"))){
                    if (currency.equals("USD")){
                        JSONObject d411 = data.getJSONObject("NEO");
                        JSONObject d412 = d411.getJSONObject("USD");
                        coin m411 = new coin();
                        m411 = gson.fromJson(d412.toString(), coin.class);
                        arrList.add(m411);
                    } else if (currency.equals("JPY")){
                        JSONObject d411 = data.getJSONObject("NEO");
                        JSONObject d412 = d411.getJSONObject("JPY");
                        coin m411 = new coin();
                        m411 = gson.fromJson(d412.toString(), coin.class);
                        arrList.add(m411);
                    } else if (currency.equals("EUR")){
                        JSONObject d411 = data.getJSONObject("NEO");
                        JSONObject d412 = d411.getJSONObject("EUR");
                        coin m411 = new coin();
                        m411 = gson.fromJson(d412.toString(), coin.class);
                        arrList.add(m411);
                    }

                } else if (coins.equals(("ETC"))){
                    if (currency.equals("USD")){
                        JSONObject d511 = data.getJSONObject("ETC");
                        JSONObject d512 = d511.getJSONObject("USD");
                        coin m511 = new coin();
                        m511 = gson.fromJson(d512.toString(), coin.class);
                        arrList.add(m511);
                    } else if (currency.equals("JPY")){
                        JSONObject d511 = data.getJSONObject("ETC");
                        JSONObject d512 = d511.getJSONObject("JPY");
                        coin m511 = new coin();
                        m511 = gson.fromJson(d512.toString(), coin.class);
                        arrList.add(m511);
                    } else if (currency.equals("EUR")){
                        JSONObject d511 = data.getJSONObject("ETC");
                        JSONObject d512 = d511.getJSONObject("EUR");
                        coin m511 = new coin();
                        m511 = gson.fromJson(d512.toString(), coin.class);
                        arrList.add(m511);
                    }

                } else if (coins.equals(("ZEC"))){
                    if (currency.equals("USD")){
                        JSONObject d611 = data.getJSONObject("ZEC");
                        JSONObject d612 = d611.getJSONObject("USD");
                        coin m611 = new coin();
                        m611 = gson.fromJson(d612.toString(), coin.class);
                        arrList.add(m611);
                    } else if (currency.equals("JPY")){
                        JSONObject d611 = data.getJSONObject("ZEC");
                        JSONObject d612 = d611.getJSONObject("JPY");
                        coin m611 = new coin();
                        m611 = gson.fromJson(d612.toString(), coin.class);
                        arrList.add(m611);
                    } else if (currency.equals("EUR")){
                        JSONObject d611 = data.getJSONObject("ZEC");
                        JSONObject d612 = d611.getJSONObject("EUR");
                        coin m611 = new coin();
                        m611 = gson.fromJson(d612.toString(), coin.class);
                        arrList.add(m611);
                    }

                } else if (coins.equals(("STEEM"))){
                    if (currency.equals("USD")){
                        JSONObject d711 = data.getJSONObject("STEEM");
                        JSONObject d712 = d711.getJSONObject("USD");
                        coin m711 = new coin();
                        m711 = gson.fromJson(d712.toString(), coin.class);
                        arrList.add(m711);
                    } else if (currency.equals("JPY")){
                        JSONObject d711 = data.getJSONObject("STEEM");
                        JSONObject d712 = d711.getJSONObject("JPY");
                        coin m711 = new coin();
                        m711 = gson.fromJson(d712.toString(), coin.class);
                        arrList.add(m711);
                    } else if (currency.equals("EUR")){
                        JSONObject d711 = data.getJSONObject("STEEM");
                        JSONObject d712 = d711.getJSONObject("EUR");
                        coin m711 = new coin();
                        m711 = gson.fromJson(d712.toString(), coin.class);
                        arrList.add(m711);
                    }

                } else if (coins.equals(("XZC"))){
                    if (currency.equals("USD")){
                        JSONObject d811 = data.getJSONObject("XZC");
                        JSONObject d812 = d811.getJSONObject("USD");
                        coin m811 = new coin();
                        m811 = gson.fromJson(d812.toString(), coin.class);
                        arrList.add(m811);
                    } else if (currency.equals("JPY")){
                        JSONObject d811 = data.getJSONObject("XZC");
                        JSONObject d812 = d811.getJSONObject("JPY");
                        coin m811 = new coin();
                        m811 = gson.fromJson(d812.toString(), coin.class);
                        arrList.add(m811);
                    } else if (currency.equals("EUR")){
                        JSONObject d811 = data.getJSONObject("XZC");
                        JSONObject d812 = d811.getJSONObject("EUR");
                        coin m811 = new coin();
                        m811 = gson.fromJson(d812.toString(), coin.class);
                        arrList.add(m811);
                    }

                } else if (coins.equals(("STORJ"))){
                    if (currency.equals("USD")){
                        JSONObject d911 = data.getJSONObject("STORJ");
                        JSONObject d912 = d911.getJSONObject("USD");
                        coin m911 = new coin();
                        m911 = gson.fromJson(d912.toString(), coin.class);
                        arrList.add(m911);
                    } else if (currency.equals("JPY")){
                        JSONObject d911 = data.getJSONObject("STORJ");
                        JSONObject d912 = d911.getJSONObject("JPY");
                        coin m911 = new coin();
                        m911 = gson.fromJson(d912.toString(), coin.class);
                        arrList.add(m911);
                    } else if (currency.equals("EUR")){
                        JSONObject d911 = data.getJSONObject("STORJ");
                        JSONObject d912 = d911.getJSONObject("EUR");
                        coin m911 = new coin();
                        m911 = gson.fromJson(d912.toString(), coin.class);
                        arrList.add(m911);
                    }

                } else if (coins.equals(("AION"))){
                    if (currency.equals("USD")){
                        JSONObject d1011 = data.getJSONObject("AION");
                        JSONObject d1012 = d1011.getJSONObject("USD");
                        coin m1011 = new coin();
                        m1011 = gson.fromJson(d1012.toString(), coin.class);
                        arrList.add(m1011);
                    } else if (currency.equals("JPY")){
                        JSONObject d1011 = data.getJSONObject("AION");
                        JSONObject d1012 = d1011.getJSONObject("JPY");
                        coin m1011 = new coin();
                        m1011 = gson.fromJson(d1012.toString(), coin.class);
                        arrList.add(m1011);
                    } else if (currency.equals("EUR")){
                        JSONObject d1011 = data.getJSONObject("AION");
                        JSONObject d1012 = d1011.getJSONObject("EUR");
                        coin m1011 = new coin();
                        m1011 = gson.fromJson(d1012.toString(), coin.class);
                        arrList.add(m1011);
                    }

                } else if (coins.equals(("HT"))){
                    if (currency.equals("USD")){
                        JSONObject d1111 = data.getJSONObject("HT");
                        JSONObject d1112 = d1111.getJSONObject("USD");
                        coin m1111 = new coin();
                        m1111 = gson.fromJson(d1112.toString(), coin.class);
                        arrList.add(m1111);
                    } else if (currency.equals("JPY")){
                        JSONObject d1111 = data.getJSONObject("HT");
                        JSONObject d1112 = d1111.getJSONObject("JPY");
                        coin m1111 = new coin();
                        m1111 = gson.fromJson(d1112.toString(), coin.class);
                        arrList.add(m1111);
                    } else if (currency.equals("EUR")){
                        JSONObject d1111 = data.getJSONObject("HT");
                        JSONObject d1112 = d1111.getJSONObject("EUR");
                        coin m1111 = new coin();
                        m1111 = gson.fromJson(d1112.toString(), coin.class);
                        arrList.add(m1111);
                    }

                } else if (coins.equals(("TRX"))){
                    if (currency.equals("USD")){
                        JSONObject d1211 = data.getJSONObject("TRX");
                        JSONObject d1212 = d1211.getJSONObject("USD");
                        coin m1211 = new coin();
                        m1211 = gson.fromJson(d1212.toString(), coin.class);
                        arrList.add(m1211);
                    } else if (currency.equals("JPY")){
                        JSONObject d1211 = data.getJSONObject("TRX");
                        JSONObject d1212 = d1211.getJSONObject("JPY");
                        coin m1211 = new coin();
                        m1211 = gson.fromJson(d1212.toString(), coin.class);
                        arrList.add(m1211);
                    } else if (currency.equals("EUR")){
                        JSONObject d1211 = data.getJSONObject("TRX");
                        JSONObject d1212 = d1211.getJSONObject("EUR");
                        coin m1211 = new coin();
                        m1211 = gson.fromJson(d1212.toString(), coin.class);
                        arrList.add(m1211);
                    }

                } else if (coins.equals(("XRB"))){
                    if (currency.equals("USD")){
                        JSONObject d1311 = data.getJSONObject("XRB");
                        JSONObject d1312 = d1311.getJSONObject("USD");
                        coin m1311 = new coin();
                        m1311 = gson.fromJson(d1312.toString(), coin.class);
                        arrList.add(m1311);
                    } else if (currency.equals("JPY")){
                        JSONObject d1311 = data.getJSONObject("XRB");
                        JSONObject d1312 = d1311.getJSONObject("JPY");
                        coin m1311 = new coin();
                        m1311 = gson.fromJson(d1312.toString(), coin.class);
                        arrList.add(m1311);
                    } else if (currency.equals("EUR")){
                        JSONObject d1311 = data.getJSONObject("XRB");
                        JSONObject d1312 = d1311.getJSONObject("EUR");
                        coin m1311 = new coin();
                        m1311 = gson.fromJson(d1312.toString(), coin.class);
                        arrList.add(m1311);
                    }

                } else if (coins.equals(("OMG"))){
                    if (currency.equals("USD")){
                        JSONObject d1411 = data.getJSONObject("OMG");
                        JSONObject d1412 = d1411.getJSONObject("USD");
                        coin m1411 = new coin();
                        m1411 = gson.fromJson(d1412.toString(), coin.class);
                        arrList.add(m1411);
                    } else if (currency.equals("JPY")){
                        JSONObject d1411 = data.getJSONObject("OMG");
                        JSONObject d1412 = d1411.getJSONObject("JPY");
                        coin m1411 = new coin();
                        m1411 = gson.fromJson(d1412.toString(), coin.class);
                        arrList.add(m1411);
                    } else if (currency.equals("EUR")){
                        JSONObject d1411 = data.getJSONObject("OMG");
                        JSONObject d1412 = d1411.getJSONObject("EUR");
                        coin m1411 = new coin();
                        m1411 = gson.fromJson(d1412.toString(), coin.class);
                        arrList.add(m1411);
                    }

                } else if (coins.equals(("ELA"))){
                    if (currency.equals("USD")){
                        JSONObject d1511 = data.getJSONObject("ELA");
                        JSONObject d1512 = d1511.getJSONObject("USD");
                        coin m1511 = new coin();
                        m1511 = gson.fromJson(d1512.toString(), coin.class);
                        arrList.add(m1511);
                    } else if (currency.equals("JPY")){
                        JSONObject d1511 = data.getJSONObject("ELA");
                        JSONObject d1512 = d1511.getJSONObject("JPY");
                        coin m1511 = new coin();
                        m1511 = gson.fromJson(d1512.toString(), coin.class);
                        arrList.add(m1511);
                    } else if (currency.equals("EUR")){
                        JSONObject d1511 = data.getJSONObject("ELA");
                        JSONObject d1512 = d1511.getJSONObject("EUR");
                        coin m1511 = new coin();
                        m1511 = gson.fromJson(d1512.toString(), coin.class);
                        arrList.add(m1511);
                    }

                } else if (coins.equals(("BNB"))){
                    if (currency.equals("USD")){
                        JSONObject d1611 = data.getJSONObject("BNB");
                        JSONObject d1612 = d1611.getJSONObject("USD");
                        coin m1611 = new coin();
                        m1611 = gson.fromJson(d1612.toString(), coin.class);
                        arrList.add(m1611);
                    } else if (currency.equals("JPY")){
                        JSONObject d1611 = data.getJSONObject("BNB");
                        JSONObject d1612 = d1611.getJSONObject("JPY");
                        coin m1611 = new coin();
                        m1611 = gson.fromJson(d1612.toString(), coin.class);
                        arrList.add(m1611);
                    } else if (currency.equals("EUR")){
                        JSONObject d1611 = data.getJSONObject("BNB");
                        JSONObject d1612 = d1611.getJSONObject("EUR");
                        coin m1611 = new coin();
                        m1611 = gson.fromJson(d1612.toString(), coin.class);
                        arrList.add(m1611);
                    }

                } else if (coins.equals(("VEN"))){
                    if (currency.equals("USD")){
                        JSONObject d1711 = data.getJSONObject("VEN");
                        JSONObject d1712 = d1711.getJSONObject("USD");
                        coin m1711 = new coin();
                        m1711 = gson.fromJson(d1712.toString(), coin.class);
                        arrList.add(m1711);
                    } else if (currency.equals("JPY")){
                        JSONObject d1711 = data.getJSONObject("VEN");
                        JSONObject d1712 = d1711.getJSONObject("JPY");
                        coin m1711 = new coin();
                        m1711 = gson.fromJson(d1712.toString(), coin.class);
                        arrList.add(m1711);
                    } else if (currency.equals("EUR")){
                        JSONObject d1711 = data.getJSONObject("VEN");
                        JSONObject d1712 = d1711.getJSONObject("EUR");
                        coin m1711 = new coin();
                        m1711 = gson.fromJson(d1712.toString(), coin.class);
                        arrList.add(m1711);
                    }

                } else if (coins.equals(("ZCL"))){
                    if (currency.equals("USD")){
                        JSONObject d1811 = data.getJSONObject("ZCL");
                        JSONObject d1812 = d1811.getJSONObject("USD");
                        coin m1811 = new coin();
                        m1811 = gson.fromJson(d1812.toString(), coin.class);
                        arrList.add(m1811);
                    } else if (currency.equals("JPY")){
                        JSONObject d1811 = data.getJSONObject("ZCL");
                        JSONObject d1812 = d1811.getJSONObject("JPY");
                        coin m1811 = new coin();
                        m1811 = gson.fromJson(d1812.toString(), coin.class);
                        arrList.add(m1811);
                    } else if (currency.equals("EUR")){
                        JSONObject d1811 = data.getJSONObject("ZCL");
                        JSONObject d1812 = d1811.getJSONObject("EUR");
                        coin m1811 = new coin();
                        m1811 = gson.fromJson(d1812.toString(), coin.class);
                        arrList.add(m1811);
                    }

                } else if (coins.equals(("DGD"))){
                    if (currency.equals("USD")){
                        JSONObject d1911 = data.getJSONObject("DGD");
                        JSONObject d1912 = d1911.getJSONObject("USD");
                        coin m1911 = new coin();
                        m1911 = gson.fromJson(d1912.toString(), coin.class);
                        arrList.add(m1911);
                    } else if (currency.equals("JPY")){
                        JSONObject d1911 = data.getJSONObject("DGD");
                        JSONObject d1912 = d1911.getJSONObject("JPY");
                        coin m1911 = new coin();
                        m1911 = gson.fromJson(d1912.toString(), coin.class);
                        arrList.add(m1911);
                    } else if (currency.equals("EUR")){
                        JSONObject d1911 = data.getJSONObject("DGD");
                        JSONObject d1912 = d1911.getJSONObject("EUR");
                        coin m1911 = new coin();
                        m1911 = gson.fromJson(d1912.toString(), coin.class);
                        arrList.add(m1911);
                    }

                } else if (coins.equals(("OCN"))){
                    if (currency.equals("USD")){
                        JSONObject d2011 = data.getJSONObject("OCN");
                        JSONObject d2012 = d2011.getJSONObject("USD");
                        coin m2011 = new coin();
                        m2011 = gson.fromJson(d2012.toString(), coin.class);
                        arrList.add(m2011);
                    } else if (currency.equals("JPY")){
                        JSONObject d2011 = data.getJSONObject("OCN");
                        JSONObject d2012 = d2011.getJSONObject("JPY");
                        coin m2011 = new coin();
                        m2011 = gson.fromJson(d2012.toString(), coin.class);
                        arrList.add(m2011);
                    } else if (currency.equals("EUR")){
                        JSONObject d2011 = data.getJSONObject("OCN");
                        JSONObject d2012 = d2011.getJSONObject("EUR");
                        coin m2011 = new coin();
                        m2011 = gson.fromJson(d2012.toString(), coin.class);
                        arrList.add(m2011);
                    }

                } else if (coins.equals(("BCPT"))){
                    if (currency.equals("USD")){
                        JSONObject d2111 = data.getJSONObject("BCPT");
                        JSONObject d2112 = d2111.getJSONObject("USD");
                        coin m2111 = new coin();
                        m2111 = gson.fromJson(d2112.toString(), coin.class);
                        arrList.add(m2111);
                    } else if (currency.equals("JPY")){
                        JSONObject d2111 = data.getJSONObject("BCPT");
                        JSONObject d2112 = d2111.getJSONObject("JPY");
                        coin m2111 = new coin();
                        m2111 = gson.fromJson(d2112.toString(), coin.class);
                        arrList.add(m2111);
                    } else if (currency.equals("EUR")){
                        JSONObject d2111 = data.getJSONObject("BCPT");
                        JSONObject d2112 = d2111.getJSONObject("EUR");
                        coin m2111 = new coin();
                        m2111 = gson.fromJson(d2112.toString(), coin.class);
                        arrList.add(m2111);
                    }

                } else if (coins.equals(("LUN"))){
                    if (currency.equals("USD")){
                        JSONObject d2211 = data.getJSONObject("LUN");
                        JSONObject d2212 = d2211.getJSONObject("USD");
                        coin m2211 = new coin();
                        m2211 = gson.fromJson(d2212.toString(), coin.class);
                        arrList.add(m2211);
                    } else if (currency.equals("JPY")){
                        JSONObject d2211 = data.getJSONObject("LUN");
                        JSONObject d2212 = d2211.getJSONObject("JPY");
                        coin m2211 = new coin();
                        m2211 = gson.fromJson(d2212.toString(), coin.class);
                        arrList.add(m2211);
                    } else if (currency.equals("EUR")){
                        JSONObject d2211 = data.getJSONObject("LUN");
                        JSONObject d2212 = d2211.getJSONObject("EUR");
                        coin m2211 = new coin();
                        m2211 = gson.fromJson(d2212.toString(), coin.class);
                        arrList.add(m2211);
                    }

                } else if (coins.equals(("IOST"))){
                    if (currency.equals("USD")){
                        JSONObject d2311 = data.getJSONObject("IOST");
                        JSONObject d2312 = d2311.getJSONObject("USD");
                        coin m2311 = new coin();
                        m2311 = gson.fromJson(d2312.toString(), coin.class);
                        arrList.add(m2311);
                    } else if (currency.equals("JPY")){
                        JSONObject d2311 = data.getJSONObject("IOST");
                        JSONObject d2312 = d2311.getJSONObject("JPY");
                        coin m2311 = new coin();
                        m2311 = gson.fromJson(d2312.toString(), coin.class);
                        arrList.add(m2311);
                    } else if (currency.equals("EUR")){
                        JSONObject d2311 = data.getJSONObject("IOST");
                        JSONObject d2312 = d2311.getJSONObject("EUR");
                        coin m2311 = new coin();
                        m2311 = gson.fromJson(d2312.toString(), coin.class);
                        arrList.add(m2311);
                    }

                } else if (coins.equals(("HSR"))){
                    if (currency.equals("USD")){
                        JSONObject d2411 = data.getJSONObject("HSR");
                        JSONObject d2412 = d2411.getJSONObject("USD");
                        coin m2411 = new coin();
                        m2411 = gson.fromJson(d2412.toString(), coin.class);
                        arrList.add(m2411);
                    } else if (currency.equals("JPY")){
                        JSONObject d2411 = data.getJSONObject("HSR");
                        JSONObject d2412 = d2411.getJSONObject("JPY");
                        coin m2411 = new coin();
                        m2411 = gson.fromJson(d2412.toString(), coin.class);
                        arrList.add(m2411);
                    } else if (currency.equals("EUR")){
                        JSONObject d2411 = data.getJSONObject("HSR");
                        JSONObject d2412 = d2411.getJSONObject("EUR");
                        coin m2411 = new coin();
                        m2411 = gson.fromJson(d2412.toString(), coin.class);
                        arrList.add(m2411);
                    }

                } else if (coins.equals(("ICX"))){
                    if (currency.equals("USD")){
                        JSONObject d2511 = data.getJSONObject("ICX");
                        JSONObject d2512 = d2511.getJSONObject("USD");
                        coin m2511 = new coin();
                        m2511 = gson.fromJson(d2512.toString(), coin.class);
                        arrList.add(m2511);
                    } else if (currency.equals("JPY")){
                        JSONObject d2511 = data.getJSONObject("ICX");
                        JSONObject d2512 = d2511.getJSONObject("JPY");
                        coin m2511 = new coin();
                        m2511 = gson.fromJson(d2512.toString(), coin.class);
                        arrList.add(m2511);
                    } else if (currency.equals("EUR")){
                        JSONObject d2511 = data.getJSONObject("ICX");
                        JSONObject d2512 = d2511.getJSONObject("EUR");
                        coin m2511 = new coin();
                        m2511 = gson.fromJson(d2512.toString(), coin.class);
                        arrList.add(m2511);
                    }

                } else if (coins.equals(("LSK"))){
                    if (currency.equals("USD")){
                        JSONObject d2611 = data.getJSONObject("LSK");
                        JSONObject d2612 = d2611.getJSONObject("USD");
                        coin m2611 = new coin();
                        m2611 = gson.fromJson(d2612.toString(), coin.class);
                        arrList.add(m2611);
                    } else if (currency.equals("JPY")){
                        JSONObject d2611 = data.getJSONObject("LSK");
                        JSONObject d2612 = d2611.getJSONObject("JPY");
                        coin m2611 = new coin();
                        m2611 = gson.fromJson(d2612.toString(), coin.class);
                        arrList.add(m2611);
                    } else if (currency.equals("EUR")){
                        JSONObject d2611 = data.getJSONObject("LSK");
                        JSONObject d2612 = d2611.getJSONObject("EUR");
                        coin m2611 = new coin();
                        m2611 = gson.fromJson(d2612.toString(), coin.class);
                        arrList.add(m2611);
                    }

                } else if (coins.equals(("NEBL"))){
                    if (currency.equals("USD")){
                        JSONObject d2711 = data.getJSONObject("NEBL");
                        JSONObject d2712 = d2711.getJSONObject("USD");
                        coin m2711 = new coin();
                        m2711 = gson.fromJson(d2712.toString(), coin.class);
                        arrList.add(m2711);
                    } else if (currency.equals("JPY")){
                        JSONObject d2711 = data.getJSONObject("NEBL");
                        JSONObject d2712 = d2711.getJSONObject("JPY");
                        coin m2711 = new coin();
                        m2711 = gson.fromJson(d2712.toString(), coin.class);
                        arrList.add(m2711);
                    } else if (currency.equals("EUR")){
                        JSONObject d2711 = data.getJSONObject("NEBL");
                        JSONObject d2712 = d2711.getJSONObject("EUR");
                        coin m2711 = new coin();
                        m2711 = gson.fromJson(d2712.toString(), coin.class);
                        arrList.add(m2711);
                    }

                } else if (coins.equals(("WAVES"))){
                    if (currency.equals("USD")){
                        JSONObject d2811 = data.getJSONObject("WAVES");
                        JSONObject d2812 = d2811.getJSONObject("USD");
                        coin m2811 = new coin();
                        m2811 = gson.fromJson(d2812.toString(), coin.class);
                        arrList.add(m2811);
                    } else if (currency.equals("JPY")){
                        JSONObject d2811 = data.getJSONObject("WAVES");
                        JSONObject d2812 = d2811.getJSONObject("JPY");
                        coin m2811 = new coin();
                        m2811 = gson.fromJson(d2812.toString(), coin.class);
                        arrList.add(m2811);
                    } else if (currency.equals("EUR")){
                        JSONObject d2811 = data.getJSONObject("WAVES");
                        JSONObject d2812 = d2811.getJSONObject("EUR");
                        coin m2811 = new coin();
                        m2811 = gson.fromJson(d2812.toString(), coin.class);
                        arrList.add(m2811);
                    }

                } else if (coins.equals(("BLZ"))){
                    if (currency.equals("USD")){
                        JSONObject d2911 = data.getJSONObject("BLZ");
                        JSONObject d2912 = d2911.getJSONObject("USD");
                        coin m2911 = new coin();
                        m2911 = gson.fromJson(d2912.toString(), coin.class);
                        arrList.add(m2911);
                    } else if (currency.equals("JPY")){
                        JSONObject d2911 = data.getJSONObject("BLZ");
                        JSONObject d2912 = d2911.getJSONObject("JPY");
                        coin m2911 = new coin();
                        m2911 = gson.fromJson(d2912.toString(), coin.class);
                        arrList.add(m2911);
                    } else if (currency.equals("EUR")){
                        JSONObject d2911 = data.getJSONObject("BLZ");
                        JSONObject d2912 = d2911.getJSONObject("EUR");
                        coin m2911 = new coin();
                        m2911 = gson.fromJson(d2912.toString(), coin.class);
                        arrList.add(m2911);
                    }

                } else if (coins.equals(("INK"))){
                    if (currency.equals("USD")){
                        JSONObject d3011 = data.getJSONObject("INK");
                        JSONObject d3012 = d3011.getJSONObject("USD");
                        coin m3011 = new coin();
                        m3011 = gson.fromJson(d3012.toString(), coin.class);
                        arrList.add(m3011);
                    } else if (currency.equals("JPY")){
                        JSONObject d3011 = data.getJSONObject("INK");
                        JSONObject d3012 = d3011.getJSONObject("JPY");
                        coin m3011 = new coin();
                        m3011 = gson.fromJson(d3012.toString(), coin.class);
                        arrList.add(m3011);
                    } else if (currency.equals("EUR")){
                        JSONObject d3011 = data.getJSONObject("INK");
                        JSONObject d3012 = d3011.getJSONObject("EUR");
                        coin m3011 = new coin();
                        m3011 = gson.fromJson(d3012.toString(), coin.class);
                        arrList.add(m3011);
                    }

                } else if (coins.equals(("ADX"))){
                    if (currency.equals("USD")){
                        JSONObject d3111 = data.getJSONObject("ADX");
                        JSONObject d3112 = d3111.getJSONObject("USD");
                        coin m3111 = new coin();
                        m3111 = gson.fromJson(d3112.toString(), coin.class);
                        arrList.add(m3111);
                    } else if (currency.equals("JPY")){
                        JSONObject d3111 = data.getJSONObject("ADX");
                        JSONObject d3112 = d3111.getJSONObject("JPY");
                        coin m3111 = new coin();
                        m3111 = gson.fromJson(d3112.toString(), coin.class);
                        arrList.add(m3111);
                    } else if (currency.equals("EUR")){
                        JSONObject d3111 = data.getJSONObject("ADX");
                        JSONObject d3112 = d3111.getJSONObject("EUR");
                        coin m3111 = new coin();
                        m3111 = gson.fromJson(d3112.toString(), coin.class);
                        arrList.add(m3111);
                    }

                } else if (coins.equals(("XVG"))){
                    if (currency.equals("USD")){
                        JSONObject d3211 = data.getJSONObject("XVG");
                        JSONObject d3212 = d3211.getJSONObject("USD");
                        coin m3211 = new coin();
                        m3211 = gson.fromJson(d3212.toString(), coin.class);
                        arrList.add(m3211);
                    } else if (currency.equals("JPY")){
                        JSONObject d3211 = data.getJSONObject("XVG");
                        JSONObject d3212 = d3211.getJSONObject("JPY");
                        coin m3211 = new coin();
                        m3211 = gson.fromJson(d3212.toString(), coin.class);
                        arrList.add(m3211);
                    } else if (currency.equals("EUR")){
                        JSONObject d3211 = data.getJSONObject("XVG");
                        JSONObject d3212 = d3211.getJSONObject("EUR");
                        coin m3211 = new coin();
                        m3211 = gson.fromJson(d3212.toString(), coin.class);
                        arrList.add(m3211);
                    }

                } else if (coins.equals(("MTL"))){
                    if (currency.equals("USD")){
                        JSONObject d3411 = data.getJSONObject("MTL");
                        JSONObject d3412 = d3411.getJSONObject("USD");
                        coin m3411 = new coin();
                        m3411 = gson.fromJson(d3412.toString(), coin.class);
                        arrList.add(m3411);
                    } else if (currency.equals("JPY")){
                        JSONObject d3411 = data.getJSONObject("MTL");
                        JSONObject d3412 = d3411.getJSONObject("JPY");
                        coin m3411 = new coin();
                        m3411 = gson.fromJson(d3412.toString(), coin.class);
                        arrList.add(m3411);
                    } else if (currency.equals("EUR")){
                        JSONObject d3411 = data.getJSONObject("MTL");
                        JSONObject d3412 = d3411.getJSONObject("EUR");
                        coin m3411 = new coin();
                        m3411 = gson.fromJson(d3412.toString(), coin.class);
                        arrList.add(m3411);
                    }

                } else if (coins.equals(("SRN"))){
                    if (currency.equals("USD")){
                        JSONObject d3511 = data.getJSONObject("SRN");
                        JSONObject d3512 = d3511.getJSONObject("USD");
                        coin m3511 = new coin();
                        m3511 = gson.fromJson(d3512.toString(), coin.class);
                        arrList.add(m3511);
                    } else if (currency.equals("JPY")){
                        JSONObject d3511 = data.getJSONObject("SRN");
                        JSONObject d3512 = d3511.getJSONObject("JPY");
                        coin m3511 = new coin();
                        m3511 = gson.fromJson(d3512.toString(), coin.class);
                        arrList.add(m3511);
                    } else if (currency.equals("EUR")){
                        JSONObject d3511 = data.getJSONObject("SRN");
                        JSONObject d3512 = d3511.getJSONObject("EUR");
                        coin m3511 = new coin();
                        m3511 = gson.fromJson(d3512.toString(), coin.class);
                        arrList.add(m3511);
                    }

                } else if (coins.equals(("ZRX"))){
                    if (currency.equals("USD")){
                        JSONObject d3611 = data.getJSONObject("ZRX");
                        JSONObject d3612 = d3611.getJSONObject("USD");
                        coin m3611 = new coin();
                        m3611 = gson.fromJson(d3612.toString(), coin.class);
                        arrList.add(m3611);
                    } else if (currency.equals("JPY")){
                        JSONObject d3611 = data.getJSONObject("ZRX");
                        JSONObject d3612 = d3611.getJSONObject("JPY");
                        coin m3611 = new coin();
                        m3611 = gson.fromJson(d3612.toString(), coin.class);
                        arrList.add(m3611);
                    } else if (currency.equals("EUR")){
                        JSONObject d3611 = data.getJSONObject("ZRX");
                        JSONObject d3612 = d3611.getJSONObject("EUR");
                        coin m3611 = new coin();
                        m3611 = gson.fromJson(d3612.toString(), coin.class);
                        arrList.add(m3611);
                    }

                } else if (coins.equals(("RLC"))){
                    if (currency.equals("USD")){
                        JSONObject d3711 = data.getJSONObject("RLC");
                        JSONObject d3712 = d3711.getJSONObject("USD");
                        coin m3711 = new coin();
                        m3711 = gson.fromJson(d3712.toString(), coin.class);
                        arrList.add(m3711);
                    } else if (currency.equals("JPY")){
                        JSONObject d3711 = data.getJSONObject("RLC");
                        JSONObject d3712 = d3711.getJSONObject("JPY");
                        coin m3711 = new coin();
                        m3711 = gson.fromJson(d3712.toString(), coin.class);
                        arrList.add(m3711);
                    } else if (currency.equals("EUR")){
                        JSONObject d3711 = data.getJSONObject("RLC");
                        JSONObject d3712 = d3711.getJSONObject("EUR");
                        coin m3711 = new coin();
                        m3711 = gson.fromJson(d3712.toString(), coin.class);
                        arrList.add(m3711);
                    }

                } else if (coins.equals(("THETA"))){
                    if (currency.equals("USD")){
                        JSONObject d3811 = data.getJSONObject("THETA");
                        JSONObject d3812 = d3811.getJSONObject("USD");
                        coin m3811 = new coin();
                        m3811 = gson.fromJson(d3812.toString(), coin.class);
                        arrList.add(m3811);
                    } else if (currency.equals("JPY")){
                        JSONObject d3811 = data.getJSONObject("THETA");
                        JSONObject d3812 = d3811.getJSONObject("JPY");
                        coin m3811 = new coin();
                        m3811 = gson.fromJson(d3812.toString(), coin.class);
                        arrList.add(m3811);
                    } else if (currency.equals("EUR")){
                        JSONObject d3811 = data.getJSONObject("THETA");
                        JSONObject d3812 = d3811.getJSONObject("EUR");
                        coin m3811 = new coin();
                        m3811 = gson.fromJson(d3812.toString(), coin.class);
                        arrList.add(m3811);
                    }

                } else if (coins.equals(("BCD"))){
                    if (currency.equals("USD")){
                        JSONObject d3911 = data.getJSONObject("BCD");
                        JSONObject d3912 = d3911.getJSONObject("USD");
                        coin m3911 = new coin();
                        m3911 = gson.fromJson(d3912.toString(), coin.class);
                        arrList.add(m3911);
                    } else if (currency.equals("JPY")){
                        JSONObject d3911 = data.getJSONObject("BCD");
                        JSONObject d3912 = d3911.getJSONObject("JPY");
                        coin m3911 = new coin();
                        m3911 = gson.fromJson(d3912.toString(), coin.class);
                        arrList.add(m3911);
                    } else if (currency.equals("EUR")){
                        JSONObject d3911 = data.getJSONObject("BCD");
                        JSONObject d3912 = d3911.getJSONObject("EUR");
                        coin m3911 = new coin();
                        m3911 = gson.fromJson(d3912.toString(), coin.class);
                        arrList.add(m3911);
                    }

                } else if (coins.equals(("OAX"))){
                    if (currency.equals("USD")){
                        JSONObject d4011 = data.getJSONObject("OAX");
                        JSONObject d4012 = d4011.getJSONObject("USD");
                        coin m4011 = new coin();
                        m4011 = gson.fromJson(d4012.toString(), coin.class);
                        arrList.add(m4011);
                    } else if (currency.equals("JPY")){
                        JSONObject d4011 = data.getJSONObject("OAX");
                        JSONObject d4012 = d4011.getJSONObject("JPY");
                        coin m4011 = new coin();
                        m4011 = gson.fromJson(d4012.toString(), coin.class);
                        arrList.add(m4011);
                    } else if (currency.equals("EUR")){
                        JSONObject d4011 = data.getJSONObject("OAX");
                        JSONObject d4012 = d4011.getJSONObject("EUR");
                        coin m4011 = new coin();
                        m4011 = gson.fromJson(d4012.toString(), coin.class);
                        arrList.add(m4011);
                    }

                } else if (coins.equals(("ELF"))){
                    if (currency.equals("USD")){
                        JSONObject d4111 = data.getJSONObject("ELF");
                        JSONObject d4112 = d4111.getJSONObject("USD");
                        coin m4111 = new coin();
                        m4111 = gson.fromJson(d4112.toString(), coin.class);
                        arrList.add(m4111);
                    } else if (currency.equals("JPY")){
                        JSONObject d4111 = data.getJSONObject("ELF");
                        JSONObject d4112 = d4111.getJSONObject("JPY");
                        coin m4111 = new coin();
                        m4111 = gson.fromJson(d4112.toString(), coin.class);
                        arrList.add(m4111);
                    } else if (currency.equals("EUR")){
                        JSONObject d4111 = data.getJSONObject("ELF");
                        JSONObject d4112 = d4111.getJSONObject("EUR");
                        coin m4111 = new coin();
                        m4111 = gson.fromJson(d4112.toString(), coin.class);
                        arrList.add(m4111);
                    }

                } else if (coins.equals(("INS"))){
                    if (currency.equals("USD")){
                        JSONObject d4211 = data.getJSONObject("INS");
                        JSONObject d4212 = d4211.getJSONObject("USD");
                        coin m4211 = new coin();
                        m4211 = gson.fromJson(d4212.toString(), coin.class);
                        arrList.add(m4211);
                    } else if (currency.equals("JPY")){
                        JSONObject d4211 = data.getJSONObject("INS");
                        JSONObject d4212 = d4211.getJSONObject("JPY");
                        coin m4211 = new coin();
                        m4211 = gson.fromJson(d4212.toString(), coin.class);
                        arrList.add(m4211);
                    } else if (currency.equals("EUR")){
                        JSONObject d4211 = data.getJSONObject("INS");
                        JSONObject d4212 = d4211.getJSONObject("EUR");
                        coin m4211 = new coin();
                        m4211 = gson.fromJson(d4212.toString(), coin.class);
                        arrList.add(m4211);
                    }

                } else if (coins.equals(("ZIL"))){
                    if (currency.equals("USD")){
                        JSONObject d4311 = data.getJSONObject("ZIL");
                        JSONObject d4312 = d4311.getJSONObject("USD");
                        coin m4311 = new coin();
                        m4311 = gson.fromJson(d4312.toString(), coin.class);
                        arrList.add(m4311);
                    } else if (currency.equals("JPY")){
                        JSONObject d4311 = data.getJSONObject("ZIL");
                        JSONObject d4312 = d4311.getJSONObject("JPY");
                        coin m4311 = new coin();
                        m4311 = gson.fromJson(d4312.toString(), coin.class);
                        arrList.add(m4311);
                    } else if (currency.equals("EUR")){
                        JSONObject d4311 = data.getJSONObject("ZIL");
                        JSONObject d4312 = d4311.getJSONObject("EUR");
                        coin m4311 = new coin();
                        m4311 = gson.fromJson(d4312.toString(), coin.class);
                        arrList.add(m4311);
                    }

                } else if (coins.equals(("AE"))){
                    if (currency.equals("USD")){
                        JSONObject d4411 = data.getJSONObject("AE");
                        JSONObject d4412 = d4411.getJSONObject("USD");
                        coin m4411 = new coin();
                        m4411 = gson.fromJson(d4412.toString(), coin.class);
                        arrList.add(m4411);
                    } else if (currency.equals("JPY")){
                        JSONObject d4411 = data.getJSONObject("AE");
                        JSONObject d4412 = d4411.getJSONObject("JPY");
                        coin m4411 = new coin();
                        m4411 = gson.fromJson(d4412.toString(), coin.class);
                        arrList.add(m4411);
                    } else if (currency.equals("EUR")){
                        JSONObject d4411 = data.getJSONObject("AE");
                        JSONObject d4412 = d4411.getJSONObject("EUR");
                        coin m4411 = new coin();
                        m4411 = gson.fromJson(d4412.toString(), coin.class);
                        arrList.add(m4411);
                    }

                } else if (coins.equals(("PRO"))){
                    if (currency.equals("USD")){
                        JSONObject d4511 = data.getJSONObject("PRO");
                        JSONObject d4512 = d4511.getJSONObject("USD");
                        coin m4511 = new coin();
                        m4511 = gson.fromJson(d4512.toString(), coin.class);
                        arrList.add(m4511);
                    } else if (currency.equals("JPY")){
                        JSONObject d4511 = data.getJSONObject("PRO");
                        JSONObject d4512 = d4511.getJSONObject("JPY");
                        coin m4511 = new coin();
                        m4511 = gson.fromJson(d4512.toString(), coin.class);
                        arrList.add(m4511);
                    } else if (currency.equals("EUR")){
                        JSONObject d4511 = data.getJSONObject("PRO");
                        JSONObject d4512 = d4511.getJSONObject("EUR");
                        coin m4511 = new coin();
                        m4511 = gson.fromJson(d4512.toString(), coin.class);
                        arrList.add(m4511);
                    }

                } else if (coins.equals(("DOGE"))){
                    if (currency.equals("USD")){
                        JSONObject d4611 = data.getJSONObject("DOGE");
                        JSONObject d4612 = d4611.getJSONObject("USD");
                        coin m4611 = new coin();
                        m4611 = gson.fromJson(d4612.toString(), coin.class);
                        arrList.add(m4611);
                    } else if (currency.equals("JPY")){
                        JSONObject d4611 = data.getJSONObject("DOGE");
                        JSONObject d4612 = d4611.getJSONObject("JPY");
                        coin m4611 = new coin();
                        m4611 = gson.fromJson(d4612.toString(), coin.class);
                        arrList.add(m4611);
                    } else if (currency.equals("EUR")){
                        JSONObject d4611 = data.getJSONObject("DOGE");
                        JSONObject d4612 = d4611.getJSONObject("EUR");
                        coin m4611 = new coin();
                        m4611 = gson.fromJson(d4612.toString(), coin.class);
                        arrList.add(m4611);
                    }

                } else if (coins.equals(("ITC"))){
                    if (currency.equals("USD")){
                        JSONObject d4711 = data.getJSONObject("ITC");
                        JSONObject d4712 = d4711.getJSONObject("USD");
                        coin m4711 = new coin();
                        m4711 = gson.fromJson(d4712.toString(), coin.class);
                        arrList.add(m4711);
                    } else if (currency.equals("JPY")){
                        JSONObject d4711 = data.getJSONObject("ITC");
                        JSONObject d4712 = d4711.getJSONObject("JPY");
                        coin m4711 = new coin();
                        m4711 = gson.fromJson(d4712.toString(), coin.class);
                        arrList.add(m4711);
                    } else if (currency.equals("EUR")){
                        JSONObject d4711 = data.getJSONObject("ITC");
                        JSONObject d4712 = d4711.getJSONObject("EUR");
                        coin m4711 = new coin();
                        m4711 = gson.fromJson(d4712.toString(), coin.class);
                        arrList.add(m4711);
                    }

                } else if (coins.equals(("RDD"))){
                    if (currency.equals("USD")){
                        JSONObject d4811 = data.getJSONObject("RDD");
                        JSONObject d4812 = d4811.getJSONObject("USD");
                        coin m4811 = new coin();
                        m4811 = gson.fromJson(d4812.toString(), coin.class);
                        arrList.add(m4811);
                    } else if (currency.equals("JPY")){
                        JSONObject d4811 = data.getJSONObject("RDD");
                        JSONObject d4812 = d4811.getJSONObject("JPY");
                        coin m4811 = new coin();
                        m4811 = gson.fromJson(d4812.toString(), coin.class);
                        arrList.add(m4811);
                    } else if (currency.equals("EUR")){
                        JSONObject d4811 = data.getJSONObject("RDD");
                        JSONObject d4812 = d4811.getJSONObject("EUR");
                        coin m4811 = new coin();
                        m4811 = gson.fromJson(d4812.toString(), coin.class);
                        arrList.add(m4811);
                    }

                } else if (coins.equals(("SWFTC"))){
                    if (currency.equals("USD")){
                        JSONObject d4911 = data.getJSONObject("SWFTC");
                        JSONObject d4912 = d4911.getJSONObject("USD");
                        coin m4911 = new coin();
                        m4911 = gson.fromJson(d4912.toString(), coin.class);
                        arrList.add(m4911);
                    } else if (currency.equals("JPY")){
                        JSONObject d4911 = data.getJSONObject("SWFTC");
                        JSONObject d4912 = d4911.getJSONObject("JPY");
                        coin m4911 = new coin();
                        m4911 = gson.fromJson(d4912.toString(), coin.class);
                        arrList.add(m4911);
                    } else if (currency.equals("EUR")){
                        JSONObject d4911 = data.getJSONObject("SWFTC");
                        JSONObject d4912 = d4911.getJSONObject("EUR");
                        coin m4911 = new coin();
                        m4911 = gson.fromJson(d4912.toString(), coin.class);
                        arrList.add(m4911);
                    }

                } else if (coins.equals(("MCO"))){
                    if (currency.equals("USD")){
                        JSONObject d5011 = data.getJSONObject("MCO");
                        JSONObject d5012 = d5011.getJSONObject("USD");
                        coin m5011 = new coin();
                        m5011 = gson.fromJson(d5012.toString(), coin.class);
                        arrList.add(m5011);
                    } else if (currency.equals("JPY")){
                        JSONObject d5011 = data.getJSONObject("MCO");
                        JSONObject d5012 = d5011.getJSONObject("JPY");
                        coin m5011 = new coin();
                        m5011 = gson.fromJson(d5012.toString(), coin.class);
                        arrList.add(m5011);
                    } else if (currency.equals("EUR")){
                        JSONObject d5011 = data.getJSONObject("MCO");
                        JSONObject d5012 = d5011.getJSONObject("EUR");
                        coin m5011 = new coin();
                        m5011 = gson.fromJson(d5012.toString(), coin.class);
                        arrList.add(m5011);
                    }

                } else if (coins.equals(("ARN"))){
                    if (currency.equals("USD")){
                        JSONObject d5111 = data.getJSONObject("ARN");
                        JSONObject d5112 = d5111.getJSONObject("USD");
                        coin m5111 = new coin();
                        m5111 = gson.fromJson(d5112.toString(), coin.class);
                        arrList.add(m5111);
                    } else if (currency.equals("JPY")){
                        JSONObject d5111 = data.getJSONObject("ARN");
                        JSONObject d5112 = d5111.getJSONObject("JPY");
                        coin m5111 = new coin();
                        m5111 = gson.fromJson(d5112.toString(), coin.class);
                        arrList.add(m5111);
                    } else if (currency.equals("EUR")){
                        JSONObject d5111 = data.getJSONObject("ARN");
                        JSONObject d5112 = d5111.getJSONObject("EUR");
                        coin m5111 = new coin();
                        m5111 = gson.fromJson(d5112.toString(), coin.class);
                        arrList.add(m5111);
                    }

                } else if (coins.equals(("MTN*"))){
                    if (currency.equals("USD")){
                        JSONObject d5211 = data.getJSONObject("MTN*");
                        JSONObject d5212 = d5211.getJSONObject("USD");
                        coin m5211 = new coin();
                        m5211 = gson.fromJson(d5212.toString(), coin.class);
                        arrList.add(m5211);
                    } else if (currency.equals("JPY")){
                        JSONObject d5211 = data.getJSONObject("MTN*");
                        JSONObject d5212 = d5211.getJSONObject("JPY");
                        coin m5211 = new coin();
                        m5211 = gson.fromJson(d5212.toString(), coin.class);
                        arrList.add(m5211);
                    } else if (currency.equals("")){
                        JSONObject d5211 = data.getJSONObject("MTN*");
                        JSONObject d5212 = d5211.getJSONObject("EUR");
                        coin m5211 = new coin();
                        m5211 = gson.fromJson(d5212.toString(), coin.class);
                        arrList.add(m5211);
                    }

                } else if (coins.equals(("BRD"))){
                    if (currency.equals("USD")){
                        JSONObject d5311 = data.getJSONObject("BRD");
                        JSONObject d5312 = d5311.getJSONObject("USD");
                        coin m5311 = new coin();
                        m5311 = gson.fromJson(d5312.toString(), coin.class);
                        arrList.add(m5311);
                    } else if (currency.equals("JPY")){
                        JSONObject d5311 = data.getJSONObject("BRD");
                        JSONObject d5312 = d5311.getJSONObject("JPY");
                        coin m5311 = new coin();
                        m5311 = gson.fromJson(d5312.toString(), coin.class);
                        arrList.add(m5311);
                    } else if (currency.equals("EUR")){
                        JSONObject d5311 = data.getJSONObject("BRD");
                        JSONObject d5312 = d5311.getJSONObject("EUR");
                        coin m5311 = new coin();
                        m5311 = gson.fromJson(d5312.toString(), coin.class);
                        arrList.add(m5311);
                    }

                } else if (coins.equals(("SAN"))){
                    if (currency.equals("USD")){
                        JSONObject d5411 = data.getJSONObject("SAN");
                        JSONObject d5412 = d5411.getJSONObject("USD");
                        coin m5411 = new coin();
                        m5411 = gson.fromJson(d5412.toString(), coin.class);
                        arrList.add(m5411);
                    } else if (currency.equals("JPY")){
                        JSONObject d5411 = data.getJSONObject("SAN");
                        JSONObject d5412 = d5411.getJSONObject("JPY");
                        coin m5411 = new coin();
                        m5411 = gson.fromJson(d5412.toString(), coin.class);
                        arrList.add(m5411);
                    } else if (currency.equals("EUR")){
                        JSONObject d5411 = data.getJSONObject("SAN");
                        JSONObject d5412 = d5411.getJSONObject("EUR");
                        coin m5411 = new coin();
                        m5411 = gson.fromJson(d5412.toString(), coin.class);
                        arrList.add(m5411);
                    }

                } else if (coins.equals(("NAS"))){
                    if (currency.equals("USD")){
                        JSONObject d5511 = data.getJSONObject("NAS");
                        JSONObject d5512 = d5511.getJSONObject("USD");
                        coin m5511 = new coin();
                        m5511 = gson.fromJson(d5512.toString(), coin.class);
                        arrList.add(m5511);
                    } else if (currency.equals("JPY")){
                        JSONObject d5511 = data.getJSONObject("NAS");
                        JSONObject d5512 = d5511.getJSONObject("JPY");
                        coin m5511 = new coin();
                        m5511 = gson.fromJson(d5512.toString(), coin.class);
                        arrList.add(m5511);
                    } else if (currency.equals("EUR")){
                        JSONObject d5511 = data.getJSONObject("NAS");
                        JSONObject d5512 = d5511.getJSONObject("EUR");
                        coin m5511 = new coin();
                        m5511 = gson.fromJson(d5512.toString(), coin.class);
                        arrList.add(m5511);
                    }

                } else if (coins.equals(("GVT"))){
                    if (currency.equals("USD")){
                        JSONObject d5611 = data.getJSONObject("GVT");
                        JSONObject d5612 = d5611.getJSONObject("USD");
                        coin m5611 = new coin();
                        m5611 = gson.fromJson(d5612.toString(), coin.class);
                        arrList.add(m5611);
                    } else if (currency.equals("JPY")){
                        JSONObject d5611 = data.getJSONObject("GVT");
                        JSONObject d5612 = d5611.getJSONObject("JPY");
                        coin m5611 = new coin();
                        m5611 = gson.fromJson(d5612.toString(), coin.class);
                        arrList.add(m5611);
                    } else if (currency.equals("EUR")){
                        JSONObject d5611 = data.getJSONObject("GVT");
                        JSONObject d5612 = d5611.getJSONObject("EUR");
                        coin m5611 = new coin();
                        m5611 = gson.fromJson(d5612.toString(), coin.class);
                        arrList.add(m5611);
                    }

                } else if (coins.equals(("QUN"))){
                    if (currency.equals("USD")){
                        JSONObject d5711 = data.getJSONObject("QUN");
                        JSONObject d5712 = d5711.getJSONObject("USD");
                        coin m5711 = new coin();
                        m5711 = gson.fromJson(d5712.toString(), coin.class);
                        arrList.add(m5711);
                    } else if (currency.equals("JPY")){
                        JSONObject d5711 = data.getJSONObject("QUN");
                        JSONObject d5712 = d5711.getJSONObject("JPY");
                        coin m5711 = new coin();
                        m5711 = gson.fromJson(d5712.toString(), coin.class);
                        arrList.add(m5711);
                    } else if (currency.equals("EUR")){
                        JSONObject d5711 = data.getJSONObject("QUN");
                        JSONObject d5712 = d5711.getJSONObject("EUR");
                        coin m5711 = new coin();
                        m5711 = gson.fromJson(d5712.toString(), coin.class);
                        arrList.add(m5711);
                    }

                } else if (coins.equals(("WTC"))){
                    if (currency.equals("USD")){
                        JSONObject d5811 = data.getJSONObject("WTC");
                        JSONObject d5812 = d5811.getJSONObject("USD");
                        coin m5811 = new coin();
                        m5811 = gson.fromJson(d5812.toString(), coin.class);
                        arrList.add(m5811);
                    } else if (currency.equals("JPY")){
                        JSONObject d5811 = data.getJSONObject("WTC");
                        JSONObject d5812 = d5811.getJSONObject("JPY");
                        coin m5811 = new coin();
                        m5811 = gson.fromJson(d5812.toString(), coin.class);
                        arrList.add(m5811);
                    } else if (currency.equals("EUR")){
                        JSONObject d5811 = data.getJSONObject("WTC");
                        JSONObject d5812 = d5811.getJSONObject("EUR");
                        coin m5811 = new coin();
                        m5811 = gson.fromJson(d5812.toString(), coin.class);
                        arrList.add(m5811);
                    }

                } else if (coins.equals(("FCT"))){
                    if (currency.equals("USD")){
                        JSONObject d5911 = data.getJSONObject("FCT");
                        JSONObject d5912 = d5911.getJSONObject("USD");
                        coin m5911 = new coin();
                        m5911 = gson.fromJson(d5912.toString(), coin.class);
                        arrList.add(m5911);
                    } else if (currency.equals("JPY")){
                        JSONObject d5911 = data.getJSONObject("FCT");
                        JSONObject d5912 = d5911.getJSONObject("JPY");
                        coin m5911 = new coin();
                        m5911 = gson.fromJson(d5912.toString(), coin.class);
                        arrList.add(m5911);
                    } else if (currency.equals("EUR")){
                        JSONObject d5911 = data.getJSONObject("FCT");
                        JSONObject d5912 = d5911.getJSONObject("EUR");
                        coin m5911 = new coin();
                        m5911 = gson.fromJson(d5912.toString(), coin.class);
                        arrList.add(m5911);
                    }

                } else if (coins.equals(("CDT"))){
                    if (currency.equals("USD")){
                        JSONObject d6011 = data.getJSONObject("CDT");
                        JSONObject d6012 = d6011.getJSONObject("USD");
                        coin m6011 = new coin();
                        m6011 = gson.fromJson(d6012.toString(), coin.class);
                        arrList.add(m6011);
                    } else if (currency.equals("JPY")){
                        JSONObject d6011 = data.getJSONObject("CDT");
                        JSONObject d6012 = d6011.getJSONObject("JPY");
                        coin m6011 = new coin();
                        m6011 = gson.fromJson(d6012.toString(), coin.class);
                        arrList.add(m6011);
                    } else if (currency.equals("EUR")){
                        JSONObject d6011 = data.getJSONObject("CDT");
                        JSONObject d6012 = d6011.getJSONObject("EUR");
                        coin m6011 = new coin();
                        m6011 = gson.fromJson(d6012.toString(), coin.class);
                        arrList.add(m6011);
                    }

                } else if (coins.equals(("RCN"))){
                    if (currency.equals("USD")){
                        JSONObject d6111 = data.getJSONObject("RCN");
                        JSONObject d6112 = d6111.getJSONObject("USD");
                        coin m6111 = new coin();
                        m6111 = gson.fromJson(d6112.toString(), coin.class);
                        arrList.add(m6111);
                    } else if (currency.equals("JPY")){
                        JSONObject d6111 = data.getJSONObject("RCN");
                        JSONObject d6112 = d6111.getJSONObject("JPY");
                        coin m6111 = new coin();
                        m6111 = gson.fromJson(d6112.toString(), coin.class);
                        arrList.add(m6111);
                    } else if (currency.equals("EUR")){
                        JSONObject d6111 = data.getJSONObject("RCN");
                        JSONObject d6112 = d6111.getJSONObject("EUR");
                        coin m6111 = new coin();
                        m6111 = gson.fromJson(d6112.toString(), coin.class);
                        arrList.add(m6111);
                    }

                }
                p.error = false;
                p.o = arrList;
            } else {
                p.error = true;
            }
        } else {
            p.error = true;
        }
        return p;
    }







    //---------------------------------------------------GET_ALERT_API-------------------------------------------------------------

    public static ParsedResponse apiGetAlert(Context mContext, String user_id) throws IOException, JSONException {
        ParsedResponse p = new ParsedResponse();

        if (utils.isNetworkAvailable(mContext)) {

            RequestBody formBody = new FormBody.Builder()
                    .add("action", "get-alert-coin")
                    .add("user_id", user_id)

                    .build();

            String res = Service.getSoapResponsePost("api.php",
                    formBody);

            if (!TextUtils.isEmpty(res)) {
                JSONObject mainobj = new JSONObject(res);
                if (mainobj.getInt("status") == 200) {
                    JSONArray data1 = mainobj.getJSONArray("data");
                    Gson gson = new Gson();
                    ArrayList<Currency> arrList = new ArrayList<>();
                    for (int i = 0; i < data1.length(); i++) {
                        JSONObject c = data1.getJSONObject(i);
                        Currency model = new Currency();
                        model = gson.fromJson(c.toString(), Currency.class);
                        arrList.add(model);

                        p.error = false;
                        p.o = arrList;
                    }
                } else {
                    p.error = true;
                    p.o = mainobj.getString("message");
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
