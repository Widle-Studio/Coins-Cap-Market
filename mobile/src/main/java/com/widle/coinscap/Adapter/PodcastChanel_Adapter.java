package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Podcast_Part_Activity;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.PodcastChanel;

/**
 * Created by imperial-web on 4/18/2018.
 */

public class PodcastChanel_Adapter  extends RecyclerView.Adapter<PodcastChanel_Adapter.ViewHolder>{
    List<PodcastChanel_Adapter.ViewHolder> holders = new ArrayList<PodcastChanel_Adapter.ViewHolder>();
    ArrayList<PodcastChanel> mArrayList = new ArrayList<>();
    Context context;

    public PodcastChanel_Adapter(Context context,ArrayList<PodcastChanel> mArrayList1) {
        this.context = context;
        this.mArrayList = mArrayList1;
    }

    @Override
    public PodcastChanel_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_podcast, parent, false);
        PodcastChanel_Adapter.ViewHolder viewHolder = new PodcastChanel_Adapter.ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PodcastChanel_Adapter.ViewHolder holder, int position) {
        final PodcastChanel current = mArrayList.get(position);

        holder.tv_title.setText(current.title);
        holder.tv_sub_title.setText(current.subtitle);

        Glide.with(context)
                .load(current.image)
                .into(holder.iv_image);

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

        private TextView tv_title, tv_sub_title;

        private ImageView iv_image;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_sub_title = itemView.findViewById(R.id.tv_sub_title);
            iv_image = itemView.findViewById(R.id.iv_image);

            Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");

            tv_title.setTypeface(type1);
            tv_sub_title.setTypeface(type1);

        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                } else {
                    ArrayList<PodcastChanel> filteredList = new ArrayList<>();
                    for (PodcastChanel row : mArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.title.toLowerCase().contains(charString.toLowerCase()) || row.title.contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mArrayList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mArrayList = (ArrayList<PodcastChanel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(PodcastChanel contact);
    }
}
