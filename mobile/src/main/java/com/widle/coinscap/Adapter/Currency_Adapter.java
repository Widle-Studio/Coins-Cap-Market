package com.widle.coinscap.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.Currency;

/**
 * Created by imperial-web1 on 28/2/18.
 */

public class Currency_Adapter extends BaseAdapter {
    ArrayList<Currency> mArrayList = new ArrayList<>();
    Context context;
    private static LayoutInflater inflater = null;

    public Currency_Adapter(Context context, ArrayList<Currency> mCurrencyArrayList) {
        this.context = context;
        this.mArrayList = mCurrencyArrayList;
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
    public long getItemId(int position) {
        return mArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.row_items_currency, null);
        }

        TextView tv_currency = (TextView) convertView.findViewById(R.id.tv_currency);
        Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        tv_currency.setTypeface(type);

        tv_currency.setText(mArrayList.get(position).currency_name + "(" + mArrayList.get(position).currency_code + ")");


        return convertView;
    }
}
