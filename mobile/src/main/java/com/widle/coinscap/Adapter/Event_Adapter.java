package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import com.widle.coinscap.Advertise_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.Event;
import com.widle.coinscap.Utils.utils;

public class Event_Adapter extends RecyclerView.Adapter<Event_Adapter.ViewHolder> {

    ArrayList<Event> mArrayList = new ArrayList<>();
    Context context;
    utils util;

    public Event_Adapter(Context context, ArrayList<Event> mArrayList) {
        this.mArrayList = mArrayList;
        this.context = context;
    }

    @Override
    public Event_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_event_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Event_Adapter.ViewHolder holder, final int position) {

        holder.tv_coin.setText(mArrayList.get(position).name + " (" + mArrayList.get(position).symbol + ")");
        holder.tv_title.setText(mArrayList.get(position).title);
        holder.tv_desc.setText(mArrayList.get(position).description);


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        Date result;
        try {
            result = df.parse(mArrayList.get(position).date_event);
            System.out.println("date:"+result); //prints date in current locale
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println(sdf.format(result)); //prints date in the format sdf
            holder.tv_date.setText(sdf.format(result));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tv_proof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                utils.showAlertMessage(context, context.getString(R.string.err_no_internet));
                util = new utils();
                util.setupDialog(context, mArrayList.get(position).proof);
            }
        });

        holder.tv_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Advertise_Activity.class);
                intent.putExtra("title", mArrayList.get(position).title);
                intent.putExtra("url", mArrayList.get(position).source);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Advertise_Activity.class);
                intent.putExtra("title", mArrayList.get(position).title);
                intent.putExtra("url", mArrayList.get(position).source);
                context.startActivity(intent);
            }
        });

        holder.tv_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Advertise_Activity.class);
                intent.putExtra("title", mArrayList.get(position).title);
                intent.putExtra("url", mArrayList.get(position).source);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_date, tv_desc, tv_proof, tv_source, tv_coin;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            tv_proof = (TextView) itemView.findViewById(R.id.tv_proof);
            tv_source = (TextView) itemView.findViewById(R.id.tv_source);
            tv_coin = (TextView) itemView.findViewById(R.id.tv_coin);

            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");
            tv_title.setTypeface(type1);
            tv_date.setTypeface(type1);
            tv_desc.setTypeface(type2);
            tv_proof.setTypeface(type1);
            tv_source.setTypeface(type1);
            tv_coin.setTypeface(type1);
        }
    }
}
