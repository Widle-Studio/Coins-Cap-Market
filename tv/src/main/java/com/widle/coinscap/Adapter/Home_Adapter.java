package com.widle.coinscap.Adapter;

import android.content.Context;
import android.graphics.Color;
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

import com.widle.coinscap.Home1_Activity;
import com.widle.coinscap.Model.coin;
import com.widle.coinscap.R;

/**
 * Created by imperial-web on 3/23/2018.
 */

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.ViewHolder>{

    List<ViewHolder> holders = new ArrayList<ViewHolder>();
    Context context;
    List<String> nameList = new ArrayList<>();
    ArrayList<String> stringList = new ArrayList<>();
    ArrayList<coin> mArrayList = new ArrayList<>();

    public Home_Adapter(Context context, List<String> nameList, ArrayList<String> stringList, ArrayList<coin> mArrayList) {
        this.context = context;
        this.nameList = nameList;
        this.stringList = stringList;
        this.mArrayList = mArrayList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_home, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(Home_Adapter.ViewHolder holder, int position) {

        if ("BTC".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.btc);
        }else if ("ETH".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.eth);
        }else if ("BCH".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.bch);
        }else if ("XRP".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.ripple);
        }else if ("LTC".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.litecoin);
        }else if ("ADA".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.ada);
        }else if ("IOT".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.iota);
        }else if ("DASH".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.dash);
        }else if ("XEM".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.xem);
        }else if ("XMR".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.xmr);
        }else if ("EOS".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.eos);
        }else if ("BTG".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.btg);
        }else if ("QTUM".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.qtum);
        }else if ("XLM".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.str);
        }else if ("NEO".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.neo);
        }else if ("ETC".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.etc2);
        }else if ("ZEC".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.zec);
        }else if ("STEEM".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.steem);
        }else if ("XZC".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.xzc1);
        }else if ("STORJ".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.sjcx);
        }else if ("AION".contains(stringList.get(position))){
            holder.iv_coin.setImageResource(R.drawable.aion);
        }else if ("HT".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010813/ht.png").into(holder.iv_coin);
        }else if ("TRX".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/12318089/trx.png").into(holder.iv_coin);
        }else if ("XRB".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383674/xrb.png").into(holder.iv_coin);
        }else if ("OMG".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383814/omisego.png").into(holder.iv_coin);
        }else if ("ELA".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010574/ela.png").into(holder.iv_coin);
        }else if ("BNB".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1382967/bnb.png").into(holder.iv_coin);
        }else if ("VEN".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/12318129/ven.png").into(holder.iv_coin);
        }else if ("ZCL".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/351926/zcl.png").into(holder.iv_coin);
        }else if ("DGD".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/350851/dgd.png").into(holder.iv_coin);
        }else if ("OCN".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010448/ocn.png").into(holder.iv_coin);
        }else if ("BCPT".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/16746476/bcpt.png").into(holder.iv_coin);
        }else if ("LUN".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383112/lunyr-logo.png").into(holder.iv_coin);
        }else if ("IOST".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010459/iost.png").into(holder.iv_coin);
        }else if ("HSR".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/12318137/hsr.png").into(holder.iv_coin);
        }else if ("ICX".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/12318192/icx.png").into(holder.iv_coin);
        }else if ("LSK".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27011060/lsk.png").into(holder.iv_coin);
        }else if ("NEBL".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1384016/nebl.png").into(holder.iv_coin);
        }else if ("WAVES".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010639/waves2.png").into(holder.iv_coin);
        }else if ("BLZ".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010607/1.png").into(holder.iv_coin);
        }else if ("INK".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/20780781/ink.png").into(holder.iv_coin);
        }else if ("ADX".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383667/adx1.png").into(holder.iv_coin);
        }else if ("XVG".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/12318032/xvg.png").into(holder.iv_coin);
        }else if ("MTL".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383743/mtl.png").into(holder.iv_coin);
        }else if ("SRN".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/14913556/srn.png").into(holder.iv_coin);
        }else if ("ZRX".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383799/zrx.png").into(holder.iv_coin);
        }else if ("RLC".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/12318418/rlc.png").into(holder.iv_coin);
        }else if ("THETA".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010450/theta.png").into(holder.iv_coin);
        }else if ("BCD".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/16404872/bcd.png").into(holder.iv_coin);
        }else if ("OAX".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383756/oax.png").into(holder.iv_coin);
        }else if ("ELF".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/20780600/elf.png").into(holder.iv_coin);
        }else if ("INS".contains(stringList.get(position))){
        }else if ("ZIL".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010464/zil.png").into(holder.iv_coin);
        }else if ("AE".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383836/ae.png").into(holder.iv_coin);
        }else if ("PRO".contains(stringList.get(position))){
//            Glide.with(context).load("https://www.cryptocompare.com/media/27010813/ht.png").into(holder.iv_coin);
        }else if ("DOGE".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/19684/doge.png").into(holder.iv_coin);
        }else if ("ITC".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/20780628/itc.png").into(holder.iv_coin);
        }else if ("RDD".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/19887/rdd.png").into(holder.iv_coin);
        }else if ("SWFTC".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010472/swftc.png").into(holder.iv_coin);
        }else if ("MCO".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383653/mco.jpg").into(holder.iv_coin);
        }else if ("ARN".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/12318261/arn.png").into(holder.iv_coin);
        }else if ("MTN*".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010631/mtn_logo.png").into(holder.iv_coin);
        }else if ("BRD".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/20780589/brd.png").into(holder.iv_coin);
        }else if ("SAN".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383730/san.png").into(holder.iv_coin);
        }else if ("NAS".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/20780653/nas.png").into(holder.iv_coin);
        }else if ("GVT".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/14913634/gvt.png").into(holder.iv_coin);
        }else if ("QUN".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/27010466/qun.png").into(holder.iv_coin);
        }else if ("WTC".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/12317959/wtc.png").into(holder.iv_coin);
        }else if ("FCT".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1382863/fct1.png").into(holder.iv_coin);
        }else if ("CDT".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/1383699/cdt.png").into(holder.iv_coin);
        }else if ("RCN".contains(stringList.get(position))){
            Glide.with(context).load("https://www.cryptocompare.com/media/12318046/rnc.png").into(holder.iv_coin);
        }
        holder.tv_name.setText(nameList.get(position));
        holder.tv_price.setText(mArrayList.get(position).PRICE);
        holder.tv_mc.setText("M.C:" + mArrayList.get(position).MKTCAP);
        if (mArrayList.get(position).CHANGEPCT24HOUR.contains("-")) {
            holder.tv_pct.setText(mArrayList.get(position).CHANGE24HOUR + " (" + mArrayList.get(position).CHANGEPCT24HOUR + "%)");
            holder.tv_pct.setTextColor(Color.parseColor("#FF0000"));
        } else {
            holder.tv_pct.setText(mArrayList.get(position).CHANGE24HOUR + " (" + mArrayList.get(position).CHANGEPCT24HOUR + "%)");
            holder.tv_pct.setTextColor(Color.parseColor("#008000"));
        }
    }

    @Override
    public int getItemCount() {
        return (null != nameList ? nameList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_coin;

        private TextView tv_name, tv_price, tv_mc, tv_pct;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_coin = itemView.findViewById(R.id.iv_coin);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_mc = itemView.findViewById(R.id.tv_mc);
            tv_pct = itemView.findViewById(R.id.tv_pct);

            Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");

            tv_name.setTypeface(type1);
            tv_price.setTypeface(type);
            tv_mc.setTypeface(type1);
            tv_pct.setTypeface(type1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    

}
