package com.widle.coinscap;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Help_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back, iv_down_q1, iv_up_q1, iv_down_q2, iv_up_q2, iv_down_q3, iv_up_q3, iv_down_q4, iv_up_q4,
            iv_down_q5, iv_up_q5, iv_down_q6, iv_up_q6, iv_down_q7, iv_up_q7, iv_down_q8, iv_up_q8;

    private LinearLayout ll_q1, ll_q2, ll_q3, ll_q4, ll_q5, ll_q6, ll_q7, ll_q8;

    private TextView tv_title, tv_q1, tv_q2, tv_q3, tv_q4, tv_q5, tv_q6, tv_label, tv_q7,tv_q8;

    private TextView tv_a1, tv_a21, tv_a31, tv_a41, tv_a51, tv_a61, tv_a71, tv_a81;

    private TextView tv_coin, tv_ico, tv_ico_listing, tv_mining, tv_mining_pool, tv_wallet;

    private int i = 0, j = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        init();
    }

    public void init() {

        ll_q1 = (LinearLayout) findViewById(R.id.ll_q1);
        ll_q2 = (LinearLayout) findViewById(R.id.ll_q2);
        ll_q3 = (LinearLayout) findViewById(R.id.ll_q3);
        ll_q4 = (LinearLayout) findViewById(R.id.ll_q4);
        ll_q5 = (LinearLayout) findViewById(R.id.ll_q5);
        ll_q6 = (LinearLayout) findViewById(R.id.ll_q6);
        ll_q7 = (LinearLayout) findViewById(R.id.ll_q7);
        ll_q8 = (LinearLayout) findViewById(R.id.ll_q8);

        iv_down_q1 = findViewById(R.id.iv_down_q1);
        iv_up_q1 = findViewById(R.id.iv_up_q1);
        iv_down_q2 = findViewById(R.id.iv_down_q2);
        iv_up_q2 = findViewById(R.id.iv_up_q2);
        iv_down_q3 = findViewById(R.id.iv_down_q3);
        iv_up_q3 = findViewById(R.id.iv_up_q3);
        iv_down_q4 = findViewById(R.id.iv_down_q4);
        iv_up_q4 = findViewById(R.id.iv_up_q4);
        iv_down_q5 = findViewById(R.id.iv_down_q5);
        iv_up_q5 = findViewById(R.id.iv_up_q5);
        iv_down_q6 = findViewById(R.id.iv_down_q6);
        iv_up_q6 = findViewById(R.id.iv_up_q6);
        iv_down_q7 = findViewById(R.id.iv_down_q7);
        iv_up_q7 = findViewById(R.id.iv_up_q7);
        iv_down_q8 = findViewById(R.id.iv_down_q8);
        iv_up_q8 = findViewById(R.id.iv_up_q8);


        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_q1 = findViewById(R.id.tv_q1);
        tv_q2 = findViewById(R.id.tv_q2);
        tv_q3 = findViewById(R.id.tv_q3);
        tv_q4 = findViewById(R.id.tv_q4);
        tv_q5 = findViewById(R.id.tv_q5);
        tv_q6 = findViewById(R.id.tv_q6);
        tv_label = findViewById(R.id.tv_label);
        tv_q7 = findViewById(R.id.tv_q7);
        tv_q8 = findViewById(R.id.tv_q8);

        tv_a1 = findViewById(R.id.tv_a1);
        tv_a21 = findViewById(R.id.tv_a21);
        tv_a31 = findViewById(R.id.tv_a31);
        tv_a41 = findViewById(R.id.tv_a41);
        tv_a51 = findViewById(R.id.tv_a51);
        tv_a61 = findViewById(R.id.tv_a61);
        tv_a71 = findViewById(R.id.tv_a71);
        tv_a81 = findViewById(R.id.tv_a81);

        tv_coin = findViewById(R.id.tv_coin);
        tv_ico = findViewById(R.id.tv_ico);
        tv_ico_listing = findViewById(R.id.tv_ico_listing);
        tv_mining = findViewById(R.id.tv_mining);
        tv_mining_pool = findViewById(R.id.tv_mining_pool);
        tv_wallet = findViewById(R.id.tv_wallet);


        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_title.setTypeface(type2);
        tv_q1.setTypeface(type);
        tv_q2.setTypeface(type);
        tv_q3.setTypeface(type);
        tv_q4.setTypeface(type);
        tv_q5.setTypeface(type);
        tv_q6.setTypeface(type);
        tv_q7.setTypeface(type);
        tv_q8.setTypeface(type);

        tv_a1.setTypeface(type1);
        tv_a21.setTypeface(type1);
        tv_a31.setTypeface(type1);
        tv_a41.setTypeface(type1);
        tv_a51.setTypeface(type1);
        tv_a61.setTypeface(type1);
        tv_label.setTypeface(type1);
        tv_a71.setTypeface(type1);
        tv_a81.setTypeface(type1);

        tv_coin.setTypeface(type);
        tv_ico.setTypeface(type);
        tv_ico_listing.setTypeface(type);
        tv_mining.setTypeface(type);
        tv_mining_pool.setTypeface(type);
        tv_wallet.setTypeface(type);

        tv_title.setText("Help");
        tv_q1.setText("What is \"Market Capitalization\" and how is it calculated?");
        tv_q2.setText("How are the prices calculated for the various cryptocurrencies?");
        tv_q3.setText("What is the difference between \"Circulating Supply\", \"Total Supply\", and \"Max Supply\"?");
        tv_q4.setText("Why is the Circulating Supply used in determining the market capitalization instead of Total Supply?");
        tv_q5.setText("What is the difference between a \"Coin\" and a \"Token\" on the site?");
        tv_q6.setText("What is the criteria for a cryptocurrency to be listed on CoinsCapMarket?");
        tv_q7.setText("Why are markets with no fees excluded from the price average and total trading volume?");
        tv_q8.setText("How do I purchase cryptocurrency?How do I purchase cryptocurrency?");

        tv_a1.setText(" - Market Capitalization is one way to rank the relative size of a cryptocurrency. It's calculated by multiplying the Price by the Circulating Supply. \n - Market Cap = Price X Circulating Supply.");
        tv_a21.setText(" - Price is calculated by taking the volume weighted average of all prices reported at each market. Sources for the prices can be found on the markets section on each cryptocurrency page. For example, Bitcoin's markets.");
        tv_a31.setText(" - Circulating Supply is the best approximation of the number of coins that are circulating in the market and in the general public's hands. \n - Total Supply is the total amount of coins in existence right now (minus any coins that have been verifiably burned). \n - Max Supply is the best approximation of the maximum amount of coins that will ever exist in the lifetime of the cryptocurrency.");
        tv_a41.setText(" - We've found that Circulating Supply is a much better metric for determining the market capitalization. Coins that are locked, reserved, or not able to be sold on the public market are coins that can't affect the price and thus should not be allowed to affect the market capitalization as well. The method of using the Circulating Supply is analogous to the method of using public float for determining the market capitalization of companies in traditional investing.");
        tv_a51.setText(" - A Coin is a cryptocurrency that can operate independently. \n - A Token is a cryptocurrency that depends on another cryptocurrency as a platform to operate. Check out the crypto tokens listings to view a list of tokens and their respective platforms.");
        tv_a61.setText("The criteria for a cryptocurrency to be listed is the following: \n - Must be a cryptocurrency or a crypto token. \n - Must be on a public exchange with an API that reports the last traded price and the last 24 hour trading volume. \n -  Must have a non-zero trading volume on at least one supported exchange so a price can be determined. \n - For market cap ranking, an accurate circulating supply figure is required.");
        tv_label.setText("If you'd like to have a cryptocurrency listed and it meets all of the criteria, please fill out the request form. \n\n   Please fill in all the necessary information for the listing.");
        tv_a71.setText(" - When no fees are being charged at the exchange, it is possible for a trader (or bot) to trade back and forth with themselves and generate a lot of \"fake\" volume without penalty. It's impossible to determine how much of the volume is fake so we exclude it entirely from the calculations.");
        tv_a81.setText(" - CoinsCapMarket reports on the trading activities of thousands of markets but does not directly sell any cryptocurrency. The best way to find where to buy is by looking on the markets section for the cryptocurrency. For example, to find where to buy Bitcoin, you can look at the markets section for Bitcoin.");


        iv_back.setOnClickListener(this);
        tv_coin.setOnClickListener(this);
        tv_ico.setOnClickListener(this);
        tv_ico_listing.setOnClickListener(this);
        tv_mining.setOnClickListener(this);
        tv_mining_pool.setOnClickListener(this);
        tv_wallet.setOnClickListener(this);
        ll_q1.setOnClickListener(this);
        ll_q2.setOnClickListener(this);
        ll_q3.setOnClickListener(this);
        ll_q4.setOnClickListener(this);
        ll_q5.setOnClickListener(this);
        ll_q6.setOnClickListener(this);
        ll_q7.setOnClickListener(this);
        ll_q8.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Help_Activity.this, Help_Link_Activity.class);
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_coin:
                intent.putExtra("type", "1");
                startActivity(intent);
                break;

            case R.id.tv_ico:
                intent.putExtra("type", "2");
                startActivity(intent);
                break;

            case R.id.tv_ico_listing:
                intent.putExtra("type", "3");
                startActivity(intent);
                break;

            case R.id.tv_mining:
                intent.putExtra("type", "4");
                startActivity(intent);
                break;

            case R.id.tv_mining_pool:
                intent.putExtra("type", "5");
                startActivity(intent);
                break;

            case R.id.tv_wallet:
                intent.putExtra("type", "6");
                startActivity(intent);
                break;

            case R.id.ll_q1:
                if (i == 0){
                    tv_a1.setVisibility(View.VISIBLE);
                    iv_down_q1.setVisibility(View.GONE);
                    iv_up_q1.setVisibility(View.VISIBLE);
                    i = 1;
                } else{
                    tv_a1.setVisibility(View.GONE);
                    iv_down_q1.setVisibility(View.VISIBLE);
                    iv_up_q1.setVisibility(View.GONE);
                    i = 0;
                }
                break;

            case R.id.ll_q2:
                if (j == 0){
                    tv_a21.setVisibility(View.VISIBLE);
                    iv_down_q2.setVisibility(View.GONE);
                    iv_up_q2.setVisibility(View.VISIBLE);
                    j = 1;
                } else{
                    tv_a21.setVisibility(View.GONE);
                    iv_down_q2.setVisibility(View.VISIBLE);
                    iv_up_q2.setVisibility(View.GONE);
                    j = 0;
                }
                break;

            case R.id.ll_q3:
                if (k == 0){
                    tv_a31.setVisibility(View.VISIBLE);
                    iv_down_q3.setVisibility(View.GONE);
                    iv_up_q3.setVisibility(View.VISIBLE);
                    k = 1;
                } else{
                    tv_a31.setVisibility(View.GONE);
                    iv_down_q3.setVisibility(View.VISIBLE);
                    iv_up_q3.setVisibility(View.GONE);
                    k = 0;
                }
                break;

            case R.id.ll_q4:
                if (l == 0){
                    tv_a41.setVisibility(View.VISIBLE);
                    iv_down_q4.setVisibility(View.GONE);
                    iv_up_q4.setVisibility(View.VISIBLE);
                    l = 1;
                } else{
                    tv_a41.setVisibility(View.GONE);
                    iv_down_q4.setVisibility(View.VISIBLE);
                    iv_up_q4.setVisibility(View.GONE);
                    l = 0;
                }
                break;

            case R.id.ll_q5:
                if (m == 0){
                    tv_a51.setVisibility(View.VISIBLE);
                    iv_down_q5.setVisibility(View.GONE);
                    iv_up_q5.setVisibility(View.VISIBLE);
                    m = 1;
                } else{
                    tv_a51.setVisibility(View.GONE);
                    iv_down_q5.setVisibility(View.VISIBLE);
                    iv_up_q5.setVisibility(View.GONE);
                    m = 0;
                }
                break;

            case R.id.ll_q6:
                if (n == 0){
                    tv_a61.setVisibility(View.VISIBLE);
                    tv_label.setVisibility(View.VISIBLE);
                    tv_coin.setVisibility(View.VISIBLE);
                    tv_ico.setVisibility(View.VISIBLE);
                    tv_ico_listing.setVisibility(View.VISIBLE);
                    tv_mining.setVisibility(View.VISIBLE);
                    tv_mining_pool.setVisibility(View.VISIBLE);
                    tv_wallet.setVisibility(View.VISIBLE);
                    iv_down_q6.setVisibility(View.GONE);
                    iv_up_q6.setVisibility(View.VISIBLE);
                    n = 1;
                } else{
                    tv_a61.setVisibility(View.GONE);
                    tv_label.setVisibility(View.GONE);
                    tv_coin.setVisibility(View.GONE);
                    tv_ico.setVisibility(View.GONE);
                    tv_ico_listing.setVisibility(View.GONE);
                    tv_mining.setVisibility(View.GONE);
                    tv_mining_pool.setVisibility(View.GONE);
                    tv_wallet.setVisibility(View.GONE);
                    iv_down_q6.setVisibility(View.VISIBLE);
                    iv_up_q6.setVisibility(View.GONE);
                    n = 0;
                }
                break;

            case R.id.ll_q7:
                if (o == 0){
                    tv_a71.setVisibility(View.VISIBLE);
                    iv_down_q7.setVisibility(View.GONE);
                    iv_up_q7.setVisibility(View.VISIBLE);
                    o = 1;
                } else{
                    tv_a71.setVisibility(View.GONE);
                    iv_down_q7.setVisibility(View.VISIBLE);
                    iv_up_q7.setVisibility(View.GONE);
                    o = 0;
                }
                break;

            case R.id.ll_q8:
                if (p == 0){
                    tv_a81.setVisibility(View.VISIBLE);
                    iv_down_q8.setVisibility(View.GONE);
                    iv_up_q8.setVisibility(View.VISIBLE);
                    p = 1;
                } else{
                    tv_a81.setVisibility(View.GONE);
                    iv_down_q8.setVisibility(View.VISIBLE);
                    iv_up_q8.setVisibility(View.GONE);
                    p = 0;
                }
                break;

        }
    }
}
