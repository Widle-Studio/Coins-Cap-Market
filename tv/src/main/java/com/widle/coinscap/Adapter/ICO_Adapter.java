package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.widle.coinscap.Model.ICO;
import com.widle.coinscap.R;

public class ICO_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ICO> mArrayList = new ArrayList<>();
    private Context mContext;
    List<Podcast_Detail_Adapter.ViewHolder> holders = new ArrayList<Podcast_Detail_Adapter.ViewHolder>();

    public ICO_Adapter(Context mContext, ArrayList<ICO> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ICO_Adapter.RecyclerViewHolder(View.inflate(mContext, R.layout.item_rv_ico, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ICO current = mArrayList.get(position);
        final ICO_Adapter.RecyclerViewHolder viewHolder = (ICO_Adapter.RecyclerViewHolder) holder;
        viewHolder.tv_title.setText(current.name);
        viewHolder.tv_desc.setText(current.description);
        GradientDrawable drawable = (GradientDrawable) viewHolder.mFrameLayout.getBackground();
        drawable.setColor(ContextCompat.getColor(mContext, ContantUtil.getRandColor()));

        Glide.with(mContext)
                .load(current.image)
                .into(viewHolder.iv_icon);

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
            viewHolder.tv_start_time.setText("Start Time : " + str);
            viewHolder.tv_end_time.setText("End Time : " + str1);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        FrameLayout mFrameLayout;
        TextView tv_title, tv_desc, tv_start_time, tv_end_time;
        ImageView iv_icon;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_start_time = itemView.findViewById(R.id.tv_start_time);
            tv_end_time = itemView.findViewById(R.id.tv_end_time);
            mFrameLayout = (FrameLayout) itemView.findViewById(R.id.fl_main_layout);
            iv_icon = itemView.findViewById(R.id.iv_icon);

            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            Typeface type1 = Typeface.createFromAsset(mContext.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(mContext.getAssets(), "Fonts/Titillium-LightUpright.otf");

            tv_title.setTypeface(type1);
            tv_desc.setTypeface(type2);
            tv_start_time.setTypeface(type2);
            tv_end_time.setTypeface(type2);
        }
    }
}
