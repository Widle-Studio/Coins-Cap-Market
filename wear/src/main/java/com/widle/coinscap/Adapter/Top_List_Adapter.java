package com.widle.coinscap.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Model.coin;
import com.widle.coinscap.R;


public class Top_List_Adapter extends RecyclerView.Adapter<Top_List_Adapter.ViewHolder> {
    List<Top_List_Adapter.ViewHolder> holders = new ArrayList<Top_List_Adapter.ViewHolder>();
    ArrayList<coin> mArrayList = new ArrayList<>();
    Context context;

    public Top_List_Adapter(Context context, ArrayList<coin> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public Top_List_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_toplist, parent, false);
        Top_List_Adapter.ViewHolder viewHolder = new Top_List_Adapter.ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Top_List_Adapter.ViewHolder holder, int position) {
        holder.tv_coin.setText(mArrayList.get(position).FULLNAME);
        holder.tv_supply.setText("Supply : " + mArrayList.get(position).SUPPLY);
        holder.tv_volume.setText("VOLUME24HOURTO : " + mArrayList.get(position).VOLUME24HOURTO);
    }

    @Override
    public int getItemCount() {
        return (null != mArrayList ? mArrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_coin, tv_supply, tv_volume;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_coin = (TextView) itemView.findViewById(R.id.tv_coin);
            tv_supply = (TextView) itemView.findViewById(R.id.tv_supply);
            tv_volume = (TextView) itemView.findViewById(R.id.tv_volume);

            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            Typeface type2 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-LightUpright.otf");

            tv_coin.setTypeface(type1);
            tv_supply.setTypeface(type1);
            tv_volume.setTypeface(type1);
        }
    }
}
