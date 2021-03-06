package com.widle.coinscap.Adapter;

import android.content.Context;
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

import com.widle.coinscap.Model.ICO;
import com.widle.coinscap.R;

public class ICO_Adapter extends RecyclerView.Adapter<ICO_Adapter.ViewHolder>  {

    List<ICO_Adapter.ViewHolder> holders = new ArrayList<ICO_Adapter.ViewHolder>();
    ArrayList<ICO> mArrayList = new ArrayList<>();
    Context context;
    private String type;

    public ICO_Adapter(Context context, ArrayList<ICO> mArrayList, String type) {
        this.context = context;
        this.mArrayList = mArrayList;
        this.type = type;
    }

    @Override
    public ICO_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_ico, parent, false);
        ICO_Adapter.ViewHolder viewHolder = new ICO_Adapter.ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ICO_Adapter.ViewHolder holder, int position) {
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
            holder.tv_start.setText("Start Time:" + str);
            holder.tv_end.setText("End Time:" + str1);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        Glide.with(context)
                .load(current.image)
                .into(holder.iv_image);


    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_symbol, tv_sub_title, tv_start, tv_end, tv_price;
        private ImageView iv_image;
        private LinearLayout ll_symbol;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_symbol = itemView.findViewById(R.id.tv_symbol);
            tv_sub_title = itemView.findViewById(R.id.tv_sub_title);
            tv_start = itemView.findViewById(R.id.tv_start);
            tv_end = itemView.findViewById(R.id.tv_end);
            tv_price = itemView.findViewById(R.id.tv_price);
            ll_symbol = itemView.findViewById(R.id.ll_symbol);

            iv_image = itemView.findViewById(R.id.iv_image);

            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");

            tv_title.setTypeface(type1);
            tv_sub_title.setTypeface(type1);
            tv_start.setTypeface(type1);
            tv_end.setTypeface(type1);
            tv_symbol.setTypeface(type1);
            tv_price.setTypeface(type1);
        }
    }
}
