package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.widle.coinscap.ICO_Link_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.ICO;

public class ICO_Live_Adapter extends RecyclerView.Adapter<ICO_Live_Adapter.ViewHolder> {

    List<ICO_Live_Adapter.ViewHolder> holders = new ArrayList<ICO_Live_Adapter.ViewHolder>();
    ArrayList<ICO> mArrayList = new ArrayList<>();
    Context context;
    String type;

    public ICO_Live_Adapter(Context context, ArrayList<ICO> mArrayList, String type) {
        this.context = context;
        this.mArrayList = mArrayList;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_ico_live, parent, false);
        ICO_Live_Adapter.ViewHolder viewHolder = new ICO_Live_Adapter.ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ICO current = mArrayList.get(position);

        if (type.equals("finished")){
            holder.ll_symbol.setVisibility(View.VISIBLE);
            holder.tv_symbol.setText(current.coin_symbol);
            holder.tv_price.setText("$"+current.price_usd);
        } else{
            holder.ll_symbol.setVisibility(View.GONE);
        }

        holder.tv_title.setText(current.name);
        holder.tv_sub_title.setText(current.description);
        holder.tv_start.setText(current.start_time);
        holder.tv_end.setText(current.end_time);


        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        Date date1 = null;
        String str = null;
        String str1 = null;

        try {
            date = inputFormat.parse(current.start_time);
            date1 = inputFormat.parse(current.start_time);
            str = outputFormat.format(date);
            str1 = outputFormat.format(date1);
            holder.tv_start.setText("Start Time : " + str);
            holder.tv_end.setText("End Time : " + str1);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        Glide.with(context)
                .load(current.image)
                .into(holder.iv_image);


        holder.tv_weblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ICO_Link_Activity.class);
                intent.putExtra("name", "Website");
                intent.putExtra("link", current.website_link);
                context.startActivity(intent);
            }
        });

        holder.tv_icolink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ICO_Link_Activity.class);
                intent.putExtra("name", "ICO Watch List");
                intent.putExtra("link", current.icowatchlist_url);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_sub_title, tv_start, tv_end, tv_weblink, tv_icolink, tv_symbol, tv_price;

        private ImageView iv_image;

        private LinearLayout ll_symbol;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_sub_title = itemView.findViewById(R.id.tv_sub_title);
            tv_weblink = itemView.findViewById(R.id.tv_weblink);
            tv_icolink = itemView.findViewById(R.id.tv_icolink);
            tv_start = itemView.findViewById(R.id.tv_start);
            tv_end = itemView.findViewById(R.id.tv_end);
            ll_symbol = itemView.findViewById(R.id.ll_symbol);
            tv_symbol = itemView.findViewById(R.id.tv_symbol);
            tv_price = itemView.findViewById(R.id.tv_price);

            iv_image = itemView.findViewById(R.id.iv_image);


            Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");

            tv_title.setTypeface(type1);
            tv_sub_title.setTypeface(type1);
            tv_start.setTypeface(type1);
            tv_end.setTypeface(type1);
            tv_weblink.setTypeface(type1);
            tv_icolink.setTypeface(type1);
            tv_symbol.setTypeface(type1);
            tv_price.setTypeface(type1);
        }
    }
}
