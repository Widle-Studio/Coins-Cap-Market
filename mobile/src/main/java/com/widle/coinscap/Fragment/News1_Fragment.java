package com.widle.coinscap.Fragment;


import android.app.ProgressDialog;
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
import android.widget.TextView;

import com.widle.coinscap.Adapter.News_Adapter;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.News;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;
import com.widle.coinscap.Utils.utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;



public class News1_Fragment extends Fragment {

    private RecyclerView rv_news;

    ArrayList<News> mArrayList = new ArrayList<>();

    News_Adapter news_adapter;

    private TextView tv_lbl;

    public ProgressDialog mProgressDialog;

    SwipeRefreshLayout mSwipeRefreshLayout;



    public News1_Fragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news1, container, false);

        rv_news = (RecyclerView) view.findViewById(R.id.rv_news);
        rv_news.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tv_lbl = (TextView) view.findViewById(R.id.tv_lbl);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange);

        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        if (utils.isNetworkAvailable(getActivity())) {
            rv_news.setVisibility(View.VISIBLE);
            tv_lbl.setVisibility(View.GONE);
            new apiNewsList().execute();
        } else {
            rv_news.setVisibility(View.GONE);
            tv_lbl.setVisibility(View.VISIBLE);
            tv_lbl.setText(R.string.err_no_internet);
        }


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new apiNewsList().execute();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }



    public class apiNewsList extends AsyncTask<Void, Void, Void> {

        boolean error;
        String msg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                ParsedResponse p = Service.apiGetNews(getActivity());
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
                news_adapter = new News_Adapter(getActivity(), mArrayList);
                rv_news.setAdapter(news_adapter);

            } else {
            }
        }
    }

}
