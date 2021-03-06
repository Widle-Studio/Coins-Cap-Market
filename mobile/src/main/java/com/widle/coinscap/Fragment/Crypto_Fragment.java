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

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import com.widle.coinscap.Adapter.Crypto_Adapter;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.News;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;

public class Crypto_Fragment extends Fragment {

    String type = "";

    public ProgressDialog mProgressDialog;

    ArrayList<News> mArrayList = new ArrayList<>();

    private RecyclerView rv_category;

    private Crypto_Adapter crypto_adapter;

    SwipeRefreshLayout mSwipeRefreshLayout;


    public Crypto_Fragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crypto_news, container, false);

        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange);


        rv_category = view.findViewById(R.id.rv_category);
        rv_category.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        type = getArguments().getString("type");
        if (type.equals("ANALYSIS")) {
            new apiCategory().execute();
        } else if (type.equals("BLOCKCHAIN")) {
            new apiCategory().execute();
        } else if (type.equals("EXCHANGES")) {
            new apiCategory().execute();
        } else if (type.equals("GOVERNMENT")) {
            new apiCategory().execute();
        } else if (type.equals("MINING")) {
            new apiCategory().execute();
        } else if (type.equals("ICOS")) {
            new apiCategory().execute();
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (type.equals("ANALYSIS")) {
                    new apiCategory().execute();
                } else if (type.equals("BLOCKCHAIN")) {
                    new apiCategory().execute();
                } else if (type.equals("EXCHANGES")) {
                    new apiCategory().execute();
                } else if (type.equals("GOVERNMENT")) {
                    new apiCategory().execute();
                } else if (type.equals("MINING")) {
                    new apiCategory().execute();
                } else if (type.equals("ICOS")) {
                    new apiCategory().execute();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }


    public class apiCategory extends AsyncTask<Void, Void, Void> {

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
                String url = "https://cryptocontrol.io/api/v1/public/news/category?language=en&key=72a7894c2969d6a3858ac0d1e70722e0";
                ParsedResponse p = Service.apiCategoryAnalysis(getActivity(), url, type);
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
                crypto_adapter = new Crypto_Adapter(getActivity(), mArrayList);
                rv_category.setAdapter(crypto_adapter);
            } else {
            }
        }
    }

}
