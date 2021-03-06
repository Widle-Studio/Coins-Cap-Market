package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.PodcastChanel;
import com.widle.coinscap.VideoPlayerActivity;

/**
 * Created by imperial-web on 4/19/2018.
 */

public class Podcast_Part_Adapter extends RecyclerView.Adapter<Podcast_Part_Adapter.ViewHolder> {
    List<Podcast_Part_Adapter.ViewHolder> holders = new ArrayList<Podcast_Part_Adapter.ViewHolder>();
    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();
    Context context;

    public Podcast_Part_Adapter(Context context, ArrayList<PodcastChanel> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public Podcast_Part_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_podcastpart, parent, false);
        Podcast_Part_Adapter.ViewHolder viewHolder = new Podcast_Part_Adapter.ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Podcast_Part_Adapter.ViewHolder holder, int position) {
        final PodcastChanel current = mArrayList.get(position);

        holder.tv_title.setText(current.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("media_link", current.media_link);
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

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);

        }
    }
}
