package com.widle.coinscap.Fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.widle.coinscap.Adapter.Reddit_News_Adapter;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.News;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reddit_News_Fragment extends Fragment implements View.OnClickListener{

    private RecyclerView rv_reddit_news;

    ArrayList<News> mArrayList = new ArrayList<>();

    Reddit_News_Adapter reddit_news_adapter;

    private TextView tv_lbl, tv_popup, tv_popup1;

    public ProgressDialog mProgressDialog;

    private PopupWindow popupWindow;

    private String pop = "blockchain", pop1 = "new";

    SwipeRefreshLayout mSwipeRefreshLayout;


    public Reddit_News_Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reddit_news, container, false);

        rv_reddit_news = (RecyclerView) view.findViewById(R.id.rv_reddit_news);
        rv_reddit_news.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tv_lbl = (TextView) view.findViewById(R.id.tv_lbl);
        tv_popup = view.findViewById(R.id.tv_popup);
        tv_popup1 = view.findViewById(R.id.tv_popup1);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange);


        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);


        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-LightUpright.otf");
        tv_popup.setTypeface(type1);
        tv_popup1.setTypeface(type1);

        if (utils.isNetworkAvailable(getActivity())) {
            rv_reddit_news.setVisibility(View.VISIBLE);
            tv_lbl.setVisibility(View.GONE);
            new apiNewsList(pop, pop1).execute();
        } else {
            rv_reddit_news.setVisibility(View.GONE);
            tv_lbl.setVisibility(View.VISIBLE);
            tv_lbl.setText(R.string.err_no_internet);
        }

        String[] list = getResources().getStringArray(R.array.crypto);
        String[] list1 = getResources().getStringArray(R.array.hot);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, list);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, list1);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new apiNewsList(pop, pop1).execute();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        tv_popup.setOnClickListener(this);
        tv_popup1.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_popup:
                showPopup();
                break;

            case R.id.tv_popup1:
                showPopup1();
                break;
        }
    }





    /*-------------------------------------------POPUP-----------------------------------------------------------*/


    private void showPopup() {
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.popup_layout, null);
        popupWindow = new PopupWindow(view);
        int width = tv_popup.getWidth();
        if (width > 0) {
            popupWindow.setWidth(width);
        } else {
            popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        }
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c6c6c6")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        final TextView tv_blockchain = view.findViewById(R.id.tv_blockchain);
        final TextView tv_cryptocurrency = view.findViewById(R.id.tv_cryptocurrency);
        final TextView tv_cryptomarkets = view.findViewById(R.id.tv_cryptomarkets);
        final TextView tv_altcoin = view.findViewById(R.id.tv_altcoin);
        final TextView tv_bitcoin = view.findViewById(R.id.tv_bitcoin);
        final TextView tv_btc = view.findViewById(R.id.tv_btc);
        final TextView tv_ethereum = view.findViewById(R.id.tv_ethereum);
        final TextView tv_litecoin = view.findViewById(R.id.tv_litecoin);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        tv_blockchain.setTypeface(type);
        tv_cryptocurrency.setTypeface(type);
        tv_cryptomarkets.setTypeface(type);
        tv_altcoin.setTypeface(type);
        tv_bitcoin.setTypeface(type);
        tv_btc.setTypeface(type);
        tv_ethereum.setTypeface(type);
        tv_litecoin.setTypeface(type);

        tv_blockchain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup.setText(tv_blockchain.getText().toString());
                pop =  tv_blockchain.getText().toString();
                pop = pop.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });

        tv_cryptocurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup.setText(tv_cryptocurrency.getText().toString());
                pop =  tv_cryptocurrency.getText().toString();
                pop = pop.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });

        tv_cryptomarkets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup.setText(tv_cryptomarkets.getText().toString());
                pop =  tv_cryptomarkets.getText().toString();
                pop = pop.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });

        tv_altcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup.setText(tv_altcoin.getText().toString());
                pop =  tv_altcoin.getText().toString();
                pop = pop.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });

        tv_bitcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup.setText(tv_bitcoin.getText().toString());
                pop =  tv_bitcoin.getText().toString();
                pop = pop.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });

        tv_btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup.setText(tv_btc.getText().toString());
                pop =  tv_btc.getText().toString();
                pop = pop.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });

        tv_ethereum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup.setText(tv_ethereum.getText().toString());
                pop =  tv_ethereum.getText().toString();
                pop = pop.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });
        tv_litecoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup.setText(tv_litecoin.getText().toString());
                pop =  tv_litecoin.getText().toString();
                pop = pop.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });


        popupWindow.showAsDropDown(tv_popup, 0, 2);
    }






    /*-------------------------------------------POPUP1-----------------------------------------------------------*/


    private void showPopup1() {
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.popup_layout1, null);
        popupWindow = new PopupWindow(view);
        int width = tv_popup1.getWidth();
        if (width > 0) {
            popupWindow.setWidth(width);
        } else {
            popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        }
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c6c6c6")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        final TextView tv_hot = view.findViewById(R.id.tv_hot);
        final TextView tv_new = view.findViewById(R.id.tv_new);
        final TextView tv_top = view.findViewById(R.id.tv_top);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        tv_hot.setTypeface(type);
        tv_new.setTypeface(type);
        tv_top.setTypeface(type);

        tv_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup1.setText(tv_hot.getText().toString());
                pop1 =  tv_hot.getText().toString();
                pop1 = pop1.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });

        tv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup1.setText(tv_new.getText().toString());
                pop1 =  tv_new.getText().toString();
                pop1 = pop1.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });

        tv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_popup1.setText(tv_top.getText().toString());
                pop1 =  tv_top.getText().toString();
                pop1 = pop1.toLowerCase();
                new apiNewsList(pop, pop1).execute();
                popupWindow.dismiss();

            }
        });


        popupWindow.showAsDropDown(tv_popup1, 0, 2);
    }





    public class apiNewsList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg, pop1, pop2;

        public apiNewsList(String pop1, String pop2) {
            this.pop1 = pop1;
            this.pop2 = pop2;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String url = "https://www.reddit.com/search.json?q=" + pop1 + "&limit=100&show=all&sort=" + pop2;
                ParsedResponse p = Service.apiGetRedditNews(getActivity(), url);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<News>) p.o;
                } else {
                    msg = (String) p.o;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                error = true;
                msg = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mProgressDialog.dismiss();
            if (!error) {
                reddit_news_adapter = new Reddit_News_Adapter(getActivity(), mArrayList);
                rv_reddit_news.setAdapter(reddit_news_adapter);

            } else {
            }
        }
    }

}
