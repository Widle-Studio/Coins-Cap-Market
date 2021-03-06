package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Advertise_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.News;

public class Reddit_News_Adapter extends RecyclerView.Adapter<Reddit_News_Adapter.ViewHolder> {
    List<Reddit_News_Adapter.ViewHolder> holders = new ArrayList<Reddit_News_Adapter.ViewHolder>();
    ArrayList<News> mArrayList = new ArrayList<>();
    Context context;

    public Reddit_News_Adapter(Context context, ArrayList<News> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public Reddit_News_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_reddit_news, parent, false);
        Reddit_News_Adapter.ViewHolder viewHolder = new Reddit_News_Adapter.ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Reddit_News_Adapter.ViewHolder holder, int position) {
        final News current = mArrayList.get(position);

        holder.tv_title.setText(current.title);
        holder.tv_sub_title.setText(current.selftext);

        Glide.with(context)
                .load(current.thumbnail)
                .into(holder.iv_bg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Advertise_Activity.class);
                intent.putExtra("title", current.title);
                intent.putExtra("url", current.url);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_bg;

        private TextView tv_title, tv_sub_title;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_bg = (ImageView)itemView.findViewById(R.id.iv_bg);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_sub_title = itemView.findViewById(R.id.tv_sub_title);

            Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            tv_title.setTypeface(type);
            tv_sub_title.setTypeface(type);

        }
    }
}
