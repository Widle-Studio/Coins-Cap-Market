package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.widle.coinscap.Exchange_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.coin;

public class Exchange_Adapter extends  RecyclerView.Adapter<Exchange_Adapter.ViewHolder> {
    ArrayList<coin> mArrayList = new ArrayList<>();
    Context context;
    String market = "", coins = "", currency = "";

    public Exchange_Adapter(  Context context, ArrayList<coin> mArrayList, String market, String coins, String currency) {
        this.context = context;
        this.mArrayList = mArrayList;
        this.market = market;
        this.coins = coins;
        this.currency = currency;
    }

    @Override
    public Exchange_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_exchange_card, parent, false);
        return new Exchange_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Exchange_Adapter.ViewHolder holder, final int position) {

        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat df1 = new DecimalFormat("000.00");
        DecimalFormat df2 = new DecimalFormat("000.00");
        DecimalFormat df3 = new DecimalFormat("00.00");
        double number = Double.parseDouble(mArrayList.get(position).LASTVOLUME);
        double number1 = Double.parseDouble(mArrayList.get(position).VOLUME24HOUR);
        double number2 = Double.parseDouble(mArrayList.get(position).OPEN24HOUR);
        double number3 = Double.parseDouble(mArrayList.get(position).HIGH24HOUR);
        double number4 = Double.parseDouble(mArrayList.get(position).LOW24HOUR);
        double number5 = Double.parseDouble(mArrayList.get(position).CHANGE24HOUR);
        double number6 = Double.parseDouble(mArrayList.get(position).CHANGEPCT24HOUR);
        final String volume = df.format(number);
        String volume1 = df1.format(number1);
        String open = df3.format(number2);
        String high = df3.format(number3);
        String low = df3.format(number4);
        String change = df2.format(number5);
        String changepct = df3.format(number6);

        holder.tv_market.setText(mArrayList.get(position).MARKET);
        holder.tv_price.setText(coins +": "+ mArrayList.get(position).PRICE +" "+ currency);
        holder.tv_last_volume.setText("Last Volume : " + volume +" "+ currency);
        holder.tv_volume24hour.setText("Volume24H : " + volume1 +" "+ currency);
        holder.tv_open.setText("Open24H : " + open);
        holder.tv_high.setText("High24H : " + high);
        holder.tv_low.setText("Low24H : " + low);
        if (mArrayList.get(position).CHANGEPCT24HOUR.contains("-")) {
            holder.tv_difference.setText("Change24h: "+ change +" "+ currency+" (" + changepct + "%)");
            holder.tv_difference.setTextColor(Color.parseColor("#FF0000"));

        } else {
            holder.tv_difference.setText("Change24h: "+ change +" "+ currency+" (" + changepct + "%)");
            holder.tv_difference.setTextColor(Color.parseColor("#008000"));
        }

        final String market = "Exchange Platform: " + mArrayList.get(position).MARKET;
        final String coin = coins +": "+ mArrayList.get(position).PRICE +" "+ currency;
        final String change24 = "Change24h: "+ change +" "+ currency+" (" + changepct + "%)";
        final String openn = "Open24H: " + open + " " + currency;
        final String highh = "High24H: " + high + " " + currency;
        final String loww = "Low24H: " + low + " " + currency;
        final String vol24 = "24h Volume: " + volume1 +" "+ currency;


        holder.iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String sAux = "\n" + market +"\n\n";
                    String sBux = coin + "\n";
                    String sCux = change24 + "\n";
                    String sDux = openn + "\n";
                    String sEux = highh + "\n";
                    String sFux = loww + "\n";
                    String sGux = vol24 + "\n\n";
                    sAux = sAux + sBux + sCux + sDux + sEux + sFux + sGux + "Check out more Cryptocurrency Live Price, News, Podcast, Event, and more on CoinsCapMarket App - https://goo.gl/mFXBDn \n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    context.startActivity(Intent.createChooser(i, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_market, tv_price, tv_difference, tv_last_volume, tv_volume24hour, tv_open, tv_high, tv_low;

        private ImageView iv_share;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_market = (TextView) itemView.findViewById(R.id.tv_market);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_difference = (TextView) itemView.findViewById(R.id.tv_difference);
            tv_last_volume = (TextView) itemView.findViewById(R.id.tv_last_volume);
            tv_volume24hour = (TextView) itemView.findViewById(R.id.tv_volume24hour);
            tv_open = (TextView) itemView.findViewById(R.id.tv_open);
            tv_high = (TextView) itemView.findViewById(R.id.tv_high);
            tv_low = (TextView) itemView.findViewById(R.id.tv_low);
            iv_share = itemView.findViewById(R.id.iv_share);

            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");
            tv_market.setTypeface(type1);
            tv_price.setTypeface(type1);
            tv_difference.setTypeface(type1);
            tv_last_volume.setTypeface(type1);
            tv_volume24hour.setTypeface(type1);
            tv_open.setTypeface(type1);
            tv_high.setTypeface(type1);
            tv_low.setTypeface(type1);

        }
    }
}
