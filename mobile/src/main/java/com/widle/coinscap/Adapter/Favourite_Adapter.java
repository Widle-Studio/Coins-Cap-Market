package com.widle.coinscap.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Coins_Activity;
import com.widle.coinscap.Coins_Detail_Activity;
import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Fragment.Coins_Fragment;
import com.widle.coinscap.Price_Alert1_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.Utils.Model.Fav_Coins;
import com.widle.coinscap.Utils.Model.coin;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

/**
 * Created by imperial-web1 on 9/3/18.
 */

public class Favourite_Adapter extends RecyclerView.Adapter<Favourite_Adapter.ViewHolder>   {
    List<ViewHolder> holders = new ArrayList<ViewHolder>();
    ArrayList<Fav_Coins> mArrayList = new ArrayList<>();
    ArrayList<coin> mCoinArrayList = new ArrayList<>();
    Context context;
    private static DatabaseHandler db;
    SharedPreferences preferences;
    private String device_id = "", user_id = "";
    public ProgressDialog mProgressDialog;


    public Favourite_Adapter(Context context, ArrayList<Fav_Coins> mArrayList, ArrayList<coin> mCoinArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
        this.mCoinArrayList = mCoinArrayList;
    }

    @Override
    public Favourite_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_favourite, parent, false);
        preferences = context.getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);
        device_id = preferences.getString(General.PREFS_Device_id, "");

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        ViewHolder viewHolder = new ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Favourite_Adapter.ViewHolder holder, int position) {
        final Fav_Coins current = mArrayList.get(position);
        final coin Coincurrent = mCoinArrayList.get(position);



        holder.tv_btc_name.setText(Coincurrent.NAME);
        holder.tv_btc_sf.setText(Coincurrent.SYMBOL);
        holder.tv_btc_mc.setText(current.getUSD().getMKTCAP());
        holder.tv_btc_price.setText(current.getUSD().getPRICE());

        if (current.getUSD().getCHANGEPCT24HOUR().contains("-")) {
            holder.tv_btc_pct.setText(current.getUSD().getCHANGE24HOUR()+ " (" + current.getUSD().getCHANGEPCT24HOUR() + "%)");
            holder.tv_btc_pct.setTextColor(Color.parseColor("#FF0000"));

        } else {
            holder.tv_btc_pct.setText(current.getUSD().getCHANGE24HOUR() + " (" + current.getUSD().getCHANGEPCT24HOUR() + "%)");
            holder.tv_btc_pct.setTextColor(Color.parseColor("#008000"));
        }

        db = new DatabaseHandler(context);

        holder.iv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferences.getBoolean(General.PREFS_IsLogin, false)) {
                    if (utils.isNetworkAvailable(context)) {
                        new DeleteAlertTask(Coincurrent.SYMBOL).execute();
                        db.Deleteall(Coincurrent.SYMBOL);
                        Intent intent = new Intent(context, Coins_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    } else {
                        utils.showAlertMessage(context, context.getString(R.string.err_no_internet));
                    }
                } else {
//                    loginPopupViewControls();
                    utils.showAlertMessage(context, context.getString(R.string.err_login));
                }
                db.Deleteall(Coincurrent.SYMBOL);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (utils.isNetworkAvailable(context)) {
                    Intent intent = new Intent(context, Coins_Detail_Activity.class);
                    intent.putExtra("name", Coincurrent.NAME);
                    intent.putExtra("symbol", Coincurrent.SYMBOL);
                    intent.putExtra("price", current.getUSD().getPRICE());
                    intent.putExtra("volume", current.getUSD().getCHANGE24HOUR());
                    intent.putExtra("market_cap", current.getUSD().getMKTCAP());
                    intent.putExtra("change24hr", current.getUSD().getCHANGEPCT24HOUR());
                    intent.putExtra("changeday", current.getUSD().getCHANGEPCTDAY());
                    intent.putExtra("fav_status", "0");
                    context.startActivity(intent);
//                    Intent intent1 = new Intent(context, Coins_Activity.class);
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent1);
//                    ((Activity)context).finish();

                } else {
                    utils.showAlertMessage(context, context.getString(R.string.err_no_internet));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_btc_name, tv_btc_sf, tv_btc_mc, tv_btc_price, tv_btc_pct;

        private ImageView iv_fav;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_btc_name = itemView.findViewById(R.id.tv_btc_name);
            tv_btc_sf = itemView.findViewById(R.id.tv_btc_sf);
            tv_btc_mc = itemView.findViewById(R.id.tv_btc_mc);
            tv_btc_price = itemView.findViewById(R.id.tv_btc_price);
            tv_btc_pct = itemView.findViewById(R.id.tv_btc_pct);
            iv_fav = itemView.findViewById(R.id.iv_fav);

            Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");

            tv_btc_name.setTypeface(type1);
            tv_btc_sf.setTypeface(type1);
            tv_btc_mc.setTypeface(type1);
            tv_btc_price.setTypeface(type);
            tv_btc_pct.setTypeface(type1);

        }
    }



    public class DeleteAlertTask extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, coin_symbol = "";


        public DeleteAlertTask(String coin_symbol) {
            this.coin_symbol = coin_symbol;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            user_id = preferences.getString(General.PREFS_User_id, "");
            try {
                ParsedResponse p = Service.apiDeleteAlert(context, user_id, coin_symbol, "1", "0", device_id);
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
//            mProgressDialog.dismiss();

            if (!error) {
//                SharedPreferences.Editor e = preferences.edit();
//                e.putBoolean(General.PREFS_IsLogin, true);
//                e.apply();
            } else {
                utils.showAlertMessage(context, msg);
            }
        }
    }
}
