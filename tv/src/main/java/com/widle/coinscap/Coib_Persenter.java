package com.widle.coinscap;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.widle.coinscap.Adapter.Custom_ArrayObjectAdapter;
import com.widle.coinscap.Adapter.RichCardView;
import com.widle.coinscap.Model.coin;

/**
 * Created by imperial-web on 4/2/2018.
 */

public class Coib_Persenter extends Presenter {

    private static final String TAG = "CardPresenter";
    private ImageView imageView_Icone;
    private TextView TV_Name,Tv_Price,Tv_Mc,Tv_Ptc;
    private static int sSelectedBackgroundColor;
    private static int sDefaultBackgroundColor;
    private Drawable mDefaultCardImage;
    private Context  mainFragment;
    Custom_ArrayObjectAdapter objectAdapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor ;
    int po;



    public Coib_Persenter(Context fragment) {
        this.mainFragment = fragment;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {


        sDefaultBackgroundColor = parent.getResources().getColor(R.color.default_background);
        sSelectedBackgroundColor = parent.getResources().getColor(R.color.light_white);

        mDefaultCardImage = parent.getResources().getDrawable(R.drawable.movie);
        preferences = PreferenceManager.getDefaultSharedPreferences(mainFragment);
        editor = preferences.edit();

        final RichCardView cardView = new RichCardView(parent.getContext());

        cardView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, final boolean isFocused) {

                final int position = view.getId();
                updateCardBackgroundColor(cardView, isFocused);
                    cardView.setSelected(isFocused,position);

            }
        });

        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        updateCardBackgroundColor(cardView, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        coin movie = (coin) item;
        RichCardView cardView = (RichCardView) viewHolder.view;
        cardView.setId(((coin) item).getId());

        Log.d(TAG, "onBindViewHolder");
        if (movie != null) {

            if ("BTC".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                cardView.setSymbole(movie.getCOINSSTRINGNAME());
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.btc)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }



            if ("ETH".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.eth)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }                }


            if ("BCH".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.bch)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("XRP".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.ripple)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("LTC".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.litecoin)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("ADA".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.ada)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("IOT".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.iota)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    /// Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("DASH".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.dash)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    ///Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("XEM".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.xem)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());
                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //   Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("XMR".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.xmr)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("EOS".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.eos)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("BTG".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.btg)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("QTUM".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.qtum)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("XLM".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.str)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("NEO".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.neo)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("ETC".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.etc2)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("ZEC".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.zec)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("STEEM".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.steem)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("XZC".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.xzc1)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("STORJ".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.sjcx)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("AION".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load(R.drawable.aion)
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("HT".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));



                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010813/ht.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("TRX".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/12318089/trx.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("XRB".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383674/xrb.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("OMG".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383814/omisego.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("ELA".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010574/ela.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("BNB".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1382967/bnb.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("VEN".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/12318129/ven.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("ZCL".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/351926/zcl.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }



            if ("DGD".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/350851/dgd.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("OCN".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010448/ocn.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("BCPT".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));



                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/16746476/bcpt.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("LUN".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383112/lunyr-logo.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("IOST".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010459/iost.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("HSR".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/12318137/hsr.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("ICX".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/12318192/icx.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("LSK".contains((CharSequence) movie.COINSSTRINGNAME)) {

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27011060/lsk.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }









            if ("NEBL".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1384016/nebl.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }



            if ("WAVES".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010639/waves2.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("BLZ".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010607/1.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }





            if ("INK".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/20780781/ink.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("ADX".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383667/adx1.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }




            if ("XVG".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/12318032/xvg.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }

            if ("MTL".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383743/mtl.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }
            if ("SRN".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/14913556/srn.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }
            if ("ZRX".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383799/zrx.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }
            if ("RLC".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/12318418/rlc.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }
            if ("THETA".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010450/theta.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }
            if ("BCD".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/16404872/bcd.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }  if ("OAX".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383756/oax.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }
            if ("ELF".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/20780600/elf.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }  if ("INS".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }  if ("ZIL".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010464/zil.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }  if ("AE".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383836/ae.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }
            if ("PRO".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());


                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }  if ("DOGE".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/19684/doge.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }  if ("ITC".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/20780628/itc.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }  if ("RDD".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/19887/rdd.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }  if ("SWFTC".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010472/swftc.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }  if ("MCO".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383653/mco.jpg")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }  if ("ARN".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/12318261/arn.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }  if ("MTN*".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010631/mtn_logo.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }  if ("BRD".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/20780589/brd.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }  if ("SAN".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383730/san.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }  if ("NAS".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/20780653/nas.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }  if ("GVT".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/14913634/gvt.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }




            if ("QUN".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/27010466/qun.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }

            if ("WTC".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/12317959/wtc.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }

            if ("FCT".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1382863/fct1.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }

            }

            if ("CDT".contains((CharSequence) movie.COINSSTRINGNAME)) {


                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));

                //cardView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH,CARD_HEIGHT));
                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/1383699/cdt.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    // Tv_Ptc.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                    //  Tv_Ptc.setTextColor(Color.parseColor("#008000"));
                }
            }

            if ("RCN".contains((CharSequence) movie.COINSSTRINGNAME)) {

                cardView.setPrice(movie.getPRICE());
                cardView.setTitleText(movie.getCOINSNAME());
                cardView.setMc(movie.getMKTCAP());
                Glide.with(viewHolder.view.getContext())
                        .load("https://www.cryptocompare.com/media/12318046/rnc.png")
                        .override(50, 50)
                        .error(mDefaultCardImage)
                        .into(cardView.setMainImageDimensions());

                if (movie.getCHANGEPCT24HOUR().contains("-")) {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                } else {
                    cardView.setPtc(movie.getCHANGE24HOUR() + " (" + movie.getCHANGEPCT24HOUR() + "%)");
                }
            }
        }
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        RichCardView cardView = (RichCardView) viewHolder.view;
    }
    private static void updateCardBackgroundColor(RichCardView view, boolean selected) {
        int color = selected ? sSelectedBackgroundColor : sDefaultBackgroundColor;
        // Both background colors should be set because the view's background is temporarily visible
        // during animations.
        view.setBackgroundColor(color);
        view.findViewById(R.id.fl_main_layout_t).setBackgroundColor(color);
    }
}
