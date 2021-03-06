package com.widle.coinscap.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.widle.coinscap.Adapter.ICO_Live_Adapter;
import com.widle.coinscap.R;
import com.widle.coinscap.Utils.Model.ICO;
import com.widle.coinscap.Utils.ParsedResponse;
import com.widle.coinscap.Utils.Service;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class Live_Fragment extends Fragment {

    public ProgressDialog mProgressDialog;

    private RecyclerView rv_ico;

    String type = "";

    ArrayList<ICO> mArrayList = new ArrayList<>();

    ICO_Live_Adapter ico_live_adapter;


    public Live_Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ico_live, container, false);

        mProgressDialog = new ProgressDialog(this.getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setCancelable(false);

        rv_ico = view.findViewById(R.id.rv_ico);
        rv_ico.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        type = getArguments().getString("type");

        new apiICO().execute();

        return view;
    }



    public class apiICO extends AsyncTask<Void, Void, Void> {

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
                String url = "https://api.icowatchlist.com/public/v1/" + type;
                ParsedResponse p = Service.apiIcoList(getActivity(), url, type);
                error = p.error;
                if (!error) {
                    mArrayList = (ArrayList<ICO>) p.o;
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
                ico_live_adapter = new ICO_Live_Adapter(getActivity(), mArrayList, type);
                rv_ico.setAdapter(ico_live_adapter);
            } else {
            }
        }
    }

}
