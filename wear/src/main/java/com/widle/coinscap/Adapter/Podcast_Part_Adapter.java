package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.R;

/**
 * Created by imperial-web on 4/19/2018.
 */

public class Podcast_Part_Adapter extends RecyclerView.Adapter<Podcast_Part_Adapter.ViewHolder> {
    List<ViewHolder> holders = new ArrayList<ViewHolder>();
    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();
    Context context;

    public Podcast_Part_Adapter(Context context, ArrayList<PodcastChanel> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_podcastpart, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PodcastChanel current = mArrayList.get(position);

        holder.tv_title.setText(current.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(current.media_link), "audio/*");
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
