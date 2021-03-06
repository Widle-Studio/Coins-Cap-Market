package com.widle.coinscap.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.wear.widget.WearableRecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.widle.coinscap.Favourite_Activity;
import com.widle.coinscap.Model.Fav_Coins;
import com.widle.coinscap.Model.coin;
import com.widle.coinscap.R;

/**
 * Created by imperial-web1 on 15/3/18.
 */

public class Favourite_Adapter  extends WearableRecyclerView.Adapter<Favourite_Adapter.ViewHolder> {
    List<ViewHolder> holders = new ArrayList<ViewHolder>();
    ArrayList<Fav_Coins> mCoinArrayList = new ArrayList<>();
    Context context;
    List<String> stringList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();


    public Favourite_Adapter(Context context, ArrayList<Fav_Coins> mArrayList, List<String> stringList, List<String> nameList) {
        this.context = context;
        this.mCoinArrayList = mArrayList;
        this.stringList = stringList;
        this.nameList = nameList;
    }

    @Override
    public Favourite_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_items_favourite, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Favourite_Adapter.ViewHolder holder, int position) {
        final Fav_Coins Coincurrent = mCoinArrayList.get(position);

        holder.tv_btc_name.setText(nameList.get(position));
        holder.tv_btc_sf.setText(stringList.get(position));
        holder.tv_btc_mc.setText(Coincurrent.getUSD().getMKTCAP());
        holder.tv_btc_price.setText(Coincurrent.getUSD().getPRICE());

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

        if (Coincurrent.getUSD().getCHANGEPCT24HOUR().contains("-")) {
            holder.tv_btc_pct.setText(Coincurrent.getUSD().getCHANGE24HOUR()+ " (" + Coincurrent.getUSD().getCHANGEPCT24HOUR() + "%)");
            holder.tv_btc_pct.setTextColor(Color.parseColor("#FF0000"));

        } else {
            holder.tv_btc_pct.setText(Coincurrent.getUSD().getCHANGE24HOUR() + " (" + Coincurrent.getUSD().getCHANGEPCT24HOUR() + "%)");
            holder.tv_btc_pct.setTextColor(Color.parseColor("#008000"));
        }
    }

    @Override
    public int getItemCount() {
        return (null != mCoinArrayList ? mCoinArrayList.size() : 0);
    }

    public class ViewHolder extends WearableRecyclerView.ViewHolder {

        private ImageView iv_coin;
        private TextView tv_btc_name, tv_btc_sf, tv_btc_price, tv_btc_pct,tv_btc_mc;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_coin = (ImageView) itemView.findViewById(R.id.iv_coin);
            tv_btc_name = (TextView) itemView.findViewById(R.id.tv_btc_name);
            tv_btc_sf = (TextView) itemView.findViewById(R.id.tv_btc_sf);
            tv_btc_price = (TextView) itemView.findViewById(R.id.tv_btc_price);
            tv_btc_pct = (TextView) itemView.findViewById(R.id.tv_btc_pct);
            tv_btc_mc = (TextView) itemView.findViewById(R.id.tv_btc_mc);

            Typeface type = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-BoldUpright.otf");
            Typeface type1 = Typeface.createFromAsset(context.getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
            tv_btc_name.setTypeface(type);
            tv_btc_sf.setTypeface(type);
            tv_btc_price.setTypeface(type);
            tv_btc_pct.setTypeface(type);
            tv_btc_mc.setTypeface(type);


            tv_btc_name.setTypeface(type1);
        }
    }
}
