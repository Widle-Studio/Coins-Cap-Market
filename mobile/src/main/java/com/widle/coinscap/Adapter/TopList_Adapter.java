package com.widle.coinscap.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.coin;

public class TopList_Adapter extends RecyclerView.Adapter<TopList_Adapter.ViewHolder> {
    ArrayList<coin> mArrayList = new ArrayList<>();
    Context context;
    String currency;

    public TopList_Adapter(Context context, ArrayList<coin> mArrayList, String currency) {
        this.context = context;
        this.mArrayList = mArrayList;
        this.currency = currency;
    }

    @Override
    public TopList_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_toplist_card, parent, false);
        return new TopList_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TopList_Adapter.ViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("0.0000");
//        double number = mArrayList.get(position).TotalVolume24H;
//        String volume1 = df.format(number);

        holder.tv_coins.setText(mArrayList.get(position).CurrencyFrom);
        holder.tv_supply.setText("Supply : " + mArrayList.get(position).Supply + " "+ currency);
        holder.tv_volume.setText("VOLUME24H : " + mArrayList.get(position).TotalVolume24H + " " + currency);

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_coins, tv_supply, tv_volume;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_coins = (TextView) itemView.findViewById(R.id.tv_coins);
            tv_supply = (TextView) itemView.findViewById(R.id.tv_supply);
            tv_volume = (TextView) itemView.findViewById(R.id.tv_volume);

            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");
            tv_coins.setTypeface(type1);
            tv_supply.setTypeface(type1);
            tv_volume.setTypeface(type1);


        }
    }
}
