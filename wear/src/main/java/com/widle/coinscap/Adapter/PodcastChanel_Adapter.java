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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.Podcast_Part_Activity;
import com.widle.coinscap.R;


/**
 * Created by imperial-web on 4/18/2018.
 */

public class PodcastChanel_Adapter extends RecyclerView.Adapter<PodcastChanel_Adapter.ViewHolder>{
    List<ViewHolder> holders = new ArrayList<ViewHolder>();
    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();
    Context context;

    public PodcastChanel_Adapter(Context context, ArrayList<PodcastChanel> mArrayList1) {
        this.context = context;
        this.mArrayList = mArrayList1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_podcast, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PodcastChanel current = mArrayList.get(position);

        holder.tv_title.setText(current.title);

        Glide.with(context)
                .load(current.image)
                .into(holder.iv_podcast);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Podcast_Part_Activity.class);
                intent.putExtra("channel_id", current.channel_id);
                intent.putExtra("title", current.title);
                intent.putExtra("description", current.description);
                intent.putExtra("subtitle", current.subtitle);
                intent.putExtra("image", current.image);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;

        private ImageView iv_podcast;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            iv_podcast = itemView.findViewById(R.id.iv_podcast);

            Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");

            tv_title.setTypeface(type1);

        }
    }
}
