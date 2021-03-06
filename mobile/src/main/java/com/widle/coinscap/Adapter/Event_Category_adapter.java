package com.widle.coinscap.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.Event;

public class Event_Category_adapter extends RecyclerView.Adapter<Event_Category_adapter.ViewHolder> {

    ArrayList<Event> mArrayList = new ArrayList<>();
    Context context;

    int row_index;

    public Event_Category_adapter(Context context, ArrayList<Event> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public Event_Category_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_event_category_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final Event_Category_adapter.ViewHolder holder, final int position) {

        holder.tv_category.setText(mArrayList.get(position).name);

        holder.tv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
            }
        });

        if(row_index==position){
            holder.tv_category.setBackgroundResource(R.drawable.roundshape);
            holder.tv_category.setTextColor(Color.parseColor("#0C2038"));
        }
        else
        {
            holder.tv_category.setBackgroundResource(R.drawable.round_corner1);
            holder.tv_category.setTextColor(Color.parseColor("#F7921C"));
        }




    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_category;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_category = (TextView) itemView.findViewById(R.id.tv_category);

            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");
            tv_category.setTypeface(type2);

        }
    }

}
