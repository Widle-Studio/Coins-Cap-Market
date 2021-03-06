package com.widle.coinscap.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v17.leanback.widget.BaseCardView;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.widle.coinscap.R;

/**
 * Created by imperial-web on 3/30/2018.
 */

public class RichCardView extends RelativeLayout {

    private ImageView imageView_Icone;
    private TextView TV_Name,Tv_Price,Tv_Mc,Tv_Ptc;
    private String Symbol;
    private RelativeLayout relativeLayout;



    public RichCardView(Context context) {
        this(context, null);
    }

    public RichCardView(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v17.leanback.R.attr.imageCardViewStyle);
    }



    public RichCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);




        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.lb_rich_card_view, this);


        imageView_Icone = findViewById(R.id.iv_coin_t);
        TV_Name = findViewById(R.id.tv_name_t);
        Tv_Price = findViewById(R.id.tv_price_t);
        Tv_Mc = findViewById(R.id.tv_mc_t);
        Tv_Ptc = findViewById(R.id.tv_pct_t);

        relativeLayout = findViewById(R.id.fl_main_layout_t);

    }




    public ImageView setMainImageDimensions() {
        ViewGroup.LayoutParams lp = imageView_Icone.getLayoutParams();
        lp.width = 50;
        lp.height = 50;
        imageView_Icone.setLayoutParams(lp);
        return imageView_Icone;
    }



    public void setTitleText(CharSequence text) {
        if (TV_Name == null) {
            return;
        }
        TV_Name.setText(text);
    }

    /**
     * Returns the title text.
     */
    public CharSequence getPrice() {
        if (TV_Name == null) {
            return null;
        }

        return TV_Name.getText();
    }


    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }


    public void setPrice(CharSequence text) {
        if (Tv_Price == null) {
            return;
        }
        Tv_Price.setText(text);
    }

    public CharSequence getSymbole() {


        return Symbol;
    }


    public void setSymbole(CharSequence text) {

        Symbol = text.toString();
    }
    /**
     * Returns the title text.
     */
    public CharSequence getTitleText() {
        if (Tv_Price == null) {
            return null;
        }

        return Tv_Price.getText();
    }


    public void setMc(CharSequence text) {
        if (Tv_Mc == null) {
            return;
        }
        Tv_Mc.setText(text);
    }

    /**
     * Returns the title text.
     */
    public CharSequence getMc() {
        if (Tv_Mc == null) {
            return null;
        }

        return Tv_Mc.getText();
    }



    public void setPtc(CharSequence text) {
        if (Tv_Ptc == null) {
            return;
        }
        Tv_Ptc.setText(text);
        Tv_Ptc.setTextColor(Color.parseColor("#008000"));
    }

    /**
     * Returns the title text.
     */
    public CharSequence getPtc() {
        if (Tv_Ptc == null) {
            return null;
        }

        return Tv_Ptc.getText();
    }





    public void setSelected(boolean isFocused, int po) {
        super.setSelected(isFocused);

        if (isFocused) {
            // when be selected, set some views Visible to show more information.
            relativeLayout.setFocusable(true);
            relativeLayout.setFocusableInTouchMode(true);

        } else {
            // when not be selected, hide those views.
            relativeLayout.setFocusable(false);
            relativeLayout.setFocusableInTouchMode(false);


        }
    }
}
