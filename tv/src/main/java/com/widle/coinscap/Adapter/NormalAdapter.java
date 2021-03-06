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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.General;
import com.widle.coinscap.VideoPlayerActivity;


public class NormalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    List<Podcast_Detail_Adapter.ViewHolder> holders = new ArrayList<Podcast_Detail_Adapter.ViewHolder>();
    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();
    SharedPreferences preferences;


    public NormalAdapter(Context context, ArrayList<PodcastChanel> mArrayList) {
        mContext = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(View.inflate(mContext, R.layout.item_recyclerview, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final PodcastChanel current = mArrayList.get(position);
        final RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
        viewHolder.mName.setText(current.title);
        GradientDrawable drawable =(GradientDrawable)viewHolder.mFrameLayout.getBackground();
        drawable.setColor(ContextCompat.getColor(mContext, ContantUtil.getRandColor()));
        preferences = mContext.getSharedPreferences(General.PREFS_Name, Context.MODE_PRIVATE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, VideoPlayerActivity.class);
                intent.putExtra("media_link", current.media_link);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
//        return ContantUtil.TEST_DATAS.length;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        FrameLayout mFrameLayout;
        TextView mName;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.tv_item_tip);
            mFrameLayout = (FrameLayout) itemView.findViewById(R.id.fl_main_layout);

            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            Typeface type1 = Typeface.createFromAsset(mContext.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");

            mName.setTypeface(type1);
        }
    }
}
