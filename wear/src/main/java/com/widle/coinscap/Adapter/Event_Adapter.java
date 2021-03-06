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
import java.util.List;
import java.util.TimeZone;

import com.widle.coinscap.Model.Event;
import com.widle.coinscap.R;

public class Event_Adapter extends RecyclerView.Adapter<Event_Adapter.ViewHolder> {
    List<Event_Adapter.ViewHolder> holders = new ArrayList<Event_Adapter.ViewHolder>();
    ArrayList<Event> mArrayList = new ArrayList<>();
    Context context;

    public Event_Adapter( Context context, ArrayList<Event> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public Event_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_event, parent, false);
        Event_Adapter.ViewHolder viewHolder = new Event_Adapter.ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Event_Adapter.ViewHolder holder, int position) {
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


    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_coin, tv_date, tv_title, tv_desc;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_coin = (TextView) itemView.findViewById(R.id.tv_coin);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);

            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");

            tv_title.setTypeface(type1);
            tv_date.setTypeface(type1);
            tv_desc.setTypeface(type2);
            tv_coin.setTypeface(type1);

        }
    }
}
