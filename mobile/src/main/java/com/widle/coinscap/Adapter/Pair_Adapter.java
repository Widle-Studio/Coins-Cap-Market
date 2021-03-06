package com.widle.coinscap.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.coin;


public class Pair_Adapter extends BaseAdapter {

    ArrayList<coin> mArrayList = new ArrayList<>();
    Context context;
    private static LayoutInflater inflater = null;

    public Pair_Adapter( Context context, ArrayList<coin> mPairArrayList) {
        this.context = context;
        this.mArrayList = mPairArrayList;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.row_items_pair, null);
        }

        TextView tv_pair = (TextView) convertView.findViewById(R.id.tv_pair);

        Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_pair.setTypeface(type1);

        tv_pair.setText(mArrayList.get(i).toSymbol);

        return convertView;
    }
}
