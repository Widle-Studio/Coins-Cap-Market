package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.widle.coinscap.Db.DatabaseHandler;
import com.widle.coinscap.Price_Alert1_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.Currency;

public class Alert_Adapter extends RecyclerView.Adapter<Alert_Adapter.ViewHolder> {

    List<Alert_Adapter.ViewHolder> holders = new ArrayList<Alert_Adapter.ViewHolder>();
    ArrayList<Currency> mArrayList = new ArrayList<>();
    Context context;
    private static DatabaseHandler db;


    public Alert_Adapter(Context context, ArrayList<Currency> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public Alert_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_alert, parent, false);
        Alert_Adapter.ViewHolder viewHolder = new Alert_Adapter.ViewHolder(view);
//        db = new DatabaseHandler(context);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Alert_Adapter.ViewHolder holder, int position) {
        final Currency current = mArrayList.get(position);

        holder.tv_coin.setText(current.coin_symbol);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date result;
        try {
            result = df.parse(mArrayList.get(position).date);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
            holder.tv_above.setText(sdf.format(result));
            holder.tv_below.setText(sdf1.format(result));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_coin, lbl_above, tv_above, lbl_below, tv_below;

//        private ImageView iv_delete;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_coin = itemView.findViewById(R.id.tv_coin);
            lbl_above = itemView.findViewById(R.id.lbl_above);
            tv_above = itemView.findViewById(R.id.tv_above);
            lbl_below = itemView.findViewById(R.id.lbl_below);
            tv_below = itemView.findViewById(R.id.tv_below);

//            iv_delete = itemView.findViewById(R.id.iv_delete);

            Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");

            tv_coin.setTypeface(type2);
            lbl_above.setTypeface(type2);
            tv_above.setTypeface(type1);
            lbl_below.setTypeface(type2);
            tv_below.setTypeface(type1);

        }
    }
}
