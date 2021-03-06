package com.widle.coinscap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Model.PodcastChanel;
import com.widle.coinscap.Podcast_Detail_Activity;
import com.widle.coinscap.R;

/**
 * Created by imperial-web on 5/9/2018.
 */

public class Podcast_Detail_Adapter extends RecyclerView.Adapter<Podcast_Detail_Adapter.ViewHolder> {
    List<Podcast_Detail_Adapter.ViewHolder> holders = new ArrayList<Podcast_Detail_Adapter.ViewHolder>();
    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();
    Context context;

    public Podcast_Detail_Adapter(Context context, ArrayList<PodcastChanel> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public Podcast_Detail_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_podcastdetail, parent, false);
        Podcast_Detail_Adapter.ViewHolder viewHolder = new Podcast_Detail_Adapter.ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Podcast_Detail_Adapter.ViewHolder holder, int position) {
        final PodcastChanel current = mArrayList.get(position);
        holder.tv_title.setText(current.title);
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
