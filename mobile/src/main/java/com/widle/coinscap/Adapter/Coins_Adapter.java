package com.widle.coinscap.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.NewCoins;
import com.widle.coinscap.Utils.Model.coin;

/**
 * Created by imperial-web on 2/26/2018.
 */

public class Coins_Adapter extends BaseAdapter{

    ArrayList<NewCoins> mArrayList = new ArrayList<>();
    Context context;
    private static LayoutInflater inflater = null;

    public Coins_Adapter( Context context, ArrayList<NewCoins> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return mArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.row_items_coins, null);
        }

        TextView tv_btc_name = (TextView) convertView.findViewById(R.id.tv_btc_name);
        TextView tv_btc_sf = (TextView) convertView.findViewById(R.id.tv_btc_sf);
        TextView tv_btc_mc = (TextView) convertView.findViewById(R.id.tv_btc_mc);
        TextView tv_btc_price = (TextView) convertView.findViewById(R.id.tv_btc_price);
        TextView tv_btc_pct = (TextView) convertView.findViewById(R.id.tv_btc_pct);
        ImageView iv_un_fav = (ImageView) convertView.findViewById(R.id.iv_un_fav);
        ImageView iv_fav = (ImageView) convertView.findViewById(R.id.iv_fav);




        Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_btc_name.setTypeface(type1);
        tv_btc_sf.setTypeface(type1);
        tv_btc_mc.setTypeface(type1);
        tv_btc_price.setTypeface(type);
        tv_btc_pct.setTypeface(type1);

        if (position == 0){
            tv_btc_name.setText("Bitcoin");
            tv_btc_sf.setText("BTC");
        } else if (position == 1){
            tv_btc_name.setText("Ethereum");
            tv_btc_sf.setText("ETH");
        }


        tv_btc_mc.setText("M.C:" + mArrayList.get(position).price);
        tv_btc_price.setText(mArrayList.get(position).price);
//        if (mArrayList.get(position).CHANGEPCT24HOUR.contains("-")) {
//            tv_btc_pct.setText(mArrayList.get(position).CHANGE24HOUR + " (" + mArrayList.get(position).CHANGEPCT24HOUR + "%)");
//            tv_btc_pct.setTextColor(Color.parseColor("#FF0000"));
//
//        } else {
//            tv_btc_pct.setText(mArrayList.get(position).CHANGE24HOUR + " (" + mArrayList.get(position).CHANGEPCT24HOUR + "%)");
//            tv_btc_pct.setTextColor(Color.parseColor("#008000"));
//        }

        return convertView;
    }


}
