package com.widle.coinscap.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Model.coin;
import com.widle.coinscap.R;

public class Exchange_Adapter extends RecyclerView.Adapter<Exchange_Adapter.ViewHolder>  {

    List<Exchange_Adapter.ViewHolder> holders = new ArrayList<Exchange_Adapter.ViewHolder>();
    ArrayList<coin> mArrayList = new ArrayList<>();
    Context context;

    public Exchange_Adapter(Context context, ArrayList<coin> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public Exchange_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_exchange, parent, false);
        return new Exchange_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Exchange_Adapter.ViewHolder holder, int position) {

        holder.tv_market.setText(mArrayList.get(position).MARKET);
        holder.tv_price.setText(mArrayList.get(position).PRICE);
        holder.tv_last_volume.setText("Last Volume : " + mArrayList.get(position).LASTVOLUME);
        holder.tv_last_raid_id.setText("Last Traid Id : " + mArrayList.get(position).LASTTRADEID);
        holder.tv_volume24hour.setText("Volume24H : " + mArrayList.get(position).VOLUME24HOUR);
        holder.tv_open.setText("Open24H : " + mArrayList.get(position).OPEN24HOUR);
        holder.tv_high.setText("High24H : " + mArrayList.get(position).HIGH24HOUR);
        holder.tv_low.setText("Low24H : " + mArrayList.get(position).LOW24HOUR);

    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_market, tv_price, tv_last_volume, tv_last_raid_id, tv_volume24hour, tv_open, tv_high, tv_low;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_market = (TextView) itemView.findViewById(R.id.tv_market);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_last_volume = (TextView) itemView.findViewById(R.id.tv_last_volume);
            tv_last_raid_id = (TextView) itemView.findViewById(R.id.tv_last_raid_id);
            tv_volume24hour = (TextView) itemView.findViewById(R.id.tv_volume24hour);
            tv_open = (TextView) itemView.findViewById(R.id.tv_open);
            tv_high = (TextView) itemView.findViewById(R.id.tv_high);
            tv_low = (TextView) itemView.findViewById(R.id.tv_low);

            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");
            tv_market.setTypeface(type1);
            tv_price.setTypeface(type1);
            tv_last_volume.setTypeface(type1);
            tv_last_raid_id.setTypeface(type1);
            tv_volume24hour.setTypeface(type1);
            tv_open.setTypeface(type1);
            tv_high.setTypeface(type1);
            tv_low.setTypeface(type1);
        }
    }
}
