package com.widle.coinscap.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import com.widle.coinscap.Adapter.Coins_Adapter;
import com.widle.coinscap.Adapter.News_Adapter;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Profile_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Soket.Server;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.NewCoins;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

public class Coin_Fragment extends Fragment {

    private static DatabaseHandler db;

    SharedPreferences preferences;

    public ProgressDialog mProgressDialog;

    private String pro_pic = "", serverIpAddress = "";

    private boolean connected = false;

    private TextView tv_app_name;

    private CircleImageView iv_profilepic;

    Server server;

    ListView lv_coin;

    ArrayList<NewCoins> mArrayList = new ArrayList<>();

    private Coins_Adapter coins_adapter;

    private static final String JSON_URL = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC&tsyms=" + "USD" + "&extraParams=your_app_name";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin, container, false);

        preferences = getActivity().getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        pro_pic = preferences.getString(General.PREFS_Picture, "");

        db = new DatabaseHandler(getActivity());

        server = new Server();

        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);


        init(view);

        Thread cThread = new Thread(new ClientThread());
        cThread.start();


        return view;
    }

    public void init(View view) {


        tv_app_name = view.findViewById(R.id.tv_app_name);
        lv_coin = view.findViewById(R.id.lv_coin);
        iv_profilepic = view.findViewById(R.id.iv_profilepic);


        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_app_name.setTypeface(type2);


        iv_profilepic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(getActivity(), Profile_Activity.class);
                startActivity(intent);
                return false;
            }
        });

        if (pro_pic.equals("")) {
        } else {
            String url = "https://widle.studio/coinscapmarket/API/profile/";
            Glide.with(getActivity()).load(url + pro_pic).into(iv_profilepic);
        }
    }


    public class ClientThread implements Runnable {

        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
                Log.d("ClientActivity", "C: Connecting...");
                Socket socket = new Socket(serverAddr, Server.socketServerPORT);
                connected = true;
                while (connected) {
                    try {
                        Log.d("ClientActivity", "C: Sending command.");
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                                .getOutputStream())), true);
                        // WHERE YOU ISSUE THE COMMANDS
                        out.println("Hey Server!");
                        Log.d("ClientActivity", "C: Sent.");

                        new apiGet_Coins().execute();


                    } catch (Exception e) {
                        Log.e("ClientActivity", "S: Error", e);
                    }
                }
                socket.close();
                Log.d("ClientActivity", "C: Closed.");
            } catch (Exception e) {
                Log.e("ClientActivity", "C: Error", e);
                connected = false;
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
                String Baseurl = "http://coincap.io/front";
                ParsedResponse p = Service.apiGetCoins(getActivity(), Baseurl);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<NewCoins>) p.o;
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
                coins_adapter = new Coins_Adapter(getActivity(), mArrayList);
                lv_coin.setAdapter(coins_adapter);

            } else {
                utils.showAlertMessage(getActivity(), msg);
            }
        }
    }
}

